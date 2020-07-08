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
package com.t2tierp.nfse.java;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.openswing.swing.message.receive.java.ValueObjectImpl;


@Entity
@Table(name = "NFSE_DETALHE")
public class NfseDetalheVO extends ValueObjectImpl implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "VALOR_SERVICOS")
    private BigDecimal valorServicos;
    @Column(name = "VALOR_DEDUCOES")
    private BigDecimal valorDeducoes;
    @Column(name = "VALOR_PIS")
    private BigDecimal valorPis;
    @Column(name = "VALOR_COFINS")
    private BigDecimal valorCofins;
    @Column(name = "VALOR_INSS")
    private BigDecimal valorInss;
    @Column(name = "VALOR_IR")
    private BigDecimal valorIr;
    @Column(name = "VALOR_CSLL")
    private BigDecimal valorCsll;
    @Column(name = "CODIGO_CNAE")
    private String codigoCnae;
    @Column(name = "CODIGO_TRIBUTACAO_MUNICIPIO")
    private String codigoTributacaoMunicipio;
    @Column(name = "VALOR_BASE_CALCULO")
    private BigDecimal valorBaseCalculo;
    @Column(name = "ALIQUOTA")
    private BigDecimal aliquota;
    @Column(name = "VALOR_ISS")
    private BigDecimal valorIss;
    @Column(name = "VALOR_LIQUIDO")
    private BigDecimal valorLiquido;
    @Column(name = "OUTRAS_RETENCOES")
    private BigDecimal outrasRetencoes;
    @Column(name = "VALOR_CREDITO")
    private BigDecimal valorCredito;
    @Column(name = "ISS_RETIDO")
    private String issRetido;
    @Column(name = "VALOR_ISS_RETIDO")
    private BigDecimal valorIssRetido;
    @Column(name = "VALOR_DESCONTO_CONDICIONADO")
    private BigDecimal valorDescontoCondicionado;
    @Column(name = "VALOR_DESCONTO_INCONDICIONADO")
    private BigDecimal valorDescontoIncondicionado;
    @Column(name = "DISCRIMINACAO")
    private String discriminacao;
    @Column(name = "MUNICIPIO_PRESTACAO")
    private Integer municipioPrestacao;
    @JoinColumn(name = "ID_NFSE_CABECALHO", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private NfseCabecalhoVO nfseCabecalho;
    @JoinColumn(name = "ID_NFSE_LISTA_SERVICO", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private NfseListaServicoVO nfseListaServico;

    public NfseDetalheVO() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigDecimal getValorServicos() {
        return valorServicos;
    }

    public void setValorServicos(BigDecimal valorServicos) {
        this.valorServicos = valorServicos;
    }

    public BigDecimal getValorDeducoes() {
        return valorDeducoes;
    }

    public void setValorDeducoes(BigDecimal valorDeducoes) {
        this.valorDeducoes = valorDeducoes;
    }

    public BigDecimal getValorPis() {
        return valorPis;
    }

    public void setValorPis(BigDecimal valorPis) {
        this.valorPis = valorPis;
    }

    public BigDecimal getValorCofins() {
        return valorCofins;
    }

    public void setValorCofins(BigDecimal valorCofins) {
        this.valorCofins = valorCofins;
    }

    public BigDecimal getValorInss() {
        return valorInss;
    }

    public void setValorInss(BigDecimal valorInss) {
        this.valorInss = valorInss;
    }

    public BigDecimal getValorIr() {
        return valorIr;
    }

    public void setValorIr(BigDecimal valorIr) {
        this.valorIr = valorIr;
    }

    public BigDecimal getValorCsll() {
        return valorCsll;
    }

    public void setValorCsll(BigDecimal valorCsll) {
        this.valorCsll = valorCsll;
    }

    public String getCodigoCnae() {
        return codigoCnae;
    }

    public void setCodigoCnae(String codigoCnae) {
        this.codigoCnae = codigoCnae;
    }

    public String getCodigoTributacaoMunicipio() {
        return codigoTributacaoMunicipio;
    }

    public void setCodigoTributacaoMunicipio(String codigoTributacaoMunicipio) {
        this.codigoTributacaoMunicipio = codigoTributacaoMunicipio;
    }

    public BigDecimal getValorBaseCalculo() {
        return valorBaseCalculo;
    }

    public void setValorBaseCalculo(BigDecimal valorBaseCalculo) {
        this.valorBaseCalculo = valorBaseCalculo;
    }

    public BigDecimal getAliquota() {
        return aliquota;
    }

    public void setAliquota(BigDecimal aliquota) {
        this.aliquota = aliquota;
    }

    public BigDecimal getValorIss() {
        return valorIss;
    }

    public void setValorIss(BigDecimal valorIss) {
        this.valorIss = valorIss;
    }

    public BigDecimal getValorLiquido() {
        return valorLiquido;
    }

    public void setValorLiquido(BigDecimal valorLiquido) {
        this.valorLiquido = valorLiquido;
    }

    public BigDecimal getOutrasRetencoes() {
        return outrasRetencoes;
    }

    public void setOutrasRetencoes(BigDecimal outrasRetencoes) {
        this.outrasRetencoes = outrasRetencoes;
    }

    public BigDecimal getValorCredito() {
        return valorCredito;
    }

    public void setValorCredito(BigDecimal valorCredito) {
        this.valorCredito = valorCredito;
    }

    public String getIssRetido() {
        return issRetido;
    }

    public void setIssRetido(String issRetido) {
        this.issRetido = issRetido;
    }

    public BigDecimal getValorIssRetido() {
        return valorIssRetido;
    }

    public void setValorIssRetido(BigDecimal valorIssRetido) {
        this.valorIssRetido = valorIssRetido;
    }

    public BigDecimal getValorDescontoCondicionado() {
        return valorDescontoCondicionado;
    }

    public void setValorDescontoCondicionado(BigDecimal valorDescontoCondicionado) {
        this.valorDescontoCondicionado = valorDescontoCondicionado;
    }

    public BigDecimal getValorDescontoIncondicionado() {
        return valorDescontoIncondicionado;
    }

    public void setValorDescontoIncondicionado(BigDecimal valorDescontoIncondicionado) {
        this.valorDescontoIncondicionado = valorDescontoIncondicionado;
    }

    public String getDiscriminacao() {
        return discriminacao;
    }

    public void setDiscriminacao(String discriminacao) {
        this.discriminacao = discriminacao;
    }

    public Integer getMunicipioPrestacao() {
        return municipioPrestacao;
    }

    public void setMunicipioPrestacao(Integer municipioPrestacao) {
        this.municipioPrestacao = municipioPrestacao;
    }

    public NfseCabecalhoVO getNfseCabecalho() {
        return nfseCabecalho;
    }

    public void setNfseCabecalho(NfseCabecalhoVO nfseCabecalho) {
        this.nfseCabecalho = nfseCabecalho;
    }

    public NfseListaServicoVO getNfseListaServico() {
        return nfseListaServico;
    }

    public void setNfseListaServico(NfseListaServicoVO nfseListaServico) {
        this.nfseListaServico = nfseListaServico;
    }


    @Override
    public String toString() {
        return "com.t2tierp.nfse.java.NfseDetalheVO[id=" + id + "]";
    }

}
