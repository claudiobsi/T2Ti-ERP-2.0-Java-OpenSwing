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
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JOptionPane;
import org.openswing.swing.mdi.client.MDIFrame;
import org.openswing.swing.message.receive.java.Response;
import org.openswing.swing.message.receive.java.ValueObject;
import org.openswing.swing.message.send.java.GridParams;
import org.openswing.swing.table.client.GridController;
import org.openswing.swing.table.java.GridDataLocator;
import org.openswing.swing.util.client.ClientUtils;

public class FinParcelaRecebimentoGridController extends GridController implements GridDataLocator {

    private FinParcelaRecebimentoGrid grid;
    private String acaoServidor;

    public FinParcelaRecebimentoGridController() {
        grid = new FinParcelaRecebimentoGrid(this);
        acaoServidor = "finParcelaRecebimentoGridAction";
        MDIFrame.add(grid);
    }

    public Response loadData(int action, int startIndex, Map filteredColumns, ArrayList currentSortedColumns, ArrayList currentSortedVersusColumns, Class valueObjectType, Map otherGridParams) {
        //define os parametros da grid
        otherGridParams.put("acao", Constantes.LOAD);
        return ClientUtils.getData(acaoServidor, new GridParams(action, startIndex, filteredColumns, currentSortedColumns, currentSortedVersusColumns, otherGridParams));
    }

    @Override
    public void doubleClick(int rowNumber, ValueObject persistentObject) {
        FinParcelaReceberVO finParcelaReceber = (FinParcelaReceberVO) persistentObject;
        new FinParcelaRecebimentoDetalheController(grid, finParcelaReceber);
    }

    public void recebimentoCompartilhado() {
        List<FinParcelaReceberVO> parcelas = grid.getGrid1().getVOListTableModel().getDataVector();
        List<FinParcelaReceberVO> parcelasSelecionadas = new ArrayList<FinParcelaReceberVO>();
        Date dataAtual = new Date();
        for (int i = 0; i < parcelas.size(); i++) {
            if (parcelas.get(i).isSelecionado()) {
                if (parcelas.get(i).getFinStatusParcela().getSituacao().equals("02")) {
                    JOptionPane.showMessageDialog(grid, "Foi selecionado parcela já quitada.\nRecebimento não realizado.", "Mensagem do Sistema", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if (parcelas.get(i).getDataVencimento().before(dataAtual)) {
                    JOptionPane.showMessageDialog(grid, "Foi selecionado parcela já vencida.\nRecebimento não realizado.", "Mensagem do Sistema", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                parcelasSelecionadas.add(parcelas.get(i));
            }
        }
        if (parcelasSelecionadas.isEmpty()) {
            if (grid.getGrid1().getSelectedRow() != -1) {
                doubleClick(0, grid.getGrid1().getVOListTableModel().getObjectForRow(grid.getGrid1().getSelectedRow()));
            }
        } else {
            if (parcelasSelecionadas.size() == 1) {
                doubleClick(0, parcelasSelecionadas.get(0));
            } else {
                FinSelecionaChequeRecebidoGrid chequeGrid = new FinSelecionaChequeRecebidoGrid(MDIFrame.getInstance(), true, false);
                chequeGrid.setVisible(true);
                if (!chequeGrid.cancelado) {
                    List<FinParcelaRecebimentoVO> listaRecebimentos = new ArrayList<FinParcelaRecebimentoVO>();
                    FinParcelaRecebimentoVO recebimento;
                    for (int i = 0; i < parcelasSelecionadas.size(); i++) {
                        recebimento = new FinParcelaRecebimentoVO();
                        recebimento.setFinParcelaReceber(parcelasSelecionadas.get(i));
                        recebimento.setFinChequeRecebido(chequeGrid.getChequeRecebido());
                        recebimento.setContaCaixa(chequeGrid.getChequeRecebido().getContaCaixa());
                        recebimento.setDataRecebimento(chequeGrid.getChequeRecebido().getBomPara());
                        recebimento.setHistorico(chequeGrid.getChequeRecebido().getHistorico());
                        recebimento.setValorRecebido(parcelasSelecionadas.get(i).getValor());

                        listaRecebimentos.add(recebimento);
                    }

                    EmpresaVO empresa = (EmpresaVO) Container.getContainer().get("empresa");
                    Map otherGridParams = new HashMap();
                    otherGridParams.put("acao", Constantes.INSERT);
                    otherGridParams.put("recebimentos", listaRecebimentos);
                    otherGridParams.put("empresa", empresa);
                    GridParams pars = new GridParams(0, 0, null, null, null, otherGridParams);
                    Response res = ClientUtils.getData(acaoServidor, pars);
                    if (res.isError()) {
                        JOptionPane.showMessageDialog(grid, "Ocorreu um erro ao gravar o recebimento.\n" + res.getErrorMessage(), "Erro do Sistema", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    JOptionPane.showMessageDialog(grid, "Recebimentos realizados com sucesso!", "Informação do Sistema", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(grid, "Recebimento não efetuado.\nCancelado pelo usuário.", "Informação do Sistema", JOptionPane.WARNING_MESSAGE);
                    return;
                }
            }
        }
    }

}
