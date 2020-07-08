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

import com.t2tierp.cadastros.java.EmpresaVO;
import com.t2tierp.financeiro.java.FinParcelaReceberVO;
import com.t2tierp.financeiro.java.FinParcelaRecebimentoVO;
import com.t2tierp.padrao.cliente.Container;
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

public class FinParcelaRecebimentoDetalheController extends FormController {

    private FinParcelaRecebimentoDetalhe finParcelaRecebimentoDetalhe = null;
    private String pk = null;
    private FinParcelaRecebimentoGrid finParcelaRecebimentoGrid = null;
    private String acaoServidor;
    private FinParcelaReceberVO finParcelaReceber;

    public FinParcelaRecebimentoDetalheController(FinParcelaRecebimentoGrid finParcelaRecebimentoGrid, FinParcelaReceberVO finParcelaReceber) {
        this.finParcelaRecebimentoGrid = finParcelaRecebimentoGrid;
        this.pk = pk;
        this.acaoServidor = "finParcelaRecebimentoDetalheAction";
        finParcelaRecebimentoDetalhe = new FinParcelaRecebimentoDetalhe(this);
        finParcelaRecebimentoDetalhe.setParentFrame(this.finParcelaRecebimentoGrid);
        this.finParcelaRecebimentoGrid.pushFrame(finParcelaRecebimentoDetalhe);
        MDIFrame.add(finParcelaRecebimentoDetalhe, true);

        this.finParcelaReceber = finParcelaReceber;
        finParcelaRecebimentoDetalhe.getForm1().reload();
    }

    @Override
    public Response loadData(Class valueObjectClass) {
        FinParcelaRecebimentoVO parcelaRecebimento = new FinParcelaRecebimentoVO();
        parcelaRecebimento.setFinParcelaReceber(finParcelaReceber);
        parcelaRecebimento.setContaCaixa(finParcelaReceber.getContaCaixa());
        parcelaRecebimento.setValorRecebido(finParcelaReceber.getValor());

        return new VOResponse(parcelaRecebimento);
    }

    @Override
    public void loadDataCompleted(boolean error) {
        finParcelaRecebimentoDetalhe.getForm1().setMode(Consts.EDIT);

        finParcelaRecebimentoDetalhe.getRecebimentoEfetuadoGridController().setParcelaReceber(finParcelaReceber);
        finParcelaRecebimentoDetalhe.getGridControlRecebimentoEfetuado().reloadData();
    }

    @Override
    public Response updateRecord(ValueObject oldPersistentObject, ValueObject persistentObject) throws Exception {
        return ClientUtils.getData(acaoServidor, new Object[]{Constantes.UPDATE, oldPersistentObject, persistentObject});
    }

    @Override
    public void afterEditData() {
        finParcelaRecebimentoGrid.getGrid1().reloadData();
    }

    public void calculaTotalRecebido() {
        finParcelaRecebimentoDetalhe.getForm1().pull();
        FinParcelaRecebimentoVO recebimento = (FinParcelaRecebimentoVO) finParcelaRecebimentoDetalhe.getForm1().getVOModel().getValueObject();
        BigDecimal valorJuro = BigDecimal.ZERO;
        BigDecimal valorMulta = BigDecimal.ZERO;
        BigDecimal valorDesconto = BigDecimal.ZERO;
        if (recebimento.getTaxaJuro() != null && recebimento.getDataRecebimento() != null) {
            Calendar dataRecebimento = Calendar.getInstance();
            dataRecebimento.setTime(recebimento.getDataRecebimento());
            Calendar dataVencimento = Calendar.getInstance();
            dataVencimento.setTime(recebimento.getFinParcelaReceber().getDataVencimento());
            if (dataVencimento.before(dataRecebimento)) {
                long diasAtraso = (dataRecebimento.getTimeInMillis() - dataVencimento.getTimeInMillis()) / 86400000l;
                //valorJuro = valor * ((taxaJuro / 30) / 100) * diasAtraso
                recebimento.setValorJuro(recebimento.getFinParcelaReceber().getValor().multiply(recebimento.getTaxaJuro().divide(BigDecimal.valueOf(30), RoundingMode.HALF_DOWN)).divide(BigDecimal.valueOf(100), RoundingMode.HALF_DOWN).multiply(BigDecimal.valueOf(diasAtraso)));
                valorJuro = recebimento.getValorJuro();
            }
        }
        recebimento.setValorJuro(valorJuro);

        if (recebimento.getTaxaMulta() != null) {
            recebimento.setValorMulta(recebimento.getFinParcelaReceber().getValor().multiply(recebimento.getTaxaMulta()).divide(BigDecimal.valueOf(100), RoundingMode.HALF_DOWN));
            valorMulta = recebimento.getValorMulta();
        } else {
            recebimento.setValorMulta(valorMulta);
        }

        if (recebimento.getTaxaDesconto() != null) {
            recebimento.setValorDesconto(recebimento.getFinParcelaReceber().getValor().multiply(recebimento.getTaxaDesconto()).divide(BigDecimal.valueOf(100), RoundingMode.HALF_DOWN));
            valorDesconto = recebimento.getValorDesconto();
        } else {
            recebimento.setValorDesconto(valorDesconto);
        }

        recebimento.setValorRecebido(recebimento.getFinParcelaReceber().getValor().add(valorJuro).add(valorMulta).subtract(valorDesconto));

        finParcelaRecebimentoDetalhe.getForm1().pull();
    }

    public void efetuaRecebimento() {
        if (finParcelaRecebimentoDetalhe.getForm1().push()) {
            calculaTotalRecebido();
            FinParcelaRecebimentoVO recebimento = (FinParcelaRecebimentoVO) finParcelaRecebimentoDetalhe.getForm1().getVOModel().getValueObject();
            recebimento.setFinChequeRecebido(null);
            if (recebimento.getFinTipoRecebimento().getTipo().equals("02")) {
                FinSelecionaChequeRecebidoGrid chequeGrid = new FinSelecionaChequeRecebidoGrid(MDIFrame.getInstance(), true, true);
                chequeGrid.setVisible(true);
                if (!chequeGrid.cancelado) {
                    recebimento.setFinChequeRecebido(chequeGrid.getChequeRecebido());
                } else {
                    JOptionPane.showMessageDialog(finParcelaRecebimentoDetalhe, "É necessário informar um cheque para este tipo de recebimento.", "Informação do Sistema", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }
            EmpresaVO empresa = (EmpresaVO) Container.getContainer().get("empresa");
            Response res = ClientUtils.getData(acaoServidor, new Object[]{Constantes.INSERT, recebimento, finParcelaRecebimentoDetalhe.getTipoBaixa(), empresa});
            if (res.isError()) {
                JOptionPane.showMessageDialog(finParcelaRecebimentoDetalhe, "Ocorreu um erro ao gravar o recebimento.\n" + res.getErrorMessage(), "Erro do Sistema", JOptionPane.ERROR_MESSAGE);
                return;
            }
            finParcelaRecebimentoDetalhe.getForm1().setMode(Consts.READONLY);
            finParcelaRecebimentoDetalhe.getForm1().reload();
        }
    }

    public void excluiRecebimento() {
        if (finParcelaRecebimentoDetalhe.getGridControlRecebimentoEfetuado().getSelectedRow() != -1){
            FinParcelaRecebimentoVO recebimento = (FinParcelaRecebimentoVO) finParcelaRecebimentoDetalhe.getGridControlRecebimentoEfetuado().getVOListTableModel().getObjectForRow(finParcelaRecebimentoDetalhe.getGridControlRecebimentoEfetuado().getSelectedRow());
            EmpresaVO empresa = (EmpresaVO) Container.getContainer().get("empresa");
            Response res = ClientUtils.getData(acaoServidor, new Object[]{Constantes.DELETE, recebimento, empresa});
            if (res.isError()) {
                JOptionPane.showMessageDialog(finParcelaRecebimentoDetalhe, "Ocorreu um erro ao excluir o recebimento.\n" + res.getErrorMessage(), "Erro do Sistema", JOptionPane.ERROR_MESSAGE);
                return;
            }
            finParcelaRecebimentoDetalhe.getForm1().setMode(Consts.READONLY);
            finParcelaRecebimentoDetalhe.getForm1().reload();
            JOptionPane.showMessageDialog(finParcelaRecebimentoDetalhe, "Recebimento excluído com sucesso!", "Informação do Sistema", JOptionPane.INFORMATION_MESSAGE);
        }
    }

}
