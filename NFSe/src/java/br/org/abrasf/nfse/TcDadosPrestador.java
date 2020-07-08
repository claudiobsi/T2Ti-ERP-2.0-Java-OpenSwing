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
 * <p>Classe Java de tcDadosPrestador complex type.
 * 
 * <p>O seguinte fragmento do esquema especifica o conteúdo esperado contido dentro desta classe.
 * 
 * <pre>
 * &lt;complexType name="tcDadosPrestador">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="IdentificacaoPrestador" type="{http://www.abrasf.org.br/nfse.xsd}tcIdentificacaoPrestador"/>
 *         &lt;element name="RazaoSocial" type="{http://www.abrasf.org.br/nfse.xsd}tsRazaoSocial"/>
 *         &lt;element name="NomeFantasia" type="{http://www.abrasf.org.br/nfse.xsd}tsNomeFantasia" minOccurs="0"/>
 *         &lt;element name="Endereco" type="{http://www.abrasf.org.br/nfse.xsd}tcEndereco"/>
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
@XmlType(name = "tcDadosPrestador", propOrder = {
    "identificacaoPrestador",
    "razaoSocial",
    "nomeFantasia",
    "endereco",
    "contato"
})
public class TcDadosPrestador {

    @XmlElement(name = "IdentificacaoPrestador", required = true)
    protected TcIdentificacaoPrestador identificacaoPrestador;
    @XmlElement(name = "RazaoSocial", required = true)
    protected String razaoSocial;
    @XmlElement(name = "NomeFantasia")
    protected String nomeFantasia;
    @XmlElement(name = "Endereco", required = true)
    protected TcEndereco endereco;
    @XmlElement(name = "Contato")
    protected TcContato contato;

    /**
     * Obtém o valor da propriedade identificacaoPrestador.
     * 
     * @return
     *     possible object is
     *     {@link TcIdentificacaoPrestador }
     *     
     */
    public TcIdentificacaoPrestador getIdentificacaoPrestador() {
        return identificacaoPrestador;
    }

    /**
     * Define o valor da propriedade identificacaoPrestador.
     * 
     * @param value
     *     allowed object is
     *     {@link TcIdentificacaoPrestador }
     *     
     */
    public void setIdentificacaoPrestador(TcIdentificacaoPrestador value) {
        this.identificacaoPrestador = value;
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
     * Obtém o valor da propriedade nomeFantasia.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNomeFantasia() {
        return nomeFantasia;
    }

    /**
     * Define o valor da propriedade nomeFantasia.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNomeFantasia(String value) {
        this.nomeFantasia = value;
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
