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
package com.t2tierp.cte.java;

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
@Table(name = "CTE_AQUAVIARIO")
public class CteAquaviarioVO extends ValueObjectImpl implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "VALOR_PRESTACAO")
    private BigDecimal valorPrestacao;
    @Column(name = "AFRMM")
    private BigDecimal afrmm;
    @Column(name = "NUMERO_BOOKING")
    private String numeroBooking;
    @Column(name = "NUMERO_CONTROLE")
    private String numeroControle;
    @Column(name = "ID_NAVIO")
    private String idNavio;
    @JoinColumn(name = "ID_CTE_CABECALHO", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private CteCabecalhoVO cteCabecalho;

    public CteAquaviarioVO() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigDecimal getValorPrestacao() {
        return valorPrestacao;
    }

    public void setValorPrestacao(BigDecimal valorPrestacao) {
        this.valorPrestacao = valorPrestacao;
    }

    public BigDecimal getAfrmm() {
        return afrmm;
    }

    public void setAfrmm(BigDecimal afrmm) {
        this.afrmm = afrmm;
    }

    public String getNumeroBooking() {
        return numeroBooking;
    }

    public void setNumeroBooking(String numeroBooking) {
        this.numeroBooking = numeroBooking;
    }

    public String getNumeroControle() {
        return numeroControle;
    }

    public void setNumeroControle(String numeroControle) {
        this.numeroControle = numeroControle;
    }

    public String getIdNavio() {
        return idNavio;
    }

    public void setIdNavio(String idNavio) {
        this.idNavio = idNavio;
    }

    public CteCabecalhoVO getCteCabecalho() {
        return cteCabecalho;
    }

    public void setCteCabecalho(CteCabecalhoVO cteCabecalho) {
        this.cteCabecalho = cteCabecalho;
    }


    @Override
    public String toString() {
        return "com.t2tierp.cte.java.CteAquaviarioVO[id=" + id + "]";
    }

}
