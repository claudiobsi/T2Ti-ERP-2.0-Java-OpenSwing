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
package com.t2tierp.nfse.cliente;

import com.t2tierp.nfse.java.NfseIntermediarioVO;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.openswing.swing.client.GridControl;
import org.openswing.swing.message.receive.java.Response;
import org.openswing.swing.message.receive.java.VOListResponse;
import org.openswing.swing.table.client.GridController;
import org.openswing.swing.table.java.GridDataLocator;
import org.openswing.swing.util.java.Consts;

public class NfseIntermediarioGridController extends GridController implements GridDataLocator {

    private List<NfseIntermediarioVO> listaNfseIntermediario;
    private NfseCabecalhoDetalhe telaDetalhe;

    public NfseIntermediarioGridController(NfseCabecalhoDetalhe telaDetalhe) {
        this.telaDetalhe = telaDetalhe;
        listaNfseIntermediario = new ArrayList<>();
    }

    public Response loadData(int action, int startIndex, Map filteredColumns, ArrayList currentSortedColumns, ArrayList currentSortedVersusColumns, Class valueObjectType, Map otherGridParams) {
        return new VOListResponse(listaNfseIntermediario, false, listaNfseIntermediario.size());    }

    @Override
    public boolean beforeInsertGrid(GridControl grid) {
        edicaoForm();
        return true;
    }

    @Override
    public Response insertRecords(int[] rowNumbers, ArrayList newValueObjects) throws Exception {
        listaNfseIntermediario.addAll(newValueObjects);
        return new VOListResponse(newValueObjects, false, newValueObjects.size());
    }

    @Override
    public boolean beforeEditGrid(GridControl grid) {
        edicaoForm();
        return true;
    }

    @Override
    public Response updateRecords(int[] rowNumbers, ArrayList oldPersistentObjects, ArrayList persistentObjects) throws Exception {
        listaNfseIntermediario = telaDetalhe.getGridControlIntermediario().getVOListTableModel().getDataVector();
        return new VOListResponse(persistentObjects, false, persistentObjects.size());
    }
    
    @Override
    public boolean beforeDeleteGrid(GridControl grid) {
        edicaoForm();
        return true;
    }
    
    @Override
    public Response deleteRecords(ArrayList persistentObjects) throws Exception {
        listaNfseIntermediario.removeAll(persistentObjects);
        return new VOListResponse(persistentObjects, false, persistentObjects.size());
    }
    
    private void edicaoForm() {
        if (telaDetalhe.getForm1().getMode() == Consts.READONLY) {
            telaDetalhe.getForm1().setMode(Consts.EDIT);
        }
    }

    public List<NfseIntermediarioVO> getListaNfseIntermediario() {
        return listaNfseIntermediario;
    }

    public void setListaNfseIntermediario(List<NfseIntermediarioVO> listaNfseIntermediario) {
        if (listaNfseIntermediario != null) {
            this.listaNfseIntermediario = listaNfseIntermediario;
        }
    }
    
}
