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
package com.t2tierp.conciliacaocontabil.cliente;

import com.t2tierp.conciliacaocontabil.java.ConciliacaoFornecedorVO;
import com.t2tierp.contabilidade.java.ContabilLancamentoDetalheVO;
import com.t2tierp.financeiro.java.FinParcelaPagamentoVO;
import java.util.List;
import org.openswing.swing.client.GridControl;

public class LancamentoFornecedorConciliadoGridController {

    private GridControl gridControl;

    public LancamentoFornecedorConciliadoGridController(GridControl gridControl) {
        this.gridControl = gridControl;
    }

    public void conciliaLancamentos(List<FinParcelaPagamentoVO> listaParcelas, List<ContabilLancamentoDetalheVO> listaLancamentos) {
        FinParcelaPagamentoVO parcela;
        ContabilLancamentoDetalheVO lancamento;
        ConciliacaoFornecedorVO conciliado;
        gridControl.clearData();
        for (int i = 0; i < listaParcelas.size(); i++) {
            parcela = listaParcelas.get(i);
            for (int j = 0; j < listaLancamentos.size(); j++) {
                lancamento = listaLancamentos.get(j);

                if (parcela.getValorPago().compareTo(lancamento.getValor()) == 0) {
                    conciliado = new ConciliacaoFornecedorVO();

                    conciliado.setParcelaDataPagamento(parcela.getDataPagamento());
                    conciliado.setParcelaValorDesconto(parcela.getValorDesconto());
                    conciliado.setParcelaValorJuro(parcela.getValorJuro());
                    conciliado.setParcelaValorMulta(parcela.getValorMulta());
                    conciliado.setParcelaValorPago(parcela.getValorPago());

                    conciliado.setLancamentoContabilConta(lancamento.getContabilConta());
                    conciliado.setLancamentoContabilHistorico(lancamento.getContabilHistorico());
                    conciliado.setLancamentoHistorico(lancamento.getHistorico());
                    conciliado.setLancamentoTipo(lancamento.getTipo());
                    conciliado.setLancamentoValor(lancamento.getValor());

                    gridControl.getVOListTableModel().addObject(conciliado);
                }
            }
        }
    }
}
