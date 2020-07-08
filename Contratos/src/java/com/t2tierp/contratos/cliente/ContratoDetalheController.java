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
import com.sun.star.lang.XComponent;
import com.sun.star.lang.XMultiComponentFactory;
import com.sun.star.lib.loader.InstallationFinder;
import com.sun.star.uno.UnoRuntime;
import com.sun.star.uno.XComponentContext;
import com.sun.star.util.XReplaceDescriptor;
import com.sun.star.util.XReplaceable;
import com.t2tierp.cadastros.java.EmpresaEnderecoVO;
import com.t2tierp.cadastros.java.EmpresaVO;
import com.t2tierp.contratos.java.ContratoHistFaturamentoVO;
import com.t2tierp.contratos.java.ContratoHistoricoReajusteVO;
import com.t2tierp.contratos.java.ContratoPrevFaturamentoVO;
import com.t2tierp.contratos.java.ContratoVO;
import com.t2tierp.contratos.java.ViewContratoDadosContratanteVO;
import com.t2tierp.ged.java.ArquivoVO;
import com.t2tierp.padrao.cliente.Container;
import com.t2tierp.padrao.java.Constantes;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.text.MaskFormatter;
import ooo.connector.BootstrapSocketConnector;
import org.openswing.swing.form.client.FormController;
import org.openswing.swing.mdi.client.MDIFrame;
import org.openswing.swing.message.receive.java.ErrorResponse;
import org.openswing.swing.message.receive.java.Response;
import org.openswing.swing.message.receive.java.VOResponse;
import org.openswing.swing.message.receive.java.ValueObject;
import org.openswing.swing.util.client.ClientUtils;
import org.openswing.swing.util.java.Consts;

public class ContratoDetalheController extends FormController {

    private ContratoDetalhe contratoDetalhe = null;
    private String pk = null;
    private ContratoGrid contratoGrid = null;
    private String acaoServidor;
    private String nomeArquivoTemporario;

    public ContratoDetalheController(ContratoGrid contratoGrid, String pk) {
        this.contratoGrid = contratoGrid;
        this.pk = pk;
        this.acaoServidor = "contratoDetalheAction";
        contratoDetalhe = new ContratoDetalhe(this);
        contratoDetalhe.setParentFrame(this.contratoGrid);
        this.contratoGrid.pushFrame(contratoDetalhe);
        MDIFrame.add(contratoDetalhe, true);
        
        //nomeArquivoTemporario = "file:///" + System.getProperty("user.dir") + System.getProperty("file.separator") + "temp.odt";
        nomeArquivoTemporario = "file:///c:" + System.getProperty("file.separator") + "t2ti" + System.getProperty("file.separator") + "template.odt";

        excluiArquivoTemporario();

        if (pk != null) {
            contratoDetalhe.getForm1().setMode(Consts.READONLY);
            contratoDetalhe.getForm1().reload();
        } else {
            contratoDetalhe.getForm1().setMode(Consts.INSERT);
            contratoDetalhe.getGridHistoricoFaturamento().reloadData();
            contratoDetalhe.getGridPrevisaoFaturamento().reloadData();
            contratoDetalhe.getGridHistoricoReajuste().reloadData();
        }
    }

    @Override
    public Response loadData(Class valueObjectClass) {
        return ClientUtils.getData(acaoServidor, new Object[]{Constantes.LOAD, pk});
    }

    @Override
    public void loadDataCompleted(boolean error) {
        contratoDetalhe.getHistoricoFaturamentoController().setPk(pk);
        contratoDetalhe.getGridHistoricoFaturamento().reloadData();

        contratoDetalhe.getPrevisaoFaturamentoController().setPk(pk);
        contratoDetalhe.getGridPrevisaoFaturamento().reloadData();

        contratoDetalhe.getHistoricoReajusteController().setPk(pk);
        contratoDetalhe.getGridHistoricoReajuste().reloadData();
    }
    
    @Override
    public Response insertRecord(ValueObject newPersistentObject) throws Exception {
        List<ContratoHistFaturamentoVO> historicoFaturamento = contratoDetalhe.getGridHistoricoFaturamento().getVOListTableModel().getDataVector();
        List<ContratoHistoricoReajusteVO> historicoReajuste = contratoDetalhe.getGridHistoricoReajuste().getVOListTableModel().getDataVector();
        List<ContratoPrevFaturamentoVO> previsaoFaturamento = contratoDetalhe.getGridPrevisaoFaturamento().getVOListTableModel().getDataVector();

        ArquivoVO arquivo;
        try {
            arquivo = getArquivo();
        } catch (Exception e) {
            return new ErrorResponse(e.getMessage());
        }

        return ClientUtils.getData(acaoServidor, new Object[]{Constantes.INSERT, newPersistentObject, historicoFaturamento, historicoReajuste, previsaoFaturamento, arquivo});
    }

    @Override
    public void afterInsertData() {
        contratoGrid.getGrid1().reloadData();
        ContratoVO contrato = (ContratoVO) contratoDetalhe.getForm1().getVOModel().getValueObject();
        try {
            if ((contrato.getContratoSolicitacaoServico().getCliente() != null) && (getArquivo().getFile() != null)) {
                if (JOptionPane.showConfirmDialog(contratoDetalhe, "Dados salvos com sucesso!\nDeseja acionar o ged para armazenar o documento?", "Informacao do Sistema", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                    acionaGed(contrato);
                }
            } else if ((contrato.getContratoSolicitacaoServico().getFornecedor() != null)) {
                if (JOptionPane.showConfirmDialog(contratoDetalhe, "Dados salvos com sucesso!\nDeseja acionar o ged para armazenar o documento?", "Informacao do Sistema", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                    acionaGed(contrato);
                }
            } else {
                JOptionPane.showMessageDialog(contratoDetalhe, "Dados salvos com sucesso!", "Informacao do Sistema", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (ClassNotFoundException e) {
            JOptionPane.showMessageDialog(contratoDetalhe, "Módulo GED não disponível!\nEntre em contato com a Software House", "Erro do Sistema", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Response updateRecord(ValueObject oldPersistentObject, ValueObject persistentObject) throws Exception {
        List<ContratoHistFaturamentoVO> historicoFaturamento = contratoDetalhe.getGridHistoricoFaturamento().getVOListTableModel().getDataVector();
        List<ContratoHistoricoReajusteVO> historicoReajuste = contratoDetalhe.getGridHistoricoReajuste().getVOListTableModel().getDataVector();
        List<ContratoPrevFaturamentoVO> previsaoFaturamento = contratoDetalhe.getGridPrevisaoFaturamento().getVOListTableModel().getDataVector();

        ContratoVO contrato = (ContratoVO) persistentObject;
        if (contrato.getContabilConta().getId() == null) {
            contrato.setContabilConta(null);
        }

        ArquivoVO arquivo;
        try {
            arquivo = getArquivo();
        } catch (Exception e) {
            return new ErrorResponse(e.getMessage());
        }

        return ClientUtils.getData(acaoServidor, new Object[]{Constantes.UPDATE, oldPersistentObject, persistentObject, historicoFaturamento, historicoReajuste, previsaoFaturamento, arquivo});
    }

    @Override
    public void afterEditData() {
        contratoGrid.getGrid1().reloadData();
        contratoDetalhe.getGridHistoricoFaturamento().reloadData();
        contratoDetalhe.getGridPrevisaoFaturamento().reloadData();
        contratoDetalhe.getGridHistoricoReajuste().reloadData();

        ContratoVO contrato = (ContratoVO) contratoDetalhe.getForm1().getVOModel().getValueObject();
        try {
            if ((contrato.getContratoSolicitacaoServico().getCliente() != null) && (getArquivo().getFile() != null)) {
                if (JOptionPane.showConfirmDialog(contratoDetalhe, "Dados alterados com sucesso!\nDeseja acionar o ged para armazenar o documento?", "Informacao do Sistema", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                    acionaGed(contrato);
                }
            } else if ((contrato.getContratoSolicitacaoServico().getFornecedor() != null)) {
                if (JOptionPane.showConfirmDialog(contratoDetalhe, "Dados salvos com sucesso!\nDeseja acionar o ged para armazenar o documento?", "Informacao do Sistema", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                    acionaGed(contrato);
                }
            } else {
                JOptionPane.showMessageDialog(contratoDetalhe, "Dados alterados com sucesso!", "Informacao do Sistema", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (ClassNotFoundException e) {
            JOptionPane.showMessageDialog(contratoDetalhe, "Módulo GED não disponível!\nEntre em contato com a Software House", "Erro do Sistema", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void geraContrato() throws Exception {
        if (contratoDetalhe.getForm1().getMode() == Consts.READONLY) {
            throw new Exception("Antes de editar um contrato, clique no botão \"Editar Registro\"");
        }
        if (contratoDetalhe.getForm1().push()) {
            ContratoVO contrato = (ContratoVO) contratoDetalhe.getForm1().getVOModel().getValueObject();
            if (contrato.getContratoSolicitacaoServico().getCliente() == null) {
                throw new Exception("Um template só pode ser utilizado para serviços prestados.");
            }
            if (contratoDetalhe.getForm1().getMode() == Consts.EDIT) {
                downloadContrato(contrato);
            } else if (contratoDetalhe.getForm1().getMode() == Consts.INSERT) {
                criaNovoContrato(contrato);
            }
            abreArquivo(contrato);
        }
    }

    private void downloadTemplate(ContratoVO contrato) throws Exception {
        Response res = ClientUtils.getData("contratoTemplateDetalheAction", new Object[]{Constantes.DOWNLOAD, contrato.getContratoTemplate().getId()});
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
                throw new Exception("Arquivo do template não localizado!");
            }
        } else {
            throw new Exception("Arquivo do template não localizado!");
        }
    }

    private void downloadContrato(ContratoVO contrato) throws Exception {
        Response res = ClientUtils.getData(acaoServidor, new Object[]{Constantes.DOWNLOAD, contrato.getId()});
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
                criaNovoContrato(contrato);
            }
        }
    }

    private void criaNovoContrato(ContratoVO contrato) throws Exception {
        if (contrato.getContratoTemplate().getId() == null) {
            throw new Exception("Selecione o template!");
        }
        downloadTemplate(contrato);
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

    private void abreArquivo(ContratoVO contrato) throws Exception {
        //verifica o diretorio onde está instalado o OpenOffice
        String localInstalacaoOffice = InstallationFinder.getPath();
        if (localInstalacaoOffice == null || localInstalacaoOffice.equals("")) {
            throw new Exception("Instalação do OpenOffice não localizada!");
        }
        XComponentContext xContext = BootstrapSocketConnector.bootstrap(localInstalacaoOffice);
        XMultiComponentFactory xRemoteServiceManager = xContext.getServiceManager();
        Object desktop = xRemoteServiceManager.createInstanceWithContext("com.sun.star.frame.Desktop", xContext);

        XDesktop xDesktop = (XDesktop) UnoRuntime.queryInterface(XDesktop.class, desktop);

        XComponentLoader xComponentLoader = (XComponentLoader) UnoRuntime.queryInterface(XComponentLoader.class, xDesktop);

        String urlArquivo = nomeArquivoTemporario;
        urlArquivo = urlArquivo.replace('\\', '/');

        XComponent xComponent = xComponentLoader.loadComponentFromURL(urlArquivo, "_blank", 0, new PropertyValue[0]);

        //define os termos a serem substituidos
        String termos[] = new String[]{
            //contratada
            "#CONTRATADA#",
            "#CNPJ_CONTRATADA#",
            "#ENDERECO_CONTRATADA#",
            "#CIDADE_CONTRATADA#",
            "#UF_CONTRATADA#",
            "#BAIRRO_CONTRATADA#",
            "#CEP_CONTRATADA#",
            //contratante
            "#CONTRATANTE#",
            "#CNPJ_CONTRATANTE#",
            "#ENDERECO_CONTRATANTE#",
            "#CIDADE_CONTRATANTE#",
            "#UF_CONTRATANTE#",
            "#BAIRRO_CONTRATANTE#",
            "#CEP_CONTRATANTE#",
            //outros
            "#VALOR_MENSAL#",
            "#DATA_EXTENSO#"
        };

        EmpresaVO empresa = (EmpresaVO) Container.getContainer().get("empresa");
        if (empresa.getListaEndereco() == null) {
            throw new Exception("Endereço da empresa não cadastrado!");
        }

        EmpresaEnderecoVO enderecoEmpresa = empresa.getListaEndereco().get(0);
        MaskFormatter formatoCnpj = new MaskFormatter("##.###.###/####-##");
        formatoCnpj.setValueContainsLiteralCharacters(false);
        MaskFormatter formatoCpf = new MaskFormatter("###.###.###-##");
        formatoCpf.setValueContainsLiteralCharacters(false);
        SimpleDateFormat formatoDataExtenso = new SimpleDateFormat("EEEE, dd 'de' MMMM 'de' yyyy");
        DecimalFormat formatoDecimal = new DecimalFormat("#,###,##0.00");

        Response res = ClientUtils.getData(acaoServidor, new Object[]{99, contrato.getContratoSolicitacaoServico().getId().toString()});
        if (res.isError()) {
            throw new Exception(res.getErrorMessage());
        }
        ViewContratoDadosContratanteVO dadosContratante = (ViewContratoDadosContratanteVO) ((VOResponse) res).getVo();

        //busca os dados para substituicoes dos termos
        String substituicoes[] = new String[]{
            //contratada
            empresa.getRazaoSocial(),
            formatoCnpj.valueToString(empresa.getCnpj()),
            enderecoEmpresa.getLogradouro() + " " + enderecoEmpresa.getNumero() + " " + (enderecoEmpresa.getComplemento() == null ? "" : enderecoEmpresa.getComplemento()),
            enderecoEmpresa.getCidade(),
            enderecoEmpresa.getUf(),
            enderecoEmpresa.getBairro(),
            enderecoEmpresa.getCep(),
            //contratante
            dadosContratante.getNome(),
            dadosContratante.getCpfCnpj().length() == 11 ? formatoCpf.valueToString(dadosContratante.getCpfCnpj()) : formatoCnpj.valueToString(dadosContratante.getCpfCnpj()),
            dadosContratante.getLogradouro() + " " + dadosContratante.getNumero() + " " + (dadosContratante.getComplemento() == null ? "" : dadosContratante.getComplemento()),
            dadosContratante.getCidade(),
            dadosContratante.getUf(),
            dadosContratante.getBairro(),
            dadosContratante.getCep(),
            //outros
            formatoDecimal.format(contrato.getValor()),
            formatoDataExtenso.format(contrato.getDataInicioVigencia())
        };

        XReplaceable xReplaceable = (XReplaceable) UnoRuntime.queryInterface(XReplaceable.class, xComponent);
        XReplaceDescriptor xReplaceDescriptor = xReplaceable.createReplaceDescriptor();

        //realiza a substituicao dos termos
        for (int i = 0; i < termos.length; i++) {
            xReplaceDescriptor.setSearchString(termos[i]);
            xReplaceDescriptor.setReplaceString(substituicoes[i]);

            xReplaceable.replaceAll(xReplaceDescriptor);
        }

        final EdicaoContratoDialog dialog = new EdicaoContratoDialog(MDIFrame.getInstance(), true);

        xComponent.addEventListener(new XEventListener() {

            public void notifyEvent(EventObject eo) {
            }

            public void disposing(com.sun.star.lang.EventObject eo) {
                dialog.dispose();
            }
        });

        dialog.setVisible(true);
    }

    private ArquivoVO getArquivo() throws Exception {
        //seta os dados do arquivo
        ArquivoVO arquivo = new ArquivoVO();
        File file = new File(nomeArquivoTemporario.substring(8));

        if (file.exists()) {
            arquivo.setFile(getBytesFromFile(file));
        }
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

    private void acionaGed(ContratoVO contrato) throws ClassNotFoundException, Exception {
        Object classe = Class.forName("com.t2tierp.ged.cliente.GedDocumentoGridController").newInstance();
        Method metodo = classe.getClass().getDeclaredMethod("integracaoModulosErp", String.class, ArquivoVO.class);

        String nomeDocumento = "GESTAO_CONTRATOS-" + contrato.getNumero();
        ArquivoVO arquivo = getArquivo();
        arquivo.setExtensao(".odt");

        metodo.invoke(classe, nomeDocumento, arquivo);
    }
}
