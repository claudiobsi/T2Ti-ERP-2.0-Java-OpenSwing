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
package com.t2tierp.nfse.java;

import com.t2tierp.cadastros.java.ClienteVO;
import com.t2tierp.cadastros.java.EmpresaVO;
import com.t2tierp.ordemservico.java.OsAberturaVO;
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
@Table(name = "NFSE_CABECALHO")
public class NfseCabecalhoVO extends ValueObjectImpl implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "NUMERO")
    private String numero;
    @Column(name = "CODIGO_VERIFICACAO")
    private String codigoVerificacao;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DATA_HORA_EMISSAO")
    private Date dataHoraEmissao;
    @Column(name = "COMPETENCIA")
    private String competencia;
    @Column(name = "NUMERO_SUBSTITUIDA")
    private String numeroSubstituida;
    @Column(name = "NATUREZA_OPERACAO")
    private Integer naturezaOperacao;
    @Column(name = "REGIME_ESPECIAL_TRIBUTACAO")
    private Integer regimeEspecialTributacao;
    @Column(name = "OPTANTE_SIMPLES_NACIONAL")
    private String optanteSimplesNacional;
    @Column(name = "INCENTIVADOR_CULTURAL")
    private String incentivadorCultural;
    @Column(name = "NUMERO_RPS")
    private String numeroRps;
    @Column(name = "SERIE_RPS")
    private String serieRps;
    @Column(name = "TIPO_RPS")
    private Integer tipoRps;
    @Temporal(TemporalType.DATE)
    @Column(name = "DATA_EMISSAO_RPS")
    private Date dataEmissaoRps;
    @Column(name = "OUTRAS_INFORMACOES")
    private String outrasInformacoes;
    @Column(name = "CODIGO_OBRA")
    private String codigoObra;
    @Column(name = "NUMERO_ART")
    private String numeroArt;
    @JoinColumn(name = "ID_EMPRESA", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private EmpresaVO empresa;
    @JoinColumn(name = "ID_CLIENTE", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private ClienteVO cliente;
    @JoinColumn(name = "ID_OS_ABERTURA", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private OsAberturaVO osAbertura;

    public NfseCabecalhoVO() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getCodigoVerificacao() {
        return codigoVerificacao;
    }

    public void setCodigoVerificacao(String codigoVerificacao) {
        this.codigoVerificacao = codigoVerificacao;
    }

    public Date getDataHoraEmissao() {
        return dataHoraEmissao;
    }

    public void setDataHoraEmissao(Date dataHoraEmissao) {
        this.dataHoraEmissao = dataHoraEmissao;
    }

    public String getCompetencia() {
        return competencia;
    }

    public void setCompetencia(String competencia) {
        this.competencia = competencia;
    }

    public String getNumeroSubstituida() {
        return numeroSubstituida;
    }

    public void setNumeroSubstituida(String numeroSubstituida) {
        this.numeroSubstituida = numeroSubstituida;
    }

    public Integer getNaturezaOperacao() {
        return naturezaOperacao;
    }

    public void setNaturezaOperacao(Integer naturezaOperacao) {
        this.naturezaOperacao = naturezaOperacao;
    }

    public Integer getRegimeEspecialTributacao() {
        return regimeEspecialTributacao;
    }

    public void setRegimeEspecialTributacao(Integer regimeEspecialTributacao) {
        this.regimeEspecialTributacao = regimeEspecialTributacao;
    }

    public String getOptanteSimplesNacional() {
        return optanteSimplesNacional;
    }

    public void setOptanteSimplesNacional(String optanteSimplesNacional) {
        this.optanteSimplesNacional = optanteSimplesNacional;
    }

    public String getIncentivadorCultural() {
        return incentivadorCultural;
    }

    public void setIncentivadorCultural(String incentivadorCultural) {
        this.incentivadorCultural = incentivadorCultural;
    }

    public String getNumeroRps() {
        return numeroRps;
    }

    public void setNumeroRps(String numeroRps) {
        this.numeroRps = numeroRps;
    }

    public String getSerieRps() {
        return serieRps;
    }

    public void setSerieRps(String serieRps) {
        this.serieRps = serieRps;
    }

    public Integer getTipoRps() {
        return tipoRps;
    }

    public void setTipoRps(Integer tipoRps) {
        this.tipoRps = tipoRps;
    }

    public Date getDataEmissaoRps() {
        return dataEmissaoRps;
    }

    public void setDataEmissaoRps(Date dataEmissaoRps) {
        this.dataEmissaoRps = dataEmissaoRps;
    }

    public String getOutrasInformacoes() {
        return outrasInformacoes;
    }

    public void setOutrasInformacoes(String outrasInformacoes) {
        this.outrasInformacoes = outrasInformacoes;
    }

    public String getCodigoObra() {
        return codigoObra;
    }

    public void setCodigoObra(String codigoObra) {
        this.codigoObra = codigoObra;
    }

    public String getNumeroArt() {
        return numeroArt;
    }

    public void setNumeroArt(String numeroArt) {
        this.numeroArt = numeroArt;
    }

    public EmpresaVO getEmpresa() {
        return empresa;
    }

    public void setEmpresa(EmpresaVO empresa) {
        this.empresa = empresa;
    }

    public ClienteVO getCliente() {
        return cliente;
    }

    public void setCliente(ClienteVO cliente) {
        this.cliente = cliente;
    }

    public OsAberturaVO getOsAbertura() {
        return osAbertura;
    }

    public void setOsAbertura(OsAberturaVO osAbertura) {
        this.osAbertura = osAbertura;
    }


    @Override
    public String toString() {
        return "com.t2tierp.nfse.java.NfseCabecalhoVO[id=" + id + "]";
    }

}
