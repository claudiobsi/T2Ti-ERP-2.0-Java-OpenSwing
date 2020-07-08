//
// Este arquivo foi gerado pela Arquitetura JavaTM para Implementação de Referência (JAXB) de Bind XML, v2.2.8-b130911.1802 
// Consulte <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Todas as modificações neste arquivo serão perdidas após a recompilação do esquema de origem. 
// Gerado em: 2017.01.24 às 11:39:55 AM BRST 
//


package br.org.abrasf.nfse;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import org.w3._2000._09.xmldsig_.SignatureType;


/**
 * <p>Classe Java de tcPedidoCancelamento complex type.
 * 
 * <p>O seguinte fragmento do esquema especifica o conteúdo esperado contido dentro desta classe.
 * 
 * <pre>
 * &lt;complexType name="tcPedidoCancelamento">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="InfPedidoCancelamento" type="{http://www.abrasf.org.br/nfse.xsd}tcInfPedidoCancelamento"/>
 *         &lt;element ref="{http://www.w3.org/2000/09/xmldsig#}Signature" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "tcPedidoCancelamento", propOrder = {
    "infPedidoCancelamento",
    "signature"
})
public class TcPedidoCancelamento {

    @XmlElement(name = "InfPedidoCancelamento", required = true)
    protected TcInfPedidoCancelamento infPedidoCancelamento;
    @XmlElement(name = "Signature", namespace = "http://www.w3.org/2000/09/xmldsig#")
    protected SignatureType signature;

    /**
     * Obtém o valor da propriedade infPedidoCancelamento.
     * 
     * @return
     *     possible object is
     *     {@link TcInfPedidoCancelamento }
     *     
     */
    public TcInfPedidoCancelamento getInfPedidoCancelamento() {
        return infPedidoCancelamento;
    }

    /**
     * Define o valor da propriedade infPedidoCancelamento.
     * 
     * @param value
     *     allowed object is
     *     {@link TcInfPedidoCancelamento }
     *     
     */
    public void setInfPedidoCancelamento(TcInfPedidoCancelamento value) {
        this.infPedidoCancelamento = value;
    }

    /**
     * Obtém o valor da propriedade signature.
     * 
     * @return
     *     possible object is
     *     {@link SignatureType }
     *     
     */
    public SignatureType getSignature() {
        return signature;
    }

    /**
     * Define o valor da propriedade signature.
     * 
     * @param value
     *     allowed object is
     *     {@link SignatureType }
     *     
     */
    public void setSignature(SignatureType value) {
        this.signature = value;
    }

}
