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
package com.t2tierp.contratos.cliente;

import com.t2tierp.padrao.cliente.LookupDataLocatorGenerico;
import java.awt.Dimension;
import org.openswing.swing.lookup.client.LookupController;
import org.openswing.swing.mdi.client.InternalFrame;

public class ContratoSolicitacaoServicoDetalhe extends InternalFrame {

    private LookupController fornecedorController = new LookupController();
    private LookupController clienteController = new LookupController();
    private LookupController colaboradorController = new LookupController();
    private LookupController setorController = new LookupController();
    private LookupController tipoServicoController = new LookupController();

    public ContratoSolicitacaoServicoDetalhe(ContratoSolicitacaoServicoDetalheController controller) {
        initComponents();

        form1.setFormController(controller);

        panelFornecedor.setVisible(false);

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
        codLookupControl2.setLookupController(fornecedorController);

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
        codLookupControl3.setLookupController(clienteController);

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
        codLookupControl5.setLookupController(colaboradorController);

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
        codLookupControl4.setLookupController(setorController);

        /*
         * Configurações do lookup do tipo de servico
         */
        tipoServicoController.setLookupValueObjectClassName("com.t2tierp.contratos.java.ContratoTipoServicoVO");
        tipoServicoController.addLookup2ParentLink("id", "contratoTipoServico.id");
        tipoServicoController.addLookup2ParentLink("nome", "contratoTipoServico.nome");
        tipoServicoController.setHeaderColumnName("id", "ID");
        tipoServicoController.setHeaderColumnName("nome", "Nome");
        tipoServicoController.setFrameTitle("Importa Tipo de Serviço");

        tipoServicoController.setVisibleStatusPanel(true);
        tipoServicoController.setVisibleColumn("id", true);
        tipoServicoController.setVisibleColumn("nome", true);
        tipoServicoController.setFramePreferedSize(new Dimension(600, 500));

        tipoServicoController.setLookupDataLocator(new LookupDataLocatorGenerico(tipoServicoController.getLookupValueObjectClassName()));
        codLookupControl6.setLookupController(tipoServicoController);
    }

    /**
     * @return the form1
     */
    public org.openswing.swing.form.client.Form getForm1() {
        return form1;
    }

    public String getClienteFornecedor() {
        if (rbServicoContratado.isSelected()) {
            return "FORNECEDOR";
        } else {
            return "CLIENTE";
        }
    }

    public void setClienteFornecedor(String clienteFornecedor) {
        if (clienteFornecedor.equals("FORNECEDOR")) {
            rbServicoContratado.setSelected(true);
            panelCliente.setVisible(false);
            panelFornecedor.setVisible(true);
        }
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

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        editButton1 = new org.openswing.swing.client.EditButton();
        reloadButton1 = new org.openswing.swing.client.ReloadButton();
        saveButton1 = new org.openswing.swing.client.SaveButton();
        form1 = new org.openswing.swing.form.client.Form();
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
        dateControl7 = new org.openswing.swing.client.DateControl();
        labelControl12 = new org.openswing.swing.client.LabelControl();
        dateControl8 = new org.openswing.swing.client.DateControl();
        labelControl13 = new org.openswing.swing.client.LabelControl();
        comboBoxControl9 = new org.openswing.swing.client.ComboBoxControl();
        labelControl14 = new org.openswing.swing.client.LabelControl();
        comboBoxControl10 = new org.openswing.swing.client.ComboBoxControl();
        labelControl15 = new org.openswing.swing.client.LabelControl();
        textControl11 = new org.openswing.swing.client.TextControl();
        jPanel2 = new javax.swing.JPanel();
        rbServicoPrestado = new javax.swing.JRadioButton();
        rbServicoContratado = new javax.swing.JRadioButton();
        panelFornecedor = new javax.swing.JPanel();
        labelControl1 = new org.openswing.swing.client.LabelControl();
        codLookupControl2 = new org.openswing.swing.client.CodLookupControl();
        textControl2 = new org.openswing.swing.client.TextControl();
        panelCliente = new javax.swing.JPanel();
        labelControl3 = new org.openswing.swing.client.LabelControl();
        codLookupControl3 = new org.openswing.swing.client.CodLookupControl();
        textControl3 = new org.openswing.swing.client.TextControl();
        jSeparator1 = new javax.swing.JSeparator();

        setTitle("T2Ti ERP - Gestão de Contratos");
        setPreferredSize(new java.awt.Dimension(700, 400));
        getContentPane().setLayout(new java.awt.GridBagLayout());

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Contrato Solicitacao Servico"));
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

        form1.setVOClassName("com.t2tierp.contratos.java.ContratoSolicitacaoServicoVO");
        form1.setEditButton(editButton1);
        form1.setFunctionId("contratoSolicitacaoServico");
        form1.setReloadButton(reloadButton1);
        form1.setSaveButton(saveButton1);
        form1.setLayout(new java.awt.GridBagLayout());

        labelControl5.setText("Setor:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        form1.add(labelControl5, gridBagConstraints);

        codLookupControl4.setAllowOnlyNumbers(true);
        codLookupControl4.setAttributeName("setor.id");
        codLookupControl4.setEnabled(false);
        codLookupControl4.setLinkLabel(labelControl15);
        codLookupControl4.setRequired(true);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 9;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = -50;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        form1.add(codLookupControl4, gridBagConstraints);

        textControl4.setAttributeName("setor.nome");
        textControl4.setEnabled(false);
        textControl4.setEnabledOnEdit(false);
        textControl4.setEnabledOnInsert(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 9;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        form1.add(textControl4, gridBagConstraints);

        labelControl7.setText("Colaborador:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        form1.add(labelControl7, gridBagConstraints);

        codLookupControl5.setAllowOnlyNumbers(true);
        codLookupControl5.setAttributeName("colaborador.id");
        codLookupControl5.setEnabled(false);
        codLookupControl5.setLinkLabel(labelControl7);
        codLookupControl5.setRequired(true);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = -50;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        form1.add(codLookupControl5, gridBagConstraints);

        textControl5.setAttributeName("colaborador.pessoa.nome");
        textControl5.setEnabled(false);
        textControl5.setEnabledOnEdit(false);
        textControl5.setEnabledOnInsert(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        form1.add(textControl5, gridBagConstraints);

        labelControl9.setText("Tipo Servico:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        form1.add(labelControl9, gridBagConstraints);

        codLookupControl6.setAllowOnlyNumbers(true);
        codLookupControl6.setAttributeName("contratoTipoServico.id");
        codLookupControl6.setEnabled(false);
        codLookupControl6.setLinkLabel(labelControl9);
        codLookupControl6.setRequired(true);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 11;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = -50;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        form1.add(codLookupControl6, gridBagConstraints);

        textControl6.setAttributeName("contratoTipoServico.nome");
        textControl6.setEnabled(false);
        textControl6.setEnabledOnEdit(false);
        textControl6.setEnabledOnInsert(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 11;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        form1.add(textControl6, gridBagConstraints);

        labelControl11.setLabel("Data Solicitacao:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 14;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        form1.add(labelControl11, gridBagConstraints);

        dateControl7.setAttributeName("dataSolicitacao");
        dateControl7.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 15;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        form1.add(dateControl7, gridBagConstraints);

        labelControl12.setLabel("Data Desejada Inicio:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 14;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        form1.add(labelControl12, gridBagConstraints);

        dateControl8.setAttributeName("dataDesejadaInicio");
        dateControl8.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 15;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        form1.add(dateControl8, gridBagConstraints);

        labelControl13.setLabel("Urgente:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 14;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        form1.add(labelControl13, gridBagConstraints);

        comboBoxControl9.setAttributeName("urgente");
        comboBoxControl9.setDomainId("simnao");
        comboBoxControl9.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 15;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        form1.add(comboBoxControl9, gridBagConstraints);

        labelControl14.setLabel("Status Solicitacao:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 14;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        form1.add(labelControl14, gridBagConstraints);

        comboBoxControl10.setAttributeName("statusSolicitacao");
        comboBoxControl10.setDomainId("contratoStatusSolicitacaoServico");
        comboBoxControl10.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 15;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        form1.add(comboBoxControl10, gridBagConstraints);

        labelControl15.setLabel("Descricao:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 12;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        form1.add(labelControl15, gridBagConstraints);

        textControl11.setAttributeName("descricao");
        textControl11.setEnabled(false);
        textControl11.setLinkLabel(labelControl15);
        textControl11.setMaxCharacters(100);
        textControl11.setRequired(true);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 13;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        form1.add(textControl11, gridBagConstraints);

        jPanel2.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        buttonGroup1.add(rbServicoPrestado);
        rbServicoPrestado.setSelected(true);
        rbServicoPrestado.setText("Servico Prestado");
        rbServicoPrestado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbServicoPrestadoActionPerformed(evt);
            }
        });
        jPanel2.add(rbServicoPrestado);

        buttonGroup1.add(rbServicoContratado);
        rbServicoContratado.setText("Servico Contratado");
        rbServicoContratado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbServicoContratadoActionPerformed(evt);
            }
        });
        jPanel2.add(rbServicoContratado);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        form1.add(jPanel2, gridBagConstraints);

        panelFornecedor.setLayout(new java.awt.GridBagLayout());

        labelControl1.setText("Fornecedor:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        panelFornecedor.add(labelControl1, gridBagConstraints);

        codLookupControl2.setAllowOnlyNumbers(true);
        codLookupControl2.setAttributeName("fornecedor.id");
        codLookupControl2.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = -50;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        panelFornecedor.add(codLookupControl2, gridBagConstraints);

        textControl2.setAttributeName("fornecedor.pessoa.nome");
        textControl2.setEnabled(false);
        textControl2.setEnabledOnEdit(false);
        textControl2.setEnabledOnInsert(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        panelFornecedor.add(textControl2, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        form1.add(panelFornecedor, gridBagConstraints);

        panelCliente.setLayout(new java.awt.GridBagLayout());

        labelControl3.setText("Cliente:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        panelCliente.add(labelControl3, gridBagConstraints);

        codLookupControl3.setAllowOnlyNumbers(true);
        codLookupControl3.setAttributeName("cliente.id");
        codLookupControl3.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = -50;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        panelCliente.add(codLookupControl3, gridBagConstraints);

        textControl3.setAttributeName("cliente.pessoa.nome");
        textControl3.setEnabled(false);
        textControl3.setEnabledOnEdit(false);
        textControl3.setEnabledOnInsert(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        panelCliente.add(textControl3, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        form1.add(panelCliente, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 16;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        form1.add(jSeparator1, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        getContentPane().add(form1, gridBagConstraints);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void rbServicoPrestadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbServicoPrestadoActionPerformed
        panelCliente.setVisible(true);
        panelFornecedor.setVisible(false);
    }//GEN-LAST:event_rbServicoPrestadoActionPerformed

    private void rbServicoContratadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbServicoContratadoActionPerformed
        panelCliente.setVisible(false);
        panelFornecedor.setVisible(true);
    }//GEN-LAST:event_rbServicoContratadoActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private org.openswing.swing.client.CodLookupControl codLookupControl2;
    private org.openswing.swing.client.CodLookupControl codLookupControl3;
    private org.openswing.swing.client.CodLookupControl codLookupControl4;
    private org.openswing.swing.client.CodLookupControl codLookupControl5;
    private org.openswing.swing.client.CodLookupControl codLookupControl6;
    private org.openswing.swing.client.ComboBoxControl comboBoxControl10;
    private org.openswing.swing.client.ComboBoxControl comboBoxControl9;
    private org.openswing.swing.client.DateControl dateControl7;
    private org.openswing.swing.client.DateControl dateControl8;
    private org.openswing.swing.client.EditButton editButton1;
    private org.openswing.swing.form.client.Form form1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JSeparator jSeparator1;
    private org.openswing.swing.client.LabelControl labelControl1;
    private org.openswing.swing.client.LabelControl labelControl11;
    private org.openswing.swing.client.LabelControl labelControl12;
    private org.openswing.swing.client.LabelControl labelControl13;
    private org.openswing.swing.client.LabelControl labelControl14;
    private org.openswing.swing.client.LabelControl labelControl15;
    private org.openswing.swing.client.LabelControl labelControl3;
    private org.openswing.swing.client.LabelControl labelControl5;
    private org.openswing.swing.client.LabelControl labelControl7;
    private org.openswing.swing.client.LabelControl labelControl9;
    private javax.swing.JPanel panelCliente;
    private javax.swing.JPanel panelFornecedor;
    private javax.swing.JRadioButton rbServicoContratado;
    private javax.swing.JRadioButton rbServicoPrestado;
    private org.openswing.swing.client.ReloadButton reloadButton1;
    private org.openswing.swing.client.SaveButton saveButton1;
    private org.openswing.swing.client.TextControl textControl11;
    private org.openswing.swing.client.TextControl textControl2;
    private org.openswing.swing.client.TextControl textControl3;
    private org.openswing.swing.client.TextControl textControl4;
    private org.openswing.swing.client.TextControl textControl5;
    private org.openswing.swing.client.TextControl textControl6;
    // End of variables declaration//GEN-END:variables
}
