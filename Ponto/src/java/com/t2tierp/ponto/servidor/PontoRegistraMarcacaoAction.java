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

import com.t2tierp.cadastros.java.UsuarioVO;
import com.t2tierp.padrao.java.Constantes;
import com.t2tierp.padrao.servidor.HibernateUtil;
import com.t2tierp.ponto.java.PontoMarcacaoVO;
import java.text.SimpleDateFormat;
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
import org.openswing.swing.message.receive.java.VOResponse;
import org.openswing.swing.server.Action;
import org.openswing.swing.server.UserSessionParameters;

public class PontoRegistraMarcacaoAction implements Action {

    public PontoRegistraMarcacaoAction() {
    }

    public String getRequestName() {
        return "pontoRegistraMarcacaoAction";
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
            UsuarioVO usuario = (UsuarioVO) pars[1];

            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();

            Criteria criteria = session.createCriteria(UsuarioVO.class);
            criteria.add(Restrictions.eq("login", usuario.getLogin()));
            criteria.add(Restrictions.eq("senha", usuario.getSenha()));
            usuario = (UsuarioVO) criteria.uniqueResult();

            if (usuario == null) {
                return new ErrorResponse("Usuário Inválido!");
            }

            Calendar dataAtual = Calendar.getInstance();
            SimpleDateFormat formatoHora = new SimpleDateFormat("HH:mm:ss");

            PontoMarcacaoVO marcacao = new PontoMarcacaoVO();
            marcacao.setColaborador(usuario.getColaborador());
            marcacao.setDataMarcacao(dataAtual.getTime());
            marcacao.setHoraMarcacao(formatoHora.format(dataAtual.getTime()));
            marcacao.setTipoRegistro("O");
            //define o tipo de marcacao
            criteria = session.createCriteria(PontoMarcacaoVO.class);
            criteria.add(Restrictions.eq("colaborador", usuario.getColaborador()));
            criteria.add(Restrictions.eq("dataMarcacao", dataAtual.getTime()));
            criteria.addOrder(Order.asc("horaMarcacao"));

            List<PontoMarcacaoVO> listaMarcacaoes = criteria.list();
            String tipoMarcacao = "E";
            int par = 1;
            for (int i = 0; i < listaMarcacaoes.size(); i++) {
                if (listaMarcacaoes.get(i).getTipoMarcacao().equals("E")) {
                    tipoMarcacao = "S";
                } else if (listaMarcacaoes.get(i).getTipoMarcacao().equals("S")) {
                    tipoMarcacao = "E";
                }
                par = Integer.valueOf(listaMarcacaoes.get(i).getParEntradaSaida().substring(1));
            }
            if (!listaMarcacaoes.isEmpty()) {
                if (listaMarcacaoes.size() % 2 == 0) {
                    par += 1;
                }
            }
            marcacao.setTipoMarcacao(tipoMarcacao);
            marcacao.setParEntradaSaida(tipoMarcacao + par);

            session.save(marcacao);

            session.getTransaction().commit();

            return new VOResponse(usuario);
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

    public Response update(Object inputPar, UserSessionParameters userSessionPars, HttpServletRequest request, HttpServletResponse response, HttpSession userSession, ServletContext context) {
        return null;
    }

    public Response delete(Object inputPar, UserSessionParameters userSessionPars, HttpServletRequest request, HttpServletResponse response, HttpSession userSession, ServletContext context) {
        return null;
    }
}
