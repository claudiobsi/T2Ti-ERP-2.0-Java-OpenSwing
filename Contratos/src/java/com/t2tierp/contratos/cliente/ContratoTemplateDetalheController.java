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
package com.t2tierp.contratos.cliente;

import com.sun.star.beans.PropertyValue;
import com.sun.star.document.EventObject;
import com.sun.star.document.XEventListener;
import com.sun.star.frame.XComponentLoader;
import com.sun.star.frame.XDesktop;
import com.sun.star.frame.XStorable;
import com.sun.star.lang.XComponent;
import com.sun.star.lang.XMultiComponentFactory;
import com.sun.star.lib.loader.InstallationFinder;
import com.sun.star.uno.UnoRuntime;
import com.sun.star.uno.XComponentContext;
import com.t2tierp.cadastros.java.EmpresaVO;
import com.t2tierp.contratos.java.ContratoTemplateVO;
import com.t2tierp.ged.java.ArquivoVO;
import com.t2tierp.padrao.cliente.Container;
import com.t2tierp.padrao.java.Constantes;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.swing.JOptionPane;
import ooo.connector.BootstrapSocketConnector;
import org.openswing.swing.form.client.FormController;
import org.openswing.swing.mdi.client.MDIFrame;
import org.openswing.swing.message.receive.java.ErrorResponse;
import org.openswing.swing.message.receive.java.Response;
import org.openswing.swing.message.receive.java.VOResponse;
import org.openswing.swing.message.receive.java.ValueObject;
import org.openswing.swing.util.client.ClientUtils;
import org.openswing.swing.util.java.Consts;

public class ContratoTemplateDetalheController extends FormController {

    private ContratoTemplateDetalhe contratoTemplateDetalhe = null;
    private String pk = null;
    private ContratoTemplateGrid contratoTemplateGrid = null;
    private String acaoServidor;
    private String nomeArquivoTemporario;

    public ContratoTemplateDetalheController(ContratoTemplateGrid contratoTemplateGrid, String pk) {
        this.contratoTemplateGrid = contratoTemplateGrid;
        this.pk = pk;
        this.acaoServidor = "contratoTemplateDetalheAction";
        contratoTemplateDetalhe = new ContratoTemplateDetalhe(this);
        contratoTemplateDetalhe.setParentFrame(this.contratoTemplateGrid);
        this.contratoTemplateGrid.pushFrame(contratoTemplateDetalhe);
        MDIFrame.add(contratoTemplateDetalhe);
        
        //nomeArquivoTemporario = "file:///" + System.getProperty("user.dir") + System.getProperty("file.separator") + "temp.odt";
        nomeArquivoTemporario = "file:///c:" + System.getProperty("file.separator") + "t2ti" + System.getProperty("file.separator") + "template.odt";
        
        excluiArquivoTemporario();

        if (pk != null) {
            contratoTemplateDetalhe.getForm1().setMode(Consts.READONLY);
            contratoTemplateDetalhe.getForm1().reload();
        } else {
            contratoTemplateDetalhe.getForm1().setMode(Consts.INSERT);
        }
    }

    @Override
    public Response loadData(Class valueObjectClass) {
        return ClientUtils.getData(acaoServidor, new Object[]{Constantes.LOAD, pk});
    }

    @Override
    public Response insertRecord(ValueObject newPersistentObject) throws Exception {
        EmpresaVO empresa = (EmpresaVO) Container.getContainer().get("empresa");

        ((ContratoTemplateVO) newPersistentObject).setEmpresa(empresa);

        ArquivoVO arquivo;
        try {
            arquivo = getArquivo();
        } catch (Exception e) {
            return new ErrorResponse(e.getMessage());
        }

        return ClientUtils.getData(acaoServidor, new Object[]{Constantes.INSERT, newPersistentObject, arquivo});
    }

    @Override
    public void afterInsertData() {
        contratoTemplateGrid.getGrid1().reloadData();
        JOptionPane.showMessageDialog(contratoTemplateDetalhe, "Dados salvos com sucesso!", "Informacao do Sistema", JOptionPane.INFORMATION_MESSAGE);
    }

    @Override
    public Response updateRecord(ValueObject oldPersistentObject, ValueObject persistentObject) throws Exception {
        ArquivoVO arquivo;
        try {
            arquivo = getArquivo();
        } catch (Exception e) {
            return new ErrorResponse(e.getMessage());
        }

        return ClientUtils.getData(acaoServidor, new Object[]{Constantes.UPDATE, oldPersistentObject, persistentObject, arquivo});
    }

    @Override
    public void afterEditData() {
        contratoTemplateGrid.getGrid1().reloadData();
        JOptionPane.showMessageDialog(contratoTemplateDetalhe, "Dados alterados com sucesso!", "Informacao do Sistema", JOptionPane.INFORMATION_MESSAGE);
    }

    public void abreTemplate() throws Exception {
        //verifica o diretorio onde está instalado o OpenOffice
        String localInstalacaoOffice = InstallationFinder.getPath();
        if (localInstalacaoOffice == null || localInstalacaoOffice.equals("")) {
            throw new Exception("Instalação do OpenOffice não localizada!");
        }

        //realiza o download do template se já existir ou cria um novo
        if (contratoTemplateDetalhe.getForm1().getMode() == Consts.INSERT) {
            abreArquivo(localInstalacaoOffice, true);
        } else if (contratoTemplateDetalhe.getForm1().getMode() == Consts.EDIT) {
            downloadArquivo();
            abreArquivo(localInstalacaoOffice, false);
        } else {
            throw new Exception("O formulário está em modo somente consulta!\nAntes de alterar o template clique no botão \"Editar Registro\".");
        }
    }

    private void abreArquivo(String localInstalacaoOffice, boolean novo) throws Exception {
        XComponentContext xContext = BootstrapSocketConnector.bootstrap(localInstalacaoOffice);
        XMultiComponentFactory xRemoteServiceManager = xContext.getServiceManager();
        Object desktop = xRemoteServiceManager.createInstanceWithContext("com.sun.star.frame.Desktop", xContext);

        XDesktop xDesktop = (XDesktop) UnoRuntime.queryInterface(XDesktop.class, desktop);

        XComponentLoader xComponentLoader = (XComponentLoader) UnoRuntime.queryInterface(XComponentLoader.class, xDesktop);

        String urlArquivo = nomeArquivoTemporario;
        if (novo) {
            urlArquivo = "private:factory/swriter";
        }

        urlArquivo = urlArquivo.replace('\\', '/');
        XComponent xComponent = xComponentLoader.loadComponentFromURL(urlArquivo, "_blank", 0, new PropertyValue[0]);

        //salva o arquivo
        if (novo) {
            XStorable xStorable = (XStorable) UnoRuntime.queryInterface(XStorable.class, xComponent);

            PropertyValue propertyValue[] = new PropertyValue[1];
            propertyValue[0] = new PropertyValue();
            propertyValue[0].Name = "Overwrite";
            propertyValue[0].Value = new Boolean(true);

            urlArquivo = nomeArquivoTemporario;
            urlArquivo = urlArquivo.replace('\\', '/');

            xStorable.storeAsURL(urlArquivo, propertyValue);
        }

        final EdicaoTemplateDialog dialog = new EdicaoTemplateDialog(MDIFrame.getInstance(), true);

        xComponent.addEventListener(new XEventListener() {

            public void notifyEvent(EventObject eo) {
            }

            public void disposing(com.sun.star.lang.EventObject eo) {
                dialog.dispose();
            }
        });

        dialog.setVisible(true);

    }

    private void downloadArquivo() throws Exception {
        ContratoTemplateVO contratoTemplate = (ContratoTemplateVO) contratoTemplateDetalhe.getForm1().getVOModel().getValueObject();
        Response res = ClientUtils.getData(acaoServidor, new Object[]{Constantes.DOWNLOAD, contratoTemplate.getId()});

        if (res.isError()) {
            throw new Exception(res.getErrorMessage());
        }

        ArquivoVO arquivo = (ArquivoVO) ((VOResponse) res).getVo();

        if (arquivo != null) {
            if (arquivo.getFile() != null) {
                File file = new File(nomeArquivoTemporario.substring(8));
                FileOutputStream out = new FileOutputStream(file.getAbsolutePath());
                byte[] bb = arquivo.getFile();
                out.write(bb);
                out.close();
            } else {
                throw new Exception("Arquivo não encontrado!");
            }
        } else {
            throw new Exception("Arquivo não encontrado!");
        }
    }

    private ArquivoVO getArquivo() throws Exception {
        //seta os dados do arquivo
        ArquivoVO arquivo = new ArquivoVO();
        File file = new File(nomeArquivoTemporario.substring(8));

        if (!file.exists()) {
            throw new Exception("Arquivo de template não localizado!\nClique no botão \"Editar Template\" para criar ou editar o arquivo.");
        }
        arquivo.setFile(getBytesFromFile(file));

        return arquivo;
    }

    private byte[] getBytesFromFile(File file) throws Exception {
        //converte o arquio em array de bytes
        InputStream is = new FileInputStream(file);
        // Get the size of the file
        long length = file.length();
        // Create the byte array to hold the data
        byte[] documento = new byte[(int) length];
        // Read in the bytes
        int offset = 0;
        int numRead = 0;
        while (offset < documento.length
                && (numRead = is.read(documento, offset, documento.length - offset)) >= 0) {
            offset += numRead;
        }
        // Ensure all the bytes have been read in
        if (offset < documento.length) {
            throw new IOException("Could not completely read file " + file.getName());
        }
        // Close the input stream and return bytes
        is.close();
        return documento;
    }

    private void excluiArquivoTemporario() {
        try {
            File file = new File(nomeArquivoTemporario.substring(8));
            if (file.exists()) {
                file.delete();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
