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
package com.t2tierp.wms.cliente;

import com.t2tierp.padrao.cliente.LookupDataLocatorGenerico;
import java.awt.Dimension;
import javax.swing.ImageIcon;
import javax.swing.ListSelectionModel;
import org.openswing.swing.lookup.client.LookupController;
import org.openswing.swing.mdi.client.InternalFrame;
import org.openswing.swing.util.client.ClientUtils;

public class WmsArmazenamentoDetalhe extends InternalFrame {

    private WmsArmazenamentoDetalheController controller;
    private WmsArmazenamentoItensController itensController;
    private WmsArmazenamentoRecebimentoController recebimentoController;
    private LookupController caixaController = new LookupController();
    
    public WmsArmazenamentoDetalhe(WmsArmazenamentoDetalheController controller) {
        initComponents();

        this.controller = controller;
        
        genericButton1.setToolTipText("Armazenar");
        
        form1.setFormController(controller);
        
        recebimentoController = new WmsArmazenamentoRecebimentoController();
        formRecebimento.setFormController(recebimentoController);
        
        itensController = new WmsArmazenamentoItensController();
        gridControl1.setController(itensController);
        gridControl1.setGridDataLocator(itensController);
        gridControl1.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        
        /*
         * Configurações do lookup da caixa
         */
        caixaController.setLookupValueObjectClassName("com.t2tierp.wms.java.WmsCaixaVO");
        caixaController.addLookup2ParentLink("id", "wmsCaixa.id");
        caixaController.addLookup2ParentLink("codigo", "wmsCaixa.codigo");
        caixaController.addLookup2ParentLink("altura", "wmsCaixa.horaOperacao");
        caixaController.addLookup2ParentLink("largura", "wmsCaixa.localOperacao");
        caixaController.addLookup2ParentLink("profundidade", "wmsCaixa.localOperacao");
        caixaController.addLookup2ParentLink("wmsEstante.codigo", "wmsCaixa.wmsEstante.codigo");
        
        caixaController.setHeaderColumnName("id", "ID");
        caixaController.setHeaderColumnName("codigo", "Código");
        caixaController.setHeaderColumnName("altura", "Altura");
        caixaController.setHeaderColumnName("largura", "Largura");
        caixaController.setHeaderColumnName("profundidade", "Profundidade");
        caixaController.setHeaderColumnName("wmsEstante.codigo", "Estante");
        caixaController.setFrameTitle("Importa Caixa");

        caixaController.setVisibleStatusPanel(true);
        caixaController.setVisibleColumn("id", true);
        caixaController.setVisibleColumn("codigo", true);
        caixaController.setVisibleColumn("altura", true);
        caixaController.setVisibleColumn("largura", true);
        caixaController.setVisibleColumn("profundidade", true);
        caixaController.setVisibleColumn("wmsEstante.codigo", true);
        caixaController.setFramePreferedSize(new Dimension(600, 500));

        caixaController.setLookupDataLocator(new LookupDataLocatorGenerico(caixaController.getLookupValueObjectClassName()));
        codLookupControl2.setLookupController(caixaController);
        
    }

    /**
     * @return the form1
     */
    public org.openswing.swing.form.client.Form getForm1() {
        return form1;
    }

    public WmsArmazenamentoRecebimentoController getRecebimentoController() {
        return recebimentoController;
    }

    public org.openswing.swing.form.client.Form getFormRecebimento() {
        return formRecebimento;
    }
    
    public WmsArmazenamentoItensController getItensController() {
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
        genericButton1 = new org.openswing.swing.client.GenericButton(new ImageIcon(ClientUtils.getImage("ok.gif")));
        formRecebimento = new org.openswing.swing.form.client.Form();
        labelControl2 = new org.openswing.swing.client.LabelControl();
        labelControl3 = new org.openswing.swing.client.LabelControl();
        textControl2 = new org.openswing.swing.client.TextControl();
        labelControl4 = new org.openswing.swing.client.LabelControl();
        dateControl3 = new org.openswing.swing.client.DateControl();
        labelControl6 = new org.openswing.swing.client.LabelControl();
        textControl4 = new org.openswing.swing.client.TextControl();
        labelControl7 = new org.openswing.swing.client.LabelControl();
        labelControl8 = new org.openswing.swing.client.LabelControl();
        numericControl6 = new org.openswing.swing.client.NumericControl();
        labelControl9 = new org.openswing.swing.client.LabelControl();
        numericControl7 = new org.openswing.swing.client.NumericControl();
        labelControl10 = new org.openswing.swing.client.LabelControl();
        comboBoxControl8 = new org.openswing.swing.client.ComboBoxControl();
        labelControl12 = new org.openswing.swing.client.LabelControl();
        dateControl1 = new org.openswing.swing.client.DateControl();
        textControl3 = new org.openswing.swing.client.TextControl();
        textControl5 = new org.openswing.swing.client.TextControl();
        form1 = new org.openswing.swing.form.client.Form();
        labelControl1 = new org.openswing.swing.client.LabelControl();
        codLookupControl2 = new org.openswing.swing.client.CodLookupControl();
        labelControl5 = new org.openswing.swing.client.LabelControl();
        numericControl4 = new org.openswing.swing.client.NumericControl();
        jPanel2 = new javax.swing.JPanel();
        gridControl1 = new org.openswing.swing.client.GridControl();
        textColumn1 = new org.openswing.swing.table.columns.client.TextColumn();
        integerColumn4 = new org.openswing.swing.table.columns.client.IntegerColumn();
        integerColumn5 = new org.openswing.swing.table.columns.client.IntegerColumn();
        integerColumn6 = new org.openswing.swing.table.columns.client.IntegerColumn();
        comboColumn7 = new org.openswing.swing.table.columns.client.ComboColumn();

        setTitle("T2Ti ERP - WMS");
        setPreferredSize(new java.awt.Dimension(700, 400));
        getContentPane().setLayout(new java.awt.GridBagLayout());

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Wms Armazenamento"));
        jPanel1.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        genericButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                genericButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(genericButton1);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        getContentPane().add(jPanel1, gridBagConstraints);

        formRecebimento.setBorder(javax.swing.BorderFactory.createTitledBorder("Dados Recebimento"));
        formRecebimento.setVOClassName("com.t2tierp.wms.java.WmsRecebimentoCabecalhoVO");
        formRecebimento.setFunctionId("wmsRecebimentoCabecalho");
        formRecebimento.setLayout(new java.awt.GridBagLayout());

        labelControl2.setText("Data Agendamento:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        formRecebimento.add(labelControl2, gridBagConstraints);

        labelControl3.setText("Hora Agendamento:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        formRecebimento.add(labelControl3, gridBagConstraints);

        textControl2.setAttributeName("horaInicio");
        textControl2.setEnabled(false);
        textControl2.setEnabledOnEdit(false);
        textControl2.setEnabledOnInsert(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        formRecebimento.add(textControl2, gridBagConstraints);

        labelControl4.setLabel("Data Recebimento:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        formRecebimento.add(labelControl4, gridBagConstraints);

        dateControl3.setAttributeName("dataRecebimento");
        dateControl3.setEnabled(false);
        dateControl3.setEnabledOnEdit(false);
        dateControl3.setEnabledOnInsert(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        formRecebimento.add(dateControl3, gridBagConstraints);

        labelControl6.setText("Local:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        formRecebimento.add(labelControl6, gridBagConstraints);

        textControl4.setAttributeName("wmsAgendamento.localOperacao");
        textControl4.setEnabled(false);
        textControl4.setEnabledOnEdit(false);
        textControl4.setEnabledOnInsert(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        formRecebimento.add(textControl4, gridBagConstraints);

        labelControl7.setLabel("Hora Fim:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        formRecebimento.add(labelControl7, gridBagConstraints);

        labelControl8.setLabel("Volume Recebido:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        formRecebimento.add(labelControl8, gridBagConstraints);

        numericControl6.setAttributeName("volumeRecebido");
        numericControl6.setEnabled(false);
        numericControl6.setEnabledOnEdit(false);
        numericControl6.setEnabledOnInsert(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        formRecebimento.add(numericControl6, gridBagConstraints);

        labelControl9.setLabel("Peso Recebido:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        formRecebimento.add(labelControl9, gridBagConstraints);

        numericControl7.setAttributeName("pesoRecebido");
        numericControl7.setDecimals(2);
        numericControl7.setEnabled(false);
        numericControl7.setEnabledOnEdit(false);
        numericControl7.setEnabledOnInsert(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        formRecebimento.add(numericControl7, gridBagConstraints);

        labelControl10.setLabel("Inconsistencia:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        formRecebimento.add(labelControl10, gridBagConstraints);

        comboBoxControl8.setAttributeName("inconsistencia");
        comboBoxControl8.setDomainId("simnao");
        comboBoxControl8.setEnabled(false);
        comboBoxControl8.setEnabledOnEdit(false);
        comboBoxControl8.setEnabledOnInsert(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        formRecebimento.add(comboBoxControl8, gridBagConstraints);

        labelControl12.setLabel("Hora Inicio:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        formRecebimento.add(labelControl12, gridBagConstraints);

        dateControl1.setAttributeName("wmsAgendamento.dataOperacao");
        dateControl1.setEnabled(false);
        dateControl1.setEnabledOnEdit(false);
        dateControl1.setEnabledOnInsert(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        formRecebimento.add(dateControl1, gridBagConstraints);

        textControl3.setAttributeName("wmsAgendamento.horaOperacao");
        textControl3.setEnabled(false);
        textControl3.setEnabledOnEdit(false);
        textControl3.setEnabledOnInsert(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        formRecebimento.add(textControl3, gridBagConstraints);

        textControl5.setAttributeName("horaFim");
        textControl5.setEnabled(false);
        textControl5.setEnabledOnEdit(false);
        textControl5.setEnabledOnInsert(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        formRecebimento.add(textControl5, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        getContentPane().add(formRecebimento, gridBagConstraints);

        form1.setBorder(javax.swing.BorderFactory.createTitledBorder("Caixa"));
        form1.setVOClassName("com.t2tierp.wms.java.WmsArmazenamentoVO");
        form1.setFunctionId("wmsArmazenamento");
        form1.setLayout(new java.awt.GridBagLayout());

        labelControl1.setText("Caixa:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        form1.add(labelControl1, gridBagConstraints);

        codLookupControl2.setAttributeName("wmsCaixa.codigo");
        codLookupControl2.setEnabled(false);
        codLookupControl2.setRequired(true);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        form1.add(codLookupControl2, gridBagConstraints);

        labelControl5.setLabel("Quantidade:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        form1.add(labelControl5, gridBagConstraints);

        numericControl4.setAttributeName("quantidade");
        numericControl4.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        form1.add(numericControl4, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        getContentPane().add(form1, gridBagConstraints);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Itens a Armazenar"));
        jPanel2.setLayout(new java.awt.GridBagLayout());

        gridControl1.setAutoLoadData(false);
        gridControl1.setFunctionId("wmsRecebimentoDetalhe");
        gridControl1.setValueObjectClassName("com.t2tierp.wms.java.WmsRecebimentoDetalheVO");

        textColumn1.setColumnName("produto.nome");
        textColumn1.setHeaderColumnName("Produto");
        textColumn1.setHeaderFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        textColumn1.setPreferredWidth(250);
        gridControl1.getColumnContainer().add(textColumn1);

        integerColumn4.setColumnName("quantidadeVolume");
        integerColumn4.setEditableOnEdit(true);
        integerColumn4.setEditableOnInsert(true);
        integerColumn4.setHeaderColumnName("Quantidade Volume");
        integerColumn4.setHeaderFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        integerColumn4.setPreferredWidth(120);
        gridControl1.getColumnContainer().add(integerColumn4);

        integerColumn5.setColumnName("quantidadeItemPorVolume");
        integerColumn5.setEditableOnEdit(true);
        integerColumn5.setEditableOnInsert(true);
        integerColumn5.setHeaderColumnName("Quantidade Item Por Volume");
        integerColumn5.setHeaderFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        integerColumn5.setPreferredWidth(180);
        gridControl1.getColumnContainer().add(integerColumn5);

        integerColumn6.setColumnName("quantidadeRecebida");
        integerColumn6.setEditableOnEdit(true);
        integerColumn6.setEditableOnInsert(true);
        integerColumn6.setHeaderColumnName("Quantidade Recebida");
        integerColumn6.setHeaderFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        integerColumn6.setPreferredWidth(130);
        gridControl1.getColumnContainer().add(integerColumn6);

        comboColumn7.setColumnName("destino");
        comboColumn7.setDomainId("wmsDestino");
        comboColumn7.setEditableOnEdit(true);
        comboColumn7.setEditableOnInsert(true);
        comboColumn7.setHeaderColumnName("Destino");
        comboColumn7.setHeaderFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        comboColumn7.setPreferredWidth(150);
        gridControl1.getColumnContainer().add(comboColumn7);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel2.add(gridControl1, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        getContentPane().add(jPanel2, gridBagConstraints);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void genericButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_genericButton1ActionPerformed
        controller.armazenaItens();
    }//GEN-LAST:event_genericButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private org.openswing.swing.client.CodLookupControl codLookupControl2;
    private org.openswing.swing.client.ComboBoxControl comboBoxControl8;
    private org.openswing.swing.table.columns.client.ComboColumn comboColumn7;
    private org.openswing.swing.client.DateControl dateControl1;
    private org.openswing.swing.client.DateControl dateControl3;
    private org.openswing.swing.form.client.Form form1;
    private org.openswing.swing.form.client.Form formRecebimento;
    private org.openswing.swing.client.GenericButton genericButton1;
    private org.openswing.swing.client.GridControl gridControl1;
    private org.openswing.swing.table.columns.client.IntegerColumn integerColumn4;
    private org.openswing.swing.table.columns.client.IntegerColumn integerColumn5;
    private org.openswing.swing.table.columns.client.IntegerColumn integerColumn6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private org.openswing.swing.client.LabelControl labelControl1;
    private org.openswing.swing.client.LabelControl labelControl10;
    private org.openswing.swing.client.LabelControl labelControl12;
    private org.openswing.swing.client.LabelControl labelControl2;
    private org.openswing.swing.client.LabelControl labelControl3;
    private org.openswing.swing.client.LabelControl labelControl4;
    private org.openswing.swing.client.LabelControl labelControl5;
    private org.openswing.swing.client.LabelControl labelControl6;
    private org.openswing.swing.client.LabelControl labelControl7;
    private org.openswing.swing.client.LabelControl labelControl8;
    private org.openswing.swing.client.LabelControl labelControl9;
    private org.openswing.swing.client.NumericControl numericControl4;
    private org.openswing.swing.client.NumericControl numericControl6;
    private org.openswing.swing.client.NumericControl numericControl7;
    private org.openswing.swing.table.columns.client.TextColumn textColumn1;
    private org.openswing.swing.client.TextControl textControl2;
    private org.openswing.swing.client.TextControl textControl3;
    private org.openswing.swing.client.TextControl textControl4;
    private org.openswing.swing.client.TextControl textControl5;
    // End of variables declaration//GEN-END:variables


}
