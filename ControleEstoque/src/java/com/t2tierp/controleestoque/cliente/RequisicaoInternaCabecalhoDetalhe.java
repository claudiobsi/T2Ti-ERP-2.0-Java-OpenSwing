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

import com.t2tierp.padrao.cliente.LookupDataLocatorGenerico;
import java.awt.Dimension;
import javax.swing.ImageIcon;
import org.openswing.swing.client.GenericButton;
import org.openswing.swing.lookup.client.LookupController;
import org.openswing.swing.mdi.client.InternalFrame;
import org.openswing.swing.util.client.ClientUtils;

public class RequisicaoInternaCabecalhoDetalhe extends InternalFrame {

    private RequisicaoInternaDetalheGridController itensController;
    private LookupController colaboradorController = new LookupController();
    private LookupController produtoController = new LookupController();
    RequisicaoInternaCabecalhoDetalheController controller;

    public RequisicaoInternaCabecalhoDetalhe(RequisicaoInternaCabecalhoDetalheController controller) {
        initComponents();

        this.controller = controller;
        form1.setFormController(controller);

        itensController = new RequisicaoInternaDetalheGridController(this);
        gridControl1.setController(itensController);
        gridControl1.setGridDataLocator(itensController);

        genericButton1.setToolTipText("Deferir Requisição");
        genericButton2.setToolTipText("Indeferir Requisição");

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
        codLookupControl2.setLookupController(colaboradorController);

        /*
         * Configurações do lookup do produto
         */
        produtoController.setLookupValueObjectClassName("com.t2tierp.cadastros.java.ProdutoVO");
        produtoController.addLookup2ParentLink("id", "produto.id");
        produtoController.addLookup2ParentLink("nome", "produto.nome");
        produtoController.addLookup2ParentLink("gtin", "produto.gtin");
        produtoController.setHeaderColumnName("id", "ID");
        produtoController.setHeaderColumnName("nome", "Nome");
        produtoController.setHeaderColumnName("gtin", "GTIN");
        produtoController.setFrameTitle("Importa Produto");

        produtoController.setVisibleStatusPanel(true);
        produtoController.setVisibleColumn("id", true);
        produtoController.setVisibleColumn("nome", true);
        produtoController.setVisibleColumn("gtin", true);
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

    public RequisicaoInternaDetalheGridController getItensController() {
        return itensController;
    }

    public org.openswing.swing.client.GridControl getGridControl1() {
        return gridControl1;
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
        genericButton1 = new GenericButton(new ImageIcon(ClientUtils.getImage("ok.gif")));
        genericButton2 = new GenericButton(new ImageIcon(ClientUtils.getImage("delete_x16.png")));
        form1 = new org.openswing.swing.form.client.Form();
        labelControl1 = new org.openswing.swing.client.LabelControl();
        codLookupControl2 = new org.openswing.swing.client.CodLookupControl();
        textControl2 = new org.openswing.swing.client.TextControl();
        labelControl3 = new org.openswing.swing.client.LabelControl();
        dateControl3 = new org.openswing.swing.client.DateControl();
        labelControl4 = new org.openswing.swing.client.LabelControl();
        comboBoxControl4 = new org.openswing.swing.client.ComboBoxControl();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        insertButton1 = new org.openswing.swing.client.InsertButton();
        editButton2 = new org.openswing.swing.client.EditButton();
        deleteButton1 = new org.openswing.swing.client.DeleteButton();
        saveButton2 = new org.openswing.swing.client.SaveButton();
        reloadButton2 = new org.openswing.swing.client.ReloadButton();
        navigatorBar1 = new org.openswing.swing.client.NavigatorBar();
        gridControl1 = new org.openswing.swing.client.GridControl();
        codLookupColumn1 = new org.openswing.swing.table.columns.client.CodLookupColumn();
        textColumn4 = new org.openswing.swing.table.columns.client.TextColumn();
        decimalColumn4 = new org.openswing.swing.table.columns.client.DecimalColumn();

        setTitle("T2Ti ERP - Controle de Estoque");
        setPreferredSize(new java.awt.Dimension(700, 400));
        getContentPane().setLayout(new java.awt.GridBagLayout());

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Requisicao Interna Cabecalho"));
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

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        getContentPane().add(jPanel1, gridBagConstraints);

        form1.setVOClassName("com.t2tierp.controleestoque.java.RequisicaoInternaCabecalhoVO");
        form1.setEditButton(editButton1);
        form1.setFunctionId("requisicaoInternaCabecalho");
        form1.setReloadButton(reloadButton1);
        form1.setSaveButton(saveButton1);
        form1.setLayout(new java.awt.GridBagLayout());

        labelControl1.setText("Colaborador:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        form1.add(labelControl1, gridBagConstraints);

        codLookupControl2.setAllowOnlyNumbers(true);
        codLookupControl2.setAttributeName("colaborador.id");
        codLookupControl2.setEnabled(false);
        codLookupControl2.setLinkLabel(labelControl1);
        codLookupControl2.setMaxCharacters(10);
        codLookupControl2.setRequired(true);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = -50;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        form1.add(codLookupControl2, gridBagConstraints);

        textControl2.setAttributeName("colaborador.pessoa.nome");
        textControl2.setEnabled(false);
        textControl2.setEnabledOnEdit(false);
        textControl2.setEnabledOnInsert(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        form1.add(textControl2, gridBagConstraints);

        labelControl3.setLabel("Data Requisicao:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        form1.add(labelControl3, gridBagConstraints);

        dateControl3.setAttributeName("dataRequisicao");
        dateControl3.setEnabled(false);
        dateControl3.setLinkLabel(labelControl3);
        dateControl3.setRequired(true);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        form1.add(dateControl3, gridBagConstraints);

        labelControl4.setLabel("Situacao:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        form1.add(labelControl4, gridBagConstraints);

        comboBoxControl4.setAttributeName("situacao");
        comboBoxControl4.setDomainId("requisicaoInternaSituacao");
        comboBoxControl4.setEnabled(false);
        comboBoxControl4.setEnabledOnEdit(false);
        comboBoxControl4.setEnabledOnInsert(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints.ipadx = 100;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        form1.add(comboBoxControl4, gridBagConstraints);

        jPanel2.setLayout(new java.awt.GridBagLayout());

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Requisicao Interna Detalhe"));
        jPanel3.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));
        jPanel3.add(insertButton1);
        jPanel3.add(editButton2);
        jPanel3.add(deleteButton1);
        jPanel3.add(saveButton2);
        jPanel3.add(reloadButton2);
        jPanel3.add(navigatorBar1);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        jPanel2.add(jPanel3, gridBagConstraints);

        gridControl1.setAutoLoadData(false);
        gridControl1.setDeleteButton(deleteButton1);
        gridControl1.setEditButton(editButton2);
        gridControl1.setFunctionId("requisicaoInternaDetalhe");
        gridControl1.setInsertButton(insertButton1);
        gridControl1.setNavBar(navigatorBar1);
        gridControl1.setReloadButton(reloadButton2);
        gridControl1.setSaveButton(saveButton2);
        gridControl1.setValueObjectClassName("com.t2tierp.controleestoque.java.RequisicaoInternaDetalheVO");
        gridControl1.getColumnContainer().setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        codLookupColumn1.setColumnName("produto.nome");
        codLookupColumn1.setEditableOnEdit(true);
        codLookupColumn1.setEditableOnInsert(true);
        codLookupColumn1.setHeaderColumnName("Produto");
        codLookupColumn1.setHeaderFont(new java.awt.Font("Arial", 1, 11));
        codLookupColumn1.setPreferredWidth(400);
        gridControl1.getColumnContainer().add(codLookupColumn1);

        textColumn4.setColumnName("produto.gtin");
        textColumn4.setColumnRequired(false);
        textColumn4.setHeaderColumnName("GTIN");
        textColumn4.setHeaderFont(new java.awt.Font("Arial", 1, 11));
        gridControl1.getColumnContainer().add(textColumn4);

        decimalColumn4.setColumnName("quantidade");
        decimalColumn4.setDecimals(2);
        decimalColumn4.setEditableOnEdit(true);
        decimalColumn4.setEditableOnInsert(true);
        decimalColumn4.setHeaderColumnName("Quantidade");
        decimalColumn4.setHeaderFont(new java.awt.Font("Arial", 1, 11));
        gridControl1.getColumnContainer().add(decimalColumn4);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel2.add(gridControl1, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        form1.add(jPanel2, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        getContentPane().add(form1, gridBagConstraints);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void genericButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_genericButton1ActionPerformed
        controller.deferirRequisicao();
    }//GEN-LAST:event_genericButton1ActionPerformed

    private void genericButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_genericButton2ActionPerformed
        controller.indeferirRequisicao();
    }//GEN-LAST:event_genericButton2ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private org.openswing.swing.table.columns.client.CodLookupColumn codLookupColumn1;
    private org.openswing.swing.client.CodLookupControl codLookupControl2;
    private org.openswing.swing.client.ComboBoxControl comboBoxControl4;
    private org.openswing.swing.client.DateControl dateControl3;
    private org.openswing.swing.table.columns.client.DecimalColumn decimalColumn4;
    private org.openswing.swing.client.DeleteButton deleteButton1;
    private org.openswing.swing.client.EditButton editButton1;
    private org.openswing.swing.client.EditButton editButton2;
    private org.openswing.swing.form.client.Form form1;
    private org.openswing.swing.client.GenericButton genericButton1;
    private org.openswing.swing.client.GenericButton genericButton2;
    private org.openswing.swing.client.GridControl gridControl1;
    private org.openswing.swing.client.InsertButton insertButton1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private org.openswing.swing.client.LabelControl labelControl1;
    private org.openswing.swing.client.LabelControl labelControl3;
    private org.openswing.swing.client.LabelControl labelControl4;
    private org.openswing.swing.client.NavigatorBar navigatorBar1;
    private org.openswing.swing.client.ReloadButton reloadButton1;
    private org.openswing.swing.client.ReloadButton reloadButton2;
    private org.openswing.swing.client.SaveButton saveButton1;
    private org.openswing.swing.client.SaveButton saveButton2;
    private org.openswing.swing.table.columns.client.TextColumn textColumn4;
    private org.openswing.swing.client.TextControl textControl2;
    // End of variables declaration//GEN-END:variables


}
