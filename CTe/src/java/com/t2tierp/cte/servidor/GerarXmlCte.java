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
package com.t2tierp.cte.servidor;

import br.inf.portalfiscal.cte.envicte.ObjectFactory;
import br.inf.portalfiscal.cte.envicte.Rodo;
import br.inf.portalfiscal.cte.envicte.TCTe;
import br.inf.portalfiscal.cte.envicte.TEndeEmi;
import br.inf.portalfiscal.cte.envicte.TEndereco;
import br.inf.portalfiscal.cte.envicte.TEnviCTe;
import br.inf.portalfiscal.cte.envicte.TImp;
import br.inf.portalfiscal.cte.envicte.TUFSemEX;
import br.inf.portalfiscal.cte.envicte.TUf;
import com.t2tierp.cte.java.CteCabecalhoVO;
import com.t2tierp.cte.java.CteCargaVO;
import com.t2tierp.cte.java.CteDestinatarioVO;
import com.t2tierp.cte.java.CteEmitenteVO;
import com.t2tierp.cte.java.CteInformacaoNfOutrosVO;
import com.t2tierp.cte.java.CteRemetenteVO;
import com.t2tierp.cte.java.CteRodoviarioOccVO;
import com.t2tierp.padrao.java.Biblioteca;
import java.io.StringWriter;
import java.security.KeyStore;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.Marshaller;
import javax.xml.transform.dom.DOMResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class GerarXmlCte {

    public String geraXmlEnvio(CteCabecalhoVO cteCabecalho, String alias, KeyStore ks, char[] senha) throws Exception {

        JAXBContext jc = JAXBContext.newInstance("br.inf.portalfiscal.cte.envicte");
        Marshaller marshaller = jc.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, false);
        
        SimpleDateFormat formatoDataHora = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX");
        SimpleDateFormat formatoData = new SimpleDateFormat("yyyy-MM-dd");
        DecimalFormatSymbols simboloDecimal = DecimalFormatSymbols.getInstance();
        simboloDecimal.setDecimalSeparator('.');
        DecimalFormat formatoValor = new DecimalFormat("0.00", simboloDecimal);
        DecimalFormat formatoQuantidade = new DecimalFormat("0.0000", simboloDecimal);

        ObjectFactory factory = new ObjectFactory();

        TEnviCTe enviCTe = factory.createTEnviCTe();
        enviCTe.setVersao("3.00");
        enviCTe.setIdLote("1");

        TCTe.InfCte infCte = factory.createTCTeInfCte();
        infCte.setVersao("3.00");
        infCte.setId("CTe" + cteCabecalho.getChaveAcesso() + cteCabecalho.getDigitoChaveAcesso());

        TCTe cte = factory.createTCTe();
        cte.setInfCte(infCte);
        enviCTe.getCTe().add(cte);
        
        TCTe.InfCte.Ide ide = factory.createTCTeInfCteIde();
        infCte.setIde(ide);

        ide.setCUF(cteCabecalho.getEmpresa().getCodigoIbgeUf().toString());
        ide.setCCT(cteCabecalho.getCodigoNumerico());
        ide.setCFOP(cteCabecalho.getCfop().toString());
        ide.setNatOp(cteCabecalho.getNaturezaOperacao());
        ide.setMod(cteCabecalho.getModelo());
        ide.setSerie(Integer.valueOf(cteCabecalho.getSerie()).toString());
        ide.setNCT(Integer.valueOf(cteCabecalho.getNumero()).toString());
        ide.setDhEmi(formatoDataHora.format(cteCabecalho.getDataHoraEmissao()));
        ide.setTpImp(cteCabecalho.getFormatoImpressaoDacte().toString());
        ide.setTpEmis(cteCabecalho.getTipoEmissao().toString());
        ide.setCDV(cteCabecalho.getDigitoChaveAcesso());
        ide.setTpAmb(cteCabecalho.getAmbiente().toString());
        ide.setTpCTe(cteCabecalho.getTipoCte().toString());
        ide.setProcEmi("0");
        ide.setVerProc("1.0");
        ide.setCMunEnv(cteCabecalho.getCodigoMunicipioEnvio().toString());
        ide.setXMunEnv(cteCabecalho.getNomeMunicipioEnvio());
        ide.setUFEnv(TUf.valueOf(cteCabecalho.getUfEnvio()));
        ide.setModal(cteCabecalho.getModal());
        ide.setTpServ(cteCabecalho.getTipoServico().toString());
        ide.setCMunIni(cteCabecalho.getCodigoMunicipioIniPrestacao().toString());
        ide.setXMunIni(cteCabecalho.getNomeMunicipioIniPrestacao());
        ide.setUFIni(TUf.valueOf(cteCabecalho.getUfIniPrestacao()));
        ide.setCMunFim(cteCabecalho.getCodigoMunicipioFimPrestacao().toString());
        ide.setXMunFim(cteCabecalho.getNomeMunicipioFimPrestacao());
        ide.setUFFim(TUf.valueOf(cteCabecalho.getUfFimPrestacao()));
        ide.setRetira("1");
        ide.setIndIEToma("9");

        if (cteCabecalho.getTomador() != 4) {
            TCTe.InfCte.Ide.Toma3 toma3 = factory.createTCTeInfCteIdeToma3();
            toma3.setToma(cteCabecalho.getTomador().toString());
            ide.setToma3(toma3);
        } else {
            TCTe.InfCte.Ide.Toma4 toma4 = factory.createTCTeInfCteIdeToma4();
            toma4.setToma(cteCabecalho.getTomador().toString());
            ide.setToma4(toma4);
        }

        //Emitente
        TCTe.InfCte.Emit emit = factory.createTCTeInfCteEmit();
        infCte.setEmit(emit);

        CteEmitenteVO cteEmitente = cteCabecalho.getCteEmitente();
        emit.setCNPJ(cteEmitente.getCnpj());
        emit.setIE(cteEmitente.getIe());
        emit.setXFant(cteEmitente.getFantasia());
        emit.setXNome(cteEmitente.getNome());

        TEndeEmi endeEmi = factory.createTEndeEmi();
        emit.setEnderEmit(endeEmi);
        endeEmi.setCEP(cteEmitente.getCep());
        endeEmi.setCMun(cteEmitente.getCodigoMunicipio().toString());
        //endeEmi.setFone(cteEmitente.getTelefone());
        endeEmi.setNro(cteEmitente.getNumero());
        endeEmi.setUF(TUFSemEX.valueOf(cteEmitente.getUf()));
        endeEmi.setXBairro(cteEmitente.getBairro());
        endeEmi.setXCpl(cteEmitente.getComplemento());
        endeEmi.setXLgr(cteEmitente.getLogradouro());
        endeEmi.setXMun(cteEmitente.getNomeMunicipio());

        //Remetente
        TCTe.InfCte.Rem rem = factory.createTCTeInfCteRem();
        infCte.setRem(rem);

        CteRemetenteVO cteRemetente = cteCabecalho.getCteRemetente();
        rem.setCNPJ(cteRemetente.getCnpj());
        //rem.setCPF(cteRemetente.getCpf().toString());
        rem.setEmail(cteRemetente.getEmail());
        //rem.setFone(cteRemetente.getTelefone());
        rem.setIE(cteRemetente.getIe());
        rem.setXFant(cteRemetente.getFantasia());
        if (cteCabecalho.getAmbiente() == 2) {
            rem.setXNome("CT-E EMITIDO EM AMBIENTE DE HOMOLOGACAO - SEM VALOR FISCAL");
        } else {
            rem.setXNome(cteRemetente.getNome());
        }
        

        TEndereco enderecoRem = factory.createTEndereco();
        rem.setEnderReme(enderecoRem);

        enderecoRem.setCEP(cteRemetente.getCep());
        enderecoRem.setCMun(cteRemetente.getCodigoMunicipio().toString());
        enderecoRem.setCPais(cteRemetente.getCodigoPais().toString());
        enderecoRem.setNro(cteRemetente.getNumero());
        enderecoRem.setUF(TUf.valueOf(cteRemetente.getUf()));
        enderecoRem.setXBairro(cteRemetente.getBairro());
        enderecoRem.setXCpl(cteRemetente.getComplemento());
        enderecoRem.setXLgr(cteRemetente.getLogradouro());
        enderecoRem.setXMun(cteRemetente.getNomeMunicipio());
        enderecoRem.setXPais(cteRemetente.getNomePais());

        //Destinatário
        CteDestinatarioVO cteDestinatario = cteCabecalho.getCteDestinatario();
        TCTe.InfCte.Dest dest = factory.createTCTeInfCteDest();
        infCte.setDest(dest);

        dest.setCNPJ(cteDestinatario.getCnpj());
        dest.setCPF(cteDestinatario.getCpf());
        dest.setEmail(cteDestinatario.getEmail());
        //dest.setFone(cteDestinatario.getTelefone());
        dest.setIE(cteDestinatario.getIe());
        if (cteCabecalho.getAmbiente() == 2) {
            dest.setXNome("CT-E EMITIDO EM AMBIENTE DE HOMOLOGACAO - SEM VALOR FISCAL");
        } else {
            dest.setXNome(cteDestinatario.getNome());
        }

        TEndereco enderecoDest = factory.createTEndereco();
        dest.setEnderDest(enderecoDest);
        enderecoDest.setCMun(cteDestinatario.getCodigoMunicipio().toString());
        enderecoDest.setCPais(cteDestinatario.getCodigoPais().toString());
        enderecoDest.setNro(cteDestinatario.getNumero());
        enderecoDest.setUF(TUf.valueOf(cteDestinatario.getUf()));
        enderecoDest.setXBairro(cteDestinatario.getBairro());
        enderecoDest.setXCpl(cteDestinatario.getComplemento());
        enderecoDest.setXLgr(cteDestinatario.getLogradouro());
        enderecoDest.setXMun(cteDestinatario.getNomeMunicipio());
        enderecoDest.setXPais(cteDestinatario.getNomePais());

        //Valores
        TCTe.InfCte.VPrest vPrest = factory.createTCTeInfCteVPrest();
        infCte.setVPrest(vPrest);

        vPrest.setVRec(formatoValor.format(cteCabecalho.getValorReceber()));
        vPrest.setVTPrest(formatoValor.format(cteCabecalho.getValorTotalServico()));

        //Imposto
        TCTe.InfCte.Imp imp = factory.createTCTeInfCteImp();
        infCte.setImp(imp);

        TImp icms = factory.createTImp();
        imp.setICMS(icms);

        if (cteCabecalho.getCst().equals("00")) {
            TImp.ICMS00 icms00 = factory.createTImpICMS00();
            icms.setICMS00(icms00);

            icms00.setCST(cteCabecalho.getCst());
            icms00.setPICMS(formatoValor.format(cteCabecalho.getAliquotaIcms()));
            icms00.setVBC(formatoValor.format(cteCabecalho.getBaseCalculoIcms()));
            icms00.setVICMS(formatoValor.format(cteCabecalho.getValorIcms()));
        }

        if (cteCabecalho.getCst().equals("20")) {
            TImp.ICMS20 icms20 = factory.createTImpICMS20();
            icms.setICMS20(icms20);

            icms20.setCST(cteCabecalho.getCst());
            icms20.setPICMS(formatoValor.format(cteCabecalho.getAliquotaIcms()));
            icms20.setVBC(formatoValor.format(cteCabecalho.getBaseCalculoIcms()));
            icms20.setVICMS(formatoValor.format(cteCabecalho.getValorIcms()));
            icms20.setPRedBC(formatoValor.format(cteCabecalho.getPercentualReducaoBcIcms()));
        }

        if (cteCabecalho.getCst().equals("45")) {
            TImp.ICMS45 icms45 = factory.createTImpICMS45();
            icms.setICMS45(icms45);

            icms45.setCST(cteCabecalho.getCst());
        }

        if (cteCabecalho.getCst().equals("60")) {
            TImp.ICMS60 icms60 = factory.createTImpICMS60();
            icms.setICMS60(icms60);

            icms60.setCST(cteCabecalho.getCst());
            icms60.setPICMSSTRet(formatoValor.format(cteCabecalho.getAliquotaIcmsStRetido()));
            icms60.setVBCSTRet(formatoValor.format(cteCabecalho.getValorBcIcmsStRetido()));
            icms60.setVCred(formatoValor.format(cteCabecalho.getValorCreditoPresumidoIcms()));
            icms60.setVICMSSTRet(formatoValor.format(cteCabecalho.getValorIcmsStRetido()));
        }

        if (cteCabecalho.getCst().equals("90")) {
            if (cteCabecalho.getCodigoMunicipioIniPrestacao().equals(cteCabecalho.getCodigoMunicipioFimPrestacao())) {
                TImp.ICMS90 icms90 = factory.createTImpICMS90();
                icms.setICMS90(icms90);

                icms90.setCST(cteCabecalho.getCst());
                icms90.setPICMS(formatoValor.format(cteCabecalho.getAliquotaIcms()));
                icms90.setPRedBC(formatoValor.format(cteCabecalho.getPercentualReducaoBcIcms()));
                icms90.setVBC(formatoValor.format(cteCabecalho.getBaseCalculoIcms()));
                icms90.setVCred(formatoValor.format(cteCabecalho.getValorCreditoPresumidoIcms()));
                icms90.setVICMS(formatoValor.format(cteCabecalho.getValorIcms()));
            } else {
                TImp.ICMSOutraUF icmsOutraUf = factory.createTImpICMSOutraUF();
                icms.setICMSOutraUF(icmsOutraUf);

                icmsOutraUf.setCST(cteCabecalho.getCst());
                icmsOutraUf.setPICMSOutraUF(formatoValor.format(cteCabecalho.getAliquotaIcmsOutraUf()));
                icmsOutraUf.setPRedBCOutraUF(formatoValor.format(cteCabecalho.getPercentualBcIcmsOutraUf()));
                icmsOutraUf.setVBCOutraUF(formatoValor.format(cteCabecalho.getValorBcIcmsOutraUf()));
                icmsOutraUf.setVICMSOutraUF(formatoValor.format(cteCabecalho.getValorIcmsOutraUf()));
            }
        }

        if (cteCabecalho.getSimplesNacionalIndicador() == 1) {
            TImp.ICMSSN icmsSn = factory.createTImpICMSSN();
            icms.setICMSSN(icmsSn);

            icmsSn.setCST(cteCabecalho.getCst());
            icmsSn.setIndSN(cteCabecalho.getSimplesNacionalIndicador().toString());
        }

        imp.setVTotTrib(formatoValor.format(cteCabecalho.getValorIcms()));
        imp.setInfAdFisco(cteCabecalho.getInformacoesAddFisco());

        //Grupo de Informacoes do CT-e Normal e Substituto
        TCTe.InfCte.InfCTeNorm infCTeNorm = factory.createTCTeInfCteInfCTeNorm();
        infCte.setInfCTeNorm(infCTeNorm);

        TCTe.InfCte.InfCTeNorm.InfCarga infCarga = factory.createTCTeInfCteInfCTeNormInfCarga();
        infCTeNorm.setInfCarga(infCarga);

        infCarga.setVCarga(formatoValor.format(cteCabecalho.getValorTotalCarga()));
        infCarga.setProPred(cteCabecalho.getProdutoPredominante());
        infCarga.setXOutCat(cteCabecalho.getCargaOutrasCaracteristicas());

        //Carga
        for (CteCargaVO c : cteCabecalho.getListaCteCarga()) {
            TCTe.InfCte.InfCTeNorm.InfCarga.InfQ infQ = factory.createTCTeInfCteInfCTeNormInfCargaInfQ();
            infCarga.getInfQ().add(infQ);

            infQ.setCUnid(c.getCodigoUnidadeMedida());
            infQ.setQCarga(formatoQuantidade.format(c.getQuantidade()));
            infQ.setTpMed(c.getTipoMedida());
        }

        //Informacoes dos documentos transportados pelo CTe
        TCTe.InfCte.InfCTeNorm.InfDoc infDoc = factory.createTCTeInfCteInfCTeNormInfDoc();
        infCTeNorm.setInfDoc(infDoc);

        for (CteInformacaoNfOutrosVO nfe : cteCabecalho.getListaCteInformacaoNfOutros()) {
            TCTe.InfCte.InfCTeNorm.InfDoc.InfNFe infNfe = factory.createTCTeInfCteInfCTeNormInfDocInfNFe();
            infDoc.getInfNFe().add(infNfe);

            infNfe.setChave(nfe.getChaveAcessoNfe());
            infNfe.setPIN(nfe.getPinSuframa() != null ? nfe.getPinSuframa().toString() : null);
            infNfe.setDPrev(nfe.getDataPrevistaEntrega() != null ? formatoData.format(nfe.getDataPrevistaEntrega()) : null);
        }

        //Informacoes do Modal
        TCTe.InfCte.InfCTeNorm.InfModal infModal = factory.createTCTeInfCteInfCTeNormInfModal();
        infModal.setVersaoModal("3.00");
        infCTeNorm.setInfModal(infModal);

        //Rodoviario
        Rodo rodo = factory.createRodo();
        rodo.setRNTRC(cteCabecalho.getCteRodoviario().getRntrc());
        
        for (CteRodoviarioOccVO o : cteCabecalho.getCteRodoviario().getListaCteRodoviarioOcc()) {
            Rodo.Occ occ = factory.createRodoOcc();
            rodo.getOcc().add(occ);
            
            occ.setDEmi(formatoData.format(o.getDataEmissao()));
            occ.setNOcc(o.getNumero().toString());
            occ.setSerie(o.getSerie());
            
            Rodo.Occ.EmiOcc emiOcc = factory.createRodoOccEmiOcc();
            occ.setEmiOcc(emiOcc);
            
            emiOcc.setCInt(o.getCodigoInterno());
            emiOcc.setCNPJ(o.getCnpj());
            emiOcc.setFone(o.getTelefone());
            emiOcc.setIE(o.getIe());
            emiOcc.setUF(TUf.valueOf(o.getUf()));
        }
        
        DOMResult res = new DOMResult();
        marshaller.marshal(factory.createRodo(rodo), res);
        Element rodoElement = ((Document) res.getNode()).getDocumentElement();
        infModal.setAny(rodoElement);

        JAXBElement<TEnviCTe> element = factory.createEnviCTe(enviCTe);

        StringWriter writer = new StringWriter();
        marshaller.marshal(element, writer);

        String xmlEnviCte = writer.toString();
        xmlEnviCte = xmlEnviCte.replaceAll("xmlns:ns2=\"http://www.w3.org/2000/09/xmldsig#\"", "");
        xmlEnviCte = xmlEnviCte.replaceAll("<CTe>", "<CTe xmlns=\"http://www.portalfiscal.inf.br/cte\">");
        
        return Biblioteca.assinaXML(xmlEnviCte, alias, ks, senha, "#CTe" + cteCabecalho.getChaveAcesso() + cteCabecalho.getDigitoChaveAcesso(), "CTe", "infCte", "Id");
    }

}
