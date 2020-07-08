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

import com.t2tierp.compras.servidor.CompraConfirmaCotacaoDetalheAction;
import com.t2tierp.compras.servidor.CompraConfirmaCotacaoDetalheGridAction;
import com.t2tierp.compras.servidor.CompraConfirmaCotacaoGridAction;
import com.t2tierp.compras.servidor.CompraCotacaoDetalheAction;
import com.t2tierp.compras.servidor.CompraCotacaoDetalheGridAction;
import com.t2tierp.compras.servidor.CompraCotacaoGridAction;
import com.t2tierp.compras.servidor.CompraFornecedorCotacaoGridAction;
import com.t2tierp.compras.servidor.CompraItensNaoCotadosGridAction;
import com.t2tierp.compras.servidor.CompraMapaComparativoDetalheAction;
import com.t2tierp.compras.servidor.CompraMapaComparativoDetalheGridAction;
import com.t2tierp.compras.servidor.CompraMapaComparativoGridAction;
import com.t2tierp.compras.servidor.CompraPedidoDetalheAction;
import com.t2tierp.compras.servidor.CompraPedidoDetalheGridAction;
import com.t2tierp.compras.servidor.CompraPedidoGridAction;
import com.t2tierp.compras.servidor.CompraRequisicaoDetalheAction;
import com.t2tierp.compras.servidor.CompraRequisicaoDetalheGridAction;
import com.t2tierp.compras.servidor.CompraRequisicaoGridAction;
import com.t2tierp.compras.servidor.CompraSugeridaGridAction;
import com.t2tierp.compras.servidor.CompraTipoPedidoDetalheAction;
import com.t2tierp.compras.servidor.CompraTipoPedidoGridAction;
import com.t2tierp.compras.servidor.CompraTipoRequisicaoDetalheAction;
import com.t2tierp.compras.servidor.CompraTipoRequisicaoGridAction;
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
        a = new CompraTipoRequisicaoGridAction(); put(a.getRequestName(), a);
        a = new CompraTipoRequisicaoDetalheAction(); put(a.getRequestName(), a);
        a = new CompraTipoPedidoGridAction(); put(a.getRequestName(), a);
        a = new CompraTipoPedidoDetalheAction(); put(a.getRequestName(), a);
        a = new CompraRequisicaoGridAction(); put(a.getRequestName(), a);
        a = new CompraRequisicaoDetalheAction(); put(a.getRequestName(), a);
        a = new CompraRequisicaoDetalheGridAction(); put(a.getRequestName(), a);
        a = new CompraCotacaoGridAction(); put(a.getRequestName(), a);
        a = new CompraCotacaoDetalheAction(); put(a.getRequestName(), a);
        a = new CompraCotacaoDetalheGridAction(); put(a.getRequestName(), a);
        a = new CompraFornecedorCotacaoGridAction(); put(a.getRequestName(), a);
        a = new CompraItensNaoCotadosGridAction(); put(a.getRequestName(), a);
        a = new CompraConfirmaCotacaoGridAction(); put(a.getRequestName(), a);
        a = new CompraConfirmaCotacaoDetalheAction(); put(a.getRequestName(), a);
        a = new CompraConfirmaCotacaoDetalheGridAction(); put(a.getRequestName(), a);
        a = new CompraMapaComparativoGridAction(); put(a.getRequestName(), a);
        a = new CompraMapaComparativoDetalheAction(); put(a.getRequestName(), a);
        a = new CompraMapaComparativoDetalheGridAction(); put(a.getRequestName(), a);
        a = new CompraPedidoGridAction(); put(a.getRequestName(), a);
        a = new CompraPedidoDetalheAction(); put(a.getRequestName(), a);
        a = new CompraPedidoDetalheGridAction(); put(a.getRequestName(), a);
        a = new CompraSugeridaGridAction(); put(a.getRequestName(), a);

        /*validacoes e importacoes*/
        a = new ImportaDadosAction(); put(a.getRequestName(), a);
        a = new ValidaDadosAction(); put(a.getRequestName(), a);
    }
}
