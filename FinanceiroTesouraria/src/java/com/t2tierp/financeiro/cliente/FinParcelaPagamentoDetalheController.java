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
package com.t2tierp.financeiro.cliente;

import com.t2tierp.financeiro.java.FinParcelaPagamentoVO;
import com.t2tierp.financeiro.java.FinParcelaPagarVO;
import com.t2tierp.padrao.java.Constantes;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Calendar;
import javax.swing.JOptionPane;
import org.openswing.swing.form.client.FormController;
import org.openswing.swing.mdi.client.MDIFrame;
import org.openswing.swing.message.receive.java.Response;
import org.openswing.swing.message.receive.java.VOResponse;
import org.openswing.swing.message.receive.java.ValueObject;
import org.openswing.swing.util.client.ClientUtils;
import org.openswing.swing.util.java.Consts;

public class FinParcelaPagamentoDetalheController extends FormController {

    private FinParcelaPagamentoDetalhe finParcelaPagamentoDetalhe = null;
    private FinParcelaPagamentoGrid finParcelaPagamentoGrid = null;
    private String acaoServidor;
    FinParcelaPagarVO finParcelaPagar;

    public FinParcelaPagamentoDetalheController(FinParcelaPagamentoGrid finParcelaPagamentoGrid, FinParcelaPagarVO finParcelaPagar) {
        this.finParcelaPagamentoGrid = finParcelaPagamentoGrid;
        this.acaoServidor = "finParcelaPagamentoDetalheAction";
        finParcelaPagamentoDetalhe = new FinParcelaPagamentoDetalhe(this);
        finParcelaPagamentoDetalhe.setParentFrame(this.finParcelaPagamentoGrid);
        this.finParcelaPagamentoGrid.pushFrame(finParcelaPagamentoDetalhe);
        MDIFrame.add(finParcelaPagamentoDetalhe, true);

        this.finParcelaPagar = finParcelaPagar;
        finParcelaPagamentoDetalhe.getForm1().reload();
    }

    @Override
    public Response loadData(Class valueObjectClass) {
        FinParcelaPagamentoVO parcelaPagamento = new FinParcelaPagamentoVO();
        parcelaPagamento.setFinParcelaPagar(finParcelaPagar);
        parcelaPagamento.setContaCaixa(finParcelaPagar.getContaCaixa());
        parcelaPagamento.setValorPago(finParcelaPagar.getValor());

        return new VOResponse(parcelaPagamento);
    }

    @Override
    public void loadDataCompleted(boolean error) {
        finParcelaPagamentoDetalhe.getForm1().setMode(Consts.EDIT);

        finParcelaPagamentoDetalhe.getPagamentoEfetuadoGridController().setParcelaPagar(finParcelaPagar);
        finParcelaPagamentoDetalhe.getGridControlPagamentoEfetuado().reloadData();
    }

    @Override
    public Response updateRecord(ValueObject oldPersistentObject, ValueObject persistentObject) throws Exception {
        return ClientUtils.getData(acaoServidor, new Object[]{Constantes.UPDATE, oldPersistentObject, persistentObject});
    }

    @Override
    public void afterEditData() {
        finParcelaPagamentoGrid.getGrid1().reloadData();
    }

    public void calculaTotalPago() {
        finParcelaPagamentoDetalhe.getForm1().pull();
        FinParcelaPagamentoVO pagamento = (FinParcelaPagamentoVO) finParcelaPagamentoDetalhe.getForm1().getVOModel().getValueObject();
        BigDecimal valorJuro = BigDecimal.ZERO;
        BigDecimal valorMulta = BigDecimal.ZERO;
        BigDecimal valorDesconto = BigDecimal.ZERO;
        if (pagamento.getTaxaJuro() != null && pagamento.getDataPagamento() != null) {
            Calendar dataPagamento = Calendar.getInstance();
            dataPagamento.setTime(pagamento.getDataPagamento());
            Calendar dataVencimento = Calendar.getInstance();
            dataVencimento.setTime(pagamento.getFinParcelaPagar().getDataVencimento());
            if (dataVencimento.before(dataPagamento)) {
                long diasAtraso = (dataPagamento.getTimeInMillis() - dataVencimento.getTimeInMillis()) / 86400000l;
                //valorJuro = valor * ((taxaJuro / 30) / 100) * diasAtraso
                pagamento.setValorJuro(pagamento.getFinParcelaPagar().getValor().multiply(pagamento.getTaxaJuro().divide(BigDecimal.valueOf(30), RoundingMode.HALF_DOWN)).divide(BigDecimal.valueOf(100), RoundingMode.HALF_DOWN).multiply(BigDecimal.valueOf(diasAtraso)));
                valorJuro = pagamento.getValorJuro();
            }
        }
        pagamento.setValorJuro(valorJuro);

        if (pagamento.getTaxaMulta() != null) {
            pagamento.setValorMulta(pagamento.getFinParcelaPagar().getValor().multiply(pagamento.getTaxaMulta()).divide(BigDecimal.valueOf(100), RoundingMode.HALF_DOWN));
            valorMulta = pagamento.getValorMulta();
        } else {
            pagamento.setValorMulta(valorMulta);
        }

        if (pagamento.getTaxaDesconto() != null) {
            pagamento.setValorDesconto(pagamento.getFinParcelaPagar().getValor().multiply(pagamento.getTaxaDesconto()).divide(BigDecimal.valueOf(100), RoundingMode.HALF_DOWN));
            valorDesconto = pagamento.getValorDesconto();
        } else {
            pagamento.setValorDesconto(valorDesconto);
        }

        pagamento.setValorPago(pagamento.getFinParcelaPagar().getValor().add(valorJuro).add(valorMulta).subtract(valorDesconto));

        finParcelaPagamentoDetalhe.getForm1().pull();
    }

    public void efetuaPagamento() {
        if (finParcelaPagamentoDetalhe.getForm1().push()) {
            calculaTotalPago();
            FinParcelaPagamentoVO pagamento = (FinParcelaPagamentoVO) finParcelaPagamentoDetalhe.getForm1().getVOModel().getValueObject();
            pagamento.setFinChequeEmitido(null);
            if (pagamento.getFinTipoPagamento().getTipo().equals("02")) {
                FinSelecionaChequeGrid chequeGrid = new FinSelecionaChequeGrid(MDIFrame.getInstance(), true, true, pagamento.getValorPago());
                chequeGrid.setVisible(true);
                if (!chequeGrid.cancelado) {
                    pagamento.setFinChequeEmitido(chequeGrid.getChequeEmitido());
                } else {
                    JOptionPane.showMessageDialog(finParcelaPagamentoDetalhe, "É necessário informar um cheque para este tipo de pagamento.", "Informação do Sistema", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }
            Response res = ClientUtils.getData(acaoServidor, new Object[]{Constantes.INSERT, pagamento, finParcelaPagamentoDetalhe.getTipoBaixa()});
            if (res.isError()) {
                JOptionPane.showMessageDialog(finParcelaPagamentoDetalhe, "Ocorreu um erro ao gravar o pagamento.\n" + res.getErrorMessage(), "Erro do Sistema", JOptionPane.ERROR_MESSAGE);
                return;
            }
            finParcelaPagamentoDetalhe.getForm1().setMode(Consts.READONLY);
            finParcelaPagamentoDetalhe.getForm1().reload();
        }
    }

    public void excluiPagamento() {
        if (finParcelaPagamentoDetalhe.getGridControlPagamentoEfetuado().getSelectedRow() != -1){
            FinParcelaPagamentoVO pagamento = (FinParcelaPagamentoVO) finParcelaPagamentoDetalhe.getGridControlPagamentoEfetuado().getVOListTableModel().getObjectForRow(finParcelaPagamentoDetalhe.getGridControlPagamentoEfetuado().getSelectedRow());
            Response res = ClientUtils.getData(acaoServidor, new Object[]{Constantes.DELETE, pagamento});
            if (res.isError()) {
                JOptionPane.showMessageDialog(finParcelaPagamentoDetalhe, "Ocorreu um erro ao excluir o pagamento.\n" + res.getErrorMessage(), "Erro do Sistema", JOptionPane.ERROR_MESSAGE);
                return;
            }
            finParcelaPagamentoDetalhe.getForm1().setMode(Consts.READONLY);
            finParcelaPagamentoDetalhe.getForm1().reload();
            JOptionPane.showMessageDialog(finParcelaPagamentoDetalhe, "Pagamento excluído com sucesso!", "Informação do Sistema", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
