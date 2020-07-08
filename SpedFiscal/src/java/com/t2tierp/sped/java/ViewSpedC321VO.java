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
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Embeddable
public class ViewSpedC321VO implements Serializable {
    private static final long serialVersionUID = 1L;
    @Column(name = "ID_PRODUTO")
    private Integer idProduto;
    @Column(name = "DESCRICAO_UNIDADE")
    private String descricaoUnidade;
    @Column(name = "DATA_EMISSAO")
    @Temporal(TemporalType.DATE)
    private Date dataEmissao;
    @Column(name = "SOMA_QUANTIDADE")
    private BigDecimal somaQuantidade;
    @Column(name = "SOMA_ITEM")
    private BigDecimal somaItem;
    @Column(name = "SOMA_DESCONTO")
    private BigDecimal somaDesconto;
    @Column(name = "SOMA_BASE_ICMS")
    private BigDecimal somaBaseIcms;
    @Column(name = "SOMA_ICMS")
    private BigDecimal somaIcms;
    @Column(name = "SOMA_PIS")
    private BigDecimal somaPis;
    @Column(name = "SOMA_COFINS")
    private BigDecimal somaCofins;

    public ViewSpedC321VO() {
    }

    public Integer getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(Integer idProduto) {
        this.idProduto = idProduto;
    }

    public String getDescricaoUnidade() {
        return descricaoUnidade;
    }

    public void setDescricaoUnidade(String descricaoUnidade) {
        this.descricaoUnidade = descricaoUnidade;
    }

    public Date getDataEmissao() {
        return dataEmissao;
    }

    public void setDataEmissao(Date dataEmissao) {
        this.dataEmissao = dataEmissao;
    }

    public BigDecimal getSomaQuantidade() {
        return somaQuantidade;
    }

    public void setSomaQuantidade(BigDecimal somaQuantidade) {
        this.somaQuantidade = somaQuantidade;
    }

    public BigDecimal getSomaItem() {
        return somaItem;
    }

    public void setSomaItem(BigDecimal somaItem) {
        this.somaItem = somaItem;
    }

    public BigDecimal getSomaDesconto() {
        return somaDesconto;
    }

    public void setSomaDesconto(BigDecimal somaDesconto) {
        this.somaDesconto = somaDesconto;
    }

    public BigDecimal getSomaBaseIcms() {
        return somaBaseIcms;
    }

    public void setSomaBaseIcms(BigDecimal somaBaseIcms) {
        this.somaBaseIcms = somaBaseIcms;
    }

    public BigDecimal getSomaIcms() {
        return somaIcms;
    }

    public void setSomaIcms(BigDecimal somaIcms) {
        this.somaIcms = somaIcms;
    }

    public BigDecimal getSomaPis() {
        return somaPis;
    }

    public void setSomaPis(BigDecimal somaPis) {
        this.somaPis = somaPis;
    }

    public BigDecimal getSomaCofins() {
        return somaCofins;
    }

    public void setSomaCofins(BigDecimal somaCofins) {
        this.somaCofins = somaCofins;
    }

}
