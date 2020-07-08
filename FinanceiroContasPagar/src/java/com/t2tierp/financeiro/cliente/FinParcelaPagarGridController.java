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

import com.t2tierp.cadastros.java.ContaCaixaVO;
import com.t2tierp.financeiro.java.FinLancamentoPagarVO;
import com.t2tierp.financeiro.java.FinParcelaPagarVO;
import com.t2tierp.padrao.java.Constantes;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JOptionPane;
import org.openswing.swing.client.GridControl;
import org.openswing.swing.message.receive.java.Response;
import org.openswing.swing.message.receive.java.VOListResponse;
import org.openswing.swing.message.send.java.GridParams;
import org.openswing.swing.table.client.GridController;
import org.openswing.swing.table.java.GridDataLocator;
import org.openswing.swing.util.client.ClientUtils;
import org.openswing.swing.util.java.Consts;

public class FinParcelaPagarGridController extends GridController implements GridDataLocator {

    private String acaoServidor;
    private FinLancamentoPagarDetalhe finLancamentoPagarDetalhe;
    private String pk;

    public FinParcelaPagarGridController(FinLancamentoPagarDetalhe finLancamentoPagarDetalhe) {
        acaoServidor = "finParcelaPagarGridAction";
        this.finLancamentoPagarDetalhe = finLancamentoPagarDetalhe;
    }

    public Response loadData(int action, int startIndex, Map filteredColumns, ArrayList currentSortedColumns, ArrayList currentSortedVersusColumns, Class valueObjectType, Map otherGridParams) {
        //define os parametros da grid
        otherGridParams.put("acao", Constantes.LOAD);
        otherGridParams.put("idLancamentoPagar", pk);

        return ClientUtils.getData(acaoServidor, new GridParams(action, startIndex, filteredColumns, currentSortedColumns, currentSortedVersusColumns, otherGridParams));
    }

    @Override
    public boolean beforeEditGrid(GridControl grid) {
        if (finLancamentoPagarDetalhe.getFormLancamentoPagar().getMode() == Consts.READONLY) {
            finLancamentoPagarDetalhe.getFormLancamentoPagar().setMode(Consts.EDIT);
        }
        return true;
    }

    @Override
    public Response updateRecords(int[] rowNumbers, ArrayList oldPersistentObjects, ArrayList persistentObjects) throws Exception {
        return new VOListResponse(persistentObjects, false, persistentObjects.size());
    }

    public void gerarParcelas() throws Exception {
        ContaCaixaVO contaCaixa = ((FinParcelaPagarVO) finLancamentoPagarDetalhe.getFormContaCaixa().getVOModel().getValueObject()).getContaCaixa();
        if (contaCaixa == null || contaCaixa.getId() == null) {
            throw new Exception("É necessário informar a conta caixa para previsão das parcelas.");
        }
        List<FinParcelaPagarVO> parcelasPagar = finLancamentoPagarDetalhe.getGridControlParcelaPagar().getVOListTableModel().getDataVector();
        if (!parcelasPagar.isEmpty()) {
            if (JOptionPane.showConfirmDialog(finLancamentoPagarDetalhe, "As parcelas que foram geradas anteriormente serão excluídas!\nDeseja continuar?", "Pergunta do Sistema", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                excluiParcelas(parcelasPagar);
            }
        }

        //gera as parcelas
        finLancamentoPagarDetalhe.getGridControlParcelaPagar().getVOListTableModel().clear();
        if (finLancamentoPagarDetalhe.getFormLancamentoPagar().push()) {
            FinLancamentoPagarVO lancamentoPagar = (FinLancamentoPagarVO) finLancamentoPagarDetalhe.getFormLancamentoPagar().getVOModel().getValueObject();
            FinParcelaPagarVO parcelaPagar;
            Date dataEmissão = new Date();
            Calendar primeiroVencimento = Calendar.getInstance();
            primeiroVencimento.setTime(lancamentoPagar.getPrimeiroVencimento());
            BigDecimal valorParcela = lancamentoPagar.getValorAPagar().divide(BigDecimal.valueOf(lancamentoPagar.getQuantidadeParcela()), RoundingMode.HALF_DOWN);
            BigDecimal somaParcelas = BigDecimal.ZERO;
            BigDecimal residuo = BigDecimal.ZERO;
            for (int i = 0; i < lancamentoPagar.getQuantidadeParcela(); i++) {
                parcelaPagar = new FinParcelaPagarVO();
                parcelaPagar.setContaCaixa(contaCaixa);
                parcelaPagar.setNumeroParcela(i + 1);
                parcelaPagar.setDataEmissao(dataEmissão);
                if (i > 0) {
                    primeiroVencimento.add(Calendar.DAY_OF_MONTH, lancamentoPagar.getIntervaloEntreParcelas());
                }
                parcelaPagar.setDataVencimento(primeiroVencimento.getTime());
                parcelaPagar.setSofreRetencao(lancamentoPagar.getFornecedor().getSofreRetencao());
                parcelaPagar.setValor(valorParcela);

                somaParcelas = somaParcelas.add(valorParcela);
                if (i == (lancamentoPagar.getQuantidadeParcela() - 1)) {
                    residuo = lancamentoPagar.getValorAPagar().subtract(somaParcelas);
                    valorParcela = valorParcela.add(residuo);
                    parcelaPagar.setValor(valorParcela);
                }
                finLancamentoPagarDetalhe.getGridControlParcelaPagar().getVOListTableModel().addObject(parcelaPagar);
            }
            if (finLancamentoPagarDetalhe.getFormLancamentoPagar().getMode() == Consts.READONLY) {
                finLancamentoPagarDetalhe.getFormLancamentoPagar().setMode(Consts.EDIT);
            }
        }
    }

    private void excluiParcelas(List<FinParcelaPagarVO> parcelasPagar) throws Exception {
        if (parcelasPagar.get(0).getId() != null) {
            //exclui as parcelas existentes
            Map otherGridParams = new HashMap();
            otherGridParams.put("acao", Constantes.DELETE);
            otherGridParams.put("persistentObjects", parcelasPagar);
            GridParams pars = new GridParams(0, 0, null, null, null, otherGridParams);
            Response res = ClientUtils.getData(acaoServidor, pars);
            if (res.isError()) {
                throw new Exception(res.getErrorMessage());
            }
        }
    }

    public void setPk(String pk) {
        this.pk = pk;
    }
}
