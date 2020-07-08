//
// Este arquivo foi gerado pela Arquitetura JavaTM para Implementação de Referência (JAXB) de Bind XML, v2.2.8-b130911.1802 
// Consulte <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Todas as modificações neste arquivo serão perdidas após a recompilação do esquema de origem. 
// Gerado em: 2017.01.24 às 11:39:55 AM BRST 
//


package br.org.abrasf.nfse;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * <p>Classe Java de tcValores complex type.
 * 
 * <p>O seguinte fragmento do esquema especifica o conteúdo esperado contido dentro desta classe.
 * 
 * <pre>
 * &lt;complexType name="tcValores">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ValorServicos" type="{http://www.abrasf.org.br/nfse.xsd}tsValor"/>
 *         &lt;element name="ValorDeducoes" type="{http://www.abrasf.org.br/nfse.xsd}tsValor" minOccurs="0"/>
 *         &lt;element name="ValorPis" type="{http://www.abrasf.org.br/nfse.xsd}tsValor" minOccurs="0"/>
 *         &lt;element name="ValorCofins" type="{http://www.abrasf.org.br/nfse.xsd}tsValor" minOccurs="0"/>
 *         &lt;element name="ValorInss" type="{http://www.abrasf.org.br/nfse.xsd}tsValor" minOccurs="0"/>
 *         &lt;element name="ValorIr" type="{http://www.abrasf.org.br/nfse.xsd}tsValor" minOccurs="0"/>
 *         &lt;element name="ValorCsll" type="{http://www.abrasf.org.br/nfse.xsd}tsValor" minOccurs="0"/>
 *         &lt;element name="IssRetido" type="{http://www.abrasf.org.br/nfse.xsd}tsSimNao"/>
 *         &lt;element name="ValorIss" type="{http://www.abrasf.org.br/nfse.xsd}tsValor" minOccurs="0"/>
 *         &lt;element name="ValorIssRetido" type="{http://www.abrasf.org.br/nfse.xsd}tsValor" minOccurs="0"/>
 *         &lt;element name="OutrasRetencoes" type="{http://www.abrasf.org.br/nfse.xsd}tsValor" minOccurs="0"/>
 *         &lt;element name="BaseCalculo" type="{http://www.abrasf.org.br/nfse.xsd}tsValor" minOccurs="0"/>
 *         &lt;element name="Aliquota" type="{http://www.abrasf.org.br/nfse.xsd}tsAliquota" minOccurs="0"/>
 *         &lt;element name="ValorLiquidoNfse" type="{http://www.abrasf.org.br/nfse.xsd}tsValor" minOccurs="0"/>
 *         &lt;element name="DescontoIncondicionado" type="{http://www.abrasf.org.br/nfse.xsd}tsValor" minOccurs="0"/>
 *         &lt;element name="DescontoCondicionado" type="{http://www.abrasf.org.br/nfse.xsd}tsValor" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "tcValores", propOrder = {
    "valorServicos",
    "valorDeducoes",
    "valorPis",
    "valorCofins",
    "valorInss",
    "valorIr",
    "valorCsll",
    "issRetido",
    "valorIss",
    "valorIssRetido",
    "outrasRetencoes",
    "baseCalculo",
    "aliquota",
    "valorLiquidoNfse",
    "descontoIncondicionado",
    "descontoCondicionado"
})
public class TcValores {

    @XmlElement(name = "ValorServicos", required = true)
    @XmlJavaTypeAdapter(FormatoDecimalAdapter.class)
    protected BigDecimal valorServicos;
    @XmlElement(name = "ValorDeducoes")
    @XmlJavaTypeAdapter(FormatoDecimalAdapter.class)
    protected BigDecimal valorDeducoes;
    @XmlElement(name = "ValorPis")
    @XmlJavaTypeAdapter(FormatoDecimalAdapter.class)
    protected BigDecimal valorPis;
    @XmlElement(name = "ValorCofins")
    @XmlJavaTypeAdapter(FormatoDecimalAdapter.class)
    protected BigDecimal valorCofins;
    @XmlElement(name = "ValorInss")
    @XmlJavaTypeAdapter(FormatoDecimalAdapter.class)
    protected BigDecimal valorInss;
    @XmlElement(name = "ValorIr")
    @XmlJavaTypeAdapter(FormatoDecimalAdapter.class)
    protected BigDecimal valorIr;
    @XmlElement(name = "ValorCsll")
    @XmlJavaTypeAdapter(FormatoDecimalAdapter.class)
    protected BigDecimal valorCsll;
    @XmlElement(name = "IssRetido")
    protected byte issRetido;
    @XmlElement(name = "ValorIss")
    @XmlJavaTypeAdapter(FormatoDecimalAdapter.class)
    protected BigDecimal valorIss;
    @XmlElement(name = "ValorIssRetido")
    @XmlJavaTypeAdapter(FormatoDecimalAdapter.class)
    protected BigDecimal valorIssRetido;
    @XmlElement(name = "OutrasRetencoes")
    @XmlJavaTypeAdapter(FormatoDecimalAdapter.class)
    protected BigDecimal outrasRetencoes;
    @XmlElement(name = "BaseCalculo")
    @XmlJavaTypeAdapter(FormatoDecimalAdapter.class)
    protected BigDecimal baseCalculo;
    @XmlElement(name = "Aliquota")
    @XmlJavaTypeAdapter(FormatoDecimalAdapter.class)
    protected BigDecimal aliquota;
    @XmlElement(name = "ValorLiquidoNfse")
    @XmlJavaTypeAdapter(FormatoDecimalAdapter.class)
    protected BigDecimal valorLiquidoNfse;
    @XmlElement(name = "DescontoIncondicionado")
    @XmlJavaTypeAdapter(FormatoDecimalAdapter.class)
    protected BigDecimal descontoIncondicionado;
    @XmlElement(name = "DescontoCondicionado")
    @XmlJavaTypeAdapter(FormatoDecimalAdapter.class)
    protected BigDecimal descontoCondicionado;

    /**
     * Obtém o valor da propriedade valorServicos.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getValorServicos() {
        return valorServicos;
    }

    /**
     * Define o valor da propriedade valorServicos.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setValorServicos(BigDecimal value) {
        this.valorServicos = value;
    }

    /**
     * Obtém o valor da propriedade valorDeducoes.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getValorDeducoes() {
        return valorDeducoes;
    }

    /**
     * Define o valor da propriedade valorDeducoes.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setValorDeducoes(BigDecimal value) {
        this.valorDeducoes = value;
    }

    /**
     * Obtém o valor da propriedade valorPis.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getValorPis() {
        return valorPis;
    }

    /**
     * Define o valor da propriedade valorPis.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setValorPis(BigDecimal value) {
        this.valorPis = value;
    }

    /**
     * Obtém o valor da propriedade valorCofins.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getValorCofins() {
        return valorCofins;
    }

    /**
     * Define o valor da propriedade valorCofins.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setValorCofins(BigDecimal value) {
        this.valorCofins = value;
    }

    /**
     * Obtém o valor da propriedade valorInss.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getValorInss() {
        return valorInss;
    }

    /**
     * Define o valor da propriedade valorInss.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setValorInss(BigDecimal value) {
        this.valorInss = value;
    }

    /**
     * Obtém o valor da propriedade valorIr.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getValorIr() {
        return valorIr;
    }

    /**
     * Define o valor da propriedade valorIr.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setValorIr(BigDecimal value) {
        this.valorIr = value;
    }

    /**
     * Obtém o valor da propriedade valorCsll.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getValorCsll() {
        return valorCsll;
    }

    /**
     * Define o valor da propriedade valorCsll.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setValorCsll(BigDecimal value) {
        this.valorCsll = value;
    }

    /**
     * Obtém o valor da propriedade issRetido.
     * 
     */
    public byte getIssRetido() {
        return issRetido;
    }

    /**
     * Define o valor da propriedade issRetido.
     * 
     */
    public void setIssRetido(byte value) {
        this.issRetido = value;
    }

    /**
     * Obtém o valor da propriedade valorIss.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getValorIss() {
        return valorIss;
    }

    /**
     * Define o valor da propriedade valorIss.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setValorIss(BigDecimal value) {
        this.valorIss = value;
    }

    /**
     * Obtém o valor da propriedade valorIssRetido.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getValorIssRetido() {
        return valorIssRetido;
    }

    /**
     * Define o valor da propriedade valorIssRetido.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setValorIssRetido(BigDecimal value) {
        this.valorIssRetido = value;
    }

    /**
     * Obtém o valor da propriedade outrasRetencoes.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getOutrasRetencoes() {
        return outrasRetencoes;
    }

    /**
     * Define o valor da propriedade outrasRetencoes.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setOutrasRetencoes(BigDecimal value) {
        this.outrasRetencoes = value;
    }

    /**
     * Obtém o valor da propriedade baseCalculo.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getBaseCalculo() {
        return baseCalculo;
    }

    /**
     * Define o valor da propriedade baseCalculo.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setBaseCalculo(BigDecimal value) {
        this.baseCalculo = value;
    }

    /**
     * Obtém o valor da propriedade aliquota.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getAliquota() {
        return aliquota;
    }

    /**
     * Define o valor da propriedade aliquota.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setAliquota(BigDecimal value) {
        this.aliquota = value;
    }

    /**
     * Obtém o valor da propriedade valorLiquidoNfse.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getValorLiquidoNfse() {
        return valorLiquidoNfse;
    }

    /**
     * Define o valor da propriedade valorLiquidoNfse.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setValorLiquidoNfse(BigDecimal value) {
        this.valorLiquidoNfse = value;
    }

    /**
     * Obtém o valor da propriedade descontoIncondicionado.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getDescontoIncondicionado() {
        return descontoIncondicionado;
    }

    /**
     * Define o valor da propriedade descontoIncondicionado.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setDescontoIncondicionado(BigDecimal value) {
        this.descontoIncondicionado = value;
    }

    /**
     * Obtém o valor da propriedade descontoCondicionado.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getDescontoCondicionado() {
        return descontoCondicionado;
    }

    /**
     * Define o valor da propriedade descontoCondicionado.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setDescontoCondicionado(BigDecimal value) {
        this.descontoCondicionado = value;
    }

}
