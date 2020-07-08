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
package com.t2tierp.pcp.cliente;

import com.t2tierp.padrao.cliente.LookupDataLocatorGenerico;
import java.awt.Dimension;
import org.openswing.swing.client.GridControl;
import org.openswing.swing.lookup.client.LookupController;
import org.openswing.swing.mdi.client.InternalFrame;

public class PcpServicoGrid extends InternalFrame {

    private PcpServicoColaboradorGridController colaboradorController;
    private PcpServicoEquipamentoGridController equipamentoController;
    private LookupController colaboradorLookupController = new LookupController();
    private LookupController equipamentoLookupController = new LookupController();
    
    public PcpServicoGrid(PcpServicoGridController controller) {
        initComponents();
        gridControl1.setController(controller);
        gridControl1.setGridDataLocator(controller);
        
        colaboradorController = new PcpServicoColaboradorGridController();
        gridControl2.setController(colaboradorController);
        gridControl2.setGridDataLocator(colaboradorController);
        
        equipamentoController = new PcpServicoEquipamentoGridController();
        gridControl3.setController(equipamentoController);
        gridControl3.setGridDataLocator(equipamentoController);
        
        setModal(true);
        setSize(800, 600);
        
        /*
         * Configurações do lookup do colaborador
         */
        colaboradorLookupController.setLookupValueObjectClassName("com.t2tierp.cadastros.java.ColaboradorVO");
        colaboradorLookupController.addLookup2ParentLink("id", "colaborador.id");
        colaboradorLookupController.addLookup2ParentLink("pessoa.nome", "colaborador.pessoa.nome");
        colaboradorLookupController.setHeaderColumnName("id", "ID");
        colaboradorLookupController.setHeaderColumnName("nome", "Nome");
        colaboradorLookupController.setFrameTitle("Importa Colaborador");

        colaboradorLookupController.setVisibleStatusPanel(true);
        colaboradorLookupController.setVisibleColumn("id", true);
        colaboradorLookupController.setVisibleColumn("nome", true);
        colaboradorLookupController.setFramePreferedSize(new Dimension(600, 500));

        colaboradorLookupController.setLookupDataLocator(new LookupDataLocatorGenerico(colaboradorLookupController.getLookupValueObjectClassName()));
        codLookupColumn1.setLookupController(colaboradorLookupController);

        /*
         * Configurações do lookup do equipamento
         */
        equipamentoLookupController.setLookupValueObjectClassName("com.t2tierp.patrimonio.java.PatrimBemVO");
        equipamentoLookupController.addLookup2ParentLink("id", "patrimBem.id");
        equipamentoLookupController.addLookup2ParentLink("nome", "patrimBem.nome");
        equipamentoLookupController.setHeaderColumnName("id", "ID");
        equipamentoLookupController.setHeaderColumnName("nome", "Nome");
        equipamentoLookupController.setFrameTitle("Importa Equipamento");

        equipamentoLookupController.setVisibleStatusPanel(true);
        equipamentoLookupController.setVisibleColumn("id", true);
        equipamentoLookupController.setVisibleColumn("nome", true);
        equipamentoLookupController.setFramePreferedSize(new Dimension(600, 500));

        equipamentoLookupController.setLookupDataLocator(new LookupDataLocatorGenerico(equipamentoLookupController.getLookupValueObjectClassName()));
        codLookupColumn2.setLookupController(equipamentoLookupController);
        
    }

    public GridControl getGrid1() {
        return gridControl1;
    }

    public PcpServicoColaboradorGridController getColaboradorController() {
        return colaboradorController;
    }

    public PcpServicoEquipamentoGridController getEquipamentoController() {
        return equipamentoController;
    }

    public org.openswing.swing.client.GridControl getGridControl2() {
        return gridControl2;
    }

    public org.openswing.swing.client.GridControl getGridControl3() {
        return gridControl3;
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
        insertButton1 = new org.openswing.swing.client.InsertButton();
        editButton1 = new org.openswing.swing.client.EditButton();
        deleteButton1 = new org.openswing.swing.client.DeleteButton();
        saveButton1 = new org.openswing.swing.client.SaveButton();
        reloadButton1 = new org.openswing.swing.client.ReloadButton();
        navigatorBar1 = new org.openswing.swing.client.NavigatorBar();
        gridControl1 = new org.openswing.swing.client.GridControl();
        dateColumn9 = new org.openswing.swing.table.columns.client.DateColumn();
        integerColumn11 = new org.openswing.swing.table.columns.client.IntegerColumn();
        dateColumn3 = new org.openswing.swing.table.columns.client.DateColumn();
        integerColumn5 = new org.openswing.swing.table.columns.client.IntegerColumn();
        dateColumn10 = new org.openswing.swing.table.columns.client.DateColumn();
        dateColumn4 = new org.openswing.swing.table.columns.client.DateColumn();
        decimalColumn14 = new org.openswing.swing.table.columns.client.DecimalColumn();
        decimalColumn8 = new org.openswing.swing.table.columns.client.DecimalColumn();
        jPanel2 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        insertButton2 = new org.openswing.swing.client.InsertButton();
        editButton2 = new org.openswing.swing.client.EditButton();
        deleteButton2 = new org.openswing.swing.client.DeleteButton();
        reloadButton2 = new org.openswing.swing.client.ReloadButton();
        saveButton2 = new org.openswing.swing.client.SaveButton();
        gridControl2 = new org.openswing.swing.client.GridControl();
        codLookupColumn1 = new org.openswing.swing.table.columns.client.CodLookupColumn();
        jPanel3 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        insertButton3 = new org.openswing.swing.client.InsertButton();
        editButton3 = new org.openswing.swing.client.EditButton();
        deleteButton3 = new org.openswing.swing.client.DeleteButton();
        reloadButton3 = new org.openswing.swing.client.ReloadButton();
        saveButton3 = new org.openswing.swing.client.SaveButton();
        gridControl3 = new org.openswing.swing.client.GridControl();
        codLookupColumn2 = new org.openswing.swing.table.columns.client.CodLookupColumn();

        setTitle("T2Ti ERP - PCP");
        setPreferredSize(new java.awt.Dimension(700, 400));
        getContentPane().setLayout(new java.awt.GridBagLayout());

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Servico"));
        jPanel1.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));
        jPanel1.add(insertButton1);
        jPanel1.add(editButton1);
        jPanel1.add(deleteButton1);
        jPanel1.add(saveButton1);
        jPanel1.add(reloadButton1);
        jPanel1.add(navigatorBar1);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        getContentPane().add(jPanel1, gridBagConstraints);

        gridControl1.setAutoLoadData(false);
        gridControl1.setDeleteButton(deleteButton1);
        gridControl1.setEditButton(editButton1);
        gridControl1.setFunctionId("pcpServico");
        gridControl1.setInsertButton(insertButton1);
        gridControl1.setNavBar(navigatorBar1);
        gridControl1.setReloadButton(reloadButton1);
        gridControl1.setSaveButton(saveButton1);
        gridControl1.setValueObjectClassName("com.t2tierp.pcp.java.PcpServicoVO");
        gridControl1.getColumnContainer().setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        dateColumn9.setColumnName("inicioPrevisto");
        dateColumn9.setEditableOnEdit(true);
        dateColumn9.setEditableOnInsert(true);
        dateColumn9.setHeaderColumnName("Inicio Previsto");
        dateColumn9.setHeaderFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        gridControl1.getColumnContainer().add(dateColumn9);

        integerColumn11.setColumnName("horasPrevisto");
        integerColumn11.setEditableOnEdit(true);
        integerColumn11.setEditableOnInsert(true);
        integerColumn11.setHeaderColumnName("Horas Previsto");
        integerColumn11.setHeaderFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        gridControl1.getColumnContainer().add(integerColumn11);

        dateColumn3.setColumnName("inicioRealizado");
        dateColumn3.setEditableOnEdit(true);
        dateColumn3.setEditableOnInsert(true);
        dateColumn3.setHeaderColumnName("Inicio Realizado");
        dateColumn3.setHeaderFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        gridControl1.getColumnContainer().add(dateColumn3);

        integerColumn5.setColumnName("horasRealizado");
        integerColumn5.setEditableOnEdit(true);
        integerColumn5.setEditableOnInsert(true);
        integerColumn5.setHeaderColumnName("Horas Realizado");
        integerColumn5.setHeaderFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        gridControl1.getColumnContainer().add(integerColumn5);

        dateColumn10.setColumnName("terminoPrevisto");
        dateColumn10.setEditableOnEdit(true);
        dateColumn10.setEditableOnInsert(true);
        dateColumn10.setHeaderColumnName("Termino Previsto");
        dateColumn10.setHeaderFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        dateColumn10.setPreferredWidth(120);
        gridControl1.getColumnContainer().add(dateColumn10);

        dateColumn4.setColumnName("terminoRealizado");
        dateColumn4.setEditableOnEdit(true);
        dateColumn4.setEditableOnInsert(true);
        dateColumn4.setHeaderColumnName("Termino Realizado");
        dateColumn4.setHeaderFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        dateColumn4.setPreferredWidth(120);
        gridControl1.getColumnContainer().add(dateColumn4);

        decimalColumn14.setColumnName("custoPrevisto");
        decimalColumn14.setDecimals(2);
        decimalColumn14.setEditableOnEdit(true);
        decimalColumn14.setEditableOnInsert(true);
        decimalColumn14.setHeaderColumnName("Custo Previsto");
        decimalColumn14.setHeaderFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        gridControl1.getColumnContainer().add(decimalColumn14);

        decimalColumn8.setColumnName("custoRealizado");
        decimalColumn8.setDecimals(2);
        decimalColumn8.setEditableOnEdit(true);
        decimalColumn8.setEditableOnInsert(true);
        decimalColumn8.setHeaderColumnName("Custo Realizado");
        decimalColumn8.setHeaderFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        gridControl1.getColumnContainer().add(decimalColumn8);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        getContentPane().add(gridControl1, gridBagConstraints);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Colaboradores"));
        jPanel2.setLayout(new java.awt.GridBagLayout());

        jPanel4.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));
        jPanel4.add(insertButton2);
        jPanel4.add(editButton2);
        jPanel4.add(deleteButton2);
        jPanel4.add(reloadButton2);
        jPanel4.add(saveButton2);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        jPanel2.add(jPanel4, gridBagConstraints);

        gridControl2.setAutoLoadData(false);
        gridControl2.setDeleteButton(deleteButton2);
        gridControl2.setEditButton(editButton2);
        gridControl2.setFunctionId("pcpServicoColaborador");
        gridControl2.setInsertButton(insertButton2);
        gridControl2.setReloadButton(reloadButton2);
        gridControl2.setSaveButton(saveButton2);
        gridControl2.setValueObjectClassName("com.t2tierp.pcp.java.PcpServicoColaboradorVO");

        codLookupColumn1.setColumnName("colaborador.pessoa.nome");
        codLookupColumn1.setEditableOnEdit(true);
        codLookupColumn1.setEditableOnInsert(true);
        codLookupColumn1.setHeaderColumnName("Colaborador");
        codLookupColumn1.setHeaderFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        codLookupColumn1.setPreferredWidth(250);
        gridControl2.getColumnContainer().add(codLookupColumn1);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel2.add(gridControl2, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        getContentPane().add(jPanel2, gridBagConstraints);

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Equipamentos"));
        jPanel3.setLayout(new java.awt.GridBagLayout());

        jPanel5.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));
        jPanel5.add(insertButton3);
        jPanel5.add(editButton3);
        jPanel5.add(deleteButton3);
        jPanel5.add(reloadButton3);
        jPanel5.add(saveButton3);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        jPanel3.add(jPanel5, gridBagConstraints);

        gridControl3.setAutoLoadData(false);
        gridControl3.setDeleteButton(deleteButton3);
        gridControl3.setEditButton(editButton3);
        gridControl3.setFunctionId("pcpServicoEquipamento");
        gridControl3.setInsertButton(insertButton3);
        gridControl3.setReloadButton(reloadButton3);
        gridControl3.setSaveButton(saveButton3);
        gridControl3.setValueObjectClassName("com.t2tierp.pcp.java.PcpServicoEquipamentoVO");

        codLookupColumn2.setColumnName("patrimBem.nome");
        codLookupColumn2.setEditableOnEdit(true);
        codLookupColumn2.setEditableOnInsert(true);
        codLookupColumn2.setHeaderColumnName("Equipamento");
        codLookupColumn2.setHeaderFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        codLookupColumn2.setPreferredWidth(250);
        gridControl3.getColumnContainer().add(codLookupColumn2);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel3.add(gridControl3, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        getContentPane().add(jPanel3, gridBagConstraints);

        pack();
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private org.openswing.swing.table.columns.client.CodLookupColumn codLookupColumn1;
    private org.openswing.swing.table.columns.client.CodLookupColumn codLookupColumn2;
    private org.openswing.swing.table.columns.client.DateColumn dateColumn10;
    private org.openswing.swing.table.columns.client.DateColumn dateColumn3;
    private org.openswing.swing.table.columns.client.DateColumn dateColumn4;
    private org.openswing.swing.table.columns.client.DateColumn dateColumn9;
    private org.openswing.swing.table.columns.client.DecimalColumn decimalColumn14;
    private org.openswing.swing.table.columns.client.DecimalColumn decimalColumn8;
    private org.openswing.swing.client.DeleteButton deleteButton1;
    private org.openswing.swing.client.DeleteButton deleteButton2;
    private org.openswing.swing.client.DeleteButton deleteButton3;
    private org.openswing.swing.client.EditButton editButton1;
    private org.openswing.swing.client.EditButton editButton2;
    private org.openswing.swing.client.EditButton editButton3;
    private org.openswing.swing.client.GridControl gridControl1;
    private org.openswing.swing.client.GridControl gridControl2;
    private org.openswing.swing.client.GridControl gridControl3;
    private org.openswing.swing.client.InsertButton insertButton1;
    private org.openswing.swing.client.InsertButton insertButton2;
    private org.openswing.swing.client.InsertButton insertButton3;
    private org.openswing.swing.table.columns.client.IntegerColumn integerColumn11;
    private org.openswing.swing.table.columns.client.IntegerColumn integerColumn5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private org.openswing.swing.client.NavigatorBar navigatorBar1;
    private org.openswing.swing.client.ReloadButton reloadButton1;
    private org.openswing.swing.client.ReloadButton reloadButton2;
    private org.openswing.swing.client.ReloadButton reloadButton3;
    private org.openswing.swing.client.SaveButton saveButton1;
    private org.openswing.swing.client.SaveButton saveButton2;
    private org.openswing.swing.client.SaveButton saveButton3;
    // End of variables declaration//GEN-END:variables

}
