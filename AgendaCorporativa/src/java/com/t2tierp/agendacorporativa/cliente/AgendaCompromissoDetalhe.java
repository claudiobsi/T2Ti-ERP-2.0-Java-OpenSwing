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
package com.t2tierp.agendacorporativa.cliente;

import com.t2tierp.padrao.cliente.LookupDataLocatorGenerico;
import java.awt.Dimension;
import javax.swing.ImageIcon;
import javax.swing.text.MaskFormatter;
import org.openswing.swing.lookup.client.LookupController;
import org.openswing.swing.mdi.client.InternalFrame;
import org.openswing.swing.util.client.ClientSettings;
import org.openswing.swing.util.client.ClientUtils;

public class AgendaCompromissoDetalhe extends InternalFrame {

    private AgendaCompromissoDetalheController controller;
    
    private LookupController colaboradorController = new LookupController();
    private LookupController categoriaController = new LookupController();
    private LookupController colaboradorConvidadoController = new LookupController();
    private LookupController salaReuniaoController = new LookupController();
    private AgendaCompromissoConvidadoGridController gridConvidadoController;
    private ReuniaoSalaEventoDetalheController reuniaoController;
    
    public AgendaCompromissoDetalhe(AgendaCompromissoDetalheController controller) {
        initComponents();

        genericButton1.setToolTipText("Visualizar Agenda");
        this.controller = controller;
        form1.setFormController(controller);
        
        gridConvidadoController = new AgendaCompromissoConvidadoGridController(this);
        gridControl1.setController(gridConvidadoController);
        gridControl1.setGridDataLocator(gridConvidadoController);
        
        reuniaoController = new ReuniaoSalaEventoDetalheController();
        form3.setFormController(reuniaoController);
        
        formattedTextControl1.setEnabled(false);
        try {
            MaskFormatter mask = new MaskFormatter("##:##:##");
            mask.setValidCharacters("0123456789");
            formattedTextControl1.setFormatter(mask);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
        /*
         * Configurações do lookup do coladorador
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
        codLookupControl2.setLookupController(colaboradorController);        
        
        /*
         * Configurações do lookup da categoria
         */
        categoriaController.setLookupValueObjectClassName("com.t2tierp.agendacorporativa.java.AgendaCategoriaCompromissoVO");
        categoriaController.addLookup2ParentLink("id", "agendaCategoriaCompromisso.id");
        categoriaController.addLookup2ParentLink("nome", "agendaCategoriaCompromisso.nome");
        categoriaController.setHeaderColumnName("id", "ID");
        categoriaController.setHeaderColumnName("nome", "Nome");
        categoriaController.setFrameTitle("Importa Categoria");

        categoriaController.setVisibleStatusPanel(true);
        categoriaController.setVisibleColumn("id", true);
        categoriaController.setVisibleColumn("nome", true);
        categoriaController.setFramePreferedSize(new Dimension(600, 500));

        categoriaController.setLookupDataLocator(new LookupDataLocatorGenerico(categoriaController.getLookupValueObjectClassName()));
        codLookupControl3.setLookupController(categoriaController);        
        
        /*
         * Configurações do lookup do convidado
         */
        colaboradorConvidadoController.setLookupValueObjectClassName("com.t2tierp.cadastros.java.ColaboradorVO");
        colaboradorConvidadoController.addLookup2ParentLink("id", "colaborador.id");
        colaboradorConvidadoController.addLookup2ParentLink("pessoa.nome", "colaborador.pessoa.nome");
        colaboradorConvidadoController.setHeaderColumnName("id", "ID");
        colaboradorConvidadoController.setHeaderColumnName("pessoa.nome", "Nome");
        colaboradorConvidadoController.setFrameTitle("Importa Colaborador");

        colaboradorConvidadoController.setVisibleStatusPanel(true);
        colaboradorConvidadoController.setVisibleColumn("id", true);
        colaboradorConvidadoController.setVisibleColumn("pessoa.nome", true);
        colaboradorConvidadoController.setFramePreferedSize(new Dimension(600, 500));

        colaboradorConvidadoController.setLookupDataLocator(new LookupDataLocatorGenerico(colaboradorConvidadoController.getLookupValueObjectClassName()));
        codLookupColumn1.setLookupController(colaboradorConvidadoController);        

        /*
         * Configurações do lookup da sala de reuniao
         */
        salaReuniaoController.setLookupValueObjectClassName("com.t2tierp.agendacorporativa.java.ReuniaoSalaVO");
        salaReuniaoController.addLookup2ParentLink("id", "reuniaoSala.id");
        salaReuniaoController.addLookup2ParentLink("predio", "reuniaoSala.predio");
        salaReuniaoController.addLookup2ParentLink("andar", "reuniaoSala.andar");
        salaReuniaoController.addLookup2ParentLink("numero", "reuniaoSala.numero");
        salaReuniaoController.setHeaderColumnName("id", "ID");
        salaReuniaoController.setHeaderColumnName("predio", "Predio");
        salaReuniaoController.setHeaderColumnName("andar", "Andar");
        salaReuniaoController.setHeaderColumnName("numero", "Numero");
        salaReuniaoController.setFrameTitle("Importa Sala Reuniao");

        salaReuniaoController.setVisibleStatusPanel(true);
        salaReuniaoController.setVisibleColumn("id", true);
        salaReuniaoController.setVisibleColumn("predio", true);
        salaReuniaoController.setVisibleColumn("andar", true);
        salaReuniaoController.setVisibleColumn("numero", true);
        salaReuniaoController.setFramePreferedSize(new Dimension(600, 500));

        salaReuniaoController.setLookupDataLocator(new LookupDataLocatorGenerico(salaReuniaoController.getLookupValueObjectClassName()));
        codLookupControl6.setLookupController(salaReuniaoController);        
        
    }

    /**
     * @return the form1
     */
    public org.openswing.swing.form.client.Form getForm1() {
        return form1;
    }

    public org.openswing.swing.form.client.Form getFormReuniao() {
        return form3;
    }

    public AgendaCompromissoConvidadoGridController getGridConvidadoController() {
        return gridConvidadoController;
    }
    
    public ReuniaoSalaEventoDetalheController getReuniaoController() {
        return reuniaoController;
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
        genericButton1 = new org.openswing.swing.client.GenericButton(new ImageIcon(ClientUtils.getImage(ClientSettings.CALENDAR)));
        form1 = new org.openswing.swing.form.client.Form();
        labelControl1 = new org.openswing.swing.client.LabelControl();
        codLookupControl2 = new org.openswing.swing.client.CodLookupControl();
        textControl2 = new org.openswing.swing.client.TextControl();
        labelControl3 = new org.openswing.swing.client.LabelControl();
        codLookupControl3 = new org.openswing.swing.client.CodLookupControl();
        textControl3 = new org.openswing.swing.client.TextControl();
        labelControl5 = new org.openswing.swing.client.LabelControl();
        dateControl4 = new org.openswing.swing.client.DateControl();
        labelControl6 = new org.openswing.swing.client.LabelControl();
        labelControl7 = new org.openswing.swing.client.LabelControl();
        numericControl6 = new org.openswing.swing.client.NumericControl();
        labelControl8 = new org.openswing.swing.client.LabelControl();
        textControl7 = new org.openswing.swing.client.TextControl();
        labelControl9 = new org.openswing.swing.client.LabelControl();
        textControl8 = new org.openswing.swing.client.TextControl();
        labelControl10 = new org.openswing.swing.client.LabelControl();
        comboBoxControl1 = new org.openswing.swing.client.ComboBoxControl();
        formattedTextControl1 = new org.openswing.swing.client.FormattedTextControl();
        labelControl11 = new org.openswing.swing.client.LabelControl();
        comboBoxControl2 = new org.openswing.swing.client.ComboBoxControl();
        labelControl12 = new org.openswing.swing.client.LabelControl();
        numericControl1 = new org.openswing.swing.client.NumericControl();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        insertButton1 = new org.openswing.swing.client.InsertButton();
        deleteButton1 = new org.openswing.swing.client.DeleteButton();
        reloadButton2 = new org.openswing.swing.client.ReloadButton();
        saveButton2 = new org.openswing.swing.client.SaveButton();
        navigatorBar1 = new org.openswing.swing.client.NavigatorBar();
        gridControl1 = new org.openswing.swing.client.GridControl();
        codLookupColumn1 = new org.openswing.swing.table.columns.client.CodLookupColumn();
        jPanel4 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        editButton2 = new org.openswing.swing.client.EditButton();
        reloadButton3 = new org.openswing.swing.client.ReloadButton();
        saveButton3 = new org.openswing.swing.client.SaveButton();
        form3 = new org.openswing.swing.form.client.Form();
        labelControl14 = new org.openswing.swing.client.LabelControl();
        codLookupControl6 = new org.openswing.swing.client.CodLookupControl();
        textControl6 = new org.openswing.swing.client.TextControl();
        labelControl16 = new org.openswing.swing.client.LabelControl();
        dateControl7 = new org.openswing.swing.client.DateControl();
        jSeparator3 = new javax.swing.JSeparator();
        textControl9 = new org.openswing.swing.client.TextControl();
        textControl10 = new org.openswing.swing.client.TextControl();
        labelControl15 = new org.openswing.swing.client.LabelControl();
        labelControl17 = new org.openswing.swing.client.LabelControl();
        labelControl18 = new org.openswing.swing.client.LabelControl();

        setTitle("T2Ti ERP - Agenda Corporativa");
        setPreferredSize(new java.awt.Dimension(700, 400));
        getContentPane().setLayout(new java.awt.GridBagLayout());

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Agenda Compromisso"));
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

        form1.setVOClassName("com.t2tierp.agendacorporativa.java.AgendaCompromissoVO");
        form1.setEditButton(editButton1);
        form1.setFunctionId("agendaCompromisso");
        form1.setReloadButton(reloadButton1);
        form1.setSaveButton(saveButton1);
        form1.setLayout(new java.awt.GridBagLayout());

        labelControl1.setText("Colaborador:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        form1.add(labelControl1, gridBagConstraints);

        codLookupControl2.setAllowOnlyNumbers(true);
        codLookupControl2.setAttributeName("colaborador.id");
        codLookupControl2.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        form1.add(codLookupControl2, gridBagConstraints);

        textControl2.setAttributeName("colaborador.pessoa.nome");
        textControl2.setEnabled(false);
        textControl2.setEnabledOnEdit(false);
        textControl2.setEnabledOnInsert(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        form1.add(textControl2, gridBagConstraints);

        labelControl3.setText("Categoria:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        form1.add(labelControl3, gridBagConstraints);

        codLookupControl3.setAllowOnlyNumbers(true);
        codLookupControl3.setAttributeName("agendaCategoriaCompromisso.id");
        codLookupControl3.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        form1.add(codLookupControl3, gridBagConstraints);

        textControl3.setAttributeName("agendaCategoriaCompromisso.nome");
        textControl3.setEnabled(false);
        textControl3.setEnabledOnEdit(false);
        textControl3.setEnabledOnInsert(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 200;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        form1.add(textControl3, gridBagConstraints);

        labelControl5.setLabel("Data Compromisso:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        form1.add(labelControl5, gridBagConstraints);

        dateControl4.setAttributeName("dataCompromisso");
        dateControl4.setEnabled(false);
        dateControl4.setLinkLabel(labelControl5);
        dateControl4.setRequired(true);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        form1.add(dateControl4, gridBagConstraints);

        labelControl6.setLabel("Hora:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        form1.add(labelControl6, gridBagConstraints);

        labelControl7.setLabel("Duracao:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        form1.add(labelControl7, gridBagConstraints);

        numericControl6.setAttributeName("duracao");
        numericControl6.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        form1.add(numericControl6, gridBagConstraints);

        labelControl8.setLabel("Onde:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        form1.add(labelControl8, gridBagConstraints);

        textControl7.setAttributeName("onde");
        textControl7.setEnabled(false);
        textControl7.setMaxCharacters(100);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        form1.add(textControl7, gridBagConstraints);

        labelControl9.setLabel("Descricao:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        form1.add(labelControl9, gridBagConstraints);

        textControl8.setAttributeName("descricao");
        textControl8.setEnabled(false);
        textControl8.setLinkLabel(labelControl9);
        textControl8.setMaxCharacters(100);
        textControl8.setRequired(true);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        form1.add(textControl8, gridBagConstraints);

        labelControl10.setLabel("Tipo:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        form1.add(labelControl10, gridBagConstraints);

        comboBoxControl1.setAttributeName("tipo");
        comboBoxControl1.setDomainId("compromissoTipo");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        form1.add(comboBoxControl1, gridBagConstraints);

        formattedTextControl1.setAttributeName("hora");
        formattedTextControl1.setLinkLabel(labelControl6);
        formattedTextControl1.setRequired(true);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        form1.add(formattedTextControl1, gridBagConstraints);

        labelControl11.setText("Recorrente:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        form1.add(labelControl11, gridBagConstraints);

        comboBoxControl2.setAttributeName("recorrente");
        comboBoxControl2.setDomainId("compromissoRecorrente");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        form1.add(comboBoxControl2, gridBagConstraints);

        labelControl12.setText("Qtde Ocorrencia:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        form1.add(labelControl12, gridBagConstraints);

        numericControl1.setAttributeName("quantidadeOcorrencia");
        numericControl1.setEnabled(false);
        numericControl1.setMinValue(1.0);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        form1.add(numericControl1, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        getContentPane().add(form1, gridBagConstraints);

        jPanel2.setLayout(new java.awt.GridBagLayout());

        jPanel3.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));
        jPanel3.add(insertButton1);
        jPanel3.add(deleteButton1);
        jPanel3.add(reloadButton2);
        jPanel3.add(saveButton2);
        jPanel3.add(navigatorBar1);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        jPanel2.add(jPanel3, gridBagConstraints);

        gridControl1.setAutoLoadData(false);
        gridControl1.setDeleteButton(deleteButton1);
        gridControl1.setFunctionId("agendaCompromissoConvidado");
        gridControl1.setInsertButton(insertButton1);
        gridControl1.setNavBar(navigatorBar1);
        gridControl1.setReloadButton(reloadButton2);
        gridControl1.setSaveButton(saveButton2);
        gridControl1.setValueObjectClassName("com.t2tierp.agendacorporativa.java.AgendaCompromissoConvidadoVO");

        codLookupColumn1.setColumnName("colaborador.pessoa.nome");
        codLookupColumn1.setEditableOnEdit(true);
        codLookupColumn1.setEditableOnInsert(true);
        codLookupColumn1.setHeaderColumnName("Colaborador");
        codLookupColumn1.setHeaderFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        codLookupColumn1.setPreferredWidth(600);
        gridControl1.getColumnContainer().add(codLookupColumn1);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel2.add(gridControl1, gridBagConstraints);

        jTabbedPane1.addTab("Convidados", jPanel2);

        jPanel4.setLayout(new java.awt.GridBagLayout());

        jPanel5.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));
        jPanel5.add(editButton2);
        jPanel5.add(reloadButton3);
        jPanel5.add(saveButton3);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        jPanel4.add(jPanel5, gridBagConstraints);

        form3.setVOClassName("com.t2tierp.agendacorporativa.java.ReuniaoSalaEventoVO");
        form3.setEditButton(editButton2);
        form3.setFunctionId("reuniaoSalaEvento");
        form3.setReloadButton(reloadButton3);
        form3.setSaveButton(saveButton3);
        form3.setLayout(new java.awt.GridBagLayout());

        labelControl14.setText("Predio:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        form3.add(labelControl14, gridBagConstraints);

        codLookupControl6.setAllowOnlyNumbers(true);
        codLookupControl6.setAttributeName("reuniaoSala.id");
        codLookupControl6.setEnabled(false);
        codLookupControl6.setLinkLabel(labelControl18);
        codLookupControl6.setRequired(true);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        form3.add(codLookupControl6, gridBagConstraints);

        textControl6.setAttributeName("reuniaoSala.andar");
        textControl6.setEnabled(false);
        textControl6.setEnabledOnEdit(false);
        textControl6.setEnabledOnInsert(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        form3.add(textControl6, gridBagConstraints);

        labelControl16.setLabel("Data Reserva:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        form3.add(labelControl16, gridBagConstraints);

        dateControl7.setAttributeName("dataReserva");
        dateControl7.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        form3.add(dateControl7, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weighty = 1.0;
        form3.add(jSeparator3, gridBagConstraints);

        textControl9.setAttributeName("reuniaoSala.numero");
        textControl9.setEnabled(false);
        textControl9.setEnabledOnEdit(false);
        textControl9.setEnabledOnInsert(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        form3.add(textControl9, gridBagConstraints);

        textControl10.setAttributeName("reuniaoSala.predio");
        textControl10.setEnabled(false);
        textControl10.setEnabledOnEdit(false);
        textControl10.setEnabledOnInsert(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        form3.add(textControl10, gridBagConstraints);

        labelControl15.setText("Andar:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        form3.add(labelControl15, gridBagConstraints);

        labelControl17.setText("Numero:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        form3.add(labelControl17, gridBagConstraints);

        labelControl18.setText("Sala Reuniao:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        form3.add(labelControl18, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel4.add(form3, gridBagConstraints);

        jTabbedPane1.addTab("Reuniao", jPanel4);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weighty = 1.0;
        getContentPane().add(jTabbedPane1, gridBagConstraints);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void genericButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_genericButton1ActionPerformed
        controller.visualizaAgenda();
    }//GEN-LAST:event_genericButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private org.openswing.swing.table.columns.client.CodLookupColumn codLookupColumn1;
    private org.openswing.swing.client.CodLookupControl codLookupControl2;
    private org.openswing.swing.client.CodLookupControl codLookupControl3;
    private org.openswing.swing.client.CodLookupControl codLookupControl6;
    private org.openswing.swing.client.ComboBoxControl comboBoxControl1;
    private org.openswing.swing.client.ComboBoxControl comboBoxControl2;
    private org.openswing.swing.client.DateControl dateControl4;
    private org.openswing.swing.client.DateControl dateControl7;
    private org.openswing.swing.client.DeleteButton deleteButton1;
    private org.openswing.swing.client.EditButton editButton1;
    private org.openswing.swing.client.EditButton editButton2;
    private org.openswing.swing.form.client.Form form1;
    private org.openswing.swing.form.client.Form form3;
    private org.openswing.swing.client.FormattedTextControl formattedTextControl1;
    private org.openswing.swing.client.GenericButton genericButton1;
    private org.openswing.swing.client.GridControl gridControl1;
    private org.openswing.swing.client.InsertButton insertButton1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private org.openswing.swing.client.LabelControl labelControl1;
    private org.openswing.swing.client.LabelControl labelControl10;
    private org.openswing.swing.client.LabelControl labelControl11;
    private org.openswing.swing.client.LabelControl labelControl12;
    private org.openswing.swing.client.LabelControl labelControl14;
    private org.openswing.swing.client.LabelControl labelControl15;
    private org.openswing.swing.client.LabelControl labelControl16;
    private org.openswing.swing.client.LabelControl labelControl17;
    private org.openswing.swing.client.LabelControl labelControl18;
    private org.openswing.swing.client.LabelControl labelControl3;
    private org.openswing.swing.client.LabelControl labelControl5;
    private org.openswing.swing.client.LabelControl labelControl6;
    private org.openswing.swing.client.LabelControl labelControl7;
    private org.openswing.swing.client.LabelControl labelControl8;
    private org.openswing.swing.client.LabelControl labelControl9;
    private org.openswing.swing.client.NavigatorBar navigatorBar1;
    private org.openswing.swing.client.NumericControl numericControl1;
    private org.openswing.swing.client.NumericControl numericControl6;
    private org.openswing.swing.client.ReloadButton reloadButton1;
    private org.openswing.swing.client.ReloadButton reloadButton2;
    private org.openswing.swing.client.ReloadButton reloadButton3;
    private org.openswing.swing.client.SaveButton saveButton1;
    private org.openswing.swing.client.SaveButton saveButton2;
    private org.openswing.swing.client.SaveButton saveButton3;
    private org.openswing.swing.client.TextControl textControl10;
    private org.openswing.swing.client.TextControl textControl2;
    private org.openswing.swing.client.TextControl textControl3;
    private org.openswing.swing.client.TextControl textControl6;
    private org.openswing.swing.client.TextControl textControl7;
    private org.openswing.swing.client.TextControl textControl8;
    private org.openswing.swing.client.TextControl textControl9;
    // End of variables declaration//GEN-END:variables


}
