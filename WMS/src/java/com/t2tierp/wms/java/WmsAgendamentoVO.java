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

import com.t2tierp.cadastros.java.EmpresaVO;
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
@Table(name = "WMS_AGENDAMENTO")
public class WmsAgendamentoVO extends ValueObjectImpl implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Temporal(TemporalType.DATE)
    @Column(name = "DATA_OPERACAO")
    private Date dataOperacao;
    @Column(name = "HORA_OPERACAO")
    private String horaOperacao;
    @Column(name = "LOCAL_OPERACAO")
    private String localOperacao;
    @Column(name = "QUANTIDADE_VOLUME")
    private Integer quantidadeVolume;
    @Column(name = "PESO_TOTAL_VOLUME")
    private BigDecimal pesoTotalVolume;
    @Column(name = "QUANTIDADE_PESSOA")
    private Integer quantidadePessoa;
    @Column(name = "QUANTIDADE_HORA")
    private Integer quantidadeHora;
    @JoinColumn(name = "ID_EMPRESA", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private EmpresaVO empresa;

    public WmsAgendamentoVO() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDataOperacao() {
        return dataOperacao;
    }

    public void setDataOperacao(Date dataOperacao) {
        this.dataOperacao = dataOperacao;
    }

    public String getHoraOperacao() {
        return horaOperacao;
    }

    public void setHoraOperacao(String horaOperacao) {
        this.horaOperacao = horaOperacao;
    }

    public String getLocalOperacao() {
        return localOperacao;
    }

    public void setLocalOperacao(String localOperacao) {
        this.localOperacao = localOperacao;
    }

    public Integer getQuantidadeVolume() {
        return quantidadeVolume;
    }

    public void setQuantidadeVolume(Integer quantidadeVolume) {
        this.quantidadeVolume = quantidadeVolume;
    }

    public BigDecimal getPesoTotalVolume() {
        return pesoTotalVolume;
    }

    public void setPesoTotalVolume(BigDecimal pesoTotalVolume) {
        this.pesoTotalVolume = pesoTotalVolume;
    }

    public Integer getQuantidadePessoa() {
        return quantidadePessoa;
    }

    public void setQuantidadePessoa(Integer quantidadePessoa) {
        this.quantidadePessoa = quantidadePessoa;
    }

    public Integer getQuantidadeHora() {
        return quantidadeHora;
    }

    public void setQuantidadeHora(Integer quantidadeHora) {
        this.quantidadeHora = quantidadeHora;
    }

    public EmpresaVO getEmpresa() {
        return empresa;
    }

    public void setEmpresa(EmpresaVO empresa) {
        this.empresa = empresa;
    }


    @Override
    public String toString() {
        return "com.t2tierp.wms.java.WmsAgendamentoVO[id=" + id + "]";
    }

}
