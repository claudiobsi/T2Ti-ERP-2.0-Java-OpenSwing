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
package com.t2tierp.cadastros.cliente;

import com.t2tierp.padrao.cliente.LookupDataLocatorGenerico;
import java.awt.Dimension;
import org.openswing.swing.lookup.client.LookupController;
import org.openswing.swing.mdi.client.InternalFrame;

public class ProdutoDetalhe extends InternalFrame {

    private LookupController subGrupoController = new LookupController();
    private LookupController unidadeController = new LookupController();
    private LookupController marcaController = new LookupController();
    private LookupController almoxarifadoController = new LookupController();
    private LookupController grupoTributarioController = new LookupController();
    private LookupController icmsCustomizadoController = new LookupController();
    private LookupController ncmController = new LookupController();

    public ProdutoDetalhe(ProdutoDetalheController controller) {
        initComponents();

        form1.setFormController(controller);

        /*
         * Configurações do lookup do sub grupo
         */
        subGrupoController.setLookupValueObjectClassName("com.t2tierp.cadastros.java.ProdutoSubGrupoVO");
        subGrupoController.addLookup2ParentLink("id", "produtoSubGrupo.id");
        subGrupoController.addLookup2ParentLink("nome", "produtoSubGrupo.nome");
        subGrupoController.addLookup2ParentLink("descricao", "produtoSubGrupo.descricao");
        subGrupoController.setHeaderColumnName("id", "ID");
        subGrupoController.setHeaderColumnName("nome", "Nome");
        subGrupoController.setHeaderColumnName("descricao", "Descrição");
        subGrupoController.setFrameTitle("Importa Agrupamento");

        subGrupoController.setVisibleStatusPanel(true);
        subGrupoController.setVisibleColumn("id", true);
        subGrupoController.setVisibleColumn("nome", true);
        subGrupoController.setVisibleColumn("descricao", true);
        subGrupoController.setFramePreferedSize(new Dimension(600, 500));

        subGrupoController.setLookupDataLocator(new LookupDataLocatorGenerico(subGrupoController.getLookupValueObjectClassName()));
        codLookupControlSubGrupo.setLookupController(subGrupoController);

        /*
         * Configurações do lookup da unidade
         */
        unidadeController.setLookupValueObjectClassName("com.t2tierp.cadastros.java.UnidadeProdutoVO");
        unidadeController.addLookup2ParentLink("id", "unidadeProduto.id");
        unidadeController.addLookup2ParentLink("sigla", "unidadeProduto.sigla");
        unidadeController.setHeaderColumnName("id", "ID");
        unidadeController.setHeaderColumnName("sigla", "Sigla");
        unidadeController.setFrameTitle("Importa Unidade Produto");

        unidadeController.setVisibleStatusPanel(true);
        unidadeController.setVisibleColumn("id", true);
        unidadeController.setVisibleColumn("sigla", true);
        unidadeController.setFramePreferedSize(new Dimension(600, 500));

        unidadeController.setLookupDataLocator(new LookupDataLocatorGenerico(unidadeController.getLookupValueObjectClassName()));
        codLookupControlUnidade.setLookupController(unidadeController);


        /*
         * Configurações do lookup da marca
         */
        marcaController.setLookupValueObjectClassName("com.t2tierp.cadastros.java.ProdutoMarcaVO");
        marcaController.addLookup2ParentLink("id", "produtoMarca.id");
        marcaController.addLookup2ParentLink("nome", "produtoMarca.nome");
        marcaController.setHeaderColumnName("id", "ID");
        marcaController.setHeaderColumnName("nome", "Nome");
        marcaController.setFrameTitle("Importa Marca Produto");

        marcaController.setVisibleStatusPanel(true);
        marcaController.setVisibleColumn("id", true);
        marcaController.setVisibleColumn("nome", true);
        marcaController.setFramePreferedSize(new Dimension(600, 500));

        marcaController.setLookupDataLocator(new LookupDataLocatorGenerico(marcaController.getLookupValueObjectClassName()));
        codLookupControlMarca.setLookupController(marcaController);

        /*
         * Configurações do lookup do almoxarifado
         */
        almoxarifadoController.setLookupValueObjectClassName("com.t2tierp.cadastros.java.AlmoxarifadoVO");
        almoxarifadoController.addLookup2ParentLink("id", "almoxarifado.id");
        almoxarifadoController.addLookup2ParentLink("nome", "almoxarifado.nome");
        almoxarifadoController.setHeaderColumnName("id", "ID");
        almoxarifadoController.setHeaderColumnName("nome", "Nome");
        almoxarifadoController.setFrameTitle("Importa Almoxarifado");

        almoxarifadoController.setVisibleStatusPanel(true);
        almoxarifadoController.setVisibleColumn("id", true);
        almoxarifadoController.setVisibleColumn("nome", true);
        almoxarifadoController.setFramePreferedSize(new Dimension(600, 500));

        almoxarifadoController.setLookupDataLocator(new LookupDataLocatorGenerico(almoxarifadoController.getLookupValueObjectClassName()));
        codLookupControlAlmoxarifado.setLookupController(almoxarifadoController);


        /*
         * Configurações do lookup do grupo tributario
         */
        grupoTributarioController.setLookupValueObjectClassName("com.t2tierp.tributacao.java.TributGrupoTributarioVO");
        grupoTributarioController.addLookup2ParentLink("id", "tributGrupoTributario.id");
        grupoTributarioController.addLookup2ParentLink("descricao", "tributGrupoTributario.descricao");
        grupoTributarioController.setHeaderColumnName("id", "ID");
        grupoTributarioController.setHeaderColumnName("descricao", "Nome");
        grupoTributarioController.setFrameTitle("Importa Grupo Tributário");

        grupoTributarioController.setVisibleStatusPanel(true);
        grupoTributarioController.setVisibleColumn("id", true);
        grupoTributarioController.setVisibleColumn("descricao", true);
        grupoTributarioController.setFramePreferedSize(new Dimension(600, 500));

        grupoTributarioController.setLookupDataLocator(new LookupDataLocatorGenerico(grupoTributarioController.getLookupValueObjectClassName()));
        codLookupControlGrupoTributario.setLookupController(grupoTributarioController);

        /*
         * Configurações do lookup do icms customizado
         */
        icmsCustomizadoController.setLookupValueObjectClassName("com.t2tierp.tributacao.java.TributIcmsCustomCabVO");
        icmsCustomizadoController.addLookup2ParentLink("id", "tributIcmsCustomCab.id");
        icmsCustomizadoController.addLookup2ParentLink("descricao", "tributIcmsCustomCab.descricao");
        icmsCustomizadoController.setHeaderColumnName("id", "ID");
        icmsCustomizadoController.setHeaderColumnName("descricao", "Descrição");
        icmsCustomizadoController.setFrameTitle("Importa Icms Customizado");

        icmsCustomizadoController.setVisibleStatusPanel(true);
        icmsCustomizadoController.setVisibleColumn("id", true);
        icmsCustomizadoController.setVisibleColumn("descricao", true);
        icmsCustomizadoController.setFramePreferedSize(new Dimension(600, 500));

        icmsCustomizadoController.setLookupDataLocator(new LookupDataLocatorGenerico(icmsCustomizadoController.getLookupValueObjectClassName()));
        codLookupControlIcmsCustomizado.setLookupController(icmsCustomizadoController);

        /*
         * Configurações do lookup do ncm
         */
        ncmController.setLookupValueObjectClassName("com.t2tierp.cadastros.java.NcmVO");
        ncmController.addLookup2ParentLink("codigo", "ncm");
        ncmController.setHeaderColumnName("codigo", "Código");
        ncmController.setHeaderColumnName("descricao", "Descrição");
        ncmController.setFrameTitle("Importa NCM");

        ncmController.setVisibleStatusPanel(true);
        ncmController.setVisibleColumn("codigo", true);
        ncmController.setVisibleColumn("descricao", true);
        ncmController.setFramePreferedSize(new Dimension(600, 500));

        ncmController.setLookupDataLocator(new LookupDataLocatorGenerico(ncmController.getLookupValueObjectClassName()));
        codLookupControlNcm.setLookupController(ncmController);

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
        form1 = new org.openswing.swing.form.client.Form();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        labelControl1 = new org.openswing.swing.client.LabelControl();
        codLookupControlIcmsCustomizado = new org.openswing.swing.client.CodLookupControl();
        labelControl7 = new org.openswing.swing.client.LabelControl();
        codLookupControlGrupoTributario = new org.openswing.swing.client.CodLookupControl();
        textControl5 = new org.openswing.swing.client.TextControl();
        textControl2 = new org.openswing.swing.client.TextControl();
        labelControl3 = new org.openswing.swing.client.LabelControl();
        codLookupControlUnidade = new org.openswing.swing.client.CodLookupControl();
        labelControl9 = new org.openswing.swing.client.LabelControl();
        codLookupControlMarca = new org.openswing.swing.client.CodLookupControl();
        textControl6 = new org.openswing.swing.client.TextControl();
        textControl3 = new org.openswing.swing.client.TextControl();
        labelControl5 = new org.openswing.swing.client.LabelControl();
        codLookupControlAlmoxarifado = new org.openswing.swing.client.CodLookupControl();
        labelControl11 = new org.openswing.swing.client.LabelControl();
        codLookupControlSubGrupo = new org.openswing.swing.client.CodLookupControl();
        textControl4 = new org.openswing.swing.client.TextControl();
        textControl7 = new org.openswing.swing.client.TextControl();
        textControl8 = new org.openswing.swing.client.TextControl();
        jPanel3 = new javax.swing.JPanel();
        labelControl13 = new org.openswing.swing.client.LabelControl();
        textControl9 = new org.openswing.swing.client.TextControl();
        labelControl14 = new org.openswing.swing.client.LabelControl();
        textControl10 = new org.openswing.swing.client.TextControl();
        labelControl15 = new org.openswing.swing.client.LabelControl();
        labelControl16 = new org.openswing.swing.client.LabelControl();
        textControl12 = new org.openswing.swing.client.TextControl();
        labelControl17 = new org.openswing.swing.client.LabelControl();
        labelControl18 = new org.openswing.swing.client.LabelControl();
        textControl14 = new org.openswing.swing.client.TextControl();
        labelControl34 = new org.openswing.swing.client.LabelControl();
        comboBoxControl29 = new org.openswing.swing.client.ComboBoxControl();
        labelControl39 = new org.openswing.swing.client.LabelControl();
        comboBoxControl34 = new org.openswing.swing.client.ComboBoxControl();
        textAreaControl1 = new org.openswing.swing.client.TextAreaControl();
        jPanel4 = new javax.swing.JPanel();
        labelControl19 = new org.openswing.swing.client.LabelControl();
        numericControl14 = new org.openswing.swing.client.NumericControl();
        numericControl15 = new org.openswing.swing.client.NumericControl();
        labelControl20 = new org.openswing.swing.client.LabelControl();
        numericControl16 = new org.openswing.swing.client.NumericControl();
        labelControl21 = new org.openswing.swing.client.LabelControl();
        numericControl17 = new org.openswing.swing.client.NumericControl();
        labelControl22 = new org.openswing.swing.client.LabelControl();
        numericControl18 = new org.openswing.swing.client.NumericControl();
        labelControl23 = new org.openswing.swing.client.LabelControl();
        numericControl19 = new org.openswing.swing.client.NumericControl();
        labelControl24 = new org.openswing.swing.client.LabelControl();
        labelControl25 = new org.openswing.swing.client.LabelControl();
        numericControl20 = new org.openswing.swing.client.NumericControl();
        labelControl26 = new org.openswing.swing.client.LabelControl();
        numericControl21 = new org.openswing.swing.client.NumericControl();
        labelControl27 = new org.openswing.swing.client.LabelControl();
        numericControl22 = new org.openswing.swing.client.NumericControl();
        labelControl28 = new org.openswing.swing.client.LabelControl();
        numericControl23 = new org.openswing.swing.client.NumericControl();
        labelControl29 = new org.openswing.swing.client.LabelControl();
        numericControl24 = new org.openswing.swing.client.NumericControl();
        labelControl30 = new org.openswing.swing.client.LabelControl();
        numericControl25 = new org.openswing.swing.client.NumericControl();
        labelControl31 = new org.openswing.swing.client.LabelControl();
        numericControl26 = new org.openswing.swing.client.NumericControl();
        labelControl32 = new org.openswing.swing.client.LabelControl();
        numericControl27 = new org.openswing.swing.client.NumericControl();
        jSeparator1 = new javax.swing.JSeparator();
        codLookupControlNcm = new org.openswing.swing.client.CodLookupControl();
        jPanel5 = new javax.swing.JPanel();
        labelControl37 = new org.openswing.swing.client.LabelControl();
        textControl32 = new org.openswing.swing.client.TextControl();
        labelControl38 = new org.openswing.swing.client.LabelControl();
        textControl33 = new org.openswing.swing.client.TextControl();
        labelControl36 = new org.openswing.swing.client.LabelControl();
        labelControl40 = new org.openswing.swing.client.LabelControl();
        comboBoxControl35 = new org.openswing.swing.client.ComboBoxControl();
        labelControl41 = new org.openswing.swing.client.LabelControl();
        comboBoxControl36 = new org.openswing.swing.client.ComboBoxControl();
        labelControl42 = new org.openswing.swing.client.LabelControl();
        labelControl49 = new org.openswing.swing.client.LabelControl();
        textControl44 = new org.openswing.swing.client.TextControl();
        labelControl50 = new org.openswing.swing.client.LabelControl();
        numericControl45 = new org.openswing.swing.client.NumericControl();
        imageControl1 = new org.openswing.swing.client.ImageControl();
        labelControl43 = new org.openswing.swing.client.LabelControl();
        numericControl38 = new org.openswing.swing.client.NumericControl();
        labelControl44 = new org.openswing.swing.client.LabelControl();
        numericControl39 = new org.openswing.swing.client.NumericControl();
        labelControl45 = new org.openswing.swing.client.LabelControl();
        numericControl40 = new org.openswing.swing.client.NumericControl();
        labelControl46 = new org.openswing.swing.client.LabelControl();
        numericControl41 = new org.openswing.swing.client.NumericControl();
        labelControl47 = new org.openswing.swing.client.LabelControl();
        numericControl42 = new org.openswing.swing.client.NumericControl();
        labelControl48 = new org.openswing.swing.client.LabelControl();
        numericControl43 = new org.openswing.swing.client.NumericControl();
        jSeparator2 = new javax.swing.JSeparator();
        comboBoxControl1 = new org.openswing.swing.client.ComboBoxControl();
        labelControl52 = new org.openswing.swing.client.LabelControl();
        comboBoxControl47 = new org.openswing.swing.client.ComboBoxControl();

        setTitle("T2Ti ERP - Cadastros");
        setPreferredSize(new java.awt.Dimension(700, 400));
        getContentPane().setLayout(new java.awt.GridBagLayout());

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Produto"));
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

        form1.setVOClassName("com.t2tierp.cadastros.java.ProdutoVO");
        form1.setEditButton(editButton1);
        form1.setFunctionId("produto");
        form1.setReloadButton(reloadButton1);
        form1.setSaveButton(saveButton1);
        form1.setLayout(new java.awt.GridBagLayout());

        jPanel2.setLayout(new java.awt.GridBagLayout());

        labelControl1.setText("ICMS Customizado:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        jPanel2.add(labelControl1, gridBagConstraints);

        codLookupControlIcmsCustomizado.setAllowOnlyNumbers(true);
        codLookupControlIcmsCustomizado.setAttributeName("tributIcmsCustomCab.id");
        codLookupControlIcmsCustomizado.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        jPanel2.add(codLookupControlIcmsCustomizado, gridBagConstraints);

        labelControl7.setText("Grupo Tributario:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        jPanel2.add(labelControl7, gridBagConstraints);

        codLookupControlGrupoTributario.setAllowOnlyNumbers(true);
        codLookupControlGrupoTributario.setAttributeName("tributGrupoTributario.id");
        codLookupControlGrupoTributario.setEnabled(false);
        codLookupControlGrupoTributario.setMaxCharacters(10);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        jPanel2.add(codLookupControlGrupoTributario, gridBagConstraints);

        textControl5.setAttributeName("tributGrupoTributario.descricao");
        textControl5.setEnabled(false);
        textControl5.setEnabledOnEdit(false);
        textControl5.setEnabledOnInsert(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        jPanel2.add(textControl5, gridBagConstraints);

        textControl2.setAttributeName("tributIcmsCustomCab.descricao");
        textControl2.setEnabled(false);
        textControl2.setEnabledOnEdit(false);
        textControl2.setEnabledOnInsert(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        jPanel2.add(textControl2, gridBagConstraints);

        labelControl3.setText("Unidade:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        jPanel2.add(labelControl3, gridBagConstraints);

        codLookupControlUnidade.setAllowOnlyNumbers(true);
        codLookupControlUnidade.setAttributeName("unidadeProduto.id");
        codLookupControlUnidade.setEnabled(false);
        codLookupControlUnidade.setLinkLabel(labelControl3);
        codLookupControlUnidade.setRequired(true);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 0);
        jPanel2.add(codLookupControlUnidade, gridBagConstraints);

        labelControl9.setText("Marca:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        jPanel2.add(labelControl9, gridBagConstraints);

        codLookupControlMarca.setAllowOnlyNumbers(true);
        codLookupControlMarca.setAttributeName("produtoMarca.id");
        codLookupControlMarca.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 0);
        jPanel2.add(codLookupControlMarca, gridBagConstraints);

        textControl6.setAttributeName("produtoMarca.nome");
        textControl6.setEnabled(false);
        textControl6.setEnabledOnEdit(false);
        textControl6.setEnabledOnInsert(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 5);
        jPanel2.add(textControl6, gridBagConstraints);

        textControl3.setAttributeName("unidadeProduto.sigla");
        textControl3.setEnabled(false);
        textControl3.setEnabledOnEdit(false);
        textControl3.setEnabledOnInsert(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 5);
        jPanel2.add(textControl3, gridBagConstraints);

        labelControl5.setText("Almoxarifado:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        jPanel2.add(labelControl5, gridBagConstraints);

        codLookupControlAlmoxarifado.setAllowOnlyNumbers(true);
        codLookupControlAlmoxarifado.setAttributeName("almoxarifado.id");
        codLookupControlAlmoxarifado.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        jPanel2.add(codLookupControlAlmoxarifado, gridBagConstraints);

        labelControl11.setText("Agrupamento:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        jPanel2.add(labelControl11, gridBagConstraints);

        codLookupControlSubGrupo.setAllowOnlyNumbers(true);
        codLookupControlSubGrupo.setAttributeName("produtoSubGrupo.id");
        codLookupControlSubGrupo.setEnabled(false);
        codLookupControlSubGrupo.setLinkLabel(labelControl11);
        codLookupControlSubGrupo.setRequired(true);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 0);
        jPanel2.add(codLookupControlSubGrupo, gridBagConstraints);

        textControl4.setAttributeName("almoxarifado.nome");
        textControl4.setEnabled(false);
        textControl4.setEnabledOnEdit(false);
        textControl4.setEnabledOnInsert(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        jPanel2.add(textControl4, gridBagConstraints);

        textControl7.setAttributeName("produtoSubGrupo.nome");
        textControl7.setEnabled(false);
        textControl7.setEnabledOnEdit(false);
        textControl7.setEnabledOnInsert(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 0);
        jPanel2.add(textControl7, gridBagConstraints);

        textControl8.setAttributeName("produtoSubGrupo.descricao");
        textControl8.setEnabled(false);
        textControl8.setEnabledOnEdit(false);
        textControl8.setEnabledOnInsert(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 5);
        jPanel2.add(textControl8, gridBagConstraints);

        jPanel3.setLayout(new java.awt.GridBagLayout());

        labelControl13.setLabel("Gtin:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        jPanel3.add(labelControl13, gridBagConstraints);

        textControl9.setAttributeName("gtin");
        textControl9.setEnabled(false);
        textControl9.setLinkLabel(labelControl13);
        textControl9.setMaxCharacters(14);
        textControl9.setRequired(true);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 100;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        jPanel3.add(textControl9, gridBagConstraints);

        labelControl14.setLabel("Codigo Interno:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        jPanel3.add(labelControl14, gridBagConstraints);

        textControl10.setAttributeName("codigoInterno");
        textControl10.setEnabled(false);
        textControl10.setMaxCharacters(60);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        jPanel3.add(textControl10, gridBagConstraints);

        labelControl15.setLabel("Ncm:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        jPanel3.add(labelControl15, gridBagConstraints);

        labelControl16.setLabel("Nome:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        jPanel3.add(labelControl16, gridBagConstraints);

        textControl12.setAttributeName("nome");
        textControl12.setEnabled(false);
        textControl12.setLinkLabel(labelControl16);
        textControl12.setMaxCharacters(100);
        textControl12.setRequired(true);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        jPanel3.add(textControl12, gridBagConstraints);

        labelControl17.setLabel("Descricao:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        jPanel3.add(labelControl17, gridBagConstraints);

        labelControl18.setLabel("Descricao Pdv:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        jPanel3.add(labelControl18, gridBagConstraints);

        textControl14.setAttributeName("descricaoPdv");
        textControl14.setEnabled(false);
        textControl14.setMaxCharacters(30);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        jPanel3.add(textControl14, gridBagConstraints);

        labelControl34.setLabel("Inativo:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        jPanel3.add(labelControl34, gridBagConstraints);

        comboBoxControl29.setAttributeName("inativo");
        comboBoxControl29.setDomainId("naosim");
        comboBoxControl29.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 50;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        jPanel3.add(comboBoxControl29, gridBagConstraints);

        labelControl39.setLabel("Classe Abc:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        jPanel3.add(labelControl39, gridBagConstraints);

        comboBoxControl34.setAttributeName("classeAbc");
        comboBoxControl34.setDomainId("produtoClasse");
        comboBoxControl34.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        jPanel3.add(comboBoxControl34, gridBagConstraints);

        textAreaControl1.setAttributeName("descricao");
        textAreaControl1.setEnabled(false);
        textAreaControl1.setMaxCharacters(1000);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridwidth = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipady = 30;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        jPanel3.add(textAreaControl1, gridBagConstraints);

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Valores Principais"));
        jPanel4.setLayout(new java.awt.GridBagLayout());

        labelControl19.setLabel("Valor Compra:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        jPanel4.add(labelControl19, gridBagConstraints);

        numericControl14.setAttributeName("valorCompra");
        numericControl14.setDecimals(2);
        numericControl14.setEnabled(false);
        numericControl14.setLinkLabel(labelControl19);
        numericControl14.setRequired(true);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        jPanel4.add(numericControl14, gridBagConstraints);

        numericControl15.setAttributeName("valorVenda");
        numericControl15.setDecimals(2);
        numericControl15.setEnabled(false);
        numericControl15.setLinkLabel(labelControl20);
        numericControl15.setRequired(true);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        jPanel4.add(numericControl15, gridBagConstraints);

        labelControl20.setLabel("Valor Venda:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        jPanel4.add(labelControl20, gridBagConstraints);

        numericControl16.setAttributeName("precoVendaMinimo");
        numericControl16.setDecimals(2);
        numericControl16.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        jPanel4.add(numericControl16, gridBagConstraints);

        labelControl21.setLabel("Preco Venda Minimo:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        jPanel4.add(labelControl21, gridBagConstraints);

        numericControl17.setAttributeName("precoSugerido");
        numericControl17.setDecimals(2);
        numericControl17.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        jPanel4.add(numericControl17, gridBagConstraints);

        labelControl22.setLabel("Preco Sugerido:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        jPanel4.add(labelControl22, gridBagConstraints);

        numericControl18.setAttributeName("custoMedioLiquido");
        numericControl18.setDecimals(2);
        numericControl18.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        jPanel4.add(numericControl18, gridBagConstraints);

        labelControl23.setLabel("Custo Medio Liquido:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        jPanel4.add(labelControl23, gridBagConstraints);

        numericControl19.setAttributeName("precoLucroZero");
        numericControl19.setDecimals(2);
        numericControl19.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        jPanel4.add(numericControl19, gridBagConstraints);

        labelControl24.setLabel("Preco Lucro Zero:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        jPanel4.add(labelControl24, gridBagConstraints);

        labelControl25.setLabel("Preco Lucro Minimo:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        jPanel4.add(labelControl25, gridBagConstraints);

        numericControl20.setAttributeName("precoLucroMinimo");
        numericControl20.setDecimals(2);
        numericControl20.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        jPanel4.add(numericControl20, gridBagConstraints);

        labelControl26.setLabel("Preco Lucro Maximo:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        jPanel4.add(labelControl26, gridBagConstraints);

        numericControl21.setAttributeName("precoLucroMaximo");
        numericControl21.setDecimals(2);
        numericControl21.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        jPanel4.add(numericControl21, gridBagConstraints);

        labelControl27.setLabel("Markup:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        jPanel4.add(labelControl27, gridBagConstraints);

        numericControl22.setAttributeName("markup");
        numericControl22.setDecimals(2);
        numericControl22.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        jPanel4.add(numericControl22, gridBagConstraints);

        labelControl28.setLabel("Quantidade Estoque:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        jPanel4.add(labelControl28, gridBagConstraints);

        numericControl23.setAttributeName("quantidadeEstoque");
        numericControl23.setDecimals(2);
        numericControl23.setEnabled(false);
        numericControl23.setLinkLabel(labelControl28);
        numericControl23.setRequired(true);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        jPanel4.add(numericControl23, gridBagConstraints);

        labelControl29.setLabel("Quantidade Estoque Anterior:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        jPanel4.add(labelControl29, gridBagConstraints);

        numericControl24.setAttributeName("quantidadeEstoqueAnterior");
        numericControl24.setDecimals(2);
        numericControl24.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        jPanel4.add(numericControl24, gridBagConstraints);

        labelControl30.setLabel("Estoque Minimo:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        jPanel4.add(labelControl30, gridBagConstraints);

        numericControl25.setAttributeName("estoqueMinimo");
        numericControl25.setDecimals(2);
        numericControl25.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        jPanel4.add(numericControl25, gridBagConstraints);

        labelControl31.setLabel("Estoque Maximo:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        jPanel4.add(labelControl31, gridBagConstraints);

        numericControl26.setAttributeName("estoqueMaximo");
        numericControl26.setDecimals(2);
        numericControl26.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        jPanel4.add(numericControl26, gridBagConstraints);

        labelControl32.setLabel("Estoque Ideal:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        jPanel4.add(labelControl32, gridBagConstraints);

        numericControl27.setAttributeName("estoqueIdeal");
        numericControl27.setDecimals(2);
        numericControl27.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        jPanel4.add(numericControl27, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel4.add(jSeparator1, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.gridwidth = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel3.add(jPanel4, gridBagConstraints);

        codLookupControlNcm.setAttributeName("ncm");
        codLookupControlNcm.setEnableCodBox(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        jPanel3.add(codLookupControlNcm, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel2.add(jPanel3, gridBagConstraints);

        jTabbedPane1.addTab("Principal", jPanel2);

        jPanel5.setLayout(new java.awt.GridBagLayout());

        labelControl37.setLabel("Ex Tipi:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        jPanel5.add(labelControl37, gridBagConstraints);

        textControl32.setAttributeName("exTipi");
        textControl32.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        jPanel5.add(textControl32, gridBagConstraints);

        labelControl38.setLabel("Codigo Lst:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        jPanel5.add(labelControl38, gridBagConstraints);

        textControl33.setAttributeName("codigoLst");
        textControl33.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        jPanel5.add(textControl33, gridBagConstraints);

        labelControl36.setLabel("Foto Produto:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        jPanel5.add(labelControl36, gridBagConstraints);

        labelControl40.setLabel("Iat:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        jPanel5.add(labelControl40, gridBagConstraints);

        comboBoxControl35.setAttributeName("iat");
        comboBoxControl35.setDomainId("produtoIat");
        comboBoxControl35.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        jPanel5.add(comboBoxControl35, gridBagConstraints);

        labelControl41.setLabel("Ippt:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        jPanel5.add(labelControl41, gridBagConstraints);

        comboBoxControl36.setAttributeName("ippt");
        comboBoxControl36.setDomainId("produtoIppt");
        comboBoxControl36.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        jPanel5.add(comboBoxControl36, gridBagConstraints);

        labelControl42.setLabel("Tipo Item Sped:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        jPanel5.add(labelControl42, gridBagConstraints);

        labelControl49.setLabel("Totalizador Parcial:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        jPanel5.add(labelControl49, gridBagConstraints);

        textControl44.setAttributeName("totalizadorParcial");
        textControl44.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        jPanel5.add(textControl44, gridBagConstraints);

        labelControl50.setLabel("Codigo Balanca:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        jPanel5.add(labelControl50, gridBagConstraints);

        numericControl45.setAttributeName("codigoBalanca");
        numericControl45.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        jPanel5.add(numericControl45, gridBagConstraints);

        imageControl1.setAttributeName("imagem");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridheight = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 270;
        gridBagConstraints.ipady = 250;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        jPanel5.add(imageControl1, gridBagConstraints);

        labelControl43.setLabel("Peso:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        jPanel5.add(labelControl43, gridBagConstraints);

        numericControl38.setAttributeName("peso");
        numericControl38.setDecimals(2);
        numericControl38.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        jPanel5.add(numericControl38, gridBagConstraints);

        labelControl44.setLabel("Porcento Comissao:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        jPanel5.add(labelControl44, gridBagConstraints);

        numericControl39.setAttributeName("porcentoComissao");
        numericControl39.setDecimals(2);
        numericControl39.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        jPanel5.add(numericControl39, gridBagConstraints);

        labelControl45.setLabel("Ponto Pedido:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        jPanel5.add(labelControl45, gridBagConstraints);

        numericControl40.setAttributeName("pontoPedido");
        numericControl40.setDecimals(2);
        numericControl40.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        jPanel5.add(numericControl40, gridBagConstraints);

        labelControl46.setLabel("Lote Economico Compra:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        jPanel5.add(labelControl46, gridBagConstraints);

        numericControl41.setAttributeName("loteEconomicoCompra");
        numericControl41.setDecimals(2);
        numericControl41.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        jPanel5.add(numericControl41, gridBagConstraints);

        labelControl47.setLabel("Aliquota Icms Paf:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        jPanel5.add(labelControl47, gridBagConstraints);

        numericControl42.setAttributeName("aliquotaIcmsPaf");
        numericControl42.setDecimals(2);
        numericControl42.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.PAGE_START;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        jPanel5.add(numericControl42, gridBagConstraints);

        labelControl48.setLabel("Aliquota Issqn Paf:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        jPanel5.add(labelControl48, gridBagConstraints);

        numericControl43.setAttributeName("aliquotaIssqnPaf");
        numericControl43.setDecimals(2);
        numericControl43.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.PAGE_START;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        jPanel5.add(numericControl43, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.gridwidth = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel5.add(jSeparator2, gridBagConstraints);

        comboBoxControl1.setAttributeName("tipoItemSped");
        comboBoxControl1.setDomainId("produtoTipoItemSped");
        comboBoxControl1.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        jPanel5.add(comboBoxControl1, gridBagConstraints);

        labelControl52.setLabel("Tipo:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        jPanel5.add(labelControl52, gridBagConstraints);

        comboBoxControl47.setAttributeName("tipo");
        comboBoxControl47.setDomainId("produtoTipo");
        comboBoxControl47.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        jPanel5.add(comboBoxControl47, gridBagConstraints);

        jTabbedPane1.addTab("Dados Complementares", jPanel5);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        form1.add(jTabbedPane1, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        getContentPane().add(form1, gridBagConstraints);

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private org.openswing.swing.client.CodLookupControl codLookupControlAlmoxarifado;
    private org.openswing.swing.client.CodLookupControl codLookupControlGrupoTributario;
    private org.openswing.swing.client.CodLookupControl codLookupControlIcmsCustomizado;
    private org.openswing.swing.client.CodLookupControl codLookupControlMarca;
    private org.openswing.swing.client.CodLookupControl codLookupControlNcm;
    private org.openswing.swing.client.CodLookupControl codLookupControlSubGrupo;
    private org.openswing.swing.client.CodLookupControl codLookupControlUnidade;
    private org.openswing.swing.client.ComboBoxControl comboBoxControl1;
    private org.openswing.swing.client.ComboBoxControl comboBoxControl29;
    private org.openswing.swing.client.ComboBoxControl comboBoxControl34;
    private org.openswing.swing.client.ComboBoxControl comboBoxControl35;
    private org.openswing.swing.client.ComboBoxControl comboBoxControl36;
    private org.openswing.swing.client.ComboBoxControl comboBoxControl47;
    private org.openswing.swing.client.EditButton editButton1;
    private org.openswing.swing.form.client.Form form1;
    private org.openswing.swing.client.ImageControl imageControl1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private org.openswing.swing.client.LabelControl labelControl1;
    private org.openswing.swing.client.LabelControl labelControl11;
    private org.openswing.swing.client.LabelControl labelControl13;
    private org.openswing.swing.client.LabelControl labelControl14;
    private org.openswing.swing.client.LabelControl labelControl15;
    private org.openswing.swing.client.LabelControl labelControl16;
    private org.openswing.swing.client.LabelControl labelControl17;
    private org.openswing.swing.client.LabelControl labelControl18;
    private org.openswing.swing.client.LabelControl labelControl19;
    private org.openswing.swing.client.LabelControl labelControl20;
    private org.openswing.swing.client.LabelControl labelControl21;
    private org.openswing.swing.client.LabelControl labelControl22;
    private org.openswing.swing.client.LabelControl labelControl23;
    private org.openswing.swing.client.LabelControl labelControl24;
    private org.openswing.swing.client.LabelControl labelControl25;
    private org.openswing.swing.client.LabelControl labelControl26;
    private org.openswing.swing.client.LabelControl labelControl27;
    private org.openswing.swing.client.LabelControl labelControl28;
    private org.openswing.swing.client.LabelControl labelControl29;
    private org.openswing.swing.client.LabelControl labelControl3;
    private org.openswing.swing.client.LabelControl labelControl30;
    private org.openswing.swing.client.LabelControl labelControl31;
    private org.openswing.swing.client.LabelControl labelControl32;
    private org.openswing.swing.client.LabelControl labelControl34;
    private org.openswing.swing.client.LabelControl labelControl36;
    private org.openswing.swing.client.LabelControl labelControl37;
    private org.openswing.swing.client.LabelControl labelControl38;
    private org.openswing.swing.client.LabelControl labelControl39;
    private org.openswing.swing.client.LabelControl labelControl40;
    private org.openswing.swing.client.LabelControl labelControl41;
    private org.openswing.swing.client.LabelControl labelControl42;
    private org.openswing.swing.client.LabelControl labelControl43;
    private org.openswing.swing.client.LabelControl labelControl44;
    private org.openswing.swing.client.LabelControl labelControl45;
    private org.openswing.swing.client.LabelControl labelControl46;
    private org.openswing.swing.client.LabelControl labelControl47;
    private org.openswing.swing.client.LabelControl labelControl48;
    private org.openswing.swing.client.LabelControl labelControl49;
    private org.openswing.swing.client.LabelControl labelControl5;
    private org.openswing.swing.client.LabelControl labelControl50;
    private org.openswing.swing.client.LabelControl labelControl52;
    private org.openswing.swing.client.LabelControl labelControl7;
    private org.openswing.swing.client.LabelControl labelControl9;
    private org.openswing.swing.client.NumericControl numericControl14;
    private org.openswing.swing.client.NumericControl numericControl15;
    private org.openswing.swing.client.NumericControl numericControl16;
    private org.openswing.swing.client.NumericControl numericControl17;
    private org.openswing.swing.client.NumericControl numericControl18;
    private org.openswing.swing.client.NumericControl numericControl19;
    private org.openswing.swing.client.NumericControl numericControl20;
    private org.openswing.swing.client.NumericControl numericControl21;
    private org.openswing.swing.client.NumericControl numericControl22;
    private org.openswing.swing.client.NumericControl numericControl23;
    private org.openswing.swing.client.NumericControl numericControl24;
    private org.openswing.swing.client.NumericControl numericControl25;
    private org.openswing.swing.client.NumericControl numericControl26;
    private org.openswing.swing.client.NumericControl numericControl27;
    private org.openswing.swing.client.NumericControl numericControl38;
    private org.openswing.swing.client.NumericControl numericControl39;
    private org.openswing.swing.client.NumericControl numericControl40;
    private org.openswing.swing.client.NumericControl numericControl41;
    private org.openswing.swing.client.NumericControl numericControl42;
    private org.openswing.swing.client.NumericControl numericControl43;
    private org.openswing.swing.client.NumericControl numericControl45;
    private org.openswing.swing.client.ReloadButton reloadButton1;
    private org.openswing.swing.client.SaveButton saveButton1;
    private org.openswing.swing.client.TextAreaControl textAreaControl1;
    private org.openswing.swing.client.TextControl textControl10;
    private org.openswing.swing.client.TextControl textControl12;
    private org.openswing.swing.client.TextControl textControl14;
    private org.openswing.swing.client.TextControl textControl2;
    private org.openswing.swing.client.TextControl textControl3;
    private org.openswing.swing.client.TextControl textControl32;
    private org.openswing.swing.client.TextControl textControl33;
    private org.openswing.swing.client.TextControl textControl4;
    private org.openswing.swing.client.TextControl textControl44;
    private org.openswing.swing.client.TextControl textControl5;
    private org.openswing.swing.client.TextControl textControl6;
    private org.openswing.swing.client.TextControl textControl7;
    private org.openswing.swing.client.TextControl textControl8;
    private org.openswing.swing.client.TextControl textControl9;
    // End of variables declaration//GEN-END:variables

}
