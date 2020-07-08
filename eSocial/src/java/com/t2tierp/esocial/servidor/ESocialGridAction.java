/*
 * The MIT License
 * 
 * Copyright: Copyright (C) 2014 T2Ti.COM
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 * 
 * The author may be contacted at: t2ti.com@gmail.com
 *
 * @author Claudio de Barros (T2Ti.com)
 * @version 2.0
 */
package com.t2tierp.esocial.servidor;

import com.t2tierp.cadastros.java.EmpresaEnderecoVO;
import com.t2tierp.cadastros.java.EmpresaVO;
import com.t2tierp.ged.java.ArquivoVO;
import com.t2tierp.nfe.java.NfeConfiguracaoVO;
import com.t2tierp.padrao.java.Biblioteca;
import com.t2tierp.padrao.servidor.HibernateUtil;
import java.io.File;
import java.io.FileInputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.KeyStore;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.openswing.swing.message.receive.java.ErrorResponse;
import org.openswing.swing.message.receive.java.Response;
import org.openswing.swing.message.receive.java.VOListResponse;
import org.openswing.swing.message.send.java.GridParams;
import org.openswing.swing.server.Action;
import org.openswing.swing.server.UserSessionParameters;

public class ESocialGridAction implements Action {

    @Override
    public String getRequestName() {
        return "eSocialGridAction";
    }

    @Override
    public Response executeCommand(Object inputPar, UserSessionParameters userSessionPars, HttpServletRequest request, HttpServletResponse response, HttpSession userSession, ServletContext context) {
        Session session = null;
        try {
            GridParams pars = (GridParams) inputPar;
            Date dataInicial = (Date) pars.getOtherGridParams().get("dataInicial");
            Date dataFinal = (Date) pars.getOtherGridParams().get("dataFinal");
            EmpresaVO empresa = (EmpresaVO) pars.getOtherGridParams().get("empresa");
            EmpresaEnderecoVO enderecoEmpresa = (EmpresaEnderecoVO) pars.getOtherGridParams().get("enderecoPrincipalEmpresa");
            Map<String, Boolean> arquivosGerar = (HashMap) pars.getOtherGridParams().get("arquivosGerar");;

            session = HibernateUtil.getSessionFactory().openSession();

            Criteria criteria = session.createCriteria(NfeConfiguracaoVO.class);
            NfeConfiguracaoVO nfeConfiguracao = (NfeConfiguracaoVO) criteria.uniqueResult();

            if (nfeConfiguracao == null) {
                throw new Exception("Configuração NF-e não definida.");
            }

            KeyStore ks = KeyStore.getInstance("PKCS12");
            char[] senha = nfeConfiguracao.getCertificadoDigitalSenha().toCharArray();
            ks.load(new FileInputStream(new File(nfeConfiguracao.getCertificadoDigitalCaminho())), senha);
            String alias = ks.aliases().nextElement();

            GeraXmlESocial geraXmlESocial = new GeraXmlESocial(alias, ks, senha);
            ValidaXmlESocial validaXmleSocial = new ValidaXmlESocial();

            File file = File.createTempFile("eSocial", ".xml");
            file.deleteOnExit();

            List<ArquivoVO> arquivos = new ArrayList<>();
            ArquivoVO arquivo;
            String xml;

            if (arquivosGerar.get("S1000")) {
                xml = geraXmlESocial.gerarESocial1000(empresa, enderecoEmpresa, dataInicial, dataFinal);

                try {
                    validaXmleSocial.validaXmlESocial1000(xml, context);
                } catch (Exception e) {
                    throw new Exception("Erro na validação do XML (S-1000)\n" + e.getMessage());
                }

                Files.write(Paths.get(file.toURI()), xml.getBytes("UTF-8"));
                arquivo = new ArquivoVO();
                arquivo.setFile(Biblioteca.getBytesFromFile(file));
                arquivo.setNome("eSocial_S1000");
                arquivo.setExtensao(".xml");

                arquivos.add(arquivo);
            }

            if (arquivosGerar.get("S1010")) {
                xml = geraXmlESocial.gerarESocial1010(empresa, enderecoEmpresa, dataInicial, dataFinal);

                try {
                    validaXmleSocial.validaXmlESocial1010(xml, context);
                } catch (Exception e) {
                    throw new Exception("Erro na validação do XML (S-1010)\n" + e.getMessage());
                }

                Files.write(Paths.get(file.toURI()), xml.getBytes("UTF-8"));
                arquivo = new ArquivoVO();
                arquivo.setFile(Biblioteca.getBytesFromFile(file));
                arquivo.setNome("eSocial_S1010");
                arquivo.setExtensao(".xml");

                arquivos.add(arquivo);
            }

            return new VOListResponse(arquivos, false, arquivos.size());
        } catch (Exception ex) {
            ex.printStackTrace();
            return new ErrorResponse(ex.getMessage());
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

}
