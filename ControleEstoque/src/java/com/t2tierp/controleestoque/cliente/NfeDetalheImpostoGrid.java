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
package com.t2tierp.controleestoque.cliente;

public class NfeDetalheImpostoGrid extends javax.swing.JDialog {

    private NfeDetalheImpostoIcmsDetalheController icmsController;
    private NfeDetalheImpostoPisDetalheController pisController;
    private NfeDetalheImpostoCofinsDetalheController cofinsController;
    private NfeDetalheImpostoIpiDetalheController ipiController;
    private NfeDetalheImpostoIiDetalheController iiController;
    private NfeDetalheImpostoIssqnDetalheController issqnController;
    private NfeDetEspecificoCombustivelDetalheController combustivelController;
    private NfeDetEspecificoVeiculoDetalheController veiculoController;
    private NfeDetEspecificoMedicamentoGridController medicamentoController;
    private NfeDetEspecificoArmamentoGridController armamentoController;
    private NfeDeclaracaoImportacaoGridController declaracaoController;
    private NfeImportacaoDetalheGridController adicaoController;

    public NfeDetalheImpostoGrid(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        this.setSize(900,400);
        this.setLocationRelativeTo(null);

        icmsController = new NfeDetalheImpostoIcmsDetalheController();
        formIcms.setFormController(icmsController);
        
        pisController = new NfeDetalheImpostoPisDetalheController();
        formPis.setFormController(pisController);

        cofinsController = new NfeDetalheImpostoCofinsDetalheController();
        formCofins.setFormController(cofinsController);
        
        ipiController = new NfeDetalheImpostoIpiDetalheController();
        formIpi.setFormController(ipiController);

        iiController = new NfeDetalheImpostoIiDetalheController();
        formIi.setFormController(iiController);

        issqnController = new NfeDetalheImpostoIssqnDetalheController();
        formIssqn.setFormController(issqnController);

        combustivelController = new NfeDetEspecificoCombustivelDetalheController();
        formCombustivel.setFormController(combustivelController);
        
        veiculoController = new NfeDetEspecificoVeiculoDetalheController();
        formVeiculo.setFormController(veiculoController);

        medicamentoController = new NfeDetEspecificoMedicamentoGridController();
        gridControlMedicamento.setController(medicamentoController);
        gridControlMedicamento.setGridDataLocator(medicamentoController);

        armamentoController = new NfeDetEspecificoArmamentoGridController();
        gridControlArmamento.setController(armamentoController);
        gridControlArmamento.setGridDataLocator(armamentoController);

        declaracaoController = new NfeDeclaracaoImportacaoGridController(this);
        gridControlDeclaracaoImportacao.setController(declaracaoController);
        gridControlDeclaracaoImportacao.setGridDataLocator(declaracaoController);

        adicaoController = new NfeImportacaoDetalheGridController();
        gridControlAdicao.setController(adicaoController);
        gridControlAdicao.setGridDataLocator(adicaoController);
    }

    public NfeDetalheImpostoIcmsDetalheController getIcmsController() {
        return icmsController;
    }

    public org.openswing.swing.form.client.Form getFormIcms() {
        return formIcms;
    }

    public NfeDetalheImpostoPisDetalheController getPisController() {
        return pisController;
    }

    public org.openswing.swing.form.client.Form getFormPis() {
        return formPis;
    }

    public NfeDetalheImpostoCofinsDetalheController getCofinsController() {
        return cofinsController;
    }

    public org.openswing.swing.form.client.Form getFormCofins() {
        return formCofins;
    }

    public NfeDetalheImpostoIpiDetalheController getIpiController() {
        return ipiController;
    }

    public org.openswing.swing.form.client.Form getFormIpi() {
        return formIpi;
    }

    public NfeDetalheImpostoIiDetalheController getIiController() {
        return iiController;
    }

    public org.openswing.swing.form.client.Form getFormIi() {
        return formIi;
    }

    public NfeDetalheImpostoIssqnDetalheController getIssqnController() {
        return issqnController;
    }

    public org.openswing.swing.form.client.Form getFormIssqn() {
        return formIssqn;
    }

    public NfeDetEspecificoCombustivelDetalheController getCombustivelController() {
        return combustivelController;
    }

    public org.openswing.swing.form.client.Form getFormCombustivel() {
        return formCombustivel;
    }

    public NfeDetEspecificoVeiculoDetalheController getVeiculoController() {
        return veiculoController;
    }

    public org.openswing.swing.form.client.Form getFormVeiculo() {
        return formVeiculo;
    }

    public NfeDetEspecificoMedicamentoGridController getMedicamentoController() {
        return medicamentoController;
    }

    public org.openswing.swing.client.GridControl getGridControlMedicamento() {
        return gridControlMedicamento;
    }

    public NfeDetEspecificoArmamentoGridController getArmamentoController() {
        return armamentoController;
    }

    public org.openswing.swing.client.GridControl getGridControlArmamento() {
        return gridControlArmamento;
    }

    public NfeDeclaracaoImportacaoGridController getDeclaracaoController() {
        return declaracaoController;
    }

    public org.openswing.swing.client.GridControl getGridControlDeclaracaoImportacao() {
        return gridControlDeclaracaoImportacao;
    }

    public NfeImportacaoDetalheGridController getAdicaoController() {
        return adicaoController;
    }

    public org.openswing.swing.client.GridControl getGridControlAdicao() {
        return gridControlAdicao;
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

        jTabbedPane1 = new javax.swing.JTabbedPane();
        formIcms = new org.openswing.swing.form.client.Form();
        labelControl3 = new org.openswing.swing.client.LabelControl();
        comboBoxControl3 = new org.openswing.swing.client.ComboBoxControl();
        labelControl4 = new org.openswing.swing.client.LabelControl();
        textControl4 = new org.openswing.swing.client.TextControl();
        labelControl5 = new org.openswing.swing.client.LabelControl();
        textControl5 = new org.openswing.swing.client.TextControl();
        labelControl6 = new org.openswing.swing.client.LabelControl();
        comboBoxControl6 = new org.openswing.swing.client.ComboBoxControl();
        labelControl7 = new org.openswing.swing.client.LabelControl();
        numericControl7 = new org.openswing.swing.client.NumericControl();
        labelControl8 = new org.openswing.swing.client.LabelControl();
        numericControl8 = new org.openswing.swing.client.NumericControl();
        labelControl9 = new org.openswing.swing.client.LabelControl();
        numericControl9 = new org.openswing.swing.client.NumericControl();
        labelControl10 = new org.openswing.swing.client.LabelControl();
        numericControl10 = new org.openswing.swing.client.NumericControl();
        labelControl11 = new org.openswing.swing.client.LabelControl();
        comboBoxControl11 = new org.openswing.swing.client.ComboBoxControl();
        labelControl12 = new org.openswing.swing.client.LabelControl();
        comboBoxControl12 = new org.openswing.swing.client.ComboBoxControl();
        labelControl13 = new org.openswing.swing.client.LabelControl();
        numericControl13 = new org.openswing.swing.client.NumericControl();
        labelControl14 = new org.openswing.swing.client.LabelControl();
        numericControl14 = new org.openswing.swing.client.NumericControl();
        labelControl15 = new org.openswing.swing.client.LabelControl();
        numericControl15 = new org.openswing.swing.client.NumericControl();
        labelControl16 = new org.openswing.swing.client.LabelControl();
        numericControl16 = new org.openswing.swing.client.NumericControl();
        labelControl17 = new org.openswing.swing.client.LabelControl();
        numericControl17 = new org.openswing.swing.client.NumericControl();
        labelControl18 = new org.openswing.swing.client.LabelControl();
        numericControl18 = new org.openswing.swing.client.NumericControl();
        labelControl19 = new org.openswing.swing.client.LabelControl();
        numericControl19 = new org.openswing.swing.client.NumericControl();
        labelControl20 = new org.openswing.swing.client.LabelControl();
        numericControl20 = new org.openswing.swing.client.NumericControl();
        labelControl21 = new org.openswing.swing.client.LabelControl();
        numericControl21 = new org.openswing.swing.client.NumericControl();
        labelControl22 = new org.openswing.swing.client.LabelControl();
        numericControl22 = new org.openswing.swing.client.NumericControl();
        labelControl23 = new org.openswing.swing.client.LabelControl();
        numericControl23 = new org.openswing.swing.client.NumericControl();
        labelControl24 = new org.openswing.swing.client.LabelControl();
        numericControl24 = new org.openswing.swing.client.NumericControl();
        labelControl25 = new org.openswing.swing.client.LabelControl();
        textControl25 = new org.openswing.swing.client.TextControl();
        jSeparator1 = new javax.swing.JSeparator();
        formPis = new org.openswing.swing.form.client.Form();
        labelControl26 = new org.openswing.swing.client.LabelControl();
        textControl3 = new org.openswing.swing.client.TextControl();
        labelControl27 = new org.openswing.swing.client.LabelControl();
        numericControl4 = new org.openswing.swing.client.NumericControl();
        labelControl28 = new org.openswing.swing.client.LabelControl();
        numericControl5 = new org.openswing.swing.client.NumericControl();
        labelControl29 = new org.openswing.swing.client.LabelControl();
        numericControl6 = new org.openswing.swing.client.NumericControl();
        labelControl30 = new org.openswing.swing.client.LabelControl();
        numericControl11 = new org.openswing.swing.client.NumericControl();
        labelControl31 = new org.openswing.swing.client.LabelControl();
        numericControl12 = new org.openswing.swing.client.NumericControl();
        jSeparator2 = new javax.swing.JSeparator();
        formCofins = new org.openswing.swing.form.client.Form();
        labelControl32 = new org.openswing.swing.client.LabelControl();
        textControl6 = new org.openswing.swing.client.TextControl();
        labelControl33 = new org.openswing.swing.client.LabelControl();
        numericControl25 = new org.openswing.swing.client.NumericControl();
        labelControl34 = new org.openswing.swing.client.LabelControl();
        numericControl26 = new org.openswing.swing.client.NumericControl();
        labelControl35 = new org.openswing.swing.client.LabelControl();
        numericControl27 = new org.openswing.swing.client.NumericControl();
        labelControl36 = new org.openswing.swing.client.LabelControl();
        numericControl28 = new org.openswing.swing.client.NumericControl();
        labelControl37 = new org.openswing.swing.client.LabelControl();
        numericControl29 = new org.openswing.swing.client.NumericControl();
        jSeparator3 = new javax.swing.JSeparator();
        formIpi = new org.openswing.swing.form.client.Form();
        labelControl38 = new org.openswing.swing.client.LabelControl();
        textControl7 = new org.openswing.swing.client.TextControl();
        labelControl39 = new org.openswing.swing.client.LabelControl();
        textControl8 = new org.openswing.swing.client.TextControl();
        labelControl40 = new org.openswing.swing.client.LabelControl();
        textControl9 = new org.openswing.swing.client.TextControl();
        labelControl41 = new org.openswing.swing.client.LabelControl();
        numericControl30 = new org.openswing.swing.client.NumericControl();
        labelControl42 = new org.openswing.swing.client.LabelControl();
        textControl10 = new org.openswing.swing.client.TextControl();
        labelControl43 = new org.openswing.swing.client.LabelControl();
        textControl11 = new org.openswing.swing.client.TextControl();
        labelControl44 = new org.openswing.swing.client.LabelControl();
        numericControl31 = new org.openswing.swing.client.NumericControl();
        labelControl45 = new org.openswing.swing.client.LabelControl();
        numericControl32 = new org.openswing.swing.client.NumericControl();
        labelControl46 = new org.openswing.swing.client.LabelControl();
        numericControl33 = new org.openswing.swing.client.NumericControl();
        labelControl47 = new org.openswing.swing.client.LabelControl();
        numericControl34 = new org.openswing.swing.client.NumericControl();
        labelControl48 = new org.openswing.swing.client.LabelControl();
        numericControl35 = new org.openswing.swing.client.NumericControl();
        jSeparator4 = new javax.swing.JSeparator();
        formIi = new org.openswing.swing.form.client.Form();
        labelControl49 = new org.openswing.swing.client.LabelControl();
        numericControl3 = new org.openswing.swing.client.NumericControl();
        labelControl50 = new org.openswing.swing.client.LabelControl();
        numericControl36 = new org.openswing.swing.client.NumericControl();
        labelControl51 = new org.openswing.swing.client.LabelControl();
        numericControl37 = new org.openswing.swing.client.NumericControl();
        labelControl52 = new org.openswing.swing.client.LabelControl();
        numericControl38 = new org.openswing.swing.client.NumericControl();
        jSeparator5 = new javax.swing.JSeparator();
        formIssqn = new org.openswing.swing.form.client.Form();
        labelControl53 = new org.openswing.swing.client.LabelControl();
        numericControl39 = new org.openswing.swing.client.NumericControl();
        labelControl54 = new org.openswing.swing.client.LabelControl();
        numericControl40 = new org.openswing.swing.client.NumericControl();
        labelControl55 = new org.openswing.swing.client.LabelControl();
        numericControl41 = new org.openswing.swing.client.NumericControl();
        labelControl56 = new org.openswing.swing.client.LabelControl();
        numericControl42 = new org.openswing.swing.client.NumericControl();
        labelControl57 = new org.openswing.swing.client.LabelControl();
        numericControl43 = new org.openswing.swing.client.NumericControl();
        labelControl58 = new org.openswing.swing.client.LabelControl();
        comboBoxControl8 = new org.openswing.swing.client.ComboBoxControl();
        jSeparator6 = new javax.swing.JSeparator();
        formCombustivel = new org.openswing.swing.form.client.Form();
        labelControl59 = new org.openswing.swing.client.LabelControl();
        numericControl44 = new org.openswing.swing.client.NumericControl();
        labelControl60 = new org.openswing.swing.client.LabelControl();
        textControl12 = new org.openswing.swing.client.TextControl();
        labelControl61 = new org.openswing.swing.client.LabelControl();
        numericControl45 = new org.openswing.swing.client.NumericControl();
        labelControl62 = new org.openswing.swing.client.LabelControl();
        textControl13 = new org.openswing.swing.client.TextControl();
        labelControl63 = new org.openswing.swing.client.LabelControl();
        numericControl46 = new org.openswing.swing.client.NumericControl();
        labelControl64 = new org.openswing.swing.client.LabelControl();
        numericControl47 = new org.openswing.swing.client.NumericControl();
        labelControl65 = new org.openswing.swing.client.LabelControl();
        numericControl48 = new org.openswing.swing.client.NumericControl();
        jSeparator7 = new javax.swing.JSeparator();
        formVeiculo = new org.openswing.swing.form.client.Form();
        labelControl66 = new org.openswing.swing.client.LabelControl();
        comboBoxControl4 = new org.openswing.swing.client.ComboBoxControl();
        labelControl67 = new org.openswing.swing.client.LabelControl();
        textControl14 = new org.openswing.swing.client.TextControl();
        labelControl68 = new org.openswing.swing.client.LabelControl();
        textControl15 = new org.openswing.swing.client.TextControl();
        labelControl69 = new org.openswing.swing.client.LabelControl();
        textControl16 = new org.openswing.swing.client.TextControl();
        labelControl70 = new org.openswing.swing.client.LabelControl();
        textControl17 = new org.openswing.swing.client.TextControl();
        labelControl71 = new org.openswing.swing.client.LabelControl();
        textControl18 = new org.openswing.swing.client.TextControl();
        labelControl72 = new org.openswing.swing.client.LabelControl();
        textControl19 = new org.openswing.swing.client.TextControl();
        labelControl73 = new org.openswing.swing.client.LabelControl();
        textControl20 = new org.openswing.swing.client.TextControl();
        labelControl74 = new org.openswing.swing.client.LabelControl();
        textControl21 = new org.openswing.swing.client.TextControl();
        labelControl75 = new org.openswing.swing.client.LabelControl();
        textControl22 = new org.openswing.swing.client.TextControl();
        labelControl76 = new org.openswing.swing.client.LabelControl();
        textControl23 = new org.openswing.swing.client.TextControl();
        labelControl77 = new org.openswing.swing.client.LabelControl();
        textControl24 = new org.openswing.swing.client.TextControl();
        labelControl78 = new org.openswing.swing.client.LabelControl();
        textControl26 = new org.openswing.swing.client.TextControl();
        labelControl79 = new org.openswing.swing.client.LabelControl();
        textControl27 = new org.openswing.swing.client.TextControl();
        labelControl80 = new org.openswing.swing.client.LabelControl();
        textControl28 = new org.openswing.swing.client.TextControl();
        labelControl81 = new org.openswing.swing.client.LabelControl();
        labelControl82 = new org.openswing.swing.client.LabelControl();
        textControl29 = new org.openswing.swing.client.TextControl();
        labelControl83 = new org.openswing.swing.client.LabelControl();
        comboBoxControl20 = new org.openswing.swing.client.ComboBoxControl();
        labelControl84 = new org.openswing.swing.client.LabelControl();
        comboBoxControl21 = new org.openswing.swing.client.ComboBoxControl();
        labelControl85 = new org.openswing.swing.client.LabelControl();
        comboBoxControl22 = new org.openswing.swing.client.ComboBoxControl();
        labelControl86 = new org.openswing.swing.client.LabelControl();
        textControl30 = new org.openswing.swing.client.TextControl();
        labelControl87 = new org.openswing.swing.client.LabelControl();
        textControl31 = new org.openswing.swing.client.TextControl();
        labelControl88 = new org.openswing.swing.client.LabelControl();
        numericControl49 = new org.openswing.swing.client.NumericControl();
        labelControl89 = new org.openswing.swing.client.LabelControl();
        comboBoxControl26 = new org.openswing.swing.client.ComboBoxControl();
        jSeparator8 = new javax.swing.JSeparator();
        textControl32 = new org.openswing.swing.client.TextControl();
        gridControlMedicamento = new org.openswing.swing.client.GridControl();
        textColumn3 = new org.openswing.swing.table.columns.client.TextColumn();
        decimalColumn4 = new org.openswing.swing.table.columns.client.DecimalColumn();
        dateColumn5 = new org.openswing.swing.table.columns.client.DateColumn();
        dateColumn6 = new org.openswing.swing.table.columns.client.DateColumn();
        decimalColumn7 = new org.openswing.swing.table.columns.client.DecimalColumn();
        gridControlArmamento = new org.openswing.swing.client.GridControl();
        comboColumn3 = new org.openswing.swing.table.columns.client.ComboColumn();
        textColumn4 = new org.openswing.swing.table.columns.client.TextColumn();
        textColumn5 = new org.openswing.swing.table.columns.client.TextColumn();
        textColumn6 = new org.openswing.swing.table.columns.client.TextColumn();
        jPanel1 = new javax.swing.JPanel();
        gridControlDeclaracaoImportacao = new org.openswing.swing.client.GridControl();
        textColumn7 = new org.openswing.swing.table.columns.client.TextColumn();
        dateColumn4 = new org.openswing.swing.table.columns.client.DateColumn();
        textColumn8 = new org.openswing.swing.table.columns.client.TextColumn();
        textColumn9 = new org.openswing.swing.table.columns.client.TextColumn();
        dateColumn7 = new org.openswing.swing.table.columns.client.DateColumn();
        textColumn10 = new org.openswing.swing.table.columns.client.TextColumn();
        jPanel2 = new javax.swing.JPanel();
        gridControlAdicao = new org.openswing.swing.client.GridControl();
        integerColumn3 = new org.openswing.swing.table.columns.client.IntegerColumn();
        integerColumn4 = new org.openswing.swing.table.columns.client.IntegerColumn();
        textColumn11 = new org.openswing.swing.table.columns.client.TextColumn();
        decimalColumn6 = new org.openswing.swing.table.columns.client.DecimalColumn();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("NFe Detalhe Imposto");
        setMinimumSize(new java.awt.Dimension(800, 248));
        getContentPane().setLayout(new java.awt.CardLayout());

        formIcms.setVOClassName("com.t2tierp.nfe.java.NfeDetalheImpostoIcmsVO");
        formIcms.setFunctionId("nfeDetalheImpostoIcms");
        formIcms.setLayout(new java.awt.GridBagLayout());

        labelControl3.setLabel("Origem Mercadoria:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        formIcms.add(labelControl3, gridBagConstraints);

        comboBoxControl3.setAttributeName("origemMercadoria");
        comboBoxControl3.setDomainId("origemMercadoria");
        comboBoxControl3.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        formIcms.add(comboBoxControl3, gridBagConstraints);

        labelControl4.setLabel("Cst Icms:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        formIcms.add(labelControl4, gridBagConstraints);

        textControl4.setAttributeName("cstIcms");
        textControl4.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        formIcms.add(textControl4, gridBagConstraints);

        labelControl5.setLabel("Csosn:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        formIcms.add(labelControl5, gridBagConstraints);

        textControl5.setAttributeName("csosn");
        textControl5.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        formIcms.add(textControl5, gridBagConstraints);

        labelControl6.setLabel("Modalidade Bc Icms:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        formIcms.add(labelControl6, gridBagConstraints);

        comboBoxControl6.setAttributeName("modalidadeBcIcms");
        comboBoxControl6.setDomainId("tributIcmsBaseCalculo");
        comboBoxControl6.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        formIcms.add(comboBoxControl6, gridBagConstraints);

        labelControl7.setLabel("Taxa Reducao Bc Icms:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        formIcms.add(labelControl7, gridBagConstraints);

        numericControl7.setAttributeName("taxaReducaoBcIcms");
        numericControl7.setDecimals(2);
        numericControl7.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        formIcms.add(numericControl7, gridBagConstraints);

        labelControl8.setLabel("Base Calculo Icms:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        formIcms.add(labelControl8, gridBagConstraints);

        numericControl8.setAttributeName("baseCalculoIcms");
        numericControl8.setDecimals(2);
        numericControl8.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        formIcms.add(numericControl8, gridBagConstraints);

        labelControl9.setLabel("Aliquota Icms:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        formIcms.add(labelControl9, gridBagConstraints);

        numericControl9.setAttributeName("aliquotaIcms");
        numericControl9.setDecimals(2);
        numericControl9.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        formIcms.add(numericControl9, gridBagConstraints);

        labelControl10.setLabel("Valor Icms:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        formIcms.add(labelControl10, gridBagConstraints);

        numericControl10.setAttributeName("valorIcms");
        numericControl10.setDecimals(2);
        numericControl10.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        formIcms.add(numericControl10, gridBagConstraints);

        labelControl11.setLabel("Motivo Desoneracao Icms:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        formIcms.add(labelControl11, gridBagConstraints);

        comboBoxControl11.setAttributeName("motivoDesoneracaoIcms");
        comboBoxControl11.setDomainId("motivoDesoneracaoIcms");
        comboBoxControl11.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 11;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        formIcms.add(comboBoxControl11, gridBagConstraints);

        labelControl12.setLabel("Modalidade Bc Icms St:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        formIcms.add(labelControl12, gridBagConstraints);

        comboBoxControl12.setAttributeName("modalidadeBcIcmsSt");
        comboBoxControl12.setDomainId("tributIcmsStBaseCalculo");
        comboBoxControl12.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 9;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        formIcms.add(comboBoxControl12, gridBagConstraints);

        labelControl13.setLabel("Percentual Mva Icms St:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        formIcms.add(labelControl13, gridBagConstraints);

        numericControl13.setAttributeName("percentualMvaIcmsSt");
        numericControl13.setDecimals(2);
        numericControl13.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        formIcms.add(numericControl13, gridBagConstraints);

        labelControl14.setLabel("Percentual Reducao Bc Icms St:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        formIcms.add(labelControl14, gridBagConstraints);

        numericControl14.setAttributeName("percentualReducaoBcIcmsSt");
        numericControl14.setDecimals(2);
        numericControl14.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        formIcms.add(numericControl14, gridBagConstraints);

        labelControl15.setLabel("Valor Base Calculo Icms St:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        formIcms.add(labelControl15, gridBagConstraints);

        numericControl15.setAttributeName("valorBaseCalculoIcmsSt");
        numericControl15.setDecimals(2);
        numericControl15.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        formIcms.add(numericControl15, gridBagConstraints);

        labelControl16.setLabel("Aliquota Icms St:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        formIcms.add(labelControl16, gridBagConstraints);

        numericControl16.setAttributeName("aliquotaIcmsSt");
        numericControl16.setDecimals(2);
        numericControl16.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        formIcms.add(numericControl16, gridBagConstraints);

        labelControl17.setLabel("Valor Icms St:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        formIcms.add(labelControl17, gridBagConstraints);

        numericControl17.setAttributeName("valorIcmsSt");
        numericControl17.setDecimals(2);
        numericControl17.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 11;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        formIcms.add(numericControl17, gridBagConstraints);

        labelControl18.setLabel("Valor Bc Icms St Retido:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        formIcms.add(labelControl18, gridBagConstraints);

        numericControl18.setAttributeName("valorBcIcmsStRetido");
        numericControl18.setDecimals(2);
        numericControl18.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 9;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        formIcms.add(numericControl18, gridBagConstraints);

        labelControl19.setLabel("Valor Icms St Retido:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        formIcms.add(labelControl19, gridBagConstraints);

        numericControl19.setAttributeName("valorIcmsStRetido");
        numericControl19.setDecimals(2);
        numericControl19.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        formIcms.add(numericControl19, gridBagConstraints);

        labelControl20.setLabel("Valor Bc Icms St Destino:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        formIcms.add(labelControl20, gridBagConstraints);

        numericControl20.setAttributeName("valorBcIcmsStDestino");
        numericControl20.setDecimals(2);
        numericControl20.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        formIcms.add(numericControl20, gridBagConstraints);

        labelControl21.setLabel("Valor Icms St Destino:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        formIcms.add(labelControl21, gridBagConstraints);

        numericControl21.setAttributeName("valorIcmsStDestino");
        numericControl21.setDecimals(2);
        numericControl21.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        formIcms.add(numericControl21, gridBagConstraints);

        labelControl22.setLabel("Aliquota Credito Icms Sn:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        formIcms.add(labelControl22, gridBagConstraints);

        numericControl22.setAttributeName("aliquotaCreditoIcmsSn");
        numericControl22.setDecimals(2);
        numericControl22.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        formIcms.add(numericControl22, gridBagConstraints);

        labelControl23.setLabel("Valor Credito Icms Sn:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        formIcms.add(labelControl23, gridBagConstraints);

        numericControl23.setAttributeName("valorCreditoIcmsSn");
        numericControl23.setDecimals(2);
        numericControl23.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 11;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        formIcms.add(numericControl23, gridBagConstraints);

        labelControl24.setLabel("Percentual Bc Operacao Propria:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        formIcms.add(labelControl24, gridBagConstraints);

        numericControl24.setAttributeName("percentualBcOperacaoPropria");
        numericControl24.setDecimals(2);
        numericControl24.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 9;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        formIcms.add(numericControl24, gridBagConstraints);

        labelControl25.setLabel("Uf St:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        formIcms.add(labelControl25, gridBagConstraints);

        textControl25.setAttributeName("ufSt");
        textControl25.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 9;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        formIcms.add(textControl25, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 12;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        formIcms.add(jSeparator1, gridBagConstraints);

        jTabbedPane1.addTab("ICMS", formIcms);

        formPis.setVOClassName("com.t2tierp.nfe.java.NfeDetalheImpostoPisVO");
        formPis.setFunctionId("nfeDetalheImpostoPis");
        formPis.setLayout(new java.awt.GridBagLayout());

        labelControl26.setLabel("Cst Pis:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        formPis.add(labelControl26, gridBagConstraints);

        textControl3.setAttributeName("cstPis");
        textControl3.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        formPis.add(textControl3, gridBagConstraints);

        labelControl27.setLabel("Quantidade Vendida:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        formPis.add(labelControl27, gridBagConstraints);

        numericControl4.setAttributeName("quantidadeVendida");
        numericControl4.setDecimals(2);
        numericControl4.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        formPis.add(numericControl4, gridBagConstraints);

        labelControl28.setLabel("Valor Base Calculo Pis:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        formPis.add(labelControl28, gridBagConstraints);

        numericControl5.setAttributeName("valorBaseCalculoPis");
        numericControl5.setDecimals(2);
        numericControl5.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        formPis.add(numericControl5, gridBagConstraints);

        labelControl29.setLabel("Aliquota Pis Percentual:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        formPis.add(labelControl29, gridBagConstraints);

        numericControl6.setAttributeName("aliquotaPisPercentual");
        numericControl6.setDecimals(2);
        numericControl6.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        formPis.add(numericControl6, gridBagConstraints);

        labelControl30.setLabel("Aliquota Pis Reais:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        formPis.add(labelControl30, gridBagConstraints);

        numericControl11.setAttributeName("aliquotaPisReais");
        numericControl11.setDecimals(2);
        numericControl11.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        formPis.add(numericControl11, gridBagConstraints);

        labelControl31.setLabel("Valor Pis:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        formPis.add(labelControl31, gridBagConstraints);

        numericControl12.setAttributeName("valorPis");
        numericControl12.setDecimals(2);
        numericControl12.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        formPis.add(numericControl12, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        formPis.add(jSeparator2, gridBagConstraints);

        jTabbedPane1.addTab("PIS", formPis);

        formCofins.setVOClassName("com.t2tierp.nfe.java.NfeDetalheImpostoCofinsVO");
        formCofins.setFunctionId("nfeDetalheImpostoCofins");
        formCofins.setLayout(new java.awt.GridBagLayout());

        labelControl32.setLabel("Cst Cofins:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        formCofins.add(labelControl32, gridBagConstraints);

        textControl6.setAttributeName("cstCofins");
        textControl6.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        formCofins.add(textControl6, gridBagConstraints);

        labelControl33.setLabel("Quantidade Vendida:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        formCofins.add(labelControl33, gridBagConstraints);

        numericControl25.setAttributeName("quantidadeVendida");
        numericControl25.setDecimals(2);
        numericControl25.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        formCofins.add(numericControl25, gridBagConstraints);

        labelControl34.setLabel("Base Calculo Cofins:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        formCofins.add(labelControl34, gridBagConstraints);

        numericControl26.setAttributeName("baseCalculoCofins");
        numericControl26.setDecimals(2);
        numericControl26.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        formCofins.add(numericControl26, gridBagConstraints);

        labelControl35.setLabel("Aliquota Cofins Percentual:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        formCofins.add(labelControl35, gridBagConstraints);

        numericControl27.setAttributeName("aliquotaCofinsPercentual");
        numericControl27.setDecimals(2);
        numericControl27.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        formCofins.add(numericControl27, gridBagConstraints);

        labelControl36.setLabel("Aliquota Cofins Reais:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        formCofins.add(labelControl36, gridBagConstraints);

        numericControl28.setAttributeName("aliquotaCofinsReais");
        numericControl28.setDecimals(2);
        numericControl28.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        formCofins.add(numericControl28, gridBagConstraints);

        labelControl37.setLabel("Valor Cofins:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        formCofins.add(labelControl37, gridBagConstraints);

        numericControl29.setAttributeName("valorCofins");
        numericControl29.setDecimals(2);
        numericControl29.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        formCofins.add(numericControl29, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        formCofins.add(jSeparator3, gridBagConstraints);

        jTabbedPane1.addTab("COFINS", formCofins);

        formIpi.setVOClassName("com.t2tierp.nfe.java.NfeDetalheImpostoIpiVO");
        formIpi.setFunctionId("nfeDetalheImpostoIpi");
        formIpi.setLayout(new java.awt.GridBagLayout());

        labelControl38.setLabel("Enquadramento Ipi:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        formIpi.add(labelControl38, gridBagConstraints);

        textControl7.setAttributeName("enquadramentoIpi");
        textControl7.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        formIpi.add(textControl7, gridBagConstraints);

        labelControl39.setLabel("Cnpj Produtor:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        formIpi.add(labelControl39, gridBagConstraints);

        textControl8.setAttributeName("cnpjProdutor");
        textControl8.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        formIpi.add(textControl8, gridBagConstraints);

        labelControl40.setLabel("Codigo Selo Ipi:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        formIpi.add(labelControl40, gridBagConstraints);

        textControl9.setAttributeName("codigoSeloIpi");
        textControl9.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        formIpi.add(textControl9, gridBagConstraints);

        labelControl41.setLabel("Quantidade Selo Ipi:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        formIpi.add(labelControl41, gridBagConstraints);

        numericControl30.setAttributeName("quantidadeSeloIpi");
        numericControl30.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        formIpi.add(numericControl30, gridBagConstraints);

        labelControl42.setLabel("Enquadramento Legal Ipi:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        formIpi.add(labelControl42, gridBagConstraints);

        textControl10.setAttributeName("enquadramentoLegalIpi");
        textControl10.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        formIpi.add(textControl10, gridBagConstraints);

        labelControl43.setLabel("Cst Ipi:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        formIpi.add(labelControl43, gridBagConstraints);

        textControl11.setAttributeName("cstIpi");
        textControl11.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        formIpi.add(textControl11, gridBagConstraints);

        labelControl44.setLabel("Valor Base Calculo Ipi:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        formIpi.add(labelControl44, gridBagConstraints);

        numericControl31.setAttributeName("valorBaseCalculoIpi");
        numericControl31.setDecimals(2);
        numericControl31.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        formIpi.add(numericControl31, gridBagConstraints);

        labelControl45.setLabel("Aliquota Ipi:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        formIpi.add(labelControl45, gridBagConstraints);

        numericControl32.setAttributeName("aliquotaIpi");
        numericControl32.setDecimals(2);
        numericControl32.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        formIpi.add(numericControl32, gridBagConstraints);

        labelControl46.setLabel("Quantidade Unidade Tributavel:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        formIpi.add(labelControl46, gridBagConstraints);

        numericControl33.setAttributeName("quantidadeUnidadeTributavel");
        numericControl33.setDecimals(2);
        numericControl33.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        formIpi.add(numericControl33, gridBagConstraints);

        labelControl47.setLabel("Valor Unidade Tributavel:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        formIpi.add(labelControl47, gridBagConstraints);

        numericControl34.setAttributeName("valorUnidadeTributavel");
        numericControl34.setDecimals(2);
        numericControl34.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        formIpi.add(numericControl34, gridBagConstraints);

        labelControl48.setLabel("Valor Ipi:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        formIpi.add(labelControl48, gridBagConstraints);

        numericControl35.setAttributeName("valorIpi");
        numericControl35.setDecimals(2);
        numericControl35.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        formIpi.add(numericControl35, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        formIpi.add(jSeparator4, gridBagConstraints);

        jTabbedPane1.addTab("IPI", formIpi);

        formIi.setVOClassName("com.t2tierp.nfe.java.NfeDetalheImpostoIiVO");
        formIi.setFunctionId("nfeDetalheImpostoIi");
        formIi.setLayout(new java.awt.GridBagLayout());

        labelControl49.setLabel("Valor Bc Ii:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        formIi.add(labelControl49, gridBagConstraints);

        numericControl3.setAttributeName("valorBcIi");
        numericControl3.setDecimals(2);
        numericControl3.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        formIi.add(numericControl3, gridBagConstraints);

        labelControl50.setLabel("Valor Despesas Aduaneiras:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        formIi.add(labelControl50, gridBagConstraints);

        numericControl36.setAttributeName("valorDespesasAduaneiras");
        numericControl36.setDecimals(2);
        numericControl36.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        formIi.add(numericControl36, gridBagConstraints);

        labelControl51.setLabel("Valor Imposto Importacao:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        formIi.add(labelControl51, gridBagConstraints);

        numericControl37.setAttributeName("valorImpostoImportacao");
        numericControl37.setDecimals(2);
        numericControl37.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        formIi.add(numericControl37, gridBagConstraints);

        labelControl52.setLabel("Valor Iof:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        formIi.add(labelControl52, gridBagConstraints);

        numericControl38.setAttributeName("valorIof");
        numericControl38.setDecimals(2);
        numericControl38.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        formIi.add(numericControl38, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        formIi.add(jSeparator5, gridBagConstraints);

        jTabbedPane1.addTab("Imposto Importação", formIi);

        formIssqn.setVOClassName("com.t2tierp.nfe.java.NfeDetalheImpostoIssqnVO");
        formIssqn.setFunctionId("nfeDetalheImpostoIssqn");
        formIssqn.setLayout(new java.awt.GridBagLayout());

        labelControl53.setLabel("Base Calculo Issqn:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        formIssqn.add(labelControl53, gridBagConstraints);

        numericControl39.setAttributeName("baseCalculoIssqn");
        numericControl39.setDecimals(2);
        numericControl39.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        formIssqn.add(numericControl39, gridBagConstraints);

        labelControl54.setLabel("Aliquota Issqn:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        formIssqn.add(labelControl54, gridBagConstraints);

        numericControl40.setAttributeName("aliquotaIssqn");
        numericControl40.setDecimals(2);
        numericControl40.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        formIssqn.add(numericControl40, gridBagConstraints);

        labelControl55.setLabel("Valor Issqn:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        formIssqn.add(labelControl55, gridBagConstraints);

        numericControl41.setAttributeName("valorIssqn");
        numericControl41.setDecimals(2);
        numericControl41.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        formIssqn.add(numericControl41, gridBagConstraints);

        labelControl56.setLabel("Municipio Issqn:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        formIssqn.add(labelControl56, gridBagConstraints);

        numericControl42.setAttributeName("municipioIssqn");
        numericControl42.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        formIssqn.add(numericControl42, gridBagConstraints);

        labelControl57.setLabel("Item Lista Servicos:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        formIssqn.add(labelControl57, gridBagConstraints);

        numericControl43.setAttributeName("itemListaServicos");
        numericControl43.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        formIssqn.add(numericControl43, gridBagConstraints);

        labelControl58.setLabel("Tributacao Issqn:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        formIssqn.add(labelControl58, gridBagConstraints);

        comboBoxControl8.setAttributeName("tributacaoIssqn");
        comboBoxControl8.setDomainId("tributacaoIssqn");
        comboBoxControl8.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints.ipadx = 100;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        formIssqn.add(comboBoxControl8, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        formIssqn.add(jSeparator6, gridBagConstraints);

        jTabbedPane1.addTab("ISSQN", formIssqn);

        formCombustivel.setVOClassName("com.t2tierp.nfe.java.NfeDetEspecificoCombustivelVO");
        formCombustivel.setFunctionId("nfeDetEspecificoCombustivel");
        formCombustivel.setLayout(new java.awt.GridBagLayout());

        labelControl59.setLabel("Codigo Anp:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        formCombustivel.add(labelControl59, gridBagConstraints);

        numericControl44.setAttributeName("codigoAnp");
        numericControl44.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        formCombustivel.add(numericControl44, gridBagConstraints);

        labelControl60.setLabel("Codif:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        formCombustivel.add(labelControl60, gridBagConstraints);

        textControl12.setAttributeName("codif");
        textControl12.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        formCombustivel.add(textControl12, gridBagConstraints);

        labelControl61.setLabel("Quantidade Temp Ambiente:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        formCombustivel.add(labelControl61, gridBagConstraints);

        numericControl45.setAttributeName("quantidadeTempAmbiente");
        numericControl45.setDecimals(2);
        numericControl45.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        formCombustivel.add(numericControl45, gridBagConstraints);

        labelControl62.setLabel("Uf Consumo:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        formCombustivel.add(labelControl62, gridBagConstraints);

        textControl13.setAttributeName("ufConsumo");
        textControl13.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        formCombustivel.add(textControl13, gridBagConstraints);

        labelControl63.setLabel("Base Calculo Cide:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        formCombustivel.add(labelControl63, gridBagConstraints);

        numericControl46.setAttributeName("baseCalculoCide");
        numericControl46.setDecimals(2);
        numericControl46.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        formCombustivel.add(numericControl46, gridBagConstraints);

        labelControl64.setLabel("Aliquota Cide:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        formCombustivel.add(labelControl64, gridBagConstraints);

        numericControl47.setAttributeName("aliquotaCide");
        numericControl47.setDecimals(2);
        numericControl47.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        formCombustivel.add(numericControl47, gridBagConstraints);

        labelControl65.setLabel("Valor Cide:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        formCombustivel.add(labelControl65, gridBagConstraints);

        numericControl48.setAttributeName("valorCide");
        numericControl48.setDecimals(2);
        numericControl48.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        formCombustivel.add(numericControl48, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        formCombustivel.add(jSeparator7, gridBagConstraints);

        jTabbedPane1.addTab("Combustível", formCombustivel);

        formVeiculo.setVOClassName("com.t2tierp.nfe.java.NfeDetEspecificoVeiculoVO");
        formVeiculo.setFunctionId("nfeDetEspecificoVeiculo");
        formVeiculo.setLayout(new java.awt.GridBagLayout());

        labelControl66.setLabel("Tipo Operacao:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        formVeiculo.add(labelControl66, gridBagConstraints);

        comboBoxControl4.setAttributeName("tipoOperacao");
        comboBoxControl4.setDomainId("tipoOperacaoVeiculo");
        comboBoxControl4.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        formVeiculo.add(comboBoxControl4, gridBagConstraints);

        labelControl67.setLabel("Chassi:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        formVeiculo.add(labelControl67, gridBagConstraints);

        textControl14.setAttributeName("chassi");
        textControl14.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        formVeiculo.add(textControl14, gridBagConstraints);

        labelControl68.setLabel("Cor:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        formVeiculo.add(labelControl68, gridBagConstraints);

        textControl15.setAttributeName("cor");
        textControl15.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        formVeiculo.add(textControl15, gridBagConstraints);

        labelControl69.setLabel("Descricao Cor:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        formVeiculo.add(labelControl69, gridBagConstraints);

        textControl16.setAttributeName("descricaoCor");
        textControl16.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 9;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        formVeiculo.add(textControl16, gridBagConstraints);

        labelControl70.setLabel("Potencia Motor:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        formVeiculo.add(labelControl70, gridBagConstraints);

        textControl17.setAttributeName("potenciaMotor");
        textControl17.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        formVeiculo.add(textControl17, gridBagConstraints);

        labelControl71.setLabel("Cilindradas:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        formVeiculo.add(labelControl71, gridBagConstraints);

        textControl18.setAttributeName("cilindradas");
        textControl18.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        formVeiculo.add(textControl18, gridBagConstraints);

        labelControl72.setLabel("Peso Liquido:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        formVeiculo.add(labelControl72, gridBagConstraints);

        textControl19.setAttributeName("pesoLiquido");
        textControl19.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        formVeiculo.add(textControl19, gridBagConstraints);

        labelControl73.setLabel("Peso Bruto:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        formVeiculo.add(labelControl73, gridBagConstraints);

        textControl20.setAttributeName("pesoBruto");
        textControl20.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        formVeiculo.add(textControl20, gridBagConstraints);

        labelControl74.setLabel("Numero Serie:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        formVeiculo.add(labelControl74, gridBagConstraints);

        textControl21.setAttributeName("numeroSerie");
        textControl21.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        formVeiculo.add(textControl21, gridBagConstraints);

        labelControl75.setLabel("Tipo Combustivel:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        formVeiculo.add(labelControl75, gridBagConstraints);

        textControl22.setAttributeName("tipoCombustivel");
        textControl22.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 9;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        formVeiculo.add(textControl22, gridBagConstraints);

        labelControl76.setLabel("Numero Motor:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        formVeiculo.add(labelControl76, gridBagConstraints);

        textControl23.setAttributeName("numeroMotor");
        textControl23.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        formVeiculo.add(textControl23, gridBagConstraints);

        labelControl77.setLabel("Capacidade Maxima Tracao:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        formVeiculo.add(labelControl77, gridBagConstraints);

        textControl24.setAttributeName("capacidadeMaximaTracao");
        textControl24.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        formVeiculo.add(textControl24, gridBagConstraints);

        labelControl78.setLabel("Distancia Eixos:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        formVeiculo.add(labelControl78, gridBagConstraints);

        textControl26.setAttributeName("distanciaEixos");
        textControl26.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        formVeiculo.add(textControl26, gridBagConstraints);

        labelControl79.setLabel("Ano Modelo:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        formVeiculo.add(labelControl79, gridBagConstraints);

        textControl27.setAttributeName("anoModelo");
        textControl27.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        formVeiculo.add(textControl27, gridBagConstraints);

        labelControl80.setLabel("Ano Fabricacao:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        formVeiculo.add(labelControl80, gridBagConstraints);

        textControl28.setAttributeName("tipoPintura");
        textControl28.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 9;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        formVeiculo.add(textControl28, gridBagConstraints);

        labelControl81.setLabel("Tipo Pintura:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        formVeiculo.add(labelControl81, gridBagConstraints);

        labelControl82.setLabel("Tipo Veiculo:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        formVeiculo.add(labelControl82, gridBagConstraints);

        textControl29.setAttributeName("tipoVeiculo");
        textControl29.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        formVeiculo.add(textControl29, gridBagConstraints);

        labelControl83.setLabel("Especie Veiculo:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        formVeiculo.add(labelControl83, gridBagConstraints);

        comboBoxControl20.setAttributeName("especieVeiculo");
        comboBoxControl20.setDomainId("especieVeiculo");
        comboBoxControl20.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        formVeiculo.add(comboBoxControl20, gridBagConstraints);

        labelControl84.setLabel("Condicao Vin:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        formVeiculo.add(labelControl84, gridBagConstraints);

        comboBoxControl21.setAttributeName("condicaoVin");
        comboBoxControl21.setDomainId("veiculoCondicaoVin");
        comboBoxControl21.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        formVeiculo.add(comboBoxControl21, gridBagConstraints);

        labelControl85.setLabel("Condicao Veiculo:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        formVeiculo.add(labelControl85, gridBagConstraints);

        comboBoxControl22.setAttributeName("condicaoVeiculo");
        comboBoxControl22.setDomainId("veiculoCondicao");
        comboBoxControl22.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        formVeiculo.add(comboBoxControl22, gridBagConstraints);

        labelControl86.setLabel("Codigo Marca Modelo:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        formVeiculo.add(labelControl86, gridBagConstraints);

        textControl30.setAttributeName("codigoMarcaModelo");
        textControl30.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        formVeiculo.add(textControl30, gridBagConstraints);

        labelControl87.setLabel("Codigo Cor:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        formVeiculo.add(labelControl87, gridBagConstraints);

        textControl31.setAttributeName("codigoCor");
        textControl31.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 9;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        formVeiculo.add(textControl31, gridBagConstraints);

        labelControl88.setLabel("Lotacao:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        formVeiculo.add(labelControl88, gridBagConstraints);

        numericControl49.setAttributeName("lotacao");
        numericControl49.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        formVeiculo.add(numericControl49, gridBagConstraints);

        labelControl89.setLabel("Restricao:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        formVeiculo.add(labelControl89, gridBagConstraints);

        comboBoxControl26.setAttributeName("restricao");
        comboBoxControl26.setDomainId("restricaoVeiculo");
        comboBoxControl26.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        formVeiculo.add(comboBoxControl26, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        formVeiculo.add(jSeparator8, gridBagConstraints);

        textControl32.setAttributeName("anoFabricacao");
        textControl32.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        formVeiculo.add(textControl32, gridBagConstraints);

        jTabbedPane1.addTab("Veículo", formVeiculo);

        gridControlMedicamento.setAutoLoadData(false);
        gridControlMedicamento.setFunctionId("nfeDetEspecificoMedicamento");
        gridControlMedicamento.setValueObjectClassName("com.t2tierp.nfe.java.NfeDetEspecificoMedicamentoVO");
        gridControlMedicamento.getColumnContainer().setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        textColumn3.setColumnName("numeroLote");
        textColumn3.setHeaderColumnName("Numero Lote");
        textColumn3.setHeaderFont(new java.awt.Font("Arial", 1, 11));
        gridControlMedicamento.getColumnContainer().add(textColumn3);

        decimalColumn4.setColumnName("quantidadeLote");
        decimalColumn4.setHeaderColumnName("Quantidade Lote");
        decimalColumn4.setHeaderFont(new java.awt.Font("Arial", 1, 11));
        decimalColumn4.setDecimals(2);
        gridControlMedicamento.getColumnContainer().add(decimalColumn4);

        dateColumn5.setColumnName("dataFabricacao");
        dateColumn5.setHeaderColumnName("Data Fabricacao");
        dateColumn5.setHeaderFont(new java.awt.Font("Arial", 1, 11));
        gridControlMedicamento.getColumnContainer().add(dateColumn5);

        dateColumn6.setColumnName("dataValidade");
        dateColumn6.setHeaderColumnName("Data Validade");
        dateColumn6.setHeaderFont(new java.awt.Font("Arial", 1, 11));
        gridControlMedicamento.getColumnContainer().add(dateColumn6);

        decimalColumn7.setColumnName("precoMaximoConsumidor");
        decimalColumn7.setDecimals(2);
        decimalColumn7.setHeaderColumnName("Preco Maximo Consumidor");
        decimalColumn7.setHeaderFont(new java.awt.Font("Arial", 1, 11));
        decimalColumn7.setPreferredWidth(160);
        gridControlMedicamento.getColumnContainer().add(decimalColumn7);

        jTabbedPane1.addTab("Medicamento", gridControlMedicamento);

        gridControlArmamento.setAutoLoadData(false);
        gridControlArmamento.setFunctionId("nfeDetEspecificoArmamento");
        gridControlArmamento.setValueObjectClassName("com.t2tierp.nfe.java.NfeDetEspecificoArmamentoVO");
        gridControlArmamento.getColumnContainer().setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        comboColumn3.setColumnName("tipoArma");
        comboColumn3.setDomainId("tipoArma");
        comboColumn3.setHeaderColumnName("Tipo Arma");
        comboColumn3.setHeaderFont(new java.awt.Font("Arial", 1, 11));
        gridControlArmamento.getColumnContainer().add(comboColumn3);

        textColumn4.setColumnName("numeroSerieArma");
        textColumn4.setHeaderColumnName("Numero Serie Arma");
        textColumn4.setHeaderFont(new java.awt.Font("Arial", 1, 11));
        textColumn4.setPreferredWidth(120);
        gridControlArmamento.getColumnContainer().add(textColumn4);

        textColumn5.setColumnName("numeroSerieCano");
        textColumn5.setHeaderColumnName("Numero Serie Cano");
        textColumn5.setHeaderFont(new java.awt.Font("Arial", 1, 11));
        textColumn5.setPreferredWidth(120);
        gridControlArmamento.getColumnContainer().add(textColumn5);

        textColumn6.setColumnName("descricao");
        textColumn6.setHeaderColumnName("Descricao");
        textColumn6.setHeaderFont(new java.awt.Font("Arial", 1, 11));
        textColumn6.setPreferredWidth(300);
        gridControlArmamento.getColumnContainer().add(textColumn6);

        jTabbedPane1.addTab("Armamento", gridControlArmamento);

        jPanel1.setLayout(new java.awt.GridBagLayout());

        gridControlDeclaracaoImportacao.setAutoLoadData(false);
        gridControlDeclaracaoImportacao.setFunctionId("nfeDeclaracaoImportacao");
        gridControlDeclaracaoImportacao.setValueObjectClassName("com.t2tierp.nfe.java.NfeDeclaracaoImportacaoVO");
        gridControlDeclaracaoImportacao.setVisibleStatusPanel(false);
        gridControlDeclaracaoImportacao.getColumnContainer().setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        textColumn7.setColumnName("numeroDocumento");
        textColumn7.setHeaderColumnName("Numero Documento");
        textColumn7.setHeaderFont(new java.awt.Font("Arial", 1, 11));
        textColumn7.setPreferredWidth(120);
        gridControlDeclaracaoImportacao.getColumnContainer().add(textColumn7);

        dateColumn4.setColumnName("dataRegistro");
        dateColumn4.setHeaderColumnName("Data Registro");
        dateColumn4.setHeaderFont(new java.awt.Font("Arial", 1, 11));
        gridControlDeclaracaoImportacao.getColumnContainer().add(dateColumn4);

        textColumn8.setColumnName("localDesembaraco");
        textColumn8.setHeaderColumnName("Local Desembaraco");
        textColumn8.setHeaderFont(new java.awt.Font("Arial", 1, 11));
        gridControlDeclaracaoImportacao.getColumnContainer().add(textColumn8);

        textColumn9.setColumnName("ufDesembaraco");
        textColumn9.setHeaderColumnName("Uf Desembaraco");
        textColumn9.setHeaderFont(new java.awt.Font("Arial", 1, 11));
        gridControlDeclaracaoImportacao.getColumnContainer().add(textColumn9);

        dateColumn7.setColumnName("dataDesembaraco");
        dateColumn7.setHeaderColumnName("Data Desembaraco");
        dateColumn7.setHeaderFont(new java.awt.Font("Arial", 1, 11));
        dateColumn7.setPreferredWidth(110);
        gridControlDeclaracaoImportacao.getColumnContainer().add(dateColumn7);

        textColumn10.setColumnName("codigoExportador");
        textColumn10.setHeaderColumnName("Codigo Exportador");
        textColumn10.setHeaderFont(new java.awt.Font("Arial", 1, 11));
        textColumn10.setPreferredWidth(120);
        gridControlDeclaracaoImportacao.getColumnContainer().add(textColumn10);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel1.add(gridControlDeclaracaoImportacao, gridBagConstraints);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Adicoes"));
        jPanel2.setLayout(new java.awt.CardLayout());

        gridControlAdicao.setAutoLoadData(false);
        gridControlAdicao.setFunctionId("nfeImportacaoDetalhe");
        gridControlAdicao.setValueObjectClassName("com.t2tierp.nfe.java.NfeImportacaoDetalheVO");
        gridControlAdicao.setVisibleStatusPanel(false);
        gridControlAdicao.getColumnContainer().setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        integerColumn3.setColumnName("numeroAdicao");
        integerColumn3.setHeaderColumnName("Numero Adicao");
        integerColumn3.setHeaderFont(new java.awt.Font("Arial", 1, 11));
        gridControlAdicao.getColumnContainer().add(integerColumn3);

        integerColumn4.setColumnName("numeroSequencial");
        integerColumn4.setHeaderColumnName("Numero Sequencial");
        integerColumn4.setHeaderFont(new java.awt.Font("Arial", 1, 11));
        integerColumn4.setPreferredWidth(120);
        gridControlAdicao.getColumnContainer().add(integerColumn4);

        textColumn11.setColumnName("codigoFabricanteEstrangeiro");
        textColumn11.setHeaderColumnName("Codigo Fabricante Estrangeiro");
        textColumn11.setHeaderFont(new java.awt.Font("Arial", 1, 11));
        textColumn11.setPreferredWidth(180);
        gridControlAdicao.getColumnContainer().add(textColumn11);

        decimalColumn6.setColumnName("valorDesconto");
        decimalColumn6.setHeaderColumnName("Valor Desconto");
        decimalColumn6.setHeaderFont(new java.awt.Font("Arial", 1, 11));
        decimalColumn6.setDecimals(2);
        gridControlAdicao.getColumnContainer().add(decimalColumn6);

        jPanel2.add(gridControlAdicao, "card2");

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel1.add(jPanel2, gridBagConstraints);

        jTabbedPane1.addTab("Declaração de Importação", jPanel1);

        getContentPane().add(jTabbedPane1, "card2");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private org.openswing.swing.client.ComboBoxControl comboBoxControl11;
    private org.openswing.swing.client.ComboBoxControl comboBoxControl12;
    private org.openswing.swing.client.ComboBoxControl comboBoxControl20;
    private org.openswing.swing.client.ComboBoxControl comboBoxControl21;
    private org.openswing.swing.client.ComboBoxControl comboBoxControl22;
    private org.openswing.swing.client.ComboBoxControl comboBoxControl26;
    private org.openswing.swing.client.ComboBoxControl comboBoxControl3;
    private org.openswing.swing.client.ComboBoxControl comboBoxControl4;
    private org.openswing.swing.client.ComboBoxControl comboBoxControl6;
    private org.openswing.swing.client.ComboBoxControl comboBoxControl8;
    private org.openswing.swing.table.columns.client.ComboColumn comboColumn3;
    private org.openswing.swing.table.columns.client.DateColumn dateColumn4;
    private org.openswing.swing.table.columns.client.DateColumn dateColumn5;
    private org.openswing.swing.table.columns.client.DateColumn dateColumn6;
    private org.openswing.swing.table.columns.client.DateColumn dateColumn7;
    private org.openswing.swing.table.columns.client.DecimalColumn decimalColumn4;
    private org.openswing.swing.table.columns.client.DecimalColumn decimalColumn6;
    private org.openswing.swing.table.columns.client.DecimalColumn decimalColumn7;
    private org.openswing.swing.form.client.Form formCofins;
    private org.openswing.swing.form.client.Form formCombustivel;
    private org.openswing.swing.form.client.Form formIcms;
    private org.openswing.swing.form.client.Form formIi;
    private org.openswing.swing.form.client.Form formIpi;
    private org.openswing.swing.form.client.Form formIssqn;
    private org.openswing.swing.form.client.Form formPis;
    private org.openswing.swing.form.client.Form formVeiculo;
    private org.openswing.swing.client.GridControl gridControlAdicao;
    private org.openswing.swing.client.GridControl gridControlArmamento;
    private org.openswing.swing.client.GridControl gridControlDeclaracaoImportacao;
    private org.openswing.swing.client.GridControl gridControlMedicamento;
    private org.openswing.swing.table.columns.client.IntegerColumn integerColumn3;
    private org.openswing.swing.table.columns.client.IntegerColumn integerColumn4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JTabbedPane jTabbedPane1;
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
    private org.openswing.swing.client.LabelControl labelControl33;
    private org.openswing.swing.client.LabelControl labelControl34;
    private org.openswing.swing.client.LabelControl labelControl35;
    private org.openswing.swing.client.LabelControl labelControl36;
    private org.openswing.swing.client.LabelControl labelControl37;
    private org.openswing.swing.client.LabelControl labelControl38;
    private org.openswing.swing.client.LabelControl labelControl39;
    private org.openswing.swing.client.LabelControl labelControl4;
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
    private org.openswing.swing.client.LabelControl labelControl51;
    private org.openswing.swing.client.LabelControl labelControl52;
    private org.openswing.swing.client.LabelControl labelControl53;
    private org.openswing.swing.client.LabelControl labelControl54;
    private org.openswing.swing.client.LabelControl labelControl55;
    private org.openswing.swing.client.LabelControl labelControl56;
    private org.openswing.swing.client.LabelControl labelControl57;
    private org.openswing.swing.client.LabelControl labelControl58;
    private org.openswing.swing.client.LabelControl labelControl59;
    private org.openswing.swing.client.LabelControl labelControl6;
    private org.openswing.swing.client.LabelControl labelControl60;
    private org.openswing.swing.client.LabelControl labelControl61;
    private org.openswing.swing.client.LabelControl labelControl62;
    private org.openswing.swing.client.LabelControl labelControl63;
    private org.openswing.swing.client.LabelControl labelControl64;
    private org.openswing.swing.client.LabelControl labelControl65;
    private org.openswing.swing.client.LabelControl labelControl66;
    private org.openswing.swing.client.LabelControl labelControl67;
    private org.openswing.swing.client.LabelControl labelControl68;
    private org.openswing.swing.client.LabelControl labelControl69;
    private org.openswing.swing.client.LabelControl labelControl7;
    private org.openswing.swing.client.LabelControl labelControl70;
    private org.openswing.swing.client.LabelControl labelControl71;
    private org.openswing.swing.client.LabelControl labelControl72;
    private org.openswing.swing.client.LabelControl labelControl73;
    private org.openswing.swing.client.LabelControl labelControl74;
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
    private org.openswing.swing.client.NumericControl numericControl21;
    private org.openswing.swing.client.NumericControl numericControl22;
    private org.openswing.swing.client.NumericControl numericControl23;
    private org.openswing.swing.client.NumericControl numericControl24;
    private org.openswing.swing.client.NumericControl numericControl25;
    private org.openswing.swing.client.NumericControl numericControl26;
    private org.openswing.swing.client.NumericControl numericControl27;
    private org.openswing.swing.client.NumericControl numericControl28;
    private org.openswing.swing.client.NumericControl numericControl29;
    private org.openswing.swing.client.NumericControl numericControl3;
    private org.openswing.swing.client.NumericControl numericControl30;
    private org.openswing.swing.client.NumericControl numericControl31;
    private org.openswing.swing.client.NumericControl numericControl32;
    private org.openswing.swing.client.NumericControl numericControl33;
    private org.openswing.swing.client.NumericControl numericControl34;
    private org.openswing.swing.client.NumericControl numericControl35;
    private org.openswing.swing.client.NumericControl numericControl36;
    private org.openswing.swing.client.NumericControl numericControl37;
    private org.openswing.swing.client.NumericControl numericControl38;
    private org.openswing.swing.client.NumericControl numericControl39;
    private org.openswing.swing.client.NumericControl numericControl4;
    private org.openswing.swing.client.NumericControl numericControl40;
    private org.openswing.swing.client.NumericControl numericControl41;
    private org.openswing.swing.client.NumericControl numericControl42;
    private org.openswing.swing.client.NumericControl numericControl43;
    private org.openswing.swing.client.NumericControl numericControl44;
    private org.openswing.swing.client.NumericControl numericControl45;
    private org.openswing.swing.client.NumericControl numericControl46;
    private org.openswing.swing.client.NumericControl numericControl47;
    private org.openswing.swing.client.NumericControl numericControl48;
    private org.openswing.swing.client.NumericControl numericControl49;
    private org.openswing.swing.client.NumericControl numericControl5;
    private org.openswing.swing.client.NumericControl numericControl6;
    private org.openswing.swing.client.NumericControl numericControl7;
    private org.openswing.swing.client.NumericControl numericControl8;
    private org.openswing.swing.client.NumericControl numericControl9;
    private org.openswing.swing.table.columns.client.TextColumn textColumn10;
    private org.openswing.swing.table.columns.client.TextColumn textColumn11;
    private org.openswing.swing.table.columns.client.TextColumn textColumn3;
    private org.openswing.swing.table.columns.client.TextColumn textColumn4;
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
    private org.openswing.swing.client.TextControl textControl19;
    private org.openswing.swing.client.TextControl textControl20;
    private org.openswing.swing.client.TextControl textControl21;
    private org.openswing.swing.client.TextControl textControl22;
    private org.openswing.swing.client.TextControl textControl23;
    private org.openswing.swing.client.TextControl textControl24;
    private org.openswing.swing.client.TextControl textControl25;
    private org.openswing.swing.client.TextControl textControl26;
    private org.openswing.swing.client.TextControl textControl27;
    private org.openswing.swing.client.TextControl textControl28;
    private org.openswing.swing.client.TextControl textControl29;
    private org.openswing.swing.client.TextControl textControl3;
    private org.openswing.swing.client.TextControl textControl30;
    private org.openswing.swing.client.TextControl textControl31;
    private org.openswing.swing.client.TextControl textControl32;
    private org.openswing.swing.client.TextControl textControl4;
    private org.openswing.swing.client.TextControl textControl5;
    private org.openswing.swing.client.TextControl textControl6;
    private org.openswing.swing.client.TextControl textControl7;
    private org.openswing.swing.client.TextControl textControl8;
    private org.openswing.swing.client.TextControl textControl9;
    // End of variables declaration//GEN-END:variables

}
