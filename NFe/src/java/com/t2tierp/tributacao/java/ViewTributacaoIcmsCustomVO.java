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
package com.t2tierp.tributacao.java;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import org.openswing.swing.message.receive.java.ValueObjectImpl;

@Entity
@Table(name = "VIEW_TRIBUTACAO_ICMS_CUSTOM")
public class ViewTributacaoIcmsCustomVO extends ValueObjectImpl implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private int id;
    @Column(name = "DESCRICAO")
    private String descricao;
    @Column(name = "ORIGEM_MERCADORIA")
    private String origemMercadoria;
    @Column(name = "UF_DESTINO")
    private String ufDestino;
    @Column(name = "CFOP")
    private Integer cfop;
    @Column(name = "CSOSN_B")
    private String csosnB;
    @Column(name = "CST_B")
    private String cstB;
    @Column(name = "MODALIDADE_BC")
    private String modalidadeBc;
    @Column(name = "ALIQUOTA")
    private BigDecimal aliquota;
    @Column(name = "VALOR_PAUTA")
    private BigDecimal valorPauta;
    @Column(name = "VALOR_PRECO_MAXIMO")
    private BigDecimal valorPrecoMaximo;
    @Column(name = "MVA")
    private BigDecimal mva;
    @Column(name = "PORCENTO_BC")
    private BigDecimal porcentoBc;
    @Column(name = "MODALIDADE_BC_ST")
    private String modalidadeBcSt;
    @Column(name = "ALIQUOTA_INTERNA_ST")
    private BigDecimal aliquotaInternaSt;
    @Column(name = "ALIQUOTA_INTERESTADUAL_ST")
    private BigDecimal aliquotaInterestadualSt;
    @Column(name = "PORCENTO_BC_ST")
    private BigDecimal porcentoBcSt;
    @Column(name = "ALIQUOTA_ICMS_ST")
    private BigDecimal aliquotaIcmsSt;
    @Column(name = "VALOR_PAUTA_ST")
    private BigDecimal valorPautaSt;
    @Column(name = "VALOR_PRECO_MAXIMO_ST")
    private BigDecimal valorPrecoMaximoSt;

    public ViewTributacaoIcmsCustomVO() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getOrigemMercadoria() {
        return origemMercadoria;
    }

    public void setOrigemMercadoria(String origemMercadoria) {
        this.origemMercadoria = origemMercadoria;
    }

    public String getUfDestino() {
        return ufDestino;
    }

    public void setUfDestino(String ufDestino) {
        this.ufDestino = ufDestino;
    }

    public Integer getCfop() {
        return cfop;
    }

    public void setCfop(Integer cfop) {
        this.cfop = cfop;
    }

    public String getCsosnB() {
        return csosnB;
    }

    public void setCsosnB(String csosnB) {
        this.csosnB = csosnB;
    }

    public String getCstB() {
        return cstB;
    }

    public void setCstB(String cstB) {
        this.cstB = cstB;
    }

    public String getModalidadeBc() {
        return modalidadeBc;
    }

    public void setModalidadeBc(String modalidadeBc) {
        this.modalidadeBc = modalidadeBc;
    }

    public BigDecimal getAliquota() {
        return aliquota;
    }

    public void setAliquota(BigDecimal aliquota) {
        this.aliquota = aliquota;
    }

    public BigDecimal getValorPauta() {
        return valorPauta;
    }

    public void setValorPauta(BigDecimal valorPauta) {
        this.valorPauta = valorPauta;
    }

    public BigDecimal getValorPrecoMaximo() {
        return valorPrecoMaximo;
    }

    public void setValorPrecoMaximo(BigDecimal valorPrecoMaximo) {
        this.valorPrecoMaximo = valorPrecoMaximo;
    }

    public BigDecimal getMva() {
        return mva;
    }

    public void setMva(BigDecimal mva) {
        this.mva = mva;
    }

    public BigDecimal getPorcentoBc() {
        return porcentoBc;
    }

    public void setPorcentoBc(BigDecimal porcentoBc) {
        this.porcentoBc = porcentoBc;
    }

    public String getModalidadeBcSt() {
        return modalidadeBcSt;
    }

    public void setModalidadeBcSt(String modalidadeBcSt) {
        this.modalidadeBcSt = modalidadeBcSt;
    }

    public BigDecimal getAliquotaInternaSt() {
        return aliquotaInternaSt;
    }

    public void setAliquotaInternaSt(BigDecimal aliquotaInternaSt) {
        this.aliquotaInternaSt = aliquotaInternaSt;
    }

    public BigDecimal getAliquotaInterestadualSt() {
        return aliquotaInterestadualSt;
    }

    public void setAliquotaInterestadualSt(BigDecimal aliquotaInterestadualSt) {
        this.aliquotaInterestadualSt = aliquotaInterestadualSt;
    }

    public BigDecimal getPorcentoBcSt() {
        return porcentoBcSt;
    }

    public void setPorcentoBcSt(BigDecimal porcentoBcSt) {
        this.porcentoBcSt = porcentoBcSt;
    }

    public BigDecimal getAliquotaIcmsSt() {
        return aliquotaIcmsSt;
    }

    public void setAliquotaIcmsSt(BigDecimal aliquotaIcmsSt) {
        this.aliquotaIcmsSt = aliquotaIcmsSt;
    }

    public BigDecimal getValorPautaSt() {
        return valorPautaSt;
    }

    public void setValorPautaSt(BigDecimal valorPautaSt) {
        this.valorPautaSt = valorPautaSt;
    }

    public BigDecimal getValorPrecoMaximoSt() {
        return valorPrecoMaximoSt;
    }

    public void setValorPrecoMaximoSt(BigDecimal valorPrecoMaximoSt) {
        this.valorPrecoMaximoSt = valorPrecoMaximoSt;
    }

}
