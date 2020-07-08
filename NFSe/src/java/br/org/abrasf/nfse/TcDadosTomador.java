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
 * <p>Classe Java de tcDadosTomador complex type.
 * 
 * <p>O seguinte fragmento do esquema especifica o conteúdo esperado contido dentro desta classe.
 * 
 * <pre>
 * &lt;complexType name="tcDadosTomador">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="IdentificacaoTomador" type="{http://www.abrasf.org.br/nfse.xsd}tcIdentificacaoTomador" minOccurs="0"/>
 *         &lt;element name="RazaoSocial" type="{http://www.abrasf.org.br/nfse.xsd}tsRazaoSocial" minOccurs="0"/>
 *         &lt;element name="Endereco" type="{http://www.abrasf.org.br/nfse.xsd}tcEndereco" minOccurs="0"/>
 *         &lt;element name="Contato" type="{http://www.abrasf.org.br/nfse.xsd}tcContato" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "tcDadosTomador", propOrder = {
    "identificacaoTomador",
    "razaoSocial",
    "endereco",
    "contato"
})
public class TcDadosTomador {

    @XmlElement(name = "IdentificacaoTomador")
    protected TcIdentificacaoTomador identificacaoTomador;
    @XmlElement(name = "RazaoSocial")
    protected String razaoSocial;
    @XmlElement(name = "Endereco")
    protected TcEndereco endereco;
    @XmlElement(name = "Contato")
    protected TcContato contato;

    /**
     * Obtém o valor da propriedade identificacaoTomador.
     * 
     * @return
     *     possible object is
     *     {@link TcIdentificacaoTomador }
     *     
     */
    public TcIdentificacaoTomador getIdentificacaoTomador() {
        return identificacaoTomador;
    }

    /**
     * Define o valor da propriedade identificacaoTomador.
     * 
     * @param value
     *     allowed object is
     *     {@link TcIdentificacaoTomador }
     *     
     */
    public void setIdentificacaoTomador(TcIdentificacaoTomador value) {
        this.identificacaoTomador = value;
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

    /**
     * Obtém o valor da propriedade endereco.
     * 
     * @return
     *     possible object is
     *     {@link TcEndereco }
     *     
     */
    public TcEndereco getEndereco() {
        return endereco;
    }

    /**
     * Define o valor da propriedade endereco.
     * 
     * @param value
     *     allowed object is
     *     {@link TcEndereco }
     *     
     */
    public void setEndereco(TcEndereco value) {
        this.endereco = value;
    }

    /**
     * Obtém o valor da propriedade contato.
     * 
     * @return
     *     possible object is
     *     {@link TcContato }
     *     
     */
    public TcContato getContato() {
        return contato;
    }

    /**
     * Define o valor da propriedade contato.
     * 
     * @param value
     *     allowed object is
     *     {@link TcContato }
     *     
     */
    public void setContato(TcContato value) {
        this.contato = value;
    }

}
