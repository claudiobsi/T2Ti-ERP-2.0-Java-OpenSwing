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

import com.t2tierp.cadastros.java.ColaboradorVO;
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
@Table(name = "FERIAS_PERIODO_AQUISITIVO")
public class FeriasPeriodoAquisitivoVO extends ValueObjectImpl implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Temporal(TemporalType.DATE)
    @Column(name = "DATA_INICIO")
    private Date dataInicio;
    @Temporal(TemporalType.DATE)
    @Column(name = "DATA_FIM")
    private Date dataFim;
    @Column(name = "SITUACAO")
    private String situacao;
    @Temporal(TemporalType.DATE)
    @Column(name = "LIMITE_PARA_GOZO")
    private Date limiteParaGozo;
    @Column(name = "DESCONTAR_FALTAS")
    private String descontarFaltas;
    @Column(name = "DESCONSIDERAR_AFASTAMENTO")
    private String desconsiderarAfastamento;
    @Column(name = "AFASTAMENTO_PREVIDENCIA")
    private Integer afastamentoPrevidencia;
    @Column(name = "AFASTAMENTO_SEM_REMUN")
    private Integer afastamentoSemRemun;
    @Column(name = "AFASTAMENTO_COM_REMUN")
    private Integer afastamentoComRemun;
    @Column(name = "DIAS_DIREITO")
    private Integer diasDireito;
    @Column(name = "DIAS_GOZADOS")
    private Integer diasGozados;
    @Column(name = "DIAS_FALTAS")
    private Integer diasFaltas;
    @Column(name = "DIAS_RESTANTES")
    private Integer diasRestantes;
    @JoinColumn(name = "ID_COLABORADOR", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private ColaboradorVO colaborador;

    public FeriasPeriodoAquisitivoVO() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    public Date getDataFim() {
        return dataFim;
    }

    public void setDataFim(Date dataFim) {
        this.dataFim = dataFim;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

    public Date getLimiteParaGozo() {
        return limiteParaGozo;
    }

    public void setLimiteParaGozo(Date limiteParaGozo) {
        this.limiteParaGozo = limiteParaGozo;
    }

    public String getDescontarFaltas() {
        return descontarFaltas;
    }

    public void setDescontarFaltas(String descontarFaltas) {
        this.descontarFaltas = descontarFaltas;
    }

    public String getDesconsiderarAfastamento() {
        return desconsiderarAfastamento;
    }

    public void setDesconsiderarAfastamento(String desconsiderarAfastamento) {
        this.desconsiderarAfastamento = desconsiderarAfastamento;
    }

    public Integer getAfastamentoPrevidencia() {
        return afastamentoPrevidencia;
    }

    public void setAfastamentoPrevidencia(Integer afastamentoPrevidencia) {
        this.afastamentoPrevidencia = afastamentoPrevidencia;
    }

    public Integer getAfastamentoSemRemun() {
        return afastamentoSemRemun;
    }

    public void setAfastamentoSemRemun(Integer afastamentoSemRemun) {
        this.afastamentoSemRemun = afastamentoSemRemun;
    }

    public Integer getAfastamentoComRemun() {
        return afastamentoComRemun;
    }

    public void setAfastamentoComRemun(Integer afastamentoComRemun) {
        this.afastamentoComRemun = afastamentoComRemun;
    }

    public Integer getDiasDireito() {
        return diasDireito;
    }

    public void setDiasDireito(Integer diasDireito) {
        this.diasDireito = diasDireito;
    }

    public Integer getDiasGozados() {
        return diasGozados;
    }

    public void setDiasGozados(Integer diasGozados) {
        this.diasGozados = diasGozados;
    }

    public Integer getDiasFaltas() {
        return diasFaltas;
    }

    public void setDiasFaltas(Integer diasFaltas) {
        this.diasFaltas = diasFaltas;
    }

    public Integer getDiasRestantes() {
        return diasRestantes;
    }

    public void setDiasRestantes(Integer diasRestantes) {
        this.diasRestantes = diasRestantes;
    }

    public ColaboradorVO getColaborador() {
        return colaborador;
    }

    public void setColaborador(ColaboradorVO colaborador) {
        this.colaborador = colaborador;
    }


    @Override
    public String toString() {
        return "com.t2tierp.folhapagamento.java.FeriasPeriodoAquisitivoVO[id=" + id + "]";
    }

}
