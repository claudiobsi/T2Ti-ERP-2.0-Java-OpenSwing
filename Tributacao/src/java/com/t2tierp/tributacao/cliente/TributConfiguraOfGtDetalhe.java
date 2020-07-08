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
package com.t2tierp.tributacao.cliente;

import com.t2tierp.padrao.cliente.LookupDataLocatorGenerico;
import java.awt.Dimension;
import org.openswing.swing.lookup.client.LookupController;
import org.openswing.swing.mdi.client.InternalFrame;

public class TributConfiguraOfGtDetalhe extends InternalFrame {

    private LookupController grupoTributarioController = new LookupController();
    private LookupController operacaoFiscalController = new LookupController();
    private LookupController ufController = new LookupController();
    private LookupController cfopController = new LookupController();
    private LookupController csosnController = new LookupController();
    private LookupController cstController = new LookupController();
    private LookupController cstPisController = new LookupController();
    private LookupController codigoApuracaoEfdPisController = new LookupController();
    private LookupController cstCofinsController = new LookupController();
    private LookupController codigoApuracaoEfdCofinsController = new LookupController();
    private LookupController cstIpiController = new LookupController();
    private LookupController tipoReceitaDipiController = new LookupController();
    private TributIcmsUfGridController icmsUfGridController;
    private TributPisCodApuracaoDetalheController pisCodApuracaoController;
    private TributCofinsCodApuracaoDetalheController cofinsCodApuracaoController;
    private TributIpiDipiDetalheController ipiDipiController;

    public TributConfiguraOfGtDetalhe(TributConfiguraOfGtDetalheController controller) {
        initComponents();

        form1.setFormController(controller);

        icmsUfGridController = new TributIcmsUfGridController(this);
        gridControlIcms.setController(icmsUfGridController);
        gridControlIcms.setGridDataLocator(icmsUfGridController);

        pisCodApuracaoController = new TributPisCodApuracaoDetalheController(this);
        formPis.setFormController(pisCodApuracaoController);

        cofinsCodApuracaoController = new TributCofinsCodApuracaoDetalheController(this);
        formCofins.setFormController(cofinsCodApuracaoController);

        ipiDipiController = new TributIpiDipiDetalheController(this);
        formIpi.setFormController(ipiDipiController);

        /*
         * Configurações do lookup do grupo tributario
         */
        grupoTributarioController.setLookupValueObjectClassName("com.t2tierp.tributacao.java.TributGrupoTributarioVO");
        grupoTributarioController.addLookup2ParentLink("id", "tributGrupoTributario.id");
        grupoTributarioController.addLookup2ParentLink("descricao", "tributGrupoTributario.descricao");
        grupoTributarioController.setHeaderColumnName("id", "ID");
        grupoTributarioController.setHeaderColumnName("descricao", "Descrição");
        grupoTributarioController.setFrameTitle("Importa Grupo Tributário");

        grupoTributarioController.setVisibleStatusPanel(true);
        grupoTributarioController.setVisibleColumn("id", true);
        grupoTributarioController.setVisibleColumn("descricao", true);
        grupoTributarioController.setFramePreferedSize(new Dimension(600, 500));

        grupoTributarioController.setLookupDataLocator(new LookupDataLocatorGenerico(grupoTributarioController.getLookupValueObjectClassName()));
        codLookupControl2.setLookupController(grupoTributarioController);


        /*
         * Configurações do lookup da operacao fiscal
         */
        operacaoFiscalController.setLookupValueObjectClassName("com.t2tierp.tributacao.java.TributOperacaoFiscalVO");
        operacaoFiscalController.addLookup2ParentLink("id", "tributOperacaoFiscal.id");
        operacaoFiscalController.addLookup2ParentLink("descricao", "tributOperacaoFiscal.descricao");
        operacaoFiscalController.setHeaderColumnName("id", "ID");
        operacaoFiscalController.setHeaderColumnName("descricao", "Descrição");
        operacaoFiscalController.setFrameTitle("Importa Operação Fiscal");

        operacaoFiscalController.setVisibleStatusPanel(true);
        operacaoFiscalController.setVisibleColumn("id", true);
        operacaoFiscalController.setVisibleColumn("descricao", true);
        operacaoFiscalController.setFramePreferedSize(new Dimension(600, 500));

        operacaoFiscalController.setLookupDataLocator(new LookupDataLocatorGenerico(operacaoFiscalController.getLookupValueObjectClassName()));
        codLookupControl3.setLookupController(operacaoFiscalController);

        /*
         * Configurações do lookup da UF
         */
        ufController.setLookupValueObjectClassName("com.t2tierp.cadastros.java.UfVO");
        ufController.addLookup2ParentLink("sigla", "ufDestino");
        ufController.setHeaderColumnName("sigla", "Sigla");
        ufController.setHeaderColumnName("nome", "Nome");
        ufController.setFrameTitle("Importa UF");

        ufController.setVisibleStatusPanel(true);
        ufController.setVisibleColumn("sigla", true);
        ufController.setVisibleColumn("nome", true);
        ufController.setFramePreferedSize(new Dimension(600, 500));

        ufController.setLookupDataLocator(new LookupDataLocatorGenerico(ufController.getLookupValueObjectClassName()));
        codLookupColumn1.setLookupController(ufController);

        /*
         * Configurações do lookup do CFOP
         */
        cfopController.setLookupValueObjectClassName("com.t2tierp.cadastros.java.CfopVO");
        cfopController.addLookup2ParentLink("cfop", "cfop");
        cfopController.setHeaderColumnName("cfop", "CFOP");
        cfopController.setHeaderColumnName("descricao", "Descrição");
        cfopController.setFrameTitle("Importa CFOP");

        cfopController.setVisibleStatusPanel(true);
        cfopController.setVisibleColumn("cfop", true);
        cfopController.setVisibleColumn("descricao", true);
        cfopController.setFramePreferedSize(new Dimension(600, 500));

        cfopController.setLookupDataLocator(new LookupDataLocatorGenerico(cfopController.getLookupValueObjectClassName()));
        codLookupColumn2.setLookupController(cfopController);

        /*
         * Configurações do lookup do CSOSN_B
         */
        csosnController.setLookupValueObjectClassName("com.t2tierp.cadastros.java.CsosnBVO");
        csosnController.addLookup2ParentLink("codigo", "csosnB");
        csosnController.setHeaderColumnName("codigo", "CSOSN_B");
        csosnController.setHeaderColumnName("descricao", "Descrição");
        csosnController.setFrameTitle("Importa CSOSN_B");

        csosnController.setVisibleStatusPanel(true);
        csosnController.setVisibleColumn("codigo", true);
        csosnController.setVisibleColumn("descricao", true);
        csosnController.setFramePreferedSize(new Dimension(600, 500));

        csosnController.setLookupDataLocator(new LookupDataLocatorGenerico(csosnController.getLookupValueObjectClassName()));
        codLookupColumn3.setLookupController(csosnController);

        /*
         * Configurações do lookup do CST_B
         */
        cstController.setLookupValueObjectClassName("com.t2tierp.cadastros.java.CstIcmsBVO");
        cstController.addLookup2ParentLink("codigo", "cstB");
        cstController.setHeaderColumnName("codigo", "CST_B");
        cstController.setHeaderColumnName("descricao", "Descrição");
        cstController.setFrameTitle("Importa CST_B");

        cstController.setVisibleStatusPanel(true);
        cstController.setVisibleColumn("codigo", true);
        cstController.setVisibleColumn("descricao", true);
        cstController.setFramePreferedSize(new Dimension(600, 500));

        cstController.setLookupDataLocator(new LookupDataLocatorGenerico(cstController.getLookupValueObjectClassName()));
        codLookupColumn4.setLookupController(cstController);

        /*
         * Configurações do lookup do CST PIS
         */
        cstPisController.setLookupValueObjectClassName("com.t2tierp.cadastros.java.CstPisVO");
        cstPisController.addLookup2ParentLink("codigo", "cstPis");
        cstPisController.addLookup2ParentLink("descricao", "cstPisVO.descricao");
        cstPisController.setHeaderColumnName("codigo", "CST PIS");
        cstPisController.setHeaderColumnName("descricao", "Descrição");
        cstPisController.setFrameTitle("Importa CST PIS");

        cstPisController.setVisibleStatusPanel(true);
        cstPisController.setVisibleColumn("codigo", true);
        cstPisController.setVisibleColumn("descricao", true);
        cstPisController.setFramePreferedSize(new Dimension(600, 500));

        cstPisController.setLookupDataLocator(new LookupDataLocatorGenerico(cstPisController.getLookupValueObjectClassName()));
        codLookupCstPis.setLookupController(cstPisController);

        /*
         * Configurações do lookup do codigo apuracao EFD PIS
         */
        codigoApuracaoEfdPisController.setLookupValueObjectClassName("com.t2tierp.cadastros.java.EfdTabela435VO");
        codigoApuracaoEfdPisController.addLookup2ParentLink("codigo", "efdTabela435");
        codigoApuracaoEfdPisController.addLookup2ParentLink("descricao", "codigoApuracaoEfdVO.descricao");
        codigoApuracaoEfdPisController.setHeaderColumnName("codigo", "Codigo Apuracao EFD");
        codigoApuracaoEfdPisController.setHeaderColumnName("descricao", "Descrição");
        codigoApuracaoEfdPisController.setFrameTitle("Importa Codigo Apuracao EFD");

        codigoApuracaoEfdPisController.setVisibleStatusPanel(true);
        codigoApuracaoEfdPisController.setVisibleColumn("codigo", true);
        codigoApuracaoEfdPisController.setVisibleColumn("descricao", true);
        codigoApuracaoEfdPisController.setFramePreferedSize(new Dimension(600, 500));

        codigoApuracaoEfdPisController.setLookupDataLocator(new LookupDataLocatorGenerico(codigoApuracaoEfdPisController.getLookupValueObjectClassName()));
        codLookupApuracaoEfdPis.setLookupController(codigoApuracaoEfdPisController);

        /*
         * Configurações do lookup do CST COFINS
         */
        cstCofinsController.setLookupValueObjectClassName("com.t2tierp.cadastros.java.CstCofinsVO");
        cstCofinsController.addLookup2ParentLink("codigo", "cstCofins");
        cstCofinsController.addLookup2ParentLink("descricao", "cstCofinsVO.descricao");
        cstCofinsController.setHeaderColumnName("codigo", "CST COFINS");
        cstCofinsController.setHeaderColumnName("descricao", "Descrição");
        cstCofinsController.setFrameTitle("Importa CST COFINS");

        cstCofinsController.setVisibleStatusPanel(true);
        cstCofinsController.setVisibleColumn("codigo", true);
        cstCofinsController.setVisibleColumn("descricao", true);
        cstCofinsController.setFramePreferedSize(new Dimension(600, 500));

        cstCofinsController.setLookupDataLocator(new LookupDataLocatorGenerico(cstCofinsController.getLookupValueObjectClassName()));
        codLookupCstCofins.setLookupController(cstCofinsController);

        /*
         * Configurações do lookup do codigo apuracao EFD COFINS
         */
        codigoApuracaoEfdCofinsController.setLookupValueObjectClassName("com.t2tierp.cadastros.java.EfdTabela435VO");
        codigoApuracaoEfdCofinsController.addLookup2ParentLink("codigo", "efdTabela435");
        codigoApuracaoEfdCofinsController.addLookup2ParentLink("descricao", "codigoApuracaoEfdVO.descricao");
        codigoApuracaoEfdCofinsController.setHeaderColumnName("codigo", "Codigo Apuracao EFD");
        codigoApuracaoEfdCofinsController.setHeaderColumnName("descricao", "Descrição");
        codigoApuracaoEfdCofinsController.setFrameTitle("Importa Codigo Apuracao EFD");

        codigoApuracaoEfdCofinsController.setVisibleStatusPanel(true);
        codigoApuracaoEfdCofinsController.setVisibleColumn("codigo", true);
        codigoApuracaoEfdCofinsController.setVisibleColumn("descricao", true);
        codigoApuracaoEfdCofinsController.setFramePreferedSize(new Dimension(600, 500));

        codigoApuracaoEfdCofinsController.setLookupDataLocator(new LookupDataLocatorGenerico(codigoApuracaoEfdCofinsController.getLookupValueObjectClassName()));
        codLookupApuracaoEfdCofins.setLookupController(codigoApuracaoEfdCofinsController);

        /*
         * Configurações do lookup do CST IPI
         */
        cstIpiController.setLookupValueObjectClassName("com.t2tierp.cadastros.java.CstIpiVO");
        cstIpiController.addLookup2ParentLink("codigo", "cstIpi");
        cstIpiController.addLookup2ParentLink("descricao", "cstIpiVO.descricao");
        cstIpiController.setHeaderColumnName("codigo", "CST IPI");
        cstIpiController.setHeaderColumnName("descricao", "Descrição");
        cstIpiController.setFrameTitle("Importa CST IPI");

        cstIpiController.setVisibleStatusPanel(true);
        cstIpiController.setVisibleColumn("codigo", true);
        cstIpiController.setVisibleColumn("descricao", true);
        cstIpiController.setFramePreferedSize(new Dimension(600, 500));

        cstIpiController.setLookupDataLocator(new LookupDataLocatorGenerico(cstIpiController.getLookupValueObjectClassName()));
        codLookupCstIpi.setLookupController(cstIpiController);

        /*
         * Configurações do lookup do tipo receita dipi 
         */
        tipoReceitaDipiController.setLookupValueObjectClassName("com.t2tierp.cadastros.java.TipoReceitaDipiVO");
        tipoReceitaDipiController.addLookup2ParentLink("id", "tipoReceitaDipi.id");
        tipoReceitaDipiController.addLookup2ParentLink("descricao", "tipoReceitaDipi.descricao");
        tipoReceitaDipiController.setHeaderColumnName("id", "ID");
        tipoReceitaDipiController.setHeaderColumnName("descricao", "Descrição");
        tipoReceitaDipiController.setFrameTitle("Importa Tipo Receita DIPI");

        tipoReceitaDipiController.setVisibleStatusPanel(true);
        tipoReceitaDipiController.setVisibleColumn("id", true);
        tipoReceitaDipiController.setVisibleColumn("descricao", true);
        tipoReceitaDipiController.setFramePreferedSize(new Dimension(600, 500));

        tipoReceitaDipiController.setLookupDataLocator(new LookupDataLocatorGenerico(tipoReceitaDipiController.getLookupValueObjectClassName()));
        codLookupTipoReceitaDipi.setLookupController(tipoReceitaDipiController);
    }

    public org.openswing.swing.form.client.Form getForm1() {
        return form1;
    }

    public TributIcmsUfGridController getIcmsUfGridController() {
        return icmsUfGridController;
    }

    public org.openswing.swing.client.GridControl getGridControlIcms() {
        return gridControlIcms;
    }

    public org.openswing.swing.form.client.Form getFormPis() {
        return formPis;
    }

    public TributPisCodApuracaoDetalheController getPisCodApuracaoController() {
        return pisCodApuracaoController;
    }

    public org.openswing.swing.form.client.Form getFormCofins() {
        return formCofins;
    }

    public TributCofinsCodApuracaoDetalheController getCofinsCodApuracaoController() {
        return cofinsCodApuracaoController;
    }

    public org.openswing.swing.form.client.Form getFormIpi() {
        return formIpi;
    }

    public TributIpiDipiDetalheController getIpiDipiController() {
        return ipiDipiController;
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
        codLookupControl3 = new org.openswing.swing.client.CodLookupControl();
        textControl3 = new org.openswing.swing.client.TextControl();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        insertButtonIcms = new org.openswing.swing.client.InsertButton();
        editButtonIcms = new org.openswing.swing.client.EditButton();
        deleteButtonIcms = new org.openswing.swing.client.DeleteButton();
        saveButtonIcms = new org.openswing.swing.client.SaveButton();
        reloadButtonIcms = new org.openswing.swing.client.ReloadButton();
        gridControlIcms = new org.openswing.swing.client.GridControl();
        codLookupColumn1 = new org.openswing.swing.table.columns.client.CodLookupColumn();
        codLookupColumn2 = new org.openswing.swing.table.columns.client.CodLookupColumn();
        codLookupColumn3 = new org.openswing.swing.table.columns.client.CodLookupColumn();
        codLookupColumn4 = new org.openswing.swing.table.columns.client.CodLookupColumn();
        comboColumn7 = new org.openswing.swing.table.columns.client.ComboColumn();
        decimalColumn8 = new org.openswing.swing.table.columns.client.DecimalColumn();
        decimalColumn9 = new org.openswing.swing.table.columns.client.DecimalColumn();
        decimalColumn10 = new org.openswing.swing.table.columns.client.DecimalColumn();
        decimalColumn11 = new org.openswing.swing.table.columns.client.DecimalColumn();
        decimalColumn12 = new org.openswing.swing.table.columns.client.DecimalColumn();
        comboColumn13 = new org.openswing.swing.table.columns.client.ComboColumn();
        decimalColumn14 = new org.openswing.swing.table.columns.client.DecimalColumn();
        decimalColumn15 = new org.openswing.swing.table.columns.client.DecimalColumn();
        decimalColumn16 = new org.openswing.swing.table.columns.client.DecimalColumn();
        decimalColumn17 = new org.openswing.swing.table.columns.client.DecimalColumn();
        decimalColumn18 = new org.openswing.swing.table.columns.client.DecimalColumn();
        decimalColumn19 = new org.openswing.swing.table.columns.client.DecimalColumn();
        jPanel4 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        editButtonPis = new org.openswing.swing.client.EditButton();
        reloadButtonPis = new org.openswing.swing.client.ReloadButton();
        saveButtonPis = new org.openswing.swing.client.SaveButton();
        formPis = new org.openswing.swing.form.client.Form();
        labelControl2 = new org.openswing.swing.client.LabelControl();
        codLookupCstPis = new org.openswing.swing.client.CodLookupControl();
        textControl4 = new org.openswing.swing.client.TextControl();
        textControl5 = new org.openswing.swing.client.TextControl();
        labelControl5 = new org.openswing.swing.client.LabelControl();
        comboBoxControl5 = new org.openswing.swing.client.ComboBoxControl();
        labelControl6 = new org.openswing.swing.client.LabelControl();
        numericControl6 = new org.openswing.swing.client.NumericControl();
        labelControl7 = new org.openswing.swing.client.LabelControl();
        numericControl7 = new org.openswing.swing.client.NumericControl();
        labelControl8 = new org.openswing.swing.client.LabelControl();
        numericControl8 = new org.openswing.swing.client.NumericControl();
        labelControl9 = new org.openswing.swing.client.LabelControl();
        numericControl9 = new org.openswing.swing.client.NumericControl();
        labelControl10 = new org.openswing.swing.client.LabelControl();
        numericControl10 = new org.openswing.swing.client.NumericControl();
        codLookupApuracaoEfdPis = new org.openswing.swing.client.CodLookupControl();
        labelControl11 = new org.openswing.swing.client.LabelControl();
        jSeparator1 = new javax.swing.JSeparator();
        jPanel6 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        editButtonCofins = new org.openswing.swing.client.EditButton();
        reloadButtonCofins = new org.openswing.swing.client.ReloadButton();
        saveButtonCofins = new org.openswing.swing.client.SaveButton();
        formCofins = new org.openswing.swing.form.client.Form();
        labelControl4 = new org.openswing.swing.client.LabelControl();
        codLookupCstCofins = new org.openswing.swing.client.CodLookupControl();
        textControl6 = new org.openswing.swing.client.TextControl();
        textControl7 = new org.openswing.swing.client.TextControl();
        labelControl12 = new org.openswing.swing.client.LabelControl();
        comboBoxControl6 = new org.openswing.swing.client.ComboBoxControl();
        labelControl13 = new org.openswing.swing.client.LabelControl();
        numericControl11 = new org.openswing.swing.client.NumericControl();
        labelControl14 = new org.openswing.swing.client.LabelControl();
        numericControl12 = new org.openswing.swing.client.NumericControl();
        labelControl15 = new org.openswing.swing.client.LabelControl();
        numericControl13 = new org.openswing.swing.client.NumericControl();
        labelControl16 = new org.openswing.swing.client.LabelControl();
        numericControl14 = new org.openswing.swing.client.NumericControl();
        labelControl17 = new org.openswing.swing.client.LabelControl();
        numericControl15 = new org.openswing.swing.client.NumericControl();
        codLookupApuracaoEfdCofins = new org.openswing.swing.client.CodLookupControl();
        labelControl18 = new org.openswing.swing.client.LabelControl();
        jSeparator2 = new javax.swing.JSeparator();
        jPanel8 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        editButtonIpi = new org.openswing.swing.client.EditButton();
        reloadButtonIpi = new org.openswing.swing.client.ReloadButton();
        saveButtonIpi = new org.openswing.swing.client.SaveButton();
        formIpi = new org.openswing.swing.form.client.Form();
        labelControl19 = new org.openswing.swing.client.LabelControl();
        codLookupCstIpi = new org.openswing.swing.client.CodLookupControl();
        textControl8 = new org.openswing.swing.client.TextControl();
        textControl9 = new org.openswing.swing.client.TextControl();
        labelControl20 = new org.openswing.swing.client.LabelControl();
        comboBoxControl7 = new org.openswing.swing.client.ComboBoxControl();
        labelControl21 = new org.openswing.swing.client.LabelControl();
        numericControl16 = new org.openswing.swing.client.NumericControl();
        labelControl22 = new org.openswing.swing.client.LabelControl();
        numericControl17 = new org.openswing.swing.client.NumericControl();
        labelControl23 = new org.openswing.swing.client.LabelControl();
        numericControl18 = new org.openswing.swing.client.NumericControl();
        labelControl24 = new org.openswing.swing.client.LabelControl();
        numericControl19 = new org.openswing.swing.client.NumericControl();
        labelControl25 = new org.openswing.swing.client.LabelControl();
        numericControl20 = new org.openswing.swing.client.NumericControl();
        codLookupTipoReceitaDipi = new org.openswing.swing.client.CodLookupControl();
        labelControl26 = new org.openswing.swing.client.LabelControl();
        jSeparator3 = new javax.swing.JSeparator();

        setTitle("T2Ti ERP - Tributacao");
        setPreferredSize(new java.awt.Dimension(700, 400));
        getContentPane().setLayout(new java.awt.GridBagLayout());

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Tribut Configura Of Gt"));
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

        form1.setVOClassName("com.t2tierp.tributacao.java.TributConfiguraOfGtVO");
        form1.setEditButton(editButton1);
        form1.setFunctionId("tributConfiguraOfGt");
        form1.setReloadButton(reloadButton1);
        form1.setSaveButton(saveButton1);
        form1.setLayout(new java.awt.GridBagLayout());

        labelControl1.setText("Grupo Tributario:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        form1.add(labelControl1, gridBagConstraints);

        codLookupControl2.setAllowOnlyNumbers(true);
        codLookupControl2.setAttributeName("tributGrupoTributario.id");
        codLookupControl2.setEnabled(false);
        codLookupControl2.setMaxCharacters(10);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = -100;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        form1.add(codLookupControl2, gridBagConstraints);

        textControl2.setAttributeName("tributGrupoTributario.descricao");
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

        labelControl3.setText("Operacao Fiscal:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        form1.add(labelControl3, gridBagConstraints);

        codLookupControl3.setAllowOnlyNumbers(true);
        codLookupControl3.setAttributeName("tributOperacaoFiscal.id");
        codLookupControl3.setEnabled(false);
        codLookupControl3.setMaxCharacters(10);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = -100;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        form1.add(codLookupControl3, gridBagConstraints);

        textControl3.setAttributeName("tributOperacaoFiscal.descricao");
        textControl3.setEnabled(false);
        textControl3.setEnabledOnEdit(false);
        textControl3.setEnabledOnInsert(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        form1.add(textControl3, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        getContentPane().add(form1, gridBagConstraints);

        jPanel2.setLayout(new java.awt.GridBagLayout());

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Tribut Icms Uf"));
        jPanel3.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));
        jPanel3.add(insertButtonIcms);
        jPanel3.add(editButtonIcms);
        jPanel3.add(deleteButtonIcms);
        jPanel3.add(saveButtonIcms);
        jPanel3.add(reloadButtonIcms);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        jPanel2.add(jPanel3, gridBagConstraints);

        gridControlIcms.setAutoLoadData(false);
        gridControlIcms.setDeleteButton(deleteButtonIcms);
        gridControlIcms.setEditButton(editButtonIcms);
        gridControlIcms.setFunctionId("tributIcmsUf");
        gridControlIcms.setInsertButton(insertButtonIcms);
        gridControlIcms.setReloadButton(reloadButtonIcms);
        gridControlIcms.setSaveButton(saveButtonIcms);
        gridControlIcms.setValueObjectClassName("com.t2tierp.tributacao.java.TributIcmsUfVO");
        gridControlIcms.getColumnContainer().setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        codLookupColumn1.setColumnName("ufDestino");
        codLookupColumn1.setColumnRequired(false);
        codLookupColumn1.setEditableOnEdit(true);
        codLookupColumn1.setEditableOnInsert(true);
        codLookupColumn1.setHeaderColumnName("UF Destino");
        codLookupColumn1.setHeaderFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        codLookupColumn1.setMaxCharacters(2);
        gridControlIcms.getColumnContainer().add(codLookupColumn1);

        codLookupColumn2.setAllowOnlyNumbers(true);
        codLookupColumn2.setColumnName("cfop");
        codLookupColumn2.setColumnRequired(false);
        codLookupColumn2.setEditableOnEdit(true);
        codLookupColumn2.setEditableOnInsert(true);
        codLookupColumn2.setHeaderColumnName("CFOP");
        codLookupColumn2.setHeaderFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        codLookupColumn2.setMaxCharacters(4);
        gridControlIcms.getColumnContainer().add(codLookupColumn2);

        codLookupColumn3.setColumnName("csosnB");
        codLookupColumn3.setColumnRequired(false);
        codLookupColumn3.setEditableOnEdit(true);
        codLookupColumn3.setEditableOnInsert(true);
        codLookupColumn3.setHeaderColumnName("CSOSN B");
        codLookupColumn3.setHeaderFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        codLookupColumn3.setMaxCharacters(3);
        gridControlIcms.getColumnContainer().add(codLookupColumn3);

        codLookupColumn4.setColumnName("cstB");
        codLookupColumn4.setColumnRequired(false);
        codLookupColumn4.setEditableOnEdit(true);
        codLookupColumn4.setEditableOnInsert(true);
        codLookupColumn4.setHeaderColumnName("CST B");
        codLookupColumn4.setHeaderFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        codLookupColumn4.setMaxCharacters(2);
        gridControlIcms.getColumnContainer().add(codLookupColumn4);

        comboColumn7.setColumnName("modalidadeBc");
        comboColumn7.setDomainId("tributIcmsBaseCalculo");
        comboColumn7.setEditableOnEdit(true);
        comboColumn7.setEditableOnInsert(true);
        comboColumn7.setHeaderColumnName("Modalidade Bc");
        comboColumn7.setHeaderFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        gridControlIcms.getColumnContainer().add(comboColumn7);

        decimalColumn8.setColumnName("aliquota");
        decimalColumn8.setColumnRequired(false);
        decimalColumn8.setDecimals(2);
        decimalColumn8.setEditableOnEdit(true);
        decimalColumn8.setEditableOnInsert(true);
        decimalColumn8.setHeaderColumnName("Aliquota");
        decimalColumn8.setHeaderFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        gridControlIcms.getColumnContainer().add(decimalColumn8);

        decimalColumn9.setColumnName("valorPauta");
        decimalColumn9.setColumnRequired(false);
        decimalColumn9.setDecimals(2);
        decimalColumn9.setEditableOnEdit(true);
        decimalColumn9.setEditableOnInsert(true);
        decimalColumn9.setHeaderColumnName("Valor Pauta");
        decimalColumn9.setHeaderFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        gridControlIcms.getColumnContainer().add(decimalColumn9);

        decimalColumn10.setColumnName("valorPrecoMaximo");
        decimalColumn10.setColumnRequired(false);
        decimalColumn10.setDecimals(2);
        decimalColumn10.setEditableOnEdit(true);
        decimalColumn10.setEditableOnInsert(true);
        decimalColumn10.setHeaderColumnName("Valor Preco Maximo");
        decimalColumn10.setHeaderFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        gridControlIcms.getColumnContainer().add(decimalColumn10);

        decimalColumn11.setColumnName("mva");
        decimalColumn11.setColumnRequired(false);
        decimalColumn11.setDecimals(2);
        decimalColumn11.setEditableOnEdit(true);
        decimalColumn11.setEditableOnInsert(true);
        decimalColumn11.setHeaderColumnName("Mva");
        decimalColumn11.setHeaderFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        gridControlIcms.getColumnContainer().add(decimalColumn11);

        decimalColumn12.setColumnName("porcentoBc");
        decimalColumn12.setColumnRequired(false);
        decimalColumn12.setDecimals(2);
        decimalColumn12.setEditableOnEdit(true);
        decimalColumn12.setEditableOnInsert(true);
        decimalColumn12.setHeaderColumnName("Porcento Bc");
        decimalColumn12.setHeaderFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        gridControlIcms.getColumnContainer().add(decimalColumn12);

        comboColumn13.setColumnName("modalidadeBcSt");
        comboColumn13.setColumnRequired(false);
        comboColumn13.setDomainId("tributIcmsStBaseCalculo");
        comboColumn13.setEditableOnEdit(true);
        comboColumn13.setEditableOnInsert(true);
        comboColumn13.setHeaderColumnName("Modalidade Bc St");
        comboColumn13.setHeaderFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        gridControlIcms.getColumnContainer().add(comboColumn13);

        decimalColumn14.setColumnName("aliquotaInternaSt");
        decimalColumn14.setColumnRequired(false);
        decimalColumn14.setDecimals(2);
        decimalColumn14.setEditableOnEdit(true);
        decimalColumn14.setEditableOnInsert(true);
        decimalColumn14.setHeaderColumnName("Aliquota Interna St");
        decimalColumn14.setHeaderFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        gridControlIcms.getColumnContainer().add(decimalColumn14);

        decimalColumn15.setColumnName("aliquotaInterestadualSt");
        decimalColumn15.setColumnRequired(false);
        decimalColumn15.setDecimals(2);
        decimalColumn15.setEditableOnEdit(true);
        decimalColumn15.setEditableOnInsert(true);
        decimalColumn15.setHeaderColumnName("Aliquota Interestadual St");
        decimalColumn15.setHeaderFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        gridControlIcms.getColumnContainer().add(decimalColumn15);

        decimalColumn16.setColumnName("porcentoBcSt");
        decimalColumn16.setColumnRequired(false);
        decimalColumn16.setDecimals(2);
        decimalColumn16.setEditableOnEdit(true);
        decimalColumn16.setEditableOnInsert(true);
        decimalColumn16.setHeaderColumnName("Porcento Bc St");
        decimalColumn16.setHeaderFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        gridControlIcms.getColumnContainer().add(decimalColumn16);

        decimalColumn17.setColumnName("aliquotaIcmsSt");
        decimalColumn17.setColumnRequired(false);
        decimalColumn17.setDecimals(2);
        decimalColumn17.setEditableOnEdit(true);
        decimalColumn17.setEditableOnInsert(true);
        decimalColumn17.setHeaderColumnName("Aliquota Icms St");
        decimalColumn17.setHeaderFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        gridControlIcms.getColumnContainer().add(decimalColumn17);

        decimalColumn18.setColumnName("valorPautaSt");
        decimalColumn18.setColumnRequired(false);
        decimalColumn18.setDecimals(2);
        decimalColumn18.setEditableOnEdit(true);
        decimalColumn18.setEditableOnInsert(true);
        decimalColumn18.setHeaderColumnName("Valor Pauta St");
        decimalColumn18.setHeaderFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        gridControlIcms.getColumnContainer().add(decimalColumn18);

        decimalColumn19.setColumnName("valorPrecoMaximoSt");
        decimalColumn19.setColumnRequired(false);
        decimalColumn19.setDecimals(2);
        decimalColumn19.setEditableOnEdit(true);
        decimalColumn19.setEditableOnInsert(true);
        decimalColumn19.setHeaderColumnName("Valor Preco Maximo St");
        decimalColumn19.setHeaderFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        gridControlIcms.getColumnContainer().add(decimalColumn19);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel2.add(gridControlIcms, gridBagConstraints);

        jTabbedPane1.addTab("ICMS", jPanel2);

        jPanel4.setLayout(new java.awt.GridBagLayout());

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder("Tribut Pis Cod Apuracao"));
        jPanel5.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));
        jPanel5.add(editButtonPis);
        jPanel5.add(reloadButtonPis);
        jPanel5.add(saveButtonPis);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        jPanel4.add(jPanel5, gridBagConstraints);

        formPis.setVOClassName("com.t2tierp.tributacao.java.TributPisCodApuracaoVO");
        formPis.setEditButton(editButtonPis);
        formPis.setFunctionId("tributPisCodApuracao");
        formPis.setReloadButton(reloadButtonPis);
        formPis.setSaveButton(saveButtonPis);
        formPis.setLayout(new java.awt.GridBagLayout());

        labelControl2.setText("CST:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        formPis.add(labelControl2, gridBagConstraints);

        codLookupCstPis.setAttributeName("cstPis");
        codLookupCstPis.setEnabled(false);
        codLookupCstPis.setMaxCharacters(2);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = -100;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        formPis.add(codLookupCstPis, gridBagConstraints);

        textControl4.setAttributeName("cstPisVO.descricao");
        textControl4.setEnabled(false);
        textControl4.setEnabledOnEdit(false);
        textControl4.setEnabledOnInsert(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        formPis.add(textControl4, gridBagConstraints);

        textControl5.setAttributeName("codigoApuracaoEfdVO.descricao");
        textControl5.setEnabled(false);
        textControl5.setEnabledOnEdit(false);
        textControl5.setEnabledOnInsert(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        formPis.add(textControl5, gridBagConstraints);

        labelControl5.setLabel("Modalidade Base Calculo:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        formPis.add(labelControl5, gridBagConstraints);

        comboBoxControl5.setAttributeName("modalidadeBaseCalculo");
        comboBoxControl5.setDomainId("pisModalidadeBaseCalculo");
        comboBoxControl5.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        formPis.add(comboBoxControl5, gridBagConstraints);

        labelControl6.setLabel("Porcento Base Calculo:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        formPis.add(labelControl6, gridBagConstraints);

        numericControl6.setAttributeName("porcentoBaseCalculo");
        numericControl6.setDecimals(2);
        numericControl6.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        formPis.add(numericControl6, gridBagConstraints);

        labelControl7.setLabel("Aliquota Porcento:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        formPis.add(labelControl7, gridBagConstraints);

        numericControl7.setAttributeName("aliquotaPorcento");
        numericControl7.setDecimals(2);
        numericControl7.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        formPis.add(numericControl7, gridBagConstraints);

        labelControl8.setLabel("Aliquota Unidade:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        formPis.add(labelControl8, gridBagConstraints);

        numericControl8.setAttributeName("aliquotaUnidade");
        numericControl8.setDecimals(2);
        numericControl8.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        formPis.add(numericControl8, gridBagConstraints);

        labelControl9.setLabel("Valor Preco Maximo:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        formPis.add(labelControl9, gridBagConstraints);

        numericControl9.setAttributeName("valorPrecoMaximo");
        numericControl9.setDecimals(2);
        numericControl9.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        formPis.add(numericControl9, gridBagConstraints);

        labelControl10.setLabel("Valor Pauta Fiscal:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        formPis.add(labelControl10, gridBagConstraints);

        numericControl10.setAttributeName("valorPautaFiscal");
        numericControl10.setDecimals(2);
        numericControl10.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        formPis.add(numericControl10, gridBagConstraints);

        codLookupApuracaoEfdPis.setAttributeName("efdTabela435");
        codLookupApuracaoEfdPis.setEnabled(false);
        codLookupApuracaoEfdPis.setMaxCharacters(2);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = -100;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        formPis.add(codLookupApuracaoEfdPis, gridBagConstraints);

        labelControl11.setText("Codigo Apuracao EFD:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        formPis.add(labelControl11, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.gridwidth = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        formPis.add(jSeparator1, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel4.add(formPis, gridBagConstraints);

        jTabbedPane1.addTab("PIS", jPanel4);

        jPanel6.setLayout(new java.awt.GridBagLayout());

        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder("Tribut Cofins Cod Apuracao"));
        jPanel7.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));
        jPanel7.add(editButtonCofins);
        jPanel7.add(reloadButtonCofins);
        jPanel7.add(saveButtonCofins);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        jPanel6.add(jPanel7, gridBagConstraints);

        formCofins.setVOClassName("com.t2tierp.tributacao.java.TributCofinsCodApuracaoVO");
        formCofins.setEditButton(editButtonCofins);
        formCofins.setFunctionId("tributCofinsCodApuracao");
        formCofins.setReloadButton(reloadButtonCofins);
        formCofins.setSaveButton(saveButtonCofins);
        formCofins.setLayout(new java.awt.GridBagLayout());

        labelControl4.setText("CST:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        formCofins.add(labelControl4, gridBagConstraints);

        codLookupCstCofins.setAttributeName("cstCofins");
        codLookupCstCofins.setEnabled(false);
        codLookupCstCofins.setMaxCharacters(2);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = -100;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        formCofins.add(codLookupCstCofins, gridBagConstraints);

        textControl6.setAttributeName("cstCofinsVO.descricao");
        textControl6.setEnabled(false);
        textControl6.setEnabledOnEdit(false);
        textControl6.setEnabledOnInsert(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        formCofins.add(textControl6, gridBagConstraints);

        textControl7.setAttributeName("codigoApuracaoEfdVO.descricao");
        textControl7.setEnabled(false);
        textControl7.setEnabledOnEdit(false);
        textControl7.setEnabledOnInsert(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        formCofins.add(textControl7, gridBagConstraints);

        labelControl12.setLabel("Modalidade Base Calculo:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        formCofins.add(labelControl12, gridBagConstraints);

        comboBoxControl6.setAttributeName("modalidadeBaseCalculo");
        comboBoxControl6.setDomainId("pisModalidadeBaseCalculo");
        comboBoxControl6.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        formCofins.add(comboBoxControl6, gridBagConstraints);

        labelControl13.setLabel("Porcento Base Calculo:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        formCofins.add(labelControl13, gridBagConstraints);

        numericControl11.setAttributeName("porcentoBaseCalculo");
        numericControl11.setDecimals(2);
        numericControl11.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        formCofins.add(numericControl11, gridBagConstraints);

        labelControl14.setLabel("Aliquota Porcento:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        formCofins.add(labelControl14, gridBagConstraints);

        numericControl12.setAttributeName("aliquotaPorcento");
        numericControl12.setDecimals(2);
        numericControl12.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        formCofins.add(numericControl12, gridBagConstraints);

        labelControl15.setLabel("Aliquota Unidade:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        formCofins.add(labelControl15, gridBagConstraints);

        numericControl13.setAttributeName("aliquotaUnidade");
        numericControl13.setDecimals(2);
        numericControl13.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        formCofins.add(numericControl13, gridBagConstraints);

        labelControl16.setLabel("Valor Preco Maximo:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        formCofins.add(labelControl16, gridBagConstraints);

        numericControl14.setAttributeName("valorPrecoMaximo");
        numericControl14.setDecimals(2);
        numericControl14.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        formCofins.add(numericControl14, gridBagConstraints);

        labelControl17.setLabel("Valor Pauta Fiscal:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        formCofins.add(labelControl17, gridBagConstraints);

        numericControl15.setAttributeName("valorPautaFiscal");
        numericControl15.setDecimals(2);
        numericControl15.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        formCofins.add(numericControl15, gridBagConstraints);

        codLookupApuracaoEfdCofins.setAttributeName("codigoApuracaoEfdVO.id");
        codLookupApuracaoEfdCofins.setEnabled(false);
        codLookupApuracaoEfdCofins.setMaxCharacters(2);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = -100;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        formCofins.add(codLookupApuracaoEfdCofins, gridBagConstraints);

        labelControl18.setText("Codigo Apuracao EFD:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        formCofins.add(labelControl18, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.gridwidth = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        formCofins.add(jSeparator2, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel6.add(formCofins, gridBagConstraints);

        jTabbedPane1.addTab("COFINS", jPanel6);

        jPanel8.setLayout(new java.awt.GridBagLayout());

        jPanel9.setBorder(javax.swing.BorderFactory.createTitledBorder("Tribut Ipi Cod Apuracao"));
        jPanel9.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));
        jPanel9.add(editButtonIpi);
        jPanel9.add(reloadButtonIpi);
        jPanel9.add(saveButtonIpi);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        jPanel8.add(jPanel9, gridBagConstraints);

        formIpi.setVOClassName("com.t2tierp.tributacao.java.TributIpiDipiVO");
        formIpi.setEditButton(editButtonIpi);
        formIpi.setFunctionId("tributIpiDipi");
        formIpi.setReloadButton(reloadButtonIpi);
        formIpi.setSaveButton(saveButtonIpi);
        formIpi.setLayout(new java.awt.GridBagLayout());

        labelControl19.setText("CST:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        formIpi.add(labelControl19, gridBagConstraints);

        codLookupCstIpi.setAttributeName("cstIpi");
        codLookupCstIpi.setEnabled(false);
        codLookupCstIpi.setMaxCharacters(2);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = -100;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        formIpi.add(codLookupCstIpi, gridBagConstraints);

        textControl8.setAttributeName("cstIpiVO.descricao");
        textControl8.setEnabled(false);
        textControl8.setEnabledOnEdit(false);
        textControl8.setEnabledOnInsert(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        formIpi.add(textControl8, gridBagConstraints);

        textControl9.setAttributeName("tipoReceitaDipi.descricao");
        textControl9.setEnabled(false);
        textControl9.setEnabledOnEdit(false);
        textControl9.setEnabledOnInsert(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        formIpi.add(textControl9, gridBagConstraints);

        labelControl20.setLabel("Modalidade Base Calculo:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        formIpi.add(labelControl20, gridBagConstraints);

        comboBoxControl7.setAttributeName("modalidadeBaseCalculo");
        comboBoxControl7.setDomainId("pisModalidadeBaseCalculo");
        comboBoxControl7.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        formIpi.add(comboBoxControl7, gridBagConstraints);

        labelControl21.setLabel("Porcento Base Calculo:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        formIpi.add(labelControl21, gridBagConstraints);

        numericControl16.setAttributeName("porcentoBaseCalculo");
        numericControl16.setDecimals(2);
        numericControl16.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        formIpi.add(numericControl16, gridBagConstraints);

        labelControl22.setLabel("Aliquota Porcento:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        formIpi.add(labelControl22, gridBagConstraints);

        numericControl17.setAttributeName("aliquotaPorcento");
        numericControl17.setDecimals(2);
        numericControl17.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        formIpi.add(numericControl17, gridBagConstraints);

        labelControl23.setLabel("Aliquota Unidade:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        formIpi.add(labelControl23, gridBagConstraints);

        numericControl18.setAttributeName("aliquotaUnidade");
        numericControl18.setDecimals(2);
        numericControl18.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        formIpi.add(numericControl18, gridBagConstraints);

        labelControl24.setLabel("Valor Preco Maximo:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        formIpi.add(labelControl24, gridBagConstraints);

        numericControl19.setAttributeName("valorPrecoMaximo");
        numericControl19.setDecimals(2);
        numericControl19.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        formIpi.add(numericControl19, gridBagConstraints);

        labelControl25.setLabel("Valor Pauta Fiscal:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        formIpi.add(labelControl25, gridBagConstraints);

        numericControl20.setAttributeName("valorPautaFiscal");
        numericControl20.setDecimals(2);
        numericControl20.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        formIpi.add(numericControl20, gridBagConstraints);

        codLookupTipoReceitaDipi.setAllowOnlyNumbers(true);
        codLookupTipoReceitaDipi.setAttributeName("tipoReceitaDipi.id");
        codLookupTipoReceitaDipi.setEnabled(false);
        codLookupTipoReceitaDipi.setMaxCharacters(2);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = -100;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        formIpi.add(codLookupTipoReceitaDipi, gridBagConstraints);

        labelControl26.setText("Tipo Receita DIPI:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        formIpi.add(labelControl26, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.gridwidth = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        formIpi.add(jSeparator3, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel8.add(formIpi, gridBagConstraints);

        jTabbedPane1.addTab("IPI", jPanel8);

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
    private org.openswing.swing.client.CodLookupControl codLookupApuracaoEfdCofins;
    private org.openswing.swing.client.CodLookupControl codLookupApuracaoEfdPis;
    private org.openswing.swing.table.columns.client.CodLookupColumn codLookupColumn1;
    private org.openswing.swing.table.columns.client.CodLookupColumn codLookupColumn2;
    private org.openswing.swing.table.columns.client.CodLookupColumn codLookupColumn3;
    private org.openswing.swing.table.columns.client.CodLookupColumn codLookupColumn4;
    private org.openswing.swing.client.CodLookupControl codLookupControl2;
    private org.openswing.swing.client.CodLookupControl codLookupControl3;
    private org.openswing.swing.client.CodLookupControl codLookupCstCofins;
    private org.openswing.swing.client.CodLookupControl codLookupCstIpi;
    private org.openswing.swing.client.CodLookupControl codLookupCstPis;
    private org.openswing.swing.client.CodLookupControl codLookupTipoReceitaDipi;
    private org.openswing.swing.client.ComboBoxControl comboBoxControl5;
    private org.openswing.swing.client.ComboBoxControl comboBoxControl6;
    private org.openswing.swing.client.ComboBoxControl comboBoxControl7;
    private org.openswing.swing.table.columns.client.ComboColumn comboColumn13;
    private org.openswing.swing.table.columns.client.ComboColumn comboColumn7;
    private org.openswing.swing.table.columns.client.DecimalColumn decimalColumn10;
    private org.openswing.swing.table.columns.client.DecimalColumn decimalColumn11;
    private org.openswing.swing.table.columns.client.DecimalColumn decimalColumn12;
    private org.openswing.swing.table.columns.client.DecimalColumn decimalColumn14;
    private org.openswing.swing.table.columns.client.DecimalColumn decimalColumn15;
    private org.openswing.swing.table.columns.client.DecimalColumn decimalColumn16;
    private org.openswing.swing.table.columns.client.DecimalColumn decimalColumn17;
    private org.openswing.swing.table.columns.client.DecimalColumn decimalColumn18;
    private org.openswing.swing.table.columns.client.DecimalColumn decimalColumn19;
    private org.openswing.swing.table.columns.client.DecimalColumn decimalColumn8;
    private org.openswing.swing.table.columns.client.DecimalColumn decimalColumn9;
    private org.openswing.swing.client.DeleteButton deleteButtonIcms;
    private org.openswing.swing.client.EditButton editButton1;
    private org.openswing.swing.client.EditButton editButtonCofins;
    private org.openswing.swing.client.EditButton editButtonIcms;
    private org.openswing.swing.client.EditButton editButtonIpi;
    private org.openswing.swing.client.EditButton editButtonPis;
    private org.openswing.swing.form.client.Form form1;
    private org.openswing.swing.form.client.Form formCofins;
    private org.openswing.swing.form.client.Form formIpi;
    private org.openswing.swing.form.client.Form formPis;
    private org.openswing.swing.client.GridControl gridControlIcms;
    private org.openswing.swing.client.InsertButton insertButtonIcms;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
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
    private org.openswing.swing.client.LabelControl labelControl2;
    private org.openswing.swing.client.LabelControl labelControl20;
    private org.openswing.swing.client.LabelControl labelControl21;
    private org.openswing.swing.client.LabelControl labelControl22;
    private org.openswing.swing.client.LabelControl labelControl23;
    private org.openswing.swing.client.LabelControl labelControl24;
    private org.openswing.swing.client.LabelControl labelControl25;
    private org.openswing.swing.client.LabelControl labelControl26;
    private org.openswing.swing.client.LabelControl labelControl3;
    private org.openswing.swing.client.LabelControl labelControl4;
    private org.openswing.swing.client.LabelControl labelControl5;
    private org.openswing.swing.client.LabelControl labelControl6;
    private org.openswing.swing.client.LabelControl labelControl7;
    private org.openswing.swing.client.LabelControl labelControl8;
    private org.openswing.swing.client.LabelControl labelControl9;
    private org.openswing.swing.client.NumericControl numericControl10;
    private org.openswing.swing.client.NumericControl numericControl11;
    private org.openswing.swing.client.NumericControl numericControl12;
    private org.openswing.swing.client.NumericControl numericControl13;
    private org.openswing.swing.client.NumericControl numericControl14;
    private org.openswing.swing.client.NumericControl numericControl15;
    private org.openswing.swing.client.NumericControl numericControl16;
    private org.openswing.swing.client.NumericControl numericControl17;
    private org.openswing.swing.client.NumericControl numericControl18;
    private org.openswing.swing.client.NumericControl numericControl19;
    private org.openswing.swing.client.NumericControl numericControl20;
    private org.openswing.swing.client.NumericControl numericControl6;
    private org.openswing.swing.client.NumericControl numericControl7;
    private org.openswing.swing.client.NumericControl numericControl8;
    private org.openswing.swing.client.NumericControl numericControl9;
    private org.openswing.swing.client.ReloadButton reloadButton1;
    private org.openswing.swing.client.ReloadButton reloadButtonCofins;
    private org.openswing.swing.client.ReloadButton reloadButtonIcms;
    private org.openswing.swing.client.ReloadButton reloadButtonIpi;
    private org.openswing.swing.client.ReloadButton reloadButtonPis;
    private org.openswing.swing.client.SaveButton saveButton1;
    private org.openswing.swing.client.SaveButton saveButtonCofins;
    private org.openswing.swing.client.SaveButton saveButtonIcms;
    private org.openswing.swing.client.SaveButton saveButtonIpi;
    private org.openswing.swing.client.SaveButton saveButtonPis;
    private org.openswing.swing.client.TextControl textControl2;
    private org.openswing.swing.client.TextControl textControl3;
    private org.openswing.swing.client.TextControl textControl4;
    private org.openswing.swing.client.TextControl textControl5;
    private org.openswing.swing.client.TextControl textControl6;
    private org.openswing.swing.client.TextControl textControl7;
    private org.openswing.swing.client.TextControl textControl8;
    private org.openswing.swing.client.TextControl textControl9;
    // End of variables declaration//GEN-END:variables

}
