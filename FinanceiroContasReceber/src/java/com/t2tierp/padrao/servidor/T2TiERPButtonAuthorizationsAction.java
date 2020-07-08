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
package com.t2tierp.padrao.servidor;

import com.t2tierp.cadastros.java.PapelFuncaoVO;
import com.t2tierp.cadastros.java.UsuarioVO;
import java.util.List;
import org.openswing.swing.server.*;
import javax.servlet.*;
import javax.servlet.http.*;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.openswing.swing.message.receive.java.*;
import org.openswing.swing.permissions.java.ButtonsAuthorizations;

public class T2TiERPButtonAuthorizationsAction implements Action {

    public T2TiERPButtonAuthorizationsAction() {
    }

    /**
     * @return request name
     */
    public final String getRequestName() {
        return "getButtonAuthorizations";
    }

    /**
     * Business logic to execute.
     */
    public final Response executeCommand(Object inputPar, UserSessionParameters userSessionPars, HttpServletRequest request, HttpServletResponse response, HttpSession userSession, ServletContext context) {
        Session session = null;
        try {
            UsuarioVO usuario = (UsuarioVO) inputPar;
            session = HibernateUtil.getSessionFactory().openSession();
            Criteria criteria = session.createCriteria(PapelFuncaoVO.class);
            criteria.add(Restrictions.eq("papel", usuario.getPapel()));

            List<PapelFuncaoVO> listaPapelFuncao = criteria.list();
            ButtonsAuthorizations ba = new ButtonsAuthorizations();

            if (!usuario.getPapel().getAcessoCompleto().equals("S")) {
                for (int i = 0; i < listaPapelFuncao.size(); i++) {
                    ba.addButtonAuthorization(listaPapelFuncao.get(i).getFuncao().getNome(),
                            listaPapelFuncao.get(i).getPodeInserir().equals("S"),
                            listaPapelFuncao.get(i).getPodeAlterar().equals("S"),
                            listaPapelFuncao.get(i).getPodeExcluir().equals("S"));
                }
            }

            return new VOResponse(ba);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                session.close();
            } catch (Exception ex1) {
            }
        }
    }
}
