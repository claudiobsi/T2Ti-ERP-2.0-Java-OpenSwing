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
import org.openswing.swing.client.GenericButton;
import org.openswing.swing.lookup.client.LookupController;
import org.openswing.swing.mdi.client.InternalFrame;
import org.openswing.swing.util.client.ClientUtils;

public class FinParcelaPagamentoDetalhe extends InternalFrame {

    private LookupController tipoPagamentoController = new LookupController();
    private LookupController contaCaixaController = new LookupController();
    private FinParcelaPagamentoEfetuadoGridController pagamentoEfetuadoGridController;
    private FinParcelaPagamentoDetalheController controller;

    public FinParcelaPagamentoDetalhe(FinParcelaPagamentoDetalheController controller) {
        initComponents();

        this.controller = controller;
        form1.setFormController(this.controller);

        pagamentoEfetuadoGridController = new FinParcelaPagamentoEfetuadoGridController(this);
        gridControlPagamentoEfetuado.setController(pagamentoEfetuadoGridController);
        gridControlPagamentoEfetuado.setGridDataLocator(pagamentoEfetuadoGridController);

        genericButton1.setToolTipText("Efetuar Pagamento");
        genericButton2.setToolTipText("Excluir Pagamento");

        /*
         * Configurações do lookup do tipo de pagamento
         */
        tipoPagamentoController.setLookupValueObjectClassName("com.t2tierp.financeiro.java.FinTipoPagamentoVO");
        tipoPagamentoController.addLookup2ParentLink("id", "finTipoPagamento.id");
        tipoPagamentoController.addLookup2ParentLink("descricao", "finTipoPagamento.descricao");
        tipoPagamentoController.addLookup2ParentLink("tipo", "finTipoPagamento.tipo");
        tipoPagamentoController.setHeaderColumnName("id", "ID");
        tipoPagamentoController.setHeaderColumnName("descricao", "Descrição");
        tipoPagamentoController.setFrameTitle("Importa Tipo Pagamento");

        tipoPagamentoController.setVisibleStatusPanel(true);
        tipoPagamentoController.setVisibleColumn("id", true);
        tipoPagamentoController.setVisibleColumn("descricao", true);
        tipoPagamentoController.setFramePreferedSize(new Dimension(600, 500));

        tipoPagamentoController.setLookupDataLocator(new LookupDataLocatorGenerico(tipoPagamentoController.getLookupValueObjectClassName()));
        codLookupControl4.setLookupController(tipoPagamentoController);

        /*
         * Configurações do lookup da conta caixa
         */
        contaCaixaController.setLookupValueObjectClassName("com.t2tierp.cadastros.java.ContaCaixaVO");
        contaCaixaController.addLookup2ParentLink("id", "contaCaixa.id");
        contaCaixaController.addLookup2ParentLink("nome", "contaCaixa.nome");
        contaCaixaController.setHeaderColumnName("id", "ID");
        contaCaixaController.setHeaderColumnName("nome", "Nome");
        contaCaixaController.setFrameTitle("Importa Conta Caixa");

        contaCaixaController.setVisibleStatusPanel(true);
        contaCaixaController.setVisibleColumn("id", true);
        contaCaixaController.setVisibleColumn("nome", true);
        contaCaixaController.setFramePreferedSize(new Dimension(600, 500));

        contaCaixaController.setLookupDataLocator(new LookupDataLocatorGenerico(contaCaixaController.getLookupValueObjectClassName()));
        codLookupControl5.setLookupController(contaCaixaController);
        
    }

    public org.openswing.swing.form.client.Form getForm1() {
        return form1;
    }

    public org.openswing.swing.client.GridControl getGridControlPagamentoEfetuado() {
        return gridControlPagamentoEfetuado;
    }

    public FinParcelaPagamentoEfetuadoGridController getPagamentoEfetuadoGridController() {
        return pagamentoEfetuadoGridController;
    }

    public String getTipoBaixa() {
        return ((String) cboTipoBaixa.getSelectedItem()).substring(0, 1);
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
        genericButton1 = new GenericButton(new ImageIcon(ClientUtils.getImage("currency_dollar_16_h.png")));
        genericButton2 = new GenericButton(new ImageIcon(ClientUtils.getImage("delete_x16.png")));
        form1 = new org.openswing.swing.form.client.Form();
        labelControl5 = new org.openswing.swing.client.LabelControl();
        codLookupControl4 = new org.openswing.swing.client.CodLookupControl();
        textControl4 = new org.openswing.swing.client.TextControl();
        labelControl7 = new org.openswing.swing.client.LabelControl();
        codLookupControl5 = new org.openswing.swing.client.CodLookupControl();
        textControl5 = new org.openswing.swing.client.TextControl();
        labelControl9 = new org.openswing.swing.client.LabelControl();
        dateControl6 = new org.openswing.swing.client.DateControl();
        labelControl10 = new org.openswing.swing.client.LabelControl();
        numericControl7 = new org.openswing.swing.client.NumericControl();
        labelControl11 = new org.openswing.swing.client.LabelControl();
        numericControl8 = new org.openswing.swing.client.NumericControl();
        labelControl12 = new org.openswing.swing.client.LabelControl();
        numericControl9 = new org.openswing.swing.client.NumericControl();
        labelControl13 = new org.openswing.swing.client.LabelControl();
        numericControl10 = new org.openswing.swing.client.NumericControl();
        labelControl14 = new org.openswing.swing.client.LabelControl();
        numericControl11 = new org.openswing.swing.client.NumericControl();
        labelControl15 = new org.openswing.swing.client.LabelControl();
        numericControl12 = new org.openswing.swing.client.NumericControl();
        labelControl16 = new org.openswing.swing.client.LabelControl();
        numericControl13 = new org.openswing.swing.client.NumericControl();
        labelControl17 = new org.openswing.swing.client.LabelControl();
        labelControl18 = new org.openswing.swing.client.LabelControl();
        dateControl7 = new org.openswing.swing.client.DateControl();
        labelControl19 = new org.openswing.swing.client.LabelControl();
        numericControl14 = new org.openswing.swing.client.NumericControl();
        textAreaControl1 = new org.openswing.swing.client.TextAreaControl();
        jLabel1 = new javax.swing.JLabel();
        cboTipoBaixa = new javax.swing.JComboBox();
        gridControlPagamentoEfetuado = new org.openswing.swing.client.GridControl();
        integerColumn1 = new org.openswing.swing.table.columns.client.IntegerColumn();
        textColumn4 = new org.openswing.swing.table.columns.client.TextColumn();
        textColumn5 = new org.openswing.swing.table.columns.client.TextColumn();
        dateColumn6 = new org.openswing.swing.table.columns.client.DateColumn();
        decimalColumn7 = new org.openswing.swing.table.columns.client.DecimalColumn();
        decimalColumn10 = new org.openswing.swing.table.columns.client.DecimalColumn();
        decimalColumn8 = new org.openswing.swing.table.columns.client.DecimalColumn();
        decimalColumn11 = new org.openswing.swing.table.columns.client.DecimalColumn();
        decimalColumn9 = new org.openswing.swing.table.columns.client.DecimalColumn();
        decimalColumn12 = new org.openswing.swing.table.columns.client.DecimalColumn();
        decimalColumn13 = new org.openswing.swing.table.columns.client.DecimalColumn();
        textColumn14 = new org.openswing.swing.table.columns.client.TextColumn();

        setTitle("T2Ti ERP - Financeiro - Contas a Pagar");
        setPreferredSize(new java.awt.Dimension(700, 400));
        getContentPane().setLayout(new java.awt.GridBagLayout());

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Fin Parcela Pagamento"));
        jPanel1.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

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

        form1.setVOClassName("com.t2tierp.financeiro.java.FinParcelaPagamentoVO");
        form1.setFunctionId("finParcelaPagamento");
        form1.setLayout(new java.awt.GridBagLayout());

        labelControl5.setLabel("Tipo Pagamento:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        form1.add(labelControl5, gridBagConstraints);

        codLookupControl4.setAllowOnlyNumbers(true);
        codLookupControl4.setAttributeName("finTipoPagamento.id");
        codLookupControl4.setEnabled(false);
        codLookupControl4.setLinkLabel(labelControl5);
        codLookupControl4.setRequired(true);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = -70;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        form1.add(codLookupControl4, gridBagConstraints);

        textControl4.setAttributeName("finTipoPagamento.descricao");
        textControl4.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        form1.add(textControl4, gridBagConstraints);

        labelControl7.setLabel("Conta Caixa:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        form1.add(labelControl7, gridBagConstraints);

        codLookupControl5.setAllowOnlyNumbers(true);
        codLookupControl5.setAttributeName("contaCaixa.id");
        codLookupControl5.setEnabled(false);
        codLookupControl5.setLinkLabel(labelControl7);
        codLookupControl5.setRequired(true);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = -70;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        form1.add(codLookupControl5, gridBagConstraints);

        textControl5.setAttributeName("contaCaixa.nome");
        textControl5.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        form1.add(textControl5, gridBagConstraints);

        labelControl9.setLabel("Data Pagamento:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        form1.add(labelControl9, gridBagConstraints);

        dateControl6.setAttributeName("dataPagamento");
        dateControl6.setEnabled(false);
        dateControl6.setLinkLabel(labelControl9);
        dateControl6.setRequired(true);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        form1.add(dateControl6, gridBagConstraints);

        labelControl10.setLabel("Taxa Juro:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        form1.add(labelControl10, gridBagConstraints);

        numericControl7.setAttributeName("taxaJuro");
        numericControl7.setDecimals(2);
        numericControl7.setEnabled(false);
        numericControl7.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                numericControl7FocusLost(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        form1.add(numericControl7, gridBagConstraints);

        labelControl11.setLabel("Taxa Multa:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        form1.add(labelControl11, gridBagConstraints);

        numericControl8.setAttributeName("taxaMulta");
        numericControl8.setDecimals(2);
        numericControl8.setEnabled(false);
        numericControl8.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                numericControl8FocusLost(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        form1.add(numericControl8, gridBagConstraints);

        labelControl12.setLabel("Taxa Desconto:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        form1.add(labelControl12, gridBagConstraints);

        numericControl9.setAttributeName("taxaDesconto");
        numericControl9.setDecimals(2);
        numericControl9.setEnabled(false);
        numericControl9.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                numericControl9FocusLost(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        form1.add(numericControl9, gridBagConstraints);

        labelControl13.setLabel("Valor Juro:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        form1.add(labelControl13, gridBagConstraints);

        numericControl10.setAttributeName("valorJuro");
        numericControl10.setDecimals(2);
        numericControl10.setEnabled(false);
        numericControl10.setEnabledOnEdit(false);
        numericControl10.setEnabledOnInsert(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        form1.add(numericControl10, gridBagConstraints);

        labelControl14.setLabel("Valor Multa:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        form1.add(labelControl14, gridBagConstraints);

        numericControl11.setAttributeName("valorMulta");
        numericControl11.setDecimals(2);
        numericControl11.setEnabled(false);
        numericControl11.setEnabledOnEdit(false);
        numericControl11.setEnabledOnInsert(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        form1.add(numericControl11, gridBagConstraints);

        labelControl15.setLabel("Valor Desconto:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        form1.add(labelControl15, gridBagConstraints);

        numericControl12.setAttributeName("valorDesconto");
        numericControl12.setDecimals(2);
        numericControl12.setEnabled(false);
        numericControl12.setEnabledOnEdit(false);
        numericControl12.setEnabledOnInsert(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        form1.add(numericControl12, gridBagConstraints);

        labelControl16.setLabel("Valor Pago:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        form1.add(labelControl16, gridBagConstraints);

        numericControl13.setAttributeName("valorPago");
        numericControl13.setDecimals(2);
        numericControl13.setEnabled(false);
        numericControl13.setEnabledOnEdit(false);
        numericControl13.setEnabledOnInsert(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        form1.add(numericControl13, gridBagConstraints);

        labelControl17.setLabel("Historico:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        form1.add(labelControl17, gridBagConstraints);

        labelControl18.setLabel("Data Vencimento:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        form1.add(labelControl18, gridBagConstraints);

        dateControl7.setAttributeName("finParcelaPagar.dataVencimento");
        dateControl7.setEnabled(false);
        dateControl7.setEnabledOnEdit(false);
        dateControl7.setEnabledOnInsert(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        form1.add(dateControl7, gridBagConstraints);

        labelControl19.setLabel("Valor a Pagar:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        form1.add(labelControl19, gridBagConstraints);

        numericControl14.setAttributeName("finParcelaPagar.valor");
        numericControl14.setDecimals(2);
        numericControl14.setEnabled(false);
        numericControl14.setEnabledOnEdit(false);
        numericControl14.setEnabledOnInsert(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        form1.add(numericControl14, gridBagConstraints);

        textAreaControl1.setAttributeName("historico");
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

        jLabel1.setText("Tipo Baixa");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        form1.add(jLabel1, gridBagConstraints);

        cboTipoBaixa.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Total", "Parcial" }));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        form1.add(cboTipoBaixa, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        getContentPane().add(form1, gridBagConstraints);

        gridControlPagamentoEfetuado.setAutoLoadData(false);
        gridControlPagamentoEfetuado.setFunctionId("finParcelaPagamento");
        gridControlPagamentoEfetuado.setValueObjectClassName("com.t2tierp.financeiro.java.FinParcelaPagamentoVO");
        gridControlPagamentoEfetuado.getColumnContainer().setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        integerColumn1.setColumnName("finChequeEmitido.cheque.numero");
        integerColumn1.setHeaderColumnName("Nr Cheque");
        integerColumn1.setHeaderFont(new java.awt.Font("Arial", 1, 11));
        gridControlPagamentoEfetuado.getColumnContainer().add(integerColumn1);

        textColumn4.setColumnName("finTipoPagamento.descricao");
        textColumn4.setHeaderColumnName("Tipo Pagamento");
        textColumn4.setHeaderFont(new java.awt.Font("Arial", 1, 11));
        gridControlPagamentoEfetuado.getColumnContainer().add(textColumn4);

        textColumn5.setColumnName("contaCaixa.nome");
        textColumn5.setHeaderColumnName("Conta Caixa");
        textColumn5.setHeaderFont(new java.awt.Font("Arial", 1, 11));
        gridControlPagamentoEfetuado.getColumnContainer().add(textColumn5);

        dateColumn6.setColumnName("dataPagamento");
        dateColumn6.setHeaderColumnName("Data Pagamento");
        dateColumn6.setHeaderFont(new java.awt.Font("Arial", 1, 11));
        gridControlPagamentoEfetuado.getColumnContainer().add(dateColumn6);

        decimalColumn7.setColumnName("taxaJuro");
        decimalColumn7.setHeaderColumnName("Taxa Juro");
        decimalColumn7.setHeaderFont(new java.awt.Font("Arial", 1, 11));
        decimalColumn7.setDecimals(2);
        gridControlPagamentoEfetuado.getColumnContainer().add(decimalColumn7);

        decimalColumn10.setColumnName("valorJuro");
        decimalColumn10.setDecimals(2);
        decimalColumn10.setHeaderColumnName("Valor Juro");
        decimalColumn10.setHeaderFont(new java.awt.Font("Arial", 1, 11));
        gridControlPagamentoEfetuado.getColumnContainer().add(decimalColumn10);

        decimalColumn8.setColumnName("taxaMulta");
        decimalColumn8.setHeaderColumnName("Taxa Multa");
        decimalColumn8.setHeaderFont(new java.awt.Font("Arial", 1, 11));
        decimalColumn8.setDecimals(2);
        gridControlPagamentoEfetuado.getColumnContainer().add(decimalColumn8);

        decimalColumn11.setColumnName("valorMulta");
        decimalColumn11.setDecimals(2);
        decimalColumn11.setHeaderColumnName("Valor Multa");
        decimalColumn11.setHeaderFont(new java.awt.Font("Arial", 1, 11));
        gridControlPagamentoEfetuado.getColumnContainer().add(decimalColumn11);

        decimalColumn9.setColumnName("taxaDesconto");
        decimalColumn9.setHeaderColumnName("Taxa Desconto");
        decimalColumn9.setHeaderFont(new java.awt.Font("Arial", 1, 11));
        decimalColumn9.setDecimals(2);
        gridControlPagamentoEfetuado.getColumnContainer().add(decimalColumn9);

        decimalColumn12.setColumnName("valorDesconto");
        decimalColumn12.setHeaderColumnName("Valor Desconto");
        decimalColumn12.setHeaderFont(new java.awt.Font("Arial", 1, 11));
        decimalColumn12.setDecimals(2);
        gridControlPagamentoEfetuado.getColumnContainer().add(decimalColumn12);

        decimalColumn13.setColumnName("valorPago");
        decimalColumn13.setHeaderColumnName("Valor Pago");
        decimalColumn13.setHeaderFont(new java.awt.Font("Arial", 1, 11));
        decimalColumn13.setDecimals(2);
        gridControlPagamentoEfetuado.getColumnContainer().add(decimalColumn13);

        textColumn14.setColumnName("historico");
        textColumn14.setHeaderColumnName("Historico");
        textColumn14.setHeaderFont(new java.awt.Font("Arial", 1, 11));
        gridControlPagamentoEfetuado.getColumnContainer().add(textColumn14);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        getContentPane().add(gridControlPagamentoEfetuado, gridBagConstraints);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void numericControl7FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_numericControl7FocusLost
        controller.calculaTotalPago();
    }//GEN-LAST:event_numericControl7FocusLost

    private void numericControl8FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_numericControl8FocusLost
        controller.calculaTotalPago();
    }//GEN-LAST:event_numericControl8FocusLost

    private void numericControl9FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_numericControl9FocusLost
        controller.calculaTotalPago();
    }//GEN-LAST:event_numericControl9FocusLost

    private void genericButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_genericButton1ActionPerformed
        controller.efetuaPagamento();
    }//GEN-LAST:event_genericButton1ActionPerformed

    private void genericButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_genericButton2ActionPerformed
        controller.excluiPagamento();
    }//GEN-LAST:event_genericButton2ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox cboTipoBaixa;
    private org.openswing.swing.client.CodLookupControl codLookupControl4;
    private org.openswing.swing.client.CodLookupControl codLookupControl5;
    private org.openswing.swing.table.columns.client.DateColumn dateColumn6;
    private org.openswing.swing.client.DateControl dateControl6;
    private org.openswing.swing.client.DateControl dateControl7;
    private org.openswing.swing.table.columns.client.DecimalColumn decimalColumn10;
    private org.openswing.swing.table.columns.client.DecimalColumn decimalColumn11;
    private org.openswing.swing.table.columns.client.DecimalColumn decimalColumn12;
    private org.openswing.swing.table.columns.client.DecimalColumn decimalColumn13;
    private org.openswing.swing.table.columns.client.DecimalColumn decimalColumn7;
    private org.openswing.swing.table.columns.client.DecimalColumn decimalColumn8;
    private org.openswing.swing.table.columns.client.DecimalColumn decimalColumn9;
    private org.openswing.swing.form.client.Form form1;
    private org.openswing.swing.client.GenericButton genericButton1;
    private org.openswing.swing.client.GenericButton genericButton2;
    private org.openswing.swing.client.GridControl gridControlPagamentoEfetuado;
    private org.openswing.swing.table.columns.client.IntegerColumn integerColumn1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
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
    private org.openswing.swing.client.LabelControl labelControl5;
    private org.openswing.swing.client.LabelControl labelControl7;
    private org.openswing.swing.client.LabelControl labelControl9;
    private org.openswing.swing.client.NumericControl numericControl10;
    private org.openswing.swing.client.NumericControl numericControl11;
    private org.openswing.swing.client.NumericControl numericControl12;
    private org.openswing.swing.client.NumericControl numericControl13;
    private org.openswing.swing.client.NumericControl numericControl14;
    private org.openswing.swing.client.NumericControl numericControl7;
    private org.openswing.swing.client.NumericControl numericControl8;
    private org.openswing.swing.client.NumericControl numericControl9;
    private org.openswing.swing.client.TextAreaControl textAreaControl1;
    private org.openswing.swing.table.columns.client.TextColumn textColumn14;
    private org.openswing.swing.table.columns.client.TextColumn textColumn4;
    private org.openswing.swing.table.columns.client.TextColumn textColumn5;
    private org.openswing.swing.client.TextControl textControl4;
    private org.openswing.swing.client.TextControl textControl5;
    // End of variables declaration//GEN-END:variables


}
