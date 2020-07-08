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
@Table(name = "FOLHA_LANCAMENTO_DETALHE")
public class FolhaLancamentoDetalheVO extends ValueObjectImpl implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "ORIGEM")
    private BigDecimal origem;
    @Column(name = "PROVENTO")
    private BigDecimal provento;
    @Column(name = "DESCONTO")
    private BigDecimal desconto;
    @JoinColumn(name = "ID_FOLHA_LANCAMENTO_CABECALHO", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private FolhaLancamentoCabecalhoVO folhaLancamentoCabecalho;
    @JoinColumn(name = "ID_FOLHA_EVENTO", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private FolhaEventoVO folhaEvento;

    public FolhaLancamentoDetalheVO() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigDecimal getOrigem() {
        return origem;
    }

    public void setOrigem(BigDecimal origem) {
        this.origem = origem;
    }

    public BigDecimal getProvento() {
        return provento;
    }

    public void setProvento(BigDecimal provento) {
        this.provento = provento;
    }

    public BigDecimal getDesconto() {
        return desconto;
    }

    public void setDesconto(BigDecimal desconto) {
        this.desconto = desconto;
    }

    public FolhaLancamentoCabecalhoVO getFolhaLancamentoCabecalho() {
        return folhaLancamentoCabecalho;
    }

    public void setFolhaLancamentoCabecalho(FolhaLancamentoCabecalhoVO folhaLancamentoCabecalho) {
        this.folhaLancamentoCabecalho = folhaLancamentoCabecalho;
    }

    public FolhaEventoVO getFolhaEvento() {
        return folhaEvento;
    }

    public void setFolhaEvento(FolhaEventoVO folhaEvento) {
        this.folhaEvento = folhaEvento;
    }


    @Override
    public String toString() {
        return "com.t2tierp.folhapagamento.java.FolhaLancamentoDetalheVO[id=" + id + "]";
    }

}
