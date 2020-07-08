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
package com.t2tierp.cte.java;

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
@Table(name = "CTE_AEREO")
public class CteAereoVO extends ValueObjectImpl implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "NUMERO_MINUTA")
    private Integer numeroMinuta;
    @Column(name = "NUMERO_CONHECIMENTO")
    private Integer numeroConhecimento;
    @Temporal(TemporalType.DATE)
    @Column(name = "DATA_PREVISTA_ENTREGA")
    private Date dataPrevistaEntrega;
    @Column(name = "ID_EMISSOR")
    private String idEmissor;
    @Column(name = "ID_INTERNA_TOMADOR")
    private String idInternaTomador;
    @Column(name = "TARIFA_CLASSE")
    private String tarifaClasse;
    @Column(name = "TARIFA_CODIGO")
    private String tarifaCodigo;
    @Column(name = "TARIFA_VALOR")
    private BigDecimal tarifaValor;
    @Column(name = "CARGA_DIMENSAO")
    private String cargaDimensao;
    @Column(name = "CARGA_INFORMACAO_MANUSEIO")
    private Integer cargaInformacaoManuseio;
    @Column(name = "CARGA_ESPECIAL")
    private String cargaEspecial;
    @JoinColumn(name = "ID_CTE_CABECALHO", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private CteCabecalhoVO cteCabecalho;

    public CteAereoVO() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getNumeroMinuta() {
        return numeroMinuta;
    }

    public void setNumeroMinuta(Integer numeroMinuta) {
        this.numeroMinuta = numeroMinuta;
    }

    public Integer getNumeroConhecimento() {
        return numeroConhecimento;
    }

    public void setNumeroConhecimento(Integer numeroConhecimento) {
        this.numeroConhecimento = numeroConhecimento;
    }

    public Date getDataPrevistaEntrega() {
        return dataPrevistaEntrega;
    }

    public void setDataPrevistaEntrega(Date dataPrevistaEntrega) {
        this.dataPrevistaEntrega = dataPrevistaEntrega;
    }

    public String getIdEmissor() {
        return idEmissor;
    }

    public void setIdEmissor(String idEmissor) {
        this.idEmissor = idEmissor;
    }

    public String getIdInternaTomador() {
        return idInternaTomador;
    }

    public void setIdInternaTomador(String idInternaTomador) {
        this.idInternaTomador = idInternaTomador;
    }

    public String getTarifaClasse() {
        return tarifaClasse;
    }

    public void setTarifaClasse(String tarifaClasse) {
        this.tarifaClasse = tarifaClasse;
    }

    public String getTarifaCodigo() {
        return tarifaCodigo;
    }

    public void setTarifaCodigo(String tarifaCodigo) {
        this.tarifaCodigo = tarifaCodigo;
    }

    public BigDecimal getTarifaValor() {
        return tarifaValor;
    }

    public void setTarifaValor(BigDecimal tarifaValor) {
        this.tarifaValor = tarifaValor;
    }

    public String getCargaDimensao() {
        return cargaDimensao;
    }

    public void setCargaDimensao(String cargaDimensao) {
        this.cargaDimensao = cargaDimensao;
    }

    public Integer getCargaInformacaoManuseio() {
        return cargaInformacaoManuseio;
    }

    public void setCargaInformacaoManuseio(Integer cargaInformacaoManuseio) {
        this.cargaInformacaoManuseio = cargaInformacaoManuseio;
    }

    public String getCargaEspecial() {
        return cargaEspecial;
    }

    public void setCargaEspecial(String cargaEspecial) {
        this.cargaEspecial = cargaEspecial;
    }

    public CteCabecalhoVO getCteCabecalho() {
        return cteCabecalho;
    }

    public void setCteCabecalho(CteCabecalhoVO cteCabecalho) {
        this.cteCabecalho = cteCabecalho;
    }


    @Override
    public String toString() {
        return "com.t2tierp.cte.java.CteAereoVO[id=" + id + "]";
    }

}
