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

public class ColaboradorDetalhe extends InternalFrame {

    private LookupController pessoaController = new LookupController();
    private LookupController tipoController = new LookupController();
    private LookupController situacaoController = new LookupController();
    private LookupController tipoAdmissaoController = new LookupController();
    private LookupController nivelFormacaoController = new LookupController();
    private LookupController cargoController = new LookupController();
    private LookupController contaContabilController = new LookupController();
    private LookupController sindicatoController = new LookupController();
    private LookupController setorController = new LookupController();

    public ColaboradorDetalhe(ColaboradorDetalheController controller) {
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
        codLookupControlPessoa.setLookupController(pessoaController);

        /*
         * Configurações do lookup do tipo
         */
        tipoController.setLookupValueObjectClassName("com.t2tierp.cadastros.java.TipoColaboradorVO");
        tipoController.addLookup2ParentLink("id", "tipoColaborador.id");
        tipoController.addLookup2ParentLink("nome", "tipoColaborador.nome");
        tipoController.setHeaderColumnName("id", "ID");
        tipoController.setHeaderColumnName("nome", "Nome");
        tipoController.setFrameTitle("Importa Tipo Colaborador");

        tipoController.setVisibleStatusPanel(true);
        tipoController.setVisibleColumn("id", true);
        tipoController.setVisibleColumn("nome", true);
        tipoController.setFramePreferedSize(new Dimension(600, 500));

        tipoController.setLookupDataLocator(new LookupDataLocatorGenerico(tipoController.getLookupValueObjectClassName()));
        codLookupControlTipo.setLookupController(tipoController);

        /*
         * Configurações do lookup da situacao
         */
        situacaoController.setLookupValueObjectClassName("com.t2tierp.cadastros.java.SituacaoColaboradorVO");
        situacaoController.addLookup2ParentLink("id", "situacaoColaborador.id");
        situacaoController.addLookup2ParentLink("nome", "situacaoColaborador.nome");
        situacaoController.setHeaderColumnName("id", "ID");
        situacaoController.setHeaderColumnName("nome", "Nome");
        situacaoController.setFrameTitle("Importa Situacao Colaborador");

        situacaoController.setVisibleStatusPanel(true);
        situacaoController.setVisibleColumn("id", true);
        situacaoController.setVisibleColumn("nome", true);
        situacaoController.setFramePreferedSize(new Dimension(600, 500));

        situacaoController.setLookupDataLocator(new LookupDataLocatorGenerico(situacaoController.getLookupValueObjectClassName()));
        codLookupControlSituacao.setLookupController(situacaoController);

        /*
         * Configurações do lookup do tipo admissao
         */
        tipoAdmissaoController.setLookupValueObjectClassName("com.t2tierp.cadastros.java.TipoAdmissaoVO");
        tipoAdmissaoController.addLookup2ParentLink("id", "tipoAdmissao.id");
        tipoAdmissaoController.addLookup2ParentLink("nome", "tipoAdmissao.nome");
        tipoAdmissaoController.setHeaderColumnName("id", "ID");
        tipoAdmissaoController.setHeaderColumnName("nome", "Nome");
        tipoAdmissaoController.setFrameTitle("Importa Tipo Admissao");

        tipoAdmissaoController.setVisibleStatusPanel(true);
        tipoAdmissaoController.setVisibleColumn("id", true);
        tipoAdmissaoController.setVisibleColumn("nome", true);
        tipoAdmissaoController.setFramePreferedSize(new Dimension(600, 500));

        tipoAdmissaoController.setLookupDataLocator(new LookupDataLocatorGenerico(tipoAdmissaoController.getLookupValueObjectClassName()));
        codLookupControlTipoAdmissao.setLookupController(tipoAdmissaoController);

        /*
         * Configurações do lookup do Nivel Formacao
         */
        nivelFormacaoController.setLookupValueObjectClassName("com.t2tierp.cadastros.java.NivelFormacaoVO");
        nivelFormacaoController.addLookup2ParentLink("id", "nivelFormacao.id");
        nivelFormacaoController.addLookup2ParentLink("nome", "nivelFormacao.nome");
        nivelFormacaoController.setHeaderColumnName("id", "ID");
        nivelFormacaoController.setHeaderColumnName("nome", "Nome");
        nivelFormacaoController.setFrameTitle("Importa Nivel Formacao");

        nivelFormacaoController.setVisibleStatusPanel(true);
        nivelFormacaoController.setVisibleColumn("id", true);
        nivelFormacaoController.setVisibleColumn("nome", true);
        nivelFormacaoController.setFramePreferedSize(new Dimension(600, 500));

        nivelFormacaoController.setLookupDataLocator(new LookupDataLocatorGenerico(nivelFormacaoController.getLookupValueObjectClassName()));
        codLookupControlNivelFormacao.setLookupController(nivelFormacaoController);
        
        /*
         * Configurações do lookup do cargo
         */
        cargoController.setLookupValueObjectClassName("com.t2tierp.cadastros.java.CargoVO");
        cargoController.addLookup2ParentLink("id", "cargo.id");
        cargoController.addLookup2ParentLink("nome", "cargo.nome");
        cargoController.setHeaderColumnName("id", "ID");
        cargoController.setHeaderColumnName("nome", "Nome");
        cargoController.setFrameTitle("Importa Cargo");

        cargoController.setVisibleStatusPanel(true);
        cargoController.setVisibleColumn("id", true);
        cargoController.setVisibleColumn("nome", true);
        cargoController.setFramePreferedSize(new Dimension(600, 500));

        cargoController.setLookupDataLocator(new LookupDataLocatorGenerico(cargoController.getLookupValueObjectClassName()));
        codLookupControlCargo.setLookupController(cargoController);

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
        codLookupControlContabilConta.setLookupController(contaContabilController);

        /*
         * Configurações do lookup do setor
         */
        setorController.setLookupValueObjectClassName("com.t2tierp.cadastros.java.SetorVO");
        setorController.addLookup2ParentLink("id", "setor.id");
        setorController.addLookup2ParentLink("nome", "setor.nome");
        setorController.setHeaderColumnName("id", "ID");
        setorController.setHeaderColumnName("nome", "Nome");
        setorController.setFrameTitle("Importa Setor");

        setorController.setVisibleStatusPanel(true);
        setorController.setVisibleColumn("id", true);
        setorController.setVisibleColumn("nome", true);
        setorController.setFramePreferedSize(new Dimension(600, 500));

        setorController.setLookupDataLocator(new LookupDataLocatorGenerico(setorController.getLookupValueObjectClassName()));
        codLookupControlSetor.setLookupController(setorController);

        /*
         * Configurações do lookup do Sindicato
         */
        sindicatoController.setLookupValueObjectClassName("com.t2tierp.cadastros.java.SindicatoVO");
        sindicatoController.addLookup2ParentLink("id", "sindicato.id");
        sindicatoController.addLookup2ParentLink("nome", "sindicato.nome");
        sindicatoController.setHeaderColumnName("id", "ID");
        sindicatoController.setHeaderColumnName("nome", "Nome");
        sindicatoController.setFrameTitle("Importa Sindicato");

        sindicatoController.setVisibleStatusPanel(true);
        sindicatoController.setVisibleColumn("id", true);
        sindicatoController.setVisibleColumn("nome", true);
        sindicatoController.setFramePreferedSize(new Dimension(600, 500));

        sindicatoController.setLookupDataLocator(new LookupDataLocatorGenerico(sindicatoController.getLookupValueObjectClassName()));
        codLookupControlSindicato.setLookupController(sindicatoController);

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
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        labelControl9 = new org.openswing.swing.client.LabelControl();
        codLookupControlPessoa = new org.openswing.swing.client.CodLookupControl();
        textControl6 = new org.openswing.swing.client.TextControl();
        labelControl11 = new org.openswing.swing.client.LabelControl();
        codLookupControlTipo = new org.openswing.swing.client.CodLookupControl();
        textControl7 = new org.openswing.swing.client.TextControl();
        labelControl7 = new org.openswing.swing.client.LabelControl();
        codLookupControlSituacao = new org.openswing.swing.client.CodLookupControl();
        textControl5 = new org.openswing.swing.client.TextControl();
        labelControl5 = new org.openswing.swing.client.LabelControl();
        codLookupControlTipoAdmissao = new org.openswing.swing.client.CodLookupControl();
        textControl4 = new org.openswing.swing.client.TextControl();
        labelControl13 = new org.openswing.swing.client.LabelControl();
        codLookupControlNivelFormacao = new org.openswing.swing.client.CodLookupControl();
        textControl8 = new org.openswing.swing.client.TextControl();
        labelControl15 = new org.openswing.swing.client.LabelControl();
        codLookupControlCargo = new org.openswing.swing.client.CodLookupControl();
        textControl9 = new org.openswing.swing.client.TextControl();
        labelControl17 = new org.openswing.swing.client.LabelControl();
        codLookupControlSetor = new org.openswing.swing.client.CodLookupControl();
        textControl10 = new org.openswing.swing.client.TextControl();
        labelControl1 = new org.openswing.swing.client.LabelControl();
        codLookupControlContabilConta = new org.openswing.swing.client.CodLookupControl();
        textControl2 = new org.openswing.swing.client.TextControl();
        labelControl3 = new org.openswing.swing.client.LabelControl();
        codLookupControlSindicato = new org.openswing.swing.client.CodLookupControl();
        textControl3 = new org.openswing.swing.client.TextControl();
        labelControl21 = new org.openswing.swing.client.LabelControl();
        dateControl13 = new org.openswing.swing.client.DateControl();
        labelControl22 = new org.openswing.swing.client.LabelControl();
        dateControl14 = new org.openswing.swing.client.DateControl();
        labelControl23 = new org.openswing.swing.client.LabelControl();
        dateControl15 = new org.openswing.swing.client.DateControl();
        labelControl24 = new org.openswing.swing.client.LabelControl();
        dateControl16 = new org.openswing.swing.client.DateControl();
        labelControl53 = new org.openswing.swing.client.LabelControl();
        dateControl45 = new org.openswing.swing.client.DateControl();
        labelControl45 = new org.openswing.swing.client.LabelControl();
        comboBoxControl37 = new org.openswing.swing.client.ComboBoxControl();
        labelControl46 = new org.openswing.swing.client.LabelControl();
        comboBoxControl38 = new org.openswing.swing.client.ComboBoxControl();
        labelControl54 = new org.openswing.swing.client.LabelControl();
        textControl46 = new org.openswing.swing.client.TextControl();
        labelControl48 = new org.openswing.swing.client.LabelControl();
        textAreaControl1 = new org.openswing.swing.client.TextAreaControl();
        jSeparator1 = new javax.swing.JSeparator();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        labelControl34 = new org.openswing.swing.client.LabelControl();
        dateControl26 = new org.openswing.swing.client.DateControl();
        dateControl27 = new org.openswing.swing.client.DateControl();
        labelControl35 = new org.openswing.swing.client.LabelControl();
        jPanel5 = new javax.swing.JPanel();
        labelControl49 = new org.openswing.swing.client.LabelControl();
        numericControl41 = new org.openswing.swing.client.NumericControl();
        labelControl47 = new org.openswing.swing.client.LabelControl();
        textControl39 = new org.openswing.swing.client.TextControl();
        labelControl52 = new org.openswing.swing.client.LabelControl();
        numericControl44 = new org.openswing.swing.client.NumericControl();
        jPanel6 = new javax.swing.JPanel();
        labelControl50 = new org.openswing.swing.client.LabelControl();
        numericControl42 = new org.openswing.swing.client.NumericControl();
        labelControl51 = new org.openswing.swing.client.LabelControl();
        numericControl43 = new org.openswing.swing.client.NumericControl();
        jPanel7 = new javax.swing.JPanel();
        labelControl25 = new org.openswing.swing.client.LabelControl();
        comboBoxControl17 = new org.openswing.swing.client.ComboBoxControl();
        dateControl18 = new org.openswing.swing.client.DateControl();
        labelControl26 = new org.openswing.swing.client.LabelControl();
        numericControl19 = new org.openswing.swing.client.NumericControl();
        labelControl27 = new org.openswing.swing.client.LabelControl();
        jPanel8 = new javax.swing.JPanel();
        labelControl41 = new org.openswing.swing.client.LabelControl();
        textControl33 = new org.openswing.swing.client.TextControl();
        labelControl42 = new org.openswing.swing.client.LabelControl();
        textControl34 = new org.openswing.swing.client.TextControl();
        labelControl43 = new org.openswing.swing.client.LabelControl();
        dateControl35 = new org.openswing.swing.client.DateControl();
        labelControl44 = new org.openswing.swing.client.LabelControl();
        textControl36 = new org.openswing.swing.client.TextControl();
        jPanel9 = new javax.swing.JPanel();
        labelControl28 = new org.openswing.swing.client.LabelControl();
        comboBoxControl20 = new org.openswing.swing.client.ComboBoxControl();
        labelControl29 = new org.openswing.swing.client.LabelControl();
        textControl21 = new org.openswing.swing.client.TextControl();
        labelControl30 = new org.openswing.swing.client.LabelControl();
        textControl22 = new org.openswing.swing.client.TextControl();
        labelControl31 = new org.openswing.swing.client.LabelControl();
        labelControl32 = new org.openswing.swing.client.LabelControl();
        textControl24 = new org.openswing.swing.client.TextControl();
        labelControl33 = new org.openswing.swing.client.LabelControl();
        textControl25 = new org.openswing.swing.client.TextControl();
        textControl26 = new org.openswing.swing.client.TextControl();
        jPanel10 = new javax.swing.JPanel();
        labelControl36 = new org.openswing.swing.client.LabelControl();
        dateControl28 = new org.openswing.swing.client.DateControl();
        labelControl37 = new org.openswing.swing.client.LabelControl();
        textControl29 = new org.openswing.swing.client.TextControl();
        textControl30 = new org.openswing.swing.client.TextControl();
        labelControl38 = new org.openswing.swing.client.LabelControl();
        labelControl39 = new org.openswing.swing.client.LabelControl();
        textControl31 = new org.openswing.swing.client.TextControl();
        labelControl40 = new org.openswing.swing.client.LabelControl();
        textControl32 = new org.openswing.swing.client.TextControl();
        jSeparator2 = new javax.swing.JSeparator();

        setTitle("T2Ti ERP - Cadastros");
        setPreferredSize(new java.awt.Dimension(700, 400));
        getContentPane().setLayout(new java.awt.GridBagLayout());

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Colaborador"));
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

        form1.setVOClassName("com.t2tierp.cadastros.java.ColaboradorVO");
        form1.setEditButton(editButton1);
        form1.setFunctionId("colaborador");
        form1.setReloadButton(reloadButton1);
        form1.setSaveButton(saveButton1);
        form1.setLayout(new java.awt.CardLayout());

        jPanel2.setLayout(new java.awt.GridBagLayout());

        labelControl9.setLabel("Pessoa:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        jPanel2.add(labelControl9, gridBagConstraints);

        codLookupControlPessoa.setAllowOnlyNumbers(true);
        codLookupControlPessoa.setAttributeName("pessoa.id");
        codLookupControlPessoa.setEnabled(false);
        codLookupControlPessoa.setLinkLabel(labelControl9);
        codLookupControlPessoa.setRequired(true);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = -70;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        jPanel2.add(codLookupControlPessoa, gridBagConstraints);

        textControl6.setAttributeName("pessoa.nome");
        textControl6.setEnabled(false);
        textControl6.setEnabledOnEdit(false);
        textControl6.setEnabledOnInsert(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        jPanel2.add(textControl6, gridBagConstraints);

        labelControl11.setLabel("Tipo:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        jPanel2.add(labelControl11, gridBagConstraints);

        codLookupControlTipo.setAllowOnlyNumbers(true);
        codLookupControlTipo.setAttributeName("tipoColaborador.id");
        codLookupControlTipo.setEnabled(false);
        codLookupControlTipo.setLinkLabel(labelControl11);
        codLookupControlTipo.setRequired(true);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = -70;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        jPanel2.add(codLookupControlTipo, gridBagConstraints);

        textControl7.setAttributeName("tipoColaborador.nome");
        textControl7.setEnabled(false);
        textControl7.setEnabledOnEdit(false);
        textControl7.setEnabledOnInsert(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        jPanel2.add(textControl7, gridBagConstraints);

        labelControl7.setLabel("Situacao:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        jPanel2.add(labelControl7, gridBagConstraints);

        codLookupControlSituacao.setAllowOnlyNumbers(true);
        codLookupControlSituacao.setAttributeName("situacaoColaborador.id");
        codLookupControlSituacao.setEnabled(false);
        codLookupControlSituacao.setLinkLabel(labelControl7);
        codLookupControlSituacao.setRequired(true);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        jPanel2.add(codLookupControlSituacao, gridBagConstraints);

        textControl5.setAttributeName("situacaoColaborador.nome");
        textControl5.setEnabled(false);
        textControl5.setEnabledOnEdit(false);
        textControl5.setEnabledOnInsert(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        jPanel2.add(textControl5, gridBagConstraints);

        labelControl5.setLabel("Tipo Admissao:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        jPanel2.add(labelControl5, gridBagConstraints);

        codLookupControlTipoAdmissao.setAttributeName("idtipoadmissao.id");
        codLookupControlTipoAdmissao.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        jPanel2.add(codLookupControlTipoAdmissao, gridBagConstraints);

        textControl4.setAttributeName("tipoAdmissao.nome");
        textControl4.setEnabled(false);
        textControl4.setEnabledOnEdit(false);
        textControl4.setEnabledOnInsert(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        jPanel2.add(textControl4, gridBagConstraints);

        labelControl13.setLabel("Nivel Formacao:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        jPanel2.add(labelControl13, gridBagConstraints);

        codLookupControlNivelFormacao.setAllowOnlyNumbers(true);
        codLookupControlNivelFormacao.setAttributeName("nivelFormacao.id");
        codLookupControlNivelFormacao.setEnabled(false);
        codLookupControlNivelFormacao.setLinkLabel(labelControl13);
        codLookupControlNivelFormacao.setRequired(true);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        jPanel2.add(codLookupControlNivelFormacao, gridBagConstraints);

        textControl8.setAttributeName("nivelFormacao.nome");
        textControl8.setEnabled(false);
        textControl8.setEnabledOnEdit(false);
        textControl8.setEnabledOnInsert(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        jPanel2.add(textControl8, gridBagConstraints);

        labelControl15.setLabel("Cargo:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        jPanel2.add(labelControl15, gridBagConstraints);

        codLookupControlCargo.setAllowOnlyNumbers(true);
        codLookupControlCargo.setAttributeName("cargo.id");
        codLookupControlCargo.setEnabled(false);
        codLookupControlCargo.setLinkLabel(labelControl15);
        codLookupControlCargo.setRequired(true);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        jPanel2.add(codLookupControlCargo, gridBagConstraints);

        textControl9.setAttributeName("cargo.nome");
        textControl9.setEnabled(false);
        textControl9.setEnabledOnEdit(false);
        textControl9.setEnabledOnInsert(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        jPanel2.add(textControl9, gridBagConstraints);

        labelControl17.setLabel("Setor:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        jPanel2.add(labelControl17, gridBagConstraints);

        codLookupControlSetor.setAllowOnlyNumbers(true);
        codLookupControlSetor.setAttributeName("setor.id");
        codLookupControlSetor.setEnabled(false);
        codLookupControlSetor.setLinkLabel(labelControl17);
        codLookupControlSetor.setRequired(true);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        jPanel2.add(codLookupControlSetor, gridBagConstraints);

        textControl10.setAttributeName("setor.nome");
        textControl10.setEnabled(false);
        textControl10.setEnabledOnEdit(false);
        textControl10.setEnabledOnInsert(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        jPanel2.add(textControl10, gridBagConstraints);

        labelControl1.setLabel("Contabil Conta:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        jPanel2.add(labelControl1, gridBagConstraints);

        codLookupControlContabilConta.setAllowOnlyNumbers(true);
        codLookupControlContabilConta.setAttributeName("contabilConta.id");
        codLookupControlContabilConta.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        jPanel2.add(codLookupControlContabilConta, gridBagConstraints);

        textControl2.setAttributeName("contabilConta.descricao");
        textControl2.setEnabled(false);
        textControl2.setEnabledOnEdit(false);
        textControl2.setEnabledOnInsert(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        jPanel2.add(textControl2, gridBagConstraints);

        labelControl3.setLabel("Sindicato:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        jPanel2.add(labelControl3, gridBagConstraints);

        codLookupControlSindicato.setAllowOnlyNumbers(true);
        codLookupControlSindicato.setAttributeName("sindicato.id");
        codLookupControlSindicato.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 9;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        jPanel2.add(codLookupControlSindicato, gridBagConstraints);

        textControl3.setAttributeName("sindicato.nome");
        textControl3.setEnabled(false);
        textControl3.setEnabledOnEdit(false);
        textControl3.setEnabledOnInsert(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 9;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        jPanel2.add(textControl3, gridBagConstraints);

        labelControl21.setLabel("Data Cadastro:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        jPanel2.add(labelControl21, gridBagConstraints);

        dateControl13.setAttributeName("dataCadastro");
        dateControl13.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 9;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        jPanel2.add(dateControl13, gridBagConstraints);

        labelControl22.setLabel("Data Admissao:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        jPanel2.add(labelControl22, gridBagConstraints);

        dateControl14.setAttributeName("dataAdmissao");
        dateControl14.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 9;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        jPanel2.add(dateControl14, gridBagConstraints);

        labelControl23.setLabel("Vencimento Ferias:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        jPanel2.add(labelControl23, gridBagConstraints);

        dateControl15.setAttributeName("vencimentoFerias");
        dateControl15.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 11;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        jPanel2.add(dateControl15, gridBagConstraints);

        labelControl24.setLabel("Data Transferencia:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        jPanel2.add(labelControl24, gridBagConstraints);

        dateControl16.setAttributeName("dataTransferencia");
        dateControl16.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 11;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        jPanel2.add(dateControl16, gridBagConstraints);

        labelControl53.setLabel("Data Demissao:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        jPanel2.add(labelControl53, gridBagConstraints);

        dateControl45.setAttributeName("dataDemissao");
        dateControl45.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 11;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        jPanel2.add(dateControl45, gridBagConstraints);

        labelControl45.setLabel("Desconto Plano Saude:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        jPanel2.add(labelControl45, gridBagConstraints);

        comboBoxControl37.setAttributeName("descontoPlanoSaude");
        comboBoxControl37.setDomainId("simnao");
        comboBoxControl37.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 11;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        jPanel2.add(comboBoxControl37, gridBagConstraints);

        labelControl46.setLabel("Sai Na Rais:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        jPanel2.add(labelControl46, gridBagConstraints);

        comboBoxControl38.setAttributeName("saiNaRais");
        comboBoxControl38.setDomainId("simnao");
        comboBoxControl38.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 11;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        jPanel2.add(comboBoxControl38, gridBagConstraints);

        labelControl54.setLabel("Codigo Turma Ponto:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        jPanel2.add(labelControl54, gridBagConstraints);

        textControl46.setAttributeName("codigoTurmaPonto");
        textControl46.setEnabled(false);
        textControl46.setMaxCharacters(5);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 11;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        jPanel2.add(textControl46, gridBagConstraints);

        labelControl48.setLabel("Observacao:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 12;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        jPanel2.add(labelControl48, gridBagConstraints);

        textAreaControl1.setAttributeName("observacao");
        textAreaControl1.setEnabled(false);
        textAreaControl1.setMaxCharacters(1000);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 13;
        gridBagConstraints.gridwidth = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipady = 50;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        jPanel2.add(textAreaControl1, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 14;
        gridBagConstraints.gridwidth = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel2.add(jSeparator1, gridBagConstraints);

        jTabbedPane1.addTab("Dados Principais", jPanel2);

        jPanel3.setLayout(new java.awt.GridBagLayout());

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Exame Medico"));
        jPanel4.setLayout(new java.awt.GridBagLayout());

        labelControl34.setLabel("Exame Medico Ultimo:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        jPanel4.add(labelControl34, gridBagConstraints);

        dateControl26.setAttributeName("exameMedicoUltimo");
        dateControl26.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        jPanel4.add(dateControl26, gridBagConstraints);

        dateControl27.setAttributeName("exameMedicoVencimento");
        dateControl27.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        jPanel4.add(dateControl27, gridBagConstraints);

        labelControl35.setLabel("Exame Medico Vencimento:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        jPanel4.add(labelControl35, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        jPanel3.add(jPanel4, gridBagConstraints);

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder("Sefip"));
        jPanel5.setLayout(new java.awt.GridBagLayout());

        labelControl49.setLabel("Ocorrencia Sefip:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        jPanel5.add(labelControl49, gridBagConstraints);

        numericControl41.setAttributeName("ocorrenciaSefip");
        numericControl41.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        jPanel5.add(numericControl41, gridBagConstraints);

        labelControl47.setLabel("Categoria Sefip:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        jPanel5.add(labelControl47, gridBagConstraints);

        textControl39.setAttributeName("categoriaSefip");
        textControl39.setEnabled(false);
        textControl39.setMaxCharacters(2);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        jPanel5.add(textControl39, gridBagConstraints);

        labelControl52.setLabel("Codigo Demissao Sefip:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        jPanel5.add(labelControl52, gridBagConstraints);

        numericControl44.setAttributeName("codigoDemissaoSefip");
        numericControl44.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        jPanel5.add(numericControl44, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        jPanel3.add(jPanel5, gridBagConstraints);

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder("Caged"));
        jPanel6.setLayout(new java.awt.GridBagLayout());

        labelControl50.setLabel("Codigo Admissao Caged:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        jPanel6.add(labelControl50, gridBagConstraints);

        numericControl42.setAttributeName("codigoAdmissaoCaged");
        numericControl42.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        jPanel6.add(numericControl42, gridBagConstraints);

        labelControl51.setLabel("Codigo Demissao Caged:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        jPanel6.add(labelControl51, gridBagConstraints);

        numericControl43.setAttributeName("codigoDemissaoCaged");
        numericControl43.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        jPanel6.add(numericControl43, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        jPanel3.add(jPanel6, gridBagConstraints);

        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder("FGTS"));
        jPanel7.setLayout(new java.awt.GridBagLayout());

        labelControl25.setLabel("Fgts Optante:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        jPanel7.add(labelControl25, gridBagConstraints);

        comboBoxControl17.setAttributeName("fgtsOptante");
        comboBoxControl17.setDomainId("simnao");
        comboBoxControl17.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        jPanel7.add(comboBoxControl17, gridBagConstraints);

        dateControl18.setAttributeName("fgtsDataOpcao");
        dateControl18.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        jPanel7.add(dateControl18, gridBagConstraints);

        labelControl26.setLabel("Fgts Data Opcao:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        jPanel7.add(labelControl26, gridBagConstraints);

        numericControl19.setAttributeName("fgtsConta");
        numericControl19.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        jPanel7.add(numericControl19, gridBagConstraints);

        labelControl27.setLabel("Fgts Conta:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        jPanel7.add(labelControl27, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        jPanel3.add(jPanel7, gridBagConstraints);

        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder("Carteira Profissional"));
        jPanel8.setLayout(new java.awt.GridBagLayout());

        labelControl41.setLabel("Ctps Numero:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        jPanel8.add(labelControl41, gridBagConstraints);

        textControl33.setAttributeName("ctpsNumero");
        textControl33.setEnabled(false);
        textControl33.setMaxCharacters(10);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        jPanel8.add(textControl33, gridBagConstraints);

        labelControl42.setLabel("Ctps Serie:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        jPanel8.add(labelControl42, gridBagConstraints);

        textControl34.setAttributeName("ctpsSerie");
        textControl34.setEnabled(false);
        textControl34.setMaxCharacters(10);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        jPanel8.add(textControl34, gridBagConstraints);

        labelControl43.setLabel("Ctps Data Expedicao:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        jPanel8.add(labelControl43, gridBagConstraints);

        dateControl35.setAttributeName("ctpsDataExpedicao");
        dateControl35.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        jPanel8.add(dateControl35, gridBagConstraints);

        labelControl44.setLabel("Ctps Uf:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        jPanel8.add(labelControl44, gridBagConstraints);

        textControl36.setAttributeName("ctpsUf");
        textControl36.setEnabled(false);
        textControl36.setMaxCharacters(2);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        jPanel8.add(textControl36, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        jPanel3.add(jPanel8, gridBagConstraints);

        jPanel9.setBorder(javax.swing.BorderFactory.createTitledBorder("Pagamento"));
        jPanel9.setLayout(new java.awt.GridBagLayout());

        labelControl28.setLabel("Pagamento Forma:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        jPanel9.add(labelControl28, gridBagConstraints);

        comboBoxControl20.setAttributeName("pagamentoForma");
        comboBoxControl20.setDomainId("colaboradorFormaPagamento");
        comboBoxControl20.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        jPanel9.add(comboBoxControl20, gridBagConstraints);

        labelControl29.setLabel("Banco:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        jPanel9.add(labelControl29, gridBagConstraints);

        textControl21.setAttributeName("pagamentoBanco");
        textControl21.setEnabled(false);
        textControl21.setMaxCharacters(10);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        jPanel9.add(textControl21, gridBagConstraints);

        labelControl30.setLabel("Agencia:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        jPanel9.add(labelControl30, gridBagConstraints);

        textControl22.setAttributeName("pagamentoAgencia");
        textControl22.setEnabled(false);
        textControl22.setMaxCharacters(10);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        jPanel9.add(textControl22, gridBagConstraints);

        labelControl31.setLabel("DV:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        jPanel9.add(labelControl31, gridBagConstraints);

        labelControl32.setLabel("Conta:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        jPanel9.add(labelControl32, gridBagConstraints);

        textControl24.setAttributeName("pagamentoConta");
        textControl24.setEnabled(false);
        textControl24.setMaxCharacters(10);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        jPanel9.add(textControl24, gridBagConstraints);

        labelControl33.setLabel("DV:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        jPanel9.add(labelControl33, gridBagConstraints);

        textControl25.setAttributeName("pagamentoConta");
        textControl25.setEnabled(false);
        textControl25.setMaxCharacters(1);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        jPanel9.add(textControl25, gridBagConstraints);

        textControl26.setAttributeName("pagamentoConta");
        textControl26.setEnabled(false);
        textControl26.setMaxCharacters(1);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        jPanel9.add(textControl26, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        jPanel3.add(jPanel9, gridBagConstraints);

        jPanel10.setBorder(javax.swing.BorderFactory.createTitledBorder("PIS"));
        jPanel10.setLayout(new java.awt.GridBagLayout());

        labelControl36.setLabel("Pis Data Cadastro:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        jPanel10.add(labelControl36, gridBagConstraints);

        dateControl28.setAttributeName("pisDataCadastro");
        dateControl28.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        jPanel10.add(dateControl28, gridBagConstraints);

        labelControl37.setLabel("Pis Numero:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        jPanel10.add(labelControl37, gridBagConstraints);

        textControl29.setAttributeName("pisNumero");
        textControl29.setEnabled(false);
        textControl29.setMaxCharacters(12);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        jPanel10.add(textControl29, gridBagConstraints);

        textControl30.setAttributeName("pisBanco");
        textControl30.setEnabled(false);
        textControl30.setMaxCharacters(10);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        jPanel10.add(textControl30, gridBagConstraints);

        labelControl38.setLabel("Pis Banco:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        jPanel10.add(labelControl38, gridBagConstraints);

        labelControl39.setLabel("Pis Agencia:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        jPanel10.add(labelControl39, gridBagConstraints);

        textControl31.setAttributeName("pisAgencia");
        textControl31.setEnabled(false);
        textControl31.setMaxCharacters(10);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        jPanel10.add(textControl31, gridBagConstraints);

        labelControl40.setLabel("Pis Agencia Digito:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        jPanel10.add(labelControl40, gridBagConstraints);

        textControl32.setAttributeName("pisAgencia");
        textControl32.setEnabled(false);
        textControl32.setMaxCharacters(1);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        jPanel10.add(textControl32, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        jPanel3.add(jPanel10, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel3.add(jSeparator2, gridBagConstraints);

        jTabbedPane1.addTab("Outras Informações", jPanel3);

        form1.add(jTabbedPane1, "card2");

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
    private org.openswing.swing.client.CodLookupControl codLookupControlCargo;
    private org.openswing.swing.client.CodLookupControl codLookupControlContabilConta;
    private org.openswing.swing.client.CodLookupControl codLookupControlNivelFormacao;
    private org.openswing.swing.client.CodLookupControl codLookupControlPessoa;
    private org.openswing.swing.client.CodLookupControl codLookupControlSetor;
    private org.openswing.swing.client.CodLookupControl codLookupControlSindicato;
    private org.openswing.swing.client.CodLookupControl codLookupControlSituacao;
    private org.openswing.swing.client.CodLookupControl codLookupControlTipo;
    private org.openswing.swing.client.CodLookupControl codLookupControlTipoAdmissao;
    private org.openswing.swing.client.ComboBoxControl comboBoxControl17;
    private org.openswing.swing.client.ComboBoxControl comboBoxControl20;
    private org.openswing.swing.client.ComboBoxControl comboBoxControl37;
    private org.openswing.swing.client.ComboBoxControl comboBoxControl38;
    private org.openswing.swing.client.DateControl dateControl13;
    private org.openswing.swing.client.DateControl dateControl14;
    private org.openswing.swing.client.DateControl dateControl15;
    private org.openswing.swing.client.DateControl dateControl16;
    private org.openswing.swing.client.DateControl dateControl18;
    private org.openswing.swing.client.DateControl dateControl26;
    private org.openswing.swing.client.DateControl dateControl27;
    private org.openswing.swing.client.DateControl dateControl28;
    private org.openswing.swing.client.DateControl dateControl35;
    private org.openswing.swing.client.DateControl dateControl45;
    private org.openswing.swing.client.EditButton editButton1;
    private org.openswing.swing.form.client.Form form1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private org.openswing.swing.client.LabelControl labelControl1;
    private org.openswing.swing.client.LabelControl labelControl11;
    private org.openswing.swing.client.LabelControl labelControl13;
    private org.openswing.swing.client.LabelControl labelControl15;
    private org.openswing.swing.client.LabelControl labelControl17;
    private org.openswing.swing.client.LabelControl labelControl21;
    private org.openswing.swing.client.LabelControl labelControl22;
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
    private org.openswing.swing.client.LabelControl labelControl33;
    private org.openswing.swing.client.LabelControl labelControl34;
    private org.openswing.swing.client.LabelControl labelControl35;
    private org.openswing.swing.client.LabelControl labelControl36;
    private org.openswing.swing.client.LabelControl labelControl37;
    private org.openswing.swing.client.LabelControl labelControl38;
    private org.openswing.swing.client.LabelControl labelControl39;
    private org.openswing.swing.client.LabelControl labelControl40;
    private org.openswing.swing.client.LabelControl labelControl41;
    private org.openswing.swing.client.LabelControl labelControl42;
    private org.openswing.swing.client.LabelControl labelControl43;
    private org.openswing.swing.client.LabelControl labelControl44;
    private org.openswing.swing.client.LabelControl labelControl45;
    private org.openswing.swing.client.LabelControl labelControl46;
    private org.openswing.swing.client.LabelControl labelControl47;
    private org.openswing.swing.client.LabelControl labelControl48;
    private org.openswing.swing.client.LabelControl labelControl49;
    private org.openswing.swing.client.LabelControl labelControl5;
    private org.openswing.swing.client.LabelControl labelControl50;
    private org.openswing.swing.client.LabelControl labelControl51;
    private org.openswing.swing.client.LabelControl labelControl52;
    private org.openswing.swing.client.LabelControl labelControl53;
    private org.openswing.swing.client.LabelControl labelControl54;
    private org.openswing.swing.client.LabelControl labelControl7;
    private org.openswing.swing.client.LabelControl labelControl9;
    private org.openswing.swing.client.NumericControl numericControl19;
    private org.openswing.swing.client.NumericControl numericControl41;
    private org.openswing.swing.client.NumericControl numericControl42;
    private org.openswing.swing.client.NumericControl numericControl43;
    private org.openswing.swing.client.NumericControl numericControl44;
    private org.openswing.swing.client.ReloadButton reloadButton1;
    private org.openswing.swing.client.SaveButton saveButton1;
    private org.openswing.swing.client.TextAreaControl textAreaControl1;
    private org.openswing.swing.client.TextControl textControl10;
    private org.openswing.swing.client.TextControl textControl2;
    private org.openswing.swing.client.TextControl textControl21;
    private org.openswing.swing.client.TextControl textControl22;
    private org.openswing.swing.client.TextControl textControl24;
    private org.openswing.swing.client.TextControl textControl25;
    private org.openswing.swing.client.TextControl textControl26;
    private org.openswing.swing.client.TextControl textControl29;
    private org.openswing.swing.client.TextControl textControl3;
    private org.openswing.swing.client.TextControl textControl30;
    private org.openswing.swing.client.TextControl textControl31;
    private org.openswing.swing.client.TextControl textControl32;
    private org.openswing.swing.client.TextControl textControl33;
    private org.openswing.swing.client.TextControl textControl34;
    private org.openswing.swing.client.TextControl textControl36;
    private org.openswing.swing.client.TextControl textControl39;
    private org.openswing.swing.client.TextControl textControl4;
    private org.openswing.swing.client.TextControl textControl46;
    private org.openswing.swing.client.TextControl textControl5;
    private org.openswing.swing.client.TextControl textControl6;
    private org.openswing.swing.client.TextControl textControl7;
    private org.openswing.swing.client.TextControl textControl8;
    private org.openswing.swing.client.TextControl textControl9;
    // End of variables declaration//GEN-END:variables

}
