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


/**
 * <p>Classe Java de tcDadosIntermediario complex type.
 * 
 * <p>O seguinte fragmento do esquema especifica o conteúdo esperado contido dentro desta classe.
 * 
 * <pre>
 * &lt;complexType name="tcDadosIntermediario">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="IdentificacaoIntermediario" type="{http://www.abrasf.org.br/nfse.xsd}tcIdentificacaoIntermediario"/>
 *         &lt;element name="RazaoSocial" type="{http://www.abrasf.org.br/nfse.xsd}tsRazaoSocial"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "tcDadosIntermediario", propOrder = {
    "identificacaoIntermediario",
    "razaoSocial"
})
public class TcDadosIntermediario {

    @XmlElement(name = "IdentificacaoIntermediario", required = true)
    protected TcIdentificacaoIntermediario identificacaoIntermediario;
    @XmlElement(name = "RazaoSocial", required = true)
    protected String razaoSocial;

    /**
     * Obtém o valor da propriedade identificacaoIntermediario.
     * 
     * @return
     *     possible object is
     *     {@link TcIdentificacaoIntermediario }
     *     
     */
    public TcIdentificacaoIntermediario getIdentificacaoIntermediario() {
        return identificacaoIntermediario;
    }

    /**
     * Define o valor da propriedade identificacaoIntermediario.
     * 
     * @param value
     *     allowed object is
     *     {@link TcIdentificacaoIntermediario }
     *     
     */
    public void setIdentificacaoIntermediario(TcIdentificacaoIntermediario value) {
        this.identificacaoIntermediario = value;
    }

    /**
     * Obtém o valor da propriedade razaoSocial.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRazaoSocial() {
        return razaoSocial;
    }

    /**
     * Define o valor da propriedade razaoSocial.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRazaoSocial(String value) {
        this.razaoSocial = value;
    }

}
