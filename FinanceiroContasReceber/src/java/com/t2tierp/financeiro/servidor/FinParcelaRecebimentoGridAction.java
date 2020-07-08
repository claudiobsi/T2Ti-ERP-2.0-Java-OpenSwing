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

import com.t2tierp.administrativo.java.AdmParametroVO;
import com.t2tierp.cadastros.java.EmpresaVO;
import com.t2tierp.financeiro.java.FinParcelaRecebimentoVO;
import com.t2tierp.financeiro.java.FinStatusParcelaVO;
import com.t2tierp.financeiro.java.FinTipoRecebimentoVO;
import com.t2tierp.padrao.servidor.HibernateUtil;
import com.t2tierp.padrao.java.Constantes;
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
import org.openswing.swing.message.send.java.GridParams;
import org.openswing.swing.server.Action;
import org.openswing.swing.server.UserSessionParameters;
import org.openswing.swing.util.server.HibernateUtils;

public class FinParcelaRecebimentoGridAction implements Action {

    public FinParcelaRecebimentoGridAction() {
    }

    public String getRequestName() {
        return "finParcelaRecebimentoGridAction";
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

        String baseSQL = "select FIN_PARCELA_RECEBER from com.t2tierp.financeiro.java.FinParcelaReceberVO as FIN_PARCELA_RECEBER";
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Response res = HibernateUtils.getBlockFromQuery(
                    pars.getAction(),
                    pars.getStartPos(),
                    Constantes.TAMANHO_BLOCO, // block size...
                    pars.getFilteredColumns(),
                    pars.getCurrentSortedColumns(),
                    pars.getCurrentSortedVersusColumns(),
                    com.t2tierp.financeiro.java.FinParcelaReceberVO.class,
                    baseSQL,
                    new Object[0],
                    new Type[0],
                    "FIN_PARCELA_RECEBER",
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
        Session session = null;
        try {
            GridParams pars = (GridParams) inputPar;
            List<FinParcelaRecebimentoVO> listaRecebimentos = (ArrayList) pars.getOtherGridParams().get("recebimentos");
            EmpresaVO empresa = (EmpresaVO) pars.getOtherGridParams().get("empresa");

            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();

            Criteria criteria = session.createCriteria(AdmParametroVO.class);
            criteria.add(Restrictions.eq("empresa", empresa));
            AdmParametroVO admParametro = (AdmParametroVO) criteria.uniqueResult();

            FinStatusParcelaVO statusParcela = null;
            if (admParametro != null) {
                criteria = session.createCriteria(FinStatusParcelaVO.class);
                criteria.add(Restrictions.eq("id", admParametro.getFinParcelaQuitado()));
                
                statusParcela = (FinStatusParcelaVO) criteria.uniqueResult();
            }
            if (statusParcela == null) {
                return new ErrorResponse("Status de parcela não cadastrado. Entre em contato com a Software House");
            }

            criteria = session.createCriteria(FinTipoRecebimentoVO.class);
            criteria.add(Restrictions.eq("tipo", "02"));
            FinTipoRecebimentoVO tipoRecebimento = (FinTipoRecebimentoVO) criteria.uniqueResult();
            if (tipoRecebimento == null) {
                return new ErrorResponse("Tipo de recebimento não cadastrado. Entre em contato com a Software House");
            }

            session.save(listaRecebimentos.get(0).getFinChequeRecebido());

            for (int i = 0; i < listaRecebimentos.size(); i++) {
                listaRecebimentos.get(i).setFinTipoRecebimento(tipoRecebimento);
                session.save(listaRecebimentos.get(i));

                listaRecebimentos.get(i).getFinParcelaReceber().setFinStatusParcela(statusParcela);
                session.update(listaRecebimentos.get(i).getFinParcelaReceber());
            }

            session.getTransaction().commit();

            return new VOListResponse(listaRecebimentos, false, listaRecebimentos.size());
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
        return null;
    }
}
