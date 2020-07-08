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
*/package com.t2tierp.financeiro.servidor;

import com.t2tierp.cadastros.java.ChequeVO;
import com.t2tierp.padrao.java.Constantes;
import com.t2tierp.padrao.servidor.HibernateUtil;
import com.t2tierp.financeiro.java.FinParcelaPagamentoVO;
import com.t2tierp.financeiro.java.FinParcelaPagarVO;
import com.t2tierp.financeiro.java.FinStatusParcelaVO;
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

public class FinParcelaPagamentoDetalheAction implements Action {

    public FinParcelaPagamentoDetalheAction() {
    }

    public String getRequestName() {
        return "finParcelaPagamentoDetalheAction";
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
        return null;
    }

    public Response insert(Object inputPar, UserSessionParameters userSessionPars, HttpServletRequest request, HttpServletResponse response, HttpSession userSession, ServletContext context) {
        Session session = null;
        try {
            Object[] pars = (Object[]) inputPar;
            FinParcelaPagamentoVO finParcelaPagamento = (FinParcelaPagamentoVO) pars[1];
            String tipoBaixa = (String) pars[2];

            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();

            if (finParcelaPagamento.getFinChequeEmitido() != null) {
                session.save(finParcelaPagamento.getFinChequeEmitido());
                ChequeVO cheque = finParcelaPagamento.getFinChequeEmitido().getCheque();
                cheque.setStatusCheque("U");
                session.update(cheque);
            }

            session.save(finParcelaPagamento);

            Criteria criteria = session.createCriteria(FinStatusParcelaVO.class);
            if (tipoBaixa.equals("T")) {
                criteria.add(Restrictions.eq("situacao", "02"));
            } else {
                criteria.add(Restrictions.eq("situacao", "03"));
            }
            FinStatusParcelaVO statusParcela = (FinStatusParcelaVO) criteria.uniqueResult();

            if (statusParcela == null) {
                return new ErrorResponse("Status de parcela não cadastrado. Entre em contato com a Software House");
            }

            FinParcelaPagarVO parcelaPagar = finParcelaPagamento.getFinParcelaPagar();
            parcelaPagar.setFinStatusParcela(statusParcela);
            session.update(parcelaPagar);

            session.getTransaction().commit();

            return new VOResponse(finParcelaPagamento);
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
        Session session = null;
        try {
            Object[] pars = (Object[]) inputPar;
            FinParcelaPagamentoVO finParcelaPagamento = (FinParcelaPagamentoVO) pars[1];

            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();

            Criteria criteria = session.createCriteria(FinStatusParcelaVO.class);
            criteria.add(Restrictions.eq("situacao", "01"));
            FinStatusParcelaVO statusParcela = (FinStatusParcelaVO) criteria.uniqueResult();

            if (statusParcela == null) {
                return new ErrorResponse("Status de parcela não cadastrado. Entre em contato com a Software House");
            }

            FinParcelaPagarVO parcelaPagar = finParcelaPagamento.getFinParcelaPagar();
            parcelaPagar.setFinStatusParcela(statusParcela);
            session.update(parcelaPagar);

            session.delete(finParcelaPagamento);

            session.getTransaction().commit();

            return new VOResponse(finParcelaPagamento);
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
}
