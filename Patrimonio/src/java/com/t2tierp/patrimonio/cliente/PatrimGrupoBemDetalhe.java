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
package com.t2tierp.patrimonio.cliente;

import com.t2tierp.padrao.cliente.LookupDataLocatorGenerico;
import java.awt.Dimension;
import org.openswing.swing.lookup.client.LookupController;
import org.openswing.swing.mdi.client.InternalFrame;

public class PatrimGrupoBemDetalhe extends InternalFrame {

    private LookupController contaDespesaDepreciacaoController = new LookupController();
    private LookupController contaDepreciacaoAcumuladaController = new LookupController();
    private LookupController contaAtivoImobilizadoController = new LookupController();
    private LookupController codigoHistoricoController = new LookupController();

    public PatrimGrupoBemDetalhe(PatrimGrupoBemDetalheController controller) {
        initComponents();

        form1.setFormController(controller);

        /*
         * Configurações do lookup da conta Despesa Depreciacao
         */
        contaDespesaDepreciacaoController.setLookupValueObjectClassName("com.t2tierp.contabilidade.java.ContabilContaVO");
        contaDespesaDepreciacaoController.addLookup2ParentLink("classificacao", "contaDespesaDepreciacao");
        contaDespesaDepreciacaoController.setHeaderColumnName("classificacao", "Classificação");
        contaDespesaDepreciacaoController.setHeaderColumnName("descricao", "Descrição");
        contaDespesaDepreciacaoController.setFrameTitle("Importa Conta Contábil");

        contaDespesaDepreciacaoController.setVisibleStatusPanel(true);
        contaDespesaDepreciacaoController.setVisibleColumn("classificacao", true);
        contaDespesaDepreciacaoController.setVisibleColumn("descricao", true);
        contaDespesaDepreciacaoController.setPreferredWidthColumn("descricao", 300);
        contaDespesaDepreciacaoController.setFramePreferedSize(new Dimension(600, 500));

        contaDespesaDepreciacaoController.setLookupDataLocator(new LookupDataLocatorGenerico(contaDespesaDepreciacaoController.getLookupValueObjectClassName()));
        codLookupControl1.setLookupController(contaDespesaDepreciacaoController);
        
        /*
         * Configurações do lookup da conta depreciacao acumulada
         */
        contaDepreciacaoAcumuladaController.setLookupValueObjectClassName("com.t2tierp.contabilidade.java.ContabilContaVO");
        contaDepreciacaoAcumuladaController.addLookup2ParentLink("classificacao", "contaDepreciacaoAcumulada");
        contaDepreciacaoAcumuladaController.setHeaderColumnName("classificacao", "Classificação");
        contaDepreciacaoAcumuladaController.setHeaderColumnName("descricao", "Descrição");
        contaDepreciacaoAcumuladaController.setFrameTitle("Importa Conta Contábil");

        contaDepreciacaoAcumuladaController.setVisibleStatusPanel(true);
        contaDepreciacaoAcumuladaController.setVisibleColumn("classificacao", true);
        contaDepreciacaoAcumuladaController.setVisibleColumn("descricao", true);
        contaDespesaDepreciacaoController.setPreferredWidthColumn("descricao", 300);
        contaDepreciacaoAcumuladaController.setFramePreferedSize(new Dimension(600, 500));

        contaDepreciacaoAcumuladaController.setLookupDataLocator(new LookupDataLocatorGenerico(contaDepreciacaoAcumuladaController.getLookupValueObjectClassName()));
        codLookupControl2.setLookupController(contaDepreciacaoAcumuladaController);

        /*
         * Configurações do lookup da conta ativo imobilizado
         */
        contaAtivoImobilizadoController.setLookupValueObjectClassName("com.t2tierp.contabilidade.java.ContabilContaVO");
        contaAtivoImobilizadoController.addLookup2ParentLink("classificacao", "contaAtivoImobilizado");
        contaAtivoImobilizadoController.setHeaderColumnName("classificacao", "Classificação");
        contaAtivoImobilizadoController.setHeaderColumnName("descricao", "Descrição");
        contaAtivoImobilizadoController.setFrameTitle("Importa Conta Contábil");

        contaAtivoImobilizadoController.setVisibleStatusPanel(true);
        contaAtivoImobilizadoController.setVisibleColumn("classificacao", true);
        contaAtivoImobilizadoController.setVisibleColumn("descricao", true);
        contaDespesaDepreciacaoController.setPreferredWidthColumn("descricao", 300);
        contaAtivoImobilizadoController.setFramePreferedSize(new Dimension(600, 500));

        contaAtivoImobilizadoController.setLookupDataLocator(new LookupDataLocatorGenerico(contaAtivoImobilizadoController.getLookupValueObjectClassName()));
        codLookupControl3.setLookupController(contaAtivoImobilizadoController);

        /*
         * Configurações do lookup do codigo do historico
         */
        codigoHistoricoController.setLookupValueObjectClassName("com.t2tierp.contabilidade.java.ContabilHistoricoVO");
        codigoHistoricoController.addLookup2ParentLink("id", "codigoHistorico");
        codigoHistoricoController.setHeaderColumnName("id", "ID");
        codigoHistoricoController.setHeaderColumnName("descricao", "Descricao");
        codigoHistoricoController.setFrameTitle("Importa Código Histórico");

        codigoHistoricoController.setVisibleStatusPanel(true);
        codigoHistoricoController.setVisibleColumn("id", true);
        codigoHistoricoController.setVisibleColumn("descricao", true);
        contaDespesaDepreciacaoController.setPreferredWidthColumn("descricao", 300);
        codigoHistoricoController.setFramePreferedSize(new Dimension(600, 500));

        codigoHistoricoController.setLookupDataLocator(new LookupDataLocatorGenerico(codigoHistoricoController.getLookupValueObjectClassName()));
        codLookupControl4.setLookupController(codigoHistoricoController);
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
        labelControl1 = new org.openswing.swing.client.LabelControl();
        textControl3 = new org.openswing.swing.client.TextControl();
        labelControl2 = new org.openswing.swing.client.LabelControl();
        textControl4 = new org.openswing.swing.client.TextControl();
        labelControl3 = new org.openswing.swing.client.LabelControl();
        labelControl4 = new org.openswing.swing.client.LabelControl();
        labelControl5 = new org.openswing.swing.client.LabelControl();
        labelControl6 = new org.openswing.swing.client.LabelControl();
        labelControl7 = new org.openswing.swing.client.LabelControl();
        textAreaControl1 = new org.openswing.swing.client.TextAreaControl();
        codLookupControl1 = new org.openswing.swing.client.CodLookupControl();
        codLookupControl2 = new org.openswing.swing.client.CodLookupControl();
        codLookupControl3 = new org.openswing.swing.client.CodLookupControl();
        codLookupControl4 = new org.openswing.swing.client.CodLookupControl();
        jSeparator1 = new javax.swing.JSeparator();

        setTitle("T2Ti ERP - Patrimonio");
        setPreferredSize(new java.awt.Dimension(700, 400));
        getContentPane().setLayout(new java.awt.GridBagLayout());

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Patrim Grupo Bem"));
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

        form1.setVOClassName("com.t2tierp.patrimonio.java.PatrimGrupoBemVO");
        form1.setEditButton(editButton1);
        form1.setFunctionId("patrimGrupoBem");
        form1.setReloadButton(reloadButton1);
        form1.setSaveButton(saveButton1);
        form1.setLayout(new java.awt.GridBagLayout());

        labelControl1.setLabel("Codigo:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        form1.add(labelControl1, gridBagConstraints);

        textControl3.setAttributeName("codigo");
        textControl3.setEnabled(false);
        textControl3.setLinkLabel(labelControl1);
        textControl3.setMaxCharacters(3);
        textControl3.setRequired(true);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        form1.add(textControl3, gridBagConstraints);

        labelControl2.setLabel("Nome:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        form1.add(labelControl2, gridBagConstraints);

        textControl4.setAttributeName("nome");
        textControl4.setEnabled(false);
        textControl4.setLinkLabel(labelControl2);
        textControl4.setMaxCharacters(50);
        textControl4.setRequired(true);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        form1.add(textControl4, gridBagConstraints);

        labelControl3.setLabel("Descricao:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        form1.add(labelControl3, gridBagConstraints);

        labelControl4.setLabel("Conta Ativo Imobilizado:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        form1.add(labelControl4, gridBagConstraints);

        labelControl5.setLabel("Conta Depreciacao Acumulada:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        form1.add(labelControl5, gridBagConstraints);

        labelControl6.setLabel("Conta Despesa Depreciacao:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        form1.add(labelControl6, gridBagConstraints);

        labelControl7.setLabel("Codigo Historico:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        form1.add(labelControl7, gridBagConstraints);

        textAreaControl1.setAttributeName("descricao");
        textAreaControl1.setEnabled(false);
        textAreaControl1.setMaxCharacters(1000);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipady = 30;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        form1.add(textAreaControl1, gridBagConstraints);

        codLookupControl1.setAttributeName("contaDespesaDepreciacao");
        codLookupControl1.setEnableCodBox(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = -50;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        form1.add(codLookupControl1, gridBagConstraints);

        codLookupControl2.setAttributeName("contaDepreciacaoAcumulada");
        codLookupControl2.setEnableCodBox(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = -50;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        form1.add(codLookupControl2, gridBagConstraints);

        codLookupControl3.setAttributeName("contaAtivoImobilizado");
        codLookupControl3.setEnableCodBox(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = -50;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        form1.add(codLookupControl3, gridBagConstraints);

        codLookupControl4.setAllowOnlyNumbers(true);
        codLookupControl4.setAttributeName("codigoHistorico");
        codLookupControl4.setEnableCodBox(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = -50;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        form1.add(codLookupControl4, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        form1.add(jSeparator1, gridBagConstraints);

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
    private org.openswing.swing.client.CodLookupControl codLookupControl1;
    private org.openswing.swing.client.CodLookupControl codLookupControl2;
    private org.openswing.swing.client.CodLookupControl codLookupControl3;
    private org.openswing.swing.client.CodLookupControl codLookupControl4;
    private org.openswing.swing.client.EditButton editButton1;
    private org.openswing.swing.form.client.Form form1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSeparator jSeparator1;
    private org.openswing.swing.client.LabelControl labelControl1;
    private org.openswing.swing.client.LabelControl labelControl2;
    private org.openswing.swing.client.LabelControl labelControl3;
    private org.openswing.swing.client.LabelControl labelControl4;
    private org.openswing.swing.client.LabelControl labelControl5;
    private org.openswing.swing.client.LabelControl labelControl6;
    private org.openswing.swing.client.LabelControl labelControl7;
    private org.openswing.swing.client.ReloadButton reloadButton1;
    private org.openswing.swing.client.SaveButton saveButton1;
    private org.openswing.swing.client.TextAreaControl textAreaControl1;
    private org.openswing.swing.client.TextControl textControl3;
    private org.openswing.swing.client.TextControl textControl4;
    // End of variables declaration//GEN-END:variables

}
