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
 *       &lt;sequence>
 *         &lt;element name="Prestador" type="{http://www.abrasf.org.br/nfse.xsd}tcIdentificacaoPrestador"/>
 *         &lt;element name="NumeroNfse" type="{http://www.abrasf.org.br/nfse.xsd}tsNumeroNfse" minOccurs="0"/>
 *         &lt;element name="PeriodoEmissao" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="DataInicial" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *                   &lt;element name="DataFinal" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="Tomador" type="{http://www.abrasf.org.br/nfse.xsd}tcIdentificacaoTomador" minOccurs="0"/>
 *         &lt;element name="IntermediarioServico" type="{http://www.abrasf.org.br/nfse.xsd}tcIdentificacaoIntermediario" minOccurs="0"/>
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
    "prestador",
    "numeroNfse",
    "periodoEmissao",
    "tomador",
    "intermediarioServico"
})
@XmlRootElement(name = "ConsultarNfseEnvio")
public class ConsultarNfseEnvio {

    @XmlElement(name = "Prestador", required = true)
    protected TcIdentificacaoPrestador prestador;
    @XmlElement(name = "NumeroNfse")
    @XmlSchemaType(name = "nonNegativeInteger")
    protected BigInteger numeroNfse;
    @XmlElement(name = "PeriodoEmissao")
    protected ConsultarNfseEnvio.PeriodoEmissao periodoEmissao;
    @XmlElement(name = "Tomador")
    protected TcIdentificacaoTomador tomador;
    @XmlElement(name = "IntermediarioServico")
    protected TcIdentificacaoIntermediario intermediarioServico;

    /**
     * Obtém o valor da propriedade prestador.
     * 
     * @return
     *     possible object is
     *     {@link TcIdentificacaoPrestador }
     *     
     */
    public TcIdentificacaoPrestador getPrestador() {
        return prestador;
    }

    /**
     * Define o valor da propriedade prestador.
     * 
     * @param value
     *     allowed object is
     *     {@link TcIdentificacaoPrestador }
     *     
     */
    public void setPrestador(TcIdentificacaoPrestador value) {
        this.prestador = value;
    }

    /**
     * Obtém o valor da propriedade numeroNfse.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getNumeroNfse() {
        return numeroNfse;
    }

    /**
     * Define o valor da propriedade numeroNfse.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setNumeroNfse(BigInteger value) {
        this.numeroNfse = value;
    }

    /**
     * Obtém o valor da propriedade periodoEmissao.
     * 
     * @return
     *     possible object is
     *     {@link ConsultarNfseEnvio.PeriodoEmissao }
     *     
     */
    public ConsultarNfseEnvio.PeriodoEmissao getPeriodoEmissao() {
        return periodoEmissao;
    }

    /**
     * Define o valor da propriedade periodoEmissao.
     * 
     * @param value
     *     allowed object is
     *     {@link ConsultarNfseEnvio.PeriodoEmissao }
     *     
     */
    public void setPeriodoEmissao(ConsultarNfseEnvio.PeriodoEmissao value) {
        this.periodoEmissao = value;
    }

    /**
     * Obtém o valor da propriedade tomador.
     * 
     * @return
     *     possible object is
     *     {@link TcIdentificacaoTomador }
     *     
     */
    public TcIdentificacaoTomador getTomador() {
        return tomador;
    }

    /**
     * Define o valor da propriedade tomador.
     * 
     * @param value
     *     allowed object is
     *     {@link TcIdentificacaoTomador }
     *     
     */
    public void setTomador(TcIdentificacaoTomador value) {
        this.tomador = value;
    }

    /**
     * Obtém o valor da propriedade intermediarioServico.
     * 
     * @return
     *     possible object is
     *     {@link TcIdentificacaoIntermediario }
     *     
     */
    public TcIdentificacaoIntermediario getIntermediarioServico() {
        return intermediarioServico;
    }

    /**
     * Define o valor da propriedade intermediarioServico.
     * 
     * @param value
     *     allowed object is
     *     {@link TcIdentificacaoIntermediario }
     *     
     */
    public void setIntermediarioServico(TcIdentificacaoIntermediario value) {
        this.intermediarioServico = value;
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
     *         &lt;element name="DataInicial" type="{http://www.w3.org/2001/XMLSchema}date"/>
     *         &lt;element name="DataFinal" type="{http://www.w3.org/2001/XMLSchema}date"/>
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
        "dataInicial",
        "dataFinal"
    })
    public static class PeriodoEmissao {

        @XmlElement(name = "DataInicial", required = true)
        @XmlSchemaType(name = "date")
        protected Date dataInicial;
        @XmlElement(name = "DataFinal", required = true)
        @XmlSchemaType(name = "date")
        protected Date dataFinal;

        /**
         * Obtém o valor da propriedade dataInicial.
         * 
         * @return
         *     possible object is
         *     {@link Date }
         *     
         */
        public Date getDataInicial() {
            return dataInicial;
        }

        /**
         * Define o valor da propriedade dataInicial.
         * 
         * @param value
         *     allowed object is
         *     {@link Date }
         *     
         */
        public void setDataInicial(Date value) {
            this.dataInicial = value;
        }

        /**
         * Obtém o valor da propriedade dataFinal.
         * 
         * @return
         *     possible object is
         *     {@link Date }
         *     
         */
        public Date getDataFinal() {
            return dataFinal;
        }

        /**
         * Define o valor da propriedade dataFinal.
         * 
         * @param value
         *     allowed object is
         *     {@link Date }
         *     
         */
        public void setDataFinal(Date value) {
            this.dataFinal = value;
        }

    }

}
