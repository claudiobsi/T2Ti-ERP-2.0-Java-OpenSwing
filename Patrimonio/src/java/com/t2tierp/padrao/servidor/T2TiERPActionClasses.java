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

import com.t2tierp.patrimonio.servidor.PatrimApoliceSeguroDetalheAction;
import com.t2tierp.patrimonio.servidor.PatrimApoliceSeguroGridAction;
import com.t2tierp.patrimonio.servidor.PatrimBemDetalheAction;
import com.t2tierp.patrimonio.servidor.PatrimBemGridAction;
import com.t2tierp.patrimonio.servidor.PatrimDepreciacaoBemGridAction;
import com.t2tierp.patrimonio.servidor.PatrimDocumentoBemGridAction;
import com.t2tierp.patrimonio.servidor.PatrimEstadoConservacaoDetalheAction;
import com.t2tierp.patrimonio.servidor.PatrimEstadoConservacaoGridAction;
import com.t2tierp.patrimonio.servidor.PatrimGrupoBemDetalheAction;
import com.t2tierp.patrimonio.servidor.PatrimGrupoBemGridAction;
import com.t2tierp.patrimonio.servidor.PatrimIndiceAtualizacaoDetalheAction;
import com.t2tierp.patrimonio.servidor.PatrimIndiceAtualizacaoGridAction;
import com.t2tierp.patrimonio.servidor.PatrimMovimentacaoBemGridAction;
import com.t2tierp.patrimonio.servidor.PatrimTaxaDepreciacaoDetalheAction;
import com.t2tierp.patrimonio.servidor.PatrimTaxaDepreciacaoGridAction;
import com.t2tierp.patrimonio.servidor.PatrimTipoAquisicaoBemDetalheAction;
import com.t2tierp.patrimonio.servidor.PatrimTipoAquisicaoBemGridAction;
import com.t2tierp.patrimonio.servidor.PatrimTipoMovimentacaoDetalheAction;
import com.t2tierp.patrimonio.servidor.PatrimTipoMovimentacaoGridAction;
import com.t2tierp.patrimonio.servidor.SeguradoraDetalheAction;
import com.t2tierp.patrimonio.servidor.SeguradoraGridAction;
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
        a = new PatrimTaxaDepreciacaoGridAction(); put(a.getRequestName(), a);
        a = new PatrimTaxaDepreciacaoDetalheAction(); put(a.getRequestName(), a);
        a = new PatrimIndiceAtualizacaoGridAction(); put(a.getRequestName(), a);
        a = new PatrimIndiceAtualizacaoDetalheAction(); put(a.getRequestName(), a);
        a = new PatrimTipoAquisicaoBemGridAction(); put(a.getRequestName(), a);
        a = new PatrimTipoAquisicaoBemDetalheAction(); put(a.getRequestName(), a);
        a = new PatrimTipoMovimentacaoGridAction(); put(a.getRequestName(), a);
        a = new PatrimTipoMovimentacaoDetalheAction(); put(a.getRequestName(), a);
        a = new PatrimEstadoConservacaoGridAction(); put(a.getRequestName(), a);
        a = new PatrimEstadoConservacaoDetalheAction(); put(a.getRequestName(), a);
        a = new PatrimGrupoBemGridAction(); put(a.getRequestName(), a);
        a = new PatrimGrupoBemDetalheAction(); put(a.getRequestName(), a);
        a = new PatrimBemGridAction(); put(a.getRequestName(), a);
        a = new PatrimBemDetalheAction(); put(a.getRequestName(), a);
        a = new PatrimDepreciacaoBemGridAction(); put(a.getRequestName(), a);
        a = new PatrimDocumentoBemGridAction(); put(a.getRequestName(), a);
        a = new PatrimMovimentacaoBemGridAction(); put(a.getRequestName(), a);
        a = new SeguradoraGridAction(); put(a.getRequestName(), a);
        a = new SeguradoraDetalheAction(); put(a.getRequestName(), a);
        a = new PatrimApoliceSeguroGridAction(); put(a.getRequestName(), a);
        a = new PatrimApoliceSeguroDetalheAction(); put(a.getRequestName(), a);

        /*validacoes e importacoes*/
        a = new ImportaDadosAction(); put(a.getRequestName(), a);
        a = new ValidaDadosAction(); put(a.getRequestName(), a);
        
    }
}
