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
package com.t2tierp.cte.servidor;

import com.t2tierp.padrao.java.Constantes;
import com.t2tierp.padrao.servidor.HibernateUtil;
import com.t2tierp.cte.java.CteCabecalhoVO;
import com.t2tierp.cte.java.CteCargaVO;
import com.t2tierp.cte.java.CteInformacaoNfOutrosVO;
import com.t2tierp.ged.java.ArquivoVO;
import com.t2tierp.nfe.java.NfeCabecalhoVO;
import com.t2tierp.nfe.java.NfeConfiguracaoVO;
import com.t2tierp.nfe.java.NfeNumeroVO;
import com.t2tierp.nfe.java.RespostaSefaz;
import com.t2tierp.padrao.java.Biblioteca;
import java.awt.Image;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.security.KeyStore;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.ImageIcon;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRXmlDataSource;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.openswing.swing.message.receive.java.ErrorResponse;
import org.openswing.swing.message.receive.java.Response;
import org.openswing.swing.message.receive.java.VOListResponse;
import org.openswing.swing.message.receive.java.VOResponse;
import org.openswing.swing.server.Action;
import org.openswing.swing.server.UserSessionParameters;

public class CteCabecalhoDetalheAction implements Action {

    public CteCabecalhoDetalheAction() {
    }

    public String getRequestName() {
        return "cteCabecalhoDetalheAction";
    }

    public Response executeCommand(Object inputPar, UserSessionParameters userSessionPars, HttpServletRequest request, HttpServletResponse response, HttpSession userSession, ServletContext context) {
        Object[] pars = (Object[]) inputPar;
        Integer acao = (Integer) pars[0];

        switch (acao) {
            case Constantes.LOAD: {
                return load(inputPar, userSessionPars, request, response, userSession, context);
            }
            case Constantes.INSERT: {
                return insert(inputPar, userSessionPars, request, response, userSession, context);
            }
            case Constantes.UPDATE: {
                return update(inputPar, userSessionPars, request, response, userSession, context);
            }
            case Constantes.DELETE: {
                return delete(inputPar, userSessionPars, request, response, userSession, context);
            }
            case 99: {
                return enviaCte(inputPar, context);
            }
            case 100: {
                return detalheNfe(inputPar, context);
            }
        }
        return null;
    }

    private Response load(Object inputPar, UserSessionParameters userSessionPars, HttpServletRequest request, HttpServletResponse response, HttpSession userSession, ServletContext context) {
        Session session = null;
        Object[] pars = (Object[]) inputPar;
        String pk = (String) pars[1];

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Criteria criteria = session.createCriteria(CteCabecalhoVO.class);
            criteria.add(Restrictions.eq("id", Integer.valueOf(pk)));

            CteCabecalhoVO cteCabecalho = (CteCabecalhoVO) criteria.uniqueResult();

            return new VOResponse(cteCabecalho);
        } catch (Exception ex) {
            ex.printStackTrace();
            return new ErrorResponse(ex.getMessage());
        } finally {
            try {
                session.close();
            } catch (Exception ex1) {
            }
        }
    }

    public Response insert(Object inputPar, UserSessionParameters userSessionPars, HttpServletRequest request, HttpServletResponse response, HttpSession userSession, ServletContext context) {
        Session session = null;
        try {
            Object[] pars = (Object[]) inputPar;
            CteCabecalhoVO cteCabecalho = (CteCabecalhoVO) pars[1];

            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();

            Date dataAtual = new Date();

            cteCabecalho.setUfEmitente(cteCabecalho.getEmpresa().getCodigoIbgeUf());
            cteCabecalho.setDataHoraEmissao(dataAtual);
            cteCabecalho.setCodigoMunicipioEnvio(cteCabecalho.getCteDestinatario().getCodigoMunicipio());

            cteCabecalho = numeroCte(cteCabecalho, session);
            cteCabecalho = configuraCte(cteCabecalho, session);

            cteCabecalho.getCteDestinatario().setCteCabecalho(cteCabecalho);
            cteCabecalho.getCteEmitente().setCteCabecalho(cteCabecalho);
            cteCabecalho.getCteRemetente().setCteCabecalho(cteCabecalho);
            cteCabecalho.getCteRodoviario().setCteCabecalho(cteCabecalho);
            
            cteCabecalho.setUfEnvio(cteCabecalho.getCteRemetente().getUf());
            cteCabecalho.setNomeMunicipioEnvio(cteCabecalho.getCteRemetente().getNomeMunicipio());
            cteCabecalho.setNomeMunicipioIniPrestacao(cteCabecalho.getCteRemetente().getNomeMunicipio());
            cteCabecalho.setUfIniPrestacao(cteCabecalho.getCteRemetente().getUf());
            cteCabecalho.setCodigoMunicipioIniPrestacao(cteCabecalho.getCteRemetente().getCodigoMunicipio());
            cteCabecalho.setCodigoMunicipioFimPrestacao(cteCabecalho.getCteDestinatario().getCodigoMunicipio());
            cteCabecalho.setUfFimPrestacao(cteCabecalho.getCteDestinatario().getUf());
            cteCabecalho.setNomeMunicipioFimPrestacao(cteCabecalho.getCteDestinatario().getNomeMunicipio());

            for (CteCargaVO c : cteCabecalho.getListaCteCarga()) {
                c.setCteCabecalho(cteCabecalho);
            }

            for (CteInformacaoNfOutrosVO c : cteCabecalho.getListaCteInformacaoNfOutros()) {
                c.setCteCabecalho(cteCabecalho);
            }
            
            session.save(cteCabecalho);

            session.getTransaction().commit();

            return new VOResponse(cteCabecalho);
        } catch (Exception ex) {
            ex.printStackTrace();
            if (session != null) {
                session.getTransaction().rollback();
            }
            return new ErrorResponse(ex.getMessage());
        } finally {
            try {
                if (session != null) {
                    session.close();
                }
            } catch (Exception ex1) {
                ex1.printStackTrace();
            }
        }
    }

    public Response update(Object inputPar, UserSessionParameters userSessionPars, HttpServletRequest request, HttpServletResponse response, HttpSession userSession, ServletContext context) {
        Session session = null;
        try {
            Object[] pars = (Object[]) inputPar;
            CteCabecalhoVO cteCabecalho = (CteCabecalhoVO) pars[2];

            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();

            for (CteCargaVO c : cteCabecalho.getListaCteCarga()) {
                c.setCteCabecalho(cteCabecalho);
            }

            for (CteInformacaoNfOutrosVO c : cteCabecalho.getListaCteInformacaoNfOutros()) {
                c.setCteCabecalho(cteCabecalho);
            }
            
            session.merge(cteCabecalho);

            session.getTransaction().commit();

            return new VOResponse(cteCabecalho);
        } catch (Exception ex) {
            ex.printStackTrace();
            if (session != null) {
                session.getTransaction().rollback();
            }
            return new ErrorResponse(ex.getMessage());
        } finally {
            try {
                if (session != null) {
                    session.close();
                }
            } catch (Exception ex1) {
                ex1.printStackTrace();
            }
        }
    }

    public Response delete(Object inputPar, UserSessionParameters userSessionPars, HttpServletRequest request, HttpServletResponse response, HttpSession userSession, ServletContext context) {
        return null;
    }

    private CteCabecalhoVO numeroCte(CteCabecalhoVO cteCabecalho, Session session) throws Exception {
        DecimalFormat formatoNumero = new DecimalFormat("000000000");
        DecimalFormat formatoCodigoNumerico = new DecimalFormat("00000000");
        SimpleDateFormat formatoAno = new SimpleDateFormat("yy");
        SimpleDateFormat formatoMes = new SimpleDateFormat("MM");

        Criteria criteria = session.createCriteria(NfeNumeroVO.class);
        NfeNumeroVO numero = (NfeNumeroVO) criteria.uniqueResult();
        if (numero == null) {
            numero = new NfeNumeroVO();
            numero.setEmpresa(cteCabecalho.getEmpresa());
            numero.setSerie("001");
            numero.setNumero(1);
        } else {
            numero.setNumero(numero.getNumero() + 1);
        }
        session.saveOrUpdate(numero);

        cteCabecalho.setNumero(formatoNumero.format(numero.getNumero()));
        cteCabecalho.setCodigoNumerico(formatoCodigoNumerico.format(numero.getNumero()));
        cteCabecalho.setSerie("000");

        cteCabecalho.setChaveAcesso("" + cteCabecalho.getEmpresa().getCodigoIbgeUf()
                + formatoAno.format(cteCabecalho.getDataHoraEmissao())
                + formatoMes.format(cteCabecalho.getDataHoraEmissao())
                + cteCabecalho.getEmpresa().getCnpj()
                + cteCabecalho.getModelo()
                + cteCabecalho.getSerie()
                + cteCabecalho.getNumero()
                + cteCabecalho.getTipoEmissao()
                + cteCabecalho.getCodigoNumerico());
        cteCabecalho.setDigitoChaveAcesso(Biblioteca.modulo11(cteCabecalho.getChaveAcesso()).toString());

        return cteCabecalho;
    }

    private CteCabecalhoVO configuraCte(CteCabecalhoVO cteCabecalho, Session session) throws Exception {
        Criteria criteria = session.createCriteria(NfeConfiguracaoVO.class);
        NfeConfiguracaoVO configuracao = (NfeConfiguracaoVO) criteria.uniqueResult();

        cteCabecalho.setAmbiente(configuracao.getWebserviceAmbiente());
        cteCabecalho.setProcessoEmissao(configuracao.getProcessoEmissao());
        cteCabecalho.setVersaoProcessoEmissao(configuracao.getVersaoProcessoEmissao());

        return cteCabecalho;
    }

    private Response detalheNfe(Object inputPar, ServletContext context) {
        Session session = null;
        Object[] pars = (Object[]) inputPar;
        Integer pk = (Integer) pars[1];

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Criteria criteria = session.createCriteria(NfeCabecalhoVO.class);
            criteria.add(Restrictions.eq("id", pk));

            NfeCabecalhoVO nfeCabecalho = (NfeCabecalhoVO) criteria.uniqueResult();

            return new VOListResponse(nfeCabecalho.getListaNfeDetalhe(), false, nfeCabecalho.getListaNfeDetalhe().size());
        } catch (Exception ex) {
            ex.printStackTrace();
            return new ErrorResponse(ex.getMessage());
        } finally {
            try {
                session.close();
            } catch (Exception ex1) {
            }
        }
    }
    
    public Response enviaCte(Object inputPar, ServletContext context) {
        Session session = null;
        Object[] pars = (Object[]) inputPar;
        Integer idCte = (Integer) pars[1];
        //EmpresaVO empresa = (EmpresaVO) pars[2];
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();

            Criteria criteria = session.createCriteria(CteCabecalhoVO.class);
            criteria.add(Restrictions.eq("id", idCte));
            CteCabecalhoVO cteCabecalho = (CteCabecalhoVO) criteria.uniqueResult();

            criteria = session.createCriteria(NfeConfiguracaoVO.class);
            NfeConfiguracaoVO nfeConfiguracao = (NfeConfiguracaoVO) criteria.uniqueResult();

            if (nfeConfiguracao == null) {
                throw new Exception("Configuração NF-e não definida.");
            }

            KeyStore ks = KeyStore.getInstance("PKCS12");
            char[] senha = nfeConfiguracao.getCertificadoDigitalSenha().toCharArray();
            ks.load(new FileInputStream(new File(nfeConfiguracao.getCertificadoDigitalCaminho())), senha);
            String alias = ks.aliases().nextElement();

            GerarXmlCte geraXmlCte = new GerarXmlCte();
            String xml = geraXmlCte.geraXmlEnvio(cteCabecalho, alias, ks, senha);

            try {
                ValidaXmlCte validaXmlNfe = new ValidaXmlCte();
                validaXmlNfe.validaXmlEnvio(xml, context);
            } catch (Exception e) {
                throw new Exception("Erro na validação do XML\n" + e.getMessage());
            }

            EnviaCte envia = new EnviaCte();
            Map mapResposta = envia.enviaCte(xml, alias, ks, senha, cteCabecalho.getUfEmitente().toString(), String.valueOf(cteCabecalho.getAmbiente()));

            Boolean autorizado = (Boolean) mapResposta.get("autorizado");
            RespostaSefaz respostaSefaz = new RespostaSefaz();
            respostaSefaz.setAutorizado(autorizado);
            respostaSefaz.setResposta((String) mapResposta.get("resposta"));
            respostaSefaz.setNumeroRecibo((String) mapResposta.get("numeroRecibo"));
            respostaSefaz.setXmlEnviNfe((String) mapResposta.get("xmlEnviCte"));

            if (autorizado) {
                String xmlProc = (String) mapResposta.get("xmlProc");
                salvaArquivos(cteCabecalho, xmlProc, context);
            }

            session.getTransaction().commit();

            return new VOResponse(respostaSefaz);
        } catch (Exception ex) {
            ex.printStackTrace();
            if (session != null) {
                session.getTransaction().rollback();
            }
            return new ErrorResponse(ex.getMessage());
        } finally {
            try {
                session.close();
            } catch (Exception ex1) {
            }
        }
    }

    private void salvaArquivos(CteCabecalhoVO cteCabecalho, String xml, ServletContext context) throws Exception {
        //salva o xml
        String caminhoArquivo = context.getRealPath("/cte/xml") + System.getProperty("file.separator") + cteCabecalho.getChaveAcesso() + cteCabecalho.getDigitoChaveAcesso();
        OutputStream outXml = new FileOutputStream(new File(caminhoArquivo + "-cteproc.xml"));
        outXml.write(xml.getBytes());
        outXml.close();

        //gera e salva o dacte
        Map map = new HashMap();
        Image image = new ImageIcon(this.getClass().getResource("/images/logo_t2ti.png")).getImage();
        map.put("Logo", image);

        JRXmlDataSource jrXmlDataSource = new JRXmlDataSource(caminhoArquivo + "-cteproc.xml", "/cteProc/CTe/infCTe/det");
        JasperPrint jp = JasperFillManager.fillReport(this.getClass().getResourceAsStream("/com/t2tierp/cte/dacte/dacte.jasper"), map, jrXmlDataSource);
        OutputStream outPdf = new FileOutputStream(new File(caminhoArquivo + ".pdf"));
        outPdf.write(JasperExportManager.exportReportToPdf(jp));
        outPdf.close();
    }

    public Response dacte(Object inputPar, ServletContext context) {
        Object[] pars = (Object[]) inputPar;
        CteCabecalhoVO cteCabecalho = (CteCabecalhoVO) pars[1];
        Session session = null;
        try {
            String caminhoArquivo = context.getRealPath("/cte/xml") + System.getProperty("file.separator") + cteCabecalho.getChaveAcesso() + cteCabecalho.getDigitoChaveAcesso() + ".pdf";
            File arquivoPdf = new File(caminhoArquivo);
            if (!arquivoPdf.exists()) {
                throw new Exception("Arquivo do dacte não localizado!");
            }

            ArquivoVO arquivo = new ArquivoVO();
            arquivo.setFile(Biblioteca.getBytesFromFile(arquivoPdf));

            return new VOResponse(arquivo);
        } catch (Exception ex) {
            ex.printStackTrace();
            if (session != null) {
                session.getTransaction().rollback();
            }
            return new ErrorResponse(ex.getMessage());
        } finally {
            try {
                session.close();
            } catch (Exception ex1) {
            }
        }
    }
    
}
