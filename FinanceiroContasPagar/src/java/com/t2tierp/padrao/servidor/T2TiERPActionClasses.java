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

import com.t2tierp.financeiro.servidor.FinLancamentoPagarDetalheAction;
import com.t2tierp.financeiro.servidor.FinLancamentoPagarGridAction;
import com.t2tierp.financeiro.servidor.FinLctoPagarNtFinanceiraGridAction;
import com.t2tierp.financeiro.servidor.FinPagamentoFixoDetalheAction;
import com.t2tierp.financeiro.servidor.FinPagamentoFixoGridAction;
import com.t2tierp.financeiro.servidor.FinParcelaPagamentoDetalheAction;
import com.t2tierp.financeiro.servidor.FinParcelaPagamentoEfetuadoGridAction;
import com.t2tierp.financeiro.servidor.FinParcelaPagamentoGridAction;
import com.t2tierp.financeiro.servidor.FinParcelaPagarGridAction;
import com.t2tierp.financeiro.servidor.ImportaChequeAction;
import com.t2tierp.financeiro.servidor.ValidaChequeAction;
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
        a = new FinLancamentoPagarGridAction(); put(a.getRequestName(), a);
        a = new FinLancamentoPagarDetalheAction(); put(a.getRequestName(), a);
        a = new FinParcelaPagarGridAction(); put(a.getRequestName(), a);
        a = new FinLctoPagarNtFinanceiraGridAction(); put(a.getRequestName(), a);
        a = new FinParcelaPagamentoGridAction(); put(a.getRequestName(), a);
        a = new FinParcelaPagamentoEfetuadoGridAction(); put(a.getRequestName(), a);
        a = new FinParcelaPagamentoDetalheAction(); put(a.getRequestName(), a);
        a = new FinPagamentoFixoGridAction(); put(a.getRequestName(), a);
        a = new FinPagamentoFixoDetalheAction(); put(a.getRequestName(), a);

        /*validacoes e importacoes*/
        a = new ImportaDadosAction(); put(a.getRequestName(), a);
        a = new ValidaDadosAction(); put(a.getRequestName(), a);
        a = new ImportaChequeAction(); put(a.getRequestName(), a);
        a = new ValidaChequeAction(); put(a.getRequestName(), a);
        
    }
}
