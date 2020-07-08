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
import javax.swing.text.MaskFormatter;
import org.openswing.swing.client.GenericButton;
import org.openswing.swing.lookup.client.LookupController;
import org.openswing.swing.mdi.client.InternalFrame;
import org.openswing.swing.util.client.ClientUtils;

public class FinCobrancaDetalhe extends InternalFrame {

    private FinCobrancaParcelaReceberGridController parcelaCobrancaController;
    private FinParcelaReceberVencidaGridController parcelaVencidaController;
    private FinCobrancaDetalheController controller;
    private LookupController clienteController = new LookupController();
    
    public FinCobrancaDetalhe(FinCobrancaDetalheController controller) {
        initComponents();

        formattedTextControl1.setEnabled(false);
        try {
            MaskFormatter mask = new MaskFormatter("##:##:##");
            mask.setValidCharacters("0123456789");
            formattedTextControl1.setFormatter(mask);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
        genericButton1.setToolTipText("Buscar Parcelas Vencidas");
        genericButton2.setToolTipText("Calcular Juros/Multa");
        genericButton3.setToolTipText("Simular Acordo");
        
        this.controller = controller;
        
        form1.setFormController(controller);

        parcelaCobrancaController = new FinCobrancaParcelaReceberGridController();
        gridControlParcelaCobranca.setController(parcelaCobrancaController);
        gridControlParcelaCobranca.setGridDataLocator(parcelaCobrancaController);

        parcelaVencidaController = new FinParcelaReceberVencidaGridController();
        gridControlParcelaReceber.setController(parcelaVencidaController);
        gridControlParcelaReceber.setGridDataLocator(parcelaVencidaController);
        
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
        codLookupControl2.setLookupController(clienteController);        
    }

    /**
     * @return the form1
     */
    public org.openswing.swing.form.client.Form getForm1() {
        return form1;
    }

    public org.openswing.swing.client.GridControl getGridControlParcelaCobranca() {
        return gridControlParcelaCobranca;
    }

    public org.openswing.swing.client.GridControl getGridControlParcelaReceber() {
        return gridControlParcelaReceber;
    }

    public FinCobrancaParcelaReceberGridController getParcelaCobrancaController() {
        return parcelaCobrancaController;
    }

    public FinParcelaReceberVencidaGridController getParcelaVencidaController() {
        return parcelaVencidaController;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
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
        genericButton2 = new GenericButton(new ImageIcon(ClientUtils.getImage("calculadora.png")));
        genericButton3 = new GenericButton(new ImageIcon(ClientUtils.getImage("ptrp16.png")));
        form1 = new org.openswing.swing.form.client.Form();
        labelControl1 = new org.openswing.swing.client.LabelControl();
        codLookupControl2 = new org.openswing.swing.client.CodLookupControl();
        textControl2 = new org.openswing.swing.client.TextControl();
        labelControl3 = new org.openswing.swing.client.LabelControl();
        dateControl3 = new org.openswing.swing.client.DateControl();
        labelControl4 = new org.openswing.swing.client.LabelControl();
        labelControl5 = new org.openswing.swing.client.LabelControl();
        textControl5 = new org.openswing.swing.client.TextControl();
        labelControl6 = new org.openswing.swing.client.LabelControl();
        dateControl6 = new org.openswing.swing.client.DateControl();
        labelControl7 = new org.openswing.swing.client.LabelControl();
        numericControl7 = new org.openswing.swing.client.NumericControl();
        formattedTextControl1 = new org.openswing.swing.client.FormattedTextControl();
        numericControl8 = new org.openswing.swing.client.NumericControl();
        labelControl8 = new org.openswing.swing.client.LabelControl();
        numericControl9 = new org.openswing.swing.client.NumericControl();
        labelControl9 = new org.openswing.swing.client.LabelControl();
        numericControl10 = new org.openswing.swing.client.NumericControl();
        labelControl10 = new org.openswing.swing.client.LabelControl();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel4 = new javax.swing.JPanel();
        gridControlParcelaReceber = new org.openswing.swing.client.GridControl();
        integerColumn5 = new org.openswing.swing.table.columns.client.IntegerColumn();
        dateColumn6 = new org.openswing.swing.table.columns.client.DateColumn();
        dateColumn7 = new org.openswing.swing.table.columns.client.DateColumn();
        decimalColumn13 = new org.openswing.swing.table.columns.client.DecimalColumn();
        decimalColumn14 = new org.openswing.swing.table.columns.client.DecimalColumn();
        decimalColumn15 = new org.openswing.swing.table.columns.client.DecimalColumn();
        decimalColumn16 = new org.openswing.swing.table.columns.client.DecimalColumn();
        decimalColumn17 = new org.openswing.swing.table.columns.client.DecimalColumn();
        textColumn18 = new org.openswing.swing.table.columns.client.TextColumn();
        textColumn2 = new org.openswing.swing.table.columns.client.TextColumn();
        jPanel5 = new javax.swing.JPanel();
        editButton3 = new org.openswing.swing.client.EditButton();
        saveButton3 = new org.openswing.swing.client.SaveButton();
        reloadButton3 = new org.openswing.swing.client.ReloadButton();
        navigatorBar2 = new org.openswing.swing.client.NavigatorBar();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        editButton2 = new org.openswing.swing.client.EditButton();
        saveButton2 = new org.openswing.swing.client.SaveButton();
        reloadButton2 = new org.openswing.swing.client.ReloadButton();
        navigatorBar1 = new org.openswing.swing.client.NavigatorBar();
        gridControlParcelaCobranca = new org.openswing.swing.client.GridControl();
        integerColumn4 = new org.openswing.swing.table.columns.client.IntegerColumn();
        dateColumn5 = new org.openswing.swing.table.columns.client.DateColumn();
        decimalColumn6 = new org.openswing.swing.table.columns.client.DecimalColumn();
        decimalColumn7 = new org.openswing.swing.table.columns.client.DecimalColumn();
        decimalColumn10 = new org.openswing.swing.table.columns.client.DecimalColumn();
        decimalColumn8 = new org.openswing.swing.table.columns.client.DecimalColumn();
        decimalColumn11 = new org.openswing.swing.table.columns.client.DecimalColumn();
        decimalColumn9 = new org.openswing.swing.table.columns.client.DecimalColumn();
        decimalColumn12 = new org.openswing.swing.table.columns.client.DecimalColumn();

        setTitle("T2Ti ERP - Financeiro");
        setPreferredSize(new java.awt.Dimension(700, 400));
        getContentPane().setLayout(new java.awt.GridBagLayout());

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Fin Cobranca"));
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

        form1.setVOClassName("com.t2tierp.financeiro.java.FinCobrancaVO");
        form1.setEditButton(editButton1);
        form1.setFunctionId("finCobranca");
        form1.setReloadButton(reloadButton1);
        form1.setSaveButton(saveButton1);
        form1.setLayout(new java.awt.GridBagLayout());

        labelControl1.setLabel("Cliente:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        form1.add(labelControl1, gridBagConstraints);

        codLookupControl2.setAllowOnlyNumbers(true);
        codLookupControl2.setAttributeName("cliente.id");
        codLookupControl2.setEnabled(false);
        codLookupControl2.setMaxCharacters(3);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = -100;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        form1.add(codLookupControl2, gridBagConstraints);

        textControl2.setAttributeName("cliente.pessoa.nome");
        textControl2.setEnabled(false);
        textControl2.setEnabledOnEdit(false);
        textControl2.setEnabledOnInsert(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        form1.add(textControl2, gridBagConstraints);

        labelControl3.setLabel("Data Contato:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        form1.add(labelControl3, gridBagConstraints);

        dateControl3.setAttributeName("dataContato");
        dateControl3.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        form1.add(dateControl3, gridBagConstraints);

        labelControl4.setLabel("Hora Contato:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        form1.add(labelControl4, gridBagConstraints);

        labelControl5.setLabel("Telefone Contato:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        form1.add(labelControl5, gridBagConstraints);

        textControl5.setAttributeName("telefoneContato");
        textControl5.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        form1.add(textControl5, gridBagConstraints);

        labelControl6.setLabel("Data Acerto Pagamento:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        form1.add(labelControl6, gridBagConstraints);

        dateControl6.setAttributeName("dataAcertoPagamento");
        dateControl6.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        form1.add(dateControl6, gridBagConstraints);

        labelControl7.setText("Valor Total Atraso:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        form1.add(labelControl7, gridBagConstraints);

        numericControl7.setAttributeName("totalMulta");
        numericControl7.setDecimals(2);
        numericControl7.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 100;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        form1.add(numericControl7, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        form1.add(formattedTextControl1, gridBagConstraints);

        numericControl8.setAttributeName("totalJuros");
        numericControl8.setDecimals(2);
        numericControl8.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 100;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        form1.add(numericControl8, gridBagConstraints);

        labelControl8.setText("Valor Total Juros:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        form1.add(labelControl8, gridBagConstraints);

        numericControl9.setAttributeName("totalAtrasado");
        numericControl9.setDecimals(2);
        numericControl9.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 100;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        form1.add(numericControl9, gridBagConstraints);

        labelControl9.setText("Valor Total Multa:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        form1.add(labelControl9, gridBagConstraints);

        numericControl10.setAttributeName("totalReceberNaData");
        numericControl10.setDecimals(2);
        numericControl10.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 100;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        form1.add(numericControl10, gridBagConstraints);

        labelControl10.setText("Valor Total Receber:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        form1.add(labelControl10, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        getContentPane().add(form1, gridBagConstraints);

        jPanel4.setLayout(new java.awt.GridBagLayout());

        gridControlParcelaReceber.setAutoLoadData(false);
        gridControlParcelaReceber.setEditButton(editButton3);
        gridControlParcelaReceber.setFunctionId("finParcelaReceber");
        gridControlParcelaReceber.setNavBar(navigatorBar2);
        gridControlParcelaReceber.setReloadButton(reloadButton3);
        gridControlParcelaReceber.setSaveButton(saveButton3);
        gridControlParcelaReceber.setValueObjectClassName("com.t2tierp.financeiro.java.FinParcelaReceberVO");

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
        dateColumn7.setHeaderColumnName("Data Vencimento");
        dateColumn7.setHeaderFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        gridControlParcelaReceber.getColumnContainer().add(dateColumn7);

        decimalColumn13.setColumnName("valor");
        decimalColumn13.setDecimals(2);
        decimalColumn13.setHeaderColumnName("Valor");
        decimalColumn13.setHeaderFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        gridControlParcelaReceber.getColumnContainer().add(decimalColumn13);

        decimalColumn14.setColumnName("taxaJuro");
        decimalColumn14.setColumnRequired(false);
        decimalColumn14.setDecimals(2);
        decimalColumn14.setEditableOnEdit(true);
        decimalColumn14.setEditableOnInsert(true);
        decimalColumn14.setHeaderColumnName("Taxa Juro");
        decimalColumn14.setHeaderFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        gridControlParcelaReceber.getColumnContainer().add(decimalColumn14);

        decimalColumn15.setColumnName("valorJuro");
        decimalColumn15.setColumnRequired(false);
        decimalColumn15.setDecimals(2);
        decimalColumn15.setHeaderColumnName("Valor Juro");
        decimalColumn15.setHeaderFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        gridControlParcelaReceber.getColumnContainer().add(decimalColumn15);

        decimalColumn16.setColumnName("taxaMulta");
        decimalColumn16.setColumnRequired(false);
        decimalColumn16.setDecimals(2);
        decimalColumn16.setEditableOnEdit(true);
        decimalColumn16.setEditableOnInsert(true);
        decimalColumn16.setHeaderColumnName("Taxa Multa");
        decimalColumn16.setHeaderFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        gridControlParcelaReceber.getColumnContainer().add(decimalColumn16);

        decimalColumn17.setColumnName("valorMulta");
        decimalColumn17.setColumnRequired(false);
        decimalColumn17.setDecimals(2);
        decimalColumn17.setHeaderColumnName("Valor Multa");
        decimalColumn17.setHeaderFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        gridControlParcelaReceber.getColumnContainer().add(decimalColumn17);

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
        jPanel4.add(gridControlParcelaReceber, gridBagConstraints);

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder("Fin Parcela Receber"));
        jPanel5.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));
        jPanel5.add(editButton3);
        jPanel5.add(saveButton3);
        jPanel5.add(reloadButton3);
        jPanel5.add(navigatorBar2);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        jPanel4.add(jPanel5, gridBagConstraints);

        jTabbedPane1.addTab("Parcelas Vencidas", jPanel4);

        jPanel2.setLayout(new java.awt.GridBagLayout());

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Fin Cobranca Parcela Receber"));
        jPanel3.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));
        jPanel3.add(editButton2);
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

        gridControlParcelaCobranca.setEditButton(editButton2);
        gridControlParcelaCobranca.setFunctionId("finCobrancaParcelaReceber");
        gridControlParcelaCobranca.setNavBar(navigatorBar1);
        gridControlParcelaCobranca.setReloadButton(reloadButton2);
        gridControlParcelaCobranca.setSaveButton(saveButton2);
        gridControlParcelaCobranca.setValueObjectClassName("com.t2tierp.financeiro.java.FinCobrancaParcelaReceberVO");

        integerColumn4.setColumnName("idFinParcelaReceber");
        integerColumn4.setHeaderColumnName("Fin Parcela Receber");
        integerColumn4.setHeaderFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        integerColumn4.setPreferredWidth(150);
        gridControlParcelaCobranca.getColumnContainer().add(integerColumn4);

        dateColumn5.setColumnName("dataVencimento");
        dateColumn5.setHeaderColumnName("Data Vencimento");
        dateColumn5.setHeaderFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        gridControlParcelaCobranca.getColumnContainer().add(dateColumn5);

        decimalColumn6.setColumnName("valorParcela");
        decimalColumn6.setDecimals(2);
        decimalColumn6.setHeaderColumnName("Valor Parcela");
        decimalColumn6.setHeaderFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        decimalColumn6.setPreferredWidth(150);
        gridControlParcelaCobranca.getColumnContainer().add(decimalColumn6);

        decimalColumn7.setColumnName("valorJuroSimulado");
        decimalColumn7.setDecimals(2);
        decimalColumn7.setEditableOnEdit(true);
        decimalColumn7.setHeaderColumnName("Valor Juro Simulado");
        decimalColumn7.setHeaderFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        decimalColumn7.setPreferredWidth(150);
        gridControlParcelaCobranca.getColumnContainer().add(decimalColumn7);

        decimalColumn10.setColumnName("valorJuroAcordo");
        decimalColumn10.setDecimals(2);
        decimalColumn10.setHeaderColumnName("Valor Juro Acordo");
        decimalColumn10.setHeaderFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        decimalColumn10.setPreferredWidth(150);
        gridControlParcelaCobranca.getColumnContainer().add(decimalColumn10);

        decimalColumn8.setColumnName("valorMultaSimulado");
        decimalColumn8.setDecimals(2);
        decimalColumn8.setEditableOnEdit(true);
        decimalColumn8.setHeaderColumnName("Valor Multa Simulado");
        decimalColumn8.setHeaderFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        decimalColumn8.setPreferredWidth(150);
        gridControlParcelaCobranca.getColumnContainer().add(decimalColumn8);

        decimalColumn11.setColumnName("valorMultaAcordo");
        decimalColumn11.setDecimals(2);
        decimalColumn11.setHeaderColumnName("Valor Multa Acordo");
        decimalColumn11.setHeaderFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        decimalColumn11.setPreferredWidth(150);
        gridControlParcelaCobranca.getColumnContainer().add(decimalColumn11);

        decimalColumn9.setColumnName("valorReceberSimulado");
        decimalColumn9.setDecimals(2);
        decimalColumn9.setHeaderColumnName("Valor Receber Simulado");
        decimalColumn9.setHeaderFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        decimalColumn9.setPreferredWidth(150);
        gridControlParcelaCobranca.getColumnContainer().add(decimalColumn9);

        decimalColumn12.setColumnName("valorReceberAcordo");
        decimalColumn12.setDecimals(2);
        decimalColumn12.setHeaderColumnName("Valor Receber Acordo");
        decimalColumn12.setHeaderFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        decimalColumn12.setPreferredWidth(150);
        gridControlParcelaCobranca.getColumnContainer().add(decimalColumn12);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel2.add(gridControlParcelaCobranca, gridBagConstraints);

        jTabbedPane1.addTab("Parcelas Simulado / Acordo", jPanel2);

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
        controller.buscaParcelasVencidas();
    }//GEN-LAST:event_genericButton1ActionPerformed

    private void genericButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_genericButton2ActionPerformed
        controller.calcularJurosMulta();
    }//GEN-LAST:event_genericButton2ActionPerformed

    private void genericButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_genericButton3ActionPerformed
        controller.simulaValores();
    }//GEN-LAST:event_genericButton3ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private org.openswing.swing.client.CodLookupControl codLookupControl2;
    private org.openswing.swing.table.columns.client.DateColumn dateColumn5;
    private org.openswing.swing.table.columns.client.DateColumn dateColumn6;
    private org.openswing.swing.table.columns.client.DateColumn dateColumn7;
    private org.openswing.swing.client.DateControl dateControl3;
    private org.openswing.swing.client.DateControl dateControl6;
    private org.openswing.swing.table.columns.client.DecimalColumn decimalColumn10;
    private org.openswing.swing.table.columns.client.DecimalColumn decimalColumn11;
    private org.openswing.swing.table.columns.client.DecimalColumn decimalColumn12;
    private org.openswing.swing.table.columns.client.DecimalColumn decimalColumn13;
    private org.openswing.swing.table.columns.client.DecimalColumn decimalColumn14;
    private org.openswing.swing.table.columns.client.DecimalColumn decimalColumn15;
    private org.openswing.swing.table.columns.client.DecimalColumn decimalColumn16;
    private org.openswing.swing.table.columns.client.DecimalColumn decimalColumn17;
    private org.openswing.swing.table.columns.client.DecimalColumn decimalColumn6;
    private org.openswing.swing.table.columns.client.DecimalColumn decimalColumn7;
    private org.openswing.swing.table.columns.client.DecimalColumn decimalColumn8;
    private org.openswing.swing.table.columns.client.DecimalColumn decimalColumn9;
    private org.openswing.swing.client.EditButton editButton1;
    private org.openswing.swing.client.EditButton editButton2;
    private org.openswing.swing.client.EditButton editButton3;
    private org.openswing.swing.form.client.Form form1;
    private org.openswing.swing.client.FormattedTextControl formattedTextControl1;
    private org.openswing.swing.client.GenericButton genericButton1;
    private org.openswing.swing.client.GenericButton genericButton2;
    private org.openswing.swing.client.GenericButton genericButton3;
    private org.openswing.swing.client.GridControl gridControlParcelaCobranca;
    private org.openswing.swing.client.GridControl gridControlParcelaReceber;
    private org.openswing.swing.table.columns.client.IntegerColumn integerColumn4;
    private org.openswing.swing.table.columns.client.IntegerColumn integerColumn5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JTabbedPane jTabbedPane1;
    private org.openswing.swing.client.LabelControl labelControl1;
    private org.openswing.swing.client.LabelControl labelControl10;
    private org.openswing.swing.client.LabelControl labelControl3;
    private org.openswing.swing.client.LabelControl labelControl4;
    private org.openswing.swing.client.LabelControl labelControl5;
    private org.openswing.swing.client.LabelControl labelControl6;
    private org.openswing.swing.client.LabelControl labelControl7;
    private org.openswing.swing.client.LabelControl labelControl8;
    private org.openswing.swing.client.LabelControl labelControl9;
    private org.openswing.swing.client.NavigatorBar navigatorBar1;
    private org.openswing.swing.client.NavigatorBar navigatorBar2;
    private org.openswing.swing.client.NumericControl numericControl10;
    private org.openswing.swing.client.NumericControl numericControl7;
    private org.openswing.swing.client.NumericControl numericControl8;
    private org.openswing.swing.client.NumericControl numericControl9;
    private org.openswing.swing.client.ReloadButton reloadButton1;
    private org.openswing.swing.client.ReloadButton reloadButton2;
    private org.openswing.swing.client.ReloadButton reloadButton3;
    private org.openswing.swing.client.SaveButton saveButton1;
    private org.openswing.swing.client.SaveButton saveButton2;
    private org.openswing.swing.client.SaveButton saveButton3;
    private org.openswing.swing.table.columns.client.TextColumn textColumn18;
    private org.openswing.swing.table.columns.client.TextColumn textColumn2;
    private org.openswing.swing.client.TextControl textControl2;
    private org.openswing.swing.client.TextControl textControl5;
    // End of variables declaration//GEN-END:variables

}
