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
package com.t2tierp.wms.java;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.openswing.swing.message.receive.java.ValueObjectImpl;


@Entity
@Table(name = "WMS_RECEBIMENTO_CABECALHO")
public class WmsRecebimentoCabecalhoVO extends ValueObjectImpl implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Temporal(TemporalType.DATE)
    @Column(name = "DATA_RECEBIMENTO")
    private Date dataRecebimento;
    @Column(name = "HORA_INICIO")
    private String horaInicio;
    @Column(name = "HORA_FIM")
    private String horaFim;
    @Column(name = "VOLUME_RECEBIDO")
    private Integer volumeRecebido;
    @Column(name = "PESO_RECEBIDO")
    private BigDecimal pesoRecebido;
    @Column(name = "INCONSISTENCIA")
    private String inconsistencia;
    @Column(name = "OBSERVACAO")
    private String observacao;
    @JoinColumn(name = "ID_WMS_AGENDAMENTO", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private WmsAgendamentoVO wmsAgendamento;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "wmsRecebimentoCabecalho", cascade = CascadeType.ALL, orphanRemoval = true)
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<WmsRecebimentoDetalheVO> listaWmsRecebimentoDetalhe;

    public WmsRecebimentoCabecalhoVO() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDataRecebimento() {
        return dataRecebimento;
    }

    public void setDataRecebimento(Date dataRecebimento) {
        this.dataRecebimento = dataRecebimento;
    }

    public String getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(String horaInicio) {
        this.horaInicio = horaInicio;
    }

    public String getHoraFim() {
        return horaFim;
    }

    public void setHoraFim(String horaFim) {
        this.horaFim = horaFim;
    }

    public Integer getVolumeRecebido() {
        return volumeRecebido;
    }

    public void setVolumeRecebido(Integer volumeRecebido) {
        this.volumeRecebido = volumeRecebido;
    }

    public BigDecimal getPesoRecebido() {
        return pesoRecebido;
    }

    public void setPesoRecebido(BigDecimal pesoRecebido) {
        this.pesoRecebido = pesoRecebido;
    }

    public String getInconsistencia() {
        return inconsistencia;
    }

    public void setInconsistencia(String inconsistencia) {
        this.inconsistencia = inconsistencia;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public WmsAgendamentoVO getWmsAgendamento() {
        return wmsAgendamento;
    }

    public void setWmsAgendamento(WmsAgendamentoVO wmsAgendamento) {
        this.wmsAgendamento = wmsAgendamento;
    }


    @Override
    public String toString() {
        return "com.t2tierp.wms.java.WmsRecebimentoCabecalhoVO[id=" + id + "]";
    }

    public List<WmsRecebimentoDetalheVO> getListaWmsRecebimentoDetalhe() {
        return listaWmsRecebimentoDetalhe;
    }

    public void setListaWmsRecebimentoDetalhe(List<WmsRecebimentoDetalheVO> listaWmsRecebimentoDetalhe) {
        this.listaWmsRecebimentoDetalhe = listaWmsRecebimentoDetalhe;
    }

}
