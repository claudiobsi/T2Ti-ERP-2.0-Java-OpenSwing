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
package com.t2tierp.ged.cliente;

import com.t2tierp.padrao.cliente.LookupDataLocatorGenerico;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import org.openswing.swing.client.GenericButton;
import org.openswing.swing.lookup.client.LookupController;
import org.openswing.swing.mdi.client.InternalFrame;
import org.openswing.swing.util.client.ClientUtils;

public class GedDocumentoDetalhe extends InternalFrame {

    private GedDocumentoDetalheController controller;
    private GedDocumentoDetalheGridController gridController;
    private LookupController tipoDocumentoController = new LookupController();
    
    public GedDocumentoDetalhe(GedDocumentoDetalheController controller) {
        initComponents();

        this.controller = controller;
        form1.setFormController(controller);
        
        btDigitalizar.setToolTipText("Digitalizar Documento");
        btDownloadDocumento.setToolTipText("Download do Documento");
        btSelecionaArquivo.setToolTipText("Selecionar Arquivo para Armazenamento");
        btVisualizaDocumento.setToolTipText("Visualizar Documento");        
        
        gridController = new GedDocumentoDetalheGridController(this);
        gridControl1.setController(gridController);
        gridControl1.setGridDataLocator(gridController);
        
        /*
         * Configurações do lookup do tipo de documento
         */
        tipoDocumentoController.setLookupValueObjectClassName("com.t2tierp.ged.java.GedTipoDocumentoVO");
        tipoDocumentoController.addLookup2ParentLink("id", "gedTipoDocumento.id");
        tipoDocumentoController.addLookup2ParentLink("nome", "gedTipoDocumento.nome");
        tipoDocumentoController.addLookup2ParentLink("tamanhoMaximo", "gedTipoDocumento.tamanhoMaximo");
        tipoDocumentoController.setPreferredWidthColumn("nome", 200);
        tipoDocumentoController.setPreferredWidthColumn("tamanhoMaximo", 200);
        tipoDocumentoController.setHeaderColumnName("nome", "Tipo Documento");
        tipoDocumentoController.setHeaderColumnName("tamanhoMaximo", "Tamanho Máximo(MB)");
        tipoDocumentoController.setFrameTitle("Tipo de Documento");

        tipoDocumentoController.setVisibleStatusPanel(true);
        tipoDocumentoController.setVisibleColumn("nome", true);
        tipoDocumentoController.setVisibleColumn("tamanhoMaximo", true);
        tipoDocumentoController.setFramePreferedSize(new Dimension(600, 500));

        tipoDocumentoController.setLookupDataLocator(new LookupDataLocatorGenerico(tipoDocumentoController.getLookupValueObjectClassName()));
        codLookupColumn1.setLookupController(tipoDocumentoController);
        
    }

    /**
     * @return the form1
     */
    public org.openswing.swing.form.client.Form getForm1() {
        return form1;
    }

    public GedDocumentoDetalheGridController getGridController() {
        return gridController;
    }

    public org.openswing.swing.client.GridControl getGridControl1() {
        return gridControl1;
    }

    public void setImagem(BufferedImage image) throws Exception {
        imgDigitalizada.setImage(image);
    }

    public byte[] getImagem() throws Exception {
        return imgDigitalizada.getImage();
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
        textControl2 = new org.openswing.swing.client.TextControl();
        labelControl2 = new org.openswing.swing.client.LabelControl();
        labelControl3 = new org.openswing.swing.client.LabelControl();
        dateControl4 = new org.openswing.swing.client.DateControl();
        textControl3 = new org.openswing.swing.client.TextControl();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        insertButton1 = new org.openswing.swing.client.InsertButton();
        editButton2 = new org.openswing.swing.client.EditButton();
        deleteButton1 = new org.openswing.swing.client.DeleteButton();
        saveButton2 = new org.openswing.swing.client.SaveButton();
        reloadButton2 = new org.openswing.swing.client.ReloadButton();
        navigatorBar1 = new org.openswing.swing.client.NavigatorBar();
        btVisualizaDocumento = new GenericButton(new ImageIcon(ClientUtils.getImage("visualizar.png")));
        btDownloadDocumento = new GenericButton(new ImageIcon(ClientUtils.getImage("download.png")));
        btSelecionaArquivo = new GenericButton(new ImageIcon(ClientUtils.getImage("open.png")));
        btDigitalizar = new GenericButton(new ImageIcon(ClientUtils.getImage("scanner.png")));
        gridControl1 = new org.openswing.swing.client.GridControl();
        codLookupColumn1 = new org.openswing.swing.table.columns.client.CodLookupColumn();
        textColumn5 = new org.openswing.swing.table.columns.client.TextColumn();
        textColumn6 = new org.openswing.swing.table.columns.client.TextColumn();
        textColumn7 = new org.openswing.swing.table.columns.client.TextColumn();
        comboColumn8 = new org.openswing.swing.table.columns.client.ComboColumn();
        comboColumn9 = new org.openswing.swing.table.columns.client.ComboColumn();
        comboColumn10 = new org.openswing.swing.table.columns.client.ComboColumn();
        dateColumn11 = new org.openswing.swing.table.columns.client.DateColumn();
        dateColumn12 = new org.openswing.swing.table.columns.client.DateColumn();
        jPanel4 = new javax.swing.JPanel();
        imgDigitalizada = new org.openswing.swing.client.ImagePanel();

        setTitle("T2Ti ERP - Ged");
        setPreferredSize(new java.awt.Dimension(700, 400));
        getContentPane().setLayout(new java.awt.GridBagLayout());

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Ged Documento Cabecalho"));
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

        form1.setVOClassName("com.t2tierp.ged.java.GedDocumentoCabecalhoVO");
        form1.setEditButton(editButton1);
        form1.setFunctionId("gedDocumentoCabecalho");
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

        textControl2.setAttributeName("nome");
        textControl2.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        form1.add(textControl2, gridBagConstraints);

        labelControl2.setLabel("Descricao:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        form1.add(labelControl2, gridBagConstraints);

        labelControl3.setLabel("Data Inclusao:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        form1.add(labelControl3, gridBagConstraints);

        dateControl4.setAttributeName("dataInclusao");
        dateControl4.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        form1.add(dateControl4, gridBagConstraints);

        textControl3.setAttributeName("nome");
        textControl3.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        form1.add(textControl3, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        getContentPane().add(form1, gridBagConstraints);

        jPanel2.setLayout(new java.awt.GridBagLayout());

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Ged Documento Detalhe"));
        jPanel3.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));
        jPanel3.add(insertButton1);
        jPanel3.add(editButton2);
        jPanel3.add(deleteButton1);
        jPanel3.add(saveButton2);
        jPanel3.add(reloadButton2);
        jPanel3.add(navigatorBar1);

        btVisualizaDocumento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btVisualizaDocumentoActionPerformed(evt);
            }
        });
        jPanel3.add(btVisualizaDocumento);

        btDownloadDocumento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btDownloadDocumentoActionPerformed(evt);
            }
        });
        jPanel3.add(btDownloadDocumento);

        btSelecionaArquivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btSelecionaArquivoActionPerformed(evt);
            }
        });
        jPanel3.add(btSelecionaArquivo);

        btDigitalizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btDigitalizarActionPerformed(evt);
            }
        });
        jPanel3.add(btDigitalizar);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        jPanel2.add(jPanel3, gridBagConstraints);

        gridControl1.setAutoLoadData(false);
        gridControl1.setDeleteButton(deleteButton1);
        gridControl1.setEditButton(editButton2);
        gridControl1.setEditOnSingleRow(true);
        gridControl1.setFunctionId("gedDocumentoDetalhe");
        gridControl1.setInsertButton(insertButton1);
        gridControl1.setNavBar(navigatorBar1);
        gridControl1.setReloadButton(reloadButton2);
        gridControl1.setSaveButton(saveButton2);
        gridControl1.setValueObjectClassName("com.t2tierp.ged.java.GedDocumentoDetalheVO");

        codLookupColumn1.setColumnName("gedTipoDocumento.nome");
        codLookupColumn1.setEditableOnEdit(true);
        codLookupColumn1.setEditableOnInsert(true);
        codLookupColumn1.setHeaderColumnName("Tipo Documento");
        codLookupColumn1.setHeaderFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        codLookupColumn1.setPreferredWidth(150);
        gridControl1.getColumnContainer().add(codLookupColumn1);

        textColumn5.setColumnName("nome");
        textColumn5.setEditableOnEdit(true);
        textColumn5.setEditableOnInsert(true);
        textColumn5.setHeaderColumnName("Nome");
        textColumn5.setHeaderFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        textColumn5.setPreferredWidth(200);
        gridControl1.getColumnContainer().add(textColumn5);

        textColumn6.setColumnName("descricao");
        textColumn6.setEditableOnEdit(true);
        textColumn6.setEditableOnInsert(true);
        textColumn6.setHeaderColumnName("Descricao");
        textColumn6.setHeaderFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        textColumn6.setPreferredWidth(200);
        gridControl1.getColumnContainer().add(textColumn6);

        textColumn7.setColumnName("palavraChave");
        textColumn7.setEditableOnEdit(true);
        textColumn7.setEditableOnInsert(true);
        textColumn7.setHeaderColumnName("Palavra Chave");
        textColumn7.setHeaderFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        gridControl1.getColumnContainer().add(textColumn7);

        comboColumn8.setColumnName("podeExcluir");
        comboColumn8.setDomainId("simnao");
        comboColumn8.setEditableOnEdit(true);
        comboColumn8.setEditableOnInsert(true);
        comboColumn8.setHeaderColumnName("Pode Excluir");
        comboColumn8.setHeaderFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        gridControl1.getColumnContainer().add(comboColumn8);

        comboColumn9.setColumnName("podeAlterar");
        comboColumn9.setDomainId("simnao");
        comboColumn9.setEditableOnEdit(true);
        comboColumn9.setEditableOnInsert(true);
        comboColumn9.setHeaderColumnName("Pode Alterar");
        comboColumn9.setHeaderFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        gridControl1.getColumnContainer().add(comboColumn9);

        comboColumn10.setColumnName("assinado");
        comboColumn10.setDomainId("simnao");
        comboColumn10.setEditableOnEdit(true);
        comboColumn10.setEditableOnInsert(true);
        comboColumn10.setHeaderColumnName("Assinado");
        comboColumn10.setHeaderFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        gridControl1.getColumnContainer().add(comboColumn10);

        dateColumn11.setColumnName("dataFimVigencia");
        dateColumn11.setColumnRequired(false);
        dateColumn11.setEditableOnEdit(true);
        dateColumn11.setEditableOnInsert(true);
        dateColumn11.setHeaderColumnName("Data Fim Vigencia");
        dateColumn11.setHeaderFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        dateColumn11.setPreferredWidth(110);
        gridControl1.getColumnContainer().add(dateColumn11);

        dateColumn12.setColumnName("dataExclusao");
        dateColumn12.setColumnRequired(false);
        dateColumn12.setHeaderColumnName("Data Exclusao");
        dateColumn12.setHeaderFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        gridControl1.getColumnContainer().add(dateColumn12);

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

        jPanel4.setLayout(new java.awt.GridBagLayout());

        imgDigitalizada.setAutoscrolls(true);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel4.add(imgDigitalizada, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        getContentPane().add(jPanel4, gridBagConstraints);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btVisualizaDocumentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btVisualizaDocumentoActionPerformed
        gridController.visualizaDocumento();
    }//GEN-LAST:event_btVisualizaDocumentoActionPerformed

    private void btDownloadDocumentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btDownloadDocumentoActionPerformed
        gridController.downloadDocumento();
    }//GEN-LAST:event_btDownloadDocumentoActionPerformed

    private void btSelecionaArquivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btSelecionaArquivoActionPerformed
        gridController.selecionaDocumento();
    }//GEN-LAST:event_btSelecionaArquivoActionPerformed

    private void btDigitalizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btDigitalizarActionPerformed
        gridController.digitalizaDocumento();
    }//GEN-LAST:event_btDigitalizarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private org.openswing.swing.client.GenericButton btDigitalizar;
    private org.openswing.swing.client.GenericButton btDownloadDocumento;
    private org.openswing.swing.client.GenericButton btSelecionaArquivo;
    private org.openswing.swing.client.GenericButton btVisualizaDocumento;
    private org.openswing.swing.table.columns.client.CodLookupColumn codLookupColumn1;
    private org.openswing.swing.table.columns.client.ComboColumn comboColumn10;
    private org.openswing.swing.table.columns.client.ComboColumn comboColumn8;
    private org.openswing.swing.table.columns.client.ComboColumn comboColumn9;
    private org.openswing.swing.table.columns.client.DateColumn dateColumn11;
    private org.openswing.swing.table.columns.client.DateColumn dateColumn12;
    private org.openswing.swing.client.DateControl dateControl4;
    private org.openswing.swing.client.DeleteButton deleteButton1;
    private org.openswing.swing.client.EditButton editButton1;
    private org.openswing.swing.client.EditButton editButton2;
    private org.openswing.swing.form.client.Form form1;
    private org.openswing.swing.client.GridControl gridControl1;
    private org.openswing.swing.client.ImagePanel imgDigitalizada;
    private org.openswing.swing.client.InsertButton insertButton1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private org.openswing.swing.client.LabelControl labelControl1;
    private org.openswing.swing.client.LabelControl labelControl2;
    private org.openswing.swing.client.LabelControl labelControl3;
    private org.openswing.swing.client.NavigatorBar navigatorBar1;
    private org.openswing.swing.client.ReloadButton reloadButton1;
    private org.openswing.swing.client.ReloadButton reloadButton2;
    private org.openswing.swing.client.SaveButton saveButton1;
    private org.openswing.swing.client.SaveButton saveButton2;
    private org.openswing.swing.table.columns.client.TextColumn textColumn5;
    private org.openswing.swing.table.columns.client.TextColumn textColumn6;
    private org.openswing.swing.table.columns.client.TextColumn textColumn7;
    private org.openswing.swing.client.TextControl textControl2;
    private org.openswing.swing.client.TextControl textControl3;
    // End of variables declaration//GEN-END:variables

}