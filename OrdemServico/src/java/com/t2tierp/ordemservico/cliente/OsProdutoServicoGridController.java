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
package com.t2tierp.ordemservico.cliente;

import com.t2tierp.ordemservico.java.OsProdutoServicoVO;
import com.t2tierp.padrao.java.Biblioteca;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.openswing.swing.client.GridControl;
import org.openswing.swing.message.receive.java.Response;
import org.openswing.swing.message.receive.java.VOListResponse;
import org.openswing.swing.table.client.GridController;
import org.openswing.swing.table.java.GridDataLocator;
import org.openswing.swing.util.java.Consts;

public class OsProdutoServicoGridController extends GridController implements GridDataLocator {

    private List<OsProdutoServicoVO> listaProdutoServico;
    private OsAberturaDetalhe detalhe;

    public OsProdutoServicoGridController(OsAberturaDetalhe detalhe) {
        this.detalhe = detalhe;
        listaProdutoServico = new ArrayList<>();
    }

    public Response loadData(int action, int startIndex, Map filteredColumns, ArrayList currentSortedColumns, ArrayList currentSortedVersusColumns, Class valueObjectType, Map otherGridParams) {
        return new VOListResponse(listaProdutoServico, false, listaProdutoServico.size());
    }

    @Override
    public boolean beforeInsertGrid(GridControl grid) {
        edicaoForm();
        return true;
    }

    @Override
    public Response insertRecords(int[] rowNumbers, ArrayList newValueObjects) throws Exception {
        for (Object o : newValueObjects) {
            calculaValores((OsProdutoServicoVO) o);
        }
        listaProdutoServico.addAll(newValueObjects);
        return new VOListResponse(newValueObjects, false, newValueObjects.size());
    }

    @Override
    public boolean beforeEditGrid(GridControl grid) {
        edicaoForm();
        return true;
    }

    @Override
    public Response updateRecords(int[] rowNumbers, ArrayList oldPersistentObjects, ArrayList persistentObjects) throws Exception {
        for (OsProdutoServicoVO o : listaProdutoServico) {
            if (persistentObjects.contains(o)) {
                calculaValores(o);
            }
        }
        return new VOListResponse(listaProdutoServico, false, listaProdutoServico.size());
    }
    
    @Override
    public boolean beforeDeleteGrid(GridControl grid) {
        edicaoForm();
        return true;
    }

    @Override
    public Response deleteRecords(ArrayList persistentObjects) throws Exception {
        listaProdutoServico.removeAll(persistentObjects);
        return new VOListResponse(persistentObjects, false, persistentObjects.size());
    }

    private void edicaoForm() {
        if (detalhe.getForm1().getMode() == Consts.READONLY) {
            detalhe.getForm1().setMode(Consts.EDIT);
        }
    }

    public List<OsProdutoServicoVO> getListaProdutoServico() {
        return listaProdutoServico;
    }

    public void setListaProdutoServico(List<OsProdutoServicoVO> listaProdutoServico) {
        if (listaProdutoServico != null) {
            this.listaProdutoServico = listaProdutoServico;
        }
    }

    private void calculaValores(OsProdutoServicoVO produtoServico) throws Exception {
        if (produtoServico.getProduto().getServico() != null && produtoServico.getProduto().getServico().equals("S")) {
            produtoServico.setTipo(1);
        } else {
            produtoServico.setTipo(0);
        }
        produtoServico.setValorUnitario(produtoServico.getProduto().getValorVenda());
        produtoServico.setValorSubtotal(Biblioteca.multiplica(produtoServico.getQuantidade(), produtoServico.getValorUnitario()));
        if (produtoServico.getTaxaDesconto() != null) {
            produtoServico.setValorDesconto(Biblioteca.multiplica(produtoServico.getValorSubtotal(), produtoServico.getTaxaDesconto()));
            produtoServico.setValorTotal(Biblioteca.subtrai(produtoServico.getValorSubtotal(), produtoServico.getValorDesconto()));
        } else {
            produtoServico.setTaxaDesconto(BigDecimal.ZERO);
            produtoServico.setValorDesconto(BigDecimal.ZERO);
            produtoServico.setValorTotal(produtoServico.getValorSubtotal());
        }
    }
}
