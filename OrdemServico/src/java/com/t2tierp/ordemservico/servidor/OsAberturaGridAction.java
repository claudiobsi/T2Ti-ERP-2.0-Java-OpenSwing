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
package com.t2tierp.ordemservico.servidor;

import com.t2tierp.ordemservico.java.OsAberturaEquipamentoVO;
import com.t2tierp.padrao.servidor.HibernateUtil;
import com.t2tierp.padrao.java.Constantes;
import com.t2tierp.ordemservico.java.OsAberturaVO;
import com.t2tierp.ordemservico.java.OsEvolucaoVO;
import com.t2tierp.ordemservico.java.OsProdutoServicoVO;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
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
import org.openswing.swing.message.receive.java.VOListResponse;
import org.openswing.swing.message.receive.java.VOResponse;
import org.openswing.swing.message.send.java.GridParams;
import org.openswing.swing.server.Action;
import org.openswing.swing.server.UserSessionParameters;
import org.openswing.swing.util.server.HibernateUtils;

public class OsAberturaGridAction implements Action {

    public OsAberturaGridAction() {
    }

    public String getRequestName() {
        return "osAberturaGridAction";
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
            case 99: {
                return mesclaLancamentos(inputPar, userSessionPars, request, response, userSession, context);
            }
        }
        return null;
    }

    private Response load(Object inputPar, UserSessionParameters userSessionPars, HttpServletRequest request, HttpServletResponse response, HttpSession userSession, ServletContext context) {
        Session session = null;
        GridParams pars = (GridParams) inputPar;
        String baseSQL = "select OS_ABERTURA from com.t2tierp.ordemservico.java.OsAberturaVO as OS_ABERTURA";
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Response res = HibernateUtils.getBlockFromQuery(
                    pars.getAction(),
                    pars.getStartPos(),
                    Constantes.TAMANHO_BLOCO, // block size...
                    pars.getFilteredColumns(),
                    pars.getCurrentSortedColumns(),
                    pars.getCurrentSortedVersusColumns(),
                    com.t2tierp.ordemservico.java.OsAberturaVO.class,
                    baseSQL,
                    new Object[0],
                    new Type[0],
                    "OS_ABERTURA",
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

    public Response insert(Object inputPar, UserSessionParameters userSessionPars, HttpServletRequest request, HttpServletResponse response, HttpSession userSession, ServletContext context) {
        return null;
    }

    public Response update(Object inputPar, UserSessionParameters userSessionPars, HttpServletRequest request, HttpServletResponse response, HttpSession userSession, ServletContext context) {
        return null;
    }

    public Response delete(Object inputPar, UserSessionParameters userSessionPars, HttpServletRequest request, HttpServletResponse response, HttpSession userSession, ServletContext context) {
        Session session = null;
        try {
            GridParams pars = (GridParams) inputPar;
            ArrayList persistentObjects = (ArrayList) pars.getOtherGridParams().get("persistentObjects");

            OsAberturaVO vo = null;

            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();

            for (int i = 0; i < persistentObjects.size(); i++) {
                vo = (OsAberturaVO) persistentObjects.get(i);
                session.delete(vo);
                session.flush();
            }

            session.getTransaction().commit();
            return new VOListResponse(persistentObjects, false, persistentObjects.size());
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

    public Response mesclaLancamentos(Object inputPar, UserSessionParameters userSessionPars, HttpServletRequest request, HttpServletResponse response, HttpSession userSession, ServletContext context) {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();

            GridParams pars = (GridParams) inputPar;
            List<OsAberturaVO> listaOsAbertura = (ArrayList) pars.getOtherGridParams().get("osSelecionados");
            if (listaOsAbertura.size() <= 1) {
                throw new Exception("Necessário selecionar 2 ou mais OS");
            }

            OsAberturaVO osMesclada = new OsAberturaVO();
            osMesclada.setOsStatus(listaOsAbertura.get(0).getOsStatus());
            osMesclada.setColaborador(listaOsAbertura.get(0).getColaborador());
            osMesclada.setCliente(listaOsAbertura.get(0).getCliente());
            osMesclada.setDataInicio(listaOsAbertura.get(0).getDataInicio());
            osMesclada.setDataFim(listaOsAbertura.get(0).getDataFim());
            osMesclada.setDataPrevisao(listaOsAbertura.get(0).getDataPrevisao());
            osMesclada.setFoneContato(listaOsAbertura.get(0).getFoneContato());
            osMesclada.setHoraFim(listaOsAbertura.get(0).getHoraFim());
            osMesclada.setHoraInicio(listaOsAbertura.get(0).getHoraInicio());
            osMesclada.setHoraPrevisao(listaOsAbertura.get(0).getHoraPrevisao());
            osMesclada.setNomeContato(listaOsAbertura.get(0).getNomeContato());
            osMesclada.setObservacaoAbertura(listaOsAbertura.get(0).getObservacaoAbertura());
            osMesclada.setObservacaoCliente(listaOsAbertura.get(0).getObservacaoCliente());
            osMesclada.setListaOsAberturaEquipamento(new ArrayList<>());
            osMesclada.setListaOsEvolucao(new ArrayList<>());
            osMesclada.setListaOsProdutoServico(new ArrayList<>());

            for (OsAberturaVO a : listaOsAbertura) {
                for (OsEvolucaoVO e : a.getListaOsEvolucao()) {
                    e.setId(null);
                    e.setOsAbertura(osMesclada);
                    osMesclada.getListaOsEvolucao().add(e);
                }
                for (OsAberturaEquipamentoVO e : a.getListaOsAberturaEquipamento()) {
                    e.setId(null);
                    e.setOsAbertura(osMesclada);
                    osMesclada.getListaOsAberturaEquipamento().add(e);
                }
                for (OsProdutoServicoVO e : a.getListaOsProdutoServico()) {
                    e.setId(null);
                    e.setOsAbertura(osMesclada);
                    osMesclada.getListaOsProdutoServico().add(e);
                }
            }

            session.save(osMesclada);
            
            osMesclada.setNumero("OS" + new DecimalFormat("0000000").format(osMesclada.getId()));

            for (OsAberturaVO a : listaOsAbertura) {
                a.setObservacaoAbertura("MESCLADO PARA OS NR " + osMesclada.getNumero() + "\n" + a.getObservacaoAbertura());
                session.update(a);
            }

            session.update(osMesclada);
            
            session.getTransaction().commit();

            return new VOResponse(osMesclada);
        } catch (Exception ex) {
            ex.printStackTrace();
            session.getTransaction().rollback();
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

}
