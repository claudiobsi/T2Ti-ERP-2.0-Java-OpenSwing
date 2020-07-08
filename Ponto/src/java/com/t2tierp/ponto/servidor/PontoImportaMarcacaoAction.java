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
import com.t2tierp.padrao.java.Biblioteca;
import com.t2tierp.padrao.servidor.HibernateUtil;
import com.t2tierp.ponto.java.AFDTipo3VO;
import com.t2tierp.ponto.java.PontoEscalaVO;
import com.t2tierp.ponto.java.PontoHorarioVO;
import com.t2tierp.ponto.java.PontoMarcacaoVO;
import com.t2tierp.ponto.java.PontoRelogioVO;
import com.t2tierp.ponto.java.PontoTurmaVO;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
import org.openswing.swing.server.Action;
import org.openswing.swing.server.UserSessionParameters;

public class PontoImportaMarcacaoAction implements Action {

    public PontoImportaMarcacaoAction() {
    }

    public String getRequestName() {
        return "pontoImportaMarcacaoAction";
    }

    public Response executeCommand(Object inputPar, UserSessionParameters userSessionPars, HttpServletRequest request, HttpServletResponse response, HttpSession userSession, ServletContext context) {
        Session session = null;
        try {
            List<AFDTipo3VO> marcacoes = (Vector) inputPar;

            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();

            //busca os dados do REP
            Criteria criteria = session.createCriteria(PontoRelogioVO.class);
            criteria.add(Restrictions.eq("numeroSerie", marcacoes.get(0).getNumeroSerieRelogioPonto()));
            PontoRelogioVO relogio = (PontoRelogioVO) criteria.uniqueResult();

            ColaboradorVO colaborador;
            PontoMarcacaoVO pontoMarcacao;
            for (int i = 0; i < marcacoes.size(); i++) {
                if (relogio == null) {
                    marcacoes.get(i).setSituacaoRegistro("Relógio de Ponto não encontrado na base de dados!");
                } else {
                    //busca os dados do colaborador
                    criteria = session.createCriteria(ColaboradorVO.class);
                    criteria.add(Restrictions.eq("pisNumero", marcacoes.get(i).getPisEmpregado()));
                    colaborador = (ColaboradorVO) criteria.uniqueResult();

                    if (colaborador == null) {
                        marcacoes.get(i).setSituacaoRegistro("Nr. PIS não cadastrado!");
                    } else {
                        marcacoes.get(i).setColaborador(colaborador);
                        if (colaborador.getCodigoTurmaPonto() == null) {
                            marcacoes.get(i).setSituacaoRegistro("Colaborador está sem o código da turma cadastrada!");
                        } else {
                            //Verifica se o registro já foi armazenado no banco de dados
                            criteria = session.createCriteria(PontoMarcacaoVO.class);
                            criteria.add(Restrictions.eq("colaborador", colaborador));
                            criteria.add(Restrictions.eq("pontoRelogio", relogio));
                            criteria.add(Restrictions.eq("dataMarcacao", marcacoes.get(i).getDataMarcacao()));
                            criteria.add(Restrictions.eq("horaMarcacao", marcacoes.get(i).getHoraMarcacao()));
                            PontoMarcacaoVO marc = (PontoMarcacaoVO) criteria.uniqueResult();

                            //busca os horários
                            criteria = session.createCriteria(PontoTurmaVO.class);
                            criteria.add(Restrictions.eq("codigo", colaborador.getCodigoTurmaPonto()));
                            PontoTurmaVO turma = (PontoTurmaVO) criteria.uniqueResult();

                            if (marc == null) {
                                if (turma != null) {
                                    pontoMarcacao = new PontoMarcacaoVO();
                                    pontoMarcacao.setColaborador(colaborador);
                                    pontoMarcacao.setPontoRelogio(relogio);
                                    if (marcacoes.get(i).getSequencial() != null) {
                                        pontoMarcacao.setNsr(marcacoes.get(i).getSequencial().intValue());
                                    }
                                    pontoMarcacao.setDataMarcacao(marcacoes.get(i).getDataMarcacao());
                                    pontoMarcacao.setHoraMarcacao(marcacoes.get(i).getHoraMarcacao());
                                    try {
                                        Map m = defineTipoMarcacaoHorario(turma.getPontoEscala(), pontoMarcacao, session);
                                        String parEntradaSaida = (String) m.get("parEntradaSaida");
                                        if (marcacoes.get(i).getDesconsiderar()) {
                                            pontoMarcacao.setTipoMarcacao("D");
                                        } else {
                                            pontoMarcacao.setTipoMarcacao(parEntradaSaida.substring(0, 1));
                                        }
                                        pontoMarcacao.setTipoRegistro(marcacoes.get(i).getTipoRegistro());
                                        pontoMarcacao.setParEntradaSaida(parEntradaSaida);
                                        pontoMarcacao.setJustificativa(marcacoes.get(i).getJustificativa());

                                        session.save(pontoMarcacao);

                                        marcacoes.get(i).setColaborador(colaborador);
                                        marcacoes.get(i).setSituacaoRegistro("Registro incluído!");
                                        marcacoes.get(i).setPontoHorario((PontoHorarioVO) m.get("pontoHorario"));
                                        marcacoes.get(i).setParEntradaSaida(parEntradaSaida);
                                    } catch (Exception e) {
                                        marcacoes.get(i).setSituacaoRegistro(e.getMessage());
                                    }
                                }
                            } else {
                                marcacoes.get(i).setSituacaoRegistro("Registro já incluído anteriormente!");
                                marcacoes.get(i).setParEntradaSaida(marc.getParEntradaSaida());
                                Map m = defineTipoMarcacaoHorario(turma.getPontoEscala(), marc, session);
                                marcacoes.get(i).setPontoHorario((PontoHorarioVO) m.get("pontoHorario"));

                                if (marcacoes.get(i).getDesconsiderar()) {
                                    marc.setTipoMarcacao("D");
                                } else {
                                    marc.setTipoMarcacao(marcacoes.get(i).getParEntradaSaida().substring(0, 1));
                                }
                                marc.setTipoRegistro(marcacoes.get(i).getTipoRegistro());
                                marc.setJustificativa(marcacoes.get(i).getJustificativa());

                                session.update(marc);
                            }
                        }
                    }
                }
            }
            session.getTransaction().commit();

            return new VOListResponse(marcacoes, false, marcacoes.size());
        } catch (Exception ex) {
            ex.printStackTrace();
            session.getTransaction().rollback();
            return new ErrorResponse(ex.getMessage());
        } finally {
            try {
                session.close();
            } catch (Exception ex1) {
                ex1.printStackTrace();
            }
        }
    }

    private Map defineTipoMarcacaoHorario(PontoEscalaVO escala, PontoMarcacaoVO marcacao, Session session) throws Exception {
        Calendar dataMarcacao = Calendar.getInstance();
        dataMarcacao.setTime(marcacao.getDataMarcacao());
        dataMarcacao.set(Calendar.HOUR_OF_DAY, Integer.valueOf(marcacao.getHoraMarcacao().substring(0, 2)));
        dataMarcacao.set(Calendar.MINUTE, Integer.valueOf(marcacao.getHoraMarcacao().substring(3, 5)));
        dataMarcacao.set(Calendar.SECOND, Integer.valueOf(marcacao.getHoraMarcacao().substring(6, 8)));
        PontoHorarioVO horario;
        Criteria criteria = session.createCriteria(PontoHorarioVO.class);
        //busca o horario de acordo com o dia da semana
        switch (dataMarcacao.get(Calendar.DAY_OF_WEEK)) {
            case 1: {//domingo
                criteria.add(Restrictions.eq("codigo", escala.getCodigoHorarioDomingo()));
                break;
            }
            case 2: {//seguna
                criteria.add(Restrictions.eq("codigo", escala.getCodigoHorarioSegunda()));
                break;
            }
            case 3: {//terca
                criteria.add(Restrictions.eq("codigo", escala.getCodigoHorarioTerca()));
                break;
            }
            case 4: {//quarta
                criteria.add(Restrictions.eq("codigo", escala.getCodigoHorarioQuarta()));
                break;
            }
            case 5: {//quinta
                criteria.add(Restrictions.eq("codigo", escala.getCodigoHorarioQuinta()));
                break;
            }
            case 6: {//sexta
                criteria.add(Restrictions.eq("codigo", escala.getCodigoHorarioSexta()));
                break;
            }
            case 7: {//sabado
                criteria.add(Restrictions.eq("codigo", escala.getCodigoHorarioSabado()));
                break;
            }
        }
        horario = (PontoHorarioVO) criteria.uniqueResult();
        if (horario == null) {
            throw new Exception("Código do horário cadastrado na escala não corresponde a um horário armazenado!");
        }

        SimpleDateFormat formatoHora = new SimpleDateFormat("HH:mm:ss");
        int segundosHoraMarcada = Biblioteca.getHoraSeg(dataMarcacao);
        int segundosHoraHorario = 0;
        int diferençaSegundos = 0;
        int diferençaSegundosAnterior = 0;
        String parEntradaSaida = null;
        Calendar dataHorario = Calendar.getInstance();
        //verifica qual registro foi efetuado de acordo com os horários cadastrados
        //será realizada a diferença entre os segundos da marcaçao e os segundos do horário cadastrado
        //o tipo de marcação a ser registrada é o que der a menor diferença entre os registros
        if (horario.getEntrada01() != null) {
            dataHorario.setTime(formatoHora.parse(horario.getEntrada01()));
            segundosHoraHorario = Biblioteca.getHoraSeg(dataHorario);
            diferençaSegundos = Math.abs(segundosHoraMarcada - segundosHoraHorario);
            diferençaSegundosAnterior = diferençaSegundos;
            parEntradaSaida = "E1";
        }
        if (horario.getSaida01() != null) {
            dataHorario.setTime(formatoHora.parse(horario.getSaida01()));
            segundosHoraHorario = Biblioteca.getHoraSeg(dataHorario);
            diferençaSegundos = Math.abs(segundosHoraMarcada - segundosHoraHorario);
            if (diferençaSegundos < diferençaSegundosAnterior) {
                diferençaSegundosAnterior = diferençaSegundos;
                parEntradaSaida = "S1";
            }
        }
        if (horario.getEntrada02() != null) {
            dataHorario.setTime(formatoHora.parse(horario.getEntrada02()));
            segundosHoraHorario = Biblioteca.getHoraSeg(dataHorario);
            diferençaSegundos = Math.abs(segundosHoraMarcada - segundosHoraHorario);
            if (diferençaSegundos < diferençaSegundosAnterior) {
                diferençaSegundosAnterior = diferençaSegundos;
                parEntradaSaida = "E2";
            }
        }
        if (horario.getSaida02() != null) {
            dataHorario.setTime(formatoHora.parse(horario.getSaida02()));
            segundosHoraHorario = Biblioteca.getHoraSeg(dataHorario);
            diferençaSegundos = Math.abs(segundosHoraMarcada - segundosHoraHorario);
            if (diferençaSegundos < diferençaSegundosAnterior) {
                diferençaSegundosAnterior = diferençaSegundos;
                parEntradaSaida = "S2";
            }
        }
        if (horario.getEntrada03() != null) {
            dataHorario.setTime(formatoHora.parse(horario.getEntrada03()));
            segundosHoraHorario = Biblioteca.getHoraSeg(dataHorario);
            diferençaSegundos = Math.abs(segundosHoraMarcada - segundosHoraHorario);
            if (diferençaSegundos < diferençaSegundosAnterior) {
                diferençaSegundosAnterior = diferençaSegundos;
                parEntradaSaida = "E3";
            }
        }
        if (horario.getSaida03() != null) {
            dataHorario.setTime(formatoHora.parse(horario.getSaida03()));
            segundosHoraHorario = Biblioteca.getHoraSeg(dataHorario);
            diferençaSegundos = Math.abs(segundosHoraMarcada - segundosHoraHorario);
            if (diferençaSegundos < diferençaSegundosAnterior) {
                diferençaSegundosAnterior = diferençaSegundos;
                parEntradaSaida = "S3";
            }
        }
        if (horario.getEntrada04() != null) {
            dataHorario.setTime(formatoHora.parse(horario.getEntrada04()));
            segundosHoraHorario = Biblioteca.getHoraSeg(dataHorario);
            diferençaSegundos = Math.abs(segundosHoraMarcada - segundosHoraHorario);
            if (diferençaSegundos < diferençaSegundosAnterior) {
                diferençaSegundosAnterior = diferençaSegundos;
                parEntradaSaida = "E4";
            }
        }
        if (horario.getSaida04() != null) {
            dataHorario.setTime(formatoHora.parse(horario.getSaida04()));
            segundosHoraHorario = Biblioteca.getHoraSeg(dataHorario);
            diferençaSegundos = Math.abs(segundosHoraMarcada - segundosHoraHorario);
            if (diferençaSegundos < diferençaSegundosAnterior) {
                diferençaSegundosAnterior = diferençaSegundos;
                parEntradaSaida = "S4";
            }
        }
        if (horario.getEntrada05() != null) {
            dataHorario.setTime(formatoHora.parse(horario.getEntrada05()));
            segundosHoraHorario = Biblioteca.getHoraSeg(dataHorario);
            diferençaSegundos = Math.abs(segundosHoraMarcada - segundosHoraHorario);
            if (diferençaSegundos < diferençaSegundosAnterior) {
                diferençaSegundosAnterior = diferençaSegundos;
                parEntradaSaida = "E5";
            }
        }
        if (horario.getSaida05() != null) {
            dataHorario.setTime(formatoHora.parse(horario.getSaida05()));
            segundosHoraHorario = Biblioteca.getHoraSeg(dataHorario);
            diferençaSegundos = Math.abs(segundosHoraMarcada - segundosHoraHorario);
            if (diferençaSegundos < diferençaSegundosAnterior) {
                diferençaSegundosAnterior = diferençaSegundos;
                parEntradaSaida = "S5";
            }
        }
        Map resultado = new HashMap();
        resultado.put("pontoHorario", horario);
        resultado.put("parEntradaSaida", parEntradaSaida);

        return resultado;
    }
}
