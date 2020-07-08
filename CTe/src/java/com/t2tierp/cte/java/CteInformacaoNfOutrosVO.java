/*
* The MIT License
* 
* Copyright: Copyright (C) 2014 T2Ti.COM
* 
* Permission is hereby granted, free of charge, to any person obtaining a copy
* of this software and associated documentation files (the "Software"), to deal
* in the Software without restriction, including without limitation the rights
* to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
* copies of the Software, and to permit persons to whom the Software is
* furnished to do so, subject to the following conditions:
* 
* The above copyright notice and this permission notice shall be included in
* all copies or substantial portions of the Software.
* 
* THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
* IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
* FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
* AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
* LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
* OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
* THE SOFTWARE.
* 
* The author may be contacted at: t2ti.com@gmail.com
*
* @author Claudio de Barros (T2Ti.com)
* @version 2.0
*/
package com.t2tierp.cte.java;

import com.t2tierp.nfe.java.NfeCabecalhoVO;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import org.openswing.swing.message.receive.java.ValueObjectImpl;


@Entity
@Table(name = "CTE_INFORMACAO_NF_OUTROS")
public class CteInformacaoNfOutrosVO extends ValueObjectImpl implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "NUMERO_ROMANEIO")
    private String numeroRomaneio;
    @Column(name = "NUMERO_PEDIDO")
    private String numeroPedido;
    @Column(name = "CHAVE_ACESSO_NFE")
    private String chaveAcessoNfe;
    @Column(name = "CODIGO_MODELO")
    private String codigoModelo;
    @Column(name = "SERIE")
    private String serie;
    @Column(name = "NUMERO")
    private String numero;
    @Temporal(TemporalType.DATE)
    @Column(name = "DATA_EMISSAO")
    private Date dataEmissao;
    @Column(name = "UF_EMITENTE")
    private Integer ufEmitente;
    @Column(name = "BASE_CALCULO_ICMS")
    private BigDecimal baseCalculoIcms;
    @Column(name = "VALOR_ICMS")
    private BigDecimal valorIcms;
    @Column(name = "BASE_CALCULO_ICMS_ST")
    private BigDecimal baseCalculoIcmsSt;
    @Column(name = "VALOR_ICMS_ST")
    private BigDecimal valorIcmsSt;
    @Column(name = "VALOR_TOTAL_PRODUTOS")
    private BigDecimal valorTotalProdutos;
    @Column(name = "VALOR_TOTAL")
    private BigDecimal valorTotal;
    @Column(name = "CFOP_PREDOMINANTE")
    private Integer cfopPredominante;
    @Column(name = "PESO_TOTAL_KG")
    private BigDecimal pesoTotalKg;
    @Column(name = "PIN_SUFRAMA")
    private Integer pinSuframa;
    @Temporal(TemporalType.DATE)
    @Column(name = "DATA_PREVISTA_ENTREGA")
    private Date dataPrevistaEntrega;
    @Column(name = "OUTRO_TIPO_DOC_ORIG")
    private String outroTipoDocOrig;
    @Column(name = "OUTRO_DESCRICAO")
    private String outroDescricao;
    @Column(name = "OUTRO_VALOR_DOCUMENTO")
    private BigDecimal outroValorDocumento;
    @JoinColumn(name = "ID_CTE_CABECALHO", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private CteCabecalhoVO cteCabecalho;
    @Transient
    private NfeCabecalhoVO nfeCabecalho;
    
    public CteInformacaoNfOutrosVO() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNumeroRomaneio() {
        return numeroRomaneio;
    }

    public void setNumeroRomaneio(String numeroRomaneio) {
        this.numeroRomaneio = numeroRomaneio;
    }

    public String getNumeroPedido() {
        return numeroPedido;
    }

    public void setNumeroPedido(String numeroPedido) {
        this.numeroPedido = numeroPedido;
    }

    public String getChaveAcessoNfe() {
        return chaveAcessoNfe;
    }

    public void setChaveAcessoNfe(String chaveAcessoNfe) {
        this.chaveAcessoNfe = chaveAcessoNfe;
    }

    public String getCodigoModelo() {
        return codigoModelo;
    }

    public void setCodigoModelo(String codigoModelo) {
        this.codigoModelo = codigoModelo;
    }

    public String getSerie() {
        return serie;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public Date getDataEmissao() {
        return dataEmissao;
    }

    public void setDataEmissao(Date dataEmissao) {
        this.dataEmissao = dataEmissao;
    }

    public Integer getUfEmitente() {
        return ufEmitente;
    }

    public void setUfEmitente(Integer ufEmitente) {
        this.ufEmitente = ufEmitente;
    }

    public BigDecimal getBaseCalculoIcms() {
        return baseCalculoIcms;
    }

    public void setBaseCalculoIcms(BigDecimal baseCalculoIcms) {
        this.baseCalculoIcms = baseCalculoIcms;
    }

    public BigDecimal getValorIcms() {
        return valorIcms;
    }

    public void setValorIcms(BigDecimal valorIcms) {
        this.valorIcms = valorIcms;
    }

    public BigDecimal getBaseCalculoIcmsSt() {
        return baseCalculoIcmsSt;
    }

    public void setBaseCalculoIcmsSt(BigDecimal baseCalculoIcmsSt) {
        this.baseCalculoIcmsSt = baseCalculoIcmsSt;
    }

    public BigDecimal getValorIcmsSt() {
        return valorIcmsSt;
    }

    public void setValorIcmsSt(BigDecimal valorIcmsSt) {
        this.valorIcmsSt = valorIcmsSt;
    }

    public BigDecimal getValorTotalProdutos() {
        return valorTotalProdutos;
    }

    public void setValorTotalProdutos(BigDecimal valorTotalProdutos) {
        this.valorTotalProdutos = valorTotalProdutos;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }

    public Integer getCfopPredominante() {
        return cfopPredominante;
    }

    public void setCfopPredominante(Integer cfopPredominante) {
        this.cfopPredominante = cfopPredominante;
    }

    public BigDecimal getPesoTotalKg() {
        return pesoTotalKg;
    }

    public void setPesoTotalKg(BigDecimal pesoTotalKg) {
        this.pesoTotalKg = pesoTotalKg;
    }

    public Integer getPinSuframa() {
        return pinSuframa;
    }

    public void setPinSuframa(Integer pinSuframa) {
        this.pinSuframa = pinSuframa;
    }

    public Date getDataPrevistaEntrega() {
        return dataPrevistaEntrega;
    }

    public void setDataPrevistaEntrega(Date dataPrevistaEntrega) {
        this.dataPrevistaEntrega = dataPrevistaEntrega;
    }

    public String getOutroTipoDocOrig() {
        return outroTipoDocOrig;
    }

    public void setOutroTipoDocOrig(String outroTipoDocOrig) {
        this.outroTipoDocOrig = outroTipoDocOrig;
    }

    public String getOutroDescricao() {
        return outroDescricao;
    }

    public void setOutroDescricao(String outroDescricao) {
        this.outroDescricao = outroDescricao;
    }

    public BigDecimal getOutroValorDocumento() {
        return outroValorDocumento;
    }

    public void setOutroValorDocumento(BigDecimal outroValorDocumento) {
        this.outroValorDocumento = outroValorDocumento;
    }

    public CteCabecalhoVO getCteCabecalho() {
        return cteCabecalho;
    }

    public void setCteCabecalho(CteCabecalhoVO cteCabecalho) {
        this.cteCabecalho = cteCabecalho;
    }


    @Override
    public String toString() {
        return "com.t2tierp.cte.java.CteInformacaoNfOutrosVO[id=" + id + "]";
    }

    public NfeCabecalhoVO getNfeCabecalho() {
        return nfeCabecalho;
    }

    public void setNfeCabecalho(NfeCabecalhoVO nfeCabecalho) {
        this.nfeCabecalho = nfeCabecalho;
    }

}
