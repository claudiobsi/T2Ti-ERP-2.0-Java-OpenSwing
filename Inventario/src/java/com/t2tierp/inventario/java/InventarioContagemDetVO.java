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
package com.t2tierp.inventario.java;

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
@Table(name = "INVENTARIO_CONTAGEM_DET")
public class InventarioContagemDetVO extends ValueObjectImpl implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "CONTAGEM01")
    private BigDecimal contagem01;
    @Column(name = "CONTAGEM02")
    private BigDecimal contagem02;
    @Column(name = "CONTAGEM03")
    private BigDecimal contagem03;
    @Column(name = "FECHADO_CONTAGEM")
    private String fechadoContagem;
    @Column(name = "QUANTIDADE_SISTEMA")
    private BigDecimal quantidadeSistema;
    @Column(name = "ACURACIDADE")
    private BigDecimal acuracidade;
    @Column(name = "DIVERGENCIA")
    private BigDecimal divergencia;
    @JoinColumn(name = "ID_INVENTARIO_CONTAGEM_CAB", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private InventarioContagemCabVO inventarioContagemCab;
    @JoinColumn(name = "ID_PRODUTO", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private ProdutoVO produto;

    public InventarioContagemDetVO() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigDecimal getContagem01() {
        return contagem01;
    }

    public void setContagem01(BigDecimal contagem01) {
        this.contagem01 = contagem01;
    }

    public BigDecimal getContagem02() {
        return contagem02;
    }

    public void setContagem02(BigDecimal contagem02) {
        this.contagem02 = contagem02;
    }

    public BigDecimal getContagem03() {
        return contagem03;
    }

    public void setContagem03(BigDecimal contagem03) {
        this.contagem03 = contagem03;
    }

    public String getFechadoContagem() {
        return fechadoContagem;
    }

    public void setFechadoContagem(String fechadoContagem) {
        this.fechadoContagem = fechadoContagem;
    }

    public BigDecimal getQuantidadeSistema() {
        return quantidadeSistema;
    }

    public void setQuantidadeSistema(BigDecimal quantidadeSistema) {
        this.quantidadeSistema = quantidadeSistema;
    }

    public BigDecimal getAcuracidade() {
        return acuracidade;
    }

    public void setAcuracidade(BigDecimal acuracidade) {
        this.acuracidade = acuracidade;
    }

    public BigDecimal getDivergencia() {
        return divergencia;
    }

    public void setDivergencia(BigDecimal divergencia) {
        this.divergencia = divergencia;
    }

    public InventarioContagemCabVO getInventarioContagemCab() {
        return inventarioContagemCab;
    }

    public void setInventarioContagemCab(InventarioContagemCabVO inventarioContagemCab) {
        this.inventarioContagemCab = inventarioContagemCab;
    }

    public ProdutoVO getProduto() {
        return produto;
    }

    public void setProduto(ProdutoVO produto) {
        this.produto = produto;
    }


    @Override
    public String toString() {
        return "com.t2tierp.inventario.java.InventarioContagemDetVO[id=" + id + "]";
    }

}
