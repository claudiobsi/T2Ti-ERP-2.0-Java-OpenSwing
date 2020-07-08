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
package com.t2tierp.cadastros.cliente;

import com.t2tierp.padrao.cliente.LookupDataLocatorGenerico;
import java.awt.Dimension;
import org.openswing.swing.lookup.client.LookupController;
import org.openswing.swing.mdi.client.InternalFrame;

public class ClienteDetalhe extends InternalFrame {

    private LookupController pessoaController = new LookupController();
    private LookupController atividadeController = new LookupController();
    private LookupController situacaoController = new LookupController();
    private LookupController contaContabilController = new LookupController();
    private LookupController operacaoFiscalController = new LookupController();

    public ClienteDetalhe(ClienteDetalheController controller) {
        initComponents();

        form1.setFormController(controller);

        /*
         * Configurações do lookup da pessoa
         */
        pessoaController.setLookupValueObjectClassName("com.t2tierp.cadastros.java.PessoaVO");
        pessoaController.addLookup2ParentLink("id", "pessoa.id");
        pessoaController.addLookup2ParentLink("nome", "pessoa.nome");
        pessoaController.setHeaderColumnName("id", "ID");
        pessoaController.setHeaderColumnName("nome", "Nome");
        pessoaController.setFrameTitle("Importa Pessoa");

        pessoaController.setVisibleStatusPanel(true);
        pessoaController.setVisibleColumn("id", true);
        pessoaController.setVisibleColumn("nome", true);
        pessoaController.setFramePreferedSize(new Dimension(600, 500));

        pessoaController.setLookupDataLocator(new LookupDataLocatorGenerico(pessoaController.getLookupValueObjectClassName()));
        codLookupControl4.setLookupController(pessoaController);

        /*
         * Configurações do lookup da situacao
         */
        situacaoController.setLookupValueObjectClassName("com.t2tierp.cadastros.java.SituacaoForCliVO");
        situacaoController.addLookup2ParentLink("id", "situacaoForCli.id");
        situacaoController.addLookup2ParentLink("nome", "situacaoForCli.nome");
        situacaoController.setHeaderColumnName("id", "ID");
        situacaoController.setHeaderColumnName("nome", "Nome");
        situacaoController.setFrameTitle("Importa Situação");

        situacaoController.setVisibleStatusPanel(true);
        situacaoController.setVisibleColumn("id", true);
        situacaoController.setVisibleColumn("nome", true);
        situacaoController.setFramePreferedSize(new Dimension(600, 500));

        situacaoController.setLookupDataLocator(new LookupDataLocatorGenerico(situacaoController.getLookupValueObjectClassName()));
        codLookupControl6.setLookupController(situacaoController);

        /*
         * Configurações do lookup da atividade
         */
        atividadeController.setLookupValueObjectClassName("com.t2tierp.cadastros.java.AtividadeForCliVO");
        atividadeController.addLookup2ParentLink("id", "atividadeForCli.id");
        atividadeController.addLookup2ParentLink("nome", "atividadeForCli.nome");
        atividadeController.setHeaderColumnName("id", "ID");
        atividadeController.setHeaderColumnName("nome", "Nome");
        atividadeController.setFrameTitle("Importa Atividade");

        atividadeController.setVisibleStatusPanel(true);
        atividadeController.setVisibleColumn("id", true);
        atividadeController.setVisibleColumn("nome", true);
        atividadeController.setFramePreferedSize(new Dimension(600, 500));

        atividadeController.setLookupDataLocator(new LookupDataLocatorGenerico(atividadeController.getLookupValueObjectClassName()));
        codLookupControl5.setLookupController(atividadeController);

        /*
         * Configurações do lookup da conta contabil
         */
        contaContabilController.setLookupValueObjectClassName("com.t2tierp.contabilidade.java.ContabilContaVO");
        contaContabilController.addLookup2ParentLink("id", "contabilConta.id");
        contaContabilController.addLookup2ParentLink("descricao", "contabilConta.descricao");
        contaContabilController.setHeaderColumnName("id", "ID");
        contaContabilController.setHeaderColumnName("descricao", "Descrição");
        contaContabilController.setFrameTitle("Importa Conta Contábil");

        contaContabilController.setVisibleStatusPanel(true);
        contaContabilController.setVisibleColumn("id", true);
        contaContabilController.setVisibleColumn("descricao", true);
        contaContabilController.setFramePreferedSize(new Dimension(600, 500));

        contaContabilController.setLookupDataLocator(new LookupDataLocatorGenerico(contaContabilController.getLookupValueObjectClassName()));
        codLookupControl2.setLookupController(contaContabilController);

        /*
         * Configurações do lookup da operacao fiscal
         */
        operacaoFiscalController.setLookupValueObjectClassName("com.t2tierp.tributacao.java.TributOperacaoFiscalVO");
        operacaoFiscalController.addLookup2ParentLink("id", "tributOperacaoFiscal.id");
        operacaoFiscalController.addLookup2ParentLink("descricao", "tributOperacaoFiscal.descricao");
        operacaoFiscalController.setHeaderColumnName("id", "ID");
        operacaoFiscalController.setHeaderColumnName("descricao", "Descrição");
        operacaoFiscalController.setFrameTitle("Importa Operação Fiscal");

        operacaoFiscalController.setVisibleStatusPanel(true);
        operacaoFiscalController.setVisibleColumn("id", true);
        operacaoFiscalController.setVisibleColumn("descricao", true);
        operacaoFiscalController.setFramePreferedSize(new Dimension(600, 500));

        operacaoFiscalController.setLookupDataLocator(new LookupDataLocatorGenerico(operacaoFiscalController.getLookupValueObjectClassName()));
        codLookupControl3.setLookupController(operacaoFiscalController);
    }

    /**
     * @return the form1
     */
    public org.openswing.swing.form.client.Form getForm1() {
        return form1;
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
        form1 = new org.openswing.swing.form.client.Form();
        labelControl1 = new org.openswing.swing.client.LabelControl();
        codLookupControl2 = new org.openswing.swing.client.CodLookupControl();
        textControl2 = new org.openswing.swing.client.TextControl();
        labelControl3 = new org.openswing.swing.client.LabelControl();
        codLookupControl3 = new org.openswing.swing.client.CodLookupControl();
        textControl3 = new org.openswing.swing.client.TextControl();
        labelControl5 = new org.openswing.swing.client.LabelControl();
        codLookupControl4 = new org.openswing.swing.client.CodLookupControl();
        textControl4 = new org.openswing.swing.client.TextControl();
        labelControl7 = new org.openswing.swing.client.LabelControl();
        codLookupControl5 = new org.openswing.swing.client.CodLookupControl();
        textControl5 = new org.openswing.swing.client.TextControl();
        labelControl9 = new org.openswing.swing.client.LabelControl();
        codLookupControl6 = new org.openswing.swing.client.CodLookupControl();
        textControl6 = new org.openswing.swing.client.TextControl();
        labelControl11 = new org.openswing.swing.client.LabelControl();
        dateControl7 = new org.openswing.swing.client.DateControl();
        labelControl12 = new org.openswing.swing.client.LabelControl();
        dateControl8 = new org.openswing.swing.client.DateControl();
        labelControl13 = new org.openswing.swing.client.LabelControl();
        labelControl14 = new org.openswing.swing.client.LabelControl();
        textControl10 = new org.openswing.swing.client.TextControl();
        labelControl15 = new org.openswing.swing.client.LabelControl();
        comboBoxControl11 = new org.openswing.swing.client.ComboBoxControl();
        labelControl16 = new org.openswing.swing.client.LabelControl();
        comboBoxControl12 = new org.openswing.swing.client.ComboBoxControl();
        labelControl17 = new org.openswing.swing.client.LabelControl();
        numericControl13 = new org.openswing.swing.client.NumericControl();
        labelControl18 = new org.openswing.swing.client.LabelControl();
        comboBoxControl14 = new org.openswing.swing.client.ComboBoxControl();
        labelControl19 = new org.openswing.swing.client.LabelControl();
        numericControl15 = new org.openswing.swing.client.NumericControl();
        labelControl20 = new org.openswing.swing.client.LabelControl();
        comboBoxControl16 = new org.openswing.swing.client.ComboBoxControl();
        textAreaControl1 = new org.openswing.swing.client.TextAreaControl();
        jSeparator1 = new javax.swing.JSeparator();

        setTitle("T2Ti ERP - Cadastros");
        setPreferredSize(new java.awt.Dimension(700, 400));
        getContentPane().setLayout(new java.awt.GridBagLayout());

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Cliente"));
        jPanel1.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));
        jPanel1.add(editButton1);
        jPanel1.add(reloadButton1);
        jPanel1.add(saveButton1);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        getContentPane().add(jPanel1, gridBagConstraints);

        form1.setVOClassName("com.t2tierp.cadastros.java.ClienteVO");
        form1.setEditButton(editButton1);
        form1.setFunctionId("cliente");
        form1.setReloadButton(reloadButton1);
        form1.setSaveButton(saveButton1);
        form1.setLayout(new java.awt.GridBagLayout());

        labelControl1.setLabel("Conta Contabil:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        form1.add(labelControl1, gridBagConstraints);

        codLookupControl2.setAllowOnlyNumbers(true);
        codLookupControl2.setAttributeName("contabilConta.id");
        codLookupControl2.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = -70;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        form1.add(codLookupControl2, gridBagConstraints);

        textControl2.setAttributeName("contabilConta.descricao");
        textControl2.setEnabled(false);
        textControl2.setEnabledOnEdit(false);
        textControl2.setEnabledOnInsert(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        form1.add(textControl2, gridBagConstraints);

        labelControl3.setLabel("Operacao Fiscal:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        form1.add(labelControl3, gridBagConstraints);

        codLookupControl3.setAllowOnlyNumbers(true);
        codLookupControl3.setAttributeName("tributOperacaoFiscal.id");
        codLookupControl3.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = -70;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        form1.add(codLookupControl3, gridBagConstraints);

        textControl3.setAttributeName("tributOperacaoFiscal.descricao");
        textControl3.setEnabled(false);
        textControl3.setEnabledOnEdit(false);
        textControl3.setEnabledOnInsert(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        form1.add(textControl3, gridBagConstraints);

        labelControl5.setLabel("Pessoa:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        form1.add(labelControl5, gridBagConstraints);

        codLookupControl4.setAllowOnlyNumbers(true);
        codLookupControl4.setAttributeName("pessoa.id");
        codLookupControl4.setEnabled(false);
        codLookupControl4.setLinkLabel(labelControl5);
        codLookupControl4.setMaxCharacters(10);
        codLookupControl4.setRequired(true);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = -70;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        form1.add(codLookupControl4, gridBagConstraints);

        textControl4.setAttributeName("pessoa.nome");
        textControl4.setEnabled(false);
        textControl4.setEnabledOnEdit(false);
        textControl4.setEnabledOnInsert(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        form1.add(textControl4, gridBagConstraints);

        labelControl7.setLabel("Atividade:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        form1.add(labelControl7, gridBagConstraints);

        codLookupControl5.setAllowOnlyNumbers(true);
        codLookupControl5.setAttributeName("atividadeForCli.id");
        codLookupControl5.setEnabled(false);
        codLookupControl5.setLinkLabel(labelControl7);
        codLookupControl5.setRequired(true);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = -70;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        form1.add(codLookupControl5, gridBagConstraints);

        textControl5.setAttributeName("atividadeForCli.nome");
        textControl5.setEnabled(false);
        textControl5.setEnabledOnEdit(false);
        textControl5.setEnabledOnInsert(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        form1.add(textControl5, gridBagConstraints);

        labelControl9.setLabel("Situacao:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        form1.add(labelControl9, gridBagConstraints);

        codLookupControl6.setAllowOnlyNumbers(true);
        codLookupControl6.setAttributeName("situacaoForCli.id");
        codLookupControl6.setEnabled(false);
        codLookupControl6.setLinkLabel(labelControl9);
        codLookupControl6.setRequired(true);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = -70;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        form1.add(codLookupControl6, gridBagConstraints);

        textControl6.setAttributeName("situacaoForCli.nome");
        textControl6.setEnabled(false);
        textControl6.setEnabledOnEdit(false);
        textControl6.setEnabledOnInsert(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        form1.add(textControl6, gridBagConstraints);

        labelControl11.setLabel("Desde:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        form1.add(labelControl11, gridBagConstraints);

        dateControl7.setAttributeName("desde");
        dateControl7.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        form1.add(dateControl7, gridBagConstraints);

        labelControl12.setLabel("Data Cadastro:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        form1.add(labelControl12, gridBagConstraints);

        dateControl8.setAttributeName("dataCadastro");
        dateControl8.setEnabled(false);
        dateControl8.setEnabledOnEdit(false);
        dateControl8.setEnabledOnInsert(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 9;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        form1.add(dateControl8, gridBagConstraints);

        labelControl13.setLabel("Observacao:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        form1.add(labelControl13, gridBagConstraints);

        labelControl14.setLabel("Conta Tomador:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        form1.add(labelControl14, gridBagConstraints);

        textControl10.setAttributeName("contaTomador");
        textControl10.setEnabled(false);
        textControl10.setMaxCharacters(30);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        form1.add(textControl10, gridBagConstraints);

        labelControl15.setLabel("Gera Financeiro:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        form1.add(labelControl15, gridBagConstraints);

        comboBoxControl11.setAttributeName("geraFinanceiro");
        comboBoxControl11.setDomainId("naosim");
        comboBoxControl11.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        form1.add(comboBoxControl11, gridBagConstraints);

        labelControl16.setLabel("Indicador Preco:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        form1.add(labelControl16, gridBagConstraints);

        comboBoxControl12.setAttributeName("indicadorPreco");
        comboBoxControl12.setDomainId("clienteIndicadorPreco");
        comboBoxControl12.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        form1.add(comboBoxControl12, gridBagConstraints);

        labelControl17.setLabel("Porcento Desconto:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        form1.add(labelControl17, gridBagConstraints);

        numericControl13.setAttributeName("porcentoDesconto");
        numericControl13.setDecimals(2);
        numericControl13.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 9;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        form1.add(numericControl13, gridBagConstraints);

        labelControl18.setLabel("Forma Desconto:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        form1.add(labelControl18, gridBagConstraints);

        comboBoxControl14.setAttributeName("formaDesconto");
        comboBoxControl14.setDomainId("clienteFormaDesconto");
        comboBoxControl14.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        form1.add(comboBoxControl14, gridBagConstraints);

        labelControl19.setLabel("Limite Credito:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        form1.add(labelControl19, gridBagConstraints);

        numericControl15.setAttributeName("limiteCredito");
        numericControl15.setDecimals(2);
        numericControl15.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 9;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        form1.add(numericControl15, gridBagConstraints);

        labelControl20.setLabel("Tipo Frete:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        form1.add(labelControl20, gridBagConstraints);

        comboBoxControl16.setAttributeName("tipoFrete");
        comboBoxControl16.setDomainId("clienteTipoFrete");
        comboBoxControl16.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        form1.add(comboBoxControl16, gridBagConstraints);

        textAreaControl1.setAttributeName("observacao");
        textAreaControl1.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 11;
        gridBagConstraints.gridwidth = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipady = 30;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        form1.add(textAreaControl1, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 12;
        gridBagConstraints.gridwidth = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        form1.add(jSeparator1, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        getContentPane().add(form1, gridBagConstraints);

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private org.openswing.swing.client.CodLookupControl codLookupControl2;
    private org.openswing.swing.client.CodLookupControl codLookupControl3;
    private org.openswing.swing.client.CodLookupControl codLookupControl4;
    private org.openswing.swing.client.CodLookupControl codLookupControl5;
    private org.openswing.swing.client.CodLookupControl codLookupControl6;
    private org.openswing.swing.client.ComboBoxControl comboBoxControl11;
    private org.openswing.swing.client.ComboBoxControl comboBoxControl12;
    private org.openswing.swing.client.ComboBoxControl comboBoxControl14;
    private org.openswing.swing.client.ComboBoxControl comboBoxControl16;
    private org.openswing.swing.client.DateControl dateControl7;
    private org.openswing.swing.client.DateControl dateControl8;
    private org.openswing.swing.client.EditButton editButton1;
    private org.openswing.swing.form.client.Form form1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSeparator jSeparator1;
    private org.openswing.swing.client.LabelControl labelControl1;
    private org.openswing.swing.client.LabelControl labelControl11;
    private org.openswing.swing.client.LabelControl labelControl12;
    private org.openswing.swing.client.LabelControl labelControl13;
    private org.openswing.swing.client.LabelControl labelControl14;
    private org.openswing.swing.client.LabelControl labelControl15;
    private org.openswing.swing.client.LabelControl labelControl16;
    private org.openswing.swing.client.LabelControl labelControl17;
    private org.openswing.swing.client.LabelControl labelControl18;
    private org.openswing.swing.client.LabelControl labelControl19;
    private org.openswing.swing.client.LabelControl labelControl20;
    private org.openswing.swing.client.LabelControl labelControl3;
    private org.openswing.swing.client.LabelControl labelControl5;
    private org.openswing.swing.client.LabelControl labelControl7;
    private org.openswing.swing.client.LabelControl labelControl9;
    private org.openswing.swing.client.NumericControl numericControl13;
    private org.openswing.swing.client.NumericControl numericControl15;
    private org.openswing.swing.client.ReloadButton reloadButton1;
    private org.openswing.swing.client.SaveButton saveButton1;
    private org.openswing.swing.client.TextAreaControl textAreaControl1;
    private org.openswing.swing.client.TextControl textControl10;
    private org.openswing.swing.client.TextControl textControl2;
    private org.openswing.swing.client.TextControl textControl3;
    private org.openswing.swing.client.TextControl textControl4;
    private org.openswing.swing.client.TextControl textControl5;
    private org.openswing.swing.client.TextControl textControl6;
    // End of variables declaration//GEN-END:variables

}
