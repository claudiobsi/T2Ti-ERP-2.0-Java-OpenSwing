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

import com.t2tierp.nfse.java.NfseDetalheVO;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.openswing.swing.client.GridControl;
import org.openswing.swing.message.receive.java.Response;
import org.openswing.swing.message.receive.java.VOListResponse;
import org.openswing.swing.message.receive.java.ValueObject;
import org.openswing.swing.table.client.GridController;
import org.openswing.swing.table.java.GridDataLocator;
import org.openswing.swing.util.java.Consts;

public class NfseDetalheGridController extends GridController implements GridDataLocator {

    private List<NfseDetalheVO> listaNfseDetalhe;
    private NfseCabecalhoDetalhe telaDetalhe;

    public NfseDetalheGridController(NfseCabecalhoDetalhe telaDetalhe) {
        this.telaDetalhe = telaDetalhe;
        listaNfseDetalhe = new ArrayList<>();
    }

    public Response loadData(int action, int startIndex, Map filteredColumns, ArrayList currentSortedColumns, ArrayList currentSortedVersusColumns, Class valueObjectType, Map otherGridParams) {
        return new VOListResponse(listaNfseDetalhe, false, listaNfseDetalhe.size());
    }

    @Override
    public boolean beforeInsertGrid(GridControl grid) {
        edicaoForm();
        return true;
    }

    @Override
    public Response insertRecords(int[] rowNumbers, ArrayList newValueObjects) throws Exception {
        listaNfseDetalhe.addAll(newValueObjects);
        return new VOListResponse(newValueObjects, false, newValueObjects.size());
    }

    @Override
    public boolean beforeEditGrid(GridControl grid) {
        edicaoForm();
        return true;
    }

    @Override
    public Response updateRecords(int[] rowNumbers, ArrayList oldPersistentObjects, ArrayList persistentObjects) throws Exception {
        listaNfseDetalhe = telaDetalhe.getGridControlServicos().getVOListTableModel().getDataVector();
        return new VOListResponse(persistentObjects, false, persistentObjects.size());
    }

    @Override
    public boolean beforeDeleteGrid(GridControl grid) {
        edicaoForm();
        return true;
    }

    @Override
    public Response deleteRecords(ArrayList persistentObjects) throws Exception {
        listaNfseDetalhe.removeAll(persistentObjects);
        return new VOListResponse(persistentObjects, false, persistentObjects.size());
    }

    @Override
    public void doubleClick(int rowNumber, ValueObject persistentObject) {
        NfseDetalheVO nfseDetalhe = (NfseDetalheVO) persistentObject;
        new NfseServicoDetalheController(nfseDetalhe);
    }

    private void edicaoForm() {
        if (telaDetalhe.getForm1().getMode() == Consts.READONLY) {
            telaDetalhe.getForm1().setMode(Consts.EDIT);
        }
    }

    public List<NfseDetalheVO> getListaNfseDetalhe() {
        return listaNfseDetalhe;
    }

    public void setListaNfseDetalhe(List<NfseDetalheVO> listaNfseDetalhe) {
        if (listaNfseDetalhe != null) {
            this.listaNfseDetalhe = listaNfseDetalhe;
        }
    }

}
