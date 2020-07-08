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

import br.inf.portalfiscal.www.cte.wsdl.cterecepcao.CteRecepcaoStub;
import br.inf.portalfiscal.www.cte.wsdl.cteretrecepcao.CteRetRecepcaoStub;
import com.t2tierp.nfe.servidor.SocketFactoryDinamico;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.cert.X509Certificate;
import java.util.HashMap;
import java.util.Map;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.util.AXIOMUtil;
import org.apache.commons.httpclient.protocol.Protocol;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class EnviaCte {
    
    public Map enviaCte(String xml, String alias, KeyStore ks, char[] senha, String codigoUf, String ambiente) throws Exception {
        String versaoDados = "3.00";
        String url = "";
        if (codigoUf.equals("53")) {
            if (ambiente.equals("1")) {
                url = "https://cte.svrs.rs.gov.br/ws/cterecepcao/CteRecepcao.asmx";
            } else if (ambiente.equals("2")) {
                url = "https://cte-homologacao.svrs.rs.gov.br/ws/cterecepcao/CteRecepcao.asmx";
            }
        }
        /* fica a cargo de cada participante definir a url que será utiizada de acordo com o código da UF
         * URLs disponíveis em:
         * Homologação: http://hom.cte.fazenda.gov.br/portal/webServices.aspx?tipoConteudo=wpdBtfbTMrw=
         * Produção: http://www.cte.fazenda.gov.br/portal/webServices.aspx?tipoConteudo=wpdBtfbTMrw=
         */

        if (url.equals("")) {
            throw new Exception("URL da sefaz não definida para o código de UF = " + codigoUf);
        }

        X509Certificate certificate = (X509Certificate) ks.getCertificate(alias);
        PrivateKey privatekey = (PrivateKey) ks.getKey(alias, senha);
        SocketFactoryDinamico socketFactory = new SocketFactoryDinamico(certificate, privatekey);
        //arquivo que contém a cadeia de certificados do serviço a ser consumido
        socketFactory.setFileCacerts(this.getClass().getResourceAsStream("/br/inf/portalfiscal/cte/jssecacerts"));

        //define o protocolo a ser utilizado na conexão
        Protocol protocol = new Protocol("https", socketFactory, 443);
        Protocol.registerProtocol("https", protocol);

        OMElement omElement = AXIOMUtil.stringToOM(xml);

        CteRecepcaoStub.CteDadosMsg dadosMsg = new CteRecepcaoStub.CteDadosMsg();
        dadosMsg.setExtraElement(omElement);

        CteRecepcaoStub.CteCabecMsg cabecMsg = new CteRecepcaoStub.CteCabecMsg();
        cabecMsg.setCUF(codigoUf);
        cabecMsg.setVersaoDados(versaoDados);

        CteRecepcaoStub.CteCabecMsgE cabecMsgE = new CteRecepcaoStub.CteCabecMsgE();
        cabecMsgE.setCteCabecMsg(cabecMsg);

        CteRecepcaoStub stub = new CteRecepcaoStub(url);

        CteRecepcaoStub.CteRecepcaoLoteResult result = stub.cteRecepcaoLote(dadosMsg, cabecMsgE);

        ByteArrayInputStream in = new ByteArrayInputStream(result.getExtraElement().toString().getBytes());

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        dbf.setNamespaceAware(true);
        Document doc = dbf.newDocumentBuilder().parse(in);

        String recibo = "";
        NodeList nodeList = doc.getDocumentElement().getElementsByTagName("nRec");

        for (int i = 0; i < nodeList.getLength(); i++) {
            Element element = (Element) nodeList.item(i);
            recibo = element.getTextContent();
        }

        Thread.sleep(3000);
        return consultaEnvioCte(recibo, xml, codigoUf, ambiente);
    }

    public Map consultaEnvioCte(String numeroRecibo, String xmlEnviCte, String codigoUf, String ambiente) throws Exception {
        Map map = new HashMap();
        String url = "";
        if (codigoUf.equals("53")) {
            if (ambiente.equals("1")) {
                url = "https://cte.svrs.rs.gov.br/ws/cteretrecepcao/cteRetRecepcao.asmx";
            } else if (ambiente.equals("2")) {
                url = "https://cte-homologacao.svrs.rs.gov.br/ws/cteretrecepcao/cteRetRecepcao.asmx";
            }
        }
        /* fica a cargo de cada participante definir a url que será utiizada de acordo com o código da UF
         * URLs disponíveis em:
         * Homologação: http://hom.cte.fazenda.gov.br/portal/webServices.aspx?tipoConteudo=wpdBtfbTMrw=
         * Produção: http://www.cte.fazenda.gov.br/portal/webServices.aspx?tipoConteudo=wpdBtfbTMrw=
         */

        if (url.equals("")) {
            throw new Exception("URL de retorno da sefaz não definida para o código de UF = " + codigoUf);
        }

        String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"
                + "<consReciCTe versao=\"3.00\" xmlns=\"http://www.portalfiscal.inf.br/cte\">"
                + "<tpAmb>" + ambiente + "</tpAmb>"
                + "<nRec>" + numeroRecibo + "</nRec>"
                + "</consReciCTe>";

        OMElement omeElement = AXIOMUtil.stringToOM(xml);

        CteRetRecepcaoStub.CteDadosMsg dadosMsg = new CteRetRecepcaoStub.CteDadosMsg();
        dadosMsg.setExtraElement(omeElement);

        CteRetRecepcaoStub.CteCabecMsg cabecMsg = new CteRetRecepcaoStub.CteCabecMsg();
        cabecMsg.setCUF(codigoUf);
        cabecMsg.setVersaoDados("3.00");

        CteRetRecepcaoStub.CteCabecMsgE cabecMsgE = new CteRetRecepcaoStub.CteCabecMsgE();
        cabecMsgE.setCteCabecMsg(cabecMsg);

        CteRetRecepcaoStub stub = new CteRetRecepcaoStub(url);

        CteRetRecepcaoStub.CteRetRecepcaoResult result = stub.cteRetRecepcao(dadosMsg, cabecMsgE);

        System.out.println(result.getExtraElement().toString());
        
        ByteArrayInputStream in = new ByteArrayInputStream(result.getExtraElement().toString().getBytes("UTF-8"));
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        dbf.setNamespaceAware(true);
        Document docResposta = dbf.newDocumentBuilder().parse(in);

        NodeList nodeListMotivo = docResposta.getDocumentElement().getElementsByTagName("xMotivo");
        NodeList nodeListProt = docResposta.getDocumentElement().getElementsByTagName("protCte");
        String respostaSefaz = "";
        String xmlProt = "";
        String xmlProc = "";
        String xmlCte = "";
        boolean autorizado = false;

        for (int i = 0; i < nodeListMotivo.getLength(); i++) {
            Element element = (Element) nodeListMotivo.item(i);
            respostaSefaz += element.getTextContent() + "\n";
            if (element.getTextContent().startsWith("Autorizado")) {
                autorizado = true;
            }
        }

        map.put("autorizado", autorizado);
        map.put("resposta", respostaSefaz);
        map.put("numeroRecibo", numeroRecibo);
        map.put("xmlEnviCte", xmlEnviCte);

        if (autorizado) {
            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer trans = tf.newTransformer();

            ByteArrayOutputStream outProt = new ByteArrayOutputStream();
            trans.transform(new DOMSource(nodeListProt.item(0)), new StreamResult(outProt));
            xmlProt = outProt.toString().replaceAll("<\\?xml version=\"1.0\" encoding=\"UTF-8\"\\?>", "");

            ByteArrayInputStream inEnviCte = new ByteArrayInputStream(xmlEnviCte.getBytes());
            Document docEnviCte = dbf.newDocumentBuilder().parse(inEnviCte);
            ByteArrayOutputStream outCte = new ByteArrayOutputStream();
            trans.transform(new DOMSource(docEnviCte.getElementsByTagName("CTe").item(0)), new StreamResult(outCte));
            xmlCte = outCte.toString().replaceAll("<\\?xml version=\"1.0\" encoding=\"UTF-8\"\\?>", "");

            xmlProc = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>";
            xmlProc += "<cteProc versao=\"3.10\" xmlns=\"http://www.portalfiscal.inf.br/cte\">";
            xmlProc += xmlCte;
            xmlProc += xmlProt;
            xmlProc += "</cteProc>";

            map.put("xmlProc", xmlProc);
        }

        return map;
    }    
}
