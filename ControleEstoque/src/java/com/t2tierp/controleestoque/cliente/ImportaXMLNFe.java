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

import br.inf.portalfiscal.nfe.procnfe.TIpi;
import br.inf.portalfiscal.nfe.procnfe.TLocal;
import br.inf.portalfiscal.nfe.procnfe.TNFe;
import br.inf.portalfiscal.nfe.procnfe.TNfeProc;
import br.inf.portalfiscal.nfe.procnfe.TProtNFe;
import br.inf.portalfiscal.nfe.procnfe.TVeiculo;
import com.t2tierp.nfe.java.NfeCabecalhoVO;
import com.t2tierp.nfe.java.NfeCteReferenciadoVO;
import com.t2tierp.nfe.java.NfeCupomFiscalReferenciadoVO;
import com.t2tierp.nfe.java.NfeDeclaracaoImportacaoVO;
import com.t2tierp.nfe.java.NfeDetEspecificoArmamentoVO;
import com.t2tierp.nfe.java.NfeDetEspecificoCombustivelVO;
import com.t2tierp.nfe.java.NfeDetEspecificoMedicamentoVO;
import com.t2tierp.nfe.java.NfeDetEspecificoVeiculoVO;
import com.t2tierp.nfe.java.NfeDetalheImpostoCofinsVO;
import com.t2tierp.nfe.java.NfeDetalheImpostoIcmsVO;
import com.t2tierp.nfe.java.NfeDetalheImpostoIiVO;
import com.t2tierp.nfe.java.NfeDetalheImpostoIpiVO;
import com.t2tierp.nfe.java.NfeDetalheImpostoIssqnVO;
import com.t2tierp.nfe.java.NfeDetalheImpostoPisVO;
import com.t2tierp.nfe.java.NfeDetalheVO;
import com.t2tierp.nfe.java.NfeDuplicataVO;
import com.t2tierp.nfe.java.NfeEmitenteVO;
import com.t2tierp.nfe.java.NfeFaturaVO;
import com.t2tierp.nfe.java.NfeImportacaoDetalheVO;
import com.t2tierp.nfe.java.NfeLocalEntregaVO;
import com.t2tierp.nfe.java.NfeLocalRetiradaVO;
import com.t2tierp.nfe.java.NfeNfReferenciadaVO;
import com.t2tierp.nfe.java.NfeProdRuralReferenciadaVO;
import com.t2tierp.nfe.java.NfeReferenciadaVO;
import com.t2tierp.nfe.java.NfeTransporteReboqueVO;
import com.t2tierp.nfe.java.NfeTransporteVO;
import com.t2tierp.nfe.java.NfeTransporteVolumeLacreVO;
import com.t2tierp.nfe.java.NfeTransporteVolumeVO;
import java.io.File;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.Unmarshaller;

public class ImportaXMLNFe {

    public Map importarXmlNFe(File arquivoXml) throws Exception {

        Map map = new HashMap();
        NfeCabecalhoVO nfeCabecalho = new NfeCabecalhoVO();
        NfeEmitenteVO emitente = new NfeEmitenteVO();
        List<NfeDetalheVO> listaNfeDetalhe = new ArrayList();

        JAXBContext jc = JAXBContext.newInstance("br.inf.portalfiscal.nfe.procnfe");
        Unmarshaller unmarshaller = jc.createUnmarshaller();

        JAXBElement<TNfeProc> element = (JAXBElement) unmarshaller.unmarshal(arquivoXml);
        TNfeProc nfeProc = element.getValue();

        TNFe nfe = nfeProc.getNFe();
        TNFe.InfNFe infNfe = nfe.getInfNFe();
        TNFe.InfNFe.Ide ide = infNfe.getIde();
        TNFe.InfNFe.Emit emit = infNfe.getEmit();
        List<TNFe.InfNFe.Det> listaDet = infNfe.getDet();
        TProtNFe protNfe = nfeProc.getProtNFe();

        //SimpleDateFormat formatoData = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat formatoData = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX");
        DecimalFormatSymbols simboloDecimal = DecimalFormatSymbols.getInstance();
        simboloDecimal.setDecimalSeparator('.');
        DecimalFormat formatoQuantidade = new DecimalFormat("0.0000", simboloDecimal);
        DecimalFormat formatoValor = new DecimalFormat("0.00", simboloDecimal);
        DecimalFormat formatoNumero = new DecimalFormat("000000000");
        DecimalFormat formatoSerie = new DecimalFormat("000");

        //cabecalho
        nfeCabecalho.setCodigoNumerico(ide.getCNF());
        nfeCabecalho.setNaturezaOperacao(ide.getNatOp());
        nfeCabecalho.setIndicadorFormaPagamento(Integer.valueOf(ide.getIndPag()));
        nfeCabecalho.setCodigoModelo(ide.getMod());
        nfeCabecalho.setSerie(formatoSerie.format(Integer.valueOf(ide.getSerie())));
        nfeCabecalho.setNumero(formatoNumero.format(Double.valueOf(ide.getNNF())));
        nfeCabecalho.setDataHoraEmissao(formatoData.parse(ide.getDhEmi()));
        if (ide.getDhSaiEnt() != null) {
            nfeCabecalho.setDataHoraEntradaSaida(formatoData.parse(ide.getDhSaiEnt()));
        }
        nfeCabecalho.setTipoEmissao(Integer.valueOf(ide.getTpEmis()));
        nfeCabecalho.setVersaoProcessoEmissao(ide.getVerProc());
        nfeCabecalho.setCodigoMunicipio(Integer.valueOf(ide.getCMunFG()));
        nfeCabecalho.setFinalidadeEmissao(Integer.valueOf(ide.getFinNFe()));
        nfeCabecalho.setTipoOperacao(0);
        nfeCabecalho.setDigitoChaveAcesso(ide.getCDV());
        nfeCabecalho.setFormatoImpressaoDanfe(Integer.valueOf(ide.getTpImp()));
        nfeCabecalho.setChaveAcesso(protNfe.getInfProt().getChNFe());
        if (protNfe.getInfProt().getCStat().equals("100")) {
            nfeCabecalho.setStatusNota(5);
        }

        //cabecalho - totais icms
        TNFe.InfNFe.Total.ICMSTot icmsTot = nfe.getInfNFe().getTotal().getICMSTot();
        if (icmsTot != null) {
            nfeCabecalho.setBaseCalculoIcms(BigDecimal.valueOf(formatoValor.parse(icmsTot.getVBC()).doubleValue()));
            nfeCabecalho.setValorIcms(BigDecimal.valueOf(formatoValor.parse(icmsTot.getVICMS()).doubleValue()));
            nfeCabecalho.setBaseCalculoIcmsSt(BigDecimal.valueOf(formatoValor.parse(icmsTot.getVBCST()).doubleValue()));
            nfeCabecalho.setValorIcmsSt(BigDecimal.valueOf(formatoValor.parse(icmsTot.getVST()).doubleValue()));
            nfeCabecalho.setValorTotalProdutos(BigDecimal.valueOf(formatoValor.parse(icmsTot.getVProd()).doubleValue()));
            nfeCabecalho.setValorFrete(BigDecimal.valueOf(formatoValor.parse(icmsTot.getVFrete()).doubleValue()));
            nfeCabecalho.setValorSeguro(BigDecimal.valueOf(formatoValor.parse(icmsTot.getVSeg()).doubleValue()));
            nfeCabecalho.setValorDesconto(BigDecimal.valueOf(formatoValor.parse(icmsTot.getVDesc()).doubleValue()));
            nfeCabecalho.setValorImpostoImportacao(BigDecimal.valueOf(formatoValor.parse(icmsTot.getVII()).doubleValue()));
            nfeCabecalho.setValorIpi(BigDecimal.valueOf(formatoValor.parse(icmsTot.getVIPI()).doubleValue()));
            nfeCabecalho.setValorPis(BigDecimal.valueOf(formatoValor.parse(icmsTot.getVPIS()).doubleValue()));
            nfeCabecalho.setValorCofins(BigDecimal.valueOf(formatoValor.parse(icmsTot.getVCOFINS()).doubleValue()));
            nfeCabecalho.setValorDespesasAcessorias(BigDecimal.valueOf(formatoValor.parse(icmsTot.getVOutro()).doubleValue()));
            nfeCabecalho.setValorTotal(BigDecimal.valueOf(formatoValor.parse(icmsTot.getVNF()).doubleValue()));
        }

        //cabecalho - totais issqn
        TNFe.InfNFe.Total.ISSQNtot issqnTot = nfe.getInfNFe().getTotal().getISSQNtot();
        if (issqnTot != null) {
            nfeCabecalho.setValorServicos(BigDecimal.valueOf(formatoValor.parse(issqnTot.getVServ()).doubleValue()));
            if (issqnTot.getVBC() != null) {
                nfeCabecalho.setBaseCalculoIssqn(BigDecimal.valueOf(formatoValor.parse(issqnTot.getVBC()).doubleValue()));
            }
            if (issqnTot.getVISS() != null) {
                nfeCabecalho.setValorIssqn(BigDecimal.valueOf(formatoValor.parse(issqnTot.getVISS()).doubleValue()));
            }
            if (issqnTot.getVPIS() != null) {
                nfeCabecalho.setValorPisIssqn(BigDecimal.valueOf(formatoValor.parse(issqnTot.getVPIS()).doubleValue()));
            }
            if (issqnTot.getVCOFINS() != null) {
                nfeCabecalho.setValorCofinsIssqn(BigDecimal.valueOf(formatoValor.parse(issqnTot.getVCOFINS()).doubleValue()));
            }
        }

        //cabecalho - retenções
        TNFe.InfNFe.Total.RetTrib retTrib = nfe.getInfNFe().getTotal().getRetTrib();
        if (retTrib != null) {
            nfeCabecalho.setValorRetidoPis(BigDecimal.valueOf(formatoValor.parse(retTrib.getVRetPIS()).doubleValue()));
            nfeCabecalho.setValorRetidoCofins(BigDecimal.valueOf(formatoValor.parse(retTrib.getVRetCOFINS()).doubleValue()));
            nfeCabecalho.setValorRetidoCsll(BigDecimal.valueOf(formatoValor.parse(retTrib.getVRetCSLL()).doubleValue()));
            nfeCabecalho.setBaseCalculoIrrf(BigDecimal.valueOf(formatoValor.parse(retTrib.getVBCIRRF()).doubleValue()));
            nfeCabecalho.setValorRetidoIrrf(BigDecimal.valueOf(formatoValor.parse(retTrib.getVIRRF()).doubleValue()));
            nfeCabecalho.setBaseCalculoPrevidencia(BigDecimal.valueOf(formatoValor.parse(retTrib.getVBCRetPrev()).doubleValue()));
            nfeCabecalho.setValorRetidoPrevidencia(BigDecimal.valueOf(formatoValor.parse(retTrib.getVRetPrev()).doubleValue()));
        }

        //cabecalho - informações adicionais
        TNFe.InfNFe.Exporta exporta = infNfe.getExporta();
        if (exporta != null) {
            nfeCabecalho.setComexUfEmbarque(exporta.getXLocExporta());
            nfeCabecalho.setComexLocalEmbarque(exporta.getXLocDespacho());
        }

        TNFe.InfNFe.Compra compra = infNfe.getCompra();
        if (compra != null) {
            nfeCabecalho.setCompraNotaEmpenho(compra.getXNEmp());
            nfeCabecalho.setCompraPedido(compra.getXPed());
            nfeCabecalho.setCompraContrato(compra.getXCont());
        }

        TNFe.InfNFe.InfAdic infAdic = infNfe.getInfAdic();
        if (infAdic != null) {
            nfeCabecalho.setInformacoesAddFisco(infAdic.getInfAdFisco());
            nfeCabecalho.setInformacoesAddContribuinte(infAdic.getInfCpl());
        }

        //emitente
        emitente.setCpfCnpj(emit.getCNPJ());
        emitente.setInscricaoEstadual(emit.getIE());
        emitente.setNome(emit.getXNome());
        emitente.setFantasia(emit.getXFant());
        emitente.setLogradouro(emit.getEnderEmit().getXLgr());
        emitente.setNumero(emit.getEnderEmit().getNro());
        emitente.setComplemento(emit.getEnderEmit().getXCpl());
        emitente.setBairro(emit.getEnderEmit().getXBairro());
        emitente.setCodigoMunicipio(Integer.valueOf(emit.getEnderEmit().getCMun()));
        emitente.setNomeMunicipio(emit.getEnderEmit().getXMun());
        emitente.setUf(emit.getEnderEmit().getUF().value());
        emitente.setCep(emit.getEnderEmit().getCEP());
        emitente.setCrt(emit.getCRT());
        emitente.setCodigoPais(Integer.valueOf(emit.getEnderEmit().getCPais()));
        emitente.setNomePais(emit.getEnderEmit().getXPais());
        emitente.setTelefone(emit.getEnderEmit().getFone());
        emitente.setInscricaoEstadualSt(emit.getIEST());
        emitente.setInscricaoMunicipal(emit.getIM());
        emitente.setCrt(emit.getCRT());
        emitente.setCnae(emit.getCNAE());

        //detalhe
        TNFe.InfNFe.Det det;
        NfeDetalheVO nfeDetalhe;
        BigDecimal valorTotalProduto;
        for (int i = 0; i < listaDet.size(); i++) {
            det = listaDet.get(i);
            nfeDetalhe = new NfeDetalheVO();

            TNFe.InfNFe.Det.Prod prod = det.getProd();

            valorTotalProduto = BigDecimal.valueOf(formatoValor.parse(prod.getQCom()).doubleValue()).multiply(BigDecimal.valueOf(formatoValor.parse(prod.getVUnCom()).doubleValue()));

            nfeDetalhe.setNumeroItem(Integer.valueOf(det.getNItem()));
            nfeDetalhe.setCodigoProduto(prod.getCProd());
            nfeDetalhe.setGtin(prod.getCEAN());
            nfeDetalhe.setNomeProduto(prod.getXProd());
            nfeDetalhe.setNcm(prod.getNCM());
            if (prod.getEXTIPI() != null) {
                nfeDetalhe.setExTipi(Integer.valueOf(prod.getEXTIPI()));
            }
            nfeDetalhe.setCfop(Integer.valueOf(prod.getCFOP()));
            nfeDetalhe.setUnidadeComercial(prod.getUCom());
            nfeDetalhe.setQuantidadeComercial(BigDecimal.valueOf(formatoQuantidade.parse(prod.getQCom()).doubleValue()));
            nfeDetalhe.setValorUnitarioComercial(BigDecimal.valueOf(formatoValor.parse(prod.getVUnCom()).doubleValue()));
            nfeDetalhe.setValorBrutoProduto(BigDecimal.valueOf(formatoValor.parse(prod.getVProd()).doubleValue()));
            nfeDetalhe.setGtinUnidadeTributavel(prod.getCEANTrib());
            nfeDetalhe.setUnidadeTributavel(prod.getUTrib());
            nfeDetalhe.setQuantidadeTributavel(BigDecimal.valueOf(formatoQuantidade.parse(prod.getQTrib()).doubleValue()));
            nfeDetalhe.setValorUnitarioTributavel(BigDecimal.valueOf(formatoValor.parse(prod.getVUnTrib()).doubleValue()));
            if (prod.getVFrete() != null) {
                nfeDetalhe.setValorFrete(BigDecimal.valueOf(formatoValor.parse(prod.getVFrete()).doubleValue()));
                valorTotalProduto = valorTotalProduto.add(nfeDetalhe.getValorFrete());
            }
            if (prod.getVSeg() != null) {
                nfeDetalhe.setValorSeguro(BigDecimal.valueOf(formatoValor.parse(prod.getVSeg()).doubleValue()));
                valorTotalProduto = valorTotalProduto.add(nfeDetalhe.getValorSeguro());
            }
            if (prod.getVDesc() != null) {
                nfeDetalhe.setValorDesconto(BigDecimal.valueOf(formatoValor.parse(prod.getVDesc()).doubleValue()));
                valorTotalProduto = valorTotalProduto.subtract(nfeDetalhe.getValorDesconto());
            }
            if (prod.getVOutro() != null) {
                nfeDetalhe.setValorOutrasDespesas(BigDecimal.valueOf(formatoValor.parse(prod.getVOutro()).doubleValue()));
                valorTotalProduto = valorTotalProduto.add(nfeDetalhe.getValorOutrasDespesas());
            }
            nfeDetalhe.setEntraTotal(Integer.valueOf(prod.getIndTot()));
            nfeDetalhe.setValorSubtotal(BigDecimal.valueOf(formatoValor.parse(prod.getQCom()).doubleValue()).multiply(BigDecimal.valueOf(formatoValor.parse(prod.getVUnCom()).doubleValue())));
            nfeDetalhe.setValorTotal(valorTotalProduto);
            nfeDetalhe.setInformacoesAdicionais(det.getInfAdProd());

            //imposto - icms
            TNFe.InfNFe.Det.Imposto.ICMS icms = null;
            if (det.getImposto().getContent() != null) {
                for (JAXBElement e : det.getImposto().getContent()) {
                    if (e.getValue() instanceof TNFe.InfNFe.Det.Imposto.ICMS) {
                        icms = (TNFe.InfNFe.Det.Imposto.ICMS) e.getValue();
                        break;
                    }
                }
            }
            if (icms != null) {
                NfeDetalheImpostoIcmsVO impostoIcms = new NfeDetalheImpostoIcmsVO();
                impostoIcms.setNfeDetalhe(nfeDetalhe);
                nfeDetalhe.setNfeDetalheImpostoIcms(impostoIcms);
                if (icms.getICMS00() != null) {
                    impostoIcms.setOrigemMercadoria(Integer.valueOf(icms.getICMS00().getOrig()));
                    impostoIcms.setCstIcms(icms.getICMS00().getCST());
                    impostoIcms.setModalidadeBcIcms(Integer.valueOf(icms.getICMS00().getModBC()));
                    impostoIcms.setBaseCalculoIcms(BigDecimal.valueOf(formatoValor.parse(icms.getICMS00().getVBC()).doubleValue()));
                    impostoIcms.setAliquotaIcms(BigDecimal.valueOf(formatoValor.parse(icms.getICMS00().getPICMS()).doubleValue()));
                    impostoIcms.setValorIcms(BigDecimal.valueOf(formatoValor.parse(icms.getICMS00().getVICMS()).doubleValue()));
                }
                if (icms.getICMS10() != null) {
                    impostoIcms.setOrigemMercadoria(Integer.valueOf(icms.getICMS10().getOrig()));
                    impostoIcms.setCstIcms(icms.getICMS10().getCST());
                    impostoIcms.setModalidadeBcIcms(Integer.valueOf(icms.getICMS10().getModBC()));
                    impostoIcms.setBaseCalculoIcms(BigDecimal.valueOf(formatoValor.parse(icms.getICMS10().getVBC()).doubleValue()));
                    impostoIcms.setAliquotaIcms(BigDecimal.valueOf(formatoValor.parse(icms.getICMS10().getPICMS()).doubleValue()));
                    impostoIcms.setValorIcms(BigDecimal.valueOf(formatoValor.parse(icms.getICMS10().getVICMS()).doubleValue()));
                }
                if (icms.getICMS20() != null) {
                    impostoIcms.setOrigemMercadoria(Integer.valueOf(icms.getICMS20().getOrig()));
                    impostoIcms.setCstIcms(icms.getICMS20().getCST());
                    impostoIcms.setModalidadeBcIcms(Integer.valueOf(icms.getICMS20().getModBC()));
                    impostoIcms.setBaseCalculoIcms(BigDecimal.valueOf(formatoValor.parse(icms.getICMS20().getVBC()).doubleValue()));
                    impostoIcms.setAliquotaIcms(BigDecimal.valueOf(formatoValor.parse(icms.getICMS20().getPICMS()).doubleValue()));
                    impostoIcms.setValorIcms(BigDecimal.valueOf(formatoValor.parse(icms.getICMS20().getVICMS()).doubleValue()));
                    impostoIcms.setTaxaReducaoBcIcms(BigDecimal.valueOf(formatoValor.parse(icms.getICMS20().getPRedBC()).doubleValue()));
                }
                if (icms.getICMS30() != null) {
                    impostoIcms.setOrigemMercadoria(Integer.valueOf(icms.getICMS30().getOrig()));
                    impostoIcms.setCstIcms(icms.getICMS30().getCST());
                    impostoIcms.setModalidadeBcIcmsSt(Integer.valueOf(icms.getICMS30().getModBCST()));
                    impostoIcms.setPercentualMvaIcmsSt(BigDecimal.valueOf(formatoValor.parse(icms.getICMS30().getPMVAST()).doubleValue()));
                    impostoIcms.setPercentualReducaoBcIcmsSt(BigDecimal.valueOf(formatoValor.parse(icms.getICMS30().getPRedBCST()).doubleValue()));
                    impostoIcms.setValorBaseCalculoIcmsSt(BigDecimal.valueOf(formatoValor.parse(icms.getICMS30().getVBCST()).doubleValue()));
                    impostoIcms.setAliquotaIcmsSt(BigDecimal.valueOf(formatoValor.parse(icms.getICMS30().getPICMSST()).doubleValue()));
                    impostoIcms.setValorIcmsSt(BigDecimal.valueOf(formatoValor.parse(icms.getICMS30().getVICMSST()).doubleValue()));
                }
                if (icms.getICMS40() != null) {
                    impostoIcms.setOrigemMercadoria(Integer.valueOf(icms.getICMS40().getOrig()));
                    impostoIcms.setCstIcms(icms.getICMS40().getCST());
                    impostoIcms.setValorIcms(BigDecimal.valueOf(formatoValor.parse(icms.getICMS40().getVICMSDeson()).doubleValue()));
                    impostoIcms.setMotivoDesoneracaoIcms(Integer.valueOf(icms.getICMS40().getMotDesICMS()));
                }
                if (icms.getICMS51() != null) {
                    impostoIcms.setOrigemMercadoria(Integer.valueOf(icms.getICMS51().getOrig()));
                    impostoIcms.setCstIcms(icms.getICMS51().getCST());
                    impostoIcms.setModalidadeBcIcms(Integer.valueOf(icms.getICMS51().getModBC()));
                    impostoIcms.setBaseCalculoIcms(BigDecimal.valueOf(formatoValor.parse(icms.getICMS51().getVBC()).doubleValue()));
                    impostoIcms.setAliquotaIcms(BigDecimal.valueOf(formatoValor.parse(icms.getICMS51().getPICMS()).doubleValue()));
                    impostoIcms.setValorIcms(BigDecimal.valueOf(formatoValor.parse(icms.getICMS51().getVICMS()).doubleValue()));
                    impostoIcms.setTaxaReducaoBcIcms(BigDecimal.valueOf(formatoValor.parse(icms.getICMS51().getPRedBC()).doubleValue()));
                }
                if (icms.getICMS60() != null) {
                    impostoIcms.setOrigemMercadoria(Integer.valueOf(icms.getICMS60().getOrig()));
                    impostoIcms.setCstIcms(icms.getICMS60().getCST());
                    impostoIcms.setValorIcmsStRetido(BigDecimal.valueOf(formatoValor.parse(icms.getICMS60().getVICMSSTRet()).doubleValue()));
                    impostoIcms.setValorBcIcmsStRetido(BigDecimal.valueOf(formatoValor.parse(icms.getICMS60().getVBCSTRet()).doubleValue()));
                }
                if (icms.getICMS70() != null) {
                    impostoIcms.setOrigemMercadoria(Integer.valueOf(icms.getICMS70().getOrig()));
                    impostoIcms.setCstIcms(icms.getICMS70().getCST());
                    impostoIcms.setModalidadeBcIcms(Integer.valueOf(icms.getICMS70().getModBCST()));
                    impostoIcms.setTaxaReducaoBcIcms(BigDecimal.valueOf(formatoValor.parse(icms.getICMS70().getPRedBC()).doubleValue()));
                    impostoIcms.setBaseCalculoIcms(BigDecimal.valueOf(formatoValor.parse(icms.getICMS70().getVBC()).doubleValue()));
                    impostoIcms.setAliquotaIcms(BigDecimal.valueOf(formatoValor.parse(icms.getICMS70().getPICMS()).doubleValue()));
                    impostoIcms.setValorIcms(BigDecimal.valueOf(formatoValor.parse(icms.getICMS70().getVICMS()).doubleValue()));
                    impostoIcms.setModalidadeBcIcmsSt(Integer.valueOf(icms.getICMS70().getModBCST()));
                    impostoIcms.setPercentualMvaIcmsSt(BigDecimal.valueOf(formatoValor.parse(icms.getICMS70().getPMVAST()).doubleValue()));
                    impostoIcms.setPercentualReducaoBcIcmsSt(BigDecimal.valueOf(formatoValor.parse(icms.getICMS70().getPRedBCST()).doubleValue()));
                    impostoIcms.setValorBaseCalculoIcmsSt(BigDecimal.valueOf(formatoValor.parse(icms.getICMS70().getVBCST()).doubleValue()));
                    impostoIcms.setAliquotaIcmsSt(BigDecimal.valueOf(formatoValor.parse(icms.getICMS70().getPICMSST()).doubleValue()));
                    impostoIcms.setValorIcmsSt(BigDecimal.valueOf(formatoValor.parse(icms.getICMS70().getVICMSST()).doubleValue()));
                }
                if (icms.getICMS90() != null) {
                    impostoIcms.setOrigemMercadoria(Integer.valueOf(icms.getICMS90().getOrig()));
                    impostoIcms.setCstIcms(icms.getICMS90().getCST());
                    impostoIcms.setModalidadeBcIcms(Integer.valueOf(icms.getICMS90().getModBCST()));
                    impostoIcms.setTaxaReducaoBcIcms(BigDecimal.valueOf(formatoValor.parse(icms.getICMS90().getPRedBC()).doubleValue()));
                    impostoIcms.setBaseCalculoIcms(BigDecimal.valueOf(formatoValor.parse(icms.getICMS90().getVBC()).doubleValue()));
                    impostoIcms.setAliquotaIcms(BigDecimal.valueOf(formatoValor.parse(icms.getICMS90().getPICMS()).doubleValue()));
                    impostoIcms.setValorIcms(BigDecimal.valueOf(formatoValor.parse(icms.getICMS90().getVICMS()).doubleValue()));
                    impostoIcms.setModalidadeBcIcmsSt(Integer.valueOf(icms.getICMS90().getModBCST()));
                    impostoIcms.setPercentualMvaIcmsSt(BigDecimal.valueOf(formatoValor.parse(icms.getICMS90().getPMVAST()).doubleValue()));
                    impostoIcms.setPercentualReducaoBcIcmsSt(BigDecimal.valueOf(formatoValor.parse(icms.getICMS90().getPRedBCST()).doubleValue()));
                    impostoIcms.setValorBaseCalculoIcmsSt(BigDecimal.valueOf(formatoValor.parse(icms.getICMS90().getVBCST()).doubleValue()));
                    impostoIcms.setAliquotaIcmsSt(BigDecimal.valueOf(formatoValor.parse(icms.getICMS90().getPICMSST()).doubleValue()));
                    impostoIcms.setValorIcmsSt(BigDecimal.valueOf(formatoValor.parse(icms.getICMS90().getVICMSST()).doubleValue()));
                }
                if (icms.getICMSPart() != null) {
                    impostoIcms.setOrigemMercadoria(Integer.valueOf(icms.getICMSPart().getOrig()));
                    impostoIcms.setCstIcms(icms.getICMSPart().getCST());
                    impostoIcms.setModalidadeBcIcms((Integer.valueOf(icms.getICMSPart().getModBCST())));
                    impostoIcms.setTaxaReducaoBcIcms(BigDecimal.valueOf(formatoValor.parse(icms.getICMSPart().getPRedBC()).doubleValue()));
                    impostoIcms.setBaseCalculoIcms(BigDecimal.valueOf(formatoValor.parse(icms.getICMSPart().getVBC()).doubleValue()));
                    impostoIcms.setAliquotaIcms(BigDecimal.valueOf(formatoValor.parse(icms.getICMSPart().getPICMS()).doubleValue()));
                    impostoIcms.setValorIcms(BigDecimal.valueOf(formatoValor.parse(icms.getICMSPart().getVICMS()).doubleValue()));
                    impostoIcms.setModalidadeBcIcmsSt(Integer.valueOf(icms.getICMSPart().getModBCST()));
                    impostoIcms.setPercentualMvaIcmsSt(BigDecimal.valueOf(formatoValor.parse(icms.getICMSPart().getPMVAST()).doubleValue()));
                    impostoIcms.setPercentualReducaoBcIcmsSt(BigDecimal.valueOf(formatoValor.parse(icms.getICMSPart().getPRedBCST()).doubleValue()));
                    impostoIcms.setValorBaseCalculoIcmsSt(BigDecimal.valueOf(formatoValor.parse(icms.getICMSPart().getVBCST()).doubleValue()));
                    impostoIcms.setAliquotaIcmsSt(BigDecimal.valueOf(formatoValor.parse(icms.getICMSPart().getPICMSST()).doubleValue()));
                    impostoIcms.setValorIcmsSt(BigDecimal.valueOf(formatoValor.parse(icms.getICMSPart().getVICMSST()).doubleValue()));
                }
                if (icms.getICMSST() != null) {
                    impostoIcms.setOrigemMercadoria(Integer.valueOf(icms.getICMSST().getOrig()));
                    impostoIcms.setCstIcms(icms.getICMSST().getCST());
                    impostoIcms.setValorBcIcmsStRetido(BigDecimal.valueOf(formatoValor.parse(icms.getICMSST().getVBCSTRet()).doubleValue()));
                    impostoIcms.setValorIcmsStRetido(BigDecimal.valueOf(formatoValor.parse(icms.getICMSST().getVICMSSTRet()).doubleValue()));
                    impostoIcms.setValorBcIcmsStDestino(BigDecimal.valueOf(formatoValor.parse(icms.getICMSST().getVBCSTDest()).doubleValue()));
                    impostoIcms.setValorIcmsStDestino(BigDecimal.valueOf(formatoValor.parse(icms.getICMSST().getVICMSSTDest()).doubleValue()));
                }
                if (icms.getICMSSN101() != null) {
                    impostoIcms.setOrigemMercadoria(Integer.valueOf(icms.getICMSSN101().getOrig()));
                    impostoIcms.setCsosn(icms.getICMSSN101().getCSOSN());
                    impostoIcms.setAliquotaCreditoIcmsSn(BigDecimal.valueOf(formatoValor.parse(icms.getICMSSN101().getPCredSN()).doubleValue()));
                    impostoIcms.setValorCreditoIcmsSn(BigDecimal.valueOf(formatoValor.parse(icms.getICMSSN101().getVCredICMSSN()).doubleValue()));
                }
                if (icms.getICMSSN102() != null) {
                    impostoIcms.setOrigemMercadoria(Integer.valueOf(icms.getICMSSN102().getOrig()));
                    impostoIcms.setCsosn(icms.getICMSSN102().getCSOSN());
                }
                if (icms.getICMSSN201() != null) {
                    impostoIcms.setOrigemMercadoria(Integer.valueOf(icms.getICMSSN201().getOrig()));
                    impostoIcms.setCsosn(icms.getICMSSN201().getCSOSN());
                    impostoIcms.setModalidadeBcIcmsSt(Integer.valueOf(icms.getICMSSN201().getModBCST()));
                    impostoIcms.setPercentualMvaIcmsSt(BigDecimal.valueOf(formatoValor.parse(icms.getICMSSN201().getPMVAST()).doubleValue()));
                    impostoIcms.setPercentualReducaoBcIcmsSt(BigDecimal.valueOf(formatoValor.parse(icms.getICMSSN201().getPRedBCST()).doubleValue()));
                    impostoIcms.setValorBaseCalculoIcmsSt(BigDecimal.valueOf(formatoValor.parse(icms.getICMSSN201().getVBCST()).doubleValue()));
                    impostoIcms.setAliquotaIcmsSt(BigDecimal.valueOf(formatoValor.parse(icms.getICMSSN201().getPICMSST()).doubleValue()));
                    impostoIcms.setValorIcmsSt(BigDecimal.valueOf(formatoValor.parse(icms.getICMSSN201().getVICMSST()).doubleValue()));
                    impostoIcms.setAliquotaCreditoIcmsSn(BigDecimal.valueOf(formatoValor.parse(icms.getICMSSN201().getPCredSN()).doubleValue()));
                    impostoIcms.setValorCreditoIcmsSn(BigDecimal.valueOf(formatoValor.parse(icms.getICMSSN201().getVCredICMSSN()).doubleValue()));
                }
                if (icms.getICMSSN202() != null) {
                    impostoIcms.setOrigemMercadoria(Integer.valueOf(icms.getICMSSN202().getOrig()));
                    impostoIcms.setCsosn(icms.getICMSSN202().getCSOSN());
                    impostoIcms.setModalidadeBcIcmsSt(Integer.valueOf(icms.getICMSSN202().getModBCST()));
                    impostoIcms.setPercentualMvaIcmsSt(BigDecimal.valueOf(formatoValor.parse(icms.getICMSSN202().getPMVAST()).doubleValue()));
                    impostoIcms.setPercentualReducaoBcIcmsSt(BigDecimal.valueOf(formatoValor.parse(icms.getICMSSN202().getPRedBCST()).doubleValue()));
                    impostoIcms.setValorBaseCalculoIcmsSt(BigDecimal.valueOf(formatoValor.parse(icms.getICMSSN202().getVBCST()).doubleValue()));
                    impostoIcms.setAliquotaIcmsSt(BigDecimal.valueOf(formatoValor.parse(icms.getICMSSN202().getPICMSST()).doubleValue()));
                    impostoIcms.setValorIcmsSt(BigDecimal.valueOf(formatoValor.parse(icms.getICMSSN202().getVICMSST()).doubleValue()));
                }
                if (icms.getICMSSN500() != null) {
                    impostoIcms.setOrigemMercadoria(Integer.valueOf(icms.getICMSSN500().getOrig()));
                    impostoIcms.setCsosn(icms.getICMSSN500().getCSOSN());
                    impostoIcms.setValorBcIcmsStRetido(BigDecimal.valueOf(formatoValor.parse(icms.getICMSSN500().getVBCSTRet()).doubleValue()));
                    impostoIcms.setValorIcmsStRetido(BigDecimal.valueOf(formatoValor.parse(icms.getICMSSN500().getVICMSSTRet()).doubleValue()));
                }
                if (icms.getICMSSN900() != null) {
                    impostoIcms.setOrigemMercadoria(Integer.valueOf(icms.getICMSSN900().getOrig()));
                    impostoIcms.setCsosn(icms.getICMSSN900().getCSOSN());
                    impostoIcms.setModalidadeBcIcms(Integer.valueOf(icms.getICMSSN900().getModBCST()));
                    impostoIcms.setTaxaReducaoBcIcms(BigDecimal.valueOf(formatoValor.parse(icms.getICMSSN900().getPRedBC()).doubleValue()));
                    impostoIcms.setBaseCalculoIcms(BigDecimal.valueOf(formatoValor.parse(icms.getICMSSN900().getVBC()).doubleValue()));
                    impostoIcms.setAliquotaIcms(BigDecimal.valueOf(formatoValor.parse(icms.getICMSSN900().getPICMS()).doubleValue()));
                    impostoIcms.setValorIcms(BigDecimal.valueOf(formatoValor.parse(icms.getICMSSN900().getVICMS()).doubleValue()));
                    impostoIcms.setModalidadeBcIcmsSt(Integer.valueOf(icms.getICMSSN900().getModBCST()));
                    impostoIcms.setPercentualMvaIcmsSt(BigDecimal.valueOf(formatoValor.parse(icms.getICMSSN900().getPMVAST()).doubleValue()));
                    impostoIcms.setPercentualReducaoBcIcmsSt(BigDecimal.valueOf(formatoValor.parse(icms.getICMSSN900().getPRedBCST()).doubleValue()));
                    impostoIcms.setValorBaseCalculoIcmsSt(BigDecimal.valueOf(formatoValor.parse(icms.getICMSSN900().getVBCST()).doubleValue()));
                    impostoIcms.setAliquotaIcmsSt(BigDecimal.valueOf(formatoValor.parse(icms.getICMSSN900().getPICMSST()).doubleValue()));
                    impostoIcms.setValorIcmsSt(BigDecimal.valueOf(formatoValor.parse(icms.getICMSSN900().getVICMSST()).doubleValue()));
                    impostoIcms.setAliquotaCreditoIcmsSn(BigDecimal.valueOf(formatoValor.parse(icms.getICMSSN900().getPCredSN()).doubleValue()));
                    impostoIcms.setValorCreditoIcmsSn(BigDecimal.valueOf(formatoValor.parse(icms.getICMSSN900().getVCredICMSSN()).doubleValue()));
                }
            }

            //imposto - ipi
            TIpi.IPITrib ipi = null;
            if (det.getImposto().getContent() != null) {
                for (JAXBElement e : det.getImposto().getContent()) {
                    if (e.getValue() instanceof TIpi.IPITrib) {
                        ipi = (TIpi.IPITrib) e.getValue();
                        break;
                    }
                }
            }
            if (ipi != null) {
                NfeDetalheImpostoIpiVO impostoIpi = new NfeDetalheImpostoIpiVO();
                impostoIpi.setNfeDetalhe(nfeDetalhe);
                nfeDetalhe.setNfeDetalheImpostoIpi(impostoIpi);

                    impostoIpi.setCstIpi(ipi.getCST());
                    impostoIpi.setValorBaseCalculoIpi(BigDecimal.valueOf(formatoValor.parse(ipi.getVBC()).doubleValue()));
                    impostoIpi.setAliquotaIpi(BigDecimal.valueOf(formatoValor.parse(ipi.getPIPI()).doubleValue()));
                    if (ipi.getQUnid() != null) {
                        impostoIpi.setQuantidadeUnidadeTributavel(BigDecimal.valueOf(formatoValor.parse(ipi.getQUnid()).doubleValue()));
                    }
                    if (ipi.getVUnid() != null) {
                        impostoIpi.setValorUnidadeTributavel(BigDecimal.valueOf(formatoValor.parse(ipi.getVUnid()).doubleValue()));
                    }
                    impostoIpi.setValorIpi(BigDecimal.valueOf(formatoValor.parse(ipi.getVIPI()).doubleValue()));
            }

            //imposto - imposto importacao
            TNFe.InfNFe.Det.Imposto.II impImp = null;
            if (det.getImposto().getContent() != null) {
                for (JAXBElement e : det.getImposto().getContent()) {
                    if (e.getValue() instanceof TNFe.InfNFe.Det.Imposto.II) {
                        impImp = (TNFe.InfNFe.Det.Imposto.II) e.getValue();
                        break;
                    }
                }
            }
            if (impImp != null) {
                NfeDetalheImpostoIiVO impostoIi = new NfeDetalheImpostoIiVO();
                impostoIi.setNfeDetalhe(nfeDetalhe);
                nfeDetalhe.setNfeDetalheImpostoIi(impostoIi);

                impostoIi.setValorBcIi(BigDecimal.valueOf(formatoValor.parse(impImp.getVBC()).doubleValue()));
                impostoIi.setValorDespesasAduaneiras(BigDecimal.valueOf(formatoValor.parse(impImp.getVDespAdu()).doubleValue()));
                impostoIi.setValorImpostoImportacao(BigDecimal.valueOf(formatoValor.parse(impImp.getVII()).doubleValue()));
                impostoIi.setValorIof(BigDecimal.valueOf(formatoValor.parse(impImp.getVIOF()).doubleValue()));
            }

            //imposto - pis
            TNFe.InfNFe.Det.Imposto.PIS pis = null;
            if (det.getImposto().getContent() != null) {
                for (JAXBElement e : det.getImposto().getContent()) {
                    if (e.getValue() instanceof TNFe.InfNFe.Det.Imposto.PIS) {
                        pis = (TNFe.InfNFe.Det.Imposto.PIS) e.getValue();
                        break;
                    }
                }
            }            
            NfeDetalheImpostoPisVO impostoPis = null;
            if (pis != null) {
                impostoPis = new NfeDetalheImpostoPisVO();
                impostoPis.setNfeDetalhe(nfeDetalhe);
                nfeDetalhe.setNfeDetalheImpostoPis(impostoPis);

                if (pis.getPISAliq() != null) {
                    impostoPis.setCstPis(pis.getPISAliq().getCST());
                    impostoPis.setValorBaseCalculoPis(BigDecimal.valueOf(formatoValor.parse(pis.getPISAliq().getVBC()).doubleValue()));
                    impostoPis.setAliquotaPisPercentual(BigDecimal.valueOf(formatoValor.parse(pis.getPISAliq().getPPIS()).doubleValue()));
                    impostoPis.setValorPis(BigDecimal.valueOf(formatoValor.parse(pis.getPISAliq().getVPIS()).doubleValue()));
                }
                if (pis.getPISQtde() != null) {
                    impostoPis.setCstPis(pis.getPISQtde().getCST());
                    impostoPis.setQuantidadeVendida(BigDecimal.valueOf(formatoValor.parse(pis.getPISQtde().getQBCProd()).doubleValue()));
                    impostoPis.setAliquotaPisReais(BigDecimal.valueOf(formatoValor.parse(pis.getPISQtde().getVAliqProd()).doubleValue()));
                    impostoPis.setValorPis(BigDecimal.valueOf(formatoValor.parse(pis.getPISQtde().getVPIS()).doubleValue()));
                }
                if (pis.getPISNT() != null) {
                    impostoPis.setCstPis(pis.getPISNT().getCST());
                }
                if (pis.getPISOutr() != null) {
                    impostoPis.setCstPis(pis.getPISOutr().getCST());
                    impostoPis.setValorBaseCalculoPis(BigDecimal.valueOf(formatoValor.parse(pis.getPISOutr().getVBC()).doubleValue()));
                    impostoPis.setAliquotaPisPercentual(BigDecimal.valueOf(formatoValor.parse(pis.getPISOutr().getPPIS()).doubleValue()));
                    impostoPis.setQuantidadeVendida(BigDecimal.valueOf(formatoValor.parse(pis.getPISOutr().getQBCProd()).doubleValue()));
                    impostoPis.setAliquotaPisReais(BigDecimal.valueOf(formatoValor.parse(pis.getPISOutr().getVAliqProd()).doubleValue()));
                    impostoPis.setValorPis(BigDecimal.valueOf(formatoValor.parse(pis.getPISOutr().getVPIS()).doubleValue()));
                }
            }

            //imposto - pis st
            TNFe.InfNFe.Det.Imposto.PISST pisSt = null;
            if (det.getImposto().getContent() != null) {
                for (JAXBElement e : det.getImposto().getContent()) {
                    if (e.getValue() instanceof TNFe.InfNFe.Det.Imposto.PISST) {
                        pisSt = (TNFe.InfNFe.Det.Imposto.PISST) e.getValue();
                        break;
                    }
                }
            }
            if (pisSt != null) {
                if (impostoPis == null) {
                    impostoPis = new NfeDetalheImpostoPisVO();
                    impostoPis.setNfeDetalhe(nfeDetalhe);
                    nfeDetalhe.setNfeDetalheImpostoPis(impostoPis);
                }
                impostoPis.setValorBaseCalculoPis(BigDecimal.valueOf(formatoValor.parse(pisSt.getVBC()).doubleValue()));
                impostoPis.setAliquotaPisPercentual(BigDecimal.valueOf(formatoValor.parse(pisSt.getPPIS()).doubleValue()));
                impostoPis.setQuantidadeVendida(BigDecimal.valueOf(formatoValor.parse(pisSt.getQBCProd()).doubleValue()));
                impostoPis.setAliquotaPisReais(BigDecimal.valueOf(formatoValor.parse(pisSt.getVAliqProd()).doubleValue()));
                impostoPis.setValorPis(BigDecimal.valueOf(formatoValor.parse(pisSt.getVPIS()).doubleValue()));
            }

            //imposto - cofins
            TNFe.InfNFe.Det.Imposto.COFINS cofins = null;
            if (det.getImposto().getContent() != null) {
                for (JAXBElement e : det.getImposto().getContent()) {
                    if (e.getValue() instanceof TNFe.InfNFe.Det.Imposto.COFINS) {
                        cofins = (TNFe.InfNFe.Det.Imposto.COFINS) e.getValue();
                        break;
                    }
                }
            }
            NfeDetalheImpostoCofinsVO impostoCofins = null;
            if (cofins != null) {
                impostoCofins = new NfeDetalheImpostoCofinsVO();
                impostoCofins.setNfeDetalhe(nfeDetalhe);
                nfeDetalhe.setNfeDetalheImpostoCofins(impostoCofins);

                if (cofins.getCOFINSAliq() != null) {
                    impostoCofins.setCstCofins(cofins.getCOFINSAliq().getCST());
                    impostoCofins.setBaseCalculoCofins(BigDecimal.valueOf(formatoValor.parse(cofins.getCOFINSAliq().getVBC()).doubleValue()));
                    impostoCofins.setAliquotaCofinsPercentual(BigDecimal.valueOf(formatoValor.parse(cofins.getCOFINSAliq().getPCOFINS()).doubleValue()));
                    impostoCofins.setValorCofins(BigDecimal.valueOf(formatoValor.parse(cofins.getCOFINSAliq().getVCOFINS()).doubleValue()));
                }
                if (cofins.getCOFINSQtde() != null) {
                    impostoCofins.setCstCofins(cofins.getCOFINSQtde().getCST());
                    impostoCofins.setQuantidadeVendida(BigDecimal.valueOf(formatoValor.parse(cofins.getCOFINSQtde().getQBCProd()).doubleValue()));
                    impostoCofins.setAliquotaCofinsReais(BigDecimal.valueOf(formatoValor.parse(cofins.getCOFINSQtde().getVAliqProd()).doubleValue()));
                    impostoCofins.setValorCofins(BigDecimal.valueOf(formatoValor.parse(cofins.getCOFINSQtde().getVCOFINS()).doubleValue()));
                }
                if (cofins.getCOFINSNT() != null) {
                    impostoCofins.setCstCofins(cofins.getCOFINSNT().getCST());
                }
                if (cofins.getCOFINSOutr() != null) {
                    impostoCofins.setCstCofins(cofins.getCOFINSOutr().getCST());
                    impostoCofins.setBaseCalculoCofins(BigDecimal.valueOf(formatoValor.parse(cofins.getCOFINSOutr().getVBC()).doubleValue()));
                    impostoCofins.setAliquotaCofinsPercentual(BigDecimal.valueOf(formatoValor.parse(cofins.getCOFINSOutr().getPCOFINS()).doubleValue()));
                    impostoCofins.setQuantidadeVendida(BigDecimal.valueOf(formatoValor.parse(cofins.getCOFINSOutr().getQBCProd()).doubleValue()));
                    impostoCofins.setAliquotaCofinsReais(BigDecimal.valueOf(formatoValor.parse(cofins.getCOFINSOutr().getVAliqProd()).doubleValue()));
                    impostoCofins.setValorCofins(BigDecimal.valueOf(formatoValor.parse(cofins.getCOFINSOutr().getVCOFINS()).doubleValue()));
                }
            }

            //imposto - cofins st
            TNFe.InfNFe.Det.Imposto.COFINSST cofinsSt = null;
            if (det.getImposto().getContent() != null) {
                for (JAXBElement e : det.getImposto().getContent()) {
                    if (e.getValue() instanceof TNFe.InfNFe.Det.Imposto.COFINSST) {
                        cofinsSt = (TNFe.InfNFe.Det.Imposto.COFINSST) e.getValue();
                        break;
                    }
                }
            }
            if (cofinsSt != null) {
                if (impostoCofins == null) {
                    impostoCofins = new NfeDetalheImpostoCofinsVO();
                    impostoCofins.setNfeDetalhe(nfeDetalhe);
                    nfeDetalhe.setNfeDetalheImpostoCofins(impostoCofins);
                }

                impostoCofins.setBaseCalculoCofins(BigDecimal.valueOf(formatoValor.parse(cofinsSt.getVBC()).doubleValue()));
                impostoCofins.setAliquotaCofinsPercentual(BigDecimal.valueOf(formatoValor.parse(cofinsSt.getPCOFINS()).doubleValue()));
                impostoCofins.setQuantidadeVendida(BigDecimal.valueOf(formatoValor.parse(cofinsSt.getQBCProd()).doubleValue()));
                impostoCofins.setAliquotaCofinsReais(BigDecimal.valueOf(formatoValor.parse(cofinsSt.getVAliqProd()).doubleValue()));
                impostoCofins.setValorCofins(BigDecimal.valueOf(formatoValor.parse(cofinsSt.getVCOFINS()).doubleValue()));
            }

            //imposto - issqn
            TNFe.InfNFe.Det.Imposto.ISSQN issqn = null;
            if (det.getImposto().getContent() != null) {
                for (JAXBElement e : det.getImposto().getContent()) {
                    if (e.getValue() instanceof TNFe.InfNFe.Det.Imposto.ISSQN) {
                        issqn = (TNFe.InfNFe.Det.Imposto.ISSQN) e.getValue();
                        break;
                    }
                }
            }
            if (issqn != null) {
                NfeDetalheImpostoIssqnVO impostoIssqn = new NfeDetalheImpostoIssqnVO();
                impostoIssqn.setNfeDetalhe(nfeDetalhe);
                nfeDetalhe.setNfeDetalheImpostoIssqn(impostoIssqn);

                impostoIssqn.setBaseCalculoIssqn(BigDecimal.valueOf(formatoValor.parse(issqn.getVBC()).doubleValue()));
                impostoIssqn.setAliquotaIssqn(BigDecimal.valueOf(formatoValor.parse(issqn.getVAliq()).doubleValue()));
                impostoIssqn.setValorIssqn(BigDecimal.valueOf(formatoValor.parse(issqn.getVISSQN()).doubleValue()));
                impostoIssqn.setMunicipioIssqn(Integer.valueOf(issqn.getCMunFG()));
                impostoIssqn.setItemListaServicos(Integer.valueOf(issqn.getCListServ()));
                //impostoIssqn.setTributacaoIssqn(issqn.getCSitTrib());
            }

            //declaracao importacao
            List<TNFe.InfNFe.Det.Prod.DI> decImp = det.getProd().getDI();
            if (decImp != null) {
                List<NfeDeclaracaoImportacaoVO> listaDeclaracaoImportacao = new ArrayList<>();
                nfeDetalhe.setListaDeclaracaoImportacao(listaDeclaracaoImportacao);

                for (int j = 0; j < decImp.size(); j++) {
                    NfeDeclaracaoImportacaoVO declaracaoImportacao = new NfeDeclaracaoImportacaoVO();
                    declaracaoImportacao.setNumeroDocumento(decImp.get(j).getNDI());
                    declaracaoImportacao.setDataRegistro(formatoData.parse(decImp.get(j).getDDI()));
                    declaracaoImportacao.setLocalDesembaraco(decImp.get(j).getXLocDesemb());
                    declaracaoImportacao.setUfDesembaraco(decImp.get(j).getUFDesemb().value());
                    declaracaoImportacao.setDataDesembaraco(formatoData.parse(decImp.get(j).getDDesemb()));
                    declaracaoImportacao.setCodigoExportador(decImp.get(j).getCExportador());

                    List<TNFe.InfNFe.Det.Prod.DI.Adi> adicoes = decImp.get(j).getAdi();
                    if (adicoes != null) {
                        List<NfeImportacaoDetalheVO> listaImportacaoDetalhe = new ArrayList<>();
                        declaracaoImportacao.setListaImportacaoDetalhe(listaImportacaoDetalhe);
                        for (int k = 0; k < adicoes.size(); k++) {
                            NfeImportacaoDetalheVO importacaoDetalhe = new NfeImportacaoDetalheVO();
                            importacaoDetalhe.setNumeroAdicao(Integer.valueOf(adicoes.get(i).getNAdicao()));
                            importacaoDetalhe.setNumeroSequencial(Integer.valueOf(adicoes.get(i).getNSeqAdic()));
                            importacaoDetalhe.setCodigoFabricanteEstrangeiro(adicoes.get(i).getCFabricante());
                            importacaoDetalhe.setValorDesconto(BigDecimal.valueOf(formatoValor.parse(adicoes.get(i).getVDescDI()).doubleValue()));

                            listaDeclaracaoImportacao.add(declaracaoImportacao);
                        }
                    }

                    listaDeclaracaoImportacao.add(declaracaoImportacao);
                }
            }

            //Detalhamento Específico de Veículos novos
            TNFe.InfNFe.Det.Prod.VeicProd veicProd = det.getProd().getVeicProd();
            if (veicProd != null) {
                NfeDetEspecificoVeiculoVO veiculo = new NfeDetEspecificoVeiculoVO();
                veiculo.setTipoOperacao(veicProd.getTpOp());
                veiculo.setChassi(veicProd.getChassi());
                veiculo.setCor(veicProd.getCCor());
                veiculo.setDescricaoCor(veicProd.getXCor());
                veiculo.setPotenciaMotor(veicProd.getPot());
                veiculo.setCilindradas(veicProd.getCilin());
                veiculo.setPesoLiquido(veicProd.getPesoL());
                veiculo.setPesoBruto(veicProd.getPesoB());
                veiculo.setNumeroSerie(veicProd.getNSerie());
                veiculo.setTipoCombustivel(veicProd.getTpComb());
                veiculo.setNumeroMotor(veicProd.getNMotor());
                veiculo.setCapacidadeMaximaTracao(veicProd.getCMT());
                veiculo.setDistanciaEixos(veicProd.getDist());
                veiculo.setAnoModelo(veicProd.getAnoMod());
                veiculo.setAnoFabricacao(veicProd.getAnoFab());
                veiculo.setTipoPintura(veicProd.getTpPint());
                veiculo.setTipoVeiculo(veicProd.getTpVeic());
                veiculo.setEspecieVeiculo(veicProd.getEspVeic());
                veiculo.setCondicaoVin(veicProd.getVIN());
                veiculo.setCondicaoVeiculo(veicProd.getCondVeic());
                veiculo.setCodigoMarcaModelo(veicProd.getCMod());
                veiculo.setCodigoCor(veicProd.getCCorDENATRAN());
                veiculo.setLotacao(Integer.valueOf(veicProd.getLota()));
                veiculo.setRestricao(veicProd.getTpRest());

                nfeDetalhe.setVeiculo(veiculo);
            }

            //Detalhamento Específico de Medicamento
            List<TNFe.InfNFe.Det.Prod.Med> med = det.getProd().getMed();
            if (med != null) {
                List<NfeDetEspecificoMedicamentoVO> listaMedicamento = new ArrayList<>();
                nfeDetalhe.setListaMedicamento(listaMedicamento);
                for (int j = 0; j < med.size(); j++) {
                    NfeDetEspecificoMedicamentoVO medicamento = new NfeDetEspecificoMedicamentoVO();
                    medicamento.setNumeroLote(med.get(i).getNLote());
                    medicamento.setQuantidadeLote(BigDecimal.valueOf(formatoValor.parse(med.get(i).getQLote()).doubleValue()));
                    medicamento.setDataFabricacao(formatoData.parse(med.get(i).getDFab()));
                    medicamento.setDataValidade(formatoData.parse(med.get(i).getDVal()));
                    medicamento.setPrecoMaximoConsumidor(BigDecimal.valueOf(formatoValor.parse(med.get(i).getVPMC()).doubleValue()));

                    listaMedicamento.add(medicamento);
                }
            }

            //Detalhamento Específico de Armamentos
            List<TNFe.InfNFe.Det.Prod.Arma> arma = det.getProd().getArma();
            if (arma != null) {
                List<NfeDetEspecificoArmamentoVO> listaArmamento = new ArrayList<>();
                nfeDetalhe.setListaArmamento(listaArmamento);
                for (int j = 0; j < arma.size(); j++) {
                    NfeDetEspecificoArmamentoVO armamento = new NfeDetEspecificoArmamentoVO();
                    armamento.setTipoArma(Integer.valueOf(arma.get(i).getTpArma()));
                    armamento.setNumeroSerieArma(arma.get(i).getNSerie());
                    armamento.setNumeroSerieCano(arma.get(i).getNCano());
                    armamento.setDescricao(arma.get(i).getDescr());

                    listaArmamento.add(armamento);
                }
            }

            //Detalhamento Específico de Combustíveis
            TNFe.InfNFe.Det.Prod.Comb comb = det.getProd().getComb();
            if (comb != null) {
                NfeDetEspecificoCombustivelVO combustivel = new NfeDetEspecificoCombustivelVO();
                nfeDetalhe.setCombustivel(combustivel);

                combustivel.setCodigoAnp(Integer.valueOf(comb.getCProdANP()));
                combustivel.setCodif(comb.getCODIF());
                combustivel.setQuantidadeTempAmbiente(BigDecimal.valueOf(formatoValor.parse(comb.getQTemp()).doubleValue()));
                combustivel.setUfConsumo(comb.getUFCons().value());
                if (comb.getCIDE().getQBCProd() != null) {
                    combustivel.setBaseCalculoCide(BigDecimal.valueOf(formatoValor.parse(comb.getCIDE().getQBCProd()).doubleValue()));
                    combustivel.setAliquotaCide(BigDecimal.valueOf(formatoValor.parse(comb.getCIDE().getVAliqProd()).doubleValue()));
                    combustivel.setValorCide(BigDecimal.valueOf(formatoValor.parse(comb.getCIDE().getVCIDE()).doubleValue()));
                }
            }

            listaNfeDetalhe.add(nfeDetalhe);
        }

        //Grupo de informação das NF/NF-e referenciadas
        List<TNFe.InfNFe.Ide.NFref> nfRef = ide.getNFref();
        List<NfeReferenciadaVO> listaNfeReferenciada = new ArrayList<>();
        List<NfeNfReferenciadaVO> listaNf1Referenciada = new ArrayList<>();
        List<NfeProdRuralReferenciadaVO> listaProdRuralReferenciada = new ArrayList<>();
        List<NfeCteReferenciadoVO> listaCteReferenciado = new ArrayList<>();
        List<NfeCupomFiscalReferenciadoVO> listaCupomFiscalReferenciado = new ArrayList<>();
        if (nfRef != null) {
            for (int i = 0; i < nfRef.size(); i++) {
                if (nfRef.get(i).getRefNFe() != null) {
                    NfeReferenciadaVO nfeReferenciada = new NfeReferenciadaVO();
                    nfeReferenciada.setChaveAcesso(nfRef.get(i).getRefNFe());

                    listaNfeReferenciada.add(nfeReferenciada);
                }

                if (nfRef.get(i).getRefNF() != null) {
                    NfeNfReferenciadaVO nfReferenciada = new NfeNfReferenciadaVO();
                    nfReferenciada.setCodigoUf(Integer.valueOf(nfRef.get(i).getRefNF().getCUF()));
                    nfReferenciada.setAnoMes(nfRef.get(i).getRefNF().getAAMM());
                    nfReferenciada.setCnpj(nfRef.get(i).getRefNF().getCNPJ());
                    nfReferenciada.setModelo(nfRef.get(i).getRefNF().getMod());
                    nfReferenciada.setSerie(nfRef.get(i).getRefNF().getSerie());
                    nfReferenciada.setNumeroNf(Integer.valueOf(nfRef.get(i).getRefNF().getNNF()));

                    listaNf1Referenciada.add(nfReferenciada);
                }

                if (nfRef.get(i).getRefNFP() != null) {
                    NfeProdRuralReferenciadaVO prodRural = new NfeProdRuralReferenciadaVO();
                    prodRural.setCodigoUf(Integer.valueOf(nfRef.get(i).getRefNFP().getCUF()));
                    prodRural.setAnoMes(nfRef.get(i).getRefNFP().getAAMM());
                    prodRural.setCnpj(nfRef.get(i).getRefNFP().getCNPJ());
                    prodRural.setCpf(nfRef.get(i).getRefNFP().getCPF());
                    prodRural.setInscricaoEstadual(nfRef.get(i).getRefNFP().getIE());
                    prodRural.setModelo(nfRef.get(i).getRefNFP().getMod());
                    prodRural.setSerie(nfRef.get(i).getRefNFP().getSerie());
                    prodRural.setNumeroNf(Integer.valueOf(nfRef.get(i).getRefNFP().getNNF()));

                    listaProdRuralReferenciada.add(prodRural);
                }

                if (nfRef.get(i).getRefCTe() != null) {
                    NfeCteReferenciadoVO cte = new NfeCteReferenciadoVO();
                    cte.setChaveAcesso(nfRef.get(i).getRefCTe());

                    listaCteReferenciado.add(cte);
                }

                if (nfRef.get(i).getRefECF() != null) {
                    NfeCupomFiscalReferenciadoVO cupom = new NfeCupomFiscalReferenciadoVO();
                    cupom.setModeloDocumentoFiscal(nfRef.get(i).getRefECF().getMod());
                    cupom.setNumeroOrdemEcf(Integer.valueOf(nfRef.get(i).getRefECF().getNECF()));
                    cupom.setCoo(Integer.valueOf(nfRef.get(i).getRefECF().getNCOO()));

                    listaCupomFiscalReferenciado.add(cupom);
                }
            }
        }

        //Local Entrega
        TLocal entrega = infNfe.getEntrega();
        NfeLocalEntregaVO localEntrega = new NfeLocalEntregaVO();
        if (entrega != null) {
            if (entrega.getCPF() != null) {
                localEntrega.setCpfCnpj(entrega.getCPF());
            } else if (entrega.getCNPJ() != null) {
                localEntrega.setCpfCnpj(entrega.getCNPJ());
            }
            localEntrega.setLogradouro(entrega.getXLgr());
            localEntrega.setNumero(entrega.getNro());
            localEntrega.setComplemento(entrega.getXCpl());
            localEntrega.setBairro(entrega.getXBairro());
            localEntrega.setCodigoMunicipio(Integer.valueOf(entrega.getCMun()));
            localEntrega.setNomeMunicipio(entrega.getXMun());
            localEntrega.setUf(entrega.getUF().value());
        }

        //Local Retirada
        TLocal retirada = infNfe.getRetirada();
        NfeLocalRetiradaVO localRetirada = new NfeLocalRetiradaVO();
        if (retirada != null) {
            if (retirada.getCPF() != null) {
                localRetirada.setCpfCnpj(retirada.getCPF());
            } else if (retirada.getCNPJ() != null) {
                localRetirada.setCpfCnpj(retirada.getCNPJ());
            }
            localRetirada.setLogradouro(retirada.getXLgr());
            localRetirada.setNumero(retirada.getNro());
            localRetirada.setComplemento(retirada.getXCpl());
            localRetirada.setBairro(retirada.getXBairro());
            localRetirada.setCodigoMunicipio(Integer.valueOf(retirada.getCMun()));
            localRetirada.setNomeMunicipio(retirada.getXMun());
            localRetirada.setUf(retirada.getUF().value());
        }

        //Transporte
        TNFe.InfNFe.Transp transp = infNfe.getTransp();
        NfeTransporteVO transporte = new NfeTransporteVO();
        if (transp != null) {
            transporte.setModalidadeFrete(Integer.valueOf(transp.getModFrete()));
            if (transp.getTransporta() != null) {
                if (transp.getTransporta().getCPF() != null) {
                    transporte.setCpfCnpj(transp.getTransporta().getCPF());
                } else if (transp.getTransporta().getCNPJ() != null) {
                    transporte.setCpfCnpj(transp.getTransporta().getCNPJ());
                }
                transporte.setNome(transp.getTransporta().getXNome());
                transporte.setInscricaoEstadual(transp.getTransporta().getIE());
                transporte.setEmpresaEndereco(transp.getTransporta().getXEnder());
                transporte.setNomeMunicipio(transp.getTransporta().getXMun());
                transporte.setUf(transp.getTransporta().getUF().value());
            }
            if (transp.getRetTransp() != null) {
                transporte.setValorServico(BigDecimal.valueOf(formatoValor.parse(transp.getRetTransp().getVServ()).doubleValue()));
                transporte.setValorBcRetencaoIcms(BigDecimal.valueOf(formatoValor.parse(transp.getRetTransp().getVBCRet()).doubleValue()));
                transporte.setAliquotaRetencaoIcms(BigDecimal.valueOf(formatoValor.parse(transp.getRetTransp().getPICMSRet()).doubleValue()));
                transporte.setValorIcmsRetido(BigDecimal.valueOf(formatoValor.parse(transp.getRetTransp().getVICMSRet()).doubleValue()));
                transporte.setCfop(Integer.valueOf(transp.getRetTransp().getCFOP()));
                transporte.setMunicipio(Integer.valueOf(transp.getRetTransp().getCMunFG()));
            }
            if (transp.getVeicTransp() != null) {
                transporte.setPlacaVeiculo(transp.getVeicTransp().getPlaca());
                transporte.setUfVeiculo(transp.getVeicTransp().getUF().value());
                transporte.setRntcVeiculo(transp.getVeicTransp().getRNTC());
            }
            transporte.setVagao(transp.getVagao());
            transporte.setBalsa(transp.getBalsa());
        }

        List<TVeiculo> reboque = infNfe.getTransp().getReboque();
        List<NfeTransporteReboqueVO> listaTransporteReboque = new ArrayList<>();
        if (reboque != null) {
            for (int i = 0; i < reboque.size(); i++) {
                NfeTransporteReboqueVO reb = new NfeTransporteReboqueVO();
                reb.setPlaca(reboque.get(i).getPlaca());
                reb.setUf(reboque.get(i).getUF().value());
                reb.setRntc(reboque.get(i).getRNTC());

                listaTransporteReboque.add(reb);
            }
        }

        List<TNFe.InfNFe.Transp.Vol> volume = infNfe.getTransp().getVol();
        List<NfeTransporteVolumeVO> listaTransporteVolume = new ArrayList<>();
        if (volume != null) {
            for (int i = 0; i < volume.size(); i++) {
                NfeTransporteVolumeVO vol = new NfeTransporteVolumeVO();
                vol.setQuantidade(Integer.valueOf(volume.get(i).getQVol()));
                vol.setEspecie(volume.get(i).getEsp());
                vol.setMarca(volume.get(i).getMarca());
                vol.setNumeracao(volume.get(i).getNVol());
                vol.setPesoLiquido(BigDecimal.valueOf(formatoValor.parse(volume.get(i).getPesoL()).doubleValue()));
                vol.setPesoBruto(BigDecimal.valueOf(formatoValor.parse(volume.get(i).getPesoB()).doubleValue()));

                List<NfeTransporteVolumeLacreVO> listaLacres = new ArrayList<>();
                vol.setListaTransporteVolumeLacre(listaLacres);
                if (volume.get(i).getLacres() != null) {
                    for (int j = 0; j < volume.get(i).getLacres().size(); j++) {
                        NfeTransporteVolumeLacreVO lacre = new NfeTransporteVolumeLacreVO();
                        lacre.setNumero(volume.get(i).getLacres().get(j).getNLacre());

                        listaLacres.add(lacre);
                    }
                }

                listaTransporteVolume.add(vol);
            }
        }

        //Fatura
        TNFe.InfNFe.Cobr cobr = infNfe.getCobr();
        NfeFaturaVO fatura = new NfeFaturaVO();
        List<NfeDuplicataVO> listaDuplicatas = new ArrayList<>();
        if (cobr != null) {
            if (cobr.getFat() != null) {
                fatura.setValorOriginal(BigDecimal.valueOf(formatoValor.parse(cobr.getFat().getVOrig()).doubleValue()));
                fatura.setValorDesconto(BigDecimal.valueOf(formatoValor.parse(cobr.getFat().getVDesc()).doubleValue()));
                fatura.setValorLiquido(BigDecimal.valueOf(formatoValor.parse(cobr.getFat().getVLiq()).doubleValue()));
            }

            //Duplicatas
            List<TNFe.InfNFe.Cobr.Dup> duplicatas = cobr.getDup();
            if (duplicatas != null) {
                for (int i = 0; i < duplicatas.size(); i++) {
                    NfeDuplicataVO dup = new NfeDuplicataVO();
                    dup.setNumero(duplicatas.get(i).getNDup());
                    dup.setDataVencimento(formatoData.parse(duplicatas.get(i).getDVenc()));
                    dup.setValor(BigDecimal.valueOf(formatoValor.parse(duplicatas.get(i).getVDup()).doubleValue()));

                    listaDuplicatas.add(dup);
                }
            }
        }

        map.put("cabecalho", nfeCabecalho);
        map.put("detalhe", listaNfeDetalhe);
        map.put("emitente", emitente);
        map.put("nfeReferenciada", listaNfeReferenciada);
        map.put("nf1Referenciada", listaNf1Referenciada);
        map.put("prodRuralReferenciada", listaProdRuralReferenciada);
        map.put("cteReferenciado", listaCteReferenciado);
        map.put("localEntrega", localEntrega);
        map.put("localRetirada", localRetirada);
        map.put("transporte", transporte);
        map.put("transporteReboque", listaTransporteReboque);
        map.put("transporteVolume", listaTransporteVolume);
        map.put("fatura", fatura);
        map.put("duplicata", listaDuplicatas);

        return map;
    }
}
