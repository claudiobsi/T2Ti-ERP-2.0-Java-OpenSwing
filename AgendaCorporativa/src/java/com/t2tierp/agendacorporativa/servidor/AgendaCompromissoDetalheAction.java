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
package com.t2tierp.agendacorporativa.servidor;

import com.t2tierp.agendacorporativa.java.AgendaCompromissoConvidadoVO;
import com.t2tierp.padrao.java.Constantes;
import com.t2tierp.padrao.servidor.HibernateUtil;
import com.t2tierp.agendacorporativa.java.AgendaCompromissoVO;
import com.t2tierp.agendacorporativa.java.ReuniaoSalaEventoVO;
import java.util.Calendar;
import java.util.List;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.openswing.swing.message.receive.java.ErrorResponse;
import org.openswing.swing.message.receive.java.Response;
import org.openswing.swing.message.receive.java.VOListResponse;
import org.openswing.swing.message.receive.java.VOResponse;
import org.openswing.swing.server.Action;
import org.openswing.swing.server.UserSessionParameters;

public class AgendaCompromissoDetalheAction implements Action {

    public AgendaCompromissoDetalheAction() {
    }

    public String getRequestName() {
        return "agendaCompromissoDetalheAction";
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
                return listaCompromisso(inputPar, userSessionPars, request, response, userSession, context);
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
            Criteria criteria = session.createCriteria(AgendaCompromissoVO.class);
            criteria.add(Restrictions.eq("id", Integer.valueOf(pk)));

            AgendaCompromissoVO agendaCompromisso = (AgendaCompromissoVO) criteria.uniqueResult();

            return new VOResponse(agendaCompromisso);
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
            AgendaCompromissoVO agendaCompromisso = (AgendaCompromissoVO) pars[1];
            ReuniaoSalaEventoVO reuniaoSalaEvento = (ReuniaoSalaEventoVO) pars[2];

            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();

            incluiCompromissoConvidado(agendaCompromisso, session);

            for (AgendaCompromissoConvidadoVO c : agendaCompromisso.getListaAgendaCompromissoConvidado()) {
                c.setAgendaCompromisso(agendaCompromisso);
            }

            session.save(agendaCompromisso);
            
            reuniaoSalaEvento.setAgendaCompromisso(agendaCompromisso);
            session.save(reuniaoSalaEvento);
            
            incluiCompromissoRecorrente(agendaCompromisso, session);

            session.getTransaction().commit();

            return new VOResponse(agendaCompromisso);
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
            AgendaCompromissoVO agendaCompromisso = (AgendaCompromissoVO) pars[2];
            ReuniaoSalaEventoVO reuniaoSalaEvento = (ReuniaoSalaEventoVO) pars[3];

            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();

            incluiCompromissoConvidado(agendaCompromisso, session);

            for (AgendaCompromissoConvidadoVO c : agendaCompromisso.getListaAgendaCompromissoConvidado()) {
                c.setAgendaCompromisso(agendaCompromisso);
            }

            session.update(agendaCompromisso);
            
            session.update(reuniaoSalaEvento);

            session.getTransaction().commit();

            return new VOResponse(agendaCompromisso);
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

    private void incluiCompromissoConvidado(AgendaCompromissoVO agendaCompromisso, Session session) throws Exception {
        for (AgendaCompromissoConvidadoVO c : agendaCompromisso.getListaAgendaCompromissoConvidado()) {
            AgendaCompromissoVO compromisso = new AgendaCompromissoVO();
            compromisso.setColaborador(c.getColaborador());
            compromisso.setAgendaCategoriaCompromisso(agendaCompromisso.getAgendaCategoriaCompromisso());
            compromisso.setDataCompromisso(agendaCompromisso.getDataCompromisso());
            compromisso.setDescricao(agendaCompromisso.getDescricao());
            compromisso.setDuracao(agendaCompromisso.getDuracao());
            compromisso.setHora(agendaCompromisso.getHora());
            compromisso.setOnde(agendaCompromisso.getOnde());
            compromisso.setTipo(agendaCompromisso.getTipo());

            session.save(compromisso);
        }
    }

    private void incluiCompromissoRecorrente(AgendaCompromissoVO agendaCompromisso, Session session) throws Exception {
        if (agendaCompromisso.getQuantidadeOcorrencia() != null && agendaCompromisso.getRecorrente() != null) {
            int campoSomar = 0;
            switch (agendaCompromisso.getRecorrente()) {
                case 1: {
                    campoSomar = Calendar.DAY_OF_MONTH;
                    break;
                }
                case 2: {
                    campoSomar = Calendar.WEEK_OF_MONTH;
                    break;
                }
                case 3: {
                    campoSomar = Calendar.MONTH;
                    break;
                }
                case 4: {
                    campoSomar = Calendar.YEAR;
                    break;
                }
                default: {
                    break;
                }
            }

            if (campoSomar != 0) {
                Calendar dataCompromisso = Calendar.getInstance();
                dataCompromisso.setTime(agendaCompromisso.getDataCompromisso());

                for (int i = 0; i < agendaCompromisso.getQuantidadeOcorrencia(); i++) {
                    AgendaCompromissoVO compromisso = new AgendaCompromissoVO();
                    compromisso.setColaborador(agendaCompromisso.getColaborador());
                    compromisso.setAgendaCategoriaCompromisso(agendaCompromisso.getAgendaCategoriaCompromisso());
                    compromisso.setDescricao(agendaCompromisso.getDescricao());
                    compromisso.setDuracao(agendaCompromisso.getDuracao());
                    compromisso.setHora(agendaCompromisso.getHora());
                    compromisso.setOnde(agendaCompromisso.getOnde());
                    compromisso.setTipo(agendaCompromisso.getTipo());

                    dataCompromisso.add(campoSomar, 1);
                    compromisso.setDataCompromisso(dataCompromisso.getTime());

                    session.save(compromisso);
                }
            }
        }
    }

    public Response listaCompromisso(Object inputPar, UserSessionParameters userSessionPars, HttpServletRequest request, HttpServletResponse response, HttpSession userSession, ServletContext context) {
        Session session = null;
        Object[] pars = (Object[]) inputPar;
        AgendaCompromissoVO agendaCompromisso = (AgendaCompromissoVO) pars[1];

        try {
            session = HibernateUtil.getSessionFactory().openSession();

            Calendar dataInicial = Calendar.getInstance();
            dataInicial.setTime(agendaCompromisso.getDataCompromisso());
            dataInicial.add(Calendar.MONTH, -3);

            Calendar dataFinal = Calendar.getInstance();
            dataFinal.setTime(agendaCompromisso.getDataCompromisso());
            dataFinal.add(Calendar.MONTH, 3);

            Criteria criteria = session.createCriteria(AgendaCompromissoVO.class);
            criteria.add(Restrictions.eq("colaborador", agendaCompromisso.getColaborador()));
            criteria.add(Restrictions.between("dataCompromisso", dataInicial.getTime(), dataFinal.getTime()));
            criteria.addOrder(Order.asc("dataCompromisso"));

            List<AgendaCompromissoVO> listaCompromisso = criteria.list();

            return new VOListResponse(listaCompromisso, false, listaCompromisso.size());
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
