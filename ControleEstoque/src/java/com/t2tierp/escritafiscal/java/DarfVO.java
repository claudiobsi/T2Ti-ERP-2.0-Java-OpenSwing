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
import java.util.Date;
import org.openswing.swing.message.receive.java.ValueObjectImpl;

public class DarfVO extends ValueObjectImpl implements Serializable {

    private String razaoSocial;
    private String telefone;
    private String periodoApuracao;
    private String cpfCnpj;
    private String codigoReceita;
    private String numeroReferencia;
    private Date dataVencimento;
    private BigDecimal valorPrincipal;
    private BigDecimal valorMulta;
    private BigDecimal valorJuros;
    private BigDecimal valorTotal;

    /**
     * @return the razaoSocial
     */
    public String getRazaoSocial() {
        return razaoSocial;
    }

    /**
     * @param razaoSocial the razaoSocial to set
     */
    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    /**
     * @return the telefone
     */
    public String getTelefone() {
        return telefone;
    }

    /**
     * @param telefone the telefone to set
     */
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    /**
     * @return the periodoApuracao
     */
    public String getPeriodoApuracao() {
        return periodoApuracao;
    }

    /**
     * @param periodoApuracao the periodoApuracao to set
     */
    public void setPeriodoApuracao(String periodoApuracao) {
        this.periodoApuracao = periodoApuracao;
    }

    /**
     * @return the cpfCnpj
     */
    public String getCpfCnpj() {
        return cpfCnpj;
    }

    /**
     * @param cpfCnpj the cpfCnpj to set
     */
    public void setCpfCnpj(String cpfCnpj) {
        this.cpfCnpj = cpfCnpj;
    }

    /**
     * @return the codigoReceita
     */
    public String getCodigoReceita() {
        return codigoReceita;
    }

    /**
     * @param codigoReceita the codigoReceita to set
     */
    public void setCodigoReceita(String codigoReceita) {
        this.codigoReceita = codigoReceita;
    }

    /**
     * @return the dataVencimento
     */
    public Date getDataVencimento() {
        return dataVencimento;
    }

    /**
     * @param dataVencimento the dataVencimento to set
     */
    public void setDataVencimento(Date dataVencimento) {
        this.dataVencimento = dataVencimento;
    }

    /**
     * @return the valorPrincipal
     */
    public BigDecimal getValorPrincipal() {
        return valorPrincipal;
    }

    /**
     * @param valorPrincipal the valorPrincipal to set
     */
    public void setValorPrincipal(BigDecimal valorPrincipal) {
        this.valorPrincipal = valorPrincipal;
    }

    /**
     * @return the valorMulta
     */
    public BigDecimal getValorMulta() {
        return valorMulta;
    }

    /**
     * @param valorMulta the valorMulta to set
     */
    public void setValorMulta(BigDecimal valorMulta) {
        this.valorMulta = valorMulta;
    }

    /**
     * @return the valorJuros
     */
    public BigDecimal getValorJuros() {
        return valorJuros;
    }

    /**
     * @param valorJuros the valorJuros to set
     */
    public void setValorJuros(BigDecimal valorJuros) {
        this.valorJuros = valorJuros;
    }

    /**
     * @return the valorTotal
     */
    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    /**
     * @param valorTotal the valorTotal to set
     */
    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }

    /**
     * @return the numeroReferencia
     */
    public String getNumeroReferencia() {
        return numeroReferencia;
    }

    /**
     * @param numeroReferencia the numeroReferencia to set
     */
    public void setNumeroReferencia(String numeroReferencia) {
        this.numeroReferencia = numeroReferencia;
    }

}
