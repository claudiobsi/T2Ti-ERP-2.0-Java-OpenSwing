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
package com.t2tierp.cadastros.java;

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
import org.openswing.swing.message.receive.java.ValueObjectImpl;


@Entity
@Table(name = "FORNECEDOR_PRODUTO")
public class FornecedorProdutoVO extends ValueObjectImpl implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "CODIGO_FORNECEDOR_PRODUTO")
    private String codigoFornecedorProduto;
    @Temporal(TemporalType.DATE)
    @Column(name = "DATA_ULTIMA_COMPRA")
    private Date dataUltimaCompra;
    @Column(name = "PRECO_ULTIMA_COMPRA")
    private BigDecimal precoUltimaCompra;
    @Column(name = "LOTE_MINIMO_COMPRA")
    private BigDecimal loteMinimoCompra;
    @Column(name = "MENOR_EMBALAGEM_COMPRA")
    private BigDecimal menorEmbalagemCompra;
    @Column(name = "CUSTO_ULTIMA_COMPRA")
    private BigDecimal custoUltimaCompra;
    @JoinColumn(name = "ID_PRODUTO", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private ProdutoVO produto;
    @JoinColumn(name = "ID_FORNECEDOR", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private FornecedorVO fornecedor;

    public FornecedorProdutoVO() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCodigoFornecedorProduto() {
        return codigoFornecedorProduto;
    }

    public void setCodigoFornecedorProduto(String codigoFornecedorProduto) {
        this.codigoFornecedorProduto = codigoFornecedorProduto;
    }

    public Date getDataUltimaCompra() {
        return dataUltimaCompra;
    }

    public void setDataUltimaCompra(Date dataUltimaCompra) {
        this.dataUltimaCompra = dataUltimaCompra;
    }

    public BigDecimal getPrecoUltimaCompra() {
        return precoUltimaCompra;
    }

    public void setPrecoUltimaCompra(BigDecimal precoUltimaCompra) {
        this.precoUltimaCompra = precoUltimaCompra;
    }

    public BigDecimal getLoteMinimoCompra() {
        return loteMinimoCompra;
    }

    public void setLoteMinimoCompra(BigDecimal loteMinimoCompra) {
        this.loteMinimoCompra = loteMinimoCompra;
    }

    public BigDecimal getMenorEmbalagemCompra() {
        return menorEmbalagemCompra;
    }

    public void setMenorEmbalagemCompra(BigDecimal menorEmbalagemCompra) {
        this.menorEmbalagemCompra = menorEmbalagemCompra;
    }

    public BigDecimal getCustoUltimaCompra() {
        return custoUltimaCompra;
    }

    public void setCustoUltimaCompra(BigDecimal custoUltimaCompra) {
        this.custoUltimaCompra = custoUltimaCompra;
    }

    public ProdutoVO getProduto() {
        return produto;
    }

    public void setProduto(ProdutoVO produto) {
        this.produto = produto;
    }

    public FornecedorVO getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(FornecedorVO fornecedor) {
        this.fornecedor = fornecedor;
    }


    @Override
    public String toString() {
        return "com.t2tierp.cadastros.java.FornecedorProdutoVO[id=" + id + "]";
    }

}
