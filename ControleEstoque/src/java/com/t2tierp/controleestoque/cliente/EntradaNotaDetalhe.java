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

import com.t2tierp.padrao.cliente.LookupDataLocatorGenerico;
import java.awt.Dimension;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import org.openswing.swing.lookup.client.LookupController;
import org.openswing.swing.mdi.client.InternalFrame;
import org.openswing.swing.util.client.ClientSettings;
import org.openswing.swing.util.client.ClientUtils;

public class EntradaNotaDetalhe extends InternalFrame {

    private LookupController lookupProdutoController = new LookupController();
    private LookupController transportadoraController = new LookupController();
    private NfeEmitenteDetalheController emitenteController;
    private NfeDetalheGridController produtoController;
    private NfeReferenciadaGridController nfeReferenciadaController;
    private NfeNfReferenciadaGridController nf1ReferenciadaController;
    private NfeCteReferenciadoGridController cteReferenciadoController;
    private NfeProdRuralReferenciadaGridController prodRuralReferenciadaController;
    private NfeCupomFiscalReferenciadoGridController cupomFiscalReferenciadoController;
    private NfeLocalEntregaDetalheController localEntregaController;
    private NfeLocalRetiradaDetalheController localRetiradaController;
    private NfeTransporteDetalheController transporteController;
    private NfeTransporteReboqueGridController transporteReboqueController;
    private NfeTransporteVolumeGridController transporteVolumeController;
    private NfeTransporteVolumeLacreGridController transporteVolumeLacreController;
    private NfeFaturaDetalheController faturaController;
    private NfeDuplicataGridController duplicataController;
    private FiscalNotaFiscalEntradaDetalheController escrituracaoController;
    private EntradaNotaDetalheController controller;

    public EntradaNotaDetalhe(EntradaNotaDetalheController controller) {
        initComponents();

        genericButton1.setToolTipText("Importar XML");
        genericButton2.setToolTipText("Cadastrar Produto");

        this.controller = controller;
        formDadosNfe.setFormController(controller);

        emitenteController = new NfeEmitenteDetalheController();
        formEmitente.setFormController(emitenteController);

        produtoController = new NfeDetalheGridController(this);
        gridControlProduto.setController(produtoController);
        gridControlProduto.setGridDataLocator(produtoController);

        nfeReferenciadaController = new NfeReferenciadaGridController(this);
        gridControlNfeReferenciada.setController(nfeReferenciadaController);
        gridControlNfeReferenciada.setGridDataLocator(nfeReferenciadaController);

        nf1ReferenciadaController = new NfeNfReferenciadaGridController(this);
        gridControlNf1Referenciada.setController(nf1ReferenciadaController);
        gridControlNf1Referenciada.setGridDataLocator(nf1ReferenciadaController);

        cteReferenciadoController = new NfeCteReferenciadoGridController(this);
        gridControlCteReferenciado.setController(cteReferenciadoController);
        gridControlCteReferenciado.setGridDataLocator(cteReferenciadoController);

        prodRuralReferenciadaController = new NfeProdRuralReferenciadaGridController(this);
        gridControlProdRuralReferenciada.setController(prodRuralReferenciadaController);
        gridControlProdRuralReferenciada.setGridDataLocator(prodRuralReferenciadaController);

        cupomFiscalReferenciadoController = new NfeCupomFiscalReferenciadoGridController(this);
        gridControlCupomFiscalReferenciado.setController(cupomFiscalReferenciadoController);
        gridControlCupomFiscalReferenciado.setGridDataLocator(cupomFiscalReferenciadoController);

        localEntregaController = new NfeLocalEntregaDetalheController();
        formDadosEntrega.setFormController(localEntregaController);

        localRetiradaController = new NfeLocalRetiradaDetalheController();
        formDadosRetirada.setFormController(localRetiradaController);

        transporteController = new NfeTransporteDetalheController(this);
        formTransporte.setFormController(transporteController);

        transporteReboqueController = new NfeTransporteReboqueGridController(this);
        gridControlTransporteReboque.setController(transporteReboqueController);
        gridControlTransporteReboque.setGridDataLocator(transporteReboqueController);

        transporteVolumeController = new NfeTransporteVolumeGridController(this);
        gridControlTransporteVolume.setController(transporteVolumeController);
        gridControlTransporteVolume.setGridDataLocator(transporteVolumeController);

        transporteVolumeLacreController = new NfeTransporteVolumeLacreGridController(this);
        gridControlTransporteVolumeLacre.setController(transporteVolumeLacreController);
        gridControlTransporteVolumeLacre.setGridDataLocator(transporteVolumeLacreController);

        faturaController = new NfeFaturaDetalheController();
        formFatura.setFormController(faturaController);

        duplicataController = new NfeDuplicataGridController(this);
        gridControlDuplicata.setController(duplicataController);
        gridControlDuplicata.setGridDataLocator(duplicataController);

        escrituracaoController = new FiscalNotaFiscalEntradaDetalheController();
        formEscrituracao.setFormController(escrituracaoController);

        /*
         * Configurações do lookup do produto
         */
        lookupProdutoController.setLookupValueObjectClassName("com.t2tierp.cadastros.java.ProdutoVO");
        lookupProdutoController.addLookup2ParentLink("id", "produto.id");
        lookupProdutoController.addLookup2ParentLink("codigoInterno", "codigoProduto");
        lookupProdutoController.addLookup2ParentLink("nome", "nomeProduto");
        lookupProdutoController.addLookup2ParentLink("gtin", "gtin");
        lookupProdutoController.addLookup2ParentLink("ncm", "ncm");
        lookupProdutoController.addLookup2ParentLink("exTipi", "exTipi");
        lookupProdutoController.addLookup2ParentLink("unidadeProduto.sigla", "unidadeComercial");
        //lookupProdutoController.addLookup2ParentLink("gtin", "gtinUnidadeTributavel");
        lookupProdutoController.addLookup2ParentLink("unidadeProduto.sigla", "unidadeTributavel");
        lookupProdutoController.setHeaderColumnName("id", "ID");
        lookupProdutoController.setHeaderColumnName("nome", "Nome");
        lookupProdutoController.setHeaderColumnName("gtin", "GTIN");
        lookupProdutoController.setFrameTitle("Importa Produto");

        lookupProdutoController.setVisibleStatusPanel(true);
        lookupProdutoController.setVisibleColumn("id", true);
        lookupProdutoController.setVisibleColumn("nome", true);
        lookupProdutoController.setVisibleColumn("gtin", true);
        lookupProdutoController.setFramePreferedSize(new Dimension(600, 500));

        lookupProdutoController.setLookupDataLocator(new LookupDataLocatorGenerico(lookupProdutoController.getLookupValueObjectClassName()));
        codLookupColumn1.setLookupController(lookupProdutoController);

        /*
         * Configurações do lookup da transportadora
         */
        transportadoraController.setLookupValueObjectClassName("com.t2tierp.cadastros.java.PessoaTransportadoraVO");
        transportadoraController.addLookup2ParentLink("id", "transportadora.id");
        transportadoraController.addLookup2ParentLink("cpfCnpj", "cpfCnpj");
        transportadoraController.addLookup2ParentLink("nome", "nome");
        transportadoraController.addLookup2ParentLink("logradouro", "endereco");
        transportadoraController.addLookup2ParentLink("cidade", "nomeMunicipio");
        transportadoraController.setHeaderColumnName("id", "ID");
        transportadoraController.setHeaderColumnName("nome", "Nome");
        transportadoraController.setHeaderColumnName("cpfCnpj", "CPF / CNPJ");
        transportadoraController.setFrameTitle("Importa Transportadora");

        transportadoraController.setVisibleStatusPanel(true);
        transportadoraController.setVisibleColumn("id", true);
        transportadoraController.setVisibleColumn("nome", true);
        transportadoraController.setVisibleColumn("cpfCnpj", true);
        transportadoraController.setFramePreferedSize(new Dimension(600, 500));

        transportadoraController.setLookupDataLocator(new LookupDataLocatorGenerico(transportadoraController.getLookupValueObjectClassName()));
        codLookupControl2.setLookupController(transportadoraController);

    }

    public EntradaNotaDetalheController getEntradaNotaController() {
        return controller;
    }

    public org.openswing.swing.form.client.Form getFormDadosNfe() {
        return formDadosNfe;
    }

    public org.openswing.swing.form.client.Form getFormEmitente() {
        return formEmitente;
    }

    public NfeEmitenteDetalheController getEmitenteController() {
        return emitenteController;
    }

    public org.openswing.swing.client.GridControl getGridControlProduto() {
        return gridControlProduto;
    }

    public NfeDetalheGridController getProdutoController() {
        return produtoController;
    }

    public NfeReferenciadaGridController getNfeReferenciadaController() {
        return nfeReferenciadaController;
    }

    public org.openswing.swing.client.GridControl getGridControlNfeReferenciada() {
        return gridControlNfeReferenciada;
    }

    public NfeNfReferenciadaGridController getNf1ReferenciadaController() {
        return nf1ReferenciadaController;
    }

    public org.openswing.swing.client.GridControl getGridControlNf1Referenciada() {
        return gridControlNf1Referenciada;
    }

    public NfeCteReferenciadoGridController getCteReferenciadoController() {
        return cteReferenciadoController;
    }

    public org.openswing.swing.client.GridControl getGridControlCteReferenciado() {
        return gridControlCteReferenciado;
    }

    public NfeProdRuralReferenciadaGridController getProdRuralReferenciadaController() {
        return prodRuralReferenciadaController;
    }

    public org.openswing.swing.client.GridControl getGridControlProdRuralReferenciada() {
        return gridControlProdRuralReferenciada;
    }

    public NfeCupomFiscalReferenciadoGridController getCupomFiscalReferenciadaController() {
        return cupomFiscalReferenciadoController;
    }

    public org.openswing.swing.client.GridControl getGridControlCupomFiscalReferenciado() {
        return gridControlCupomFiscalReferenciado;
    }

    public org.openswing.swing.form.client.Form getFormLocalEntrega() {
        return formDadosEntrega;
    }

    public NfeLocalEntregaDetalheController getLocalEntregaController() {
        return localEntregaController;
    }

    public org.openswing.swing.form.client.Form getFormLocalRetirada() {
        return formDadosRetirada;
    }

    public NfeLocalRetiradaDetalheController getLocalRetiradaController() {
        return localRetiradaController;
    }

    public org.openswing.swing.form.client.Form getFormTransporte() {
        return formTransporte;
    }

    public NfeTransporteDetalheController getTransporteController() {
        return transporteController;
    }

    public NfeTransporteReboqueGridController getTransporteReboqueController() {
        return transporteReboqueController;
    }

    public org.openswing.swing.client.GridControl getGridControlTransporteReboque() {
        return gridControlTransporteReboque;
    }

    public NfeTransporteVolumeGridController getTransporteVolumeController() {
        return transporteVolumeController;
    }

    public org.openswing.swing.client.GridControl getGridControlTransporteVolume() {
        return gridControlTransporteVolume;
    }

    public NfeTransporteVolumeLacreGridController getTransporteVolumeLacreController() {
        return transporteVolumeLacreController;
    }

    public org.openswing.swing.client.GridControl getGridControlTransporteVolumeLacre() {
        return gridControlTransporteVolumeLacre;
    }

    public org.openswing.swing.form.client.Form getFormFatura() {
        return formFatura;
    }

    public NfeFaturaDetalheController getFaturaController() {
        return faturaController;
    }

    public NfeDuplicataGridController getDuplicataController() {
        return duplicataController;
    }

    public org.openswing.swing.client.GridControl getGridControlDuplicata() {
        return gridControlDuplicata;
    }

    public org.openswing.swing.form.client.Form getFormEscrituracao() {
        return formEscrituracao;
    }

    public FiscalNotaFiscalEntradaDetalheController getEscrituracaoController() {
        return escrituracaoController;
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
        genericButton1 = new org.openswing.swing.client.GenericButton(new ImageIcon(ClientUtils.getImage(ClientSettings.BUTTON_IMPORT_IMAGE_NAME)));
        jTabbedPane2 = new javax.swing.JTabbedPane();
        formDadosNfe = new org.openswing.swing.form.client.Form();
        labelControl13 = new org.openswing.swing.client.LabelControl();
        comboBoxControl17 = new org.openswing.swing.client.ComboBoxControl();
        labelControl11 = new org.openswing.swing.client.LabelControl();
        textControl9 = new org.openswing.swing.client.TextControl();
        labelControl19 = new org.openswing.swing.client.LabelControl();
        comboBoxControl18 = new org.openswing.swing.client.ComboBoxControl();
        labelControl21 = new org.openswing.swing.client.LabelControl();
        comboBoxControl19 = new org.openswing.swing.client.ComboBoxControl();
        labelControl22 = new org.openswing.swing.client.LabelControl();
        comboBoxControl20 = new org.openswing.swing.client.ComboBoxControl();
        labelControl12 = new org.openswing.swing.client.LabelControl();
        comboBoxControl10 = new org.openswing.swing.client.ComboBoxControl();
        labelControl26 = new org.openswing.swing.client.LabelControl();
        comboBoxControl24 = new org.openswing.swing.client.ComboBoxControl();
        jPanel2 = new javax.swing.JPanel();
        labelControl23 = new org.openswing.swing.client.LabelControl();
        textControl22 = new org.openswing.swing.client.TextControl();
        labelControl24 = new org.openswing.swing.client.LabelControl();
        textControl21 = new org.openswing.swing.client.TextControl();
        labelControl10 = new org.openswing.swing.client.LabelControl();
        textControl8 = new org.openswing.swing.client.TextControl();
        labelControl14 = new org.openswing.swing.client.LabelControl();
        textControl12 = new org.openswing.swing.client.TextControl();
        labelControl15 = new org.openswing.swing.client.LabelControl();
        textControl13 = new org.openswing.swing.client.TextControl();
        labelControl16 = new org.openswing.swing.client.LabelControl();
        dateControl14 = new org.openswing.swing.client.DateControl();
        labelControl17 = new org.openswing.swing.client.LabelControl();
        dateControl15 = new org.openswing.swing.client.DateControl();
        labelControl18 = new org.openswing.swing.client.LabelControl();
        textControl16 = new org.openswing.swing.client.TextControl();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel34 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        labelControl31 = new org.openswing.swing.client.LabelControl();
        numericControl29 = new org.openswing.swing.client.NumericControl();
        labelControl32 = new org.openswing.swing.client.LabelControl();
        numericControl30 = new org.openswing.swing.client.NumericControl();
        labelControl33 = new org.openswing.swing.client.LabelControl();
        numericControl31 = new org.openswing.swing.client.NumericControl();
        labelControl34 = new org.openswing.swing.client.LabelControl();
        numericControl32 = new org.openswing.swing.client.NumericControl();
        labelControl40 = new org.openswing.swing.client.LabelControl();
        numericControl38 = new org.openswing.swing.client.NumericControl();
        labelControl42 = new org.openswing.swing.client.LabelControl();
        numericControl40 = new org.openswing.swing.client.NumericControl();
        labelControl35 = new org.openswing.swing.client.LabelControl();
        numericControl33 = new org.openswing.swing.client.NumericControl();
        labelControl36 = new org.openswing.swing.client.LabelControl();
        numericControl34 = new org.openswing.swing.client.NumericControl();
        labelControl41 = new org.openswing.swing.client.LabelControl();
        numericControl39 = new org.openswing.swing.client.NumericControl();
        labelControl37 = new org.openswing.swing.client.LabelControl();
        numericControl35 = new org.openswing.swing.client.NumericControl();
        labelControl43 = new org.openswing.swing.client.LabelControl();
        numericControl41 = new org.openswing.swing.client.NumericControl();
        labelControl38 = new org.openswing.swing.client.LabelControl();
        numericControl36 = new org.openswing.swing.client.NumericControl();
        labelControl39 = new org.openswing.swing.client.LabelControl();
        numericControl37 = new org.openswing.swing.client.NumericControl();
        labelControl44 = new org.openswing.swing.client.LabelControl();
        numericControl42 = new org.openswing.swing.client.NumericControl();
        jPanel35 = new javax.swing.JPanel();
        labelControl134 = new org.openswing.swing.client.LabelControl();
        numericControl63 = new org.openswing.swing.client.NumericControl();
        labelControl135 = new org.openswing.swing.client.LabelControl();
        numericControl64 = new org.openswing.swing.client.NumericControl();
        labelControl136 = new org.openswing.swing.client.LabelControl();
        numericControl65 = new org.openswing.swing.client.NumericControl();
        labelControl137 = new org.openswing.swing.client.LabelControl();
        numericControl66 = new org.openswing.swing.client.NumericControl();
        labelControl138 = new org.openswing.swing.client.LabelControl();
        numericControl67 = new org.openswing.swing.client.NumericControl();
        labelControl139 = new org.openswing.swing.client.LabelControl();
        numericControl68 = new org.openswing.swing.client.NumericControl();
        labelControl140 = new org.openswing.swing.client.LabelControl();
        numericControl69 = new org.openswing.swing.client.NumericControl();
        labelControl141 = new org.openswing.swing.client.LabelControl();
        numericControl70 = new org.openswing.swing.client.NumericControl();
        labelControl142 = new org.openswing.swing.client.LabelControl();
        numericControl71 = new org.openswing.swing.client.NumericControl();
        labelControl143 = new org.openswing.swing.client.LabelControl();
        numericControl72 = new org.openswing.swing.client.NumericControl();
        labelControl144 = new org.openswing.swing.client.LabelControl();
        numericControl73 = new org.openswing.swing.client.NumericControl();
        labelControl145 = new org.openswing.swing.client.LabelControl();
        numericControl74 = new org.openswing.swing.client.NumericControl();
        jSeparator1 = new javax.swing.JSeparator();
        jPanel5 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        labelControl57 = new org.openswing.swing.client.LabelControl();
        textControl55 = new org.openswing.swing.client.TextControl();
        labelControl58 = new org.openswing.swing.client.LabelControl();
        textControl56 = new org.openswing.swing.client.TextControl();
        jPanel7 = new javax.swing.JPanel();
        labelControl59 = new org.openswing.swing.client.LabelControl();
        textControl57 = new org.openswing.swing.client.TextControl();
        labelControl60 = new org.openswing.swing.client.LabelControl();
        textControl58 = new org.openswing.swing.client.TextControl();
        labelControl61 = new org.openswing.swing.client.LabelControl();
        textControl59 = new org.openswing.swing.client.TextControl();
        labelControl62 = new org.openswing.swing.client.LabelControl();
        labelControl63 = new org.openswing.swing.client.LabelControl();
        textAreaControl1 = new org.openswing.swing.client.TextAreaControl();
        textAreaControl2 = new org.openswing.swing.client.TextAreaControl();
        jSeparator2 = new javax.swing.JSeparator();
        formEmitente = new org.openswing.swing.form.client.Form();
        labelControl3 = new org.openswing.swing.client.LabelControl();
        textControl3 = new org.openswing.swing.client.TextControl();
        labelControl4 = new org.openswing.swing.client.LabelControl();
        textControl4 = new org.openswing.swing.client.TextControl();
        labelControl5 = new org.openswing.swing.client.LabelControl();
        textControl5 = new org.openswing.swing.client.TextControl();
        labelControl6 = new org.openswing.swing.client.LabelControl();
        textControl6 = new org.openswing.swing.client.TextControl();
        labelControl7 = new org.openswing.swing.client.LabelControl();
        textControl7 = new org.openswing.swing.client.TextControl();
        labelControl8 = new org.openswing.swing.client.LabelControl();
        textControl10 = new org.openswing.swing.client.TextControl();
        labelControl9 = new org.openswing.swing.client.LabelControl();
        textControl11 = new org.openswing.swing.client.TextControl();
        labelControl20 = new org.openswing.swing.client.LabelControl();
        numericControl10 = new org.openswing.swing.client.NumericControl();
        labelControl25 = new org.openswing.swing.client.LabelControl();
        textControl14 = new org.openswing.swing.client.TextControl();
        labelControl27 = new org.openswing.swing.client.LabelControl();
        textControl15 = new org.openswing.swing.client.TextControl();
        labelControl28 = new org.openswing.swing.client.LabelControl();
        textControl17 = new org.openswing.swing.client.TextControl();
        labelControl64 = new org.openswing.swing.client.LabelControl();
        textControl19 = new org.openswing.swing.client.TextControl();
        labelControl65 = new org.openswing.swing.client.LabelControl();
        textControl20 = new org.openswing.swing.client.TextControl();
        labelControl66 = new org.openswing.swing.client.LabelControl();
        textControl23 = new org.openswing.swing.client.TextControl();
        labelControl67 = new org.openswing.swing.client.LabelControl();
        textControl24 = new org.openswing.swing.client.TextControl();
        labelControl68 = new org.openswing.swing.client.LabelControl();
        textControl25 = new org.openswing.swing.client.TextControl();
        labelControl69 = new org.openswing.swing.client.LabelControl();
        comboBoxControl21 = new org.openswing.swing.client.ComboBoxControl();
        labelControl70 = new org.openswing.swing.client.LabelControl();
        textControl26 = new org.openswing.swing.client.TextControl();
        labelControl71 = new org.openswing.swing.client.LabelControl();
        textControl27 = new org.openswing.swing.client.TextControl();
        jSeparator3 = new javax.swing.JSeparator();
        jPanel8 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        insertButtonProduto = new org.openswing.swing.client.InsertButton();
        editButtonProduto = new org.openswing.swing.client.EditButton();
        deleteButtonProduto = new org.openswing.swing.client.DeleteButton();
        saveButtonProduto = new org.openswing.swing.client.SaveButton();
        reloadButtonProduto = new org.openswing.swing.client.ReloadButton();
        navigatorBarProduto = new org.openswing.swing.client.NavigatorBar();
        genericButton2 = new org.openswing.swing.client.GenericButton(new ImageIcon(ClientUtils.getImage("db_register16.png")));
        gridControlProduto = new org.openswing.swing.client.GridControl();
        checkBoxColumn1 = new org.openswing.swing.table.columns.client.CheckBoxColumn();
        integerColumn4 = new org.openswing.swing.table.columns.client.IntegerColumn();
        codLookupColumn1 = new org.openswing.swing.table.columns.client.CodLookupColumn();
        textColumn5 = new org.openswing.swing.table.columns.client.TextColumn();
        textColumn7 = new org.openswing.swing.table.columns.client.TextColumn();
        textColumn8 = new org.openswing.swing.table.columns.client.TextColumn();
        integerColumn9 = new org.openswing.swing.table.columns.client.IntegerColumn();
        integerColumn10 = new org.openswing.swing.table.columns.client.IntegerColumn();
        textColumn11 = new org.openswing.swing.table.columns.client.TextColumn();
        decimalColumn12 = new org.openswing.swing.table.columns.client.DecimalColumn();
        decimalColumn13 = new org.openswing.swing.table.columns.client.DecimalColumn();
        decimalColumn14 = new org.openswing.swing.table.columns.client.DecimalColumn();
        textColumn15 = new org.openswing.swing.table.columns.client.TextColumn();
        textColumn16 = new org.openswing.swing.table.columns.client.TextColumn();
        decimalColumn17 = new org.openswing.swing.table.columns.client.DecimalColumn();
        decimalColumn18 = new org.openswing.swing.table.columns.client.DecimalColumn();
        decimalColumn19 = new org.openswing.swing.table.columns.client.DecimalColumn();
        decimalColumn20 = new org.openswing.swing.table.columns.client.DecimalColumn();
        decimalColumn21 = new org.openswing.swing.table.columns.client.DecimalColumn();
        decimalColumn22 = new org.openswing.swing.table.columns.client.DecimalColumn();
        comboColumn23 = new org.openswing.swing.table.columns.client.ComboColumn();
        decimalColumn24 = new org.openswing.swing.table.columns.client.DecimalColumn();
        decimalColumn25 = new org.openswing.swing.table.columns.client.DecimalColumn();
        textColumn26 = new org.openswing.swing.table.columns.client.TextColumn();
        integerColumn27 = new org.openswing.swing.table.columns.client.IntegerColumn();
        textColumn28 = new org.openswing.swing.table.columns.client.TextColumn();
        jTabbedPane3 = new javax.swing.JTabbedPane();
        jPanel10 = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        insertButtonNfeReferenciada = new org.openswing.swing.client.InsertButton();
        editButtonNfeReferenciada = new org.openswing.swing.client.EditButton();
        deleteButtonNfeReferenciada = new org.openswing.swing.client.DeleteButton();
        saveButtonNfeReferenciada = new org.openswing.swing.client.SaveButton();
        reloadButtonNfeReferenciada = new org.openswing.swing.client.ReloadButton();
        navigatorBarNfeReferenciada = new org.openswing.swing.client.NavigatorBar();
        gridControlNfeReferenciada = new org.openswing.swing.client.GridControl();
        textColumn3 = new org.openswing.swing.table.columns.client.TextColumn();
        jPanel12 = new javax.swing.JPanel();
        jPanel13 = new javax.swing.JPanel();
        insertButtonNf1Referenciada = new org.openswing.swing.client.InsertButton();
        editButtonNf1Referenciada = new org.openswing.swing.client.EditButton();
        deleteButtonNf1Referenciada = new org.openswing.swing.client.DeleteButton();
        saveButtonNf1Referenciada = new org.openswing.swing.client.SaveButton();
        reloadButtonNf1Referenciada = new org.openswing.swing.client.ReloadButton();
        navigatorBarNf1Referenciada = new org.openswing.swing.client.NavigatorBar();
        gridControlNf1Referenciada = new org.openswing.swing.client.GridControl();
        integerColumn3 = new org.openswing.swing.table.columns.client.IntegerColumn();
        textColumn4 = new org.openswing.swing.table.columns.client.TextColumn();
        textColumn6 = new org.openswing.swing.table.columns.client.TextColumn();
        textColumn9 = new org.openswing.swing.table.columns.client.TextColumn();
        textColumn10 = new org.openswing.swing.table.columns.client.TextColumn();
        textColumn12 = new org.openswing.swing.table.columns.client.TextColumn();
        jPanel14 = new javax.swing.JPanel();
        jPanel15 = new javax.swing.JPanel();
        insertButtonCteReferenciada = new org.openswing.swing.client.InsertButton();
        editButtonCteReferenciada = new org.openswing.swing.client.EditButton();
        deleteButtonCteReferenciada = new org.openswing.swing.client.DeleteButton();
        saveButtonCteReferenciada = new org.openswing.swing.client.SaveButton();
        reloadButtonCteReferenciada = new org.openswing.swing.client.ReloadButton();
        navigatorBarCteReferenciada = new org.openswing.swing.client.NavigatorBar();
        gridControlCteReferenciado = new org.openswing.swing.client.GridControl();
        textColumn13 = new org.openswing.swing.table.columns.client.TextColumn();
        jPanel16 = new javax.swing.JPanel();
        jPanel17 = new javax.swing.JPanel();
        insertButtonProdRuralReferenciada = new org.openswing.swing.client.InsertButton();
        editButtonProdRuralReferenciada = new org.openswing.swing.client.EditButton();
        deleteButtonProdRuralReferenciada = new org.openswing.swing.client.DeleteButton();
        saveButtonProdRuralReferenciada = new org.openswing.swing.client.SaveButton();
        reloadButtonProdRuralReferenciada = new org.openswing.swing.client.ReloadButton();
        navigatorBarProdRuralReferenciada = new org.openswing.swing.client.NavigatorBar();
        gridControlProdRuralReferenciada = new org.openswing.swing.client.GridControl();
        integerColumn5 = new org.openswing.swing.table.columns.client.IntegerColumn();
        textColumn14 = new org.openswing.swing.table.columns.client.TextColumn();
        textColumn17 = new org.openswing.swing.table.columns.client.TextColumn();
        textColumn18 = new org.openswing.swing.table.columns.client.TextColumn();
        textColumn19 = new org.openswing.swing.table.columns.client.TextColumn();
        textColumn20 = new org.openswing.swing.table.columns.client.TextColumn();
        textColumn21 = new org.openswing.swing.table.columns.client.TextColumn();
        textColumn22 = new org.openswing.swing.table.columns.client.TextColumn();
        jPanel18 = new javax.swing.JPanel();
        jPanel19 = new javax.swing.JPanel();
        insertButtonCupomFiscalReferenciado = new org.openswing.swing.client.InsertButton();
        editButtonCupomFiscalReferenciado = new org.openswing.swing.client.EditButton();
        deleteButtonCupomFiscalReferenciado = new org.openswing.swing.client.DeleteButton();
        saveButtonCupomFiscalReferenciado = new org.openswing.swing.client.SaveButton();
        reloadButtonCupomFiscalReferenciado = new org.openswing.swing.client.ReloadButton();
        navigatorBarCupomFiscalReferenciado = new org.openswing.swing.client.NavigatorBar();
        gridControlCupomFiscalReferenciado = new org.openswing.swing.client.GridControl();
        textColumn23 = new org.openswing.swing.table.columns.client.TextColumn();
        integerColumn6 = new org.openswing.swing.table.columns.client.IntegerColumn();
        integerColumn7 = new org.openswing.swing.table.columns.client.IntegerColumn();
        dateColumn6 = new org.openswing.swing.table.columns.client.DateColumn();
        integerColumn8 = new org.openswing.swing.table.columns.client.IntegerColumn();
        textColumn24 = new org.openswing.swing.table.columns.client.TextColumn();
        jPanel20 = new javax.swing.JPanel();
        formDadosEntrega = new org.openswing.swing.form.client.Form();
        labelControl29 = new org.openswing.swing.client.LabelControl();
        textControl18 = new org.openswing.swing.client.TextControl();
        labelControl30 = new org.openswing.swing.client.LabelControl();
        textControl28 = new org.openswing.swing.client.TextControl();
        labelControl72 = new org.openswing.swing.client.LabelControl();
        textControl29 = new org.openswing.swing.client.TextControl();
        labelControl73 = new org.openswing.swing.client.LabelControl();
        textControl30 = new org.openswing.swing.client.TextControl();
        labelControl74 = new org.openswing.swing.client.LabelControl();
        textControl31 = new org.openswing.swing.client.TextControl();
        labelControl75 = new org.openswing.swing.client.LabelControl();
        numericControl8 = new org.openswing.swing.client.NumericControl();
        labelControl76 = new org.openswing.swing.client.LabelControl();
        textControl32 = new org.openswing.swing.client.TextControl();
        labelControl77 = new org.openswing.swing.client.LabelControl();
        textControl33 = new org.openswing.swing.client.TextControl();
        jSeparator4 = new javax.swing.JSeparator();
        formDadosRetirada = new org.openswing.swing.form.client.Form();
        labelControl78 = new org.openswing.swing.client.LabelControl();
        textControl34 = new org.openswing.swing.client.TextControl();
        labelControl79 = new org.openswing.swing.client.LabelControl();
        textControl35 = new org.openswing.swing.client.TextControl();
        labelControl80 = new org.openswing.swing.client.LabelControl();
        textControl36 = new org.openswing.swing.client.TextControl();
        labelControl81 = new org.openswing.swing.client.LabelControl();
        textControl37 = new org.openswing.swing.client.TextControl();
        labelControl82 = new org.openswing.swing.client.LabelControl();
        textControl38 = new org.openswing.swing.client.TextControl();
        labelControl83 = new org.openswing.swing.client.LabelControl();
        numericControl9 = new org.openswing.swing.client.NumericControl();
        labelControl84 = new org.openswing.swing.client.LabelControl();
        textControl39 = new org.openswing.swing.client.TextControl();
        labelControl85 = new org.openswing.swing.client.LabelControl();
        textControl40 = new org.openswing.swing.client.TextControl();
        jSeparator5 = new javax.swing.JSeparator();
        jPanel21 = new javax.swing.JPanel();
        formTransporte = new org.openswing.swing.form.client.Form();
        labelControl88 = new org.openswing.swing.client.LabelControl();
        comboBoxControl4 = new org.openswing.swing.client.ComboBoxControl();
        labelControl104 = new org.openswing.swing.client.LabelControl();
        textControl51 = new org.openswing.swing.client.TextControl();
        labelControl105 = new org.openswing.swing.client.LabelControl();
        textControl52 = new org.openswing.swing.client.TextControl();
        jPanel22 = new javax.swing.JPanel();
        labelControl1 = new org.openswing.swing.client.LabelControl();
        codLookupControl2 = new org.openswing.swing.client.CodLookupControl();
        labelControl89 = new org.openswing.swing.client.LabelControl();
        textControl42 = new org.openswing.swing.client.TextControl();
        labelControl90 = new org.openswing.swing.client.LabelControl();
        textControl43 = new org.openswing.swing.client.TextControl();
        textControl44 = new org.openswing.swing.client.TextControl();
        labelControl91 = new org.openswing.swing.client.LabelControl();
        labelControl92 = new org.openswing.swing.client.LabelControl();
        textControl45 = new org.openswing.swing.client.TextControl();
        labelControl93 = new org.openswing.swing.client.LabelControl();
        textControl46 = new org.openswing.swing.client.TextControl();
        labelControl94 = new org.openswing.swing.client.LabelControl();
        textControl47 = new org.openswing.swing.client.TextControl();
        jPanel23 = new javax.swing.JPanel();
        labelControl102 = new org.openswing.swing.client.LabelControl();
        textControl49 = new org.openswing.swing.client.TextControl();
        labelControl103 = new org.openswing.swing.client.LabelControl();
        textControl50 = new org.openswing.swing.client.TextControl();
        labelControl101 = new org.openswing.swing.client.LabelControl();
        textControl48 = new org.openswing.swing.client.TextControl();
        jPanel24 = new javax.swing.JPanel();
        labelControl96 = new org.openswing.swing.client.LabelControl();
        numericControl12 = new org.openswing.swing.client.NumericControl();
        labelControl97 = new org.openswing.swing.client.LabelControl();
        numericControl13 = new org.openswing.swing.client.NumericControl();
        labelControl98 = new org.openswing.swing.client.LabelControl();
        numericControl14 = new org.openswing.swing.client.NumericControl();
        labelControl99 = new org.openswing.swing.client.LabelControl();
        numericControl15 = new org.openswing.swing.client.NumericControl();
        labelControl100 = new org.openswing.swing.client.LabelControl();
        numericControl16 = new org.openswing.swing.client.NumericControl();
        labelControl95 = new org.openswing.swing.client.LabelControl();
        numericControl11 = new org.openswing.swing.client.NumericControl();
        jTabbedPane4 = new javax.swing.JTabbedPane();
        jPanel25 = new javax.swing.JPanel();
        jPanel28 = new javax.swing.JPanel();
        insertButtonTransporteReboque = new org.openswing.swing.client.InsertButton();
        editButtonTransporteReboque = new org.openswing.swing.client.EditButton();
        deleteButtonTransporteReboque = new org.openswing.swing.client.DeleteButton();
        saveButtonTransporteReboque = new org.openswing.swing.client.SaveButton();
        reloadButtonTransporteReboque = new org.openswing.swing.client.ReloadButton();
        navigatorBarTransporteReboque = new org.openswing.swing.client.NavigatorBar();
        gridControlTransporteReboque = new org.openswing.swing.client.GridControl();
        textColumn25 = new org.openswing.swing.table.columns.client.TextColumn();
        textColumn27 = new org.openswing.swing.table.columns.client.TextColumn();
        textColumn29 = new org.openswing.swing.table.columns.client.TextColumn();
        jPanel26 = new javax.swing.JPanel();
        jPanel29 = new javax.swing.JPanel();
        insertButtonTransporteVolume = new org.openswing.swing.client.InsertButton();
        editButtonTransporteVolume = new org.openswing.swing.client.EditButton();
        deleteButtonTransporteVolume = new org.openswing.swing.client.DeleteButton();
        saveButtonTransporteVolume = new org.openswing.swing.client.SaveButton();
        reloadButtonTransporteVolume = new org.openswing.swing.client.ReloadButton();
        navigatorBarTransporteVolume = new org.openswing.swing.client.NavigatorBar();
        gridControlTransporteVolume = new org.openswing.swing.client.GridControl();
        integerColumn11 = new org.openswing.swing.table.columns.client.IntegerColumn();
        textColumn30 = new org.openswing.swing.table.columns.client.TextColumn();
        textColumn31 = new org.openswing.swing.table.columns.client.TextColumn();
        textColumn32 = new org.openswing.swing.table.columns.client.TextColumn();
        decimalColumn7 = new org.openswing.swing.table.columns.client.DecimalColumn();
        decimalColumn8 = new org.openswing.swing.table.columns.client.DecimalColumn();
        jPanel31 = new javax.swing.JPanel();
        insertButtonTransporteVolumeLacre = new org.openswing.swing.client.InsertButton();
        editButtonTransporteVolumeLacre = new org.openswing.swing.client.EditButton();
        deleteButtonTransporteVolumeLacre = new org.openswing.swing.client.DeleteButton();
        saveButtonTransporteVolumeLacre = new org.openswing.swing.client.SaveButton();
        reloadButtonTransporteVolumeLacre = new org.openswing.swing.client.ReloadButton();
        navigatorBarTransporteVolumeLacre = new org.openswing.swing.client.NavigatorBar();
        gridControlTransporteVolumeLacre = new org.openswing.swing.client.GridControl();
        textColumn33 = new org.openswing.swing.table.columns.client.TextColumn();
        jPanel27 = new javax.swing.JPanel();
        formFatura = new org.openswing.swing.form.client.Form();
        labelControl87 = new org.openswing.swing.client.LabelControl();
        textControl41 = new org.openswing.swing.client.TextControl();
        labelControl106 = new org.openswing.swing.client.LabelControl();
        numericControl4 = new org.openswing.swing.client.NumericControl();
        labelControl107 = new org.openswing.swing.client.LabelControl();
        numericControl5 = new org.openswing.swing.client.NumericControl();
        labelControl108 = new org.openswing.swing.client.LabelControl();
        numericControl6 = new org.openswing.swing.client.NumericControl();
        jPanel30 = new javax.swing.JPanel();
        jPanel32 = new javax.swing.JPanel();
        insertButtonDuplicata = new org.openswing.swing.client.InsertButton();
        editButtonDuplicata = new org.openswing.swing.client.EditButton();
        deleteButtonDuplicata = new org.openswing.swing.client.DeleteButton();
        saveButtonDuplicata = new org.openswing.swing.client.SaveButton();
        reloadButtonDuplicata = new org.openswing.swing.client.ReloadButton();
        navigatorBarDuplicata = new org.openswing.swing.client.NavigatorBar();
        gridControlDuplicata = new org.openswing.swing.client.GridControl();
        textColumn34 = new org.openswing.swing.table.columns.client.TextColumn();
        dateColumn4 = new org.openswing.swing.table.columns.client.DateColumn();
        decimalColumn5 = new org.openswing.swing.table.columns.client.DecimalColumn();
        jPanel33 = new javax.swing.JPanel();
        formEscrituracao = new org.openswing.swing.form.client.Form();
        labelControl109 = new org.openswing.swing.client.LabelControl();
        textControl53 = new org.openswing.swing.client.TextControl();
        labelControl110 = new org.openswing.swing.client.LabelControl();
        numericControl7 = new org.openswing.swing.client.NumericControl();
        labelControl111 = new org.openswing.swing.client.LabelControl();
        numericControl17 = new org.openswing.swing.client.NumericControl();
        labelControl112 = new org.openswing.swing.client.LabelControl();
        numericControl18 = new org.openswing.swing.client.NumericControl();
        labelControl113 = new org.openswing.swing.client.LabelControl();
        numericControl19 = new org.openswing.swing.client.NumericControl();
        labelControl114 = new org.openswing.swing.client.LabelControl();
        numericControl20 = new org.openswing.swing.client.NumericControl();
        labelControl119 = new org.openswing.swing.client.LabelControl();
        textControl54 = new org.openswing.swing.client.TextControl();
        labelControl129 = new org.openswing.swing.client.LabelControl();
        numericControl57 = new org.openswing.swing.client.NumericControl();
        labelControl131 = new org.openswing.swing.client.LabelControl();
        numericControl59 = new org.openswing.swing.client.NumericControl();
        labelControl86 = new org.openswing.swing.client.LabelControl();
        numericControl21 = new org.openswing.swing.client.NumericControl();
        labelControl115 = new org.openswing.swing.client.LabelControl();
        numericControl22 = new org.openswing.swing.client.NumericControl();
        labelControl116 = new org.openswing.swing.client.LabelControl();
        textControl60 = new org.openswing.swing.client.TextControl();
        labelControl117 = new org.openswing.swing.client.LabelControl();
        numericControl26 = new org.openswing.swing.client.NumericControl();
        labelControl118 = new org.openswing.swing.client.LabelControl();
        numericControl23 = new org.openswing.swing.client.NumericControl();
        labelControl120 = new org.openswing.swing.client.LabelControl();
        numericControl24 = new org.openswing.swing.client.NumericControl();
        labelControl121 = new org.openswing.swing.client.LabelControl();
        numericControl25 = new org.openswing.swing.client.NumericControl();
        labelControl122 = new org.openswing.swing.client.LabelControl();
        textControl61 = new org.openswing.swing.client.TextControl();
        labelControl123 = new org.openswing.swing.client.LabelControl();
        numericControl27 = new org.openswing.swing.client.NumericControl();
        labelControl124 = new org.openswing.swing.client.LabelControl();
        numericControl28 = new org.openswing.swing.client.NumericControl();
        labelControl125 = new org.openswing.swing.client.LabelControl();
        numericControl55 = new org.openswing.swing.client.NumericControl();
        labelControl126 = new org.openswing.swing.client.LabelControl();
        numericControl56 = new org.openswing.swing.client.NumericControl();
        labelControl127 = new org.openswing.swing.client.LabelControl();
        numericControl58 = new org.openswing.swing.client.NumericControl();
        labelControl128 = new org.openswing.swing.client.LabelControl();
        textControl62 = new org.openswing.swing.client.TextControl();
        labelControl130 = new org.openswing.swing.client.LabelControl();
        numericControl60 = new org.openswing.swing.client.NumericControl();
        labelControl132 = new org.openswing.swing.client.LabelControl();
        numericControl61 = new org.openswing.swing.client.NumericControl();
        labelControl133 = new org.openswing.swing.client.LabelControl();
        numericControl62 = new org.openswing.swing.client.NumericControl();
        jSeparator6 = new javax.swing.JSeparator();

        setTitle("T2Ti ERP - Controle de Estoque");
        setPreferredSize(new java.awt.Dimension(700, 400));
        getContentPane().setLayout(new java.awt.GridBagLayout());

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Entrada Nota Fiscal"));
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

        formDadosNfe.setVOClassName("com.t2tierp.nfe.java.NfeCabecalhoVO");
        formDadosNfe.setEditButton(editButton1);
        formDadosNfe.setFunctionId("nfeCabecalho");
        formDadosNfe.setReloadButton(reloadButton1);
        formDadosNfe.setSaveButton(saveButton1);
        formDadosNfe.setLayout(new java.awt.GridBagLayout());

        labelControl13.setLabel("Codigo Modelo:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        formDadosNfe.add(labelControl13, gridBagConstraints);

        comboBoxControl17.setAttributeName("codigoModelo");
        comboBoxControl17.setDomainId("CodigoModeloNf");
        comboBoxControl17.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        formDadosNfe.add(comboBoxControl17, gridBagConstraints);

        labelControl11.setLabel("Natureza Operacao:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        formDadosNfe.add(labelControl11, gridBagConstraints);

        textControl9.setAttributeName("naturezaOperacao");
        textControl9.setEnabled(false);
        textControl9.setMaxCharacters(60);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        formDadosNfe.add(textControl9, gridBagConstraints);

        labelControl19.setLabel("Tipo Operacao:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        formDadosNfe.add(labelControl19, gridBagConstraints);

        comboBoxControl18.setAttributeName("tipoOperacao");
        comboBoxControl18.setDomainId("tipoOperacao");
        comboBoxControl18.setEnabled(false);
        comboBoxControl18.setEnabledOnEdit(false);
        comboBoxControl18.setEnabledOnInsert(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        formDadosNfe.add(comboBoxControl18, gridBagConstraints);

        labelControl21.setLabel("Formato Impressao Danfe:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        formDadosNfe.add(labelControl21, gridBagConstraints);

        comboBoxControl19.setAttributeName("formatoImpressaoDanfe");
        comboBoxControl19.setDomainId("tipoImpressaoDanfe");
        comboBoxControl19.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        formDadosNfe.add(comboBoxControl19, gridBagConstraints);

        labelControl22.setLabel("Tipo Emissao:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        formDadosNfe.add(labelControl22, gridBagConstraints);

        comboBoxControl20.setAttributeName("tipoEmissao");
        comboBoxControl20.setDomainId("formaEmissao");
        comboBoxControl20.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        formDadosNfe.add(comboBoxControl20, gridBagConstraints);

        labelControl12.setLabel("Indicador Forma Pagamento:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        formDadosNfe.add(labelControl12, gridBagConstraints);

        comboBoxControl10.setAttributeName("indicadorFormaPagamento");
        comboBoxControl10.setDomainId("formaPagamento");
        comboBoxControl10.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        formDadosNfe.add(comboBoxControl10, gridBagConstraints);

        labelControl26.setLabel("Finalidade Emissao:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        formDadosNfe.add(labelControl26, gridBagConstraints);

        comboBoxControl24.setAttributeName("finalidadeEmissao");
        comboBoxControl24.setDomainId("finalidadeEmissao");
        comboBoxControl24.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        formDadosNfe.add(comboBoxControl24, gridBagConstraints);

        jPanel2.setLayout(new java.awt.GridBagLayout());

        labelControl23.setLabel("Chave Acesso:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        jPanel2.add(labelControl23, gridBagConstraints);

        textControl22.setAttributeName("chaveAcesso");
        textControl22.setEnabled(false);
        textControl22.setMaxCharacters(44);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 0);
        jPanel2.add(textControl22, gridBagConstraints);

        labelControl24.setText("DV:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 0, 5);
        jPanel2.add(labelControl24, gridBagConstraints);

        textControl21.setAttributeName("chaveAcesso");
        textControl21.setEnabled(false);
        textControl21.setMaxCharacters(1);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = -70;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 0);
        jPanel2.add(textControl21, gridBagConstraints);

        labelControl10.setLabel("Codigo Numerico:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        jPanel2.add(labelControl10, gridBagConstraints);

        textControl8.setAttributeName("codigoNumerico");
        textControl8.setEnabled(false);
        textControl8.setMaxCharacters(8);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        jPanel2.add(textControl8, gridBagConstraints);

        labelControl14.setLabel("Serie:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        jPanel2.add(labelControl14, gridBagConstraints);

        textControl12.setAttributeName("serie");
        textControl12.setEnabled(false);
        textControl12.setMaxCharacters(6);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        jPanel2.add(textControl12, gridBagConstraints);

        labelControl15.setLabel("Numero:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        jPanel2.add(labelControl15, gridBagConstraints);

        textControl13.setAttributeName("numero");
        textControl13.setEnabled(false);
        textControl13.setMaxCharacters(9);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        jPanel2.add(textControl13, gridBagConstraints);

        labelControl16.setLabel("Data Emissao:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        jPanel2.add(labelControl16, gridBagConstraints);

        dateControl14.setAttributeName("dataEmissao");
        dateControl14.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        jPanel2.add(dateControl14, gridBagConstraints);

        labelControl17.setLabel("Data Entrada Saida:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        jPanel2.add(labelControl17, gridBagConstraints);

        dateControl15.setAttributeName("dataEntradaSaida");
        dateControl15.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        jPanel2.add(dateControl15, gridBagConstraints);

        labelControl18.setLabel("Hora Entrada Saida:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 7;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        jPanel2.add(labelControl18, gridBagConstraints);

        textControl16.setAttributeName("horaEntradaSaida");
        textControl16.setEnabled(false);
        textControl16.setMaxCharacters(8);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 7;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        jPanel2.add(textControl16, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 8;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        formDadosNfe.add(jPanel2, gridBagConstraints);

        jPanel34.setLayout(new java.awt.GridBagLayout());

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Totais Gerais"));
        jPanel3.setLayout(new java.awt.GridBagLayout());

        labelControl31.setLabel("Base Calculo Icms:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        jPanel3.add(labelControl31, gridBagConstraints);

        numericControl29.setAttributeName("baseCalculoIcms");
        numericControl29.setDecimals(2);
        numericControl29.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        jPanel3.add(numericControl29, gridBagConstraints);

        labelControl32.setLabel("Valor Icms:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        jPanel3.add(labelControl32, gridBagConstraints);

        numericControl30.setAttributeName("valorIcms");
        numericControl30.setDecimals(2);
        numericControl30.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        jPanel3.add(numericControl30, gridBagConstraints);

        labelControl33.setLabel("Base Calculo Icms St:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        jPanel3.add(labelControl33, gridBagConstraints);

        numericControl31.setAttributeName("baseCalculoIcmsSt");
        numericControl31.setDecimals(2);
        numericControl31.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        jPanel3.add(numericControl31, gridBagConstraints);

        labelControl34.setLabel("Valor Icms St:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        jPanel3.add(labelControl34, gridBagConstraints);

        numericControl32.setAttributeName("valorIcmsSt");
        numericControl32.setDecimals(2);
        numericControl32.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        jPanel3.add(numericControl32, gridBagConstraints);

        labelControl40.setLabel("Valor Ipi:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        jPanel3.add(labelControl40, gridBagConstraints);

        numericControl38.setAttributeName("valorIpi");
        numericControl38.setDecimals(2);
        numericControl38.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        jPanel3.add(numericControl38, gridBagConstraints);

        labelControl42.setLabel("Valor Cofins:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        jPanel3.add(labelControl42, gridBagConstraints);

        numericControl40.setAttributeName("valorCofins");
        numericControl40.setDecimals(2);
        numericControl40.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        jPanel3.add(numericControl40, gridBagConstraints);

        labelControl35.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        labelControl35.setLabel("Valor Total Produtos:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        jPanel3.add(labelControl35, gridBagConstraints);

        numericControl33.setAttributeName("valorTotalProdutos");
        numericControl33.setDecimals(2);
        numericControl33.setEnabled(false);
        numericControl33.setEnabledOnEdit(false);
        numericControl33.setEnabledOnInsert(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        jPanel3.add(numericControl33, gridBagConstraints);

        labelControl36.setLabel("Valor Frete:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        jPanel3.add(labelControl36, gridBagConstraints);

        numericControl34.setAttributeName("valorFrete");
        numericControl34.setDecimals(2);
        numericControl34.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        jPanel3.add(numericControl34, gridBagConstraints);

        labelControl41.setLabel("Valor Pis:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        jPanel3.add(labelControl41, gridBagConstraints);

        numericControl39.setAttributeName("valorPis");
        numericControl39.setDecimals(2);
        numericControl39.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        jPanel3.add(numericControl39, gridBagConstraints);

        labelControl37.setLabel("Valor Seguro:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        jPanel3.add(labelControl37, gridBagConstraints);

        numericControl35.setAttributeName("valorSeguro");
        numericControl35.setDecimals(2);
        numericControl35.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        jPanel3.add(numericControl35, gridBagConstraints);

        labelControl43.setLabel("Valor Despesas Acessorias:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        jPanel3.add(labelControl43, gridBagConstraints);

        numericControl41.setAttributeName("valorDespesasAcessorias");
        numericControl41.setDecimals(2);
        numericControl41.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        jPanel3.add(numericControl41, gridBagConstraints);

        labelControl38.setLabel("Valor Desconto:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        jPanel3.add(labelControl38, gridBagConstraints);

        numericControl36.setAttributeName("valorDesconto");
        numericControl36.setDecimals(2);
        numericControl36.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        jPanel3.add(numericControl36, gridBagConstraints);

        labelControl39.setLabel("Valor Imposto Importacao:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        jPanel3.add(labelControl39, gridBagConstraints);

        numericControl37.setAttributeName("valorImpostoImportacao");
        numericControl37.setDecimals(2);
        numericControl37.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        jPanel3.add(numericControl37, gridBagConstraints);

        labelControl44.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        labelControl44.setLabel("Valor Total:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        jPanel3.add(labelControl44, gridBagConstraints);

        numericControl42.setAttributeName("valorTotal");
        numericControl42.setDecimals(2);
        numericControl42.setEnabled(false);
        numericControl42.setEnabledOnEdit(false);
        numericControl42.setEnabledOnInsert(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        jPanel3.add(numericControl42, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        jPanel34.add(jPanel3, gridBagConstraints);

        jPanel35.setBorder(javax.swing.BorderFactory.createTitledBorder("Outros Totais"));
        jPanel35.setLayout(new java.awt.GridBagLayout());

        labelControl134.setLabel("Base Calculo Issqn:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        jPanel35.add(labelControl134, gridBagConstraints);

        numericControl63.setAttributeName("baseCalculoIssqn");
        numericControl63.setDecimals(2);
        numericControl63.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        jPanel35.add(numericControl63, gridBagConstraints);

        labelControl135.setLabel("Valor Issqn:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        jPanel35.add(labelControl135, gridBagConstraints);

        numericControl64.setAttributeName("valorIssqn");
        numericControl64.setDecimals(2);
        numericControl64.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        jPanel35.add(numericControl64, gridBagConstraints);

        labelControl136.setLabel("Valor Pis Issqn:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        jPanel35.add(labelControl136, gridBagConstraints);

        numericControl65.setAttributeName("valorPisIssqn");
        numericControl65.setDecimals(2);
        numericControl65.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        jPanel35.add(numericControl65, gridBagConstraints);

        labelControl137.setLabel("Valor Cofins Issqn:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        jPanel35.add(labelControl137, gridBagConstraints);

        numericControl66.setAttributeName("valorCofinsIssqn");
        numericControl66.setDecimals(2);
        numericControl66.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        jPanel35.add(numericControl66, gridBagConstraints);

        labelControl138.setLabel("Valor Servicos:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        jPanel35.add(labelControl138, gridBagConstraints);

        numericControl67.setAttributeName("valorServicos");
        numericControl67.setDecimals(2);
        numericControl67.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        jPanel35.add(numericControl67, gridBagConstraints);

        labelControl139.setLabel("Valor Retido Pis:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        jPanel35.add(labelControl139, gridBagConstraints);

        numericControl68.setAttributeName("valorRetidoPis");
        numericControl68.setDecimals(2);
        numericControl68.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        jPanel35.add(numericControl68, gridBagConstraints);

        labelControl140.setLabel("Valor Retido Cofins:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        jPanel35.add(labelControl140, gridBagConstraints);

        numericControl69.setAttributeName("valorRetidoCofins");
        numericControl69.setDecimals(2);
        numericControl69.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        jPanel35.add(numericControl69, gridBagConstraints);

        labelControl141.setLabel("Valor Retido Csll:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        jPanel35.add(labelControl141, gridBagConstraints);

        numericControl70.setAttributeName("valorRetidoCsll");
        numericControl70.setDecimals(2);
        numericControl70.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        jPanel35.add(numericControl70, gridBagConstraints);

        labelControl142.setLabel("Base Calculo Irrf:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        jPanel35.add(labelControl142, gridBagConstraints);

        numericControl71.setAttributeName("baseCalculoIrrf");
        numericControl71.setDecimals(2);
        numericControl71.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        jPanel35.add(numericControl71, gridBagConstraints);

        labelControl143.setLabel("Valor Retido Irrf:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        jPanel35.add(labelControl143, gridBagConstraints);

        numericControl72.setAttributeName("valorRetidoIrrf");
        numericControl72.setDecimals(2);
        numericControl72.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        jPanel35.add(numericControl72, gridBagConstraints);

        labelControl144.setLabel("Base Calculo Previdencia:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        jPanel35.add(labelControl144, gridBagConstraints);

        numericControl73.setAttributeName("baseCalculoPrevidencia");
        numericControl73.setDecimals(2);
        numericControl73.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        jPanel35.add(numericControl73, gridBagConstraints);

        labelControl145.setLabel("Valor Retido Previdencia:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        jPanel35.add(labelControl145, gridBagConstraints);

        numericControl74.setAttributeName("valorRetidoPrevidencia");
        numericControl74.setDecimals(2);
        numericControl74.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        jPanel35.add(numericControl74, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel35.add(jSeparator1, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel34.add(jPanel35, gridBagConstraints);

        jTabbedPane1.addTab("Totais", jPanel34);

        jPanel5.setLayout(new java.awt.GridBagLayout());

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder("Comercio Exterior"));
        jPanel6.setLayout(new java.awt.GridBagLayout());

        labelControl57.setLabel("Comex Uf Embarque:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        jPanel6.add(labelControl57, gridBagConstraints);

        textControl55.setAttributeName("comexUfEmbarque");
        textControl55.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        jPanel6.add(textControl55, gridBagConstraints);

        labelControl58.setLabel("Comex Local Embarque:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        jPanel6.add(labelControl58, gridBagConstraints);

        textControl56.setAttributeName("comexLocalEmbarque");
        textControl56.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        jPanel6.add(textControl56, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        jPanel5.add(jPanel6, gridBagConstraints);

        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder("Compras"));
        jPanel7.setLayout(new java.awt.GridBagLayout());

        labelControl59.setLabel("Compra Nota Empenho:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        jPanel7.add(labelControl59, gridBagConstraints);

        textControl57.setAttributeName("compraNotaEmpenho");
        textControl57.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        jPanel7.add(textControl57, gridBagConstraints);

        labelControl60.setLabel("Compra Pedido:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        jPanel7.add(labelControl60, gridBagConstraints);

        textControl58.setAttributeName("compraPedido");
        textControl58.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        jPanel7.add(textControl58, gridBagConstraints);

        labelControl61.setLabel("Compra Contrato:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        jPanel7.add(labelControl61, gridBagConstraints);

        textControl59.setAttributeName("compraContrato");
        textControl59.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        jPanel7.add(textControl59, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        jPanel5.add(jPanel7, gridBagConstraints);

        labelControl62.setLabel("Informacoes Add Fisco:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        jPanel5.add(labelControl62, gridBagConstraints);

        labelControl63.setLabel("Informacoes Add Contribuinte:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        jPanel5.add(labelControl63, gridBagConstraints);

        textAreaControl1.setAttributeName("informacoesAddContribuinte");
        textAreaControl1.setEnabled(false);
        textAreaControl1.setMaxCharacters(1000);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipady = 30;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        jPanel5.add(textAreaControl1, gridBagConstraints);

        textAreaControl2.setAttributeName("informacoesAddFisco");
        textAreaControl2.setEnabled(false);
        textAreaControl2.setMaxCharacters(1000);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipady = 30;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        jPanel5.add(textAreaControl2, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel5.add(jSeparator2, gridBagConstraints);

        jTabbedPane1.addTab("Informações Adicionais", jPanel5);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.gridwidth = 8;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        formDadosNfe.add(jTabbedPane1, gridBagConstraints);

        jTabbedPane2.addTab("Dados da NF", formDadosNfe);

        formEmitente.setVOClassName("com.t2tierp.nfe.java.NfeEmitenteVO");
        formEmitente.setFunctionId("nfeEmitente");
        formEmitente.setLayout(new java.awt.GridBagLayout());

        labelControl3.setLabel("Cpf Cnpj:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        formEmitente.add(labelControl3, gridBagConstraints);

        textControl3.setAttributeName("cpfCnpj");
        textControl3.setEnabled(false);
        textControl3.setMaxCharacters(14);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        formEmitente.add(textControl3, gridBagConstraints);

        labelControl4.setLabel("Razao Social:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        formEmitente.add(labelControl4, gridBagConstraints);

        textControl4.setAttributeName("nome");
        textControl4.setEnabled(false);
        textControl4.setMaxCharacters(60);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        formEmitente.add(textControl4, gridBagConstraints);

        labelControl5.setLabel("Fantasia:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        formEmitente.add(labelControl5, gridBagConstraints);

        textControl5.setAttributeName("fantasia");
        textControl5.setEnabled(false);
        textControl5.setMaxCharacters(60);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        formEmitente.add(textControl5, gridBagConstraints);

        labelControl6.setLabel("Logradouro:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        formEmitente.add(labelControl6, gridBagConstraints);

        textControl6.setAttributeName("logradouro");
        textControl6.setEnabled(false);
        textControl6.setMaxCharacters(60);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        formEmitente.add(textControl6, gridBagConstraints);

        labelControl7.setLabel("Numero:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        formEmitente.add(labelControl7, gridBagConstraints);

        textControl7.setAttributeName("numero");
        textControl7.setEnabled(false);
        textControl7.setMaxCharacters(60);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        formEmitente.add(textControl7, gridBagConstraints);

        labelControl8.setLabel("Complemento:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        formEmitente.add(labelControl8, gridBagConstraints);

        textControl10.setAttributeName("complemento");
        textControl10.setEnabled(false);
        textControl10.setMaxCharacters(60);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        formEmitente.add(textControl10, gridBagConstraints);

        labelControl9.setLabel("Bairro:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        formEmitente.add(labelControl9, gridBagConstraints);

        textControl11.setAttributeName("bairro");
        textControl11.setEnabled(false);
        textControl11.setMaxCharacters(60);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        formEmitente.add(textControl11, gridBagConstraints);

        labelControl20.setLabel("Codigo Municipio:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        formEmitente.add(labelControl20, gridBagConstraints);

        numericControl10.setAttributeName("codigoMunicipio");
        numericControl10.setEnabled(false);
        numericControl10.setMaxCharacters(2);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        formEmitente.add(numericControl10, gridBagConstraints);

        labelControl25.setLabel("Nome Municipio:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        formEmitente.add(labelControl25, gridBagConstraints);

        textControl14.setAttributeName("nomeMunicipio");
        textControl14.setEnabled(false);
        textControl14.setMaxCharacters(60);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        formEmitente.add(textControl14, gridBagConstraints);

        labelControl27.setLabel("Uf:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        formEmitente.add(labelControl27, gridBagConstraints);

        textControl15.setAttributeName("uf");
        textControl15.setEnabled(false);
        textControl15.setMaxCharacters(2);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        formEmitente.add(textControl15, gridBagConstraints);

        labelControl28.setLabel("Cep:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        formEmitente.add(labelControl28, gridBagConstraints);

        textControl17.setAttributeName("cep");
        textControl17.setEnabled(false);
        textControl17.setMaxCharacters(8);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        formEmitente.add(textControl17, gridBagConstraints);

        labelControl64.setLabel("Telefone:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        formEmitente.add(labelControl64, gridBagConstraints);

        textControl19.setAttributeName("telefone");
        textControl19.setEnabled(false);
        textControl19.setMaxCharacters(14);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        formEmitente.add(textControl19, gridBagConstraints);

        labelControl65.setLabel("Inscricao Estadual:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        formEmitente.add(labelControl65, gridBagConstraints);

        textControl20.setAttributeName("inscricaoEstadual");
        textControl20.setEnabled(false);
        textControl20.setMaxCharacters(14);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        formEmitente.add(textControl20, gridBagConstraints);

        labelControl66.setLabel("Inscricao Estadual St:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        formEmitente.add(labelControl66, gridBagConstraints);

        textControl23.setAttributeName("inscricaoEstadualSt");
        textControl23.setEnabled(false);
        textControl23.setMaxCharacters(14);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        formEmitente.add(textControl23, gridBagConstraints);

        labelControl67.setLabel("Inscricao Municipal:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        formEmitente.add(labelControl67, gridBagConstraints);

        textControl24.setAttributeName("inscricaoMunicipal");
        textControl24.setEnabled(false);
        textControl24.setMaxCharacters(15);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        formEmitente.add(textControl24, gridBagConstraints);

        labelControl68.setLabel("Cnae:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        formEmitente.add(labelControl68, gridBagConstraints);

        textControl25.setAttributeName("cnae");
        textControl25.setEnabled(false);
        textControl25.setMaxCharacters(7);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        formEmitente.add(textControl25, gridBagConstraints);

        labelControl69.setLabel("Crt:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        formEmitente.add(labelControl69, gridBagConstraints);

        comboBoxControl21.setAttributeName("crt");
        comboBoxControl21.setDomainId("crt");
        comboBoxControl21.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        formEmitente.add(comboBoxControl21, gridBagConstraints);

        labelControl70.setLabel("Email:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        formEmitente.add(labelControl70, gridBagConstraints);

        textControl26.setAttributeName("email");
        textControl26.setEnabled(false);
        textControl26.setMaxCharacters(60);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 9;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        formEmitente.add(textControl26, gridBagConstraints);

        labelControl71.setLabel("Suframa:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        formEmitente.add(labelControl71, gridBagConstraints);

        textControl27.setAttributeName("suframa");
        textControl27.setEnabled(false);
        textControl27.setMaxCharacters(9);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        formEmitente.add(textControl27, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.gridwidth = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        formEmitente.add(jSeparator3, gridBagConstraints);

        jTabbedPane2.addTab("Emitente", formEmitente);

        jPanel8.setLayout(new java.awt.GridBagLayout());

        jPanel9.setBorder(javax.swing.BorderFactory.createTitledBorder("Nfe Detalhe"));
        jPanel9.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));
        jPanel9.add(insertButtonProduto);
        jPanel9.add(editButtonProduto);
        jPanel9.add(deleteButtonProduto);
        jPanel9.add(saveButtonProduto);
        jPanel9.add(reloadButtonProduto);
        jPanel9.add(navigatorBarProduto);

        genericButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                genericButton2ActionPerformed(evt);
            }
        });
        jPanel9.add(genericButton2);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        jPanel8.add(jPanel9, gridBagConstraints);

        gridControlProduto.setAutoLoadData(false);
        gridControlProduto.setDeleteButton(deleteButtonProduto);
        gridControlProduto.setEditButton(editButtonProduto);
        gridControlProduto.setFunctionId("nfeDetalhe");
        gridControlProduto.setInsertButton(insertButtonProduto);
        gridControlProduto.setInsertRowsOnTop(false);
        gridControlProduto.setNavBar(navigatorBarProduto);
        gridControlProduto.setReloadButton(reloadButtonProduto);
        gridControlProduto.setSaveButton(saveButtonProduto);
        gridControlProduto.setValueObjectClassName("com.t2tierp.nfe.java.NfeDetalheVO");
        gridControlProduto.getColumnContainer().setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        checkBoxColumn1.setColumnName("produtoCadastrado");
        checkBoxColumn1.setColumnRequired(false);
        checkBoxColumn1.setHeaderColumnName("Cadastrado");
        checkBoxColumn1.setHeaderFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        checkBoxColumn1.setPreferredWidth(70);
        gridControlProduto.getColumnContainer().add(checkBoxColumn1);

        integerColumn4.setColumnName("numeroItem");
        integerColumn4.setColumnRequired(false);
        integerColumn4.setHeaderColumnName("Numero Item");
        integerColumn4.setHeaderFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        gridControlProduto.getColumnContainer().add(integerColumn4);

        codLookupColumn1.setColumnName("gtin");
        codLookupColumn1.setEditableOnEdit(true);
        codLookupColumn1.setEditableOnInsert(true);
        codLookupColumn1.setHeaderColumnName("GTIN");
        codLookupColumn1.setHeaderFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        codLookupColumn1.setMaxCharacters(14);
        gridControlProduto.getColumnContainer().add(codLookupColumn1);

        textColumn5.setColumnName("codigoProduto");
        textColumn5.setEditableOnEdit(true);
        textColumn5.setEditableOnInsert(true);
        textColumn5.setHeaderColumnName("Codigo Produto");
        textColumn5.setHeaderFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        textColumn5.setMaxCharacters(60);
        gridControlProduto.getColumnContainer().add(textColumn5);

        textColumn7.setColumnName("nomeProduto");
        textColumn7.setEditableOnEdit(true);
        textColumn7.setEditableOnInsert(true);
        textColumn7.setHeaderColumnName("Nome Produto");
        textColumn7.setHeaderFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        textColumn7.setMaxCharacters(100);
        textColumn7.setPreferredWidth(200);
        gridControlProduto.getColumnContainer().add(textColumn7);

        textColumn8.setColumnName("ncm");
        textColumn8.setColumnRequired(false);
        textColumn8.setEditableOnEdit(true);
        textColumn8.setEditableOnInsert(true);
        textColumn8.setHeaderColumnName("Ncm");
        textColumn8.setHeaderFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        textColumn8.setMaxCharacters(8);
        gridControlProduto.getColumnContainer().add(textColumn8);

        integerColumn9.setColumnName("exTipi");
        integerColumn9.setColumnRequired(false);
        integerColumn9.setEditableOnEdit(true);
        integerColumn9.setEditableOnInsert(true);
        integerColumn9.setHeaderColumnName("Ex Tipi");
        integerColumn9.setHeaderFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        gridControlProduto.getColumnContainer().add(integerColumn9);

        integerColumn10.setColumnName("cfop");
        integerColumn10.setColumnRequired(false);
        integerColumn10.setEditableOnEdit(true);
        integerColumn10.setEditableOnInsert(true);
        integerColumn10.setHeaderColumnName("Cfop");
        integerColumn10.setHeaderFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        gridControlProduto.getColumnContainer().add(integerColumn10);

        textColumn11.setColumnName("unidadeComercial");
        textColumn11.setColumnRequired(false);
        textColumn11.setEditableOnEdit(true);
        textColumn11.setEditableOnInsert(true);
        textColumn11.setHeaderColumnName("Unidade Comercial");
        textColumn11.setHeaderFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        textColumn11.setMaxCharacters(6);
        gridControlProduto.getColumnContainer().add(textColumn11);

        decimalColumn12.setColumnName("quantidadeComercial");
        decimalColumn12.setColumnRequired(false);
        decimalColumn12.setDecimals(2);
        decimalColumn12.setEditableOnEdit(true);
        decimalColumn12.setEditableOnInsert(true);
        decimalColumn12.setHeaderColumnName("Quantidade Comercial");
        decimalColumn12.setHeaderFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        gridControlProduto.getColumnContainer().add(decimalColumn12);

        decimalColumn13.setColumnName("valorUnitarioComercial");
        decimalColumn13.setColumnRequired(false);
        decimalColumn13.setDecimals(2);
        decimalColumn13.setEditableOnEdit(true);
        decimalColumn13.setEditableOnInsert(true);
        decimalColumn13.setHeaderColumnName("Valor Unitario Comercial");
        decimalColumn13.setHeaderFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        gridControlProduto.getColumnContainer().add(decimalColumn13);

        decimalColumn14.setColumnName("valorBrutoProduto");
        decimalColumn14.setColumnRequired(false);
        decimalColumn14.setDecimals(2);
        decimalColumn14.setEditableOnEdit(true);
        decimalColumn14.setEditableOnInsert(true);
        decimalColumn14.setHeaderColumnName("Valor Bruto Produto");
        decimalColumn14.setHeaderFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        gridControlProduto.getColumnContainer().add(decimalColumn14);

        textColumn15.setColumnName("gtinUnidadeTributavel");
        textColumn15.setColumnRequired(false);
        textColumn15.setEditableOnEdit(true);
        textColumn15.setEditableOnInsert(true);
        textColumn15.setHeaderColumnName("Gtin Unidade Tributavel");
        textColumn15.setHeaderFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        textColumn15.setMaxCharacters(14);
        gridControlProduto.getColumnContainer().add(textColumn15);

        textColumn16.setColumnName("unidadeTributavel");
        textColumn16.setColumnRequired(false);
        textColumn16.setEditableOnEdit(true);
        textColumn16.setEditableOnInsert(true);
        textColumn16.setHeaderColumnName("Unidade Tributavel");
        textColumn16.setHeaderFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        textColumn16.setMaxCharacters(6);
        gridControlProduto.getColumnContainer().add(textColumn16);

        decimalColumn17.setColumnName("quantidadeTributavel");
        decimalColumn17.setColumnRequired(false);
        decimalColumn17.setDecimals(2);
        decimalColumn17.setEditableOnEdit(true);
        decimalColumn17.setEditableOnInsert(true);
        decimalColumn17.setHeaderColumnName("Quantidade Tributavel");
        decimalColumn17.setHeaderFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        gridControlProduto.getColumnContainer().add(decimalColumn17);

        decimalColumn18.setColumnName("valorUnitarioTributavel");
        decimalColumn18.setColumnRequired(false);
        decimalColumn18.setDecimals(2);
        decimalColumn18.setEditableOnEdit(true);
        decimalColumn18.setEditableOnInsert(true);
        decimalColumn18.setHeaderColumnName("Valor Unitario Tributavel");
        decimalColumn18.setHeaderFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        gridControlProduto.getColumnContainer().add(decimalColumn18);

        decimalColumn19.setColumnName("valorFrete");
        decimalColumn19.setColumnRequired(false);
        decimalColumn19.setDecimals(2);
        decimalColumn19.setEditableOnEdit(true);
        decimalColumn19.setEditableOnInsert(true);
        decimalColumn19.setHeaderColumnName("Valor Frete");
        decimalColumn19.setHeaderFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        gridControlProduto.getColumnContainer().add(decimalColumn19);

        decimalColumn20.setColumnName("valorSeguro");
        decimalColumn20.setColumnRequired(false);
        decimalColumn20.setDecimals(2);
        decimalColumn20.setEditableOnEdit(true);
        decimalColumn20.setEditableOnInsert(true);
        decimalColumn20.setHeaderColumnName("Valor Seguro");
        decimalColumn20.setHeaderFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        gridControlProduto.getColumnContainer().add(decimalColumn20);

        decimalColumn21.setColumnName("valorDesconto");
        decimalColumn21.setColumnRequired(false);
        decimalColumn21.setDecimals(2);
        decimalColumn21.setEditableOnEdit(true);
        decimalColumn21.setEditableOnInsert(true);
        decimalColumn21.setHeaderColumnName("Valor Desconto");
        decimalColumn21.setHeaderFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        gridControlProduto.getColumnContainer().add(decimalColumn21);

        decimalColumn22.setColumnName("valorOutrasDespesas");
        decimalColumn22.setColumnRequired(false);
        decimalColumn22.setDecimals(2);
        decimalColumn22.setEditableOnEdit(true);
        decimalColumn22.setEditableOnInsert(true);
        decimalColumn22.setHeaderColumnName("Valor Outras Despesas");
        decimalColumn22.setHeaderFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        gridControlProduto.getColumnContainer().add(decimalColumn22);

        comboColumn23.setColumnName("entraTotal");
        comboColumn23.setColumnRequired(false);
        comboColumn23.setDomainId("entraTotalNfe");
        comboColumn23.setEditableOnEdit(true);
        comboColumn23.setEditableOnInsert(true);
        comboColumn23.setHeaderColumnName("Entra Total");
        comboColumn23.setHeaderFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        gridControlProduto.getColumnContainer().add(comboColumn23);

        decimalColumn24.setColumnName("valorSubtotal");
        decimalColumn24.setColumnRequired(false);
        decimalColumn24.setDecimals(2);
        decimalColumn24.setEditableOnEdit(true);
        decimalColumn24.setEditableOnInsert(true);
        decimalColumn24.setHeaderColumnName("Valor Subtotal");
        decimalColumn24.setHeaderFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        gridControlProduto.getColumnContainer().add(decimalColumn24);

        decimalColumn25.setColumnName("valorTotal");
        decimalColumn25.setColumnRequired(false);
        decimalColumn25.setDecimals(2);
        decimalColumn25.setEditableOnEdit(true);
        decimalColumn25.setEditableOnInsert(true);
        decimalColumn25.setHeaderColumnName("Valor Total");
        decimalColumn25.setHeaderFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        gridControlProduto.getColumnContainer().add(decimalColumn25);

        textColumn26.setColumnName("numeroPedidoCompra");
        textColumn26.setColumnRequired(false);
        textColumn26.setEditableOnEdit(true);
        textColumn26.setEditableOnInsert(true);
        textColumn26.setHeaderColumnName("Numero Pedido Compra");
        textColumn26.setHeaderFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        textColumn26.setMaxCharacters(15);
        gridControlProduto.getColumnContainer().add(textColumn26);

        integerColumn27.setColumnName("itemPedidoCompra");
        integerColumn27.setColumnRequired(false);
        integerColumn27.setEditableOnEdit(true);
        integerColumn27.setEditableOnInsert(true);
        integerColumn27.setHeaderColumnName("Item Pedido Compra");
        integerColumn27.setHeaderFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        gridControlProduto.getColumnContainer().add(integerColumn27);

        textColumn28.setColumnName("informacoesAdicionais");
        textColumn28.setColumnRequired(false);
        textColumn28.setEditableOnEdit(true);
        textColumn28.setEditableOnInsert(true);
        textColumn28.setHeaderColumnName("Informacoes Adicionais");
        textColumn28.setHeaderFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        textColumn28.setMaxCharacters(500);
        gridControlProduto.getColumnContainer().add(textColumn28);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel8.add(gridControlProduto, gridBagConstraints);

        jTabbedPane2.addTab("Produtos / Serviços", jPanel8);

        jPanel10.setLayout(new java.awt.GridBagLayout());

        jPanel11.setBorder(javax.swing.BorderFactory.createTitledBorder("Nfe Detalhe"));
        jPanel11.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));
        jPanel11.add(insertButtonNfeReferenciada);
        jPanel11.add(editButtonNfeReferenciada);
        jPanel11.add(deleteButtonNfeReferenciada);
        jPanel11.add(saveButtonNfeReferenciada);
        jPanel11.add(reloadButtonNfeReferenciada);
        jPanel11.add(navigatorBarNfeReferenciada);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        jPanel10.add(jPanel11, gridBagConstraints);

        gridControlNfeReferenciada.setAutoLoadData(false);
        gridControlNfeReferenciada.setDeleteButton(deleteButtonNfeReferenciada);
        gridControlNfeReferenciada.setEditButton(editButtonNfeReferenciada);
        gridControlNfeReferenciada.setFunctionId("nfeReferenciada");
        gridControlNfeReferenciada.setInsertButton(insertButtonNfeReferenciada);
        gridControlNfeReferenciada.setNavBar(navigatorBarNfeReferenciada);
        gridControlNfeReferenciada.setReloadButton(reloadButtonNfeReferenciada);
        gridControlNfeReferenciada.setSaveButton(saveButtonNfeReferenciada);
        gridControlNfeReferenciada.setValueObjectClassName("com.t2tierp.nfe.java.NfeReferenciadaVO");
        gridControlNfeReferenciada.getColumnContainer().setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        textColumn3.setColumnName("nfeCabecalho.digitoChaveAcesso");
        textColumn3.setEditableOnEdit(true);
        textColumn3.setEditableOnInsert(true);
        textColumn3.setHeaderColumnName("Chave de Acesso");
        textColumn3.setHeaderFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        textColumn3.setMaxCharacters(44);
        textColumn3.setPreferredWidth(300);
        gridControlNfeReferenciada.getColumnContainer().add(textColumn3);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel10.add(gridControlNfeReferenciada, gridBagConstraints);

        jTabbedPane3.addTab("NF-e", jPanel10);

        jPanel12.setLayout(new java.awt.GridBagLayout());

        jPanel13.setBorder(javax.swing.BorderFactory.createTitledBorder("Nfe Nf Referenciada"));
        jPanel13.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));
        jPanel13.add(insertButtonNf1Referenciada);
        jPanel13.add(editButtonNf1Referenciada);
        jPanel13.add(deleteButtonNf1Referenciada);
        jPanel13.add(saveButtonNf1Referenciada);
        jPanel13.add(reloadButtonNf1Referenciada);
        jPanel13.add(navigatorBarNf1Referenciada);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        jPanel12.add(jPanel13, gridBagConstraints);

        gridControlNf1Referenciada.setAutoLoadData(false);
        gridControlNf1Referenciada.setDeleteButton(deleteButtonNf1Referenciada);
        gridControlNf1Referenciada.setEditButton(editButtonNf1Referenciada);
        gridControlNf1Referenciada.setFunctionId("nfeNfReferenciada");
        gridControlNf1Referenciada.setInsertButton(insertButtonNf1Referenciada);
        gridControlNf1Referenciada.setNavBar(navigatorBarNf1Referenciada);
        gridControlNf1Referenciada.setReloadButton(reloadButtonNf1Referenciada);
        gridControlNf1Referenciada.setSaveButton(saveButtonNf1Referenciada);
        gridControlNf1Referenciada.setValueObjectClassName("com.t2tierp.nfe.java.NfeNfReferenciadaVO");
        gridControlNf1Referenciada.getColumnContainer().setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        integerColumn3.setColumnName("codigoUf");
        integerColumn3.setEditableOnEdit(true);
        integerColumn3.setEditableOnInsert(true);
        integerColumn3.setHeaderColumnName("Codigo Uf");
        integerColumn3.setHeaderFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        integerColumn3.setMaxCharacters(2);
        gridControlNf1Referenciada.getColumnContainer().add(integerColumn3);

        textColumn4.setColumnName("anoMes");
        textColumn4.setEditableOnEdit(true);
        textColumn4.setEditableOnInsert(true);
        textColumn4.setHeaderColumnName("Ano Mes");
        textColumn4.setHeaderFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        textColumn4.setMaxCharacters(4);
        gridControlNf1Referenciada.getColumnContainer().add(textColumn4);

        textColumn6.setColumnName("cnpj");
        textColumn6.setEditableOnEdit(true);
        textColumn6.setEditableOnInsert(true);
        textColumn6.setHeaderColumnName("Cnpj");
        textColumn6.setHeaderFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        textColumn6.setMaxCharacters(14);
        gridControlNf1Referenciada.getColumnContainer().add(textColumn6);

        textColumn9.setColumnName("modelo");
        textColumn9.setEditableOnEdit(true);
        textColumn9.setEditableOnInsert(true);
        textColumn9.setHeaderColumnName("Modelo");
        textColumn9.setHeaderFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        textColumn9.setMaxCharacters(2);
        gridControlNf1Referenciada.getColumnContainer().add(textColumn9);

        textColumn10.setColumnName("serie");
        textColumn10.setEditableOnEdit(true);
        textColumn10.setEditableOnInsert(true);
        textColumn10.setHeaderColumnName("Serie");
        textColumn10.setHeaderFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        textColumn10.setMaxCharacters(3);
        gridControlNf1Referenciada.getColumnContainer().add(textColumn10);

        textColumn12.setColumnName("numeroNf");
        textColumn12.setEditableOnEdit(true);
        textColumn12.setEditableOnInsert(true);
        textColumn12.setHeaderColumnName("Numero Nf");
        textColumn12.setHeaderFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        textColumn12.setMaxCharacters(9);
        gridControlNf1Referenciada.getColumnContainer().add(textColumn12);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel12.add(gridControlNf1Referenciada, gridBagConstraints);

        jTabbedPane3.addTab("NF 1/1A", jPanel12);

        jPanel14.setLayout(new java.awt.GridBagLayout());

        jPanel15.setBorder(javax.swing.BorderFactory.createTitledBorder("Nfe Cte Referenciado"));
        jPanel15.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));
        jPanel15.add(insertButtonCteReferenciada);
        jPanel15.add(editButtonCteReferenciada);
        jPanel15.add(deleteButtonCteReferenciada);
        jPanel15.add(saveButtonCteReferenciada);
        jPanel15.add(reloadButtonCteReferenciada);
        jPanel15.add(navigatorBarCteReferenciada);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        jPanel14.add(jPanel15, gridBagConstraints);

        gridControlCteReferenciado.setAutoLoadData(false);
        gridControlCteReferenciado.setDeleteButton(deleteButtonCteReferenciada);
        gridControlCteReferenciado.setEditButton(editButtonCteReferenciada);
        gridControlCteReferenciado.setFunctionId("nfeCteReferenciado");
        gridControlCteReferenciado.setInsertButton(insertButtonCteReferenciada);
        gridControlCteReferenciado.setNavBar(navigatorBarCteReferenciada);
        gridControlCteReferenciado.setReloadButton(reloadButtonCteReferenciada);
        gridControlCteReferenciado.setSaveButton(saveButtonCteReferenciada);
        gridControlCteReferenciado.setValueObjectClassName("com.t2tierp.nfe.java.NfeCteReferenciadoVO");
        gridControlCteReferenciado.getColumnContainer().setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        textColumn13.setColumnName("chaveAcesso");
        textColumn13.setEditableOnEdit(true);
        textColumn13.setEditableOnInsert(true);
        textColumn13.setHeaderColumnName("Chave Acesso");
        textColumn13.setHeaderFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        textColumn13.setMaxCharacters(44);
        textColumn13.setPreferredWidth(300);
        gridControlCteReferenciado.getColumnContainer().add(textColumn13);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel14.add(gridControlCteReferenciado, gridBagConstraints);

        jTabbedPane3.addTab("CT-e", jPanel14);

        jPanel16.setLayout(new java.awt.GridBagLayout());

        jPanel17.setBorder(javax.swing.BorderFactory.createTitledBorder("Nfe Prod Rural Referenciada"));
        jPanel17.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));
        jPanel17.add(insertButtonProdRuralReferenciada);
        jPanel17.add(editButtonProdRuralReferenciada);
        jPanel17.add(deleteButtonProdRuralReferenciada);
        jPanel17.add(saveButtonProdRuralReferenciada);
        jPanel17.add(reloadButtonProdRuralReferenciada);
        jPanel17.add(navigatorBarProdRuralReferenciada);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        jPanel16.add(jPanel17, gridBagConstraints);

        gridControlProdRuralReferenciada.setAutoLoadData(false);
        gridControlProdRuralReferenciada.setDeleteButton(deleteButtonProdRuralReferenciada);
        gridControlProdRuralReferenciada.setEditButton(editButtonProdRuralReferenciada);
        gridControlProdRuralReferenciada.setFunctionId("nfeProdRuralReferenciada");
        gridControlProdRuralReferenciada.setInsertButton(insertButtonProdRuralReferenciada);
        gridControlProdRuralReferenciada.setNavBar(navigatorBarProdRuralReferenciada);
        gridControlProdRuralReferenciada.setReloadButton(reloadButtonProdRuralReferenciada);
        gridControlProdRuralReferenciada.setSaveButton(saveButtonProdRuralReferenciada);
        gridControlProdRuralReferenciada.setValueObjectClassName("com.t2tierp.nfe.java.NfeProdRuralReferenciadaVO");
        gridControlProdRuralReferenciada.getColumnContainer().setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        integerColumn5.setColumnName("codigoUf");
        integerColumn5.setEditableOnEdit(true);
        integerColumn5.setEditableOnInsert(true);
        integerColumn5.setHeaderColumnName("Codigo Uf");
        integerColumn5.setHeaderFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        integerColumn5.setMaxCharacters(2);
        gridControlProdRuralReferenciada.getColumnContainer().add(integerColumn5);

        textColumn14.setColumnName("anoMes");
        textColumn14.setEditableOnEdit(true);
        textColumn14.setEditableOnInsert(true);
        textColumn14.setHeaderColumnName("Ano Mes");
        textColumn14.setHeaderFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        textColumn14.setMaxCharacters(4);
        gridControlProdRuralReferenciada.getColumnContainer().add(textColumn14);

        textColumn17.setColumnName("cnpj");
        textColumn17.setColumnRequired(false);
        textColumn17.setEditableOnEdit(true);
        textColumn17.setEditableOnInsert(true);
        textColumn17.setHeaderColumnName("Cnpj");
        textColumn17.setHeaderFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        textColumn17.setMaxCharacters(14);
        gridControlProdRuralReferenciada.getColumnContainer().add(textColumn17);

        textColumn18.setColumnName("cpf");
        textColumn18.setColumnRequired(false);
        textColumn18.setEditableOnEdit(true);
        textColumn18.setEditableOnInsert(true);
        textColumn18.setHeaderColumnName("Cpf");
        textColumn18.setHeaderFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        textColumn18.setMaxCharacters(11);
        gridControlProdRuralReferenciada.getColumnContainer().add(textColumn18);

        textColumn19.setColumnName("inscricaoEstadual");
        textColumn19.setColumnRequired(false);
        textColumn19.setEditableOnEdit(true);
        textColumn19.setEditableOnInsert(true);
        textColumn19.setHeaderColumnName("Inscricao Estadual");
        textColumn19.setHeaderFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        textColumn19.setMaxCharacters(14);
        textColumn19.setPreferredWidth(110);
        gridControlProdRuralReferenciada.getColumnContainer().add(textColumn19);

        textColumn20.setColumnName("modelo");
        textColumn20.setEditableOnEdit(true);
        textColumn20.setEditableOnInsert(true);
        textColumn20.setHeaderColumnName("Modelo");
        textColumn20.setHeaderFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        textColumn20.setMaxCharacters(2);
        gridControlProdRuralReferenciada.getColumnContainer().add(textColumn20);

        textColumn21.setColumnName("serie");
        textColumn21.setEditableOnEdit(true);
        textColumn21.setEditableOnInsert(true);
        textColumn21.setHeaderColumnName("Serie");
        textColumn21.setHeaderFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        textColumn21.setMaxCharacters(3);
        gridControlProdRuralReferenciada.getColumnContainer().add(textColumn21);

        textColumn22.setColumnName("numeroNf");
        textColumn22.setEditableOnEdit(true);
        textColumn22.setEditableOnInsert(true);
        textColumn22.setHeaderColumnName("Numero Nf");
        textColumn22.setHeaderFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        textColumn22.setMaxCharacters(9);
        gridControlProdRuralReferenciada.getColumnContainer().add(textColumn22);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel16.add(gridControlProdRuralReferenciada, gridBagConstraints);

        jTabbedPane3.addTab("NF Produtor Rural", jPanel16);

        jPanel18.setLayout(new java.awt.GridBagLayout());

        jPanel19.setBorder(javax.swing.BorderFactory.createTitledBorder("Nfe Cupom Fiscal Referenciado"));
        jPanel19.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));
        jPanel19.add(insertButtonCupomFiscalReferenciado);
        jPanel19.add(editButtonCupomFiscalReferenciado);
        jPanel19.add(deleteButtonCupomFiscalReferenciado);
        jPanel19.add(saveButtonCupomFiscalReferenciado);
        jPanel19.add(reloadButtonCupomFiscalReferenciado);
        jPanel19.add(navigatorBarCupomFiscalReferenciado);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        jPanel18.add(jPanel19, gridBagConstraints);

        gridControlCupomFiscalReferenciado.setDeleteButton(deleteButtonCupomFiscalReferenciado);
        gridControlCupomFiscalReferenciado.setEditButton(editButtonCupomFiscalReferenciado);
        gridControlCupomFiscalReferenciado.setFunctionId("nfeCupomFiscalReferenciado");
        gridControlCupomFiscalReferenciado.setInsertButton(insertButtonCupomFiscalReferenciado);
        gridControlCupomFiscalReferenciado.setNavBar(navigatorBarCupomFiscalReferenciado);
        gridControlCupomFiscalReferenciado.setReloadButton(reloadButtonCupomFiscalReferenciado);
        gridControlCupomFiscalReferenciado.setSaveButton(saveButtonCupomFiscalReferenciado);
        gridControlCupomFiscalReferenciado.setValueObjectClassName("com.t2tierp.nfe.java.NfeCupomFiscalReferenciadoVO");
        gridControlCupomFiscalReferenciado.getColumnContainer().setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        textColumn23.setColumnName("modeloDocumentoFiscal");
        textColumn23.setEditableOnEdit(true);
        textColumn23.setEditableOnInsert(true);
        textColumn23.setHeaderColumnName("Modelo Documento Fiscal");
        textColumn23.setHeaderFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        textColumn23.setMaxCharacters(2);
        textColumn23.setPreferredWidth(150);
        gridControlCupomFiscalReferenciado.getColumnContainer().add(textColumn23);

        integerColumn6.setColumnName("numeroOrdemEcf");
        integerColumn6.setEditableOnEdit(true);
        integerColumn6.setEditableOnInsert(true);
        integerColumn6.setHeaderColumnName("Numero Ordem Ecf");
        integerColumn6.setHeaderFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        integerColumn6.setMaxCharacters(3);
        integerColumn6.setPreferredWidth(110);
        gridControlCupomFiscalReferenciado.getColumnContainer().add(integerColumn6);

        integerColumn7.setColumnName("coo");
        integerColumn7.setEditableOnEdit(true);
        integerColumn7.setEditableOnInsert(true);
        integerColumn7.setHeaderColumnName("Coo");
        integerColumn7.setHeaderFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        integerColumn7.setMaxCharacters(10);
        gridControlCupomFiscalReferenciado.getColumnContainer().add(integerColumn7);

        dateColumn6.setColumnName("dataEmissaoCupom");
        dateColumn6.setEditableOnEdit(true);
        dateColumn6.setEditableOnInsert(true);
        dateColumn6.setHeaderColumnName("Data Emissao Cupom");
        dateColumn6.setHeaderFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        dateColumn6.setPreferredWidth(130);
        gridControlCupomFiscalReferenciado.getColumnContainer().add(dateColumn6);

        integerColumn8.setColumnName("numeroCaixa");
        integerColumn8.setEditableOnEdit(true);
        integerColumn8.setEditableOnInsert(true);
        integerColumn8.setHeaderColumnName("Numero Caixa");
        integerColumn8.setHeaderFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        gridControlCupomFiscalReferenciado.getColumnContainer().add(integerColumn8);

        textColumn24.setColumnName("numeroSerieEcf");
        textColumn24.setEditableOnEdit(true);
        textColumn24.setEditableOnInsert(true);
        textColumn24.setHeaderColumnName("Numero Serie Ecf");
        textColumn24.setHeaderFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        textColumn24.setMaxCharacters(21);
        textColumn24.setPreferredWidth(150);
        gridControlCupomFiscalReferenciado.getColumnContainer().add(textColumn24);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel18.add(gridControlCupomFiscalReferenciado, gridBagConstraints);

        jTabbedPane3.addTab("Cupom Fiscal", jPanel18);

        jTabbedPane2.addTab("Documentos Referenciados", jTabbedPane3);

        jPanel20.setLayout(new java.awt.GridBagLayout());

        formDadosEntrega.setBorder(javax.swing.BorderFactory.createTitledBorder("Dados Para Entrega"));
        formDadosEntrega.setVOClassName("com.t2tierp.nfe.java.NfeLocalEntregaVO");
        formDadosEntrega.setFunctionId("nfeLocalEntrega");
        formDadosEntrega.setLayout(new java.awt.GridBagLayout());

        labelControl29.setLabel("Cpf Cnpj:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        formDadosEntrega.add(labelControl29, gridBagConstraints);

        textControl18.setAttributeName("cpfCnpj");
        textControl18.setEnabled(false);
        textControl18.setMaxCharacters(14);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        formDadosEntrega.add(textControl18, gridBagConstraints);

        labelControl30.setLabel("Logradouro:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        formDadosEntrega.add(labelControl30, gridBagConstraints);

        textControl28.setAttributeName("logradouro");
        textControl28.setEnabled(false);
        textControl28.setMaxCharacters(60);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        formDadosEntrega.add(textControl28, gridBagConstraints);

        labelControl72.setLabel("Numero:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        formDadosEntrega.add(labelControl72, gridBagConstraints);

        textControl29.setAttributeName("numero");
        textControl29.setEnabled(false);
        textControl29.setMaxCharacters(60);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        formDadosEntrega.add(textControl29, gridBagConstraints);

        labelControl73.setLabel("Complemento:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        formDadosEntrega.add(labelControl73, gridBagConstraints);

        textControl30.setAttributeName("complemento");
        textControl30.setEnabled(false);
        textControl30.setMaxCharacters(60);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        formDadosEntrega.add(textControl30, gridBagConstraints);

        labelControl74.setLabel("Bairro:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        formDadosEntrega.add(labelControl74, gridBagConstraints);

        textControl31.setAttributeName("bairro");
        textControl31.setEnabled(false);
        textControl31.setMaxCharacters(60);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        formDadosEntrega.add(textControl31, gridBagConstraints);

        labelControl75.setLabel("Codigo Municipio:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        formDadosEntrega.add(labelControl75, gridBagConstraints);

        numericControl8.setAttributeName("codigoMunicipio");
        numericControl8.setEnabled(false);
        numericControl8.setMaxCharacters(2);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        formDadosEntrega.add(numericControl8, gridBagConstraints);

        labelControl76.setLabel("Nome Municipio:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        formDadosEntrega.add(labelControl76, gridBagConstraints);

        textControl32.setAttributeName("nomeMunicipio");
        textControl32.setEnabled(false);
        textControl32.setMaxCharacters(60);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        formDadosEntrega.add(textControl32, gridBagConstraints);

        labelControl77.setLabel("Uf:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        formDadosEntrega.add(labelControl77, gridBagConstraints);

        textControl33.setAttributeName("uf");
        textControl33.setEnabled(false);
        textControl33.setMaxCharacters(2);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        formDadosEntrega.add(textControl33, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.gridwidth = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        formDadosEntrega.add(jSeparator4, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        jPanel20.add(formDadosEntrega, gridBagConstraints);

        formDadosRetirada.setBorder(javax.swing.BorderFactory.createTitledBorder("Dados Para Retirada"));
        formDadosRetirada.setVOClassName("com.t2tierp.nfe.java.NfeLocalRetiradaVO");
        formDadosRetirada.setFunctionId("nfeLocalRetirada");
        formDadosRetirada.setLayout(new java.awt.GridBagLayout());

        labelControl78.setLabel("Cpf Cnpj:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        formDadosRetirada.add(labelControl78, gridBagConstraints);

        textControl34.setAttributeName("cpfCnpj");
        textControl34.setEnabled(false);
        textControl34.setMaxCharacters(14);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        formDadosRetirada.add(textControl34, gridBagConstraints);

        labelControl79.setLabel("Logradouro:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        formDadosRetirada.add(labelControl79, gridBagConstraints);

        textControl35.setAttributeName("logradouro");
        textControl35.setEnabled(false);
        textControl35.setMaxCharacters(60);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        formDadosRetirada.add(textControl35, gridBagConstraints);

        labelControl80.setLabel("Numero:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        formDadosRetirada.add(labelControl80, gridBagConstraints);

        textControl36.setAttributeName("numero");
        textControl36.setEnabled(false);
        textControl36.setMaxCharacters(60);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        formDadosRetirada.add(textControl36, gridBagConstraints);

        labelControl81.setLabel("Complemento:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        formDadosRetirada.add(labelControl81, gridBagConstraints);

        textControl37.setAttributeName("complemento");
        textControl37.setEnabled(false);
        textControl37.setMaxCharacters(60);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        formDadosRetirada.add(textControl37, gridBagConstraints);

        labelControl82.setLabel("Bairro:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        formDadosRetirada.add(labelControl82, gridBagConstraints);

        textControl38.setAttributeName("bairro");
        textControl38.setEnabled(false);
        textControl38.setMaxCharacters(60);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        formDadosRetirada.add(textControl38, gridBagConstraints);

        labelControl83.setLabel("Codigo Municipio:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        formDadosRetirada.add(labelControl83, gridBagConstraints);

        numericControl9.setAttributeName("codigoMunicipio");
        numericControl9.setEnabled(false);
        numericControl9.setMaxCharacters(2);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        formDadosRetirada.add(numericControl9, gridBagConstraints);

        labelControl84.setLabel("Nome Municipio:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        formDadosRetirada.add(labelControl84, gridBagConstraints);

        textControl39.setAttributeName("nomeMunicipio");
        textControl39.setEnabled(false);
        textControl39.setMaxCharacters(60);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        formDadosRetirada.add(textControl39, gridBagConstraints);

        labelControl85.setLabel("Uf:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        formDadosRetirada.add(labelControl85, gridBagConstraints);

        textControl40.setAttributeName("uf");
        textControl40.setEnabled(false);
        textControl40.setMaxCharacters(2);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        formDadosRetirada.add(textControl40, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.gridwidth = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        formDadosRetirada.add(jSeparator5, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel20.add(formDadosRetirada, gridBagConstraints);

        jTabbedPane2.addTab("Entrega / Retirada", jPanel20);

        jPanel21.setLayout(new java.awt.GridBagLayout());

        formTransporte.setVOClassName("com.t2tierp.nfe.java.NfeTransporteVO");
        formTransporte.setFunctionId("nfeTransporte");
        formTransporte.setLayout(new java.awt.GridBagLayout());

        labelControl88.setLabel("Modalidade Frete:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        formTransporte.add(labelControl88, gridBagConstraints);

        comboBoxControl4.setAttributeName("modalidadeFrete");
        comboBoxControl4.setDomainId("transporteModalidadeFrete");
        comboBoxControl4.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        formTransporte.add(comboBoxControl4, gridBagConstraints);

        labelControl104.setLabel("Vagao:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        formTransporte.add(labelControl104, gridBagConstraints);

        textControl51.setAttributeName("vagao");
        textControl51.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        formTransporte.add(textControl51, gridBagConstraints);

        labelControl105.setLabel("Balsa:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        formTransporte.add(labelControl105, gridBagConstraints);

        textControl52.setAttributeName("balsa");
        textControl52.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints.ipadx = 100;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        formTransporte.add(textControl52, gridBagConstraints);

        jPanel22.setBorder(javax.swing.BorderFactory.createTitledBorder("Transportador"));
        jPanel22.setLayout(new java.awt.GridBagLayout());

        labelControl1.setText("Transportadora(ID):");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        jPanel22.add(labelControl1, gridBagConstraints);

        codLookupControl2.setAttributeName("transportadora.id");
        codLookupControl2.setEnabled(false);
        codLookupControl2.setMaxCharacters(10);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = -70;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        jPanel22.add(codLookupControl2, gridBagConstraints);

        labelControl89.setLabel("Cpf Cnpj:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        jPanel22.add(labelControl89, gridBagConstraints);

        textControl42.setAttributeName("cpfCnpj");
        textControl42.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 30;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        jPanel22.add(textControl42, gridBagConstraints);

        labelControl90.setLabel("Nome:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        jPanel22.add(labelControl90, gridBagConstraints);

        textControl43.setAttributeName("nome");
        textControl43.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        jPanel22.add(textControl43, gridBagConstraints);

        textControl44.setAttributeName("inscricaoEstadual");
        textControl44.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        jPanel22.add(textControl44, gridBagConstraints);

        labelControl91.setLabel("Inscricao Estadual:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        jPanel22.add(labelControl91, gridBagConstraints);

        labelControl92.setLabel("Endereco:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        jPanel22.add(labelControl92, gridBagConstraints);

        textControl45.setAttributeName("endereco");
        textControl45.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        jPanel22.add(textControl45, gridBagConstraints);

        labelControl93.setLabel("Nome Municipio:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        jPanel22.add(labelControl93, gridBagConstraints);

        textControl46.setAttributeName("nomeMunicipio");
        textControl46.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        jPanel22.add(textControl46, gridBagConstraints);

        labelControl94.setLabel("Uf:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        jPanel22.add(labelControl94, gridBagConstraints);

        textControl47.setAttributeName("uf");
        textControl47.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        jPanel22.add(textControl47, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        formTransporte.add(jPanel22, gridBagConstraints);

        jPanel23.setBorder(javax.swing.BorderFactory.createTitledBorder("Veiculo"));
        jPanel23.setLayout(new java.awt.GridBagLayout());

        labelControl102.setLabel("Uf Veiculo:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        jPanel23.add(labelControl102, gridBagConstraints);

        textControl49.setAttributeName("ufVeiculo");
        textControl49.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        jPanel23.add(textControl49, gridBagConstraints);

        labelControl103.setLabel("Rntc Veiculo:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        jPanel23.add(labelControl103, gridBagConstraints);

        textControl50.setAttributeName("rntcVeiculo");
        textControl50.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        jPanel23.add(textControl50, gridBagConstraints);

        labelControl101.setLabel("Placa Veiculo:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        jPanel23.add(labelControl101, gridBagConstraints);

        textControl48.setAttributeName("placaVeiculo");
        textControl48.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        jPanel23.add(textControl48, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        formTransporte.add(jPanel23, gridBagConstraints);

        jPanel24.setBorder(javax.swing.BorderFactory.createTitledBorder("Retenção ICMS"));
        jPanel24.setLayout(new java.awt.GridBagLayout());

        labelControl96.setLabel("Valor Bc Retencao Icms:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        jPanel24.add(labelControl96, gridBagConstraints);

        numericControl12.setAttributeName("valorBcRetencaoIcms");
        numericControl12.setDecimals(2);
        numericControl12.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        jPanel24.add(numericControl12, gridBagConstraints);

        labelControl97.setLabel("Aliquota Retencao Icms:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        jPanel24.add(labelControl97, gridBagConstraints);

        numericControl13.setAttributeName("aliquotaRetencaoIcms");
        numericControl13.setDecimals(2);
        numericControl13.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        jPanel24.add(numericControl13, gridBagConstraints);

        labelControl98.setLabel("Valor Icms Retido:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        jPanel24.add(labelControl98, gridBagConstraints);

        numericControl14.setAttributeName("valorIcmsRetido");
        numericControl14.setDecimals(2);
        numericControl14.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        jPanel24.add(numericControl14, gridBagConstraints);

        labelControl99.setLabel("Cfop:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        jPanel24.add(labelControl99, gridBagConstraints);

        numericControl15.setAttributeName("cfop");
        numericControl15.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        jPanel24.add(numericControl15, gridBagConstraints);

        labelControl100.setText("Cod. Municipio:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        jPanel24.add(labelControl100, gridBagConstraints);

        numericControl16.setAttributeName("municipio");
        numericControl16.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        jPanel24.add(numericControl16, gridBagConstraints);

        labelControl95.setLabel("Valor Servico:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        jPanel24.add(labelControl95, gridBagConstraints);

        numericControl11.setAttributeName("valorServico");
        numericControl11.setDecimals(2);
        numericControl11.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        jPanel24.add(numericControl11, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        formTransporte.add(jPanel24, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        jPanel21.add(formTransporte, gridBagConstraints);

        jPanel25.setLayout(new java.awt.GridBagLayout());

        jPanel28.setBorder(javax.swing.BorderFactory.createTitledBorder("Nfe Transporte Reboque"));
        jPanel28.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));
        jPanel28.add(insertButtonTransporteReboque);
        jPanel28.add(editButtonTransporteReboque);
        jPanel28.add(deleteButtonTransporteReboque);
        jPanel28.add(saveButtonTransporteReboque);
        jPanel28.add(reloadButtonTransporteReboque);
        jPanel28.add(navigatorBarTransporteReboque);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        jPanel25.add(jPanel28, gridBagConstraints);

        gridControlTransporteReboque.setAutoLoadData(false);
        gridControlTransporteReboque.setDeleteButton(deleteButtonTransporteReboque);
        gridControlTransporteReboque.setEditButton(editButtonTransporteReboque);
        gridControlTransporteReboque.setFunctionId("nfeTransporteReboque");
        gridControlTransporteReboque.setInsertButton(insertButtonTransporteReboque);
        gridControlTransporteReboque.setNavBar(navigatorBarTransporteReboque);
        gridControlTransporteReboque.setReloadButton(reloadButtonTransporteReboque);
        gridControlTransporteReboque.setSaveButton(saveButtonTransporteReboque);
        gridControlTransporteReboque.setValueObjectClassName("com.t2tierp.nfe.java.NfeTransporteReboqueVO");
        gridControlTransporteReboque.getColumnContainer().setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        textColumn25.setColumnName("placa");
        textColumn25.setEditableOnEdit(true);
        textColumn25.setEditableOnInsert(true);
        textColumn25.setHeaderColumnName("Placa");
        textColumn25.setHeaderFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        textColumn25.setMaxCharacters(8);
        gridControlTransporteReboque.getColumnContainer().add(textColumn25);

        textColumn27.setColumnName("uf");
        textColumn27.setEditableOnEdit(true);
        textColumn27.setEditableOnInsert(true);
        textColumn27.setHeaderColumnName("Uf");
        textColumn27.setHeaderFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        textColumn27.setMaxCharacters(2);
        gridControlTransporteReboque.getColumnContainer().add(textColumn27);

        textColumn29.setColumnName("rntc");
        textColumn29.setEditableOnEdit(true);
        textColumn29.setEditableOnInsert(true);
        textColumn29.setHeaderColumnName("Rntc");
        textColumn29.setHeaderFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        textColumn29.setMaxCharacters(20);
        gridControlTransporteReboque.getColumnContainer().add(textColumn29);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel25.add(gridControlTransporteReboque, gridBagConstraints);

        jTabbedPane4.addTab("Reboque", jPanel25);

        jPanel26.setLayout(new java.awt.GridBagLayout());

        jPanel29.setBorder(javax.swing.BorderFactory.createTitledBorder("Nfe Transporte Volume"));
        jPanel29.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));
        jPanel29.add(insertButtonTransporteVolume);
        jPanel29.add(editButtonTransporteVolume);
        jPanel29.add(deleteButtonTransporteVolume);
        jPanel29.add(saveButtonTransporteVolume);
        jPanel29.add(reloadButtonTransporteVolume);
        jPanel29.add(navigatorBarTransporteVolume);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        jPanel26.add(jPanel29, gridBagConstraints);

        gridControlTransporteVolume.setAutoLoadData(false);
        gridControlTransporteVolume.setDeleteButton(deleteButtonTransporteVolume);
        gridControlTransporteVolume.setEditButton(editButtonTransporteVolume);
        gridControlTransporteVolume.setFunctionId("nfeTransporteVolume");
        gridControlTransporteVolume.setInsertButton(insertButtonTransporteVolume);
        gridControlTransporteVolume.setNavBar(navigatorBarTransporteVolume);
        gridControlTransporteVolume.setReloadButton(reloadButtonTransporteVolume);
        gridControlTransporteVolume.setSaveButton(saveButtonTransporteVolume);
        gridControlTransporteVolume.setValueObjectClassName("com.t2tierp.nfe.java.NfeTransporteVolumeVO");
        gridControlTransporteVolume.getColumnContainer().setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        integerColumn11.setColumnName("quantidade");
        integerColumn11.setEditableOnEdit(true);
        integerColumn11.setEditableOnInsert(true);
        integerColumn11.setHeaderColumnName("Quantidade");
        integerColumn11.setHeaderFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        gridControlTransporteVolume.getColumnContainer().add(integerColumn11);

        textColumn30.setColumnName("especie");
        textColumn30.setEditableOnEdit(true);
        textColumn30.setEditableOnInsert(true);
        textColumn30.setHeaderColumnName("Especie");
        textColumn30.setHeaderFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        textColumn30.setMaxCharacters(60);
        gridControlTransporteVolume.getColumnContainer().add(textColumn30);

        textColumn31.setColumnName("marca");
        textColumn31.setEditableOnEdit(true);
        textColumn31.setEditableOnInsert(true);
        textColumn31.setHeaderColumnName("Marca");
        textColumn31.setHeaderFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        textColumn31.setMaxCharacters(60);
        gridControlTransporteVolume.getColumnContainer().add(textColumn31);

        textColumn32.setColumnName("numeracao");
        textColumn32.setEditableOnEdit(true);
        textColumn32.setEditableOnInsert(true);
        textColumn32.setHeaderColumnName("Numeracao");
        textColumn32.setHeaderFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        textColumn32.setMaxCharacters(60);
        gridControlTransporteVolume.getColumnContainer().add(textColumn32);

        decimalColumn7.setColumnName("pesoLiquido");
        decimalColumn7.setDecimals(2);
        decimalColumn7.setEditableOnEdit(true);
        decimalColumn7.setEditableOnInsert(true);
        decimalColumn7.setHeaderColumnName("Peso Liquido");
        decimalColumn7.setHeaderFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        gridControlTransporteVolume.getColumnContainer().add(decimalColumn7);

        decimalColumn8.setColumnName("pesoBruto");
        decimalColumn8.setDecimals(2);
        decimalColumn8.setEditableOnEdit(true);
        decimalColumn8.setEditableOnInsert(true);
        decimalColumn8.setHeaderColumnName("Peso Bruto");
        decimalColumn8.setHeaderFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        gridControlTransporteVolume.getColumnContainer().add(decimalColumn8);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel26.add(gridControlTransporteVolume, gridBagConstraints);

        jPanel31.setBorder(javax.swing.BorderFactory.createTitledBorder("Nfe Transporte Lacres"));
        jPanel31.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));
        jPanel31.add(insertButtonTransporteVolumeLacre);
        jPanel31.add(editButtonTransporteVolumeLacre);
        jPanel31.add(deleteButtonTransporteVolumeLacre);
        jPanel31.add(saveButtonTransporteVolumeLacre);
        jPanel31.add(reloadButtonTransporteVolumeLacre);
        jPanel31.add(navigatorBarTransporteVolumeLacre);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        jPanel26.add(jPanel31, gridBagConstraints);

        gridControlTransporteVolumeLacre.setAutoLoadData(false);
        gridControlTransporteVolumeLacre.setDeleteButton(deleteButtonTransporteVolumeLacre);
        gridControlTransporteVolumeLacre.setEditButton(editButtonTransporteVolumeLacre);
        gridControlTransporteVolumeLacre.setFunctionId("nfeTransporteVolumeLacre");
        gridControlTransporteVolumeLacre.setInsertButton(insertButtonTransporteVolumeLacre);
        gridControlTransporteVolumeLacre.setNavBar(navigatorBarTransporteVolumeLacre);
        gridControlTransporteVolumeLacre.setReloadButton(reloadButtonTransporteVolumeLacre);
        gridControlTransporteVolumeLacre.setSaveButton(saveButtonTransporteVolumeLacre);
        gridControlTransporteVolumeLacre.setValueObjectClassName("com.t2tierp.nfe.java.NfeTransporteVolumeLacreVO");
        gridControlTransporteVolumeLacre.getColumnContainer().setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        textColumn33.setColumnName("numero");
        textColumn33.setEditableOnEdit(true);
        textColumn33.setEditableOnInsert(true);
        textColumn33.setHeaderColumnName("Numero");
        textColumn33.setHeaderFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        textColumn33.setMaxCharacters(60);
        textColumn33.setPreferredWidth(200);
        gridControlTransporteVolumeLacre.getColumnContainer().add(textColumn33);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weighty = 1.0;
        jPanel26.add(gridControlTransporteVolumeLacre, gridBagConstraints);

        jTabbedPane4.addTab("Volumes / Lacres", jPanel26);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel21.add(jTabbedPane4, gridBagConstraints);

        jTabbedPane2.addTab("Transporte", jPanel21);

        jPanel27.setLayout(new java.awt.GridBagLayout());

        formFatura.setVOClassName("com.t2tierp.nfe.java.NfeFaturaVO");
        formFatura.setFunctionId("nfeFatura");
        formFatura.setLayout(new java.awt.GridBagLayout());

        labelControl87.setLabel("Numero:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        formFatura.add(labelControl87, gridBagConstraints);

        textControl41.setAttributeName("numero");
        textControl41.setEnabled(false);
        textControl41.setMaxCharacters(60);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        formFatura.add(textControl41, gridBagConstraints);

        labelControl106.setLabel("Valor Original:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        formFatura.add(labelControl106, gridBagConstraints);

        numericControl4.setAttributeName("valorOriginal");
        numericControl4.setDecimals(2);
        numericControl4.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        formFatura.add(numericControl4, gridBagConstraints);

        labelControl107.setLabel("Valor Desconto:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        formFatura.add(labelControl107, gridBagConstraints);

        numericControl5.setAttributeName("valorDesconto");
        numericControl5.setDecimals(2);
        numericControl5.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        formFatura.add(numericControl5, gridBagConstraints);

        labelControl108.setLabel("Valor Liquido:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        formFatura.add(labelControl108, gridBagConstraints);

        numericControl6.setAttributeName("valorLiquido");
        numericControl6.setDecimals(2);
        numericControl6.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        formFatura.add(numericControl6, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        jPanel27.add(formFatura, gridBagConstraints);

        jPanel30.setLayout(new java.awt.GridBagLayout());

        jPanel32.setBorder(javax.swing.BorderFactory.createTitledBorder("Nfe Duplicata"));
        jPanel32.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));
        jPanel32.add(insertButtonDuplicata);
        jPanel32.add(editButtonDuplicata);
        jPanel32.add(deleteButtonDuplicata);
        jPanel32.add(saveButtonDuplicata);
        jPanel32.add(reloadButtonDuplicata);
        jPanel32.add(navigatorBarDuplicata);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        jPanel30.add(jPanel32, gridBagConstraints);

        gridControlDuplicata.setAutoLoadData(false);
        gridControlDuplicata.setFunctionId("nfeDuplicata");
        gridControlDuplicata.setValueObjectClassName("com.t2tierp.nfe.java.NfeDuplicataVO");
        gridControlDuplicata.getColumnContainer().setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        textColumn34.setColumnName("numero");
        textColumn34.setEditableOnEdit(true);
        textColumn34.setEditableOnInsert(true);
        textColumn34.setHeaderColumnName("Numero");
        textColumn34.setHeaderFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        textColumn34.setMaxCharacters(60);
        gridControlDuplicata.getColumnContainer().add(textColumn34);

        dateColumn4.setColumnName("dataVencimento");
        dateColumn4.setEditableOnEdit(true);
        dateColumn4.setEditableOnInsert(true);
        dateColumn4.setHeaderColumnName("Data Vencimento");
        dateColumn4.setHeaderFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        gridControlDuplicata.getColumnContainer().add(dateColumn4);

        decimalColumn5.setColumnName("valor");
        decimalColumn5.setDecimals(2);
        decimalColumn5.setEditableOnEdit(true);
        decimalColumn5.setEditableOnInsert(true);
        decimalColumn5.setHeaderColumnName("Valor");
        decimalColumn5.setHeaderFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        gridControlDuplicata.getColumnContainer().add(decimalColumn5);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel30.add(gridControlDuplicata, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel27.add(jPanel30, gridBagConstraints);

        jTabbedPane2.addTab("Cobrança", jPanel27);

        jPanel33.setLayout(new java.awt.GridBagLayout());

        formEscrituracao.setVOClassName("com.t2tierp.escritafiscal.java.FiscalNotaFiscalEntradaVO");
        formEscrituracao.setFunctionId("fiscalNotaFiscalEntrada");
        formEscrituracao.setLayout(new java.awt.GridBagLayout());

        labelControl109.setLabel("Competencia:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        formEscrituracao.add(labelControl109, gridBagConstraints);

        textControl53.setAttributeName("competencia");
        textControl53.setEnabled(false);
        textControl53.setMaxCharacters(7);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        formEscrituracao.add(textControl53, gridBagConstraints);

        labelControl110.setLabel("Cfop Entrada:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        formEscrituracao.add(labelControl110, gridBagConstraints);

        numericControl7.setAttributeName("cfopEntrada");
        numericControl7.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        formEscrituracao.add(numericControl7, gridBagConstraints);

        labelControl111.setLabel("Valor Rateio Frete:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        formEscrituracao.add(labelControl111, gridBagConstraints);

        numericControl17.setAttributeName("valorRateioFrete");
        numericControl17.setDecimals(2);
        numericControl17.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        formEscrituracao.add(numericControl17, gridBagConstraints);

        labelControl112.setLabel("Valor Custo Medio:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        formEscrituracao.add(labelControl112, gridBagConstraints);

        numericControl18.setAttributeName("valorCustoMedio");
        numericControl18.setDecimals(2);
        numericControl18.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        formEscrituracao.add(numericControl18, gridBagConstraints);

        labelControl113.setLabel("Valor Icms Antecipado:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        formEscrituracao.add(labelControl113, gridBagConstraints);

        numericControl19.setAttributeName("valorIcmsAntecipado");
        numericControl19.setDecimals(2);
        numericControl19.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        formEscrituracao.add(numericControl19, gridBagConstraints);

        labelControl114.setLabel("Valor Bc Icms Antecipado:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        formEscrituracao.add(labelControl114, gridBagConstraints);

        numericControl20.setAttributeName("valorBcIcmsAntecipado");
        numericControl20.setDecimals(2);
        numericControl20.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        formEscrituracao.add(numericControl20, gridBagConstraints);

        labelControl119.setLabel("Cst Credito Icms:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        formEscrituracao.add(labelControl119, gridBagConstraints);

        textControl54.setAttributeName("cstCreditoIcms");
        textControl54.setEnabled(false);
        textControl54.setMaxCharacters(3);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        formEscrituracao.add(textControl54, gridBagConstraints);

        labelControl129.setLabel("Qtde Parcela Credito Icms:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        formEscrituracao.add(labelControl129, gridBagConstraints);

        numericControl57.setAttributeName("qtdeParcelaCreditoIcms");
        numericControl57.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        formEscrituracao.add(numericControl57, gridBagConstraints);

        labelControl131.setLabel("Aliquota Credito Icms:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        formEscrituracao.add(labelControl131, gridBagConstraints);

        numericControl59.setAttributeName("aliquotaCreditoIcms");
        numericControl59.setDecimals(2);
        numericControl59.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        formEscrituracao.add(numericControl59, gridBagConstraints);

        labelControl86.setLabel("Valor Bc Icms Creditado:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        formEscrituracao.add(labelControl86, gridBagConstraints);

        numericControl21.setAttributeName("valorBcIcmsCreditado");
        numericControl21.setDecimals(2);
        numericControl21.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        formEscrituracao.add(numericControl21, gridBagConstraints);

        labelControl115.setLabel("Valor Icms Creditado:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        formEscrituracao.add(labelControl115, gridBagConstraints);

        numericControl22.setAttributeName("valorIcmsCreditado");
        numericControl22.setDecimals(2);
        numericControl22.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        formEscrituracao.add(numericControl22, gridBagConstraints);

        labelControl116.setLabel("Cst Credito Pis:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        formEscrituracao.add(labelControl116, gridBagConstraints);

        textControl60.setAttributeName("cstCreditoPis");
        textControl60.setEnabled(false);
        textControl60.setMaxCharacters(2);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        formEscrituracao.add(textControl60, gridBagConstraints);

        labelControl117.setLabel("Aliquota Credito Pis:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        formEscrituracao.add(labelControl117, gridBagConstraints);

        numericControl26.setAttributeName("aliquotaCreditoPis");
        numericControl26.setDecimals(2);
        numericControl26.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        formEscrituracao.add(numericControl26, gridBagConstraints);

        labelControl118.setLabel("Qtde Parcela Credito Pis:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        formEscrituracao.add(labelControl118, gridBagConstraints);

        numericControl23.setAttributeName("qtdeParcelaCreditoPis");
        numericControl23.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        formEscrituracao.add(numericControl23, gridBagConstraints);

        labelControl120.setLabel("Valor Bc Pis Creditado:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        formEscrituracao.add(labelControl120, gridBagConstraints);

        numericControl24.setAttributeName("valorBcPisCreditado");
        numericControl24.setDecimals(2);
        numericControl24.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        formEscrituracao.add(numericControl24, gridBagConstraints);

        labelControl121.setLabel("Valor Pis Creditado:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        formEscrituracao.add(labelControl121, gridBagConstraints);

        numericControl25.setAttributeName("valorPisCreditado");
        numericControl25.setDecimals(2);
        numericControl25.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        formEscrituracao.add(numericControl25, gridBagConstraints);

        labelControl122.setLabel("Cst Credito Cofins:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        formEscrituracao.add(labelControl122, gridBagConstraints);

        textControl61.setAttributeName("cstCreditoCofins");
        textControl61.setEnabled(false);
        textControl61.setMaxCharacters(2);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        formEscrituracao.add(textControl61, gridBagConstraints);

        labelControl123.setLabel("Valor Cofins Creditado:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        formEscrituracao.add(labelControl123, gridBagConstraints);

        numericControl27.setAttributeName("valorCofinsCreditado");
        numericControl27.setDecimals(2);
        numericControl27.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        formEscrituracao.add(numericControl27, gridBagConstraints);

        labelControl124.setLabel("Qtde Parcela Credito Cofins:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        formEscrituracao.add(labelControl124, gridBagConstraints);

        numericControl28.setAttributeName("qtdeParcelaCreditoCofins");
        numericControl28.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        formEscrituracao.add(numericControl28, gridBagConstraints);

        labelControl125.setLabel("Aliquota Credito Cofins:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        formEscrituracao.add(labelControl125, gridBagConstraints);

        numericControl55.setAttributeName("aliquotaCreditoCofins");
        numericControl55.setDecimals(2);
        numericControl55.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        formEscrituracao.add(numericControl55, gridBagConstraints);

        labelControl126.setLabel("Valor Bc Cofins Creditado:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        formEscrituracao.add(labelControl126, gridBagConstraints);

        numericControl56.setAttributeName("valorBcCofinsCreditado");
        numericControl56.setDecimals(2);
        numericControl56.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        formEscrituracao.add(numericControl56, gridBagConstraints);

        labelControl127.setLabel("Valor Ipi Creditado:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        formEscrituracao.add(labelControl127, gridBagConstraints);

        numericControl58.setAttributeName("valorIpiCreditado");
        numericControl58.setDecimals(2);
        numericControl58.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 9;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        formEscrituracao.add(numericControl58, gridBagConstraints);

        labelControl128.setLabel("Cst Credito Ipi:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        formEscrituracao.add(labelControl128, gridBagConstraints);

        textControl62.setAttributeName("cstCreditoIpi");
        textControl62.setEnabled(false);
        textControl62.setMaxCharacters(2);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 9;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        formEscrituracao.add(textControl62, gridBagConstraints);

        labelControl130.setLabel("Valor Bc Ipi Creditado:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        formEscrituracao.add(labelControl130, gridBagConstraints);

        numericControl60.setAttributeName("valorBcIpiCreditado");
        numericControl60.setDecimals(2);
        numericControl60.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 9;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        formEscrituracao.add(numericControl60, gridBagConstraints);

        labelControl132.setLabel("Qtde Parcela Credito Ipi:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        formEscrituracao.add(labelControl132, gridBagConstraints);

        numericControl61.setAttributeName("qtdeParcelaCreditoIpi");
        numericControl61.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 9;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        formEscrituracao.add(numericControl61, gridBagConstraints);

        labelControl133.setLabel("Aliquota Credito Ipi:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        formEscrituracao.add(labelControl133, gridBagConstraints);

        numericControl62.setAttributeName("aliquotaCreditoIpi");
        numericControl62.setDecimals(2);
        numericControl62.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 9;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        formEscrituracao.add(numericControl62, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.gridwidth = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        formEscrituracao.add(jSeparator6, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel33.add(formEscrituracao, gridBagConstraints);

        jTabbedPane2.addTab("Escrituração", jPanel33);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        getContentPane().add(jTabbedPane2, gridBagConstraints);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void genericButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_genericButton1ActionPerformed
        controller.importaNfe();
    }//GEN-LAST:event_genericButton1ActionPerformed

    private void genericButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_genericButton2ActionPerformed
        try {
            getProdutoController().cadastrarProduto();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Ocorreu um erro ao cadastrar o produto.\n" + e.getMessage(), "Erro do Sistema", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_genericButton2ActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private org.openswing.swing.table.columns.client.CheckBoxColumn checkBoxColumn1;
    private org.openswing.swing.table.columns.client.CodLookupColumn codLookupColumn1;
    private org.openswing.swing.client.CodLookupControl codLookupControl2;
    private org.openswing.swing.client.ComboBoxControl comboBoxControl10;
    private org.openswing.swing.client.ComboBoxControl comboBoxControl17;
    private org.openswing.swing.client.ComboBoxControl comboBoxControl18;
    private org.openswing.swing.client.ComboBoxControl comboBoxControl19;
    private org.openswing.swing.client.ComboBoxControl comboBoxControl20;
    private org.openswing.swing.client.ComboBoxControl comboBoxControl21;
    private org.openswing.swing.client.ComboBoxControl comboBoxControl24;
    private org.openswing.swing.client.ComboBoxControl comboBoxControl4;
    private org.openswing.swing.table.columns.client.ComboColumn comboColumn23;
    private org.openswing.swing.table.columns.client.DateColumn dateColumn4;
    private org.openswing.swing.table.columns.client.DateColumn dateColumn6;
    private org.openswing.swing.client.DateControl dateControl14;
    private org.openswing.swing.client.DateControl dateControl15;
    private org.openswing.swing.table.columns.client.DecimalColumn decimalColumn12;
    private org.openswing.swing.table.columns.client.DecimalColumn decimalColumn13;
    private org.openswing.swing.table.columns.client.DecimalColumn decimalColumn14;
    private org.openswing.swing.table.columns.client.DecimalColumn decimalColumn17;
    private org.openswing.swing.table.columns.client.DecimalColumn decimalColumn18;
    private org.openswing.swing.table.columns.client.DecimalColumn decimalColumn19;
    private org.openswing.swing.table.columns.client.DecimalColumn decimalColumn20;
    private org.openswing.swing.table.columns.client.DecimalColumn decimalColumn21;
    private org.openswing.swing.table.columns.client.DecimalColumn decimalColumn22;
    private org.openswing.swing.table.columns.client.DecimalColumn decimalColumn24;
    private org.openswing.swing.table.columns.client.DecimalColumn decimalColumn25;
    private org.openswing.swing.table.columns.client.DecimalColumn decimalColumn5;
    private org.openswing.swing.table.columns.client.DecimalColumn decimalColumn7;
    private org.openswing.swing.table.columns.client.DecimalColumn decimalColumn8;
    private org.openswing.swing.client.DeleteButton deleteButtonCteReferenciada;
    private org.openswing.swing.client.DeleteButton deleteButtonCupomFiscalReferenciado;
    private org.openswing.swing.client.DeleteButton deleteButtonDuplicata;
    private org.openswing.swing.client.DeleteButton deleteButtonNf1Referenciada;
    private org.openswing.swing.client.DeleteButton deleteButtonNfeReferenciada;
    private org.openswing.swing.client.DeleteButton deleteButtonProdRuralReferenciada;
    private org.openswing.swing.client.DeleteButton deleteButtonProduto;
    private org.openswing.swing.client.DeleteButton deleteButtonTransporteReboque;
    private org.openswing.swing.client.DeleteButton deleteButtonTransporteVolume;
    private org.openswing.swing.client.DeleteButton deleteButtonTransporteVolumeLacre;
    private org.openswing.swing.client.EditButton editButton1;
    private org.openswing.swing.client.EditButton editButtonCteReferenciada;
    private org.openswing.swing.client.EditButton editButtonCupomFiscalReferenciado;
    private org.openswing.swing.client.EditButton editButtonDuplicata;
    private org.openswing.swing.client.EditButton editButtonNf1Referenciada;
    private org.openswing.swing.client.EditButton editButtonNfeReferenciada;
    private org.openswing.swing.client.EditButton editButtonProdRuralReferenciada;
    private org.openswing.swing.client.EditButton editButtonProduto;
    private org.openswing.swing.client.EditButton editButtonTransporteReboque;
    private org.openswing.swing.client.EditButton editButtonTransporteVolume;
    private org.openswing.swing.client.EditButton editButtonTransporteVolumeLacre;
    private org.openswing.swing.form.client.Form formDadosEntrega;
    private org.openswing.swing.form.client.Form formDadosNfe;
    private org.openswing.swing.form.client.Form formDadosRetirada;
    private org.openswing.swing.form.client.Form formEmitente;
    private org.openswing.swing.form.client.Form formEscrituracao;
    private org.openswing.swing.form.client.Form formFatura;
    private org.openswing.swing.form.client.Form formTransporte;
    private org.openswing.swing.client.GenericButton genericButton1;
    private org.openswing.swing.client.GenericButton genericButton2;
    private org.openswing.swing.client.GridControl gridControlCteReferenciado;
    private org.openswing.swing.client.GridControl gridControlCupomFiscalReferenciado;
    private org.openswing.swing.client.GridControl gridControlDuplicata;
    private org.openswing.swing.client.GridControl gridControlNf1Referenciada;
    private org.openswing.swing.client.GridControl gridControlNfeReferenciada;
    private org.openswing.swing.client.GridControl gridControlProdRuralReferenciada;
    private org.openswing.swing.client.GridControl gridControlProduto;
    private org.openswing.swing.client.GridControl gridControlTransporteReboque;
    private org.openswing.swing.client.GridControl gridControlTransporteVolume;
    private org.openswing.swing.client.GridControl gridControlTransporteVolumeLacre;
    private org.openswing.swing.client.InsertButton insertButtonCteReferenciada;
    private org.openswing.swing.client.InsertButton insertButtonCupomFiscalReferenciado;
    private org.openswing.swing.client.InsertButton insertButtonDuplicata;
    private org.openswing.swing.client.InsertButton insertButtonNf1Referenciada;
    private org.openswing.swing.client.InsertButton insertButtonNfeReferenciada;
    private org.openswing.swing.client.InsertButton insertButtonProdRuralReferenciada;
    private org.openswing.swing.client.InsertButton insertButtonProduto;
    private org.openswing.swing.client.InsertButton insertButtonTransporteReboque;
    private org.openswing.swing.client.InsertButton insertButtonTransporteVolume;
    private org.openswing.swing.client.InsertButton insertButtonTransporteVolumeLacre;
    private org.openswing.swing.table.columns.client.IntegerColumn integerColumn10;
    private org.openswing.swing.table.columns.client.IntegerColumn integerColumn11;
    private org.openswing.swing.table.columns.client.IntegerColumn integerColumn27;
    private org.openswing.swing.table.columns.client.IntegerColumn integerColumn3;
    private org.openswing.swing.table.columns.client.IntegerColumn integerColumn4;
    private org.openswing.swing.table.columns.client.IntegerColumn integerColumn5;
    private org.openswing.swing.table.columns.client.IntegerColumn integerColumn6;
    private org.openswing.swing.table.columns.client.IntegerColumn integerColumn7;
    private org.openswing.swing.table.columns.client.IntegerColumn integerColumn8;
    private org.openswing.swing.table.columns.client.IntegerColumn integerColumn9;
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
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel24;
    private javax.swing.JPanel jPanel25;
    private javax.swing.JPanel jPanel26;
    private javax.swing.JPanel jPanel27;
    private javax.swing.JPanel jPanel28;
    private javax.swing.JPanel jPanel29;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel30;
    private javax.swing.JPanel jPanel31;
    private javax.swing.JPanel jPanel32;
    private javax.swing.JPanel jPanel33;
    private javax.swing.JPanel jPanel34;
    private javax.swing.JPanel jPanel35;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JTabbedPane jTabbedPane3;
    private javax.swing.JTabbedPane jTabbedPane4;
    private org.openswing.swing.client.LabelControl labelControl1;
    private org.openswing.swing.client.LabelControl labelControl10;
    private org.openswing.swing.client.LabelControl labelControl100;
    private org.openswing.swing.client.LabelControl labelControl101;
    private org.openswing.swing.client.LabelControl labelControl102;
    private org.openswing.swing.client.LabelControl labelControl103;
    private org.openswing.swing.client.LabelControl labelControl104;
    private org.openswing.swing.client.LabelControl labelControl105;
    private org.openswing.swing.client.LabelControl labelControl106;
    private org.openswing.swing.client.LabelControl labelControl107;
    private org.openswing.swing.client.LabelControl labelControl108;
    private org.openswing.swing.client.LabelControl labelControl109;
    private org.openswing.swing.client.LabelControl labelControl11;
    private org.openswing.swing.client.LabelControl labelControl110;
    private org.openswing.swing.client.LabelControl labelControl111;
    private org.openswing.swing.client.LabelControl labelControl112;
    private org.openswing.swing.client.LabelControl labelControl113;
    private org.openswing.swing.client.LabelControl labelControl114;
    private org.openswing.swing.client.LabelControl labelControl115;
    private org.openswing.swing.client.LabelControl labelControl116;
    private org.openswing.swing.client.LabelControl labelControl117;
    private org.openswing.swing.client.LabelControl labelControl118;
    private org.openswing.swing.client.LabelControl labelControl119;
    private org.openswing.swing.client.LabelControl labelControl12;
    private org.openswing.swing.client.LabelControl labelControl120;
    private org.openswing.swing.client.LabelControl labelControl121;
    private org.openswing.swing.client.LabelControl labelControl122;
    private org.openswing.swing.client.LabelControl labelControl123;
    private org.openswing.swing.client.LabelControl labelControl124;
    private org.openswing.swing.client.LabelControl labelControl125;
    private org.openswing.swing.client.LabelControl labelControl126;
    private org.openswing.swing.client.LabelControl labelControl127;
    private org.openswing.swing.client.LabelControl labelControl128;
    private org.openswing.swing.client.LabelControl labelControl129;
    private org.openswing.swing.client.LabelControl labelControl13;
    private org.openswing.swing.client.LabelControl labelControl130;
    private org.openswing.swing.client.LabelControl labelControl131;
    private org.openswing.swing.client.LabelControl labelControl132;
    private org.openswing.swing.client.LabelControl labelControl133;
    private org.openswing.swing.client.LabelControl labelControl134;
    private org.openswing.swing.client.LabelControl labelControl135;
    private org.openswing.swing.client.LabelControl labelControl136;
    private org.openswing.swing.client.LabelControl labelControl137;
    private org.openswing.swing.client.LabelControl labelControl138;
    private org.openswing.swing.client.LabelControl labelControl139;
    private org.openswing.swing.client.LabelControl labelControl14;
    private org.openswing.swing.client.LabelControl labelControl140;
    private org.openswing.swing.client.LabelControl labelControl141;
    private org.openswing.swing.client.LabelControl labelControl142;
    private org.openswing.swing.client.LabelControl labelControl143;
    private org.openswing.swing.client.LabelControl labelControl144;
    private org.openswing.swing.client.LabelControl labelControl145;
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
    private org.openswing.swing.client.LabelControl labelControl5;
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
    private org.openswing.swing.client.NavigatorBar navigatorBarCteReferenciada;
    private org.openswing.swing.client.NavigatorBar navigatorBarCupomFiscalReferenciado;
    private org.openswing.swing.client.NavigatorBar navigatorBarDuplicata;
    private org.openswing.swing.client.NavigatorBar navigatorBarNf1Referenciada;
    private org.openswing.swing.client.NavigatorBar navigatorBarNfeReferenciada;
    private org.openswing.swing.client.NavigatorBar navigatorBarProdRuralReferenciada;
    private org.openswing.swing.client.NavigatorBar navigatorBarProduto;
    private org.openswing.swing.client.NavigatorBar navigatorBarTransporteReboque;
    private org.openswing.swing.client.NavigatorBar navigatorBarTransporteVolume;
    private org.openswing.swing.client.NavigatorBar navigatorBarTransporteVolumeLacre;
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
    private org.openswing.swing.client.NumericControl numericControl5;
    private org.openswing.swing.client.NumericControl numericControl55;
    private org.openswing.swing.client.NumericControl numericControl56;
    private org.openswing.swing.client.NumericControl numericControl57;
    private org.openswing.swing.client.NumericControl numericControl58;
    private org.openswing.swing.client.NumericControl numericControl59;
    private org.openswing.swing.client.NumericControl numericControl6;
    private org.openswing.swing.client.NumericControl numericControl60;
    private org.openswing.swing.client.NumericControl numericControl61;
    private org.openswing.swing.client.NumericControl numericControl62;
    private org.openswing.swing.client.NumericControl numericControl63;
    private org.openswing.swing.client.NumericControl numericControl64;
    private org.openswing.swing.client.NumericControl numericControl65;
    private org.openswing.swing.client.NumericControl numericControl66;
    private org.openswing.swing.client.NumericControl numericControl67;
    private org.openswing.swing.client.NumericControl numericControl68;
    private org.openswing.swing.client.NumericControl numericControl69;
    private org.openswing.swing.client.NumericControl numericControl7;
    private org.openswing.swing.client.NumericControl numericControl70;
    private org.openswing.swing.client.NumericControl numericControl71;
    private org.openswing.swing.client.NumericControl numericControl72;
    private org.openswing.swing.client.NumericControl numericControl73;
    private org.openswing.swing.client.NumericControl numericControl74;
    private org.openswing.swing.client.NumericControl numericControl8;
    private org.openswing.swing.client.NumericControl numericControl9;
    private org.openswing.swing.client.ReloadButton reloadButton1;
    private org.openswing.swing.client.ReloadButton reloadButtonCteReferenciada;
    private org.openswing.swing.client.ReloadButton reloadButtonCupomFiscalReferenciado;
    private org.openswing.swing.client.ReloadButton reloadButtonDuplicata;
    private org.openswing.swing.client.ReloadButton reloadButtonNf1Referenciada;
    private org.openswing.swing.client.ReloadButton reloadButtonNfeReferenciada;
    private org.openswing.swing.client.ReloadButton reloadButtonProdRuralReferenciada;
    private org.openswing.swing.client.ReloadButton reloadButtonProduto;
    private org.openswing.swing.client.ReloadButton reloadButtonTransporteReboque;
    private org.openswing.swing.client.ReloadButton reloadButtonTransporteVolume;
    private org.openswing.swing.client.ReloadButton reloadButtonTransporteVolumeLacre;
    private org.openswing.swing.client.SaveButton saveButton1;
    private org.openswing.swing.client.SaveButton saveButtonCteReferenciada;
    private org.openswing.swing.client.SaveButton saveButtonCupomFiscalReferenciado;
    private org.openswing.swing.client.SaveButton saveButtonDuplicata;
    private org.openswing.swing.client.SaveButton saveButtonNf1Referenciada;
    private org.openswing.swing.client.SaveButton saveButtonNfeReferenciada;
    private org.openswing.swing.client.SaveButton saveButtonProdRuralReferenciada;
    private org.openswing.swing.client.SaveButton saveButtonProduto;
    private org.openswing.swing.client.SaveButton saveButtonTransporteReboque;
    private org.openswing.swing.client.SaveButton saveButtonTransporteVolume;
    private org.openswing.swing.client.SaveButton saveButtonTransporteVolumeLacre;
    private org.openswing.swing.client.TextAreaControl textAreaControl1;
    private org.openswing.swing.client.TextAreaControl textAreaControl2;
    private org.openswing.swing.table.columns.client.TextColumn textColumn10;
    private org.openswing.swing.table.columns.client.TextColumn textColumn11;
    private org.openswing.swing.table.columns.client.TextColumn textColumn12;
    private org.openswing.swing.table.columns.client.TextColumn textColumn13;
    private org.openswing.swing.table.columns.client.TextColumn textColumn14;
    private org.openswing.swing.table.columns.client.TextColumn textColumn15;
    private org.openswing.swing.table.columns.client.TextColumn textColumn16;
    private org.openswing.swing.table.columns.client.TextColumn textColumn17;
    private org.openswing.swing.table.columns.client.TextColumn textColumn18;
    private org.openswing.swing.table.columns.client.TextColumn textColumn19;
    private org.openswing.swing.table.columns.client.TextColumn textColumn20;
    private org.openswing.swing.table.columns.client.TextColumn textColumn21;
    private org.openswing.swing.table.columns.client.TextColumn textColumn22;
    private org.openswing.swing.table.columns.client.TextColumn textColumn23;
    private org.openswing.swing.table.columns.client.TextColumn textColumn24;
    private org.openswing.swing.table.columns.client.TextColumn textColumn25;
    private org.openswing.swing.table.columns.client.TextColumn textColumn26;
    private org.openswing.swing.table.columns.client.TextColumn textColumn27;
    private org.openswing.swing.table.columns.client.TextColumn textColumn28;
    private org.openswing.swing.table.columns.client.TextColumn textColumn29;
    private org.openswing.swing.table.columns.client.TextColumn textColumn3;
    private org.openswing.swing.table.columns.client.TextColumn textColumn30;
    private org.openswing.swing.table.columns.client.TextColumn textColumn31;
    private org.openswing.swing.table.columns.client.TextColumn textColumn32;
    private org.openswing.swing.table.columns.client.TextColumn textColumn33;
    private org.openswing.swing.table.columns.client.TextColumn textColumn34;
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
    private org.openswing.swing.client.TextControl textControl33;
    private org.openswing.swing.client.TextControl textControl34;
    private org.openswing.swing.client.TextControl textControl35;
    private org.openswing.swing.client.TextControl textControl36;
    private org.openswing.swing.client.TextControl textControl37;
    private org.openswing.swing.client.TextControl textControl38;
    private org.openswing.swing.client.TextControl textControl39;
    private org.openswing.swing.client.TextControl textControl4;
    private org.openswing.swing.client.TextControl textControl40;
    private org.openswing.swing.client.TextControl textControl41;
    private org.openswing.swing.client.TextControl textControl42;
    private org.openswing.swing.client.TextControl textControl43;
    private org.openswing.swing.client.TextControl textControl44;
    private org.openswing.swing.client.TextControl textControl45;
    private org.openswing.swing.client.TextControl textControl46;
    private org.openswing.swing.client.TextControl textControl47;
    private org.openswing.swing.client.TextControl textControl48;
    private org.openswing.swing.client.TextControl textControl49;
    private org.openswing.swing.client.TextControl textControl5;
    private org.openswing.swing.client.TextControl textControl50;
    private org.openswing.swing.client.TextControl textControl51;
    private org.openswing.swing.client.TextControl textControl52;
    private org.openswing.swing.client.TextControl textControl53;
    private org.openswing.swing.client.TextControl textControl54;
    private org.openswing.swing.client.TextControl textControl55;
    private org.openswing.swing.client.TextControl textControl56;
    private org.openswing.swing.client.TextControl textControl57;
    private org.openswing.swing.client.TextControl textControl58;
    private org.openswing.swing.client.TextControl textControl59;
    private org.openswing.swing.client.TextControl textControl6;
    private org.openswing.swing.client.TextControl textControl60;
    private org.openswing.swing.client.TextControl textControl61;
    private org.openswing.swing.client.TextControl textControl62;
    private org.openswing.swing.client.TextControl textControl7;
    private org.openswing.swing.client.TextControl textControl8;
    private org.openswing.swing.client.TextControl textControl9;
    // End of variables declaration//GEN-END:variables
}
