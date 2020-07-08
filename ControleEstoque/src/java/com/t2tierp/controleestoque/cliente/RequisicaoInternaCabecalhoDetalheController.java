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

import com.t2tierp.controleestoque.java.RequisicaoInternaCabecalhoVO;
import com.t2tierp.controleestoque.java.RequisicaoInternaDetalheVO;
import com.t2tierp.padrao.java.Constantes;
import java.util.List;
import javax.swing.JOptionPane;
import org.openswing.swing.form.client.FormController;
import org.openswing.swing.mdi.client.MDIFrame;
import org.openswing.swing.message.receive.java.ErrorResponse;
import org.openswing.swing.message.receive.java.Response;
import org.openswing.swing.message.receive.java.ValueObject;
import org.openswing.swing.util.client.ClientUtils;
import org.openswing.swing.util.java.Consts;

public class RequisicaoInternaCabecalhoDetalheController extends FormController {

    private RequisicaoInternaCabecalhoDetalhe requisicaoInternaCabecalhoDetalhe = null;
    private String pk = null;
    private RequisicaoInternaCabecalhoGrid requisicaoInternaCabecalhoGrid = null;
    private String acaoServidor;

    public RequisicaoInternaCabecalhoDetalheController(RequisicaoInternaCabecalhoGrid requisicaoInternaCabecalhoGrid, String pk) {
        this.requisicaoInternaCabecalhoGrid = requisicaoInternaCabecalhoGrid;
        this.pk = pk;
        this.acaoServidor = "requisicaoInternaCabecalhoDetalheAction";
        requisicaoInternaCabecalhoDetalhe = new RequisicaoInternaCabecalhoDetalhe(this);
        requisicaoInternaCabecalhoDetalhe.setParentFrame(this.requisicaoInternaCabecalhoGrid);
        this.requisicaoInternaCabecalhoGrid.pushFrame(requisicaoInternaCabecalhoDetalhe);
        MDIFrame.add(requisicaoInternaCabecalhoDetalhe, true);

        if (pk != null) {
            requisicaoInternaCabecalhoDetalhe.getForm1().setMode(Consts.READONLY);
            requisicaoInternaCabecalhoDetalhe.getForm1().reload();
        } else {
            requisicaoInternaCabecalhoDetalhe.getForm1().setMode(Consts.INSERT);
            requisicaoInternaCabecalhoDetalhe.getGridControl1().reloadData();
        }
    }

    @Override
    public Response loadData(Class valueObjectClass) {
        return ClientUtils.getData(acaoServidor, new Object[]{Constantes.LOAD, pk});
    }

    @Override
    public void loadDataCompleted(boolean error) {
        requisicaoInternaCabecalhoDetalhe.getItensController().setPk(pk);
        requisicaoInternaCabecalhoDetalhe.getGridControl1().reloadData();
    }

    @Override
    public Response insertRecord(ValueObject newPersistentObject) throws Exception {
        List<RequisicaoInternaDetalheVO> listaItens = requisicaoInternaCabecalhoDetalhe.getGridControl1().getVOListTableModel().getDataVector();
        if (listaItens.isEmpty()) {
            return new ErrorResponse("Não há itens na requisição.");
        }

        return ClientUtils.getData(acaoServidor, new Object[]{Constantes.INSERT, newPersistentObject, listaItens});
    }

    @Override
    public void afterInsertData() {
        requisicaoInternaCabecalhoGrid.getGrid1().reloadData();
        JOptionPane.showMessageDialog(requisicaoInternaCabecalhoDetalhe, "Dados salvos com sucesso!", "Informacao do Sistema", JOptionPane.INFORMATION_MESSAGE);
        requisicaoInternaCabecalhoDetalhe.dispose();
    }

    @Override
    public Response updateRecord(ValueObject oldPersistentObject, ValueObject persistentObject) throws Exception {
        List<RequisicaoInternaDetalheVO> listaItens = requisicaoInternaCabecalhoDetalhe.getGridControl1().getVOListTableModel().getDataVector();
        if (listaItens.isEmpty()) {
            return new ErrorResponse("Não há itens na requisição.");
        }
        if (!((RequisicaoInternaCabecalhoVO) persistentObject).getSituacao().equals("A")) {
            return new ErrorResponse("Requisição já deferida ou indeferida. Alteração não permitida.");
        }

        return ClientUtils.getData(acaoServidor, new Object[]{Constantes.UPDATE, oldPersistentObject, persistentObject, listaItens});
    }

    @Override
    public void afterEditData() {
        requisicaoInternaCabecalhoGrid.getGrid1().reloadData();
        JOptionPane.showMessageDialog(requisicaoInternaCabecalhoDetalhe, "Dados alterados com sucesso!", "Informacao do Sistema", JOptionPane.INFORMATION_MESSAGE);
        requisicaoInternaCabecalhoDetalhe.dispose();
    }

    public void deferirRequisicao() {
        if (requisicaoInternaCabecalhoDetalhe.getForm1().getMode() != Consts.READONLY) {
            JOptionPane.showMessageDialog(requisicaoInternaCabecalhoDetalhe, "Requisição ainda não foi salva.", "Informação do Sistema", JOptionPane.WARNING_MESSAGE);
        } else {
            RequisicaoInternaCabecalhoVO requisicao = (RequisicaoInternaCabecalhoVO) requisicaoInternaCabecalhoDetalhe.getForm1().getVOModel().getValueObject();
            if (requisicao.getSituacao().equals("D")) {
                JOptionPane.showMessageDialog(requisicaoInternaCabecalhoDetalhe, "Esta requisição já foi deferida.\nNenhuma alteração realizada.", "Informação do Sistema", JOptionPane.WARNING_MESSAGE);
            } else if (requisicao.getSituacao().equals("I")) {
                JOptionPane.showMessageDialog(requisicaoInternaCabecalhoDetalhe, "Esta requisição já foi indeferida.\nNenhuma alteração realizada.", "Informação do Sistema", JOptionPane.WARNING_MESSAGE);
            } else {
                List<RequisicaoInternaDetalheVO> listaItens = requisicaoInternaCabecalhoDetalhe.getGridControl1().getVOListTableModel().getDataVector();
                requisicao.setSituacao("D");
                Response res = ClientUtils.getData(acaoServidor, new Object[]{Constantes.UPDATE, null, requisicao, listaItens});
                if (res.isError()) {
                    JOptionPane.showMessageDialog(requisicaoInternaCabecalhoDetalhe, "Ocorreu um erro ao deferir a requisiçao.\n" + res.getErrorMessage(), "Erro do Sistema", JOptionPane.ERROR_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(requisicaoInternaCabecalhoDetalhe, "Requisição deferida com sucesso.", "Informação do Sistema", JOptionPane.INFORMATION_MESSAGE);
                    requisicaoInternaCabecalhoGrid.getGrid1().reloadData();
                    requisicaoInternaCabecalhoDetalhe.dispose();
                }
            }
        }
    }

    public void indeferirRequisicao() {
        if (requisicaoInternaCabecalhoDetalhe.getForm1().getMode() != Consts.READONLY) {
            JOptionPane.showMessageDialog(requisicaoInternaCabecalhoDetalhe, "Requisição ainda não foi salva.", "Informação do Sistema", JOptionPane.WARNING_MESSAGE);
        } else {
            RequisicaoInternaCabecalhoVO requisicao = (RequisicaoInternaCabecalhoVO) requisicaoInternaCabecalhoDetalhe.getForm1().getVOModel().getValueObject();
            if (requisicao.getSituacao().equals("D")) {
                JOptionPane.showMessageDialog(requisicaoInternaCabecalhoDetalhe, "Esta requisição já foi deferida.\nNenhuma alteração realizada.", "Informação do Sistema", JOptionPane.WARNING_MESSAGE);
            } else if (requisicao.getSituacao().equals("I")) {
                JOptionPane.showMessageDialog(requisicaoInternaCabecalhoDetalhe, "Esta requisição já foi indeferida.\nNenhuma alteração realizada.", "Informação do Sistema", JOptionPane.WARNING_MESSAGE);
            } else {
                List<RequisicaoInternaDetalheVO> listaItens = requisicaoInternaCabecalhoDetalhe.getGridControl1().getVOListTableModel().getDataVector();
                requisicao.setSituacao("I");
                Response res = ClientUtils.getData(acaoServidor, new Object[]{Constantes.UPDATE, null, requisicao, listaItens});
                if (res.isError()) {
                    JOptionPane.showMessageDialog(requisicaoInternaCabecalhoDetalhe, "Ocorreu um erro ao indeferir a requisiçao.\n" + res.getErrorMessage(), "Erro do Sistema", JOptionPane.ERROR_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(requisicaoInternaCabecalhoDetalhe, "Requisição indeferida com sucesso.", "Informação do Sistema", JOptionPane.INFORMATION_MESSAGE);
                    requisicaoInternaCabecalhoGrid.getGrid1().reloadData();
                    requisicaoInternaCabecalhoDetalhe.dispose();
                }
            }
        }
    }
}
