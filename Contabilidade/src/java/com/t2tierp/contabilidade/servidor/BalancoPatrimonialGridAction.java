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
package com.t2tierp.contabilidade.servidor;

import com.t2tierp.contabilidade.java.ViewBalancoPatrimonialVO;
import com.t2tierp.padrao.servidor.HibernateUtil;
import java.util.List;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.openswing.swing.message.receive.java.ErrorResponse;
import org.openswing.swing.message.receive.java.Response;
import org.openswing.swing.message.receive.java.VOListResponse;
import org.openswing.swing.server.Action;
import org.openswing.swing.server.UserSessionParameters;

public class BalancoPatrimonialGridAction implements Action {

    public BalancoPatrimonialGridAction() {
    }

    public String getRequestName() {
        return "balancoPatrimonialGridAction";
    }

    public Response executeCommand(Object inputPar, UserSessionParameters userSessionPars, HttpServletRequest request, HttpServletResponse response, HttpSession userSession, ServletContext context) {
        Object[] pars = (Object[]) inputPar;
        String ano = (String) pars[0];
        Session session = null;
        try {
            String anoAnterior = String.valueOf(Integer.valueOf(ano) - 1);
            session = HibernateUtil.getSessionFactory().openSession();

            String sql = "SELECT id, classificacao, descricao, "
                    + "SUM((CASE WHEN ano=:anoAnterior THEN valor ELSE 0 END)) AS ANO_ANTERIOR,"
                    + "SUM((CASE WHEN ano=:anoAtual THEN valor ELSE 0 END)) AS ANO_ATUAL "
                    //+ "SUM(IF(ano=:anoAnterior, valor, NULL)) AS ANO_ANTERIOR, "
                    //+ "SUM(IF(ano=:anoAtual, valor, NULL)) AS ANO_ATUAL "
                    + "FROM view_balanco_patrimonial "
                    + "GROUP BY classificacao, descricao, id "
                    + "order by classificacao";

            SQLQuery query = session.createSQLQuery(sql);
            query.setString("anoAtual", ano);
            query.setString("anoAnterior", anoAnterior);
            query.addEntity(ViewBalancoPatrimonialVO.class);

            List<ViewBalancoPatrimonialVO> listaBalanco = query.list();

            return new VOListResponse(listaBalanco, false, listaBalanco.size());
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
