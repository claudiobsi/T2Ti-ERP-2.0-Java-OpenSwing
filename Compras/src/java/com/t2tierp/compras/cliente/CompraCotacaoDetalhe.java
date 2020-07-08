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
package com.t2tierp.compras.cliente;

import com.t2tierp.compras.java.CompraCotacaoDetalheVO;
import com.t2tierp.compras.java.CompraRequisicaoDetalheVO;
import com.t2tierp.padrao.cliente.LookupDataLocatorGenerico;
import java.awt.Dimension;
import java.math.BigDecimal;
import javax.swing.JOptionPane;
import org.openswing.swing.lookup.client.LookupController;
import org.openswing.swing.mdi.client.InternalFrame;

public class CompraCotacaoDetalhe extends InternalFrame {

    private CompraFornecedorCotacaoGridController fornecedorCotacaoController;
    private CompraCotacaoDetalheGridController cotacaoDetalheController;
    private CompraItensNaoCotadosGridController itensNaoCotadosController;
    private LookupController fornecedorController = new LookupController();

    public CompraCotacaoDetalhe(CompraCotacaoDetalheController controller) {
        initComponents();

        form1.setFormController(controller);

        fornecedorCotacaoController = new CompraFornecedorCotacaoGridController(this);
        gridFornecedor.setController(fornecedorCotacaoController);
        gridFornecedor.setGridDataLocator(fornecedorCotacaoController);

        cotacaoDetalheController = new CompraCotacaoDetalheGridController(this);
        gridItensCotacao.setController(cotacaoDetalheController);
        gridItensCotacao.setGridDataLocator(cotacaoDetalheController);

        itensNaoCotadosController = new CompraItensNaoCotadosGridController();
        gridItensNaoCotados.setController(itensNaoCotadosController);
        gridItensNaoCotados.setGridDataLocator(itensNaoCotadosController);

        /*
         * Configurações do lookup do fornecedor
         */
        fornecedorController.setLookupValueObjectClassName("com.t2tierp.cadastros.java.FornecedorVO");
        fornecedorController.addLookup2ParentLink("id", "fornecedor.id");
        fornecedorController.addLookup2ParentLink("pessoa.nome", "fornecedor.pessoa.nome");
        fornecedorController.setHeaderColumnName("id", "ID");
        fornecedorController.setHeaderColumnName("pessoa.nome", "Nome");
        fornecedorController.setFrameTitle("Importa Fornecedor");

        fornecedorController.setVisibleStatusPanel(true);
        fornecedorController.setVisibleColumn("id", true);
        fornecedorController.setVisibleColumn("pessoa.nome", true);
        fornecedorController.setFramePreferedSize(new Dimension(600, 500));

        fornecedorController.setLookupDataLocator(new LookupDataLocatorGenerico(fornecedorController.getLookupValueObjectClassName()));
        codLookupColumn1.setLookupController(fornecedorController);

    }

    private void insereItemCotacao() {
        try {
            if (gridItensNaoCotados.getSelectedRow() == -1){
                throw new Exception("Selecione um item da requisição!");
            }
            BigDecimal quantidade = ncQuantidade.getBigDecimal();
            if (quantidade == null || quantidade.compareTo(BigDecimal.ZERO) <= 0){
                throw new Exception("A quantidade informada deve ser maior que 0(zero)!");
            }
            CompraRequisicaoDetalheVO requisicaoDetalhe = (CompraRequisicaoDetalheVO) gridItensNaoCotados.getVOListTableModel().getObjectForRow(gridItensNaoCotados.getSelectedRow());
            getCotacaoDetalheController().insereItemCotacao(requisicaoDetalhe, quantidade);
            gridItensNaoCotados.getVOListTableModel().updateObjectAt(gridItensNaoCotados.getSelectedRow());
        } catch (Exception e) {
            //e.printStackTrace();
            JOptionPane.showMessageDialog(this, e.getMessage(), "Erro do Sistema", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void removeItemCotacao() {
        try {
            if (gridItensCotacao.getSelectedRow() == -1){
                throw new Exception("Selecione um item da cotação!");
            }
            CompraCotacaoDetalheVO cotacaoDetalhe = (CompraCotacaoDetalheVO) gridItensCotacao.getVOListTableModel().getObjectForRow(gridItensCotacao.getSelectedRow());
            getCotacaoDetalheController().removeItemCotacao(cotacaoDetalhe);
        } catch (Exception e) {
            //e.printStackTrace();
            JOptionPane.showMessageDialog(this, e.getMessage(), "Erro do Sistema", JOptionPane.ERROR_MESSAGE);
        }

    }

    /**
     * @return the form1
     */
    public org.openswing.swing.form.client.Form getForm1() {
        return form1;
    }

    /**
     * @return the gridFornecedor
     */
    public org.openswing.swing.client.GridControl getGridFornecedor() {
        return gridFornecedor;
    }

    /**
     * @return the gridItensCotacao
     */
    public org.openswing.swing.client.GridControl getGridItensCotacao() {
        return gridItensCotacao;
    }

    /**
     * @return the gridRequisicaoDetalhe
     */
    public org.openswing.swing.client.GridControl getGridRequisicaoDetalhe() {
        return gridItensNaoCotados;
    }

    /**
     * @return the fornecedorCotacaoController
     */
    public CompraFornecedorCotacaoGridController getFornecedorCotacaoController() {
        return fornecedorCotacaoController;
    }

    /**
     * @return the cotacaoDetalheController
     */
    public CompraCotacaoDetalheGridController getCotacaoDetalheController() {
        return cotacaoDetalheController;
    }

    /**
     * @return the itensNaoCotadosController
     */
    public CompraItensNaoCotadosGridController getItensNaoCotadosController() {
        return itensNaoCotadosController;
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
        saveButton1 = new org.openswing.swing.client.SaveButton();
        form1 = new org.openswing.swing.form.client.Form();
        labelControl1 = new org.openswing.swing.client.LabelControl();
        dateControl2 = new org.openswing.swing.client.DateControl();
        labelControl2 = new org.openswing.swing.client.LabelControl();
        textControl3 = new org.openswing.swing.client.TextControl();
        labelControl3 = new org.openswing.swing.client.LabelControl();
        comboBoxControl4 = new org.openswing.swing.client.ComboBoxControl();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        insertButtonFornecedor = new org.openswing.swing.client.InsertButton();
        editButtonFornecedor = new org.openswing.swing.client.EditButton();
        deleteButtonFornecedor = new org.openswing.swing.client.DeleteButton();
        saveButtonFornecedor = new org.openswing.swing.client.SaveButton();
        reloadButtonFornecedor = new org.openswing.swing.client.ReloadButton();
        navigatorBarFornecedor = new org.openswing.swing.client.NavigatorBar();
        gridFornecedor = new org.openswing.swing.client.GridControl();
        codLookupColumn1 = new org.openswing.swing.table.columns.client.CodLookupColumn();
        jSplitPane1 = new javax.swing.JSplitPane();
        jPanel4 = new javax.swing.JPanel();
        gridItensNaoCotados = new org.openswing.swing.client.GridControl();
        textColumn1 = new org.openswing.swing.table.columns.client.TextColumn();
        decimalColumn4 = new org.openswing.swing.table.columns.client.DecimalColumn();
        decimalColumn5 = new org.openswing.swing.table.columns.client.DecimalColumn();
        jPanel5 = new javax.swing.JPanel();
        gridItensCotacao = new org.openswing.swing.client.GridControl();
        textColumn4 = new org.openswing.swing.table.columns.client.TextColumn();
        decimalColumn6 = new org.openswing.swing.table.columns.client.DecimalColumn();
        btnRetirar = new javax.swing.JButton();
        btnInserir = new javax.swing.JButton();
        ncQuantidade = new org.openswing.swing.client.NumericControl();
        jSeparator1 = new javax.swing.JSeparator();

        setTitle("T2Ti ERP - Gestão de Compras");
        setPreferredSize(new java.awt.Dimension(700, 400));
        getContentPane().setLayout(new java.awt.GridBagLayout());

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Compra Cotacao"));
        jPanel1.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));
        jPanel1.add(reloadButton1);
        jPanel1.add(saveButton1);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        getContentPane().add(jPanel1, gridBagConstraints);

        form1.setVOClassName("com.t2tierp.compras.java.CompraCotacaoVO");
        form1.setFunctionId("compraCotacao");
        form1.setReloadButton(reloadButton1);
        form1.setSaveButton(saveButton1);
        form1.setLayout(new java.awt.GridBagLayout());

        labelControl1.setLabel("Data Cotacao:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        form1.add(labelControl1, gridBagConstraints);

        dateControl2.setAttributeName("dataCotacao");
        dateControl2.setEnabled(false);
        dateControl2.setLinkLabel(labelControl1);
        dateControl2.setRequired(true);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        form1.add(dateControl2, gridBagConstraints);

        labelControl2.setLabel("Descricao:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        form1.add(labelControl2, gridBagConstraints);

        textControl3.setAttributeName("descricao");
        textControl3.setEnabled(false);
        textControl3.setLinkLabel(labelControl2);
        textControl3.setMaxCharacters(100);
        textControl3.setRequired(true);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        form1.add(textControl3, gridBagConstraints);

        labelControl3.setLabel("Situacao:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        form1.add(labelControl3, gridBagConstraints);

        comboBoxControl4.setAttributeName("situacao");
        comboBoxControl4.setDomainId("compraSituacaoCotacao");
        comboBoxControl4.setEnabled(false);
        comboBoxControl4.setEnabledOnEdit(false);
        comboBoxControl4.setEnabledOnInsert(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 100;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        form1.add(comboBoxControl4, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        getContentPane().add(form1, gridBagConstraints);

        jPanel2.setLayout(new java.awt.GridBagLayout());

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Compra Fornecedor Cotacao"));
        jPanel3.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));
        jPanel3.add(insertButtonFornecedor);
        jPanel3.add(editButtonFornecedor);
        jPanel3.add(deleteButtonFornecedor);
        jPanel3.add(saveButtonFornecedor);
        jPanel3.add(reloadButtonFornecedor);
        jPanel3.add(navigatorBarFornecedor);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        jPanel2.add(jPanel3, gridBagConstraints);

        gridFornecedor.setAutoLoadData(false);
        gridFornecedor.setDeleteButton(deleteButtonFornecedor);
        gridFornecedor.setEditButton(editButtonFornecedor);
        gridFornecedor.setFunctionId("compraFornecedorCotacao");
        gridFornecedor.setInsertButton(insertButtonFornecedor);
        gridFornecedor.setNavBar(navigatorBarFornecedor);
        gridFornecedor.setReloadButton(reloadButtonFornecedor);
        gridFornecedor.setSaveButton(saveButtonFornecedor);
        gridFornecedor.setValueObjectClassName("com.t2tierp.compras.java.CompraFornecedorCotacaoVO");
        gridFornecedor.getColumnContainer().setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        codLookupColumn1.setColumnName("fornecedor.pessoa.nome");
        codLookupColumn1.setEditableOnEdit(true);
        codLookupColumn1.setEditableOnInsert(true);
        codLookupColumn1.setEnableCodBox(false);
        codLookupColumn1.setHeaderColumnName("Fornecedor");
        codLookupColumn1.setHeaderFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        codLookupColumn1.setPreferredWidth(400);
        gridFornecedor.getColumnContainer().add(codLookupColumn1);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel2.add(gridFornecedor, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        getContentPane().add(jPanel2, gridBagConstraints);

        jSplitPane1.setDividerLocation(550);

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Itens de requisição ainda não cotados"));
        jPanel4.setLayout(new java.awt.GridBagLayout());

        gridItensNaoCotados.setAutoLoadData(false);
        gridItensNaoCotados.setFunctionId("compraRequisicaoDetalhe");
        gridItensNaoCotados.setValueObjectClassName("com.t2tierp.compras.java.CompraRequisicaoDetalheVO");
        gridItensNaoCotados.getColumnContainer().setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        textColumn1.setColumnName("produto.nome");
        textColumn1.setHeaderColumnName("Produto");
        textColumn1.setHeaderFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        textColumn1.setPreferredWidth(200);
        gridItensNaoCotados.getColumnContainer().add(textColumn1);

        decimalColumn4.setColumnName("quantidade");
        decimalColumn4.setDecimals(2);
        decimalColumn4.setEditableOnEdit(true);
        decimalColumn4.setEditableOnInsert(true);
        decimalColumn4.setHeaderColumnName("Quantidade");
        decimalColumn4.setHeaderFont(new java.awt.Font("Arial", 1, 11));
        decimalColumn4.setPreferredWidth(90);
        gridItensNaoCotados.getColumnContainer().add(decimalColumn4);

        decimalColumn5.setColumnName("quantidadeCotada");
        decimalColumn5.setColumnRequired(false);
        decimalColumn5.setDecimals(2);
        decimalColumn5.setHeaderColumnName("Quantidade Cotada");
        decimalColumn5.setHeaderFont(new java.awt.Font("Arial", 1, 11));
        decimalColumn5.setPreferredWidth(120);
        gridItensNaoCotados.getColumnContainer().add(decimalColumn5);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel4.add(gridItensNaoCotados, gridBagConstraints);

        jSplitPane1.setLeftComponent(jPanel4);

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder("Itens da Cotação"));
        jPanel5.setLayout(new java.awt.GridBagLayout());

        gridItensCotacao.setAutoLoadData(false);
        gridItensCotacao.setFunctionId("compraCotacaoDetalhe");
        gridItensCotacao.setValueObjectClassName("com.t2tierp.compras.java.CompraCotacaoDetalheVO");
        gridItensCotacao.getColumnContainer().setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        textColumn4.setColumnName("produto.nome");
        textColumn4.setHeaderColumnName("Produto");
        textColumn4.setHeaderFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        textColumn4.setPreferredWidth(200);
        gridItensCotacao.getColumnContainer().add(textColumn4);

        decimalColumn6.setColumnName("quantidade");
        decimalColumn6.setDecimals(2);
        decimalColumn6.setHeaderColumnName("Quantidade");
        decimalColumn6.setHeaderFont(new java.awt.Font("Arial", 1, 11));
        decimalColumn6.setPreferredWidth(90);
        gridItensCotacao.getColumnContainer().add(decimalColumn6);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel5.add(gridItensCotacao, gridBagConstraints);

        btnRetirar.setText("Retirar");
        btnRetirar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRetirarActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel5.add(btnRetirar, gridBagConstraints);

        btnInserir.setText("Inserir");
        btnInserir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInserirActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel5.add(btnInserir, gridBagConstraints);

        ncQuantidade.setDecimals(3);
        ncQuantidade.setMaxCharacters(10);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel5.add(ncQuantidade, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weighty = 1.0;
        jPanel5.add(jSeparator1, gridBagConstraints);

        jSplitPane1.setRightComponent(jPanel5);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weighty = 1.0;
        getContentPane().add(jSplitPane1, gridBagConstraints);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnInserirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInserirActionPerformed
        insereItemCotacao();
    }//GEN-LAST:event_btnInserirActionPerformed

    private void btnRetirarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRetirarActionPerformed
        removeItemCotacao();
    }//GEN-LAST:event_btnRetirarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnInserir;
    private javax.swing.JButton btnRetirar;
    private org.openswing.swing.table.columns.client.CodLookupColumn codLookupColumn1;
    private org.openswing.swing.client.ComboBoxControl comboBoxControl4;
    private org.openswing.swing.client.DateControl dateControl2;
    private org.openswing.swing.table.columns.client.DecimalColumn decimalColumn4;
    private org.openswing.swing.table.columns.client.DecimalColumn decimalColumn5;
    private org.openswing.swing.table.columns.client.DecimalColumn decimalColumn6;
    private org.openswing.swing.client.DeleteButton deleteButtonFornecedor;
    private org.openswing.swing.client.EditButton editButtonFornecedor;
    private org.openswing.swing.form.client.Form form1;
    private org.openswing.swing.client.GridControl gridFornecedor;
    private org.openswing.swing.client.GridControl gridItensCotacao;
    private org.openswing.swing.client.GridControl gridItensNaoCotados;
    private org.openswing.swing.client.InsertButton insertButtonFornecedor;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSplitPane jSplitPane1;
    private org.openswing.swing.client.LabelControl labelControl1;
    private org.openswing.swing.client.LabelControl labelControl2;
    private org.openswing.swing.client.LabelControl labelControl3;
    private org.openswing.swing.client.NavigatorBar navigatorBarFornecedor;
    private org.openswing.swing.client.NumericControl ncQuantidade;
    private org.openswing.swing.client.ReloadButton reloadButton1;
    private org.openswing.swing.client.ReloadButton reloadButtonFornecedor;
    private org.openswing.swing.client.SaveButton saveButton1;
    private org.openswing.swing.client.SaveButton saveButtonFornecedor;
    private org.openswing.swing.table.columns.client.TextColumn textColumn1;
    private org.openswing.swing.table.columns.client.TextColumn textColumn4;
    private org.openswing.swing.client.TextControl textControl3;
    // End of variables declaration//GEN-END:variables

}
