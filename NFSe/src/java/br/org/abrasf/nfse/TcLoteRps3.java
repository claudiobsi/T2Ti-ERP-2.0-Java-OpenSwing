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
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * <p>Classe Java de tcLoteRps3 complex type.
 * 
 * <p>O seguinte fragmento do esquema especifica o conteúdo esperado contido dentro desta classe.
 * 
 * <pre>
 * &lt;complexType name="tcLoteRps3">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="NumeroLote" type="{http://www.abrasf.org.br/nfse.xsd}tsNumeroLote"/>
 *         &lt;element name="Cnpj" type="{http://www.abrasf.org.br/nfse.xsd}tsCnpj"/>
 *         &lt;element name="InscricaoMunicipal" type="{http://www.abrasf.org.br/nfse.xsd}tsInscricaoMunicipal"/>
 *         &lt;element name="QuantidadeRps" type="{http://www.abrasf.org.br/nfse.xsd}tsQuantidadeRps"/>
 *         &lt;element name="ListaRps">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="Rps" type="{http://www.abrasf.org.br/nfse.xsd}tcRps" maxOccurs="3"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *       &lt;attribute name="Id" type="{http://www.abrasf.org.br/nfse.xsd}tsIdTag" />
 *       &lt;attribute name="versao" use="required" type="{http://www.abrasf.org.br/nfse.xsd}tsVersao" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "tcLoteRps3", propOrder = {
    "numeroLote",
    "cnpj",
    "inscricaoMunicipal",
    "quantidadeRps",
    "listaRps"
})
public class TcLoteRps3 {

    @XmlElement(name = "NumeroLote", required = true)
    @XmlSchemaType(name = "nonNegativeInteger")
    protected BigInteger numeroLote;
    @XmlElement(name = "Cnpj", required = true)
    protected String cnpj;
    @XmlElement(name = "InscricaoMunicipal", required = true)
    protected String inscricaoMunicipal;
    @XmlElement(name = "QuantidadeRps")
    protected int quantidadeRps;
    @XmlElement(name = "ListaRps", required = true)
    protected TcLoteRps3 .ListaRps listaRps;
    @XmlAttribute(name = "Id")
    protected String id;
    @XmlAttribute(name = "versao", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String versao;

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
     * Obtém o valor da propriedade cnpj.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCnpj() {
        return cnpj;
    }

    /**
     * Define o valor da propriedade cnpj.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCnpj(String value) {
        this.cnpj = value;
    }

    /**
     * Obtém o valor da propriedade inscricaoMunicipal.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInscricaoMunicipal() {
        return inscricaoMunicipal;
    }

    /**
     * Define o valor da propriedade inscricaoMunicipal.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInscricaoMunicipal(String value) {
        this.inscricaoMunicipal = value;
    }

    /**
     * Obtém o valor da propriedade quantidadeRps.
     * 
     */
    public int getQuantidadeRps() {
        return quantidadeRps;
    }

    /**
     * Define o valor da propriedade quantidadeRps.
     * 
     */
    public void setQuantidadeRps(int value) {
        this.quantidadeRps = value;
    }

    /**
     * Obtém o valor da propriedade listaRps.
     * 
     * @return
     *     possible object is
     *     {@link TcLoteRps3 .ListaRps }
     *     
     */
    public TcLoteRps3 .ListaRps getListaRps() {
        return listaRps;
    }

    /**
     * Define o valor da propriedade listaRps.
     * 
     * @param value
     *     allowed object is
     *     {@link TcLoteRps3 .ListaRps }
     *     
     */
    public void setListaRps(TcLoteRps3 .ListaRps value) {
        this.listaRps = value;
    }

    /**
     * Obtém o valor da propriedade id.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getId() {
        return id;
    }

    /**
     * Define o valor da propriedade id.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setId(String value) {
        this.id = value;
    }

    /**
     * Obtém o valor da propriedade versao.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVersao() {
        return versao;
    }

    /**
     * Define o valor da propriedade versao.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVersao(String value) {
        this.versao = value;
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
     *         &lt;element name="Rps" type="{http://www.abrasf.org.br/nfse.xsd}tcRps" maxOccurs="3"/>
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
        "rps"
    })
    public static class ListaRps {

        @XmlElement(name = "Rps", required = true)
        protected List<TcRps> rps;

        /**
         * Gets the value of the rps property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the rps property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getRps().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link TcRps }
         * 
         * 
         */
        public List<TcRps> getRps() {
            if (rps == null) {
                rps = new ArrayList<TcRps>();
            }
            return this.rps;
        }

    }

}
