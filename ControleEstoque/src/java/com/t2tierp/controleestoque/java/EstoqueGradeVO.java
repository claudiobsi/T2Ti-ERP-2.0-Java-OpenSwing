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
package com.t2tierp.controleestoque.java;

import com.t2tierp.cadastros.java.ProdutoVO;
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
@Table(name = "ESTOQUE_GRADE")
public class EstoqueGradeVO extends ValueObjectImpl implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "CODIGO")
    private String codigo;
    @Column(name = "QUANTIDADE")
    private BigDecimal quantidade;
    @JoinColumn(name = "ID_ESTOQUE_COR", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private EstoqueCorVO estoqueCor;
    @JoinColumn(name = "ID_ESTOQUE_TAMANHO", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private EstoqueTamanhoVO estoqueTamanho;
    @JoinColumn(name = "ID_PRODUTO", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private ProdutoVO produto;
    @JoinColumn(name = "ID_ESTOQUE_SABOR", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private EstoqueSaborVO estoqueSabor;
    @JoinColumn(name = "ID_ESTOQUE_MARCA", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private EstoqueMarcaVO estoqueMarca;

    public EstoqueGradeVO() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public BigDecimal getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(BigDecimal quantidade) {
        this.quantidade = quantidade;
    }

    public EstoqueCorVO getEstoqueCor() {
        return estoqueCor;
    }

    public void setEstoqueCor(EstoqueCorVO estoqueCor) {
        this.estoqueCor = estoqueCor;
    }

    public EstoqueTamanhoVO getEstoqueTamanho() {
        return estoqueTamanho;
    }

    public void setEstoqueTamanho(EstoqueTamanhoVO estoqueTamanho) {
        this.estoqueTamanho = estoqueTamanho;
    }

    public ProdutoVO getProduto() {
        return produto;
    }

    public void setProduto(ProdutoVO produto) {
        this.produto = produto;
    }

    public EstoqueSaborVO getEstoqueSabor() {
        return estoqueSabor;
    }

    public void setEstoqueSabor(EstoqueSaborVO estoqueSabor) {
        this.estoqueSabor = estoqueSabor;
    }

    public EstoqueMarcaVO getEstoqueMarca() {
        return estoqueMarca;
    }

    public void setEstoqueMarca(EstoqueMarcaVO estoqueMarca) {
        this.estoqueMarca = estoqueMarca;
    }


    @Override
    public String toString() {
        return "com.t2tierp.controleestoque.java.EstoqueGradeVO[id=" + id + "]";
    }

}
