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
package com.t2tierp.compras.servidor;

import com.t2tierp.compras.java.CompraCotacaoDetalheVO;
import com.t2tierp.padrao.java.Constantes;
import com.t2tierp.padrao.servidor.HibernateUtil;
import com.t2tierp.compras.java.CompraCotacaoVO;
import com.t2tierp.compras.java.CompraFornecedorCotacaoVO;
import com.t2tierp.compras.java.CompraReqCotacaoDetalheVO;
import com.t2tierp.compras.java.CompraRequisicaoDetalheVO;
import java.util.List;
import java.util.Vector;
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

public class CompraCotacaoDetalheAction implements Action {

    public CompraCotacaoDetalheAction() {
    }

    public String getRequestName() {
        return "compraCotacaoDetalheAction";
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
            Criteria criteria = session.createCriteria(CompraCotacaoVO.class);
            criteria.add(Restrictions.eq("id", Integer.valueOf(pk)));

            CompraCotacaoVO compraCotacao = (CompraCotacaoVO) criteria.uniqueResult();

            return new VOResponse(compraCotacao);
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
            CompraCotacaoVO compraCotacao = (CompraCotacaoVO) pars[1];
            List<CompraFornecedorCotacaoVO> fornecedores = (Vector) pars[2];
            List<CompraCotacaoDetalheVO> cotacaoDetalhe = (Vector) pars[3];
            List<CompraRequisicaoDetalheVO> requisicaoDetalhe = (Vector) pars[4];

            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();

            compraCotacao.setSituacao("A");
            session.save(compraCotacao);

            CompraReqCotacaoDetalheVO compraReqCotacaoDetalheVO;
            for (int i = 0; i < cotacaoDetalhe.size(); i++) {
                compraReqCotacaoDetalheVO = new CompraReqCotacaoDetalheVO();
                compraReqCotacaoDetalheVO.setCompraCotacao(compraCotacao);
                compraReqCotacaoDetalheVO.setCompraRequisicaoDetalhe(cotacaoDetalhe.get(i).getCompraRequisicaoDetalhe());
                compraReqCotacaoDetalheVO.setQuantidadeCotada(cotacaoDetalhe.get(i).getQuantidade());

                session.save(compraReqCotacaoDetalheVO);

                session.update(cotacaoDetalhe.get(i).getCompraRequisicaoDetalhe());
            }

            for (int i = 0; i < fornecedores.size(); i++) {
                fornecedores.get(i).setCompraCotacao(compraCotacao);
                session.save(fornecedores.get(i));
                for (int j = 0; j < cotacaoDetalhe.size(); j++) {
                    cotacaoDetalhe.get(j).setCompraFornecedorCotacao(fornecedores.get(i));
                    session.save(cotacaoDetalhe.get(j));
                    session.evict(cotacaoDetalhe.get(j));
                    cotacaoDetalhe.get(j).setId(null);
                }
            }

            session.getTransaction().commit();

            return new VOResponse(compraCotacao);
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
        return null;
    }

    public Response delete(Object inputPar, UserSessionParameters userSessionPars, HttpServletRequest request, HttpServletResponse response, HttpSession userSession, ServletContext context) {
        return null;
    }
}
