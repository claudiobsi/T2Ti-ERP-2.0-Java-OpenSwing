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
package com.t2tierp.ordemservico.cliente;

import com.t2tierp.padrao.cliente.LookupDataLocatorGenerico;
import java.awt.Dimension;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.text.MaskFormatter;
import org.openswing.swing.client.GenericButton;
import org.openswing.swing.lookup.client.LookupController;
import org.openswing.swing.mdi.client.InternalFrame;
import org.openswing.swing.util.client.ClientUtils;

public class OsAberturaDetalhe extends InternalFrame {

    private OsAberturaDetalheController controller;
    private LookupController statusController = new LookupController();
    private LookupController colaboradorController = new LookupController();
    private LookupController clienteController = new LookupController();
    private LookupController produtoController = new LookupController();
    private LookupController equipamentoController = new LookupController();
    private OsEvolucaoGridController evolucaoGridController;
    private OsProdutoServicoGridController produtoServicoGridController;
    private OsAberturaEquipamentoGridController aberturaEquipamentoGridController;
    
    public OsAberturaDetalhe(OsAberturaDetalheController controller) {
        initComponents();

        genericButton1.setToolTipText("Gerar Orçamento");
        
        this.controller = controller;
        form1.setFormController(controller);
        
        evolucaoGridController = new OsEvolucaoGridController(this);
        gridControlEvolucao.setController(evolucaoGridController);
        gridControlEvolucao.setGridDataLocator(evolucaoGridController);
        
        produtoServicoGridController = new OsProdutoServicoGridController(this);
        gridControlProdutoServico.setController(produtoServicoGridController);
        gridControlProdutoServico.setGridDataLocator(produtoServicoGridController);
        
        aberturaEquipamentoGridController = new OsAberturaEquipamentoGridController(this);
        gridControlEquipamento.setController(aberturaEquipamentoGridController);
        gridControlEquipamento.setGridDataLocator(aberturaEquipamentoGridController);
        
        formattedTextControl1.setEnabled(false);
        formattedTextControl2.setEnabled(false);
        try {
            MaskFormatter mask = new MaskFormatter("##:##:##");
            mask.setValidCharacters("0123456789");
            formattedTextControl1.setFormatter(mask);

            mask = new MaskFormatter("##:##:##");
            mask.setValidCharacters("0123456789");
            formattedTextControl2.setFormatter(mask);

            mask = new MaskFormatter("##:##:##");
            mask.setValidCharacters("0123456789");
            formattedTextColumn1.setFormatter(mask);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        /*
         * Configurações do lookup do status
         */
        statusController.setLookupValueObjectClassName("com.t2tierp.ordemservico.java.OsStatusVO");
        statusController.addLookup2ParentLink("id", "osStatus.id");
        statusController.addLookup2ParentLink("nome", "osStatus.nome");
        statusController.setHeaderColumnName("id", "ID");
        statusController.setHeaderColumnName("nome", "Nome");
        statusController.setFrameTitle("Importa Status");

        statusController.setVisibleStatusPanel(true);
        statusController.setVisibleColumn("id", true);
        statusController.setVisibleColumn("nome", true);
        statusController.setFramePreferedSize(new Dimension(600, 500));

        statusController.setLookupDataLocator(new LookupDataLocatorGenerico(statusController.getLookupValueObjectClassName()));
        codLookupControl2.setLookupController(statusController);
        
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
        codLookupControl4.setLookupController(clienteController);

        /*
         * Configurações do lookup do produto
         */
        produtoController.setLookupValueObjectClassName("com.t2tierp.cadastros.java.ProdutoVO");
        produtoController.addLookup2ParentLink("id", "produto.id");
        produtoController.addLookup2ParentLink("nome", "produto.nome");
        produtoController.addLookup2ParentLink("servico", "produto.servico");
        produtoController.addLookup2ParentLink("valorVenda", "produto.valorVenda");
        produtoController.setHeaderColumnName("id", "ID");
        produtoController.setHeaderColumnName("nome", "Nome");
        produtoController.setHeaderColumnName("servico", "Serviço");
        produtoController.setFrameTitle("Importa Produto/Serviço");

        produtoController.setVisibleStatusPanel(true);
        produtoController.setVisibleColumn("id", true);
        produtoController.setVisibleColumn("nome", true);
        produtoController.setVisibleColumn("servico", true);
        produtoController.setFramePreferedSize(new Dimension(600, 500));

        produtoController.setLookupDataLocator(new LookupDataLocatorGenerico(produtoController.getLookupValueObjectClassName()));
        codLookupColumn1.setLookupController(produtoController);

        /*
         * Configurações do lookup do equipamento
         */
        equipamentoController.setLookupValueObjectClassName("com.t2tierp.ordemservico.java.OsEquipamentoVO");
        equipamentoController.addLookup2ParentLink("id", "osEquipamento.id");
        equipamentoController.addLookup2ParentLink("nome", "osEquipamento.nome");
        equipamentoController.setHeaderColumnName("id", "ID");
        equipamentoController.setHeaderColumnName("nome", "Nome");
        equipamentoController.setFrameTitle("Importa Equipamento");

        equipamentoController.setVisibleStatusPanel(true);
        equipamentoController.setVisibleColumn("id", true);
        equipamentoController.setVisibleColumn("nome", true);
        equipamentoController.setFramePreferedSize(new Dimension(600, 500));

        equipamentoController.setLookupDataLocator(new LookupDataLocatorGenerico(equipamentoController.getLookupValueObjectClassName()));
        codLookupColumn2.setLookupController(equipamentoController);
        
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
        genericButton1 = new GenericButton(new ImageIcon(ClientUtils.getImage("print.gif")));
        form1 = new org.openswing.swing.form.client.Form();
        labelControl1 = new org.openswing.swing.client.LabelControl();
        codLookupControl2 = new org.openswing.swing.client.CodLookupControl();
        labelControl3 = new org.openswing.swing.client.LabelControl();
        codLookupControl3 = new org.openswing.swing.client.CodLookupControl();
        labelControl5 = new org.openswing.swing.client.LabelControl();
        codLookupControl4 = new org.openswing.swing.client.CodLookupControl();
        labelControl7 = new org.openswing.swing.client.LabelControl();
        textControl5 = new org.openswing.swing.client.TextControl();
        labelControl8 = new org.openswing.swing.client.LabelControl();
        dateControl6 = new org.openswing.swing.client.DateControl();
        labelControl9 = new org.openswing.swing.client.LabelControl();
        labelControl10 = new org.openswing.swing.client.LabelControl();
        dateControl8 = new org.openswing.swing.client.DateControl();
        labelControl11 = new org.openswing.swing.client.LabelControl();
        labelControl12 = new org.openswing.swing.client.LabelControl();
        dateControl10 = new org.openswing.swing.client.DateControl();
        labelControl13 = new org.openswing.swing.client.LabelControl();
        textControl11 = new org.openswing.swing.client.TextControl();
        labelControl14 = new org.openswing.swing.client.LabelControl();
        textControl12 = new org.openswing.swing.client.TextControl();
        labelControl15 = new org.openswing.swing.client.LabelControl();
        textControl13 = new org.openswing.swing.client.TextControl();
        labelControl16 = new org.openswing.swing.client.LabelControl();
        labelControl17 = new org.openswing.swing.client.LabelControl();
        formattedTextControl1 = new org.openswing.swing.client.FormattedTextControl();
        formattedTextControl2 = new org.openswing.swing.client.FormattedTextControl();
        textAreaControl1 = new org.openswing.swing.client.TextAreaControl();
        textAreaControl2 = new org.openswing.swing.client.TextAreaControl();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        insertButton1 = new org.openswing.swing.client.InsertButton();
        editButton2 = new org.openswing.swing.client.EditButton();
        deleteButton1 = new org.openswing.swing.client.DeleteButton();
        saveButton2 = new org.openswing.swing.client.SaveButton();
        reloadButton2 = new org.openswing.swing.client.ReloadButton();
        navigatorBar1 = new org.openswing.swing.client.NavigatorBar();
        gridControlEvolucao = new org.openswing.swing.client.GridControl();
        dateColumn3 = new org.openswing.swing.table.columns.client.DateColumn();
        formattedTextColumn1 = new org.openswing.swing.table.columns.client.FormattedTextColumn();
        textColumn5 = new org.openswing.swing.table.columns.client.TextColumn();
        comboColumn6 = new org.openswing.swing.table.columns.client.ComboColumn();
        jPanel3 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        insertButton2 = new org.openswing.swing.client.InsertButton();
        editButton3 = new org.openswing.swing.client.EditButton();
        deleteButton2 = new org.openswing.swing.client.DeleteButton();
        saveButton3 = new org.openswing.swing.client.SaveButton();
        reloadButton3 = new org.openswing.swing.client.ReloadButton();
        navigatorBar2 = new org.openswing.swing.client.NavigatorBar();
        gridControlProdutoServico = new org.openswing.swing.client.GridControl();
        codLookupColumn1 = new org.openswing.swing.table.columns.client.CodLookupColumn();
        comboColumn1 = new org.openswing.swing.table.columns.client.ComboColumn();
        textColumn6 = new org.openswing.swing.table.columns.client.TextColumn();
        decimalColumn6 = new org.openswing.swing.table.columns.client.DecimalColumn();
        decimalColumn7 = new org.openswing.swing.table.columns.client.DecimalColumn();
        decimalColumn8 = new org.openswing.swing.table.columns.client.DecimalColumn();
        decimalColumn9 = new org.openswing.swing.table.columns.client.DecimalColumn();
        decimalColumn10 = new org.openswing.swing.table.columns.client.DecimalColumn();
        decimalColumn11 = new org.openswing.swing.table.columns.client.DecimalColumn();
        jPanel4 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        insertButton3 = new org.openswing.swing.client.InsertButton();
        editButton4 = new org.openswing.swing.client.EditButton();
        deleteButton3 = new org.openswing.swing.client.DeleteButton();
        saveButton4 = new org.openswing.swing.client.SaveButton();
        reloadButton4 = new org.openswing.swing.client.ReloadButton();
        navigatorBar3 = new org.openswing.swing.client.NavigatorBar();
        gridControlEquipamento = new org.openswing.swing.client.GridControl();
        codLookupColumn2 = new org.openswing.swing.table.columns.client.CodLookupColumn();
        textColumn4 = new org.openswing.swing.table.columns.client.TextColumn();
        comboColumn2 = new org.openswing.swing.table.columns.client.ComboColumn();

        setTitle("T2Ti ERP - Ordem de Servico");
        setPreferredSize(new java.awt.Dimension(700, 400));
        getContentPane().setLayout(new java.awt.GridBagLayout());

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Os Abertura"));
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

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        getContentPane().add(jPanel1, gridBagConstraints);

        form1.setVOClassName("com.t2tierp.ordemservico.java.OsAberturaVO");
        form1.setEditButton(editButton1);
        form1.setFunctionId("osAbertura");
        form1.setReloadButton(reloadButton1);
        form1.setSaveButton(saveButton1);
        form1.setLayout(new java.awt.GridBagLayout());

        labelControl1.setText("Status:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        form1.add(labelControl1, gridBagConstraints);

        codLookupControl2.setAttributeName("osStatus.nome");
        codLookupControl2.setEnableCodBox(false);
        codLookupControl2.setLinkLabel(labelControl1);
        codLookupControl2.setRequired(true);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        form1.add(codLookupControl2, gridBagConstraints);

        labelControl3.setText("Colaborador:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        form1.add(labelControl3, gridBagConstraints);

        codLookupControl3.setAttributeName("colaborador.pessoa.nome");
        codLookupControl3.setEnableCodBox(false);
        codLookupControl3.setLinkLabel(labelControl3);
        codLookupControl3.setRequired(true);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        form1.add(codLookupControl3, gridBagConstraints);

        labelControl5.setText("Cliente:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        form1.add(labelControl5, gridBagConstraints);

        codLookupControl4.setAttributeName("cliente.pessoa.nome");
        codLookupControl4.setEnableCodBox(false);
        codLookupControl4.setLinkLabel(labelControl5);
        codLookupControl4.setRequired(true);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        form1.add(codLookupControl4, gridBagConstraints);

        labelControl7.setLabel("Numero:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        form1.add(labelControl7, gridBagConstraints);

        textControl5.setAttributeName("numero");
        textControl5.setEnabled(false);
        textControl5.setEnabledOnEdit(false);
        textControl5.setEnabledOnInsert(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        form1.add(textControl5, gridBagConstraints);

        labelControl8.setLabel("Data Inicio:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        form1.add(labelControl8, gridBagConstraints);

        dateControl6.setAttributeName("dataInicio");
        dateControl6.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        form1.add(dateControl6, gridBagConstraints);

        labelControl9.setLabel("Hora Inicio:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        form1.add(labelControl9, gridBagConstraints);

        labelControl10.setLabel("Data Previsao:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        form1.add(labelControl10, gridBagConstraints);

        dateControl8.setAttributeName("dataPrevisao");
        dateControl8.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        form1.add(dateControl8, gridBagConstraints);

        labelControl11.setLabel("Hora Previsao:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        form1.add(labelControl11, gridBagConstraints);

        labelControl12.setLabel("Data Fim:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        form1.add(labelControl12, gridBagConstraints);

        dateControl10.setAttributeName("dataFim");
        dateControl10.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        form1.add(dateControl10, gridBagConstraints);

        labelControl13.setLabel("Hora Fim:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        form1.add(labelControl13, gridBagConstraints);

        textControl11.setAttributeName("horaFim");
        textControl11.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        form1.add(textControl11, gridBagConstraints);

        labelControl14.setLabel("Nome Contato:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        form1.add(labelControl14, gridBagConstraints);

        textControl12.setAttributeName("nomeContato");
        textControl12.setEnabled(false);
        textControl12.setMaxCharacters(50);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        form1.add(textControl12, gridBagConstraints);

        labelControl15.setLabel("Fone Contato:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        form1.add(labelControl15, gridBagConstraints);

        textControl13.setAttributeName("foneContato");
        textControl13.setEnabled(false);
        textControl13.setMaxCharacters(15);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        form1.add(textControl13, gridBagConstraints);

        labelControl16.setLabel("Observacao Cliente:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        form1.add(labelControl16, gridBagConstraints);

        labelControl17.setLabel("Observacao Abertura:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        form1.add(labelControl17, gridBagConstraints);

        formattedTextControl1.setAttributeName("horaPrevisao");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        form1.add(formattedTextControl1, gridBagConstraints);

        formattedTextControl2.setAttributeName("horaInicio");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        form1.add(formattedTextControl2, gridBagConstraints);

        textAreaControl1.setAttributeName("observacaoCliente");
        textAreaControl1.setEnabled(false);
        textAreaControl1.setMaxCharacters(1000);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 9;
        gridBagConstraints.gridwidth = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipady = 30;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        form1.add(textAreaControl1, gridBagConstraints);

        textAreaControl2.setAttributeName("observacaoAbertura");
        textAreaControl2.setEnabled(false);
        textAreaControl2.setMaxCharacters(1000);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.gridwidth = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipady = 30;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        form1.add(textAreaControl2, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        getContentPane().add(form1, gridBagConstraints);

        jPanel2.setLayout(new java.awt.GridBagLayout());

        jPanel5.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));
        jPanel5.add(insertButton1);
        jPanel5.add(editButton2);
        jPanel5.add(deleteButton1);
        jPanel5.add(saveButton2);
        jPanel5.add(reloadButton2);
        jPanel5.add(navigatorBar1);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        jPanel2.add(jPanel5, gridBagConstraints);

        gridControlEvolucao.setAutoLoadData(false);
        gridControlEvolucao.setDeleteButton(deleteButton1);
        gridControlEvolucao.setEditButton(editButton2);
        gridControlEvolucao.setFunctionId("osEvolucao");
        gridControlEvolucao.setInsertButton(insertButton1);
        gridControlEvolucao.setNavBar(navigatorBar1);
        gridControlEvolucao.setReloadButton(reloadButton2);
        gridControlEvolucao.setSaveButton(saveButton2);
        gridControlEvolucao.setValueObjectClassName("com.t2tierp.ordemservico.java.OsEvolucaoVO");

        dateColumn3.setColumnName("dataRegistro");
        dateColumn3.setEditableOnEdit(true);
        dateColumn3.setEditableOnInsert(true);
        dateColumn3.setHeaderColumnName("Data Registro");
        dateColumn3.setHeaderFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        gridControlEvolucao.getColumnContainer().add(dateColumn3);

        formattedTextColumn1.setColumnName("horaRegistro");
        formattedTextColumn1.setEditableOnEdit(true);
        formattedTextColumn1.setEditableOnInsert(true);
        formattedTextColumn1.setHeaderColumnName("Hora Registro");
        formattedTextColumn1.setHeaderFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        gridControlEvolucao.getColumnContainer().add(formattedTextColumn1);

        textColumn5.setColumnName("observacao");
        textColumn5.setEditableOnEdit(true);
        textColumn5.setEditableOnInsert(true);
        textColumn5.setHeaderColumnName("Observacao");
        textColumn5.setHeaderFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        textColumn5.setPreferredWidth(400);
        gridControlEvolucao.getColumnContainer().add(textColumn5);

        comboColumn6.setColumnName("enviarEmail");
        comboColumn6.setDomainId("simnao");
        comboColumn6.setEditableOnEdit(true);
        comboColumn6.setEditableOnInsert(true);
        comboColumn6.setHeaderColumnName("Enviar Email");
        comboColumn6.setHeaderFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        gridControlEvolucao.getColumnContainer().add(comboColumn6);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel2.add(gridControlEvolucao, gridBagConstraints);

        jTabbedPane1.addTab("Evolução", jPanel2);

        jPanel3.setLayout(new java.awt.GridBagLayout());

        jPanel6.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));
        jPanel6.add(insertButton2);
        jPanel6.add(editButton3);
        jPanel6.add(deleteButton2);
        jPanel6.add(saveButton3);
        jPanel6.add(reloadButton3);
        jPanel6.add(navigatorBar2);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        jPanel3.add(jPanel6, gridBagConstraints);

        gridControlProdutoServico.setAutoLoadData(false);
        gridControlProdutoServico.setDeleteButton(deleteButton2);
        gridControlProdutoServico.setEditButton(editButton3);
        gridControlProdutoServico.setFunctionId("osProdutoServico");
        gridControlProdutoServico.setInsertButton(insertButton2);
        gridControlProdutoServico.setNavBar(navigatorBar2);
        gridControlProdutoServico.setReloadButton(reloadButton3);
        gridControlProdutoServico.setSaveButton(saveButton3);
        gridControlProdutoServico.setValueObjectClassName("com.t2tierp.ordemservico.java.OsProdutoServicoVO");

        codLookupColumn1.setColumnName("produto.nome");
        codLookupColumn1.setEditableOnEdit(true);
        codLookupColumn1.setEditableOnInsert(true);
        codLookupColumn1.setHeaderColumnName("Produto / Serviço");
        codLookupColumn1.setHeaderFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        codLookupColumn1.setPreferredWidth(200);
        gridControlProdutoServico.getColumnContainer().add(codLookupColumn1);

        comboColumn1.setColumnName("tipo");
        comboColumn1.setColumnRequired(false);
        comboColumn1.setDomainId("osTipoProdutoServico");
        comboColumn1.setHeaderColumnName("Tipo");
        comboColumn1.setHeaderFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        gridControlProdutoServico.getColumnContainer().add(comboColumn1);

        textColumn6.setColumnName("complemento");
        textColumn6.setColumnRequired(false);
        textColumn6.setEditableOnEdit(true);
        textColumn6.setEditableOnInsert(true);
        textColumn6.setHeaderColumnName("Complemento");
        textColumn6.setHeaderFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        gridControlProdutoServico.getColumnContainer().add(textColumn6);

        decimalColumn6.setColumnName("quantidade");
        decimalColumn6.setDecimals(2);
        decimalColumn6.setEditableOnEdit(true);
        decimalColumn6.setEditableOnInsert(true);
        decimalColumn6.setHeaderColumnName("Quantidade");
        decimalColumn6.setHeaderFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        gridControlProdutoServico.getColumnContainer().add(decimalColumn6);

        decimalColumn7.setColumnName("valorUnitario");
        decimalColumn7.setColumnRequired(false);
        decimalColumn7.setDecimals(2);
        decimalColumn7.setHeaderColumnName("Valor Unitario");
        decimalColumn7.setHeaderFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        gridControlProdutoServico.getColumnContainer().add(decimalColumn7);

        decimalColumn8.setColumnName("valorSubtotal");
        decimalColumn8.setColumnRequired(false);
        decimalColumn8.setDecimals(2);
        decimalColumn8.setHeaderColumnName("Valor Subtotal");
        decimalColumn8.setHeaderFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        gridControlProdutoServico.getColumnContainer().add(decimalColumn8);

        decimalColumn9.setColumnName("taxaDesconto");
        decimalColumn9.setColumnRequired(false);
        decimalColumn9.setDecimals(2);
        decimalColumn9.setEditableOnEdit(true);
        decimalColumn9.setEditableOnInsert(true);
        decimalColumn9.setHeaderColumnName("Taxa Desconto");
        decimalColumn9.setHeaderFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        gridControlProdutoServico.getColumnContainer().add(decimalColumn9);

        decimalColumn10.setColumnName("valorDesconto");
        decimalColumn10.setColumnRequired(false);
        decimalColumn10.setDecimals(2);
        decimalColumn10.setHeaderColumnName("Valor Desconto");
        decimalColumn10.setHeaderFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        gridControlProdutoServico.getColumnContainer().add(decimalColumn10);

        decimalColumn11.setColumnName("valorTotal");
        decimalColumn11.setColumnRequired(false);
        decimalColumn11.setDecimals(2);
        decimalColumn11.setHeaderColumnName("Valor Total");
        decimalColumn11.setHeaderFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        gridControlProdutoServico.getColumnContainer().add(decimalColumn11);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel3.add(gridControlProdutoServico, gridBagConstraints);

        jTabbedPane1.addTab("Produtos / Serviços", jPanel3);

        jPanel4.setLayout(new java.awt.GridBagLayout());

        jPanel7.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));
        jPanel7.add(insertButton3);
        jPanel7.add(editButton4);
        jPanel7.add(deleteButton3);
        jPanel7.add(saveButton4);
        jPanel7.add(reloadButton4);
        jPanel7.add(navigatorBar3);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        jPanel4.add(jPanel7, gridBagConstraints);

        gridControlEquipamento.setAutoLoadData(false);
        gridControlEquipamento.setDeleteButton(deleteButton3);
        gridControlEquipamento.setEditButton(editButton4);
        gridControlEquipamento.setFunctionId("osAberturaEquipamento");
        gridControlEquipamento.setInsertButton(insertButton3);
        gridControlEquipamento.setNavBar(navigatorBar3);
        gridControlEquipamento.setReloadButton(reloadButton4);
        gridControlEquipamento.setSaveButton(saveButton4);
        gridControlEquipamento.setValueObjectClassName("com.t2tierp.ordemservico.java.OsAberturaEquipamentoVO");

        codLookupColumn2.setColumnName("osEquipamento.nome");
        codLookupColumn2.setEditableOnEdit(true);
        codLookupColumn2.setEditableOnInsert(true);
        codLookupColumn2.setHeaderColumnName("Equipamento");
        codLookupColumn2.setHeaderFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        codLookupColumn2.setPreferredWidth(300);
        gridControlEquipamento.getColumnContainer().add(codLookupColumn2);

        textColumn4.setColumnName("numeroSerie");
        textColumn4.setEditableOnEdit(true);
        textColumn4.setEditableOnInsert(true);
        textColumn4.setHeaderColumnName("Numero Serie");
        textColumn4.setHeaderFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        textColumn4.setMaxCharacters(50);
        textColumn4.setPreferredWidth(200);
        gridControlEquipamento.getColumnContainer().add(textColumn4);

        comboColumn2.setColumnName("tipoCobertura");
        comboColumn2.setDomainId("osTipoCobertura");
        comboColumn2.setEditableOnEdit(true);
        comboColumn2.setEditableOnInsert(true);
        comboColumn2.setHeaderColumnName("Tipo Cobertura");
        comboColumn2.setHeaderFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        gridControlEquipamento.getColumnContainer().add(comboColumn2);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel4.add(gridControlEquipamento, gridBagConstraints);

        jTabbedPane1.addTab("Equipamentos", jPanel4);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weighty = 1.0;
        getContentPane().add(jTabbedPane1, gridBagConstraints);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void genericButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_genericButton1ActionPerformed
        try {
            controller.geraOrcamento();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro do Sistema", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_genericButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private org.openswing.swing.table.columns.client.CodLookupColumn codLookupColumn1;
    private org.openswing.swing.table.columns.client.CodLookupColumn codLookupColumn2;
    private org.openswing.swing.client.CodLookupControl codLookupControl2;
    private org.openswing.swing.client.CodLookupControl codLookupControl3;
    private org.openswing.swing.client.CodLookupControl codLookupControl4;
    private org.openswing.swing.table.columns.client.ComboColumn comboColumn1;
    private org.openswing.swing.table.columns.client.ComboColumn comboColumn2;
    private org.openswing.swing.table.columns.client.ComboColumn comboColumn6;
    private org.openswing.swing.table.columns.client.DateColumn dateColumn3;
    private org.openswing.swing.client.DateControl dateControl10;
    private org.openswing.swing.client.DateControl dateControl6;
    private org.openswing.swing.client.DateControl dateControl8;
    private org.openswing.swing.table.columns.client.DecimalColumn decimalColumn10;
    private org.openswing.swing.table.columns.client.DecimalColumn decimalColumn11;
    private org.openswing.swing.table.columns.client.DecimalColumn decimalColumn6;
    private org.openswing.swing.table.columns.client.DecimalColumn decimalColumn7;
    private org.openswing.swing.table.columns.client.DecimalColumn decimalColumn8;
    private org.openswing.swing.table.columns.client.DecimalColumn decimalColumn9;
    private org.openswing.swing.client.DeleteButton deleteButton1;
    private org.openswing.swing.client.DeleteButton deleteButton2;
    private org.openswing.swing.client.DeleteButton deleteButton3;
    private org.openswing.swing.client.EditButton editButton1;
    private org.openswing.swing.client.EditButton editButton2;
    private org.openswing.swing.client.EditButton editButton3;
    private org.openswing.swing.client.EditButton editButton4;
    private org.openswing.swing.form.client.Form form1;
    private org.openswing.swing.table.columns.client.FormattedTextColumn formattedTextColumn1;
    private org.openswing.swing.client.FormattedTextControl formattedTextControl1;
    private org.openswing.swing.client.FormattedTextControl formattedTextControl2;
    private org.openswing.swing.client.GenericButton genericButton1;
    private org.openswing.swing.client.GridControl gridControlEquipamento;
    private org.openswing.swing.client.GridControl gridControlEvolucao;
    private org.openswing.swing.client.GridControl gridControlProdutoServico;
    private org.openswing.swing.client.InsertButton insertButton1;
    private org.openswing.swing.client.InsertButton insertButton2;
    private org.openswing.swing.client.InsertButton insertButton3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
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
    private org.openswing.swing.client.LabelControl labelControl3;
    private org.openswing.swing.client.LabelControl labelControl5;
    private org.openswing.swing.client.LabelControl labelControl7;
    private org.openswing.swing.client.LabelControl labelControl8;
    private org.openswing.swing.client.LabelControl labelControl9;
    private org.openswing.swing.client.NavigatorBar navigatorBar1;
    private org.openswing.swing.client.NavigatorBar navigatorBar2;
    private org.openswing.swing.client.NavigatorBar navigatorBar3;
    private org.openswing.swing.client.ReloadButton reloadButton1;
    private org.openswing.swing.client.ReloadButton reloadButton2;
    private org.openswing.swing.client.ReloadButton reloadButton3;
    private org.openswing.swing.client.ReloadButton reloadButton4;
    private org.openswing.swing.client.SaveButton saveButton1;
    private org.openswing.swing.client.SaveButton saveButton2;
    private org.openswing.swing.client.SaveButton saveButton3;
    private org.openswing.swing.client.SaveButton saveButton4;
    private org.openswing.swing.client.TextAreaControl textAreaControl1;
    private org.openswing.swing.client.TextAreaControl textAreaControl2;
    private org.openswing.swing.table.columns.client.TextColumn textColumn4;
    private org.openswing.swing.table.columns.client.TextColumn textColumn5;
    private org.openswing.swing.table.columns.client.TextColumn textColumn6;
    private org.openswing.swing.client.TextControl textControl11;
    private org.openswing.swing.client.TextControl textControl12;
    private org.openswing.swing.client.TextControl textControl13;
    private org.openswing.swing.client.TextControl textControl5;
    // End of variables declaration//GEN-END:variables

    public OsEvolucaoGridController getEvolucaoGridController() {
        return evolucaoGridController;
    }

    public OsProdutoServicoGridController getProdutoServicoGridController() {
        return produtoServicoGridController;
    }

    public OsAberturaEquipamentoGridController getAberturaEquipamentoGridController() {
        return aberturaEquipamentoGridController;
    }

    public org.openswing.swing.client.GridControl getGridControlEquipamento() {
        return gridControlEquipamento;
    }

    public org.openswing.swing.client.GridControl getGridControlEvolucao() {
        return gridControlEvolucao;
    }

    public org.openswing.swing.client.GridControl getGridControlProdutoServico() {
        return gridControlProdutoServico;
    }

}
