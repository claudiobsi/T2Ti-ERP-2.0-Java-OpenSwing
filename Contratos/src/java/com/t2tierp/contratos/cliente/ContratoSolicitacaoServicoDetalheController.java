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
package com.t2tierp.contratos.cliente;

import com.t2tierp.contratos.java.ContratoSolicitacaoServicoVO;
import com.t2tierp.padrao.java.Constantes;
import java.util.Date;
import javax.swing.JOptionPane;
import org.openswing.swing.form.client.FormController;
import org.openswing.swing.mdi.client.MDIFrame;
import org.openswing.swing.message.receive.java.ErrorResponse;
import org.openswing.swing.message.receive.java.Response;
import org.openswing.swing.message.receive.java.ValueObject;
import org.openswing.swing.util.client.ClientUtils;
import org.openswing.swing.util.java.Consts;

public class ContratoSolicitacaoServicoDetalheController extends FormController {

    private ContratoSolicitacaoServicoDetalhe contratoSolicitacaoServicoDetalhe = null;
    private String pk = null;
    private ContratoSolicitacaoServicoGrid contratoSolicitacaoServicoGrid = null;
    private String acaoServidor;

    public ContratoSolicitacaoServicoDetalheController(ContratoSolicitacaoServicoGrid contratoSolicitacaoServicoGrid, String pk) {
        this.contratoSolicitacaoServicoGrid = contratoSolicitacaoServicoGrid;
        this.pk = pk;
        this.acaoServidor = "contratoSolicitacaoServicoDetalheAction";
        contratoSolicitacaoServicoDetalhe = new ContratoSolicitacaoServicoDetalhe(this);
        contratoSolicitacaoServicoDetalhe.setParentFrame(this.contratoSolicitacaoServicoGrid);
        this.contratoSolicitacaoServicoGrid.pushFrame(contratoSolicitacaoServicoDetalhe);
        MDIFrame.add(contratoSolicitacaoServicoDetalhe, true);

        if (pk != null) {
            contratoSolicitacaoServicoDetalhe.getForm1().setMode(Consts.READONLY);
            contratoSolicitacaoServicoDetalhe.getForm1().reload();
        } else {
            contratoSolicitacaoServicoDetalhe.getForm1().setMode(Consts.INSERT);
            valoresPadrao();
        }
    }

    @Override
    public Response loadData(Class valueObjectClass) {
        return ClientUtils.getData(acaoServidor, new Object[]{Constantes.LOAD, pk});
    }

    @Override
    public void loadDataCompleted(boolean error) {
        ContratoSolicitacaoServicoVO solicitacao = (ContratoSolicitacaoServicoVO) contratoSolicitacaoServicoDetalhe.getForm1().getVOModel().getValueObject();
        if (solicitacao.getCliente().getId() == null){
            contratoSolicitacaoServicoDetalhe.setClienteFornecedor("FORNECEDOR");
        }
    }

    @Override
    public Response insertRecord(ValueObject newPersistentObject) throws Exception {
        ContratoSolicitacaoServicoVO solicitacao = (ContratoSolicitacaoServicoVO) newPersistentObject;

        if (contratoSolicitacaoServicoDetalhe.getClienteFornecedor().equals("CLIENTE")) {
            solicitacao.setFornecedor(null);
            if (solicitacao.getCliente().getId() == null) {
                return new ErrorResponse("Selecione um cliente!");
            }
        } else {
            solicitacao.setCliente(null);
            if (solicitacao.getFornecedor().getId() == null) {
                return new ErrorResponse("Selecione um fornecedor!");
            }
        }

        return ClientUtils.getData(acaoServidor, new Object[]{Constantes.INSERT, newPersistentObject});
    }

    @Override
    public void afterInsertData() {
        contratoSolicitacaoServicoGrid.getGrid1().reloadData();
        JOptionPane.showMessageDialog(contratoSolicitacaoServicoDetalhe, "Dados salvos com sucesso!", "Informacao do Sistema", JOptionPane.INFORMATION_MESSAGE);
    }

    @Override
    public Response updateRecord(ValueObject oldPersistentObject, ValueObject persistentObject) throws Exception {
        ContratoSolicitacaoServicoVO solicitacao = (ContratoSolicitacaoServicoVO) persistentObject;

        if (contratoSolicitacaoServicoDetalhe.getClienteFornecedor().equals("CLIENTE")) {
            solicitacao.setFornecedor(null);
            if (solicitacao.getCliente().getId() == null) {
                return new ErrorResponse("Selecione um cliente!");
            }
        } else {
            solicitacao.setCliente(null);
            if (solicitacao.getFornecedor().getId() == null) {
                return new ErrorResponse("Selecione um fornecedor!");
            }
        }

        return ClientUtils.getData(acaoServidor, new Object[]{Constantes.UPDATE, oldPersistentObject, persistentObject});
    }

    @Override
    public void afterEditData() {
        contratoSolicitacaoServicoGrid.getGrid1().reloadData();
        JOptionPane.showMessageDialog(contratoSolicitacaoServicoDetalhe, "Dados alterados com sucesso!", "Informacao do Sistema", JOptionPane.INFORMATION_MESSAGE);
    }

    private void valoresPadrao(){
        ContratoSolicitacaoServicoVO solicitacao = (ContratoSolicitacaoServicoVO) contratoSolicitacaoServicoDetalhe.getForm1().getVOModel().getValueObject();
        solicitacao.setDataSolicitacao(new Date());
        solicitacao.setUrgente("N");
        contratoSolicitacaoServicoDetalhe.getForm1().pull();
    }
}
