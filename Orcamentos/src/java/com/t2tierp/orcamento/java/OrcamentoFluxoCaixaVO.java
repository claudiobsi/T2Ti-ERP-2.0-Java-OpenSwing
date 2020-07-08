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
package com.t2tierp.orcamento.java;

import com.t2tierp.financeiro.java.PlanoNaturezaFinanceiraVO;
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
import javax.persistence.Transient;
import org.openswing.swing.message.receive.java.ValueObjectImpl;


@Entity
@Table(name = "ORCAMENTO_FLUXO_CAIXA")
public class OrcamentoFluxoCaixaVO extends ValueObjectImpl implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "NOME")
    private String nome;
    @Column(name = "DESCRICAO")
    private String descricao;
    @Temporal(TemporalType.DATE)
    @Column(name = "DATA_INICIAL")
    private Date dataInicial;
    @Column(name = "NUMERO_PERIODOS")
    private Integer numeroPeriodos;
    @Temporal(TemporalType.DATE)
    @Column(name = "DATA_BASE")
    private Date dataBase;
    @JoinColumn(name = "ID_ORC_FLUXO_CAIXA_PERIODO", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private OrcamentoFluxoCaixaPeriodoVO orcamentoFluxoCaixaPeriodo;
    @Transient
    private PlanoNaturezaFinanceiraVO planoNaturezaFinanceira;

    public OrcamentoFluxoCaixaVO() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Date getDataInicial() {
        return dataInicial;
    }

    public void setDataInicial(Date dataInicial) {
        this.dataInicial = dataInicial;
    }

    public Integer getNumeroPeriodos() {
        return numeroPeriodos;
    }

    public void setNumeroPeriodos(Integer numeroPeriodos) {
        this.numeroPeriodos = numeroPeriodos;
    }

    public Date getDataBase() {
        return dataBase;
    }

    public void setDataBase(Date dataBase) {
        this.dataBase = dataBase;
    }

    public OrcamentoFluxoCaixaPeriodoVO getOrcamentoFluxoCaixaPeriodo() {
        return orcamentoFluxoCaixaPeriodo;
    }

    public void setOrcamentoFluxoCaixaPeriodo(OrcamentoFluxoCaixaPeriodoVO orcamentoFluxoCaixaPeriodo) {
        this.orcamentoFluxoCaixaPeriodo = orcamentoFluxoCaixaPeriodo;
    }

    public PlanoNaturezaFinanceiraVO getPlanoNaturezaFinanceira() {
        return planoNaturezaFinanceira;
    }

    public void setPlanoNaturezaFinanceira(PlanoNaturezaFinanceiraVO planoNaturezaFinanceira) {
        this.planoNaturezaFinanceira = planoNaturezaFinanceira;
    }

    @Override
    public String toString() {
        return "com.t2tierp.orcamento.java.OrcamentoFluxoCaixaVO[id=" + id + "]";
    }

}
