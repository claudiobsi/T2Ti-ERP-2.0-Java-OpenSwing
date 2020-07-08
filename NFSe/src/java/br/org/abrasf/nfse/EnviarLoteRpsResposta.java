//
// Este arquivo foi gerado pela Arquitetura JavaTM para Implementação de Referência (JAXB) de Bind XML, v2.2.8-b130911.1802 
// Consulte <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Todas as modificações neste arquivo serão perdidas após a recompilação do esquema de origem. 
// Gerado em: 2017.01.24 às 11:39:55 AM BRST 
//


package br.org.abrasf.nfse;

import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import java.util.Date;


/**
 * <p>Classe Java de anonymous complex type.
 * 
 * <p>O seguinte fragmento do esquema especifica o conteúdo esperado contido dentro desta classe.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;choice>
 *         &lt;sequence>
 *           &lt;element name="NumeroLote" type="{http://www.abrasf.org.br/nfse.xsd}tsNumeroLote"/>
 *           &lt;element name="DataRecebimento" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *           &lt;element name="Protocolo" type="{http://www.abrasf.org.br/nfse.xsd}tsNumeroProtocolo"/>
 *         &lt;/sequence>
 *         &lt;element ref="{http://www.abrasf.org.br/nfse.xsd}ListaMensagemRetorno"/>
 *       &lt;/choice>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "numeroLote",
    "dataRecebimento",
    "protocolo",
    "listaMensagemRetorno"
})
@XmlRootElement(name = "EnviarLoteRpsResposta")
public class EnviarLoteRpsResposta {

    @XmlElement(name = "NumeroLote")
    @XmlSchemaType(name = "nonNegativeInteger")
    protected BigInteger numeroLote;
    @XmlElement(name = "DataRecebimento")
    @XmlSchemaType(name = "dateTime")
    protected Date dataRecebimento;
    @XmlElement(name = "Protocolo")
    protected String protocolo;
    @XmlElement(name = "ListaMensagemRetorno")
    protected ListaMensagemRetorno listaMensagemRetorno;

    /**
     * Obtém o valor da propriedade numeroLote.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getNumeroLote() {
        return numeroLote;
    }

    /**
     * Define o valor da propriedade numeroLote.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setNumeroLote(BigInteger value) {
        this.numeroLote = value;
    }

    /**
     * Obtém o valor da propriedade dataRecebimento.
     * 
     * @return
     *     possible object is
     *     {@link Date }
     *     
     */
    public Date getDataRecebimento() {
        return dataRecebimento;
    }

    /**
     * Define o valor da propriedade dataRecebimento.
     * 
     * @param value
     *     allowed object is
     *     {@link Date }
     *     
     */
    public void setDataRecebimento(Date value) {
        this.dataRecebimento = value;
    }

    /**
     * Obtém o valor da propriedade protocolo.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProtocolo() {
        return protocolo;
    }

    /**
     * Define o valor da propriedade protocolo.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProtocolo(String value) {
        this.protocolo = value;
    }

    /**
     * Obtém o valor da propriedade listaMensagemRetorno.
     * 
     * @return
     *     possible object is
     *     {@link ListaMensagemRetorno }
     *     
     */
    public ListaMensagemRetorno getListaMensagemRetorno() {
        return listaMensagemRetorno;
    }

    /**
     * Define o valor da propriedade listaMensagemRetorno.
     * 
     * @param value
     *     allowed object is
     *     {@link ListaMensagemRetorno }
     *     
     */
    public void setListaMensagemRetorno(ListaMensagemRetorno value) {
        this.listaMensagemRetorno = value;
    }

}
