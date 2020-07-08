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
package com.t2tierp.nfe.cliente;

import com.t2tierp.cadastros.java.EmpresaVO;
import com.t2tierp.ged.java.ArquivoVO;
import com.t2tierp.nfe.java.NfeCabecalhoVO;
import com.t2tierp.nfe.java.NfeCteReferenciadoVO;
import com.t2tierp.nfe.java.NfeCupomFiscalReferenciadoVO;
import com.t2tierp.nfe.java.NfeDestinatarioVO;
import com.t2tierp.nfe.java.NfeDetalheVO;
import com.t2tierp.nfe.java.NfeDuplicataVO;
import com.t2tierp.nfe.java.NfeFaturaVO;
import com.t2tierp.nfe.java.NfeLocalEntregaVO;
import com.t2tierp.nfe.java.NfeLocalRetiradaVO;
import com.t2tierp.nfe.java.NfeNfReferenciadaVO;
import com.t2tierp.nfe.java.NfeProdRuralReferenciadaVO;
import com.t2tierp.nfe.java.NfeReferenciadaVO;
import com.t2tierp.nfe.java.NfeTransporteReboqueVO;
import com.t2tierp.nfe.java.NfeTransporteVO;
import com.t2tierp.nfe.java.NfeTransporteVolumeVO;
import com.t2tierp.nfe.java.RespostaSefaz;
import com.t2tierp.padrao.cliente.Container;
import com.t2tierp.padrao.cliente.SelecionaCertificado;
import com.t2tierp.padrao.java.Certificado;
import com.t2tierp.padrao.java.Constantes;
import java.io.File;
import java.io.FileOutputStream;
import java.math.BigDecimal;
import java.util.List;
import javax.swing.JOptionPane;
import org.openswing.swing.form.client.Form;
import org.openswing.swing.form.client.FormController;
import org.openswing.swing.mdi.client.MDIFrame;
import org.openswing.swing.message.receive.java.ErrorResponse;
import org.openswing.swing.message.receive.java.Response;
import org.openswing.swing.message.receive.java.VOResponse;
import org.openswing.swing.message.receive.java.ValueObject;
import org.openswing.swing.util.client.ClientUtils;
import org.openswing.swing.util.java.Consts;

public class NfeDetalheController extends FormController {

    private NfeDetalhe nfeDetalhe = null;
    private String pk = null;
    private NfeGrid nfeGrid = null;
    private String acaoServidor;

    public NfeDetalheController(NfeGrid nfeGrid, String pk) {
        this.nfeGrid = nfeGrid;
        this.pk = pk;
        this.acaoServidor = "nfeDetalheAction";
        nfeDetalhe = new NfeDetalhe(this);
        nfeDetalhe.setParentFrame(this.nfeGrid);
        this.nfeGrid.pushFrame(nfeDetalhe);
        MDIFrame.add(nfeDetalhe, true);

        if (pk != null) {
            nfeDetalhe.getFormDadosNfe().setMode(Consts.READONLY);
            nfeDetalhe.getFormDadosNfe().reload();
        } else {
            nfeDetalhe.getFormDadosNfe().setMode(Consts.INSERT);
            nfeDetalhe.getFormDestinatario().setMode(Consts.INSERT);
            nfeDetalhe.getFormLocalEntrega().setMode(Consts.INSERT);
            nfeDetalhe.getFormLocalRetirada().setMode(Consts.INSERT);
            nfeDetalhe.getFormTransporte().setMode(Consts.INSERT);
            nfeDetalhe.getFormFatura().setMode(Consts.INSERT);
            loadDataCompleted(false);
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
            NfeCabecalhoVO nfeCabecalho = (NfeCabecalhoVO) nfeDetalhe.getFormDadosNfe().getVOModel().getValueObject();

            nfeDetalhe.getDestinatarioController().setNfeCabecalho(nfeCabecalho);
            nfeDetalhe.getFormDestinatario().reload();

            nfeDetalhe.getProdutoController().setNfeCabecalho(nfeCabecalho);
            nfeDetalhe.getGridControlProduto().reloadData();

            nfeDetalhe.getNfeReferenciadaController().setNfeCabecalho(nfeCabecalho);
            nfeDetalhe.getGridControlNfeReferenciada().reloadData();

            nfeDetalhe.getNf1ReferenciadaController().setNfeCabecalho(nfeCabecalho);
            nfeDetalhe.getGridControlNf1Referenciada().reloadData();

            nfeDetalhe.getCteReferenciadoController().setNfeCabecalho(nfeCabecalho);
            nfeDetalhe.getGridControlCteReferenciado().reloadData();

            nfeDetalhe.getProdRuralReferenciadaController().setNfeCabecalho(nfeCabecalho);
            nfeDetalhe.getGridControlProdRuralReferenciada().reloadData();

            nfeDetalhe.getCupomFiscalReferenciadaController().setNfeCabecalho(nfeCabecalho);
            nfeDetalhe.getGridControlCupomFiscalReferenciado().reloadData();

            nfeDetalhe.getLocalEntregaController().setNfeCabecalho(nfeCabecalho);
            nfeDetalhe.getFormLocalEntrega().reload();

            nfeDetalhe.getLocalRetiradaController().setNfeCabecalho(nfeCabecalho);
            nfeDetalhe.getFormLocalRetirada().reload();

            nfeDetalhe.getTransporteController().setNfeCabecalho(nfeCabecalho);
            nfeDetalhe.getFormTransporte().reload();

            nfeDetalhe.getFaturaController().setNfeCabecalho(nfeCabecalho);
            nfeDetalhe.getFormFatura().reload();

            nfeDetalhe.getDuplicataController().setNfeCabecalho(nfeCabecalho);
            nfeDetalhe.getGridControlDuplicata().reloadData();
        }
    }

    @Override
    public Response insertRecord(ValueObject newPersistentObject) throws Exception {
        if (!nfeDetalhe.getFormDestinatario().save()) {
            return new ErrorResponse("Erro ao salvar os dados do destinatário!");
        }
        if (!nfeDetalhe.getFormLocalEntrega().save()) {
            retornaEdicaoForm();
            return new ErrorResponse("Erro ao salvar os dados do local de entrega!");
        }
        if (!nfeDetalhe.getFormLocalRetirada().save()) {
            retornaEdicaoForm();
            return new ErrorResponse("Erro ao salvar os dados do local de retirada!");
        }
        if (!nfeDetalhe.getFormTransporte().save()) {
            retornaEdicaoForm();
            return new ErrorResponse("Erro ao salvar os dados do transporte!");
        }
        if (!nfeDetalhe.getFormFatura().save()) {
            retornaEdicaoForm();
            return new ErrorResponse("Erro ao salvar os dados da fatura!");
        }

        List<NfeDetalheVO> listaNfeDetalhe = nfeDetalhe.getGridControlProduto().getVOListTableModel().getDataVector();
        if (listaNfeDetalhe.isEmpty()) {
            retornaEdicaoForm();
            return new ErrorResponse("Não foram inseridos produtos na nota!");
        }

        NfeCabecalhoVO nfeCabecalho = (NfeCabecalhoVO) newPersistentObject;
        EmpresaVO empresa = (EmpresaVO) Container.getContainer().get("empresa");
        nfeCabecalho.setEmpresa(empresa);
        nfeCabecalho.setListaNfeDetalhe(listaNfeDetalhe);

        List<NfeCupomFiscalReferenciadoVO> listaCuponsFiscais = nfeDetalhe.getGridControlCupomFiscalReferenciado().getVOListTableModel().getDataVector();
        nfeCabecalho.setListaCupomFiscalReferenciado(listaCuponsFiscais);

        List<NfeReferenciadaVO> listaNfeReferenciada = nfeDetalhe.getGridControlNfeReferenciada().getVOListTableModel().getDataVector();
        nfeCabecalho.setListaNfeReferenciada(listaNfeReferenciada);

        List<NfeCteReferenciadoVO> listaCte = nfeDetalhe.getGridControlCteReferenciado().getVOListTableModel().getDataVector();
        nfeCabecalho.setListaCteReferenciado(listaCte);

        List<NfeNfReferenciadaVO> listaNf1Referenciada = nfeDetalhe.getGridControlNf1Referenciada().getVOListTableModel().getDataVector();
        nfeCabecalho.setListaNfReferenciada(listaNf1Referenciada);

        List<NfeProdRuralReferenciadaVO> prodRuralReferenciada = nfeDetalhe.getGridControlProdRuralReferenciada().getVOListTableModel().getDataVector();
        nfeCabecalho.setListaProdRuralReferenciada(prodRuralReferenciada);

        NfeDestinatarioVO destinatario = (NfeDestinatarioVO) nfeDetalhe.getFormDestinatario().getVOModel().getValueObject();
        nfeCabecalho.setDestinatario(destinatario);

        nfeCabecalho.setCliente(destinatario.getNfeCabecalho().getCliente());

        NfeLocalEntregaVO localEntrega = (NfeLocalEntregaVO) nfeDetalhe.getFormLocalEntrega().getVOModel().getValueObject();
        nfeCabecalho.setLocalEntrega(localEntrega);

        NfeLocalRetiradaVO localRetirada = (NfeLocalRetiradaVO) nfeDetalhe.getFormLocalRetirada().getVOModel().getValueObject();
        nfeCabecalho.setLocalRetirada(localRetirada);

        NfeTransporteVO transporte = (NfeTransporteVO) nfeDetalhe.getFormTransporte().getVOModel().getValueObject();
        nfeCabecalho.setTransporte(transporte);

        List<NfeTransporteReboqueVO> transporteReboque = nfeDetalhe.getGridControlTransporteReboque().getVOListTableModel().getDataVector();
        nfeCabecalho.getTransporte().setListaTransporteReboque(transporteReboque);

        List<NfeTransporteVolumeVO> transporteVolume = nfeDetalhe.getGridControlTransporteVolume().getVOListTableModel().getDataVector();
        nfeCabecalho.getTransporte().setListaTransporteVolume(transporteVolume);

        NfeFaturaVO fatura = (NfeFaturaVO) nfeDetalhe.getFormFatura().getVOModel().getValueObject();
        nfeCabecalho.setFatura(fatura);

        List<NfeDuplicataVO> listaDuplicata = nfeDetalhe.getGridControlDuplicata().getVOListTableModel().getDataVector();
        nfeCabecalho.setListaDuplicata(listaDuplicata);

        Response res = ClientUtils.getData(acaoServidor, new Object[]{Constantes.INSERT, nfeCabecalho});

        if (res.isError()) {
            retornaEdicaoForm();
        }

        return res;
    }

    @Override
    public void afterInsertData() {
        nfeGrid.getGrid1().reloadData();
        this.pk = ((NfeCabecalhoVO) (nfeDetalhe.getFormDadosNfe().getVOModel().getValueObject())).getId().toString();
        nfeDetalhe.getFormDadosNfe().reload();
        JOptionPane.showMessageDialog(nfeDetalhe, "Dados salvos com sucesso!", "Informacao do Sistema", JOptionPane.INFORMATION_MESSAGE);
    }

    @Override
    public boolean beforeEditData(Form form) {
        retornaEdicaoForm();
        return super.beforeEditData(form);
    }

    @Override
    public Response updateRecord(ValueObject oldPersistentObject, ValueObject persistentObject) throws Exception {
        if (!nfeDetalhe.getFormDestinatario().save()) {
            return new ErrorResponse("Erro ao salvar os dados do emitente!");
        }
        if (!nfeDetalhe.getFormLocalEntrega().save()) {
            retornaEdicaoForm();
            return new ErrorResponse("Erro ao salvar os dados do local de entrega!");
        }
        if (!nfeDetalhe.getFormLocalRetirada().save()) {
            retornaEdicaoForm();
            return new ErrorResponse("Erro ao salvar os dados do local de retirada!");
        }
        if (!nfeDetalhe.getFormTransporte().save()) {
            retornaEdicaoForm();
            return new ErrorResponse("Erro ao salvar os dados do transporte!");
        }
        if (!nfeDetalhe.getFormFatura().save()) {
            retornaEdicaoForm();
            return new ErrorResponse("Erro ao salvar os dados da fatura!");
        }

        List<NfeDetalheVO> listaNfeDetalhe = nfeDetalhe.getGridControlProduto().getVOListTableModel().getDataVector();
        if (listaNfeDetalhe.isEmpty()) {
            retornaEdicaoForm();
            return new ErrorResponse("Não foram inseridos produtos na nota!");
        }

        NfeCabecalhoVO nfeCabecalho = (NfeCabecalhoVO) persistentObject;
        if (nfeCabecalho.getStatusNota().intValue() == 5 || nfeCabecalho.getStatusNota().intValue() == 6) {
            return new ErrorResponse("NF-e já autorizada ou cancelada. Alteração não permitida!");
        }
        nfeCabecalho.setListaNfeDetalhe(listaNfeDetalhe);

        List<NfeCupomFiscalReferenciadoVO> listaCuponsFiscais = nfeDetalhe.getGridControlCupomFiscalReferenciado().getVOListTableModel().getDataVector();
        nfeCabecalho.setListaCupomFiscalReferenciado(listaCuponsFiscais);

        List<NfeReferenciadaVO> listaNfeReferenciada = nfeDetalhe.getGridControlNfeReferenciada().getVOListTableModel().getDataVector();
        nfeCabecalho.setListaNfeReferenciada(listaNfeReferenciada);

        List<NfeCteReferenciadoVO> listaCte = nfeDetalhe.getGridControlCteReferenciado().getVOListTableModel().getDataVector();
        nfeCabecalho.setListaCteReferenciado(listaCte);

        List<NfeNfReferenciadaVO> listaNf1Referenciada = nfeDetalhe.getGridControlNf1Referenciada().getVOListTableModel().getDataVector();
        nfeCabecalho.setListaNfReferenciada(listaNf1Referenciada);

        List<NfeProdRuralReferenciadaVO> prodRuralReferenciada = nfeDetalhe.getGridControlProdRuralReferenciada().getVOListTableModel().getDataVector();
        nfeCabecalho.setListaProdRuralReferenciada(prodRuralReferenciada);

        NfeDestinatarioVO destinatario = (NfeDestinatarioVO) nfeDetalhe.getFormDestinatario().getVOModel().getValueObject();
        nfeCabecalho.setDestinatario(destinatario);

        nfeCabecalho.setCliente(destinatario.getNfeCabecalho().getCliente());

        NfeLocalEntregaVO localEntrega = (NfeLocalEntregaVO) nfeDetalhe.getFormLocalEntrega().getVOModel().getValueObject();
        nfeCabecalho.setLocalEntrega(localEntrega);

        NfeLocalRetiradaVO localRetirada = (NfeLocalRetiradaVO) nfeDetalhe.getFormLocalRetirada().getVOModel().getValueObject();
        nfeCabecalho.setLocalRetirada(localRetirada);

        NfeTransporteVO transporte = (NfeTransporteVO) nfeDetalhe.getFormTransporte().getVOModel().getValueObject();
        nfeCabecalho.setTransporte(transporte);

        List<NfeTransporteReboqueVO> transporteReboque = nfeDetalhe.getGridControlTransporteReboque().getVOListTableModel().getDataVector();
        nfeCabecalho.getTransporte().setListaTransporteReboque(transporteReboque);

        List<NfeTransporteVolumeVO> transporteVolume = nfeDetalhe.getGridControlTransporteVolume().getVOListTableModel().getDataVector();
        nfeCabecalho.getTransporte().setListaTransporteVolume(transporteVolume);

        NfeFaturaVO fatura = (NfeFaturaVO) nfeDetalhe.getFormFatura().getVOModel().getValueObject();
        nfeCabecalho.setFatura(fatura);

        List<NfeDuplicataVO> listaDuplicata = nfeDetalhe.getGridControlDuplicata().getVOListTableModel().getDataVector();
        nfeCabecalho.setListaDuplicata(listaDuplicata);

        Response res = ClientUtils.getData(acaoServidor, new Object[]{Constantes.UPDATE, nfeCabecalho});

        if (res.isError()) {
            retornaEdicaoForm();
        }

        return res;
    }

    @Override
    public void afterEditData() {
        nfeDetalhe.getFormDadosNfe().reload();
        JOptionPane.showMessageDialog(nfeDetalhe, "Dados alterados com sucesso!", "Informacao do Sistema", JOptionPane.INFORMATION_MESSAGE);
    }

    public void atualizaTotais(List<NfeDetalheVO> itensNfe) {
        BigDecimal totalProdutos = BigDecimal.ZERO;
        BigDecimal valorFrete = BigDecimal.ZERO;
        BigDecimal valorSeguro = BigDecimal.ZERO;
        BigDecimal valorOutrasDespesas = BigDecimal.ZERO;
        BigDecimal desconto = BigDecimal.ZERO;
        BigDecimal baseCalculoIcms = BigDecimal.ZERO;
        BigDecimal valorIcms = BigDecimal.ZERO;
        BigDecimal baseCalculoIcmsSt = BigDecimal.ZERO;
        BigDecimal valorIcmsSt = BigDecimal.ZERO;
        BigDecimal valorIpi = BigDecimal.ZERO;
        BigDecimal valorPis = BigDecimal.ZERO;
        BigDecimal valorCofins = BigDecimal.ZERO;
        BigDecimal valorNotaFiscal = BigDecimal.ZERO;

        BigDecimal totalServicos = BigDecimal.ZERO;
        BigDecimal baseCalculoIssqn = BigDecimal.ZERO;
        BigDecimal valorIssqn = BigDecimal.ZERO;
        BigDecimal valorPisIssqn = BigDecimal.ZERO;
        BigDecimal valorCofinsIssqn = BigDecimal.ZERO;

        NfeCabecalhoVO nfeCabecalho = (NfeCabecalhoVO) nfeDetalhe.getFormDadosNfe().getVOModel().getValueObject();

        //Se houver CFOP cadastrado na Operação Fiscal, a nota é de serviços
        if (nfeCabecalho.getTributOperacaoFiscal().getCfop() != null) {
            for (int i = 0; i < itensNfe.size(); i++) {
                totalServicos = totalServicos.add(itensNfe.get(i).getValorTotal());
                valorFrete = valorFrete.add(itensNfe.get(i).getValorFrete());
                valorSeguro = valorSeguro.add(itensNfe.get(i).getValorSeguro());
                valorOutrasDespesas = valorOutrasDespesas.add(itensNfe.get(i).getValorOutrasDespesas());
                desconto = desconto.add(itensNfe.get(i).getValorDesconto());
                baseCalculoIssqn = baseCalculoIssqn.add(itensNfe.get(i).getNfeDetalheImpostoIssqn().getBaseCalculoIssqn());
                valorIssqn = valorIssqn.add(itensNfe.get(i).getNfeDetalheImpostoIssqn().getValorIssqn());
                valorPisIssqn = valorPisIssqn.add(itensNfe.get(i).getNfeDetalheImpostoPis().getValorPis());
                valorCofinsIssqn = valorCofinsIssqn.add(itensNfe.get(i).getNfeDetalheImpostoCofins().getValorCofins());
            }
            //valorNotaFiscal = totalServicos.add(valorPis).add(valorCofins).add(valorOutrasDespesas).subtract(desconto);
            valorNotaFiscal = totalServicos.add(valorOutrasDespesas).subtract(desconto);
        } else {
            for (int i = 0; i < itensNfe.size(); i++) {
                totalProdutos = totalProdutos.add(itensNfe.get(i).getValorTotal());
                valorFrete = valorFrete.add(itensNfe.get(i).getValorFrete());
                valorSeguro = valorSeguro.add(itensNfe.get(i).getValorSeguro());
                valorOutrasDespesas = valorOutrasDespesas.add(itensNfe.get(i).getValorOutrasDespesas());
                desconto = desconto.add(itensNfe.get(i).getValorDesconto());
                if (itensNfe.get(i).getNfeDetalheImpostoIcms().getBaseCalculoIcms() != null) {
                    baseCalculoIcms = baseCalculoIcms.add(itensNfe.get(i).getNfeDetalheImpostoIcms().getBaseCalculoIcms());
                }
                if (itensNfe.get(i).getNfeDetalheImpostoIcms().getValorIcms() != null) {
                    valorIcms = valorIcms.add(itensNfe.get(i).getNfeDetalheImpostoIcms().getValorIcms());
                }
                if (itensNfe.get(i).getNfeDetalheImpostoIcms().getValorBaseCalculoIcmsSt() != null) {
                    baseCalculoIcmsSt = baseCalculoIcmsSt.add(itensNfe.get(i).getNfeDetalheImpostoIcms().getValorBaseCalculoIcmsSt());
                }
                if (itensNfe.get(i).getNfeDetalheImpostoIcms().getValorIcmsSt() != null) {
                    valorIcmsSt = valorIcmsSt.add(itensNfe.get(i).getNfeDetalheImpostoIcms().getValorIcmsSt());
                }
                if (itensNfe.get(i).getNfeDetalheImpostoIpi().getValorIpi() != null) {
                    valorIpi = valorIpi.add(itensNfe.get(i).getNfeDetalheImpostoIpi().getValorIpi());
                }
                if (itensNfe.get(i).getNfeDetalheImpostoPis().getValorPis() != null) {
                    valorPis = valorPis.add(itensNfe.get(i).getNfeDetalheImpostoPis().getValorPis());
                }
                if (itensNfe.get(i).getNfeDetalheImpostoCofins().getValorCofins() != null) {
                    valorCofins = valorCofins.add(itensNfe.get(i).getNfeDetalheImpostoCofins().getValorCofins());
                }
            }
            //valorNotaFiscal = totalProdutos.add(valorIcmsSt).add(valorPis).add(valorCofins).add(valorIpi).add(valorOutrasDespesas).subtract(desconto);
            valorNotaFiscal = totalProdutos.add(valorIpi).add(valorOutrasDespesas).subtract(desconto);
        }

        nfeCabecalho.setValorFrete(valorFrete);
        nfeCabecalho.setValorDespesasAcessorias(valorOutrasDespesas);
        nfeCabecalho.setValorSeguro(valorSeguro);
        nfeCabecalho.setValorDesconto(desconto);

        nfeCabecalho.setValorServicos(totalServicos);
        nfeCabecalho.setBaseCalculoIssqn(baseCalculoIssqn);
        nfeCabecalho.setValorIssqn(valorIssqn);
        nfeCabecalho.setValorPisIssqn(valorPisIssqn);
        nfeCabecalho.setValorCofinsIssqn(valorCofinsIssqn);

        nfeCabecalho.setValorTotalProdutos(totalProdutos);
        nfeCabecalho.setBaseCalculoIcms(baseCalculoIcms);
        nfeCabecalho.setValorIcms(valorIcms);
        nfeCabecalho.setBaseCalculoIcmsSt(baseCalculoIcmsSt);
        nfeCabecalho.setValorIcmsSt(valorIcmsSt);
        nfeCabecalho.setValorIpi(valorIpi);
        nfeCabecalho.setValorPis(valorPis);
        nfeCabecalho.setValorCofins(valorCofins);

        nfeCabecalho.setValorTotal(valorNotaFiscal);

        nfeDetalhe.getFormDadosNfe().pull();
    }

    private void valoresPadrao() {
        NfeCabecalhoVO nfeCabecalho = (NfeCabecalhoVO) nfeDetalhe.getFormDadosNfe().getVOModel().getValueObject();

        nfeCabecalho.setTipoOperacao(1);
        nfeCabecalho.setStatusNota(0);
        nfeCabecalho.setFormatoImpressaoDanfe(1);
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
        nfeCabecalho.setValorImpostoImportacao(BigDecimal.ZERO);
        nfeCabecalho.setBaseCalculoIssqn(BigDecimal.ZERO);
        nfeCabecalho.setValorIssqn(BigDecimal.ZERO);
        nfeCabecalho.setValorPisIssqn(BigDecimal.ZERO);
        nfeCabecalho.setValorCofinsIssqn(BigDecimal.ZERO);
        nfeCabecalho.setValorServicos(BigDecimal.ZERO);
        nfeCabecalho.setValorRetidoPis(BigDecimal.ZERO);
        nfeCabecalho.setValorRetidoCofins(BigDecimal.ZERO);
        nfeCabecalho.setValorRetidoCsll(BigDecimal.ZERO);
        nfeCabecalho.setBaseCalculoIrrf(BigDecimal.ZERO);
        nfeCabecalho.setValorRetidoIrrf(BigDecimal.ZERO);
        nfeCabecalho.setBaseCalculoPrevidencia(BigDecimal.ZERO);
        nfeCabecalho.setValorRetidoPrevidencia(BigDecimal.ZERO);
        nfeCabecalho.setValorIcmsDesonerado(BigDecimal.ZERO);

        nfeDetalhe.getFormDadosNfe().pull();
    }

    private void retornaEdicaoForm() {
        nfeDetalhe.getFormDestinatario().setMode(Consts.EDIT);
        nfeDetalhe.getFormLocalEntrega().setMode(Consts.EDIT);
        nfeDetalhe.getFormLocalRetirada().setMode(Consts.EDIT);
        nfeDetalhe.getFormTransporte().setMode(Consts.EDIT);
        nfeDetalhe.getFormFatura().setMode(Consts.EDIT);
    }

    public void enviaNfe() throws Exception {
        if (nfeDetalhe.getFormDadosNfe().getMode() != Consts.READONLY) {
            JOptionPane.showMessageDialog(nfeDetalhe, "É necessário salvar a NF-e antes do envio", "Aviso do Sistema", JOptionPane.INFORMATION_MESSAGE);
        } else {
            EmpresaVO empresa = (EmpresaVO) Container.getContainer().get("empresa");
            NfeCabecalhoVO nfeCabecalho = (NfeCabecalhoVO) nfeDetalhe.getFormDadosNfe().getVOModel().getValueObject();

            if (nfeCabecalho.getStatusNota() == 5) {
                throw new Exception("Esta NF-e já foi autorizada. Envio não realizado");
            }
            if (nfeCabecalho.getStatusNota() == 6) {
                throw new Exception("Esta NF-e já foi cancelada. Envio não realizado");
            }

            SelecionaCertificado selecionaCertificado = new SelecionaCertificado(null, true);
            selecionaCertificado.setVisible(true);
            if (!selecionaCertificado.isCancelado()) {
                Certificado certificado = selecionaCertificado.getDadosCertificado();

                Response res = ClientUtils.getData(acaoServidor, new Object[]{100, nfeCabecalho.getId(), certificado, empresa});
                if (res.isError()) {
                    throw new Exception(res.getErrorMessage());
                }

                RespostaSefaz respostaSefaz = (RespostaSefaz) ((VOResponse) res).getVo();

                if (respostaSefaz.getResposta().contains("Lote em processamento")) {
                    if (JOptionPane.showConfirmDialog(null, "Nr recibo: " + respostaSefaz.getNumeroRecibo() + "\nLote ainda está em Processamento. Consultar novamente?", "Pergunta do Sistema", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                        consultaReciboNfe(respostaSefaz, nfeCabecalho.getId());
                    }
                } else {
                    JOptionPane.showMessageDialog(nfeDetalhe, respostaSefaz.getResposta(), "Aviso do Sistema", JOptionPane.INFORMATION_MESSAGE);
                    nfeDetalhe.getFormDadosNfe().reload();
                }
            }
        }
    }

    private void consultaReciboNfe(RespostaSefaz respostaSefaz, Integer idNfe) throws Exception {
        Response res = ClientUtils.getData(acaoServidor, new Object[]{104, idNfe, respostaSefaz.getNumeroRecibo(), respostaSefaz.getXmlEnviNfe()});
        if (res.isError()) {
            throw new Exception(res.getErrorMessage());
        }

        respostaSefaz = (RespostaSefaz) ((VOResponse) res).getVo();

        if (respostaSefaz.getResposta().contains("Lote em processamento")) {
            if (JOptionPane.showConfirmDialog(null, "Nr recibo: " + respostaSefaz.getNumeroRecibo() + "\nLote ainda está em Processamento. Consultar novamente?", "Pergunta do Sistema", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                consultaReciboNfe(respostaSefaz, idNfe);
            }
        } else {
            JOptionPane.showMessageDialog(nfeDetalhe, respostaSefaz.getResposta(), "Aviso do Sistema", JOptionPane.INFORMATION_MESSAGE);
            nfeDetalhe.getFormDadosNfe().reload();
        }
    }

    public void imprimeDanfe() throws Exception {
        NfeCabecalhoVO nfeCabecalho = (NfeCabecalhoVO) nfeDetalhe.getFormDadosNfe().getVOModel().getValueObject();
        if (nfeCabecalho.getStatusNota() != 5) {
            throw new Exception("NF-e não autorizada. Impressão do Danfe não permitida!");
        }

        String justificativa = null;
        if (nfeCabecalho.getQuantidadeImpressaoDanfe() != null) {
            justificativa = JOptionPane.showInputDialog(nfeDetalhe, "Informe a justificativa para reimpressão:");
        } else {
            justificativa = "Primeira impressão";
            nfeCabecalho.setQuantidadeImpressaoDanfe(0);
        }

        if (justificativa == null || justificativa.trim().equals("")) {
            throw new Exception("É necessário informar uma justificativa para reimpressão do danfe.");
        }

        nfeCabecalho.setQuantidadeImpressaoDanfe(nfeCabecalho.getQuantidadeImpressaoDanfe() + 1);
        nfeDetalhe.getFormDadosNfe().pull();

        Response res = ClientUtils.getData(acaoServidor, new Object[]{101, nfeCabecalho});
        if (res.isError()) {
            throw new Exception(res.getErrorMessage());
        }

        ArquivoVO arquivo = (ArquivoVO) ((VOResponse) res).getVo();
        File file = File.createTempFile(nfeCabecalho.getChaveAcesso(), ".pdf");
        file.deleteOnExit();
        FileOutputStream out = new FileOutputStream(file.getAbsolutePath());
        byte[] bb = arquivo.getFile();
        out.write(bb);
        out.close();

        ClientUtils.displayURL(file.getAbsolutePath());
    }

    public void cancelaNfe() throws Exception {
        NfeCabecalhoVO nfeCabecalho = (NfeCabecalhoVO) nfeDetalhe.getFormDadosNfe().getVOModel().getValueObject();
        if (nfeCabecalho.getStatusNota() == 6) {
            throw new Exception("NF-e já cancelada. Cancelamento não permitido!");
        }
        if (nfeCabecalho.getStatusNota() != 5) {
            throw new Exception("NF-e não autorizada. Cancelamento não permitido!");
        }

        String justificativa = JOptionPane.showInputDialog(nfeDetalhe, "Informe a justificativa do cancelamento");
        if (justificativa != null) {
            if (justificativa.trim().equals("")) {
                throw new Exception("É necessário informar uma justificativa para o cancelamento da NF-e.");
            }
            if (justificativa.trim().length() < 15) {
                throw new Exception("A justificativa deve ter no mínimo 15 caracteres.");
            }
            if (justificativa.trim().length() > 255) {
                throw new Exception("A justificativa deve ter no máximo 255 caracteres.");
            }
            SelecionaCertificado selecionaCertificado = new SelecionaCertificado(null, true);
            selecionaCertificado.setVisible(true);
            if (!selecionaCertificado.isCancelado()) {
                Certificado certificado = selecionaCertificado.getDadosCertificado();

                Response res = ClientUtils.getData(acaoServidor, new Object[]{102, nfeCabecalho.getId(), certificado, justificativa});
                if (res.isError()) {
                    throw new Exception(res.getErrorMessage());
                }

                RespostaSefaz respostaSefaz = (RespostaSefaz) ((VOResponse) res).getVo();

                JOptionPane.showMessageDialog(nfeDetalhe, respostaSefaz.getResposta(), "Aviso do Sistema", JOptionPane.INFORMATION_MESSAGE);
                nfeDetalhe.getFormDadosNfe().reload();
            }
        }
    }

    public void enviaEmailDestinatario() throws Exception {
        NfeCabecalhoVO nfeCabecalho = (NfeCabecalhoVO) nfeDetalhe.getFormDadosNfe().getVOModel().getValueObject();
        NfeDestinatarioVO nfeDestinatario = (NfeDestinatarioVO) nfeDetalhe.getFormDestinatario().getVOModel().getValueObject();
        if (nfeCabecalho.getStatusNota() != 5) {
            throw new Exception("NF-e não autorizada. Envio de email não realizado!");
        }
        Response res = ClientUtils.getData(acaoServidor, new Object[]{103, nfeCabecalho, nfeDestinatario});
        if (res.isError()) {
            throw new Exception(res.getErrorMessage());
        }
        JOptionPane.showMessageDialog(nfeDetalhe, "Email enviado com sucesso!", "Aviso do Sistema", JOptionPane.INFORMATION_MESSAGE);
    }

    public void cartaCorrecaoNfe() throws Exception {
        NfeCabecalhoVO nfeCabecalho = (NfeCabecalhoVO) nfeDetalhe.getFormDadosNfe().getVOModel().getValueObject();
        if (nfeCabecalho.getStatusNota() == 6) {
            throw new Exception("NF-e já cancelada. Carta de Correção não permitida!");
        }
        if (nfeCabecalho.getStatusNota() != 5) {
            throw new Exception("NF-e não autorizada. Carta de Correção não permitida!");
        }

        String justificativa = JOptionPane.showInputDialog(nfeDetalhe, "Informe a justificativa da Carta de Correção");
        if (justificativa != null) {
            if (justificativa.trim().equals("")) {
                throw new Exception("É necessário informar uma justificativa para a Carta de Correção.");
            }
            if (justificativa.trim().length() < 15) {
                throw new Exception("A justificativa deve ter no mínimo 15 caracteres.");
            }
            if (justificativa.trim().length() > 255) {
                throw new Exception("A justificativa deve ter no máximo 255 caracteres.");
            }
            SelecionaCertificado selecionaCertificado = new SelecionaCertificado(null, true);
            selecionaCertificado.setVisible(true);
            if (!selecionaCertificado.isCancelado()) {
                Certificado certificado = selecionaCertificado.getDadosCertificado();

                Response res = ClientUtils.getData(acaoServidor, new Object[]{105, nfeCabecalho.getId(), certificado, justificativa});
                if (res.isError()) {
                    throw new Exception(res.getErrorMessage());
                }

                RespostaSefaz respostaSefaz = (RespostaSefaz) ((VOResponse) res).getVo();

                JOptionPane.showMessageDialog(nfeDetalhe, respostaSefaz.getResposta(), "Aviso do Sistema", JOptionPane.INFORMATION_MESSAGE);
                nfeDetalhe.getFormDadosNfe().reload();
            }
        }
    }

    public void geraLancamentoReceber() throws Exception {
        NfeCabecalhoVO nfeCabecalho = (NfeCabecalhoVO) nfeDetalhe.getFormDadosNfe().getVOModel().getValueObject();

        if (nfeCabecalho.getStatusNota() == 6) {
            throw new Exception("NF-e já cancelada. Procedimento não permitido!");
        }
        if (nfeCabecalho.getStatusNota() != 5) {
            throw new Exception("NF-e não autorizada. Procedimento não permitido!");
        }

        Integer resposta = JOptionPane.showConfirmDialog(nfeDetalhe, "Gerar Lançamento Receber?", "Pergunta do Sistema", JOptionPane.YES_NO_OPTION);

        if (resposta == JOptionPane.YES_OPTION) {
            Response res = ClientUtils.getData(acaoServidor, new Object[]{106, nfeCabecalho.getId()});
            if (res.isError()) {
                throw new Exception(res.getErrorMessage());
            }

            JOptionPane.showMessageDialog(nfeDetalhe, "Lançamento gerado com sucesso", "Aviso do Sistema", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public void geraEtiqueta() throws Exception {
        NfeCabecalhoVO nfeCabecalho = (NfeCabecalhoVO) nfeDetalhe.getFormDadosNfe().getVOModel().getValueObject();
        if (nfeCabecalho.getStatusNota() != 5) {
            throw new Exception("NF-e não autorizada. Geração de etiqueta não permitida!");
        }

        Response res = ClientUtils.getData(acaoServidor, new Object[]{107, nfeCabecalho});
        if (res.isError()) {
            throw new Exception(res.getErrorMessage());
        }

        ArquivoVO arquivo = (ArquivoVO) ((VOResponse) res).getVo();
        File file = File.createTempFile("etiqueta", ".pdf");
        file.deleteOnExit();
        FileOutputStream out = new FileOutputStream(file.getAbsolutePath());
        byte[] bb = arquivo.getFile();
        out.write(bb);
        out.close();

        ClientUtils.displayURL(file.getAbsolutePath());
    }    
}
