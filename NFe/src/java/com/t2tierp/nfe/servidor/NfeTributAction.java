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
package com.t2tierp.nfe.servidor;

import com.t2tierp.padrao.servidor.HibernateUtil;
import com.t2tierp.tributacao.java.ViewTributacaoCofinsVO;
import com.t2tierp.tributacao.java.ViewTributacaoIcmsCustomVO;
import com.t2tierp.tributacao.java.ViewTributacaoIcmsVO;
import com.t2tierp.tributacao.java.ViewTributacaoIpiVO;
import com.t2tierp.tributacao.java.ViewTributacaoPisVO;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.openswing.swing.message.receive.java.ErrorResponse;
import org.openswing.swing.message.receive.java.Response;
import org.openswing.swing.message.receive.java.VOResponse;
import org.openswing.swing.server.Action;
import org.openswing.swing.server.UserSessionParameters;

public class NfeTributAction implements Action {

    public NfeTributAction() {
    }

    public String getRequestName() {
        return "nfeTributAction";
    }

    public Response executeCommand(Object inputPar, UserSessionParameters userSessionPars, HttpServletRequest request, HttpServletResponse response, HttpSession userSession, ServletContext context) {
        Object[] pars = (Object[]) inputPar;
        Integer acao = (Integer) pars[0];

        switch (acao) {
            case 1: {
                return loadIcms(inputPar);
            }
            case 2: {
                return loadIpi(inputPar);
            }
            case 3: {
                return loadPis(inputPar);
            }
            case 4: {
                return loadCofins(inputPar);
            }
            case 5: {
                return loadIcmsCustom(inputPar);
            }
        }
        return null;
    }

    private Response loadIcms(Object inputPar) {
        Session session = null;
        Object[] pars = (Object[]) inputPar;
        Integer idOperacaoFiscal = (Integer) pars[1];
        Integer idGrupoTributario = (Integer) pars[2];
        String ufDestino = (String) pars[3];

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Criteria criteria = session.createCriteria(ViewTributacaoIcmsVO.class);
            criteria.add(Restrictions.eq("idTributOperacaoFiscal", idOperacaoFiscal));
            criteria.add(Restrictions.eq("idTributGrupoTributario", idGrupoTributario));
            criteria.add(Restrictions.eq("ufDestino", ufDestino));

            ViewTributacaoIcmsVO icms = (ViewTributacaoIcmsVO) criteria.uniqueResult();

            return new VOResponse(icms);
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

    private Response loadIpi(Object inputPar) {
        Session session = null;
        Object[] pars = (Object[]) inputPar;
        Integer idOperacaoFiscal = (Integer) pars[1];
        Integer idGrupoTributario = (Integer) pars[2];

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Criteria criteria = session.createCriteria(ViewTributacaoIpiVO.class);
            criteria.add(Restrictions.eq("idTributOperacaoFiscal", idOperacaoFiscal));
            criteria.add(Restrictions.eq("idTributGrupoTributario", idGrupoTributario));

            ViewTributacaoIpiVO ipi = (ViewTributacaoIpiVO) criteria.uniqueResult();

            return new VOResponse(ipi);
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

    private Response loadPis(Object inputPar) {
        Session session = null;
        Object[] pars = (Object[]) inputPar;
        Integer idOperacaoFiscal = (Integer) pars[1];
        Integer idGrupoTributario = (Integer) pars[2];

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Criteria criteria = session.createCriteria(ViewTributacaoPisVO.class);
            criteria.add(Restrictions.eq("idTributOperacaoFiscal", idOperacaoFiscal));
            criteria.add(Restrictions.eq("idTributGrupoTributario", idGrupoTributario));

            ViewTributacaoPisVO pis = (ViewTributacaoPisVO) criteria.uniqueResult();

            return new VOResponse(pis);
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

    private Response loadCofins(Object inputPar) {
        Session session = null;
        Object[] pars = (Object[]) inputPar;
        Integer idOperacaoFiscal = (Integer) pars[1];
        Integer idGrupoTributario = (Integer) pars[2];

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Criteria criteria = session.createCriteria(ViewTributacaoCofinsVO.class);
            criteria.add(Restrictions.eq("idTributOperacaoFiscal", idOperacaoFiscal));
            criteria.add(Restrictions.eq("idTributGrupoTributario", idGrupoTributario));

            ViewTributacaoCofinsVO cofins = (ViewTributacaoCofinsVO) criteria.uniqueResult();

            return new VOResponse(cofins);
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

    private Response loadIcmsCustom(Object inputPar) {
        Session session = null;
        Object[] pars = (Object[]) inputPar;
        Integer id = (Integer) pars[1];
        String ufDestino = (String) pars[2];

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Criteria criteria = session.createCriteria(ViewTributacaoIcmsCustomVO.class);
            criteria.add(Restrictions.eq("id", id));
            criteria.add(Restrictions.eq("ufDestino", ufDestino));

            ViewTributacaoIcmsCustomVO icms = (ViewTributacaoIcmsCustomVO) criteria.uniqueResult();

            return new VOResponse(icms);
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
