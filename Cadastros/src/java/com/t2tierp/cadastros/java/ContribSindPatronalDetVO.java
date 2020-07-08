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
@Table(name = "CONTRIB_SIND_PATRONAL_DET")
public class ContribSindPatronalDetVO extends ValueObjectImpl implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "DE")
    private BigDecimal de;
    @Column(name = "ATE")
    private BigDecimal ate;
    @Column(name = "PERCENTUAL")
    private BigDecimal percentual;
    @Column(name = "VALOR_ADICIONAR")
    private BigDecimal valorAdicionar;
    @JoinColumn(name = "ID_CONTRIB_SIND_PATRONAL_CAB", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private ContribSindPatronalCabVO contribSindPatronalCab;

    public ContribSindPatronalDetVO() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigDecimal getDe() {
        return de;
    }

    public void setDe(BigDecimal de) {
        this.de = de;
    }

    public BigDecimal getAte() {
        return ate;
    }

    public void setAte(BigDecimal ate) {
        this.ate = ate;
    }

    public BigDecimal getPercentual() {
        return percentual;
    }

    public void setPercentual(BigDecimal percentual) {
        this.percentual = percentual;
    }

    public BigDecimal getValorAdicionar() {
        return valorAdicionar;
    }

    public void setValorAdicionar(BigDecimal valorAdicionar) {
        this.valorAdicionar = valorAdicionar;
    }

    public ContribSindPatronalCabVO getContribSindPatronalCab() {
        return contribSindPatronalCab;
    }

    public void setContribSindPatronalCab(ContribSindPatronalCabVO contribSindPatronalCab) {
        this.contribSindPatronalCab = contribSindPatronalCab;
    }


    @Override
    public String toString() {
        return "com.t2tierp.cadastros.java.ContribSindPatronalDetVO[id=" + id + "]";
    }

}
