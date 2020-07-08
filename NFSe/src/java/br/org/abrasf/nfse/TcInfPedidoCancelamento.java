//
// Este arquivo foi gerado pela Arquitetura JavaTM para Implementação de Referência (JAXB) de Bind XML, v2.2.8-b130911.1802 
// Consulte <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Todas as modificações neste arquivo serão perdidas após a recompilação do esquema de origem. 
// Gerado em: 2017.01.24 às 11:39:55 AM BRST 
//


package br.org.abrasf.nfse;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java de tcInfPedidoCancelamento complex type.
 * 
 * <p>O seguinte fragmento do esquema especifica o conteúdo esperado contido dentro desta classe.
 * 
 * <pre>
 * &lt;complexType name="tcInfPedidoCancelamento">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="IdentificacaoNfse" type="{http://www.abrasf.org.br/nfse.xsd}tcIdentificacaoNfse"/>
 *         &lt;element name="CodigoCancelamento" type="{http://www.abrasf.org.br/nfse.xsd}tsCodigoCancelamentoNfse"/>
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
@XmlType(name = "tcInfPedidoCancelamento", propOrder = {
    "identificacaoNfse",
    "codigoCancelamento"
})
public class TcInfPedidoCancelamento {

    @XmlElement(name = "IdentificacaoNfse", required = true)
    protected TcIdentificacaoNfse identificacaoNfse;
    @XmlElement(name = "CodigoCancelamento", required = true)
    protected String codigoCancelamento;
    @XmlAttribute(name = "Id")
    protected String id;

    /**
     * Obtém o valor da propriedade identificacaoNfse.
     * 
     * @return
     *     possible object is
     *     {@link TcIdentificacaoNfse }
     *     
     */
    public TcIdentificacaoNfse getIdentificacaoNfse() {
        return identificacaoNfse;
    }

    /**
     * Define o valor da propriedade identificacaoNfse.
     * 
     * @param value
     *     allowed object is
     *     {@link TcIdentificacaoNfse }
     *     
     */
    public void setIdentificacaoNfse(TcIdentificacaoNfse value) {
        this.identificacaoNfse = value;
    }

    /**
     * Obtém o valor da propriedade codigoCancelamento.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodigoCancelamento() {
        return codigoCancelamento;
    }

    /**
     * Define o valor da propriedade codigoCancelamento.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodigoCancelamento(String value) {
        this.codigoCancelamento = value;
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
