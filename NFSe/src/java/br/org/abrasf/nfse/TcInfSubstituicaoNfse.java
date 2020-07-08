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
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java de tcInfSubstituicaoNfse complex type.
 * 
 * <p>O seguinte fragmento do esquema especifica o conteúdo esperado contido dentro desta classe.
 * 
 * <pre>
 * &lt;complexType name="tcInfSubstituicaoNfse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="NfseSubstituidora" type="{http://www.abrasf.org.br/nfse.xsd}tsNumeroNfse"/>
 *       &lt;/sequence>
 *       &lt;attribute name="Id" type="{http://www.abrasf.org.br/nfse.xsd}tsIdTag" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "tcInfSubstituicaoNfse", propOrder = {
    "nfseSubstituidora"
})
public class TcInfSubstituicaoNfse {

    @XmlElement(name = "NfseSubstituidora", required = true)
    @XmlSchemaType(name = "nonNegativeInteger")
    protected BigInteger nfseSubstituidora;
    @XmlAttribute(name = "Id")
    protected String id;

    /**
     * Obtém o valor da propriedade nfseSubstituidora.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getNfseSubstituidora() {
        return nfseSubstituidora;
    }

    /**
     * Define o valor da propriedade nfseSubstituidora.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setNfseSubstituidora(BigInteger value) {
        this.nfseSubstituidora = value;
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

}
