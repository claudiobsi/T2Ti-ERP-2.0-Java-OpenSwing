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
package com.t2tierp.geradoretiqueta.servidor;

import com.t2tierp.padrao.servidor.HibernateUtil;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.hibernate.Session;
import org.hibernate.jdbc.Work;
import org.openswing.swing.message.receive.java.ErrorResponse;
import org.openswing.swing.message.receive.java.Response;
import org.openswing.swing.message.receive.java.VOResponse;
import org.openswing.swing.server.Action;
import org.openswing.swing.server.UserSessionParameters;

public class EtiquetaTabelasAction implements Action {

    public EtiquetaTabelasAction() {
    }

    public String getRequestName() {
        return "etiquetaTabelasAction";
    }

    public Response executeCommand(Object inputPar, UserSessionParameters userSessionPars, HttpServletRequest request, HttpServletResponse response, HttpSession userSession, ServletContext context) {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Map<String, List<String>> tabelas = new HashMap<>();
            
            session.doWork(new Work() {

                @Override
                public void execute(Connection conn) throws SQLException {
                    DatabaseMetaData databaseMetaData = conn.getMetaData();
                    ResultSet rsTabelas = databaseMetaData.getTables(null, null, "%", new String[]{"TABLE"});
                    ResultSet rsCampos;

                    List<String> campos;

                    String nomeTabela;
                    while (rsTabelas.next()) {
                        campos = new ArrayList<>();
                        nomeTabela = rsTabelas.getString("TABLE_NAME");

                        rsCampos = databaseMetaData.getColumns(null, null, nomeTabela, null);
                        while (rsCampos.next()) {
                            campos.add(rsCampos.getString("COLUMN_NAME"));
                        }

                        tabelas.put(nomeTabela, campos);
                    }

                }
            });

            return new VOResponse(tabelas);
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
