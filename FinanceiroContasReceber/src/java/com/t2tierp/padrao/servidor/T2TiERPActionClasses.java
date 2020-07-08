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

import com.t2tierp.financeiro.servidor.FinCobrancaDetalheAction;
import com.t2tierp.financeiro.servidor.FinCobrancaGridAction;
import com.t2tierp.financeiro.servidor.FinCobrancaParcelaReceberGridAction;
import com.t2tierp.financeiro.servidor.FinConfiguracaoBoletoGridAction;
import com.t2tierp.financeiro.servidor.FinLancamentoReceberDetalheAction;
import com.t2tierp.financeiro.servidor.FinLancamentoReceberGridAction;
import com.t2tierp.financeiro.servidor.FinLctoReceberNtFinanceiraGridAction;
import com.t2tierp.financeiro.servidor.FinParcelaReceberGridAction;
import com.t2tierp.financeiro.servidor.FinParcelaRecebimentoDetalheAction;
import com.t2tierp.financeiro.servidor.FinParcelaRecebimentoEfetuadoGridAction;
import com.t2tierp.financeiro.servidor.FinParcelaRecebimentoGridAction;
import com.t2tierp.financeiro.servidor.FinTrataArquivoRetornoGridAction;
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
        a = new FinConfiguracaoBoletoGridAction(); put(a.getRequestName(), a);
        a = new FinParcelaReceberGridAction(); put(a.getRequestName(), a);
        a = new FinLancamentoReceberGridAction(); put(a.getRequestName(), a);
        a = new FinLancamentoReceberDetalheAction(); put(a.getRequestName(), a);
        a = new FinLctoReceberNtFinanceiraGridAction(); put(a.getRequestName(), a);
        a = new FinParcelaRecebimentoGridAction(); put(a.getRequestName(), a);
        a = new FinParcelaRecebimentoDetalheAction(); put(a.getRequestName(), a);
        a = new FinParcelaRecebimentoEfetuadoGridAction(); put(a.getRequestName(), a);
        a = new FinTrataArquivoRetornoGridAction(); put(a.getRequestName(), a);
        a = new FinCobrancaGridAction(); put(a.getRequestName(), a);
        a = new FinCobrancaDetalheAction(); put(a.getRequestName(), a);
        a = new FinCobrancaParcelaReceberGridAction(); put(a.getRequestName(), a);

        /*validacoes e importacoes*/
        a = new ImportaDadosAction(); put(a.getRequestName(), a);
        a = new ValidaDadosAction(); put(a.getRequestName(), a);
        a = new ImportaChequeAction(); put(a.getRequestName(), a);
        a = new ValidaChequeAction(); put(a.getRequestName(), a);
        
    }
}
