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
package com.t2tierp.controleestoque.cliente;

import com.t2tierp.cadastros.java.ProdutoSubgrupoVO;
import com.t2tierp.cadastros.java.ProdutoVO;
import com.t2tierp.padrao.java.Constantes;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JOptionPane;
import org.openswing.swing.form.client.FormController;
import org.openswing.swing.mdi.client.MDIFrame;
import org.openswing.swing.message.receive.java.ErrorResponse;
import org.openswing.swing.message.receive.java.Response;
import org.openswing.swing.message.receive.java.ValueObject;
import org.openswing.swing.message.send.java.GridParams;
import org.openswing.swing.util.client.ClientUtils;
import org.openswing.swing.util.java.Consts;

public class EstoqueFormacaoPrecoDetalheController extends FormController {

    private EstoqueFormacaoPrecoDetalhe estoqueFormacaoPrecoDetalhe = null;
    private EstoqueFormacaoPrecoGrid estoqueFormacaoPrecoGrid = null;
    private String acaoServidor;

    public EstoqueFormacaoPrecoDetalheController(EstoqueFormacaoPrecoGrid estoqueFormacaoPrecoGrid, ProdutoSubgrupoVO produtoSubGrupo) {
        this.estoqueFormacaoPrecoGrid = estoqueFormacaoPrecoGrid;
        this.acaoServidor = "estoqueFormacaoPrecoDetalheGridAction";
        estoqueFormacaoPrecoDetalhe = new EstoqueFormacaoPrecoDetalhe(this);
        estoqueFormacaoPrecoDetalhe.setParentFrame(this.estoqueFormacaoPrecoGrid);
        this.estoqueFormacaoPrecoGrid.pushFrame(estoqueFormacaoPrecoDetalhe);
        MDIFrame.add(estoqueFormacaoPrecoDetalhe, true);

        estoqueFormacaoPrecoDetalhe.getForm1().setMode(Consts.EDIT);
        ProdutoVO produto = (ProdutoVO) estoqueFormacaoPrecoDetalhe.getForm1().getVOModel().getValueObject();
        produto.setProdutoSubgrupo(produtoSubGrupo);
        estoqueFormacaoPrecoDetalhe.getForm1().pull();
        estoqueFormacaoPrecoDetalhe.getItensController().setProdutoSubGrupo(produtoSubGrupo);
        estoqueFormacaoPrecoDetalhe.getGrid1().reloadData();
    }

    @Override
    public Response updateRecord(ValueObject oldPersistentObject, ValueObject persistentObject) throws Exception {
        List<ProdutoVO> itensFormacaoPreco = estoqueFormacaoPrecoDetalhe.getGrid1().getVOListTableModel().getDataVector();

        if (itensFormacaoPreco.isEmpty()) {
            return new ErrorResponse("Nenhum produto na lista");
        }

        //define os parametros da grid
        Map otherGridParams = new HashMap();
        otherGridParams.put("acao", Constantes.UPDATE);
        otherGridParams.put("itensFormacaoPreco", itensFormacaoPreco);

        //seta os parametros da grid
        GridParams pars = new GridParams(0, 0, null, null, null, otherGridParams);

        return ClientUtils.getData(acaoServidor, pars);
    }

    @Override
    public void afterEditData() {
        estoqueFormacaoPrecoGrid.getGrid1().reloadData();
        JOptionPane.showMessageDialog(estoqueFormacaoPrecoDetalhe, "Preços formados com sucesso!", "Informacao do Sistema", JOptionPane.INFORMATION_MESSAGE);
        estoqueFormacaoPrecoDetalhe.dispose();
    }

    public void efetuarCalculos() {
        /*
        C = Valor Compra
        E = % de encargos sobre vendas
        M = % markup  sobre o custo
        P = preço de venda
        
        P = C(1 + M) ÷ (1 - E)
         */
        ProdutoVO produto = (ProdutoVO) estoqueFormacaoPrecoDetalhe.getForm1().getVOModel().getValueObject();
        if (produto.getEncargosVenda() == null) {
            JOptionPane.showMessageDialog(estoqueFormacaoPrecoDetalhe, "Informe os encargos da venda.", "Informação do Sistema", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (produto.getMarkup() == null) {
            JOptionPane.showMessageDialog(estoqueFormacaoPrecoDetalhe, "Informe o markup.", "Informação do Sistema", JOptionPane.WARNING_MESSAGE);
            return;
        }

        List<ProdutoVO> itensFormacaoPreco = estoqueFormacaoPrecoDetalhe.getGrid1().getVOListTableModel().getDataVector();
        if (itensFormacaoPreco.isEmpty()) {
            JOptionPane.showMessageDialog(estoqueFormacaoPrecoDetalhe, "Nenhum item para formar preço.", "Informação do Sistema", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        BigDecimal valorVenda;
        BigDecimal valorCompra;
        BigDecimal encargo;
        BigDecimal markup;
        for (int i = 0; i < itensFormacaoPreco.size(); i++) {
            if (itensFormacaoPreco.get(i).getValorCompra() != null) {
                valorCompra = itensFormacaoPreco.get(i).getValorCompra();
                markup = produto.getMarkup().divide(BigDecimal.valueOf(100), RoundingMode.DOWN);
                encargo = produto.getEncargosVenda().divide(BigDecimal.valueOf(100), RoundingMode.DOWN);

                valorVenda = valorCompra.multiply(BigDecimal.ONE.add(markup)).divide(BigDecimal.ONE.subtract(encargo), RoundingMode.DOWN);

                itensFormacaoPreco.get(i).setValorVenda(valorVenda);
                itensFormacaoPreco.get(i).setMarkup(produto.getMarkup());
                itensFormacaoPreco.get(i).setEncargosVenda(produto.getEncargosVenda());
            }
            estoqueFormacaoPrecoDetalhe.getGrid1().getVOListTableModel().updateObjectAt(i);
        }
        JOptionPane.showMessageDialog(estoqueFormacaoPrecoDetalhe, "Cálculos Efetuados.", "Informação do Sistema", JOptionPane.INFORMATION_MESSAGE);
    }
}
