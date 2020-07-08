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

import com.t2tierp.cadastros.java.ColaboradorVO;
import com.t2tierp.cadastros.java.SefipCodigoRecolhimentoVO;
import com.t2tierp.padrao.servidor.HibernateUtil;
import java.util.Calendar;
import java.util.List;
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

public class InformativosGuiasAction implements Action {

    public InformativosGuiasAction() {
    }

    public String getRequestName() {
        return "informativosGuiasAction";
    }

    public Response executeCommand(Object inputPar, UserSessionParameters userSessionPars, HttpServletRequest request, HttpServletResponse response, HttpSession userSession, ServletContext context) {
        Object[] pars = (Object[]) inputPar;
        String acao = (String) pars[0];

        if (acao.equals("codigoRecolhimentoGfip")) {
            return carregaCodigoRecolhimentoGfip();
        } else if (acao.equals("colaboradoresCaged")) {
            return carregaDadosCaged((Integer) pars[1], (Integer) pars[2]);
        }

        return null;
    }

    private Response carregaCodigoRecolhimentoGfip() {
        Session session = null;

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Criteria criteria = session.createCriteria(SefipCodigoRecolhimentoVO.class);

            List<SefipCodigoRecolhimentoVO> codigosRecolhimento = criteria.list();

            return new VOListResponse(codigosRecolhimento, false, codigosRecolhimento.size());
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

    private Response carregaDadosCaged(int mes, int ano) {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();

            mes -= 1;

            Calendar dataInicial = Calendar.getInstance();
            dataInicial.set(Calendar.DAY_OF_MONTH, 1);
            dataInicial.set(Calendar.MONTH, mes);
            dataInicial.set(Calendar.YEAR, ano);

            Calendar dataFinal = Calendar.getInstance();
            dataFinal.set(Calendar.DAY_OF_MONTH, dataInicial.getActualMaximum(Calendar.DAY_OF_MONTH));
            dataFinal.set(Calendar.MONTH, mes);
            dataFinal.set(Calendar.YEAR, ano);

            Criteria criteria = session.createCriteria(ColaboradorVO.class);
            criteria.add(Restrictions.or(
                    Restrictions.between("dataAdmissao", dataInicial.getTime(), dataFinal.getTime()),
                    Restrictions.between("dataDemissao", dataInicial.getTime(), dataFinal.getTime())));

            List<ColaboradorVO> colaboradores = criteria.list();

            return new VOListResponse(colaboradores, false, colaboradores.size());
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