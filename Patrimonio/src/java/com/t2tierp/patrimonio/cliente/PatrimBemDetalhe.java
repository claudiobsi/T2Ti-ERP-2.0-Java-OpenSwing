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
package com.t2tierp.patrimonio.cliente;

import com.t2tierp.padrao.cliente.LookupDataLocatorGenerico;
import java.awt.Dimension;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import org.openswing.swing.client.GenericButton;
import org.openswing.swing.lookup.client.LookupController;
import org.openswing.swing.mdi.client.InternalFrame;
import org.openswing.swing.util.client.ClientUtils;

public class PatrimBemDetalhe extends InternalFrame {

    private LookupController tipoAquisicaoController = new LookupController();
    private LookupController estadoConservacaoController = new LookupController();
    private LookupController grupoController = new LookupController();
    private LookupController setorController = new LookupController();
    private LookupController fornecedorController = new LookupController();
    private LookupController colaboradorController = new LookupController();
    private LookupController tipoMovimentacaoController = new LookupController();
    private PatrimDocumentoBemGridController documentacaoController;
    private PatrimDepreciacaoBemGridController depreciacaoController;
    private PatrimMovimentacaoBemGridController movimentacaoController;

    public PatrimBemDetalhe(PatrimBemDetalheController controller) {
        initComponents();

        form1.setFormController(controller);

        genericButton1.setToolTipText("Acionar GED");
        genericButton1.setToolTipText("Calcular Depreciação");

        documentacaoController = new PatrimDocumentoBemGridController(this);
        gridDocumentacao.setGridDataLocator(documentacaoController);
        gridDocumentacao.setController(documentacaoController);

        depreciacaoController = new PatrimDepreciacaoBemGridController(this);
        gridDepreciacao.setGridDataLocator(depreciacaoController);
        gridDepreciacao.setController(depreciacaoController);

        movimentacaoController = new PatrimMovimentacaoBemGridController(this);
        gridMovimentacao.setGridDataLocator(movimentacaoController);
        gridMovimentacao.setController(movimentacaoController);

        /*
         * Configurações do lookup do tipo de aquisicao
         */
        tipoAquisicaoController.setLookupValueObjectClassName("com.t2tierp.patrimonio.java.PatrimTipoAquisicaoBemVO");
        tipoAquisicaoController.addLookup2ParentLink("id", "patrimTipoAquisicaoBem.id");
        tipoAquisicaoController.addLookup2ParentLink("nome", "patrimTipoAquisicaoBem.nome");
        tipoAquisicaoController.setHeaderColumnName("id", "ID");
        tipoAquisicaoController.setHeaderColumnName("nome", "Nome");
        tipoAquisicaoController.setFrameTitle("Importa Tipo Aquisição");

        tipoAquisicaoController.setVisibleStatusPanel(true);
        tipoAquisicaoController.setVisibleColumn("id", true);
        tipoAquisicaoController.setVisibleColumn("nome", true);
        tipoAquisicaoController.setFramePreferedSize(new Dimension(600, 500));

        tipoAquisicaoController.setLookupDataLocator(new LookupDataLocatorGenerico(tipoAquisicaoController.getLookupValueObjectClassName()));
        codLookupControl2.setLookupController(tipoAquisicaoController);

        /*
         * Configurações do lookup do estado de conservacao
         */
        estadoConservacaoController.setLookupValueObjectClassName("com.t2tierp.patrimonio.java.PatrimEstadoConservacaoVO");
        estadoConservacaoController.addLookup2ParentLink("id", "patrimEstadoConservacao.id");
        estadoConservacaoController.addLookup2ParentLink("nome", "patrimEstadoConservacao.nome");
        estadoConservacaoController.setHeaderColumnName("id", "ID");
        estadoConservacaoController.setHeaderColumnName("nome", "Nome");
        estadoConservacaoController.setFrameTitle("Importa Estado Conservação");

        estadoConservacaoController.setVisibleStatusPanel(true);
        estadoConservacaoController.setVisibleColumn("id", true);
        estadoConservacaoController.setVisibleColumn("nome", true);
        estadoConservacaoController.setFramePreferedSize(new Dimension(600, 500));

        estadoConservacaoController.setLookupDataLocator(new LookupDataLocatorGenerico(estadoConservacaoController.getLookupValueObjectClassName()));
        codLookupControl3.setLookupController(estadoConservacaoController);

        /*
         * Configurações do lookup do grupo
         */
        grupoController.setLookupValueObjectClassName("com.t2tierp.patrimonio.java.PatrimGrupoBemVO");
        grupoController.addLookup2ParentLink("id", "patrimGrupoBem.id");
        grupoController.addLookup2ParentLink("nome", "patrimGrupoBem.nome");
        grupoController.setHeaderColumnName("id", "ID");
        grupoController.setHeaderColumnName("nome", "Nome");
        grupoController.setFrameTitle("Importa Grupo Bem");

        grupoController.setVisibleStatusPanel(true);
        grupoController.setVisibleColumn("id", true);
        grupoController.setVisibleColumn("nome", true);
        grupoController.setFramePreferedSize(new Dimension(600, 500));

        grupoController.setLookupDataLocator(new LookupDataLocatorGenerico(grupoController.getLookupValueObjectClassName()));
        codLookupControl4.setLookupController(grupoController);

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
        codLookupControl5.setLookupController(setorController);

        /*
         * Configurações do lookup do fornecedor
         */
        fornecedorController.setLookupValueObjectClassName("com.t2tierp.cadastros.java.FornecedorVO");
        fornecedorController.addLookup2ParentLink("id", "fornecedor.id");
        fornecedorController.addLookup2ParentLink("pessoa.nome", "fornecedor.pessoa.nome");
        fornecedorController.setHeaderColumnName("id", "ID");
        fornecedorController.setHeaderColumnName("pessoa.nome", "Nome");
        fornecedorController.setFrameTitle("Importa Fornecedor");

        fornecedorController.setVisibleStatusPanel(true);
        fornecedorController.setVisibleColumn("id", true);
        fornecedorController.setVisibleColumn("pessoa.nome", true);
        fornecedorController.setFramePreferedSize(new Dimension(600, 500));

        fornecedorController.setLookupDataLocator(new LookupDataLocatorGenerico(fornecedorController.getLookupValueObjectClassName()));
        codLookupControl6.setLookupController(fornecedorController);

        /*
         * Configurações do lookup do colaborador
         */
        colaboradorController.setLookupValueObjectClassName("com.t2tierp.cadastros.java.ColaboradorVO");
        colaboradorController.addLookup2ParentLink("id", "colaborador.id");
        colaboradorController.addLookup2ParentLink("pessoa.nome", "colaborador.pessoa.nome");
        colaboradorController.setHeaderColumnName("id", "ID");
        colaboradorController.setHeaderColumnName("pessoa.nome", "Nome");
        colaboradorController.setFrameTitle("Importa Colaborador");

        colaboradorController.setVisibleStatusPanel(true);
        colaboradorController.setVisibleColumn("id", true);
        colaboradorController.setVisibleColumn("pessoa.nome", true);
        colaboradorController.setFramePreferedSize(new Dimension(600, 500));

        colaboradorController.setLookupDataLocator(new LookupDataLocatorGenerico(colaboradorController.getLookupValueObjectClassName()));
        codLookupControl7.setLookupController(colaboradorController);

        /*
         * Configurações do tipo de movimentacao
         */
        tipoMovimentacaoController.setLookupValueObjectClassName("com.t2tierp.patrimonio.java.PatrimTipoMovimentacaoVO");
        tipoMovimentacaoController.addLookup2ParentLink("id", "patrimTipoMovimentacao.id");
        tipoMovimentacaoController.addLookup2ParentLink("nome", "patrimTipoMovimentacao.nome");
        tipoMovimentacaoController.setHeaderColumnName("id", "ID");
        tipoMovimentacaoController.setHeaderColumnName("nome", "Nome");
        tipoMovimentacaoController.setFrameTitle("Importa Tipo Movimentação");

        tipoMovimentacaoController.setVisibleStatusPanel(true);
        tipoMovimentacaoController.setVisibleColumn("id", true);
        tipoMovimentacaoController.setVisibleColumn("nome", true);
        tipoMovimentacaoController.setFramePreferedSize(new Dimension(600, 500));

        tipoMovimentacaoController.setLookupDataLocator(new LookupDataLocatorGenerico(tipoMovimentacaoController.getLookupValueObjectClassName()));
        codLookupColumn1.setLookupController(tipoMovimentacaoController);
    }

    /**
     * @return the form1
     */
    public org.openswing.swing.form.client.Form getForm1() {
        return form1;
    }

    public PatrimDocumentoBemGridController getDocumentacaoController() {
        return documentacaoController;
    }

    public PatrimDepreciacaoBemGridController getDepreciacaoController() {
        return depreciacaoController;
    }

    public PatrimMovimentacaoBemGridController getMovimentacaoController() {
        return movimentacaoController;
    }

    /**
     * @return the gridDepreciacao
     */
    public org.openswing.swing.client.GridControl getGridDepreciacao() {
        return gridDepreciacao;
    }

    /**
     * @return the gridDocumentacao
     */
    public org.openswing.swing.client.GridControl getGridDocumentacao() {
        return gridDocumentacao;
    }

    /**
     * @return the gridMovimentacao
     */
    public org.openswing.swing.client.GridControl getGridMovimentacao() {
        return gridMovimentacao;
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
        jTabbedPane1 = new javax.swing.JTabbedPane();
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
        codLookupControl7 = new org.openswing.swing.client.CodLookupControl();
        textControl7 = new org.openswing.swing.client.TextControl();
        labelControl13 = new org.openswing.swing.client.LabelControl();
        textControl8 = new org.openswing.swing.client.TextControl();
        labelControl14 = new org.openswing.swing.client.LabelControl();
        textControl9 = new org.openswing.swing.client.TextControl();
        labelControl15 = new org.openswing.swing.client.LabelControl();
        labelControl16 = new org.openswing.swing.client.LabelControl();
        textControl11 = new org.openswing.swing.client.TextControl();
        labelControl17 = new org.openswing.swing.client.LabelControl();
        dateControl12 = new org.openswing.swing.client.DateControl();
        labelControl18 = new org.openswing.swing.client.LabelControl();
        dateControl13 = new org.openswing.swing.client.DateControl();
        labelControl19 = new org.openswing.swing.client.LabelControl();
        dateControl14 = new org.openswing.swing.client.DateControl();
        labelControl20 = new org.openswing.swing.client.LabelControl();
        dateControl15 = new org.openswing.swing.client.DateControl();
        labelControl21 = new org.openswing.swing.client.LabelControl();
        dateControl16 = new org.openswing.swing.client.DateControl();
        labelControl22 = new org.openswing.swing.client.LabelControl();
        dateControl17 = new org.openswing.swing.client.DateControl();
        labelControl23 = new org.openswing.swing.client.LabelControl();
        dateControl18 = new org.openswing.swing.client.DateControl();
        labelControl24 = new org.openswing.swing.client.LabelControl();
        dateControl19 = new org.openswing.swing.client.DateControl();
        labelControl25 = new org.openswing.swing.client.LabelControl();
        textControl20 = new org.openswing.swing.client.TextControl();
        labelControl26 = new org.openswing.swing.client.LabelControl();
        textControl21 = new org.openswing.swing.client.TextControl();
        labelControl27 = new org.openswing.swing.client.LabelControl();
        numericControl22 = new org.openswing.swing.client.NumericControl();
        labelControl28 = new org.openswing.swing.client.LabelControl();
        numericControl23 = new org.openswing.swing.client.NumericControl();
        labelControl29 = new org.openswing.swing.client.LabelControl();
        numericControl24 = new org.openswing.swing.client.NumericControl();
        labelControl30 = new org.openswing.swing.client.LabelControl();
        numericControl25 = new org.openswing.swing.client.NumericControl();
        labelControl31 = new org.openswing.swing.client.LabelControl();
        comboBoxControl26 = new org.openswing.swing.client.ComboBoxControl();
        labelControl32 = new org.openswing.swing.client.LabelControl();
        comboBoxControl27 = new org.openswing.swing.client.ComboBoxControl();
        labelControl33 = new org.openswing.swing.client.LabelControl();
        dateControl28 = new org.openswing.swing.client.DateControl();
        labelControl34 = new org.openswing.swing.client.LabelControl();
        dateControl29 = new org.openswing.swing.client.DateControl();
        labelControl35 = new org.openswing.swing.client.LabelControl();
        comboBoxControl30 = new org.openswing.swing.client.ComboBoxControl();
        labelControl36 = new org.openswing.swing.client.LabelControl();
        numericControl31 = new org.openswing.swing.client.NumericControl();
        labelControl37 = new org.openswing.swing.client.LabelControl();
        numericControl32 = new org.openswing.swing.client.NumericControl();
        labelControl38 = new org.openswing.swing.client.LabelControl();
        numericControl33 = new org.openswing.swing.client.NumericControl();
        labelControl39 = new org.openswing.swing.client.LabelControl();
        numericControl34 = new org.openswing.swing.client.NumericControl();
        labelControl40 = new org.openswing.swing.client.LabelControl();
        textControl35 = new org.openswing.swing.client.TextControl();
        textAreaControl1 = new org.openswing.swing.client.TextAreaControl();
        jSeparator1 = new javax.swing.JSeparator();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        insertButtonDocumentacao = new org.openswing.swing.client.InsertButton();
        editButtonDocumentacao = new org.openswing.swing.client.EditButton();
        deleteButtonDocumentacao = new org.openswing.swing.client.DeleteButton();
        saveButtonDocumentacao = new org.openswing.swing.client.SaveButton();
        reloadButtonDocumentacao = new org.openswing.swing.client.ReloadButton();
        navigatorBarDocumentacao = new org.openswing.swing.client.NavigatorBar();
        genericButton1 = new GenericButton(new ImageIcon(ClientUtils.getImage("scanner.png")));
        gridDocumentacao = new org.openswing.swing.client.GridControl();
        textColumn3 = new org.openswing.swing.table.columns.client.TextColumn();
        textColumn4 = new org.openswing.swing.table.columns.client.TextColumn();
        jPanel4 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        genericButton2 = new GenericButton(new ImageIcon(ClientUtils.getImage("calculadora.png")));
        gridDepreciacao = new org.openswing.swing.client.GridControl();
        dateColumn3 = new org.openswing.swing.table.columns.client.DateColumn();
        integerColumn4 = new org.openswing.swing.table.columns.client.IntegerColumn();
        decimalColumn5 = new org.openswing.swing.table.columns.client.DecimalColumn();
        decimalColumn6 = new org.openswing.swing.table.columns.client.DecimalColumn();
        decimalColumn7 = new org.openswing.swing.table.columns.client.DecimalColumn();
        decimalColumn8 = new org.openswing.swing.table.columns.client.DecimalColumn();
        jPanel6 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        insertButtonMovimentacao = new org.openswing.swing.client.InsertButton();
        editButtonMovimentacao = new org.openswing.swing.client.EditButton();
        deleteButtonMovimentacao = new org.openswing.swing.client.DeleteButton();
        saveButtonMovimentacao = new org.openswing.swing.client.SaveButton();
        reloadButtonMovimentacao = new org.openswing.swing.client.ReloadButton();
        navigatorBarMovimentacao = new org.openswing.swing.client.NavigatorBar();
        gridMovimentacao = new org.openswing.swing.client.GridControl();
        codLookupColumn1 = new org.openswing.swing.table.columns.client.CodLookupColumn();
        dateColumn4 = new org.openswing.swing.table.columns.client.DateColumn();
        textColumn7 = new org.openswing.swing.table.columns.client.TextColumn();

        setTitle("T2Ti ERP - Controle Patrimonial");
        setPreferredSize(new java.awt.Dimension(700, 400));
        getContentPane().setLayout(new java.awt.GridBagLayout());

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Patrim Bem"));
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

        form1.setVOClassName("com.t2tierp.patrimonio.java.PatrimBemVO");
        form1.setEditButton(editButton1);
        form1.setFunctionId("patrimBem");
        form1.setReloadButton(reloadButton1);
        form1.setSaveButton(saveButton1);
        form1.setLayout(new java.awt.GridBagLayout());

        labelControl1.setLabel("Tipo Aquisicao:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        form1.add(labelControl1, gridBagConstraints);

        codLookupControl2.setAllowOnlyNumbers(true);
        codLookupControl2.setAttributeName("patrimTipoAquisicaoBem.id");
        codLookupControl2.setEnabled(false);
        codLookupControl2.setLinkLabel(labelControl1);
        codLookupControl2.setRequired(true);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = -70;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        form1.add(codLookupControl2, gridBagConstraints);

        textControl2.setAttributeName("patrimTipoAquisicaoBem.nome");
        textControl2.setEnabled(false);
        textControl2.setEnabledOnEdit(false);
        textControl2.setEnabledOnInsert(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        form1.add(textControl2, gridBagConstraints);

        labelControl3.setLabel("Estado Conservacao:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        form1.add(labelControl3, gridBagConstraints);

        codLookupControl3.setAllowOnlyNumbers(true);
        codLookupControl3.setAttributeName("patrimEstadoConservacao.id");
        codLookupControl3.setEnabled(false);
        codLookupControl3.setLinkLabel(labelControl3);
        codLookupControl3.setRequired(true);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = -70;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        form1.add(codLookupControl3, gridBagConstraints);

        textControl3.setAttributeName("patrimEstadoConservacao.nome");
        textControl3.setEnabled(false);
        textControl3.setEnabledOnEdit(false);
        textControl3.setEnabledOnInsert(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        form1.add(textControl3, gridBagConstraints);

        labelControl5.setLabel("Grupo:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        form1.add(labelControl5, gridBagConstraints);

        codLookupControl4.setAllowOnlyNumbers(true);
        codLookupControl4.setAttributeName("patrimGrupoBem.id");
        codLookupControl4.setEnabled(false);
        codLookupControl4.setLinkLabel(labelControl5);
        codLookupControl4.setRequired(true);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = -70;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        form1.add(codLookupControl4, gridBagConstraints);

        textControl4.setAttributeName("patrimGrupoBem.nome");
        textControl4.setEnabled(false);
        textControl4.setEnabledOnEdit(false);
        textControl4.setEnabledOnInsert(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        form1.add(textControl4, gridBagConstraints);

        labelControl7.setLabel("Setor:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        form1.add(labelControl7, gridBagConstraints);

        codLookupControl5.setAllowOnlyNumbers(true);
        codLookupControl5.setAttributeName("setor.id");
        codLookupControl5.setEnabled(false);
        codLookupControl5.setLinkLabel(labelControl7);
        codLookupControl5.setRequired(true);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = -70;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        form1.add(codLookupControl5, gridBagConstraints);

        textControl5.setAttributeName("setor.nome");
        textControl5.setEnabled(false);
        textControl5.setEnabledOnEdit(false);
        textControl5.setEnabledOnInsert(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        form1.add(textControl5, gridBagConstraints);

        labelControl9.setLabel("Fornecedor:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        form1.add(labelControl9, gridBagConstraints);

        codLookupControl6.setAllowOnlyNumbers(true);
        codLookupControl6.setAttributeName("fornecedor.id");
        codLookupControl6.setEnabled(false);
        codLookupControl6.setLinkLabel(labelControl9);
        codLookupControl6.setRequired(true);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = -70;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        form1.add(codLookupControl6, gridBagConstraints);

        textControl6.setAttributeName("fornecedor.pessoa.nome");
        textControl6.setEnabled(false);
        textControl6.setEnabledOnEdit(false);
        textControl6.setEnabledOnInsert(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        form1.add(textControl6, gridBagConstraints);

        labelControl11.setLabel("Colaborador:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        form1.add(labelControl11, gridBagConstraints);

        codLookupControl7.setAllowOnlyNumbers(true);
        codLookupControl7.setAttributeName("colaborador.id");
        codLookupControl7.setEnabled(false);
        codLookupControl7.setLinkLabel(labelControl11);
        codLookupControl7.setRequired(true);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = -70;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        form1.add(codLookupControl7, gridBagConstraints);

        textControl7.setAttributeName("colaborador.pessoa.nome");
        textControl7.setEnabled(false);
        textControl7.setEnabledOnEdit(false);
        textControl7.setEnabledOnInsert(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        form1.add(textControl7, gridBagConstraints);

        labelControl13.setLabel("Numero Nb:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        form1.add(labelControl13, gridBagConstraints);

        textControl8.setAttributeName("numeroNb");
        textControl8.setEnabled(false);
        textControl8.setLinkLabel(labelControl13);
        textControl8.setMaxCharacters(20);
        textControl8.setRequired(true);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        form1.add(textControl8, gridBagConstraints);

        labelControl14.setLabel("Nome:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        form1.add(labelControl14, gridBagConstraints);

        textControl9.setAttributeName("nome");
        textControl9.setEnabled(false);
        textControl9.setLinkLabel(labelControl14);
        textControl9.setMaxCharacters(100);
        textControl9.setRequired(true);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        form1.add(textControl9, gridBagConstraints);

        labelControl15.setLabel("Descricao:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        form1.add(labelControl15, gridBagConstraints);

        labelControl16.setLabel("Numero Serie:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        form1.add(labelControl16, gridBagConstraints);

        textControl11.setAttributeName("numeroSerie");
        textControl11.setEnabled(false);
        textControl11.setMaxCharacters(50);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        form1.add(textControl11, gridBagConstraints);

        labelControl17.setLabel("Data Aquisicao:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        form1.add(labelControl17, gridBagConstraints);

        dateControl12.setAttributeName("dataAquisicao");
        dateControl12.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 9;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        form1.add(dateControl12, gridBagConstraints);

        labelControl18.setLabel("Data Aceite:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        form1.add(labelControl18, gridBagConstraints);

        dateControl13.setAttributeName("dataAceite");
        dateControl13.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 9;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        form1.add(dateControl13, gridBagConstraints);

        labelControl19.setLabel("Data Cadastro:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        form1.add(labelControl19, gridBagConstraints);

        dateControl14.setAttributeName("dataCadastro");
        dateControl14.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 11;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        form1.add(dateControl14, gridBagConstraints);

        labelControl20.setLabel("Data Contabilizado:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        form1.add(labelControl20, gridBagConstraints);

        dateControl15.setAttributeName("dataContabilizado");
        dateControl15.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 11;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        form1.add(dateControl15, gridBagConstraints);

        labelControl21.setLabel("Data Vistoria:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        form1.add(labelControl21, gridBagConstraints);

        dateControl16.setAttributeName("dataVistoria");
        dateControl16.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 11;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        form1.add(dateControl16, gridBagConstraints);

        labelControl22.setLabel("Data Marcacao:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        form1.add(labelControl22, gridBagConstraints);

        dateControl17.setAttributeName("dataMarcacao");
        dateControl17.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 11;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        form1.add(dateControl17, gridBagConstraints);

        labelControl23.setLabel("Data Baixa:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        form1.add(labelControl23, gridBagConstraints);

        dateControl18.setAttributeName("dataBaixa");
        dateControl18.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 11;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        form1.add(dateControl18, gridBagConstraints);

        labelControl24.setLabel("Vencimento Garantia:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        form1.add(labelControl24, gridBagConstraints);

        dateControl19.setAttributeName("vencimentoGarantia");
        dateControl19.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 11;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        form1.add(dateControl19, gridBagConstraints);

        labelControl25.setLabel("Numero Nota Fiscal:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 12;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        form1.add(labelControl25, gridBagConstraints);

        textControl20.setAttributeName("numeroNotaFiscal");
        textControl20.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 13;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        form1.add(textControl20, gridBagConstraints);

        labelControl26.setLabel("Chave Nfe:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 12;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        form1.add(labelControl26, gridBagConstraints);

        textControl21.setAttributeName("chaveNfe");
        textControl21.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 13;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        form1.add(textControl21, gridBagConstraints);

        labelControl27.setLabel("Valor Original:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 12;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        form1.add(labelControl27, gridBagConstraints);

        numericControl22.setAttributeName("valorOriginal");
        numericControl22.setDecimals(2);
        numericControl22.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 13;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        form1.add(numericControl22, gridBagConstraints);

        labelControl28.setLabel("Valor Compra:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 12;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        form1.add(labelControl28, gridBagConstraints);

        numericControl23.setAttributeName("valorCompra");
        numericControl23.setDecimals(2);
        numericControl23.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 13;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        form1.add(numericControl23, gridBagConstraints);

        labelControl29.setLabel("Valor Atualizado:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 12;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        form1.add(labelControl29, gridBagConstraints);

        numericControl24.setAttributeName("valorAtualizado");
        numericControl24.setDecimals(2);
        numericControl24.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 13;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        form1.add(numericControl24, gridBagConstraints);

        labelControl30.setLabel("Valor Baixa:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 12;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        form1.add(labelControl30, gridBagConstraints);

        numericControl25.setAttributeName("valorBaixa");
        numericControl25.setDecimals(2);
        numericControl25.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 13;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        form1.add(numericControl25, gridBagConstraints);

        labelControl31.setLabel("Deprecia:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 14;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        form1.add(labelControl31, gridBagConstraints);

        comboBoxControl26.setAttributeName("deprecia");
        comboBoxControl26.setDomainId("simnao");
        comboBoxControl26.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 15;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        form1.add(comboBoxControl26, gridBagConstraints);

        labelControl32.setLabel("Metodo Depreciacao:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 14;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        form1.add(labelControl32, gridBagConstraints);

        comboBoxControl27.setAttributeName("metodoDepreciacao");
        comboBoxControl27.setDomainId("metodoDepreciacao");
        comboBoxControl27.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 15;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        form1.add(comboBoxControl27, gridBagConstraints);

        labelControl33.setLabel("Inicio Depreciacao:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 14;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        form1.add(labelControl33, gridBagConstraints);

        dateControl28.setAttributeName("inicioDepreciacao");
        dateControl28.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 15;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        form1.add(dateControl28, gridBagConstraints);

        labelControl34.setLabel("Ultima Depreciacao:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 14;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        form1.add(labelControl34, gridBagConstraints);

        dateControl29.setAttributeName("ultimaDepreciacao");
        dateControl29.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 15;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        form1.add(dateControl29, gridBagConstraints);

        labelControl35.setLabel("Tipo Depreciacao:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 14;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        form1.add(labelControl35, gridBagConstraints);

        comboBoxControl30.setAttributeName("tipoDepreciacao");
        comboBoxControl30.setDomainId("tipoDepreciacao");
        comboBoxControl30.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 15;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        form1.add(comboBoxControl30, gridBagConstraints);

        labelControl36.setLabel("Taxa Anual Depreciacao:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 14;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        form1.add(labelControl36, gridBagConstraints);

        numericControl31.setAttributeName("taxaAnualDepreciacao");
        numericControl31.setDecimals(2);
        numericControl31.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 15;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        form1.add(numericControl31, gridBagConstraints);

        labelControl37.setLabel("Taxa Mensal Depreciacao:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 16;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        form1.add(labelControl37, gridBagConstraints);

        numericControl32.setAttributeName("taxaMensalDepreciacao");
        numericControl32.setDecimals(2);
        numericControl32.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 17;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        form1.add(numericControl32, gridBagConstraints);

        labelControl38.setLabel("Taxa Depreciacao Acelerada:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 16;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        form1.add(labelControl38, gridBagConstraints);

        numericControl33.setAttributeName("taxaDepreciacaoAcelerada");
        numericControl33.setDecimals(2);
        numericControl33.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 17;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        form1.add(numericControl33, gridBagConstraints);

        labelControl39.setLabel("Taxa Depreciacao Incentivada:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 16;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        form1.add(labelControl39, gridBagConstraints);

        numericControl34.setAttributeName("taxaDepreciacaoIncentivada");
        numericControl34.setDecimals(2);
        numericControl34.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 17;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        form1.add(numericControl34, gridBagConstraints);

        labelControl40.setLabel("Funcao:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 18;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        form1.add(labelControl40, gridBagConstraints);

        textControl35.setAttributeName("funcao");
        textControl35.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 19;
        gridBagConstraints.gridwidth = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        form1.add(textControl35, gridBagConstraints);

        textAreaControl1.setAttributeName("descricao");
        textAreaControl1.setEnabled(false);
        textAreaControl1.setMaxCharacters(1000);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.gridwidth = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipady = 30;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        form1.add(textAreaControl1, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 20;
        gridBagConstraints.gridwidth = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        form1.add(jSeparator1, gridBagConstraints);

        jTabbedPane1.addTab("Dados do Bem", form1);

        jPanel2.setLayout(new java.awt.GridBagLayout());

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Patrim Documento Bem"));
        jPanel3.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));
        jPanel3.add(insertButtonDocumentacao);
        jPanel3.add(editButtonDocumentacao);
        jPanel3.add(deleteButtonDocumentacao);
        jPanel3.add(saveButtonDocumentacao);
        jPanel3.add(reloadButtonDocumentacao);
        jPanel3.add(navigatorBarDocumentacao);

        genericButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                genericButton1ActionPerformed(evt);
            }
        });
        jPanel3.add(genericButton1);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        jPanel2.add(jPanel3, gridBagConstraints);

        gridDocumentacao.setAutoLoadData(false);
        gridDocumentacao.setDeleteButton(deleteButtonDocumentacao);
        gridDocumentacao.setEditButton(editButtonDocumentacao);
        gridDocumentacao.setFunctionId("patrimDocumentoBem");
        gridDocumentacao.setInsertButton(insertButtonDocumentacao);
        gridDocumentacao.setNavBar(navigatorBarDocumentacao);
        gridDocumentacao.setReloadButton(reloadButtonDocumentacao);
        gridDocumentacao.setSaveButton(saveButtonDocumentacao);
        gridDocumentacao.setValueObjectClassName("com.t2tierp.patrimonio.java.PatrimDocumentoBemVO");
        gridDocumentacao.getColumnContainer().setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        textColumn3.setColumnName("nome");
        textColumn3.setEditableOnEdit(true);
        textColumn3.setEditableOnInsert(true);
        textColumn3.setHeaderColumnName("Nome");
        textColumn3.setHeaderFont(new java.awt.Font("Arial", 1, 11));
        textColumn3.setMaxCharacters(50);
        textColumn3.setPreferredWidth(200);
        gridDocumentacao.getColumnContainer().add(textColumn3);

        textColumn4.setColumnName("descricao");
        textColumn4.setColumnRequired(false);
        textColumn4.setEditableOnEdit(true);
        textColumn4.setEditableOnInsert(true);
        textColumn4.setHeaderColumnName("Descricao");
        textColumn4.setHeaderFont(new java.awt.Font("Arial", 1, 11));
        textColumn4.setMaxCharacters(1000);
        textColumn4.setPreferredWidth(400);
        gridDocumentacao.getColumnContainer().add(textColumn4);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel2.add(gridDocumentacao, gridBagConstraints);

        jTabbedPane1.addTab("Documentacao", jPanel2);

        jPanel4.setLayout(new java.awt.GridBagLayout());

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder("Patrim Depreciacao Bem"));
        jPanel5.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        genericButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                genericButton2ActionPerformed(evt);
            }
        });
        jPanel5.add(genericButton2);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        jPanel4.add(jPanel5, gridBagConstraints);

        gridDepreciacao.setAutoLoadData(false);
        gridDepreciacao.setFunctionId("patrimDepreciacaoBem");
        gridDepreciacao.setValueObjectClassName("com.t2tierp.patrimonio.java.PatrimDepreciacaoBemVO");
        gridDepreciacao.getColumnContainer().setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        dateColumn3.setColumnName("dataDepreciacao");
        dateColumn3.setHeaderColumnName("Data Depreciacao");
        dateColumn3.setHeaderFont(new java.awt.Font("Arial", 1, 11));
        gridDepreciacao.getColumnContainer().add(dateColumn3);

        integerColumn4.setColumnName("dias");
        integerColumn4.setHeaderColumnName("Dias");
        integerColumn4.setHeaderFont(new java.awt.Font("Arial", 1, 11));
        gridDepreciacao.getColumnContainer().add(integerColumn4);

        decimalColumn5.setColumnName("taxa");
        decimalColumn5.setHeaderColumnName("Taxa");
        decimalColumn5.setHeaderFont(new java.awt.Font("Arial", 1, 11));
        decimalColumn5.setDecimals(2);
        gridDepreciacao.getColumnContainer().add(decimalColumn5);

        decimalColumn6.setColumnName("indice");
        decimalColumn6.setHeaderColumnName("Indice");
        decimalColumn6.setHeaderFont(new java.awt.Font("Arial", 1, 11));
        decimalColumn6.setDecimals(2);
        gridDepreciacao.getColumnContainer().add(decimalColumn6);

        decimalColumn7.setColumnName("valor");
        decimalColumn7.setHeaderColumnName("Valor");
        decimalColumn7.setHeaderFont(new java.awt.Font("Arial", 1, 11));
        decimalColumn7.setDecimals(2);
        gridDepreciacao.getColumnContainer().add(decimalColumn7);

        decimalColumn8.setColumnName("depreciacaoAcumulada");
        decimalColumn8.setDecimals(2);
        decimalColumn8.setHeaderColumnName("Depreciacao Acumulada");
        decimalColumn8.setHeaderFont(new java.awt.Font("Arial", 1, 11));
        decimalColumn8.setPreferredWidth(150);
        gridDepreciacao.getColumnContainer().add(decimalColumn8);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel4.add(gridDepreciacao, gridBagConstraints);

        jTabbedPane1.addTab("Depreciacao", jPanel4);

        jPanel6.setLayout(new java.awt.GridBagLayout());

        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder("Patrim Movimentacao Bem"));
        jPanel7.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));
        jPanel7.add(insertButtonMovimentacao);
        jPanel7.add(editButtonMovimentacao);
        jPanel7.add(deleteButtonMovimentacao);
        jPanel7.add(saveButtonMovimentacao);
        jPanel7.add(reloadButtonMovimentacao);
        jPanel7.add(navigatorBarMovimentacao);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        jPanel6.add(jPanel7, gridBagConstraints);

        gridMovimentacao.setAutoLoadData(false);
        gridMovimentacao.setDeleteButton(deleteButtonMovimentacao);
        gridMovimentacao.setEditButton(editButtonMovimentacao);
        gridMovimentacao.setFunctionId("patrimMovimentacaoBem");
        gridMovimentacao.setInsertButton(insertButtonMovimentacao);
        gridMovimentacao.setNavBar(navigatorBarMovimentacao);
        gridMovimentacao.setReloadButton(reloadButtonMovimentacao);
        gridMovimentacao.setSaveButton(saveButtonMovimentacao);
        gridMovimentacao.setValueObjectClassName("com.t2tierp.patrimonio.java.PatrimMovimentacaoBemVO");
        gridMovimentacao.getColumnContainer().setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        codLookupColumn1.setColumnName("patrimTipoMovimentacao.nome");
        codLookupColumn1.setEditableOnEdit(true);
        codLookupColumn1.setEditableOnInsert(true);
        codLookupColumn1.setEnableCodBox(false);
        codLookupColumn1.setHeaderColumnName("Tipo Movimentacao");
        codLookupColumn1.setHeaderFont(new java.awt.Font("Arial", 1, 11));
        codLookupColumn1.setPreferredWidth(150);
        gridMovimentacao.getColumnContainer().add(codLookupColumn1);

        dateColumn4.setColumnName("dataMovimentacao");
        dateColumn4.setEditableOnEdit(true);
        dateColumn4.setEditableOnInsert(true);
        dateColumn4.setHeaderColumnName("Data Movimentacao");
        dateColumn4.setHeaderFont(new java.awt.Font("Arial", 1, 11));
        dateColumn4.setPreferredWidth(120);
        gridMovimentacao.getColumnContainer().add(dateColumn4);

        textColumn7.setColumnName("responsavel");
        textColumn7.setEditableOnEdit(true);
        textColumn7.setEditableOnInsert(true);
        textColumn7.setHeaderColumnName("Responsavel");
        textColumn7.setHeaderFont(new java.awt.Font("Arial", 1, 11));
        textColumn7.setMaxCharacters(50);
        textColumn7.setPreferredWidth(300);
        gridMovimentacao.getColumnContainer().add(textColumn7);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel6.add(gridMovimentacao, gridBagConstraints);

        jTabbedPane1.addTab("Movimentacao", jPanel6);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        getContentPane().add(jTabbedPane1, gridBagConstraints);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void genericButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_genericButton1ActionPerformed
        try {
            documentacaoController.acionaGed();
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(this, "Módulo GED não disponível!", "Erro do Sistema", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Ocorreu um erro ao acionar o GED!\n" + ex.getMessage(), "Erro do Sistema", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_genericButton1ActionPerformed

    private void genericButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_genericButton2ActionPerformed
        try {
            depreciacaoController.calculaDepreciacao();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Ocorreu um erro ao realizar o cáculo da depreciação!\n" + ex.getMessage(), "Erro do Sistema", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_genericButton2ActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private org.openswing.swing.table.columns.client.CodLookupColumn codLookupColumn1;
    private org.openswing.swing.client.CodLookupControl codLookupControl2;
    private org.openswing.swing.client.CodLookupControl codLookupControl3;
    private org.openswing.swing.client.CodLookupControl codLookupControl4;
    private org.openswing.swing.client.CodLookupControl codLookupControl5;
    private org.openswing.swing.client.CodLookupControl codLookupControl6;
    private org.openswing.swing.client.CodLookupControl codLookupControl7;
    private org.openswing.swing.client.ComboBoxControl comboBoxControl26;
    private org.openswing.swing.client.ComboBoxControl comboBoxControl27;
    private org.openswing.swing.client.ComboBoxControl comboBoxControl30;
    private org.openswing.swing.table.columns.client.DateColumn dateColumn3;
    private org.openswing.swing.table.columns.client.DateColumn dateColumn4;
    private org.openswing.swing.client.DateControl dateControl12;
    private org.openswing.swing.client.DateControl dateControl13;
    private org.openswing.swing.client.DateControl dateControl14;
    private org.openswing.swing.client.DateControl dateControl15;
    private org.openswing.swing.client.DateControl dateControl16;
    private org.openswing.swing.client.DateControl dateControl17;
    private org.openswing.swing.client.DateControl dateControl18;
    private org.openswing.swing.client.DateControl dateControl19;
    private org.openswing.swing.client.DateControl dateControl28;
    private org.openswing.swing.client.DateControl dateControl29;
    private org.openswing.swing.table.columns.client.DecimalColumn decimalColumn5;
    private org.openswing.swing.table.columns.client.DecimalColumn decimalColumn6;
    private org.openswing.swing.table.columns.client.DecimalColumn decimalColumn7;
    private org.openswing.swing.table.columns.client.DecimalColumn decimalColumn8;
    private org.openswing.swing.client.DeleteButton deleteButtonDocumentacao;
    private org.openswing.swing.client.DeleteButton deleteButtonMovimentacao;
    private org.openswing.swing.client.EditButton editButton1;
    private org.openswing.swing.client.EditButton editButtonDocumentacao;
    private org.openswing.swing.client.EditButton editButtonMovimentacao;
    private org.openswing.swing.form.client.Form form1;
    private org.openswing.swing.client.GenericButton genericButton1;
    private org.openswing.swing.client.GenericButton genericButton2;
    private org.openswing.swing.client.GridControl gridDepreciacao;
    private org.openswing.swing.client.GridControl gridDocumentacao;
    private org.openswing.swing.client.GridControl gridMovimentacao;
    private org.openswing.swing.client.InsertButton insertButtonDocumentacao;
    private org.openswing.swing.client.InsertButton insertButtonMovimentacao;
    private org.openswing.swing.table.columns.client.IntegerColumn integerColumn4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private org.openswing.swing.client.LabelControl labelControl1;
    private org.openswing.swing.client.LabelControl labelControl11;
    private org.openswing.swing.client.LabelControl labelControl13;
    private org.openswing.swing.client.LabelControl labelControl14;
    private org.openswing.swing.client.LabelControl labelControl15;
    private org.openswing.swing.client.LabelControl labelControl16;
    private org.openswing.swing.client.LabelControl labelControl17;
    private org.openswing.swing.client.LabelControl labelControl18;
    private org.openswing.swing.client.LabelControl labelControl19;
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
    private org.openswing.swing.client.LabelControl labelControl40;
    private org.openswing.swing.client.LabelControl labelControl5;
    private org.openswing.swing.client.LabelControl labelControl7;
    private org.openswing.swing.client.LabelControl labelControl9;
    private org.openswing.swing.client.NavigatorBar navigatorBarDocumentacao;
    private org.openswing.swing.client.NavigatorBar navigatorBarMovimentacao;
    private org.openswing.swing.client.NumericControl numericControl22;
    private org.openswing.swing.client.NumericControl numericControl23;
    private org.openswing.swing.client.NumericControl numericControl24;
    private org.openswing.swing.client.NumericControl numericControl25;
    private org.openswing.swing.client.NumericControl numericControl31;
    private org.openswing.swing.client.NumericControl numericControl32;
    private org.openswing.swing.client.NumericControl numericControl33;
    private org.openswing.swing.client.NumericControl numericControl34;
    private org.openswing.swing.client.ReloadButton reloadButton1;
    private org.openswing.swing.client.ReloadButton reloadButtonDocumentacao;
    private org.openswing.swing.client.ReloadButton reloadButtonMovimentacao;
    private org.openswing.swing.client.SaveButton saveButton1;
    private org.openswing.swing.client.SaveButton saveButtonDocumentacao;
    private org.openswing.swing.client.SaveButton saveButtonMovimentacao;
    private org.openswing.swing.client.TextAreaControl textAreaControl1;
    private org.openswing.swing.table.columns.client.TextColumn textColumn3;
    private org.openswing.swing.table.columns.client.TextColumn textColumn4;
    private org.openswing.swing.table.columns.client.TextColumn textColumn7;
    private org.openswing.swing.client.TextControl textControl11;
    private org.openswing.swing.client.TextControl textControl2;
    private org.openswing.swing.client.TextControl textControl20;
    private org.openswing.swing.client.TextControl textControl21;
    private org.openswing.swing.client.TextControl textControl3;
    private org.openswing.swing.client.TextControl textControl35;
    private org.openswing.swing.client.TextControl textControl4;
    private org.openswing.swing.client.TextControl textControl5;
    private org.openswing.swing.client.TextControl textControl6;
    private org.openswing.swing.client.TextControl textControl7;
    private org.openswing.swing.client.TextControl textControl8;
    private org.openswing.swing.client.TextControl textControl9;
    // End of variables declaration//GEN-END:variables
}
