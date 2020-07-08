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
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import org.openswing.swing.client.GenericButton;
import org.openswing.swing.client.GridControl;
import org.openswing.swing.lookup.client.LookupController;
import org.openswing.swing.mdi.client.InternalFrame;
import org.openswing.swing.util.client.ClientUtils;

public class ContratoDetalhe extends InternalFrame {

    private ContratoDetalheController controller;
    private ContratoHistFaturamentoGridController historicoFaturamentoController;
    private ContratoHistoricoReajusteGridController historicoReajusteController;
    private ContratoPrevFaturamentoGridController previsaoFaturamentoController;
    private LookupController solicitacaoServicoController = new LookupController();
    private LookupController tipoContratoController = new LookupController();
    private LookupController contaContabilController = new LookupController();
    private LookupController templateController = new LookupController();

    public ContratoDetalhe(ContratoDetalheController controller) {
        initComponents();

        this.controller = controller;
        form1.setFormController(controller);

        genericButton1.setToolTipText("Gerar/Editar Contrato");

        historicoFaturamentoController = new ContratoHistFaturamentoGridController(this);
        gridControl1.setController(historicoFaturamentoController);
        gridControl1.setGridDataLocator(historicoFaturamentoController);

        historicoReajusteController = new ContratoHistoricoReajusteGridController(this);
        gridControl2.setController(historicoReajusteController);
        gridControl2.setGridDataLocator(historicoReajusteController);

        previsaoFaturamentoController = new ContratoPrevFaturamentoGridController(this);
        gridControl3.setController(previsaoFaturamentoController);
        gridControl3.setGridDataLocator(previsaoFaturamentoController);

        /*
         * Configurações do lookup da solicitacao de servico
         */
        solicitacaoServicoController.setLookupValueObjectClassName("com.t2tierp.contratos.java.ContratoSolicitacaoServicoVO");
        solicitacaoServicoController.addLookup2ParentLink("id", "contratoSolicitacaoServico.id");
        solicitacaoServicoController.addLookup2ParentLink("descricao", "contratoSolicitacaoServico.descricao");
        solicitacaoServicoController.addLookup2ParentLink("cliente", "contratoSolicitacaoServico.cliente");
        solicitacaoServicoController.setHeaderColumnName("id", "ID");
        solicitacaoServicoController.setHeaderColumnName("descricao", "Descrição");
        solicitacaoServicoController.setHeaderColumnName("fornecedor.pessoa.nome", "Fornecedor");
        solicitacaoServicoController.setHeaderColumnName("cliente.pessoa.nome", "Cliente");
        solicitacaoServicoController.setHeaderColumnName("dataSolicitacao", "Data Solicitação");
        solicitacaoServicoController.setHeaderColumnName("dataDesejadaInicio", "Data Desejada Inicio");
        solicitacaoServicoController.setHeaderColumnName("urgente", "Urgente");
        solicitacaoServicoController.setDomainColumn("urgente", "simnao");
        solicitacaoServicoController.setHeaderColumnName("statusSolicitacao", "Status");
        solicitacaoServicoController.setDomainColumn("statusSolicitacao", "contratoStatusSolicitacaoServico");
        solicitacaoServicoController.setFrameTitle("Importa Solicitação de Serviço");

        solicitacaoServicoController.setVisibleStatusPanel(true);
        solicitacaoServicoController.setVisibleColumn("id", true);
        solicitacaoServicoController.setVisibleColumn("descricao", true);
        solicitacaoServicoController.setVisibleColumn("fornecedor.pessoa.nome", true);
        solicitacaoServicoController.setVisibleColumn("cliente.pessoa.nome", true);
        solicitacaoServicoController.setVisibleColumn("dataSolicitacao", true);
        solicitacaoServicoController.setVisibleColumn("dataDesejadaInicio", true);
        solicitacaoServicoController.setVisibleColumn("urgente", true);
        solicitacaoServicoController.setVisibleColumn("urgente", true);
        solicitacaoServicoController.setVisibleColumn("statusSolicitacao", true);
        solicitacaoServicoController.setFramePreferedSize(new Dimension(500, 400));

        solicitacaoServicoController.setLookupDataLocator(new LookupDataLocatorGenerico(solicitacaoServicoController.getLookupValueObjectClassName()));
        codLookupControl2.setLookupController(solicitacaoServicoController);

        /*
         * Configurações do lookup do tipo de contrato
         */
        tipoContratoController.setLookupValueObjectClassName("com.t2tierp.contratos.java.TipoContratoVO");
        tipoContratoController.addLookup2ParentLink("id", "tipoContrato.id");
        tipoContratoController.addLookup2ParentLink("nome", "tipoContrato.nome");
        tipoContratoController.setHeaderColumnName("id", "ID");
        tipoContratoController.setHeaderColumnName("nome", "Nome");
        tipoContratoController.setFrameTitle("Importa Tipo Contrato");

        tipoContratoController.setVisibleStatusPanel(true);
        tipoContratoController.setVisibleColumn("id", true);
        tipoContratoController.setVisibleColumn("nome", true);
        tipoContratoController.setFramePreferedSize(new Dimension(500, 400));

        tipoContratoController.setLookupDataLocator(new LookupDataLocatorGenerico(tipoContratoController.getLookupValueObjectClassName()));
        codLookupControl4.setLookupController(tipoContratoController);

        /*
         * Configurações do lookup da conta contabil
         */
        contaContabilController.setLookupValueObjectClassName("com.t2tierp.contabilidade.java.ContabilContaVO");
        contaContabilController.addLookup2ParentLink("id", "contabilConta.id");
        contaContabilController.addLookup2ParentLink("descricao", "contabilConta.descricao");
        contaContabilController.addLookup2ParentLink("classificacao", "contabilConta.classificacao");
        contaContabilController.setHeaderColumnName("id", "ID");
        contaContabilController.setHeaderColumnName("descricao", "Descrição");
        contaContabilController.setFrameTitle("Importa Conta Contábil");

        contaContabilController.setVisibleStatusPanel(true);
        contaContabilController.setVisibleColumn("id", true);
        contaContabilController.setVisibleColumn("descricao", true);
        contaContabilController.setFramePreferedSize(new Dimension(500, 400));

        contaContabilController.setLookupDataLocator(new LookupDataLocatorGenerico(contaContabilController.getLookupValueObjectClassName()));
        codLookupControl3.setLookupController(contaContabilController);

        /*
         * Configurações do lookup do template
         */
        templateController.setLookupValueObjectClassName("com.t2tierp.contratos.java.ContratoTemplateVO");
        templateController.addLookup2ParentLink("id", "contratoTemplate.id");
        templateController.addLookup2ParentLink("nome", "contratoTemplate.nome");
        templateController.addLookup2ParentLink("descricao", "contratoTemplate.descricao");
        templateController.setHeaderColumnName("id", "ID");
        templateController.setHeaderColumnName("nome", "Nome");
        templateController.setHeaderColumnName("descricao", "Descrição");
        templateController.setFrameTitle("Importa Template");

        templateController.setVisibleStatusPanel(true);
        templateController.setVisibleColumn("id", true);
        templateController.setVisibleColumn("nome", true);
        templateController.setVisibleColumn("descricao", true);
        templateController.setFramePreferedSize(new Dimension(500, 400));

        templateController.setLookupDataLocator(new LookupDataLocatorGenerico(templateController.getLookupValueObjectClassName()));
        codLookupControl1.setLookupController(templateController);
    }

    /**
     * @return the form1
     */
    public org.openswing.swing.form.client.Form getForm1() {
        return form1;
    }

    public GridControl getGridHistoricoFaturamento() {
        return gridControl1;
    }

    public GridControl getGridHistoricoReajuste() {
        return gridControl2;
    }

    public GridControl getGridPrevisaoFaturamento() {
        return gridControl3;
    }

    /**
     * @return the historicoFaturamentoController
     */
    public ContratoHistFaturamentoGridController getHistoricoFaturamentoController() {
        return historicoFaturamentoController;
    }

    /**
     * @return the historicoReajusteController
     */
    public ContratoHistoricoReajusteGridController getHistoricoReajusteController() {
        return historicoReajusteController;
    }

    /**
     * @return the previsaoFaturamentoController
     */
    public ContratoPrevFaturamentoGridController getPrevisaoFaturamentoController() {
        return previsaoFaturamentoController;
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
        genericButton1 = new GenericButton(new ImageIcon(ClientUtils.getImage("contrato.jpg")));
        form1 = new org.openswing.swing.form.client.Form();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        labelControl1 = new org.openswing.swing.client.LabelControl();
        codLookupControl2 = new org.openswing.swing.client.CodLookupControl();
        textControl2 = new org.openswing.swing.client.TextControl();
        labelControl3 = new org.openswing.swing.client.LabelControl();
        codLookupControl3 = new org.openswing.swing.client.CodLookupControl();
        textControl3 = new org.openswing.swing.client.TextControl();
        codLookupControl4 = new org.openswing.swing.client.CodLookupControl();
        labelControl5 = new org.openswing.swing.client.LabelControl();
        textControl4 = new org.openswing.swing.client.TextControl();
        textControl5 = new org.openswing.swing.client.TextControl();
        textControl6 = new org.openswing.swing.client.TextControl();
        labelControl7 = new org.openswing.swing.client.LabelControl();
        labelControl8 = new org.openswing.swing.client.LabelControl();
        jPanel3 = new javax.swing.JPanel();
        labelControl9 = new org.openswing.swing.client.LabelControl();
        dateControl8 = new org.openswing.swing.client.DateControl();
        labelControl10 = new org.openswing.swing.client.LabelControl();
        dateControl9 = new org.openswing.swing.client.DateControl();
        labelControl11 = new org.openswing.swing.client.LabelControl();
        dateControl10 = new org.openswing.swing.client.DateControl();
        labelControl12 = new org.openswing.swing.client.LabelControl();
        labelControl17 = new org.openswing.swing.client.LabelControl();
        numericControl14 = new org.openswing.swing.client.NumericControl();
        labelControl16 = new org.openswing.swing.client.LabelControl();
        numericControl13 = new org.openswing.swing.client.NumericControl();
        labelControl15 = new org.openswing.swing.client.LabelControl();
        numericControl12 = new org.openswing.swing.client.NumericControl();
        labelControl14 = new org.openswing.swing.client.LabelControl();
        labelControl13 = new org.openswing.swing.client.LabelControl();
        textControl11 = new org.openswing.swing.client.TextControl();
        textAreaControl1 = new org.openswing.swing.client.TextAreaControl();
        textAreaControl2 = new org.openswing.swing.client.TextAreaControl();
        codLookupControl1 = new org.openswing.swing.client.CodLookupControl();
        labelControl18 = new org.openswing.swing.client.LabelControl();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jPanel4 = new javax.swing.JPanel();
        gridControl1 = new org.openswing.swing.client.GridControl();
        dateColumn3 = new org.openswing.swing.table.columns.client.DateColumn();
        decimalColumn4 = new org.openswing.swing.table.columns.client.DecimalColumn();
        jPanel5 = new javax.swing.JPanel();
        insertButtonHistFaturamento = new org.openswing.swing.client.InsertButton();
        editButtonHistFaturamento = new org.openswing.swing.client.EditButton();
        deleteButtonHistFaturamento = new org.openswing.swing.client.DeleteButton();
        saveButtonHistFaturamento = new org.openswing.swing.client.SaveButton();
        reloadButtonHistFaturamento = new org.openswing.swing.client.ReloadButton();
        navigatorBarHistFaturamento = new org.openswing.swing.client.NavigatorBar();
        jPanel6 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        insertButtonHistReajuste = new org.openswing.swing.client.InsertButton();
        editButtonHistReajuste = new org.openswing.swing.client.EditButton();
        deleteButtonHistReajuste = new org.openswing.swing.client.DeleteButton();
        saveButtonHistReajuste = new org.openswing.swing.client.SaveButton();
        reloadButtonHistReajuste = new org.openswing.swing.client.ReloadButton();
        navigatorBarHistReajuste = new org.openswing.swing.client.NavigatorBar();
        gridControl2 = new org.openswing.swing.client.GridControl();
        decimalColumn3 = new org.openswing.swing.table.columns.client.DecimalColumn();
        decimalColumn5 = new org.openswing.swing.table.columns.client.DecimalColumn();
        decimalColumn6 = new org.openswing.swing.table.columns.client.DecimalColumn();
        dateColumn6 = new org.openswing.swing.table.columns.client.DateColumn();
        textColumn7 = new org.openswing.swing.table.columns.client.TextColumn();
        jPanel8 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        insertButtonPrevFaturamento = new org.openswing.swing.client.InsertButton();
        editButtonPrevFaturamento = new org.openswing.swing.client.EditButton();
        deleteButtonPrevFaturamento = new org.openswing.swing.client.DeleteButton();
        saveButtonPrevFaturamento = new org.openswing.swing.client.SaveButton();
        reloadButtonPrevFaturamento = new org.openswing.swing.client.ReloadButton();
        navigatorBarPrevFaturamento = new org.openswing.swing.client.NavigatorBar();
        gridControl3 = new org.openswing.swing.client.GridControl();
        dateColumn4 = new org.openswing.swing.table.columns.client.DateColumn();
        decimalColumn7 = new org.openswing.swing.table.columns.client.DecimalColumn();

        setTitle("T2Ti ERP - Contratos");
        setPreferredSize(new java.awt.Dimension(700, 400));
        getContentPane().setLayout(new java.awt.GridBagLayout());

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Contrato"));
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

        form1.setVOClassName("com.t2tierp.contratos.java.ContratoVO");
        form1.setEditButton(editButton1);
        form1.setFunctionId("contrato");
        form1.setReloadButton(reloadButton1);
        form1.setSaveButton(saveButton1);
        form1.setLayout(new java.awt.GridBagLayout());

        jPanel2.setLayout(new java.awt.GridBagLayout());

        labelControl1.setText("Solicitacao Servico:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        jPanel2.add(labelControl1, gridBagConstraints);

        codLookupControl2.setAllowOnlyNumbers(true);
        codLookupControl2.setAttributeName("contratoSolicitacaoServico.id");
        codLookupControl2.setEnabled(false);
        codLookupControl2.setLinkLabel(labelControl1);
        codLookupControl2.setRequired(true);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        jPanel2.add(codLookupControl2, gridBagConstraints);

        textControl2.setAttributeName("contratoSolicitacaoServico.descricao");
        textControl2.setEnabled(false);
        textControl2.setEnabledOnEdit(false);
        textControl2.setEnabledOnInsert(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        jPanel2.add(textControl2, gridBagConstraints);

        labelControl3.setText("Conta Contabil:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        jPanel2.add(labelControl3, gridBagConstraints);

        codLookupControl3.setAllowOnlyNumbers(true);
        codLookupControl3.setAttributeName("contabilConta.id");
        codLookupControl3.setEnabled(false);
        codLookupControl3.setLinkLabel(labelControl3);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        jPanel2.add(codLookupControl3, gridBagConstraints);

        textControl3.setAttributeName("classificacaoContabilConta");
        textControl3.setEnabled(false);
        textControl3.setEnabledOnEdit(false);
        textControl3.setEnabledOnInsert(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        jPanel2.add(textControl3, gridBagConstraints);

        codLookupControl4.setAllowOnlyNumbers(true);
        codLookupControl4.setAttributeName("tipoContrato.id");
        codLookupControl4.setEnabled(false);
        codLookupControl4.setLinkLabel(labelControl5);
        codLookupControl4.setRequired(true);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        jPanel2.add(codLookupControl4, gridBagConstraints);

        labelControl5.setText("Tipo de Contrato:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        jPanel2.add(labelControl5, gridBagConstraints);

        textControl4.setAttributeName("tipoContrato.nome");
        textControl4.setEnabled(false);
        textControl4.setEnabledOnEdit(false);
        textControl4.setEnabledOnInsert(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        jPanel2.add(textControl4, gridBagConstraints);

        textControl5.setAttributeName("numero");
        textControl5.setEnabled(false);
        textControl5.setLinkLabel(labelControl8);
        textControl5.setMaxCharacters(50);
        textControl5.setRequired(true);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        jPanel2.add(textControl5, gridBagConstraints);

        textControl6.setAttributeName("nome");
        textControl6.setEnabled(false);
        textControl6.setLinkLabel(labelControl7);
        textControl6.setMaxCharacters(100);
        textControl6.setRequired(true);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        jPanel2.add(textControl6, gridBagConstraints);

        labelControl7.setText("Nome:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        jPanel2.add(labelControl7, gridBagConstraints);

        labelControl8.setLabel("Numero:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        jPanel2.add(labelControl8, gridBagConstraints);

        jTabbedPane1.addTab("Dados Básicos", jPanel2);

        jPanel3.setLayout(new java.awt.GridBagLayout());

        labelControl9.setLabel("Descricao:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        jPanel3.add(labelControl9, gridBagConstraints);

        dateControl8.setAttributeName("dataCadastro");
        dateControl8.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 30;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        jPanel3.add(dateControl8, gridBagConstraints);

        labelControl10.setLabel("Data Cadastro:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        jPanel3.add(labelControl10, gridBagConstraints);

        dateControl9.setAttributeName("dataInicioVigencia");
        dateControl9.setEnabled(false);
        dateControl9.setLinkLabel(labelControl11);
        dateControl9.setRequired(true);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 30;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        jPanel3.add(dateControl9, gridBagConstraints);

        labelControl11.setText("Inicio Vigencia:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        jPanel3.add(labelControl11, gridBagConstraints);

        dateControl10.setAttributeName("dataFimVigencia");
        dateControl10.setEnabled(false);
        dateControl10.setLinkLabel(labelControl12);
        dateControl10.setRequired(true);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 30;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        jPanel3.add(dateControl10, gridBagConstraints);

        labelControl12.setText("Fim Vigencia:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        jPanel3.add(labelControl12, gridBagConstraints);

        labelControl17.setLabel("Observacao:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        jPanel3.add(labelControl17, gridBagConstraints);

        numericControl14.setAttributeName("intervaloEntreParcelas");
        numericControl14.setEnabled(false);
        numericControl14.setMaxCharacters(10);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        jPanel3.add(numericControl14, gridBagConstraints);

        labelControl16.setLabel("Intervalo Entre Parcelas:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        jPanel3.add(labelControl16, gridBagConstraints);

        numericControl13.setAttributeName("quantidadeParcelas");
        numericControl13.setEnabled(false);
        numericControl13.setMaxCharacters(10);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        jPanel3.add(numericControl13, gridBagConstraints);

        labelControl15.setLabel("Quantidade Parcelas:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        jPanel3.add(labelControl15, gridBagConstraints);

        numericControl12.setAttributeName("valor");
        numericControl12.setDecimals(2);
        numericControl12.setEnabled(false);
        numericControl12.setLinkLabel(labelControl14);
        numericControl12.setRequired(true);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 50;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        jPanel3.add(numericControl12, gridBagConstraints);

        labelControl14.setLabel("Valor:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        jPanel3.add(labelControl14, gridBagConstraints);

        labelControl13.setLabel("Dia Faturamento:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        jPanel3.add(labelControl13, gridBagConstraints);

        textControl11.setAttributeName("diaFaturamento");
        textControl11.setEnabled(false);
        textControl11.setMaxCharacters(10);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        jPanel3.add(textControl11, gridBagConstraints);

        textAreaControl1.setAttributeName("observacao");
        textAreaControl1.setEnabled(false);
        textAreaControl1.setMaxCharacters(1000);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridwidth = 8;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipady = 30;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        jPanel3.add(textAreaControl1, gridBagConstraints);

        textAreaControl2.setAttributeName("descricao");
        textAreaControl2.setEnabled(false);
        textAreaControl2.setMaxCharacters(1000);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 8;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipady = 30;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        jPanel3.add(textAreaControl2, gridBagConstraints);

        codLookupControl1.setAttributeName("contratoTemplate.nome");
        codLookupControl1.setEnableCodBox(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 7;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        jPanel3.add(codLookupControl1, gridBagConstraints);

        labelControl18.setText("Template:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 7;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        jPanel3.add(labelControl18, gridBagConstraints);

        jTabbedPane1.addTab("Dados Complementares", jPanel3);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        form1.add(jTabbedPane1, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        getContentPane().add(form1, gridBagConstraints);

        jPanel4.setLayout(new java.awt.GridBagLayout());

        gridControl1.setAutoLoadData(false);
        gridControl1.setDeleteButton(deleteButtonHistFaturamento);
        gridControl1.setEditButton(editButtonHistFaturamento);
        gridControl1.setFunctionId("contratoHistFaturamento");
        gridControl1.setInsertButton(insertButtonHistFaturamento);
        gridControl1.setNavBar(navigatorBarHistFaturamento);
        gridControl1.setReloadButton(reloadButtonHistFaturamento);
        gridControl1.setSaveButton(saveButtonHistFaturamento);
        gridControl1.setValueObjectClassName("com.t2tierp.contratos.java.ContratoHistFaturamentoVO");
        gridControl1.getColumnContainer().setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        dateColumn3.setColumnName("dataFatura");
        dateColumn3.setEditableOnEdit(true);
        dateColumn3.setEditableOnInsert(true);
        dateColumn3.setHeaderColumnName("Data Fatura");
        dateColumn3.setHeaderFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        gridControl1.getColumnContainer().add(dateColumn3);

        decimalColumn4.setColumnName("valor");
        decimalColumn4.setDecimals(2);
        decimalColumn4.setEditableOnEdit(true);
        decimalColumn4.setEditableOnInsert(true);
        decimalColumn4.setHeaderColumnName("Valor");
        decimalColumn4.setHeaderFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        gridControl1.getColumnContainer().add(decimalColumn4);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel4.add(gridControl1, gridBagConstraints);

        jPanel5.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));
        jPanel5.add(insertButtonHistFaturamento);
        jPanel5.add(editButtonHistFaturamento);
        jPanel5.add(deleteButtonHistFaturamento);
        jPanel5.add(saveButtonHistFaturamento);
        jPanel5.add(reloadButtonHistFaturamento);
        jPanel5.add(navigatorBarHistFaturamento);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        jPanel4.add(jPanel5, gridBagConstraints);

        jTabbedPane2.addTab("Historico Faturamento", jPanel4);

        jPanel6.setLayout(new java.awt.GridBagLayout());

        jPanel7.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));
        jPanel7.add(insertButtonHistReajuste);
        jPanel7.add(editButtonHistReajuste);
        jPanel7.add(deleteButtonHistReajuste);
        jPanel7.add(saveButtonHistReajuste);
        jPanel7.add(reloadButtonHistReajuste);
        jPanel7.add(navigatorBarHistReajuste);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        jPanel6.add(jPanel7, gridBagConstraints);

        gridControl2.setAutoLoadData(false);
        gridControl2.setDeleteButton(deleteButtonHistReajuste);
        gridControl2.setEditButton(editButtonHistReajuste);
        gridControl2.setFunctionId("contratoHistoricoReajuste");
        gridControl2.setInsertButton(insertButtonHistReajuste);
        gridControl2.setNavBar(navigatorBarHistReajuste);
        gridControl2.setReloadButton(reloadButtonHistReajuste);
        gridControl2.setSaveButton(saveButtonHistReajuste);
        gridControl2.setValueObjectClassName("com.t2tierp.contratos.java.ContratoHistoricoReajusteVO");
        gridControl2.getColumnContainer().setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        decimalColumn3.setColumnName("indice");
        decimalColumn3.setDecimals(2);
        decimalColumn3.setEditableOnEdit(true);
        decimalColumn3.setEditableOnInsert(true);
        decimalColumn3.setHeaderColumnName("Indice");
        decimalColumn3.setHeaderFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        gridControl2.getColumnContainer().add(decimalColumn3);

        decimalColumn5.setColumnName("valorAnterior");
        decimalColumn5.setColumnRequired(false);
        decimalColumn5.setDecimals(2);
        decimalColumn5.setEditableOnEdit(true);
        decimalColumn5.setEditableOnInsert(true);
        decimalColumn5.setHeaderColumnName("Valor Anterior");
        decimalColumn5.setHeaderFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        gridControl2.getColumnContainer().add(decimalColumn5);

        decimalColumn6.setColumnName("valorAtual");
        decimalColumn6.setColumnRequired(false);
        decimalColumn6.setDecimals(2);
        decimalColumn6.setEditableOnEdit(true);
        decimalColumn6.setEditableOnInsert(true);
        decimalColumn6.setHeaderColumnName("Valor Atual");
        decimalColumn6.setHeaderFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        gridControl2.getColumnContainer().add(decimalColumn6);

        dateColumn6.setColumnName("dataReajuste");
        dateColumn6.setColumnRequired(false);
        dateColumn6.setEditableOnEdit(true);
        dateColumn6.setEditableOnInsert(true);
        dateColumn6.setHeaderColumnName("Data Reajuste");
        dateColumn6.setHeaderFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        gridControl2.getColumnContainer().add(dateColumn6);

        textColumn7.setColumnName("observacao");
        textColumn7.setColumnRequired(false);
        textColumn7.setEditableOnEdit(true);
        textColumn7.setEditableOnInsert(true);
        textColumn7.setHeaderColumnName("Observacao");
        textColumn7.setHeaderFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        textColumn7.setMaxCharacters(1000);
        textColumn7.setPreferredWidth(500);
        gridControl2.getColumnContainer().add(textColumn7);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel6.add(gridControl2, gridBagConstraints);

        jTabbedPane2.addTab("Historico Reajuste", jPanel6);

        jPanel8.setLayout(new java.awt.GridBagLayout());

        jPanel9.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));
        jPanel9.add(insertButtonPrevFaturamento);
        jPanel9.add(editButtonPrevFaturamento);
        jPanel9.add(deleteButtonPrevFaturamento);
        jPanel9.add(saveButtonPrevFaturamento);
        jPanel9.add(reloadButtonPrevFaturamento);
        jPanel9.add(navigatorBarPrevFaturamento);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        jPanel8.add(jPanel9, gridBagConstraints);

        gridControl3.setAutoLoadData(false);
        gridControl3.setDeleteButton(deleteButtonPrevFaturamento);
        gridControl3.setEditButton(editButtonPrevFaturamento);
        gridControl3.setFunctionId("contratoPrevFaturamento");
        gridControl3.setInsertButton(insertButtonPrevFaturamento);
        gridControl3.setNavBar(navigatorBarPrevFaturamento);
        gridControl3.setReloadButton(reloadButtonPrevFaturamento);
        gridControl3.setSaveButton(saveButtonPrevFaturamento);
        gridControl3.setValueObjectClassName("com.t2tierp.contratos.java.ContratoPrevFaturamentoVO");
        gridControl3.getColumnContainer().setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        dateColumn4.setColumnName("dataPrevista");
        dateColumn4.setEditableOnEdit(true);
        dateColumn4.setEditableOnInsert(true);
        dateColumn4.setHeaderColumnName("Data Prevista");
        dateColumn4.setHeaderFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        gridControl3.getColumnContainer().add(dateColumn4);

        decimalColumn7.setColumnName("valor");
        decimalColumn7.setDecimals(2);
        decimalColumn7.setEditableOnEdit(true);
        decimalColumn7.setEditableOnInsert(true);
        decimalColumn7.setHeaderColumnName("Valor");
        decimalColumn7.setHeaderFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        gridControl3.getColumnContainer().add(decimalColumn7);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel8.add(gridControl3, gridBagConstraints);

        jTabbedPane2.addTab("Previsao de Faturamento", jPanel8);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        getContentPane().add(jTabbedPane2, gridBagConstraints);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void genericButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_genericButton1ActionPerformed
        try {
            controller.geraContrato();
        } catch (Exception e) {
            //e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Ocorreu um erro ao gerar o contrato\n" + e.getMessage(), "Erro do Sistema", JOptionPane.ERROR_MESSAGE);
        }
}//GEN-LAST:event_genericButton1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private org.openswing.swing.client.CodLookupControl codLookupControl1;
    private org.openswing.swing.client.CodLookupControl codLookupControl2;
    private org.openswing.swing.client.CodLookupControl codLookupControl3;
    private org.openswing.swing.client.CodLookupControl codLookupControl4;
    private org.openswing.swing.table.columns.client.DateColumn dateColumn3;
    private org.openswing.swing.table.columns.client.DateColumn dateColumn4;
    private org.openswing.swing.table.columns.client.DateColumn dateColumn6;
    private org.openswing.swing.client.DateControl dateControl10;
    private org.openswing.swing.client.DateControl dateControl8;
    private org.openswing.swing.client.DateControl dateControl9;
    private org.openswing.swing.table.columns.client.DecimalColumn decimalColumn3;
    private org.openswing.swing.table.columns.client.DecimalColumn decimalColumn4;
    private org.openswing.swing.table.columns.client.DecimalColumn decimalColumn5;
    private org.openswing.swing.table.columns.client.DecimalColumn decimalColumn6;
    private org.openswing.swing.table.columns.client.DecimalColumn decimalColumn7;
    private org.openswing.swing.client.DeleteButton deleteButtonHistFaturamento;
    private org.openswing.swing.client.DeleteButton deleteButtonHistReajuste;
    private org.openswing.swing.client.DeleteButton deleteButtonPrevFaturamento;
    private org.openswing.swing.client.EditButton editButton1;
    private org.openswing.swing.client.EditButton editButtonHistFaturamento;
    private org.openswing.swing.client.EditButton editButtonHistReajuste;
    private org.openswing.swing.client.EditButton editButtonPrevFaturamento;
    private org.openswing.swing.form.client.Form form1;
    private org.openswing.swing.client.GenericButton genericButton1;
    private org.openswing.swing.client.GridControl gridControl1;
    private org.openswing.swing.client.GridControl gridControl2;
    private org.openswing.swing.client.GridControl gridControl3;
    private org.openswing.swing.client.InsertButton insertButtonHistFaturamento;
    private org.openswing.swing.client.InsertButton insertButtonHistReajuste;
    private org.openswing.swing.client.InsertButton insertButtonPrevFaturamento;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTabbedPane jTabbedPane2;
    private org.openswing.swing.client.LabelControl labelControl1;
    private org.openswing.swing.client.LabelControl labelControl10;
    private org.openswing.swing.client.LabelControl labelControl11;
    private org.openswing.swing.client.LabelControl labelControl12;
    private org.openswing.swing.client.LabelControl labelControl13;
    private org.openswing.swing.client.LabelControl labelControl14;
    private org.openswing.swing.client.LabelControl labelControl15;
    private org.openswing.swing.client.LabelControl labelControl16;
    private org.openswing.swing.client.LabelControl labelControl17;
    private org.openswing.swing.client.LabelControl labelControl18;
    private org.openswing.swing.client.LabelControl labelControl3;
    private org.openswing.swing.client.LabelControl labelControl5;
    private org.openswing.swing.client.LabelControl labelControl7;
    private org.openswing.swing.client.LabelControl labelControl8;
    private org.openswing.swing.client.LabelControl labelControl9;
    private org.openswing.swing.client.NavigatorBar navigatorBarHistFaturamento;
    private org.openswing.swing.client.NavigatorBar navigatorBarHistReajuste;
    private org.openswing.swing.client.NavigatorBar navigatorBarPrevFaturamento;
    private org.openswing.swing.client.NumericControl numericControl12;
    private org.openswing.swing.client.NumericControl numericControl13;
    private org.openswing.swing.client.NumericControl numericControl14;
    private org.openswing.swing.client.ReloadButton reloadButton1;
    private org.openswing.swing.client.ReloadButton reloadButtonHistFaturamento;
    private org.openswing.swing.client.ReloadButton reloadButtonHistReajuste;
    private org.openswing.swing.client.ReloadButton reloadButtonPrevFaturamento;
    private org.openswing.swing.client.SaveButton saveButton1;
    private org.openswing.swing.client.SaveButton saveButtonHistFaturamento;
    private org.openswing.swing.client.SaveButton saveButtonHistReajuste;
    private org.openswing.swing.client.SaveButton saveButtonPrevFaturamento;
    private org.openswing.swing.client.TextAreaControl textAreaControl1;
    private org.openswing.swing.client.TextAreaControl textAreaControl2;
    private org.openswing.swing.table.columns.client.TextColumn textColumn7;
    private org.openswing.swing.client.TextControl textControl11;
    private org.openswing.swing.client.TextControl textControl2;
    private org.openswing.swing.client.TextControl textControl3;
    private org.openswing.swing.client.TextControl textControl4;
    private org.openswing.swing.client.TextControl textControl5;
    private org.openswing.swing.client.TextControl textControl6;
    // End of variables declaration//GEN-END:variables
}
