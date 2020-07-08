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

import com.t2tierp.padrao.java.Biblioteca;
import com.t2tierp.padrao.servidor.HibernateUtil;
import com.t2tierp.padrao.java.Constantes;
import com.t2tierp.ponto.java.AFDTipo3VO;
import com.t2tierp.ponto.java.PontoClassificacaoJornadaVO;
import com.t2tierp.ponto.java.PontoFechamentoJornadaVO;
import com.t2tierp.ponto.java.PontoParametroVO;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
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

public class PontoProcessaFechamentoJornadaAction implements Action {

    public PontoProcessaFechamentoJornadaAction() {
    }

    public String getRequestName() {
        return "pontoProcessaFechamentoJornadaAction";
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
        List<AFDTipo3VO> marcacoes = (Vector) pars.getOtherGridParams().get("marcacoes");
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            //busca a classificacao de jornada padrao
            Criteria criteria = session.createCriteria(PontoClassificacaoJornadaVO.class);
            criteria.add(Restrictions.eq("padrao", "S"));
            List<PontoClassificacaoJornadaVO> listaClassificacao = criteria.list();
            if (listaClassificacao.isEmpty()) {
                throw new Exception("Nenhuma classificação de jornada cadastrada como padrão!");
            }
            PontoClassificacaoJornadaVO classificacao = listaClassificacao.get(0);
            //ordena a lista pelo numero do PIS e Data da Marcacao
            Collections.sort(marcacoes, new Comparator() {

                public int compare(Object o1, Object o2) {
                    AFDTipo3VO m1 = (AFDTipo3VO) o1;
                    AFDTipo3VO m2 = (AFDTipo3VO) o2;
                    int resultado = m1.getPisEmpregado().compareTo(m2.getPisEmpregado());
                    if (resultado != 0) {
                        return resultado;
                    }
                    resultado = m1.getDataMarcacao().compareTo(m2.getDataMarcacao());
                    return resultado;
                }
            });

            String numeroPis = "";
            Calendar dataMarcacao = Calendar.getInstance();
            AFDTipo3VO marcacao;
            PontoFechamentoJornadaVO fechamentoJornada = null;
            List<PontoFechamentoJornadaVO> listaFechamento = new ArrayList<>();
            for (int i = 0; i < marcacoes.size(); i++) {
                marcacao = marcacoes.get(i);
                if (!marcacao.getDesconsiderar() && marcacao.getPontoHorario() != null) {
                    if ((!marcacao.getPisEmpregado().equals(numeroPis)) || (marcacao.getDataMarcacao().compareTo(dataMarcacao.getTime()) != 0)) {
                        numeroPis = marcacao.getPisEmpregado();
                        dataMarcacao.setTime(marcacao.getDataMarcacao());

                        fechamentoJornada = new PontoFechamentoJornadaVO();
                        fechamentoJornada.setPontoClassificacaoJornada(classificacao);
                        fechamentoJornada.setColaborador(marcacao.getColaborador());
                        fechamentoJornada.setDataFechamento(dataMarcacao.getTime());
                        switch (dataMarcacao.get(Calendar.DAY_OF_WEEK)) {
                            case 1:
                                fechamentoJornada.setDiaSemana("DOMINGO");
                                break;
                            case 2:
                                fechamentoJornada.setDiaSemana("SEGUNDA");
                                break;
                            case 3:
                                fechamentoJornada.setDiaSemana("TERCA");
                                break;
                            case 4:
                                fechamentoJornada.setDiaSemana("QUARTA");
                                break;
                            case 5:
                                fechamentoJornada.setDiaSemana("QUINTA");
                                break;
                            case 6:
                                fechamentoJornada.setDiaSemana("SEXTA");
                                break;
                            case 7:
                                fechamentoJornada.setDiaSemana("SABADO");
                                break;
                        }
                        fechamentoJornada.setCodigoHorario(marcacao.getPontoHorario().getCodigo());
                        fechamentoJornada.setCargaHorariaEsperada(marcacao.getPontoHorario().getCargaHoraria());
                        fechamentoJornada.setHoraInicioJornada(marcacao.getPontoHorario().getHoraInicioJornada());
                        fechamentoJornada.setHoraFimJornada(marcacao.getPontoHorario().getHoraFimJornada());

                        listaFechamento.add(fechamentoJornada);
                    }
                    defineTipoRegistro(fechamentoJornada, marcacao);
                }
            }

            //realiza os calculos necessarios
            Calendar dataFechamento = Calendar.getInstance();
            for (int i = 0; i < listaFechamento.size(); i++) {
                fechamentoJornada = listaFechamento.get(i);
                dataFechamento.setTime(fechamentoJornada.getDataFechamento());
                int mes = dataFechamento.get(Calendar.MONTH) + 1;
                int ano = dataFechamento.get(Calendar.YEAR);
                String mesAno = mes < 10 ? "0" + mes + "/" + ano : mes + "/" + ano;
                criteria = session.createCriteria(PontoParametroVO.class);
                criteria.add(Restrictions.eq("mesAno", mesAno));
                PontoParametroVO parametro = (PontoParametroVO) criteria.uniqueResult();
                if (parametro == null) {
                    throw new Exception("Não existe parâmetros cadastrados para o período " + mesAno);
                }
                //verifica se os pares Entrada/Saida foram registrados
                verificaParesFechamento(fechamentoJornada);

                //calcula a quantidade de horas trabalhadas
                fechamentoJornada.setCargaHorariaTotal(calculaHorasTrabalhados(fechamentoJornada));
                fechamentoJornada.setCargaHorariaNoturna(calculaCargaHorariaNoturna(fechamentoJornada, parametro));
                fechamentoJornada.setCargaHorariaDiurna(calculaCargaHorariaDiurna(fechamentoJornada));
                calculaHorasExtras(fechamentoJornada, parametro);
            }

            return new VOListResponse(listaFechamento, false, listaFechamento.size());
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
            List<PontoFechamentoJornadaVO> listaFechamento = (Vector) pars.getOtherGridParams().get("listaFechamento");

            PontoFechamentoJornadaVO vo = null;

            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();

            for (int i = 0; i < listaFechamento.size(); i++) {
                vo = listaFechamento.get(i);
                session.save(vo);
            }

            session.getTransaction().commit();
            return new VOListResponse(listaFechamento, false, listaFechamento.size());
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
        return null;
    }

    public Response delete(Object inputPar, UserSessionParameters userSessionPars, HttpServletRequest request, HttpServletResponse response, HttpSession userSession, ServletContext context) {
        return null;
    }

    private void defineTipoRegistro(PontoFechamentoJornadaVO fechamento, AFDTipo3VO marcacao) {
        if (marcacao.getParEntradaSaida() != null) {
            if (marcacao.getParEntradaSaida().equals("E1")) {
                fechamento.setEntrada01(marcacao.getHoraMarcacao());
            }
            if (marcacao.getParEntradaSaida().equals("S1")) {
                fechamento.setSaida01(marcacao.getHoraMarcacao());
            }
            if (marcacao.getParEntradaSaida().equals("E2")) {
                fechamento.setEntrada02(marcacao.getHoraMarcacao());
            }
            if (marcacao.getParEntradaSaida().equals("S2")) {
                fechamento.setSaida02(marcacao.getHoraMarcacao());
            }
            if (marcacao.getParEntradaSaida().equals("E3")) {
                fechamento.setEntrada03(marcacao.getHoraMarcacao());
            }
            if (marcacao.getParEntradaSaida().equals("S3")) {
                fechamento.setSaida03(marcacao.getHoraMarcacao());
            }
            if (marcacao.getParEntradaSaida().equals("E4")) {
                fechamento.setEntrada04(marcacao.getHoraMarcacao());
            }
            if (marcacao.getParEntradaSaida().equals("S4")) {
                fechamento.setSaida04(marcacao.getHoraMarcacao());
            }
            if (marcacao.getParEntradaSaida().equals("E5")) {
                fechamento.setEntrada05(marcacao.getHoraMarcacao());
            }
            if (marcacao.getParEntradaSaida().equals("S5")) {
                fechamento.setSaida05(marcacao.getHoraMarcacao());
            }
        }
    }

    private void verificaParesFechamento(PontoFechamentoJornadaVO fechamento) {
        if (fechamento.getEntrada01() != null && fechamento.getSaida01() == null) {
            fechamento.setObservacao("Registro de entrada sem o registro de saída correspondente!");
        }
        if (fechamento.getEntrada01() == null && fechamento.getSaida01() != null) {
            fechamento.setObservacao("Registro de saida sem o registro de entrada correspondente!");
        }
        if (fechamento.getEntrada02() != null && fechamento.getSaida02() == null) {
            fechamento.setObservacao("Registro de entrada sem o registro de saída correspondente!");
        }
        if (fechamento.getEntrada02() == null && fechamento.getSaida02() != null) {
            fechamento.setObservacao("Registro de saida sem o registro de entrada correspondente!");
        }
        if (fechamento.getEntrada03() != null && fechamento.getSaida03() == null) {
            fechamento.setObservacao("Registro de entrada sem o registro de saída correspondente!");
        }
        if (fechamento.getEntrada03() == null && fechamento.getSaida03() != null) {
            fechamento.setObservacao("Registro de saida sem o registro de entrada correspondente!");
        }
        if (fechamento.getEntrada04() != null && fechamento.getSaida04() == null) {
            fechamento.setObservacao("Registro de entrada sem o registro de saída correspondente!");
        }
        if (fechamento.getEntrada04() == null && fechamento.getSaida04() != null) {
            fechamento.setObservacao("Registro de saida sem o registro de entrada correspondente!");
        }
        if (fechamento.getEntrada05() != null && fechamento.getSaida05() == null) {
            fechamento.setObservacao("Registro de entrada sem o registro de saída correspondente!");
        }
        if (fechamento.getEntrada05() == null && fechamento.getSaida05() != null) {
            fechamento.setObservacao("Registro de saida sem o registro de entrada correspondente!");
        }
    }

    private String calculaHorasTrabalhados(PontoFechamentoJornadaVO fechamento) {
        int segundosTrabalhados = 0;
        Calendar dataC = Calendar.getInstance();
        //par E1/S1
        if (fechamento.getEntrada01() != null && fechamento.getSaida01() != null) {
            dataC = Biblioteca.horaStrToCalendar(fechamento.getSaida01());
            segundosTrabalhados += Biblioteca.getHoraSeg(dataC);

            dataC = Biblioteca.horaStrToCalendar(fechamento.getEntrada01());
            segundosTrabalhados -= Biblioteca.getHoraSeg(dataC);
        }

        //par E2/S2
        if (fechamento.getEntrada02() != null && fechamento.getSaida02() != null) {
            dataC = Biblioteca.horaStrToCalendar(fechamento.getSaida02());
            segundosTrabalhados += Biblioteca.getHoraSeg(dataC);

            dataC = Biblioteca.horaStrToCalendar(fechamento.getEntrada02());
            segundosTrabalhados -= Biblioteca.getHoraSeg(dataC);
        }
        //par E3/S3
        if (fechamento.getEntrada03() != null && fechamento.getSaida03() != null) {
            dataC = Biblioteca.horaStrToCalendar(fechamento.getSaida03());
            segundosTrabalhados += Biblioteca.getHoraSeg(dataC);

            dataC = Biblioteca.horaStrToCalendar(fechamento.getEntrada03());
            segundosTrabalhados -= Biblioteca.getHoraSeg(dataC);
        }
        //par E4/S4
        if (fechamento.getEntrada04() != null && fechamento.getSaida04() != null) {
            dataC = Biblioteca.horaStrToCalendar(fechamento.getSaida04());
            segundosTrabalhados += Biblioteca.getHoraSeg(dataC);

            dataC = Biblioteca.horaStrToCalendar(fechamento.getEntrada01());
            segundosTrabalhados -= Biblioteca.getHoraSeg(dataC);
        }
        //par E5/S5
        if (fechamento.getEntrada05() != null && fechamento.getSaida05() != null) {
            dataC = Biblioteca.horaStrToCalendar(fechamento.getSaida05());
            segundosTrabalhados += Biblioteca.getHoraSeg(dataC);

            dataC = Biblioteca.horaStrToCalendar(fechamento.getEntrada01());
            segundosTrabalhados -= Biblioteca.getHoraSeg(dataC);
        }
        return Biblioteca.getHoraMinutoSegundo(segundosTrabalhados);
    }

    private String calculaCargaHorariaNoturna(PontoFechamentoJornadaVO fechamento, PontoParametroVO parametro) {
        int segundosHoraNoturnaTrabalhada = 0;
        int segundosHoraSaida = 0;
        int segundosHoraEntrada = 0;
        int inicioHoraNoturna = Biblioteca.getHoraSeg(Biblioteca.horaStrToCalendar(parametro.getHoraNoturnaInicio()));
        int fimHoraNoturna = Biblioteca.getHoraSeg(Biblioteca.horaStrToCalendar(parametro.getHoraNoturnaFim()));

        //par E1/S1
        if (fechamento.getEntrada01() != null && fechamento.getSaida01() != null) {
            segundosHoraEntrada = Biblioteca.getHoraSeg(Biblioteca.horaStrToCalendar(fechamento.getEntrada01()));
            segundosHoraSaida = Biblioteca.getHoraSeg(Biblioteca.horaStrToCalendar(fechamento.getSaida01()));

            segundosHoraNoturnaTrabalhada += calculaSegundosHoraNoturna(segundosHoraEntrada, segundosHoraSaida, inicioHoraNoturna, fimHoraNoturna);
        }
        //par E2/S2
        if (fechamento.getEntrada02() != null && fechamento.getSaida02() != null) {
            segundosHoraEntrada = Biblioteca.getHoraSeg(Biblioteca.horaStrToCalendar(fechamento.getEntrada02()));
            segundosHoraSaida = Biblioteca.getHoraSeg(Biblioteca.horaStrToCalendar(fechamento.getSaida02()));

            segundosHoraNoturnaTrabalhada += calculaSegundosHoraNoturna(segundosHoraEntrada, segundosHoraSaida, inicioHoraNoturna, fimHoraNoturna);
        }
        //par E3/S3
        if (fechamento.getEntrada03() != null && fechamento.getSaida03() != null) {
            segundosHoraEntrada = Biblioteca.getHoraSeg(Biblioteca.horaStrToCalendar(fechamento.getEntrada03()));
            segundosHoraSaida = Biblioteca.getHoraSeg(Biblioteca.horaStrToCalendar(fechamento.getSaida03()));

            segundosHoraNoturnaTrabalhada += calculaSegundosHoraNoturna(segundosHoraEntrada, segundosHoraSaida, inicioHoraNoturna, fimHoraNoturna);
        }
        //par E4/S4
        if (fechamento.getEntrada04() != null && fechamento.getSaida04() != null) {
            segundosHoraEntrada = Biblioteca.getHoraSeg(Biblioteca.horaStrToCalendar(fechamento.getEntrada04()));
            segundosHoraSaida = Biblioteca.getHoraSeg(Biblioteca.horaStrToCalendar(fechamento.getSaida04()));

            segundosHoraNoturnaTrabalhada += calculaSegundosHoraNoturna(segundosHoraEntrada, segundosHoraSaida, inicioHoraNoturna, fimHoraNoturna);
        }
        //par E5/S5
        if (fechamento.getEntrada05() != null && fechamento.getSaida05() != null) {
            segundosHoraEntrada = Biblioteca.getHoraSeg(Biblioteca.horaStrToCalendar(fechamento.getEntrada05()));
            segundosHoraSaida = Biblioteca.getHoraSeg(Biblioteca.horaStrToCalendar(fechamento.getSaida05()));

            segundosHoraNoturnaTrabalhada += calculaSegundosHoraNoturna(segundosHoraEntrada, segundosHoraSaida, inicioHoraNoturna, fimHoraNoturna);
        }

        return Biblioteca.getHoraMinutoSegundo(segundosHoraNoturnaTrabalhada);
    }

    private int calculaSegundosHoraNoturna(int segundosHoraEntrada, int segundosHoraSaida, int inicioHoraNoturna, int fimHoraNoturna) {
        if (segundosHoraSaida > inicioHoraNoturna) {
            if (segundosHoraEntrada > inicioHoraNoturna) {
                return segundosHoraSaida - segundosHoraEntrada;
            } else {
                return segundosHoraSaida - inicioHoraNoturna;
            }
        }
        if (segundosHoraEntrada < fimHoraNoturna) {
            if (segundosHoraSaida < fimHoraNoturna) {
                return segundosHoraSaida - segundosHoraEntrada;
            } else {
                return fimHoraNoturna - segundosHoraEntrada;
            }
        }
        return 0;
    }

    private String calculaCargaHorariaDiurna(PontoFechamentoJornadaVO fechamento) {
        int segundosCargaHorariaTotal = Biblioteca.getHoraSeg(Biblioteca.horaStrToCalendar(fechamento.getCargaHorariaTotal()));
        int segundosCargaHorariaNoturna = Biblioteca.getHoraSeg(Biblioteca.horaStrToCalendar(fechamento.getCargaHorariaNoturna()));

        return Biblioteca.getHoraMinutoSegundo(segundosCargaHorariaTotal - segundosCargaHorariaNoturna);
    }

    private void calculaHorasExtras(PontoFechamentoJornadaVO fechamento, PontoParametroVO parametro) {

        int segundosTrabalhados = Biblioteca.getHoraSeg(Biblioteca.horaStrToCalendar(fechamento.getCargaHorariaTotal()));
        int segundosCargaHoraria = Biblioteca.getHoraSeg(Biblioteca.horaStrToCalendar(fechamento.getCargaHorariaEsperada()));
        int cargaHorariaNoturna = Biblioteca.getHoraSeg(Biblioteca.horaStrToCalendar(fechamento.getCargaHorariaNoturna()));
        int totalHoraExtra = 0;
        if (segundosTrabalhados > segundosCargaHoraria) {
            totalHoraExtra = segundosTrabalhados - segundosCargaHoraria;
            if (parametro.getTratamentoHoraMais().equals("E")) {
                if (cargaHorariaNoturna > 0) {
                    if (cargaHorariaNoturna == totalHoraExtra) {
                        fechamento.setHoraExtra01(Biblioteca.getHoraMinutoSegundo(cargaHorariaNoturna));
                        fechamento.setPercentualHoraExtra01(parametro.getPercentualHeNoturna());
                        fechamento.setModalidadeHoraExtra01("N");
                    } else if (cargaHorariaNoturna < totalHoraExtra) {
                        fechamento.setHoraExtra01(Biblioteca.getHoraMinutoSegundo(cargaHorariaNoturna));
                        fechamento.setPercentualHoraExtra01(parametro.getPercentualHeNoturna());
                        fechamento.setModalidadeHoraExtra01("N");

                        fechamento.setHoraExtra02(Biblioteca.getHoraMinutoSegundo(totalHoraExtra - cargaHorariaNoturna));
                        fechamento.setPercentualHoraExtra02(parametro.getPercentualHeDiurna());
                        fechamento.setModalidadeHoraExtra02("D");
                    }
                } else {
                    fechamento.setPercentualHoraExtra01(parametro.getPercentualHeDiurna());
                    fechamento.setModalidadeHoraExtra01("D");
                }
            } else if (parametro.getTratamentoHoraMais().equals("B")) {
                fechamento.setCompensar("1");
                if (cargaHorariaNoturna > 0) {
                    if ((cargaHorariaNoturna == totalHoraExtra) || cargaHorariaNoturna > totalHoraExtra) {
                        fechamento.setBancoHoras(Biblioteca.getHoraMinutoSegundo(totalHoraExtra * (8 / 7)));
                    } else if (cargaHorariaNoturna < totalHoraExtra) {
                        fechamento.setBancoHoras(Biblioteca.getHoraMinutoSegundo((cargaHorariaNoturna * (8 / 7))
                                + (totalHoraExtra - cargaHorariaNoturna)));
                    }
                } else {
                    fechamento.setBancoHoras(Biblioteca.getHoraMinutoSegundo(totalHoraExtra));
                }
            }
        } else if (segundosTrabalhados < segundosCargaHoraria) {
            totalHoraExtra = segundosCargaHoraria - segundosTrabalhados;
            if (parametro.getTratamentoHoraMenos().equals("F")) {
                fechamento.setFaltaAtraso(Biblioteca.getHoraMinutoSegundo(totalHoraExtra));
            } else {
                fechamento.setCompensar("2");
                fechamento.setBancoHoras(Biblioteca.getHoraMinutoSegundo(totalHoraExtra));
            }
        }
    }
}
