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
package com.t2tierp.tributacao.cliente;

import com.t2tierp.padrao.cliente.LookupDataLocatorGenerico;
import java.awt.Dimension;
import org.openswing.swing.lookup.client.LookupController;
import org.openswing.swing.mdi.client.InternalFrame;

public class TributIcmsCustomCabDetalhe extends InternalFrame {

    private LookupController ufController = new LookupController();
    private LookupController cfopController = new LookupController();
    private LookupController csosnController = new LookupController();
    private LookupController cstController = new LookupController();
    private TributIcmsCustomDetGridController icmsUfGridController;


    public TributIcmsCustomCabDetalhe(TributIcmsCustomCabDetalheController controller) {
        initComponents();

        form1.setFormController(controller);

        icmsUfGridController = new TributIcmsCustomDetGridController(this);
        gridControlIcms.setController(icmsUfGridController);
        gridControlIcms.setGridDataLocator(icmsUfGridController);

        /*
         * Configurações do lookup da UF
         */
        ufController.setLookupValueObjectClassName("com.t2tierp.cadastros.java.UfVO");
        ufController.addLookup2ParentLink("sigla", "ufDestino");
        ufController.setHeaderColumnName("sigla", "Sigla");
        ufController.setHeaderColumnName("nome", "Nome");
        ufController.setFrameTitle("Importa UF");

        ufController.setVisibleStatusPanel(true);
        ufController.setVisibleColumn("sigla", true);
        ufController.setVisibleColumn("nome", true);
        ufController.setFramePreferedSize(new Dimension(600, 500));

        ufController.setLookupDataLocator(new LookupDataLocatorGenerico(ufController.getLookupValueObjectClassName()));
        codLookupColumn1.setLookupController(ufController);

        /*
         * Configurações do lookup do CFOP
         */
        cfopController.setLookupValueObjectClassName("com.t2tierp.cadastros.java.CfopVO");
        cfopController.addLookup2ParentLink("cfop", "cfop");
        cfopController.setHeaderColumnName("cfop", "CFOP");
        cfopController.setHeaderColumnName("descricao", "Descrição");
        cfopController.setFrameTitle("Importa CFOP");

        cfopController.setVisibleStatusPanel(true);
        cfopController.setVisibleColumn("cfop", true);
        cfopController.setVisibleColumn("descricao", true);
        cfopController.setFramePreferedSize(new Dimension(600, 500));

        cfopController.setLookupDataLocator(new LookupDataLocatorGenerico(cfopController.getLookupValueObjectClassName()));
        codLookupColumn2.setLookupController(cfopController);

        /*
         * Configurações do lookup do CSOSN_B
         */
        csosnController.setLookupValueObjectClassName("com.t2tierp.cadastros.java.CsosnBVO");
        csosnController.addLookup2ParentLink("codigo", "csosnB");
        csosnController.setHeaderColumnName("codigo", "CSOSN_B");
        csosnController.setHeaderColumnName("descricao", "Descrição");
        csosnController.setFrameTitle("Importa CSOSN_B");

        csosnController.setVisibleStatusPanel(true);
        csosnController.setVisibleColumn("codigo", true);
        csosnController.setVisibleColumn("descricao", true);
        csosnController.setFramePreferedSize(new Dimension(600, 500));

        csosnController.setLookupDataLocator(new LookupDataLocatorGenerico(csosnController.getLookupValueObjectClassName()));
        codLookupColumn3.setLookupController(csosnController);

        /*
         * Configurações do lookup do CST_B
         */
        cstController.setLookupValueObjectClassName("com.t2tierp.cadastros.java.CstIcmsBVO");
        cstController.addLookup2ParentLink("codigo", "cstB");
        cstController.setHeaderColumnName("codigo", "CST_B");
        cstController.setHeaderColumnName("descricao", "Descrição");
        cstController.setFrameTitle("Importa CST_B");

        cstController.setVisibleStatusPanel(true);
        cstController.setVisibleColumn("codigo", true);
        cstController.setVisibleColumn("descricao", true);
        cstController.setFramePreferedSize(new Dimension(600, 500));

        cstController.setLookupDataLocator(new LookupDataLocatorGenerico(cstController.getLookupValueObjectClassName()));
        codLookupColumn4.setLookupController(cstController);

    }

    /**
     * @return the form1
     */
    public org.openswing.swing.form.client.Form getForm1() {
        return form1;
    }

    public TributIcmsCustomDetGridController getIcmsUfGridController() {
        return icmsUfGridController;
    }

    public org.openswing.swing.client.GridControl getGridControlIcms() {
        return gridControlIcms;
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
        textControl3 = new org.openswing.swing.client.TextControl();
        labelControl2 = new org.openswing.swing.client.LabelControl();
        comboBoxControl4 = new org.openswing.swing.client.ComboBoxControl();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        insertButtonIcms = new org.openswing.swing.client.InsertButton();
        editButtonIcms = new org.openswing.swing.client.EditButton();
        deleteButtonIcms = new org.openswing.swing.client.DeleteButton();
        saveButtonIcms = new org.openswing.swing.client.SaveButton();
        reloadButtonIcms = new org.openswing.swing.client.ReloadButton();
        gridControlIcms = new org.openswing.swing.client.GridControl();
        codLookupColumn1 = new org.openswing.swing.table.columns.client.CodLookupColumn();
        codLookupColumn2 = new org.openswing.swing.table.columns.client.CodLookupColumn();
        codLookupColumn3 = new org.openswing.swing.table.columns.client.CodLookupColumn();
        codLookupColumn4 = new org.openswing.swing.table.columns.client.CodLookupColumn();
        comboColumn7 = new org.openswing.swing.table.columns.client.ComboColumn();
        decimalColumn8 = new org.openswing.swing.table.columns.client.DecimalColumn();
        decimalColumn9 = new org.openswing.swing.table.columns.client.DecimalColumn();
        decimalColumn10 = new org.openswing.swing.table.columns.client.DecimalColumn();
        decimalColumn11 = new org.openswing.swing.table.columns.client.DecimalColumn();
        decimalColumn12 = new org.openswing.swing.table.columns.client.DecimalColumn();
        comboColumn13 = new org.openswing.swing.table.columns.client.ComboColumn();
        decimalColumn14 = new org.openswing.swing.table.columns.client.DecimalColumn();
        decimalColumn15 = new org.openswing.swing.table.columns.client.DecimalColumn();
        decimalColumn16 = new org.openswing.swing.table.columns.client.DecimalColumn();
        decimalColumn17 = new org.openswing.swing.table.columns.client.DecimalColumn();
        decimalColumn18 = new org.openswing.swing.table.columns.client.DecimalColumn();
        decimalColumn19 = new org.openswing.swing.table.columns.client.DecimalColumn();

        setTitle("T2Ti ERP - Tributacao");
        setPreferredSize(new java.awt.Dimension(700, 400));
        getContentPane().setLayout(new java.awt.GridBagLayout());

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Tribut Icms Custom Cab"));
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

        form1.setVOClassName("com.t2tierp.tributacao.java.TributIcmsCustomCabVO");
        form1.setEditButton(editButton1);
        form1.setFunctionId("tributIcmsCustomCab");
        form1.setReloadButton(reloadButton1);
        form1.setSaveButton(saveButton1);
        form1.setLayout(new java.awt.GridBagLayout());

        labelControl1.setLabel("Descricao:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        form1.add(labelControl1, gridBagConstraints);

        textControl3.setAttributeName("descricao");
        textControl3.setEnabled(false);
        textControl3.setMaxCharacters(100);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        form1.add(textControl3, gridBagConstraints);

        labelControl2.setLabel("Origem Mercadoria:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        form1.add(labelControl2, gridBagConstraints);

        comboBoxControl4.setAttributeName("origemMercadoria");
        comboBoxControl4.setDomainId("origemMercadoria");
        comboBoxControl4.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 150;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        form1.add(comboBoxControl4, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        getContentPane().add(form1, gridBagConstraints);

        jPanel2.setLayout(new java.awt.GridBagLayout());

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Tribut Icms Custom Det"));
        jPanel3.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));
        jPanel3.add(insertButtonIcms);
        jPanel3.add(editButtonIcms);
        jPanel3.add(deleteButtonIcms);
        jPanel3.add(saveButtonIcms);
        jPanel3.add(reloadButtonIcms);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        jPanel2.add(jPanel3, gridBagConstraints);

        gridControlIcms.setAutoLoadData(false);
        gridControlIcms.setDeleteButton(deleteButtonIcms);
        gridControlIcms.setEditButton(editButtonIcms);
        gridControlIcms.setFunctionId("tributIcmsCustomDet");
        gridControlIcms.setInsertButton(insertButtonIcms);
        gridControlIcms.setReloadButton(reloadButtonIcms);
        gridControlIcms.setSaveButton(saveButtonIcms);
        gridControlIcms.setValueObjectClassName("com.t2tierp.tributacao.java.TributIcmsCustomDetVO");
        gridControlIcms.getColumnContainer().setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        codLookupColumn1.setColumnName("ufDestino");
        codLookupColumn1.setColumnRequired(false);
        codLookupColumn1.setEditableOnEdit(true);
        codLookupColumn1.setEditableOnInsert(true);
        codLookupColumn1.setHeaderColumnName("UF Destino");
        codLookupColumn1.setHeaderFont(new java.awt.Font("Arial", 1, 11));
        codLookupColumn1.setMaxCharacters(2);
        gridControlIcms.getColumnContainer().add(codLookupColumn1);

        codLookupColumn2.setAllowOnlyNumbers(true);
        codLookupColumn2.setColumnName("cfop");
        codLookupColumn2.setColumnRequired(false);
        codLookupColumn2.setEditableOnEdit(true);
        codLookupColumn2.setEditableOnInsert(true);
        codLookupColumn2.setHeaderColumnName("CFOP");
        codLookupColumn2.setHeaderFont(new java.awt.Font("Arial", 1, 11));
        codLookupColumn2.setMaxCharacters(4);
        gridControlIcms.getColumnContainer().add(codLookupColumn2);

        codLookupColumn3.setColumnName("csosnB");
        codLookupColumn3.setColumnRequired(false);
        codLookupColumn3.setEditableOnEdit(true);
        codLookupColumn3.setEditableOnInsert(true);
        codLookupColumn3.setHeaderColumnName("CSOSN B");
        codLookupColumn3.setHeaderFont(new java.awt.Font("Arial", 1, 11));
        codLookupColumn3.setMaxCharacters(3);
        gridControlIcms.getColumnContainer().add(codLookupColumn3);

        codLookupColumn4.setColumnName("cstB");
        codLookupColumn4.setColumnRequired(false);
        codLookupColumn4.setEditableOnEdit(true);
        codLookupColumn4.setEditableOnInsert(true);
        codLookupColumn4.setHeaderColumnName("CST B");
        codLookupColumn4.setHeaderFont(new java.awt.Font("Arial", 1, 11));
        codLookupColumn4.setMaxCharacters(2);
        gridControlIcms.getColumnContainer().add(codLookupColumn4);

        comboColumn7.setColumnName("modalidadeBc");
        comboColumn7.setDomainId("tributIcmsBaseCalculo");
        comboColumn7.setEditableOnEdit(true);
        comboColumn7.setEditableOnInsert(true);
        comboColumn7.setHeaderColumnName("Modalidade Bc");
        comboColumn7.setHeaderFont(new java.awt.Font("Arial", 1, 11));
        gridControlIcms.getColumnContainer().add(comboColumn7);

        decimalColumn8.setColumnName("aliquota");
        decimalColumn8.setColumnRequired(false);
        decimalColumn8.setDecimals(2);
        decimalColumn8.setEditableOnEdit(true);
        decimalColumn8.setEditableOnInsert(true);
        decimalColumn8.setHeaderColumnName("Aliquota");
        decimalColumn8.setHeaderFont(new java.awt.Font("Arial", 1, 11));
        gridControlIcms.getColumnContainer().add(decimalColumn8);

        decimalColumn9.setColumnName("valorPauta");
        decimalColumn9.setColumnRequired(false);
        decimalColumn9.setDecimals(2);
        decimalColumn9.setEditableOnEdit(true);
        decimalColumn9.setEditableOnInsert(true);
        decimalColumn9.setHeaderColumnName("Valor Pauta");
        decimalColumn9.setHeaderFont(new java.awt.Font("Arial", 1, 11));
        gridControlIcms.getColumnContainer().add(decimalColumn9);

        decimalColumn10.setColumnName("valorPrecoMaximo");
        decimalColumn10.setColumnRequired(false);
        decimalColumn10.setDecimals(2);
        decimalColumn10.setEditableOnEdit(true);
        decimalColumn10.setEditableOnInsert(true);
        decimalColumn10.setHeaderColumnName("Valor Preco Maximo");
        decimalColumn10.setHeaderFont(new java.awt.Font("Arial", 1, 11));
        gridControlIcms.getColumnContainer().add(decimalColumn10);

        decimalColumn11.setColumnName("mva");
        decimalColumn11.setColumnRequired(false);
        decimalColumn11.setDecimals(2);
        decimalColumn11.setEditableOnEdit(true);
        decimalColumn11.setEditableOnInsert(true);
        decimalColumn11.setHeaderColumnName("Mva");
        decimalColumn11.setHeaderFont(new java.awt.Font("Arial", 1, 11));
        gridControlIcms.getColumnContainer().add(decimalColumn11);

        decimalColumn12.setColumnName("porcentoBc");
        decimalColumn12.setColumnRequired(false);
        decimalColumn12.setDecimals(2);
        decimalColumn12.setEditableOnEdit(true);
        decimalColumn12.setEditableOnInsert(true);
        decimalColumn12.setHeaderColumnName("Porcento Bc");
        decimalColumn12.setHeaderFont(new java.awt.Font("Arial", 1, 11));
        gridControlIcms.getColumnContainer().add(decimalColumn12);

        comboColumn13.setColumnName("modalidadeBcSt");
        comboColumn13.setColumnRequired(false);
        comboColumn13.setDomainId("tributIcmsStBaseCalculo");
        comboColumn13.setEditableOnEdit(true);
        comboColumn13.setEditableOnInsert(true);
        comboColumn13.setHeaderColumnName("Modalidade Bc St");
        comboColumn13.setHeaderFont(new java.awt.Font("Arial", 1, 11));
        gridControlIcms.getColumnContainer().add(comboColumn13);

        decimalColumn14.setColumnName("aliquotaInternaSt");
        decimalColumn14.setColumnRequired(false);
        decimalColumn14.setDecimals(2);
        decimalColumn14.setEditableOnEdit(true);
        decimalColumn14.setEditableOnInsert(true);
        decimalColumn14.setHeaderColumnName("Aliquota Interna St");
        decimalColumn14.setHeaderFont(new java.awt.Font("Arial", 1, 11));
        gridControlIcms.getColumnContainer().add(decimalColumn14);

        decimalColumn15.setColumnName("aliquotaInterestadualSt");
        decimalColumn15.setColumnRequired(false);
        decimalColumn15.setDecimals(2);
        decimalColumn15.setEditableOnEdit(true);
        decimalColumn15.setEditableOnInsert(true);
        decimalColumn15.setHeaderColumnName("Aliquota Interestadual St");
        decimalColumn15.setHeaderFont(new java.awt.Font("Arial", 1, 11));
        gridControlIcms.getColumnContainer().add(decimalColumn15);

        decimalColumn16.setColumnName("porcentoBcSt");
        decimalColumn16.setColumnRequired(false);
        decimalColumn16.setDecimals(2);
        decimalColumn16.setEditableOnEdit(true);
        decimalColumn16.setEditableOnInsert(true);
        decimalColumn16.setHeaderColumnName("Porcento Bc St");
        decimalColumn16.setHeaderFont(new java.awt.Font("Arial", 1, 11));
        gridControlIcms.getColumnContainer().add(decimalColumn16);

        decimalColumn17.setColumnName("aliquotaIcmsSt");
        decimalColumn17.setColumnRequired(false);
        decimalColumn17.setDecimals(2);
        decimalColumn17.setEditableOnEdit(true);
        decimalColumn17.setEditableOnInsert(true);
        decimalColumn17.setHeaderColumnName("Aliquota Icms St");
        decimalColumn17.setHeaderFont(new java.awt.Font("Arial", 1, 11));
        gridControlIcms.getColumnContainer().add(decimalColumn17);

        decimalColumn18.setColumnName("valorPautaSt");
        decimalColumn18.setColumnRequired(false);
        decimalColumn18.setDecimals(2);
        decimalColumn18.setEditableOnEdit(true);
        decimalColumn18.setEditableOnInsert(true);
        decimalColumn18.setHeaderColumnName("Valor Pauta St");
        decimalColumn18.setHeaderFont(new java.awt.Font("Arial", 1, 11));
        gridControlIcms.getColumnContainer().add(decimalColumn18);

        decimalColumn19.setColumnName("valorPrecoMaximoSt");
        decimalColumn19.setColumnRequired(false);
        decimalColumn19.setDecimals(2);
        decimalColumn19.setEditableOnEdit(true);
        decimalColumn19.setEditableOnInsert(true);
        decimalColumn19.setHeaderColumnName("Valor Preco Maximo St");
        decimalColumn19.setHeaderFont(new java.awt.Font("Arial", 1, 11));
        gridControlIcms.getColumnContainer().add(decimalColumn19);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel2.add(gridControlIcms, gridBagConstraints);

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
    private org.openswing.swing.table.columns.client.CodLookupColumn codLookupColumn2;
    private org.openswing.swing.table.columns.client.CodLookupColumn codLookupColumn3;
    private org.openswing.swing.table.columns.client.CodLookupColumn codLookupColumn4;
    private org.openswing.swing.client.ComboBoxControl comboBoxControl4;
    private org.openswing.swing.table.columns.client.ComboColumn comboColumn13;
    private org.openswing.swing.table.columns.client.ComboColumn comboColumn7;
    private org.openswing.swing.table.columns.client.DecimalColumn decimalColumn10;
    private org.openswing.swing.table.columns.client.DecimalColumn decimalColumn11;
    private org.openswing.swing.table.columns.client.DecimalColumn decimalColumn12;
    private org.openswing.swing.table.columns.client.DecimalColumn decimalColumn14;
    private org.openswing.swing.table.columns.client.DecimalColumn decimalColumn15;
    private org.openswing.swing.table.columns.client.DecimalColumn decimalColumn16;
    private org.openswing.swing.table.columns.client.DecimalColumn decimalColumn17;
    private org.openswing.swing.table.columns.client.DecimalColumn decimalColumn18;
    private org.openswing.swing.table.columns.client.DecimalColumn decimalColumn19;
    private org.openswing.swing.table.columns.client.DecimalColumn decimalColumn8;
    private org.openswing.swing.table.columns.client.DecimalColumn decimalColumn9;
    private org.openswing.swing.client.DeleteButton deleteButtonIcms;
    private org.openswing.swing.client.EditButton editButton1;
    private org.openswing.swing.client.EditButton editButtonIcms;
    private org.openswing.swing.form.client.Form form1;
    private org.openswing.swing.client.GridControl gridControlIcms;
    private org.openswing.swing.client.InsertButton insertButtonIcms;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private org.openswing.swing.client.LabelControl labelControl1;
    private org.openswing.swing.client.LabelControl labelControl2;
    private org.openswing.swing.client.ReloadButton reloadButton1;
    private org.openswing.swing.client.ReloadButton reloadButtonIcms;
    private org.openswing.swing.client.SaveButton saveButton1;
    private org.openswing.swing.client.SaveButton saveButtonIcms;
    private org.openswing.swing.client.TextControl textControl3;
    // End of variables declaration//GEN-END:variables

}
