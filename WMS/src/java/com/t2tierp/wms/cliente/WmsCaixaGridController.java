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

import com.t2tierp.wms.java.WmsCaixaVO;
import com.t2tierp.wms.java.WmsEstanteVO;
import java.util.ArrayList;
import java.util.Map;
import javax.swing.JOptionPane;
import org.openswing.swing.client.GridControl;
import org.openswing.swing.message.receive.java.Response;
import org.openswing.swing.message.receive.java.VOListResponse;
import org.openswing.swing.message.receive.java.ValueObject;
import org.openswing.swing.table.client.GridController;
import org.openswing.swing.table.java.GridDataLocator;
import org.openswing.swing.util.java.Consts;

public class WmsCaixaGridController extends GridController implements GridDataLocator {

    private WmsEstanteVO wmsEstante;
    private WmsRuaDetalhe telaDetalhe;

    public WmsCaixaGridController(WmsRuaDetalhe telaDetalhe) {
        this.telaDetalhe = telaDetalhe;
    }

    public Response loadData(int action, int startIndex, Map filteredColumns, ArrayList currentSortedColumns, ArrayList currentSortedVersusColumns, Class valueObjectType, Map otherGridParams) {
        if (wmsEstante != null && wmsEstante.getListaWmsCaixa() != null) {
            return new VOListResponse(wmsEstante.getListaWmsCaixa(), false, wmsEstante.getListaWmsCaixa().size());
        } else {
            return new VOListResponse();
        }
    }

    @Override
    public boolean beforeInsertGrid(GridControl grid) {
        return edicaoForm();
    }

    @Override
    public void createValueObject(ValueObject valueObject) throws Exception {
        ((WmsCaixaVO) valueObject).setWmsEstante(wmsEstante);
    }

    @Override
    public Response insertRecords(int[] rowNumbers, ArrayList newValueObjects) throws Exception {
        wmsEstante.getListaWmsCaixa().addAll(newValueObjects);
        return new VOListResponse(newValueObjects, false, newValueObjects.size());
    }

    @Override
    public boolean beforeEditGrid(GridControl grid) {
        return edicaoForm();
    }

    @Override
    public Response updateRecords(int[] rowNumbers, ArrayList oldPersistentObjects, ArrayList persistentObjects) throws Exception {
        wmsEstante.setListaWmsCaixa(telaDetalhe.getGridControlCaixa().getVOListTableModel().getDataVector());
        return new VOListResponse(persistentObjects, false, persistentObjects.size());
    }

    @Override
    public boolean beforeDeleteGrid(GridControl grid) {
        return edicaoForm();
    }

    @Override
    public Response deleteRecords(ArrayList persistentObjects) throws Exception {
        wmsEstante.getListaWmsCaixa().removeAll(persistentObjects);
        return new VOListResponse(persistentObjects, false, persistentObjects.size());
    }

    private boolean edicaoForm() {
        if (telaDetalhe.getGridControlEstante().getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(telaDetalhe, "Nenhuma estante selecionada", "Erro", JOptionPane.ERROR_MESSAGE);
            return false;
        } else {
            if (telaDetalhe.getForm1().getMode() == Consts.READONLY) {
                telaDetalhe.getForm1().setMode(Consts.EDIT);
            }
            return true;
        }
    }

    public void setWmsEstante(WmsEstanteVO wmsEstante) {
        this.wmsEstante = wmsEstante;
    }
}
