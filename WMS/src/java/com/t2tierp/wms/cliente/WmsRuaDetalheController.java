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
package com.t2tierp.wms.cliente;

import com.t2tierp.padrao.java.Constantes;
import com.t2tierp.wms.java.WmsRuaVO;
import javax.swing.JOptionPane;
import org.openswing.swing.form.client.FormController;
import org.openswing.swing.mdi.client.MDIFrame;
import org.openswing.swing.message.receive.java.Response;
import org.openswing.swing.message.receive.java.ValueObject;
import org.openswing.swing.util.client.ClientUtils;
import org.openswing.swing.util.java.Consts;

public class WmsRuaDetalheController extends FormController {

    private WmsRuaDetalhe telaDetalhe = null;
    private String pk = null;
    private WmsRuaGrid wmsRuaGrid = null;
    private String acaoServidor;

    public WmsRuaDetalheController(WmsRuaGrid wmsRuaGrid, String pk) {
        this.wmsRuaGrid = wmsRuaGrid;
        this.pk = pk;
        this.acaoServidor = "wmsRuaDetalheAction";
        telaDetalhe = new WmsRuaDetalhe(this);
        telaDetalhe.setParentFrame(this.wmsRuaGrid);
        this.wmsRuaGrid.pushFrame(telaDetalhe);
        MDIFrame.add(telaDetalhe, true);

        if (pk != null) {
            telaDetalhe.getForm1().setMode(Consts.READONLY);
            telaDetalhe.getForm1().reload();
        } else {
            telaDetalhe.getForm1().setMode(Consts.INSERT);
            telaDetalhe.getGridControlEstante().reloadData();
        }
    }

    @Override
    public Response loadData(Class valueObjectClass) {
        return ClientUtils.getData(acaoServidor, new Object[]{Constantes.LOAD, pk});
    }

    @Override
    public void loadDataCompleted(boolean error) {
        WmsRuaVO wmsRua = (WmsRuaVO) telaDetalhe.getForm1().getVOModel().getValueObject();
        
        telaDetalhe.getEstanteController().setListaWmsEstante(wmsRua.getListaWmsEstante());
        telaDetalhe.getGridControlEstante().reloadData();
    }
    
    @Override
    public Response insertRecord(ValueObject newPersistentObject) throws Exception {
        WmsRuaVO wmsRua = (WmsRuaVO) newPersistentObject;
        wmsRua.setListaWmsEstante(telaDetalhe.getEstanteController().getListaWmsEstante());
        
        return ClientUtils.getData(acaoServidor, new Object[]{Constantes.INSERT, wmsRua});
    }

    @Override
    public void afterInsertData() {
        wmsRuaGrid.getGrid1().reloadData();
        JOptionPane.showMessageDialog(telaDetalhe, "Dados salvos com sucesso!", "Informacao do Sistema", JOptionPane.INFORMATION_MESSAGE);
    }

    @Override
    public Response updateRecord(ValueObject oldPersistentObject, ValueObject persistentObject) throws Exception {
        WmsRuaVO wmsRua = (WmsRuaVO) persistentObject;
        wmsRua.setListaWmsEstante(telaDetalhe.getEstanteController().getListaWmsEstante());

        return ClientUtils.getData(acaoServidor, new Object[]{Constantes.UPDATE, oldPersistentObject, wmsRua});
    }

    @Override
    public void afterEditData() {
        wmsRuaGrid.getGrid1().reloadData();
        JOptionPane.showMessageDialog(telaDetalhe, "Dados alterados com sucesso!", "Informacao do Sistema", JOptionPane.INFORMATION_MESSAGE);
    }
}
