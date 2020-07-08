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
package com.t2tierp.esocial.servidor;

import br.gov.esocial.schema.evt.evtinfoempregador.v02_02_00.ESocial;
import br.gov.esocial.schema.evt.evtinfoempregador.v02_02_00.TIdeCadastro;
import br.gov.esocial.schema.evt.evtinfoempregador.v02_02_00.TIdePeriodo;
import br.gov.esocial.schema.evt.evtinfoempregador.v02_02_00.TInfoEmpregador;
import br.gov.esocial.schema.evt.evttabrubrica.v02_02_00.TDadosRubrica;
import br.gov.esocial.schema.evt.evttabrubrica.v02_02_00.TEmpregador;
import br.gov.esocial.schema.evt.evttabrubrica.v02_02_00.TIdeRubrica;
import com.t2tierp.cadastros.java.EmpresaEnderecoVO;
import com.t2tierp.cadastros.java.EmpresaVO;
import com.t2tierp.padrao.java.Biblioteca;
import java.io.StringWriter;
import java.math.BigInteger;
import java.security.KeyStore;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.Marshaller;
import javax.xml.namespace.QName;

public class GeraXmlESocial {

    private final String alias;
    private final KeyStore ks;
    private final char[] senha;
    private SimpleDateFormat formatoData = new SimpleDateFormat("yyyy-MM");

    public GeraXmlESocial(String alias, KeyStore ks, char[] senha) {
        this.alias = alias;
        this.ks = ks;
        this.senha = senha;
    }

    public String gerarESocial1000(EmpresaVO empresa, EmpresaEnderecoVO endereco, Date dataInicial, Date dataFinal) throws Exception {
        JAXBContext jc = JAXBContext.newInstance("br.gov.esocial.schema.evt.evtinfoempregador.v02_02_00");
        Marshaller marshaller = jc.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, false);

        br.gov.esocial.schema.evt.evtinfoempregador.v02_02_00.ObjectFactory factory = new br.gov.esocial.schema.evt.evtinfoempregador.v02_02_00.ObjectFactory();
        
        ESocial eSocial = factory.createESocial();
        
        ESocial.EvtInfoEmpregador evtInfoEmpregador = factory.createESocialEvtInfoEmpregador();
        eSocial.setEvtInfoEmpregador(evtInfoEmpregador);
        evtInfoEmpregador.setId("evtInfoEmpregador");
        
        TIdeCadastro ideCadastro = factory.createTIdeCadastro();
        evtInfoEmpregador.setIdeEvento(ideCadastro);
        ideCadastro.setTpAmb(new Integer(3).byteValue());
        ideCadastro.setProcEmi(new Integer(1).byteValue());
        ideCadastro.setVerProc("1.0");
        
        ESocial.EvtInfoEmpregador.IdeEmpregador ideEmpregador = factory.createESocialEvtInfoEmpregadorIdeEmpregador();
        evtInfoEmpregador.setIdeEmpregador(ideEmpregador);
        ideEmpregador.setTpInsc(new Integer(1).byteValue());
        ideEmpregador.setNrInsc("0123456789");
        
        ESocial.EvtInfoEmpregador.InfoEmpregador infoEmpregador = factory.createESocialEvtInfoEmpregadorInfoEmpregador();
        evtInfoEmpregador.setInfoEmpregador(infoEmpregador);
        
        ESocial.EvtInfoEmpregador.InfoEmpregador.Inclusao inclusao = factory.createESocialEvtInfoEmpregadorInfoEmpregadorInclusao();
        infoEmpregador.setInclusao(inclusao);
        
        TIdePeriodo idePeriodo = factory.createTIdePeriodo();
        inclusao.setIdePeriodo(idePeriodo);
        idePeriodo.setIniValid(formatoData.format(dataInicial));
        idePeriodo.setFimValid(formatoData.format(dataFinal));
        
        TInfoEmpregador tInfoEmpregador = factory.createTInfoEmpregador();
        inclusao.setInfoCadastro(tInfoEmpregador);
        tInfoEmpregador.setNmRazao(empresa.getRazaoSocial());
        tInfoEmpregador.setClassTrib("01");
        tInfoEmpregador.setNatJurid("1234");
        tInfoEmpregador.setIndCoop(new Integer(1).byteValue());
        tInfoEmpregador.setIndConstr(new Integer(1).byteValue());
        tInfoEmpregador.setIndDesFolha(new Integer(1).byteValue());
        tInfoEmpregador.setIndOptRegEletron(new Integer(1).byteValue());
        tInfoEmpregador.setMultTabRubricas("N");
        tInfoEmpregador.setIndEntEd("N");
        tInfoEmpregador.setIndEtt("N");
        tInfoEmpregador.setNrRegEtt(BigInteger.valueOf(30));
        
        TInfoEmpregador.Contato contato = factory.createTInfoEmpregadorContato();
        tInfoEmpregador.setContato(contato);
        contato.setNmCtt(empresa.getContato());
        contato.setCpfCtt("11111111111");
        contato.setFoneFixo(endereco.getFone());
        contato.setFoneCel(endereco.getFone());
        contato.setEmail(empresa.getEmail());
        
        TInfoEmpregador.SoftwareHouse softwareHouse = factory.createTInfoEmpregadorSoftwareHouse();
        tInfoEmpregador.getSoftwareHouse().add(softwareHouse);
        softwareHouse.setCnpjSoftHouse("12345678901234");
        softwareHouse.setNmRazao("T2Ti.com");
        softwareHouse.setNmCont("Claudio");
        softwareHouse.setTelefone("6130425277");
        softwareHouse.setEmail("t2ti.com@gmail.com");
     
        TInfoEmpregador.InfoComplementares infoComplementares = factory.createTInfoEmpregadorInfoComplementares();
        tInfoEmpregador.setInfoComplementares(infoComplementares);
        
        TInfoEmpregador.InfoComplementares.SituacaoPJ situacaoPJ = factory.createTInfoEmpregadorInfoComplementaresSituacaoPJ();
        infoComplementares.setSituacaoPJ(situacaoPJ);
        situacaoPJ.setIndSitPJ(new Integer(0).byteValue());

        QName qName = new QName("http://www.esocial.gov.br/schema/evt/evtInfoEmpregador/v02_02_00", "eSocial");
        JAXBElement<ESocial> element = new JAXBElement<>(qName, ESocial.class, null, eSocial);

        StringWriter writer = new StringWriter();
        marshaller.marshal(element, writer);

        String xmlESocial = writer.toString();
        
        return Biblioteca.assinaXML(xmlESocial, alias, ks, senha, "#" + evtInfoEmpregador.getId(), "eSocial", "evtInfoEmpregador", "Id");
    }

    public String gerarESocial1010(EmpresaVO empresa, EmpresaEnderecoVO endereco, Date dataInicial, Date dataFinal) throws Exception {
        JAXBContext jc = JAXBContext.newInstance("br.gov.esocial.schema.evt.evttabrubrica.v02_02_00");
        Marshaller marshaller = jc.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, false);

        br.gov.esocial.schema.evt.evttabrubrica.v02_02_00.ObjectFactory factory = new br.gov.esocial.schema.evt.evttabrubrica.v02_02_00.ObjectFactory();
        
        br.gov.esocial.schema.evt.evttabrubrica.v02_02_00.ESocial eSocial = factory.createESocial();
        
        br.gov.esocial.schema.evt.evttabrubrica.v02_02_00.ESocial.EvtTabRubrica evtTabRubrica = factory.createESocialEvtTabRubrica();
        eSocial.setEvtTabRubrica(evtTabRubrica);
        evtTabRubrica.setId("evtTabRubrica");
        
        br.gov.esocial.schema.evt.evttabrubrica.v02_02_00.TIdeCadastro ideCadastro = factory.createTIdeCadastro();
        evtTabRubrica.setIdeEvento(ideCadastro);
        ideCadastro.setTpAmb(new Integer(3).byteValue());
        ideCadastro.setProcEmi(new Integer(1).byteValue());
        ideCadastro.setVerProc("1.0");
                
        TEmpregador empregador = factory.createTEmpregador();
        evtTabRubrica.setIdeEmpregador(empregador);
        empregador.setTpInsc(new Integer(1).byteValue());
        empregador.setNrInsc(empresa.getCnpj().substring(0, 8));

        br.gov.esocial.schema.evt.evttabrubrica.v02_02_00.ESocial.EvtTabRubrica.InfoRubrica infoRubrica = factory.createESocialEvtTabRubricaInfoRubrica();
        evtTabRubrica.setInfoRubrica(infoRubrica);
        
        br.gov.esocial.schema.evt.evttabrubrica.v02_02_00.ESocial.EvtTabRubrica.InfoRubrica.Inclusao inclusao = factory.createESocialEvtTabRubricaInfoRubricaInclusao();
        infoRubrica.setInclusao(inclusao);
        
        TIdeRubrica ideRubrica = factory.createTIdeRubrica();
        inclusao.setIdeRubrica(ideRubrica);
        ideRubrica.setCodRubr("codRubrica");
        ideRubrica.setIniValid(formatoData.format(dataInicial));
        ideRubrica.setFimValid(formatoData.format(dataFinal));

        TDadosRubrica dadosRubrica = factory.createTDadosRubrica();
        inclusao.setDadosRubrica(dadosRubrica);
        dadosRubrica.setDscRubr("nomeDaRubrica");
        dadosRubrica.setNatRubr(BigInteger.valueOf(1000));
        dadosRubrica.setTpRubr(new Integer(1).byteValue());
        dadosRubrica.setCodIncCP("00");
        dadosRubrica.setCodIncIRRF("00");
        dadosRubrica.setCodIncFGTS("00");
        dadosRubrica.setCodIncSIND("00");
        dadosRubrica.setRepDSR("S");
        dadosRubrica.setRep13("S");
        dadosRubrica.setRepFerias("N");
        dadosRubrica.setRepAviso("N");
        dadosRubrica.setObservacao("Observações sobre a rubrica");
        
        QName qName = new QName("http://www.esocial.gov.br/schema/evt/evtTabRubrica/v02_02_00", "eSocial");
        JAXBElement<br.gov.esocial.schema.evt.evttabrubrica.v02_02_00.ESocial> element = new JAXBElement<>(qName, br.gov.esocial.schema.evt.evttabrubrica.v02_02_00.ESocial.class, null, eSocial);

        StringWriter writer = new StringWriter();
        marshaller.marshal(element, writer);

        String xmlESocial = writer.toString();
        
        return Biblioteca.assinaXML(xmlESocial, alias, ks, senha, "#" + evtTabRubrica.getId(), "eSocial", "evtTabRubrica", "Id");
    }    
}
