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
 package com.t2tierp.ponto.cliente;

import com.t2tierp.padrao.cliente.LookupDataLocatorGenerico;
import java.awt.Dimension;
import javax.swing.text.MaskFormatter;
import org.openswing.swing.lookup.client.LookupController;
import org.openswing.swing.mdi.client.InternalFrame;

public class PontoEscalaDetalhe extends InternalFrame {

    private PontoTurmaGridController turmaController;
    private LookupController domingoController = new LookupController();
    private LookupController segundaController = new LookupController();
    private LookupController tercaController = new LookupController();
    private LookupController quartaController = new LookupController();
    private LookupController quintaController = new LookupController();
    private LookupController sextaController = new LookupController();
    private LookupController sabadoController = new LookupController();

    public PontoEscalaDetalhe(PontoEscalaDetalheController controller) {
        initComponents();

        form1.setFormController(controller);

        turmaController = new PontoTurmaGridController(this);
        gridControl1.setController(turmaController);
        gridControl1.setGridDataLocator(turmaController);

        formattedTextControl1.setEnabled(false);
        formattedTextControl2.setEnabled(false);
        try {
            MaskFormatter mask = new MaskFormatter("##:##:##");
            mask.setValidCharacters("0123456789");
            formattedTextControl1.setFormatter(mask);

            mask = new MaskFormatter("##:##:##");
            mask.setValidCharacters("0123456789");
            formattedTextControl2.setFormatter(mask);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        /*
         * Configurações do lookup do domingo
         */
        domingoController.setLookupValueObjectClassName("com.t2tierp.ponto.java.PontoHorarioVO");
        domingoController.addLookup2ParentLink("codigo", "codigoHorarioDomingo");
        domingoController.setHeaderColumnName("id", "ID");
        domingoController.setHeaderColumnName("nome", "Nome");
        domingoController.setHeaderColumnName("codigo", "Código");
        domingoController.setFrameTitle("Importa Horario");

        domingoController.setVisibleStatusPanel(true);
        domingoController.setVisibleColumn("id", true);
        domingoController.setVisibleColumn("nome", true);
        domingoController.setVisibleColumn("codigo", true);
        domingoController.setFramePreferedSize(new Dimension(600, 500));

        domingoController.setLookupDataLocator(new LookupDataLocatorGenerico(domingoController.getLookupValueObjectClassName()));
        codLookupControl7.setLookupController(domingoController);
        
        /*
         * Configurações do lookup da segunda
         */
        segundaController.setLookupValueObjectClassName("com.t2tierp.ponto.java.PontoHorarioVO");
        segundaController.addLookup2ParentLink("codigo", "codigoHorarioSegunda");
        segundaController.setHeaderColumnName("id", "ID");
        segundaController.setHeaderColumnName("nome", "Nome");
        segundaController.setHeaderColumnName("codigo", "Código");
        segundaController.setFrameTitle("Importa Horario");

        segundaController.setVisibleStatusPanel(true);
        segundaController.setVisibleColumn("id", true);
        segundaController.setVisibleColumn("nome", true);
        segundaController.setVisibleColumn("codigo", true);
        segundaController.setFramePreferedSize(new Dimension(600, 500));

        segundaController.setLookupDataLocator(new LookupDataLocatorGenerico(segundaController.getLookupValueObjectClassName()));
        codLookupControl1.setLookupController(segundaController);


        /*
         * Configurações do lookup da terca
         */
        tercaController.setLookupValueObjectClassName("com.t2tierp.ponto.java.PontoHorarioVO");
        tercaController.addLookup2ParentLink("codigo", "codigoHorarioTerca");
        tercaController.setHeaderColumnName("id", "ID");
        tercaController.setHeaderColumnName("nome", "Nome");
        tercaController.setHeaderColumnName("codigo", "Código");
        tercaController.setFrameTitle("Importa Horario");

        tercaController.setVisibleStatusPanel(true);
        tercaController.setVisibleColumn("id", true);
        tercaController.setVisibleColumn("nome", true);
        tercaController.setVisibleColumn("codigo", true);
        tercaController.setFramePreferedSize(new Dimension(600, 500));

        tercaController.setLookupDataLocator(new LookupDataLocatorGenerico(tercaController.getLookupValueObjectClassName()));
        codLookupControl2.setLookupController(tercaController);

        /*
         * Configurações do lookup da quarta
         */
        quartaController.setLookupValueObjectClassName("com.t2tierp.ponto.java.PontoHorarioVO");
        quartaController.addLookup2ParentLink("codigo", "codigoHorarioQuarta");
        quartaController.setHeaderColumnName("id", "ID");
        quartaController.setHeaderColumnName("nome", "Nome");
        quartaController.setHeaderColumnName("codigo", "Código");
        quartaController.setFrameTitle("Importa Horario");

        quartaController.setVisibleStatusPanel(true);
        quartaController.setVisibleColumn("id", true);
        quartaController.setVisibleColumn("nome", true);
        quartaController.setVisibleColumn("codigo", true);
        quartaController.setFramePreferedSize(new Dimension(600, 500));

        quartaController.setLookupDataLocator(new LookupDataLocatorGenerico(quartaController.getLookupValueObjectClassName()));
        codLookupControl6.setLookupController(quartaController);

        /*
         * Configurações do lookup da quinta
         */
        quintaController.setLookupValueObjectClassName("com.t2tierp.ponto.java.PontoHorarioVO");
        quintaController.addLookup2ParentLink("codigo", "codigoHorarioQuinta");
        quintaController.setHeaderColumnName("id", "ID");
        quintaController.setHeaderColumnName("nome", "Nome");
        quintaController.setHeaderColumnName("codigo", "Código");
        quintaController.setFrameTitle("Importa Horario");

        quintaController.setVisibleStatusPanel(true);
        quintaController.setVisibleColumn("id", true);
        quintaController.setVisibleColumn("nome", true);
        quintaController.setVisibleColumn("codigo", true);
        quintaController.setFramePreferedSize(new Dimension(600, 500));

        quintaController.setLookupDataLocator(new LookupDataLocatorGenerico(quintaController.getLookupValueObjectClassName()));
        codLookupControl5.setLookupController(quintaController);

        /*
         * Configurações do lookup da sexta
         */
        sextaController.setLookupValueObjectClassName("com.t2tierp.ponto.java.PontoHorarioVO");
        sextaController.addLookup2ParentLink("codigo", "codigoHorarioSexta");
        sextaController.setHeaderColumnName("id", "ID");
        sextaController.setHeaderColumnName("nome", "Nome");
        sextaController.setHeaderColumnName("codigo", "Código");
        sextaController.setFrameTitle("Importa Horario");

        sextaController.setVisibleStatusPanel(true);
        sextaController.setVisibleColumn("id", true);
        sextaController.setVisibleColumn("nome", true);
        sextaController.setVisibleColumn("codigo", true);
        sextaController.setFramePreferedSize(new Dimension(600, 500));

        sextaController.setLookupDataLocator(new LookupDataLocatorGenerico(sextaController.getLookupValueObjectClassName()));
        codLookupControl4.setLookupController(sextaController);
        
        /*
         * Configurações do lookup do sabado
         */
        sabadoController.setLookupValueObjectClassName("com.t2tierp.ponto.java.PontoHorarioVO");
        sabadoController.addLookup2ParentLink("codigo", "codigoHorarioSabado");
        sabadoController.setHeaderColumnName("id", "ID");
        sabadoController.setHeaderColumnName("nome", "Nome");
        sabadoController.setHeaderColumnName("codigo", "Código");
        sabadoController.setFrameTitle("Importa Horario");

        sabadoController.setVisibleStatusPanel(true);
        sabadoController.setVisibleColumn("id", true);
        sabadoController.setVisibleColumn("nome", true);
        sabadoController.setVisibleColumn("codigo", true);
        sabadoController.setFramePreferedSize(new Dimension(600, 500));

        sabadoController.setLookupDataLocator(new LookupDataLocatorGenerico(sabadoController.getLookupValueObjectClassName()));
        codLookupControl3.setLookupController(sabadoController);

    }

    /**
     * @return the form1
     */
    public org.openswing.swing.form.client.Form getForm1() {
        return form1;
    }

    /**
     * @return the gridControl1
     */
    public org.openswing.swing.client.GridControl getGridControl1() {
        return gridControl1;
    }

    /**
     * @return the turmaController
     */
    public PontoTurmaGridController getTurmaController() {
        return turmaController;
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
        form1 = new org.openswing.swing.form.client.Form();
        labelControl1 = new org.openswing.swing.client.LabelControl();
        textControl3 = new org.openswing.swing.client.TextControl();
        labelControl2 = new org.openswing.swing.client.LabelControl();
        labelControl3 = new org.openswing.swing.client.LabelControl();
        labelControl4 = new org.openswing.swing.client.LabelControl();
        labelControl5 = new org.openswing.swing.client.LabelControl();
        labelControl6 = new org.openswing.swing.client.LabelControl();
        labelControl7 = new org.openswing.swing.client.LabelControl();
        labelControl8 = new org.openswing.swing.client.LabelControl();
        labelControl9 = new org.openswing.swing.client.LabelControl();
        labelControl10 = new org.openswing.swing.client.LabelControl();
        formattedTextControl1 = new org.openswing.swing.client.FormattedTextControl();
        formattedTextControl2 = new org.openswing.swing.client.FormattedTextControl();
        codLookupControl1 = new org.openswing.swing.client.CodLookupControl();
        codLookupControl2 = new org.openswing.swing.client.CodLookupControl();
        codLookupControl3 = new org.openswing.swing.client.CodLookupControl();
        codLookupControl4 = new org.openswing.swing.client.CodLookupControl();
        codLookupControl5 = new org.openswing.swing.client.CodLookupControl();
        codLookupControl6 = new org.openswing.swing.client.CodLookupControl();
        codLookupControl7 = new org.openswing.swing.client.CodLookupControl();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        insertButton1 = new org.openswing.swing.client.InsertButton();
        editButton2 = new org.openswing.swing.client.EditButton();
        deleteButton1 = new org.openswing.swing.client.DeleteButton();
        reloadButton2 = new org.openswing.swing.client.ReloadButton();
        saveButton2 = new org.openswing.swing.client.SaveButton();
        gridControl1 = new org.openswing.swing.client.GridControl();
        textColumn3 = new org.openswing.swing.table.columns.client.TextColumn();
        textColumn4 = new org.openswing.swing.table.columns.client.TextColumn();

        setResizable(false);
        setTitle("T2Ti ERP - Ponto Eletrônico");
        setPreferredSize(new java.awt.Dimension(700, 400));
        getContentPane().setLayout(new java.awt.GridBagLayout());

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Ponto Escala"));
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

        form1.setVOClassName("com.t2tierp.ponto.java.PontoEscalaVO");
        form1.setEditButton(editButton1);
        form1.setReloadButton(reloadButton1);
        form1.setSaveButton(saveButton1);
        form1.setLayout(new java.awt.GridBagLayout());

        labelControl1.setLabel("Nome:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        form1.add(labelControl1, gridBagConstraints);

        textControl3.setAttributeName("nome");
        textControl3.setEnabled(false);
        textControl3.setMaxCharacters(50);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        form1.add(textControl3, gridBagConstraints);

        labelControl2.setLabel("Desconto Hora Dia:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        form1.add(labelControl2, gridBagConstraints);

        labelControl3.setLabel("Desconto DSR:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        form1.add(labelControl3, gridBagConstraints);

        labelControl4.setLabel("Codigo Horario Domingo:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        form1.add(labelControl4, gridBagConstraints);

        labelControl5.setLabel("Codigo Horario Segunda:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        form1.add(labelControl5, gridBagConstraints);

        labelControl6.setLabel("Codigo Horario Terca:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        form1.add(labelControl6, gridBagConstraints);

        labelControl7.setLabel("Codigo Horario Quarta:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        form1.add(labelControl7, gridBagConstraints);

        labelControl8.setLabel("Codigo Horario Quinta:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        form1.add(labelControl8, gridBagConstraints);

        labelControl9.setLabel("Codigo Horario Sexta:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        form1.add(labelControl9, gridBagConstraints);

        labelControl10.setLabel("Codigo Horario Sabado:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        form1.add(labelControl10, gridBagConstraints);

        formattedTextControl1.setAttributeName("descontoDsr");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        form1.add(formattedTextControl1, gridBagConstraints);

        formattedTextControl2.setAttributeName("descontoHoraDia");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        form1.add(formattedTextControl2, gridBagConstraints);

        codLookupControl1.setAttributeName("codigoHorarioSegunda");
        codLookupControl1.setEnableCodBox(false);
        codLookupControl1.setLinkLabel(labelControl5);
        codLookupControl1.setRequired(true);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        form1.add(codLookupControl1, gridBagConstraints);

        codLookupControl2.setAttributeName("codigoHorarioTerca");
        codLookupControl2.setEnableCodBox(false);
        codLookupControl2.setLinkLabel(labelControl6);
        codLookupControl2.setRequired(true);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        form1.add(codLookupControl2, gridBagConstraints);

        codLookupControl3.setAttributeName("codigoHorarioSabado");
        codLookupControl3.setEnableCodBox(false);
        codLookupControl3.setLinkLabel(labelControl10);
        codLookupControl3.setRequired(true);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        form1.add(codLookupControl3, gridBagConstraints);

        codLookupControl4.setAttributeName("codigoHorarioSexta");
        codLookupControl4.setEnableCodBox(false);
        codLookupControl4.setLinkLabel(labelControl9);
        codLookupControl4.setRequired(true);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        form1.add(codLookupControl4, gridBagConstraints);

        codLookupControl5.setAttributeName("codigoHorarioQuinta");
        codLookupControl5.setEnableCodBox(false);
        codLookupControl5.setLinkLabel(labelControl8);
        codLookupControl5.setRequired(true);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        form1.add(codLookupControl5, gridBagConstraints);

        codLookupControl6.setAttributeName("codigoHorarioQuarta");
        codLookupControl6.setEnableCodBox(false);
        codLookupControl6.setLinkLabel(labelControl7);
        codLookupControl6.setRequired(true);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        form1.add(codLookupControl6, gridBagConstraints);

        codLookupControl7.setAttributeName("codigoHorarioDomingo");
        codLookupControl7.setEnableCodBox(false);
        codLookupControl7.setLinkLabel(labelControl4);
        codLookupControl7.setRequired(true);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        form1.add(codLookupControl7, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        getContentPane().add(form1, gridBagConstraints);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Turma"));
        jPanel2.setLayout(new java.awt.GridBagLayout());

        jPanel3.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));
        jPanel3.add(insertButton1);
        jPanel3.add(editButton2);
        jPanel3.add(deleteButton1);
        jPanel3.add(reloadButton2);
        jPanel3.add(saveButton2);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        jPanel2.add(jPanel3, gridBagConstraints);

        gridControl1.setAutoLoadData(false);
        gridControl1.setDeleteButton(deleteButton1);
        gridControl1.setEditButton(editButton2);
        gridControl1.setInsertButton(insertButton1);
        gridControl1.setReloadButton(reloadButton2);
        gridControl1.setSaveButton(saveButton2);
        gridControl1.setValueObjectClassName("com.t2tierp.ponto.java.PontoTurmaVO");
        gridControl1.getColumnContainer().setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        textColumn3.setColumnName("codigo");
        textColumn3.setEditableOnEdit(true);
        textColumn3.setEditableOnInsert(true);
        textColumn3.setHeaderColumnName("Codigo");
        textColumn3.setHeaderFont(new java.awt.Font("Arial", 1, 11));
        textColumn3.setMaxCharacters(5);
        gridControl1.getColumnContainer().add(textColumn3);

        textColumn4.setColumnName("nome");
        textColumn4.setEditableOnEdit(true);
        textColumn4.setEditableOnInsert(true);
        textColumn4.setHeaderColumnName("Nome");
        textColumn4.setHeaderFont(new java.awt.Font("Arial", 1, 11));
        textColumn4.setMaxCharacters(50);
        textColumn4.setPreferredWidth(300);
        gridControl1.getColumnContainer().add(textColumn4);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel2.add(gridControl1, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        getContentPane().add(jPanel2, gridBagConstraints);

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private org.openswing.swing.client.CodLookupControl codLookupControl1;
    private org.openswing.swing.client.CodLookupControl codLookupControl2;
    private org.openswing.swing.client.CodLookupControl codLookupControl3;
    private org.openswing.swing.client.CodLookupControl codLookupControl4;
    private org.openswing.swing.client.CodLookupControl codLookupControl5;
    private org.openswing.swing.client.CodLookupControl codLookupControl6;
    private org.openswing.swing.client.CodLookupControl codLookupControl7;
    private org.openswing.swing.client.DeleteButton deleteButton1;
    private org.openswing.swing.client.EditButton editButton1;
    private org.openswing.swing.client.EditButton editButton2;
    private org.openswing.swing.form.client.Form form1;
    private org.openswing.swing.client.FormattedTextControl formattedTextControl1;
    private org.openswing.swing.client.FormattedTextControl formattedTextControl2;
    private org.openswing.swing.client.GridControl gridControl1;
    private org.openswing.swing.client.InsertButton insertButton1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private org.openswing.swing.client.LabelControl labelControl1;
    private org.openswing.swing.client.LabelControl labelControl10;
    private org.openswing.swing.client.LabelControl labelControl2;
    private org.openswing.swing.client.LabelControl labelControl3;
    private org.openswing.swing.client.LabelControl labelControl4;
    private org.openswing.swing.client.LabelControl labelControl5;
    private org.openswing.swing.client.LabelControl labelControl6;
    private org.openswing.swing.client.LabelControl labelControl7;
    private org.openswing.swing.client.LabelControl labelControl8;
    private org.openswing.swing.client.LabelControl labelControl9;
    private org.openswing.swing.client.ReloadButton reloadButton1;
    private org.openswing.swing.client.ReloadButton reloadButton2;
    private org.openswing.swing.client.SaveButton saveButton1;
    private org.openswing.swing.client.SaveButton saveButton2;
    private org.openswing.swing.table.columns.client.TextColumn textColumn3;
    private org.openswing.swing.table.columns.client.TextColumn textColumn4;
    private org.openswing.swing.client.TextControl textControl3;
    // End of variables declaration//GEN-END:variables

}
