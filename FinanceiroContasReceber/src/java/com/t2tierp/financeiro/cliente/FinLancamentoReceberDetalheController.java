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
import com.t2tierp.financeiro.java.FinLancamentoReceberVO;
import com.t2tierp.financeiro.java.FinLctoReceberNtFinanceiraVO;
import com.t2tierp.financeiro.java.FinParcelaReceberVO;
import com.t2tierp.padrao.cliente.Container;
import com.t2tierp.padrao.java.Constantes;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import javax.swing.JOptionPane;
import org.openswing.swing.form.client.FormController;
import org.openswing.swing.mdi.client.MDIFrame;
import org.openswing.swing.message.receive.java.ErrorResponse;
import org.openswing.swing.message.receive.java.Response;
import org.openswing.swing.message.receive.java.ValueObject;
import org.openswing.swing.util.client.ClientUtils;
import org.openswing.swing.util.java.Consts;

public class FinLancamentoReceberDetalheController extends FormController {

    private FinLancamentoReceberDetalhe finLancamentoReceberDetalhe = null;
    private String pk = null;
    private FinLancamentoReceberGrid finLancamentoReceberGrid = null;
    private String acaoServidor;

    public FinLancamentoReceberDetalheController(FinLancamentoReceberGrid finLancamentoReceberGrid, String pk) {
        this.finLancamentoReceberGrid = finLancamentoReceberGrid;
        this.pk = pk;
        this.acaoServidor = "finLancamentoReceberDetalheAction";
        finLancamentoReceberDetalhe = new FinLancamentoReceberDetalhe(this);
        finLancamentoReceberDetalhe.setParentFrame(this.finLancamentoReceberGrid);
        this.finLancamentoReceberGrid.pushFrame(finLancamentoReceberDetalhe);
        MDIFrame.add(finLancamentoReceberDetalhe, true);

        if (pk != null) {
            finLancamentoReceberDetalhe.getFormLancamentoReceber().setMode(Consts.READONLY);
            finLancamentoReceberDetalhe.getFormLancamentoReceber().reload();
        } else {
            finLancamentoReceberDetalhe.getFormLancamentoReceber().setMode(Consts.INSERT);
            finLancamentoReceberDetalhe.getGridControlParcelaReceber().reloadData();
            finLancamentoReceberDetalhe.getGridControlNaturezaFinanceira().reloadData();
        }
    }

    @Override
    public Response loadData(Class valueObjectClass) {
        return ClientUtils.getData(acaoServidor, new Object[]{Constantes.LOAD, pk});
    }

    @Override
    public void loadDataCompleted(boolean error) {
        finLancamentoReceberDetalhe.getParcelaPagarController().setPk(pk);
        finLancamentoReceberDetalhe.getGridControlParcelaReceber().reloadData();

        finLancamentoReceberDetalhe.getGridNaturezaFinanceiraController().setPk(pk);
        finLancamentoReceberDetalhe.getGridControlNaturezaFinanceira().reloadData();
    }

    @Override
    public Response insertRecord(ValueObject newPersistentObject) throws Exception {
        FinLancamentoReceberVO lancamentoReceber = (FinLancamentoReceberVO) newPersistentObject;
        List<FinParcelaReceberVO> parcelasReceber = finLancamentoReceberDetalhe.getGridControlParcelaReceber().getVOListTableModel().getDataVector();
        List<FinLctoReceberNtFinanceiraVO> naturezasFinanceiras = finLancamentoReceberDetalhe.getGridControlNaturezaFinanceira().getVOListTableModel().getDataVector();
        EmpresaVO empresa = (EmpresaVO) Container.getContainer().get("empresa");
        
        if (lancamentoReceber.getValorAReceber().compareTo(getTotalParcelaReceber(parcelasReceber)) != 0) {
            return new ErrorResponse("Os valores informados nas parcelas não batem com o valor a receber.");
        }

        if (lancamentoReceber.getValorAReceber().compareTo(getTotalNaturezaFinanceira(naturezasFinanceiras)) != 0) {
            return new ErrorResponse("Os valores informados nas naturezas financeiras não batem com o valor a receber.");
        }

        return ClientUtils.getData(acaoServidor, new Object[]{Constantes.INSERT, newPersistentObject, parcelasReceber, naturezasFinanceiras, empresa});
    }

    @Override
    public void afterInsertData() {
        finLancamentoReceberGrid.getGrid1().reloadData();
        FinLancamentoReceberVO lancamentoReceber = (FinLancamentoReceberVO) finLancamentoReceberDetalhe.getFormLancamentoReceber().getVOModel().getValueObject();
        pk = lancamentoReceber.getId().toString();
        finLancamentoReceberDetalhe.getFormLancamentoReceber().reload();
        JOptionPane.showMessageDialog(finLancamentoReceberDetalhe, "Dados salvos com sucesso!", "Informacao do Sistema", JOptionPane.INFORMATION_MESSAGE);
    }

    @Override
    public Response updateRecord(ValueObject oldPersistentObject, ValueObject persistentObject) throws Exception {
        FinLancamentoReceberVO lancamentoReceber = (FinLancamentoReceberVO) persistentObject;
        List<FinParcelaReceberVO> parcelasReceber = finLancamentoReceberDetalhe.getGridControlParcelaReceber().getVOListTableModel().getDataVector();
        List<FinLctoReceberNtFinanceiraVO> naturezasFinanceiras = finLancamentoReceberDetalhe.getGridControlNaturezaFinanceira().getVOListTableModel().getDataVector();
        EmpresaVO empresa = (EmpresaVO) Container.getContainer().get("empresa");
        
        if (lancamentoReceber.getValorAReceber().compareTo(getTotalParcelaReceber(parcelasReceber)) != 0) {
            return new ErrorResponse("Os valores informados nas parcelas não batem com o valor a receber.");
        }

        if (lancamentoReceber.getValorAReceber().compareTo(getTotalNaturezaFinanceira(naturezasFinanceiras)) != 0) {
            return new ErrorResponse("Os valores informados nas naturezas financeiras não batem com o valor a receber.");
        }

        return ClientUtils.getData(acaoServidor, new Object[]{Constantes.UPDATE, oldPersistentObject, persistentObject, parcelasReceber, naturezasFinanceiras, empresa});
    }

    @Override
    public void afterEditData() {
        finLancamentoReceberGrid.getGrid1().reloadData();
        JOptionPane.showMessageDialog(finLancamentoReceberDetalhe, "Dados alterados com sucesso!", "Informacao do Sistema", JOptionPane.INFORMATION_MESSAGE);
    }

    private BigDecimal getTotalParcelaReceber(List<FinParcelaReceberVO> parcelas) {
        BigDecimal total = BigDecimal.ZERO;
        for (int i = 0; i < parcelas.size(); i++) {
            total = total.add(parcelas.get(i).getValor());
        }
        return total;
    }

    private BigDecimal getTotalNaturezaFinanceira(List<FinLctoReceberNtFinanceiraVO> naturezasFinanceiras) {
        BigDecimal total = BigDecimal.ZERO;
        for (int i = 0; i < naturezasFinanceiras.size(); i++) {
            total = total.add(naturezasFinanceiras.get(i).getValor());
        }
        return total;
    }

    public void calculaValorComissao() {
        FinLancamentoReceberVO lancamentoReceber = (FinLancamentoReceberVO) finLancamentoReceberDetalhe.getFormLancamentoReceber().getVOModel().getValueObject();
        if (lancamentoReceber.getValorAReceber() != null && lancamentoReceber.getTaxaComissao() != null) {
            lancamentoReceber.setValorComissao(lancamentoReceber.getValorAReceber().multiply(lancamentoReceber.getTaxaComissao()).divide(BigDecimal.valueOf(100), RoundingMode.HALF_DOWN));
            finLancamentoReceberDetalhe.getFormLancamentoReceber().pull();
        }
    }

}
