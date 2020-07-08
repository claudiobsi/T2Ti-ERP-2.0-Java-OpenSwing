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
package com.t2tierp.ponto.servidor;

import com.t2tierp.cadastros.java.ColaboradorVO;
import com.t2tierp.cadastros.java.UsuarioVO;
import com.t2tierp.padrao.servidor.HibernateUtil;
import com.t2tierp.padrao.java.Constantes;
import com.t2tierp.ponto.java.PontoClassificacaoJornadaVO;
import com.t2tierp.ponto.java.PontoFechamentoJornadaVO;
import com.t2tierp.ponto.java.PontoHorarioVO;
import com.t2tierp.ponto.java.PontoMarcacaoVO;
import com.t2tierp.ponto.java.PontoTurmaVO;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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
import org.openswing.swing.message.send.java.GridParams;
import org.openswing.swing.server.Action;
import org.openswing.swing.server.UserSessionParameters;

public class PontoEspelhoDetalheAction implements Action {

    public PontoEspelhoDetalheAction() {
    }

    public String getRequestName() {
        return "pontoEspelhoDetalheAction";
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
            case 98: {
                return horariosColaborador(inputPar);
            }
            case 99: {
                return marcacoesColaborador(inputPar);
            }
        }
        return null;
    }

    private Response load(Object inputPar, UserSessionParameters userSessionPars, HttpServletRequest request, HttpServletResponse response, HttpSession userSession, ServletContext context) {
        Session session = null;
        GridParams pars = (GridParams) inputPar;
        ColaboradorVO colaborador = (ColaboradorVO) pars.getOtherGridParams().get("colaborador");
        Date dataInicial = (Date) pars.getOtherGridParams().get("dataInicio");
        Date dataFinal = (Date) pars.getOtherGridParams().get("dataFim");

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Criteria criteria = session.createCriteria(PontoFechamentoJornadaVO.class);
            criteria.add(Restrictions.eq("colaborador", colaborador));
            criteria.add(Restrictions.between("dataFechamento", dataInicial, dataFinal));
            criteria.addOrder(Order.asc("dataFechamento"));

            List<PontoFechamentoJornadaVO> fechamento = criteria.list();

            return new VOListResponse(fechamento, false, fechamento.size());
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
        GridParams pars = (GridParams) inputPar;
        UsuarioVO usuario = (UsuarioVO) pars.getOtherGridParams().get("usuario");
        ColaboradorVO colaborador = (ColaboradorVO) pars.getOtherGridParams().get("colaborador");
        Calendar dataclassificar = Calendar.getInstance();
        dataclassificar.setTime((Date) pars.getOtherGridParams().get("dataClassificar"));
        PontoClassificacaoJornadaVO classificacao = (PontoClassificacaoJornadaVO) pars.getOtherGridParams().get("classificacao");

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            // busca os dados da escala e turma do colaborador selecionado
            Criteria criteria = session.createCriteria(PontoTurmaVO.class);
            criteria.add(Restrictions.eq("codigo", colaborador.getCodigoTurmaPonto()));
            PontoTurmaVO turma = (PontoTurmaVO) criteria.uniqueResult();

            if (turma == null) {
                throw new Exception("Colaborador está sem o código da turma cadastrada!");
            }

            PontoFechamentoJornadaVO fechamento = new PontoFechamentoJornadaVO();
            fechamento.setColaborador(colaborador);
            fechamento.setDataFechamento(dataclassificar.getTime());
            fechamento.setPontoClassificacaoJornada(classificacao);

            criteria = session.createCriteria(PontoHorarioVO.class);

            switch (dataclassificar.get(Calendar.DAY_OF_WEEK)) {
                case 1: {
                    fechamento.setDiaSemana("DOMINGO");
                    criteria.add(Restrictions.eq("codigo", turma.getPontoEscala().getCodigoHorarioDomingo()));
                    break;
                }
                case 2: {
                    criteria.add(Restrictions.eq("codigo", turma.getPontoEscala().getCodigoHorarioSegunda()));
                    fechamento.setDiaSemana("SEGUNDA");
                    break;
                }
                case 3: {
                    criteria.add(Restrictions.eq("codigo", turma.getPontoEscala().getCodigoHorarioTerca()));
                    fechamento.setDiaSemana("TERCA");
                    break;
                }
                case 4: {
                    criteria.add(Restrictions.eq("codigo", turma.getPontoEscala().getCodigoHorarioQuarta()));
                    fechamento.setDiaSemana("QUARTA");
                    break;
                }
                case 5: {
                    criteria.add(Restrictions.eq("codigo", turma.getPontoEscala().getCodigoHorarioQuinta()));
                    fechamento.setDiaSemana("QUINTA");
                    break;
                }
                case 6: {
                    criteria.add(Restrictions.eq("codigo", turma.getPontoEscala().getCodigoHorarioSexta()));
                    fechamento.setDiaSemana("SEXTA");
                    break;
                }
                case 7: {
                    criteria.add(Restrictions.eq("codigo", turma.getPontoEscala().getCodigoHorarioSabado()));
                    fechamento.setDiaSemana("SABADO");
                    break;
                }
            }

            PontoHorarioVO horario = (PontoHorarioVO) criteria.uniqueResult();
            if (horario == null) {
                throw new Exception("Código do horário cadastrado na escala não corresponde a um horário armazenado!");
            }

            fechamento.setCodigoHorario(horario.getCodigo());
            fechamento.setCargaHorariaEsperada(horario.getCargaHoraria());
            if (classificacao.getDescontarHoras().equals("S")) {
                fechamento.setFaltaAtraso(horario.getCargaHoraria());
            }
            fechamento.setHoraInicioJornada(horario.getHoraInicioJornada());
            fechamento.setHoraFimJornada(horario.getHoraFimJornada());
            fechamento.setObservacao("Registro incluído via sistema pelo usuário " + usuario.getColaborador().getPessoa().getNome());

            session.save(fechamento);

            session.getTransaction().commit();

            return new VOResponse(fechamento);
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

    public Response update(Object inputPar, UserSessionParameters userSessionPars, HttpServletRequest request, HttpServletResponse response, HttpSession userSession, ServletContext context) {
        return null;
    }

    public Response delete(Object inputPar, UserSessionParameters userSessionPars, HttpServletRequest request, HttpServletResponse response, HttpSession userSession, ServletContext context) {
        return null;
    }

    public Response horariosColaborador(Object inputPar) {
        Session session = null;
        GridParams pars = (GridParams) inputPar;
        ColaboradorVO colaborador = (ColaboradorVO) pars.getOtherGridParams().get("colaborador");

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Criteria criteria = session.createCriteria(PontoTurmaVO.class);
            criteria.add(Restrictions.eq("codigo", colaborador.getCodigoTurmaPonto()));
            PontoTurmaVO turma = (PontoTurmaVO) criteria.uniqueResult();
            if (turma == null) {
                throw new Exception("Colaborador está sem o código da turma cadastrada!");
            }

            List<PontoHorarioVO> listaHorarios = new ArrayList<PontoHorarioVO>();

            PontoHorarioVO horario;
            //horario domingo
            criteria = session.createCriteria(PontoHorarioVO.class);
            criteria.add(Restrictions.eq("codigo", turma.getPontoEscala().getCodigoHorarioDomingo()));
            horario = (PontoHorarioVO) criteria.uniqueResult();
            if (horario != null) {
                listaHorarios.add(horario);
            }
            //horario segunda
            criteria = session.createCriteria(PontoHorarioVO.class);
            criteria.add(Restrictions.eq("codigo", turma.getPontoEscala().getCodigoHorarioSegunda()));
            horario = (PontoHorarioVO) criteria.uniqueResult();
            if (horario != null) {
                if (!listaHorarios.contains(horario)) {
                    listaHorarios.add(horario);
                }
            }
            //horario terca
            criteria = session.createCriteria(PontoHorarioVO.class);
            criteria.add(Restrictions.eq("codigo", turma.getPontoEscala().getCodigoHorarioTerca()));
            horario = (PontoHorarioVO) criteria.uniqueResult();
            if (horario != null) {
                if (!listaHorarios.contains(horario)) {
                    listaHorarios.add(horario);
                }
            }
            //horario quarta
            criteria = session.createCriteria(PontoHorarioVO.class);
            criteria.add(Restrictions.eq("codigo", turma.getPontoEscala().getCodigoHorarioQuarta()));
            horario = (PontoHorarioVO) criteria.uniqueResult();
            if (horario != null) {
                if (!listaHorarios.contains(horario)) {
                    listaHorarios.add(horario);
                }
            }
            //horario quinta
            criteria = session.createCriteria(PontoHorarioVO.class);
            criteria.add(Restrictions.eq("codigo", turma.getPontoEscala().getCodigoHorarioQuinta()));
            horario = (PontoHorarioVO) criteria.uniqueResult();
            if (horario != null) {
                if (!listaHorarios.contains(horario)) {
                    listaHorarios.add(horario);
                }
            }
            //horario sexta
            criteria = session.createCriteria(PontoHorarioVO.class);
            criteria.add(Restrictions.eq("codigo", turma.getPontoEscala().getCodigoHorarioSexta()));
            horario = (PontoHorarioVO) criteria.uniqueResult();
            if (horario != null) {
                if (!listaHorarios.contains(horario)) {
                    listaHorarios.add(horario);
                }
            }
            //horario sabado
            criteria = session.createCriteria(PontoHorarioVO.class);
            criteria.add(Restrictions.eq("codigo", turma.getPontoEscala().getCodigoHorarioSabado()));
            horario = (PontoHorarioVO) criteria.uniqueResult();
            if (horario != null) {
                if (!listaHorarios.contains(horario)) {
                    listaHorarios.add(horario);
                }
            }

            return new VOListResponse(listaHorarios, false, listaHorarios.size());
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

    public Response marcacoesColaborador(Object inputPar) {
        Session session = null;
        GridParams pars = (GridParams) inputPar;
        ColaboradorVO colaborador = (ColaboradorVO) pars.getOtherGridParams().get("colaborador");
        Date dataInicial = (Date) pars.getOtherGridParams().get("dataInicio");
        Date dataFinal = (Date) pars.getOtherGridParams().get("dataFim");

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Criteria criteria = session.createCriteria(PontoMarcacaoVO.class);
            criteria.add(Restrictions.eq("colaborador", colaborador));
            criteria.add(Restrictions.between("dataMarcacao", dataInicial, dataFinal));
            criteria.addOrder(Order.asc("dataMarcacao"));
            criteria.addOrder(Order.asc("horaMarcacao"));

            List<PontoMarcacaoVO> listaMarcacoes = criteria.list();

            return new VOListResponse(listaMarcacoes, false, listaMarcacoes.size());
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
