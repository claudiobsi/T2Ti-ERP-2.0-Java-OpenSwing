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
import com.t2tierp.ponto.java.PontoClassificacaoJornadaVO;
import com.t2tierp.ponto.java.PontoFechamentoJornadaVO;
import java.awt.Dimension;
import java.util.Date;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.text.MaskFormatter;
import org.openswing.swing.client.GenericButton;
import org.openswing.swing.form.client.FormController;
import org.openswing.swing.lookup.client.LookupController;
import org.openswing.swing.mdi.client.InternalFrame;
import org.openswing.swing.util.client.ClientSettings;
import org.openswing.swing.util.client.ClientUtils;

public class PontoEspelhoDetalhe extends InternalFrame {

    private PontoEspelhoDetalheController controller;
    private LookupController classificacaoJornadaController = new LookupController();

    public PontoEspelhoDetalhe(PontoEspelhoDetalheController controller) {
        initComponents();

        formColaborador.setFormController(new FormController());

        formClassificacaoJornada.setFormController(new FormController());

        this.controller = controller;

        gridControlFechamento.setController(controller);
        gridControlFechamento.setGridDataLocator(controller);

        try {
            MaskFormatter mask = new MaskFormatter("##/####");
            mask.setValidCharacters("0123456789");
            mask.install(txtPeriodo);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        genericButton1.setToolTipText("Classificar Dia");
        genericButton2.setToolTipText("Gerar relatório");

        /*
         * Configurações do lookup da classificação da jornada
         */
        classificacaoJornadaController.setLookupValueObjectClassName("com.t2tierp.ponto.java.PontoClassificacaoJornadaVO");
        classificacaoJornadaController.addLookup2ParentLink("id", "pontoClassificacaoJornada.id");
        classificacaoJornadaController.addLookup2ParentLink("nome", "pontoClassificacaoJornada.nome");
        classificacaoJornadaController.addLookup2ParentLink("codigo", "pontoClassificacaoJornada.codigo");
        classificacaoJornadaController.addLookup2ParentLink("padrao", "pontoClassificacaoJornada.padrao");
        classificacaoJornadaController.addLookup2ParentLink("descontarHoras", "pontoClassificacaoJornada.descontarHoras");
        classificacaoJornadaController.setHeaderColumnName("id", "ID");
        classificacaoJornadaController.setHeaderColumnName("nome", "Nome");
        classificacaoJornadaController.setHeaderColumnName("codigo", "Código");
        classificacaoJornadaController.setHeaderColumnName("padrao", "Padrão");
        classificacaoJornadaController.setHeaderColumnName("descontarHoras", "Descontar Horas");
        classificacaoJornadaController.setFrameTitle("Importa Classificação Jornada");

        classificacaoJornadaController.setVisibleStatusPanel(true);
        classificacaoJornadaController.setVisibleColumn("id", true);
        classificacaoJornadaController.setVisibleColumn("nome", true);
        classificacaoJornadaController.setVisibleColumn("codigo", true);
        classificacaoJornadaController.setVisibleColumn("padrao", true);
        classificacaoJornadaController.setVisibleColumn("descontarHoras", true);
        classificacaoJornadaController.setFramePreferedSize(new Dimension(600, 500));

        classificacaoJornadaController.setLookupDataLocator(new LookupDataLocatorGenerico(classificacaoJornadaController.getLookupValueObjectClassName()));
        codLookupControl1.setLookupController(classificacaoJornadaController);
    }

    public String getPeriodo() throws Exception {
        txtPeriodo.commitEdit();
        return txtPeriodo.getText();
    }

    /**
     * @return the formColaborador
     */
    public org.openswing.swing.form.client.Form getFormColaborador() {
        return formColaborador;
    }

    /**
     * @return the gridControl1
     */
    public org.openswing.swing.client.GridControl getGridControlFechamento() {
        return gridControlFechamento;
    }

    public Date getDataClassificar() {
        return dateControl1.getDate();
    }

    public PontoClassificacaoJornadaVO getClassificacao() {
        return ((PontoFechamentoJornadaVO) formClassificacaoJornada.getVOModel().getValueObject()).getPontoClassificacaoJornada();
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
        reloadButton1 = new org.openswing.swing.client.ReloadButton();
        txtPeriodo = new javax.swing.JFormattedTextField();
        genericButton2 = new GenericButton(new ImageIcon(ClientUtils.getImage(ClientSettings.BUTTON_IMPORT_IMAGE_NAME)));
        labelControl5 = new org.openswing.swing.client.LabelControl();
        formColaborador = new org.openswing.swing.form.client.Form();
        textControl1 = new org.openswing.swing.client.TextControl();
        labelControl7 = new org.openswing.swing.client.LabelControl();
        jPanel2 = new javax.swing.JPanel();
        gridControlFechamento = new org.openswing.swing.client.GridControl();
        dateColumn4 = new org.openswing.swing.table.columns.client.DateColumn();
        textColumn10 = new org.openswing.swing.table.columns.client.TextColumn();
        textColumn11 = new org.openswing.swing.table.columns.client.TextColumn();
        textColumn12 = new org.openswing.swing.table.columns.client.TextColumn();
        textColumn13 = new org.openswing.swing.table.columns.client.TextColumn();
        textColumn14 = new org.openswing.swing.table.columns.client.TextColumn();
        textColumn15 = new org.openswing.swing.table.columns.client.TextColumn();
        textColumn16 = new org.openswing.swing.table.columns.client.TextColumn();
        textColumn17 = new org.openswing.swing.table.columns.client.TextColumn();
        textColumn18 = new org.openswing.swing.table.columns.client.TextColumn();
        textColumn19 = new org.openswing.swing.table.columns.client.TextColumn();
        textColumn20 = new org.openswing.swing.table.columns.client.TextColumn();
        textColumn21 = new org.openswing.swing.table.columns.client.TextColumn();
        textColumn22 = new org.openswing.swing.table.columns.client.TextColumn();
        textColumn23 = new org.openswing.swing.table.columns.client.TextColumn();
        textColumn24 = new org.openswing.swing.table.columns.client.TextColumn();
        textColumn25 = new org.openswing.swing.table.columns.client.TextColumn();
        textColumn26 = new org.openswing.swing.table.columns.client.TextColumn();
        textColumn27 = new org.openswing.swing.table.columns.client.TextColumn();
        textColumn28 = new org.openswing.swing.table.columns.client.TextColumn();
        decimalColumn24 = new org.openswing.swing.table.columns.client.DecimalColumn();
        comboColumn25 = new org.openswing.swing.table.columns.client.ComboColumn();
        textColumn29 = new org.openswing.swing.table.columns.client.TextColumn();
        decimalColumn27 = new org.openswing.swing.table.columns.client.DecimalColumn();
        comboColumn28 = new org.openswing.swing.table.columns.client.ComboColumn();
        textColumn30 = new org.openswing.swing.table.columns.client.TextColumn();
        decimalColumn30 = new org.openswing.swing.table.columns.client.DecimalColumn();
        comboColumn31 = new org.openswing.swing.table.columns.client.ComboColumn();
        textColumn32 = new org.openswing.swing.table.columns.client.TextColumn();
        decimalColumn33 = new org.openswing.swing.table.columns.client.DecimalColumn();
        comboColumn34 = new org.openswing.swing.table.columns.client.ComboColumn();
        textColumn35 = new org.openswing.swing.table.columns.client.TextColumn();
        comboColumn36 = new org.openswing.swing.table.columns.client.ComboColumn();
        textColumn37 = new org.openswing.swing.table.columns.client.TextColumn();
        textColumn38 = new org.openswing.swing.table.columns.client.TextColumn();
        jPanel3 = new javax.swing.JPanel();
        labelControl1 = new org.openswing.swing.client.LabelControl();
        dateControl1 = new org.openswing.swing.client.DateControl();
        genericButton1 = new GenericButton(new ImageIcon(ClientUtils.getImage("calculadora.png")));
        formClassificacaoJornada = new org.openswing.swing.form.client.Form();
        labelControl2 = new org.openswing.swing.client.LabelControl();
        codLookupControl1 = new org.openswing.swing.client.CodLookupControl();
        textControl2 = new org.openswing.swing.client.TextControl();

        setTitle("T2Ti ERP - Ponto Eletrônico");
        setPreferredSize(new java.awt.Dimension(700, 400));
        getContentPane().setLayout(new java.awt.GridBagLayout());

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Ponto Espelho"));
        jPanel1.setLayout(new java.awt.GridBagLayout());
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel1.add(reloadButton1, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints.ipadx = 50;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        jPanel1.add(txtPeriodo, gridBagConstraints);

        genericButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                genericButton2ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel1.add(genericButton2, gridBagConstraints);

        labelControl5.setText("Periodo:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        jPanel1.add(labelControl5, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        getContentPane().add(jPanel1, gridBagConstraints);

        formColaborador.setVOClassName("com.t2tierp.cadastros.java.ColaboradorVO");
        formColaborador.setLayout(new java.awt.GridBagLayout());

        textControl1.setAttributeName("pessoa.nome");
        textControl1.setEnabled(false);
        textControl1.setEnabledOnEdit(false);
        textControl1.setEnabledOnInsert(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        formColaborador.add(textControl1, gridBagConstraints);

        labelControl7.setLabel("Colaborador:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        formColaborador.add(labelControl7, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        getContentPane().add(formColaborador, gridBagConstraints);

        jPanel2.setLayout(new java.awt.GridBagLayout());

        gridControlFechamento.setAutoLoadData(false);
        gridControlFechamento.setFunctionId("pontoFechamentoJornada");
        gridControlFechamento.setReloadButton(reloadButton1);
        gridControlFechamento.setValueObjectClassName("com.t2tierp.ponto.java.PontoFechamentoJornadaVO");
        gridControlFechamento.getColumnContainer().setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        dateColumn4.setColumnName("dataFechamento");
        dateColumn4.setHeaderColumnName("Data Fechamento");
        dateColumn4.setHeaderFont(new java.awt.Font("Arial", 1, 11));
        gridControlFechamento.getColumnContainer().add(dateColumn4);

        textColumn10.setColumnName("diaSemana");
        textColumn10.setHeaderColumnName("Dia Semana");
        textColumn10.setHeaderFont(new java.awt.Font("Arial", 1, 11));
        gridControlFechamento.getColumnContainer().add(textColumn10);

        textColumn11.setColumnName("codigoHorario");
        textColumn11.setHeaderColumnName("Codigo Horario");
        textColumn11.setHeaderFont(new java.awt.Font("Arial", 1, 11));
        gridControlFechamento.getColumnContainer().add(textColumn11);

        textColumn12.setColumnName("cargaHorariaEsperada");
        textColumn12.setHeaderColumnName("Carga Horaria Esperada");
        textColumn12.setHeaderFont(new java.awt.Font("Arial", 1, 11));
        gridControlFechamento.getColumnContainer().add(textColumn12);

        textColumn13.setColumnName("cargaHorariaDiurna");
        textColumn13.setHeaderColumnName("Carga Horaria Diurna");
        textColumn13.setHeaderFont(new java.awt.Font("Arial", 1, 11));
        gridControlFechamento.getColumnContainer().add(textColumn13);

        textColumn14.setColumnName("cargaHorariaNoturna");
        textColumn14.setHeaderColumnName("Carga Horaria Noturna");
        textColumn14.setHeaderFont(new java.awt.Font("Arial", 1, 11));
        gridControlFechamento.getColumnContainer().add(textColumn14);

        textColumn15.setColumnName("cargaHorariaTotal");
        textColumn15.setHeaderColumnName("Carga Horaria Total");
        textColumn15.setHeaderFont(new java.awt.Font("Arial", 1, 11));
        gridControlFechamento.getColumnContainer().add(textColumn15);

        textColumn16.setColumnName("entrada01");
        textColumn16.setHeaderColumnName("Entrada01");
        textColumn16.setHeaderFont(new java.awt.Font("Arial", 1, 11));
        gridControlFechamento.getColumnContainer().add(textColumn16);

        textColumn17.setColumnName("saida01");
        textColumn17.setHeaderColumnName("Saida01");
        textColumn17.setHeaderFont(new java.awt.Font("Arial", 1, 11));
        gridControlFechamento.getColumnContainer().add(textColumn17);

        textColumn18.setColumnName("entrada02");
        textColumn18.setHeaderColumnName("Entrada02");
        textColumn18.setHeaderFont(new java.awt.Font("Arial", 1, 11));
        gridControlFechamento.getColumnContainer().add(textColumn18);

        textColumn19.setColumnName("saida02");
        textColumn19.setHeaderColumnName("Saida02");
        textColumn19.setHeaderFont(new java.awt.Font("Arial", 1, 11));
        gridControlFechamento.getColumnContainer().add(textColumn19);

        textColumn20.setColumnName("entrada03");
        textColumn20.setHeaderColumnName("Entrada03");
        textColumn20.setHeaderFont(new java.awt.Font("Arial", 1, 11));
        gridControlFechamento.getColumnContainer().add(textColumn20);

        textColumn21.setColumnName("saida03");
        textColumn21.setHeaderColumnName("Saida03");
        textColumn21.setHeaderFont(new java.awt.Font("Arial", 1, 11));
        gridControlFechamento.getColumnContainer().add(textColumn21);

        textColumn22.setColumnName("entrada04");
        textColumn22.setHeaderColumnName("Entrada04");
        textColumn22.setHeaderFont(new java.awt.Font("Arial", 1, 11));
        gridControlFechamento.getColumnContainer().add(textColumn22);

        textColumn23.setColumnName("saida04");
        textColumn23.setHeaderColumnName("Saida04");
        textColumn23.setHeaderFont(new java.awt.Font("Arial", 1, 11));
        gridControlFechamento.getColumnContainer().add(textColumn23);

        textColumn24.setColumnName("entrada05");
        textColumn24.setHeaderColumnName("Entrada05");
        textColumn24.setHeaderFont(new java.awt.Font("Arial", 1, 11));
        gridControlFechamento.getColumnContainer().add(textColumn24);

        textColumn25.setColumnName("saida05");
        textColumn25.setHeaderColumnName("Saida05");
        textColumn25.setHeaderFont(new java.awt.Font("Arial", 1, 11));
        gridControlFechamento.getColumnContainer().add(textColumn25);

        textColumn26.setColumnName("horaInicioJornada");
        textColumn26.setHeaderColumnName("Hora Inicio Jornada");
        textColumn26.setHeaderFont(new java.awt.Font("Arial", 1, 11));
        gridControlFechamento.getColumnContainer().add(textColumn26);

        textColumn27.setColumnName("horaFimJornada");
        textColumn27.setHeaderColumnName("Hora Fim Jornada");
        textColumn27.setHeaderFont(new java.awt.Font("Arial", 1, 11));
        gridControlFechamento.getColumnContainer().add(textColumn27);

        textColumn28.setColumnName("horaExtra01");
        textColumn28.setHeaderColumnName("Hora Extra01");
        textColumn28.setHeaderFont(new java.awt.Font("Arial", 1, 11));
        gridControlFechamento.getColumnContainer().add(textColumn28);

        decimalColumn24.setColumnName("percentualHoraExtra01");
        decimalColumn24.setHeaderColumnName("Percentual Hora Extra01");
        decimalColumn24.setHeaderFont(new java.awt.Font("Arial", 1, 11));
        decimalColumn24.setDecimals(2);
        gridControlFechamento.getColumnContainer().add(decimalColumn24);

        comboColumn25.setColumnName("modalidadeHoraExtra01");
        comboColumn25.setDomainId("pontoModalidadeHoraExtra");
        comboColumn25.setHeaderColumnName("Modalidade Hora Extra01");
        comboColumn25.setHeaderFont(new java.awt.Font("Arial", 1, 11));
        gridControlFechamento.getColumnContainer().add(comboColumn25);

        textColumn29.setColumnName("horaExtra02");
        textColumn29.setHeaderColumnName("Hora Extra02");
        textColumn29.setHeaderFont(new java.awt.Font("Arial", 1, 11));
        gridControlFechamento.getColumnContainer().add(textColumn29);

        decimalColumn27.setColumnName("percentualHoraExtra02");
        decimalColumn27.setHeaderColumnName("Percentual Hora Extra02");
        decimalColumn27.setHeaderFont(new java.awt.Font("Arial", 1, 11));
        decimalColumn27.setDecimals(2);
        gridControlFechamento.getColumnContainer().add(decimalColumn27);

        comboColumn28.setColumnName("modalidadeHoraExtra02");
        comboColumn28.setDomainId("pontoModalidadeHoraExtra");
        comboColumn28.setHeaderColumnName("Modalidade Hora Extra02");
        comboColumn28.setHeaderFont(new java.awt.Font("Arial", 1, 11));
        gridControlFechamento.getColumnContainer().add(comboColumn28);

        textColumn30.setColumnName("horaExtra03");
        textColumn30.setHeaderColumnName("Hora Extra03");
        textColumn30.setHeaderFont(new java.awt.Font("Arial", 1, 11));
        gridControlFechamento.getColumnContainer().add(textColumn30);

        decimalColumn30.setColumnName("percentualHoraExtra03");
        decimalColumn30.setHeaderColumnName("Percentual Hora Extra03");
        decimalColumn30.setHeaderFont(new java.awt.Font("Arial", 1, 11));
        decimalColumn30.setDecimals(2);
        gridControlFechamento.getColumnContainer().add(decimalColumn30);

        comboColumn31.setColumnName("modalidadeHoraExtra03");
        comboColumn31.setDomainId("pontoModalidadeHoraExtra");
        comboColumn31.setHeaderColumnName("Modalidade Hora Extra03");
        comboColumn31.setHeaderFont(new java.awt.Font("Arial", 1, 11));
        gridControlFechamento.getColumnContainer().add(comboColumn31);

        textColumn32.setColumnName("horaExtra04");
        textColumn32.setHeaderColumnName("Hora Extra04");
        textColumn32.setHeaderFont(new java.awt.Font("Arial", 1, 11));
        gridControlFechamento.getColumnContainer().add(textColumn32);

        decimalColumn33.setColumnName("percentualHoraExtra04");
        decimalColumn33.setHeaderColumnName("Percentual Hora Extra04");
        decimalColumn33.setHeaderFont(new java.awt.Font("Arial", 1, 11));
        decimalColumn33.setDecimals(2);
        gridControlFechamento.getColumnContainer().add(decimalColumn33);

        comboColumn34.setColumnName("modalidadeHoraExtra04");
        comboColumn34.setDomainId("pontoModalidadeHoraExtra");
        comboColumn34.setHeaderColumnName("Modalidade Hora Extra04");
        comboColumn34.setHeaderFont(new java.awt.Font("Arial", 1, 11));
        gridControlFechamento.getColumnContainer().add(comboColumn34);

        textColumn35.setColumnName("faltaAtraso");
        textColumn35.setHeaderColumnName("Falta Atraso");
        textColumn35.setHeaderFont(new java.awt.Font("Arial", 1, 11));
        gridControlFechamento.getColumnContainer().add(textColumn35);

        comboColumn36.setColumnName("compensar");
        comboColumn36.setDomainId("pontoHoraCompensar");
        comboColumn36.setHeaderColumnName("Compensar");
        comboColumn36.setHeaderFont(new java.awt.Font("Arial", 1, 11));
        gridControlFechamento.getColumnContainer().add(comboColumn36);

        textColumn37.setColumnName("bancoHoras");
        textColumn37.setHeaderColumnName("Banco Horas");
        textColumn37.setHeaderFont(new java.awt.Font("Arial", 1, 11));
        gridControlFechamento.getColumnContainer().add(textColumn37);

        textColumn38.setColumnName("observacao");
        textColumn38.setHeaderColumnName("Observacao");
        textColumn38.setHeaderFont(new java.awt.Font("Arial", 1, 11));
        gridControlFechamento.getColumnContainer().add(textColumn38);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel2.add(gridControlFechamento, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        getContentPane().add(jPanel2, gridBagConstraints);

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Classificação da Jornada"));
        jPanel3.setLayout(new java.awt.GridBagLayout());

        labelControl1.setText("Data Classificar:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        jPanel3.add(labelControl1, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        jPanel3.add(dateControl1, gridBagConstraints);

        genericButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                genericButton1ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel3.add(genericButton1, gridBagConstraints);

        formClassificacaoJornada.setVOClassName("com.t2tierp.ponto.java.PontoFechamentoJornadaVO");
        formClassificacaoJornada.setLayout(new java.awt.GridBagLayout());

        labelControl2.setText("Classificação:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        formClassificacaoJornada.add(labelControl2, gridBagConstraints);

        codLookupControl1.setAllowOnlyNumbers(true);
        codLookupControl1.setAttributeName("pontoClassificacaoJornada.id");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.ipadx = -70;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        formClassificacaoJornada.add(codLookupControl1, gridBagConstraints);

        textControl2.setAttributeName("pontoClassificacaoJornada.nome");
        textControl2.setEnabled(false);
        textControl2.setEnabledOnEdit(false);
        textControl2.setEnabledOnInsert(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        formClassificacaoJornada.add(textControl2, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        jPanel3.add(formClassificacaoJornada, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        getContentPane().add(jPanel3, gridBagConstraints);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void genericButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_genericButton1ActionPerformed
        controller.classificarDia();
    }//GEN-LAST:event_genericButton1ActionPerformed

    private void genericButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_genericButton2ActionPerformed
        try {
            controller.geraRelatório();
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Ocorreu um erro ao gerar o relatório!\n" + ex.getMessage(), "Erro do Sistema", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_genericButton2ActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private org.openswing.swing.client.CodLookupControl codLookupControl1;
    private org.openswing.swing.table.columns.client.ComboColumn comboColumn25;
    private org.openswing.swing.table.columns.client.ComboColumn comboColumn28;
    private org.openswing.swing.table.columns.client.ComboColumn comboColumn31;
    private org.openswing.swing.table.columns.client.ComboColumn comboColumn34;
    private org.openswing.swing.table.columns.client.ComboColumn comboColumn36;
    private org.openswing.swing.table.columns.client.DateColumn dateColumn4;
    private org.openswing.swing.client.DateControl dateControl1;
    private org.openswing.swing.table.columns.client.DecimalColumn decimalColumn24;
    private org.openswing.swing.table.columns.client.DecimalColumn decimalColumn27;
    private org.openswing.swing.table.columns.client.DecimalColumn decimalColumn30;
    private org.openswing.swing.table.columns.client.DecimalColumn decimalColumn33;
    private org.openswing.swing.form.client.Form formClassificacaoJornada;
    private org.openswing.swing.form.client.Form formColaborador;
    private org.openswing.swing.client.GenericButton genericButton1;
    private org.openswing.swing.client.GenericButton genericButton2;
    private org.openswing.swing.client.GridControl gridControlFechamento;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private org.openswing.swing.client.LabelControl labelControl1;
    private org.openswing.swing.client.LabelControl labelControl2;
    private org.openswing.swing.client.LabelControl labelControl5;
    private org.openswing.swing.client.LabelControl labelControl7;
    private org.openswing.swing.client.ReloadButton reloadButton1;
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
    private org.openswing.swing.table.columns.client.TextColumn textColumn30;
    private org.openswing.swing.table.columns.client.TextColumn textColumn32;
    private org.openswing.swing.table.columns.client.TextColumn textColumn35;
    private org.openswing.swing.table.columns.client.TextColumn textColumn37;
    private org.openswing.swing.table.columns.client.TextColumn textColumn38;
    private org.openswing.swing.client.TextControl textControl1;
    private org.openswing.swing.client.TextControl textControl2;
    private javax.swing.JFormattedTextField txtPeriodo;
    // End of variables declaration//GEN-END:variables
}
