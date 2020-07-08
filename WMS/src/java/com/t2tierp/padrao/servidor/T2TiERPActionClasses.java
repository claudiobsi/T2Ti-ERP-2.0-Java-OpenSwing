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

import com.t2tierp.wms.servidor.WmsAgendamentoDetalheAction;
import com.t2tierp.wms.servidor.WmsAgendamentoGridAction;
import com.t2tierp.wms.servidor.WmsArmazenamentoDetalheAction;
import com.t2tierp.wms.servidor.WmsArmazenamentoGridAction;
import com.t2tierp.wms.servidor.WmsExpedicaoDetalheAction;
import com.t2tierp.wms.servidor.WmsExpedicaoGridAction;
import com.t2tierp.wms.servidor.WmsOrdemSeparacaoCabDetalheAction;
import com.t2tierp.wms.servidor.WmsOrdemSeparacaoCabGridAction;
import com.t2tierp.wms.servidor.WmsParametroDetalheAction;
import com.t2tierp.wms.servidor.WmsParametroGridAction;
import com.t2tierp.wms.servidor.WmsRecebimentoCabecalhoDetalheAction;
import com.t2tierp.wms.servidor.WmsRecebimentoCabecalhoGridAction;
import com.t2tierp.wms.servidor.WmsRuaDetalheAction;
import com.t2tierp.wms.servidor.WmsRuaGridAction;
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
        a = new WmsParametroGridAction(); put(a.getRequestName(), a);
        a = new WmsParametroDetalheAction(); put(a.getRequestName(), a);
        a = new WmsRuaGridAction(); put(a.getRequestName(), a);
        a = new WmsRuaDetalheAction(); put(a.getRequestName(), a);
        a = new WmsAgendamentoGridAction(); put(a.getRequestName(), a);
        a = new WmsAgendamentoDetalheAction(); put(a.getRequestName(), a);
        a = new WmsRecebimentoCabecalhoGridAction(); put(a.getRequestName(), a);
        a = new WmsRecebimentoCabecalhoDetalheAction(); put(a.getRequestName(), a);
        a = new WmsArmazenamentoGridAction(); put(a.getRequestName(), a);
        a = new WmsArmazenamentoDetalheAction(); put(a.getRequestName(), a);
        a = new WmsOrdemSeparacaoCabGridAction(); put(a.getRequestName(), a);
        a = new WmsOrdemSeparacaoCabDetalheAction(); put(a.getRequestName(), a);
        a = new WmsExpedicaoGridAction(); put(a.getRequestName(), a);
        a = new WmsExpedicaoDetalheAction(); put(a.getRequestName(), a);
        
        /*validacoes e importacoes*/
        a = new ImportaDadosAction(); put(a.getRequestName(), a);
        a = new ValidaDadosAction(); put(a.getRequestName(), a);
    }
}
