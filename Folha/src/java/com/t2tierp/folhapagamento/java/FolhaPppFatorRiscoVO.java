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
@Table(name = "FOLHA_PPP_FATOR_RISCO")
public class FolhaPppFatorRiscoVO extends ValueObjectImpl implements Serializable {

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
    @Column(name = "TIPO")
    private String tipo;
    @Column(name = "FATOR_RISCO")
    private String fatorRisco;
    @Column(name = "INTENSIDADE")
    private String intensidade;
    @Column(name = "TECNICA_UTILIZADA")
    private String tecnicaUtilizada;
    @Column(name = "EPC_EFICAZ")
    private String epcEficaz;
    @Column(name = "EPI_EFICAZ")
    private String epiEficaz;
    @Column(name = "CA_EPI")
    private Integer caEpi;
    @Column(name = "ATENDIMENTO_NR06_1")
    private String atendimentoNr061;
    @Column(name = "ATENDIMENTO_NR06_2")
    private String atendimentoNr062;
    @Column(name = "ATENDIMENTO_NR06_3")
    private String atendimentoNr063;
    @Column(name = "ATENDIMENTO_NR06_4")
    private String atendimentoNr064;
    @Column(name = "ATENDIMENTO_NR06_5")
    private String atendimentoNr065;
    @JoinColumn(name = "ID_FOLHA_PPP", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private FolhaPppVO folhaPpp;

    public FolhaPppFatorRiscoVO() {
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

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getFatorRisco() {
        return fatorRisco;
    }

    public void setFatorRisco(String fatorRisco) {
        this.fatorRisco = fatorRisco;
    }

    public String getIntensidade() {
        return intensidade;
    }

    public void setIntensidade(String intensidade) {
        this.intensidade = intensidade;
    }

    public String getTecnicaUtilizada() {
        return tecnicaUtilizada;
    }

    public void setTecnicaUtilizada(String tecnicaUtilizada) {
        this.tecnicaUtilizada = tecnicaUtilizada;
    }

    public String getEpcEficaz() {
        return epcEficaz;
    }

    public void setEpcEficaz(String epcEficaz) {
        this.epcEficaz = epcEficaz;
    }

    public String getEpiEficaz() {
        return epiEficaz;
    }

    public void setEpiEficaz(String epiEficaz) {
        this.epiEficaz = epiEficaz;
    }

    public Integer getCaEpi() {
        return caEpi;
    }

    public void setCaEpi(Integer caEpi) {
        this.caEpi = caEpi;
    }

    public String getAtendimentoNr061() {
        return atendimentoNr061;
    }

    public void setAtendimentoNr061(String atendimentoNr061) {
        this.atendimentoNr061 = atendimentoNr061;
    }

    public String getAtendimentoNr062() {
        return atendimentoNr062;
    }

    public void setAtendimentoNr062(String atendimentoNr062) {
        this.atendimentoNr062 = atendimentoNr062;
    }

    public String getAtendimentoNr063() {
        return atendimentoNr063;
    }

    public void setAtendimentoNr063(String atendimentoNr063) {
        this.atendimentoNr063 = atendimentoNr063;
    }

    public String getAtendimentoNr064() {
        return atendimentoNr064;
    }

    public void setAtendimentoNr064(String atendimentoNr064) {
        this.atendimentoNr064 = atendimentoNr064;
    }

    public String getAtendimentoNr065() {
        return atendimentoNr065;
    }

    public void setAtendimentoNr065(String atendimentoNr065) {
        this.atendimentoNr065 = atendimentoNr065;
    }

    public FolhaPppVO getFolhaPpp() {
        return folhaPpp;
    }

    public void setFolhaPpp(FolhaPppVO folhaPpp) {
        this.folhaPpp = folhaPpp;
    }


    @Override
    public String toString() {
        return "com.t2tierp.folhapagamento.java.FolhaPppFatorRiscoVO[id=" + id + "]";
    }

}
