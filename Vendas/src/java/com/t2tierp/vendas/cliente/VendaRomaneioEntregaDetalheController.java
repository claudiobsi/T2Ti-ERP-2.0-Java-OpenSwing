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
package com.t2tierp.vendas.cliente;

import com.t2tierp.padrao.java.Constantes;
import com.t2tierp.vendas.java.VendaCabecalhoVO;
import java.util.List;
import javax.swing.JOptionPane;
import org.openswing.swing.form.client.FormController;
import org.openswing.swing.mdi.client.MDIFrame;
import org.openswing.swing.message.receive.java.ErrorResponse;
import org.openswing.swing.message.receive.java.Response;
import org.openswing.swing.message.receive.java.ValueObject;
import org.openswing.swing.util.client.ClientUtils;
import org.openswing.swing.util.java.Consts;

public class VendaRomaneioEntregaDetalheController extends FormController {

    private VendaRomaneioEntregaDetalhe vendaRomaneioEntregaDetalhe = null;
    private String pk = null;
    private VendaRomaneioEntregaGrid vendaRomaneioEntregaGrid = null;
    private String acaoServidor;

    public VendaRomaneioEntregaDetalheController(VendaRomaneioEntregaGrid vendaRomaneioEntregaGrid, String pk) {
        this.vendaRomaneioEntregaGrid = vendaRomaneioEntregaGrid;
        this.pk = pk;
        this.acaoServidor = "vendaRomaneioEntregaDetalheAction";
        vendaRomaneioEntregaDetalhe = new VendaRomaneioEntregaDetalhe(this);
        vendaRomaneioEntregaDetalhe.setParentFrame(this.vendaRomaneioEntregaGrid);
        this.vendaRomaneioEntregaGrid.pushFrame(vendaRomaneioEntregaDetalhe);
        MDIFrame.add(vendaRomaneioEntregaDetalhe, true);

        if (pk != null) {
            vendaRomaneioEntregaDetalhe.getForm1().setMode(Consts.READONLY);
            vendaRomaneioEntregaDetalhe.getForm1().reload();
        } else {
            vendaRomaneioEntregaDetalhe.getForm1().setMode(Consts.INSERT);
            vendaRomaneioEntregaDetalhe.getGridControl1().reloadData();
        }
    }

    @Override
    public Response loadData(Class valueObjectClass) {
        return ClientUtils.getData(acaoServidor, new Object[]{Constantes.LOAD, pk});
    }

    @Override
    public void loadDataCompleted(boolean error) {
        vendaRomaneioEntregaDetalhe.getVendasController().setPk(pk);
        vendaRomaneioEntregaDetalhe.getGridControl1().reloadData();
    }

    @Override
    public Response insertRecord(ValueObject newPersistentObject) throws Exception {
        List<VendaCabecalhoVO> vendas = vendaRomaneioEntregaDetalhe.getGridControl1().getVOListTableModel().getDataVector();

        if (vendas.isEmpty()) {
            return new ErrorResponse("Não há vendas neste romaneio.");
        }

        return ClientUtils.getData(acaoServidor, new Object[]{Constantes.INSERT, newPersistentObject, vendas});
    }

    @Override
    public void afterInsertData() {
        vendaRomaneioEntregaGrid.getGrid1().reloadData();
        JOptionPane.showMessageDialog(vendaRomaneioEntregaDetalhe, "Dados salvos com sucesso!", "Informacao do Sistema", JOptionPane.INFORMATION_MESSAGE);
    }

    @Override
    public Response updateRecord(ValueObject oldPersistentObject, ValueObject persistentObject) throws Exception {
        List<VendaCabecalhoVO> vendas = vendaRomaneioEntregaDetalhe.getGridControl1().getVOListTableModel().getDataVector();

        if (vendas.isEmpty()) {
            return new ErrorResponse("Não há vendas neste romaneio.");
        }

        return ClientUtils.getData(acaoServidor, new Object[]{Constantes.UPDATE, oldPersistentObject, persistentObject, vendas});
    }

    @Override
    public void afterEditData() {
        vendaRomaneioEntregaGrid.getGrid1().reloadData();
        JOptionPane.showMessageDialog(vendaRomaneioEntregaDetalhe, "Dados alterados com sucesso!", "Informacao do Sistema", JOptionPane.INFORMATION_MESSAGE);
    }
}
