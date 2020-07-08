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

import com.t2tierp.padrao.cliente.LookupDataLocatorGenerico;
import com.t2tierp.vendas.java.VendaCabecalhoVO;
import java.awt.Dimension;
import java.text.ParseException;
import javax.swing.text.MaskFormatter;
import org.openswing.swing.client.GridControl;
import org.openswing.swing.lookup.client.LookupController;
import org.openswing.swing.lookup.client.LookupServerDataLocator;
import org.openswing.swing.mdi.client.InternalFrame;

public class VendaDetalhe extends InternalFrame {

    private VendaDetalheGridController itensController;
    private VendaDetalheController controller;

    private LookupController vendedorController = new LookupController();
    private LookupController clienteController = new LookupController();
    private LookupController transportadoraController = new LookupController();
    private LookupController condicaoPagamentoController = new LookupController();
    private LookupController produtoController = new LookupController();
    private LookupController orcamentoController = new LookupController();
    private LookupController tipoNotaFiscalController = new LookupController();

    public VendaDetalhe(VendaDetalheController controller) {
        initComponents();

        this.controller = controller;

        form1.setFormController(controller);

        itensController = new VendaDetalheGridController(this);
        gridControl1.setController(itensController);
        gridControl1.setGridDataLocator(itensController);
        try {
            //formotação do campo hora sáida
            MaskFormatter mask = new MaskFormatter("##:##:##");
            mask.setValidCharacters("0123456789");
            formattedTextControl1.setFormatter(mask);
        } catch (ParseException ex) {
            ex.printStackTrace();
        }

        /*
         * Configurações do lookup do vendedor
         */
        vendedorController.setLookupValueObjectClassName("com.t2tierp.cadastros.java.VendedorVO");
        vendedorController.addLookup2ParentLink("id", "vendedor.id");
        vendedorController.addLookup2ParentLink("colaborador.pessoa.nome", "vendedor.colaborador.pessoa.nome");
        vendedorController.addLookup2ParentLink("comissao", "taxaComissao");
        vendedorController.setHeaderColumnName("id", "ID");
        vendedorController.setHeaderColumnName("colaborador.pessoa.nome", "Nome");
        vendedorController.setFrameTitle("Importa Vendedor");

        vendedorController.setVisibleStatusPanel(true);
        vendedorController.setVisibleColumn("id", true);
        vendedorController.setVisibleColumn("colaborador.pessoa.nome", true);
        vendedorController.setFramePreferedSize(new Dimension(500, 400));

        vendedorController.setLookupDataLocator(new LookupDataLocatorGenerico(vendedorController.getLookupValueObjectClassName()));
        codLookupVendedor.setLookupController(vendedorController);
        
        /*
         * Configurações do lookup do cliente
         */
        clienteController.setLookupValueObjectClassName("com.t2tierp.cadastros.java.ClienteVO");
        clienteController.addLookup2ParentLink("id", "cliente.id");
        clienteController.addLookup2ParentLink("pessoa.nome", "cliente.pessoa.nome");
        clienteController.setHeaderColumnName("id", "ID");
        clienteController.setHeaderColumnName("pessoa.nome", "Nome");
        clienteController.setFrameTitle("Importa Cliente");

        clienteController.setVisibleStatusPanel(true);
        clienteController.setVisibleColumn("id", true);
        clienteController.setVisibleColumn("pessoa.nome", true);
        clienteController.setFramePreferedSize(new Dimension(500, 400));

        clienteController.setLookupDataLocator(new LookupDataLocatorGenerico(clienteController.getLookupValueObjectClassName()));
        codLookupCliente.setLookupController(clienteController);

        /*
         * Configurações do lookup da transportadora
         */
        transportadoraController.setLookupValueObjectClassName("com.t2tierp.cadastros.java.TransportadoraVO");
        transportadoraController.addLookup2ParentLink("id", "transportadora.id");
        transportadoraController.addLookup2ParentLink("pessoa.nome", "transportadora.pessoa.nome");
        transportadoraController.setHeaderColumnName("id", "ID");
        transportadoraController.setHeaderColumnName("pessoa.nome", "Nome");
        transportadoraController.setFrameTitle("Importa Transportadora");

        transportadoraController.setVisibleStatusPanel(true);
        transportadoraController.setVisibleColumn("id", true);
        transportadoraController.setVisibleColumn("pessoa.nome", true);
        transportadoraController.setFramePreferedSize(new Dimension(500, 400));

        transportadoraController.setLookupDataLocator(new LookupDataLocatorGenerico(transportadoraController.getLookupValueObjectClassName()));
        codLookupTrasportadora.setLookupController(transportadoraController);

        /*
         * Configurações do lookup das condições de pagamento
         */
        condicaoPagamentoController.setLookupValueObjectClassName("com.t2tierp.vendas.java.CondicoesPagamentoVO");
        condicaoPagamentoController.addLookup2ParentLink("id", "condicoesPagamento.id");
        condicaoPagamentoController.addLookup2ParentLink("nome", "condicoesPagamento.nome");
        condicaoPagamentoController.setHeaderColumnName("id", "ID");
        condicaoPagamentoController.setHeaderColumnName("nome", "Nome");
        condicaoPagamentoController.setFrameTitle("Importa Condições de Pagamento");

        condicaoPagamentoController.setVisibleStatusPanel(true);
        condicaoPagamentoController.setVisibleColumn("id", true);
        condicaoPagamentoController.setVisibleColumn("nome", true);
        condicaoPagamentoController.setFramePreferedSize(new Dimension(500, 400));

        condicaoPagamentoController.setLookupDataLocator(new LookupDataLocatorGenerico(condicaoPagamentoController.getLookupValueObjectClassName()));
        codLookupCondicaoPagamento.setLookupController(condicaoPagamentoController);

        /*
         * Configurações do lookup do produto
         */
        produtoController.setLookupValueObjectClassName("com.t2tierp.cadastros.java.ProdutoVO");
        produtoController.addLookup2ParentLink("id", "produto.id");
        produtoController.addLookup2ParentLink("nome", "produto.nome");
        produtoController.addLookup2ParentLink("valorVenda", "valorUnitario");
        produtoController.setHeaderColumnName("id", "ID");
        produtoController.setHeaderColumnName("nome", "Nome");
        produtoController.setHeaderColumnName("valorVenda", "Valor");
        produtoController.setFrameTitle("Importa Produto");

        produtoController.setVisibleStatusPanel(true);
        produtoController.setVisibleColumn("id", true);
        produtoController.setVisibleColumn("nome", true);
        produtoController.setVisibleColumn("valorVenda", true);
        produtoController.setFramePreferedSize(new Dimension(500, 400));

        produtoController.setLookupDataLocator(new LookupDataLocatorGenerico(produtoController.getLookupValueObjectClassName()));
        codLookupColumn1.setLookupController(produtoController);

        /*
         * Configurações do lookup do orcamento
         */
        orcamentoController.setLookupValueObjectClassName("com.t2tierp.vendas.java.VendaOrcamentoCabecalhoVO");
        orcamentoController.addLookup2ParentLink("id", "vendaOrcamentoCabecalho.id");
        orcamentoController.addLookup2ParentLink("codigo", "vendaOrcamentoCabecalho.codigo");
        orcamentoController.addLookup2ParentLink("condicoesPagamento", "condicoesPagamento");
        orcamentoController.addLookup2ParentLink("transportadora", "transportadora");
        orcamentoController.addLookup2ParentLink("cliente", "cliente");
        orcamentoController.addLookup2ParentLink("vendedor", "vendedor");
        orcamentoController.addLookup2ParentLink("tipoFrete", "tipoFrete");
        orcamentoController.addLookup2ParentLink("valorSubtotal", "valorSubtotal");
        orcamentoController.addLookup2ParentLink("valorFrete", "valorFrete");
        orcamentoController.addLookup2ParentLink("taxaComissao", "taxaComissao");
        orcamentoController.addLookup2ParentLink("valorComissao", "valorComissao");
        orcamentoController.addLookup2ParentLink("taxaDesconto", "taxaDesconto");
        orcamentoController.addLookup2ParentLink("valorDesconto", "valorDesconto");
        orcamentoController.addLookup2ParentLink("valorTotal", "valorTotal");
        orcamentoController.addLookup2ParentLink("observacao", "observacao");
        orcamentoController.setHeaderColumnName("id", "ID");
        orcamentoController.setHeaderColumnName("codigo", "Código");
        orcamentoController.setHeaderColumnName("cliente.pessoa.nome", "Cliente");
        orcamentoController.setFrameTitle("Importa Orçamento");

        orcamentoController.setVisibleStatusPanel(true);
        orcamentoController.setVisibleColumn("id", true);
        orcamentoController.setVisibleColumn("codigo", true);
        orcamentoController.setVisibleColumn("cliente.pessoa.nome", true);
        orcamentoController.setFramePreferedSize(new Dimension(500, 400));

        orcamentoController.setLookupDataLocator(new LookupDataLocatorGenerico(orcamentoController.getLookupValueObjectClassName()));
        codLookupOrcamento.setLookupController(orcamentoController);

        /*
         * Configurações do lookup do tipo de nota fiscal
         */
        tipoNotaFiscalController.setLookupValueObjectClassName("com.t2tierp.vendas.java.NotaFiscalTipoVO");
        tipoNotaFiscalController.addLookup2ParentLink("id", "notaFiscalTipo.id");
        tipoNotaFiscalController.addLookup2ParentLink("nome", "notaFiscalTipo.nome");
        tipoNotaFiscalController.setHeaderColumnName("id", "ID");
        tipoNotaFiscalController.setHeaderColumnName("nome", "Nome");
        tipoNotaFiscalController.setFrameTitle("Importa Tipo Nota Fiscal");

        tipoNotaFiscalController.setVisibleStatusPanel(true);
        tipoNotaFiscalController.setVisibleColumn("id", true);
        tipoNotaFiscalController.setVisibleColumn("nome", true);
        tipoNotaFiscalController.setFramePreferedSize(new Dimension(500, 400));

        tipoNotaFiscalController.setLookupDataLocator(new LookupDataLocatorGenerico(tipoNotaFiscalController.getLookupValueObjectClassName()));
        codLookupTipoNotaFiscal.setLookupController(tipoNotaFiscalController);

    }

    public org.openswing.swing.form.client.Form getForm1() {
        return form1;
    }

    public VendaDetalheController getFormController() {
        return controller;
    }

    public GridControl getGrid1() {
        return gridControl1;
    }

    public VendaDetalheGridController getItensController() {
        return itensController;
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel6 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        gridControl1 = new org.openswing.swing.client.GridControl();
        codLookupColumn1 = new org.openswing.swing.table.columns.client.CodLookupColumn();
        decimalColumn1 = new org.openswing.swing.table.columns.client.DecimalColumn();
        decimalColumn2 = new org.openswing.swing.table.columns.client.DecimalColumn();
        decimalColumn3 = new org.openswing.swing.table.columns.client.DecimalColumn();
        decimalColumn6 = new org.openswing.swing.table.columns.client.DecimalColumn();
        decimalColumn4 = new org.openswing.swing.table.columns.client.DecimalColumn();
        decimalColumn5 = new org.openswing.swing.table.columns.client.DecimalColumn();
        jPanel5 = new javax.swing.JPanel();
        insertButton1 = new org.openswing.swing.client.InsertButton();
        editButton2 = new org.openswing.swing.client.EditButton();
        deleteButton1 = new org.openswing.swing.client.DeleteButton();
        saveButton2 = new org.openswing.swing.client.SaveButton();
        reloadButton2 = new org.openswing.swing.client.ReloadButton();
        navigatorBar1 = new org.openswing.swing.client.NavigatorBar();
        jPanel2 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        editButton1 = new org.openswing.swing.client.EditButton();
        reloadButton1 = new org.openswing.swing.client.ReloadButton();
        saveButton1 = new org.openswing.swing.client.SaveButton();
        form1 = new org.openswing.swing.form.client.Form();
        labelControl1 = new org.openswing.swing.client.LabelControl();
        labelControl2 = new org.openswing.swing.client.LabelControl();
        labelControl3 = new org.openswing.swing.client.LabelControl();
        labelControl4 = new org.openswing.swing.client.LabelControl();
        labelControl5 = new org.openswing.swing.client.LabelControl();
        labelControl6 = new org.openswing.swing.client.LabelControl();
        labelControl7 = new org.openswing.swing.client.LabelControl();
        labelControl8 = new org.openswing.swing.client.LabelControl();
        textAreaControl1 = new org.openswing.swing.client.TextAreaControl();
        numericControl1 = new org.openswing.swing.client.NumericControl();
        numericControl2 = new org.openswing.swing.client.NumericControl();
        numericControl3 = new org.openswing.swing.client.NumericControl();
        numericControl4 = new org.openswing.swing.client.NumericControl();
        numericControl5 = new org.openswing.swing.client.NumericControl();
        numericControl6 = new org.openswing.swing.client.NumericControl();
        codLookupCondicaoPagamento = new org.openswing.swing.client.CodLookupControl();
        textControl2 = new org.openswing.swing.client.TextControl();
        codLookupTrasportadora = new org.openswing.swing.client.CodLookupControl();
        textControl3 = new org.openswing.swing.client.TextControl();
        labelControl9 = new org.openswing.swing.client.LabelControl();
        codLookupVendedor = new org.openswing.swing.client.CodLookupControl();
        labelControl19 = new org.openswing.swing.client.LabelControl();
        textControl7 = new org.openswing.swing.client.TextControl();
        codLookupCliente = new org.openswing.swing.client.CodLookupControl();
        labelControl20 = new org.openswing.swing.client.LabelControl();
        textControl8 = new org.openswing.swing.client.TextControl();
        numericControl13 = new org.openswing.swing.client.NumericControl();
        labelControl21 = new org.openswing.swing.client.LabelControl();
        comboBoxControl1 = new org.openswing.swing.client.ComboBoxControl();
        labelControl23 = new org.openswing.swing.client.LabelControl();
        comboBoxControl2 = new org.openswing.swing.client.ComboBoxControl();
        labelControl24 = new org.openswing.swing.client.LabelControl();
        labelControl25 = new org.openswing.swing.client.LabelControl();
        labelControl26 = new org.openswing.swing.client.LabelControl();
        labelControl27 = new org.openswing.swing.client.LabelControl();
        dateControl2 = new org.openswing.swing.client.DateControl();
        dateControl3 = new org.openswing.swing.client.DateControl();
        jLabel1 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox();
        labelControl28 = new org.openswing.swing.client.LabelControl();
        codLookupTipoNotaFiscal = new org.openswing.swing.client.CodLookupControl();
        textControl9 = new org.openswing.swing.client.TextControl();
        labelControl29 = new org.openswing.swing.client.LabelControl();
        codLookupOrcamento = new org.openswing.swing.client.CodLookupControl();
        textControl10 = new org.openswing.swing.client.TextControl();
        labelControl30 = new org.openswing.swing.client.LabelControl();
        numericControl14 = new org.openswing.swing.client.NumericControl();
        formattedTextControl1 = new org.openswing.swing.client.FormattedTextControl();
        labelControl31 = new org.openswing.swing.client.LabelControl();
        labelControl32 = new org.openswing.swing.client.LabelControl();
        textControl4 = new org.openswing.swing.client.TextControl();
        textControl5 = new org.openswing.swing.client.TextControl();

        setTitle("T2Ti ERP - Vendas e Faturamento");
        setPreferredSize(new java.awt.Dimension(700, 400));
        getContentPane().setLayout(new java.awt.CardLayout());

        jPanel6.setLayout(new java.awt.GridBagLayout());

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Itens da Venda"));
        jPanel3.setLayout(new java.awt.GridBagLayout());

        gridControl1.setAutoLoadData(false);
        gridControl1.setDeleteButton(deleteButton1);
        gridControl1.setEditButton(editButton2);
        gridControl1.setFunctionId("vendaDetalhe");
        gridControl1.setInsertButton(insertButton1);
        gridControl1.setInsertRowsOnTop(false);
        gridControl1.setNavBar(navigatorBar1);
        gridControl1.setPreferredSize(new java.awt.Dimension(500, 300));
        gridControl1.setReloadButton(reloadButton2);
        gridControl1.setSaveButton(saveButton2);
        gridControl1.setValueObjectClassName("com.t2tierp.vendas.java.VendaDetalheVO");
        gridControl1.getColumnContainer().setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        codLookupColumn1.setColumnName("produto.nome");
        codLookupColumn1.setEditableOnEdit(true);
        codLookupColumn1.setEditableOnInsert(true);
        codLookupColumn1.setHeaderColumnName("Produto");
        codLookupColumn1.setHeaderFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        codLookupColumn1.setPreferredWidth(200);
        gridControl1.getColumnContainer().add(codLookupColumn1);

        decimalColumn1.setColumnName("quantidade");
        decimalColumn1.setDecimals(3);
        decimalColumn1.setEditableOnEdit(true);
        decimalColumn1.setEditableOnInsert(true);
        decimalColumn1.setHeaderColumnName("Quantidade");
        decimalColumn1.setHeaderFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        decimalColumn1.setMaxCharacters(3);
        decimalColumn1.setPreferredWidth(80);
        gridControl1.getColumnContainer().add(decimalColumn1);

        decimalColumn2.setColumnName("valorUnitario");
        decimalColumn2.setColumnRequired(false);
        decimalColumn2.setDecimals(2);
        decimalColumn2.setGrouping(true);
        decimalColumn2.setHeaderColumnName("Valor Unitário");
        decimalColumn2.setHeaderFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        gridControl1.getColumnContainer().add(decimalColumn2);

        decimalColumn3.setColumnName("valorSubtotal");
        decimalColumn3.setColumnRequired(false);
        decimalColumn3.setDecimals(2);
        decimalColumn3.setGrouping(true);
        decimalColumn3.setHeaderColumnName("SubTotal");
        decimalColumn3.setHeaderFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        gridControl1.getColumnContainer().add(decimalColumn3);

        decimalColumn6.setColumnName("taxaDesconto");
        decimalColumn6.setColumnRequired(false);
        decimalColumn6.setDecimals(2);
        decimalColumn6.setEditableOnEdit(true);
        decimalColumn6.setEditableOnInsert(true);
        decimalColumn6.setGrouping(true);
        decimalColumn6.setHeaderColumnName("Taxa Desconto");
        decimalColumn6.setHeaderFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        gridControl1.getColumnContainer().add(decimalColumn6);

        decimalColumn4.setColumnName("valorDesconto");
        decimalColumn4.setColumnRequired(false);
        decimalColumn4.setDecimals(2);
        decimalColumn4.setGrouping(true);
        decimalColumn4.setHeaderColumnName("Desconto");
        decimalColumn4.setHeaderFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        gridControl1.getColumnContainer().add(decimalColumn4);

        decimalColumn5.setColumnName("valorTotal");
        decimalColumn5.setColumnRequired(false);
        decimalColumn5.setDecimals(2);
        decimalColumn5.setGrouping(true);
        decimalColumn5.setHeaderColumnName("Valor Total");
        decimalColumn5.setHeaderFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        gridControl1.getColumnContainer().add(decimalColumn5);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel3.add(gridControl1, gridBagConstraints);

        jPanel5.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));
        jPanel5.add(insertButton1);
        jPanel5.add(editButton2);
        jPanel5.add(deleteButton1);
        jPanel5.add(saveButton2);
        jPanel5.add(reloadButton2);
        jPanel5.add(navigatorBar1);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        jPanel3.add(jPanel5, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weighty = 1.0;
        jPanel6.add(jPanel3, gridBagConstraints);

        jPanel2.setLayout(new java.awt.GridBagLayout());

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Venda"));
        jPanel4.setLayout(new java.awt.GridBagLayout());

        jPanel1.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));
        jPanel1.add(editButton1);
        jPanel1.add(reloadButton1);
        jPanel1.add(saveButton1);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        jPanel4.add(jPanel1, gridBagConstraints);

        form1.setVOClassName("com.t2tierp.vendas.java.VendaCabecalhoVO");
        form1.setEditButton(editButton1);
        form1.setFunctionId("vendaCabecalho");
        form1.setReloadButton(reloadButton1);
        form1.setSaveButton(saveButton1);
        form1.setLayout(new java.awt.GridBagLayout());

        labelControl1.setLabel("Transportadora:");
        labelControl1.setToolTipText("");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        form1.add(labelControl1, gridBagConstraints);

        labelControl2.setLabel("Valor Subtotal:");
        labelControl2.setToolTipText("");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 12;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        form1.add(labelControl2, gridBagConstraints);

        labelControl3.setLabel("Valor Frete:");
        labelControl3.setToolTipText("");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 12;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        form1.add(labelControl3, gridBagConstraints);

        labelControl4.setLabel("Taxa Comissão:");
        labelControl4.setToolTipText("");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 12;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        form1.add(labelControl4, gridBagConstraints);

        labelControl5.setLabel("Valor Comissão:");
        labelControl5.setToolTipText("");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 12;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        form1.add(labelControl5, gridBagConstraints);

        labelControl6.setLabel("Taxa Desconto:");
        labelControl6.setToolTipText("");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 12;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        form1.add(labelControl6, gridBagConstraints);

        labelControl7.setLabel("Valor Total:");
        labelControl7.setToolTipText("");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 12;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        form1.add(labelControl7, gridBagConstraints);

        labelControl8.setLabel("Observações:");
        labelControl8.setToolTipText("");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 14;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        form1.add(labelControl8, gridBagConstraints);

        textAreaControl1.setAttributeName("observacao");
        textAreaControl1.setEnabled(false);
        textAreaControl1.setMaxCharacters(1000);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 15;
        gridBagConstraints.gridwidth = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipady = 30;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        form1.add(textAreaControl1, gridBagConstraints);

        numericControl1.setAttributeName("valorSubtotal");
        numericControl1.setDecimals(2);
        numericControl1.setEnabled(false);
        numericControl1.setEnabledOnEdit(false);
        numericControl1.setEnabledOnInsert(false);
        numericControl1.setGrouping(true);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 13;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        form1.add(numericControl1, gridBagConstraints);

        numericControl2.setAttributeName("valorFrete");
        numericControl2.setDecimals(2);
        numericControl2.setEnabled(false);
        numericControl2.setGrouping(true);
        numericControl2.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                numericControl2FocusLost(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 13;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        form1.add(numericControl2, gridBagConstraints);

        numericControl3.setAttributeName("taxaComissao");
        numericControl3.setDecimals(2);
        numericControl3.setEnabled(false);
        numericControl3.setEnabledOnEdit(false);
        numericControl3.setEnabledOnInsert(false);
        numericControl3.setGrouping(true);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 13;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        form1.add(numericControl3, gridBagConstraints);

        numericControl4.setAttributeName("valorComissao");
        numericControl4.setDecimals(2);
        numericControl4.setEnabled(false);
        numericControl4.setEnabledOnEdit(false);
        numericControl4.setEnabledOnInsert(false);
        numericControl4.setGrouping(true);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 13;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        form1.add(numericControl4, gridBagConstraints);

        numericControl5.setAttributeName("taxaDesconto");
        numericControl5.setDecimals(2);
        numericControl5.setEnabled(false);
        numericControl5.setGrouping(true);
        numericControl5.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                numericControl5FocusLost(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 13;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        form1.add(numericControl5, gridBagConstraints);

        numericControl6.setAttributeName("valorTotal");
        numericControl6.setDecimals(2);
        numericControl6.setEnabled(false);
        numericControl6.setEnabledOnEdit(false);
        numericControl6.setEnabledOnInsert(false);
        numericControl6.setGrouping(true);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 13;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        form1.add(numericControl6, gridBagConstraints);

        codLookupCondicaoPagamento.setAllowOnlyNumbers(true);
        codLookupCondicaoPagamento.setAttributeName("condicoesPagamento.id");
        codLookupCondicaoPagamento.setEnabled(false);
        codLookupCondicaoPagamento.setEnabledOnInsert(false);
        codLookupCondicaoPagamento.setLinkLabel(labelControl9);
        codLookupCondicaoPagamento.setRequired(true);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = -50;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 0);
        form1.add(codLookupCondicaoPagamento, gridBagConstraints);

        textControl2.setAttributeName("condicoesPagamento.nome");
        textControl2.setEnabled(false);
        textControl2.setEnabledOnEdit(false);
        textControl2.setEnabledOnInsert(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 5);
        form1.add(textControl2, gridBagConstraints);

        codLookupTrasportadora.setAllowOnlyNumbers(true);
        codLookupTrasportadora.setAttributeName("transportadora.id");
        codLookupTrasportadora.setEnabled(false);
        codLookupTrasportadora.setEnabledOnInsert(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = -70;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 0);
        form1.add(codLookupTrasportadora, gridBagConstraints);

        textControl3.setAttributeName("localEntrega");
        textControl3.setEnabled(false);
        textControl3.setMaxCharacters(100);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 11;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 5);
        form1.add(textControl3, gridBagConstraints);

        labelControl9.setLabel("Condições Pagamento:");
        labelControl9.setToolTipText("");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        form1.add(labelControl9, gridBagConstraints);

        codLookupVendedor.setAllowOnlyNumbers(true);
        codLookupVendedor.setAttributeName("vendedor.id");
        codLookupVendedor.setEnabled(false);
        codLookupVendedor.setEnabledOnInsert(false);
        codLookupVendedor.setLinkLabel(labelControl28);
        codLookupVendedor.setRequired(true);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = -70;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 0);
        form1.add(codLookupVendedor, gridBagConstraints);

        labelControl19.setLabel("Tipo de Nota Fiscal:");
        labelControl19.setToolTipText("");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        form1.add(labelControl19, gridBagConstraints);

        textControl7.setAttributeName("vendedor.colaborador.pessoa.nome");
        textControl7.setEnabled(false);
        textControl7.setEnabledOnEdit(false);
        textControl7.setEnabledOnInsert(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 5);
        form1.add(textControl7, gridBagConstraints);

        codLookupCliente.setAllowOnlyNumbers(true);
        codLookupCliente.setAttributeName("cliente.id");
        codLookupCliente.setEnabled(false);
        codLookupCliente.setEnabledOnInsert(false);
        codLookupCliente.setLinkLabel(labelControl20);
        codLookupCliente.setRequired(true);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = -50;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 0);
        form1.add(codLookupCliente, gridBagConstraints);

        labelControl20.setLabel("Cliente:");
        labelControl20.setToolTipText("");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        form1.add(labelControl20, gridBagConstraints);

        textControl8.setAttributeName("cliente.pessoa.nome");
        textControl8.setEnabled(false);
        textControl8.setEnabledOnEdit(false);
        textControl8.setEnabledOnInsert(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 5);
        form1.add(textControl8, gridBagConstraints);

        numericControl13.setAttributeName("numeroFatura");
        numericControl13.setEnabled(false);
        numericControl13.setGrouping(true);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 9;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        form1.add(numericControl13, gridBagConstraints);

        labelControl21.setLabel("Valor Desconto:");
        labelControl21.setToolTipText("");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 12;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        form1.add(labelControl21, gridBagConstraints);

        comboBoxControl1.setAttributeName("tipoFrete");
        comboBoxControl1.setDomainId("compraTipoFrete");
        comboBoxControl1.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 9;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        form1.add(comboBoxControl1, gridBagConstraints);

        labelControl23.setLabel("Local de Entrega:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        form1.add(labelControl23, gridBagConstraints);

        comboBoxControl2.setAttributeName("formaPagamento");
        comboBoxControl2.setDomainId("formaPagamento");
        comboBoxControl2.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 9;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        form1.add(comboBoxControl2, gridBagConstraints);

        labelControl24.setLabel("Frete:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        form1.add(labelControl24, gridBagConstraints);

        labelControl25.setLabel("Data Saída:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        form1.add(labelControl25, gridBagConstraints);

        labelControl26.setLabel("Número Fatura:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        form1.add(labelControl26, gridBagConstraints);

        labelControl27.setLabel("Data Venda:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        form1.add(labelControl27, gridBagConstraints);

        dateControl2.setAttributeName("dataSaida");
        dateControl2.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 9;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        form1.add(dateControl2, gridBagConstraints);

        dateControl3.setAttributeName("dataVenda");
        dateControl3.setEnabled(false);
        dateControl3.setLinkLabel(labelControl27);
        dateControl3.setRequired(true);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 9;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        form1.add(dateControl3, gridBagConstraints);

        jLabel1.setText("Tipo de Venda:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        form1.add(jLabel1, gridBagConstraints);

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Do Orçamento", "Venda Direta" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        form1.add(jComboBox1, gridBagConstraints);

        labelControl28.setLabel("Vendedor:");
        labelControl28.setToolTipText("");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        form1.add(labelControl28, gridBagConstraints);

        codLookupTipoNotaFiscal.setAllowOnlyNumbers(true);
        codLookupTipoNotaFiscal.setAttributeName("notaFiscalTipo.id");
        codLookupTipoNotaFiscal.setEnabled(false);
        codLookupTipoNotaFiscal.setLinkLabel(labelControl19);
        codLookupTipoNotaFiscal.setRequired(true);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = -50;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 0);
        form1.add(codLookupTipoNotaFiscal, gridBagConstraints);

        textControl9.setAttributeName("notaFiscalTipo.nome");
        textControl9.setEnabled(false);
        textControl9.setEnabledOnEdit(false);
        textControl9.setEnabledOnInsert(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 5);
        form1.add(textControl9, gridBagConstraints);

        labelControl29.setLabel("Orçamento:");
        labelControl29.setToolTipText("");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        form1.add(labelControl29, gridBagConstraints);

        codLookupOrcamento.setAllowOnlyNumbers(true);
        codLookupOrcamento.setAttributeName("vendaOrcamentoCabecalho.id");
        codLookupOrcamento.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = -70;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 0);
        form1.add(codLookupOrcamento, gridBagConstraints);

        textControl10.setAttributeName("vendaOrcamentoCabecalho.codigo");
        textControl10.setEnabled(false);
        textControl10.setEnabledOnEdit(false);
        textControl10.setEnabledOnInsert(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 5);
        form1.add(textControl10, gridBagConstraints);

        labelControl30.setLabel("Hora Saída:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        form1.add(labelControl30, gridBagConstraints);

        numericControl14.setAttributeName("valorDesconto");
        numericControl14.setDecimals(2);
        numericControl14.setEnabled(false);
        numericControl14.setEnabledOnEdit(false);
        numericControl14.setEnabledOnInsert(false);
        numericControl14.setGrouping(true);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 13;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        form1.add(numericControl14, gridBagConstraints);

        formattedTextControl1.setAttributeName("horaSaida");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 9;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        form1.add(formattedTextControl1, gridBagConstraints);

        labelControl31.setLabel("Local de Cobrança:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        form1.add(labelControl31, gridBagConstraints);

        labelControl32.setLabel("Forma de Pagamento:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        form1.add(labelControl32, gridBagConstraints);

        textControl4.setAttributeName("localCobranca");
        textControl4.setEnabled(false);
        textControl4.setMaxCharacters(100);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 11;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 5);
        form1.add(textControl4, gridBagConstraints);

        textControl5.setAttributeName("transportadora.pessoa.nome");
        textControl5.setEnabled(false);
        textControl5.setEnabledOnEdit(false);
        textControl5.setEnabledOnInsert(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 5);
        form1.add(textControl5, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel4.add(form1, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        jPanel2.add(jPanel4, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        jPanel6.add(jPanel2, gridBagConstraints);

        jScrollPane1.setViewportView(jPanel6);

        getContentPane().add(jScrollPane1, "card3");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        if (jComboBox1.getSelectedIndex() == 0) {//do orcamento
            codLookupOrcamento.setEnabled(true);
            codLookupCliente.setEnabled(false);
            codLookupVendedor.setEnabled(false);
            codLookupCondicaoPagamento.setEnabled(false);
            codLookupTrasportadora.setEnabled(false);
        } else {
            //venda direta
            VendaCabecalhoVO vendaCabecalho = (VendaCabecalhoVO) form1.getVOModel().getValueObject();
            codLookupOrcamento.setEnabled(false);
            vendaCabecalho.getVendaOrcamentoCabecalho().setId(null);
            vendaCabecalho.getVendaOrcamentoCabecalho().setCodigo(null);
            
            codLookupCliente.setEnabled(true);
            codLookupVendedor.setEnabled(true);
            codLookupCondicaoPagamento.setEnabled(true);
            codLookupTrasportadora.setEnabled(true);

            form1.pull();
        }
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void numericControl2FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_numericControl2FocusLost
        controller.atualizaTotais();
    }//GEN-LAST:event_numericControl2FocusLost

    private void numericControl5FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_numericControl5FocusLost
        controller.atualizaTotais();
    }//GEN-LAST:event_numericControl5FocusLost

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private org.openswing.swing.client.CodLookupControl codLookupCliente;
    private org.openswing.swing.table.columns.client.CodLookupColumn codLookupColumn1;
    private org.openswing.swing.client.CodLookupControl codLookupCondicaoPagamento;
    private org.openswing.swing.client.CodLookupControl codLookupOrcamento;
    private org.openswing.swing.client.CodLookupControl codLookupTipoNotaFiscal;
    private org.openswing.swing.client.CodLookupControl codLookupTrasportadora;
    private org.openswing.swing.client.CodLookupControl codLookupVendedor;
    private org.openswing.swing.client.ComboBoxControl comboBoxControl1;
    private org.openswing.swing.client.ComboBoxControl comboBoxControl2;
    private org.openswing.swing.client.DateControl dateControl2;
    private org.openswing.swing.client.DateControl dateControl3;
    private org.openswing.swing.table.columns.client.DecimalColumn decimalColumn1;
    private org.openswing.swing.table.columns.client.DecimalColumn decimalColumn2;
    private org.openswing.swing.table.columns.client.DecimalColumn decimalColumn3;
    private org.openswing.swing.table.columns.client.DecimalColumn decimalColumn4;
    private org.openswing.swing.table.columns.client.DecimalColumn decimalColumn5;
    private org.openswing.swing.table.columns.client.DecimalColumn decimalColumn6;
    private org.openswing.swing.client.DeleteButton deleteButton1;
    private org.openswing.swing.client.EditButton editButton1;
    private org.openswing.swing.client.EditButton editButton2;
    private org.openswing.swing.form.client.Form form1;
    private org.openswing.swing.client.FormattedTextControl formattedTextControl1;
    private org.openswing.swing.client.GridControl gridControl1;
    private org.openswing.swing.client.InsertButton insertButton1;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private org.openswing.swing.client.LabelControl labelControl1;
    private org.openswing.swing.client.LabelControl labelControl19;
    private org.openswing.swing.client.LabelControl labelControl2;
    private org.openswing.swing.client.LabelControl labelControl20;
    private org.openswing.swing.client.LabelControl labelControl21;
    private org.openswing.swing.client.LabelControl labelControl23;
    private org.openswing.swing.client.LabelControl labelControl24;
    private org.openswing.swing.client.LabelControl labelControl25;
    private org.openswing.swing.client.LabelControl labelControl26;
    private org.openswing.swing.client.LabelControl labelControl27;
    private org.openswing.swing.client.LabelControl labelControl28;
    private org.openswing.swing.client.LabelControl labelControl29;
    private org.openswing.swing.client.LabelControl labelControl3;
    private org.openswing.swing.client.LabelControl labelControl30;
    private org.openswing.swing.client.LabelControl labelControl31;
    private org.openswing.swing.client.LabelControl labelControl32;
    private org.openswing.swing.client.LabelControl labelControl4;
    private org.openswing.swing.client.LabelControl labelControl5;
    private org.openswing.swing.client.LabelControl labelControl6;
    private org.openswing.swing.client.LabelControl labelControl7;
    private org.openswing.swing.client.LabelControl labelControl8;
    private org.openswing.swing.client.LabelControl labelControl9;
    private org.openswing.swing.client.NavigatorBar navigatorBar1;
    private org.openswing.swing.client.NumericControl numericControl1;
    private org.openswing.swing.client.NumericControl numericControl13;
    private org.openswing.swing.client.NumericControl numericControl14;
    private org.openswing.swing.client.NumericControl numericControl2;
    private org.openswing.swing.client.NumericControl numericControl3;
    private org.openswing.swing.client.NumericControl numericControl4;
    private org.openswing.swing.client.NumericControl numericControl5;
    private org.openswing.swing.client.NumericControl numericControl6;
    private org.openswing.swing.client.ReloadButton reloadButton1;
    private org.openswing.swing.client.ReloadButton reloadButton2;
    private org.openswing.swing.client.SaveButton saveButton1;
    private org.openswing.swing.client.SaveButton saveButton2;
    private org.openswing.swing.client.TextAreaControl textAreaControl1;
    private org.openswing.swing.client.TextControl textControl10;
    private org.openswing.swing.client.TextControl textControl2;
    private org.openswing.swing.client.TextControl textControl3;
    private org.openswing.swing.client.TextControl textControl4;
    private org.openswing.swing.client.TextControl textControl5;
    private org.openswing.swing.client.TextControl textControl7;
    private org.openswing.swing.client.TextControl textControl8;
    private org.openswing.swing.client.TextControl textControl9;
    // End of variables declaration//GEN-END:variables

}
