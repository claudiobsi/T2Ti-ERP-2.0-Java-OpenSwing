//
// Este arquivo foi gerado pela Arquitetura JavaTM para Implementação de Referência (JAXB) de Bind XML, v2.2.8-b130911.1802 
// Consulte <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Todas as modificações neste arquivo serão perdidas após a recompilação do esquema de origem. 
// Gerado em: 2017.01.24 às 11:39:55 AM BRST 
//


package br.org.abrasf.nfse;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java de anonymous complex type.
 * 
 * <p>O seguinte fragmento do esquema especifica o conteúdo esperado contido dentro desta classe.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;choice>
 *         &lt;element name="ListaNfse">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element ref="{http://www.abrasf.org.br/nfse.xsd}CompNfse" maxOccurs="50"/>
 *                   &lt;element name="ProximaPagina" type="{http://www.abrasf.org.br/nfse.xsd}tsPagina" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element ref="{http://www.abrasf.org.br/nfse.xsd}ListaMensagemRetorno"/>
 *       &lt;/choice>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "listaNfse",
    "listaMensagemRetorno"
})
@XmlRootElement(name = "ConsultarNfseFaixaResposta")
public class ConsultarNfseFaixaResposta {

    @XmlElement(name = "ListaNfse")
    protected ConsultarNfseFaixaResposta.ListaNfse listaNfse;
    @XmlElement(name = "ListaMensagemRetorno")
    protected ListaMensagemRetorno listaMensagemRetorno;

    /**
     * Obtém o valor da propriedade listaNfse.
     * 
     * @return
     *     possible object is
     *     {@link ConsultarNfseFaixaResposta.ListaNfse }
     *     
     */
    public ConsultarNfseFaixaResposta.ListaNfse getListaNfse() {
        return listaNfse;
    }

    /**
     * Define o valor da propriedade listaNfse.
     * 
     * @param value
     *     allowed object is
     *     {@link ConsultarNfseFaixaResposta.ListaNfse }
     *     
     */
    public void setListaNfse(ConsultarNfseFaixaResposta.ListaNfse value) {
        this.listaNfse = value;
    }

    /**
     * Obtém o valor da propriedade listaMensagemRetorno.
     * 
     * @return
     *     possible object is
     *     {@link ListaMensagemRetorno }
     *     
     */
    public ListaMensagemRetorno getListaMensagemRetorno() {
        return listaMensagemRetorno;
    }

    /**
     * Define o valor da propriedade listaMensagemRetorno.
     * 
     * @param value
     *     allowed object is
     *     {@link ListaMensagemRetorno }
     *     
     */
    public void setListaMensagemRetorno(ListaMensagemRetorno value) {
        this.listaMensagemRetorno = value;
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
     *         &lt;element ref="{http://www.abrasf.org.br/nfse.xsd}CompNfse" maxOccurs="50"/>
     *         &lt;element name="ProximaPagina" type="{http://www.abrasf.org.br/nfse.xsd}tsPagina" minOccurs="0"/>
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
        "compNfse",
        "proximaPagina"
    })
    public static class ListaNfse {

        @XmlElement(name = "CompNfse", required = true)
        protected List<TcCompNfse> compNfse;
        @XmlElement(name = "ProximaPagina")
        @XmlSchemaType(name = "nonNegativeInteger")
        protected Integer proximaPagina;

        /**
         * Gets the value of the compNfse property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the compNfse property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getCompNfse().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link TcCompNfse }
         * 
         * 
         */
        public List<TcCompNfse> getCompNfse() {
            if (compNfse == null) {
                compNfse = new ArrayList<TcCompNfse>();
            }
            return this.compNfse;
        }

        /**
         * Obtém o valor da propriedade proximaPagina.
         * 
         * @return
         *     possible object is
         *     {@link Integer }
         *     
         */
        public Integer getProximaPagina() {
            return proximaPagina;
        }

        /**
         * Define o valor da propriedade proximaPagina.
         * 
         * @param value
         *     allowed object is
         *     {@link Integer }
         *     
         */
        public void setProximaPagina(Integer value) {
            this.proximaPagina = value;
        }

    }

}
