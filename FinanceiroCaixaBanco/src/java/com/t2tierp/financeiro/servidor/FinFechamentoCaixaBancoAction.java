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
package com.t2tierp.financeiro.servidor;

import com.t2tierp.cadastros.java.ContaCaixaVO;
import com.t2tierp.financeiro.java.FinFechamentoCaixaBancoVO;
import com.t2tierp.padrao.java.Constantes;
import com.t2tierp.padrao.servidor.HibernateUtil;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Calendar;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.hibernate.type.Type;
import org.openswing.swing.message.receive.java.ErrorResponse;
import org.openswing.swing.message.receive.java.Response;
import org.openswing.swing.message.receive.java.VOResponse;
import org.openswing.swing.message.send.java.GridParams;
import org.openswing.swing.server.Action;
import org.openswing.swing.server.UserSessionParameters;
import org.openswing.swing.util.server.HibernateUtils;

public class FinFechamentoCaixaBancoAction implements Action {

    public FinFechamentoCaixaBancoAction() {
    }

    public String getRequestName() {
        return "finFechamentoCaixaBancoAction";
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
            case 5: {
                return chequeNaoCompensado(inputPar, userSessionPars, request, response, userSession, context);
            }
        }
        return null;
    }

    private Response load(Object inputPar, UserSessionParameters userSessionPars, HttpServletRequest request, HttpServletResponse response, HttpSession userSession, ServletContext context) {
        Session session = null;
        Object[] pars = (Object[]) inputPar;
        Integer idContaCaixa = (Integer) pars[1];
        int mes = (Integer) pars[2];
        int ano = (Integer) pars[3];
        DecimalFormat formatoMes = new DecimalFormat("00");

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            ContaCaixaVO contaCaixa = (ContaCaixaVO) session.get(ContaCaixaVO.class, idContaCaixa);

            Criteria criteria = session.createCriteria(FinFechamentoCaixaBancoVO.class);
            criteria.add(Restrictions.eq("contaCaixa", contaCaixa));
            criteria.add(Restrictions.eq("mes", formatoMes.format(mes)));
            criteria.add(Restrictions.eq("ano", String.valueOf(ano)));

            FinFechamentoCaixaBancoVO fechamentoCaixaBanco = (FinFechamentoCaixaBancoVO) criteria.uniqueResult();

            mes -= 1;
            if (mes == 0) {
                mes = 12;
                ano -= 1;
            }
            String baseSQL = "select SALDO_DISPONIVEL from FIN_FECHAMENTO_CAIXA_BANCO "
                    + "where MES = '" + formatoMes.format(mes) + "' and ANO = '" + ano + "' "
                    + "and ID_CONTA_CAIXA = " + idContaCaixa;

            BigDecimal saldoAnterior = (BigDecimal) session.createSQLQuery(baseSQL).uniqueResult();
            if (fechamentoCaixaBanco == null) {
                fechamentoCaixaBanco = new FinFechamentoCaixaBancoVO();
                fechamentoCaixaBanco.setContaCaixa(contaCaixa);
            }
            if (saldoAnterior != null) {
                fechamentoCaixaBanco.setSaldoAnterior(saldoAnterior);
            } else {
                fechamentoCaixaBanco.setSaldoAnterior(BigDecimal.ZERO);
            }

            return new VOResponse(fechamentoCaixaBanco);
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
            FinFechamentoCaixaBancoVO fechamento = (FinFechamentoCaixaBancoVO) pars[1];
            int mes = (Integer) pars[2];
            int ano = (Integer) pars[3];
            DecimalFormat formatoMes = new DecimalFormat("00");

            session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();

            fechamento.setMes(formatoMes.format(mes));
            fechamento.setAno(String.valueOf(ano));
            fechamento.setDataFechamento(Calendar.getInstance().getTime());

            session.save(fechamento);

            session.getTransaction().commit();

            return new VOResponse(fechamento);
        } catch (Exception ex) {
            ex.printStackTrace();
            session.getTransaction().rollback();
            return new ErrorResponse(ex.getMessage());
        } finally {
            try {
                session.close();
            } catch (Exception ex1) {
            }
        }
    }

    public final Response update(Object inputPar, UserSessionParameters userSessionPars, HttpServletRequest request, HttpServletResponse response, HttpSession userSession, ServletContext context) {
        Session session = null;

        Object[] pars = (Object[]) inputPar;
        FinFechamentoCaixaBancoVO oldPersistentObject = (FinFechamentoCaixaBancoVO) pars[1];
        FinFechamentoCaixaBancoVO persistentObject = (FinFechamentoCaixaBancoVO) pars[2];

        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();
            session.update(persistentObject);
            session.flush();
            session.getTransaction().commit();

            return new VOResponse(persistentObject);
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

    public final Response delete(Object inputPar, UserSessionParameters userSessionPars, HttpServletRequest request, HttpServletResponse response, HttpSession userSession, ServletContext context) {
        return null;
    }

    private Response chequeNaoCompensado(Object inputPar, UserSessionParameters userSessionPars, HttpServletRequest request, HttpServletResponse response, HttpSession userSession, ServletContext context) {
        Session session = null;
        Object[] pars = (Object[]) inputPar;
        Integer idContaCaixa = (Integer) pars[1];

        GridParams gridParams = new GridParams();

        try {
            session = HibernateUtil.getSessionFactory().openSession();

            String baseSQL = "select CHEQUE_NAO_COMPENSADO from com.t2tierp.financeiro.java.ViewFinChequeNaoCompensadoID as CHEQUE_NAO_COMPENSADO "
                    + " where CHEQUE_NAO_COMPENSADO.viewFinChequeNaoCompensado.idContaCaixa = " + idContaCaixa;

            Response res = HibernateUtils.getAllFromQuery(gridParams.getFilteredColumns(),
                    gridParams.getCurrentSortedColumns(),
                    gridParams.getCurrentSortedVersusColumns(),
                    com.t2tierp.financeiro.java.ViewFinChequeNaoCompensadoID.class,
                    baseSQL,
                    new Object[0],
                    new Type[0],
                    "CHEQUE_NAO_COMPENSADO",
                    HibernateUtil.getSessionFactory(),
                    session);

            return res;
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
}
