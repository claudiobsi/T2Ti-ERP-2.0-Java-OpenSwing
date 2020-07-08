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
package com.t2tierp.ged.servidor;

import com.t2tierp.cadastros.java.UsuarioVO;
import com.t2tierp.ged.java.ArquivoVO;
import com.t2tierp.padrao.java.Constantes;
import com.t2tierp.padrao.servidor.HibernateUtil;
import com.t2tierp.ged.java.GedDocumentoCabecalhoVO;
import com.t2tierp.ged.java.GedDocumentoDetalheVO;
import com.t2tierp.ged.java.GedVersaoDocumentoVO;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.openswing.swing.message.receive.java.ErrorResponse;
import org.openswing.swing.message.receive.java.Response;
import org.openswing.swing.message.receive.java.VOResponse;
import org.openswing.swing.server.Action;
import org.openswing.swing.server.UserSessionParameters;

public class GedDocumentoCabecalhoDetalheAction implements Action {

    public GedDocumentoCabecalhoDetalheAction() {
    }

    public String getRequestName() {
        return "gedDocumentoCabecalhoDetalheAction";
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
        }
        return null;
    }

    private Response load(Object inputPar, UserSessionParameters userSessionPars, HttpServletRequest request, HttpServletResponse response, HttpSession userSession, ServletContext context) {
        Session session = null;
        Object[] pars = (Object[]) inputPar;
        String pk = (String) pars[1];

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Criteria criteria = session.createCriteria(GedDocumentoCabecalhoVO.class);
            criteria.add(Restrictions.eq("id", Integer.valueOf(pk)));

            GedDocumentoCabecalhoVO gedDocumentoCabecalho = (GedDocumentoCabecalhoVO) criteria.uniqueResult();

            for (GedDocumentoDetalheVO detalhe : gedDocumentoCabecalho.getListaGedDocumentoDetalhe()) {
                GedVersaoDocumentoVO gedVersaoDocumento = detalhe.getListaGedVersaoDocumento().get(detalhe.getListaGedVersaoDocumento().size() - 1);

                File file = new File(gedVersaoDocumento.getCaminho());
                ArquivoVO arquivo = new ArquivoVO();
                arquivo.setFile(Files.readAllBytes(Paths.get(file.toURI())));
                arquivo.setExtensao(getExtensaoArquivo(gedVersaoDocumento.getCaminho()));
                arquivo.setHash(gedVersaoDocumento.getHashArquivo());

                detalhe.setArquivo(arquivo);
            }

            return new VOResponse(gedDocumentoCabecalho);
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
            GedDocumentoCabecalhoVO gedDocumentoCabecalho = (GedDocumentoCabecalhoVO) pars[1];
            UsuarioVO usuario = (UsuarioVO) pars[2];

            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();

            String caminhoArquivo;
            Date dataAtual = new Date();
            for (GedDocumentoDetalheVO detalhe : gedDocumentoCabecalho.getListaGedDocumentoDetalhe()) {
                detalhe.setGedDocumentoCabecalho(gedDocumentoCabecalho);

                //salva arquivos relacionados ao documento
                caminhoArquivo = context.getRealPath("/ged") + System.getProperty("file.separator") + detalhe.getArquivo().getHash() + detalhe.getArquivo().getExtensao();
                File file = new File(caminhoArquivo);
                if (file.createNewFile()) {
                    salvaArquivo(caminhoArquivo, detalhe.getArquivo().getFile());
                }

                //se o documento for assinado, salva a assinatura
                if (detalhe.getAssinado().equals("S")) {
                    String caminhoAssinatura = context.getRealPath("/ged") + System.getProperty("file.separator") + detalhe.getArquivo().getHash();
                    File fileAssinatura = new File(caminhoAssinatura);
                    if (fileAssinatura.createNewFile()) {
                        salvaArquivo(caminhoAssinatura, detalhe.getArquivo().getAssinatura());
                    }
                }

                //salva os dados da versao do documento
                GedVersaoDocumentoVO gedVersaoDocumento = new GedVersaoDocumentoVO();
                gedVersaoDocumento.setAcao("I");
                gedVersaoDocumento.setCaminho(caminhoArquivo);
                gedVersaoDocumento.setDataHora(dataAtual);
                gedVersaoDocumento.setGedDocumentoDetalhe(detalhe);
                gedVersaoDocumento.setHashArquivo(detalhe.getArquivo().getHash());
                gedVersaoDocumento.setColaborador(usuario.getColaborador());
                gedVersaoDocumento.setVersao(1);

                detalhe.setListaGedVersaoDocumento(new ArrayList<>());
                detalhe.getListaGedVersaoDocumento().add(gedVersaoDocumento);
            }

            session.save(gedDocumentoCabecalho);

            session.getTransaction().commit();

            return new VOResponse(gedDocumentoCabecalho);
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
            GedDocumentoCabecalhoVO gedDocumentoCabecalho = (GedDocumentoCabecalhoVO) pars[2];
            UsuarioVO usuario = (UsuarioVO) pars[3];

            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();

            String caminhoArquivo;
            Date dataAtual = new Date();
            for (GedDocumentoDetalheVO detalhe : gedDocumentoCabecalho.getListaGedDocumentoDetalhe()) {
                //salva arquivos relacionados ao documento
                caminhoArquivo = context.getRealPath("/ged") + System.getProperty("file.separator") + detalhe.getArquivo().getHash() + detalhe.getArquivo().getExtensao();
                File file = new File(caminhoArquivo);
                if (file.createNewFile()) {
                    salvaArquivo(caminhoArquivo, detalhe.getArquivo().getFile());
                }

                //se o documento for assinado, salva a assinatura
                if (detalhe.getAssinado().equals("S")) {
                    String caminhoAssinatura = context.getRealPath("/ged") + System.getProperty("file.separator") + detalhe.getArquivo().getHash();
                    File fileAssinatura = new File(caminhoAssinatura);
                    if (fileAssinatura.createNewFile()) {
                        salvaArquivo(caminhoAssinatura, detalhe.getArquivo().getAssinatura());
                    }
                }

                GedVersaoDocumentoVO gedVersaoDocumento = null;
                if (detalhe.getListaGedVersaoDocumento() != null) {
                    gedVersaoDocumento = detalhe.getListaGedVersaoDocumento().get(detalhe.getListaGedVersaoDocumento().size() - 1);
                } else {
                    detalhe.setListaGedVersaoDocumento(new ArrayList<>());
                }

                //se for incluso novo detalhe OU se o hash do arquivo mudar OU se for excluido um detalhe
                if (gedVersaoDocumento == null
                        || (!gedVersaoDocumento.getHashArquivo().equals(detalhe.getArquivo().getHash()))//
                        || detalhe.getDataExclusao() != null) {
                    GedVersaoDocumentoVO novaVersao = new GedVersaoDocumentoVO();
                    if (detalhe.getDataExclusao() != null) {
                        novaVersao.setAcao("E");
                    } else {
                        novaVersao.setAcao("A");
                    }
                    novaVersao.setCaminho(caminhoArquivo);
                    novaVersao.setDataHora(dataAtual);
                    novaVersao.setGedDocumentoDetalhe(detalhe);
                    novaVersao.setHashArquivo(detalhe.getArquivo().getHash());
                    novaVersao.setColaborador(usuario.getColaborador());
                    novaVersao.setVersao(gedVersaoDocumento.getVersao() + 1);

                    session.save(novaVersao);
                }

                detalhe.getListaGedVersaoDocumento().add(gedVersaoDocumento);
            }

            session.update(gedDocumentoCabecalho);

            session.getTransaction().commit();

            return new VOResponse(gedDocumentoCabecalho);
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

    private String getExtensaoArquivo(String caminhoArquivo) {
        if (!caminhoArquivo.equals("")) {
            int indiceExtensao = caminhoArquivo.lastIndexOf(".");
            if (indiceExtensao > -1) {
                return caminhoArquivo.substring(indiceExtensao, caminhoArquivo.length());
            }
        }
        return "";
    }
    
    private void salvaArquivo(String caminhoArquivo, byte[] file) throws Exception {
        FileOutputStream out = new FileOutputStream(caminhoArquivo);
        out.write(file);
        out.close();
    }

}
