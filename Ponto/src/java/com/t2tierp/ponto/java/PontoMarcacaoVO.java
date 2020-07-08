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
@Table(name = "PONTO_MARCACAO")
public class PontoMarcacaoVO extends ValueObjectImpl implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "NSR")
    private Integer nsr;
    @Temporal(TemporalType.DATE)
    @Column(name = "DATA_MARCACAO")
    private Date dataMarcacao;
    @Column(name = "HORA_MARCACAO")
    private String horaMarcacao;
    @Column(name = "TIPO_MARCACAO")
    private String tipoMarcacao;
    @Column(name = "TIPO_REGISTRO")
    private String tipoRegistro;
    @Column(name = "PAR_ENTRADA_SAIDA")
    private String parEntradaSaida;
    @Column(name = "JUSTIFICATIVA")
    private String justificativa;
    @JoinColumn(name = "ID_PONTO_RELOGIO", referencedColumnName = "ID")
    @ManyToOne
    private PontoRelogioVO pontoRelogio;
    @JoinColumn(name = "ID_COLABORADOR", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private ColaboradorVO colaborador;

    public PontoMarcacaoVO() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getNsr() {
        return nsr;
    }

    public void setNsr(Integer nsr) {
        this.nsr = nsr;
    }

    public Date getDataMarcacao() {
        return dataMarcacao;
    }

    public void setDataMarcacao(Date dataMarcacao) {
        this.dataMarcacao = dataMarcacao;
    }

    public String getHoraMarcacao() {
        return horaMarcacao;
    }

    public void setHoraMarcacao(String horaMarcacao) {
        this.horaMarcacao = horaMarcacao;
    }

    public String getTipoMarcacao() {
        return tipoMarcacao;
    }

    public void setTipoMarcacao(String tipoMarcacao) {
        this.tipoMarcacao = tipoMarcacao;
    }

    public String getTipoRegistro() {
        return tipoRegistro;
    }

    public void setTipoRegistro(String tipoRegistro) {
        this.tipoRegistro = tipoRegistro;
    }

    public String getParEntradaSaida() {
        return parEntradaSaida;
    }

    public void setParEntradaSaida(String parEntradaSaida) {
        this.parEntradaSaida = parEntradaSaida;
    }

    public String getJustificativa() {
        return justificativa;
    }

    public void setJustificativa(String justificativa) {
        this.justificativa = justificativa;
    }

    public PontoRelogioVO getPontoRelogio() {
        return pontoRelogio;
    }

    public void setPontoRelogio(PontoRelogioVO pontoRelogio) {
        this.pontoRelogio = pontoRelogio;
    }

    public ColaboradorVO getColaborador() {
        return colaborador;
    }

    public void setColaborador(ColaboradorVO colaborador) {
        this.colaborador = colaborador;
    }


    @Override
    public String toString() {
        return "com.t2tierp.ponto.java.PontoMarcacaoVO[id=" + id + "]";
    }

}
