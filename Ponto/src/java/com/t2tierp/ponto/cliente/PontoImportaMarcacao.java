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
import com.t2tierp.padrao.java.Biblioteca;
import java.awt.Dimension;
import java.text.ParseException;
import javax.swing.ImageIcon;
import javax.swing.JFormattedTextField;
import org.openswing.swing.client.GenericButton;
import org.openswing.swing.client.GridControl;
import org.openswing.swing.lookup.client.LookupController;
import org.openswing.swing.mdi.client.InternalFrame;
import org.openswing.swing.util.client.ClientSettings;
import org.openswing.swing.util.client.ClientUtils;

public class PontoImportaMarcacao extends InternalFrame {

    private PontoImportaMarcacaoController importaArquivoController;
    private PontoProcessaFechamentoJornadaController fechamentoController;
    private PontoIncluiMarcacaoGridController marcacaoController;
    private LookupController colaboradorController = new LookupController();

    public PontoImportaMarcacao(PontoImportaMarcacaoController importaArquivoController) {
        initComponents();

        this.importaArquivoController = importaArquivoController;

        gridControlImportaArquivo.setController(importaArquivoController);
        gridControlImportaArquivo.setGridDataLocator(importaArquivoController);

        fechamentoController = new PontoProcessaFechamentoJornadaController(this);
        gridControlFechamento.setController(fechamentoController);
        gridControlFechamento.setGridDataLocator(fechamentoController);

        marcacaoController = new PontoIncluiMarcacaoGridController();
        gridControlMarcacao.setController(marcacaoController);
        gridControlMarcacao.setGridDataLocator(marcacaoController);

        genericButton1.setToolTipText("Importar Arquivo AFD");
        genericButton2.setToolTipText("Processar Fechamento");
        genericButton3.setToolTipText("Gravar Fechamento");

        JFormattedTextField.AbstractFormatter formatter = new JFormattedTextField.AbstractFormatter() {

            public Object stringToValue(String text) throws ParseException {
                return valueToString(text);
            }

            public String valueToString(Object value) throws ParseException {
                if (value == null) {
                    return null;
                }
                String t = value.toString();
                if ((t.length() != 6) && (t.length() != 8)) {
                    throw new ParseException("Invalid pattern!", t.length() - 1);
                }
                if (t.length() == 8) {
                    t = t.substring(0, 2) + ":" + t.substring(3, 5) + ":" + t.substring(6, 8);
                } else {
                    t = t.substring(0, 2) + ":" + t.substring(2, 4) + ":" + t.substring(4, 6);
                }
                if (Biblioteca.validaHora(Integer.valueOf(t.substring(0, 2)), Integer.valueOf(t.substring(3, 5)), Integer.valueOf(t.substring(6, 8)))) {
                    return t;
                } else {
                    return null;
                }
            }
        };
        formattedTextColumn1.setFormatter(formatter);

        /*
         * Configurações do lookup do colaborador
         */
        colaboradorController.setLookupValueObjectClassName("com.t2tierp.cadastros.java.ColaboradorVO");
        colaboradorController.addLookup2ParentLink("id", "colaborador.id");
        colaboradorController.addLookup2ParentLink("pisNumero", "colaborador.pisNumero");
        colaboradorController.addLookup2ParentLink("pessoa.nome", "colaborador.pessoa.nome");
        colaboradorController.setHeaderColumnName("id", "ID");
        colaboradorController.setHeaderColumnName("pessoa.nome", "Nome");
        colaboradorController.setFrameTitle("Importa Colaborador");

        colaboradorController.setVisibleStatusPanel(true);
        colaboradorController.setVisibleColumn("id", true);
        colaboradorController.setVisibleColumn("pessoa.nome", true);
        colaboradorController.setFramePreferedSize(new Dimension(600, 500));

        colaboradorController.setLookupDataLocator(new LookupDataLocatorGenerico(colaboradorController.getLookupValueObjectClassName()));
        codLookupColumn1.setLookupController(colaboradorController);

    }

    public GridControl getGridImportaArquivo() {
        return gridControlImportaArquivo;
    }

    public GridControl getGridMarcacao() {
        return gridControlMarcacao;
    }

    public GridControl getGridFechamento() {
        return gridControlFechamento;
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
        genericButton1 = new GenericButton(new ImageIcon(ClientUtils.getImage(ClientSettings.BUTTON_IMPORT_IMAGE_NAME)));
        genericButton2 = new GenericButton(new ImageIcon(ClientUtils.getImage("ptrp16.png")));
        genericButton3 = new GenericButton(new ImageIcon(ClientUtils.getImage("ok.gif")));
        jTabbedPane1 = new javax.swing.JTabbedPane();
        gridControlImportaArquivo = new org.openswing.swing.client.GridControl();
        integerColumn4 = new org.openswing.swing.table.columns.client.IntegerColumn();
        dateColumn1 = new org.openswing.swing.table.columns.client.DateColumn();
        textColumn5 = new org.openswing.swing.table.columns.client.TextColumn();
        textColumn6 = new org.openswing.swing.table.columns.client.TextColumn();
        textColumn7 = new org.openswing.swing.table.columns.client.TextColumn();
        textColumn8 = new org.openswing.swing.table.columns.client.TextColumn();
        textColumn9 = new org.openswing.swing.table.columns.client.TextColumn();
        checkBoxColumn1 = new org.openswing.swing.table.columns.client.CheckBoxColumn();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        insertButton1 = new org.openswing.swing.client.InsertButton();
        editButton1 = new org.openswing.swing.client.EditButton();
        deleteButton1 = new org.openswing.swing.client.DeleteButton();
        saveButton1 = new org.openswing.swing.client.SaveButton();
        reloadButton1 = new org.openswing.swing.client.ReloadButton();
        navigatorBar1 = new org.openswing.swing.client.NavigatorBar();
        gridControlMarcacao = new org.openswing.swing.client.GridControl();
        codLookupColumn1 = new org.openswing.swing.table.columns.client.CodLookupColumn();
        dateColumn5 = new org.openswing.swing.table.columns.client.DateColumn();
        formattedTextColumn1 = new org.openswing.swing.table.columns.client.FormattedTextColumn();
        comboColumn8 = new org.openswing.swing.table.columns.client.ComboColumn();
        textColumn34 = new org.openswing.swing.table.columns.client.TextColumn();
        gridControlFechamento = new org.openswing.swing.client.GridControl();
        textColumn3 = new org.openswing.swing.table.columns.client.TextColumn();
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

        setTitle("T2Ti ERP - Ponto Eletrônico");
        setPreferredSize(new java.awt.Dimension(600, 400));
        getContentPane().setLayout(new java.awt.GridBagLayout());

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Importar Marcação:"));
        jPanel1.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

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
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        getContentPane().add(jPanel1, gridBagConstraints);

        gridControlImportaArquivo.setAutoLoadData(false);
        gridControlImportaArquivo.setValueObjectClassName("com.t2tierp.ponto.java.AFDTipo3VO");
        gridControlImportaArquivo.getColumnContainer().setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        integerColumn4.setColumnName("sequencial");
        integerColumn4.setHeaderColumnName("Sequencial");
        integerColumn4.setHeaderFont(new java.awt.Font("Arial", 1, 11));
        integerColumn4.setPreferredWidth(120);
        gridControlImportaArquivo.getColumnContainer().add(integerColumn4);

        dateColumn1.setColumnName("dataMarcacao");
        dateColumn1.setHeaderColumnName("Data Marcação");
        dateColumn1.setHeaderFont(new java.awt.Font("Arial", 1, 11));
        gridControlImportaArquivo.getColumnContainer().add(dateColumn1);

        textColumn5.setColumnName("horaMarcacao");
        textColumn5.setHeaderColumnName("Hora Marcação");
        textColumn5.setHeaderFont(new java.awt.Font("Arial", 1, 11));
        textColumn5.setPreferredWidth(120);
        gridControlImportaArquivo.getColumnContainer().add(textColumn5);

        textColumn6.setColumnName("pisEmpregado");
        textColumn6.setHeaderColumnName("PIS");
        textColumn6.setHeaderFont(new java.awt.Font("Arial", 1, 11));
        textColumn6.setPreferredWidth(150);
        gridControlImportaArquivo.getColumnContainer().add(textColumn6);

        textColumn7.setColumnName("colaborador.pessoa.nome");
        textColumn7.setHeaderColumnName("Colaborador");
        textColumn7.setHeaderFont(new java.awt.Font("Arial", 1, 11));
        textColumn7.setPreferredWidth(200);
        gridControlImportaArquivo.getColumnContainer().add(textColumn7);

        textColumn8.setColumnName("numeroSerieRelogioPonto");
        textColumn8.setHeaderColumnName("Nr Serie Relogio de Ponto");
        textColumn8.setHeaderFont(new java.awt.Font("Arial", 1, 11));
        textColumn8.setPreferredWidth(200);
        gridControlImportaArquivo.getColumnContainer().add(textColumn8);

        textColumn9.setColumnName("situacaoRegistro");
        textColumn9.setHeaderColumnName("Situacao Registro");
        textColumn9.setHeaderFont(new java.awt.Font("Arial", 1, 11));
        textColumn9.setPreferredWidth(200);
        gridControlImportaArquivo.getColumnContainer().add(textColumn9);

        checkBoxColumn1.setColumnName("desconsiderar");
        checkBoxColumn1.setHeaderColumnName("Desconsiderar");
        checkBoxColumn1.setHeaderFont(new java.awt.Font("Arial", 1, 11));
        gridControlImportaArquivo.getColumnContainer().add(checkBoxColumn1);

        jTabbedPane1.addTab("Marcações no Arquivo", gridControlImportaArquivo);

        jPanel2.setLayout(new java.awt.GridBagLayout());

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Ponto Marcacao"));
        jPanel3.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));
        jPanel3.add(insertButton1);
        jPanel3.add(editButton1);
        jPanel3.add(deleteButton1);
        jPanel3.add(saveButton1);
        jPanel3.add(reloadButton1);
        jPanel3.add(navigatorBar1);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        jPanel2.add(jPanel3, gridBagConstraints);

        gridControlMarcacao.setDeleteButton(deleteButton1);
        gridControlMarcacao.setEditButton(editButton1);
        gridControlMarcacao.setFunctionId("pontoMarcacao");
        gridControlMarcacao.setInsertButton(insertButton1);
        gridControlMarcacao.setInsertRowsOnTop(false);
        gridControlMarcacao.setNavBar(navigatorBar1);
        gridControlMarcacao.setReloadButton(reloadButton1);
        gridControlMarcacao.setSaveButton(saveButton1);
        gridControlMarcacao.setValueObjectClassName("com.t2tierp.ponto.java.PontoMarcacaoVO");
        gridControlMarcacao.getColumnContainer().setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        codLookupColumn1.setColumnName("colaborador.pessoa.nome");
        codLookupColumn1.setEditableOnEdit(true);
        codLookupColumn1.setEditableOnInsert(true);
        codLookupColumn1.setHeaderColumnName("Colaborador");
        codLookupColumn1.setHeaderFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        codLookupColumn1.setPreferredWidth(200);
        gridControlMarcacao.getColumnContainer().add(codLookupColumn1);

        dateColumn5.setColumnName("dataMarcacao");
        dateColumn5.setEditableOnEdit(true);
        dateColumn5.setEditableOnInsert(true);
        dateColumn5.setHeaderColumnName("Data Marcacao");
        dateColumn5.setHeaderFont(new java.awt.Font("Arial", 1, 11));
        gridControlMarcacao.getColumnContainer().add(dateColumn5);

        formattedTextColumn1.setColumnName("horaMarcacao");
        formattedTextColumn1.setEditableOnEdit(true);
        formattedTextColumn1.setEditableOnInsert(true);
        formattedTextColumn1.setHeaderColumnName("Hora Marcacao");
        formattedTextColumn1.setHeaderFont(new java.awt.Font("Arial", 1, 11));
        gridControlMarcacao.getColumnContainer().add(formattedTextColumn1);

        comboColumn8.setColumnName("tipoRegistro");
        comboColumn8.setDomainId("tipoRegistroPonto");
        comboColumn8.setHeaderColumnName("Tipo Registro");
        comboColumn8.setHeaderFont(new java.awt.Font("Arial", 1, 11));
        gridControlMarcacao.getColumnContainer().add(comboColumn8);

        textColumn34.setColumnName("justificativa");
        textColumn34.setEditableOnEdit(true);
        textColumn34.setEditableOnInsert(true);
        textColumn34.setHeaderColumnName("Justificativa");
        textColumn34.setHeaderFont(new java.awt.Font("Arial", 1, 11));
        textColumn34.setMaxCharacters(100);
        textColumn34.setPreferredWidth(200);
        gridControlMarcacao.getColumnContainer().add(textColumn34);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel2.add(gridControlMarcacao, gridBagConstraints);

        jTabbedPane1.addTab("Marcações Incluídas", jPanel2);

        gridControlFechamento.setFunctionId("pontoFechamentoJornada");
        gridControlFechamento.setValueObjectClassName("com.t2tierp.ponto.java.PontoFechamentoJornadaVO");
        gridControlFechamento.getColumnContainer().setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        textColumn3.setColumnName("colaborador.pessoa.nome");
        textColumn3.setHeaderColumnName("Colaborador");
        textColumn3.setHeaderFont(new java.awt.Font("Arial", 1, 11));
        textColumn3.setPreferredWidth(200);
        gridControlFechamento.getColumnContainer().add(textColumn3);

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

        jTabbedPane1.addTab("Fechamento Jornada", gridControlFechamento);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        getContentPane().add(jTabbedPane1, gridBagConstraints);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void genericButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_genericButton1ActionPerformed
        importaArquivoController.importaMarcacao();
    }//GEN-LAST:event_genericButton1ActionPerformed

    private void genericButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_genericButton2ActionPerformed
        jTabbedPane1.setSelectedIndex(2);
        importaArquivoController.salvaRegistros();
        gridControlFechamento.reloadData();
    }//GEN-LAST:event_genericButton2ActionPerformed

    private void genericButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_genericButton3ActionPerformed
        fechamentoController.gravaFechamento();
    }//GEN-LAST:event_genericButton3ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private org.openswing.swing.table.columns.client.CheckBoxColumn checkBoxColumn1;
    private org.openswing.swing.table.columns.client.CodLookupColumn codLookupColumn1;
    private org.openswing.swing.table.columns.client.ComboColumn comboColumn25;
    private org.openswing.swing.table.columns.client.ComboColumn comboColumn28;
    private org.openswing.swing.table.columns.client.ComboColumn comboColumn31;
    private org.openswing.swing.table.columns.client.ComboColumn comboColumn34;
    private org.openswing.swing.table.columns.client.ComboColumn comboColumn36;
    private org.openswing.swing.table.columns.client.ComboColumn comboColumn8;
    private org.openswing.swing.table.columns.client.DateColumn dateColumn1;
    private org.openswing.swing.table.columns.client.DateColumn dateColumn4;
    private org.openswing.swing.table.columns.client.DateColumn dateColumn5;
    private org.openswing.swing.table.columns.client.DecimalColumn decimalColumn24;
    private org.openswing.swing.table.columns.client.DecimalColumn decimalColumn27;
    private org.openswing.swing.table.columns.client.DecimalColumn decimalColumn30;
    private org.openswing.swing.table.columns.client.DecimalColumn decimalColumn33;
    private org.openswing.swing.client.DeleteButton deleteButton1;
    private org.openswing.swing.client.EditButton editButton1;
    private org.openswing.swing.table.columns.client.FormattedTextColumn formattedTextColumn1;
    private org.openswing.swing.client.GenericButton genericButton1;
    private org.openswing.swing.client.GenericButton genericButton2;
    private org.openswing.swing.client.GenericButton genericButton3;
    private org.openswing.swing.client.GridControl gridControlFechamento;
    private org.openswing.swing.client.GridControl gridControlImportaArquivo;
    private org.openswing.swing.client.GridControl gridControlMarcacao;
    private org.openswing.swing.client.InsertButton insertButton1;
    private org.openswing.swing.table.columns.client.IntegerColumn integerColumn4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private org.openswing.swing.client.NavigatorBar navigatorBar1;
    private org.openswing.swing.client.ReloadButton reloadButton1;
    private org.openswing.swing.client.SaveButton saveButton1;
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
    private org.openswing.swing.table.columns.client.TextColumn textColumn32;
    private org.openswing.swing.table.columns.client.TextColumn textColumn34;
    private org.openswing.swing.table.columns.client.TextColumn textColumn35;
    private org.openswing.swing.table.columns.client.TextColumn textColumn37;
    private org.openswing.swing.table.columns.client.TextColumn textColumn38;
    private org.openswing.swing.table.columns.client.TextColumn textColumn5;
    private org.openswing.swing.table.columns.client.TextColumn textColumn6;
    private org.openswing.swing.table.columns.client.TextColumn textColumn7;
    private org.openswing.swing.table.columns.client.TextColumn textColumn8;
    private org.openswing.swing.table.columns.client.TextColumn textColumn9;
    // End of variables declaration//GEN-END:variables
}
