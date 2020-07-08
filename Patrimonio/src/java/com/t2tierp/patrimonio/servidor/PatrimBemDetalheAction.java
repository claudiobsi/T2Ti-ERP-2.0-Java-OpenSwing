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
package com.t2tierp.patrimonio.servidor;

import com.t2tierp.contabilidade.java.CentroResultadoVO;
import com.t2tierp.padrao.java.Constantes;
import com.t2tierp.padrao.servidor.HibernateUtil;
import com.t2tierp.patrimonio.java.PatrimBemVO;
import com.t2tierp.patrimonio.java.PatrimDepreciacaoBemVO;
import com.t2tierp.patrimonio.java.PatrimDocumentoBemVO;
import com.t2tierp.patrimonio.java.PatrimMovimentacaoBemVO;
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

public class PatrimBemDetalheAction implements Action {

    public PatrimBemDetalheAction() {
    }

    public String getRequestName() {
        return "patrimBemDetalheAction";
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
            Criteria criteria = session.createCriteria(PatrimBemVO.class);
            criteria.add(Restrictions.eq("id", Integer.valueOf(pk)));

            PatrimBemVO patrimBem = (PatrimBemVO) criteria.uniqueResult();

            return new VOResponse(patrimBem);
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
            PatrimBemVO patrimBem = (PatrimBemVO) pars[1];
            List<PatrimDocumentoBemVO> documentacao = (Vector) pars[2];
            List<PatrimDepreciacaoBemVO> depreciacao = (Vector) pars[3];
            List<PatrimMovimentacaoBemVO> movimentacao = (Vector) pars[4];


            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();

            //Exercicio: incluir na janela PatrimBemDetalhe campo para que o usuário selecione o centro de resultado
            CentroResultadoVO centroResultado = new CentroResultadoVO();
            centroResultado.setId(1);
            patrimBem.setCentroResultado(centroResultado);
            //*************************
            
            session.save(patrimBem);

            for (int i = 0; i < documentacao.size(); i++){
                documentacao.get(i).setPatrimBem(patrimBem);
                session.save(documentacao.get(i));
            }

            for (int i = 0; i < depreciacao.size(); i++){
                depreciacao.get(i).setPatrimBem(patrimBem);
                session.save(depreciacao.get(i));
            }

            for (int i = 0; i < movimentacao.size(); i++){
                movimentacao.get(i).setPatrimBem(patrimBem);
                session.save(movimentacao.get(i));
            }

            session.getTransaction().commit();

            return new VOResponse(patrimBem);
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
            PatrimBemVO patrimBem = (PatrimBemVO) pars[2];
            List<PatrimDocumentoBemVO> documentacao = (Vector) pars[3];
            List<PatrimDepreciacaoBemVO> depreciacao = (Vector) pars[4];
            List<PatrimMovimentacaoBemVO> movimentacao = (Vector) pars[5];

            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();

            session.update(patrimBem);

            String sql = "delete from PATRIM_DOCUMENTO_BEM where ID_PATRIM_BEM = :id";
            Query query = session.createSQLQuery(sql);
            query.setInteger("id", patrimBem.getId());
            query.executeUpdate();
            
            sql = "delete from PATRIM_DEPRECIACAO_BEM where ID_PATRIM_BEM = :id";
            query = session.createSQLQuery(sql);
            query.setInteger("id", patrimBem.getId());
            query.executeUpdate();

            sql = "delete from PATRIM_MOVIMENTACAO_BEM where ID_PATRIM_BEM = :id";
            query = session.createSQLQuery(sql);
            query.setInteger("id", patrimBem.getId());
            query.executeUpdate();

            for (int i = 0; i < documentacao.size(); i++){
                documentacao.get(i).setPatrimBem(patrimBem);
                session.save(documentacao.get(i));
            }

            for (int i = 0; i < depreciacao.size(); i++){
                depreciacao.get(i).setPatrimBem(patrimBem);
                session.save(depreciacao.get(i));
            }

            for (int i = 0; i < movimentacao.size(); i++){
                movimentacao.get(i).setPatrimBem(patrimBem);
                session.save(movimentacao.get(i));
            }

            session.getTransaction().commit();

            return new VOResponse(patrimBem);
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
}
