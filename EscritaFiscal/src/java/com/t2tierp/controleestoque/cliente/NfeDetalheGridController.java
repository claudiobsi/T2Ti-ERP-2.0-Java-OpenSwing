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
package com.t2tierp.controleestoque.cliente;

import com.t2tierp.cadastros.cliente.ProdutoDetalhe;
import com.t2tierp.cadastros.cliente.ProdutoDetalheController;
import com.t2tierp.cadastros.java.ProdutoVO;
import com.t2tierp.nfe.java.NfeDetalheVO;
import com.t2tierp.padrao.java.Constantes;
import java.util.ArrayList;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameListener;
import org.openswing.swing.client.GridControl;
import org.openswing.swing.mdi.client.MDIFrame;
import org.openswing.swing.message.receive.java.Response;
import org.openswing.swing.message.receive.java.VOListResponse;
import org.openswing.swing.message.receive.java.ValueObject;
import org.openswing.swing.message.send.java.GridParams;
import org.openswing.swing.table.client.GridController;
import org.openswing.swing.table.java.GridDataLocator;
import org.openswing.swing.util.client.ClientUtils;
import org.openswing.swing.util.java.Consts;

public class NfeDetalheGridController extends GridController implements GridDataLocator {

    private EntradaNotaDetalhe entradaNotaDetalhe;
    private String acaoServidor;
    private String pk;

    public NfeDetalheGridController(EntradaNotaDetalhe entradaNotaDetalhe) {
        acaoServidor = "estoqueNfeDetalheGridAction";
        this.entradaNotaDetalhe = entradaNotaDetalhe;
    }

    public Response loadData(int action, int startIndex, Map filteredColumns, ArrayList currentSortedColumns, ArrayList currentSortedVersusColumns, Class valueObjectType, Map otherGridParams) {
        //define os parametros da grid
        otherGridParams.put("acao", Constantes.LOAD);
        otherGridParams.put("idNfeCabecalho", pk);

        return ClientUtils.getData(acaoServidor, new GridParams(action, startIndex, filteredColumns, currentSortedColumns, currentSortedVersusColumns, otherGridParams));
    }

    @Override
    public boolean beforeInsertGrid(GridControl grid) {
        if (entradaNotaDetalhe.getFormDadosNfe().getMode() == Consts.READONLY) {
            entradaNotaDetalhe.getFormDadosNfe().setMode(Consts.EDIT);
        }
        return true;
    }

    @Override
    public Response insertRecords(int[] rowNumbers, ArrayList newValueObjects) throws Exception {
        return new VOListResponse(newValueObjects, false, newValueObjects.size());
    }

    @Override
    public boolean beforeEditGrid(GridControl grid) {
        if (entradaNotaDetalhe.getFormDadosNfe().getMode() == Consts.READONLY) {
            entradaNotaDetalhe.getFormDadosNfe().setMode(Consts.EDIT);
        }
        return true;
    }

    @Override
    public Response updateRecords(int[] rowNumbers, ArrayList oldPersistentObjects, ArrayList persistentObjects) throws Exception {
        return new VOListResponse(persistentObjects, false, persistentObjects.size());
    }

    @Override
    public boolean beforeDeleteGrid(GridControl grid) {
        if (entradaNotaDetalhe.getFormDadosNfe().getMode() == Consts.READONLY) {
            entradaNotaDetalhe.getFormDadosNfe().setMode(Consts.EDIT);
        }
        return true;
    }

    @Override
    public Response deleteRecords(ArrayList persistentObjects) throws Exception {
        return new VOListResponse(persistentObjects, false, persistentObjects.size());
    }

    public void setPk(String pk) {
        this.pk = pk;
    }

    @Override
    public void doubleClick(int rowNumber, ValueObject persistentObject) {
        NfeDetalheVO detalhe = (NfeDetalheVO) persistentObject;

        NfeDetalheImpostoGrid impostoGrid = new NfeDetalheImpostoGrid(MDIFrame.getInstance(), true);

        impostoGrid.getIcmsController().setIcms(detalhe.getNfeDetalheImpostoIcms());
        impostoGrid.getFormIcms().reload();

        impostoGrid.getPisController().setPis(detalhe.getNfeDetalheImpostoPis());
        impostoGrid.getFormPis().reload();

        impostoGrid.getCofinsController().setCofins(detalhe.getNfeDetalheImpostoCofins());
        impostoGrid.getFormCofins().reload();

        impostoGrid.getIpiController().setIpi(detalhe.getNfeDetalheImpostoIpi());
        impostoGrid.getFormIpi().reload();

        impostoGrid.getIiController().setIi(detalhe.getNfeDetalheImpostoIi());
        impostoGrid.getFormIi().reload();

        impostoGrid.getIssqnController().setIssqn(detalhe.getNfeDetalheImpostoIssqn());
        impostoGrid.getFormIssqn().reload();

        impostoGrid.getCombustivelController().setCombustivel(detalhe.getCombustivel());
        impostoGrid.getFormCombustivel().reload();

        impostoGrid.getVeiculoController().setVeiculo(detalhe.getVeiculo());
        impostoGrid.getFormVeiculo().reload();

        impostoGrid.getMedicamentoController().setMedicamento(detalhe.getListaMedicamento());
        impostoGrid.getGridControlMedicamento().reloadData();

        impostoGrid.getArmamentoController().setArmamento(detalhe.getListaArmamento());
        impostoGrid.getGridControlArmamento().reloadData();

        impostoGrid.getDeclaracaoController().setDeclaracao(detalhe.getListaDeclaracaoImportacao());
        impostoGrid.getGridControlDeclaracaoImportacao().reloadData();

        impostoGrid.setVisible(true);
    }

    public void cadastrarProduto() throws Exception {
        if (entradaNotaDetalhe.getGridControlProduto().getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(entradaNotaDetalhe, "Selecione um item da lista.", "Informação do Sistema", JOptionPane.INFORMATION_MESSAGE);
        } else {
            NfeDetalheVO nfeDetalhe = (NfeDetalheVO) entradaNotaDetalhe.getGridControlProduto().getVOListTableModel().getObjectForRow(entradaNotaDetalhe.getGridControlProduto().getSelectedRow());
            if (nfeDetalhe.isProdutoCadastrado()) {
                JOptionPane.showMessageDialog(entradaNotaDetalhe, "Este produto já está cadastrado.", "Informação do Sistema", JOptionPane.INFORMATION_MESSAGE);
            } else {
                ProdutoVO produto = new ProdutoVO();
                produto.setGtin(nfeDetalhe.getGtin());
                produto.setNcm(nfeDetalhe.getNcm());
                produto.setNome(nfeDetalhe.getNomeProduto());
                produto.setDescricaoPdv(nfeDetalhe.getNomeProduto());
                produto.setDescricao(nfeDetalhe.getNomeProduto());
                produto.setValorCompra(nfeDetalhe.getValorBrutoProduto());

                ProdutoDetalhe p = new ProdutoDetalheController(produto).getProdutoDetalhe();
                p.addInternalFrameListener(new InternalFrameListener() {

                    public void internalFrameOpened(InternalFrameEvent e) {
                        //throw new UnsupportedOperationException("Not supported yet.");
                    }

                    public void internalFrameClosing(InternalFrameEvent e) {
                        //throw new UnsupportedOperationException("Not supported yet.");
                    }

                    public void internalFrameClosed(InternalFrameEvent e) {
                        try {
                            entradaNotaDetalhe.getEntradaNotaController().verificaProdutoNaoCadastrado();
                        } catch (Exception ex) {
                        }
                    }

                    public void internalFrameIconified(InternalFrameEvent e) {
                        //throw new UnsupportedOperationException("Not supported yet.");
                    }

                    public void internalFrameDeiconified(InternalFrameEvent e) {
                        //throw new UnsupportedOperationException("Not supported yet.");
                    }

                    public void internalFrameActivated(InternalFrameEvent e) {
                        //throw new UnsupportedOperationException("Not supported yet.");
                    }

                    public void internalFrameDeactivated(InternalFrameEvent e) {
                        //throw new UnsupportedOperationException("Not supported yet.");
                    }
                });
            }
        }
    }
}
