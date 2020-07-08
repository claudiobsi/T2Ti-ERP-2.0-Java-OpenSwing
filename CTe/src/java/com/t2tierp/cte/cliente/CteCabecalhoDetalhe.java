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
package com.t2tierp.cte.cliente;

import com.t2tierp.padrao.cliente.LookupDataLocatorGenerico;
import java.awt.Dimension;
import javax.swing.ImageIcon;
import org.openswing.swing.lookup.client.LookupController;
import org.openswing.swing.mdi.client.InternalFrame;
import org.openswing.swing.util.client.ClientUtils;

public class CteCabecalhoDetalhe extends InternalFrame {

    private LookupController nfeController = new LookupController();
    private CteCabecalhoDetalheController controller;
    private CteCargaGridController cargaGridController;
    private CteInformacaoNfOutrosGridController informacaoNfOutrosGridController;
    private CteRodoviarioDetalheController rodoviarioController;
    private CteRodoviarioOccGridController rodoviarioOccGridController;
    private CteRemetenteDetalheController remetenteController;
    private CteDestinatarioDetalheController destinatarioController;
    
    public CteCabecalhoDetalhe(CteCabecalhoDetalheController controller) {
        initComponents();

        genericButton1.setToolTipText("Enviar CT-e");
        
        this.controller = controller;
        form1.setFormController(controller);
        
        cargaGridController = new CteCargaGridController(this);
        gridControlCarga.setController(cargaGridController);
        gridControlCarga.setGridDataLocator(cargaGridController);
        
        informacaoNfOutrosGridController = new CteInformacaoNfOutrosGridController(this);
        gridControlDocumentos.setController(informacaoNfOutrosGridController);
        gridControlDocumentos.setGridDataLocator(informacaoNfOutrosGridController);

        rodoviarioController = new CteRodoviarioDetalheController();
        formRodoviario.setFormController(rodoviarioController);
        
        rodoviarioOccGridController = new CteRodoviarioOccGridController(this);
        gridControlOcc.setController(rodoviarioOccGridController);
        gridControlOcc.setGridDataLocator(rodoviarioOccGridController);
        
        remetenteController = new CteRemetenteDetalheController();
        formRemetente.setFormController(remetenteController);

        destinatarioController = new CteDestinatarioDetalheController();
        formDestinatario.setFormController(destinatarioController);
        
        /*
         * Configurações do lookup da NF-e
         */
        nfeController.setLookupValueObjectClassName("com.t2tierp.nfe.java.NfeCabecalhoVO");
        nfeController.addLookup2ParentLink("id", "nfeCabecalho.id");
        nfeController.addLookup2ParentLink("chaveAcesso", "chaveAcessoNfe");
        nfeController.addLookup2ParentLink("digitoChaveAcesso", "nfeCabecalho.digitoChaveAcesso");
        nfeController.addLookup2ParentLink("numero", "nfeCabecalho.numero");        
        nfeController.addLookup2ParentLink("codigoModelo", "nfeCabecalho.codigoModelo");
        nfeController.addLookup2ParentLink("serie", "nfeCabecalho.serie");
        nfeController.addLookup2ParentLink("baseCalculoIcms", "nfeCabecalho.baseCalculoIcms");
        nfeController.addLookup2ParentLink("dataHoraEmissao", "nfeCabecalho.dataHoraEmissao");
        nfeController.addLookup2ParentLink("valorIcms", "nfeCabecalho.valorIcms");
        nfeController.addLookup2ParentLink("valorTotal", "nfeCabecalho.valorTotal");
        nfeController.addLookup2ParentLink("valorTotalProdutos", "nfeCabecalho.valorTotalProdutos");
        nfeController.addLookup2ParentLink("destinatario", "nfeCabecalho.destinatario");
        nfeController.addLookup2ParentLink("empresa", "nfeCabecalho.empresa");
        
        nfeController.setHeaderColumnName("numero", "Número");
        nfeController.setHeaderColumnName("chaveAcesso", "Chave Acesso");
        nfeController.setFrameTitle("Importa NF-e");

        nfeController.setVisibleStatusPanel(true);
        nfeController.setVisibleColumn("chaveAcesso", true);
        nfeController.setPreferredWidthColumn("chaveAcesso", 400);
        nfeController.setVisibleColumn("numero", true);
        nfeController.setFramePreferedSize(new Dimension(600, 500));

        nfeController.setLookupDataLocator(new LookupDataLocatorGenerico(nfeController.getLookupValueObjectClassName()));
        codLookupColumn1.setLookupController(nfeController);
        
    }

    /**
     * @return the form1
     */
    public org.openswing.swing.form.client.Form getForm1() {
        return form1;
    }
    
    public CteCabecalhoDetalheController getCabecalhoController() {
        return controller;
    }

    public CteCargaGridController getCargaGridController() {
        return cargaGridController;
    }

    public CteInformacaoNfOutrosGridController getInformacaoNfOutrosGridController() {
        return informacaoNfOutrosGridController;
    }

    public CteRodoviarioDetalheController getRodoviarioController() {
        return rodoviarioController;
    }

    public org.openswing.swing.form.client.Form getFormRodoviario() {
        return formRodoviario;
    }
    
    public CteRodoviarioOccGridController getRodoviarioOccGridController() {
        return rodoviarioOccGridController;
    }

    public org.openswing.swing.client.GridControl getGridControlCarga() {
        return gridControlCarga;
    }

    public org.openswing.swing.client.GridControl getGridControlDocumentos() {
        return gridControlDocumentos;
    }

    public org.openswing.swing.client.GridControl getGridControlOcc() {
        return gridControlOcc;
    }

    public CteRemetenteDetalheController getRemetenteController() {
        return remetenteController;
    }

    public CteDestinatarioDetalheController getDestinatarioController() {
        return destinatarioController;
    }

    public org.openswing.swing.form.client.Form getFormDestinatario() {
        return formDestinatario;
    }

    public org.openswing.swing.form.client.Form getFormRemetente() {
        return formRemetente;
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

        jTabbedPane2 = new javax.swing.JTabbedPane();
        form1 = new org.openswing.swing.form.client.Form();
        labelControl4 = new org.openswing.swing.client.LabelControl();
        textControl6 = new org.openswing.swing.client.TextControl();
        labelControl6 = new org.openswing.swing.client.LabelControl();
        textControl8 = new org.openswing.swing.client.TextControl();
        labelControl12 = new org.openswing.swing.client.LabelControl();
        textControl14 = new org.openswing.swing.client.TextControl();
        labelControl10 = new org.openswing.swing.client.LabelControl();
        labelControl9 = new org.openswing.swing.client.LabelControl();
        dateControl10 = new org.openswing.swing.client.DateControl();
        labelControl8 = new org.openswing.swing.client.LabelControl();
        textControl10 = new org.openswing.swing.client.TextControl();
        labelControl7 = new org.openswing.swing.client.LabelControl();
        textControl9 = new org.openswing.swing.client.TextControl();
        labelControl13 = new org.openswing.swing.client.LabelControl();
        textControl15 = new org.openswing.swing.client.TextControl();
        comboBoxControl1 = new org.openswing.swing.client.ComboBoxControl();
        labelControl3 = new org.openswing.swing.client.LabelControl();
        numericControl5 = new org.openswing.swing.client.NumericControl();
        labelControl11 = new org.openswing.swing.client.LabelControl();
        labelControl5 = new org.openswing.swing.client.LabelControl();
        labelControl22 = new org.openswing.swing.client.LabelControl();
        comboBoxControl2 = new org.openswing.swing.client.ComboBoxControl();
        comboBoxControl3 = new org.openswing.swing.client.ComboBoxControl();
        comboBoxControl4 = new org.openswing.swing.client.ComboBoxControl();
        labelControl23 = new org.openswing.swing.client.LabelControl();
        comboBoxControl5 = new org.openswing.swing.client.ComboBoxControl();
        labelControl15 = new org.openswing.swing.client.LabelControl();
        comboBoxControl6 = new org.openswing.swing.client.ComboBoxControl();
        labelControl20 = new org.openswing.swing.client.LabelControl();
        textControl22 = new org.openswing.swing.client.TextControl();
        textControl23 = new org.openswing.swing.client.TextControl();
        labelControl21 = new org.openswing.swing.client.LabelControl();
        labelControl28 = new org.openswing.swing.client.LabelControl();
        labelControl29 = new org.openswing.swing.client.LabelControl();
        textControl31 = new org.openswing.swing.client.TextControl();
        textControl30 = new org.openswing.swing.client.TextControl();
        labelControl32 = new org.openswing.swing.client.LabelControl();
        comboBoxControl7 = new org.openswing.swing.client.ComboBoxControl();
        labelControl35 = new org.openswing.swing.client.LabelControl();
        textControl37 = new org.openswing.swing.client.TextControl();
        labelControl36 = new org.openswing.swing.client.LabelControl();
        textControl38 = new org.openswing.swing.client.TextControl();
        labelControl37 = new org.openswing.swing.client.LabelControl();
        textControl39 = new org.openswing.swing.client.TextControl();
        labelControl39 = new org.openswing.swing.client.LabelControl();
        comboBoxControl8 = new org.openswing.swing.client.ComboBoxControl();
        labelControl47 = new org.openswing.swing.client.LabelControl();
        textControl49 = new org.openswing.swing.client.TextControl();
        textControl50 = new org.openswing.swing.client.TextControl();
        labelControl48 = new org.openswing.swing.client.LabelControl();
        labelControl49 = new org.openswing.swing.client.LabelControl();
        numericControl52 = new org.openswing.swing.client.NumericControl();
        labelControl50 = new org.openswing.swing.client.LabelControl();
        numericControl53 = new org.openswing.swing.client.NumericControl();
        labelControl51 = new org.openswing.swing.client.LabelControl();
        textAreaControl1 = new org.openswing.swing.client.TextAreaControl();
        jSeparator2 = new javax.swing.JSeparator();
        jTabbedPane3 = new javax.swing.JTabbedPane();
        jPanel9 = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        insertButton2 = new org.openswing.swing.client.InsertButton();
        editButton3 = new org.openswing.swing.client.EditButton();
        deleteButton2 = new org.openswing.swing.client.DeleteButton();
        reloadButton3 = new org.openswing.swing.client.ReloadButton();
        saveButton3 = new org.openswing.swing.client.SaveButton();
        navigatorBar2 = new org.openswing.swing.client.NavigatorBar();
        gridControlDocumentos = new org.openswing.swing.client.GridControl();
        codLookupColumn1 = new org.openswing.swing.table.columns.client.CodLookupColumn();
        integerColumn19 = new org.openswing.swing.table.columns.client.IntegerColumn();
        dateColumn20 = new org.openswing.swing.table.columns.client.DateColumn();
        jTabbedPane4 = new javax.swing.JTabbedPane();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel17 = new javax.swing.JPanel();
        formRodoviario = new org.openswing.swing.form.client.Form();
        labelControl75 = new org.openswing.swing.client.LabelControl();
        textControl3 = new org.openswing.swing.client.TextControl();
        labelControl76 = new org.openswing.swing.client.LabelControl();
        dateControl4 = new org.openswing.swing.client.DateControl();
        labelControl77 = new org.openswing.swing.client.LabelControl();
        labelControl78 = new org.openswing.swing.client.LabelControl();
        numericControl7 = new org.openswing.swing.client.NumericControl();
        comboBoxControl9 = new org.openswing.swing.client.ComboBoxControl();
        jSeparator1 = new javax.swing.JSeparator();
        jPanel18 = new javax.swing.JPanel();
        editButton7 = new org.openswing.swing.client.EditButton();
        reloadButton7 = new org.openswing.swing.client.ReloadButton();
        saveButton7 = new org.openswing.swing.client.SaveButton();
        jPanel2 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        insertButton1 = new org.openswing.swing.client.InsertButton();
        editButton2 = new org.openswing.swing.client.EditButton();
        deleteButton1 = new org.openswing.swing.client.DeleteButton();
        reloadButton2 = new org.openswing.swing.client.ReloadButton();
        saveButton2 = new org.openswing.swing.client.SaveButton();
        navigatorBar1 = new org.openswing.swing.client.NavigatorBar();
        gridControlOcc = new org.openswing.swing.client.GridControl();
        textColumn3 = new org.openswing.swing.table.columns.client.TextColumn();
        integerColumn4 = new org.openswing.swing.table.columns.client.IntegerColumn();
        dateColumn5 = new org.openswing.swing.table.columns.client.DateColumn();
        textColumn6 = new org.openswing.swing.table.columns.client.TextColumn();
        textColumn7 = new org.openswing.swing.table.columns.client.TextColumn();
        textColumn8 = new org.openswing.swing.table.columns.client.TextColumn();
        textColumn9 = new org.openswing.swing.table.columns.client.TextColumn();
        textColumn10 = new org.openswing.swing.table.columns.client.TextColumn();
        jPanel7 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        insertButton3 = new org.openswing.swing.client.InsertButton();
        editButton4 = new org.openswing.swing.client.EditButton();
        deleteButton3 = new org.openswing.swing.client.DeleteButton();
        reloadButton4 = new org.openswing.swing.client.ReloadButton();
        saveButton4 = new org.openswing.swing.client.SaveButton();
        navigatorBar3 = new org.openswing.swing.client.NavigatorBar();
        gridControlCarga = new org.openswing.swing.client.GridControl();
        comboColumn1 = new org.openswing.swing.table.columns.client.ComboColumn();
        textColumn5 = new org.openswing.swing.table.columns.client.TextColumn();
        decimalColumn5 = new org.openswing.swing.table.columns.client.DecimalColumn();
        jPanel13 = new javax.swing.JPanel();
        jPanel15 = new javax.swing.JPanel();
        editButton5 = new org.openswing.swing.client.EditButton();
        reloadButton5 = new org.openswing.swing.client.ReloadButton();
        saveButton5 = new org.openswing.swing.client.SaveButton();
        formRemetente = new org.openswing.swing.form.client.Form();
        labelControl79 = new org.openswing.swing.client.LabelControl();
        textControl5 = new org.openswing.swing.client.TextControl();
        labelControl81 = new org.openswing.swing.client.LabelControl();
        textControl7 = new org.openswing.swing.client.TextControl();
        labelControl82 = new org.openswing.swing.client.LabelControl();
        textControl11 = new org.openswing.swing.client.TextControl();
        labelControl83 = new org.openswing.swing.client.LabelControl();
        textControl12 = new org.openswing.swing.client.TextControl();
        labelControl84 = new org.openswing.swing.client.LabelControl();
        textControl13 = new org.openswing.swing.client.TextControl();
        labelControl85 = new org.openswing.swing.client.LabelControl();
        textControl16 = new org.openswing.swing.client.TextControl();
        labelControl86 = new org.openswing.swing.client.LabelControl();
        textControl17 = new org.openswing.swing.client.TextControl();
        labelControl87 = new org.openswing.swing.client.LabelControl();
        textControl18 = new org.openswing.swing.client.TextControl();
        labelControl88 = new org.openswing.swing.client.LabelControl();
        textControl21 = new org.openswing.swing.client.TextControl();
        labelControl90 = new org.openswing.swing.client.LabelControl();
        textControl24 = new org.openswing.swing.client.TextControl();
        labelControl91 = new org.openswing.swing.client.LabelControl();
        textControl25 = new org.openswing.swing.client.TextControl();
        labelControl92 = new org.openswing.swing.client.LabelControl();
        textControl26 = new org.openswing.swing.client.TextControl();
        labelControl95 = new org.openswing.swing.client.LabelControl();
        textControl32 = new org.openswing.swing.client.TextControl();
        jSeparator3 = new javax.swing.JSeparator();
        jPanel14 = new javax.swing.JPanel();
        jPanel16 = new javax.swing.JPanel();
        editButton6 = new org.openswing.swing.client.EditButton();
        reloadButton6 = new org.openswing.swing.client.ReloadButton();
        saveButton6 = new org.openswing.swing.client.SaveButton();
        formDestinatario = new org.openswing.swing.form.client.Form();
        labelControl80 = new org.openswing.swing.client.LabelControl();
        textControl29 = new org.openswing.swing.client.TextControl();
        labelControl89 = new org.openswing.swing.client.LabelControl();
        textControl34 = new org.openswing.swing.client.TextControl();
        labelControl93 = new org.openswing.swing.client.LabelControl();
        textControl35 = new org.openswing.swing.client.TextControl();
        labelControl94 = new org.openswing.swing.client.LabelControl();
        textControl41 = new org.openswing.swing.client.TextControl();
        labelControl96 = new org.openswing.swing.client.LabelControl();
        textControl42 = new org.openswing.swing.client.TextControl();
        labelControl97 = new org.openswing.swing.client.LabelControl();
        textControl43 = new org.openswing.swing.client.TextControl();
        labelControl98 = new org.openswing.swing.client.LabelControl();
        textControl44 = new org.openswing.swing.client.TextControl();
        labelControl99 = new org.openswing.swing.client.LabelControl();
        textControl45 = new org.openswing.swing.client.TextControl();
        labelControl100 = new org.openswing.swing.client.LabelControl();
        textControl51 = new org.openswing.swing.client.TextControl();
        labelControl101 = new org.openswing.swing.client.LabelControl();
        textControl52 = new org.openswing.swing.client.TextControl();
        labelControl102 = new org.openswing.swing.client.LabelControl();
        textControl53 = new org.openswing.swing.client.TextControl();
        labelControl103 = new org.openswing.swing.client.LabelControl();
        textControl55 = new org.openswing.swing.client.TextControl();
        labelControl104 = new org.openswing.swing.client.LabelControl();
        textControl56 = new org.openswing.swing.client.TextControl();
        labelControl105 = new org.openswing.swing.client.LabelControl();
        textControl57 = new org.openswing.swing.client.TextControl();
        jSeparator4 = new javax.swing.JSeparator();
        jPanel1 = new javax.swing.JPanel();
        editButton1 = new org.openswing.swing.client.EditButton();
        reloadButton1 = new org.openswing.swing.client.ReloadButton();
        saveButton1 = new org.openswing.swing.client.SaveButton();
        genericButton1 = new org.openswing.swing.client.GenericButton(new ImageIcon(ClientUtils.getImage("ok.gif")));

        setTitle("T2Ti ERP - CT-e");
        setPreferredSize(new java.awt.Dimension(700, 400));
        getContentPane().setLayout(new java.awt.GridBagLayout());

        form1.setVOClassName("com.t2tierp.cte.java.CteCabecalhoVO");
        form1.setEditButton(editButton1);
        form1.setFunctionId("cteCabecalho");
        form1.setReloadButton(reloadButton1);
        form1.setSaveButton(saveButton1);
        form1.setLayout(new java.awt.GridBagLayout());

        labelControl4.setLabel("Natureza Operacao:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        form1.add(labelControl4, gridBagConstraints);

        textControl6.setAttributeName("naturezaOperacao");
        textControl6.setEnabled(false);
        textControl6.setEnabledOnEdit(false);
        textControl6.setEnabledOnInsert(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        form1.add(textControl6, gridBagConstraints);

        labelControl6.setLabel("Modelo:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        form1.add(labelControl6, gridBagConstraints);

        textControl8.setAttributeName("modelo");
        textControl8.setEnabled(false);
        textControl8.setEnabledOnEdit(false);
        textControl8.setEnabledOnInsert(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        form1.add(textControl8, gridBagConstraints);

        labelControl12.setLabel("Chave Acesso:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        form1.add(labelControl12, gridBagConstraints);

        textControl14.setAttributeName("digitoChaveAcesso");
        textControl14.setEnabled(false);
        textControl14.setEnabledOnEdit(false);
        textControl14.setEnabledOnInsert(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        form1.add(textControl14, gridBagConstraints);

        labelControl10.setText("Formato Impressao DACTE:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        form1.add(labelControl10, gridBagConstraints);

        labelControl9.setLabel("Data Hora Emissao:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        form1.add(labelControl9, gridBagConstraints);

        dateControl10.setAttributeName("dataHoraEmissao");
        dateControl10.setEnabled(false);
        dateControl10.setEnabledOnEdit(false);
        dateControl10.setEnabledOnInsert(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 50;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        form1.add(dateControl10, gridBagConstraints);

        labelControl8.setLabel("Numero:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        form1.add(labelControl8, gridBagConstraints);

        textControl10.setAttributeName("numero");
        textControl10.setEnabled(false);
        textControl10.setEnabledOnEdit(false);
        textControl10.setEnabledOnInsert(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        form1.add(textControl10, gridBagConstraints);

        labelControl7.setLabel("Serie:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        form1.add(labelControl7, gridBagConstraints);

        textControl9.setAttributeName("serie");
        textControl9.setEnabled(false);
        textControl9.setEnabledOnEdit(false);
        textControl9.setEnabledOnInsert(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        form1.add(textControl9, gridBagConstraints);

        labelControl13.setText("DV:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        form1.add(labelControl13, gridBagConstraints);

        textControl15.setAttributeName("chaveAcesso");
        textControl15.setEnabled(false);
        textControl15.setEnabledOnEdit(false);
        textControl15.setEnabledOnInsert(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        form1.add(textControl15, gridBagConstraints);

        comboBoxControl1.setAttributeName("modal");
        comboBoxControl1.setDomainId("cteModal");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        form1.add(comboBoxControl1, gridBagConstraints);

        labelControl3.setText("CFOP:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        form1.add(labelControl3, gridBagConstraints);

        numericControl5.setAttributeName("cfop");
        numericControl5.setEnabled(false);
        numericControl5.setMaxCharacters(4);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        form1.add(numericControl5, gridBagConstraints);

        labelControl11.setLabel("Tipo Emissao:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        form1.add(labelControl11, gridBagConstraints);

        labelControl5.setLabel("Forma Pagamento:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        form1.add(labelControl5, gridBagConstraints);

        labelControl22.setText("Modalidade:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        form1.add(labelControl22, gridBagConstraints);

        comboBoxControl2.setAttributeName("tipoEmissao");
        comboBoxControl2.setDomainId("cteTipoEmissao");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        form1.add(comboBoxControl2, gridBagConstraints);

        comboBoxControl3.setAttributeName("formaPagamento");
        comboBoxControl3.setDomainId("cteFormaPagamento");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        form1.add(comboBoxControl3, gridBagConstraints);

        comboBoxControl4.setAttributeName("tomador");
        comboBoxControl4.setDomainId("cteTipoPeriodo");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 11;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        form1.add(comboBoxControl4, gridBagConstraints);

        labelControl23.setLabel("Tipo Servico:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        form1.add(labelControl23, gridBagConstraints);

        comboBoxControl5.setAttributeName("formatoImpressaoDacte");
        comboBoxControl5.setDomainId("cteFormatoImpressaoDacte");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        form1.add(comboBoxControl5, gridBagConstraints);

        labelControl15.setText("Tipo CT-e:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        form1.add(labelControl15, gridBagConstraints);

        comboBoxControl6.setAttributeName("tipoServico");
        comboBoxControl6.setDomainId("cteTipoServico");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        form1.add(comboBoxControl6, gridBagConstraints);

        labelControl20.setLabel("Nome Municipio Envio:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        form1.add(labelControl20, gridBagConstraints);

        textControl22.setAttributeName("nomeMunicipioEnvio");
        textControl22.setEnabled(false);
        textControl22.setEnabledOnEdit(false);
        textControl22.setEnabledOnInsert(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        form1.add(textControl22, gridBagConstraints);

        textControl23.setAttributeName("ufEnvio");
        textControl23.setEnabled(false);
        textControl23.setEnabledOnEdit(false);
        textControl23.setEnabledOnInsert(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        form1.add(textControl23, gridBagConstraints);

        labelControl21.setText("UF Envio:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        form1.add(labelControl21, gridBagConstraints);

        labelControl28.setLabel("Nome Municipio Fim Prestacao:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        form1.add(labelControl28, gridBagConstraints);

        labelControl29.setText("UF Fim Prestacao:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        form1.add(labelControl29, gridBagConstraints);

        textControl31.setAttributeName("ufFimPrestacao");
        textControl31.setEnabled(false);
        textControl31.setEnabledOnEdit(false);
        textControl31.setEnabledOnInsert(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        form1.add(textControl31, gridBagConstraints);

        textControl30.setAttributeName("nomeMunicipioFimPrestacao");
        textControl30.setEnabled(false);
        textControl30.setEnabledOnEdit(false);
        textControl30.setEnabledOnInsert(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        form1.add(textControl30, gridBagConstraints);

        labelControl32.setLabel("Tomador:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        form1.add(labelControl32, gridBagConstraints);

        comboBoxControl7.setAttributeName("tipoCte");
        comboBoxControl7.setDomainId("cteTipo");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        form1.add(comboBoxControl7, gridBagConstraints);

        labelControl35.setText("Caracteristica Adicional Transporte:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        form1.add(labelControl35, gridBagConstraints);

        textControl37.setAttributeName("caracAdicionalTransporte");
        textControl37.setEnabled(false);
        textControl37.setMaxCharacters(15);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 9;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        form1.add(textControl37, gridBagConstraints);

        labelControl36.setText("Caracteristica Adicional Servico:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        form1.add(labelControl36, gridBagConstraints);

        textControl38.setAttributeName("caracAdicionalServico");
        textControl38.setEnabled(false);
        textControl38.setMaxCharacters(30);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 9;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        form1.add(textControl38, gridBagConstraints);

        labelControl37.setLabel("Funcionario Emissor:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        form1.add(labelControl37, gridBagConstraints);

        textControl39.setAttributeName("funcionarioEmissor");
        textControl39.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 11;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        form1.add(textControl39, gridBagConstraints);

        labelControl39.setLabel("Entrega Tipo Periodo:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        form1.add(labelControl39, gridBagConstraints);

        comboBoxControl8.setAttributeName("tomador");
        comboBoxControl8.setDomainId("cteTomador");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 9;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        form1.add(comboBoxControl8, gridBagConstraints);

        labelControl47.setLabel("Municipio Origem Calculo:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        form1.add(labelControl47, gridBagConstraints);

        textControl49.setAttributeName("municipioOrigemCalculo");
        textControl49.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 11;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        form1.add(textControl49, gridBagConstraints);

        textControl50.setAttributeName("municipioDestinoCalculo");
        textControl50.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 11;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        form1.add(textControl50, gridBagConstraints);

        labelControl48.setLabel("Municipio Destino Calculo:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        form1.add(labelControl48, gridBagConstraints);

        labelControl49.setLabel("Observacoes Gerais:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 14;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        form1.add(labelControl49, gridBagConstraints);

        numericControl52.setAttributeName("valorTotalServico");
        numericControl52.setDecimals(2);
        numericControl52.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 13;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        form1.add(numericControl52, gridBagConstraints);

        labelControl50.setLabel("Valor Total Servico:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 12;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        form1.add(labelControl50, gridBagConstraints);

        numericControl53.setAttributeName("valorReceber");
        numericControl53.setDecimals(2);
        numericControl53.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 13;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints.ipadx = 50;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        form1.add(numericControl53, gridBagConstraints);

        labelControl51.setLabel("Valor Receber:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 12;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        form1.add(labelControl51, gridBagConstraints);

        textAreaControl1.setAttributeName("observacoesGerais");
        textAreaControl1.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 15;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipady = 50;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        form1.add(textAreaControl1, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 16;
        gridBagConstraints.gridwidth = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weighty = 1.0;
        form1.add(jSeparator2, gridBagConstraints);

        jTabbedPane2.addTab("Dados Principais", form1);

        jPanel9.setLayout(new java.awt.GridBagLayout());

        jPanel10.setBorder(javax.swing.BorderFactory.createTitledBorder("Informacoes NF-e"));
        jPanel10.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));
        jPanel10.add(insertButton2);
        jPanel10.add(editButton3);
        jPanel10.add(deleteButton2);
        jPanel10.add(reloadButton3);
        jPanel10.add(saveButton3);
        jPanel10.add(navigatorBar2);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        jPanel9.add(jPanel10, gridBagConstraints);

        gridControlDocumentos.setAutoLoadData(false);
        gridControlDocumentos.setDeleteButton(deleteButton2);
        gridControlDocumentos.setEditButton(editButton3);
        gridControlDocumentos.setFunctionId("cteInformacaoNfOutros");
        gridControlDocumentos.setInsertButton(insertButton2);
        gridControlDocumentos.setNavBar(navigatorBar2);
        gridControlDocumentos.setReloadButton(reloadButton3);
        gridControlDocumentos.setSaveButton(saveButton3);
        gridControlDocumentos.setValueObjectClassName("com.t2tierp.cte.java.CteInformacaoNfOutrosVO");

        codLookupColumn1.setColumnName("chaveAcessoNfe");
        codLookupColumn1.setEditableOnEdit(true);
        codLookupColumn1.setEditableOnInsert(true);
        codLookupColumn1.setHeaderColumnName("Chave Acesso NF-e");
        codLookupColumn1.setHeaderFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        codLookupColumn1.setMaxCharacters(44);
        codLookupColumn1.setPreferredWidth(400);
        gridControlDocumentos.getColumnContainer().add(codLookupColumn1);

        integerColumn19.setColumnName("pinSuframa");
        integerColumn19.setColumnRequired(false);
        integerColumn19.setEditableOnEdit(true);
        integerColumn19.setEditableOnInsert(true);
        integerColumn19.setHeaderColumnName("Pin Suframa");
        integerColumn19.setHeaderFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        integerColumn19.setMaxCharacters(9);
        gridControlDocumentos.getColumnContainer().add(integerColumn19);

        dateColumn20.setColumnName("dataPrevistaEntrega");
        dateColumn20.setColumnRequired(false);
        dateColumn20.setEditableOnEdit(true);
        dateColumn20.setEditableOnInsert(true);
        dateColumn20.setHeaderColumnName("Data Prevista Entrega");
        dateColumn20.setHeaderFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        dateColumn20.setPreferredWidth(130);
        gridControlDocumentos.getColumnContainer().add(dateColumn20);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel9.add(gridControlDocumentos, gridBagConstraints);

        jTabbedPane3.addTab("NF-e", jPanel9);

        jTabbedPane2.addTab("Documentos", jTabbedPane3);

        jPanel17.setLayout(new java.awt.GridBagLayout());

        formRodoviario.setVOClassName("com.t2tierp.cte.java.CteRodoviarioVO");
        formRodoviario.setEditButton(editButton7);
        formRodoviario.setFunctionId("cteRodoviario");
        formRodoviario.setReloadButton(reloadButton7);
        formRodoviario.setSaveButton(saveButton7);
        formRodoviario.setLayout(new java.awt.GridBagLayout());

        labelControl75.setText("RNTRC:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        formRodoviario.add(labelControl75, gridBagConstraints);

        textControl3.setAttributeName("rntrc");
        textControl3.setEnabled(false);
        textControl3.setMaxCharacters(8);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        formRodoviario.add(textControl3, gridBagConstraints);

        labelControl76.setLabel("Data Prevista Entrega:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        formRodoviario.add(labelControl76, gridBagConstraints);

        dateControl4.setAttributeName("dataPrevistaEntrega");
        dateControl4.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        formRodoviario.add(dateControl4, gridBagConstraints);

        labelControl77.setLabel("Indicador Lotacao:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        formRodoviario.add(labelControl77, gridBagConstraints);

        labelControl78.setText("CIOT:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        formRodoviario.add(labelControl78, gridBagConstraints);

        numericControl7.setAttributeName("ciot");
        numericControl7.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        formRodoviario.add(numericControl7, gridBagConstraints);

        comboBoxControl9.setAttributeName("indicadorLotacao");
        comboBoxControl9.setDomainId("tipoTelefone");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        formRodoviario.add(comboBoxControl9, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weighty = 1.0;
        formRodoviario.add(jSeparator1, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weighty = 1.0;
        jPanel17.add(formRodoviario, gridBagConstraints);

        jPanel18.setBorder(javax.swing.BorderFactory.createTitledBorder("Cte Rodoviario"));
        jPanel18.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));
        jPanel18.add(editButton7);
        jPanel18.add(reloadButton7);
        jPanel18.add(saveButton7);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        jPanel17.add(jPanel18, gridBagConstraints);

        jTabbedPane1.addTab("Principal", jPanel17);

        jPanel2.setLayout(new java.awt.GridBagLayout());

        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder("Cte Rodoviario Occ"));
        jPanel8.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));
        jPanel8.add(insertButton1);
        jPanel8.add(editButton2);
        jPanel8.add(deleteButton1);
        jPanel8.add(reloadButton2);
        jPanel8.add(saveButton2);
        jPanel8.add(navigatorBar1);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        jPanel2.add(jPanel8, gridBagConstraints);

        gridControlOcc.setAutoLoadData(false);
        gridControlOcc.setDeleteButton(deleteButton1);
        gridControlOcc.setEditButton(editButton1);
        gridControlOcc.setFunctionId("cteRodoviarioOcc");
        gridControlOcc.setInsertButton(insertButton1);
        gridControlOcc.setInsertRowsOnTop(false);
        gridControlOcc.setNavBar(navigatorBar1);
        gridControlOcc.setReloadButton(reloadButton2);
        gridControlOcc.setSaveButton(saveButton2);
        gridControlOcc.setValueObjectClassName("com.t2tierp.cte.java.CteRodoviarioOccVO");

        textColumn3.setColumnName("serie");
        textColumn3.setColumnRequired(false);
        textColumn3.setEditableOnEdit(true);
        textColumn3.setEditableOnInsert(true);
        textColumn3.setHeaderColumnName("Serie");
        textColumn3.setHeaderFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        gridControlOcc.getColumnContainer().add(textColumn3);

        integerColumn4.setColumnName("numero");
        integerColumn4.setEditableOnEdit(true);
        integerColumn4.setEditableOnInsert(true);
        integerColumn4.setHeaderColumnName("Numero");
        integerColumn4.setHeaderFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        integerColumn4.setMaxCharacters(6);
        gridControlOcc.getColumnContainer().add(integerColumn4);

        dateColumn5.setColumnName("dataEmissao");
        dateColumn5.setEditableOnEdit(true);
        dateColumn5.setEditableOnInsert(true);
        dateColumn5.setHeaderColumnName("Data Emissao");
        dateColumn5.setHeaderFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        gridControlOcc.getColumnContainer().add(dateColumn5);

        textColumn6.setColumnName("cnpj");
        textColumn6.setEditableOnEdit(true);
        textColumn6.setEditableOnInsert(true);
        textColumn6.setHeaderColumnName("CNPJ");
        textColumn6.setHeaderFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        textColumn6.setMaxCharacters(14);
        textColumn6.setPreferredWidth(130);
        gridControlOcc.getColumnContainer().add(textColumn6);

        textColumn7.setColumnName("codigoInterno");
        textColumn7.setColumnRequired(false);
        textColumn7.setEditableOnEdit(true);
        textColumn7.setEditableOnInsert(true);
        textColumn7.setHeaderColumnName("Codigo Interno");
        textColumn7.setHeaderFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        gridControlOcc.getColumnContainer().add(textColumn7);

        textColumn8.setColumnName("ie");
        textColumn8.setEditableOnEdit(true);
        textColumn8.setEditableOnInsert(true);
        textColumn8.setHeaderColumnName("IE");
        textColumn8.setHeaderFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        gridControlOcc.getColumnContainer().add(textColumn8);

        textColumn9.setColumnName("uf");
        textColumn9.setEditableOnEdit(true);
        textColumn9.setEditableOnInsert(true);
        textColumn9.setHeaderColumnName("UF");
        textColumn9.setHeaderFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        textColumn9.setMaxCharacters(2);
        gridControlOcc.getColumnContainer().add(textColumn9);

        textColumn10.setColumnName("telefone");
        textColumn10.setColumnRequired(false);
        textColumn10.setEditableOnEdit(true);
        textColumn10.setEditableOnInsert(true);
        textColumn10.setHeaderColumnName("Telefone");
        textColumn10.setHeaderFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        gridControlOcc.getColumnContainer().add(textColumn10);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel2.add(gridControlOcc, gridBagConstraints);

        jTabbedPane1.addTab("Ordens de Coleta", jPanel2);

        jTabbedPane4.addTab("Rodoviário", jTabbedPane1);

        jPanel7.setLayout(new java.awt.GridBagLayout());
        jTabbedPane4.addTab("Dutoviário", jPanel7);

        jPanel6.setLayout(new java.awt.GridBagLayout());
        jTabbedPane4.addTab("Ferroviário", jPanel6);

        jPanel5.setLayout(new java.awt.GridBagLayout());
        jTabbedPane4.addTab("Aquaviário", jPanel5);

        jPanel4.setLayout(new java.awt.GridBagLayout());
        jTabbedPane4.addTab("Aéreo", jPanel4);

        jTabbedPane2.addTab("Modalidade", jTabbedPane4);

        jPanel11.setLayout(new java.awt.GridBagLayout());

        jPanel12.setBorder(javax.swing.BorderFactory.createTitledBorder("Cte Carga"));
        jPanel12.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));
        jPanel12.add(insertButton3);
        jPanel12.add(editButton4);
        jPanel12.add(deleteButton3);
        jPanel12.add(reloadButton4);
        jPanel12.add(saveButton4);
        jPanel12.add(navigatorBar3);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        jPanel11.add(jPanel12, gridBagConstraints);

        gridControlCarga.setAutoLoadData(false);
        gridControlCarga.setDeleteButton(deleteButton3);
        gridControlCarga.setEditButton(editButton4);
        gridControlCarga.setFunctionId("cteCarga");
        gridControlCarga.setInsertButton(insertButton3);
        gridControlCarga.setNavBar(navigatorBar3);
        gridControlCarga.setReloadButton(reloadButton4);
        gridControlCarga.setSaveButton(saveButton4);
        gridControlCarga.setValueObjectClassName("com.t2tierp.cte.java.CteCargaVO");

        comboColumn1.setColumnName("codigoUnidadeMedida");
        comboColumn1.setDomainId("cteCodigoUnidadeMedida");
        comboColumn1.setEditableOnEdit(true);
        comboColumn1.setEditableOnInsert(true);
        comboColumn1.setHeaderColumnName("Codigo Unidade Medida");
        comboColumn1.setHeaderFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        comboColumn1.setPreferredWidth(150);
        gridControlCarga.getColumnContainer().add(comboColumn1);

        textColumn5.setColumnName("tipoMedida");
        textColumn5.setEditableOnEdit(true);
        textColumn5.setEditableOnInsert(true);
        textColumn5.setHeaderColumnName("Tipo Medida");
        textColumn5.setHeaderFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        textColumn5.setMaxCharacters(20);
        textColumn5.setPreferredWidth(200);
        gridControlCarga.getColumnContainer().add(textColumn5);

        decimalColumn5.setColumnName("quantidade");
        decimalColumn5.setDecimals(2);
        decimalColumn5.setEditableOnEdit(true);
        decimalColumn5.setEditableOnInsert(true);
        decimalColumn5.setHeaderColumnName("Quantidade");
        decimalColumn5.setHeaderFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        gridControlCarga.getColumnContainer().add(decimalColumn5);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel11.add(gridControlCarga, gridBagConstraints);

        jTabbedPane2.addTab("Carga", jPanel11);

        jPanel13.setLayout(new java.awt.GridBagLayout());

        jPanel15.setBorder(javax.swing.BorderFactory.createTitledBorder("Cte Remetente"));
        jPanel15.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));
        jPanel15.add(editButton5);
        jPanel15.add(reloadButton5);
        jPanel15.add(saveButton5);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        jPanel13.add(jPanel15, gridBagConstraints);

        formRemetente.setVOClassName("com.t2tierp.cte.java.CteRemetenteVO");
        formRemetente.setEditButton(editButton5);
        formRemetente.setFunctionId("cteRemetente");
        formRemetente.setReloadButton(reloadButton5);
        formRemetente.setSaveButton(saveButton5);
        formRemetente.setLayout(new java.awt.GridBagLayout());

        labelControl79.setLabel("Cnpj:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        formRemetente.add(labelControl79, gridBagConstraints);

        textControl5.setAttributeName("cnpj");
        textControl5.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        formRemetente.add(textControl5, gridBagConstraints);

        labelControl81.setLabel("Ie:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        formRemetente.add(labelControl81, gridBagConstraints);

        textControl7.setAttributeName("ie");
        textControl7.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        formRemetente.add(textControl7, gridBagConstraints);

        labelControl82.setLabel("Nome:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        formRemetente.add(labelControl82, gridBagConstraints);

        textControl11.setAttributeName("nome");
        textControl11.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        formRemetente.add(textControl11, gridBagConstraints);

        labelControl83.setLabel("Fantasia:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        formRemetente.add(labelControl83, gridBagConstraints);

        textControl12.setAttributeName("fantasia");
        textControl12.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        formRemetente.add(textControl12, gridBagConstraints);

        labelControl84.setLabel("Telefone:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        formRemetente.add(labelControl84, gridBagConstraints);

        textControl13.setAttributeName("telefone");
        textControl13.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        formRemetente.add(textControl13, gridBagConstraints);

        labelControl85.setLabel("Logradouro:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        formRemetente.add(labelControl85, gridBagConstraints);

        textControl16.setAttributeName("logradouro");
        textControl16.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        formRemetente.add(textControl16, gridBagConstraints);

        labelControl86.setLabel("Numero:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        formRemetente.add(labelControl86, gridBagConstraints);

        textControl17.setAttributeName("numero");
        textControl17.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        formRemetente.add(textControl17, gridBagConstraints);

        labelControl87.setLabel("Complemento:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        formRemetente.add(labelControl87, gridBagConstraints);

        textControl18.setAttributeName("complemento");
        textControl18.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        formRemetente.add(textControl18, gridBagConstraints);

        labelControl88.setLabel("Bairro:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        formRemetente.add(labelControl88, gridBagConstraints);

        textControl21.setAttributeName("bairro");
        textControl21.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        formRemetente.add(textControl21, gridBagConstraints);

        labelControl90.setLabel("Nome Municipio:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        formRemetente.add(labelControl90, gridBagConstraints);

        textControl24.setAttributeName("nomeMunicipio");
        textControl24.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        formRemetente.add(textControl24, gridBagConstraints);

        labelControl91.setLabel("Uf:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        formRemetente.add(labelControl91, gridBagConstraints);

        textControl25.setAttributeName("uf");
        textControl25.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        formRemetente.add(textControl25, gridBagConstraints);

        labelControl92.setLabel("Cep:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        formRemetente.add(labelControl92, gridBagConstraints);

        textControl26.setAttributeName("cep");
        textControl26.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        formRemetente.add(textControl26, gridBagConstraints);

        labelControl95.setLabel("Email:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        formRemetente.add(labelControl95, gridBagConstraints);

        textControl32.setAttributeName("email");
        textControl32.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        formRemetente.add(textControl32, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.gridwidth = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        formRemetente.add(jSeparator3, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel13.add(formRemetente, gridBagConstraints);

        jTabbedPane2.addTab("Remetente", jPanel13);

        jPanel14.setLayout(new java.awt.GridBagLayout());

        jPanel16.setBorder(javax.swing.BorderFactory.createTitledBorder("Cte Destinatario"));
        jPanel16.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));
        jPanel16.add(editButton6);
        jPanel16.add(reloadButton6);
        jPanel16.add(saveButton6);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        jPanel14.add(jPanel16, gridBagConstraints);

        formDestinatario.setVOClassName("com.t2tierp.cte.java.CteDestinatarioVO");
        formDestinatario.setEditButton(editButton6);
        formDestinatario.setFunctionId("cteDestinatario");
        formDestinatario.setReloadButton(reloadButton6);
        formDestinatario.setSaveButton(saveButton6);
        formDestinatario.setLayout(new java.awt.GridBagLayout());

        labelControl80.setLabel("Cnpj:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        formDestinatario.add(labelControl80, gridBagConstraints);

        textControl29.setAttributeName("cnpj");
        textControl29.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        formDestinatario.add(textControl29, gridBagConstraints);

        labelControl89.setLabel("Cpf:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        formDestinatario.add(labelControl89, gridBagConstraints);

        textControl34.setAttributeName("cpf");
        textControl34.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        formDestinatario.add(textControl34, gridBagConstraints);

        labelControl93.setLabel("Ie:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        formDestinatario.add(labelControl93, gridBagConstraints);

        textControl35.setAttributeName("ie");
        textControl35.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        formDestinatario.add(textControl35, gridBagConstraints);

        labelControl94.setLabel("Nome:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        formDestinatario.add(labelControl94, gridBagConstraints);

        textControl41.setAttributeName("nome");
        textControl41.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        formDestinatario.add(textControl41, gridBagConstraints);

        labelControl96.setLabel("Fantasia:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        formDestinatario.add(labelControl96, gridBagConstraints);

        textControl42.setAttributeName("fantasia");
        textControl42.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        formDestinatario.add(textControl42, gridBagConstraints);

        labelControl97.setLabel("Logradouro:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        formDestinatario.add(labelControl97, gridBagConstraints);

        textControl43.setAttributeName("logradouro");
        textControl43.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        formDestinatario.add(textControl43, gridBagConstraints);

        labelControl98.setLabel("Numero:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        formDestinatario.add(labelControl98, gridBagConstraints);

        textControl44.setAttributeName("numero");
        textControl44.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        formDestinatario.add(textControl44, gridBagConstraints);

        labelControl99.setLabel("Complemento:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        formDestinatario.add(labelControl99, gridBagConstraints);

        textControl45.setAttributeName("complemento");
        textControl45.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        formDestinatario.add(textControl45, gridBagConstraints);

        labelControl100.setLabel("Bairro:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        formDestinatario.add(labelControl100, gridBagConstraints);

        textControl51.setAttributeName("bairro");
        textControl51.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        formDestinatario.add(textControl51, gridBagConstraints);

        labelControl101.setLabel("Uf:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        formDestinatario.add(labelControl101, gridBagConstraints);

        textControl52.setAttributeName("uf");
        textControl52.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        formDestinatario.add(textControl52, gridBagConstraints);

        labelControl102.setLabel("Nome Municipio:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        formDestinatario.add(labelControl102, gridBagConstraints);

        textControl53.setAttributeName("nomeMunicipio");
        textControl53.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        formDestinatario.add(textControl53, gridBagConstraints);

        labelControl103.setLabel("Cep:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        formDestinatario.add(labelControl103, gridBagConstraints);

        textControl55.setAttributeName("cep");
        textControl55.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        formDestinatario.add(textControl55, gridBagConstraints);

        labelControl104.setLabel("Telefone:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        formDestinatario.add(labelControl104, gridBagConstraints);

        textControl56.setAttributeName("telefone");
        textControl56.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        formDestinatario.add(textControl56, gridBagConstraints);

        labelControl105.setLabel("Email:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        formDestinatario.add(labelControl105, gridBagConstraints);

        textControl57.setAttributeName("email");
        textControl57.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        formDestinatario.add(textControl57, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.gridwidth = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        formDestinatario.add(jSeparator4, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel14.add(formDestinatario, gridBagConstraints);

        jTabbedPane2.addTab("Destinatário", jPanel14);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        getContentPane().add(jTabbedPane2, gridBagConstraints);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Cte Cabecalho"));
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

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void genericButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_genericButton1ActionPerformed
        controller.enviarCte();
    }//GEN-LAST:event_genericButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private org.openswing.swing.table.columns.client.CodLookupColumn codLookupColumn1;
    private org.openswing.swing.client.ComboBoxControl comboBoxControl1;
    private org.openswing.swing.client.ComboBoxControl comboBoxControl2;
    private org.openswing.swing.client.ComboBoxControl comboBoxControl3;
    private org.openswing.swing.client.ComboBoxControl comboBoxControl4;
    private org.openswing.swing.client.ComboBoxControl comboBoxControl5;
    private org.openswing.swing.client.ComboBoxControl comboBoxControl6;
    private org.openswing.swing.client.ComboBoxControl comboBoxControl7;
    private org.openswing.swing.client.ComboBoxControl comboBoxControl8;
    private org.openswing.swing.client.ComboBoxControl comboBoxControl9;
    private org.openswing.swing.table.columns.client.ComboColumn comboColumn1;
    private org.openswing.swing.table.columns.client.DateColumn dateColumn20;
    private org.openswing.swing.table.columns.client.DateColumn dateColumn5;
    private org.openswing.swing.client.DateControl dateControl10;
    private org.openswing.swing.client.DateControl dateControl4;
    private org.openswing.swing.table.columns.client.DecimalColumn decimalColumn5;
    private org.openswing.swing.client.DeleteButton deleteButton1;
    private org.openswing.swing.client.DeleteButton deleteButton2;
    private org.openswing.swing.client.DeleteButton deleteButton3;
    private org.openswing.swing.client.EditButton editButton1;
    private org.openswing.swing.client.EditButton editButton2;
    private org.openswing.swing.client.EditButton editButton3;
    private org.openswing.swing.client.EditButton editButton4;
    private org.openswing.swing.client.EditButton editButton5;
    private org.openswing.swing.client.EditButton editButton6;
    private org.openswing.swing.client.EditButton editButton7;
    private org.openswing.swing.form.client.Form form1;
    private org.openswing.swing.form.client.Form formDestinatario;
    private org.openswing.swing.form.client.Form formRemetente;
    private org.openswing.swing.form.client.Form formRodoviario;
    private org.openswing.swing.client.GenericButton genericButton1;
    private org.openswing.swing.client.GridControl gridControlCarga;
    private org.openswing.swing.client.GridControl gridControlDocumentos;
    private org.openswing.swing.client.GridControl gridControlOcc;
    private org.openswing.swing.client.InsertButton insertButton1;
    private org.openswing.swing.client.InsertButton insertButton2;
    private org.openswing.swing.client.InsertButton insertButton3;
    private org.openswing.swing.table.columns.client.IntegerColumn integerColumn19;
    private org.openswing.swing.table.columns.client.IntegerColumn integerColumn4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JTabbedPane jTabbedPane3;
    private javax.swing.JTabbedPane jTabbedPane4;
    private org.openswing.swing.client.LabelControl labelControl10;
    private org.openswing.swing.client.LabelControl labelControl100;
    private org.openswing.swing.client.LabelControl labelControl101;
    private org.openswing.swing.client.LabelControl labelControl102;
    private org.openswing.swing.client.LabelControl labelControl103;
    private org.openswing.swing.client.LabelControl labelControl104;
    private org.openswing.swing.client.LabelControl labelControl105;
    private org.openswing.swing.client.LabelControl labelControl11;
    private org.openswing.swing.client.LabelControl labelControl12;
    private org.openswing.swing.client.LabelControl labelControl13;
    private org.openswing.swing.client.LabelControl labelControl15;
    private org.openswing.swing.client.LabelControl labelControl20;
    private org.openswing.swing.client.LabelControl labelControl21;
    private org.openswing.swing.client.LabelControl labelControl22;
    private org.openswing.swing.client.LabelControl labelControl23;
    private org.openswing.swing.client.LabelControl labelControl28;
    private org.openswing.swing.client.LabelControl labelControl29;
    private org.openswing.swing.client.LabelControl labelControl3;
    private org.openswing.swing.client.LabelControl labelControl32;
    private org.openswing.swing.client.LabelControl labelControl35;
    private org.openswing.swing.client.LabelControl labelControl36;
    private org.openswing.swing.client.LabelControl labelControl37;
    private org.openswing.swing.client.LabelControl labelControl39;
    private org.openswing.swing.client.LabelControl labelControl4;
    private org.openswing.swing.client.LabelControl labelControl47;
    private org.openswing.swing.client.LabelControl labelControl48;
    private org.openswing.swing.client.LabelControl labelControl49;
    private org.openswing.swing.client.LabelControl labelControl5;
    private org.openswing.swing.client.LabelControl labelControl50;
    private org.openswing.swing.client.LabelControl labelControl51;
    private org.openswing.swing.client.LabelControl labelControl6;
    private org.openswing.swing.client.LabelControl labelControl7;
    private org.openswing.swing.client.LabelControl labelControl75;
    private org.openswing.swing.client.LabelControl labelControl76;
    private org.openswing.swing.client.LabelControl labelControl77;
    private org.openswing.swing.client.LabelControl labelControl78;
    private org.openswing.swing.client.LabelControl labelControl79;
    private org.openswing.swing.client.LabelControl labelControl8;
    private org.openswing.swing.client.LabelControl labelControl80;
    private org.openswing.swing.client.LabelControl labelControl81;
    private org.openswing.swing.client.LabelControl labelControl82;
    private org.openswing.swing.client.LabelControl labelControl83;
    private org.openswing.swing.client.LabelControl labelControl84;
    private org.openswing.swing.client.LabelControl labelControl85;
    private org.openswing.swing.client.LabelControl labelControl86;
    private org.openswing.swing.client.LabelControl labelControl87;
    private org.openswing.swing.client.LabelControl labelControl88;
    private org.openswing.swing.client.LabelControl labelControl89;
    private org.openswing.swing.client.LabelControl labelControl9;
    private org.openswing.swing.client.LabelControl labelControl90;
    private org.openswing.swing.client.LabelControl labelControl91;
    private org.openswing.swing.client.LabelControl labelControl92;
    private org.openswing.swing.client.LabelControl labelControl93;
    private org.openswing.swing.client.LabelControl labelControl94;
    private org.openswing.swing.client.LabelControl labelControl95;
    private org.openswing.swing.client.LabelControl labelControl96;
    private org.openswing.swing.client.LabelControl labelControl97;
    private org.openswing.swing.client.LabelControl labelControl98;
    private org.openswing.swing.client.LabelControl labelControl99;
    private org.openswing.swing.client.NavigatorBar navigatorBar1;
    private org.openswing.swing.client.NavigatorBar navigatorBar2;
    private org.openswing.swing.client.NavigatorBar navigatorBar3;
    private org.openswing.swing.client.NumericControl numericControl5;
    private org.openswing.swing.client.NumericControl numericControl52;
    private org.openswing.swing.client.NumericControl numericControl53;
    private org.openswing.swing.client.NumericControl numericControl7;
    private org.openswing.swing.client.ReloadButton reloadButton1;
    private org.openswing.swing.client.ReloadButton reloadButton2;
    private org.openswing.swing.client.ReloadButton reloadButton3;
    private org.openswing.swing.client.ReloadButton reloadButton4;
    private org.openswing.swing.client.ReloadButton reloadButton5;
    private org.openswing.swing.client.ReloadButton reloadButton6;
    private org.openswing.swing.client.ReloadButton reloadButton7;
    private org.openswing.swing.client.SaveButton saveButton1;
    private org.openswing.swing.client.SaveButton saveButton2;
    private org.openswing.swing.client.SaveButton saveButton3;
    private org.openswing.swing.client.SaveButton saveButton4;
    private org.openswing.swing.client.SaveButton saveButton5;
    private org.openswing.swing.client.SaveButton saveButton6;
    private org.openswing.swing.client.SaveButton saveButton7;
    private org.openswing.swing.client.TextAreaControl textAreaControl1;
    private org.openswing.swing.table.columns.client.TextColumn textColumn10;
    private org.openswing.swing.table.columns.client.TextColumn textColumn3;
    private org.openswing.swing.table.columns.client.TextColumn textColumn5;
    private org.openswing.swing.table.columns.client.TextColumn textColumn6;
    private org.openswing.swing.table.columns.client.TextColumn textColumn7;
    private org.openswing.swing.table.columns.client.TextColumn textColumn8;
    private org.openswing.swing.table.columns.client.TextColumn textColumn9;
    private org.openswing.swing.client.TextControl textControl10;
    private org.openswing.swing.client.TextControl textControl11;
    private org.openswing.swing.client.TextControl textControl12;
    private org.openswing.swing.client.TextControl textControl13;
    private org.openswing.swing.client.TextControl textControl14;
    private org.openswing.swing.client.TextControl textControl15;
    private org.openswing.swing.client.TextControl textControl16;
    private org.openswing.swing.client.TextControl textControl17;
    private org.openswing.swing.client.TextControl textControl18;
    private org.openswing.swing.client.TextControl textControl21;
    private org.openswing.swing.client.TextControl textControl22;
    private org.openswing.swing.client.TextControl textControl23;
    private org.openswing.swing.client.TextControl textControl24;
    private org.openswing.swing.client.TextControl textControl25;
    private org.openswing.swing.client.TextControl textControl26;
    private org.openswing.swing.client.TextControl textControl29;
    private org.openswing.swing.client.TextControl textControl3;
    private org.openswing.swing.client.TextControl textControl30;
    private org.openswing.swing.client.TextControl textControl31;
    private org.openswing.swing.client.TextControl textControl32;
    private org.openswing.swing.client.TextControl textControl34;
    private org.openswing.swing.client.TextControl textControl35;
    private org.openswing.swing.client.TextControl textControl37;
    private org.openswing.swing.client.TextControl textControl38;
    private org.openswing.swing.client.TextControl textControl39;
    private org.openswing.swing.client.TextControl textControl41;
    private org.openswing.swing.client.TextControl textControl42;
    private org.openswing.swing.client.TextControl textControl43;
    private org.openswing.swing.client.TextControl textControl44;
    private org.openswing.swing.client.TextControl textControl45;
    private org.openswing.swing.client.TextControl textControl49;
    private org.openswing.swing.client.TextControl textControl5;
    private org.openswing.swing.client.TextControl textControl50;
    private org.openswing.swing.client.TextControl textControl51;
    private org.openswing.swing.client.TextControl textControl52;
    private org.openswing.swing.client.TextControl textControl53;
    private org.openswing.swing.client.TextControl textControl55;
    private org.openswing.swing.client.TextControl textControl56;
    private org.openswing.swing.client.TextControl textControl57;
    private org.openswing.swing.client.TextControl textControl6;
    private org.openswing.swing.client.TextControl textControl7;
    private org.openswing.swing.client.TextControl textControl8;
    private org.openswing.swing.client.TextControl textControl9;
    // End of variables declaration//GEN-END:variables





}
