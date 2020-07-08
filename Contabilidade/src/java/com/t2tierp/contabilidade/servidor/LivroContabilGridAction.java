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

import com.t2tierp.financeiro.java.ViewFinMovimentoCaixaBancoID;
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

public class LivroContabilGridAction implements Action {

    public LivroContabilGridAction() {
    }

    public String getRequestName() {
        return "livroContabilGridAction";
    }

    public Response executeCommand(Object inputPar, UserSessionParameters userSessionPars, HttpServletRequest request, HttpServletResponse response, HttpSession userSession, ServletContext context) {
        Session session = null;
        Object[] parametros = (Object[]) inputPar;
        String periodo = (String) parametros[0];
        try {
            session = HibernateUtil.getSessionFactory().openSession();

            String sql = "select * from VIEW_FIN_MOVIMENTO_CAIXA_BANCO "
                    + "where to_char(DATA_PAGO_RECEBIDO, 'MM/YYYY') = :PERIODO "
                    //+ "where DATE_FORMAT(DATA_PAGO_RECEBIDO,'%m/%Y') = :PERIODO "
                    + "order by OPERACAO";

            SQLQuery query = session.createSQLQuery(sql);
            query.setString("PERIODO", periodo);
            query.addEntity(ViewFinMovimentoCaixaBancoID.class);
            List<ViewFinMovimentoCaixaBancoID> listaLivroCaixa = query.list();

            return new VOListResponse(listaLivroCaixa, false, listaLivroCaixa.size());
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
