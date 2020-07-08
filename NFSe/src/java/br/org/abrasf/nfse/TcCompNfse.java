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
 * <p>Classe Java de tcCompNfse complex type.
 * 
 * <p>O seguinte fragmento do esquema especifica o conteúdo esperado contido dentro desta classe.
 * 
 * <pre>
 * &lt;complexType name="tcCompNfse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Nfse" type="{http://www.abrasf.org.br/nfse.xsd}tcNfse"/>
 *         &lt;element name="NfseCancelamento" type="{http://www.abrasf.org.br/nfse.xsd}tcCancelamentoNfse" minOccurs="0"/>
 *         &lt;element name="NfseSubstituicao" type="{http://www.abrasf.org.br/nfse.xsd}tcSubstituicaoNfse" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "tcCompNfse", propOrder = {
    "nfse",
    "nfseCancelamento",
    "nfseSubstituicao"
})
public class TcCompNfse {

    @XmlElement(name = "Nfse", required = true)
    protected TcNfse nfse;
    @XmlElement(name = "NfseCancelamento")
    protected TcCancelamentoNfse nfseCancelamento;
    @XmlElement(name = "NfseSubstituicao")
    protected TcSubstituicaoNfse nfseSubstituicao;

    /**
     * Obtém o valor da propriedade nfse.
     * 
     * @return
     *     possible object is
     *     {@link TcNfse }
     *     
     */
    public TcNfse getNfse() {
        return nfse;
    }

    /**
     * Define o valor da propriedade nfse.
     * 
     * @param value
     *     allowed object is
     *     {@link TcNfse }
     *     
     */
    public void setNfse(TcNfse value) {
        this.nfse = value;
    }

    /**
     * Obtém o valor da propriedade nfseCancelamento.
     * 
     * @return
     *     possible object is
     *     {@link TcCancelamentoNfse }
     *     
     */
    public TcCancelamentoNfse getNfseCancelamento() {
        return nfseCancelamento;
    }

    /**
     * Define o valor da propriedade nfseCancelamento.
     * 
     * @param value
     *     allowed object is
     *     {@link TcCancelamentoNfse }
     *     
     */
    public void setNfseCancelamento(TcCancelamentoNfse value) {
        this.nfseCancelamento = value;
    }

    /**
     * Obtém o valor da propriedade nfseSubstituicao.
     * 
     * @return
     *     possible object is
     *     {@link TcSubstituicaoNfse }
     *     
     */
    public TcSubstituicaoNfse getNfseSubstituicao() {
        return nfseSubstituicao;
    }

    /**
     * Define o valor da propriedade nfseSubstituicao.
     * 
     * @param value
     *     allowed object is
     *     {@link TcSubstituicaoNfse }
     *     
     */
    public void setNfseSubstituicao(TcSubstituicaoNfse value) {
        this.nfseSubstituicao = value;
    }

}
