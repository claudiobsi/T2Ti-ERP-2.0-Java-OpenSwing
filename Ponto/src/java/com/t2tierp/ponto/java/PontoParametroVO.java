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

import com.t2tierp.cadastros.java.EmpresaVO;
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
@Table(name = "PONTO_PARAMETRO")
public class PontoParametroVO extends ValueObjectImpl implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "MES_ANO")
    private String mesAno;
    @Column(name = "DIA_INICIAL_APURACAO")
    private Integer diaInicialApuracao;
    @Column(name = "HORA_NOTURNA_INICIO")
    private String horaNoturnaInicio;
    @Column(name = "HORA_NOTURNA_FIM")
    private String horaNoturnaFim;
    @Column(name = "PERIODO_MINIMO_INTERJORNADA")
    private String periodoMinimoInterjornada;
    @Column(name = "PERCENTUAL_HE_DIURNA")
    private BigDecimal percentualHeDiurna;
    @Column(name = "PERCENTUAL_HE_NOTURNA")
    private BigDecimal percentualHeNoturna;
    @Column(name = "DURACAO_HORA_NOTURNA")
    private String duracaoHoraNoturna;
    @Column(name = "TRATAMENTO_HORA_MAIS")
    private String tratamentoHoraMais;
    @Column(name = "TRATAMENTO_HORA_MENOS")
    private String tratamentoHoraMenos;
    @JoinColumn(name = "ID_EMPRESA", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private EmpresaVO empresa;

    public PontoParametroVO() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMesAno() {
        return mesAno;
    }

    public void setMesAno(String mesAno) {
        this.mesAno = mesAno;
    }

    public Integer getDiaInicialApuracao() {
        return diaInicialApuracao;
    }

    public void setDiaInicialApuracao(Integer diaInicialApuracao) {
        this.diaInicialApuracao = diaInicialApuracao;
    }

    public String getHoraNoturnaInicio() {
        return horaNoturnaInicio;
    }

    public void setHoraNoturnaInicio(String horaNoturnaInicio) {
        this.horaNoturnaInicio = horaNoturnaInicio;
    }

    public String getHoraNoturnaFim() {
        return horaNoturnaFim;
    }

    public void setHoraNoturnaFim(String horaNoturnaFim) {
        this.horaNoturnaFim = horaNoturnaFim;
    }

    public String getPeriodoMinimoInterjornada() {
        return periodoMinimoInterjornada;
    }

    public void setPeriodoMinimoInterjornada(String periodoMinimoInterjornada) {
        this.periodoMinimoInterjornada = periodoMinimoInterjornada;
    }

    public BigDecimal getPercentualHeDiurna() {
        return percentualHeDiurna;
    }

    public void setPercentualHeDiurna(BigDecimal percentualHeDiurna) {
        this.percentualHeDiurna = percentualHeDiurna;
    }

    public BigDecimal getPercentualHeNoturna() {
        return percentualHeNoturna;
    }

    public void setPercentualHeNoturna(BigDecimal percentualHeNoturna) {
        this.percentualHeNoturna = percentualHeNoturna;
    }

    public String getDuracaoHoraNoturna() {
        return duracaoHoraNoturna;
    }

    public void setDuracaoHoraNoturna(String duracaoHoraNoturna) {
        this.duracaoHoraNoturna = duracaoHoraNoturna;
    }

    public String getTratamentoHoraMais() {
        return tratamentoHoraMais;
    }

    public void setTratamentoHoraMais(String tratamentoHoraMais) {
        this.tratamentoHoraMais = tratamentoHoraMais;
    }

    public String getTratamentoHoraMenos() {
        return tratamentoHoraMenos;
    }

    public void setTratamentoHoraMenos(String tratamentoHoraMenos) {
        this.tratamentoHoraMenos = tratamentoHoraMenos;
    }

    public EmpresaVO getEmpresa() {
        return empresa;
    }

    public void setEmpresa(EmpresaVO empresa) {
        this.empresa = empresa;
    }


    @Override
    public String toString() {
        return "com.t2tierp.ponto.java.PontoParametroVO[id=" + id + "]";
    }

}
