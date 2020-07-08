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
import com.t2tierp.padrao.java.Constantes;
import java.math.BigDecimal;
import java.util.List;
import javax.swing.JOptionPane;
import org.openswing.swing.form.client.FormController;
import org.openswing.swing.mdi.client.MDIFrame;
import org.openswing.swing.message.receive.java.ErrorResponse;
import org.openswing.swing.message.receive.java.Response;
import org.openswing.swing.message.receive.java.ValueObject;
import org.openswing.swing.util.client.ClientUtils;
import org.openswing.swing.util.java.Consts;

public class CompraMapaComparativoDetalheController extends FormController {

    private CompraMapaComparativoDetalhe compraMapaComparativoDetalhe = null;
    private String pk = null;
    private CompraMapaComparativoGrid compraMapaComparativoGrid = null;
    private String acaoServidor;

    public CompraMapaComparativoDetalheController(CompraMapaComparativoGrid compraMapaComparativoGrid, String pk) {
        this.compraMapaComparativoGrid = compraMapaComparativoGrid;
        this.pk = pk;
        this.acaoServidor = "compraMapaComparativoDetalheAction";
        compraMapaComparativoDetalhe = new CompraMapaComparativoDetalhe(this);
        compraMapaComparativoDetalhe.setParentFrame(this.compraMapaComparativoGrid);
        this.compraMapaComparativoGrid.pushFrame(compraMapaComparativoDetalhe);
        MDIFrame.add(compraMapaComparativoDetalhe, true);

        compraMapaComparativoDetalhe.getForm1().setMode(Consts.READONLY);
        compraMapaComparativoDetalhe.getForm1().reload();
    }

    @Override
    public Response loadData(Class valueObjectClass) {
        return ClientUtils.getData(acaoServidor, new Object[]{Constantes.LOAD, pk});
    }

    @Override
    public void loadDataCompleted(boolean error) {
        compraMapaComparativoDetalhe.getFornecedorMapaComparativoController().setPk(pk);
        compraMapaComparativoDetalhe.getGridFornecedor().reloadData();

        compraMapaComparativoDetalhe.getMapaComparativoDetalheController().setPk(pk);
        compraMapaComparativoDetalhe.getGridItensCotacao().reloadData();
    }

    @Override
    public Response updateRecord(ValueObject oldPersistentObject, ValueObject persistentObject) throws Exception {
        List<CompraCotacaoDetalheVO> compraCotacaoDetalhe = compraMapaComparativoDetalhe.getGridItensCotacao().getVOListTableModel().getDataVector();
        boolean produtoPedido = false;
        for (int i = 0; i < compraCotacaoDetalhe.size(); i++) {
            if (compraCotacaoDetalhe.get(i).getQuantidadePedida() != null) {
                if (compraCotacaoDetalhe.get(i).getQuantidadePedida().compareTo(compraCotacaoDetalhe.get(i).getQuantidade()) == 1) {
                    return new ErrorResponse("Quantidade pedida do produto '" + compraCotacaoDetalhe.get(i).getProduto().getNome() + "' é maior que a quantidade cotada!");
                } else if (compraCotacaoDetalhe.get(i).getQuantidadePedida().compareTo(BigDecimal.ZERO) == 1) {
                    produtoPedido = true;
                }
            }
        }
        if (!produtoPedido) {
            return new ErrorResponse("Nenhum produto com quantidade pedida maior que 0(zero)!");
        }
        List<CompraFornecedorCotacaoVO> fornecedores = compraMapaComparativoDetalhe.getGridFornecedor().getVOListTableModel().getDataVector();

        return ClientUtils.getData(acaoServidor, new Object[]{Constantes.UPDATE, persistentObject, compraCotacaoDetalhe, fornecedores});
    }

    @Override
    public void afterEditData() {
        JOptionPane.showMessageDialog(compraMapaComparativoDetalhe, "Pedido realizado com sucesso!", "Informacao do Sistema", JOptionPane.INFORMATION_MESSAGE);
        compraMapaComparativoGrid.getGrid1().reloadData();
        compraMapaComparativoDetalhe.dispose();
    }
}
