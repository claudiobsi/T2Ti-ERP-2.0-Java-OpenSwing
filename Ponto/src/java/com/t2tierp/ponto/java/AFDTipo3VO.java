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
package com.t2tierp.ponto.java;

import com.t2tierp.cadastros.java.ColaboradorVO;
import java.io.Serializable;
import java.util.Date;
import org.openswing.swing.message.receive.java.ValueObjectImpl;

public class AFDTipo3VO extends ValueObjectImpl implements Serializable{

    private static final long serialVersionUID = 1L;
    private Long sequencial;
    private Date dataMarcacao;
    private String horaMarcacao;
    private String pisEmpregado;
    private String numeroSerieRelogioPonto;
    private String situacaoRegistro;
    private ColaboradorVO colaborador;
    private PontoHorarioVO pontoHorario;
    private String parEntradaSaida;
    private Boolean desconsiderar;
    private String tipoRegistro;
    private String justificativa;

    /**
     * @return the sequencial
     */
    public Long getSequencial() {
        return sequencial;
    }

    /**
     * @param sequencial the sequencial to set
     */
    public void setSequencial(Long sequencial) {
        this.sequencial = sequencial;
    }

    /**
     * @return the dataMarcacao
     */
    public Date getDataMarcacao() {
        return dataMarcacao;
    }

    /**
     * @param dataMarcacao the dataMarcacao to set
     */
    public void setDataMarcacao(Date dataMarcacao) {
        this.dataMarcacao = dataMarcacao;
    }

    /**
     * @return the horaMarcacao
     */
    public String getHoraMarcacao() {
        return horaMarcacao;
    }

    /**
     * @param horaMarcacao the horaMarcacao to set
     */
    public void setHoraMarcacao(String horaMarcacao) {
        this.horaMarcacao = horaMarcacao;
    }

    /**
     * @return the pisEmpregado
     */
    public String getPisEmpregado() {
        return pisEmpregado;
    }

    /**
     * @param pisEmpregado the pisEmpregado to set
     */
    public void setPisEmpregado(String pisEmpregado) {
        this.pisEmpregado = pisEmpregado;
    }

    /**
     * @return the numeroSerieRelogioPonto
     */
    public String getNumeroSerieRelogioPonto() {
        return numeroSerieRelogioPonto;
    }

    /**
     * @param numeroSerieRelogioPonto the numeroSerieRelogioPonto to set
     */
    public void setNumeroSerieRelogioPonto(String numeroSerieRelogioPonto) {
        this.numeroSerieRelogioPonto = numeroSerieRelogioPonto;
    }

    /**
     * @return the situacaoRegistro
     */
    public String getSituacaoRegistro() {
        return situacaoRegistro;
    }

    /**
     * @param situacaoRegistro the situacaoRegistro to set
     */
    public void setSituacaoRegistro(String situacaoRegistro) {
        this.situacaoRegistro = situacaoRegistro;
    }

    /**
     * @return the colaborador
     */
    public ColaboradorVO getColaborador() {
        return colaborador;
    }

    /**
     * @param colaborador the colaborador to set
     */
    public void setColaborador(ColaboradorVO colaborador) {
        this.colaborador = colaborador;
    }

    /**
     * @return the pontoHorario
     */
    public PontoHorarioVO getPontoHorario() {
        return pontoHorario;
    }

    /**
     * @param pontoHorario the pontoHorario to set
     */
    public void setPontoHorario(PontoHorarioVO pontoHorario) {
        this.pontoHorario = pontoHorario;
    }

    /**
     * @return the parEntradaSaida
     */
    public String getParEntradaSaida() {
        return parEntradaSaida;
    }

    /**
     * @param parEntradaSaida the parEntradaSaida to set
     */
    public void setParEntradaSaida(String parEntradaSaida) {
        this.parEntradaSaida = parEntradaSaida;
    }

    /**
     * @return the desconsiderar
     */
    public Boolean getDesconsiderar() {
        return desconsiderar;
    }

    /**
     * @param desconsiderar the desconsiderar to set
     */
    public void setDesconsiderar(Boolean desconsiderar) {
        this.desconsiderar = desconsiderar;
    }

    /**
     * @return the tipoRegistro
     */
    public String getTipoRegistro() {
        return tipoRegistro;
    }

    /**
     * @param tipoRegistro the tipoRegistro to set
     */
    public void setTipoRegistro(String tipoRegistro) {
        this.tipoRegistro = tipoRegistro;
    }

    /**
     * @return the justificativa
     */
    public String getJustificativa() {
        return justificativa;
    }

    /**
     * @param justificativa the justificativa to set
     */
    public void setJustificativa(String justificativa) {
        this.justificativa = justificativa;
    }


}
