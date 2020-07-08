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
package com.t2tierp.orcamento.cliente;

import com.t2tierp.padrao.cliente.LookupDataLocatorGenerico;
import java.awt.Dimension;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import org.openswing.swing.client.GenericButton;
import org.openswing.swing.lookup.client.LookupController;
import org.openswing.swing.mdi.client.InternalFrame;
import org.openswing.swing.util.client.ClientUtils;

public class OrcamentoFluxoCaixaDetalhe extends InternalFrame {

    private OrcamentoFluxoCaixaDetalheGridController gridController;
    private LookupController periodoController = new LookupController();
    private LookupController planoNaturezaFinanceiraController = new LookupController();
    private OrcamentoFluxoCaixaDetalheController controller;

    public OrcamentoFluxoCaixaDetalhe(OrcamentoFluxoCaixaDetalheController controller) {
        initComponents();

        this.controller = controller;

        genericButtonPegaRealizado.setToolTipText("Buscar Realizado");
        genericButtonCalculaVariacao.setToolTipText("Calcular Variação");
       
        form1.setFormController(controller);

        gridController = new OrcamentoFluxoCaixaDetalheGridController();
        gridControl1.setController(gridController);
        gridControl1.setGridDataLocator(gridController);

        /*
         * Configurações do lookup do periodo
         */
        periodoController.setLookupValueObjectClassName("com.t2tierp.orcamento.java.OrcamentoFluxoCaixaPeriodoVO");
        periodoController.addLookup2ParentLink("id", "orcamentoFluxoCaixaPeriodo.id");
        periodoController.addLookup2ParentLink("nome", "orcamentoFluxoCaixaPeriodo.nome");
        periodoController.addLookup2ParentLink("periodo", "orcamentoFluxoCaixaPeriodo.periodo");
        periodoController.addLookup2ParentLink("contaCaixa.nome", "orcamentoFluxoCaixaPeriodo.contaCaixa.nome");
        periodoController.setHeaderColumnName("id", "ID");
        periodoController.setHeaderColumnName("nome", "Nome");
        periodoController.setHeaderColumnName("contaCaixa.nome", "Conta Caixa");
        periodoController.setFrameTitle("Importa Período");

        periodoController.setVisibleStatusPanel(true);
        periodoController.setVisibleColumn("id", true);
        periodoController.setVisibleColumn("nome", true);
        periodoController.setVisibleColumn("contaCaixa.nome", true);
        periodoController.setFramePreferedSize(new Dimension(600, 500));

        periodoController.setLookupDataLocator(new LookupDataLocatorGenerico(periodoController.getLookupValueObjectClassName()));
        codLookupControl2.setLookupController(periodoController);

        /*
         * Configurações do lookup do plano natureza financeira
         */
        planoNaturezaFinanceiraController.setLookupValueObjectClassName("com.t2tierp.financeiro.java.PlanoNaturezaFinanceiraVO");
        planoNaturezaFinanceiraController.addLookup2ParentLink("id", "planoNaturezaFinanceira.id");
        planoNaturezaFinanceiraController.addLookup2ParentLink("nome", "planoNaturezaFinanceira.nome");
        planoNaturezaFinanceiraController.setHeaderColumnName("id", "ID");
        planoNaturezaFinanceiraController.setHeaderColumnName("nome", "Nome");
        planoNaturezaFinanceiraController.setFrameTitle("Importa Plano Natureza Financeira");

        planoNaturezaFinanceiraController.setVisibleStatusPanel(true);
        planoNaturezaFinanceiraController.setVisibleColumn("id", true);
        planoNaturezaFinanceiraController.setVisibleColumn("nome", true);
        planoNaturezaFinanceiraController.setFramePreferedSize(new Dimension(600, 500));

        planoNaturezaFinanceiraController.setLookupDataLocator(new LookupDataLocatorGenerico(planoNaturezaFinanceiraController.getLookupValueObjectClassName()));
        codLookupControl3.setLookupController(planoNaturezaFinanceiraController);

    }

    /**
     * @return the form1
     */
    public org.openswing.swing.form.client.Form getForm1() {
        return form1;
    }

    public OrcamentoFluxoCaixaDetalheGridController getGridController() {
        return gridController;
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
        genericButtonPegaRealizado = new GenericButton(new ImageIcon(ClientUtils.getImage("currencies_16_h.png")));
        genericButtonCalculaVariacao = new GenericButton(new ImageIcon(ClientUtils.getImage("calculadora.png")));
        form1 = new org.openswing.swing.form.client.Form();
        labelControl1 = new org.openswing.swing.client.LabelControl();
        codLookupControl2 = new org.openswing.swing.client.CodLookupControl();
        labelControl2 = new org.openswing.swing.client.LabelControl();
        textControl2 = new org.openswing.swing.client.TextControl();
        labelControl3 = new org.openswing.swing.client.LabelControl();
        textControl3 = new org.openswing.swing.client.TextControl();
        labelControl4 = new org.openswing.swing.client.LabelControl();
        labelControl5 = new org.openswing.swing.client.LabelControl();
        dateControl5 = new org.openswing.swing.client.DateControl();
        labelControl6 = new org.openswing.swing.client.LabelControl();
        numericControl6 = new org.openswing.swing.client.NumericControl();
        labelControl7 = new org.openswing.swing.client.LabelControl();
        dateControl7 = new org.openswing.swing.client.DateControl();
        textControl5 = new org.openswing.swing.client.TextControl();
        textAreaControl1 = new org.openswing.swing.client.TextAreaControl();
        labelControl8 = new org.openswing.swing.client.LabelControl();
        codLookupControl3 = new org.openswing.swing.client.CodLookupControl();
        textControl6 = new org.openswing.swing.client.TextControl();
        jPanel2 = new javax.swing.JPanel();
        gridControl1 = new org.openswing.swing.client.GridControl();
        textColumn2 = new org.openswing.swing.table.columns.client.TextColumn();
        textColumn3 = new org.openswing.swing.table.columns.client.TextColumn();
        textColumn4 = new org.openswing.swing.table.columns.client.TextColumn();
        decimalColumn5 = new org.openswing.swing.table.columns.client.DecimalColumn();
        decimalColumn6 = new org.openswing.swing.table.columns.client.DecimalColumn();
        decimalColumn7 = new org.openswing.swing.table.columns.client.DecimalColumn();
        decimalColumn8 = new org.openswing.swing.table.columns.client.DecimalColumn();
        jPanel3 = new javax.swing.JPanel();
        editButton2 = new org.openswing.swing.client.EditButton();
        saveButton2 = new org.openswing.swing.client.SaveButton();
        reloadButton2 = new org.openswing.swing.client.ReloadButton();
        navigatorBar1 = new org.openswing.swing.client.NavigatorBar();

        setTitle("T2Ti ERP - Controle de Orçamento");
        setPreferredSize(new java.awt.Dimension(700, 400));
        getContentPane().setLayout(new java.awt.GridBagLayout());

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Orcamento Fluxo Caixa"));
        jPanel1.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));
        jPanel1.add(editButton1);
        jPanel1.add(reloadButton1);
        jPanel1.add(saveButton1);

        genericButtonPegaRealizado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                genericButtonPegaRealizadoActionPerformed(evt);
            }
        });
        jPanel1.add(genericButtonPegaRealizado);

        genericButtonCalculaVariacao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                genericButtonCalculaVariacaoActionPerformed(evt);
            }
        });
        jPanel1.add(genericButtonCalculaVariacao);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        getContentPane().add(jPanel1, gridBagConstraints);

        form1.setVOClassName("com.t2tierp.orcamento.java.OrcamentoFluxoCaixaVO");
        form1.setEditButton(editButton1);
        form1.setFunctionId("orcamentoFluxoCaixa");
        form1.setReloadButton(reloadButton1);
        form1.setSaveButton(saveButton1);
        form1.setLayout(new java.awt.GridBagLayout());

        labelControl1.setLabel("Periodo:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        form1.add(labelControl1, gridBagConstraints);

        codLookupControl2.setAllowOnlyNumbers(true);
        codLookupControl2.setAttributeName("orcamentoFluxoCaixaPeriodo.id");
        codLookupControl2.setEnabled(false);
        codLookupControl2.setLinkLabel(labelControl1);
        codLookupControl2.setRequired(true);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = -70;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        form1.add(codLookupControl2, gridBagConstraints);

        labelControl2.setLabel("Conta Caixa:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        form1.add(labelControl2, gridBagConstraints);

        textControl2.setAttributeName("orcamentoFluxoCaixaPeriodo.contaCaixa.nome");
        textControl2.setEnabled(false);
        textControl2.setEnabledOnEdit(false);
        textControl2.setEnabledOnInsert(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        form1.add(textControl2, gridBagConstraints);

        labelControl3.setLabel("Nome:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        form1.add(labelControl3, gridBagConstraints);

        textControl3.setAttributeName("nome");
        textControl3.setEnabled(false);
        textControl3.setLinkLabel(labelControl3);
        textControl3.setMaxCharacters(30);
        textControl3.setRequired(true);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        form1.add(textControl3, gridBagConstraints);

        labelControl4.setLabel("Descricao:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        form1.add(labelControl4, gridBagConstraints);

        labelControl5.setLabel("Data Inicial:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        form1.add(labelControl5, gridBagConstraints);

        dateControl5.setAttributeName("dataInicial");
        dateControl5.setEnabled(false);
        dateControl5.setLinkLabel(labelControl5);
        dateControl5.setRequired(true);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        form1.add(dateControl5, gridBagConstraints);

        labelControl6.setLabel("Numero Periodos:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        form1.add(labelControl6, gridBagConstraints);

        numericControl6.setAttributeName("numeroPeriodos");
        numericControl6.setEnabled(false);
        numericControl6.setLinkLabel(labelControl6);
        numericControl6.setRequired(true);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        form1.add(numericControl6, gridBagConstraints);

        labelControl7.setLabel("Data Base:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        form1.add(labelControl7, gridBagConstraints);

        dateControl7.setAttributeName("dataBase");
        dateControl7.setEnabled(false);
        dateControl7.setLinkLabel(labelControl7);
        dateControl7.setRequired(true);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        form1.add(dateControl7, gridBagConstraints);

        textControl5.setAttributeName("orcamentoFluxoCaixaPeriodo.nome");
        textControl5.setEnabled(false);
        textControl5.setEnabledOnEdit(false);
        textControl5.setEnabledOnInsert(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        form1.add(textControl5, gridBagConstraints);

        textAreaControl1.setAttributeName("descricao");
        textAreaControl1.setEnabled(false);
        textAreaControl1.setMaxCharacters(1000);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipady = 30;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        form1.add(textAreaControl1, gridBagConstraints);

        labelControl8.setLabel("Plano Natureza Financeira:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        form1.add(labelControl8, gridBagConstraints);

        codLookupControl3.setAllowOnlyNumbers(true);
        codLookupControl3.setAttributeName("planoNaturezaFinanceira.id");
        codLookupControl3.setEnabled(false);
        codLookupControl3.setLinkLabel(labelControl8);
        codLookupControl3.setRequired(true);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = -70;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        form1.add(codLookupControl3, gridBagConstraints);

        textControl6.setAttributeName("planoNaturezaFinanceira.nome");
        textControl6.setEnabled(false);
        textControl6.setEnabledOnEdit(false);
        textControl6.setEnabledOnInsert(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        form1.add(textControl6, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        getContentPane().add(form1, gridBagConstraints);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Itens do Orcamento"));
        jPanel2.setLayout(new java.awt.GridBagLayout());

        gridControl1.setAutoLoadData(false);
        gridControl1.setEditButton(editButton2);
        gridControl1.setFunctionId("orcamentoFluxoCaixaDetalhe");
        gridControl1.setNavBar(navigatorBar1);
        gridControl1.setReloadButton(reloadButton2);
        gridControl1.setSaveButton(saveButton2);
        gridControl1.setValueObjectClassName("com.t2tierp.orcamento.java.OrcamentoFluxoCaixaDetalheVO");
        gridControl1.getColumnContainer().setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        textColumn2.setColumnName("naturezaFinanceira.classificacao");
        textColumn2.setColumnRequired(false);
        textColumn2.setHeaderColumnName("Classificacao Natureza Financeira");
        textColumn2.setHeaderFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        textColumn2.setPreferredWidth(200);
        gridControl1.getColumnContainer().add(textColumn2);

        textColumn3.setColumnName("naturezaFinanceira.descricao");
        textColumn3.setColumnRequired(false);
        textColumn3.setHeaderColumnName("Decricao Natureza Financeira");
        textColumn3.setHeaderFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        textColumn3.setPreferredWidth(200);
        gridControl1.getColumnContainer().add(textColumn3);

        textColumn4.setColumnName("periodo");
        textColumn4.setColumnRequired(false);
        textColumn4.setHeaderColumnName("Periodo");
        textColumn4.setHeaderFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        gridControl1.getColumnContainer().add(textColumn4);

        decimalColumn5.setColumnName("valorOrcado");
        decimalColumn5.setColumnRequired(false);
        decimalColumn5.setDecimals(2);
        decimalColumn5.setEditableOnEdit(true);
        decimalColumn5.setEditableOnInsert(true);
        decimalColumn5.setHeaderColumnName("Valor Orcado");
        decimalColumn5.setHeaderFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        gridControl1.getColumnContainer().add(decimalColumn5);

        decimalColumn6.setColumnName("valorRealizado");
        decimalColumn6.setColumnRequired(false);
        decimalColumn6.setDecimals(2);
        decimalColumn6.setHeaderColumnName("Valor Realizado");
        decimalColumn6.setHeaderFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        gridControl1.getColumnContainer().add(decimalColumn6);

        decimalColumn7.setColumnName("taxaVariacao");
        decimalColumn7.setColumnRequired(false);
        decimalColumn7.setDecimals(2);
        decimalColumn7.setHeaderColumnName("Taxa Variacao");
        decimalColumn7.setHeaderFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        gridControl1.getColumnContainer().add(decimalColumn7);

        decimalColumn8.setColumnName("valorVariacao");
        decimalColumn8.setColumnRequired(false);
        decimalColumn8.setDecimals(2);
        decimalColumn8.setHeaderColumnName("Valor Variacao");
        decimalColumn8.setHeaderFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        gridControl1.getColumnContainer().add(decimalColumn8);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel2.add(gridControl1, gridBagConstraints);

        jPanel3.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));
        jPanel3.add(editButton2);
        jPanel3.add(saveButton2);
        jPanel3.add(reloadButton2);
        jPanel3.add(navigatorBar1);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        jPanel2.add(jPanel3, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        getContentPane().add(jPanel2, gridBagConstraints);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void genericButtonPegaRealizadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_genericButtonPegaRealizadoActionPerformed
        try {
            controller.buscaValorRealizado();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Ocorreu um erro ao buscar os valores realizados.\n" + ex.getMessage(), "Erro do Sistema", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_genericButtonPegaRealizadoActionPerformed

    private void genericButtonCalculaVariacaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_genericButtonCalculaVariacaoActionPerformed
        try {
            controller.calcularVariacao();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Ocorreu um erro ao calcular a variação.\n" + ex.getMessage(), "Erro do Sistema", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_genericButtonCalculaVariacaoActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private org.openswing.swing.client.CodLookupControl codLookupControl2;
    private org.openswing.swing.client.CodLookupControl codLookupControl3;
    private org.openswing.swing.client.DateControl dateControl5;
    private org.openswing.swing.client.DateControl dateControl7;
    private org.openswing.swing.table.columns.client.DecimalColumn decimalColumn5;
    private org.openswing.swing.table.columns.client.DecimalColumn decimalColumn6;
    private org.openswing.swing.table.columns.client.DecimalColumn decimalColumn7;
    private org.openswing.swing.table.columns.client.DecimalColumn decimalColumn8;
    private org.openswing.swing.client.EditButton editButton1;
    private org.openswing.swing.client.EditButton editButton2;
    private org.openswing.swing.form.client.Form form1;
    private org.openswing.swing.client.GenericButton genericButtonCalculaVariacao;
    private org.openswing.swing.client.GenericButton genericButtonPegaRealizado;
    private org.openswing.swing.client.GridControl gridControl1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private org.openswing.swing.client.LabelControl labelControl1;
    private org.openswing.swing.client.LabelControl labelControl2;
    private org.openswing.swing.client.LabelControl labelControl3;
    private org.openswing.swing.client.LabelControl labelControl4;
    private org.openswing.swing.client.LabelControl labelControl5;
    private org.openswing.swing.client.LabelControl labelControl6;
    private org.openswing.swing.client.LabelControl labelControl7;
    private org.openswing.swing.client.LabelControl labelControl8;
    private org.openswing.swing.client.NavigatorBar navigatorBar1;
    private org.openswing.swing.client.NumericControl numericControl6;
    private org.openswing.swing.client.ReloadButton reloadButton1;
    private org.openswing.swing.client.ReloadButton reloadButton2;
    private org.openswing.swing.client.SaveButton saveButton1;
    private org.openswing.swing.client.SaveButton saveButton2;
    private org.openswing.swing.client.TextAreaControl textAreaControl1;
    private org.openswing.swing.table.columns.client.TextColumn textColumn2;
    private org.openswing.swing.table.columns.client.TextColumn textColumn3;
    private org.openswing.swing.table.columns.client.TextColumn textColumn4;
    private org.openswing.swing.client.TextControl textControl2;
    private org.openswing.swing.client.TextControl textControl3;
    private org.openswing.swing.client.TextControl textControl5;
    private org.openswing.swing.client.TextControl textControl6;
    // End of variables declaration//GEN-END:variables

}
