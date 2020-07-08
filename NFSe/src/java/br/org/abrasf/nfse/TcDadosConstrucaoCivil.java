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
 * <p>Classe Java de tcDadosConstrucaoCivil complex type.
 * 
 * <p>O seguinte fragmento do esquema especifica o conteúdo esperado contido dentro desta classe.
 * 
 * <pre>
 * &lt;complexType name="tcDadosConstrucaoCivil">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="CodigoObra" type="{http://www.abrasf.org.br/nfse.xsd}tsCodigoObra"/>
 *         &lt;element name="Art" type="{http://www.abrasf.org.br/nfse.xsd}tsArt"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "tcDadosConstrucaoCivil", propOrder = {
    "codigoObra",
    "art"
})
public class TcDadosConstrucaoCivil {

    @XmlElement(name = "CodigoObra", required = true)
    protected String codigoObra;
    @XmlElement(name = "Art", required = true)
    protected String art;

    /**
     * Obtém o valor da propriedade codigoObra.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodigoObra() {
        return codigoObra;
    }

    /**
     * Define o valor da propriedade codigoObra.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodigoObra(String value) {
        this.codigoObra = value;
    }

    /**
     * Obtém o valor da propriedade art.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getArt() {
        return art;
    }

    /**
     * Define o valor da propriedade art.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setArt(String value) {
        this.art = value;
    }

}
