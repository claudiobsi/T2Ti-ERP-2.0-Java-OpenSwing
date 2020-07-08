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
package com.t2tierp.sped.java;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Embeddable
public class ViewSpedC190VO implements Serializable {
    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @Column(name = "ID")
    private int id;
    @Column(name = "CST_ICMS")
    private String cstIcms;
    @Column(name = "CFOP")
    private Integer cfop;
    @Column(name = "ALIQUOTA_ICMS")
    private BigDecimal aliquotaIcms;
    @Column(name = "DATA_EMISSAO")
    @Temporal(TemporalType.DATE)
    private Date dataEmissao;
    @Column(name = "SOMA_VALOR_OPERACAO")
    private BigDecimal somaValorOperacao;
    @Column(name = "SOMA_BASE_CALCULO_ICMS")
    private BigDecimal somaBaseCalculoIcms;
    @Column(name = "SOMA_VALOR_ICMS")
    private BigDecimal somaValorIcms;
    @Column(name = "SOMA_BASE_CALCULO_ICMS_ST")
    private BigDecimal somaBaseCalculoIcmsSt;
    @Column(name = "SOMA_VALOR_ICMS_ST")
    private BigDecimal somaValorIcmsSt;
    @Column(name = "SOMA_VL_RED_BC")
    private BigDecimal somaVlRedBc;
    @Column(name = "SOMA_VALOR_IPI")
    private BigDecimal somaValorIpi;

    public ViewSpedC190VO() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCstIcms() {
        return cstIcms;
    }

    public void setCstIcms(String cstIcms) {
        this.cstIcms = cstIcms;
    }

    public Integer getCfop() {
        return cfop;
    }

    public void setCfop(Integer cfop) {
        this.cfop = cfop;
    }

    public BigDecimal getAliquotaIcms() {
        return aliquotaIcms;
    }

    public void setAliquotaIcms(BigDecimal aliquotaIcms) {
        this.aliquotaIcms = aliquotaIcms;
    }

    public Date getDataEmissao() {
        return dataEmissao;
    }

    public void setDataEmissao(Date dataEmissao) {
        this.dataEmissao = dataEmissao;
    }

    public BigDecimal getSomaValorOperacao() {
        return somaValorOperacao;
    }

    public void setSomaValorOperacao(BigDecimal somaValorOperacao) {
        this.somaValorOperacao = somaValorOperacao;
    }

    public BigDecimal getSomaBaseCalculoIcms() {
        return somaBaseCalculoIcms;
    }

    public void setSomaBaseCalculoIcms(BigDecimal somaBaseCalculoIcms) {
        this.somaBaseCalculoIcms = somaBaseCalculoIcms;
    }

    public BigDecimal getSomaValorIcms() {
        return somaValorIcms;
    }

    public void setSomaValorIcms(BigDecimal somaValorIcms) {
        this.somaValorIcms = somaValorIcms;
    }

    public BigDecimal getSomaBaseCalculoIcmsSt() {
        return somaBaseCalculoIcmsSt;
    }

    public void setSomaBaseCalculoIcmsSt(BigDecimal somaBaseCalculoIcmsSt) {
        this.somaBaseCalculoIcmsSt = somaBaseCalculoIcmsSt;
    }

    public BigDecimal getSomaValorIcmsSt() {
        return somaValorIcmsSt;
    }

    public void setSomaValorIcmsSt(BigDecimal somaValorIcmsSt) {
        this.somaValorIcmsSt = somaValorIcmsSt;
    }

    public BigDecimal getSomaVlRedBc() {
        return somaVlRedBc;
    }

    public void setSomaVlRedBc(BigDecimal somaVlRedBc) {
        this.somaVlRedBc = somaVlRedBc;
    }

    public BigDecimal getSomaValorIpi() {
        return somaValorIpi;
    }

    public void setSomaValorIpi(BigDecimal somaValorIpi) {
        this.somaValorIpi = somaValorIpi;
    }

}
