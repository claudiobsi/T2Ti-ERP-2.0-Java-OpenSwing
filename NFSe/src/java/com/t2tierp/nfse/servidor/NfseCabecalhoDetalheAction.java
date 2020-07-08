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
package com.t2tierp.nfse.servidor;

import com.t2tierp.cadastros.java.EmpresaVO;
import com.t2tierp.nfe.java.NfeConfiguracaoVO;
import com.t2tierp.nfe.java.RespostaSefaz;
import com.t2tierp.padrao.java.Constantes;
import com.t2tierp.padrao.servidor.HibernateUtil;
import com.t2tierp.nfse.java.NfseCabecalhoVO;
import com.t2tierp.nfse.java.NfseDetalheVO;
import com.t2tierp.nfse.java.NfseIntermediarioVO;
import com.t2tierp.nfse.java.NfseListaServicoVO;
import com.t2tierp.ordemservico.java.OsAberturaVO;
import com.t2tierp.ordemservico.java.OsProdutoServicoVO;
import com.t2tierp.padrao.java.Biblioteca;
import com.t2tierp.tributacao.java.TributIssVO;
import java.io.File;
import java.io.FileInputStream;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.KeyStore;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.openswing.swing.message.receive.java.ErrorResponse;
import org.openswing.swing.message.receive.java.Response;
import org.openswing.swing.message.receive.java.VOListResponse;
import org.openswing.swing.message.receive.java.VOResponse;
import org.openswing.swing.server.Action;
import org.openswing.swing.server.UserSessionParameters;

public class NfseCabecalhoDetalheAction implements Action {

    public NfseCabecalhoDetalheAction() {
    }

    public String getRequestName() {
        return "nfseCabecalhoDetalheAction";
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
            case 97: {
                return calculaItensNfse(inputPar, userSessionPars, request, response, userSession, context);
            }
            case 98: {
                return enviaNfse(inputPar, userSessionPars, request, response, userSession, context);
            }
            case 99: {
                return consultaEnvioNfse(inputPar, userSessionPars, request, response, userSession, context);
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
            Criteria criteria = session.createCriteria(NfseCabecalhoVO.class);
            criteria.add(Restrictions.eq("id", Integer.valueOf(pk)));

            NfseCabecalhoVO nfseCabecalho = (NfseCabecalhoVO) criteria.uniqueResult();

            return new VOResponse(nfseCabecalho);
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
            NfseCabecalhoVO nfseCabecalho = (NfseCabecalhoVO) pars[1];
            Date dataAtual = new Date();

            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();

            for (NfseDetalheVO d : nfseCabecalho.getListaNfseDetalhe()) {
                d.setNfseCabecalho(nfseCabecalho);
            }

            for (NfseIntermediarioVO d : nfseCabecalho.getListaNfseIntermediario()) {
                d.setNfseCabecalho(nfseCabecalho);
            }

            session.save(nfseCabecalho);

            nfseCabecalho.setNumeroRps(new DecimalFormat("0000000000").format(nfseCabecalho.getId()));
            nfseCabecalho.setSerieRps("T2Ti");
            nfseCabecalho.setDataEmissaoRps(dataAtual);

            session.update(nfseCabecalho);

            session.getTransaction().commit();

            return new VOResponse(nfseCabecalho);
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
            NfseCabecalhoVO nfseCabecalho = (NfseCabecalhoVO) pars[2];

            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();

            for (NfseDetalheVO d : nfseCabecalho.getListaNfseDetalhe()) {
                d.setNfseCabecalho(nfseCabecalho);
            }

            for (NfseIntermediarioVO d : nfseCabecalho.getListaNfseIntermediario()) {
                d.setNfseCabecalho(nfseCabecalho);
            }

            session.merge(nfseCabecalho);

            session.getTransaction().commit();

            return new VOResponse(nfseCabecalho);
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

    private Response calculaItensNfse(Object inputPar, UserSessionParameters userSessionPars, HttpServletRequest request, HttpServletResponse response, HttpSession userSession, ServletContext context) {
        Session session = null;
        try {
            Object[] pars = (Object[]) inputPar;
            String numeroOS = (String) pars[1];
            EmpresaVO empresa = (EmpresaVO) pars[2];

            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();

            Criteria criteria = session.createCriteria(OsAberturaVO.class);
            criteria.add(Restrictions.eq("numero", numeroOS));
            OsAberturaVO osAbertura = (OsAberturaVO) criteria.uniqueResult();

            List<NfseDetalheVO> listaNfseDetalhe = new ArrayList<>();

            for (OsProdutoServicoVO s : osAbertura.getListaOsProdutoServico()) {
                if (osAbertura.getCliente().getTributOperacaoFiscal() == null) {
                    throw new Exception("Operação Fiscal não definida para o cliente.");
                }

                criteria = session.createCriteria(TributIssVO.class);
                criteria.add(Restrictions.eq("tributOperacaoFiscal", osAbertura.getCliente().getTributOperacaoFiscal()));
                TributIssVO iss = (TributIssVO) criteria.uniqueResult();

                if (iss == null) {
                    throw new Exception("Configuração ISS não localizada.");
                }

                criteria = session.createCriteria(NfseListaServicoVO.class);
                criteria.add(Restrictions.eq("id", iss.getItemListaServico()));
                NfseListaServicoVO nfseListaServico = (NfseListaServicoVO) criteria.uniqueResult();

                NfseDetalheVO nfseDetalhe = new NfseDetalheVO();
                nfseDetalhe.setDiscriminacao(s.getProduto().getDescricao());
                nfseDetalhe.setNfseListaServico(nfseListaServico);
                //nfseDetalhe.setCodigoCnae();
                nfseDetalhe.setCodigoTributacaoMunicipio("010100188");
                nfseDetalhe.setIssRetido("N");
                nfseDetalhe.setMunicipioPrestacao(empresa.getCodigoIbgeCidade());
                nfseDetalhe.setOutrasRetencoes(BigDecimal.ZERO);
                nfseDetalhe.setValorCofins(BigDecimal.ZERO);
                nfseDetalhe.setValorCredito(BigDecimal.ZERO);
                nfseDetalhe.setValorCsll(BigDecimal.ZERO);
                nfseDetalhe.setValorDeducoes(BigDecimal.ZERO);
                nfseDetalhe.setValorDescontoCondicionado(BigDecimal.ZERO);
                nfseDetalhe.setValorInss(BigDecimal.ZERO);
                nfseDetalhe.setValorIr(BigDecimal.ZERO);
                nfseDetalhe.setValorIssRetido(BigDecimal.ZERO);
                nfseDetalhe.setValorLiquido(BigDecimal.ZERO);
                nfseDetalhe.setValorPis(BigDecimal.ZERO);

                nfseDetalhe.setValorBaseCalculo(s.getValorTotal());
                nfseDetalhe.setValorDescontoIncondicionado(s.getValorDesconto());
                nfseDetalhe.setValorServicos(s.getValorTotal());
                nfseDetalhe.setValorIss(Biblioteca.multiplica(nfseDetalhe.getValorServicos(), iss.getAliquotaPorcento()));

                listaNfseDetalhe.add(nfseDetalhe);
            }
            //(ValorServicos - ValorPIS - ValorCOFINS - ValorINSS - ValorIR - ValorCSLL - OutrasRetençoes - ValorISSRetido -  DescontoIncondicionado - DescontoCondicionado)

            return new VOListResponse(listaNfseDetalhe, false, listaNfseDetalhe.size());
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

    private Response enviaNfse(Object inputPar, UserSessionParameters userSessionPars, HttpServletRequest request, HttpServletResponse response, HttpSession userSession, ServletContext context) {
        Session session = null;
        try {
            Object[] pars = (Object[]) inputPar;
            NfseCabecalhoVO nfseCabecalho = (NfseCabecalhoVO) pars[1];

            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();

            Criteria criteria = session.createCriteria(NfeConfiguracaoVO.class);
            NfeConfiguracaoVO nfeConfiguracao = (NfeConfiguracaoVO) criteria.uniqueResult();

            if (nfeConfiguracao == null) {
                throw new Exception("Configuração NF-e não definida.");
            }

            KeyStore ks = KeyStore.getInstance("PKCS12");
            char[] senha = nfeConfiguracao.getCertificadoDigitalSenha().toCharArray();
            ks.load(new FileInputStream(new File(nfeConfiguracao.getCertificadoDigitalCaminho())), senha);
            String alias = ks.aliases().nextElement();

            GeraXmlRPS geraXmlRps = new GeraXmlRPS();
            String xml = geraXmlRps.geraXml(nfseCabecalho, alias, ks, senha);

            try {
                ValidaXmlNfse validaXmlNfse = new ValidaXmlNfse();
                validaXmlNfse.validaXmlEnvio(xml, context);
            } catch (Exception e) {
                throw e;
            }

            EnviaRPS enviaRps = new EnviaRPS();
            Map map = enviaRps.enviaRPS(xml, alias, ks, senha);

            String resposta = (String) map.get("mensagem");

            String protocolo = (String) map.get("protocolo");
            if (!protocolo.isEmpty()) {
                String pastaXml = context.getRealPath("/nfse/xml") + System.getProperty("file.separator");

                Files.write(Paths.get(new File(pastaXml + nfseCabecalho.getNumeroRps() + ".protocolo").toURI()), protocolo.getBytes("UTF-8"));

                Thread.sleep(5000);
                resposta += consultaEnvioNfse(nfseCabecalho, ks, senha, alias, session, context);
            }

            RespostaSefaz respostaSefaz = new RespostaSefaz();
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
                if (session != null) {
                    session.close();
                }
            } catch (Exception ex1) {
                ex1.printStackTrace();
            }
        }
    }

    private Response consultaEnvioNfse(Object inputPar, UserSessionParameters userSessionPars, HttpServletRequest request, HttpServletResponse response, HttpSession userSession, ServletContext context) {
        Session session = null;
        try {
            Object[] pars = (Object[]) inputPar;
            NfseCabecalhoVO nfseCabecalho = (NfseCabecalhoVO) pars[1];

            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();

            Criteria criteria = session.createCriteria(NfeConfiguracaoVO.class);
            NfeConfiguracaoVO nfeConfiguracao = (NfeConfiguracaoVO) criteria.uniqueResult();

            if (nfeConfiguracao == null) {
                throw new Exception("Configuração NF-e não definida.");
            }
            
            KeyStore ks = KeyStore.getInstance("PKCS12");
            char[] senha = nfeConfiguracao.getCertificadoDigitalSenha().toCharArray();
            ks.load(new FileInputStream(new File(nfeConfiguracao.getCertificadoDigitalCaminho())), senha);
            String alias = ks.aliases().nextElement();

            String resposta = consultaEnvioNfse(nfseCabecalho, ks, senha, alias, session, context);

            RespostaSefaz respostaSefaz = new RespostaSefaz();
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
                if (session != null) {
                    session.close();
                }
            } catch (Exception ex1) {
                ex1.printStackTrace();
            }
        }
    }
    
    private String consultaEnvioNfse(NfseCabecalhoVO nfseCabecalho, KeyStore ks, char[] senha, String alias, Session session, ServletContext context) throws Exception {
        String pastaXml = context.getRealPath("/nfse/xml") + System.getProperty("file.separator");

        String protocolo = new String(Files.readAllBytes(Paths.get(new File(pastaXml + nfseCabecalho.getNumeroRps() + ".protocolo").toURI())), "UTF-8");

        EnviaRPS enviaRps = new EnviaRPS();
        Map map = enviaRps.consultaEnvioRPS(protocolo, nfseCabecalho.getEmpresa().getCnpj(), nfseCabecalho.getEmpresa().getInscricaoMunicipal(), alias, ks, senha);

        String resposta = (String) map.get("msgRetorno");
        resposta += "\nProtocolo: " + protocolo;

        String nfse = (String) map.get("nfse");
        if (!nfse.isEmpty()) {
            Files.write(Paths.get(new File(pastaXml + nfseCabecalho.getNumeroRps() + ".xml").toURI()), nfse.getBytes("UTF-8"));

            nfseCabecalho.setNumero(((BigInteger) map.get("numero")).toString());
            nfseCabecalho.setCodigoVerificacao((String) map.get("codigoVerificacao"));
            nfseCabecalho.setDataHoraEmissao((Date) map.get("dataEmissao"));

            session.update(nfseCabecalho);
        }
        
        return resposta;
    }

}
