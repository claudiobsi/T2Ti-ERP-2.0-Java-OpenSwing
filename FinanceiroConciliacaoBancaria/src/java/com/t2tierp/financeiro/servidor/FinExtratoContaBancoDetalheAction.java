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
import com.t2tierp.financeiro.java.FinChequeEmitidoVO;
import com.t2tierp.padrao.servidor.HibernateUtil;
import com.t2tierp.padrao.java.Constantes;
import com.t2tierp.financeiro.java.FinExtratoContaBancoVO;
import com.t2tierp.financeiro.java.FinParcelaPagamentoVO;
import com.t2tierp.financeiro.java.FinParcelaRecebimentoVO;
import java.math.BigDecimal;
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
import org.openswing.swing.message.receive.java.VOListResponse;
import org.openswing.swing.message.send.java.GridParams;
import org.openswing.swing.server.Action;
import org.openswing.swing.server.UserSessionParameters;

public class FinExtratoContaBancoDetalheAction implements Action {

    public FinExtratoContaBancoDetalheAction() {
    }

    public String getRequestName() {
        return "finExtratoContaBancoDetalheAction";
    }

    public Response executeCommand(Object inputPar, UserSessionParameters userSessionPars, HttpServletRequest request, HttpServletResponse response, HttpSession userSession, ServletContext context) {
        GridParams pars = (GridParams) inputPar;
        Integer acao = (Integer) pars.getOtherGridParams().get("acao");

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
        GridParams pars = (GridParams) inputPar;
        ContaCaixaVO contaCaixa = (ContaCaixaVO) pars.getOtherGridParams().get("contaCaixa");
        String mesAno = (String) pars.getOtherGridParams().get("mesAno");

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Criteria criteria = session.createCriteria(FinExtratoContaBancoVO.class);
            criteria.add(Restrictions.eq("contaCaixa", contaCaixa));
            criteria.add(Restrictions.eq("mesAno", mesAno));

            List<FinExtratoContaBancoVO> listaExtrato = criteria.list();

            return new VOListResponse(listaExtrato, false, listaExtrato.size());
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
            GridParams pars = (GridParams) inputPar;
            String mesAno = (String) pars.getOtherGridParams().get("mesAno");
            List<FinExtratoContaBancoVO> extrato = (Vector) pars.getOtherGridParams().get("newValueObjects");

            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();

            for (int i = 0; i < extrato.size(); i++) {
                extrato.get(i).setMesAno(mesAno);
                extrato.get(i).setMes(mesAno.substring(0, 2));
                extrato.get(i).setAno(mesAno.substring(3, 7));

                session.saveOrUpdate(extrato.get(i));
            }

            session.getTransaction().commit();
            return new VOListResponse(extrato, false, extrato.size());
        } catch (Exception ex) {
            if (session != null) {
                session.getTransaction().rollback();
            }
            ex.printStackTrace();
            return new ErrorResponse(ex.getMessage());
        } finally {
            try {
                if (session != null) {
                    session.close();
                }
            } catch (Exception ex1) {
            }
        }
    }

    public Response update(Object inputPar, UserSessionParameters userSessionPars, HttpServletRequest request, HttpServletResponse response, HttpSession userSession, ServletContext context) {
        Session session = null;
        GridParams pars = (GridParams) inputPar;
        String tipoConciliacao = (String) pars.getOtherGridParams().get("tipoConciliacao");
        ContaCaixaVO contaCaixa = (ContaCaixaVO) pars.getOtherGridParams().get("contaCaixa");
        List<FinExtratoContaBancoVO> listaExtrato = (Vector) pars.getOtherGridParams().get("extrato");

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Criteria criteria;
            String sql;
            for (int i = 0; i < listaExtrato.size(); i++) {
                //conciliacao lancamentos
                if (tipoConciliacao.equals("lancamentos") && (!listaExtrato.get(i).getHistorico().contains("Cheque"))) {
                    if (listaExtrato.get(i).getValor().compareTo(BigDecimal.ZERO) < 0) {
                        criteria = session.createCriteria(FinParcelaPagamentoVO.class);
                        criteria.add(Restrictions.eq("dataPagamento", listaExtrato.get(i).getDataMovimento()));
                        criteria.add(Restrictions.eq("valorPago", listaExtrato.get(i).getValor().negate()));
                    } else {
                        criteria = session.createCriteria(FinParcelaRecebimentoVO.class);
                        criteria.add(Restrictions.eq("dataRecebimento", listaExtrato.get(i).getDataMovimento()));
                        criteria.add(Restrictions.eq("valorRecebido", listaExtrato.get(i).getValor()));
                    }
                    criteria.add(Restrictions.eq("contaCaixa", contaCaixa));
                    if (criteria.list().isEmpty()) {
                        listaExtrato.get(i).setConciliado("N");
                    } else {
                        listaExtrato.get(i).setConciliado("S");
                    }
                }

                //conciliacao cheques
                if (tipoConciliacao.equals("cheques") && (listaExtrato.get(i).getHistorico().contains("Cheque"))) {
                    sql = "select CHEQUE_EMITIDO from com.t2tierp.financeiro.java.FinChequeEmitidoVO as CHEQUE_EMITIDO"
                            + " where CHEQUE_EMITIDO.cheque.numero = " + Integer.valueOf(listaExtrato.get(i).getDocumento())
                            + " and CHEQUE_EMITIDO.cheque.talonarioCheque.contaCaixa.id = " + contaCaixa.getId();

                    FinChequeEmitidoVO chequeEmitido = (FinChequeEmitidoVO) session.createQuery(sql).uniqueResult();
                    if (chequeEmitido != null) {
                        listaExtrato.get(i).setConciliado("S");
                        if (chequeEmitido.getValor().compareTo(listaExtrato.get(i).getValor().negate()) != 0){
                            listaExtrato.get(i).setObservacao("VALOR DO CHEQUE NO EXTRATO DIFERE DO VALOR ARMAZENADO NO BANCO DE DADOS - CHEQUE NAO CONCILIADO");
                            listaExtrato.get(i).setConciliado("N");
                        }
                    } else {
                        listaExtrato.get(i).setConciliado("N");
                    }
                }
            }

            return new VOListResponse(listaExtrato, false, listaExtrato.size());
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

    public Response delete(Object inputPar, UserSessionParameters userSessionPars, HttpServletRequest request, HttpServletResponse response, HttpSession userSession, ServletContext context) {
        return null;
    }
}
