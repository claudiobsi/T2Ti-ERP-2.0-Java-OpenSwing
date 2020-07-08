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
import com.t2tierp.vendas.java.VendaOrcamentoCabecalhoVO;
import com.t2tierp.vendas.java.VendaOrcamentoDetalheVO;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.openswing.swing.client.GridControl;
import org.openswing.swing.message.receive.java.Response;
import org.openswing.swing.message.receive.java.VOListResponse;
import org.openswing.swing.message.send.java.GridParams;
import org.openswing.swing.table.client.GridController;
import org.openswing.swing.table.java.GridDataLocator;
import org.openswing.swing.util.client.ClientUtils;
import org.openswing.swing.util.java.Consts;

public class VendaOrcamentoDetalheGridController extends GridController implements GridDataLocator {

    private String acaoServidor;
    private VendaOrcamentoDetalhe vendaOrcamentoDetalhe;
    private String pk;

    public VendaOrcamentoDetalheGridController(VendaOrcamentoDetalhe vendaOrcamentoDetalhe) {
        acaoServidor = "vendaOrcamentoDetalheGridAction";
        this.vendaOrcamentoDetalhe = vendaOrcamentoDetalhe;
    }

    public Response loadData(int action, int startIndex, Map filteredColumns, ArrayList currentSortedColumns, ArrayList currentSortedVersusColumns, Class valueObjectType, Map otherGridParams) {
        //define os parametros da grid
        otherGridParams.put("acao", Constantes.LOAD);
        otherGridParams.put("idVendaOrcamentoCabecalho", pk);

        return ClientUtils.getData(acaoServidor, new GridParams(action, startIndex, filteredColumns, currentSortedColumns, currentSortedVersusColumns, otherGridParams));
    }

    @Override
    public boolean beforeInsertGrid(GridControl grid) {
        if (vendaOrcamentoDetalhe.getForm1().getMode() == Consts.READONLY) {
            vendaOrcamentoDetalhe.getForm1().setMode(Consts.EDIT);
        }
        return true;
    }

    @Override
    public Response insertRecords(int[] rowNumbers, ArrayList newValueObjects) throws Exception {
        return new VOListResponse(newValueObjects, false, newValueObjects.size());
    }

    @Override
    public void afterInsertGrid(GridControl grid) {
        calculaTotais();
    }

    @Override
    public boolean beforeEditGrid(GridControl grid) {
        if (vendaOrcamentoDetalhe.getForm1().getMode() == Consts.READONLY) {
            vendaOrcamentoDetalhe.getForm1().setMode(Consts.EDIT);
        }
        return true;
    }

    @Override
    public Response updateRecords(int[] rowNumbers, ArrayList oldPersistentObjects, ArrayList persistentObjects) throws Exception {
        return new VOListResponse(persistentObjects, false, persistentObjects.size());
    }

    @Override
    public void afterEditGrid(GridControl grid) {
        calculaTotais();
    }

    @Override
    public boolean beforeDeleteGrid(GridControl grid) {
        if (vendaOrcamentoDetalhe.getForm1().getMode() == Consts.READONLY) {
            vendaOrcamentoDetalhe.getForm1().setMode(Consts.EDIT);
        }
        return true;
    }

    @Override
    public Response deleteRecords(ArrayList persistentObjects) throws Exception {
        return new VOListResponse(persistentObjects, false, persistentObjects.size());
    }

    @Override
    public void afterDeleteGrid() {
        calculaTotais();
    }

    public void setPk(String pk) {
        this.pk = pk;
    }

    private void calculaTotais() {
        List<VendaOrcamentoDetalheVO> orcamentoDetalhe = vendaOrcamentoDetalhe.getGridControl1().getVOListTableModel().getDataVector();
        VendaOrcamentoCabecalhoVO orcamentoCabecalho = (VendaOrcamentoCabecalhoVO) vendaOrcamentoDetalhe.getForm1().getVOModel().getValueObject();
        BigDecimal subTotal = BigDecimal.ZERO;
        BigDecimal totalDesconto = BigDecimal.ZERO;
        for (int i = 0; i < orcamentoDetalhe.size(); i++) {
            orcamentoDetalhe.get(i).setValorSubtotal(orcamentoDetalhe.get(i).getQuantidade().multiply(orcamentoDetalhe.get(i).getValorUnitario()));
            subTotal = subTotal.add(orcamentoDetalhe.get(i).getValorSubtotal());
            if (orcamentoDetalhe.get(i).getTaxaDesconto() != null) {
                orcamentoDetalhe.get(i).setValorDesconto(orcamentoDetalhe.get(i).getValorSubtotal().multiply(orcamentoDetalhe.get(i).getTaxaDesconto().divide(BigDecimal.valueOf(100), RoundingMode.HALF_DOWN)));
            }
            if (orcamentoDetalhe.get(i).getValorDesconto() != null) {
                totalDesconto = totalDesconto.add(orcamentoDetalhe.get(i).getValorDesconto());
                orcamentoDetalhe.get(i).setValorTotal(orcamentoDetalhe.get(i).getValorSubtotal().subtract(orcamentoDetalhe.get(i).getValorDesconto()));
            } else {
                orcamentoDetalhe.get(i).setValorTotal(orcamentoDetalhe.get(i).getValorSubtotal());
            }
        }
        orcamentoCabecalho.setValorSubtotal(subTotal);
        if (totalDesconto.compareTo(BigDecimal.ZERO) != 0) {
            orcamentoCabecalho.setValorDesconto(totalDesconto);
            orcamentoCabecalho.setTaxaDesconto(totalDesconto.divide(subTotal, RoundingMode.HALF_DOWN).multiply(BigDecimal.valueOf(100)));
        }

        orcamentoCabecalho.setValorTotal(subTotal);
        if (orcamentoCabecalho.getValorFrete() != null){
            orcamentoCabecalho.setValorTotal(orcamentoCabecalho.getValorTotal().add(orcamentoCabecalho.getValorFrete()));
        }
        if (orcamentoCabecalho.getValorDesconto() != null){
            orcamentoCabecalho.setValorTotal(orcamentoCabecalho.getValorTotal().subtract(orcamentoCabecalho.getValorDesconto()));
        }

        if (orcamentoCabecalho.getTaxaComissao() != null) {
            orcamentoCabecalho.setValorComissao(subTotal.subtract(totalDesconto).multiply(orcamentoCabecalho.getTaxaComissao().divide(BigDecimal.valueOf(100), RoundingMode.HALF_DOWN)));
        }
        vendaOrcamentoDetalhe.getFormController().atualizaTotais();
    }

}
