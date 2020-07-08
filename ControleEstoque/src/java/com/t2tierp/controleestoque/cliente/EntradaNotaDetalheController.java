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
package com.t2tierp.controleestoque.cliente;

import com.t2tierp.cadastros.java.EmpresaVO;
import com.t2tierp.escritafiscal.java.FiscalNotaFiscalEntradaVO;
import com.t2tierp.nfe.java.NfeCabecalhoVO;
import com.t2tierp.nfe.java.NfeCteReferenciadoVO;
import com.t2tierp.nfe.java.NfeCupomFiscalReferenciadoVO;
import com.t2tierp.nfe.java.NfeDetalheVO;
import com.t2tierp.nfe.java.NfeDuplicataVO;
import com.t2tierp.nfe.java.NfeEmitenteVO;
import com.t2tierp.nfe.java.NfeFaturaVO;
import com.t2tierp.nfe.java.NfeLocalEntregaVO;
import com.t2tierp.nfe.java.NfeLocalRetiradaVO;
import com.t2tierp.nfe.java.NfeNfReferenciadaVO;
import com.t2tierp.nfe.java.NfeProdRuralReferenciadaVO;
import com.t2tierp.nfe.java.NfeReferenciadaVO;
import com.t2tierp.nfe.java.NfeTransporteReboqueVO;
import com.t2tierp.nfe.java.NfeTransporteVO;
import com.t2tierp.nfe.java.NfeTransporteVolumeLacreVO;
import com.t2tierp.nfe.java.NfeTransporteVolumeVO;
import com.t2tierp.padrao.cliente.Container;
import com.t2tierp.padrao.java.Constantes;
import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;
import org.openswing.swing.form.client.FormController;
import org.openswing.swing.mdi.client.MDIFrame;
import org.openswing.swing.message.receive.java.ErrorResponse;
import org.openswing.swing.message.receive.java.Response;
import org.openswing.swing.message.receive.java.VOListResponse;
import org.openswing.swing.message.receive.java.ValueObject;
import org.openswing.swing.util.client.ClientUtils;
import org.openswing.swing.util.java.Consts;

public class EntradaNotaDetalheController extends FormController {

    private EntradaNotaDetalhe entradaNotaDetalhe = null;
    private String pk = null;
    private EntradaNotaGrid entradaNotaGrid = null;
    private String acaoServidor;

    public EntradaNotaDetalheController(EntradaNotaGrid entradaNotaGrid, String pk) {
        this.entradaNotaGrid = entradaNotaGrid;
        this.pk = pk;
        this.acaoServidor = "entradaNotaDetalheAction";
        entradaNotaDetalhe = new EntradaNotaDetalhe(this);
        entradaNotaDetalhe.setParentFrame(this.entradaNotaGrid);
        this.entradaNotaGrid.pushFrame(entradaNotaDetalhe);
        MDIFrame.add(entradaNotaDetalhe, true);

        if (pk != null) {
            entradaNotaDetalhe.getFormDadosNfe().setMode(Consts.READONLY);
            entradaNotaDetalhe.getFormDadosNfe().reload();
        } else {
            entradaNotaDetalhe.getFormDadosNfe().setMode(Consts.INSERT);
            entradaNotaDetalhe.getFormEmitente().setMode(Consts.INSERT);
            entradaNotaDetalhe.getGridControlProduto().reloadData();
            entradaNotaDetalhe.getGridControlNfeReferenciada().reloadData();
            entradaNotaDetalhe.getGridControlNf1Referenciada().reloadData();
            entradaNotaDetalhe.getGridControlCteReferenciado().reloadData();
            entradaNotaDetalhe.getGridControlProdRuralReferenciada().reloadData();
            entradaNotaDetalhe.getGridControlCupomFiscalReferenciado().reloadData();
            entradaNotaDetalhe.getFormLocalEntrega().setMode(Consts.INSERT);
            entradaNotaDetalhe.getFormLocalRetirada().setMode(Consts.INSERT);
            entradaNotaDetalhe.getFormTransporte().setMode(Consts.INSERT);
            entradaNotaDetalhe.getGridControlTransporteReboque().reloadData();
            entradaNotaDetalhe.getGridControlTransporteVolume().reloadData();
            entradaNotaDetalhe.getFormFatura().setMode(Consts.INSERT);
            entradaNotaDetalhe.getGridControlDuplicata().reloadData();
            entradaNotaDetalhe.getFormEscrituracao().setMode(Consts.INSERT);
            valoresPadrao();
        }
    }

    @Override
    public Response loadData(Class valueObjectClass) {
        return ClientUtils.getData(acaoServidor, new Object[]{Constantes.LOAD, pk});
    }

    @Override
    public void loadDataCompleted(boolean error) {
        if (!error) {
            NfeCabecalhoVO nfeCabecalho = (NfeCabecalhoVO) entradaNotaDetalhe.getFormDadosNfe().getVOModel().getValueObject();

            entradaNotaDetalhe.getEmitenteController().setNfeCabecalho(nfeCabecalho);
            entradaNotaDetalhe.getFormEmitente().reload();

            entradaNotaDetalhe.getProdutoController().setPk(pk);
            entradaNotaDetalhe.getGridControlProduto().reloadData();

            entradaNotaDetalhe.getNfeReferenciadaController().setPk(pk);
            entradaNotaDetalhe.getGridControlNfeReferenciada().reloadData();

            entradaNotaDetalhe.getNf1ReferenciadaController().setPk(pk);
            entradaNotaDetalhe.getGridControlNf1Referenciada().reloadData();

            entradaNotaDetalhe.getCteReferenciadoController().setPk(pk);
            entradaNotaDetalhe.getGridControlCteReferenciado().reloadData();

            entradaNotaDetalhe.getProdRuralReferenciadaController().setPk(pk);
            entradaNotaDetalhe.getGridControlProdRuralReferenciada().reloadData();

            entradaNotaDetalhe.getCupomFiscalReferenciadaController().setPk(pk);
            entradaNotaDetalhe.getGridControlCupomFiscalReferenciado().reloadData();

            entradaNotaDetalhe.getLocalEntregaController().setNfeCabecalho(nfeCabecalho);
            entradaNotaDetalhe.getFormLocalEntrega().reload();

            entradaNotaDetalhe.getLocalRetiradaController().setNfeCabecalho(nfeCabecalho);
            entradaNotaDetalhe.getFormLocalRetirada().reload();

            entradaNotaDetalhe.getTransporteController().setNfeCabecalho(nfeCabecalho);
            entradaNotaDetalhe.getFormTransporte().reload();

            entradaNotaDetalhe.getFaturaController().setNfeCabecalho(nfeCabecalho);
            entradaNotaDetalhe.getFormFatura().reload();

            entradaNotaDetalhe.getDuplicataController().setPk(pk);
            entradaNotaDetalhe.getGridControlDuplicata().reloadData();

            entradaNotaDetalhe.getEscrituracaoController().setNfeCabecalho(nfeCabecalho);
            entradaNotaDetalhe.getFormEscrituracao().reload();
        }
    }

    @Override
    public Response insertRecord(ValueObject newPersistentObject) throws Exception {
        if (!entradaNotaDetalhe.getFormEmitente().save()) {
            return new ErrorResponse("Erro ao salvar os dados do emitente!");
        }
        if (!entradaNotaDetalhe.getFormLocalEntrega().save()) {
            retornaEdicaoForm();
            return new ErrorResponse("Erro ao salvar os dados do local de entrega!");
        }
        if (!entradaNotaDetalhe.getFormLocalRetirada().save()) {
            retornaEdicaoForm();
            return new ErrorResponse("Erro ao salvar os dados do local de retirada!");
        }
        if (!entradaNotaDetalhe.getFormTransporte().save()) {
            retornaEdicaoForm();
            return new ErrorResponse("Erro ao salvar os dados do transporte!");
        }
        if (!entradaNotaDetalhe.getFormFatura().save()) {
            retornaEdicaoForm();
            return new ErrorResponse("Erro ao salvar os dados da fatura!");
        }
        if (!entradaNotaDetalhe.getFormEscrituracao().save()) {
            retornaEdicaoForm();
            return new ErrorResponse("Erro ao salvar os dados da escrituração!");
        }

        List<NfeDetalheVO> listaNfeDetalhe = entradaNotaDetalhe.getGridControlProduto().getVOListTableModel().getDataVector();
        if (listaNfeDetalhe.isEmpty()) {
            retornaEdicaoForm();
            return new ErrorResponse("Não foram inseridos produtos na nota!");
        }

        if (verificaProdutoNaoCadastrado()) {
            retornaEdicaoForm();
            return new ErrorResponse("Foram informados produtos que ainda não foram cadastrados.!");
        }

        List<NfeCupomFiscalReferenciadoVO> listaCuponsFiscais = entradaNotaDetalhe.getGridControlCupomFiscalReferenciado().getVOListTableModel().getDataVector();

        List<NfeReferenciadaVO> listaNfeReferenciada = entradaNotaDetalhe.getGridControlNfeReferenciada().getVOListTableModel().getDataVector();

        List<NfeCteReferenciadoVO> listaCte = entradaNotaDetalhe.getGridControlCteReferenciado().getVOListTableModel().getDataVector();

        List<NfeNfReferenciadaVO> listaNf1Referenciada = entradaNotaDetalhe.getGridControlNf1Referenciada().getVOListTableModel().getDataVector();

        List<NfeProdRuralReferenciadaVO> prodRuralReferenciada = entradaNotaDetalhe.getGridControlProdRuralReferenciada().getVOListTableModel().getDataVector();

        NfeCabecalhoVO nfeCabecalho = (NfeCabecalhoVO) newPersistentObject;
        EmpresaVO empresa = (EmpresaVO) Container.getContainer().get("empresa");
        nfeCabecalho.setEmpresa(empresa);

        NfeEmitenteVO emitente = (NfeEmitenteVO) entradaNotaDetalhe.getFormEmitente().getVOModel().getValueObject();

        NfeLocalEntregaVO localEntrega = (NfeLocalEntregaVO) entradaNotaDetalhe.getFormLocalEntrega().getVOModel().getValueObject();
        NfeLocalRetiradaVO localRetirada = (NfeLocalRetiradaVO) entradaNotaDetalhe.getFormLocalRetirada().getVOModel().getValueObject();

        NfeTransporteVO transporte = (NfeTransporteVO) entradaNotaDetalhe.getFormTransporte().getVOModel().getValueObject();
        List<NfeTransporteReboqueVO> transporteReboque = entradaNotaDetalhe.getGridControlTransporteReboque().getVOListTableModel().getDataVector();
        List<NfeTransporteVolumeVO> transporteVolume = entradaNotaDetalhe.getGridControlTransporteVolume().getVOListTableModel().getDataVector();
        List<NfeTransporteVolumeLacreVO> transporteVolumeLacre = entradaNotaDetalhe.getGridControlTransporteVolumeLacre().getVOListTableModel().getDataVector();

        NfeFaturaVO fatura = (NfeFaturaVO) entradaNotaDetalhe.getFormFatura().getVOModel().getValueObject();

        List<NfeDuplicataVO> listaDuplicata = entradaNotaDetalhe.getGridControlDuplicata().getVOListTableModel().getDataVector();

        FiscalNotaFiscalEntradaVO escrituracao = (FiscalNotaFiscalEntradaVO) entradaNotaDetalhe.getFormEscrituracao().getVOModel().getValueObject();

        return ClientUtils.getData(acaoServidor, new Object[]{
                    Constantes.INSERT,
                    nfeCabecalho,
                    emitente,
                    listaNfeDetalhe,
                    listaCuponsFiscais,
                    localEntrega,
                    localRetirada,
                    transporte,
                    transporteReboque,
                    transporteVolume,
                    transporteVolumeLacre,
                    fatura,
                    listaDuplicata,
                    listaNfeReferenciada,
                    listaNf1Referenciada,
                    listaCte,
                    prodRuralReferenciada,
                    escrituracao});
    }

    @Override
    public void afterInsertData() {
        entradaNotaGrid.getGrid1().reloadData();
        this.pk = ((NfeCabecalhoVO) (entradaNotaDetalhe.getFormDadosNfe().getVOModel().getValueObject())).getId().toString();
        entradaNotaDetalhe.getFormDadosNfe().reload();
        JOptionPane.showMessageDialog(entradaNotaDetalhe, "Dados salvos com sucesso!", "Informacao do Sistema", JOptionPane.INFORMATION_MESSAGE);
    }

    @Override
    public Response updateRecord(ValueObject oldPersistentObject, ValueObject persistentObject) throws Exception {
        if (!entradaNotaDetalhe.getFormEmitente().save()) {
            return new ErrorResponse("Erro ao salvar os dados do emitente!");
        }
        if (!entradaNotaDetalhe.getFormLocalEntrega().save()) {
            retornaEdicaoForm();
            return new ErrorResponse("Erro ao salvar os dados do local de entrega!");
        }
        if (!entradaNotaDetalhe.getFormLocalRetirada().save()) {
            retornaEdicaoForm();
            return new ErrorResponse("Erro ao salvar os dados do local de retirada!");
        }
        if (!entradaNotaDetalhe.getFormTransporte().save()) {
            retornaEdicaoForm();
            return new ErrorResponse("Erro ao salvar os dados do transporte!");
        }
        if (!entradaNotaDetalhe.getFormFatura().save()) {
            retornaEdicaoForm();
            return new ErrorResponse("Erro ao salvar os dados da fatura!");
        }
        if (!entradaNotaDetalhe.getFormEscrituracao().save()) {
            retornaEdicaoForm();
            return new ErrorResponse("Erro ao salvar os dados da escrituração!");
        }

        List<NfeDetalheVO> listaNfeDetalhe = entradaNotaDetalhe.getGridControlProduto().getVOListTableModel().getDataVector();
        if (listaNfeDetalhe.isEmpty()) {
            retornaEdicaoForm();
            return new ErrorResponse("Não foram inseridos produtos na nota!");
        }

        if (verificaProdutoNaoCadastrado()) {
            retornaEdicaoForm();
            return new ErrorResponse("Foram informados produtos que ainda não foram cadastrados!");
        }

        List<NfeCupomFiscalReferenciadoVO> listaCuponsFiscais = entradaNotaDetalhe.getGridControlCupomFiscalReferenciado().getVOListTableModel().getDataVector();

        List<NfeReferenciadaVO> listaNfeReferenciada = entradaNotaDetalhe.getGridControlNfeReferenciada().getVOListTableModel().getDataVector();

        List<NfeCteReferenciadoVO> listaCte = entradaNotaDetalhe.getGridControlCteReferenciado().getVOListTableModel().getDataVector();

        List<NfeNfReferenciadaVO> listaNf1Referenciada = entradaNotaDetalhe.getGridControlNf1Referenciada().getVOListTableModel().getDataVector();

        List<NfeProdRuralReferenciadaVO> prodRuralReferenciada = entradaNotaDetalhe.getGridControlProdRuralReferenciada().getVOListTableModel().getDataVector();

        NfeCabecalhoVO nfeCabecalho = (NfeCabecalhoVO) persistentObject;

        NfeEmitenteVO emitente = (NfeEmitenteVO) entradaNotaDetalhe.getFormEmitente().getVOModel().getValueObject();

        NfeLocalEntregaVO localEntrega = (NfeLocalEntregaVO) entradaNotaDetalhe.getFormLocalEntrega().getVOModel().getValueObject();
        NfeLocalRetiradaVO localRetirada = (NfeLocalRetiradaVO) entradaNotaDetalhe.getFormLocalRetirada().getVOModel().getValueObject();

        NfeTransporteVO transporte = (NfeTransporteVO) entradaNotaDetalhe.getFormTransporte().getVOModel().getValueObject();
        List<NfeTransporteReboqueVO> transporteReboque = entradaNotaDetalhe.getGridControlTransporteReboque().getVOListTableModel().getDataVector();
        List<NfeTransporteVolumeVO> transporteVolume = entradaNotaDetalhe.getGridControlTransporteVolume().getVOListTableModel().getDataVector();
        List<NfeTransporteVolumeLacreVO> transporteVolumeLacre = entradaNotaDetalhe.getGridControlTransporteVolumeLacre().getVOListTableModel().getDataVector();

        NfeFaturaVO fatura = (NfeFaturaVO) entradaNotaDetalhe.getFormFatura().getVOModel().getValueObject();

        List<NfeDuplicataVO> listaDuplicata = entradaNotaDetalhe.getGridControlDuplicata().getVOListTableModel().getDataVector();

        FiscalNotaFiscalEntradaVO escrituracao = (FiscalNotaFiscalEntradaVO) entradaNotaDetalhe.getFormEscrituracao().getVOModel().getValueObject();

        return ClientUtils.getData(acaoServidor, new Object[]{
                    Constantes.UPDATE,
                    nfeCabecalho,
                    emitente,
                    listaNfeDetalhe,
                    listaCuponsFiscais,
                    localEntrega,
                    localRetirada,
                    transporte,
                    transporteReboque,
                    transporteVolume,
                    transporteVolumeLacre,
                    fatura,
                    listaDuplicata,
                    listaNfeReferenciada,
                    listaNf1Referenciada,
                    listaCte,
                    prodRuralReferenciada,
                    escrituracao});
    }

    @Override
    public void afterEditData() {
        entradaNotaGrid.getGrid1().reloadData();
        JOptionPane.showMessageDialog(entradaNotaDetalhe, "Dados alterados com sucesso!", "Informacao do Sistema", JOptionPane.INFORMATION_MESSAGE);
    }

    private void valoresPadrao() {
        NfeCabecalhoVO nfeCabecalho = (NfeCabecalhoVO) entradaNotaDetalhe.getFormDadosNfe().getVOModel().getValueObject();

        nfeCabecalho.setTipoOperacao(0);
        nfeCabecalho.setStatusNota(0);
        nfeCabecalho.setBaseCalculoIcms(BigDecimal.ZERO);
        nfeCabecalho.setValorIcms(BigDecimal.ZERO);
        nfeCabecalho.setValorTotalProdutos(BigDecimal.ZERO);
        nfeCabecalho.setBaseCalculoIcmsSt(BigDecimal.ZERO);
        nfeCabecalho.setValorIcmsSt(BigDecimal.ZERO);
        nfeCabecalho.setValorIpi(BigDecimal.ZERO);
        nfeCabecalho.setValorPis(BigDecimal.ZERO);
        nfeCabecalho.setValorCofins(BigDecimal.ZERO);
        nfeCabecalho.setValorFrete(BigDecimal.ZERO);
        nfeCabecalho.setValorSeguro(BigDecimal.ZERO);
        nfeCabecalho.setValorDespesasAcessorias(BigDecimal.ZERO);
        nfeCabecalho.setValorDesconto(BigDecimal.ZERO);
        nfeCabecalho.setValorTotal(BigDecimal.ZERO);

        entradaNotaDetalhe.getFormDadosNfe().pull();
    }

    public void importaNfe() {

        try {
            FileFilter filter = new FileFilter() {

                @Override
                public boolean accept(File f) {
                    String arquivo = f.getName().toLowerCase();
                    return f.isDirectory()
                            || arquivo.endsWith(".xml");
                }

                @Override
                public String getDescription() {
                    return "*.xml";
                }
            };

            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setFileFilter(filter);
            fileChooser.showOpenDialog(entradaNotaDetalhe);
            File file = fileChooser.getSelectedFile();

            if (file != null) {
                ImportaXMLNFe importaXml = new ImportaXMLNFe();
                Map map = importaXml.importarXmlNFe(file);
                entradaNotaDetalhe.getFormDadosNfe().getVOModel().setValueObject((NfeCabecalhoVO) map.get("cabecalho"));
                entradaNotaDetalhe.getFormDadosNfe().pull();

                entradaNotaDetalhe.getFormEmitente().getVOModel().setValueObject((NfeEmitenteVO) map.get("emitente"));
                entradaNotaDetalhe.getFormEmitente().pull();

                entradaNotaDetalhe.getGridControlProduto().getVOListTableModel().clear();
                List<NfeDetalheVO> listaDetalhe = (ArrayList) map.get("detalhe");
                for (int i = 0; i < listaDetalhe.size(); i++) {
                    entradaNotaDetalhe.getGridControlProduto().getVOListTableModel().addObject(listaDetalhe.get(i));
                }

                entradaNotaDetalhe.getGridControlNfeReferenciada().getVOListTableModel().clear();
                List<NfeReferenciadaVO> listaNfeReferenciada = (ArrayList) map.get("nfeReferenciada");
                for (int i = 0; i < listaNfeReferenciada.size(); i++) {
                    entradaNotaDetalhe.getGridControlNfeReferenciada().getVOListTableModel().addObject(listaNfeReferenciada.get(i));
                }

                entradaNotaDetalhe.getGridControlNf1Referenciada().getVOListTableModel().clear();
                List<NfeNfReferenciadaVO> listaNf1Referenciada = (ArrayList) map.get("nf1Referenciada");
                for (int i = 0; i < listaNf1Referenciada.size(); i++) {
                    entradaNotaDetalhe.getGridControlNf1Referenciada().getVOListTableModel().addObject(listaNf1Referenciada.get(i));
                }

                entradaNotaDetalhe.getGridControlProdRuralReferenciada().getVOListTableModel().clear();
                List<NfeProdRuralReferenciadaVO> listaProdRuralReferenciada = (ArrayList) map.get("prodRuralReferenciada");
                for (int i = 0; i < listaProdRuralReferenciada.size(); i++) {
                    entradaNotaDetalhe.getGridControlProdRuralReferenciada().getVOListTableModel().addObject(listaProdRuralReferenciada.get(i));
                }

                entradaNotaDetalhe.getGridControlCteReferenciado().getVOListTableModel().clear();
                List<NfeCteReferenciadoVO> listaCteReferenciado = (ArrayList) map.get("cteReferenciado");
                for (int i = 0; i < listaCteReferenciado.size(); i++) {
                    entradaNotaDetalhe.getGridControlCteReferenciado().getVOListTableModel().addObject(listaCteReferenciado.get(i));
                }

                entradaNotaDetalhe.getFormLocalEntrega().getVOModel().setValueObject((NfeLocalEntregaVO) map.get("localEntrega"));
                entradaNotaDetalhe.getFormLocalEntrega().pull();

                entradaNotaDetalhe.getFormLocalRetirada().getVOModel().setValueObject((NfeLocalRetiradaVO) map.get("localRetirada"));
                entradaNotaDetalhe.getFormLocalRetirada().pull();

                entradaNotaDetalhe.getFormTransporte().getVOModel().setValueObject((NfeTransporteVO) map.get("transporte"));
                entradaNotaDetalhe.getFormTransporte().pull();

                entradaNotaDetalhe.getGridControlTransporteReboque().getVOListTableModel().clear();
                List<NfeTransporteReboqueVO> listaTransporteReboque = (ArrayList) map.get("transporteReboque");
                for (int i = 0; i < listaTransporteReboque.size(); i++) {
                    entradaNotaDetalhe.getGridControlTransporteReboque().getVOListTableModel().addObject(listaTransporteReboque.get(i));
                }

                entradaNotaDetalhe.getGridControlTransporteVolume().getVOListTableModel().clear();
                List<NfeTransporteVolumeVO> listaTransporteVolume = (ArrayList) map.get("transporteVolume");
                for (int i = 0; i < listaTransporteVolume.size(); i++) {
                    entradaNotaDetalhe.getGridControlTransporteVolume().getVOListTableModel().addObject(listaTransporteVolume.get(i));
                }

                entradaNotaDetalhe.getFormFatura().getVOModel().setValueObject((NfeFaturaVO) map.get("fatura"));
                entradaNotaDetalhe.getFormFatura().pull();

                entradaNotaDetalhe.getGridControlDuplicata().getVOListTableModel().clear();
                List<NfeDuplicataVO> listaDuplicata = (ArrayList) map.get("duplicata");
                for (int i = 0; i < listaDuplicata.size(); i++) {
                    entradaNotaDetalhe.getGridControlDuplicata().getVOListTableModel().addObject(listaDuplicata.get(i));
                }

                verificaProdutoNaoCadastrado();

                JOptionPane.showMessageDialog(entradaNotaDetalhe, "Dados importados com sucesso!", "Informação do Sistema", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(entradaNotaDetalhe, "Erro ao importar os dados!\n" + e.getMessage(), "Erro do Sistema", JOptionPane.ERROR_MESSAGE);
        }
    }

    public boolean verificaProdutoNaoCadastrado() throws Exception {
        boolean produtoNaoCadastrado = false;
        List<NfeDetalheVO> listaNfeDetalhe = entradaNotaDetalhe.getGridControlProduto().getVOListTableModel().getDataVector();
        Response res = ClientUtils.getData(acaoServidor, new Object[]{99, listaNfeDetalhe});
        if (res.isError()) {
            throw new Exception(res.getErrorMessage());
        }
        List<NfeDetalheVO> produtos = ((VOListResponse) res).getRows();
        entradaNotaDetalhe.getGridControlProduto().clearData();
        for (int i = 0; i < produtos.size(); i++) {
            entradaNotaDetalhe.getGridControlProduto().getVOListTableModel().addObject(produtos.get(i));
            if (!produtos.get(i).isProdutoCadastrado()) {
                produtoNaoCadastrado = true;
            }
        }
        return produtoNaoCadastrado;
    }

    private void retornaEdicaoForm() {
        entradaNotaDetalhe.getFormEmitente().setMode(Consts.EDIT);
        entradaNotaDetalhe.getFormLocalEntrega().setMode(Consts.EDIT);
        entradaNotaDetalhe.getFormLocalRetirada().setMode(Consts.EDIT);
        entradaNotaDetalhe.getFormTransporte().setMode(Consts.EDIT);
        entradaNotaDetalhe.getFormFatura().setMode(Consts.EDIT);
        entradaNotaDetalhe.getFormEscrituracao().setMode(Consts.EDIT);
    }
}
