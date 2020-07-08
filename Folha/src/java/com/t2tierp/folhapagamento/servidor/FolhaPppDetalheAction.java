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
package com.t2tierp.folhapagamento.servidor;

import com.t2tierp.folhapagamento.java.FolhaPppAtividadeVO;
import com.t2tierp.folhapagamento.java.FolhaPppCatVO;
import com.t2tierp.folhapagamento.java.FolhaPppExameMedicoVO;
import com.t2tierp.folhapagamento.java.FolhaPppFatorRiscoVO;
import com.t2tierp.padrao.java.Constantes;
import com.t2tierp.padrao.servidor.HibernateUtil;
import com.t2tierp.folhapagamento.java.FolhaPppVO;
import java.util.List;
import java.util.Vector;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.openswing.swing.message.receive.java.ErrorResponse;
import org.openswing.swing.message.receive.java.Response;
import org.openswing.swing.message.receive.java.VOResponse;
import org.openswing.swing.server.Action;
import org.openswing.swing.server.UserSessionParameters;

public class FolhaPppDetalheAction implements Action {

    public FolhaPppDetalheAction() {
    }

    public String getRequestName() {
        return "folhaPppDetalheAction";
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
        Session session = null;
        Object[] pars = (Object[]) inputPar;
        String pk = (String) pars[1];

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Criteria criteria = session.createCriteria(FolhaPppVO.class);
            criteria.add(Restrictions.eq("id", Integer.valueOf(pk)));

            FolhaPppVO folhaPpp = (FolhaPppVO) criteria.uniqueResult();

            return new VOResponse(folhaPpp);
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
            FolhaPppVO folhaPpp = (FolhaPppVO) pars[1];
            List<FolhaPppCatVO> cat = (Vector) pars[2];
            List<FolhaPppAtividadeVO> atividade = (Vector) pars[3];
            List<FolhaPppFatorRiscoVO> fatorRisco = (Vector) pars[4];
            List<FolhaPppExameMedicoVO> exameMedico = (Vector) pars[5];

            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();

            session.save(folhaPpp);

            //salva os dados do CAT
            for (int i = 0; i < cat.size(); i++) {
                cat.get(i).setFolhaPpp(folhaPpp);
                session.save(cat.get(i));
            }

            //salva os dados da Atividade
            for (int i = 0; i < atividade.size(); i++) {
                atividade.get(i).setFolhaPpp(folhaPpp);
                session.save(atividade.get(i));
            }

            //salva os dados do Fator de Risco
            for (int i = 0; i < fatorRisco.size(); i++) {
                fatorRisco.get(i).setFolhaPpp(folhaPpp);
                session.save(fatorRisco.get(i));
            }

            //salva os dados do Exame Medico
            for (int i = 0; i < exameMedico.size(); i++) {
                exameMedico.get(i).setFolhaPpp(folhaPpp);
                session.save(exameMedico.get(i));
            }

            session.getTransaction().commit();

            return new VOResponse(folhaPpp);
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
        Session session = null;
        try {
            Object[] pars = (Object[]) inputPar;
            FolhaPppVO folhaPpp = (FolhaPppVO) pars[2];
            List<FolhaPppCatVO> cat = (Vector) pars[3];
            List<FolhaPppAtividadeVO> atividade = (Vector) pars[4];
            List<FolhaPppFatorRiscoVO> fatorRisco = (Vector) pars[5];
            List<FolhaPppExameMedicoVO> exameMedico = (Vector) pars[6];


            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();

            session.update(folhaPpp);

            //exclui os dados do CAT
            String sql = "delete from FOLHA_PPP_CAT where ID_FOLHA_PPP = :id";
            Query query = session.createSQLQuery(sql);
            query.setInteger("id", folhaPpp.getId());
            query.executeUpdate();

            //exclui os dados da Atividade
            sql = "delete from FOLHA_PPP_ATIVIDADE where ID_FOLHA_PPP = :id";
            query = session.createSQLQuery(sql);
            query.setInteger("id", folhaPpp.getId());
            query.executeUpdate();

            //exclui os dados do Fator de Risco
            sql = "delete from FOLHA_PPP_FATOR_RISCO where ID_FOLHA_PPP = :id";
            query = session.createSQLQuery(sql);
            query.setInteger("id", folhaPpp.getId());
            query.executeUpdate();

            //exclui os dados do Exame Medico
            sql = "delete from FOLHA_PPP_EXAME_MEDICO where ID_FOLHA_PPP = :id";
            query = session.createSQLQuery(sql);
            query.setInteger("id", folhaPpp.getId());
            query.executeUpdate();

            //salva os dados do CAT
            for (int i = 0; i < cat.size(); i++) {
                cat.get(i).setFolhaPpp(folhaPpp);
                session.save(cat.get(i));
            }

            //salva os dados da Atividade
            for (int i = 0; i < atividade.size(); i++) {
                atividade.get(i).setFolhaPpp(folhaPpp);
                session.save(atividade.get(i));
            }

            //salva os dados do Fator de Risco
            for (int i = 0; i < fatorRisco.size(); i++) {
                fatorRisco.get(i).setFolhaPpp(folhaPpp);
                session.save(fatorRisco.get(i));
            }

            //salva os dados do Exame Medico
            for (int i = 0; i < exameMedico.size(); i++) {
                exameMedico.get(i).setFolhaPpp(folhaPpp);
                session.save(exameMedico.get(i));
            }

            session.getTransaction().commit();

            return new VOResponse(folhaPpp);
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

    public Response delete(Object inputPar, UserSessionParameters userSessionPars, HttpServletRequest request, HttpServletResponse response, HttpSession userSession, ServletContext context) {
        return null;
    }

}
