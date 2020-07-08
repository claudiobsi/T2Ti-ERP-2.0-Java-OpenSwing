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
package com.t2tierp.patrimonio.java;

import java.io.Serializable;
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
@Table(name = "PATRIM_MOVIMENTACAO_BEM")
public class PatrimMovimentacaoBemVO extends ValueObjectImpl implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Temporal(TemporalType.DATE)
    @Column(name = "DATA_MOVIMENTACAO")
    private Date dataMovimentacao;
    @Column(name = "RESPONSAVEL")
    private String responsavel;
    @JoinColumn(name = "ID_PATRIM_TIPO_MOVIMENTACAO", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private PatrimTipoMovimentacaoVO patrimTipoMovimentacao;
    @JoinColumn(name = "ID_PATRIM_BEM", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private PatrimBemVO patrimBem;

    public PatrimMovimentacaoBemVO() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDataMovimentacao() {
        return dataMovimentacao;
    }

    public void setDataMovimentacao(Date dataMovimentacao) {
        this.dataMovimentacao = dataMovimentacao;
    }

    public String getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(String responsavel) {
        this.responsavel = responsavel;
    }

    public PatrimTipoMovimentacaoVO getPatrimTipoMovimentacao() {
        return patrimTipoMovimentacao;
    }

    public void setPatrimTipoMovimentacao(PatrimTipoMovimentacaoVO patrimTipoMovimentacao) {
        this.patrimTipoMovimentacao = patrimTipoMovimentacao;
    }

    public PatrimBemVO getPatrimBem() {
        return patrimBem;
    }

    public void setPatrimBem(PatrimBemVO patrimBem) {
        this.patrimBem = patrimBem;
    }


    @Override
    public String toString() {
        return "com.t2tierp.patrimonio.java.PatrimMovimentacaoBemVO[id=" + id + "]";
    }

}
