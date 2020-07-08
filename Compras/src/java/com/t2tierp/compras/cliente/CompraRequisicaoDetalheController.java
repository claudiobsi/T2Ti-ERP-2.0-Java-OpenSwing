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
import com.t2tierp.compras.java.CompraRequisicaoDetalheVO;
import com.t2tierp.padrao.java.Constantes;
import java.math.BigDecimal;
import java.util.List;
import javax.swing.JOptionPane;
import org.openswing.swing.form.client.FormController;
import org.openswing.swing.mdi.client.MDIFrame;
import org.openswing.swing.message.receive.java.Response;
import org.openswing.swing.message.receive.java.ValueObject;
import org.openswing.swing.util.client.ClientUtils;
import org.openswing.swing.util.java.Consts;

public class CompraRequisicaoDetalheController extends FormController {

    private CompraRequisicaoDetalhe compraRequisicaoDetalhe = null;
    private String pk = null;
    private CompraRequisicaoGrid compraRequisicaoGrid = null;
    private String acaoServidor;
    private List<ProdutoVO> listaProduto;

    public CompraRequisicaoDetalheController(CompraRequisicaoGrid compraRequisicaoGrid, String pk) {
        this.compraRequisicaoGrid = compraRequisicaoGrid;
        this.pk = pk;
        this.acaoServidor = "compraRequisicaoDetalheAction";
        compraRequisicaoDetalhe = new CompraRequisicaoDetalhe(this);
        compraRequisicaoDetalhe.setParentFrame(this.compraRequisicaoGrid);
        this.compraRequisicaoGrid.pushFrame(compraRequisicaoDetalhe);
        MDIFrame.add(compraRequisicaoDetalhe, true);

        if (!pk.equals("")) {
            compraRequisicaoDetalhe.getForm1().setMode(Consts.READONLY);
            compraRequisicaoDetalhe.getForm1().reload();
        } else {
            compraRequisicaoDetalhe.getForm1().setMode(Consts.INSERT);
            compraRequisicaoDetalhe.getGridRequisicaoDetalhe().reloadData();
        }
    }

    public CompraRequisicaoDetalheController(CompraRequisicaoGrid compraRequisicaoGrid, List<ProdutoVO> listaProduto) {
        this.compraRequisicaoGrid = compraRequisicaoGrid;
        this.acaoServidor = "compraRequisicaoDetalheAction";
        compraRequisicaoDetalhe = new CompraRequisicaoDetalhe(this);
        compraRequisicaoDetalhe.setParentFrame(this.compraRequisicaoGrid);
        this.compraRequisicaoGrid.pushFrame(compraRequisicaoDetalhe);
        MDIFrame.add(compraRequisicaoDetalhe);
        
        this.listaProduto = listaProduto;
        compraRequisicaoDetalhe.getForm1().setMode(Consts.INSERT);
        compraRequisicaoDetalhe.getRequisicaoDetalheGridController().setCompraSugerida(true);
        compraRequisicaoDetalhe.getGridRequisicaoDetalhe().reloadData();
    }

    @Override
    public Response loadData(Class valueObjectClass) {
        return ClientUtils.getData(acaoServidor, new Object[]{Constantes.LOAD, pk});
    }

    @Override
    public void loadDataCompleted(boolean error) {
        compraRequisicaoDetalhe.getRequisicaoDetalheGridController().setPk(pk);
        compraRequisicaoDetalhe.getGridRequisicaoDetalhe().reloadData();
    }

    @Override
    public Response insertRecord(ValueObject newPersistentObject) throws Exception {
        List<CompraRequisicaoDetalheVO> requisicaoDetalhe = compraRequisicaoDetalhe.getGridRequisicaoDetalhe().getVOListTableModel().getDataVector();

        return ClientUtils.getData(acaoServidor, new Object[]{Constantes.INSERT, newPersistentObject, requisicaoDetalhe});
    }

    @Override
    public void afterInsertData() {
        compraRequisicaoGrid.getGrid1().reloadData();
        JOptionPane.showMessageDialog(compraRequisicaoDetalhe, "Dados salvos com sucesso!", "Informacao do Sistema", JOptionPane.INFORMATION_MESSAGE);
    }

    @Override
    public Response updateRecord(ValueObject oldPersistentObject, ValueObject persistentObject) throws Exception {
        List<CompraRequisicaoDetalheVO> requisicaoDetalhe = compraRequisicaoDetalhe.getGridRequisicaoDetalhe().getVOListTableModel().getDataVector();

        return ClientUtils.getData(acaoServidor, new Object[]{Constantes.UPDATE, oldPersistentObject, persistentObject, requisicaoDetalhe});
    }

    @Override
    public void afterEditData() {
        compraRequisicaoGrid.getGrid1().reloadData();
        JOptionPane.showMessageDialog(compraRequisicaoDetalhe, "Dados alterados com sucesso!", "Informacao do Sistema", JOptionPane.INFORMATION_MESSAGE);
    }

    public void incluiCompraSugerida(){
        CompraRequisicaoDetalheVO requisicao;
        for (int i = 0; i < listaProduto.size(); i++) {
            requisicao = new CompraRequisicaoDetalheVO();
            requisicao.setProduto(listaProduto.get(i));
            requisicao.setQuantidade(listaProduto.get(i).getEstoqueIdeal().subtract(listaProduto.get(i).getQuantidadeEstoque()));
            requisicao.setQuantidadeCotada(BigDecimal.ZERO);
            requisicao.setItemCotado("N");

            compraRequisicaoDetalhe.getGridRequisicaoDetalhe().getVOListTableModel().addObject(requisicao);
        }
    }
}
