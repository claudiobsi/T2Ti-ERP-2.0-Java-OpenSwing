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
package com.t2tierp.compras.cliente;

import com.t2tierp.compras.java.CompraCotacaoDetalheVO;
import com.t2tierp.compras.java.CompraFornecedorCotacaoVO;
import com.t2tierp.compras.java.CompraRequisicaoDetalheVO;
import com.t2tierp.padrao.java.Constantes;
import java.util.List;
import javax.swing.JOptionPane;
import org.openswing.swing.form.client.Form;
import org.openswing.swing.form.client.FormController;
import org.openswing.swing.mdi.client.MDIFrame;
import org.openswing.swing.message.receive.java.ErrorResponse;
import org.openswing.swing.message.receive.java.Response;
import org.openswing.swing.message.receive.java.ValueObject;
import org.openswing.swing.util.client.ClientUtils;
import org.openswing.swing.util.java.Consts;

public class CompraCotacaoDetalheController extends FormController {

    private CompraCotacaoDetalhe compraCotacaoDetalhe = null;
    private String pk = null;
    private CompraCotacaoGrid compraCotacaoGrid = null;
    private String acaoServidor;

    public CompraCotacaoDetalheController(CompraCotacaoGrid compraCotacaoGrid, String pk) {
        this.compraCotacaoGrid = compraCotacaoGrid;
        this.pk = pk;
        this.acaoServidor = "compraCotacaoDetalheAction";
        compraCotacaoDetalhe = new CompraCotacaoDetalhe(this);
        compraCotacaoDetalhe.setParentFrame(this.compraCotacaoGrid);
        this.compraCotacaoGrid.pushFrame(compraCotacaoDetalhe);
        MDIFrame.add(compraCotacaoDetalhe, true);

        if (pk != null) {
            compraCotacaoDetalhe.getForm1().setMode(Consts.READONLY);
            compraCotacaoDetalhe.getForm1().reload();
        } else {
            compraCotacaoDetalhe.getForm1().setMode(Consts.INSERT);
            compraCotacaoDetalhe.getGridFornecedor().reloadData();
            compraCotacaoDetalhe.getGridRequisicaoDetalhe().reloadData();
        }
    }

    @Override
    public Response loadData(Class valueObjectClass) {
        return ClientUtils.getData(acaoServidor, new Object[]{Constantes.LOAD, pk});
    }

    @Override
    public void loadDataCompleted(boolean error) {
        compraCotacaoDetalhe.getFornecedorCotacaoController().setPk(pk);
        compraCotacaoDetalhe.getGridFornecedor().reloadData();

        compraCotacaoDetalhe.getGridRequisicaoDetalhe().reloadData();
    }

    @Override
    public boolean beforeSaveDataInInsert(Form form) {
        if (JOptionPane.showConfirmDialog(compraCotacaoDetalhe, "Confirma a operação?\nA cotação não poderá ser alterada posteriormente", "Pergunta do Sistema", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            return true;
        }
        return false;
    }

    @Override
    public Response insertRecord(ValueObject newPersistentObject) throws Exception {
        List<CompraFornecedorCotacaoVO> fornecedores = compraCotacaoDetalhe.getGridFornecedor().getVOListTableModel().getDataVector();
        if (fornecedores.isEmpty() || (compraCotacaoDetalhe.getGridFornecedor().getMode() == Consts.INSERT)) {
            return new ErrorResponse("É necessário incluir um fornecedor para cotação!");
        }
        List<CompraCotacaoDetalheVO> cotacaoDetalhe = compraCotacaoDetalhe.getGridItensCotacao().getVOListTableModel().getDataVector();
        if (cotacaoDetalhe.isEmpty()) {
            return new ErrorResponse("É necessário incluir itens para cotação!");
        }

        List<CompraRequisicaoDetalheVO> requisicaoDetalhe = compraCotacaoDetalhe.getGridRequisicaoDetalhe().getVOListTableModel().getDataVector();

        return ClientUtils.getData(acaoServidor, new Object[]{Constantes.INSERT, newPersistentObject, fornecedores, cotacaoDetalhe, requisicaoDetalhe});
    }

    @Override
    public void afterInsertData() {
        compraCotacaoGrid.getGrid1().reloadData();
        JOptionPane.showMessageDialog(compraCotacaoDetalhe, "Dados salvos com sucesso!", "Informacao do Sistema", JOptionPane.INFORMATION_MESSAGE);
        compraCotacaoDetalhe.dispose();
    }
}
