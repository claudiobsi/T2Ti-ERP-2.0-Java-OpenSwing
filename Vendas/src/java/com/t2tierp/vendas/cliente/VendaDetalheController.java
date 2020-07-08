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
package com.t2tierp.vendas.cliente;

import com.t2tierp.padrao.java.Constantes;
import com.t2tierp.vendas.java.VendaCabecalhoVO;
import com.t2tierp.vendas.java.VendaDetalheVO;
import com.t2tierp.vendas.java.VendaOrcamentoDetalheVO;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JOptionPane;
import org.openswing.swing.form.client.Form;
import org.openswing.swing.form.client.FormController;
import org.openswing.swing.mdi.client.MDIFrame;
import org.openswing.swing.message.receive.java.ErrorResponse;
import org.openswing.swing.message.receive.java.Response;
import org.openswing.swing.message.receive.java.VOListResponse;
import org.openswing.swing.message.receive.java.ValueObject;
import org.openswing.swing.message.send.java.GridParams;
import org.openswing.swing.util.client.ClientUtils;
import org.openswing.swing.util.java.Consts;

public class VendaDetalheController extends FormController {

    private VendaDetalhe vendaDetalhe = null;
    private String pk = null;
    private VendaGrid vendaGrid = null;
    private String acaoServidor;

    public VendaDetalheController(VendaGrid vendaGrid, String pk) {
        this.vendaGrid = vendaGrid;
        this.pk = pk;
        this.acaoServidor = "vendaDetalheAction";
        vendaDetalhe = new VendaDetalhe(this);
        vendaDetalhe.setParentFrame(this.vendaGrid);
        this.vendaGrid.pushFrame(vendaDetalhe);
        MDIFrame.add(vendaDetalhe, true);

        if (pk != null) {
            vendaDetalhe.getForm1().setMode(Consts.READONLY);
            vendaDetalhe.getForm1().reload();
        } else {
            vendaDetalhe.getForm1().setMode(Consts.INSERT);
            vendaDetalhe.getGrid1().reloadData();
        }
    }

    @Override
    public Response loadData(Class valueObjectClass) {
        return ClientUtils.getData(acaoServidor, new Object[]{Constantes.LOAD, pk});
    }

    @Override
    public void loadDataCompleted(boolean error) {
        vendaDetalhe.getItensController().setPk(pk);
        vendaDetalhe.getGrid1().reloadData();
    }

    @Override
    public boolean beforeInsertData(Form form) {
        atualizaTotais();
        return true;
    }

    @Override
    public Response insertRecord(ValueObject newPersistentObject) throws Exception {
        List<VendaDetalheVO> itensVenda = vendaDetalhe.getGrid1().getVOListTableModel().getDataVector();

        if (itensVenda.isEmpty()) {
            return new ErrorResponse("Não há produtos na venda.");
        }

        ((VendaCabecalhoVO) newPersistentObject).setSituacao("D");

        return ClientUtils.getData(acaoServidor, new Object[]{Constantes.INSERT, newPersistentObject, itensVenda});
    }

    @Override
    public void afterInsertData() {
        vendaGrid.getGrid1().reloadData();
        JOptionPane.showMessageDialog(vendaDetalhe, "Dados salvos com sucesso!", "Informacao do Sistema", JOptionPane.INFORMATION_MESSAGE);
    }

    @Override
    public boolean beforeEditData(Form form) {
        atualizaTotais();
        return true;
    }

    @Override
    public Response updateRecord(ValueObject oldPersistentObject, ValueObject persistentObject) throws Exception {
        String situacao = ((VendaCabecalhoVO) persistentObject).getSituacao();
        if (!situacao.equals("D")) {
            String mensagem = "Este registro não pode ser alterado.\n";
            if (situacao.equals("P")) {
                mensagem += "Situação: Em Produção";
            }
            if (situacao.equals("X")) {
                mensagem += "Situação: Em Expedição";
            }
            if (situacao.equals("F")) {
                mensagem += "Situação: Faturado";
            }
            if (situacao.equals("E")) {
                mensagem += "Situação: Entregue";
            }
            return new ErrorResponse(mensagem);
        }
        List<VendaDetalheVO> itensVenda = vendaDetalhe.getGrid1().getVOListTableModel().getDataVector();

        if (itensVenda.isEmpty()) {
            return new ErrorResponse("Não há produtos no orçamento.");
        }

        return ClientUtils.getData(acaoServidor, new Object[]{Constantes.UPDATE, oldPersistentObject, persistentObject, itensVenda});
    }

    @Override
    public void afterEditData() {
        vendaGrid.getGrid1().reloadData();
        JOptionPane.showMessageDialog(vendaDetalhe, "Dados alterados com sucesso!", "Informacao do Sistema", JOptionPane.INFORMATION_MESSAGE);
    }

    @Override
    public boolean validateControl(String attributeName, Object oldValue, Object newValue) {
        if (attributeName.equals("vendaOrcamentoCabecalho.id")) {
            carregaItensOrcamento((String.valueOf(newValue)));
        }
        return super.validateControl(attributeName, oldValue, newValue);
    }

    public void atualizaTotais() {
        VendaCabecalhoVO vendaCabecalho = (VendaCabecalhoVO) vendaDetalhe.getForm1().getVOModel().getValueObject();
        if (vendaCabecalho.getValorSubtotal() != null) {
            if (vendaCabecalho.getTaxaDesconto() != null) {
                vendaCabecalho.setValorDesconto(vendaCabecalho.getValorSubtotal().multiply(vendaCabecalho.getTaxaDesconto().divide(BigDecimal.valueOf(100), RoundingMode.HALF_DOWN)));
                vendaCabecalho.setValorTotal(vendaCabecalho.getValorSubtotal().subtract(vendaCabecalho.getValorDesconto()));
            }
            if (vendaCabecalho.getValorFrete() != null) {
                if (vendaCabecalho.getValorTotal() == null) {
                    vendaCabecalho.setValorTotal(vendaCabecalho.getValorSubtotal());
                }
                vendaCabecalho.setValorTotal(vendaCabecalho.getValorTotal().add(vendaCabecalho.getValorFrete()));
            }
        }
        vendaDetalhe.getForm1().pull();
    }

    private void carregaItensOrcamento(String idOrcamento) {
        try {
            Map otherGridParams = new HashMap();
            otherGridParams.put("acao", Constantes.LOAD);
            otherGridParams.put("idVendaOrcamentoCabecalho", idOrcamento);

            GridParams pars = new GridParams();
            pars.setOtherGridParams(otherGridParams);

            Response res = ClientUtils.getData("vendaOrcamentoDetalheGridAction", pars);
            if (res.isError()) {
                throw new Exception(res.getErrorMessage());
            }
            
            List<VendaOrcamentoDetalheVO> orcamentoDetalhe = ((VOListResponse) res).getRows();
            VendaDetalheVO itemVenda;
            vendaDetalhe.getGrid1().clearData();
            for (int i = 0; i < orcamentoDetalhe.size(); i++) {
                itemVenda = new VendaDetalheVO();
                itemVenda.setProduto(orcamentoDetalhe.get(i).getProduto());
                itemVenda.setQuantidade(orcamentoDetalhe.get(i).getQuantidade());
                itemVenda.setTaxaDesconto(orcamentoDetalhe.get(i).getTaxaDesconto());
                itemVenda.setValorDesconto(orcamentoDetalhe.get(i).getValorDesconto());
                itemVenda.setValorSubtotal(orcamentoDetalhe.get(i).getValorSubtotal());
                itemVenda.setValorTotal(orcamentoDetalhe.get(i).getValorTotal());
                itemVenda.setValorUnitario(orcamentoDetalhe.get(i).getValorUnitario());

                vendaDetalhe.getGrid1().getVOListTableModel().addObject(itemVenda);
            }
            vendaDetalhe.getItensController().calculaTotais();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(vendaDetalhe, "Ocorreu um erro ao carregar os itens do orçamento\n" + e.getMessage(), "Erro do Sistema", JOptionPane.ERROR_MESSAGE);
        }
    }
}
