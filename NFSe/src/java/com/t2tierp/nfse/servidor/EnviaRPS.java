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

import br.gov.pbh.bhiss.ws.NfseWSServiceStub;
import br.org.abrasf.nfse.ConsultarLoteRpsResposta;
import br.org.abrasf.nfse.EnviarLoteRpsResposta;
import br.org.abrasf.nfse.TcCompNfse;
import br.org.abrasf.nfse.TcMensagemRetorno;
import br.org.abrasf.nfse.TcMensagemRetornoLote;
import com.t2tierp.nfe.servidor.SocketFactoryDinamico;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.cert.X509Certificate;
import java.util.HashMap;
import java.util.Map;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.apache.commons.httpclient.protocol.Protocol;
import org.w3c.dom.Document;

public class EnviaRPS {

    public Map enviaRPS(String xml, String alias, KeyStore ks, char[] senha) throws Exception {
        NfseWSServiceStub.Input input = new NfseWSServiceStub.Input();
        input.setNfseCabecMsg(getCabecalho());
        input.setNfseDadosMsg(xml);

        NfseWSServiceStub.RecepcionarLoteRpsRequest recepcionarLoteRpsRequest = new NfseWSServiceStub.RecepcionarLoteRpsRequest();
        recepcionarLoteRpsRequest.setRecepcionarLoteRpsRequest(input);

        NfseWSServiceStub stub = new NfseWSServiceStub(configuraConexao(alias, ks, senha));

        NfseWSServiceStub.RecepcionarLoteRpsResponse result = stub.recepcionarLoteRps(recepcionarLoteRpsRequest);

        ByteArrayInputStream in = new ByteArrayInputStream(result.getRecepcionarLoteRpsResponse().getOutputXML().getBytes("UTF-8"));

        String protocolo = "";
        String mensagem = "";

        JAXBContext jc = JAXBContext.newInstance("br.org.abrasf.nfse");
        Unmarshaller unmarshaller = jc.createUnmarshaller();

        EnviarLoteRpsResposta resposta = (EnviarLoteRpsResposta) unmarshaller.unmarshal(in);

        if (resposta.getListaMensagemRetorno() != null && resposta.getListaMensagemRetorno().getMensagemRetorno() != null) {
            for (TcMensagemRetorno m : resposta.getListaMensagemRetorno().getMensagemRetorno()) {
                mensagem += m.getCodigo() + ": " + m.getMensagem() + "\n";
            }
        }

        protocolo = resposta.getProtocolo() != null ? resposta.getProtocolo() : "";

        Map map = new HashMap();
        map.put("protocolo", protocolo);
        map.put("mensagem", mensagem);

        return map;
    }

    public Map consultaEnvioRPS(String protocolo, String cnpj, String inscricaoMunicipal, String alias, KeyStore ks, char[] senha) throws Exception {
        String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"
                + "<ConsultarLoteRpsEnvio xmlns=\"http://www.abrasf.org.br/nfse.xsd\">"
                + "<Prestador>"
                + "<Cnpj>"
                + cnpj
                + "</Cnpj>"
                + "<InscricaoMunicipal>"
                + inscricaoMunicipal
                + "</InscricaoMunicipal>"
                + "</Prestador>"
                + "<Protocolo>"
                + protocolo
                + "</Protocolo>"
                + "</ConsultarLoteRpsEnvio>";
        NfseWSServiceStub.Input input = new NfseWSServiceStub.Input();
        input.setNfseCabecMsg(getCabecalho());
        input.setNfseDadosMsg(xml);

        NfseWSServiceStub.ConsultarLoteRpsRequest consultarLoteRpsRequest = new NfseWSServiceStub.ConsultarLoteRpsRequest();
        consultarLoteRpsRequest.setConsultarLoteRpsRequest(input);

        NfseWSServiceStub stub = new NfseWSServiceStub(configuraConexao(alias, ks, senha));

        NfseWSServiceStub.ConsultarLoteRpsResponse result = stub.consultarLoteRps(consultarLoteRpsRequest);

        ByteArrayInputStream in = new ByteArrayInputStream(result.getConsultarLoteRpsResponse().getOutputXML().getBytes("UTF-8"));

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        dbf.setNamespaceAware(true);
        Document doc = dbf.newDocumentBuilder().parse(in);

        JAXBContext jc = JAXBContext.newInstance("br.org.abrasf.nfse");
        Unmarshaller unmarshaller = jc.createUnmarshaller();

        ConsultarLoteRpsResposta consultarLoteRpsResposta = (ConsultarLoteRpsResposta) unmarshaller.unmarshal(doc);

        Map map = new HashMap();
        String nfse = "";
        String msgRetorno = "";

        if (consultarLoteRpsResposta.getListaMensagemRetorno() != null && consultarLoteRpsResposta.getListaMensagemRetorno().getMensagemRetorno() != null) {
            for (TcMensagemRetorno m : consultarLoteRpsResposta.getListaMensagemRetorno().getMensagemRetorno()) {
                msgRetorno += m.getCodigo() + ": " + m.getMensagem() + "\n";
            }
        }

        if (consultarLoteRpsResposta.getListaMensagemRetornoLote() != null && consultarLoteRpsResposta.getListaMensagemRetornoLote().getMensagemRetorno() != null) {
            for (TcMensagemRetornoLote m : consultarLoteRpsResposta.getListaMensagemRetornoLote().getMensagemRetorno()) {
                msgRetorno += m.getCodigo() + ": " + m.getMensagem() + "\n";
            }
        }

        if (consultarLoteRpsResposta.getListaNfse() != null && consultarLoteRpsResposta.getListaNfse().getCompNfse() != null) {
            TcCompNfse tcCompNfse = consultarLoteRpsResposta.getListaNfse().getCompNfse().get(0);

            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer trans = tf.newTransformer();

            ByteArrayOutputStream outNfse = new ByteArrayOutputStream();
            trans.transform(new DOMSource(doc), new StreamResult(outNfse));
            nfse = outNfse.toString();

            map.put("numero", tcCompNfse.getNfse().getInfNfse().getNumero());
            map.put("codigoVerificacao", tcCompNfse.getNfse().getInfNfse().getCodigoVerificacao());
            map.put("dataEmissao", tcCompNfse.getNfse().getInfNfse().getDataEmissao());
        }

        map.put("nfse", nfse);
        map.put("msgRetorno", msgRetorno);

        return map;
    }

    private String configuraConexao(String alias, KeyStore ks, char[] senha) throws Exception {
        String url = "https://bhisshomologa.pbh.gov.br/bhiss-ws/nfse";
        //String url = "https://nfse-hom.procempa.com.br/bhiss-ws/nfse?wsdl";

        X509Certificate certificate = (X509Certificate) ks.getCertificate(alias);
        PrivateKey privatekey = (PrivateKey) ks.getKey(alias, senha);
        SocketFactoryDinamico socketFactory = new SocketFactoryDinamico(certificate, privatekey);
        //arquivo que contém a cadeia de certificados do serviço a ser consumido
        socketFactory.setFileCacerts(this.getClass().getResourceAsStream("/br/gov/pbh/bhiss/ws/jssecacerts"));

        //define o protocolo a ser utilizado na conexão
        Protocol protocol = new Protocol("https", socketFactory, 443);
        Protocol.registerProtocol("https", protocol);

        return url;
    }

    private String getCabecalho() {
        return "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"
                + "<cabecalho xmlns=\"http://www.abrasf.org.br/nfse.xsd\" versao=\"1.00\">"
                + "<versaoDados>1.00</versaoDados>"
                + "</cabecalho>";
    }
}
