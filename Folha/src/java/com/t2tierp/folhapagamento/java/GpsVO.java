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
package com.t2tierp.folhapagamento.java;

import java.io.Serializable;
import java.math.BigDecimal;
import org.openswing.swing.message.receive.java.ValueObjectImpl;

public class GpsVO extends ValueObjectImpl implements Serializable {

    private String razaoSocial;
    private String endereco;
    private String bairro;
    private String cidade;
    private String uf;
    private String cep;
    private String telefone;
    private Integer codigoPagamento;
    private String mesAnoCompetencia;
    private String identificador;
    private BigDecimal valorInss;
    private BigDecimal valorOutrasEntidades;
    private BigDecimal valorMultaJuros;
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
     * @return the endereco
     */
    public String getEndereco() {
        return endereco;
    }

    /**
     * @param endereco the endereco to set
     */
    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    /**
     * @return the bairro
     */
    public String getBairro() {
        return bairro;
    }

    /**
     * @param bairro the bairro to set
     */
    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    /**
     * @return the cidade
     */
    public String getCidade() {
        return cidade;
    }

    /**
     * @param cidade the cidade to set
     */
    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    /**
     * @return the uf
     */
    public String getUf() {
        return uf;
    }

    /**
     * @param uf the uf to set
     */
    public void setUf(String uf) {
        this.uf = uf;
    }

    /**
     * @return the cep
     */
    public String getCep() {
        return cep;
    }

    /**
     * @param cep the cep to set
     */
    public void setCep(String cep) {
        this.cep = cep;
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
     * @return the codigoPagamento
     */
    public Integer getCodigoPagamento() {
        return codigoPagamento;
    }

    /**
     * @param codigoPagamento the codigoPagamento to set
     */
    public void setCodigoPagamento(Integer codigoPagamento) {
        this.codigoPagamento = codigoPagamento;
    }

    /**
     * @return the mesAnoCompetencia
     */
    public String getMesAnoCompetencia() {
        return mesAnoCompetencia;
    }

    /**
     * @param mesAnoCompetencia the mesAnoCompetencia to set
     */
    public void setMesAnoCompetencia(String mesAnoCompetencia) {
        this.mesAnoCompetencia = mesAnoCompetencia;
    }

    /**
     * @return the identificador
     */
    public String getIdentificador() {
        return identificador;
    }

    /**
     * @param identificador the identificador to set
     */
    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }

    /**
     * @return the valorInss
     */
    public BigDecimal getValorInss() {
        return valorInss;
    }

    /**
     * @param valorInss the valorInss to set
     */
    public void setValorInss(BigDecimal valorInss) {
        this.valorInss = valorInss;
    }

    /**
     * @return the valorOutrasEntidades
     */
    public BigDecimal getValorOutrasEntidades() {
        return valorOutrasEntidades;
    }

    /**
     * @param valorOutrasEntidades the valorOutrasEntidades to set
     */
    public void setValorOutrasEntidades(BigDecimal valorOutrasEntidades) {
        this.valorOutrasEntidades = valorOutrasEntidades;
    }

    /**
     * @return the valorMultaJuros
     */
    public BigDecimal getValorMultaJuros() {
        return valorMultaJuros;
    }

    /**
     * @param valorMultaJuros the valorMultaJuros to set
     */
    public void setValorMultaJuros(BigDecimal valorMultaJuros) {
        this.valorMultaJuros = valorMultaJuros;
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
}
