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

import com.t2tierp.cadastros.java.EmpresaVO;
import com.t2tierp.ged.java.ArquivoVO;
import com.t2tierp.ged.java.GedDocumentoCabecalhoVO;
import com.t2tierp.ged.java.GedDocumentoDetalheVO;
import com.t2tierp.padrao.cliente.Container;
import com.t2tierp.padrao.cliente.SelecionaCertificado;
import com.t2tierp.padrao.java.Biblioteca;
import com.t2tierp.padrao.java.Certificado;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import org.openswing.swing.client.GridControl;
import org.openswing.swing.message.receive.java.Response;
import org.openswing.swing.message.receive.java.VOListResponse;
import org.openswing.swing.table.client.GridController;
import org.openswing.swing.table.java.GridDataLocator;
import org.openswing.swing.util.client.ClientUtils;
import org.openswing.swing.util.java.Consts;

public class GedDocumentoDetalheGridController extends GridController implements GridDataLocator {

    private GedDocumentoDetalhe gedDocumentoDetalhe;
    private GedDocumentoCabecalhoVO gedDocumento;
    private ArquivoVO arquivo;
    private GedDocumentoDetalheVO detalheExcluido;

    public GedDocumentoDetalheGridController(GedDocumentoDetalhe gedDocumentoDetalhe) {
        this.gedDocumentoDetalhe = gedDocumentoDetalhe;
    }

    public Response loadData(int action, int startIndex, Map filteredColumns, ArrayList currentSortedColumns, ArrayList currentSortedVersusColumns, Class valueObjectType, Map otherGridParams) {
        if (gedDocumento != null && gedDocumento.getListaGedDocumentoDetalhe() != null) {
            return new VOListResponse(gedDocumento.getListaGedDocumentoDetalhe(), false, gedDocumento.getListaGedDocumentoDetalhe().size());
        } else {
            return new VOListResponse();
        }
    }

    @Override
    public boolean beforeInsertGrid(GridControl grid) {
        if (gedDocumentoDetalhe.getForm1().getMode() == Consts.READONLY) {
            gedDocumentoDetalhe.getForm1().setMode(Consts.EDIT);
        }
        return true;
    }

    @Override
    public Response insertRecords(int[] rowNumbers, ArrayList newValueObjects) throws Exception {
        if (arquivo == null || arquivo.getFile() == null) {
            throw new Exception("Nenhum documento selecionado");
        }
        EmpresaVO empresa = (EmpresaVO) Container.getContainer().get("empresa");
        GedDocumentoDetalheVO detalhe = (GedDocumentoDetalheVO) newValueObjects.get(0);

        detalhe.setArquivo(getArquivo());
        detalhe.setEmpresa(empresa);
        if (detalhe.getAssinado().equals("S")) {
            detalhe.getArquivo().setAssinatura(getAssinaturaDocumento(detalhe.getArquivo()));
        }
        return new VOListResponse(newValueObjects, false, newValueObjects.size());
    }

    @Override
    public boolean beforeEditGrid(GridControl grid) {
        try {
            if (getItemSelecionado().getPodeAlterar().equals("N")) {
                JOptionPane.showMessageDialog(gedDocumentoDetalhe, "Este documento não pode ser alterado.", "Informação do Sistema", JOptionPane.INFORMATION_MESSAGE);
            } else {
                if (gedDocumentoDetalhe.getForm1().getMode() == Consts.READONLY) {
                    gedDocumentoDetalhe.getForm1().setMode(Consts.EDIT);
                }
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Response updateRecords(int[] rowNumbers, ArrayList oldPersistentObjects, ArrayList persistentObjects) throws Exception {
        GedDocumentoDetalheVO detalhe = (GedDocumentoDetalheVO) persistentObjects.get(0);
        if (arquivo != null && arquivo.getFile() != null) {
            detalhe.setArquivo(getArquivo());
        }
        if (detalhe.getAssinado().equals("S")) {
            detalhe.getArquivo().setAssinatura(getAssinaturaDocumento(detalhe.getArquivo()));
        }

        return new VOListResponse(persistentObjects, false, persistentObjects.size());
    }

    @Override
    public boolean beforeDeleteGrid(GridControl grid) {
        try {
            if (getItemSelecionado().getPodeExcluir().equals("N")) {
                JOptionPane.showMessageDialog(gedDocumentoDetalhe, "Este documento não pode ser excluído.", "Informação do Sistema", JOptionPane.INFORMATION_MESSAGE);
            } else {
                if (gedDocumentoDetalhe.getForm1().getMode() == Consts.READONLY) {
                    gedDocumentoDetalhe.getForm1().setMode(Consts.EDIT);
                }
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Response deleteRecords(ArrayList persistentObjects) throws Exception {
        detalheExcluido = (GedDocumentoDetalheVO) persistentObjects.get(0);
        detalheExcluido.setDataExclusao(new Date());
        return new VOListResponse();
    }

    @Override
    public void afterDeleteGrid() {
        gedDocumentoDetalhe.getGridControl1().getVOListTableModel().addObject(detalheExcluido);
    }

    public void setGedDocumento(GedDocumentoCabecalhoVO gedDocumento) {
        this.gedDocumento = gedDocumento;
    }

    public void selecionaDocumento() {
        try {
            JFileChooser file = new JFileChooser();
            file.setFileSelectionMode(JFileChooser.FILES_ONLY);
            int resposta = file.showOpenDialog(gedDocumentoDetalhe);

            if (resposta == JFileChooser.APPROVE_OPTION) {
                arquivo = new ArquivoVO();
                arquivo.setFile(Biblioteca.getBytesFromFile(file.getSelectedFile()));
                arquivo.setExtensao(getExtensaoArquivo(file.getSelectedFile().getAbsolutePath()));
                arquivo.setHash(getHashDocumento(arquivo));

                //tenta carregar a imagem no imagePanel
                try {
                    gedDocumentoDetalhe.setImagem(ImageIO.read(file.getSelectedFile()));
                } catch (Exception e) {
                    //e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(gedDocumentoDetalhe, "Ocorreu um erro!\n" + e.getMessage(), "Erro do Sistema", JOptionPane.ERROR_MESSAGE);
        }
    }

    private String getExtensaoArquivo(String caminhoArquivo) {
        if (!caminhoArquivo.equals("")) {
            int indiceExtensao = caminhoArquivo.lastIndexOf(".");
            if (indiceExtensao > -1) {
                return caminhoArquivo.substring(indiceExtensao, caminhoArquivo.length());
            }
        }
        return "";
    }

    public void downloadDocumento() {
        try {
            GedDocumentoDetalheVO detalhe = getItemSelecionado();

            JFileChooser file = new JFileChooser();
            file.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            int resposta = file.showSaveDialog(gedDocumentoDetalhe);
            if (resposta == JFileChooser.APPROVE_OPTION) {
                String pasta = file.getSelectedFile().getAbsolutePath() + System.getProperty("file.separator");
                File copia = new File(pasta + detalhe.getNome() + detalhe.getArquivo().getExtensao());
                Files.write(Paths.get(copia.toURI()), detalhe.getArquivo().getFile());
                JOptionPane.showMessageDialog(gedDocumentoDetalhe, "Arquivo salvo com sucesso!", "Informação do Sistema", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(gedDocumentoDetalhe, "Ocorreu um erro ao salvar o arquivo!\n" + e.getMessage(), "Erro do Sistema", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void visualizaDocumento() {
        try {
            GedDocumentoDetalheVO detalhe = getItemSelecionado();

            File file = File.createTempFile(detalhe.getNome(), detalhe.getArquivo().getExtensao());
            Files.write(Paths.get(file.toURI()), detalhe.getArquivo().getFile());

            ClientUtils.displayURL(file.getAbsolutePath());
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(gedDocumentoDetalhe, "Ocorreu um erro ao abrir o arquivo!\n" + e.getMessage(), "Erro do Sistema", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void digitalizaDocumento() {
        try {
            DigitalizaDocumento dd;
            dd = new DigitalizaDocumento();
            Object documento = dd.getDocumento();

            if (documento != null) {
                if (documento instanceof Exception) {
                    throw (Exception) documento;
                }
                if (documento instanceof BufferedImage) {
                    //converte a imagem digitalizada em bytes
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    ImageIO.write((BufferedImage) documento, "jpg", baos);
                    byte[] bytesOut = baos.toByteArray();

                    arquivo = new ArquivoVO();
                    arquivo.setFile(bytesOut);
                    arquivo.setExtensao(".jpg");
                    arquivo.setHash(getHashDocumento(arquivo));

                    //tenta carregar a imagem no imagePanel
                    try {
                        gedDocumentoDetalhe.setImagem((BufferedImage) documento);
                    } catch (Exception e) {
                        //e.printStackTrace();
                    }
                }
                if (documento instanceof File) {
                    File file = (File) documento;
                    arquivo = new ArquivoVO();
                    arquivo.setFile(Biblioteca.getBytesFromFile(file));
                    arquivo.setExtensao(getExtensaoArquivo(file.getAbsolutePath()));
                    arquivo.setHash(getHashDocumento(arquivo));
                }
            } else {
                throw new Exception("Imagem não obtida no dispositivo");
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(gedDocumentoDetalhe, "Ocorreu um erro!\n" + e.getMessage(), "Erro do Sistema", JOptionPane.ERROR_MESSAGE);
        } finally {

        }
    }

    private String getHashDocumento(ArquivoVO arquivo) throws Exception {
        File file = File.createTempFile("arquivo", arquivo.getExtensao());
        file.deleteOnExit();
        Files.write(Paths.get(file.toURI()), arquivo.getFile());

        return Biblioteca.md5Arquivo(file.getAbsolutePath());
    }

    private GedDocumentoDetalheVO getItemSelecionado() throws Exception {
        if (gedDocumentoDetalhe.getGridControl1().getSelectedRow() == -1) {
            throw new Exception("Nenhum item selecionado");
        }
        return (GedDocumentoDetalheVO) gedDocumentoDetalhe.getGridControl1().getVOListTableModel().getObjectForRow(gedDocumentoDetalhe.getGridControl1().getSelectedRow());
    }

    private ArquivoVO getArquivo() {
        ArquivoVO arq = new ArquivoVO();
        arq.setFile(arquivo.getFile());
        arq.setExtensao(arquivo.getExtensao());
        arq.setTamanho(arquivo.getTamanho());
        arq.setHash(arquivo.getHash());
        arq.setAssinatura(arquivo.getAssinatura());

        arquivo = null;

        return arq;
    }

    private byte[] getAssinaturaDocumento(ArquivoVO arquivo) throws Exception {
        SelecionaCertificado selecionaCertificado = new SelecionaCertificado(null, true);
        selecionaCertificado.setVisible(true);
        Certificado certificado = selecionaCertificado.getDadosCertificado();

        File file = File.createTempFile("certificado", ".pfx");
        Files.write(Paths.get(file.toURI()), certificado.getArquivo());

        return Biblioteca.geraAssinaturaArquivo(arquivo.getFile(), file, certificado.getSenha());
    }
}
