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

import com.t2tierp.cadastros.servidor.FichaTecnicaGridAction;
import com.t2tierp.cadastros.servidor.ProdutoDetalheAction;
import com.t2tierp.cadastros.servidor.ProdutoGridAction;
import com.t2tierp.cadastros.servidor.ProdutoGrupoDetalheAction;
import com.t2tierp.cadastros.servidor.ProdutoGrupoGridAction;
import com.t2tierp.cadastros.servidor.ProdutoSubGrupoDetalheAction;
import com.t2tierp.cadastros.servidor.ProdutoSubGrupoGridAction;
import com.t2tierp.cadastros.servidor.UnidadeProdutoDetalheAction;
import com.t2tierp.cadastros.servidor.UnidadeProdutoGridAction;
import com.t2tierp.pcp.servidor.PcpInstrucaoDetalheAction;
import com.t2tierp.pcp.servidor.PcpInstrucaoGridAction;
import com.t2tierp.pcp.servidor.PcpInstrucaoOpGridAction;
import com.t2tierp.pcp.servidor.PcpOpCabecalhoDetalheAction;
import com.t2tierp.pcp.servidor.PcpOpCabecalhoGridAction;
import com.t2tierp.pcp.servidor.PcpOpDetalheGridAction;
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
        a = new PcpInstrucaoGridAction(); put(a.getRequestName(), a);
        a = new PcpInstrucaoDetalheAction(); put(a.getRequestName(), a);
        a = new PcpInstrucaoOpGridAction(); put(a.getRequestName(), a);
        a = new PcpOpCabecalhoGridAction(); put(a.getRequestName(), a);
        a = new PcpOpCabecalhoDetalheAction(); put(a.getRequestName(), a);
        a = new PcpOpDetalheGridAction(); put(a.getRequestName(), a);
        
        //Produto
        a = new UnidadeProdutoGridAction(); put(a.getRequestName(), a);
        a = new UnidadeProdutoDetalheAction(); put(a.getRequestName(), a);
        a = new ProdutoGrupoGridAction(); put(a.getRequestName(), a);
        a = new ProdutoGrupoDetalheAction(); put(a.getRequestName(), a);
        a = new ProdutoSubGrupoGridAction(); put(a.getRequestName(), a);
        a = new ProdutoSubGrupoDetalheAction(); put(a.getRequestName(), a);
        a = new ProdutoGridAction(); put(a.getRequestName(), a);
        a = new ProdutoDetalheAction(); put(a.getRequestName(), a);
        a = new FichaTecnicaGridAction(); put(a.getRequestName(), a);
        
        /*validacoes e importacoes*/
        a = new ImportaDadosAction(); put(a.getRequestName(), a);
        a = new ValidaDadosAction(); put(a.getRequestName(), a);
        
    }
}
