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
import javax.swing.text.MaskFormatter;
import org.openswing.swing.lookup.client.LookupController;
import org.openswing.swing.mdi.client.InternalFrame;

public class PessoaDetalhe extends InternalFrame {

    private PessoaFisicaDetalheController pessoaFisicaController;
    private PessoaJuridicaDetalheController pessoaJuridicaController;
    private PessoaContatoGridController contatoController;
    private PessoaEnderecoGridController enderecoController;
    private PessoaTelefoneGridController telefoneController;
    private LookupController estadoCivilController = new LookupController();

    public PessoaDetalhe(PessoaDetalheController controller) {
        initComponents();

        formattedTextControlCpf.setEnabled(false);
        formattedTextControlCnpj.setEnabled(false);
        try {
            MaskFormatter mask = new MaskFormatter("###.###.###-##");
            mask.setValidCharacters("0123456789");
            mask.setValueContainsLiteralCharacters(false);
            formattedTextControlCpf.setFormatter(mask);

            mask = new MaskFormatter("##.###.###/####-##");
            mask.setValidCharacters("0123456789");
            mask.setValueContainsLiteralCharacters(false);
            formattedTextControlCnpj.setFormatter(mask);

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        form1.setFormController(controller);

        pessoaFisicaController = new PessoaFisicaDetalheController();
        formPessoaFisica.setFormController(pessoaFisicaController);

        pessoaJuridicaController = new PessoaJuridicaDetalheController();
        formPessoaJuridica.setFormController(pessoaJuridicaController);

        contatoController = new PessoaContatoGridController(this);
        gridControlContatos.setController(contatoController);
        gridControlContatos.setGridDataLocator(contatoController);

        enderecoController = new PessoaEnderecoGridController(this);
        gridControlEndereco.setController(enderecoController);
        gridControlEndereco.setGridDataLocator(enderecoController);

        telefoneController = new PessoaTelefoneGridController(this);
        gridControlTelefone.setController(telefoneController);
        gridControlTelefone.setGridDataLocator(telefoneController);
        
        /*
         * Configurações do lookup do estado civil
         */
        estadoCivilController.setLookupValueObjectClassName("com.t2tierp.cadastros.java.EstadoCivilVO");
        estadoCivilController.addLookup2ParentLink("id", "estadoCivil.id");
        estadoCivilController.addLookup2ParentLink("nome", "estadoCivil.nome");
        estadoCivilController.setHeaderColumnName("id", "ID");
        estadoCivilController.setHeaderColumnName("nome", "Nome");
        estadoCivilController.setFrameTitle("Importa Estado Civil");

        estadoCivilController.setVisibleStatusPanel(true);
        estadoCivilController.setVisibleColumn("id", true);
        estadoCivilController.setVisibleColumn("nome", true);
        estadoCivilController.setFramePreferedSize(new Dimension(600, 500));

        estadoCivilController.setLookupDataLocator(new LookupDataLocatorGenerico(estadoCivilController.getLookupValueObjectClassName()));
        codLookupControl3.setLookupController(estadoCivilController);


    }

    /**
     * @return the form1
     */
    public org.openswing.swing.form.client.Form getForm1() {
        return form1;
    }

    public PessoaFisicaDetalheController getPessoaFisicaController() {
        return pessoaFisicaController;
    }

    public PessoaJuridicaDetalheController getPessoaJuridicaController() {
        return pessoaJuridicaController;
    }

    public PessoaContatoGridController getContatoController() {
        return contatoController;
    }

    public PessoaEnderecoGridController getEnderecoController() {
        return enderecoController;
    }

    public PessoaTelefoneGridController getTelefoneController() {
        return telefoneController;
    }
    
    public org.openswing.swing.form.client.Form getFormPessoaFisica() {
        return formPessoaFisica;
    }

    public org.openswing.swing.form.client.Form getFormPessoaJuridica() {
        return formPessoaJuridica;
    }

    public org.openswing.swing.client.GridControl getGridControlContatos() {
        return gridControlContatos;
    }

    public org.openswing.swing.client.GridControl getGridControlEndereco() {
        return gridControlEndereco;
    }

    public org.openswing.swing.client.GridControl getGridControlTelefone() {
        return gridControlTelefone;
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
        textControl2 = new org.openswing.swing.client.TextControl();
        labelControl2 = new org.openswing.swing.client.LabelControl();
        comboBoxControl3 = new org.openswing.swing.client.ComboBoxControl();
        labelControl3 = new org.openswing.swing.client.LabelControl();
        textControl4 = new org.openswing.swing.client.TextControl();
        labelControl4 = new org.openswing.swing.client.LabelControl();
        textControl5 = new org.openswing.swing.client.TextControl();
        jPanel2 = new javax.swing.JPanel();
        labelControl5 = new org.openswing.swing.client.LabelControl();
        comboBoxControl6 = new org.openswing.swing.client.ComboBoxControl();
        labelControl6 = new org.openswing.swing.client.LabelControl();
        comboBoxControl7 = new org.openswing.swing.client.ComboBoxControl();
        labelControl7 = new org.openswing.swing.client.LabelControl();
        comboBoxControl8 = new org.openswing.swing.client.ComboBoxControl();
        labelControl10 = new org.openswing.swing.client.LabelControl();
        comboBoxControl11 = new org.openswing.swing.client.ComboBoxControl();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel3 = new javax.swing.JPanel();
        formPessoaFisica = new org.openswing.swing.form.client.Form();
        labelControl11 = new org.openswing.swing.client.LabelControl();
        codLookupControl3 = new org.openswing.swing.client.CodLookupControl();
        labelControl12 = new org.openswing.swing.client.LabelControl();
        labelControl13 = new org.openswing.swing.client.LabelControl();
        dateControl8 = new org.openswing.swing.client.DateControl();
        labelControl14 = new org.openswing.swing.client.LabelControl();
        comboBoxControl12 = new org.openswing.swing.client.ComboBoxControl();
        labelControl15 = new org.openswing.swing.client.LabelControl();
        textControl10 = new org.openswing.swing.client.TextControl();
        labelControl16 = new org.openswing.swing.client.LabelControl();
        textControl11 = new org.openswing.swing.client.TextControl();
        labelControl17 = new org.openswing.swing.client.LabelControl();
        comboBoxControl13 = new org.openswing.swing.client.ComboBoxControl();
        labelControl18 = new org.openswing.swing.client.LabelControl();
        labelControl23 = new org.openswing.swing.client.LabelControl();
        textControl22 = new org.openswing.swing.client.TextControl();
        labelControl24 = new org.openswing.swing.client.LabelControl();
        textControl23 = new org.openswing.swing.client.TextControl();
        jPanel5 = new javax.swing.JPanel();
        labelControl19 = new org.openswing.swing.client.LabelControl();
        textControl6 = new org.openswing.swing.client.TextControl();
        labelControl20 = new org.openswing.swing.client.LabelControl();
        textControl7 = new org.openswing.swing.client.TextControl();
        dateControl7 = new org.openswing.swing.client.DateControl();
        labelControl21 = new org.openswing.swing.client.LabelControl();
        jPanel6 = new javax.swing.JPanel();
        labelControl22 = new org.openswing.swing.client.LabelControl();
        textControl20 = new org.openswing.swing.client.TextControl();
        labelControl25 = new org.openswing.swing.client.LabelControl();
        numericControl21 = new org.openswing.swing.client.NumericControl();
        comboBoxControl14 = new org.openswing.swing.client.ComboBoxControl();
        jPanel7 = new javax.swing.JPanel();
        labelControl26 = new org.openswing.swing.client.LabelControl();
        textControl14 = new org.openswing.swing.client.TextControl();
        textControl15 = new org.openswing.swing.client.TextControl();
        labelControl27 = new org.openswing.swing.client.LabelControl();
        dateControl16 = new org.openswing.swing.client.DateControl();
        labelControl28 = new org.openswing.swing.client.LabelControl();
        jPanel8 = new javax.swing.JPanel();
        labelControl29 = new org.openswing.swing.client.LabelControl();
        textControl17 = new org.openswing.swing.client.TextControl();
        labelControl30 = new org.openswing.swing.client.LabelControl();
        numericControl18 = new org.openswing.swing.client.NumericControl();
        labelControl31 = new org.openswing.swing.client.LabelControl();
        numericControl19 = new org.openswing.swing.client.NumericControl();
        formattedTextControlCpf = new org.openswing.swing.client.FormattedTextControl();
        jSeparator1 = new javax.swing.JSeparator();
        formPessoaJuridica = new org.openswing.swing.form.client.Form();
        labelControl32 = new org.openswing.swing.client.LabelControl();
        labelControl33 = new org.openswing.swing.client.LabelControl();
        textControl8 = new org.openswing.swing.client.TextControl();
        labelControl34 = new org.openswing.swing.client.LabelControl();
        textControl9 = new org.openswing.swing.client.TextControl();
        labelControl35 = new org.openswing.swing.client.LabelControl();
        textControl12 = new org.openswing.swing.client.TextControl();
        labelControl36 = new org.openswing.swing.client.LabelControl();
        dateControl9 = new org.openswing.swing.client.DateControl();
        labelControl37 = new org.openswing.swing.client.LabelControl();
        comboBoxControl15 = new org.openswing.swing.client.ComboBoxControl();
        labelControl38 = new org.openswing.swing.client.LabelControl();
        comboBoxControl16 = new org.openswing.swing.client.ComboBoxControl();
        labelControl39 = new org.openswing.swing.client.LabelControl();
        textControl13 = new org.openswing.swing.client.TextControl();
        formattedTextControlCnpj = new org.openswing.swing.client.FormattedTextControl();
        jSeparator2 = new javax.swing.JSeparator();
        jPanel4 = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        insertButton1 = new org.openswing.swing.client.InsertButton();
        editButton2 = new org.openswing.swing.client.EditButton();
        deleteButton1 = new org.openswing.swing.client.DeleteButton();
        saveButton2 = new org.openswing.swing.client.SaveButton();
        reloadButton2 = new org.openswing.swing.client.ReloadButton();
        navigatorBar1 = new org.openswing.swing.client.NavigatorBar();
        gridControlContatos = new org.openswing.swing.client.GridControl();
        textColumn4 = new org.openswing.swing.table.columns.client.TextColumn();
        textColumn5 = new org.openswing.swing.table.columns.client.TextColumn();
        textColumn6 = new org.openswing.swing.table.columns.client.TextColumn();
        textColumn7 = new org.openswing.swing.table.columns.client.TextColumn();
        textColumn8 = new org.openswing.swing.table.columns.client.TextColumn();
        jPanel9 = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        insertButton2 = new org.openswing.swing.client.InsertButton();
        editButton3 = new org.openswing.swing.client.EditButton();
        deleteButton2 = new org.openswing.swing.client.DeleteButton();
        saveButton3 = new org.openswing.swing.client.SaveButton();
        reloadButton3 = new org.openswing.swing.client.ReloadButton();
        navigatorBar2 = new org.openswing.swing.client.NavigatorBar();
        gridControlEndereco = new org.openswing.swing.client.GridControl();
        textColumn9 = new org.openswing.swing.table.columns.client.TextColumn();
        textColumn10 = new org.openswing.swing.table.columns.client.TextColumn();
        textColumn11 = new org.openswing.swing.table.columns.client.TextColumn();
        textColumn12 = new org.openswing.swing.table.columns.client.TextColumn();
        textColumn13 = new org.openswing.swing.table.columns.client.TextColumn();
        textColumn14 = new org.openswing.swing.table.columns.client.TextColumn();
        integerColumn10 = new org.openswing.swing.table.columns.client.IntegerColumn();
        textColumn15 = new org.openswing.swing.table.columns.client.TextColumn();
        textColumn16 = new org.openswing.swing.table.columns.client.TextColumn();
        comboColumn14 = new org.openswing.swing.table.columns.client.ComboColumn();
        comboColumn15 = new org.openswing.swing.table.columns.client.ComboColumn();
        comboColumn16 = new org.openswing.swing.table.columns.client.ComboColumn();
        comboColumn17 = new org.openswing.swing.table.columns.client.ComboColumn();
        jPanel12 = new javax.swing.JPanel();
        jPanel13 = new javax.swing.JPanel();
        insertButton3 = new org.openswing.swing.client.InsertButton();
        editButton4 = new org.openswing.swing.client.EditButton();
        deleteButton3 = new org.openswing.swing.client.DeleteButton();
        saveButton4 = new org.openswing.swing.client.SaveButton();
        reloadButton4 = new org.openswing.swing.client.ReloadButton();
        navigatorBar3 = new org.openswing.swing.client.NavigatorBar();
        gridControlTelefone = new org.openswing.swing.client.GridControl();
        comboColumn1 = new org.openswing.swing.table.columns.client.ComboColumn();
        textColumn19 = new org.openswing.swing.table.columns.client.TextColumn();
        textColumn18 = new org.openswing.swing.table.columns.client.TextColumn();

        setTitle("T2Ti ERP - Cadastros");
        setPreferredSize(new java.awt.Dimension(700, 400));
        getContentPane().setLayout(new java.awt.GridBagLayout());

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Pessoa"));
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

        form1.setVOClassName("com.t2tierp.cadastros.java.PessoaVO");
        form1.setEditButton(editButton1);
        form1.setFunctionId("pessoa");
        form1.setReloadButton(reloadButton1);
        form1.setSaveButton(saveButton1);
        form1.setLayout(new java.awt.GridBagLayout());

        labelControl1.setLabel("Nome:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        form1.add(labelControl1, gridBagConstraints);

        textControl2.setAttributeName("nome");
        textControl2.setEnabled(false);
        textControl2.setMaxCharacters(150);
        textControl2.setRequired(true);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 1;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        form1.add(textControl2, gridBagConstraints);

        labelControl2.setLabel("Tipo:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        form1.add(labelControl2, gridBagConstraints);

        comboBoxControl3.setAttributeName("tipo");
        comboBoxControl3.setDomainId("tipoPessoa");
        comboBoxControl3.setEnabled(false);
        comboBoxControl3.setEnabledOnEdit(false);
        comboBoxControl3.setLinkLabel(labelControl2);
        comboBoxControl3.setRequired(true);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        form1.add(comboBoxControl3, gridBagConstraints);

        labelControl3.setLabel("Email:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        form1.add(labelControl3, gridBagConstraints);

        textControl4.setAttributeName("email");
        textControl4.setEnabled(false);
        textControl4.setMaxCharacters(250);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        form1.add(textControl4, gridBagConstraints);

        labelControl4.setLabel("Site:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        form1.add(labelControl4, gridBagConstraints);

        textControl5.setAttributeName("site");
        textControl5.setEnabled(false);
        textControl5.setMaxCharacters(250);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        form1.add(textControl5, gridBagConstraints);

        jPanel2.setLayout(new java.awt.GridBagLayout());

        labelControl5.setLabel("Cliente:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        jPanel2.add(labelControl5, gridBagConstraints);

        comboBoxControl6.setAttributeName("cliente");
        comboBoxControl6.setDomainId("naosim");
        comboBoxControl6.setEnabled(false);
        comboBoxControl6.setLinkLabel(labelControl5);
        comboBoxControl6.setRequired(true);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        jPanel2.add(comboBoxControl6, gridBagConstraints);

        labelControl6.setLabel("Fornecedor:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        jPanel2.add(labelControl6, gridBagConstraints);

        comboBoxControl7.setAttributeName("fornecedor");
        comboBoxControl7.setDomainId("naosim");
        comboBoxControl7.setEnabled(false);
        comboBoxControl7.setLinkLabel(labelControl6);
        comboBoxControl7.setRequired(true);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        jPanel2.add(comboBoxControl7, gridBagConstraints);

        labelControl7.setLabel("Colaborador:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        jPanel2.add(labelControl7, gridBagConstraints);

        comboBoxControl8.setAttributeName("colaborador");
        comboBoxControl8.setDomainId("naosim");
        comboBoxControl8.setEnabled(false);
        comboBoxControl8.setLinkLabel(labelControl7);
        comboBoxControl8.setRequired(true);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        jPanel2.add(comboBoxControl8, gridBagConstraints);

        labelControl10.setLabel("Transportadora:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        jPanel2.add(labelControl10, gridBagConstraints);

        comboBoxControl11.setAttributeName("transportadora");
        comboBoxControl11.setDomainId("naosim");
        comboBoxControl11.setEnabled(false);
        comboBoxControl11.setLinkLabel(labelControl10);
        comboBoxControl11.setRequired(true);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        jPanel2.add(comboBoxControl11, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        form1.add(jPanel2, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        getContentPane().add(form1, gridBagConstraints);

        jPanel3.setLayout(new java.awt.GridBagLayout());

        formPessoaFisica.setBorder(javax.swing.BorderFactory.createTitledBorder("Pessoa Fisica"));
        formPessoaFisica.setVOClassName("com.t2tierp.cadastros.java.PessoaFisicaVO");
        formPessoaFisica.setFunctionId("pessoaFisica");
        formPessoaFisica.setLayout(new java.awt.GridBagLayout());

        labelControl11.setLabel("Estado Civil:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        formPessoaFisica.add(labelControl11, gridBagConstraints);

        codLookupControl3.setAttributeName("estadoCivil.nome");
        codLookupControl3.setEnabled(false);
        codLookupControl3.setLinkLabel(labelControl11);
        codLookupControl3.setRequired(true);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = -70;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        formPessoaFisica.add(codLookupControl3, gridBagConstraints);

        labelControl12.setLabel("CPF:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        formPessoaFisica.add(labelControl12, gridBagConstraints);

        labelControl13.setLabel("Data Nascimento:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        formPessoaFisica.add(labelControl13, gridBagConstraints);

        dateControl8.setAttributeName("dataNascimento");
        dateControl8.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        formPessoaFisica.add(dateControl8, gridBagConstraints);

        labelControl14.setLabel("Sexo:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        formPessoaFisica.add(labelControl14, gridBagConstraints);

        comboBoxControl12.setAttributeName("sexo");
        comboBoxControl12.setDomainId("sexo");
        comboBoxControl12.setEnabled(false);
        comboBoxControl12.setLinkLabel(labelControl14);
        comboBoxControl12.setRequired(true);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        formPessoaFisica.add(comboBoxControl12, gridBagConstraints);

        labelControl15.setLabel("Naturalidade:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        formPessoaFisica.add(labelControl15, gridBagConstraints);

        textControl10.setAttributeName("naturalidade");
        textControl10.setEnabled(false);
        textControl10.setMaxCharacters(100);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        formPessoaFisica.add(textControl10, gridBagConstraints);

        labelControl16.setLabel("Nacionalidade:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        formPessoaFisica.add(labelControl16, gridBagConstraints);

        textControl11.setAttributeName("nacionalidade");
        textControl11.setEnabled(false);
        textControl11.setMaxCharacters(100);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        formPessoaFisica.add(textControl11, gridBagConstraints);

        labelControl17.setLabel("Raca:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        formPessoaFisica.add(labelControl17, gridBagConstraints);

        comboBoxControl13.setAttributeName("raca");
        comboBoxControl13.setDomainId("racaCor");
        comboBoxControl13.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 100;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        formPessoaFisica.add(comboBoxControl13, gridBagConstraints);

        labelControl18.setLabel("Tipo Sangue:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        formPessoaFisica.add(labelControl18, gridBagConstraints);

        labelControl23.setLabel("Nome Mae:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        formPessoaFisica.add(labelControl23, gridBagConstraints);

        textControl22.setAttributeName("nomeMae");
        textControl22.setEnabled(false);
        textControl22.setMaxCharacters(100);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        formPessoaFisica.add(textControl22, gridBagConstraints);

        labelControl24.setLabel("Nome Pai:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        formPessoaFisica.add(labelControl24, gridBagConstraints);

        textControl23.setAttributeName("nomePai");
        textControl23.setEnabled(false);
        textControl23.setMaxCharacters(100);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        formPessoaFisica.add(textControl23, gridBagConstraints);

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder("RG"));
        jPanel5.setLayout(new java.awt.GridBagLayout());

        labelControl19.setLabel("Rg:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        jPanel5.add(labelControl19, gridBagConstraints);

        textControl6.setAttributeName("rg");
        textControl6.setEnabled(false);
        textControl6.setMaxCharacters(20);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 11;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        jPanel5.add(textControl6, gridBagConstraints);

        labelControl20.setLabel("Orgao Rg:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        jPanel5.add(labelControl20, gridBagConstraints);

        textControl7.setAttributeName("orgaoRg");
        textControl7.setEnabled(false);
        textControl7.setMaxCharacters(20);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 11;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        jPanel5.add(textControl7, gridBagConstraints);

        dateControl7.setAttributeName("dataEmissaoRg");
        dateControl7.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 11;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        jPanel5.add(dateControl7, gridBagConstraints);

        labelControl21.setLabel("Data Emissao Rg:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        jPanel5.add(labelControl21, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        formPessoaFisica.add(jPanel5, gridBagConstraints);

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder("Reservista"));
        jPanel6.setLayout(new java.awt.GridBagLayout());

        labelControl22.setLabel("Reservista Numero:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        jPanel6.add(labelControl22, gridBagConstraints);

        textControl20.setAttributeName("reservistaNumero");
        textControl20.setEnabled(false);
        textControl20.setMaxCharacters(20);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 9;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        jPanel6.add(textControl20, gridBagConstraints);

        labelControl25.setLabel("Reservista Categoria:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        jPanel6.add(labelControl25, gridBagConstraints);

        numericControl21.setAttributeName("reservistaCategoria");
        numericControl21.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 9;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        jPanel6.add(numericControl21, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        formPessoaFisica.add(jPanel6, gridBagConstraints);

        comboBoxControl14.setAttributeName("tipoSangue");
        comboBoxControl14.setDomainId("tipoSangue");
        comboBoxControl14.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        formPessoaFisica.add(comboBoxControl14, gridBagConstraints);

        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder("CNH"));
        jPanel7.setLayout(new java.awt.GridBagLayout());

        labelControl26.setLabel("Cnh Numero:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        jPanel7.add(labelControl26, gridBagConstraints);

        textControl14.setAttributeName("cnhNumero");
        textControl14.setEnabled(false);
        textControl14.setMaxCharacters(20);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        jPanel7.add(textControl14, gridBagConstraints);

        textControl15.setAttributeName("cnhCategoria");
        textControl15.setEnabled(false);
        textControl15.setMaxCharacters(2);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        jPanel7.add(textControl15, gridBagConstraints);

        labelControl27.setLabel("Cnh Categoria:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        jPanel7.add(labelControl27, gridBagConstraints);

        dateControl16.setAttributeName("cnhVencimento");
        dateControl16.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        jPanel7.add(dateControl16, gridBagConstraints);

        labelControl28.setLabel("Cnh Vencimento:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        jPanel7.add(labelControl28, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        formPessoaFisica.add(jPanel7, gridBagConstraints);

        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder("Titulo Eleitoral"));
        jPanel8.setLayout(new java.awt.GridBagLayout());

        labelControl29.setLabel("Titulo Eleitoral Numero:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        jPanel8.add(labelControl29, gridBagConstraints);

        textControl17.setAttributeName("tituloEleitoralNumero");
        textControl17.setEnabled(false);
        textControl17.setMaxCharacters(20);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        jPanel8.add(textControl17, gridBagConstraints);

        labelControl30.setLabel("Titulo Eleitoral Zona:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        jPanel8.add(labelControl30, gridBagConstraints);

        numericControl18.setAttributeName("tituloEleitoralZona");
        numericControl18.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        jPanel8.add(numericControl18, gridBagConstraints);

        labelControl31.setLabel("Titulo Eleitoral Secao:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        jPanel8.add(labelControl31, gridBagConstraints);

        numericControl19.setAttributeName("tituloEleitoralSecao");
        numericControl19.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        jPanel8.add(numericControl19, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        formPessoaFisica.add(jPanel8, gridBagConstraints);

        formattedTextControlCpf.setAttributeName("cpf");
        formattedTextControlCpf.setLinkLabel(labelControl12);
        formattedTextControlCpf.setRequired(true);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 70;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        formPessoaFisica.add(formattedTextControlCpf, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.gridwidth = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        formPessoaFisica.add(jSeparator1, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel3.add(formPessoaFisica, gridBagConstraints);

        formPessoaJuridica.setBorder(javax.swing.BorderFactory.createTitledBorder("Pessoa Juridica"));
        formPessoaJuridica.setVOClassName("com.t2tierp.cadastros.java.PessoaJuridicaVO");
        formPessoaJuridica.setFunctionId("pessoaJuridica");
        formPessoaJuridica.setLayout(new java.awt.GridBagLayout());

        labelControl32.setLabel("Cnpj:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        formPessoaJuridica.add(labelControl32, gridBagConstraints);

        labelControl33.setLabel("Fantasia:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        formPessoaJuridica.add(labelControl33, gridBagConstraints);

        textControl8.setAttributeName("fantasia");
        textControl8.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        formPessoaJuridica.add(textControl8, gridBagConstraints);

        labelControl34.setLabel("Inscricao Municipal:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        formPessoaJuridica.add(labelControl34, gridBagConstraints);

        textControl9.setAttributeName("inscricaoMunicipal");
        textControl9.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        formPessoaJuridica.add(textControl9, gridBagConstraints);

        labelControl35.setLabel("Inscricao Estadual:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        formPessoaJuridica.add(labelControl35, gridBagConstraints);

        textControl12.setAttributeName("inscricaoEstadual");
        textControl12.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        formPessoaJuridica.add(textControl12, gridBagConstraints);

        labelControl36.setLabel("Data Constituicao:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        formPessoaJuridica.add(labelControl36, gridBagConstraints);

        dateControl9.setAttributeName("dataConstituicao");
        dateControl9.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        formPessoaJuridica.add(dateControl9, gridBagConstraints);

        labelControl37.setLabel("Tipo Regime:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        formPessoaJuridica.add(labelControl37, gridBagConstraints);

        comboBoxControl15.setAttributeName("tipoRegime");
        comboBoxControl15.setDomainId("tipoRegimeEmpresa");
        comboBoxControl15.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        formPessoaJuridica.add(comboBoxControl15, gridBagConstraints);

        labelControl38.setLabel("Crt:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        formPessoaJuridica.add(labelControl38, gridBagConstraints);

        comboBoxControl16.setAttributeName("crt");
        comboBoxControl16.setDomainId("crt");
        comboBoxControl16.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        formPessoaJuridica.add(comboBoxControl16, gridBagConstraints);

        labelControl39.setLabel("Suframa:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        formPessoaJuridica.add(labelControl39, gridBagConstraints);

        textControl13.setAttributeName("suframa");
        textControl13.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        formPessoaJuridica.add(textControl13, gridBagConstraints);

        formattedTextControlCnpj.setAttributeName("cnpj");
        formattedTextControlCnpj.setLinkLabel(labelControl32);
        formattedTextControlCnpj.setRequired(true);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 70;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        formPessoaJuridica.add(formattedTextControlCnpj, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        formPessoaJuridica.add(jSeparator2, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel3.add(formPessoaJuridica, gridBagConstraints);

        jTabbedPane1.addTab("Informações do Tipo de Pessoa", jPanel3);

        jPanel4.setLayout(new java.awt.GridBagLayout());

        jPanel10.setBorder(javax.swing.BorderFactory.createTitledBorder("Contato"));
        jPanel10.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));
        jPanel10.add(insertButton1);
        jPanel10.add(editButton2);
        jPanel10.add(deleteButton1);
        jPanel10.add(saveButton2);
        jPanel10.add(reloadButton2);
        jPanel10.add(navigatorBar1);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        jPanel4.add(jPanel10, gridBagConstraints);

        gridControlContatos.setAutoLoadData(false);
        gridControlContatos.setDeleteButton(deleteButton1);
        gridControlContatos.setEditButton(editButton2);
        gridControlContatos.setFunctionId("contato");
        gridControlContatos.setInsertButton(insertButton1);
        gridControlContatos.setNavBar(navigatorBar1);
        gridControlContatos.setReloadButton(reloadButton2);
        gridControlContatos.setSaveButton(saveButton2);
        gridControlContatos.setValueObjectClassName("com.t2tierp.cadastros.java.PessoaContatoVO");
        gridControlContatos.getColumnContainer().setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        textColumn4.setColumnName("nome");
        textColumn4.setEditableOnEdit(true);
        textColumn4.setEditableOnInsert(true);
        textColumn4.setHeaderColumnName("Nome");
        textColumn4.setHeaderFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        textColumn4.setMaxCharacters(100);
        textColumn4.setPreferredWidth(200);
        gridControlContatos.getColumnContainer().add(textColumn4);

        textColumn5.setColumnName("email");
        textColumn5.setColumnRequired(false);
        textColumn5.setEditableOnEdit(true);
        textColumn5.setEditableOnInsert(true);
        textColumn5.setHeaderColumnName("Email");
        textColumn5.setHeaderFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        textColumn5.setMaxCharacters(250);
        textColumn5.setPreferredWidth(200);
        gridControlContatos.getColumnContainer().add(textColumn5);

        textColumn6.setColumnName("foneComercial");
        textColumn6.setColumnRequired(false);
        textColumn6.setEditableOnEdit(true);
        textColumn6.setEditableOnInsert(true);
        textColumn6.setHeaderColumnName("Fone Comercial");
        textColumn6.setHeaderFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        textColumn6.setMaxCharacters(14);
        gridControlContatos.getColumnContainer().add(textColumn6);

        textColumn7.setColumnName("foneResidencial");
        textColumn7.setColumnRequired(false);
        textColumn7.setEditableOnEdit(true);
        textColumn7.setEditableOnInsert(true);
        textColumn7.setHeaderColumnName("Fone Residencial");
        textColumn7.setHeaderFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        textColumn7.setMaxCharacters(14);
        gridControlContatos.getColumnContainer().add(textColumn7);

        textColumn8.setColumnName("foneCelular");
        textColumn8.setColumnRequired(false);
        textColumn8.setEditableOnEdit(true);
        textColumn8.setEditableOnInsert(true);
        textColumn8.setHeaderColumnName("Fone Celular");
        textColumn8.setHeaderFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        textColumn8.setMaxCharacters(14);
        gridControlContatos.getColumnContainer().add(textColumn8);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel4.add(gridControlContatos, gridBagConstraints);

        jTabbedPane1.addTab("Contatos", jPanel4);

        jPanel9.setLayout(new java.awt.GridBagLayout());

        jPanel11.setBorder(javax.swing.BorderFactory.createTitledBorder("Endereco"));
        jPanel11.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));
        jPanel11.add(insertButton2);
        jPanel11.add(editButton3);
        jPanel11.add(deleteButton2);
        jPanel11.add(saveButton3);
        jPanel11.add(reloadButton3);
        jPanel11.add(navigatorBar2);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        jPanel9.add(jPanel11, gridBagConstraints);

        gridControlEndereco.setAutoLoadData(false);
        gridControlEndereco.setDeleteButton(deleteButton2);
        gridControlEndereco.setEditButton(editButton3);
        gridControlEndereco.setFunctionId("endereco");
        gridControlEndereco.setInsertButton(insertButton2);
        gridControlEndereco.setNavBar(navigatorBar2);
        gridControlEndereco.setReloadButton(reloadButton3);
        gridControlEndereco.setSaveButton(saveButton3);
        gridControlEndereco.setValueObjectClassName("com.t2tierp.cadastros.java.PessoaEnderecoVO");
        gridControlEndereco.getColumnContainer().setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        textColumn9.setColumnName("logradouro");
        textColumn9.setEditableOnEdit(true);
        textColumn9.setEditableOnInsert(true);
        textColumn9.setHeaderColumnName("Logradouro");
        textColumn9.setHeaderFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        textColumn9.setMaxCharacters(60);
        textColumn9.setPreferredWidth(200);
        gridControlEndereco.getColumnContainer().add(textColumn9);

        textColumn10.setColumnName("numero");
        textColumn10.setEditableOnEdit(true);
        textColumn10.setEditableOnInsert(true);
        textColumn10.setHeaderColumnName("Numero");
        textColumn10.setHeaderFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        textColumn10.setMaxCharacters(10);
        gridControlEndereco.getColumnContainer().add(textColumn10);

        textColumn11.setColumnName("complemento");
        textColumn11.setColumnRequired(false);
        textColumn11.setEditableOnEdit(true);
        textColumn11.setEditableOnInsert(true);
        textColumn11.setHeaderColumnName("Complemento");
        textColumn11.setHeaderFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        textColumn11.setMaxCharacters(60);
        gridControlEndereco.getColumnContainer().add(textColumn11);

        textColumn12.setColumnName("bairro");
        textColumn12.setEditableOnEdit(true);
        textColumn12.setEditableOnInsert(true);
        textColumn12.setHeaderColumnName("Bairro");
        textColumn12.setHeaderFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        textColumn12.setMaxCharacters(60);
        gridControlEndereco.getColumnContainer().add(textColumn12);

        textColumn13.setColumnName("cidade");
        textColumn13.setEditableOnEdit(true);
        textColumn13.setEditableOnInsert(true);
        textColumn13.setHeaderColumnName("Cidade");
        textColumn13.setHeaderFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        textColumn13.setMaxCharacters(60);
        gridControlEndereco.getColumnContainer().add(textColumn13);

        textColumn14.setColumnName("cep");
        textColumn14.setColumnRequired(false);
        textColumn14.setEditableOnEdit(true);
        textColumn14.setEditableOnInsert(true);
        textColumn14.setHeaderColumnName("Cep");
        textColumn14.setHeaderFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        textColumn14.setMaxCharacters(8);
        gridControlEndereco.getColumnContainer().add(textColumn14);

        integerColumn10.setColumnName("municipioIbge");
        integerColumn10.setColumnRequired(false);
        integerColumn10.setHeaderColumnName("Municipio Ibge");
        integerColumn10.setHeaderFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        gridControlEndereco.getColumnContainer().add(integerColumn10);

        textColumn15.setColumnName("uf");
        textColumn15.setEditableOnEdit(true);
        textColumn15.setEditableOnInsert(true);
        textColumn15.setHeaderColumnName("Uf");
        textColumn15.setHeaderFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        textColumn15.setMaxCharacters(2);
        gridControlEndereco.getColumnContainer().add(textColumn15);

        textColumn16.setColumnName("fone");
        textColumn16.setColumnRequired(false);
        textColumn16.setEditableOnEdit(true);
        textColumn16.setEditableOnInsert(true);
        textColumn16.setHeaderColumnName("Fone");
        textColumn16.setHeaderFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        textColumn16.setMaxCharacters(14);
        gridControlEndereco.getColumnContainer().add(textColumn16);

        comboColumn14.setColumnName("principal");
        comboColumn14.setDomainId("naosim");
        comboColumn14.setEditableOnEdit(true);
        comboColumn14.setEditableOnInsert(true);
        comboColumn14.setHeaderColumnName("Principal");
        comboColumn14.setHeaderFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        gridControlEndereco.getColumnContainer().add(comboColumn14);

        comboColumn15.setColumnName("entrega");
        comboColumn15.setDomainId("naosim");
        comboColumn15.setEditableOnEdit(true);
        comboColumn15.setEditableOnInsert(true);
        comboColumn15.setHeaderColumnName("Entrega");
        comboColumn15.setHeaderFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        gridControlEndereco.getColumnContainer().add(comboColumn15);

        comboColumn16.setColumnName("cobranca");
        comboColumn16.setDomainId("naosim");
        comboColumn16.setEditableOnEdit(true);
        comboColumn16.setEditableOnInsert(true);
        comboColumn16.setHeaderColumnName("Cobranca");
        comboColumn16.setHeaderFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        gridControlEndereco.getColumnContainer().add(comboColumn16);

        comboColumn17.setColumnName("correspondencia");
        comboColumn17.setDomainId("naosim");
        comboColumn17.setEditableOnEdit(true);
        comboColumn17.setEditableOnInsert(true);
        comboColumn17.setHeaderColumnName("Correspondencia");
        comboColumn17.setHeaderFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        gridControlEndereco.getColumnContainer().add(comboColumn17);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel9.add(gridControlEndereco, gridBagConstraints);

        jTabbedPane1.addTab("Endereços", jPanel9);

        jPanel12.setLayout(new java.awt.GridBagLayout());

        jPanel13.setBorder(javax.swing.BorderFactory.createTitledBorder("Endereco"));
        jPanel13.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));
        jPanel13.add(insertButton3);
        jPanel13.add(editButton4);
        jPanel13.add(deleteButton3);
        jPanel13.add(saveButton4);
        jPanel13.add(reloadButton4);
        jPanel13.add(navigatorBar3);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        jPanel12.add(jPanel13, gridBagConstraints);

        gridControlTelefone.setAutoLoadData(false);
        gridControlTelefone.setDeleteButton(deleteButton3);
        gridControlTelefone.setEditButton(editButton4);
        gridControlTelefone.setFunctionId("telefone");
        gridControlTelefone.setInsertButton(insertButton3);
        gridControlTelefone.setNavBar(navigatorBar3);
        gridControlTelefone.setReloadButton(reloadButton2);
        gridControlTelefone.setSaveButton(saveButton4);
        gridControlTelefone.setValueObjectClassName("com.t2tierp.cadastros.java.PessoaTelefoneVO");

        comboColumn1.setColumnName("tipo");
        comboColumn1.setDomainId("tipoTelefone");
        comboColumn1.setEditableOnEdit(true);
        comboColumn1.setEditableOnInsert(true);
        comboColumn1.setHeaderColumnName("Tipo");
        comboColumn1.setHeaderFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        comboColumn1.setPreferredWidth(150);
        gridControlTelefone.getColumnContainer().add(comboColumn1);

        textColumn19.setColumnName("numero");
        textColumn19.setColumnRequired(false);
        textColumn19.setEditableOnEdit(true);
        textColumn19.setEditableOnInsert(true);
        textColumn19.setHeaderColumnName("Numero");
        textColumn19.setHeaderFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        textColumn19.setMaxCharacters(14);
        gridControlTelefone.getColumnContainer().add(textColumn19);

        textColumn18.setColumnName("observacao");
        textColumn18.setColumnRequired(false);
        textColumn18.setEditableOnEdit(true);
        textColumn18.setEditableOnInsert(true);
        textColumn18.setHeaderColumnName("Observacao");
        textColumn18.setHeaderFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        textColumn18.setMaxCharacters(1000);
        textColumn18.setPreferredWidth(400);
        gridControlTelefone.getColumnContainer().add(textColumn18);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel12.add(gridControlTelefone, gridBagConstraints);

        jTabbedPane1.addTab("Telefones", jPanel12);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        getContentPane().add(jTabbedPane1, gridBagConstraints);

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private org.openswing.swing.client.CodLookupControl codLookupControl3;
    private org.openswing.swing.client.ComboBoxControl comboBoxControl11;
    private org.openswing.swing.client.ComboBoxControl comboBoxControl12;
    private org.openswing.swing.client.ComboBoxControl comboBoxControl13;
    private org.openswing.swing.client.ComboBoxControl comboBoxControl14;
    private org.openswing.swing.client.ComboBoxControl comboBoxControl15;
    private org.openswing.swing.client.ComboBoxControl comboBoxControl16;
    private org.openswing.swing.client.ComboBoxControl comboBoxControl3;
    private org.openswing.swing.client.ComboBoxControl comboBoxControl6;
    private org.openswing.swing.client.ComboBoxControl comboBoxControl7;
    private org.openswing.swing.client.ComboBoxControl comboBoxControl8;
    private org.openswing.swing.table.columns.client.ComboColumn comboColumn1;
    private org.openswing.swing.table.columns.client.ComboColumn comboColumn14;
    private org.openswing.swing.table.columns.client.ComboColumn comboColumn15;
    private org.openswing.swing.table.columns.client.ComboColumn comboColumn16;
    private org.openswing.swing.table.columns.client.ComboColumn comboColumn17;
    private org.openswing.swing.client.DateControl dateControl16;
    private org.openswing.swing.client.DateControl dateControl7;
    private org.openswing.swing.client.DateControl dateControl8;
    private org.openswing.swing.client.DateControl dateControl9;
    private org.openswing.swing.client.DeleteButton deleteButton1;
    private org.openswing.swing.client.DeleteButton deleteButton2;
    private org.openswing.swing.client.DeleteButton deleteButton3;
    private org.openswing.swing.client.EditButton editButton1;
    private org.openswing.swing.client.EditButton editButton2;
    private org.openswing.swing.client.EditButton editButton3;
    private org.openswing.swing.client.EditButton editButton4;
    private org.openswing.swing.form.client.Form form1;
    private org.openswing.swing.form.client.Form formPessoaFisica;
    private org.openswing.swing.form.client.Form formPessoaJuridica;
    private org.openswing.swing.client.FormattedTextControl formattedTextControlCnpj;
    private org.openswing.swing.client.FormattedTextControl formattedTextControlCpf;
    private org.openswing.swing.client.GridControl gridControlContatos;
    private org.openswing.swing.client.GridControl gridControlEndereco;
    private org.openswing.swing.client.GridControl gridControlTelefone;
    private org.openswing.swing.client.InsertButton insertButton1;
    private org.openswing.swing.client.InsertButton insertButton2;
    private org.openswing.swing.client.InsertButton insertButton3;
    private org.openswing.swing.table.columns.client.IntegerColumn integerColumn10;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
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
    private org.openswing.swing.client.LabelControl labelControl10;
    private org.openswing.swing.client.LabelControl labelControl11;
    private org.openswing.swing.client.LabelControl labelControl12;
    private org.openswing.swing.client.LabelControl labelControl13;
    private org.openswing.swing.client.LabelControl labelControl14;
    private org.openswing.swing.client.LabelControl labelControl15;
    private org.openswing.swing.client.LabelControl labelControl16;
    private org.openswing.swing.client.LabelControl labelControl17;
    private org.openswing.swing.client.LabelControl labelControl18;
    private org.openswing.swing.client.LabelControl labelControl19;
    private org.openswing.swing.client.LabelControl labelControl2;
    private org.openswing.swing.client.LabelControl labelControl20;
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
    private org.openswing.swing.client.LabelControl labelControl4;
    private org.openswing.swing.client.LabelControl labelControl5;
    private org.openswing.swing.client.LabelControl labelControl6;
    private org.openswing.swing.client.LabelControl labelControl7;
    private org.openswing.swing.client.NavigatorBar navigatorBar1;
    private org.openswing.swing.client.NavigatorBar navigatorBar2;
    private org.openswing.swing.client.NavigatorBar navigatorBar3;
    private org.openswing.swing.client.NumericControl numericControl18;
    private org.openswing.swing.client.NumericControl numericControl19;
    private org.openswing.swing.client.NumericControl numericControl21;
    private org.openswing.swing.client.ReloadButton reloadButton1;
    private org.openswing.swing.client.ReloadButton reloadButton2;
    private org.openswing.swing.client.ReloadButton reloadButton3;
    private org.openswing.swing.client.ReloadButton reloadButton4;
    private org.openswing.swing.client.SaveButton saveButton1;
    private org.openswing.swing.client.SaveButton saveButton2;
    private org.openswing.swing.client.SaveButton saveButton3;
    private org.openswing.swing.client.SaveButton saveButton4;
    private org.openswing.swing.table.columns.client.TextColumn textColumn10;
    private org.openswing.swing.table.columns.client.TextColumn textColumn11;
    private org.openswing.swing.table.columns.client.TextColumn textColumn12;
    private org.openswing.swing.table.columns.client.TextColumn textColumn13;
    private org.openswing.swing.table.columns.client.TextColumn textColumn14;
    private org.openswing.swing.table.columns.client.TextColumn textColumn15;
    private org.openswing.swing.table.columns.client.TextColumn textColumn16;
    private org.openswing.swing.table.columns.client.TextColumn textColumn18;
    private org.openswing.swing.table.columns.client.TextColumn textColumn19;
    private org.openswing.swing.table.columns.client.TextColumn textColumn4;
    private org.openswing.swing.table.columns.client.TextColumn textColumn5;
    private org.openswing.swing.table.columns.client.TextColumn textColumn6;
    private org.openswing.swing.table.columns.client.TextColumn textColumn7;
    private org.openswing.swing.table.columns.client.TextColumn textColumn8;
    private org.openswing.swing.table.columns.client.TextColumn textColumn9;
    private org.openswing.swing.client.TextControl textControl10;
    private org.openswing.swing.client.TextControl textControl11;
    private org.openswing.swing.client.TextControl textControl12;
    private org.openswing.swing.client.TextControl textControl13;
    private org.openswing.swing.client.TextControl textControl14;
    private org.openswing.swing.client.TextControl textControl15;
    private org.openswing.swing.client.TextControl textControl17;
    private org.openswing.swing.client.TextControl textControl2;
    private org.openswing.swing.client.TextControl textControl20;
    private org.openswing.swing.client.TextControl textControl22;
    private org.openswing.swing.client.TextControl textControl23;
    private org.openswing.swing.client.TextControl textControl4;
    private org.openswing.swing.client.TextControl textControl5;
    private org.openswing.swing.client.TextControl textControl6;
    private org.openswing.swing.client.TextControl textControl7;
    private org.openswing.swing.client.TextControl textControl8;
    private org.openswing.swing.client.TextControl textControl9;
    // End of variables declaration//GEN-END:variables

}
