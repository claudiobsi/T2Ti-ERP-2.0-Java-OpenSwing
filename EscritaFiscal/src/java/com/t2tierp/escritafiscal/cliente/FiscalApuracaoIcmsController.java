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
package com.t2tierp.escritafiscal.cliente;

import com.t2tierp.cadastros.java.EmpresaVO;
import com.t2tierp.escritafiscal.java.FiscalApuracaoIcmsVO;
import com.t2tierp.padrao.cliente.Container;
import com.t2tierp.padrao.java.Constantes;
import org.openswing.swing.form.client.FormController;
import org.openswing.swing.mdi.client.MDIFrame;
import org.openswing.swing.message.receive.java.ErrorResponse;
import org.openswing.swing.message.receive.java.Response;
import org.openswing.swing.util.client.ClientUtils;
import org.openswing.swing.util.java.Consts;

public class FiscalApuracaoIcmsController extends FormController {

    private FiscalApuracaoIcms fiscalApuracaoIcms = null;
    private String acaoServidor;
    private EmpresaVO empresa;

    public FiscalApuracaoIcmsController() {
        this.acaoServidor = "fiscalApuracaoIcmsAction";
        fiscalApuracaoIcms = new FiscalApuracaoIcms(this);
        empresa = (EmpresaVO) Container.getContainer().get("empresa");
        MDIFrame.add(fiscalApuracaoIcms, true);
        fiscalApuracaoIcms.getForm1().setMode(Consts.READONLY);
    }

    @Override
    public Response loadData(Class valueObjectClass) {
        if (fiscalApuracaoIcms.getPeriodo() == null) {
            return new ErrorResponse("Informe o Período!");
        }

        return ClientUtils.getData(acaoServidor, new Object[]{Constantes.LOAD, fiscalApuracaoIcms.getPeriodo(), empresa});
    }

}
