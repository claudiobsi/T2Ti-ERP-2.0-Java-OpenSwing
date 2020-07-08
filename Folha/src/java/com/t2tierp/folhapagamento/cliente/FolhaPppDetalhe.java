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
package com.t2tierp.folhapagamento.cliente;

import com.t2tierp.padrao.cliente.LookupDataLocatorGenerico;
import java.awt.Dimension;
import org.openswing.swing.lookup.client.LookupController;
import org.openswing.swing.mdi.client.InternalFrame;

public class FolhaPppDetalhe extends InternalFrame {

    private LookupController colaboradorController = new LookupController();
    private FolhaPppCatGridController catController;
    private FolhaPppAtividadeGridController atividadeController;
    private FolhaPppFatorRiscoGridController fatorRiscoController;
    private FolhaPppExameMedicoGridController exameMedicoController;

    public FolhaPppDetalhe(FolhaPppDetalheController controller) {
        initComponents();

        form1.setFormController(controller);

        catController = new FolhaPppCatGridController();
        gridCat.setController(catController);
        gridCat.setGridDataLocator(catController);

        atividadeController = new FolhaPppAtividadeGridController();
        gridAtividade.setController(atividadeController);
        gridAtividade.setGridDataLocator(atividadeController);

        fatorRiscoController = new FolhaPppFatorRiscoGridController();
        gridFatorRisco.setController(fatorRiscoController);
        gridFatorRisco.setGridDataLocator(fatorRiscoController);

        exameMedicoController = new FolhaPppExameMedicoGridController();
        gridExameMedico.setController(exameMedicoController);
        gridExameMedico.setGridDataLocator(exameMedicoController);

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
        colaboradorController.setFramePreferedSize(new Dimension(500, 400));

        colaboradorController.setLookupDataLocator(new LookupDataLocatorGenerico(colaboradorController.getLookupValueObjectClassName()));
        codLookupControl2.setLookupController(colaboradorController);
    }

    /**
     * @return the form1
     */
    public org.openswing.swing.form.client.Form getForm1() {
        return form1;
    }

    /**
     * @return the gridAtividade
     */
    public org.openswing.swing.client.GridControl getGridAtividade() {
        return gridAtividade;
    }

    /**
     * @return the gridCat
     */
    public org.openswing.swing.client.GridControl getGridCat() {
        return gridCat;
    }

    /**
     * @return the gridExameMedico
     */
    public org.openswing.swing.client.GridControl getGridExameMedico() {
        return gridExameMedico;
    }

    /**
     * @return the gridFatorRisco
     */
    public org.openswing.swing.client.GridControl getGridFatorRisco() {
        return gridFatorRisco;
    }

    /**
     * @return the catController
     */
    public FolhaPppCatGridController getCatController() {
        return catController;
    }

    /**
     * @return the atividadeController
     */
    public FolhaPppAtividadeGridController getAtividadeController() {
        return atividadeController;
    }

    /**
     * @return the fatorRiscoController
     */
    public FolhaPppFatorRiscoGridController getFatorRiscoController() {
        return fatorRiscoController;
    }

    /**
     * @return the exameMedicoController
     */
    public FolhaPppExameMedicoGridController getExameMedicoController() {
        return exameMedicoController;
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
        codLookupControl2 = new org.openswing.swing.client.CodLookupControl();
        textControl2 = new org.openswing.swing.client.TextControl();
        labelControl3 = new org.openswing.swing.client.LabelControl();
        textAreaControl1 = new org.openswing.swing.client.TextAreaControl();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        insertButtonCAT = new org.openswing.swing.client.InsertButton();
        editButtonCAT = new org.openswing.swing.client.EditButton();
        deleteButtonCAT = new org.openswing.swing.client.DeleteButton();
        saveButtonCAT = new org.openswing.swing.client.SaveButton();
        reloadButtonCAT = new org.openswing.swing.client.ReloadButton();
        navigatorBarCAT = new org.openswing.swing.client.NavigatorBar();
        gridCat = new org.openswing.swing.client.GridControl();
        integerColumn3 = new org.openswing.swing.table.columns.client.IntegerColumn();
        dateColumn4 = new org.openswing.swing.table.columns.client.DateColumn();
        dateColumn5 = new org.openswing.swing.table.columns.client.DateColumn();
        jPanel4 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        insertButtonAtividade = new org.openswing.swing.client.InsertButton();
        editButtonAtividade = new org.openswing.swing.client.EditButton();
        deleteButtonAtividade = new org.openswing.swing.client.DeleteButton();
        saveButtonAtividade = new org.openswing.swing.client.SaveButton();
        reloadButtonAtividade = new org.openswing.swing.client.ReloadButton();
        navigatorBarAtividade = new org.openswing.swing.client.NavigatorBar();
        gridAtividade = new org.openswing.swing.client.GridControl();
        dateColumn3 = new org.openswing.swing.table.columns.client.DateColumn();
        dateColumn6 = new org.openswing.swing.table.columns.client.DateColumn();
        textColumn5 = new org.openswing.swing.table.columns.client.TextColumn();
        jPanel6 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        insertButtonFatorRisco = new org.openswing.swing.client.InsertButton();
        editButtonFatorRisco = new org.openswing.swing.client.EditButton();
        deleteButtonFatorRisco = new org.openswing.swing.client.DeleteButton();
        saveButtonFatorRisco = new org.openswing.swing.client.SaveButton();
        reloadButtonFatorRisco = new org.openswing.swing.client.ReloadButton();
        navigatorBarFatorRisco = new org.openswing.swing.client.NavigatorBar();
        gridFatorRisco = new org.openswing.swing.client.GridControl();
        dateColumn7 = new org.openswing.swing.table.columns.client.DateColumn();
        dateColumn8 = new org.openswing.swing.table.columns.client.DateColumn();
        comboColumn1 = new org.openswing.swing.table.columns.client.ComboColumn();
        textColumn7 = new org.openswing.swing.table.columns.client.TextColumn();
        textColumn8 = new org.openswing.swing.table.columns.client.TextColumn();
        textColumn9 = new org.openswing.swing.table.columns.client.TextColumn();
        comboColumn2 = new org.openswing.swing.table.columns.client.ComboColumn();
        comboColumn3 = new org.openswing.swing.table.columns.client.ComboColumn();
        integerColumn11 = new org.openswing.swing.table.columns.client.IntegerColumn();
        comboColumn4 = new org.openswing.swing.table.columns.client.ComboColumn();
        comboColumn5 = new org.openswing.swing.table.columns.client.ComboColumn();
        comboColumn6 = new org.openswing.swing.table.columns.client.ComboColumn();
        comboColumn7 = new org.openswing.swing.table.columns.client.ComboColumn();
        comboColumn8 = new org.openswing.swing.table.columns.client.ComboColumn();
        jPanel8 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        insertButtonExameMedico = new org.openswing.swing.client.InsertButton();
        editButtonExameMedico = new org.openswing.swing.client.EditButton();
        deleteButtonExameMedico = new org.openswing.swing.client.DeleteButton();
        saveButtonExameMedico = new org.openswing.swing.client.SaveButton();
        reloadButtonExameMedico = new org.openswing.swing.client.ReloadButton();
        navigatorBarExameMedico = new org.openswing.swing.client.NavigatorBar();
        gridExameMedico = new org.openswing.swing.client.GridControl();
        dateColumn9 = new org.openswing.swing.table.columns.client.DateColumn();
        comboColumn9 = new org.openswing.swing.table.columns.client.ComboColumn();
        textColumn6 = new org.openswing.swing.table.columns.client.TextColumn();
        comboColumn10 = new org.openswing.swing.table.columns.client.ComboColumn();
        textColumn11 = new org.openswing.swing.table.columns.client.TextColumn();

        setTitle("T2Ti ERP - Folha de Pagamento");
        setPreferredSize(new java.awt.Dimension(800, 600));
        getContentPane().setLayout(new java.awt.GridBagLayout());

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Folha Ppp"));
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

        form1.setVOClassName("com.t2tierp.folhapagamento.java.FolhaPppVO");
        form1.setEditButton(editButton1);
        form1.setReloadButton(reloadButton1);
        form1.setSaveButton(saveButton1);
        form1.setLayout(new java.awt.GridBagLayout());

        labelControl1.setLabel("Colaborador:");
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
        gridBagConstraints.ipadx = -50;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        form1.add(codLookupControl2, gridBagConstraints);

        textControl2.setAttributeName("colaborador.pessoa.nome");
        textControl2.setEnabled(false);
        textControl2.setEnabledOnEdit(false);
        textControl2.setEnabledOnInsert(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        form1.add(textControl2, gridBagConstraints);

        labelControl3.setLabel("Observacao:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        form1.add(labelControl3, gridBagConstraints);

        textAreaControl1.setAttributeName("observacao");
        textAreaControl1.setEnabled(false);
        textAreaControl1.setMaxCharacters(1000);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipady = 50;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        form1.add(textAreaControl1, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        getContentPane().add(form1, gridBagConstraints);

        jPanel2.setLayout(new java.awt.GridBagLayout());

        jPanel3.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));
        jPanel3.add(insertButtonCAT);
        jPanel3.add(editButtonCAT);
        jPanel3.add(deleteButtonCAT);
        jPanel3.add(saveButtonCAT);
        jPanel3.add(reloadButtonCAT);
        jPanel3.add(navigatorBarCAT);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        jPanel2.add(jPanel3, gridBagConstraints);

        gridCat.setAutoLoadData(false);
        gridCat.setDeleteButton(deleteButtonCAT);
        gridCat.setEditButton(editButtonCAT);
        gridCat.setInsertButton(insertButtonCAT);
        gridCat.setNavBar(navigatorBarCAT);
        gridCat.setReloadButton(reloadButtonCAT);
        gridCat.setSaveButton(saveButtonCAT);
        gridCat.setValueObjectClassName("com.t2tierp.folhapagamento.java.FolhaPppCatVO");
        gridCat.getColumnContainer().setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        integerColumn3.setColumnName("numeroCat");
        integerColumn3.setEditableOnEdit(true);
        integerColumn3.setEditableOnInsert(true);
        integerColumn3.setHeaderColumnName("Numero CAT");
        integerColumn3.setHeaderFont(new java.awt.Font("Arial", 1, 11));
        gridCat.getColumnContainer().add(integerColumn3);

        dateColumn4.setColumnName("dataAfastamento");
        dateColumn4.setEditableOnEdit(true);
        dateColumn4.setEditableOnInsert(true);
        dateColumn4.setHeaderColumnName("Data Afastamento");
        dateColumn4.setHeaderFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        dateColumn4.setPreferredWidth(110);
        gridCat.getColumnContainer().add(dateColumn4);

        dateColumn5.setColumnName("dataRegistro");
        dateColumn5.setEditableOnEdit(true);
        dateColumn5.setEditableOnInsert(true);
        dateColumn5.setHeaderColumnName("Data Registro");
        dateColumn5.setHeaderFont(new java.awt.Font("Arial", 1, 11));
        gridCat.getColumnContainer().add(dateColumn5);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel2.add(gridCat, gridBagConstraints);

        jTabbedPane1.addTab("CAT", jPanel2);

        jPanel4.setLayout(new java.awt.GridBagLayout());

        jPanel5.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));
        jPanel5.add(insertButtonAtividade);
        jPanel5.add(editButtonAtividade);
        jPanel5.add(deleteButtonAtividade);
        jPanel5.add(saveButtonAtividade);
        jPanel5.add(reloadButtonAtividade);
        jPanel5.add(navigatorBarAtividade);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        jPanel4.add(jPanel5, gridBagConstraints);

        gridAtividade.setAutoLoadData(false);
        gridAtividade.setDeleteButton(deleteButtonAtividade);
        gridAtividade.setEditButton(editButtonAtividade);
        gridAtividade.setInsertButton(insertButtonAtividade);
        gridAtividade.setNavBar(navigatorBarAtividade);
        gridAtividade.setReloadButton(reloadButtonAtividade);
        gridAtividade.setSaveButton(saveButtonAtividade);
        gridAtividade.setValueObjectClassName("com.t2tierp.folhapagamento.java.FolhaPppAtividadeVO");
        gridAtividade.getColumnContainer().setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        dateColumn3.setColumnName("dataInicio");
        dateColumn3.setEditableOnEdit(true);
        dateColumn3.setEditableOnInsert(true);
        dateColumn3.setHeaderColumnName("Data Inicio");
        dateColumn3.setHeaderFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        gridAtividade.getColumnContainer().add(dateColumn3);

        dateColumn6.setColumnName("dataFim");
        dateColumn6.setEditableOnEdit(true);
        dateColumn6.setEditableOnInsert(true);
        dateColumn6.setHeaderColumnName("Data Fim");
        dateColumn6.setHeaderFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        gridAtividade.getColumnContainer().add(dateColumn6);

        textColumn5.setColumnName("descricao");
        textColumn5.setEditableOnEdit(true);
        textColumn5.setEditableOnInsert(true);
        textColumn5.setHeaderColumnName("Descricao");
        textColumn5.setHeaderFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        textColumn5.setMaxCharacters(1000);
        textColumn5.setPreferredWidth(500);
        gridAtividade.getColumnContainer().add(textColumn5);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel4.add(gridAtividade, gridBagConstraints);

        jTabbedPane1.addTab("Atividade", jPanel4);

        jPanel6.setLayout(new java.awt.GridBagLayout());

        jPanel7.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));
        jPanel7.add(insertButtonFatorRisco);
        jPanel7.add(editButtonFatorRisco);
        jPanel7.add(deleteButtonFatorRisco);
        jPanel7.add(saveButtonFatorRisco);
        jPanel7.add(reloadButtonFatorRisco);
        jPanel7.add(navigatorBarFatorRisco);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        jPanel6.add(jPanel7, gridBagConstraints);

        gridFatorRisco.setAutoLoadData(false);
        gridFatorRisco.setDeleteButton(deleteButtonFatorRisco);
        gridFatorRisco.setEditButton(editButtonFatorRisco);
        gridFatorRisco.setInsertButton(insertButtonFatorRisco);
        gridFatorRisco.setNavBar(navigatorBarFatorRisco);
        gridFatorRisco.setReloadButton(reloadButtonFatorRisco);
        gridFatorRisco.setSaveButton(saveButtonFatorRisco);
        gridFatorRisco.setValueObjectClassName("com.t2tierp.folhapagamento.java.FolhaPppFatorRiscoVO");
        gridFatorRisco.getColumnContainer().setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        dateColumn7.setColumnName("dataInicio");
        dateColumn7.setColumnRequired(false);
        dateColumn7.setEditableOnEdit(true);
        dateColumn7.setEditableOnInsert(true);
        dateColumn7.setHeaderColumnName("Data Inicio");
        dateColumn7.setHeaderFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        gridFatorRisco.getColumnContainer().add(dateColumn7);

        dateColumn8.setColumnName("dataFim");
        dateColumn8.setColumnRequired(false);
        dateColumn8.setEditableOnEdit(true);
        dateColumn8.setEditableOnInsert(true);
        dateColumn8.setHeaderColumnName("Data Fim");
        dateColumn8.setHeaderFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        gridFatorRisco.getColumnContainer().add(dateColumn8);

        comboColumn1.setColumnName("tipo");
        comboColumn1.setColumnRequired(false);
        comboColumn1.setDomainId("tipoFatorRiscoPpp");
        comboColumn1.setEditableOnEdit(true);
        comboColumn1.setEditableOnInsert(true);
        comboColumn1.setHeaderColumnName("Tipo");
        comboColumn1.setHeaderFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        gridFatorRisco.getColumnContainer().add(comboColumn1);

        textColumn7.setColumnName("fatorRisco");
        textColumn7.setColumnRequired(false);
        textColumn7.setEditableOnEdit(true);
        textColumn7.setEditableOnInsert(true);
        textColumn7.setHeaderColumnName("Fator Risco");
        textColumn7.setHeaderFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        textColumn7.setMaxCharacters(40);
        gridFatorRisco.getColumnContainer().add(textColumn7);

        textColumn8.setColumnName("intensidade");
        textColumn8.setColumnRequired(false);
        textColumn8.setEditableOnEdit(true);
        textColumn8.setEditableOnInsert(true);
        textColumn8.setHeaderColumnName("Intensidade");
        textColumn8.setHeaderFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        textColumn8.setMaxCharacters(15);
        gridFatorRisco.getColumnContainer().add(textColumn8);

        textColumn9.setColumnName("tecnicaUtilizada");
        textColumn9.setColumnRequired(false);
        textColumn9.setEditableOnEdit(true);
        textColumn9.setEditableOnInsert(true);
        textColumn9.setHeaderColumnName("Tecnica Utilizada");
        textColumn9.setHeaderFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        textColumn9.setMaxCharacters(40);
        gridFatorRisco.getColumnContainer().add(textColumn9);

        comboColumn2.setColumnName("epcEficaz");
        comboColumn2.setColumnRequired(false);
        comboColumn2.setDomainId("simnao");
        comboColumn2.setEditableOnEdit(true);
        comboColumn2.setEditableOnInsert(true);
        comboColumn2.setHeaderColumnName("EPC Eficaz");
        comboColumn2.setHeaderFont(new java.awt.Font("Arial", 1, 11));
        gridFatorRisco.getColumnContainer().add(comboColumn2);

        comboColumn3.setColumnName("epiEficaz");
        comboColumn3.setColumnRequired(false);
        comboColumn3.setDomainId("simnao");
        comboColumn3.setEditableOnEdit(true);
        comboColumn3.setEditableOnInsert(true);
        comboColumn3.setHeaderColumnName("EPI Eficaz");
        comboColumn3.setHeaderFont(new java.awt.Font("Arial", 1, 11));
        gridFatorRisco.getColumnContainer().add(comboColumn3);

        integerColumn11.setColumnName("caEpi");
        integerColumn11.setColumnRequired(false);
        integerColumn11.setEditableOnEdit(true);
        integerColumn11.setEditableOnInsert(true);
        integerColumn11.setHeaderColumnName("CA EPI");
        integerColumn11.setHeaderFont(new java.awt.Font("Arial", 1, 11));
        gridFatorRisco.getColumnContainer().add(integerColumn11);

        comboColumn4.setColumnName("atendimentoNr061");
        comboColumn4.setColumnRequired(false);
        comboColumn4.setDomainId("simnao");
        comboColumn4.setEditableOnEdit(true);
        comboColumn4.setEditableOnInsert(true);
        comboColumn4.setHeaderColumnName("Atendimento NR 6 1");
        comboColumn4.setHeaderFont(new java.awt.Font("Arial", 1, 11));
        comboColumn4.setPreferredWidth(115);
        gridFatorRisco.getColumnContainer().add(comboColumn4);

        comboColumn5.setColumnName("atendimentoNr062");
        comboColumn5.setColumnRequired(false);
        comboColumn5.setDomainId("simnao");
        comboColumn5.setEditableOnEdit(true);
        comboColumn5.setEditableOnInsert(true);
        comboColumn5.setHeaderColumnName("Atendimento NR 6 2");
        comboColumn5.setHeaderFont(new java.awt.Font("Arial", 1, 11));
        comboColumn5.setPreferredWidth(115);
        gridFatorRisco.getColumnContainer().add(comboColumn5);

        comboColumn6.setColumnName("atendimentoNr063");
        comboColumn6.setColumnRequired(false);
        comboColumn6.setDomainId("simnao");
        comboColumn6.setEditableOnEdit(true);
        comboColumn6.setEditableOnInsert(true);
        comboColumn6.setHeaderColumnName("Atendimento NR 6 3");
        comboColumn6.setHeaderFont(new java.awt.Font("Arial", 1, 11));
        comboColumn6.setPreferredWidth(115);
        gridFatorRisco.getColumnContainer().add(comboColumn6);

        comboColumn7.setColumnName("atendimentoNr064");
        comboColumn7.setColumnRequired(false);
        comboColumn7.setDomainId("simnao");
        comboColumn7.setEditableOnEdit(true);
        comboColumn7.setEditableOnInsert(true);
        comboColumn7.setHeaderColumnName("Atendimento NR 6 4");
        comboColumn7.setHeaderFont(new java.awt.Font("Arial", 1, 11));
        comboColumn7.setPreferredWidth(115);
        gridFatorRisco.getColumnContainer().add(comboColumn7);

        comboColumn8.setColumnName("atendimentoNr065");
        comboColumn8.setColumnRequired(false);
        comboColumn8.setDomainId("simnao");
        comboColumn8.setEditableOnEdit(true);
        comboColumn8.setEditableOnInsert(true);
        comboColumn8.setHeaderColumnName("Atendimento NR 6 5");
        comboColumn8.setHeaderFont(new java.awt.Font("Arial", 1, 11));
        comboColumn8.setPreferredWidth(115);
        gridFatorRisco.getColumnContainer().add(comboColumn8);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel6.add(gridFatorRisco, gridBagConstraints);

        jTabbedPane1.addTab("Fator de Risco", jPanel6);

        jPanel8.setLayout(new java.awt.GridBagLayout());

        jPanel9.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));
        jPanel9.add(insertButtonExameMedico);
        jPanel9.add(editButtonExameMedico);
        jPanel9.add(deleteButtonExameMedico);
        jPanel9.add(saveButtonExameMedico);
        jPanel9.add(reloadButtonExameMedico);
        jPanel9.add(navigatorBarExameMedico);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        jPanel8.add(jPanel9, gridBagConstraints);

        gridExameMedico.setAutoLoadData(false);
        gridExameMedico.setDeleteButton(deleteButtonExameMedico);
        gridExameMedico.setEditButton(editButtonExameMedico);
        gridExameMedico.setInsertButton(insertButtonExameMedico);
        gridExameMedico.setNavBar(navigatorBarExameMedico);
        gridExameMedico.setReloadButton(reloadButtonExameMedico);
        gridExameMedico.setSaveButton(saveButtonExameMedico);
        gridExameMedico.setValueObjectClassName("com.t2tierp.folhapagamento.java.FolhaPppExameMedicoVO");
        gridExameMedico.getColumnContainer().setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        dateColumn9.setColumnName("dataUltimo");
        dateColumn9.setEditableOnEdit(true);
        dateColumn9.setEditableOnInsert(true);
        dateColumn9.setHeaderColumnName("Data Ultimo");
        dateColumn9.setHeaderFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        gridExameMedico.getColumnContainer().add(dateColumn9);

        comboColumn9.setColumnName("tipo");
        comboColumn9.setDomainId("tipoExameMedicoPpp");
        comboColumn9.setEditableOnEdit(true);
        comboColumn9.setEditableOnInsert(true);
        comboColumn9.setHeaderColumnName("Tipo");
        comboColumn9.setHeaderFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        gridExameMedico.getColumnContainer().add(comboColumn9);

        textColumn6.setColumnName("natureza");
        textColumn6.setEditableOnEdit(true);
        textColumn6.setEditableOnInsert(true);
        textColumn6.setHeaderColumnName("Natureza");
        textColumn6.setHeaderFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        textColumn6.setMaxCharacters(50);
        gridExameMedico.getColumnContainer().add(textColumn6);

        comboColumn10.setColumnName("exame");
        comboColumn10.setDomainId("exameMedicoPpp");
        comboColumn10.setEditableOnEdit(true);
        comboColumn10.setEditableOnInsert(true);
        comboColumn10.setHeaderColumnName("Exame");
        comboColumn10.setHeaderFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        gridExameMedico.getColumnContainer().add(comboColumn10);

        textColumn11.setColumnName("indicacaoResultados");
        textColumn11.setEditableOnEdit(true);
        textColumn11.setEditableOnInsert(true);
        textColumn11.setHeaderColumnName("Indicacao Resultados");
        textColumn11.setHeaderFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        textColumn11.setMaxCharacters(50);
        textColumn11.setPreferredWidth(200);
        gridExameMedico.getColumnContainer().add(textColumn11);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel8.add(gridExameMedico, gridBagConstraints);

        jTabbedPane1.addTab("Exame Médico", jPanel8);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        getContentPane().add(jTabbedPane1, gridBagConstraints);

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private org.openswing.swing.client.CodLookupControl codLookupControl2;
    private org.openswing.swing.table.columns.client.ComboColumn comboColumn1;
    private org.openswing.swing.table.columns.client.ComboColumn comboColumn10;
    private org.openswing.swing.table.columns.client.ComboColumn comboColumn2;
    private org.openswing.swing.table.columns.client.ComboColumn comboColumn3;
    private org.openswing.swing.table.columns.client.ComboColumn comboColumn4;
    private org.openswing.swing.table.columns.client.ComboColumn comboColumn5;
    private org.openswing.swing.table.columns.client.ComboColumn comboColumn6;
    private org.openswing.swing.table.columns.client.ComboColumn comboColumn7;
    private org.openswing.swing.table.columns.client.ComboColumn comboColumn8;
    private org.openswing.swing.table.columns.client.ComboColumn comboColumn9;
    private org.openswing.swing.table.columns.client.DateColumn dateColumn3;
    private org.openswing.swing.table.columns.client.DateColumn dateColumn4;
    private org.openswing.swing.table.columns.client.DateColumn dateColumn5;
    private org.openswing.swing.table.columns.client.DateColumn dateColumn6;
    private org.openswing.swing.table.columns.client.DateColumn dateColumn7;
    private org.openswing.swing.table.columns.client.DateColumn dateColumn8;
    private org.openswing.swing.table.columns.client.DateColumn dateColumn9;
    private org.openswing.swing.client.DeleteButton deleteButtonAtividade;
    private org.openswing.swing.client.DeleteButton deleteButtonCAT;
    private org.openswing.swing.client.DeleteButton deleteButtonExameMedico;
    private org.openswing.swing.client.DeleteButton deleteButtonFatorRisco;
    private org.openswing.swing.client.EditButton editButton1;
    private org.openswing.swing.client.EditButton editButtonAtividade;
    private org.openswing.swing.client.EditButton editButtonCAT;
    private org.openswing.swing.client.EditButton editButtonExameMedico;
    private org.openswing.swing.client.EditButton editButtonFatorRisco;
    private org.openswing.swing.form.client.Form form1;
    private org.openswing.swing.client.GridControl gridAtividade;
    private org.openswing.swing.client.GridControl gridCat;
    private org.openswing.swing.client.GridControl gridExameMedico;
    private org.openswing.swing.client.GridControl gridFatorRisco;
    private org.openswing.swing.client.InsertButton insertButtonAtividade;
    private org.openswing.swing.client.InsertButton insertButtonCAT;
    private org.openswing.swing.client.InsertButton insertButtonExameMedico;
    private org.openswing.swing.client.InsertButton insertButtonFatorRisco;
    private org.openswing.swing.table.columns.client.IntegerColumn integerColumn11;
    private org.openswing.swing.table.columns.client.IntegerColumn integerColumn3;
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
    private org.openswing.swing.client.LabelControl labelControl1;
    private org.openswing.swing.client.LabelControl labelControl3;
    private org.openswing.swing.client.NavigatorBar navigatorBarAtividade;
    private org.openswing.swing.client.NavigatorBar navigatorBarCAT;
    private org.openswing.swing.client.NavigatorBar navigatorBarExameMedico;
    private org.openswing.swing.client.NavigatorBar navigatorBarFatorRisco;
    private org.openswing.swing.client.ReloadButton reloadButton1;
    private org.openswing.swing.client.ReloadButton reloadButtonAtividade;
    private org.openswing.swing.client.ReloadButton reloadButtonCAT;
    private org.openswing.swing.client.ReloadButton reloadButtonExameMedico;
    private org.openswing.swing.client.ReloadButton reloadButtonFatorRisco;
    private org.openswing.swing.client.SaveButton saveButton1;
    private org.openswing.swing.client.SaveButton saveButtonAtividade;
    private org.openswing.swing.client.SaveButton saveButtonCAT;
    private org.openswing.swing.client.SaveButton saveButtonExameMedico;
    private org.openswing.swing.client.SaveButton saveButtonFatorRisco;
    private org.openswing.swing.client.TextAreaControl textAreaControl1;
    private org.openswing.swing.table.columns.client.TextColumn textColumn11;
    private org.openswing.swing.table.columns.client.TextColumn textColumn5;
    private org.openswing.swing.table.columns.client.TextColumn textColumn6;
    private org.openswing.swing.table.columns.client.TextColumn textColumn7;
    private org.openswing.swing.table.columns.client.TextColumn textColumn8;
    private org.openswing.swing.table.columns.client.TextColumn textColumn9;
    private org.openswing.swing.client.TextControl textControl2;
    // End of variables declaration//GEN-END:variables


}
