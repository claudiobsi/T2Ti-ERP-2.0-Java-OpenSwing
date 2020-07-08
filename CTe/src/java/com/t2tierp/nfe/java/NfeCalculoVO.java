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
package com.t2tierp.nfe.java;

import java.io.Serializable;
import java.math.BigDecimal;
import org.openswing.swing.message.receive.java.ValueObjectImpl;

public class NfeCalculoVO extends ValueObjectImpl implements Serializable {
    private static final long serialVersionUID = 1L;
    //emissor
    private Integer crtEmissor;
    private Integer ufEmissor;
    //cliente ou fornecedor
    private String tipoCliente;
    private String ufCliente;
    private Integer ufClienteCod;
    //icms
    private BigDecimal valorBrutoProdutos;
    private BigDecimal valorFrete;
    private BigDecimal valorSeguro;
    private BigDecimal valorOutrasDespesas;
    private BigDecimal valorDesconto;
    private String cstIcms;
    private String csosn;
    private Integer modalidadeBcIcms;
    private BigDecimal baseCalculoIcms;
    private BigDecimal taxaReducaoBcIcms;
    private BigDecimal valorReducaoBcIcms;
    private BigDecimal aliquotaIcms;
    private BigDecimal aliquotaIcmsInter;
    private BigDecimal valorIcms;
    //icms ST
    private Integer modalidadeBcIcmsSt;
    private BigDecimal percentualMvaIcmsSt;
    private BigDecimal baseCalculoIcmsSt;
    private BigDecimal reducaoBcIcmsSt;
    private BigDecimal valorReducaoBcIcmsSt;
    private BigDecimal aliquotaIcmsSt;
    private BigDecimal valorIcmsSt;
    //icms credito simples
    private BigDecimal aliquotaCreditoIcmsSn;
    private BigDecimal valorCreditoIcmsSn;
    //ipi
    private String cstIpi;
    private BigDecimal baseCalculoIpi;
    private BigDecimal aliquotaIpi;
    private BigDecimal valorIpi;
    //pis
    private String cstPis;
    private BigDecimal baseCalculoPis;
    private BigDecimal aliquotaPis;
    private BigDecimal aliquotaPisReais;
    private BigDecimal valorPis;
    //cofins
    private String cstCofins;
    private BigDecimal baseCalculoCofins;
    private BigDecimal aliquotaCofins;
    private BigDecimal aliquotaCofinsReais;
    private BigDecimal valorCofins;

    public Integer getCrtEmissor() {
        return crtEmissor;
    }

    public void setCrtEmissor(Integer crtEmissor) {
        this.crtEmissor = crtEmissor;
    }

    public Integer getUfEmissor() {
        return ufEmissor;
    }

    public void setUfEmissor(Integer ufEmissor) {
        this.ufEmissor = ufEmissor;
    }

    public String getTipoCliente() {
        return tipoCliente;
    }

    public void setTipoCliente(String tipoCliente) {
        this.tipoCliente = tipoCliente;
    }

    public String getUfCliente() {
        return ufCliente;
    }

    public void setUfCliente(String ufCliente) {
        this.ufCliente = ufCliente;
    }

    public Integer getUfClienteCod() {
        return ufClienteCod;
    }

    public void setUfClienteCod(Integer ufClienteCod) {
        this.ufClienteCod = ufClienteCod;
    }

    public BigDecimal getValorBrutoProdutos() {
        return valorBrutoProdutos;
    }

    public void setValorBrutoProdutos(BigDecimal valorBrutoProdutos) {
        this.valorBrutoProdutos = valorBrutoProdutos;
    }

    public BigDecimal getValorFrete() {
        return valorFrete;
    }

    public void setValorFrete(BigDecimal valorFrete) {
        this.valorFrete = valorFrete;
    }

    public BigDecimal getValorSeguro() {
        return valorSeguro;
    }

    public void setValorSeguro(BigDecimal valorSeguro) {
        this.valorSeguro = valorSeguro;
    }

    public BigDecimal getValorOutrasDespesas() {
        return valorOutrasDespesas;
    }

    public void setValorOutrasDespesas(BigDecimal valorOutrasDespesas) {
        this.valorOutrasDespesas = valorOutrasDespesas;
    }

    public BigDecimal getValorDesconto() {
        return valorDesconto;
    }

    public void setValorDesconto(BigDecimal valorDesconto) {
        this.valorDesconto = valorDesconto;
    }

    public String getCstIcms() {
        return cstIcms;
    }

    public void setCstIcms(String cstIcms) {
        this.cstIcms = cstIcms;
    }

    public String getCsosn() {
        return csosn;
    }

    public void setCsosn(String csosn) {
        this.csosn = csosn;
    }

    public Integer getModalidadeBcIcms() {
        return modalidadeBcIcms;
    }

    public void setModalidadeBcIcms(Integer modalidadeBcIcms) {
        this.modalidadeBcIcms = modalidadeBcIcms;
    }

    public BigDecimal getBaseCalculoIcms() {
        return baseCalculoIcms;
    }

    public void setBaseCalculoIcms(BigDecimal baseCalculoIcms) {
        this.baseCalculoIcms = baseCalculoIcms;
    }

    public BigDecimal getTaxaReducaoBcIcms() {
        return taxaReducaoBcIcms;
    }

    public void setTaxaReducaoBcIcms(BigDecimal taxaReducaoBcIcms) {
        this.taxaReducaoBcIcms = taxaReducaoBcIcms;
    }

    public BigDecimal getValorReducaoBcIcms() {
        return valorReducaoBcIcms;
    }

    public void setValorReducaoBcIcms(BigDecimal valorReducaoBcIcms) {
        this.valorReducaoBcIcms = valorReducaoBcIcms;
    }

    public BigDecimal getAliquotaIcms() {
        return aliquotaIcms;
    }

    public void setAliquotaIcms(BigDecimal aliquotaIcms) {
        this.aliquotaIcms = aliquotaIcms;
    }

    public BigDecimal getAliquotaIcmsInter() {
        return aliquotaIcmsInter;
    }

    public void setAliquotaIcmsInter(BigDecimal aliquotaIcmsInter) {
        this.aliquotaIcmsInter = aliquotaIcmsInter;
    }

    public BigDecimal getValorIcms() {
        return valorIcms;
    }

    public void setValorIcms(BigDecimal valorIcms) {
        this.valorIcms = valorIcms;
    }

    public Integer getModalidadeBcIcmsSt() {
        return modalidadeBcIcmsSt;
    }

    public void setModalidadeBcIcmsSt(Integer modalidadeBcIcmsSt) {
        this.modalidadeBcIcmsSt = modalidadeBcIcmsSt;
    }

    public BigDecimal getPercentualMvaIcmsSt() {
        return percentualMvaIcmsSt;
    }

    public void setPercentualMvaIcmsSt(BigDecimal percentualMvaIcmsSt) {
        this.percentualMvaIcmsSt = percentualMvaIcmsSt;
    }

    public BigDecimal getBaseCalculoIcmsSt() {
        return baseCalculoIcmsSt;
    }

    public void setBaseCalculoIcmsSt(BigDecimal baseCalculoIcmsSt) {
        this.baseCalculoIcmsSt = baseCalculoIcmsSt;
    }

    public BigDecimal getReducaoBcIcmsSt() {
        return reducaoBcIcmsSt;
    }

    public void setReducaoBcIcmsSt(BigDecimal reducaoBcIcmsSt) {
        this.reducaoBcIcmsSt = reducaoBcIcmsSt;
    }

    public BigDecimal getValorReducaoBcIcmsSt() {
        return valorReducaoBcIcmsSt;
    }

    public void setValorReducaoBcIcmsSt(BigDecimal valorReducaoBcIcmsSt) {
        this.valorReducaoBcIcmsSt = valorReducaoBcIcmsSt;
    }

    public BigDecimal getAliquotaIcmsSt() {
        return aliquotaIcmsSt;
    }

    public void setAliquotaIcmsSt(BigDecimal aliquotaIcmsSt) {
        this.aliquotaIcmsSt = aliquotaIcmsSt;
    }

    public BigDecimal getValorIcmsSt() {
        return valorIcmsSt;
    }

    public void setValorIcmsSt(BigDecimal valorIcmsSt) {
        this.valorIcmsSt = valorIcmsSt;
    }

    public BigDecimal getAliquotaCreditoIcmsSn() {
        return aliquotaCreditoIcmsSn;
    }

    public void setAliquotaCreditoIcmsSn(BigDecimal aliquotaCreditoIcmsSn) {
        this.aliquotaCreditoIcmsSn = aliquotaCreditoIcmsSn;
    }

    public BigDecimal getValorCreditoIcmsSn() {
        return valorCreditoIcmsSn;
    }

    public void setValorCreditoIcmsSn(BigDecimal valorCreditoIcmsSn) {
        this.valorCreditoIcmsSn = valorCreditoIcmsSn;
    }

    public String getCstIpi() {
        return cstIpi;
    }

    public void setCstIpi(String cstIpi) {
        this.cstIpi = cstIpi;
    }

    public BigDecimal getBaseCalculoIpi() {
        return baseCalculoIpi;
    }

    public void setBaseCalculoIpi(BigDecimal baseCalculoIpi) {
        this.baseCalculoIpi = baseCalculoIpi;
    }

    public BigDecimal getAliquotaIpi() {
        return aliquotaIpi;
    }

    public void setAliquotaIpi(BigDecimal aliquotaIpi) {
        this.aliquotaIpi = aliquotaIpi;
    }

    public BigDecimal getValorIpi() {
        return valorIpi;
    }

    public void setValorIpi(BigDecimal valorIpi) {
        this.valorIpi = valorIpi;
    }

    public String getCstPis() {
        return cstPis;
    }

    public void setCstPis(String cstPis) {
        this.cstPis = cstPis;
    }

    public BigDecimal getBaseCalculoPis() {
        return baseCalculoPis;
    }

    public void setBaseCalculoPis(BigDecimal baseCalculoPis) {
        this.baseCalculoPis = baseCalculoPis;
    }

    public BigDecimal getAliquotaPis() {
        return aliquotaPis;
    }

    public void setAliquotaPis(BigDecimal aliquotaPis) {
        this.aliquotaPis = aliquotaPis;
    }

    public BigDecimal getAliquotaPisReais() {
        return aliquotaPisReais;
    }

    public void setAliquotaPisReais(BigDecimal aliquotaPisReais) {
        this.aliquotaPisReais = aliquotaPisReais;
    }

    public BigDecimal getValorPis() {
        return valorPis;
    }

    public void setValorPis(BigDecimal valorPis) {
        this.valorPis = valorPis;
    }

    public String getCstCofins() {
        return cstCofins;
    }

    public void setCstCofins(String cstCofins) {
        this.cstCofins = cstCofins;
    }

    public BigDecimal getBaseCalculoCofins() {
        return baseCalculoCofins;
    }

    public void setBaseCalculoCofins(BigDecimal baseCalculoCofins) {
        this.baseCalculoCofins = baseCalculoCofins;
    }

    public BigDecimal getAliquotaCofins() {
        return aliquotaCofins;
    }

    public void setAliquotaCofins(BigDecimal aliquotaCofins) {
        this.aliquotaCofins = aliquotaCofins;
    }

    public BigDecimal getAliquotaCofinsReais() {
        return aliquotaCofinsReais;
    }

    public void setAliquotaCofinsReais(BigDecimal aliquotaCofinsReais) {
        this.aliquotaCofinsReais = aliquotaCofinsReais;
    }

    public BigDecimal getValorCofins() {
        return valorCofins;
    }

    public void setValorCofins(BigDecimal valorCofins) {
        this.valorCofins = valorCofins;
    }
}
