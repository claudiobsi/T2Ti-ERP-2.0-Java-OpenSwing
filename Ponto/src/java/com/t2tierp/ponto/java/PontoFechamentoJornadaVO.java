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
import java.math.BigDecimal;
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
@Table(name = "PONTO_FECHAMENTO_JORNADA")
public class PontoFechamentoJornadaVO extends ValueObjectImpl implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Temporal(TemporalType.DATE)
    @Column(name = "DATA_FECHAMENTO")
    private Date dataFechamento;
    @Column(name = "DIA_SEMANA")
    private String diaSemana;
    @Column(name = "CODIGO_HORARIO")
    private String codigoHorario;
    @Column(name = "CARGA_HORARIA_ESPERADA")
    private String cargaHorariaEsperada;
    @Column(name = "CARGA_HORARIA_DIURNA")
    private String cargaHorariaDiurna;
    @Column(name = "CARGA_HORARIA_NOTURNA")
    private String cargaHorariaNoturna;
    @Column(name = "CARGA_HORARIA_TOTAL")
    private String cargaHorariaTotal;
    @Column(name = "ENTRADA01")
    private String entrada01;
    @Column(name = "SAIDA01")
    private String saida01;
    @Column(name = "ENTRADA02")
    private String entrada02;
    @Column(name = "SAIDA02")
    private String saida02;
    @Column(name = "ENTRADA03")
    private String entrada03;
    @Column(name = "SAIDA03")
    private String saida03;
    @Column(name = "ENTRADA04")
    private String entrada04;
    @Column(name = "SAIDA04")
    private String saida04;
    @Column(name = "ENTRADA05")
    private String entrada05;
    @Column(name = "SAIDA05")
    private String saida05;
    @Column(name = "HORA_INICIO_JORNADA")
    private String horaInicioJornada;
    @Column(name = "HORA_FIM_JORNADA")
    private String horaFimJornada;
    @Column(name = "HORA_EXTRA01")
    private String horaExtra01;
    @Column(name = "PERCENTUAL_HORA_EXTRA01")
    private BigDecimal percentualHoraExtra01;
    @Column(name = "MODALIDADE_HORA_EXTRA01")
    private String modalidadeHoraExtra01;
    @Column(name = "HORA_EXTRA02")
    private String horaExtra02;
    @Column(name = "PERCENTUAL_HORA_EXTRA02")
    private BigDecimal percentualHoraExtra02;
    @Column(name = "MODALIDADE_HORA_EXTRA02")
    private String modalidadeHoraExtra02;
    @Column(name = "HORA_EXTRA03")
    private String horaExtra03;
    @Column(name = "PERCENTUAL_HORA_EXTRA03")
    private BigDecimal percentualHoraExtra03;
    @Column(name = "MODALIDADE_HORA_EXTRA03")
    private String modalidadeHoraExtra03;
    @Column(name = "HORA_EXTRA04")
    private String horaExtra04;
    @Column(name = "PERCENTUAL_HORA_EXTRA04")
    private BigDecimal percentualHoraExtra04;
    @Column(name = "MODALIDADE_HORA_EXTRA04")
    private String modalidadeHoraExtra04;
    @Column(name = "FALTA_ATRASO")
    private String faltaAtraso;
    @Column(name = "COMPENSAR")
    private String compensar;
    @Column(name = "BANCO_HORAS")
    private String bancoHoras;
    @Column(name = "OBSERVACAO")
    private String observacao;
    @JoinColumn(name = "ID_COLABORADOR", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private ColaboradorVO colaborador;
    @JoinColumn(name = "ID_PONTO_CLASSIFICACAO_JORNADA", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private PontoClassificacaoJornadaVO pontoClassificacaoJornada;

    public PontoFechamentoJornadaVO() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDataFechamento() {
        return dataFechamento;
    }

    public void setDataFechamento(Date dataFechamento) {
        this.dataFechamento = dataFechamento;
    }

    public String getDiaSemana() {
        return diaSemana;
    }

    public void setDiaSemana(String diaSemana) {
        this.diaSemana = diaSemana;
    }

    public String getCodigoHorario() {
        return codigoHorario;
    }

    public void setCodigoHorario(String codigoHorario) {
        this.codigoHorario = codigoHorario;
    }

    public String getCargaHorariaEsperada() {
        return cargaHorariaEsperada;
    }

    public void setCargaHorariaEsperada(String cargaHorariaEsperada) {
        this.cargaHorariaEsperada = cargaHorariaEsperada;
    }

    public String getCargaHorariaDiurna() {
        return cargaHorariaDiurna;
    }

    public void setCargaHorariaDiurna(String cargaHorariaDiurna) {
        this.cargaHorariaDiurna = cargaHorariaDiurna;
    }

    public String getCargaHorariaNoturna() {
        return cargaHorariaNoturna;
    }

    public void setCargaHorariaNoturna(String cargaHorariaNoturna) {
        this.cargaHorariaNoturna = cargaHorariaNoturna;
    }

    public String getCargaHorariaTotal() {
        return cargaHorariaTotal;
    }

    public void setCargaHorariaTotal(String cargaHorariaTotal) {
        this.cargaHorariaTotal = cargaHorariaTotal;
    }

    public String getEntrada01() {
        return entrada01;
    }

    public void setEntrada01(String entrada01) {
        this.entrada01 = entrada01;
    }

    public String getSaida01() {
        return saida01;
    }

    public void setSaida01(String saida01) {
        this.saida01 = saida01;
    }

    public String getEntrada02() {
        return entrada02;
    }

    public void setEntrada02(String entrada02) {
        this.entrada02 = entrada02;
    }

    public String getSaida02() {
        return saida02;
    }

    public void setSaida02(String saida02) {
        this.saida02 = saida02;
    }

    public String getEntrada03() {
        return entrada03;
    }

    public void setEntrada03(String entrada03) {
        this.entrada03 = entrada03;
    }

    public String getSaida03() {
        return saida03;
    }

    public void setSaida03(String saida03) {
        this.saida03 = saida03;
    }

    public String getEntrada04() {
        return entrada04;
    }

    public void setEntrada04(String entrada04) {
        this.entrada04 = entrada04;
    }

    public String getSaida04() {
        return saida04;
    }

    public void setSaida04(String saida04) {
        this.saida04 = saida04;
    }

    public String getEntrada05() {
        return entrada05;
    }

    public void setEntrada05(String entrada05) {
        this.entrada05 = entrada05;
    }

    public String getSaida05() {
        return saida05;
    }

    public void setSaida05(String saida05) {
        this.saida05 = saida05;
    }

    public String getHoraInicioJornada() {
        return horaInicioJornada;
    }

    public void setHoraInicioJornada(String horaInicioJornada) {
        this.horaInicioJornada = horaInicioJornada;
    }

    public String getHoraFimJornada() {
        return horaFimJornada;
    }

    public void setHoraFimJornada(String horaFimJornada) {
        this.horaFimJornada = horaFimJornada;
    }

    public String getHoraExtra01() {
        return horaExtra01;
    }

    public void setHoraExtra01(String horaExtra01) {
        this.horaExtra01 = horaExtra01;
    }

    public BigDecimal getPercentualHoraExtra01() {
        return percentualHoraExtra01;
    }

    public void setPercentualHoraExtra01(BigDecimal percentualHoraExtra01) {
        this.percentualHoraExtra01 = percentualHoraExtra01;
    }

    public String getModalidadeHoraExtra01() {
        return modalidadeHoraExtra01;
    }

    public void setModalidadeHoraExtra01(String modalidadeHoraExtra01) {
        this.modalidadeHoraExtra01 = modalidadeHoraExtra01;
    }

    public String getHoraExtra02() {
        return horaExtra02;
    }

    public void setHoraExtra02(String horaExtra02) {
        this.horaExtra02 = horaExtra02;
    }

    public BigDecimal getPercentualHoraExtra02() {
        return percentualHoraExtra02;
    }

    public void setPercentualHoraExtra02(BigDecimal percentualHoraExtra02) {
        this.percentualHoraExtra02 = percentualHoraExtra02;
    }

    public String getModalidadeHoraExtra02() {
        return modalidadeHoraExtra02;
    }

    public void setModalidadeHoraExtra02(String modalidadeHoraExtra02) {
        this.modalidadeHoraExtra02 = modalidadeHoraExtra02;
    }

    public String getHoraExtra03() {
        return horaExtra03;
    }

    public void setHoraExtra03(String horaExtra03) {
        this.horaExtra03 = horaExtra03;
    }

    public BigDecimal getPercentualHoraExtra03() {
        return percentualHoraExtra03;
    }

    public void setPercentualHoraExtra03(BigDecimal percentualHoraExtra03) {
        this.percentualHoraExtra03 = percentualHoraExtra03;
    }

    public String getModalidadeHoraExtra03() {
        return modalidadeHoraExtra03;
    }

    public void setModalidadeHoraExtra03(String modalidadeHoraExtra03) {
        this.modalidadeHoraExtra03 = modalidadeHoraExtra03;
    }

    public String getHoraExtra04() {
        return horaExtra04;
    }

    public void setHoraExtra04(String horaExtra04) {
        this.horaExtra04 = horaExtra04;
    }

    public BigDecimal getPercentualHoraExtra04() {
        return percentualHoraExtra04;
    }

    public void setPercentualHoraExtra04(BigDecimal percentualHoraExtra04) {
        this.percentualHoraExtra04 = percentualHoraExtra04;
    }

    public String getModalidadeHoraExtra04() {
        return modalidadeHoraExtra04;
    }

    public void setModalidadeHoraExtra04(String modalidadeHoraExtra04) {
        this.modalidadeHoraExtra04 = modalidadeHoraExtra04;
    }

    public String getFaltaAtraso() {
        return faltaAtraso;
    }

    public void setFaltaAtraso(String faltaAtraso) {
        this.faltaAtraso = faltaAtraso;
    }

    public String getCompensar() {
        return compensar;
    }

    public void setCompensar(String compensar) {
        this.compensar = compensar;
    }

    public String getBancoHoras() {
        return bancoHoras;
    }

    public void setBancoHoras(String bancoHoras) {
        this.bancoHoras = bancoHoras;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public ColaboradorVO getColaborador() {
        return colaborador;
    }

    public void setColaborador(ColaboradorVO colaborador) {
        this.colaborador = colaborador;
    }

    public PontoClassificacaoJornadaVO getPontoClassificacaoJornada() {
        return pontoClassificacaoJornada;
    }

    public void setPontoClassificacaoJornada(PontoClassificacaoJornadaVO pontoClassificacaoJornada) {
        this.pontoClassificacaoJornada = pontoClassificacaoJornada;
    }


    @Override
    public String toString() {
        return "com.t2tierp.ponto.java.PontoFechamentoJornadaVO[id=" + id + "]";
    }

}
