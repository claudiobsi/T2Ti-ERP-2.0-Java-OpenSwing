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
package com.t2tierp.padrao.cliente;

import com.t2tierp.padrao.java.Biblioteca;
import com.t2tierp.padrao.java.Certificado;
import java.io.File;
import java.io.FileInputStream;
import java.security.KeyStore;
import javax.swing.filechooser.FileFilter;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class SelecionaCertificado extends javax.swing.JDialog {

    private boolean cancelado;
    private Certificado certificado;

    /** Creates new form SelecionaCertificado */
    public SelecionaCertificado(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setSize(500, 300);
        setLocationRelativeTo(null);
        cancelado = false;
    }

    private void selecionaCertificado() {
        FileFilter filter = new FileFilter() {

            @Override
            public boolean accept(File f) {
                String arquivo = f.getName().toLowerCase();
                return f.isDirectory()
                        || arquivo.endsWith(".p12")
                        || arquivo.endsWith(".pfx");
            }

            @Override
            public String getDescription() {
                return "*.p12;*.pfx";
            }
        };

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(filter);
        fileChooser.showOpenDialog(this);
        File file = fileChooser.getSelectedFile();

        if (file != null) {
            txtCaminhoCertificado.setText(file.getAbsolutePath());
        }
    }

    public Certificado getDadosCertificado() {
        return certificado;
    }

    public void confirma() {
        if (jRadioButtonA1.isSelected()) {
            if (txtCaminhoCertificado.getText().trim().equals("")) {
                JOptionPane.showMessageDialog(this, "Selecione o arquivo do certificado!", "Informação do Sistema", JOptionPane.WARNING_MESSAGE);
            } else if (txtSenha.getPassword().length == 0) {
                JOptionPane.showMessageDialog(this, "Informe a senha do certificado!", "Informação do Sistema", JOptionPane.WARNING_MESSAGE);
                txtSenha.requestFocus();
            } else {
                try {
                    KeyStore ks = KeyStore.getInstance("PKCS12");
                    ks.load(new FileInputStream(txtCaminhoCertificado.getText()), txtSenha.getPassword());
                    ks.aliases().nextElement();
                    
                    certificado = new Certificado();
                    certificado.setArquivo(Biblioteca.getBytesFromFile(new File(txtCaminhoCertificado.getText())));
                    certificado.setSenha(txtSenha.getPassword());
                    this.dispose();
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(this, "Ocorreu um erro ao carregar os dados do certificado A1.\n" + e.getMessage(), "Erro do Sistema", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
        /* else if (jRadioButtonA3.isSelected()) {
            if (txtSenha.getPassword().length == 0) {
                JOptionPane.showMessageDialog(this, "Informe a senha do certificado!", "Informação do Sistema", JOptionPane.WARNING_MESSAGE);
                txtSenha.requestFocus();
            } else {
                try {
                    //carrega o certificado A3
                    Provider p = new SunPKCS11(this.getClass().getResourceAsStream("/token.cfg"));
                    Security.addProvider(p);
                    ks = KeyStore.getInstance("PKCS11");
                    ks.load(null, txtSenha.getPassword());
                    alias = ks.aliases().nextElement();
                    this.dispose();
                } catch (Exception e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(this, "Ocorreu um erro ao carregar os dados do certificado A3.\n" + e.getMessage(), "Erro do Sistema", JOptionPane.ERROR_MESSAGE);
                }
            }
        } else if (jRadioButtonWindows.isSelected()) {
            if (cboCertificado.getSelectedIndex() == -1) {
                JOptionPane.showMessageDialog(this, "Selecione um certificado da lista.", "Informação do Sistema", JOptionPane.INFORMATION_MESSAGE);
            } else {
                alias = cboCertificado.getSelectedItem().toString();
                this.dispose();
            }
        }*/
    }

    public boolean isCancelado() {
        return cancelado;
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

        buttonGroup1 = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        txtCaminhoCertificado = new javax.swing.JTextField();
        btnSelecionaCertificado = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        txtSenha = new javax.swing.JPasswordField();
        jPanel1 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jRadioButtonWindows = new javax.swing.JRadioButton();
        jRadioButtonA1 = new javax.swing.JRadioButton();
        cboCertificado = new javax.swing.JComboBox();
        jLabel3 = new javax.swing.JLabel();
        jRadioButtonA3 = new javax.swing.JRadioButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Selecionar Certificado Digital");
        setModal(true);
        getContentPane().setLayout(new java.awt.GridBagLayout());

        jLabel1.setText("Caminho do Certificado:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        getContentPane().add(jLabel1, gridBagConstraints);

        txtCaminhoCertificado.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        getContentPane().add(txtCaminhoCertificado, gridBagConstraints);

        btnSelecionaCertificado.setText("...");
        btnSelecionaCertificado.setToolTipText("Selecionar Certificado");
        btnSelecionaCertificado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSelecionaCertificadoActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        getContentPane().add(btnSelecionaCertificado, gridBagConstraints);

        jLabel2.setText("Senha:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        getContentPane().add(jLabel2, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 9;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        getContentPane().add(txtSenha, gridBagConstraints);

        jButton1.setText("OK");
        jButton1.setPreferredSize(new java.awt.Dimension(75, 23));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1);

        jButton2.setText("Cancelar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        getContentPane().add(jPanel1, gridBagConstraints);

        buttonGroup1.add(jRadioButtonWindows);
        jRadioButtonWindows.setText("Armazém do Windows");
        jRadioButtonWindows.setEnabled(false);
        jRadioButtonWindows.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonWindowsActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        getContentPane().add(jRadioButtonWindows, gridBagConstraints);

        buttonGroup1.add(jRadioButtonA1);
        jRadioButtonA1.setSelected(true);
        jRadioButtonA1.setText("Certificado A1:");
        jRadioButtonA1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonA1ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        getContentPane().add(jRadioButtonA1, gridBagConstraints);

        cboCertificado.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        getContentPane().add(cboCertificado, gridBagConstraints);

        jLabel3.setText("Selecione o Certificado:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        getContentPane().add(jLabel3, gridBagConstraints);

        buttonGroup1.add(jRadioButtonA3);
        jRadioButtonA3.setText("Certificado A3");
        jRadioButtonA3.setEnabled(false);
        jRadioButtonA3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonA3ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        getContentPane().add(jRadioButtonA3, gridBagConstraints);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSelecionaCertificadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSelecionaCertificadoActionPerformed
        selecionaCertificado();
    }//GEN-LAST:event_btnSelecionaCertificadoActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        confirma();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        cancelado = true;
        this.dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jRadioButtonWindowsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonWindowsActionPerformed
        try {
            /*cboCertificado.setEnabled(true);
            txtCaminhoCertificado.setEnabled(false);
            txtSenha.setEnabled(false);
            cboCertificado.removeAllItems();
            //carrega os certificados contidos no armazém de certificados do windows
            ks = KeyStore.getInstance("Windows-MY", "SunMSCAPI");
            ks.load(null, null);
            Enumeration<String> aliases = ks.aliases();
            while (aliases.hasMoreElements()) {
                cboCertificado.addItem(aliases.nextElement());
            }*/
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Ocorreu um erro ao carregar os certificados do Windows.\n" + e.getMessage(), "Erro do Sistema", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jRadioButtonWindowsActionPerformed

    private void jRadioButtonA1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonA1ActionPerformed
        cboCertificado.setEnabled(false);
        txtCaminhoCertificado.setEnabled(true);
        txtSenha.setEnabled(true);
    }//GEN-LAST:event_jRadioButtonA1ActionPerformed

    private void jRadioButtonA3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonA3ActionPerformed
        cboCertificado.setEnabled(false);
        txtCaminhoCertificado.setEnabled(false);
        txtSenha.setEnabled(true);
    }//GEN-LAST:event_jRadioButtonA3ActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSelecionaCertificado;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox cboCertificado;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JRadioButton jRadioButtonA1;
    private javax.swing.JRadioButton jRadioButtonA3;
    private javax.swing.JRadioButton jRadioButtonWindows;
    private javax.swing.JTextField txtCaminhoCertificado;
    private javax.swing.JPasswordField txtSenha;
    // End of variables declaration//GEN-END:variables
}
