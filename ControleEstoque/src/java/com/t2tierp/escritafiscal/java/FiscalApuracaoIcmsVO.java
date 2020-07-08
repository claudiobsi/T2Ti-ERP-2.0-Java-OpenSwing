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

import com.t2tierp.cadastros.java.EmpresaVO;
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
@Table(name = "FISCAL_APURACAO_ICMS")
public class FiscalApuracaoIcmsVO extends ValueObjectImpl implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "COMPETENCIA")
    private String competencia;
    @Column(name = "VALOR_TOTAL_DEBITO")
    private BigDecimal valorTotalDebito;
    @Column(name = "VALOR_AJUSTE_DEBITO")
    private BigDecimal valorAjusteDebito;
    @Column(name = "VALOR_TOTAL_AJUSTE_DEBITO")
    private BigDecimal valorTotalAjusteDebito;
    @Column(name = "VALOR_ESTORNO_CREDITO")
    private BigDecimal valorEstornoCredito;
    @Column(name = "VALOR_TOTAL_CREDITO")
    private BigDecimal valorTotalCredito;
    @Column(name = "VALOR_AJUSTE_CREDITO")
    private BigDecimal valorAjusteCredito;
    @Column(name = "VALOR_TOTAL_AJUSTE_CREDITO")
    private BigDecimal valorTotalAjusteCredito;
    @Column(name = "VALOR_ESTORNO_DEBITO")
    private BigDecimal valorEstornoDebito;
    @Column(name = "VALOR_SALDO_CREDOR_ANTERIOR")
    private BigDecimal valorSaldoCredorAnterior;
    @Column(name = "VALOR_SALDO_APURADO")
    private BigDecimal valorSaldoApurado;
    @Column(name = "VALOR_TOTAL_DEDUCAO")
    private BigDecimal valorTotalDeducao;
    @Column(name = "VALOR_ICMS_RECOLHER")
    private BigDecimal valorIcmsRecolher;
    @Column(name = "VALOR_SALDO_CREDOR_TRANSP")
    private BigDecimal valorSaldoCredorTransp;
    @Column(name = "VALOR_DEBITO_ESPECIAL")
    private BigDecimal valorDebitoEspecial;
    @JoinColumn(name = "ID_EMPRESA", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private EmpresaVO empresa;

    public FiscalApuracaoIcmsVO() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCompetencia() {
        return competencia;
    }

    public void setCompetencia(String competencia) {
        this.competencia = competencia;
    }

    public BigDecimal getValorTotalDebito() {
        return valorTotalDebito;
    }

    public void setValorTotalDebito(BigDecimal valorTotalDebito) {
        this.valorTotalDebito = valorTotalDebito;
    }

    public BigDecimal getValorAjusteDebito() {
        return valorAjusteDebito;
    }

    public void setValorAjusteDebito(BigDecimal valorAjusteDebito) {
        this.valorAjusteDebito = valorAjusteDebito;
    }

    public BigDecimal getValorTotalAjusteDebito() {
        return valorTotalAjusteDebito;
    }

    public void setValorTotalAjusteDebito(BigDecimal valorTotalAjusteDebito) {
        this.valorTotalAjusteDebito = valorTotalAjusteDebito;
    }

    public BigDecimal getValorEstornoCredito() {
        return valorEstornoCredito;
    }

    public void setValorEstornoCredito(BigDecimal valorEstornoCredito) {
        this.valorEstornoCredito = valorEstornoCredito;
    }

    public BigDecimal getValorTotalCredito() {
        return valorTotalCredito;
    }

    public void setValorTotalCredito(BigDecimal valorTotalCredito) {
        this.valorTotalCredito = valorTotalCredito;
    }

    public BigDecimal getValorAjusteCredito() {
        return valorAjusteCredito;
    }

    public void setValorAjusteCredito(BigDecimal valorAjusteCredito) {
        this.valorAjusteCredito = valorAjusteCredito;
    }

    public BigDecimal getValorTotalAjusteCredito() {
        return valorTotalAjusteCredito;
    }

    public void setValorTotalAjusteCredito(BigDecimal valorTotalAjusteCredito) {
        this.valorTotalAjusteCredito = valorTotalAjusteCredito;
    }

    public BigDecimal getValorEstornoDebito() {
        return valorEstornoDebito;
    }

    public void setValorEstornoDebito(BigDecimal valorEstornoDebito) {
        this.valorEstornoDebito = valorEstornoDebito;
    }

    public BigDecimal getValorSaldoCredorAnterior() {
        return valorSaldoCredorAnterior;
    }

    public void setValorSaldoCredorAnterior(BigDecimal valorSaldoCredorAnterior) {
        this.valorSaldoCredorAnterior = valorSaldoCredorAnterior;
    }

    public BigDecimal getValorSaldoApurado() {
        return valorSaldoApurado;
    }

    public void setValorSaldoApurado(BigDecimal valorSaldoApurado) {
        this.valorSaldoApurado = valorSaldoApurado;
    }

    public BigDecimal getValorTotalDeducao() {
        return valorTotalDeducao;
    }

    public void setValorTotalDeducao(BigDecimal valorTotalDeducao) {
        this.valorTotalDeducao = valorTotalDeducao;
    }

    public BigDecimal getValorIcmsRecolher() {
        return valorIcmsRecolher;
    }

    public void setValorIcmsRecolher(BigDecimal valorIcmsRecolher) {
        this.valorIcmsRecolher = valorIcmsRecolher;
    }

    public BigDecimal getValorSaldoCredorTransp() {
        return valorSaldoCredorTransp;
    }

    public void setValorSaldoCredorTransp(BigDecimal valorSaldoCredorTransp) {
        this.valorSaldoCredorTransp = valorSaldoCredorTransp;
    }

    public BigDecimal getValorDebitoEspecial() {
        return valorDebitoEspecial;
    }

    public void setValorDebitoEspecial(BigDecimal valorDebitoEspecial) {
        this.valorDebitoEspecial = valorDebitoEspecial;
    }

    public EmpresaVO getEmpresa() {
        return empresa;
    }

    public void setEmpresa(EmpresaVO empresa) {
        this.empresa = empresa;
    }


    @Override
    public String toString() {
        return "com.t2tierp.escritafiscal.java.FiscalApuracaoIcmsVO[id=" + id + "]";
    }

}
