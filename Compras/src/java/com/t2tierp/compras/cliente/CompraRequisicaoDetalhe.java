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
package com.t2tierp.compras.cliente;

import com.t2tierp.padrao.cliente.LookupDataLocatorGenerico;
import java.awt.Dimension;
import org.openswing.swing.lookup.client.LookupController;
import org.openswing.swing.mdi.client.InternalFrame;

public class CompraRequisicaoDetalhe extends InternalFrame {

    private LookupController tipoRequisicaoController = new LookupController();
    private LookupController colaboradorController = new LookupController();
    private LookupController produtoController = new LookupController();
    private CompraRequisicaoDetalheController controller;
    private CompraRequisicaoDetalheGridController requisicaoDetalheGridController;

    public CompraRequisicaoDetalhe(CompraRequisicaoDetalheController controller) {
        initComponents();

        this.controller = controller;
        form1.setFormController(controller);

        requisicaoDetalheGridController = new CompraRequisicaoDetalheGridController(this);
        gridRequisicaoDetalhe.setController(requisicaoDetalheGridController);
        gridRequisicaoDetalhe.setGridDataLocator(requisicaoDetalheGridController);

        /*
         * Configurações do lookup do tipo de requisicao
         */
        tipoRequisicaoController.setLookupValueObjectClassName("com.t2tierp.compras.java.CompraTipoRequisicaoVO");
        tipoRequisicaoController.addLookup2ParentLink("id", "compraTipoRequisicao.id");
        tipoRequisicaoController.addLookup2ParentLink("nome", "compraTipoRequisicao.nome");
        tipoRequisicaoController.setHeaderColumnName("id", "ID");
        tipoRequisicaoController.setHeaderColumnName("nome", "Nome");
        tipoRequisicaoController.setFrameTitle("Importa Tipo Requisição");

        tipoRequisicaoController.setVisibleStatusPanel(true);
        tipoRequisicaoController.setVisibleColumn("id", true);
        tipoRequisicaoController.setVisibleColumn("nome", true);
        tipoRequisicaoController.setFramePreferedSize(new Dimension(600, 500));

        tipoRequisicaoController.setLookupDataLocator(new LookupDataLocatorGenerico(tipoRequisicaoController.getLookupValueObjectClassName()));
        codLookupControl2.setLookupController(tipoRequisicaoController);

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
        codLookupControl3.setLookupController(colaboradorController);

        /*
         * Configurações do lookup do produto
         */
        produtoController.setLookupValueObjectClassName("com.t2tierp.cadastros.java.ProdutoVO");
        produtoController.addLookup2ParentLink("id", "produto.id");
        produtoController.addLookup2ParentLink("nome", "produto.nome");
        produtoController.setHeaderColumnName("id", "ID");
        produtoController.setHeaderColumnName("nome", "Nome");
        produtoController.setFrameTitle("Importa Produto");

        produtoController.setVisibleStatusPanel(true);
        produtoController.setVisibleColumn("id", true);
        produtoController.setVisibleColumn("nome", true);
        produtoController.setPreferredWidthColumn("nome", 300);
        produtoController.setFramePreferedSize(new Dimension(600, 500));

        produtoController.setLookupDataLocator(new LookupDataLocatorGenerico(produtoController.getLookupValueObjectClassName()));
        codLookupColumn1.setLookupController(produtoController);
    }

    /**
     * @return the form1
     */
    public org.openswing.swing.form.client.Form getForm1() {
        return form1;
    }

    /**
     * @return the controller
     */
    public CompraRequisicaoDetalheController getController() {
        return controller;
    }

    /**
     * @return the gridRequisicaoDetalhe
     */
    public org.openswing.swing.client.GridControl getGridRequisicaoDetalhe() {
        return gridRequisicaoDetalhe;
    }

    /**
     * @return the requisicaoDetalheGridController
     */
    public CompraRequisicaoDetalheGridController getRequisicaoDetalheGridController() {
        return requisicaoDetalheGridController;
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
        dateControl4 = new org.openswing.swing.client.DateControl();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        insertButtonRequisicaoDetalhe = new org.openswing.swing.client.InsertButton();
        editButtonRequisicaoDetalhe = new org.openswing.swing.client.EditButton();
        deleteButtonRequisicaoDetalhe = new org.openswing.swing.client.DeleteButton();
        saveButtonRequisicaoDetalhe = new org.openswing.swing.client.SaveButton();
        reloadButtonRequisicaoDetalhe = new org.openswing.swing.client.ReloadButton();
        navigatorBarRequisicaoDetalhe = new org.openswing.swing.client.NavigatorBar();
        gridRequisicaoDetalhe = new org.openswing.swing.client.GridControl();
        codLookupColumn1 = new org.openswing.swing.table.columns.client.CodLookupColumn();
        decimalColumn4 = new org.openswing.swing.table.columns.client.DecimalColumn();
        decimalColumn5 = new org.openswing.swing.table.columns.client.DecimalColumn();
        comboColumn6 = new org.openswing.swing.table.columns.client.ComboColumn();

        setTitle("T2Ti ERP - Gestão de Compras");
        setPreferredSize(new java.awt.Dimension(700, 400));
        getContentPane().setLayout(new java.awt.GridBagLayout());

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Compra Requisicao"));
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

        form1.setVOClassName("com.t2tierp.compras.java.CompraRequisicaoVO");
        form1.setEditButton(editButton1);
        form1.setFunctionId("compraRequisicao");
        form1.setReloadButton(reloadButton1);
        form1.setSaveButton(saveButton1);
        form1.setLayout(new java.awt.GridBagLayout());

        labelControl1.setLabel("Tipo Requisicao:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        form1.add(labelControl1, gridBagConstraints);

        codLookupControl2.setAllowOnlyNumbers(true);
        codLookupControl2.setAttributeName("compraTipoRequisicao.id");
        codLookupControl2.setEnabled(false);
        codLookupControl2.setRequired(true);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = -70;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        form1.add(codLookupControl2, gridBagConstraints);

        textControl2.setAttributeName("compraTipoRequisicao.nome");
        textControl2.setEnabled(false);
        textControl2.setEnabledOnEdit(false);
        textControl2.setEnabledOnInsert(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        form1.add(textControl2, gridBagConstraints);

        labelControl3.setLabel("Colaborador:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        form1.add(labelControl3, gridBagConstraints);

        codLookupControl3.setAllowOnlyNumbers(true);
        codLookupControl3.setAttributeName("colaborador.id");
        codLookupControl3.setEnabled(false);
        codLookupControl3.setRequired(true);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = -70;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        form1.add(codLookupControl3, gridBagConstraints);

        textControl3.setAttributeName("colaborador.pessoa.nome");
        textControl3.setEnabled(false);
        textControl3.setEnabledOnEdit(false);
        textControl3.setEnabledOnInsert(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        form1.add(textControl3, gridBagConstraints);

        labelControl5.setLabel("Data Requisicao:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        form1.add(labelControl5, gridBagConstraints);

        dateControl4.setAttributeName("dataRequisicao");
        dateControl4.setEnabled(false);
        dateControl4.setEnabledOnEdit(false);
        dateControl4.setEnabledOnInsert(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        form1.add(dateControl4, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        getContentPane().add(form1, gridBagConstraints);

        jPanel2.setLayout(new java.awt.GridBagLayout());

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Compra Requisicao Detalhe"));
        jPanel3.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));
        jPanel3.add(insertButtonRequisicaoDetalhe);
        jPanel3.add(editButtonRequisicaoDetalhe);
        jPanel3.add(deleteButtonRequisicaoDetalhe);
        jPanel3.add(saveButtonRequisicaoDetalhe);
        jPanel3.add(reloadButtonRequisicaoDetalhe);
        jPanel3.add(navigatorBarRequisicaoDetalhe);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        jPanel2.add(jPanel3, gridBagConstraints);

        gridRequisicaoDetalhe.setAutoLoadData(false);
        gridRequisicaoDetalhe.setDeleteButton(deleteButtonRequisicaoDetalhe);
        gridRequisicaoDetalhe.setEditButton(editButtonRequisicaoDetalhe);
        gridRequisicaoDetalhe.setFunctionId("compraRequisicaoDetalhe");
        gridRequisicaoDetalhe.setInsertButton(insertButtonRequisicaoDetalhe);
        gridRequisicaoDetalhe.setNavBar(navigatorBarRequisicaoDetalhe);
        gridRequisicaoDetalhe.setReloadButton(reloadButtonRequisicaoDetalhe);
        gridRequisicaoDetalhe.setSaveButton(saveButtonRequisicaoDetalhe);
        gridRequisicaoDetalhe.setValueObjectClassName("com.t2tierp.compras.java.CompraRequisicaoDetalheVO");
        gridRequisicaoDetalhe.getColumnContainer().setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        codLookupColumn1.setColumnName("produto.nome");
        codLookupColumn1.setEditableOnEdit(true);
        codLookupColumn1.setEditableOnInsert(true);
        codLookupColumn1.setHeaderColumnName("Produto");
        codLookupColumn1.setHeaderFont(new java.awt.Font("Arial", 1, 11));
        codLookupColumn1.setPreferredWidth(300);
        gridRequisicaoDetalhe.getColumnContainer().add(codLookupColumn1);

        decimalColumn4.setColumnName("quantidade");
        decimalColumn4.setDecimals(2);
        decimalColumn4.setEditableOnEdit(true);
        decimalColumn4.setEditableOnInsert(true);
        decimalColumn4.setHeaderColumnName("Quantidade");
        decimalColumn4.setHeaderFont(new java.awt.Font("Arial", 1, 11));
        gridRequisicaoDetalhe.getColumnContainer().add(decimalColumn4);

        decimalColumn5.setColumnName("quantidadeCotada");
        decimalColumn5.setColumnRequired(false);
        decimalColumn5.setDecimals(2);
        decimalColumn5.setHeaderColumnName("Quantidade Cotada");
        decimalColumn5.setHeaderFont(new java.awt.Font("Arial", 1, 11));
        decimalColumn5.setPreferredWidth(120);
        gridRequisicaoDetalhe.getColumnContainer().add(decimalColumn5);

        comboColumn6.setColumnName("itemCotado");
        comboColumn6.setColumnRequired(false);
        comboColumn6.setDomainId("simnao");
        comboColumn6.setHeaderColumnName("Item Cotado");
        comboColumn6.setHeaderFont(new java.awt.Font("Arial", 1, 11));
        gridRequisicaoDetalhe.getColumnContainer().add(comboColumn6);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel2.add(gridRequisicaoDetalhe, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        getContentPane().add(jPanel2, gridBagConstraints);

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private org.openswing.swing.table.columns.client.CodLookupColumn codLookupColumn1;
    private org.openswing.swing.client.CodLookupControl codLookupControl2;
    private org.openswing.swing.client.CodLookupControl codLookupControl3;
    private org.openswing.swing.table.columns.client.ComboColumn comboColumn6;
    private org.openswing.swing.client.DateControl dateControl4;
    private org.openswing.swing.table.columns.client.DecimalColumn decimalColumn4;
    private org.openswing.swing.table.columns.client.DecimalColumn decimalColumn5;
    private org.openswing.swing.client.DeleteButton deleteButtonRequisicaoDetalhe;
    private org.openswing.swing.client.EditButton editButton1;
    private org.openswing.swing.client.EditButton editButtonRequisicaoDetalhe;
    private org.openswing.swing.form.client.Form form1;
    private org.openswing.swing.client.GridControl gridRequisicaoDetalhe;
    private org.openswing.swing.client.InsertButton insertButtonRequisicaoDetalhe;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private org.openswing.swing.client.LabelControl labelControl1;
    private org.openswing.swing.client.LabelControl labelControl3;
    private org.openswing.swing.client.LabelControl labelControl5;
    private org.openswing.swing.client.NavigatorBar navigatorBarRequisicaoDetalhe;
    private org.openswing.swing.client.ReloadButton reloadButton1;
    private org.openswing.swing.client.ReloadButton reloadButtonRequisicaoDetalhe;
    private org.openswing.swing.client.SaveButton saveButton1;
    private org.openswing.swing.client.SaveButton saveButtonRequisicaoDetalhe;
    private org.openswing.swing.client.TextControl textControl2;
    private org.openswing.swing.client.TextControl textControl3;
    // End of variables declaration//GEN-END:variables


}
