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
package com.t2tierp.cadastros.servidor;

import com.t2tierp.padrao.java.Constantes;
import com.t2tierp.padrao.servidor.HibernateUtil;
import com.t2tierp.cadastros.java.ProdutoVO;
import com.t2tierp.padrao.java.Biblioteca;
import java.io.File;
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

public class ProdutoDetalheAction implements Action {

    public ProdutoDetalheAction() {
    }

    public String getRequestName() {
        return "produtoDetalheAction";
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
            Criteria criteria = session.createCriteria(ProdutoVO.class);
            criteria.add(Restrictions.eq("id", Integer.valueOf(pk)));

            ProdutoVO produto = (ProdutoVO) criteria.uniqueResult();

            if (produto.getFotoProduto() != null) {
                File imagem = new File(produto.getFotoProduto());
                if (imagem.exists()) {
                    produto.setImagem(Biblioteca.getBytesFromFile(imagem));
                }
            }

            return new VOResponse(produto);
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
            ProdutoVO produto = (ProdutoVO) pars[1];

            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();

            Criteria criteria = session.createCriteria(ProdutoVO.class);
            criteria.add(Restrictions.eq("gtin", produto.getGtin()));
            if (criteria.uniqueResult() != null) {
                return new ErrorResponse("Este GTIN já está sendo utilizado por outro produto");
            }

            if (produto.getTributIcmsCustomCab().getId() == null) {
                produto.setTributIcmsCustomCab(null);
            }
            if (produto.getTributGrupoTributario().getId() == null) {
                produto.setTributGrupoTributario(null);
            }
            if (produto.getAlmoxarifado().getId() == null) {
                produto.setAlmoxarifado(null);
            }
            if (produto.getProdutoMarca().getId() == null) {
                produto.setProdutoMarca(null);
            }

            if (produto.getImagem() != null) {
                String caminhoArquivo = context.getRealPath("/imagens")
                        + System.getProperty("file.separator")
                        + "produtos"
                        + System.getProperty("file.separator")
                        + produto.getGtin() + ".jpg";
                produto.setFotoProduto(caminhoArquivo);
                Biblioteca.salvaArquivo(caminhoArquivo, produto.getImagem());
            }

            produto.setDataCadastro(new Date());
            session.save(produto);

            session.getTransaction().commit();

            return new VOResponse(produto);
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
            ProdutoVO produto = (ProdutoVO) pars[2];

            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();

            Criteria criteria = session.createCriteria(ProdutoVO.class);
            criteria.add(Restrictions.eq("gtin", produto.getGtin()));
            criteria.add(Restrictions.ne("id", produto.getId()));
            if (criteria.uniqueResult() != null) {
                return new ErrorResponse("Este GTIN já está sendo utilizado por outro produto");
            }

            if (produto.getTributIcmsCustomCab().getId() == null) {
                produto.setTributIcmsCustomCab(null);
            }
            if (produto.getTributGrupoTributario().getId() == null) {
                produto.setTributGrupoTributario(null);
            }
            if (produto.getAlmoxarifado().getId() == null) {
                produto.setAlmoxarifado(null);
            }
            if (produto.getProdutoMarca().getId() == null) {
                produto.setProdutoMarca(null);
            }

            if (produto.getImagem() != null) {
                String caminhoArquivo = context.getRealPath("/imagens")
                        + System.getProperty("file.separator")
                        + "produtos"
                        + System.getProperty("file.separator")
                        + produto.getGtin() + ".jpg";
                produto.setFotoProduto(caminhoArquivo);
                Biblioteca.salvaArquivo(caminhoArquivo, produto.getImagem());
            }

            produto.setDataAlteracao(new Date());
            session.update(produto);

            session.getTransaction().commit();

            return new VOResponse(produto);
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
}
