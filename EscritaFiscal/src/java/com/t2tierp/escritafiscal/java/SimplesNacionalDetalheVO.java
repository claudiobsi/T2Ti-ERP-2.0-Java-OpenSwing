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
package com.t2tierp.escritafiscal.java;

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
@Table(name = "SIMPLES_NACIONAL_DETALHE")
public class SimplesNacionalDetalheVO extends ValueObjectImpl implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "FAIXA")
    private Integer faixa;
    @Column(name = "VALOR_INICIAL")
    private BigDecimal valorInicial;
    @Column(name = "VALOR_FINAL")
    private BigDecimal valorFinal;
    @Column(name = "ALIQUOTA")
    private BigDecimal aliquota;
    @Column(name = "IRPJ")
    private BigDecimal irpj;
    @Column(name = "CSLL")
    private BigDecimal csll;
    @Column(name = "COFINS")
    private BigDecimal cofins;
    @Column(name = "PIS_PASEP")
    private BigDecimal pisPasep;
    @Column(name = "CPP")
    private BigDecimal cpp;
    @Column(name = "ICMS")
    private BigDecimal icms;
    @Column(name = "IPI")
    private BigDecimal ipi;
    @Column(name = "ISS")
    private BigDecimal iss;
    @JoinColumn(name = "ID_SIMPLES_NACIONAL_CABECALHO", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private SimplesNacionalCabecalhoVO simplesNacionalCabecalho;

    public SimplesNacionalDetalheVO() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getFaixa() {
        return faixa;
    }

    public void setFaixa(Integer faixa) {
        this.faixa = faixa;
    }

    public BigDecimal getValorInicial() {
        return valorInicial;
    }

    public void setValorInicial(BigDecimal valorInicial) {
        this.valorInicial = valorInicial;
    }

    public BigDecimal getValorFinal() {
        return valorFinal;
    }

    public void setValorFinal(BigDecimal valorFinal) {
        this.valorFinal = valorFinal;
    }

    public BigDecimal getAliquota() {
        return aliquota;
    }

    public void setAliquota(BigDecimal aliquota) {
        this.aliquota = aliquota;
    }

    public BigDecimal getIrpj() {
        return irpj;
    }

    public void setIrpj(BigDecimal irpj) {
        this.irpj = irpj;
    }

    public BigDecimal getCsll() {
        return csll;
    }

    public void setCsll(BigDecimal csll) {
        this.csll = csll;
    }

    public BigDecimal getCofins() {
        return cofins;
    }

    public void setCofins(BigDecimal cofins) {
        this.cofins = cofins;
    }

    public BigDecimal getPisPasep() {
        return pisPasep;
    }

    public void setPisPasep(BigDecimal pisPasep) {
        this.pisPasep = pisPasep;
    }

    public BigDecimal getCpp() {
        return cpp;
    }

    public void setCpp(BigDecimal cpp) {
        this.cpp = cpp;
    }

    public BigDecimal getIcms() {
        return icms;
    }

    public void setIcms(BigDecimal icms) {
        this.icms = icms;
    }

    public BigDecimal getIpi() {
        return ipi;
    }

    public void setIpi(BigDecimal ipi) {
        this.ipi = ipi;
    }

    public BigDecimal getIss() {
        return iss;
    }

    public void setIss(BigDecimal iss) {
        this.iss = iss;
    }

    public SimplesNacionalCabecalhoVO getSimplesNacionalCabecalho() {
        return simplesNacionalCabecalho;
    }

    public void setSimplesNacionalCabecalho(SimplesNacionalCabecalhoVO simplesNacionalCabecalho) {
        this.simplesNacionalCabecalho = simplesNacionalCabecalho;
    }


    @Override
    public String toString() {
        return "com.t2tierp.escritafiscal.java.SimplesNacionalDetalheVO[id=" + id + "]";
    }

}
