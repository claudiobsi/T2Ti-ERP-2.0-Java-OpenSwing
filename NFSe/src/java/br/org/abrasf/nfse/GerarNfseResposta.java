//
// Este arquivo foi gerado pela Arquitetura JavaTM para Implementação de Referência (JAXB) de Bind XML, v2.2.8-b130911.1802 
// Consulte <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Todas as modificações neste arquivo serão perdidas após a recompilação do esquema de origem. 
// Gerado em: 2017.01.24 às 11:39:55 AM BRST 
//


package br.org.abrasf.nfse;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
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
 *       &lt;sequence>
 *         &lt;element name="NumeroLote" type="{http://www.abrasf.org.br/nfse.xsd}tsNumeroLote" minOccurs="0"/>
 *         &lt;element name="DataRecebimento" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="Protocolo" type="{http://www.abrasf.org.br/nfse.xsd}tsNumeroProtocolo" minOccurs="0"/>
 *         &lt;choice>
 *           &lt;element name="ListaNfse">
 *             &lt;complexType>
 *               &lt;complexContent>
 *                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                   &lt;sequence>
 *                     &lt;element ref="{http://www.abrasf.org.br/nfse.xsd}CompNfse" maxOccurs="3"/>
 *                   &lt;/sequence>
 *                 &lt;/restriction>
 *               &lt;/complexContent>
 *             &lt;/complexType>
 *           &lt;/element>
 *           &lt;element ref="{http://www.abrasf.org.br/nfse.xsd}ListaMensagemRetorno"/>
 *           &lt;element ref="{http://www.abrasf.org.br/nfse.xsd}ListaMensagemRetornoLote"/>
 *         &lt;/choice>
 *       &lt;/sequence>
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
    "listaNfse",
    "listaMensagemRetorno",
    "listaMensagemRetornoLote"
})
@XmlRootElement(name = "GerarNfseResposta")
public class GerarNfseResposta {

    @XmlElement(name = "NumeroLote")
    @XmlSchemaType(name = "nonNegativeInteger")
    protected BigInteger numeroLote;
    @XmlElement(name = "DataRecebimento")
    @XmlSchemaType(name = "dateTime")
    protected Date dataRecebimento;
    @XmlElement(name = "Protocolo")
    protected String protocolo;
    @XmlElement(name = "ListaNfse")
    protected GerarNfseResposta.ListaNfse listaNfse;
    @XmlElement(name = "ListaMensagemRetorno")
    protected ListaMensagemRetorno listaMensagemRetorno;
    @XmlElement(name = "ListaMensagemRetornoLote")
    protected ListaMensagemRetornoLote listaMensagemRetornoLote;

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
     * Obtém o valor da propriedade listaNfse.
     * 
     * @return
     *     possible object is
     *     {@link GerarNfseResposta.ListaNfse }
     *     
     */
    public GerarNfseResposta.ListaNfse getListaNfse() {
        return listaNfse;
    }

    /**
     * Define o valor da propriedade listaNfse.
     * 
     * @param value
     *     allowed object is
     *     {@link GerarNfseResposta.ListaNfse }
     *     
     */
    public void setListaNfse(GerarNfseResposta.ListaNfse value) {
        this.listaNfse = value;
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

    /**
     * Obtém o valor da propriedade listaMensagemRetornoLote.
     * 
     * @return
     *     possible object is
     *     {@link ListaMensagemRetornoLote }
     *     
     */
    public ListaMensagemRetornoLote getListaMensagemRetornoLote() {
        return listaMensagemRetornoLote;
    }

    /**
     * Define o valor da propriedade listaMensagemRetornoLote.
     * 
     * @param value
     *     allowed object is
     *     {@link ListaMensagemRetornoLote }
     *     
     */
    public void setListaMensagemRetornoLote(ListaMensagemRetornoLote value) {
        this.listaMensagemRetornoLote = value;
    }


    /**
     * <p>Classe Java de anonymous complex type.
     * 
     * <p>O seguinte fragmento do esquema especifica o conteúdo esperado contido dentro desta classe.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element ref="{http://www.abrasf.org.br/nfse.xsd}CompNfse" maxOccurs="3"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "compNfse"
    })
    public static class ListaNfse {

        @XmlElement(name = "CompNfse", required = true)
        protected List<TcCompNfse> compNfse;

        /**
         * Gets the value of the compNfse property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the compNfse property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getCompNfse().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link TcCompNfse }
         * 
         * 
         */
        public List<TcCompNfse> getCompNfse() {
            if (compNfse == null) {
                compNfse = new ArrayList<TcCompNfse>();
            }
            return this.compNfse;
        }

    }

}
