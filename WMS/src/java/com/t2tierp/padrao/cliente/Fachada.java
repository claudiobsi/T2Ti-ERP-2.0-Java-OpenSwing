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
package com.t2tierp.padrao.cliente;

import com.t2tierp.wms.cliente.WmsAgendamentoGridController;
import com.t2tierp.wms.cliente.WmsArmazenamentoGridController;
import com.t2tierp.wms.cliente.WmsExpedicaoGridController;
import com.t2tierp.wms.cliente.WmsOrdemSeparacaoCabGridController;
import com.t2tierp.wms.cliente.WmsParametroGridController;
import com.t2tierp.wms.cliente.WmsRecebimentoCabecalhoGridController;
import com.t2tierp.wms.cliente.WmsRuaGridController;
import javax.swing.JOptionPane;
import org.openswing.swing.mdi.client.*;

public class Fachada implements ClientFacade {

    public Fachada() {
    }
    
    public void getWmsParametro() {
        new WmsParametroGridController();
    }
    
    public void getWmsEndereco() {
        new WmsRuaGridController();
    }

    public void getWmsAgendamento() {
        new WmsAgendamentoGridController();
    }

    public void getWmsRecebimento() {
        new WmsRecebimentoCabecalhoGridController();
    }

    public void getWmsArmazenamento() {
        new WmsArmazenamentoGridController();
    }

    public void getWmsOrdemSeparacao() {
        new WmsOrdemSeparacaoCabGridController();
    }

    public void getWmsExpedicao() {
        new WmsExpedicaoGridController();
    }
    
    public void getFuncaoPadrao() {
        JOptionPane.showMessageDialog(null, "Acesso não autorizado!", "Informação do Sistema", JOptionPane.INFORMATION_MESSAGE);
    }
}