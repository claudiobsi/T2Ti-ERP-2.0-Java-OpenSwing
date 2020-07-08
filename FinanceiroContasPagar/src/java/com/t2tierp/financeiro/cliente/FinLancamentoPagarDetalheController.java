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
import com.t2tierp.financeiro.java.FinLancamentoPagarVO;
import com.t2tierp.financeiro.java.FinLctoPagarNtFinanceiraVO;
import com.t2tierp.financeiro.java.FinParcelaPagarVO;
import com.t2tierp.padrao.cliente.Container;
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

public class FinLancamentoPagarDetalheController extends FormController {

    private FinLancamentoPagarDetalhe finLancamentoPagarDetalhe = null;
    private String pk = null;
    private FinLancamentoPagarGrid finLancamentoPagarGrid = null;
    private String acaoServidor;

    public FinLancamentoPagarDetalheController(FinLancamentoPagarGrid finLancamentoPagarGrid, String pk) {
        this.finLancamentoPagarGrid = finLancamentoPagarGrid;
        this.pk = pk;
        this.acaoServidor = "finLancamentoPagarDetalheAction";
        finLancamentoPagarDetalhe = new FinLancamentoPagarDetalhe(this);
        finLancamentoPagarDetalhe.setParentFrame(this.finLancamentoPagarGrid);
        this.finLancamentoPagarGrid.pushFrame(finLancamentoPagarDetalhe);
        MDIFrame.add(finLancamentoPagarDetalhe, true);

        if (pk != null) {
            finLancamentoPagarDetalhe.getFormLancamentoPagar().setMode(Consts.READONLY);
            finLancamentoPagarDetalhe.getFormLancamentoPagar().reload();
        } else {
            finLancamentoPagarDetalhe.getFormLancamentoPagar().setMode(Consts.INSERT);
            finLancamentoPagarDetalhe.getGridControlParcelaPagar().reloadData();
            finLancamentoPagarDetalhe.getGridControlNaturezaFinanceira().reloadData();
        }
    }

    @Override
    public Response loadData(Class valueObjectClass) {
        return ClientUtils.getData(acaoServidor, new Object[]{Constantes.LOAD, pk});
    }

    @Override
    public void loadDataCompleted(boolean error) {
        finLancamentoPagarDetalhe.getParcelaPagarController().setPk(pk);
        finLancamentoPagarDetalhe.getGridControlParcelaPagar().reloadData();

        finLancamentoPagarDetalhe.getGridNaturezaFinanceiraController().setPk(pk);
        finLancamentoPagarDetalhe.getGridControlNaturezaFinanceira().reloadData();
    }

    @Override
    public Response insertRecord(ValueObject newPersistentObject) throws Exception {
        FinLancamentoPagarVO lancamentoPagar = (FinLancamentoPagarVO) newPersistentObject;
        List<FinParcelaPagarVO> parcelasPagar = finLancamentoPagarDetalhe.getGridControlParcelaPagar().getVOListTableModel().getDataVector();
        List<FinLctoPagarNtFinanceiraVO> naturezasFinanceiras = finLancamentoPagarDetalhe.getGridControlNaturezaFinanceira().getVOListTableModel().getDataVector();
        EmpresaVO empresa = (EmpresaVO) Container.getContainer().get("empresa");
        
        if (lancamentoPagar.getValorAPagar().compareTo(getTotalParcelaPagar(parcelasPagar)) != 0) {
            return new ErrorResponse("Os valores informados nas parcelas não batem com o valor a pagar.");
        }

        if (lancamentoPagar.getValorAPagar().compareTo(getTotalNaturezaFinanceira(naturezasFinanceiras)) != 0) {
            return new ErrorResponse("Os valores informados nas naturezas financeiras não batem com o valor a pagar.");
        }

        return ClientUtils.getData(acaoServidor, new Object[]{Constantes.INSERT, newPersistentObject, parcelasPagar, naturezasFinanceiras, empresa});
    }

    @Override
    public void afterInsertData() {
        finLancamentoPagarGrid.getGrid1().reloadData();
        FinLancamentoPagarVO lancamentoPagar = (FinLancamentoPagarVO) finLancamentoPagarDetalhe.getFormLancamentoPagar().getVOModel().getValueObject();
        if (JOptionPane.showConfirmDialog(finLancamentoPagarDetalhe, "Dados salvos com sucesso!\nDeseja salvar como pagamento fixo/recorrente?", "Informacao do Sistema", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            Response res = ClientUtils.getData(acaoServidor, new Object[]{99, lancamentoPagar});
            if (res.isError()) {
                JOptionPane.showMessageDialog(finLancamentoPagarDetalhe, "Erro ao salvar pagamento fixo.\n" + res.getErrorMessage(), "Erro do Sistema", JOptionPane.ERROR_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(finLancamentoPagarDetalhe, "Pagamento fixo cadastrado com sucesso!", "Informacao do Sistema", JOptionPane.INFORMATION_MESSAGE);
            }
        }
        pk = lancamentoPagar.getId().toString();
        finLancamentoPagarDetalhe.getFormLancamentoPagar().reload();
    }

    @Override
    public Response updateRecord(ValueObject oldPersistentObject, ValueObject persistentObject) throws Exception {
        FinLancamentoPagarVO lancamentoPagar = (FinLancamentoPagarVO) persistentObject;
        List<FinParcelaPagarVO> parcelasPagar = finLancamentoPagarDetalhe.getGridControlParcelaPagar().getVOListTableModel().getDataVector();
        List<FinLctoPagarNtFinanceiraVO> naturezasFinanceiras = finLancamentoPagarDetalhe.getGridControlNaturezaFinanceira().getVOListTableModel().getDataVector();
        EmpresaVO empresa = (EmpresaVO) Container.getContainer().get("empresa");
        
        if (lancamentoPagar.getValorAPagar().compareTo(getTotalParcelaPagar(parcelasPagar)) != 0) {
            return new ErrorResponse("Os valores informados nas parcelas não batem com o valor a pagar.");
        }

        if (lancamentoPagar.getValorAPagar().compareTo(getTotalNaturezaFinanceira(naturezasFinanceiras)) != 0) {
            return new ErrorResponse("Os valores informados nas naturezas financeiras não batem com o valor a pagar.");
        }
        return ClientUtils.getData(acaoServidor, new Object[]{Constantes.UPDATE, oldPersistentObject, persistentObject, parcelasPagar, naturezasFinanceiras, empresa});
    }

    @Override
    public void afterEditData() {
        finLancamentoPagarGrid.getGrid1().reloadData();
        JOptionPane.showMessageDialog(finLancamentoPagarDetalhe, "Dados alterados com sucesso!", "Informacao do Sistema", JOptionPane.INFORMATION_MESSAGE);
    }

    private BigDecimal getTotalParcelaPagar(List<FinParcelaPagarVO> parcelas) {
        BigDecimal total = BigDecimal.ZERO;
        for (int i = 0; i < parcelas.size(); i++) {
            total = total.add(parcelas.get(i).getValor());
        }
        return total;
    }

    private BigDecimal getTotalNaturezaFinanceira(List<FinLctoPagarNtFinanceiraVO> naturezasFinanceiras) {
        BigDecimal total = BigDecimal.ZERO;
        for (int i = 0; i < naturezasFinanceiras.size(); i++) {
            total = total.add(naturezasFinanceiras.get(i).getValor());
        }
        return total;
    }

/*    public void acionaGed() throws ClassNotFoundException, Exception {
        if (finLancamentoPagarDetalhe.getFormLancamentoPagar().push()) {
            FinLancamentoPagarVO lancamentoPagar = (FinLancamentoPagarVO) finLancamentoPagarDetalhe.getFormLancamentoPagar().getVOModel().getValueObject();

            Object classe = Class.forName("com.t2tierp.ged.cliente.GedDocumentoGridController").newInstance();
            Method metodo = classe.getClass().getDeclaredMethod("integracaoModulosErp", String.class, ArquivoVO.class);

            String nomeDocumentoGed = "LANCAMENTO_PAGAR_" + lancamentoPagar.getNumeroDocumento();
            lancamentoPagar.setImagemDocumento(nomeDocumentoGed);

            ArquivoVO arquivo = new ArquivoVO();

            metodo.invoke(classe, nomeDocumentoGed, arquivo);
        }
    }
*/    
}
