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

import com.t2tierp.contabilidade.servidor.AidfAimdfDetalheAction;
import com.t2tierp.contabilidade.servidor.AidfAimdfGridAction;
import com.t2tierp.contabilidade.servidor.BalancoPatrimonialGridAction;
import com.t2tierp.contabilidade.servidor.ContabilContaDetalheAction;
import com.t2tierp.contabilidade.servidor.ContabilContaGridAction;
import com.t2tierp.contabilidade.servidor.ContabilDreCabecalhoDetalheAction;
import com.t2tierp.contabilidade.servidor.ContabilDreCabecalhoGridAction;
import com.t2tierp.contabilidade.servidor.ContabilDreDetalheGridAction;
import com.t2tierp.contabilidade.servidor.ContabilEncerramentoExeCabDetalheAction;
import com.t2tierp.contabilidade.servidor.ContabilEncerramentoExeCabGridAction;
import com.t2tierp.contabilidade.servidor.ContabilEncerramentoExeDetGridAction;
import com.t2tierp.contabilidade.servidor.ContabilFechamentoDetalheAction;
import com.t2tierp.contabilidade.servidor.ContabilFechamentoGridAction;
import com.t2tierp.contabilidade.servidor.ContabilHistoricoDetalheAction;
import com.t2tierp.contabilidade.servidor.ContabilHistoricoGridAction;
import com.t2tierp.contabilidade.servidor.ContabilIndiceDetalheAction;
import com.t2tierp.contabilidade.servidor.ContabilIndiceGridAction;
import com.t2tierp.contabilidade.servidor.ContabilIndiceValorGridAction;
import com.t2tierp.contabilidade.servidor.ContabilLancamentoCabecalhoDetalheAction;
import com.t2tierp.contabilidade.servidor.ContabilLancamentoCabecalhoGridAction;
import com.t2tierp.contabilidade.servidor.ContabilLancamentoDetalheGridAction;
import com.t2tierp.contabilidade.servidor.ContabilLancamentoOrcadoDetalheAction;
import com.t2tierp.contabilidade.servidor.ContabilLancamentoOrcadoGridAction;
import com.t2tierp.contabilidade.servidor.ContabilLancamentoPadraoDetalheAction;
import com.t2tierp.contabilidade.servidor.ContabilLancamentoPadraoGridAction;
import com.t2tierp.contabilidade.servidor.ContabilLivroDetalheAction;
import com.t2tierp.contabilidade.servidor.ContabilLivroGridAction;
import com.t2tierp.contabilidade.servidor.ContabilLoteDetalheAction;
import com.t2tierp.contabilidade.servidor.ContabilLoteGridAction;
import com.t2tierp.contabilidade.servidor.ContabilParametrosDetalheAction;
import com.t2tierp.contabilidade.servidor.ContabilParametrosGridAction;
import com.t2tierp.contabilidade.servidor.ContabilTermoGridAction;
import com.t2tierp.contabilidade.servidor.DfcGridAction;
import com.t2tierp.contabilidade.servidor.FapDetalheAction;
import com.t2tierp.contabilidade.servidor.FapGridAction;
import com.t2tierp.contabilidade.servidor.LivroContabilGridAction;
import com.t2tierp.contabilidade.servidor.PlanoContaDetalheAction;
import com.t2tierp.contabilidade.servidor.PlanoContaGridAction;
import com.t2tierp.contabilidade.servidor.PlanoContaRefSpedDetalheAction;
import com.t2tierp.contabilidade.servidor.PlanoContaRefSpedGridAction;
import com.t2tierp.contabilidade.servidor.RegistroCartorioDetalheAction;
import com.t2tierp.contabilidade.servidor.RegistroCartorioGridAction;
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
        a = new RegistroCartorioGridAction(); put(a.getRequestName(), a);
        a = new RegistroCartorioDetalheAction(); put(a.getRequestName(), a);
        a = new ContabilParametrosGridAction(); put(a.getRequestName(), a);
        a = new ContabilParametrosDetalheAction(); put(a.getRequestName(), a);
        a = new ContabilIndiceGridAction(); put(a.getRequestName(), a);
        a = new ContabilIndiceDetalheAction(); put(a.getRequestName(), a);
        a = new ContabilIndiceValorGridAction(); put(a.getRequestName(), a);
        a = new ContabilHistoricoGridAction(); put(a.getRequestName(), a);
        a = new ContabilHistoricoDetalheAction(); put(a.getRequestName(), a);
        a = new AidfAimdfGridAction(); put(a.getRequestName(), a);
        a = new AidfAimdfDetalheAction(); put(a.getRequestName(), a);
        a = new FapGridAction(); put(a.getRequestName(), a);
        a = new FapDetalheAction(); put(a.getRequestName(), a);
        a = new PlanoContaGridAction(); put(a.getRequestName(), a);
        a = new PlanoContaDetalheAction(); put(a.getRequestName(), a);
        a = new PlanoContaRefSpedGridAction(); put(a.getRequestName(), a);
        a = new PlanoContaRefSpedDetalheAction(); put(a.getRequestName(), a);
        a = new ContabilContaGridAction(); put(a.getRequestName(), a);
        a = new ContabilContaDetalheAction(); put(a.getRequestName(), a);
        a = new ContabilFechamentoGridAction(); put(a.getRequestName(), a);
        a = new ContabilFechamentoDetalheAction(); put(a.getRequestName(), a);
        a = new ContabilLancamentoPadraoGridAction(); put(a.getRequestName(), a);
        a = new ContabilLancamentoPadraoDetalheAction(); put(a.getRequestName(), a);
        a = new ContabilLoteGridAction(); put(a.getRequestName(), a);
        a = new ContabilLoteDetalheAction(); put(a.getRequestName(), a);
        a = new ContabilLancamentoOrcadoGridAction(); put(a.getRequestName(), a);
        a = new ContabilLancamentoOrcadoDetalheAction(); put(a.getRequestName(), a);
        a = new ContabilLancamentoCabecalhoGridAction(); put(a.getRequestName(), a);
        a = new ContabilLancamentoCabecalhoDetalheAction(); put(a.getRequestName(), a);
        a = new ContabilLancamentoDetalheGridAction(); put(a.getRequestName(), a);
        a = new ContabilDreCabecalhoGridAction(); put(a.getRequestName(), a);
        a = new ContabilDreCabecalhoDetalheAction(); put(a.getRequestName(), a);
        a = new ContabilDreDetalheGridAction(); put(a.getRequestName(), a);
        a = new ContabilEncerramentoExeCabGridAction(); put(a.getRequestName(), a);
        a = new ContabilEncerramentoExeCabDetalheAction(); put(a.getRequestName(), a);
        a = new ContabilEncerramentoExeDetGridAction(); put(a.getRequestName(), a);
        a = new ContabilLivroGridAction(); put(a.getRequestName(), a);
        a = new ContabilLivroDetalheAction(); put(a.getRequestName(), a);
        a = new ContabilTermoGridAction(); put(a.getRequestName(), a);
        a = new DfcGridAction(); put(a.getRequestName(), a);
        a = new BalancoPatrimonialGridAction(); put(a.getRequestName(), a);
        a = new LivroContabilGridAction(); put(a.getRequestName(), a);
        
        /*validacoes e importacoes*/
        a = new ImportaDadosAction(); put(a.getRequestName(), a);
        a = new ValidaDadosAction(); put(a.getRequestName(), a);
        
    }
}
