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

import com.t2tierp.tributacao.servidor.TributCofinsCodApuracaoDetalheAction;
import com.t2tierp.tributacao.servidor.TributConfiguraOfGtDetalheAction;
import com.t2tierp.tributacao.servidor.TributConfiguraOfGtGridAction;
import com.t2tierp.tributacao.servidor.TributGrupoTributarioDetalheAction;
import com.t2tierp.tributacao.servidor.TributGrupoTributarioGridAction;
import com.t2tierp.tributacao.servidor.TributIcmsCustomCabDetalheAction;
import com.t2tierp.tributacao.servidor.TributIcmsCustomCabGridAction;
import com.t2tierp.tributacao.servidor.TributIcmsCustomDetGridAction;
import com.t2tierp.tributacao.servidor.TributIcmsUfGridAction;
import com.t2tierp.tributacao.servidor.TributIpiDipiDetalheAction;
import com.t2tierp.tributacao.servidor.TributIssDetalheAction;
import com.t2tierp.tributacao.servidor.TributIssGridAction;
import com.t2tierp.tributacao.servidor.TributOperacaoFiscalDetalheAction;
import com.t2tierp.tributacao.servidor.TributOperacaoFiscalGridAction;
import com.t2tierp.tributacao.servidor.TributPisCodApuracaoDetalheAction;
import org.openswing.swing.server.ActionsCollection;
import org.openswing.swing.server.Action;

public class T2TiERPActionClasses extends ActionsCollection {

    public T2TiERPActionClasses() {
        Action a = null;

        //infra
        a = new T2TiERPButtonAuthorizationsAction(); put(a.getRequestName(), a);
        a = new T2TiERPFunctionAuthorizationsAction(); put(a.getRequestName(), a);
        a = new UserLoginAction(); put(a.getRequestName(), a);
        a = new T2TiERPContainerAction(); put(a.getRequestName(), a);

        /*actions do módulo */
        a = new TributOperacaoFiscalGridAction(); put(a.getRequestName(), a);
        a = new TributOperacaoFiscalDetalheAction(); put(a.getRequestName(), a);
        a = new TributGrupoTributarioGridAction(); put(a.getRequestName(), a);
        a = new TributGrupoTributarioDetalheAction(); put(a.getRequestName(), a);
        a = new TributConfiguraOfGtGridAction(); put(a.getRequestName(), a);
        a = new TributConfiguraOfGtDetalheAction(); put(a.getRequestName(), a);
        a = new TributIcmsUfGridAction(); put(a.getRequestName(), a);
        a = new TributPisCodApuracaoDetalheAction(); put(a.getRequestName(), a);
        a = new TributCofinsCodApuracaoDetalheAction(); put(a.getRequestName(), a);
        a = new TributIpiDipiDetalheAction(); put(a.getRequestName(), a);
        a = new TributIcmsCustomCabGridAction(); put(a.getRequestName(), a);
        a = new TributIcmsCustomCabDetalheAction(); put(a.getRequestName(), a);
        a = new TributIcmsCustomDetGridAction(); put(a.getRequestName(), a);
        a = new TributIssGridAction(); put(a.getRequestName(), a);
        a = new TributIssDetalheAction(); put(a.getRequestName(), a);
        
        /*validacoes e importacoes*/
        a = new ImportaDadosAction(); put(a.getRequestName(), a);
        a = new ValidaDadosAction(); put(a.getRequestName(), a);
        
    }
}
