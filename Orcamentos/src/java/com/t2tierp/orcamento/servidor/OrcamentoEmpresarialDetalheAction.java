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
package com.t2tierp.orcamento.servidor;

import com.t2tierp.financeiro.java.NaturezaFinanceiraVO;
import com.t2tierp.financeiro.java.FinParcelaPagamentoVO;
import com.t2tierp.financeiro.java.FinParcelaRecebimentoVO;
import com.t2tierp.orcamento.java.OrcamentoDetalheVO;
import com.t2tierp.padrao.java.Constantes;
import com.t2tierp.padrao.servidor.HibernateUtil;
import com.t2tierp.orcamento.java.OrcamentoEmpresarialVO;
import com.t2tierp.padrao.java.Biblioteca;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.openswing.swing.message.receive.java.ErrorResponse;
import org.openswing.swing.message.receive.java.Response;
import org.openswing.swing.message.receive.java.VOResponse;
import org.openswing.swing.server.Action;
import org.openswing.swing.server.UserSessionParameters;

public class OrcamentoEmpresarialDetalheAction implements Action {

    public OrcamentoEmpresarialDetalheAction() {
    }

    public String getRequestName() {
        return "orcamentoEmpresarialDetalheAction";
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
            case 98: {
                return buscaValorRealizado(inputPar, userSessionPars, request, response, userSession, context);
            }
            case 99: {
                return calcularVariacao(inputPar, userSessionPars, request, response, userSession, context);
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
            Criteria criteria = session.createCriteria(OrcamentoEmpresarialVO.class);
            criteria.add(Restrictions.eq("id", Integer.valueOf(pk)));

            OrcamentoEmpresarialVO orcamentoEmpresarial = (OrcamentoEmpresarialVO) criteria.uniqueResult();

            return new VOResponse(orcamentoEmpresarial);
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
            OrcamentoEmpresarialVO orcamentoEmpresarial = (OrcamentoEmpresarialVO) pars[1];

            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();

            Criteria criteria = session.createCriteria(NaturezaFinanceiraVO.class);
            criteria.add(Restrictions.eq("planoNaturezaFinanceira", orcamentoEmpresarial.getPlanoNaturezaFinanceira()));
            List<NaturezaFinanceiraVO> listaNatureza = criteria.list();

            List<OrcamentoDetalheVO> listaOrcamento = geraDetalhesOrcamento(session, listaNatureza, orcamentoEmpresarial);

            session.save(orcamentoEmpresarial);

            for (int i = 0; i < listaOrcamento.size(); i++) {
                listaOrcamento.get(i).setOrcamentoEmpresarial(orcamentoEmpresarial);
                session.save(listaOrcamento.get(i));
            }

            session.getTransaction().commit();

            return new VOResponse(orcamentoEmpresarial);
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
            OrcamentoEmpresarialVO orcamentoEmpresarial = (OrcamentoEmpresarialVO) pars[2];

            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();

            Criteria criteria = session.createCriteria(NaturezaFinanceiraVO.class);
            criteria.add(Restrictions.eq("planoNaturezaFinanceira", orcamentoEmpresarial.getPlanoNaturezaFinanceira()));
            List<NaturezaFinanceiraVO> listaNatureza = criteria.list();

            List<OrcamentoDetalheVO> listaOrcamento = geraDetalhesOrcamento(session, listaNatureza, orcamentoEmpresarial);

            session.update(orcamentoEmpresarial);

            String sqlExcluir = "delete from ORCAMENTO_DETALHE where ID_ORCAMENTO_EMPRESARIAL = :id";
            Query query = session.createSQLQuery(sqlExcluir);
            query.setInteger("id", orcamentoEmpresarial.getId());
            query.executeUpdate();

            for (int i = 0; i < listaOrcamento.size(); i++) {
                listaOrcamento.get(i).setOrcamentoEmpresarial(orcamentoEmpresarial);
                session.save(listaOrcamento.get(i));
            }

            session.update(orcamentoEmpresarial);

            session.getTransaction().commit();

            return new VOResponse(orcamentoEmpresarial);
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

    private List<OrcamentoDetalheVO> geraDetalhesOrcamento(Session session, List<NaturezaFinanceiraVO> listaNatureza, OrcamentoEmpresarialVO orcamento) throws Exception {
        List<OrcamentoDetalheVO> listaOrcamento = new ArrayList<OrcamentoDetalheVO>();

        int periodo = Integer.valueOf(orcamento.getOrcamentoPeriodo().getPeriodo());

        Calendar dataBaseFim = Calendar.getInstance();
        dataBaseFim.setLenient(false);

        Calendar dataPeriodo = Calendar.getInstance();
        dataPeriodo.setLenient(false);

        int numeroPeriodos = orcamento.getNumeroPeriodos();

        SimpleDateFormat formatoDiario = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat formatoMensal = new SimpleDateFormat("MM/yyyy");
        SimpleDateFormat formatoAnual = new SimpleDateFormat("yyyy");

        OrcamentoDetalheVO orcamentoDetalhe;

        for (int i = 0; i < listaNatureza.size(); i++) {
            dataPeriodo.setTime(orcamento.getDataInicial());

            for (int j = 0; j < numeroPeriodos; j++) {
                orcamentoDetalhe = new OrcamentoDetalheVO();
                orcamentoDetalhe.setNaturezaFinanceira(listaNatureza.get(i));
                orcamentoDetalhe.setOrcamentoEmpresarial(orcamento);

                switch (periodo) {
                    case 1: {
                        //Diário
                        if (j != 0) {
                            dataPeriodo.add(Calendar.DAY_OF_MONTH, 1);
                        }
                        orcamentoDetalhe.setPeriodo(formatoDiario.format(dataPeriodo.getTime()));
                        break;
                    }
                    case 2: {
                        //Semanal
                        if (j != 0) {
                            dataPeriodo.add(Calendar.DAY_OF_MONTH, 7);
                        }
                        orcamentoDetalhe.setPeriodo(formatoDiario.format(dataPeriodo.getTime()));
                        break;
                    }
                    case 3: {
                        //Mensal
                        if (j != 0) {
                            dataPeriodo.add(Calendar.MONTH, 1);
                        }
                        orcamentoDetalhe.setPeriodo(formatoMensal.format(dataPeriodo.getTime()));
                        break;
                    }
                    case 4: {
                        //Bimestral
                        if (j != 0) {
                            dataPeriodo.add(Calendar.MONTH, 2);
                        }
                        orcamentoDetalhe.setPeriodo(formatoMensal.format(dataPeriodo.getTime()));
                        break;
                    }
                    case 5: {
                        //Trimestral
                        if (j != 0) {
                            dataPeriodo.add(Calendar.MONTH, 3);
                        }
                        orcamentoDetalhe.setPeriodo(formatoMensal.format(dataPeriodo.getTime()));
                        break;
                    }
                    case 6: {
                        //Semestral
                        if (j != 0) {
                            dataPeriodo.add(Calendar.MONTH, 6);
                        }
                        orcamentoDetalhe.setPeriodo(formatoMensal.format(dataPeriodo.getTime()));
                        break;
                    }
                    case 7: {
                        //Anual
                        if (j != 0) {
                            dataPeriodo.add(Calendar.YEAR, 1);
                        }
                        orcamentoDetalhe.setPeriodo(formatoAnual.format(dataPeriodo.getTime()));
                        break;
                    }
                }
                listaOrcamento.add(orcamentoDetalhe);
            }
        }
        return listaOrcamento;
    }

    private Response buscaValorRealizado(Object inputPar, UserSessionParameters userSessionPars, HttpServletRequest request, HttpServletResponse response, HttpSession userSession, ServletContext context) {
        Session session = null;
        try {
            Object[] pars = (Object[]) inputPar;
            OrcamentoEmpresarialVO orcamentoEmpresarial = (OrcamentoEmpresarialVO) pars[1];
            Calendar dataInicio = Calendar.getInstance();
            Calendar dataFim = Calendar.getInstance();
            int periodo = Integer.valueOf(orcamentoEmpresarial.getOrcamentoPeriodo().getPeriodo());

            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();

            Criteria criteria = session.createCriteria(OrcamentoDetalheVO.class);
            criteria.add(Restrictions.eq("orcamentoEmpresarial", orcamentoEmpresarial));
            List<OrcamentoDetalheVO> listaOrcamentoDetalhe = criteria.list();

            OrcamentoDetalheVO orcamentoDetalhe;
            BigDecimal realizadoReceber;
            BigDecimal realizadoPagar;
            for (int i = 0; i < listaOrcamentoDetalhe.size(); i++) {
                orcamentoDetalhe = listaOrcamentoDetalhe.get(i);

                dataInicio = Biblioteca.dataStrToCalendar(orcamentoDetalhe.getPeriodo());

                switch (periodo) {
                    case 1: {
                        dataFim.setTime(dataInicio.getTime());
                        break;
                    }
                    case 2: {
                        dataFim.add(Calendar.DAY_OF_MONTH, 7);
                        break;
                    }
                    case 3: {
                        dataFim.add(Calendar.MONTH, 1);
                        break;
                    }
                    case 4: {
                        dataFim.add(Calendar.MONTH, 2);
                        break;
                    }
                    case 5: {
                        dataFim.add(Calendar.MONTH, 3);
                        break;
                    }
                    case 6: {
                        dataFim.add(Calendar.MONTH, 6);
                        break;
                    }
                    case 7: {
                        dataFim.add(Calendar.YEAR, 1);
                        break;
                    }
                }

                //busca receita do período
                criteria = session.createCriteria(FinParcelaRecebimentoVO.class);
                criteria.add(Restrictions.between("dataRecebimento", dataInicio.getTime(), dataFim.getTime()));
                criteria.setProjection(Projections.sum("valorRecebido"));
                realizadoReceber = (BigDecimal) criteria.uniqueResult();
                realizadoReceber = realizadoReceber == null ? BigDecimal.ZERO : realizadoReceber;

                //busca despesa do período
                criteria = session.createCriteria(FinParcelaPagamentoVO.class);
                criteria.add(Restrictions.between("dataPagamento", dataInicio.getTime(), dataFim.getTime()));
                criteria.setProjection(Projections.sum("valorPago"));
                realizadoPagar = (BigDecimal) criteria.uniqueResult();
                realizadoPagar = realizadoPagar == null ? BigDecimal.ZERO : realizadoPagar;

                orcamentoDetalhe.setValorRealizado(realizadoReceber.add(realizadoPagar));

                session.update(orcamentoDetalhe);
            }

            session.getTransaction().commit();

            return new VOResponse(orcamentoEmpresarial);
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

    private Response calcularVariacao(Object inputPar, UserSessionParameters userSessionPars, HttpServletRequest request, HttpServletResponse response, HttpSession userSession, ServletContext context) {
        Session session = null;
        try {
            Object[] pars = (Object[]) inputPar;
            OrcamentoEmpresarialVO orcamentoEmpresarial = (OrcamentoEmpresarialVO) pars[1];

            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();

            Criteria criteria1 = session.createCriteria(OrcamentoDetalheVO.class);
            criteria1.add(Restrictions.eq("orcamentoEmpresarial", orcamentoEmpresarial));
            List<OrcamentoDetalheVO> listaOrcamentoDetalhe = criteria1.list();

            OrcamentoDetalheVO orcamentoDetalhe;
            BigDecimal realizado;
            BigDecimal orcado;
            BigDecimal variacao;
            for (int i = 0; i < listaOrcamentoDetalhe.size(); i++) {
                orcamentoDetalhe = listaOrcamentoDetalhe.get(i);
                realizado = orcamentoDetalhe.getValorRealizado();
                orcado = orcamentoDetalhe.getValorOrcado();
                if (realizado != null && orcado != null) {
                    if ((realizado.compareTo(BigDecimal.ZERO) != 0) || (orcado.compareTo(BigDecimal.ZERO) != 0)) {
                        variacao = realizado.subtract(orcado);
                        orcamentoDetalhe.setValorVariacao(variacao);

                        variacao = variacao.divide(orcado, RoundingMode.HALF_DOWN).multiply(BigDecimal.valueOf(100));
                        variacao = variacao.setScale(2, RoundingMode.HALF_DOWN);
                        orcamentoDetalhe.setTaxaVariacao(variacao);
                    }
                }

                session.update(orcamentoDetalhe);
            }

            session.getTransaction().commit();

            return new VOResponse(orcamentoEmpresarial);
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
