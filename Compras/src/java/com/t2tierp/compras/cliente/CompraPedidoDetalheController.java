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

import com.t2tierp.cadastros.java.ProdutoVO;
import com.t2tierp.compras.java.CompraPedidoDetalheVO;
import com.t2tierp.padrao.java.Constantes;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import org.openswing.swing.form.client.FormController;
import org.openswing.swing.mdi.client.MDIFrame;
import org.openswing.swing.message.receive.java.Response;
import org.openswing.swing.message.receive.java.ValueObject;
import org.openswing.swing.util.client.ClientUtils;
import org.openswing.swing.util.java.Consts;

public class CompraPedidoDetalheController extends FormController {

    private CompraPedidoDetalhe compraPedidoDetalhe = null;
    private String pk = null;
    private CompraPedidoGrid compraPedidoGrid = null;
    private String acaoServidor;
    private List<ProdutoVO> listaProduto;

    public CompraPedidoDetalheController(CompraPedidoGrid compraPedidoGrid, String pk) {
        this.compraPedidoGrid = compraPedidoGrid;
        this.pk = pk;
        this.acaoServidor = "compraPedidoDetalheAction";
        compraPedidoDetalhe = new CompraPedidoDetalhe(this);
        compraPedidoDetalhe.setParentFrame(this.compraPedidoGrid);
        this.compraPedidoGrid.pushFrame(compraPedidoDetalhe);
        MDIFrame.add(compraPedidoDetalhe, true);

        if (!pk.equals("")) {
            compraPedidoDetalhe.getForm1().setMode(Consts.READONLY);
            compraPedidoDetalhe.getForm1().reload();
        } else {
            compraPedidoDetalhe.getForm1().setMode(Consts.INSERT);
            compraPedidoDetalhe.getGridControl1().reloadData();
        }
    }

    public CompraPedidoDetalheController(CompraPedidoGrid compraPedidoGrid, List<ProdutoVO> listaProduto) {
        this.compraPedidoGrid = compraPedidoGrid;
        this.acaoServidor = "compraPedidoDetalheAction";
        compraPedidoDetalhe = new CompraPedidoDetalhe(this);
        compraPedidoDetalhe.setParentFrame(this.compraPedidoGrid);
        this.compraPedidoGrid.pushFrame(compraPedidoDetalhe);
        MDIFrame.add(compraPedidoDetalhe);

        this.listaProduto = listaProduto;
        compraPedidoDetalhe.getForm1().setMode(Consts.INSERT);
        compraPedidoDetalhe.getItensPedidoController().setCompraSugerida(true);
        compraPedidoDetalhe.getGridControl1().reloadData();
    }

    @Override
    public Response loadData(Class valueObjectClass) {
        return ClientUtils.getData(acaoServidor, new Object[]{Constantes.LOAD, pk});
    }

    @Override
    public void loadDataCompleted(boolean error) {
        compraPedidoDetalhe.getItensPedidoController().setPk(pk);
        compraPedidoDetalhe.getGridControl1().reloadData();
    }

    @Override
    public Response insertRecord(ValueObject newPersistentObject) throws Exception {
        List<CompraPedidoDetalheVO> pedidoDetalhe = compraPedidoDetalhe.getGridControl1().getVOListTableModel().getDataVector();

        return ClientUtils.getData(acaoServidor, new Object[]{Constantes.INSERT, newPersistentObject, pedidoDetalhe});
    }

    @Override
    public void afterInsertData() {
        compraPedidoGrid.getGrid1().reloadData();
        JOptionPane.showMessageDialog(compraPedidoDetalhe, "Pedido gerado com sucesso!", "Informacao do Sistema", JOptionPane.INFORMATION_MESSAGE);
        compraPedidoDetalhe.dispose();
    }

    @Override
    public Response updateRecord(ValueObject oldPersistentObject, ValueObject persistentObject) throws Exception {
        return ClientUtils.getData(acaoServidor, new Object[]{Constantes.UPDATE, oldPersistentObject, persistentObject});
    }

    @Override
    public void afterEditData() {
        compraPedidoGrid.getGrid1().reloadData();
        JOptionPane.showMessageDialog(compraPedidoDetalhe, "Dados alterados com sucesso!", "Informacao do Sistema", JOptionPane.INFORMATION_MESSAGE);
    }

    public void incluiCompraSugerida() {
        CompraPedidoDetalheVO pedidoDetalhe;
        for (int i = 0; i < listaProduto.size(); i++) {
            pedidoDetalhe = new CompraPedidoDetalheVO();
            pedidoDetalhe.setProduto(listaProduto.get(i));
            pedidoDetalhe.setQuantidade(listaProduto.get(i).getEstoqueIdeal().subtract(listaProduto.get(i).getQuantidadeEstoque()));
            pedidoDetalhe.setValorUnitario(listaProduto.get(i).getValorCompra());

            compraPedidoDetalhe.getGridControl1().getVOListTableModel().addObject(pedidoDetalhe);
        }
        compraPedidoDetalhe.getItensPedidoController().atualizaTotaisItens(compraPedidoDetalhe.getGridControl1().getVOListTableModel().getDataVector());
        compraPedidoDetalhe.getItensPedidoController().atualizaTotaisPedido();
    }
}
