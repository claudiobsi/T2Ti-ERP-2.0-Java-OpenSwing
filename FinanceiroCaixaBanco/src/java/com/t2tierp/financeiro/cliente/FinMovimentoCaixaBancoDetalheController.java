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

import com.t2tierp.financeiro.java.FinFechamentoCaixaBancoVO;
import com.t2tierp.financeiro.java.ViewFinMovimentoCaixaBancoID;
import com.t2tierp.financeiro.java.ViewFinMovimentoCaixaBancoVO;
import com.t2tierp.padrao.java.Constantes;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import javax.swing.JOptionPane;
import org.openswing.swing.mdi.client.MDIFrame;
import org.openswing.swing.message.receive.java.Response;
import org.openswing.swing.message.send.java.GridParams;
import org.openswing.swing.table.client.GridController;
import org.openswing.swing.table.java.GridDataLocator;
import org.openswing.swing.util.client.ClientUtils;
import org.openswing.swing.util.java.Consts;

public class FinMovimentoCaixaBancoDetalheController extends GridController implements GridDataLocator {

    private String acaoServidor;
    private Date dataInicio;
    private Date dataFim;
    private FinMovimentoCaixaBancoDetalhe finMovimentoCaixaBancoDetalhe;
    private FinMovimentoCaixaBancoGrid finMovimentoCaixaBancoGrid;
    private ViewFinMovimentoCaixaBancoVO movimentoCaixaBanco;

    public FinMovimentoCaixaBancoDetalheController(FinMovimentoCaixaBancoGrid finMovimentoCaixaBancoGrid, ViewFinMovimentoCaixaBancoID movimentoCaixaBanco, Date dataInicio, Date dataFim) {
        acaoServidor = "finMovimentoCaixaBancoDetalheAction";
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;

        this.finMovimentoCaixaBancoGrid = finMovimentoCaixaBancoGrid;
        this.movimentoCaixaBanco = movimentoCaixaBanco.getViewFinMovimentoCaixaBanco();

        finMovimentoCaixaBancoDetalhe = new FinMovimentoCaixaBancoDetalhe(this);
        finMovimentoCaixaBancoDetalhe.setTextoContaCaixa(movimentoCaixaBanco.getViewFinMovimentoCaixaBanco().getNomeContaCaixa());
        SimpleDateFormat formatoPeriodo = new SimpleDateFormat("MM/yyyy");
        finMovimentoCaixaBancoDetalhe.setTextoPeriodo(formatoPeriodo.format(dataInicio));
        finMovimentoCaixaBancoDetalhe.setParentFrame(this.finMovimentoCaixaBancoGrid);
        this.finMovimentoCaixaBancoGrid.pushFrame(finMovimentoCaixaBancoDetalhe);
        MDIFrame.add(finMovimentoCaixaBancoDetalhe, true);

        finMovimentoCaixaBancoDetalhe.getGrid1().reloadData();
    }

    public Response loadData(int action, int startIndex, Map filteredColumns, ArrayList currentSortedColumns, ArrayList currentSortedVersusColumns, Class valueObjectType, Map otherGridParams) {
        //define os parametros da grid
        otherGridParams.put("acao", Constantes.LOAD);
        otherGridParams.put("idContaCaixa", movimentoCaixaBanco.getIdContaCaixa());
        otherGridParams.put("dataInicio", dataInicio);
        otherGridParams.put("dataFim", dataFim);

        return ClientUtils.getData(acaoServidor, new GridParams(action, startIndex, filteredColumns, currentSortedColumns, currentSortedVersusColumns, otherGridParams));
    }

    @Override
    public void loadDataCompleted(boolean error) {
        Calendar dataFechamento = Calendar.getInstance();
        dataFechamento.setTime(dataInicio);

        finMovimentoCaixaBancoDetalhe.getFechamentoController().setIdContaCaixa(movimentoCaixaBanco.getIdContaCaixa());
        finMovimentoCaixaBancoDetalhe.getFechamentoController().setMes(dataFechamento.get(Calendar.MONTH) + 1);
        finMovimentoCaixaBancoDetalhe.getFechamentoController().setAno(dataFechamento.get(Calendar.YEAR));

        finMovimentoCaixaBancoDetalhe.getFormFechamento().reload();
    }

    public void processaFechamento() {
        String[] opcoes = {"Sim", "Não"};
        int escolha = JOptionPane.showOptionDialog(finMovimentoCaixaBancoDetalhe,
                "Confirma o fechamento do movimento?",
                "Confirmação",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null, opcoes, opcoes[0]);
        if (escolha == JOptionPane.YES_OPTION) {
            FinFechamentoCaixaBancoVO fechamento = (FinFechamentoCaixaBancoVO) finMovimentoCaixaBancoDetalhe.getFormFechamento().getVOModel().getValueObject();
            if (fechamento.getId() != null) {
                finMovimentoCaixaBancoDetalhe.getFormFechamento().setMode(Consts.EDIT);
            } else {
                finMovimentoCaixaBancoDetalhe.getFormFechamento().setMode(Consts.INSERT);
                finMovimentoCaixaBancoDetalhe.getFormFechamento().getVOModel().setValueObject(fechamento);
            }
            finMovimentoCaixaBancoDetalhe.getFormFechamento().pull();
            if (finMovimentoCaixaBancoDetalhe.getFormFechamento().save()) {
                JOptionPane.showMessageDialog(finMovimentoCaixaBancoDetalhe, "Fechamento processado com sucesso!", "Aviso do Sistema", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }
}
