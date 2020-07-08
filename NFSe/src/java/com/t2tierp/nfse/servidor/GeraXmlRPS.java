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
package com.t2tierp.nfse.servidor;

import br.org.abrasf.nfse.TcRps;
import br.org.abrasf.nfse.EnviarLoteRpsEnvio;
import br.org.abrasf.nfse.TcDadosServico;
import br.org.abrasf.nfse.TcIdentificacaoPrestador;
import br.org.abrasf.nfse.TcIdentificacaoRps;
import br.org.abrasf.nfse.TcInfRps;
import br.org.abrasf.nfse.TcLoteRps;
import br.org.abrasf.nfse.ObjectFactory;
import br.org.abrasf.nfse.TcCpfCnpj;
import br.org.abrasf.nfse.TcDadosTomador;
import br.org.abrasf.nfse.TcEndereco;
import br.org.abrasf.nfse.TcIdentificacaoTomador;
import br.org.abrasf.nfse.TcValores;
import com.t2tierp.cadastros.java.PessoaEnderecoVO;
import com.t2tierp.nfse.java.NfseCabecalhoVO;
import com.t2tierp.nfse.java.NfseDetalheVO;
import com.t2tierp.padrao.java.Biblioteca;
import java.io.StringWriter;
import java.math.BigInteger;
import java.security.KeyStore;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.Marshaller;
import javax.xml.namespace.QName;

public class GeraXmlRPS {

    public String geraXml(NfseCabecalhoVO nfseCabecalho, String alias, KeyStore ks, char[] senha) throws Exception {
        ObjectFactory factory = new ObjectFactory();
        List<String> listaRpsAssinar = new ArrayList<>();

        EnviarLoteRpsEnvio enviarLoteRpsEnvio = factory.createEnviarLoteRpsEnvio();
        TcLoteRps tcLoteRps = factory.createTcLoteRps();
        enviarLoteRpsEnvio.setLoteRps(tcLoteRps);

        tcLoteRps.setNumeroLote(BigInteger.ONE);
        tcLoteRps.setId("LoteRps" + tcLoteRps.getNumeroLote());
        tcLoteRps.setVersao("1.00");
        tcLoteRps.setCnpj(nfseCabecalho.getEmpresa().getCnpj());
        tcLoteRps.setInscricaoMunicipal(nfseCabecalho.getEmpresa().getInscricaoMunicipal());
        tcLoteRps.setQuantidadeRps(nfseCabecalho.getListaNfseDetalhe().size());

        TcLoteRps.ListaRps listaRps = factory.createTcLoteRpsListaRps();
        tcLoteRps.setListaRps(listaRps);

        for (NfseDetalheVO d : nfseCabecalho.getListaNfseDetalhe()) {

            TcRps tcRps = factory.createTcRps();
            listaRps.getRps().add(tcRps);

            TcInfRps tcInfRps = factory.createTcInfRps();
            tcRps.setInfRps(tcInfRps);

            tcInfRps.setId("InfRPS" + d.getId());
            listaRpsAssinar.add(tcInfRps.getId());

            TcIdentificacaoRps tcIdentificacaoRps = factory.createTcIdentificacaoRps();
            tcIdentificacaoRps.setNumero(BigInteger.valueOf(Long.valueOf(nfseCabecalho.getNumeroRps())));
            tcIdentificacaoRps.setSerie(nfseCabecalho.getSerieRps());
            tcIdentificacaoRps.setTipo(nfseCabecalho.getTipoRps().byteValue());

            tcInfRps.setIdentificacaoRps(tcIdentificacaoRps);
            tcInfRps.setDataEmissao(nfseCabecalho.getDataEmissaoRps());
            tcInfRps.setNaturezaOperacao(nfseCabecalho.getNaturezaOperacao().byteValue());
            tcInfRps.setOptanteSimplesNacional(simNao(nfseCabecalho.getOptanteSimplesNacional()));
            tcInfRps.setRegimeEspecialTributacao(nfseCabecalho.getRegimeEspecialTributacao().byteValue());
            tcInfRps.setIncentivadorCultural(simNao(nfseCabecalho.getIncentivadorCultural()));
            tcInfRps.setStatus(new Integer(1).byteValue());

            //SERVICO
            TcDadosServico tcDadosServico = factory.createTcDadosServico();

            TcValores tcValores = factory.createTcValores();
            tcValores.setValorServicos(d.getValorServicos());
            tcValores.setValorDeducoes(d.getValorDeducoes());
            tcValores.setValorPis(d.getValorPis());
            tcValores.setValorCofins(d.getValorCofins());
            tcValores.setValorInss(d.getValorInss());
            tcValores.setValorIr(d.getValorIr());
            tcValores.setValorCsll(d.getValorCsll());
            tcValores.setValorIss(d.getValorIss());
            tcValores.setOutrasRetencoes(d.getOutrasRetencoes());
            tcValores.setBaseCalculo(d.getValorServicos());
            tcValores.setAliquota(d.getAliquota());
            tcValores.setValorLiquidoNfse(d.getValorServicos());
            tcValores.setValorIssRetido(d.getValorIssRetido());
            tcValores.setDescontoIncondicionado(d.getValorDescontoIncondicionado());
            tcValores.setDescontoCondicionado(d.getValorDescontoCondicionado());
            tcValores.setIssRetido(simNao("N"));

            tcDadosServico.setValores(tcValores);

            tcDadosServico.setItemListaServico(Integer.valueOf(d.getNfseListaServico().getCodigoPrincipal()) + "." + d.getNfseListaServico().getCodigoSecundario());
            tcDadosServico.setCodigoTributacaoMunicipio(d.getCodigoTributacaoMunicipio());
            tcDadosServico.setDiscriminacao(d.getDiscriminacao());
            tcDadosServico.setCodigoMunicipio(d.getMunicipioPrestacao());
            
            tcInfRps.setServico(tcDadosServico);

            //PRESTADOR
            TcIdentificacaoPrestador tcIdentificacaoPrestador = factory.createTcIdentificacaoPrestador();
            tcIdentificacaoPrestador.setCnpj(tcLoteRps.getCnpj());
            tcIdentificacaoPrestador.setInscricaoMunicipal(tcLoteRps.getInscricaoMunicipal());

            tcInfRps.setPrestador(tcIdentificacaoPrestador);

            //TOMADOR
            TcDadosTomador tcDadosTomador = factory.createTcDadosTomador();

            TcIdentificacaoTomador tcIdentificacaoTomador = factory.createTcIdentificacaoTomador();
            TcCpfCnpj tcCpfCnpj = factory.createTcCpfCnpj();
            if (nfseCabecalho.getCliente().getPessoa().getPessoaFisica() != null) {
                tcCpfCnpj.setCpf(nfseCabecalho.getCliente().getPessoa().getPessoaFisica().getCpf());
            }
            if (nfseCabecalho.getCliente().getPessoa().getPessoaJuridica() != null) {
                tcCpfCnpj.setCnpj(nfseCabecalho.getCliente().getPessoa().getPessoaJuridica().getCnpj());
            }
            tcIdentificacaoTomador.setCpfCnpj(tcCpfCnpj);
            tcDadosTomador.setRazaoSocial(nfseCabecalho.getCliente().getPessoa().getNome());

            TcEndereco tcEndereco = null;
            if (nfseCabecalho.getCliente().getPessoa().getListaEndereco() != null) {
                PessoaEnderecoVO endereco = nfseCabecalho.getCliente().getPessoa().getListaEndereco().get(0);
                tcEndereco = factory.createTcEndereco();
                tcEndereco.setEndereco(endereco.getLogradouro());
                tcEndereco.setNumero(endereco.getNumero());
                tcEndereco.setComplemento(endereco.getComplemento());
                tcEndereco.setBairro(endereco.getBairro());
                tcEndereco.setCep(Integer.valueOf(endereco.getCep()));
                tcEndereco.setUf(endereco.getUf());
                tcEndereco.setCodigoMunicipio(endereco.getMunicipioIbge());
            }
            tcDadosTomador.setEndereco(tcEndereco);

            tcInfRps.setTomador(tcDadosTomador);
        }

        JAXBContext jc = JAXBContext.newInstance("br.org.abrasf.nfse");
        Marshaller marshaller = jc.createMarshaller();

        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, false);
        QName qName = new QName("http://www.abrasf.org.br/nfse.xsd", "EnviarLoteRpsEnvio");
        JAXBElement<EnviarLoteRpsEnvio> element = new JAXBElement<>(qName, EnviarLoteRpsEnvio.class, null, enviarLoteRpsEnvio);

        StringWriter writer = new StringWriter();
        marshaller.marshal(element, writer);

        String xmlNfse = writer.toString();

        xmlNfse = xmlNfse.replaceAll("xmlns:ns2=\"http://www.w3.org/2000/09/xmldsig#\"", "");
        xmlNfse = xmlNfse.replaceAll("xmlns:ns3=\"http://www.abrasf.org.br/nfse.xsd\"", "");
        xmlNfse = xmlNfse.replaceAll("ns3:EnviarLoteRpsEnvio", "EnviarLoteRpsEnvio");

        //assina os RPS's
        for (String s : listaRpsAssinar) {
            xmlNfse = Biblioteca.assinaXML(xmlNfse, alias, ks, senha, "#" + s, "Rps", "InfRps", "Id");
        }

        //assina o lote
        xmlNfse = Biblioteca.assinaXML(xmlNfse, alias, ks, senha, "#" + tcLoteRps.getId(), "EnviarLoteRpsEnvio", "LoteRps", "Id");

        return xmlNfse;
    }

    private byte simNao(String simNao) {
        return simNao.equals("S") ? new Integer(1).byteValue() : new Integer(2).byteValue();
    }

}
