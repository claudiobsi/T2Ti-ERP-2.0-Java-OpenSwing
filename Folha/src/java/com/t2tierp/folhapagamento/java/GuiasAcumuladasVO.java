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
@Table(name = "GUIAS_ACUMULADAS")
public class GuiasAcumuladasVO extends ValueObjectImpl implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "GPS_TIPO")
    private String gpsTipo;
    @Column(name = "GPS_COMPETENCIA")
    private String gpsCompetencia;
    @Column(name = "GPS_VALOR_INSS")
    private BigDecimal gpsValorInss;
    @Column(name = "GPS_VALOR_OUTRAS_ENT")
    private BigDecimal gpsValorOutrasEnt;
    @Temporal(TemporalType.DATE)
    @Column(name = "GPS_DATA_PAGAMENTO")
    private Date gpsDataPagamento;
    @Column(name = "IRRF_COMPETENCIA")
    private String irrfCompetencia;
    @Column(name = "IRRF_CODIGO_RECOLHIMENTO")
    private Integer irrfCodigoRecolhimento;
    @Column(name = "IRRF_VALOR_ACUMULADO")
    private BigDecimal irrfValorAcumulado;
    @Temporal(TemporalType.DATE)
    @Column(name = "IRRF_DATA_PAGAMENTO")
    private Date irrfDataPagamento;
    @Column(name = "PIS_COMPETENCIA")
    private String pisCompetencia;
    @Column(name = "PIS_VALOR_ACUMULADO")
    private BigDecimal pisValorAcumulado;
    @Temporal(TemporalType.DATE)
    @Column(name = "PIS_DATA_PAGAMENTO")
    private Date pisDataPagamento;
    @JoinColumn(name = "ID_EMPRESA", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private EmpresaVO empresa;

    public GuiasAcumuladasVO() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getGpsTipo() {
        return gpsTipo;
    }

    public void setGpsTipo(String gpsTipo) {
        this.gpsTipo = gpsTipo;
    }

    public String getGpsCompetencia() {
        return gpsCompetencia;
    }

    public void setGpsCompetencia(String gpsCompetencia) {
        this.gpsCompetencia = gpsCompetencia;
    }

    public BigDecimal getGpsValorInss() {
        return gpsValorInss;
    }

    public void setGpsValorInss(BigDecimal gpsValorInss) {
        this.gpsValorInss = gpsValorInss;
    }

    public BigDecimal getGpsValorOutrasEnt() {
        return gpsValorOutrasEnt;
    }

    public void setGpsValorOutrasEnt(BigDecimal gpsValorOutrasEnt) {
        this.gpsValorOutrasEnt = gpsValorOutrasEnt;
    }

    public Date getGpsDataPagamento() {
        return gpsDataPagamento;
    }

    public void setGpsDataPagamento(Date gpsDataPagamento) {
        this.gpsDataPagamento = gpsDataPagamento;
    }

    public String getIrrfCompetencia() {
        return irrfCompetencia;
    }

    public void setIrrfCompetencia(String irrfCompetencia) {
        this.irrfCompetencia = irrfCompetencia;
    }

    public Integer getIrrfCodigoRecolhimento() {
        return irrfCodigoRecolhimento;
    }

    public void setIrrfCodigoRecolhimento(Integer irrfCodigoRecolhimento) {
        this.irrfCodigoRecolhimento = irrfCodigoRecolhimento;
    }

    public BigDecimal getIrrfValorAcumulado() {
        return irrfValorAcumulado;
    }

    public void setIrrfValorAcumulado(BigDecimal irrfValorAcumulado) {
        this.irrfValorAcumulado = irrfValorAcumulado;
    }

    public Date getIrrfDataPagamento() {
        return irrfDataPagamento;
    }

    public void setIrrfDataPagamento(Date irrfDataPagamento) {
        this.irrfDataPagamento = irrfDataPagamento;
    }

    public String getPisCompetencia() {
        return pisCompetencia;
    }

    public void setPisCompetencia(String pisCompetencia) {
        this.pisCompetencia = pisCompetencia;
    }

    public BigDecimal getPisValorAcumulado() {
        return pisValorAcumulado;
    }

    public void setPisValorAcumulado(BigDecimal pisValorAcumulado) {
        this.pisValorAcumulado = pisValorAcumulado;
    }

    public Date getPisDataPagamento() {
        return pisDataPagamento;
    }

    public void setPisDataPagamento(Date pisDataPagamento) {
        this.pisDataPagamento = pisDataPagamento;
    }

    public EmpresaVO getEmpresa() {
        return empresa;
    }

    public void setEmpresa(EmpresaVO empresa) {
        this.empresa = empresa;
    }


    @Override
    public String toString() {
        return "com.t2tierp.folhapagamento.java.GuiasAcumuladasVO[id=" + id + "]";
    }

}
