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
package com.t2tierp.nfse.cliente;

import com.t2tierp.padrao.cliente.LookupDataLocatorGenerico;
import java.awt.Dimension;
import javax.swing.ImageIcon;
import javax.swing.text.MaskFormatter;
import org.openswing.swing.lookup.client.LookupController;
import org.openswing.swing.mdi.client.InternalFrame;
import org.openswing.swing.util.client.ClientUtils;
import org.openswing.swing.util.java.Consts;

public class NfseCabecalhoDetalhe extends InternalFrame {

    private NfseCabecalhoDetalheController controller;
    private NfseDetalheGridController servicosController;
    private NfseIntermediarioGridController intermediarioController;
    private LookupController osController = new LookupController();
    private LookupController listaServicoController1 = new LookupController();

    public NfseCabecalhoDetalhe(NfseCabecalhoDetalheController controller) {
        initComponents();

        this.controller = controller;
        form1.setFormController(controller);

        servicosController = new NfseDetalheGridController(this);
        gridControlServicos.setController(servicosController);
        gridControlServicos.setGridDataLocator(servicosController);

        intermediarioController = new NfseIntermediarioGridController(this);
        gridControlIntermediario.setController(intermediarioController);
        gridControlIntermediario.setGridDataLocator(intermediarioController);

        genericButton1.setToolTipText("Enviar NFS-e");
        genericButton2.setToolTipText("Consultar Envio NFS-e");
        genericButton3.setToolTipText("Imprimir NFS-e");

        dateControl17.setDateType(Consts.TYPE_DATE_TIME);
        dateControl7.setDateType(Consts.TYPE_DATE_TIME);

        formattedTextControl1.setEnabled(false);
        try {
            MaskFormatter mask = new MaskFormatter("####/##");
            mask.setValidCharacters("0123456789");
            formattedTextControl1.setFormatter(mask);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        /*
         * Configurações do lookup da OS
         */
        osController.setLookupValueObjectClassName("com.t2tierp.ordemservico.java.OsAberturaVO");
        osController.addLookup2ParentLink("id", "osAbertura.id");
        osController.addLookup2ParentLink("numero", "osAbertura.numero");
        osController.addLookup2ParentLink("cliente", "cliente");
        //osController.addLookup2ParentLink("listaOsProdutoServico", "osAbertura.listaOsProdutoServico");
        osController.setHeaderColumnName("id", "ID");
        osController.setHeaderColumnName("numero", "Número");
        osController.setFrameTitle("Importa OS");

        osController.setVisibleStatusPanel(true);
        osController.setVisibleColumn("id", true);
        osController.setVisibleColumn("numero", true);
        osController.setFramePreferedSize(new Dimension(600, 500));

        osController.setLookupDataLocator(new LookupDataLocatorGenerico(osController.getLookupValueObjectClassName()));
        codLookupControl2.setLookupController(osController);

        /*
         * Configurações do lookup da Lista de Serviço
         */
        listaServicoController1.setLookupValueObjectClassName("com.t2tierp.nfse.java.NfseListaServicoVO");
        listaServicoController1.addLookup2ParentLink("id", "nfseListaServico.id");
        listaServicoController1.addLookup2ParentLink("descricao", "nfseListaServico.descricao");
        listaServicoController1.setHeaderColumnName("id", "ID");
        listaServicoController1.setHeaderColumnName("descricao", "Descrição");
        listaServicoController1.setFrameTitle("Importa Lista Serviço");

        listaServicoController1.setVisibleStatusPanel(true);
        listaServicoController1.setVisibleColumn("id", true);
        listaServicoController1.setVisibleColumn("descricao", true);
        listaServicoController1.setFramePreferedSize(new Dimension(600, 500));

        listaServicoController1.setLookupDataLocator(new LookupDataLocatorGenerico(listaServicoController1.getLookupValueObjectClassName()));
        codLookupColumn1.setLookupController(listaServicoController1);

    }

    /**
     * @return the form1
     */
    public org.openswing.swing.form.client.Form getForm1() {
        return form1;
    }

    public NfseDetalheGridController getServicosController() {
        return servicosController;
    }

    public org.openswing.swing.client.GridControl getGridControlServicos() {
        return gridControlServicos;
    }

    public NfseIntermediarioGridController getIntermediarioController() {
        return intermediarioController;
    }

    public org.openswing.swing.client.GridControl getGridControlIntermediario() {
        return gridControlIntermediario;
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
        genericButton1 = new org.openswing.swing.client.GenericButton(new ImageIcon(ClientUtils.getImage("ok.gif")));
        genericButton2 = new org.openswing.swing.client.GenericButton(new ImageIcon(ClientUtils.getImage("detail.gif")));
        genericButton3 = new org.openswing.swing.client.GenericButton(new ImageIcon(ClientUtils.getImage("print.gif")));
        form1 = new org.openswing.swing.form.client.Form();
        labelControl1 = new org.openswing.swing.client.LabelControl();
        codLookupControl2 = new org.openswing.swing.client.CodLookupControl();
        labelControl3 = new org.openswing.swing.client.LabelControl();
        labelControl5 = new org.openswing.swing.client.LabelControl();
        textControl5 = new org.openswing.swing.client.TextControl();
        labelControl6 = new org.openswing.swing.client.LabelControl();
        textControl6 = new org.openswing.swing.client.TextControl();
        labelControl7 = new org.openswing.swing.client.LabelControl();
        dateControl7 = new org.openswing.swing.client.DateControl();
        labelControl8 = new org.openswing.swing.client.LabelControl();
        labelControl9 = new org.openswing.swing.client.LabelControl();
        textControl9 = new org.openswing.swing.client.TextControl();
        labelControl10 = new org.openswing.swing.client.LabelControl();
        labelControl11 = new org.openswing.swing.client.LabelControl();
        labelControl12 = new org.openswing.swing.client.LabelControl();
        comboBoxControl12 = new org.openswing.swing.client.ComboBoxControl();
        labelControl13 = new org.openswing.swing.client.LabelControl();
        comboBoxControl13 = new org.openswing.swing.client.ComboBoxControl();
        labelControl14 = new org.openswing.swing.client.LabelControl();
        textControl14 = new org.openswing.swing.client.TextControl();
        labelControl15 = new org.openswing.swing.client.LabelControl();
        textControl15 = new org.openswing.swing.client.TextControl();
        labelControl16 = new org.openswing.swing.client.LabelControl();
        labelControl17 = new org.openswing.swing.client.LabelControl();
        dateControl17 = new org.openswing.swing.client.DateControl();
        labelControl18 = new org.openswing.swing.client.LabelControl();
        labelControl19 = new org.openswing.swing.client.LabelControl();
        textControl19 = new org.openswing.swing.client.TextControl();
        labelControl20 = new org.openswing.swing.client.LabelControl();
        textControl20 = new org.openswing.swing.client.TextControl();
        comboBoxControl1 = new org.openswing.swing.client.ComboBoxControl();
        comboBoxControl2 = new org.openswing.swing.client.ComboBoxControl();
        comboBoxControl3 = new org.openswing.swing.client.ComboBoxControl();
        formattedTextControl1 = new org.openswing.swing.client.FormattedTextControl();
        textAreaControl1 = new org.openswing.swing.client.TextAreaControl();
        textControl7 = new org.openswing.swing.client.TextControl();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        insertButton1 = new org.openswing.swing.client.InsertButton();
        editButton2 = new org.openswing.swing.client.EditButton();
        deleteButton1 = new org.openswing.swing.client.DeleteButton();
        saveButton2 = new org.openswing.swing.client.SaveButton();
        reloadButton2 = new org.openswing.swing.client.ReloadButton();
        navigatorBar1 = new org.openswing.swing.client.NavigatorBar();
        gridControlServicos = new org.openswing.swing.client.GridControl();
        codLookupColumn1 = new org.openswing.swing.table.columns.client.CodLookupColumn();
        decimalColumn4 = new org.openswing.swing.table.columns.client.DecimalColumn();
        decimalColumn14 = new org.openswing.swing.table.columns.client.DecimalColumn();
        decimalColumn15 = new org.openswing.swing.table.columns.client.DecimalColumn();
        decimalColumn22 = new org.openswing.swing.table.columns.client.DecimalColumn();
        decimalColumn16 = new org.openswing.swing.table.columns.client.DecimalColumn();
        jPanel3 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        insertButton2 = new org.openswing.swing.client.InsertButton();
        editButton3 = new org.openswing.swing.client.EditButton();
        deleteButton2 = new org.openswing.swing.client.DeleteButton();
        saveButton3 = new org.openswing.swing.client.SaveButton();
        reloadButton3 = new org.openswing.swing.client.ReloadButton();
        navigatorBar2 = new org.openswing.swing.client.NavigatorBar();
        gridControlIntermediario = new org.openswing.swing.client.GridControl();
        textColumn5 = new org.openswing.swing.table.columns.client.TextColumn();
        textColumn4 = new org.openswing.swing.table.columns.client.TextColumn();
        textColumn6 = new org.openswing.swing.table.columns.client.TextColumn();

        setTitle("T2Ti ERP - NFS-e");
        setPreferredSize(new java.awt.Dimension(700, 400));
        getContentPane().setLayout(new java.awt.GridBagLayout());

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Nfse Cabecalho"));
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

        form1.setVOClassName("com.t2tierp.nfse.java.NfseCabecalhoVO");
        form1.setEditButton(editButton1);
        form1.setFunctionId("nfseCabecalho");
        form1.setReloadButton(reloadButton1);
        form1.setSaveButton(saveButton1);
        form1.setLayout(new java.awt.GridBagLayout());

        labelControl1.setText("Nr. OS:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        form1.add(labelControl1, gridBagConstraints);

        codLookupControl2.setAttributeName("osAbertura.numero");
        codLookupControl2.setEnableCodBox(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        form1.add(codLookupControl2, gridBagConstraints);

        labelControl3.setText("Cliente:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        form1.add(labelControl3, gridBagConstraints);

        labelControl5.setLabel("Numero:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        form1.add(labelControl5, gridBagConstraints);

        textControl5.setAttributeName("numero");
        textControl5.setEnabled(false);
        textControl5.setEnabledOnEdit(false);
        textControl5.setEnabledOnInsert(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        form1.add(textControl5, gridBagConstraints);

        labelControl6.setLabel("Codigo Verificacao:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        form1.add(labelControl6, gridBagConstraints);

        textControl6.setAttributeName("codigoVerificacao");
        textControl6.setEnabled(false);
        textControl6.setEnabledOnEdit(false);
        textControl6.setEnabledOnInsert(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        form1.add(textControl6, gridBagConstraints);

        labelControl7.setLabel("Data Hora Emissao:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        form1.add(labelControl7, gridBagConstraints);

        dateControl7.setAttributeName("dataHoraEmissao");
        dateControl7.setEnabled(false);
        dateControl7.setEnabledOnEdit(false);
        dateControl7.setEnabledOnInsert(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        form1.add(dateControl7, gridBagConstraints);

        labelControl8.setLabel("Competencia:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        form1.add(labelControl8, gridBagConstraints);

        labelControl9.setLabel("Numero Substituida:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        form1.add(labelControl9, gridBagConstraints);

        textControl9.setAttributeName("numeroSubstituida");
        textControl9.setEnabled(false);
        textControl9.setEnabledOnEdit(false);
        textControl9.setEnabledOnInsert(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        form1.add(textControl9, gridBagConstraints);

        labelControl10.setLabel("Natureza Operacao:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        form1.add(labelControl10, gridBagConstraints);

        labelControl11.setLabel("Regime Especial Tributacao:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        form1.add(labelControl11, gridBagConstraints);

        labelControl12.setLabel("Optante Simples Nacional:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        form1.add(labelControl12, gridBagConstraints);

        comboBoxControl12.setAttributeName("optanteSimplesNacional");
        comboBoxControl12.setDomainId("simnao");
        comboBoxControl12.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        form1.add(comboBoxControl12, gridBagConstraints);

        labelControl13.setLabel("Incentivador Cultural:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        form1.add(labelControl13, gridBagConstraints);

        comboBoxControl13.setAttributeName("incentivadorCultural");
        comboBoxControl13.setDomainId("simnao");
        comboBoxControl13.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        form1.add(comboBoxControl13, gridBagConstraints);

        labelControl14.setText("Numero RPS:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        form1.add(labelControl14, gridBagConstraints);

        textControl14.setAttributeName("numeroRps");
        textControl14.setEnabled(false);
        textControl14.setEnabledOnEdit(false);
        textControl14.setEnabledOnInsert(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        form1.add(textControl14, gridBagConstraints);

        labelControl15.setText("Serie RPS:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        form1.add(labelControl15, gridBagConstraints);

        textControl15.setAttributeName("serieRps");
        textControl15.setEnabled(false);
        textControl15.setEnabledOnEdit(false);
        textControl15.setEnabledOnInsert(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        form1.add(textControl15, gridBagConstraints);

        labelControl16.setText("Tipo RPS:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        form1.add(labelControl16, gridBagConstraints);

        labelControl17.setText("Data Emissao RPS:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        form1.add(labelControl17, gridBagConstraints);

        dateControl17.setAttributeName("dataEmissaoRps");
        dateControl17.setEnabled(false);
        dateControl17.setEnabledOnEdit(false);
        dateControl17.setEnabledOnInsert(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        form1.add(dateControl17, gridBagConstraints);

        labelControl18.setLabel("Outras Informacoes:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        form1.add(labelControl18, gridBagConstraints);

        labelControl19.setLabel("Codigo Obra:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        form1.add(labelControl19, gridBagConstraints);

        textControl19.setAttributeName("codigoObra");
        textControl19.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        form1.add(textControl19, gridBagConstraints);

        labelControl20.setText("Numero ART:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        form1.add(labelControl20, gridBagConstraints);

        textControl20.setAttributeName("numeroArt");
        textControl20.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        form1.add(textControl20, gridBagConstraints);

        comboBoxControl1.setAttributeName("tipoRps");
        comboBoxControl1.setDomainId("nfseTipoRPS");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        form1.add(comboBoxControl1, gridBagConstraints);

        comboBoxControl2.setAttributeName("regimeEspecialTributacao");
        comboBoxControl2.setDomainId("nfseRegimeEspecialTributacao");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        form1.add(comboBoxControl2, gridBagConstraints);

        comboBoxControl3.setAttributeName("naturezaOperacao");
        comboBoxControl3.setDomainId("nfseNaturezaOperacao");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        form1.add(comboBoxControl3, gridBagConstraints);

        formattedTextControl1.setAttributeName("competencia");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        form1.add(formattedTextControl1, gridBagConstraints);

        textAreaControl1.setAttributeName("outrasInformacoes");
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

        textControl7.setAttributeName("cliente.pessoa.nome");
        textControl7.setEnabled(false);
        textControl7.setEnabledOnEdit(false);
        textControl7.setEnabledOnInsert(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        form1.add(textControl7, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        getContentPane().add(form1, gridBagConstraints);

        jPanel2.setLayout(new java.awt.GridBagLayout());

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Nfse Detalhe"));
        jPanel4.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));
        jPanel4.add(insertButton1);
        jPanel4.add(editButton2);
        jPanel4.add(deleteButton1);
        jPanel4.add(saveButton2);
        jPanel4.add(reloadButton2);
        jPanel4.add(navigatorBar1);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        jPanel2.add(jPanel4, gridBagConstraints);

        gridControlServicos.setAutoLoadData(false);
        gridControlServicos.setDeleteButton(deleteButton1);
        gridControlServicos.setEditButton(editButton2);
        gridControlServicos.setFunctionId("nfseDetalhe");
        gridControlServicos.setInsertButton(insertButton1);
        gridControlServicos.setNavBar(navigatorBar1);
        gridControlServicos.setReloadButton(reloadButton2);
        gridControlServicos.setSaveButton(saveButton2);
        gridControlServicos.setValueObjectClassName("com.t2tierp.nfse.java.NfseDetalheVO");

        codLookupColumn1.setColumnName("nfseListaServico.descricao");
        codLookupColumn1.setEditableOnEdit(true);
        codLookupColumn1.setEditableOnInsert(true);
        codLookupColumn1.setHeaderColumnName("Servico");
        codLookupColumn1.setHeaderFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        codLookupColumn1.setPreferredWidth(300);
        gridControlServicos.getColumnContainer().add(codLookupColumn1);

        decimalColumn4.setColumnName("valorServicos");
        decimalColumn4.setHeaderColumnName("Valor Servicos");
        decimalColumn4.setHeaderFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        decimalColumn4.setDecimals(2);
        gridControlServicos.getColumnContainer().add(decimalColumn4);

        decimalColumn14.setColumnName("aliquota");
        decimalColumn14.setHeaderColumnName("Aliquota");
        decimalColumn14.setHeaderFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        decimalColumn14.setDecimals(2);
        gridControlServicos.getColumnContainer().add(decimalColumn14);

        decimalColumn15.setColumnName("valorIss");
        decimalColumn15.setDecimals(2);
        decimalColumn15.setHeaderColumnName("Valor ISS");
        decimalColumn15.setHeaderFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        gridControlServicos.getColumnContainer().add(decimalColumn15);

        decimalColumn22.setColumnName("valorDescontoIncondicionado");
        decimalColumn22.setDecimals(2);
        decimalColumn22.setHeaderColumnName("Valor Desconto");
        decimalColumn22.setHeaderFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        gridControlServicos.getColumnContainer().add(decimalColumn22);

        decimalColumn16.setColumnName("valorServicos");
        decimalColumn16.setDecimals(2);
        decimalColumn16.setHeaderColumnName("Valor Liquido");
        decimalColumn16.setHeaderFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        gridControlServicos.getColumnContainer().add(decimalColumn16);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel2.add(gridControlServicos, gridBagConstraints);

        jTabbedPane1.addTab("Serviços", jPanel2);

        jPanel3.setLayout(new java.awt.GridBagLayout());

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder("Nfse Detalhe"));
        jPanel5.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));
        jPanel5.add(insertButton2);
        jPanel5.add(editButton3);
        jPanel5.add(deleteButton2);
        jPanel5.add(saveButton3);
        jPanel5.add(reloadButton3);
        jPanel5.add(navigatorBar2);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        jPanel3.add(jPanel5, gridBagConstraints);

        gridControlIntermediario.setAutoLoadData(false);
        gridControlIntermediario.setDeleteButton(deleteButton2);
        gridControlIntermediario.setEditButton(editButton3);
        gridControlIntermediario.setFunctionId("nfseIntermediario");
        gridControlIntermediario.setInsertButton(insertButton2);
        gridControlIntermediario.setNavBar(navigatorBar2);
        gridControlIntermediario.setReloadButton(reloadButton3);
        gridControlIntermediario.setSaveButton(saveButton3);
        gridControlIntermediario.setValueObjectClassName("com.t2tierp.nfse.java.NfseIntermediarioVO");

        textColumn5.setColumnName("razao");
        textColumn5.setHeaderColumnName("Razao");
        textColumn5.setHeaderFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        textColumn5.setMaxCharacters(150);
        textColumn5.setPreferredWidth(300);
        gridControlIntermediario.getColumnContainer().add(textColumn5);

        textColumn4.setColumnName("cnpj");
        textColumn4.setHeaderColumnName("Cnpj");
        textColumn4.setHeaderFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        textColumn4.setMaxCharacters(14);
        textColumn4.setPreferredWidth(150);
        gridControlIntermediario.getColumnContainer().add(textColumn4);

        textColumn6.setColumnName("inscricaoMunicipal");
        textColumn6.setColumnRequired(false);
        textColumn6.setHeaderColumnName("Inscricao Municipal");
        textColumn6.setHeaderFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        textColumn6.setMaxCharacters(15);
        textColumn6.setPreferredWidth(150);
        gridControlIntermediario.getColumnContainer().add(textColumn6);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel3.add(gridControlIntermediario, gridBagConstraints);

        jTabbedPane1.addTab("Intermediarios", jPanel3);

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
        controller.enviarNfse();
    }//GEN-LAST:event_genericButton1ActionPerformed

    private void genericButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_genericButton2ActionPerformed
        controller.consultaEnvioNfse();
    }//GEN-LAST:event_genericButton2ActionPerformed

    private void genericButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_genericButton3ActionPerformed
        controller.imprimeNfse();
    }//GEN-LAST:event_genericButton3ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private org.openswing.swing.table.columns.client.CodLookupColumn codLookupColumn1;
    private org.openswing.swing.client.CodLookupControl codLookupControl2;
    private org.openswing.swing.client.ComboBoxControl comboBoxControl1;
    private org.openswing.swing.client.ComboBoxControl comboBoxControl12;
    private org.openswing.swing.client.ComboBoxControl comboBoxControl13;
    private org.openswing.swing.client.ComboBoxControl comboBoxControl2;
    private org.openswing.swing.client.ComboBoxControl comboBoxControl3;
    private org.openswing.swing.client.DateControl dateControl17;
    private org.openswing.swing.client.DateControl dateControl7;
    private org.openswing.swing.table.columns.client.DecimalColumn decimalColumn14;
    private org.openswing.swing.table.columns.client.DecimalColumn decimalColumn15;
    private org.openswing.swing.table.columns.client.DecimalColumn decimalColumn16;
    private org.openswing.swing.table.columns.client.DecimalColumn decimalColumn22;
    private org.openswing.swing.table.columns.client.DecimalColumn decimalColumn4;
    private org.openswing.swing.client.DeleteButton deleteButton1;
    private org.openswing.swing.client.DeleteButton deleteButton2;
    private org.openswing.swing.client.EditButton editButton1;
    private org.openswing.swing.client.EditButton editButton2;
    private org.openswing.swing.client.EditButton editButton3;
    private org.openswing.swing.form.client.Form form1;
    private org.openswing.swing.client.FormattedTextControl formattedTextControl1;
    private org.openswing.swing.client.GenericButton genericButton1;
    private org.openswing.swing.client.GenericButton genericButton2;
    private org.openswing.swing.client.GenericButton genericButton3;
    private org.openswing.swing.client.GridControl gridControlIntermediario;
    private org.openswing.swing.client.GridControl gridControlServicos;
    private org.openswing.swing.client.InsertButton insertButton1;
    private org.openswing.swing.client.InsertButton insertButton2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
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
    private org.openswing.swing.client.LabelControl labelControl18;
    private org.openswing.swing.client.LabelControl labelControl19;
    private org.openswing.swing.client.LabelControl labelControl20;
    private org.openswing.swing.client.LabelControl labelControl3;
    private org.openswing.swing.client.LabelControl labelControl5;
    private org.openswing.swing.client.LabelControl labelControl6;
    private org.openswing.swing.client.LabelControl labelControl7;
    private org.openswing.swing.client.LabelControl labelControl8;
    private org.openswing.swing.client.LabelControl labelControl9;
    private org.openswing.swing.client.NavigatorBar navigatorBar1;
    private org.openswing.swing.client.NavigatorBar navigatorBar2;
    private org.openswing.swing.client.ReloadButton reloadButton1;
    private org.openswing.swing.client.ReloadButton reloadButton2;
    private org.openswing.swing.client.ReloadButton reloadButton3;
    private org.openswing.swing.client.SaveButton saveButton1;
    private org.openswing.swing.client.SaveButton saveButton2;
    private org.openswing.swing.client.SaveButton saveButton3;
    private org.openswing.swing.client.TextAreaControl textAreaControl1;
    private org.openswing.swing.table.columns.client.TextColumn textColumn4;
    private org.openswing.swing.table.columns.client.TextColumn textColumn5;
    private org.openswing.swing.table.columns.client.TextColumn textColumn6;
    private org.openswing.swing.client.TextControl textControl14;
    private org.openswing.swing.client.TextControl textControl15;
    private org.openswing.swing.client.TextControl textControl19;
    private org.openswing.swing.client.TextControl textControl20;
    private org.openswing.swing.client.TextControl textControl5;
    private org.openswing.swing.client.TextControl textControl6;
    private org.openswing.swing.client.TextControl textControl7;
    private org.openswing.swing.client.TextControl textControl9;
    // End of variables declaration//GEN-END:variables

}
