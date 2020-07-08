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
@Table(name = "FOLHA_INSS_RETENCAO")
public class FolhaInssRetencaoVO extends ValueObjectImpl implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "VALOR_MENSAL")
    private BigDecimal valorMensal;
    @Column(name = "VALOR_13")
    private BigDecimal valor13;
    @JoinColumn(name = "ID_FOLHA_INSS_SERVICO", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private FolhaInssServicoVO folhaInssServico;
    @JoinColumn(name = "ID_FOLHA_INSS", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private FolhaInssVO folhaInss;

    public FolhaInssRetencaoVO() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigDecimal getValorMensal() {
        return valorMensal;
    }

    public void setValorMensal(BigDecimal valorMensal) {
        this.valorMensal = valorMensal;
    }

    public BigDecimal getValor13() {
        return valor13;
    }

    public void setValor13(BigDecimal valor13) {
        this.valor13 = valor13;
    }

    public FolhaInssServicoVO getFolhaInssServico() {
        return folhaInssServico;
    }

    public void setFolhaInssServico(FolhaInssServicoVO folhaInssServico) {
        this.folhaInssServico = folhaInssServico;
    }

    public FolhaInssVO getFolhaInss() {
        return folhaInss;
    }

    public void setFolhaInss(FolhaInssVO folhaInss) {
        this.folhaInss = folhaInss;
    }


    @Override
    public String toString() {
        return "com.t2tierp.folhapagamento.java.FolhaInssRetencaoVO[id=" + id + "]";
    }

}
