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
package com.t2tierp.nfe.servidor;

import br.inf.portalfiscal.nfe.procnfe.TNfeProc;
import com.t2tierp.cadastros.java.ContaCaixaVO;
import com.t2tierp.cadastros.java.EmpresaVO;
import com.t2tierp.cadastros.java.ProdutoVO;
import com.t2tierp.controleestoque.servidor.ControleEstoqueAction;
import com.t2tierp.financeiro.java.FinDocumentoOrigemVO;
import com.t2tierp.financeiro.java.FinLancamentoReceberVO;
import com.t2tierp.financeiro.java.FinParcelaReceberVO;
import com.t2tierp.financeiro.java.FinStatusParcelaVO;
import com.t2tierp.ged.java.ArquivoVO;
import com.t2tierp.padrao.java.Constantes;
import com.t2tierp.padrao.servidor.HibernateUtil;
import com.t2tierp.nfe.java.NfeCabecalhoVO;
import com.t2tierp.nfe.java.NfeConfiguracaoVO;
import com.t2tierp.nfe.java.NfeCteReferenciadoVO;
import com.t2tierp.nfe.java.NfeCupomFiscalReferenciadoVO;
import com.t2tierp.nfe.java.NfeDeclaracaoImportacaoVO;
import com.t2tierp.nfe.java.NfeDestinatarioVO;
import com.t2tierp.nfe.java.NfeDetEspecificoArmamentoVO;
import com.t2tierp.nfe.java.NfeDetEspecificoMedicamentoVO;
import com.t2tierp.nfe.java.NfeDetalheVO;
import com.t2tierp.nfe.java.NfeDuplicataVO;
import com.t2tierp.nfe.java.NfeImportacaoDetalheVO;
import com.t2tierp.nfe.java.NfeNfReferenciadaVO;
import com.t2tierp.nfe.java.NfeNumeroVO;
import com.t2tierp.nfe.java.NfeProdRuralReferenciadaVO;
import com.t2tierp.nfe.java.NfeReferenciadaVO;
import com.t2tierp.nfe.java.NfeTransporteReboqueVO;
import com.t2tierp.nfe.java.NfeTransporteVolumeLacreVO;
import com.t2tierp.nfe.java.NfeTransporteVolumeVO;
import com.t2tierp.nfe.java.RespostaSefaz;
import com.t2tierp.padrao.java.Biblioteca;
import com.t2tierp.padrao.java.Certificado;
import java.awt.Image;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.security.KeyStore;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.ImageIcon;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.Unmarshaller;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRXmlDataSource;
import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.MultiPartEmail;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.openswing.swing.message.receive.java.ErrorResponse;
import org.openswing.swing.message.receive.java.Response;
import org.openswing.swing.message.receive.java.VOListResponse;
import org.openswing.swing.message.receive.java.VOResponse;
import org.openswing.swing.server.Action;
import org.openswing.swing.server.UserSessionParameters;

public class NfeDetalheAction implements Action {

    public NfeDetalheAction() {
    }

    public String getRequestName() {
        return "nfeDetalheAction";
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
                return verificaProdutoCadastrado(inputPar, userSessionPars, request, response, userSession, context);
            }
            case 100: {
                return enviaNfe(inputPar, context);
            }
            case 101: {
                return danfe(inputPar, context);
            }
            case 102: {
                return cancelaNfe(inputPar, context);
            }
            case 103: {
                return enviaEmail(inputPar, context);
            }
            case 104: {
                return consultaEnvioNfe(inputPar, context);
            }
            case 105: {
                return cartaCorrecaoNfe(inputPar, context);
            }
            case 106: {
                return geraLancamentoReceber(inputPar, context);
            }
            case 107: {
                return etiqueta(inputPar, context);
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
            Criteria criteria = session.createCriteria(NfeCabecalhoVO.class);
            criteria.add(Restrictions.eq("id", Integer.valueOf(pk)));

            NfeCabecalhoVO nfeCabecalho = (NfeCabecalhoVO) criteria.uniqueResult();

            return new VOResponse(nfeCabecalho);
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
            NfeCabecalhoVO nfeCabecalho = (NfeCabecalhoVO) pars[1];

            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();

            if (nfeCabecalho.getVendaCabecalho().getId() == null) {
                nfeCabecalho.setVendaCabecalho(null);
            }

            Date dataAtual = new Date();

            nfeCabecalho.setUfEmitente(nfeCabecalho.getEmpresa().getCodigoIbgeUf());
            nfeCabecalho.setDataHoraEmissao(dataAtual);
            nfeCabecalho.setDataHoraEntradaSaida(dataAtual);
            nfeCabecalho.setCodigoMunicipio(nfeCabecalho.getDestinatario().getCodigoMunicipio());

            nfeCabecalho = numeroNfe(nfeCabecalho, session);

            nfeCabecalho = configuraNfe(nfeCabecalho, session);

            nfeCabecalho.getDestinatario().setNfeCabecalho(nfeCabecalho);
            nfeCabecalho.getDestinatario().setCodigoPais(1058);
            nfeCabecalho.getDestinatario().setNomePais("Brazil");

            int i = 0;
            for (NfeDetalheVO nfeDetalhe : nfeCabecalho.getListaNfeDetalhe()) {
                i++;
                nfeDetalhe.setNfeCabecalho(nfeCabecalho);
                nfeDetalhe.setNumeroItem(i);

                for (NfeDetEspecificoArmamentoVO armamento : nfeDetalhe.getListaArmamento()) {
                    armamento.setNfeDetalhe(nfeDetalhe);
                }

                for (NfeDeclaracaoImportacaoVO declaracaoImportacao : nfeDetalhe.getListaDeclaracaoImportacao()) {
                    declaracaoImportacao.setNfeDetalhe(nfeDetalhe);
                    for (NfeImportacaoDetalheVO importacaoDetalhe : declaracaoImportacao.getListaImportacaoDetalhe()) {
                        importacaoDetalhe.setNfeDeclaracaoImportacao(declaracaoImportacao);
                    }
                }

                for (NfeDetEspecificoMedicamentoVO medicamento : nfeDetalhe.getListaMedicamento()) {
                    medicamento.setNfeDetalhe(nfeDetalhe);
                }
            }

            for (NfeCupomFiscalReferenciadoVO cupom : nfeCabecalho.getListaCupomFiscalReferenciado()) {
                cupom.setNfeCabecalho(nfeCabecalho);
            }

            nfeCabecalho.getLocalEntrega().setNfeCabecalho(nfeCabecalho);

            nfeCabecalho.getLocalRetirada().setNfeCabecalho(nfeCabecalho);

            nfeCabecalho.getTransporte().setNfeCabecalho(nfeCabecalho);
            if (nfeCabecalho.getTransporte().getTransportadora().getId() == null) {
                nfeCabecalho.getTransporte().setTransportadora(null);
            }

            for (NfeTransporteReboqueVO reboque : nfeCabecalho.getTransporte().getListaTransporteReboque()) {
                reboque.setNfeTransporte(nfeCabecalho.getTransporte());
            }

            for (NfeTransporteVolumeVO volume : nfeCabecalho.getTransporte().getListaTransporteVolume()) {
                volume.setNfeTransporte(nfeCabecalho.getTransporte());

                for (NfeTransporteVolumeLacreVO lacre : volume.getListaTransporteVolumeLacre()) {
                    lacre.setNfeTransporteVolume(volume);
                }
            }

            nfeCabecalho.getFatura().setNfeCabecalho(nfeCabecalho);

            for (NfeDuplicataVO duplicata : nfeCabecalho.getListaDuplicata()) {
                duplicata.setNfeCabecalho(nfeCabecalho);
            }

            for (NfeReferenciadaVO nfeReferenciada : nfeCabecalho.getListaNfeReferenciada()) {
                nfeReferenciada.setNfeCabecalho(nfeCabecalho);
            }

            for (NfeNfReferenciadaVO nfeNfReferenciada : nfeCabecalho.getListaNfReferenciada()) {
                nfeNfReferenciada.setNfeCabecalho(nfeCabecalho);
            }

            for (NfeCteReferenciadoVO cteReferenciado : nfeCabecalho.getListaCteReferenciado()) {
                cteReferenciado.setNfeCabecalho(nfeCabecalho);
            }

            for (NfeProdRuralReferenciadaVO prodRuralReferenciada : nfeCabecalho.getListaProdRuralReferenciada()) {
                prodRuralReferenciada.setNfeCabecalho(nfeCabecalho);
            }

            session.save(nfeCabecalho);

            ControleEstoqueAction.atualizaEstoque(session, nfeCabecalho.getListaNfeDetalhe());

            session.getTransaction().commit();

            return new VOResponse(nfeCabecalho);
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
            NfeCabecalhoVO nfeCabecalho = (NfeCabecalhoVO) pars[1];

            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();

            if (nfeCabecalho.getVendaCabecalho().getId() == null) {
                nfeCabecalho.setVendaCabecalho(null);
            }

            if (nfeCabecalho.getTransporte().getTransportadora().getId() == null) {
                nfeCabecalho.getTransporte().setTransportadora(null);
            }

            Criteria criteria = session.createCriteria(NfeDetalheVO.class);
            criteria.add(Restrictions.eq("nfeCabecalho", nfeCabecalho));
            List<NfeDetalheVO> listaNfeDetOld = criteria.list();
            for (NfeDetalheVO detalhe : listaNfeDetOld) {
                ControleEstoqueAction.atualizaEstoque(session, detalhe.getProduto().getId(), detalhe.getQuantidadeComercial());
            }

            int i = 0;
            for (NfeDetalheVO detalhe : nfeCabecalho.getListaNfeDetalhe()) {
                i++;
                detalhe.setNfeCabecalho(nfeCabecalho);
                detalhe.setNumeroItem(i);
            }

            for (NfeTransporteReboqueVO reboque : nfeCabecalho.getTransporte().getListaTransporteReboque()) {
                reboque.setNfeTransporte(nfeCabecalho.getTransporte());
            }

            for (NfeTransporteVolumeVO volume : nfeCabecalho.getTransporte().getListaTransporteVolume()) {
                volume.setNfeTransporte(nfeCabecalho.getTransporte());

                for (NfeTransporteVolumeLacreVO lacre : volume.getListaTransporteVolumeLacre()) {
                    lacre.setNfeTransporteVolume(volume);
                }
            }

            for (NfeDuplicataVO duplicata : nfeCabecalho.getListaDuplicata()) {
                duplicata.setNfeCabecalho(nfeCabecalho);
            }

            for (NfeReferenciadaVO nfeReferenciada : nfeCabecalho.getListaNfeReferenciada()) {
                nfeReferenciada.setNfeCabecalho(nfeCabecalho);
            }

            for (NfeNfReferenciadaVO nfeNfReferenciada : nfeCabecalho.getListaNfReferenciada()) {
                nfeNfReferenciada.setNfeCabecalho(nfeCabecalho);
            }

            for (NfeCteReferenciadoVO cteReferenciado : nfeCabecalho.getListaCteReferenciado()) {
                cteReferenciado.setNfeCabecalho(nfeCabecalho);
            }

            for (NfeProdRuralReferenciadaVO prodRuralReferenciada : nfeCabecalho.getListaProdRuralReferenciada()) {
                prodRuralReferenciada.setNfeCabecalho(nfeCabecalho);
            }

            session.merge(nfeCabecalho);

            ControleEstoqueAction.atualizaEstoque(session, nfeCabecalho.getListaNfeDetalhe());

            session.getTransaction().commit();

            return new VOResponse(nfeCabecalho);
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

    public Response verificaProdutoCadastrado(Object inputPar, UserSessionParameters userSessionPars, HttpServletRequest request, HttpServletResponse response, HttpSession userSession, ServletContext context) {
        Session session = null;
        Object[] pars = (Object[]) inputPar;
        List<NfeDetalheVO> listaNfeDetalhe = (Vector) pars[1];

        try {
            session = HibernateUtil.getSessionFactory().openSession();

            Criteria criteria = session.createCriteria(ProdutoVO.class);

            ProdutoVO produto;
            for (int i = 0; i < listaNfeDetalhe.size(); i++) {
                criteria.add(Restrictions.eq("gtin", listaNfeDetalhe.get(i).getGtin()));
                produto = (ProdutoVO) criteria.uniqueResult();
                if (produto != null) {
                    listaNfeDetalhe.get(i).setProduto(produto);
                    listaNfeDetalhe.get(i).setProdutoCadastrado(true);
                } else {
                    listaNfeDetalhe.get(i).setProdutoCadastrado(false);
                }
            }

            return new VOListResponse(listaNfeDetalhe, false, listaNfeDetalhe.size());
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

    private NfeCabecalhoVO numeroNfe(NfeCabecalhoVO nfeCabecalho, Session session) throws Exception {
        DecimalFormat formatoNumero = new DecimalFormat("000000000");
        DecimalFormat formatoCodigoNumerico = new DecimalFormat("00000000");
        SimpleDateFormat formatoAno = new SimpleDateFormat("yy");
        SimpleDateFormat formatoMes = new SimpleDateFormat("MM");

        Criteria criteria = session.createCriteria(NfeNumeroVO.class);
        NfeNumeroVO numero = (NfeNumeroVO) criteria.uniqueResult();
        if (numero == null) {
            numero = new NfeNumeroVO();
            numero.setEmpresa(nfeCabecalho.getEmpresa());
            numero.setSerie("001");
            numero.setNumero(1);
        } else {
            numero.setNumero(numero.getNumero() + 1);
        }
        session.saveOrUpdate(numero);

        nfeCabecalho.setNumero(formatoNumero.format(numero.getNumero()));
        nfeCabecalho.setCodigoNumerico(formatoCodigoNumerico.format(numero.getNumero()));
        nfeCabecalho.setSerie(numero.getSerie());

        nfeCabecalho.setChaveAcesso("" + nfeCabecalho.getEmpresa().getCodigoIbgeUf()
                + formatoAno.format(nfeCabecalho.getDataHoraEmissao())
                + formatoMes.format(nfeCabecalho.getDataHoraEmissao())
                + nfeCabecalho.getEmpresa().getCnpj()
                + nfeCabecalho.getCodigoModelo()
                + nfeCabecalho.getSerie()
                + nfeCabecalho.getNumero()
                + "1"
                + nfeCabecalho.getCodigoNumerico());
        nfeCabecalho.setDigitoChaveAcesso(Biblioteca.modulo11(nfeCabecalho.getChaveAcesso()).toString());

        return nfeCabecalho;
    }

    private NfeCabecalhoVO configuraNfe(NfeCabecalhoVO nfeCabecalho, Session session) throws Exception {
        Criteria criteria = session.createCriteria(NfeConfiguracaoVO.class);
        NfeConfiguracaoVO configuracao = (NfeConfiguracaoVO) criteria.uniqueResult();

        nfeCabecalho.setAmbiente(configuracao.getWebserviceAmbiente());
        nfeCabecalho.setProcessoEmissao(configuracao.getProcessoEmissao());
        nfeCabecalho.setVersaoProcessoEmissao(configuracao.getVersaoProcessoEmissao());

        return nfeCabecalho;
    }

    public Response enviaNfe(Object inputPar, ServletContext context) {
        Session session = null;
        Object[] pars = (Object[]) inputPar;
        Integer idNfe = (Integer) pars[1];
        Certificado certificado = (Certificado) pars[2];
        EmpresaVO empresa = (EmpresaVO) pars[3];
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();

            Criteria criteria = session.createCriteria(NfeCabecalhoVO.class);
            criteria.add(Restrictions.eq("id", idNfe));
            NfeCabecalhoVO nfeCabecalho = (NfeCabecalhoVO) criteria.uniqueResult();

            if (nfeCabecalho.getStatusNota().intValue() == 5) {
                throw new Exception("Esta NF-e já foi autorizada. Envio não realizado");
            }
            if (nfeCabecalho.getStatusNota().intValue() == 6) {
                throw new Exception("Esta NF-e já foi cancelada. Envio não realizado");
            }

            KeyStore ks = KeyStore.getInstance("PKCS12");
            ks.load(new ByteArrayInputStream(certificado.getArquivo()), certificado.getSenha());
            String alias = ks.aliases().nextElement();
            char[] senha = certificado.getSenha();

            GeraXMLEnvio geraXmlNfe = new GeraXMLEnvio();
            String xml = geraXmlNfe.gerarXmlEnvio(empresa, nfeCabecalho, alias, ks, senha);

            try {
                ValidaXmlNfe validaXmlNfe = new ValidaXmlNfe();
                validaXmlNfe.validaXmlEnvio(xml, context);
            } catch (Exception e) {
                throw new Exception("Erro na validação do XML\n" + e.getMessage());
            }

            EnviaNfe envia = new EnviaNfe();
            Map mapResposta = envia.enviaNfe(xml, alias, ks, senha, nfeCabecalho.getUfEmitente().toString(), String.valueOf(nfeCabecalho.getAmbiente()));

            Boolean autorizado = (Boolean) mapResposta.get("autorizado");
            RespostaSefaz respostaSefaz = new RespostaSefaz();
            respostaSefaz.setAutorizado(autorizado);
            respostaSefaz.setResposta((String) mapResposta.get("resposta"));
            respostaSefaz.setNumeroRecibo((String) mapResposta.get("numeroRecibo"));
            respostaSefaz.setXmlEnviNfe((String) mapResposta.get("xmlEnviNfe"));

            if (autorizado) {
                String xmlProc = (String) mapResposta.get("xmlProc");
                salvaArquivos(nfeCabecalho, xmlProc, context, session);
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

    public Response consultaEnvioNfe(Object inputPar, ServletContext context) {
        Session session = null;
        Object[] pars = (Object[]) inputPar;
        Integer idNfe = (Integer) pars[1];
        String numeroRecibo = (String) pars[2];
        String xmlEnviNfe = (String) pars[3];
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();

            Criteria criteria = session.createCriteria(NfeCabecalhoVO.class);
            criteria.add(Restrictions.eq("id", idNfe));
            NfeCabecalhoVO nfeCabecalho = (NfeCabecalhoVO) criteria.uniqueResult();

            EnviaNfe envia = new EnviaNfe();
            Map mapResposta = envia.consultaEnvioNfe(numeroRecibo, xmlEnviNfe, nfeCabecalho.getUfEmitente().toString(), String.valueOf(nfeCabecalho.getAmbiente()));

            Boolean autorizado = (Boolean) mapResposta.get("autorizado");
            RespostaSefaz respostaSefaz = new RespostaSefaz();
            respostaSefaz.setAutorizado(autorizado);
            respostaSefaz.setResposta((String) mapResposta.get("resposta"));
            respostaSefaz.setNumeroRecibo((String) mapResposta.get("numeroRecibo"));
            respostaSefaz.setXmlEnviNfe((String) mapResposta.get("xmlEnviNfe"));

            if (autorizado) {
                String xmlProc = (String) mapResposta.get("xmlProc");
                salvaArquivos(nfeCabecalho, xmlProc, context, session);
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

    private void salvaArquivos(NfeCabecalhoVO nfeCabecalho, String xml, ServletContext context, Session session) throws Exception {
        nfeCabecalho.setStatusNota(5);
        if (nfeCabecalho.getVendaCabecalho() == null || nfeCabecalho.getVendaCabecalho().getId() == null) {
            nfeCabecalho.setVendaCabecalho(null);
        }

        session.merge(nfeCabecalho);

        //salva o xml
        String caminhoArquivo = context.getRealPath("/nfe/xml") + System.getProperty("file.separator") + nfeCabecalho.getChaveAcesso() + nfeCabecalho.getDigitoChaveAcesso();
        OutputStream outXml = new FileOutputStream(new File(caminhoArquivo + "-nfeproc.xml"));
        outXml.write(xml.getBytes());
        outXml.close();

        //gera e salva o danfe
        Map map = new HashMap();
        Image image = new ImageIcon(this.getClass().getResource("/images/logo_t2ti.png")).getImage();
        map.put("Logo", image);

        JRXmlDataSource jrXmlDataSource = new JRXmlDataSource(caminhoArquivo + "-nfeproc.xml", "/nfeProc/NFe/infNFe/det");
        JasperPrint jp = JasperFillManager.fillReport(this.getClass().getResourceAsStream("/com/t2tierp/nfe/danfe/danfeR.jasper"), map, jrXmlDataSource);
        OutputStream outPdf = new FileOutputStream(new File(caminhoArquivo + ".pdf"));
        outPdf.write(JasperExportManager.exportReportToPdf(jp));
        outPdf.close();
    }

    public Response danfe(Object inputPar, ServletContext context) {
        Object[] pars = (Object[]) inputPar;
        NfeCabecalhoVO nfeCabecalho = (NfeCabecalhoVO) pars[1];
        Session session = null;
        try {
            if (nfeCabecalho.getStatusNota().intValue() != 5) {
                throw new Exception("NF-e não autorizada. Impressão do Danfe não permitida!");
            }

            String caminhoArquivo = context.getRealPath("/nfe/xml") + System.getProperty("file.separator") + nfeCabecalho.getChaveAcesso() + nfeCabecalho.getDigitoChaveAcesso() + ".pdf";
            File arquivoPdf = new File(caminhoArquivo);
            if (!arquivoPdf.exists()) {
                throw new Exception("Arquivo do danfe não localizado!");
            }

            if (nfeCabecalho.getVendaCabecalho().getId() == null) {
                nfeCabecalho.setVendaCabecalho(null);
            }
            if (nfeCabecalho.getTransporte().getTransportadora().getId() == null) {
                nfeCabecalho.getTransporte().setTransportadora(null);
            }

            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.update(nfeCabecalho);
            session.getTransaction().commit();

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

    public Response cancelaNfe(Object inputPar, ServletContext context) {
        Session session = null;
        Object[] pars = (Object[]) inputPar;
        Integer idNfe = (Integer) pars[1];
        Certificado certificado = (Certificado) pars[2];
        String justificativa = (String) pars[3];
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();

            Criteria criteria = session.createCriteria(NfeCabecalhoVO.class);
            criteria.add(Restrictions.eq("id", idNfe));
            NfeCabecalhoVO nfeCabecalho = (NfeCabecalhoVO) criteria.uniqueResult();

            if (nfeCabecalho.getStatusNota() == 6) {
                throw new Exception("NF-e já cancelada. Cancelamento não permitido!");
            }
            if (nfeCabecalho.getStatusNota() != 5) {
                throw new Exception("NF-e não autorizada. Cancelamento não permitido!");
            }

            String caminhoArquivo = context.getRealPath("/nfe/xml") + System.getProperty("file.separator") + nfeCabecalho.getChaveAcesso() + nfeCabecalho.getDigitoChaveAcesso() + "-nfeproc.xml";
            File arquivoXml = new File(caminhoArquivo);
            if (!arquivoXml.exists()) {
                throw new Exception("Arquivo XML da NF-e não localizado!");
            }

            JAXBContext jc = JAXBContext.newInstance("br.inf.portalfiscal.nfe.procnfe");
            Unmarshaller unmarshaller = jc.createUnmarshaller();

            JAXBElement<TNfeProc> element = (JAXBElement) unmarshaller.unmarshal(arquivoXml);
            String protocolo = element.getValue().getProtNFe().getInfProt().getNProt();

            if (justificativa.trim().equals("")) {
                throw new Exception("É necessário informar uma justificativa para o cancelamento da NF-e.");
            }
            if (justificativa.trim().length() < 15) {
                throw new Exception("A justificativa deve ter no mínimo 15 caracteres.");
            }
            if (justificativa.trim().length() > 255) {
                throw new Exception("A justificativa deve ter no máximo 255 caracteres.");
            }

            KeyStore ks = KeyStore.getInstance("PKCS12");
            ks.load(new ByteArrayInputStream(certificado.getArquivo()), certificado.getSenha());
            String alias = ks.aliases().nextElement();
            char[] senha = certificado.getSenha();

            CancelaNfe cancelaNfe = new CancelaNfe();
            Map dadosCancelamento = cancelaNfe.cancelaNfe(alias, ks, senha, nfeCabecalho.getUfEmitente().toString(), String.valueOf(nfeCabecalho.getAmbiente()), nfeCabecalho.getChaveAcesso() + nfeCabecalho.getDigitoChaveAcesso(), protocolo, justificativa.trim(), nfeCabecalho.getEmpresa().getCnpj());

            RespostaSefaz respostaSefaz = new RespostaSefaz();
            respostaSefaz.setCancelado((Boolean) dadosCancelamento.get("nfeCancelada"));

            String resposta = "";
            if (respostaSefaz.isCancelado()) {
                //salva o xml
                caminhoArquivo = context.getRealPath("/nfe/xml") + System.getProperty("file.separator") + nfeCabecalho.getChaveAcesso() + nfeCabecalho.getDigitoChaveAcesso() + "-nfeCanc.xml";
                OutputStream out = new FileOutputStream(new File(caminhoArquivo));
                out.write(((String) dadosCancelamento.get("xmlCancelamento")).getBytes());
                out.close();

                nfeCabecalho.setStatusNota(6);
                session.merge(nfeCabecalho);

                //atualiza o estoque
                for (NfeDetalheVO nfeDetalhe : nfeCabecalho.getListaNfeDetalhe()) {
                    ControleEstoqueAction.atualizaEstoque(session, nfeDetalhe.getProduto().getId(), nfeDetalhe.getQuantidadeComercial());
                }

                resposta += "NF-e Cancelada com sucesso";
            } else {
                resposta += "A NF-e NÃO foi cancelada.";
            }
            resposta += "\n" + (String) dadosCancelamento.get("motivo1");
            resposta += "\n" + (String) dadosCancelamento.get("motivo2");

            respostaSefaz.setResposta(resposta);

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

    public Response enviaEmail(Object inputPar, ServletContext context) {
        Session session = null;
        Object[] pars = (Object[]) inputPar;
        NfeCabecalhoVO nfeCabecalho = (NfeCabecalhoVO) pars[1];
        NfeDestinatarioVO nfeDestinatario = nfeCabecalho.getDestinatario();

        try {
            if (nfeCabecalho.getStatusNota().intValue() != 5) {
                throw new Exception("NF-e não autorizada. Envio de email não permitido!");
            }

            session = HibernateUtil.getSessionFactory().openSession();

            Criteria criteria = session.createCriteria(NfeConfiguracaoVO.class);
            NfeConfiguracaoVO configuracao = (NfeConfiguracaoVO) criteria.uniqueResult();

            if (configuracao == null) {
                return new ErrorResponse("Configuração NFe não definida");
            }
            if (configuracao.getEmailAssunto() == null
                    || configuracao.getEmailSenha() == null
                    || configuracao.getEmailServidorSmtp() == null
                    || configuracao.getEmailTexto() == null
                    || configuracao.getEmailUsuario() == null) {
                return new ErrorResponse("Configuração de envio de email não definida");
            }

            MultiPartEmail email = new MultiPartEmail();
            email.setHostName(configuracao.getEmailServidorSmtp());
            email.setFrom(configuracao.getEmpresa().getEmail());
            email.addTo(nfeDestinatario.getEmail());
            email.setSubject(configuracao.getEmailAssunto());
            email.setMsg(configuracao.getEmailTexto());

            email.setAuthentication(configuracao.getEmailUsuario(), configuracao.getEmailSenha());
            if (configuracao.getEmailPorta() != null) {
                if (configuracao.getEmailAutenticaSsl() != null && configuracao.getEmailAutenticaSsl().equals("S")) {
                    email.setSSLOnConnect(true);
                    email.setSslSmtpPort(configuracao.getEmailPorta().toString());
                } else {
                    email.setSmtpPort(configuracao.getEmailPorta());
                }
            }

            EmailAttachment anexo = new EmailAttachment();
            anexo.setPath(context.getRealPath("/nfe/xml") + System.getProperty("file.separator") + nfeCabecalho.getChaveAcesso() + nfeCabecalho.getDigitoChaveAcesso() + "-nfeproc.xml");
            anexo.setDisposition(EmailAttachment.ATTACHMENT);
            anexo.setDescription("Nota Fiscal Eletronica");
            anexo.setName(nfeCabecalho.getChaveAcesso() + nfeCabecalho.getDigitoChaveAcesso() + "-nfeproc.xml");

            EmailAttachment anexo2 = new EmailAttachment();
            anexo2.setPath(context.getRealPath("/nfe/xml") + System.getProperty("file.separator") + nfeCabecalho.getChaveAcesso() + nfeCabecalho.getDigitoChaveAcesso() + ".pdf");
            anexo2.setDisposition(EmailAttachment.ATTACHMENT);
            anexo2.setDescription("Nota Fiscal Eletronica");
            anexo2.setName(nfeCabecalho.getChaveAcesso() + nfeCabecalho.getDigitoChaveAcesso() + ".pdf");

            email.attach(anexo);
            email.attach(anexo2);

            email.send();

            return new VOResponse(nfeCabecalho);
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

    public Response cartaCorrecaoNfe(Object inputPar, ServletContext context) {
        Session session = null;
        Object[] pars = (Object[]) inputPar;
        Integer idNfe = (Integer) pars[1];
        Certificado certificado = (Certificado) pars[2];
        String justificativa = (String) pars[3];
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();

            Criteria criteria = session.createCriteria(NfeCabecalhoVO.class);
            criteria.add(Restrictions.eq("id", idNfe));
            NfeCabecalhoVO nfeCabecalho = (NfeCabecalhoVO) criteria.uniqueResult();

            if (nfeCabecalho.getStatusNota() == 6) {
                throw new Exception("NF-e já cancelada. Correção não permitida!");
            }
            if (nfeCabecalho.getStatusNota() != 5) {
                throw new Exception("NF-e não autorizada. Correção não permitida!");
            }

            String caminhoArquivo = context.getRealPath("/nfe/xml") + System.getProperty("file.separator") + nfeCabecalho.getChaveAcesso() + nfeCabecalho.getDigitoChaveAcesso() + "-nfeproc.xml";
            File arquivoXml = new File(caminhoArquivo);
            if (!arquivoXml.exists()) {
                throw new Exception("Arquivo XML da NF-e não localizado!");
            }

            JAXBContext jc = JAXBContext.newInstance("br.inf.portalfiscal.nfe.procnfe");
            Unmarshaller unmarshaller = jc.createUnmarshaller();

            JAXBElement<TNfeProc> element = (JAXBElement) unmarshaller.unmarshal(arquivoXml);
            String protocolo = element.getValue().getProtNFe().getInfProt().getNProt();

            if (justificativa.trim().equals("")) {
                throw new Exception("É necessário informar uma justificativa para a correção da NF-e.");
            }
            if (justificativa.trim().length() < 15) {
                throw new Exception("A justificativa deve ter no mínimo 15 caracteres.");
            }
            if (justificativa.trim().length() > 255) {
                throw new Exception("A justificativa deve ter no máximo 255 caracteres.");
            }

            KeyStore ks = KeyStore.getInstance("PKCS12");
            ks.load(new ByteArrayInputStream(certificado.getArquivo()), certificado.getSenha());
            String alias = ks.aliases().nextElement();
            char[] senha = certificado.getSenha();

            CartaCorrecaoNfe cartaCorrecaoNfe = new CartaCorrecaoNfe();
            Map dadosCancelamento = cartaCorrecaoNfe.cartaCorrecao(alias, ks, senha, nfeCabecalho.getUfEmitente().toString(), String.valueOf(nfeCabecalho.getAmbiente()), nfeCabecalho.getChaveAcesso() + nfeCabecalho.getDigitoChaveAcesso(), protocolo, justificativa.trim(), nfeCabecalho.getEmpresa().getCnpj());

            RespostaSefaz respostaSefaz = new RespostaSefaz();
            respostaSefaz.setCancelado((Boolean) dadosCancelamento.get("nfeCorrigida"));

            String resposta = "";
            if (respostaSefaz.isCancelado()) {
                //salva o xml
                caminhoArquivo = context.getRealPath("/nfe/xml") + System.getProperty("file.separator") + nfeCabecalho.getChaveAcesso() + nfeCabecalho.getDigitoChaveAcesso() + "-nfeCorrecao.xml";
                OutputStream out = new FileOutputStream(new File(caminhoArquivo));
                out.write(((String) dadosCancelamento.get("xmlCorrecao")).getBytes());
                out.close();

                nfeCabecalho.setStatusNota(6);
                session.merge(nfeCabecalho);

                //atualiza o estoque
                for (NfeDetalheVO nfeDetalhe : nfeCabecalho.getListaNfeDetalhe()) {
                    ControleEstoqueAction.atualizaEstoque(session, nfeDetalhe.getProduto().getId(), nfeDetalhe.getQuantidadeComercial());
                }

                resposta += "Carta de Correção enviada com sucesso";
            } else {
                resposta += "Carta de Correção NÃO enviada.";
            }
            resposta += "\n" + (String) dadosCancelamento.get("motivo1");
            resposta += "\n" + (String) dadosCancelamento.get("motivo2");

            respostaSefaz.setResposta(resposta);

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

    public Response geraLancamentoReceber(Object inputPar, ServletContext context) {
        Session session = null;
        Object[] pars = (Object[]) inputPar;
        Integer idNfe = (Integer) pars[1];
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();

            Criteria criteria = session.createCriteria(NfeCabecalhoVO.class);
            criteria.add(Restrictions.eq("id", idNfe));
            NfeCabecalhoVO nfeCabecalho = (NfeCabecalhoVO) criteria.uniqueResult();

            //Exercicio: Implemente este método de acordo com a necessidade de do seu cliente
            //01-Pergunte de ele quer lançar no contas a receber após a emissão da nota
            //01-Deixe uma opção na tela para mandar os dados para o financeiro
            //03-Deixe a opção de capturar esses dados no financeiro apenas
            //04-Realize o lançamento sem que o usuário da NF-e saiba o que está ocorendo e deixe o restante a cargo do profissional do setor financeiro.
            //
            //Corrija as informações estáticas que estão no código.
            FinDocumentoOrigemVO documentoOrigem = new FinDocumentoOrigemVO();
            documentoOrigem.setId(32);
            FinStatusParcelaVO statusParcela = new FinStatusParcelaVO();
            statusParcela.setId(1);
            ContaCaixaVO contaCaixa = new ContaCaixaVO();
            contaCaixa.setId(1);

            FinLancamentoReceberVO lancamentoReceber = new FinLancamentoReceberVO();
            lancamentoReceber.setCliente(nfeCabecalho.getCliente());
            lancamentoReceber.setFinDocumentoOrigem(documentoOrigem);
            lancamentoReceber.setQuantidadeParcela(1);
            lancamentoReceber.setValorTotal(nfeCabecalho.getValorTotal());
            lancamentoReceber.setValorAReceber(nfeCabecalho.getValorTotal());
            lancamentoReceber.setDataLancamento(nfeCabecalho.getDataHoraEmissao());
            lancamentoReceber.setNumeroDocumento("NF-e: " + nfeCabecalho.getNumero());
            lancamentoReceber.setPrimeiroVencimento(nfeCabecalho.getDataHoraEmissao());
            lancamentoReceber.setIntervaloEntreParcelas(30);

            FinParcelaReceberVO parcelaReceberVO = new FinParcelaReceberVO();
            parcelaReceberVO.setFinLancamentoReceber(lancamentoReceber);
            parcelaReceberVO.setFinStatusParcela(statusParcela);
            parcelaReceberVO.setContaCaixa(contaCaixa);
            parcelaReceberVO.setDataEmissao(nfeCabecalho.getDataHoraEmissao());
            parcelaReceberVO.setDataVencimento(nfeCabecalho.getDataHoraEmissao());
            parcelaReceberVO.setValor(nfeCabecalho.getValorTotal());

            session.save(lancamentoReceber);

            session.save(parcelaReceberVO);

            session.getTransaction().commit();

            return new VOResponse(nfeCabecalho);
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

    public Response etiqueta(Object inputPar, ServletContext context) {
        Object[] pars = (Object[]) inputPar;
        NfeCabecalhoVO nfeCabecalho = (NfeCabecalhoVO) pars[1];
        try {
            if (nfeCabecalho.getStatusNota() != 5) {
                throw new Exception("NF-e não autorizada. Geração de Etiqueta não permitida!");
            }

            File etiqueta = File.createTempFile("etiqueta", ".pdf");

            Map map = new HashMap();
            Image image = new ImageIcon(context.getRealPath("/imagens/logo_t2ti.png")).getImage();
            map.put("LOGO", image);
            map.put("EMPRESA", nfeCabecalho.getEmpresa().getNomeFantasia());
            map.put("DESCRICAO", "Produtos Diversos");
            map.put("CONTEUDO", "000457092");
            map.put("QUANTIDADE", nfeCabecalho.getListaNfeDetalhe().size());
            map.put("NUMERO", nfeCabecalho.getChaveAcesso());

            JasperPrint jp = JasperFillManager.fillReport(context.getRealPath("/nfe/etiqueta/EtiquetaNFe.jasper"), map, new JREmptyDataSource());
            OutputStream outPdf = new FileOutputStream(etiqueta);
            outPdf.write(JasperExportManager.exportReportToPdf(jp));
            outPdf.close();

            ArquivoVO arquivo = new ArquivoVO();
            arquivo.setFile(Biblioteca.getBytesFromFile(etiqueta));

            return new VOResponse(arquivo);
        } catch (Exception ex) {
            ex.printStackTrace();
            return new ErrorResponse(ex.getMessage());
        }
    }

}
