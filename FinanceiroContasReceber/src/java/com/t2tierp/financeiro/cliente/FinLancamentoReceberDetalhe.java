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

import com.t2tierp.padrao.cliente.LookupDataLocatorGenerico;
import java.awt.Dimension;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import org.openswing.swing.client.GenericButton;
import org.openswing.swing.form.client.FormController;
import org.openswing.swing.lookup.client.LookupController;
import org.openswing.swing.mdi.client.InternalFrame;
import org.openswing.swing.util.client.ClientUtils;

public class FinLancamentoReceberDetalhe extends InternalFrame {

    private LookupController clienteController = new LookupController();
    private LookupController documentoOrigemController = new LookupController();
    private LookupController contaCaixaController = new LookupController();
    private LookupController naturezaFinanceiraController = new LookupController();
    private FinLctoReceberNtFinanceiraGridController gridNaturezaFinanceiraController;
    private FinParcelaReceberGridController parcelaReceberController;
    private FinLancamentoReceberDetalheController controller;

    public FinLancamentoReceberDetalhe(FinLancamentoReceberDetalheController controller) {
        initComponents();

        this.controller = controller;
        formLancamentoReceber.setFormController(controller);

        genericButton1.setToolTipText("Gerar Parcelas");
        genericButton2.setToolTipText("Emitir Boletos");
        genericButton3.setToolTipText("Gerar Arquivo Remessa");

        formContaCaixa.setFormController(new FormController());

        parcelaReceberController = new FinParcelaReceberGridController(this);
        gridControlParcelaReceber.setController(parcelaReceberController);
        gridControlParcelaReceber.setGridDataLocator(parcelaReceberController);

        gridNaturezaFinanceiraController = new FinLctoReceberNtFinanceiraGridController(this);
        gridControlNaturezaFinanceira.setController(gridNaturezaFinanceiraController);
        gridControlNaturezaFinanceira.setGridDataLocator(gridNaturezaFinanceiraController);


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
        clienteController.setFramePreferedSize(new Dimension(600, 500));

        clienteController.setLookupDataLocator(new LookupDataLocatorGenerico(clienteController.getLookupValueObjectClassName()));
        codLookupControl3.setLookupController(clienteController);

        /*
         * Configurações do lookup do documento origem
         */
        documentoOrigemController.setLookupValueObjectClassName("com.t2tierp.financeiro.java.FinDocumentoOrigemVO");
        documentoOrigemController.addLookup2ParentLink("id", "finDocumentoOrigem.id");
        documentoOrigemController.addLookup2ParentLink("descricao", "finDocumentoOrigem.descricao");
        documentoOrigemController.setHeaderColumnName("id", "ID");
        documentoOrigemController.setHeaderColumnName("descricao", "Descrição");
        documentoOrigemController.setFrameTitle("Importa Documento Origem");

        documentoOrigemController.setVisibleStatusPanel(true);
        documentoOrigemController.setVisibleColumn("id", true);
        documentoOrigemController.setVisibleColumn("descricao", true);
        documentoOrigemController.setFramePreferedSize(new Dimension(600, 500));

        documentoOrigemController.setLookupDataLocator(new LookupDataLocatorGenerico(documentoOrigemController.getLookupValueObjectClassName()));
        codLookupControl2.setLookupController(documentoOrigemController);

        /*
         * Configurações do lookup da conta caixa
         */
        contaCaixaController.setLookupValueObjectClassName("com.t2tierp.cadastros.java.ContaCaixaVO");
        contaCaixaController.addLookup2ParentLink("id", "contaCaixa.id");
        contaCaixaController.addLookup2ParentLink("nome", "contaCaixa.nome");
        contaCaixaController.addLookup2ParentLink("codigo", "contaCaixa.codigo");
        contaCaixaController.addLookup2ParentLink("digito", "contaCaixa.digito");
        contaCaixaController.addLookup2ParentLink("agenciaBanco", "contaCaixa.agenciaBanco");
        contaCaixaController.addLookup2ParentLink("agenciaBanco.banco", "contaCaixa.agenciaBanco.banco");
        contaCaixaController.setHeaderColumnName("id", "ID");
        contaCaixaController.setHeaderColumnName("nome", "Nome");
        contaCaixaController.setFrameTitle("Importa Conta Caixa");

        contaCaixaController.setVisibleStatusPanel(true);
        contaCaixaController.setVisibleColumn("id", true);
        contaCaixaController.setVisibleColumn("nome", true);
        contaCaixaController.setFramePreferedSize(new Dimension(600, 500));

        contaCaixaController.setLookupDataLocator(new LookupDataLocatorGenerico(contaCaixaController.getLookupValueObjectClassName()));
        codLookupControl1.setLookupController(contaCaixaController);

        /*
         * Configurações do lookup da natureza financeira
         */
        naturezaFinanceiraController.setLookupValueObjectClassName("com.t2tierp.financeiro.java.NaturezaFinanceiraVO");
        naturezaFinanceiraController.addLookup2ParentLink("id", "naturezaFinanceira.id");
        naturezaFinanceiraController.addLookup2ParentLink("descricao", "naturezaFinanceira.descricao");
        naturezaFinanceiraController.addLookup2ParentLink("classificacao", "naturezaFinanceira.classificacao");
        naturezaFinanceiraController.setHeaderColumnName("id", "ID");
        naturezaFinanceiraController.setHeaderColumnName("descricao", "Descrição");
        naturezaFinanceiraController.setHeaderColumnName("classificacao", "Classificação");
        naturezaFinanceiraController.setFrameTitle("Importa Conta Caixa");

        naturezaFinanceiraController.setVisibleStatusPanel(true);
        naturezaFinanceiraController.setVisibleColumn("id", true);
        naturezaFinanceiraController.setVisibleColumn("descricao", true);
        naturezaFinanceiraController.setVisibleColumn("classificacao", true);
        naturezaFinanceiraController.setFramePreferedSize(new Dimension(600, 500));

        naturezaFinanceiraController.setLookupDataLocator(new LookupDataLocatorGenerico(naturezaFinanceiraController.getLookupValueObjectClassName()));
        codLookupColumn1.setLookupController(naturezaFinanceiraController);

    }

    public org.openswing.swing.form.client.Form getFormLancamentoReceber() {
        return formLancamentoReceber;
    }

    public org.openswing.swing.form.client.Form getFormContaCaixa() {
        return formContaCaixa;
    }

    public org.openswing.swing.client.GridControl getGridControlParcelaReceber() {
        return gridControlParcelaReceber;
    }

    public org.openswing.swing.client.GridControl getGridControlNaturezaFinanceira() {
        return gridControlNaturezaFinanceira;
    }

    public FinParcelaReceberGridController getParcelaPagarController() {
        return parcelaReceberController;
    }

    public FinLctoReceberNtFinanceiraGridController getGridNaturezaFinanceiraController() {
        return gridNaturezaFinanceiraController;
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

        jPanel1 = new javax.swing.JPanel();
        editButton1 = new org.openswing.swing.client.EditButton();
        reloadButton1 = new org.openswing.swing.client.ReloadButton();
        saveButton1 = new org.openswing.swing.client.SaveButton();
        genericButton1 = new GenericButton(new ImageIcon(ClientUtils.getImage("currencies_16_h.png")));
        genericButton2 = new GenericButton(new ImageIcon(ClientUtils.getImage("views16_d.png")));
        genericButton3 = new GenericButton(new ImageIcon(ClientUtils.getImage("new_document16_d.png")));
        formLancamentoReceber = new org.openswing.swing.form.client.Form();
        labelControl1 = new org.openswing.swing.client.LabelControl();
        codLookupControl2 = new org.openswing.swing.client.CodLookupControl();
        textControl2 = new org.openswing.swing.client.TextControl();
        labelControl3 = new org.openswing.swing.client.LabelControl();
        codLookupControl3 = new org.openswing.swing.client.CodLookupControl();
        textControl3 = new org.openswing.swing.client.TextControl();
        labelControl5 = new org.openswing.swing.client.LabelControl();
        numericControl4 = new org.openswing.swing.client.NumericControl();
        labelControl6 = new org.openswing.swing.client.LabelControl();
        numericControl5 = new org.openswing.swing.client.NumericControl();
        labelControl7 = new org.openswing.swing.client.LabelControl();
        numericControl6 = new org.openswing.swing.client.NumericControl();
        labelControl8 = new org.openswing.swing.client.LabelControl();
        dateControl7 = new org.openswing.swing.client.DateControl();
        labelControl9 = new org.openswing.swing.client.LabelControl();
        textControl8 = new org.openswing.swing.client.TextControl();
        labelControl10 = new org.openswing.swing.client.LabelControl();
        dateControl9 = new org.openswing.swing.client.DateControl();
        labelControl11 = new org.openswing.swing.client.LabelControl();
        numericControl10 = new org.openswing.swing.client.NumericControl();
        labelControl12 = new org.openswing.swing.client.LabelControl();
        numericControl11 = new org.openswing.swing.client.NumericControl();
        labelControl13 = new org.openswing.swing.client.LabelControl();
        numericControl12 = new org.openswing.swing.client.NumericControl();
        labelControl14 = new org.openswing.swing.client.LabelControl();
        numericControl7 = new org.openswing.swing.client.NumericControl();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        gridControlParcelaReceber = new org.openswing.swing.client.GridControl();
        integerColumn5 = new org.openswing.swing.table.columns.client.IntegerColumn();
        dateColumn6 = new org.openswing.swing.table.columns.client.DateColumn();
        dateColumn7 = new org.openswing.swing.table.columns.client.DateColumn();
        dateColumn8 = new org.openswing.swing.table.columns.client.DateColumn();
        decimalColumn9 = new org.openswing.swing.table.columns.client.DecimalColumn();
        decimalColumn10 = new org.openswing.swing.table.columns.client.DecimalColumn();
        decimalColumn13 = new org.openswing.swing.table.columns.client.DecimalColumn();
        decimalColumn11 = new org.openswing.swing.table.columns.client.DecimalColumn();
        decimalColumn14 = new org.openswing.swing.table.columns.client.DecimalColumn();
        decimalColumn12 = new org.openswing.swing.table.columns.client.DecimalColumn();
        decimalColumn15 = new org.openswing.swing.table.columns.client.DecimalColumn();
        comboColumn16 = new org.openswing.swing.table.columns.client.ComboColumn();
        textColumn17 = new org.openswing.swing.table.columns.client.TextColumn();
        textColumn18 = new org.openswing.swing.table.columns.client.TextColumn();
        textColumn2 = new org.openswing.swing.table.columns.client.TextColumn();
        jPanel3 = new javax.swing.JPanel();
        reloadButtonParcelaReceber = new org.openswing.swing.client.ReloadButton();
        formContaCaixa = new org.openswing.swing.form.client.Form();
        textControl1 = new org.openswing.swing.client.TextControl();
        codLookupControl1 = new org.openswing.swing.client.CodLookupControl();
        labelControl2 = new org.openswing.swing.client.LabelControl();
        saveButtonParcelaReceber = new org.openswing.swing.client.SaveButton();
        editButtonParcelaReceber = new org.openswing.swing.client.EditButton();
        jPanel4 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        insertButtonNaturezaFinanceira = new org.openswing.swing.client.InsertButton();
        editButtonNaturezaFinanceira = new org.openswing.swing.client.EditButton();
        deleteButtonNaturezaFinanceira = new org.openswing.swing.client.DeleteButton();
        saveButtonNaturezaFinanceira = new org.openswing.swing.client.SaveButton();
        reloadButtonNaturezaFinanceira = new org.openswing.swing.client.ReloadButton();
        gridControlNaturezaFinanceira = new org.openswing.swing.client.GridControl();
        codLookupColumn1 = new org.openswing.swing.table.columns.client.CodLookupColumn();
        dateColumn5 = new org.openswing.swing.table.columns.client.DateColumn();
        decimalColumn6 = new org.openswing.swing.table.columns.client.DecimalColumn();

        setTitle("T2Ti ERP - Financeiro - Contas a Receber");
        setPreferredSize(new java.awt.Dimension(700, 400));
        getContentPane().setLayout(new java.awt.GridBagLayout());

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Fin Lancamento Receber"));
        jPanel1.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));
        jPanel1.add(editButton1);
        jPanel1.add(reloadButton1);
        jPanel1.add(saveButton1);

        genericButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                genericButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(genericButton1);

        genericButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                genericButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(genericButton2);

        genericButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                genericButton3ActionPerformed(evt);
            }
        });
        jPanel1.add(genericButton3);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        getContentPane().add(jPanel1, gridBagConstraints);

        formLancamentoReceber.setVOClassName("com.t2tierp.financeiro.java.FinLancamentoReceberVO");
        formLancamentoReceber.setEditButton(editButton1);
        formLancamentoReceber.setFunctionId("finLancamentoReceber");
        formLancamentoReceber.setReloadButton(reloadButton1);
        formLancamentoReceber.setSaveButton(saveButton1);
        formLancamentoReceber.setLayout(new java.awt.GridBagLayout());

        labelControl1.setText("Documento Origem:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        formLancamentoReceber.add(labelControl1, gridBagConstraints);

        codLookupControl2.setAllowOnlyNumbers(true);
        codLookupControl2.setAttributeName("finDocumentoOrigem.id");
        codLookupControl2.setEnabled(false);
        codLookupControl2.setLinkLabel(labelControl1);
        codLookupControl2.setMaxCharacters(10);
        codLookupControl2.setRequired(true);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = -70;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        formLancamentoReceber.add(codLookupControl2, gridBagConstraints);

        textControl2.setAttributeName("finDocumentoOrigem.descricao");
        textControl2.setEnabled(false);
        textControl2.setEnabledOnEdit(false);
        textControl2.setEnabledOnInsert(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        formLancamentoReceber.add(textControl2, gridBagConstraints);

        labelControl3.setText("Cliente:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        formLancamentoReceber.add(labelControl3, gridBagConstraints);

        codLookupControl3.setAllowOnlyNumbers(true);
        codLookupControl3.setAttributeName("cliente.id");
        codLookupControl3.setEnabled(false);
        codLookupControl3.setLinkLabel(labelControl3);
        codLookupControl3.setMaxCharacters(10);
        codLookupControl3.setRequired(true);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = -70;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        formLancamentoReceber.add(codLookupControl3, gridBagConstraints);

        textControl3.setAttributeName("cliente.pessoa.nome");
        textControl3.setEnabled(false);
        textControl3.setEnabledOnEdit(false);
        textControl3.setEnabledOnInsert(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        formLancamentoReceber.add(textControl3, gridBagConstraints);

        labelControl5.setLabel("Quantidade Parcela:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        formLancamentoReceber.add(labelControl5, gridBagConstraints);

        numericControl4.setAttributeName("quantidadeParcela");
        numericControl4.setEnabled(false);
        numericControl4.setLinkLabel(labelControl5);
        numericControl4.setMinValue(1.0);
        numericControl4.setRequired(true);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        formLancamentoReceber.add(numericControl4, gridBagConstraints);

        labelControl6.setLabel("Valor Total:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        formLancamentoReceber.add(labelControl6, gridBagConstraints);

        numericControl5.setAttributeName("valorTotal");
        numericControl5.setDecimals(2);
        numericControl5.setEnabled(false);
        numericControl5.setLinkLabel(labelControl6);
        numericControl5.setRequired(true);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        formLancamentoReceber.add(numericControl5, gridBagConstraints);

        labelControl7.setLabel("Valor A Receber:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        formLancamentoReceber.add(labelControl7, gridBagConstraints);

        numericControl6.setAttributeName("valorAReceber");
        numericControl6.setDecimals(2);
        numericControl6.setEnabled(false);
        numericControl6.setLinkLabel(labelControl7);
        numericControl6.setRequired(true);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        formLancamentoReceber.add(numericControl6, gridBagConstraints);

        labelControl8.setLabel("Data Lancamento:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        formLancamentoReceber.add(labelControl8, gridBagConstraints);

        dateControl7.setAttributeName("dataLancamento");
        dateControl7.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        formLancamentoReceber.add(dateControl7, gridBagConstraints);

        labelControl9.setLabel("Numero Documento:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        formLancamentoReceber.add(labelControl9, gridBagConstraints);

        textControl8.setAttributeName("numeroDocumento");
        textControl8.setEnabled(false);
        textControl8.setLinkLabel(labelControl9);
        textControl8.setMaxCharacters(50);
        textControl8.setRequired(true);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        formLancamentoReceber.add(textControl8, gridBagConstraints);

        labelControl10.setLabel("Primeiro Vencimento:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        formLancamentoReceber.add(labelControl10, gridBagConstraints);

        dateControl9.setAttributeName("primeiroVencimento");
        dateControl9.setEnabled(false);
        dateControl9.setLinkLabel(labelControl10);
        dateControl9.setRequired(true);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        formLancamentoReceber.add(dateControl9, gridBagConstraints);

        labelControl11.setLabel("Taxa Comissao:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        formLancamentoReceber.add(labelControl11, gridBagConstraints);

        numericControl10.setAttributeName("taxaComissao");
        numericControl10.setDecimals(2);
        numericControl10.setEnabled(false);
        numericControl10.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                numericControl10FocusLost(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        formLancamentoReceber.add(numericControl10, gridBagConstraints);

        labelControl12.setLabel("Valor Comissao:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        formLancamentoReceber.add(labelControl12, gridBagConstraints);

        numericControl11.setAttributeName("valorComissao");
        numericControl11.setDecimals(2);
        numericControl11.setEnabled(false);
        numericControl11.setEnabledOnEdit(false);
        numericControl11.setEnabledOnInsert(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints.ipadx = 50;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        formLancamentoReceber.add(numericControl11, gridBagConstraints);

        labelControl13.setLabel("Intervalo Entre Parcelas:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        formLancamentoReceber.add(labelControl13, gridBagConstraints);

        numericControl12.setAttributeName("intervaloEntreParcelas");
        numericControl12.setEnabled(false);
        numericControl12.setLinkLabel(labelControl13);
        numericControl12.setMinValue(1.0);
        numericControl12.setRequired(true);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        formLancamentoReceber.add(numericControl12, gridBagConstraints);

        labelControl14.setText("Valor Desconto Convenio:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        formLancamentoReceber.add(labelControl14, gridBagConstraints);

        numericControl7.setAttributeName("valorDescontoConvenio");
        numericControl7.setDecimals(2);
        numericControl7.setEnabled(false);
        numericControl7.setLinkLabel(labelControl14);
        numericControl7.setRequired(true);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        formLancamentoReceber.add(numericControl7, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        getContentPane().add(formLancamentoReceber, gridBagConstraints);

        jPanel2.setLayout(new java.awt.GridBagLayout());

        gridControlParcelaReceber.setAutoLoadData(false);
        gridControlParcelaReceber.setEditButton(editButtonParcelaReceber);
        gridControlParcelaReceber.setFunctionId("finParcelaReceber");
        gridControlParcelaReceber.setReloadButton(reloadButtonParcelaReceber);
        gridControlParcelaReceber.setSaveButton(saveButtonParcelaReceber);
        gridControlParcelaReceber.setValueObjectClassName("com.t2tierp.financeiro.java.FinParcelaReceberVO");
        gridControlParcelaReceber.getColumnContainer().setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        integerColumn5.setColumnName("numeroParcela");
        integerColumn5.setColumnRequired(false);
        integerColumn5.setHeaderColumnName("Numero Parcela");
        integerColumn5.setHeaderFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        gridControlParcelaReceber.getColumnContainer().add(integerColumn5);

        dateColumn6.setColumnName("dataEmissao");
        dateColumn6.setColumnRequired(false);
        dateColumn6.setHeaderColumnName("Data Emissao");
        dateColumn6.setHeaderFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        gridControlParcelaReceber.getColumnContainer().add(dateColumn6);

        dateColumn7.setColumnName("dataVencimento");
        dateColumn7.setEditableOnEdit(true);
        dateColumn7.setHeaderColumnName("Data Vencimento");
        dateColumn7.setHeaderFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        gridControlParcelaReceber.getColumnContainer().add(dateColumn7);

        dateColumn8.setColumnName("descontoAte");
        dateColumn8.setColumnRequired(false);
        dateColumn8.setEditableOnEdit(true);
        dateColumn8.setEditableOnInsert(true);
        dateColumn8.setHeaderColumnName("Desconto Ate");
        dateColumn8.setHeaderFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        gridControlParcelaReceber.getColumnContainer().add(dateColumn8);

        decimalColumn9.setColumnName("valor");
        decimalColumn9.setDecimals(2);
        decimalColumn9.setEditableOnEdit(true);
        decimalColumn9.setEditableOnInsert(true);
        decimalColumn9.setHeaderColumnName("Valor");
        decimalColumn9.setHeaderFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        gridControlParcelaReceber.getColumnContainer().add(decimalColumn9);

        decimalColumn10.setColumnName("taxaJuro");
        decimalColumn10.setColumnRequired(false);
        decimalColumn10.setDecimals(2);
        decimalColumn10.setEditableOnEdit(true);
        decimalColumn10.setEditableOnInsert(true);
        decimalColumn10.setHeaderColumnName("Taxa Juro");
        decimalColumn10.setHeaderFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        gridControlParcelaReceber.getColumnContainer().add(decimalColumn10);

        decimalColumn13.setColumnName("valorJuro");
        decimalColumn13.setColumnRequired(false);
        decimalColumn13.setDecimals(2);
        decimalColumn13.setEditableOnEdit(true);
        decimalColumn13.setEditableOnInsert(true);
        decimalColumn13.setHeaderColumnName("Valor Juro");
        decimalColumn13.setHeaderFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        gridControlParcelaReceber.getColumnContainer().add(decimalColumn13);

        decimalColumn11.setColumnName("taxaMulta");
        decimalColumn11.setColumnRequired(false);
        decimalColumn11.setDecimals(2);
        decimalColumn11.setEditableOnEdit(true);
        decimalColumn11.setEditableOnInsert(true);
        decimalColumn11.setHeaderColumnName("Taxa Multa");
        decimalColumn11.setHeaderFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        gridControlParcelaReceber.getColumnContainer().add(decimalColumn11);

        decimalColumn14.setColumnName("valorMulta");
        decimalColumn14.setColumnRequired(false);
        decimalColumn14.setDecimals(2);
        decimalColumn14.setEditableOnEdit(true);
        decimalColumn14.setEditableOnInsert(true);
        decimalColumn14.setHeaderColumnName("Valor Multa");
        decimalColumn14.setHeaderFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        gridControlParcelaReceber.getColumnContainer().add(decimalColumn14);

        decimalColumn12.setColumnName("taxaDesconto");
        decimalColumn12.setColumnRequired(false);
        decimalColumn12.setDecimals(2);
        decimalColumn12.setEditableOnEdit(true);
        decimalColumn12.setEditableOnInsert(true);
        decimalColumn12.setHeaderColumnName("Taxa Desconto");
        decimalColumn12.setHeaderFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        gridControlParcelaReceber.getColumnContainer().add(decimalColumn12);

        decimalColumn15.setColumnName("valorDesconto");
        decimalColumn15.setColumnRequired(false);
        decimalColumn15.setDecimals(2);
        decimalColumn15.setEditableOnEdit(true);
        decimalColumn15.setEditableOnInsert(true);
        decimalColumn15.setHeaderColumnName("Valor Desconto");
        decimalColumn15.setHeaderFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        gridControlParcelaReceber.getColumnContainer().add(decimalColumn15);

        comboColumn16.setColumnName("emitiuBoleto");
        comboColumn16.setDomainId("simnao");
        comboColumn16.setEditableOnEdit(true);
        comboColumn16.setHeaderColumnName("Emitir Boleto");
        comboColumn16.setHeaderFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        gridControlParcelaReceber.getColumnContainer().add(comboColumn16);

        textColumn17.setColumnName("boletoNossoNumero");
        textColumn17.setColumnRequired(false);
        textColumn17.setHeaderColumnName("Boleto Nosso Numero");
        textColumn17.setHeaderFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        textColumn17.setPreferredWidth(150);
        gridControlParcelaReceber.getColumnContainer().add(textColumn17);

        textColumn18.setColumnName("finStatusParcela.descricao");
        textColumn18.setColumnRequired(false);
        textColumn18.setHeaderColumnName("Status");
        textColumn18.setHeaderFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        gridControlParcelaReceber.getColumnContainer().add(textColumn18);

        textColumn2.setColumnName("contaCaixa.nome");
        textColumn2.setColumnRequired(false);
        textColumn2.setHeaderColumnName("Conta Caixa");
        textColumn2.setHeaderFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        gridControlParcelaReceber.getColumnContainer().add(textColumn2);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel2.add(gridControlParcelaReceber, gridBagConstraints);

        jPanel3.setLayout(new java.awt.GridBagLayout());
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel3.add(reloadButtonParcelaReceber, gridBagConstraints);

        formContaCaixa.setVOClassName("com.t2tierp.financeiro.java.FinParcelaReceberVO");
        formContaCaixa.setLayout(new java.awt.GridBagLayout());

        textControl1.setAttributeName("contaCaixa.nome");
        textControl1.setEnabled(false);
        textControl1.setEnabledOnEdit(false);
        textControl1.setEnabledOnInsert(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        formContaCaixa.add(textControl1, gridBagConstraints);

        codLookupControl1.setAllowOnlyNumbers(true);
        codLookupControl1.setAttributeName("contaCaixa.id");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = -70;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        formContaCaixa.add(codLookupControl1, gridBagConstraints);

        labelControl2.setLabel("Conta Caixa:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        formContaCaixa.add(labelControl2, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        jPanel3.add(formContaCaixa, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel3.add(saveButtonParcelaReceber, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel3.add(editButtonParcelaReceber, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        jPanel2.add(jPanel3, gridBagConstraints);

        jTabbedPane1.addTab("Parcelas", jPanel2);

        jPanel4.setLayout(new java.awt.GridBagLayout());

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder("Fin Lcto Receber Nt Financeira"));
        jPanel5.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));
        jPanel5.add(insertButtonNaturezaFinanceira);
        jPanel5.add(editButtonNaturezaFinanceira);
        jPanel5.add(deleteButtonNaturezaFinanceira);
        jPanel5.add(saveButtonNaturezaFinanceira);
        jPanel5.add(reloadButtonNaturezaFinanceira);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        jPanel4.add(jPanel5, gridBagConstraints);

        gridControlNaturezaFinanceira.setAutoLoadData(false);
        gridControlNaturezaFinanceira.setDeleteButton(deleteButtonNaturezaFinanceira);
        gridControlNaturezaFinanceira.setEditButton(editButtonNaturezaFinanceira);
        gridControlNaturezaFinanceira.setFunctionId("finLctoPagarNtFinanceira");
        gridControlNaturezaFinanceira.setInsertButton(insertButtonNaturezaFinanceira);
        gridControlNaturezaFinanceira.setReloadButton(reloadButtonNaturezaFinanceira);
        gridControlNaturezaFinanceira.setSaveButton(saveButtonNaturezaFinanceira);
        gridControlNaturezaFinanceira.setValueObjectClassName("com.t2tierp.financeiro.java.FinLctoReceberNtFinanceiraVO");
        gridControlNaturezaFinanceira.getColumnContainer().setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        codLookupColumn1.setColumnName("naturezaFinanceira.descricao");
        codLookupColumn1.setEditableOnEdit(true);
        codLookupColumn1.setEditableOnInsert(true);
        codLookupColumn1.setHeaderColumnName("Natureza Financeira");
        codLookupColumn1.setHeaderFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        codLookupColumn1.setPreferredWidth(200);
        gridControlNaturezaFinanceira.getColumnContainer().add(codLookupColumn1);

        dateColumn5.setColumnName("dataInclusao");
        dateColumn5.setEditableOnEdit(true);
        dateColumn5.setEditableOnInsert(true);
        dateColumn5.setHeaderColumnName("Data Inclusao");
        dateColumn5.setHeaderFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        gridControlNaturezaFinanceira.getColumnContainer().add(dateColumn5);

        decimalColumn6.setColumnName("valor");
        decimalColumn6.setDecimals(2);
        decimalColumn6.setEditableOnEdit(true);
        decimalColumn6.setEditableOnInsert(true);
        decimalColumn6.setHeaderColumnName("Valor");
        decimalColumn6.setHeaderFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        gridControlNaturezaFinanceira.getColumnContainer().add(decimalColumn6);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel4.add(gridControlNaturezaFinanceira, gridBagConstraints);

        jTabbedPane1.addTab("Naturezas Financeiras Vinculadas", jPanel4);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        getContentPane().add(jTabbedPane1, gridBagConstraints);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void genericButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_genericButton1ActionPerformed
        try {
            parcelaReceberController.gerarParcelas();
        } catch (Exception ex) {
            //ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Ocorreu um erro ao gerar as parcelas\n" + ex.getMessage(), "Erro do Sistema", JOptionPane.ERROR_MESSAGE);
        }
}//GEN-LAST:event_genericButton1ActionPerformed

    private void genericButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_genericButton2ActionPerformed
        try {
            parcelaReceberController.gerarBoleto();
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Ocorreu um erro ao gerar os boletos!\n" + ex.getMessage(), "Erro do Sistema", JOptionPane.ERROR_MESSAGE);
        }
}//GEN-LAST:event_genericButton2ActionPerformed

    private void numericControl10FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_numericControl10FocusLost
        controller.calculaValorComissao();
    }//GEN-LAST:event_numericControl10FocusLost

    private void genericButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_genericButton3ActionPerformed
        parcelaReceberController.gerarRemessa();
    }//GEN-LAST:event_genericButton3ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private org.openswing.swing.table.columns.client.CodLookupColumn codLookupColumn1;
    private org.openswing.swing.client.CodLookupControl codLookupControl1;
    private org.openswing.swing.client.CodLookupControl codLookupControl2;
    private org.openswing.swing.client.CodLookupControl codLookupControl3;
    private org.openswing.swing.table.columns.client.ComboColumn comboColumn16;
    private org.openswing.swing.table.columns.client.DateColumn dateColumn5;
    private org.openswing.swing.table.columns.client.DateColumn dateColumn6;
    private org.openswing.swing.table.columns.client.DateColumn dateColumn7;
    private org.openswing.swing.table.columns.client.DateColumn dateColumn8;
    private org.openswing.swing.client.DateControl dateControl7;
    private org.openswing.swing.client.DateControl dateControl9;
    private org.openswing.swing.table.columns.client.DecimalColumn decimalColumn10;
    private org.openswing.swing.table.columns.client.DecimalColumn decimalColumn11;
    private org.openswing.swing.table.columns.client.DecimalColumn decimalColumn12;
    private org.openswing.swing.table.columns.client.DecimalColumn decimalColumn13;
    private org.openswing.swing.table.columns.client.DecimalColumn decimalColumn14;
    private org.openswing.swing.table.columns.client.DecimalColumn decimalColumn15;
    private org.openswing.swing.table.columns.client.DecimalColumn decimalColumn6;
    private org.openswing.swing.table.columns.client.DecimalColumn decimalColumn9;
    private org.openswing.swing.client.DeleteButton deleteButtonNaturezaFinanceira;
    private org.openswing.swing.client.EditButton editButton1;
    private org.openswing.swing.client.EditButton editButtonNaturezaFinanceira;
    private org.openswing.swing.client.EditButton editButtonParcelaReceber;
    private org.openswing.swing.form.client.Form formContaCaixa;
    private org.openswing.swing.form.client.Form formLancamentoReceber;
    private org.openswing.swing.client.GenericButton genericButton1;
    private org.openswing.swing.client.GenericButton genericButton2;
    private org.openswing.swing.client.GenericButton genericButton3;
    private org.openswing.swing.client.GridControl gridControlNaturezaFinanceira;
    private org.openswing.swing.client.GridControl gridControlParcelaReceber;
    private org.openswing.swing.client.InsertButton insertButtonNaturezaFinanceira;
    private org.openswing.swing.table.columns.client.IntegerColumn integerColumn5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JTabbedPane jTabbedPane1;
    private org.openswing.swing.client.LabelControl labelControl1;
    private org.openswing.swing.client.LabelControl labelControl10;
    private org.openswing.swing.client.LabelControl labelControl11;
    private org.openswing.swing.client.LabelControl labelControl12;
    private org.openswing.swing.client.LabelControl labelControl13;
    private org.openswing.swing.client.LabelControl labelControl14;
    private org.openswing.swing.client.LabelControl labelControl2;
    private org.openswing.swing.client.LabelControl labelControl3;
    private org.openswing.swing.client.LabelControl labelControl5;
    private org.openswing.swing.client.LabelControl labelControl6;
    private org.openswing.swing.client.LabelControl labelControl7;
    private org.openswing.swing.client.LabelControl labelControl8;
    private org.openswing.swing.client.LabelControl labelControl9;
    private org.openswing.swing.client.NumericControl numericControl10;
    private org.openswing.swing.client.NumericControl numericControl11;
    private org.openswing.swing.client.NumericControl numericControl12;
    private org.openswing.swing.client.NumericControl numericControl4;
    private org.openswing.swing.client.NumericControl numericControl5;
    private org.openswing.swing.client.NumericControl numericControl6;
    private org.openswing.swing.client.NumericControl numericControl7;
    private org.openswing.swing.client.ReloadButton reloadButton1;
    private org.openswing.swing.client.ReloadButton reloadButtonNaturezaFinanceira;
    private org.openswing.swing.client.ReloadButton reloadButtonParcelaReceber;
    private org.openswing.swing.client.SaveButton saveButton1;
    private org.openswing.swing.client.SaveButton saveButtonNaturezaFinanceira;
    private org.openswing.swing.client.SaveButton saveButtonParcelaReceber;
    private org.openswing.swing.table.columns.client.TextColumn textColumn17;
    private org.openswing.swing.table.columns.client.TextColumn textColumn18;
    private org.openswing.swing.table.columns.client.TextColumn textColumn2;
    private org.openswing.swing.client.TextControl textControl1;
    private org.openswing.swing.client.TextControl textControl2;
    private org.openswing.swing.client.TextControl textControl3;
    private org.openswing.swing.client.TextControl textControl8;
    // End of variables declaration//GEN-END:variables
}
