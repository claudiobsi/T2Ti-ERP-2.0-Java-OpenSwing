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

import com.t2tierp.cadastros.java.EmpresaVO;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.openswing.swing.message.receive.java.ValueObjectImpl;

@Entity
@Table(name = "CTE_CABECALHO")
public class CteCabecalhoVO extends ValueObjectImpl implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "UF_EMITENTE")
    private Integer ufEmitente;
    @Column(name = "CODIGO_NUMERICO")
    private String codigoNumerico;
    @Column(name = "CFOP")
    private Integer cfop;
    @Column(name = "NATUREZA_OPERACAO")
    private String naturezaOperacao;
    @Column(name = "FORMA_PAGAMENTO")
    private Integer formaPagamento;
    @Column(name = "MODELO")
    private String modelo;
    @Column(name = "SERIE")
    private String serie;
    @Column(name = "NUMERO")
    private String numero;
    @Column(name = "DATA_HORA_EMISSAO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataHoraEmissao;
    @Column(name = "FORMATO_IMPRESSAO_DACTE")
    private Integer formatoImpressaoDacte;
    @Column(name = "TIPO_EMISSAO")
    private Integer tipoEmissao;
    @Column(name = "CHAVE_ACESSO")
    private String chaveAcesso;
    @Column(name = "DIGITO_CHAVE_ACESSO")
    private String digitoChaveAcesso;
    @Column(name = "AMBIENTE")
    private Integer ambiente;
    @Column(name = "TIPO_CTE")
    private Integer tipoCte;
    @Column(name = "PROCESSO_EMISSAO")
    private Integer processoEmissao;
    @Column(name = "VERSAO_PROCESSO_EMISSAO")
    private String versaoProcessoEmissao;
    @Column(name = "CHAVE_REFERENCIADO")
    private String chaveReferenciado;
    @Column(name = "CODIGO_MUNICIPIO_ENVIO")
    private Integer codigoMunicipioEnvio;
    @Column(name = "NOME_MUNICIPIO_ENVIO")
    private String nomeMunicipioEnvio;
    @Column(name = "UF_ENVIO")
    private String ufEnvio;
    @Column(name = "MODAL")
    private String modal;
    @Column(name = "TIPO_SERVICO")
    private Integer tipoServico;
    @Column(name = "CODIGO_MUNICIPIO_INI_PRESTACAO")
    private Integer codigoMunicipioIniPrestacao;
    @Column(name = "NOME_MUNICIPIO_INI_PRESTACAO")
    private String nomeMunicipioIniPrestacao;
    @Column(name = "UF_INI_PRESTACAO")
    private String ufIniPrestacao;
    @Column(name = "CODIGO_MUNICIPIO_FIM_PRESTACAO")
    private Integer codigoMunicipioFimPrestacao;
    @Column(name = "NOME_MUNICIPIO_FIM_PRESTACAO")
    private String nomeMunicipioFimPrestacao;
    @Column(name = "UF_FIM_PRESTACAO")
    private String ufFimPrestacao;
    @Column(name = "RETIRA")
    private Integer retira;
    @Column(name = "RETIRA_DETALHE")
    private String retiraDetalhe;
    @Column(name = "TOMADOR")
    private Integer tomador;
    @Column(name = "DATA_ENTRADA_CONTINGENCIA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataEntradaContingencia;
    @Column(name = "JUSTIFICATIVA_CONTINGENCIA")
    private String justificativaContingencia;
    @Column(name = "CARAC_ADICIONAL_TRANSPORTE")
    private String caracAdicionalTransporte;
    @Column(name = "CARAC_ADICIONAL_SERVICO")
    private String caracAdicionalServico;
    @Column(name = "FUNCIONARIO_EMISSOR")
    private String funcionarioEmissor;
    @Column(name = "FLUXO_ORIGEM")
    private String fluxoOrigem;
    @Column(name = "ENTREGA_TIPO_PERIODO")
    private Integer entregaTipoPeriodo;
    @Temporal(TemporalType.DATE)
    @Column(name = "ENTREGA_DATA_PROGRAMADA")
    private Date entregaDataProgramada;
    @Temporal(TemporalType.DATE)
    @Column(name = "ENTREGA_DATA_INICIAL")
    private Date entregaDataInicial;
    @Temporal(TemporalType.DATE)
    @Column(name = "ENTREGA_DATA_FINAL")
    private Date entregaDataFinal;
    @Column(name = "ENTREGA_TIPO_HORA")
    private Integer entregaTipoHora;
    @Column(name = "ENTREGA_HORA_PROGRAMADA")
    private String entregaHoraProgramada;
    @Column(name = "ENTREGA_HORA_INICIAL")
    private String entregaHoraInicial;
    @Column(name = "ENTREGA_HORA_FINAL")
    private String entregaHoraFinal;
    @Column(name = "MUNICIPIO_ORIGEM_CALCULO")
    private String municipioOrigemCalculo;
    @Column(name = "MUNICIPIO_DESTINO_CALCULO")
    private String municipioDestinoCalculo;
    @Column(name = "OBSERVACOES_GERAIS")
    private String observacoesGerais;
    @Column(name = "VALOR_TOTAL_SERVICO")
    private BigDecimal valorTotalServico;
    @Column(name = "VALOR_RECEBER")
    private BigDecimal valorReceber;
    @Column(name = "CST")
    private String cst;
    @Column(name = "BASE_CALCULO_ICMS")
    private BigDecimal baseCalculoIcms;
    @Column(name = "ALIQUOTA_ICMS")
    private BigDecimal aliquotaIcms;
    @Column(name = "VALOR_ICMS")
    private BigDecimal valorIcms;
    @Column(name = "PERCENTUAL_REDUCAO_BC_ICMS")
    private BigDecimal percentualReducaoBcIcms;
    @Column(name = "VALOR_BC_ICMS_ST_RETIDO")
    private BigDecimal valorBcIcmsStRetido;
    @Column(name = "VALOR_ICMS_ST_RETIDO")
    private BigDecimal valorIcmsStRetido;
    @Column(name = "ALIQUOTA_ICMS_ST_RETIDO")
    private BigDecimal aliquotaIcmsStRetido;
    @Column(name = "VALOR_CREDITO_PRESUMIDO_ICMS")
    private BigDecimal valorCreditoPresumidoIcms;
    @Column(name = "PERCENTUAL_BC_ICMS_OUTRA_UF")
    private BigDecimal percentualBcIcmsOutraUf;
    @Column(name = "VALOR_BC_ICMS_OUTRA_UF")
    private BigDecimal valorBcIcmsOutraUf;
    @Column(name = "ALIQUOTA_ICMS_OUTRA_UF")
    private BigDecimal aliquotaIcmsOutraUf;
    @Column(name = "VALOR_ICMS_OUTRA_UF")
    private BigDecimal valorIcmsOutraUf;
    @Column(name = "SIMPLES_NACIONAL_INDICADOR")
    private Integer simplesNacionalIndicador;
    @Column(name = "SIMPLES_NACIONAL_TOTAL")
    private BigDecimal simplesNacionalTotal;
    @Column(name = "INFORMACOES_ADD_FISCO")
    private String informacoesAddFisco;
    @Column(name = "VALOR_TOTAL_CARGA")
    private BigDecimal valorTotalCarga;
    @Column(name = "PRODUTO_PREDOMINANTE")
    private String produtoPredominante;
    @Column(name = "CARGA_OUTRAS_CARACTERISTICAS")
    private String cargaOutrasCaracteristicas;
    @Column(name = "MODAL_VERSAO_LAYOUT")
    private Integer modalVersaoLayout;
    @Column(name = "CHAVE_CTE_SUBSTITUIDO")
    private String chaveCteSubstituido;
    @JoinColumn(name = "ID_EMPRESA", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private EmpresaVO empresa;
    @OneToOne(mappedBy = "cteCabecalho", cascade = CascadeType.ALL, orphanRemoval = true)
    private CteRodoviarioVO cteRodoviario;
    @OneToOne(mappedBy = "cteCabecalho", cascade = CascadeType.ALL, orphanRemoval = true)
    private CteEmitenteVO cteEmitente;
    @OneToOne(mappedBy = "cteCabecalho", cascade = CascadeType.ALL, orphanRemoval = true)
    private CteRemetenteVO cteRemetente;
    @OneToOne(mappedBy = "cteCabecalho", cascade = CascadeType.ALL, orphanRemoval = true)
    private CteDestinatarioVO cteDestinatario;
    @OneToMany(mappedBy="cteCabecalho", cascade = CascadeType.ALL, orphanRemoval = true)
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<CteCargaVO> listaCteCarga;
    @OneToMany(mappedBy="cteCabecalho", cascade = CascadeType.ALL, orphanRemoval = true)
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<CteInformacaoNfOutrosVO> listaCteInformacaoNfOutros;
    
    public CteCabecalhoVO() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUfEmitente() {
        return ufEmitente;
    }

    public void setUfEmitente(Integer ufEmitente) {
        this.ufEmitente = ufEmitente;
    }

    public String getCodigoNumerico() {
        return codigoNumerico;
    }

    public void setCodigoNumerico(String codigoNumerico) {
        this.codigoNumerico = codigoNumerico;
    }

    public Integer getCfop() {
        return cfop;
    }

    public void setCfop(Integer cfop) {
        this.cfop = cfop;
    }

    public String getNaturezaOperacao() {
        return naturezaOperacao;
    }

    public void setNaturezaOperacao(String naturezaOperacao) {
        this.naturezaOperacao = naturezaOperacao;
    }

    public Integer getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento(Integer formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getSerie() {
        return serie;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public Date getDataHoraEmissao() {
        return dataHoraEmissao;
    }

    public void setDataHoraEmissao(Date dataHoraEmissao) {
        this.dataHoraEmissao = dataHoraEmissao;
    }

    public Integer getFormatoImpressaoDacte() {
        return formatoImpressaoDacte;
    }

    public void setFormatoImpressaoDacte(Integer formatoImpressaoDacte) {
        this.formatoImpressaoDacte = formatoImpressaoDacte;
    }

    public Integer getTipoEmissao() {
        return tipoEmissao;
    }

    public void setTipoEmissao(Integer tipoEmissao) {
        this.tipoEmissao = tipoEmissao;
    }

    public String getChaveAcesso() {
        return chaveAcesso;
    }

    public void setChaveAcesso(String chaveAcesso) {
        this.chaveAcesso = chaveAcesso;
    }

    public String getDigitoChaveAcesso() {
        return digitoChaveAcesso;
    }

    public void setDigitoChaveAcesso(String digitoChaveAcesso) {
        this.digitoChaveAcesso = digitoChaveAcesso;
    }

    public Integer getAmbiente() {
        return ambiente;
    }

    public void setAmbiente(Integer ambiente) {
        this.ambiente = ambiente;
    }

    public Integer getTipoCte() {
        return tipoCte;
    }

    public void setTipoCte(Integer tipoCte) {
        this.tipoCte = tipoCte;
    }

    public Integer getProcessoEmissao() {
        return processoEmissao;
    }

    public void setProcessoEmissao(Integer processoEmissao) {
        this.processoEmissao = processoEmissao;
    }

    public String getVersaoProcessoEmissao() {
        return versaoProcessoEmissao;
    }

    public void setVersaoProcessoEmissao(String versaoProcessoEmissao) {
        this.versaoProcessoEmissao = versaoProcessoEmissao;
    }

    public String getChaveReferenciado() {
        return chaveReferenciado;
    }

    public void setChaveReferenciado(String chaveReferenciado) {
        this.chaveReferenciado = chaveReferenciado;
    }

    public Integer getCodigoMunicipioEnvio() {
        return codigoMunicipioEnvio;
    }

    public void setCodigoMunicipioEnvio(Integer codigoMunicipioEnvio) {
        this.codigoMunicipioEnvio = codigoMunicipioEnvio;
    }

    public String getNomeMunicipioEnvio() {
        return nomeMunicipioEnvio;
    }

    public void setNomeMunicipioEnvio(String nomeMunicipioEnvio) {
        this.nomeMunicipioEnvio = nomeMunicipioEnvio;
    }

    public String getUfEnvio() {
        return ufEnvio;
    }

    public void setUfEnvio(String ufEnvio) {
        this.ufEnvio = ufEnvio;
    }

    public String getModal() {
        return modal;
    }

    public void setModal(String modal) {
        this.modal = modal;
    }

    public Integer getTipoServico() {
        return tipoServico;
    }

    public void setTipoServico(Integer tipoServico) {
        this.tipoServico = tipoServico;
    }

    public Integer getCodigoMunicipioIniPrestacao() {
        return codigoMunicipioIniPrestacao;
    }

    public void setCodigoMunicipioIniPrestacao(Integer codigoMunicipioIniPrestacao) {
        this.codigoMunicipioIniPrestacao = codigoMunicipioIniPrestacao;
    }

    public String getNomeMunicipioIniPrestacao() {
        return nomeMunicipioIniPrestacao;
    }

    public void setNomeMunicipioIniPrestacao(String nomeMunicipioIniPrestacao) {
        this.nomeMunicipioIniPrestacao = nomeMunicipioIniPrestacao;
    }

    public String getUfIniPrestacao() {
        return ufIniPrestacao;
    }

    public void setUfIniPrestacao(String ufIniPrestacao) {
        this.ufIniPrestacao = ufIniPrestacao;
    }

    public Integer getCodigoMunicipioFimPrestacao() {
        return codigoMunicipioFimPrestacao;
    }

    public void setCodigoMunicipioFimPrestacao(Integer codigoMunicipioFimPrestacao) {
        this.codigoMunicipioFimPrestacao = codigoMunicipioFimPrestacao;
    }

    public String getNomeMunicipioFimPrestacao() {
        return nomeMunicipioFimPrestacao;
    }

    public void setNomeMunicipioFimPrestacao(String nomeMunicipioFimPrestacao) {
        this.nomeMunicipioFimPrestacao = nomeMunicipioFimPrestacao;
    }

    public String getUfFimPrestacao() {
        return ufFimPrestacao;
    }

    public void setUfFimPrestacao(String ufFimPrestacao) {
        this.ufFimPrestacao = ufFimPrestacao;
    }

    public Integer getRetira() {
        return retira;
    }

    public void setRetira(Integer retira) {
        this.retira = retira;
    }

    public String getRetiraDetalhe() {
        return retiraDetalhe;
    }

    public void setRetiraDetalhe(String retiraDetalhe) {
        this.retiraDetalhe = retiraDetalhe;
    }

    public Integer getTomador() {
        return tomador;
    }

    public void setTomador(Integer tomador) {
        this.tomador = tomador;
    }

    public Date getDataEntradaContingencia() {
        return dataEntradaContingencia;
    }

    public void setDataEntradaContingencia(Date dataEntradaContingencia) {
        this.dataEntradaContingencia = dataEntradaContingencia;
    }

    public String getJustificativaContingencia() {
        return justificativaContingencia;
    }

    public void setJustificativaContingencia(String justificativaContingencia) {
        this.justificativaContingencia = justificativaContingencia;
    }

    public String getCaracAdicionalTransporte() {
        return caracAdicionalTransporte;
    }

    public void setCaracAdicionalTransporte(String caracAdicionalTransporte) {
        this.caracAdicionalTransporte = caracAdicionalTransporte;
    }

    public String getCaracAdicionalServico() {
        return caracAdicionalServico;
    }

    public void setCaracAdicionalServico(String caracAdicionalServico) {
        this.caracAdicionalServico = caracAdicionalServico;
    }

    public String getFuncionarioEmissor() {
        return funcionarioEmissor;
    }

    public void setFuncionarioEmissor(String funcionarioEmissor) {
        this.funcionarioEmissor = funcionarioEmissor;
    }

    public String getFluxoOrigem() {
        return fluxoOrigem;
    }

    public void setFluxoOrigem(String fluxoOrigem) {
        this.fluxoOrigem = fluxoOrigem;
    }

    public Integer getEntregaTipoPeriodo() {
        return entregaTipoPeriodo;
    }

    public void setEntregaTipoPeriodo(Integer entregaTipoPeriodo) {
        this.entregaTipoPeriodo = entregaTipoPeriodo;
    }

    public Date getEntregaDataProgramada() {
        return entregaDataProgramada;
    }

    public void setEntregaDataProgramada(Date entregaDataProgramada) {
        this.entregaDataProgramada = entregaDataProgramada;
    }

    public Date getEntregaDataInicial() {
        return entregaDataInicial;
    }

    public void setEntregaDataInicial(Date entregaDataInicial) {
        this.entregaDataInicial = entregaDataInicial;
    }

    public Date getEntregaDataFinal() {
        return entregaDataFinal;
    }

    public void setEntregaDataFinal(Date entregaDataFinal) {
        this.entregaDataFinal = entregaDataFinal;
    }

    public Integer getEntregaTipoHora() {
        return entregaTipoHora;
    }

    public void setEntregaTipoHora(Integer entregaTipoHora) {
        this.entregaTipoHora = entregaTipoHora;
    }

    public String getEntregaHoraProgramada() {
        return entregaHoraProgramada;
    }

    public void setEntregaHoraProgramada(String entregaHoraProgramada) {
        this.entregaHoraProgramada = entregaHoraProgramada;
    }

    public String getEntregaHoraInicial() {
        return entregaHoraInicial;
    }

    public void setEntregaHoraInicial(String entregaHoraInicial) {
        this.entregaHoraInicial = entregaHoraInicial;
    }

    public String getEntregaHoraFinal() {
        return entregaHoraFinal;
    }

    public void setEntregaHoraFinal(String entregaHoraFinal) {
        this.entregaHoraFinal = entregaHoraFinal;
    }

    public String getMunicipioOrigemCalculo() {
        return municipioOrigemCalculo;
    }

    public void setMunicipioOrigemCalculo(String municipioOrigemCalculo) {
        this.municipioOrigemCalculo = municipioOrigemCalculo;
    }

    public String getMunicipioDestinoCalculo() {
        return municipioDestinoCalculo;
    }

    public void setMunicipioDestinoCalculo(String municipioDestinoCalculo) {
        this.municipioDestinoCalculo = municipioDestinoCalculo;
    }

    public String getObservacoesGerais() {
        return observacoesGerais;
    }

    public void setObservacoesGerais(String observacoesGerais) {
        this.observacoesGerais = observacoesGerais;
    }

    public BigDecimal getValorTotalServico() {
        return valorTotalServico;
    }

    public void setValorTotalServico(BigDecimal valorTotalServico) {
        this.valorTotalServico = valorTotalServico;
    }

    public BigDecimal getValorReceber() {
        return valorReceber;
    }

    public void setValorReceber(BigDecimal valorReceber) {
        this.valorReceber = valorReceber;
    }

    public String getCst() {
        return cst;
    }

    public void setCst(String cst) {
        this.cst = cst;
    }

    public BigDecimal getBaseCalculoIcms() {
        return baseCalculoIcms;
    }

    public void setBaseCalculoIcms(BigDecimal baseCalculoIcms) {
        this.baseCalculoIcms = baseCalculoIcms;
    }

    public BigDecimal getAliquotaIcms() {
        return aliquotaIcms;
    }

    public void setAliquotaIcms(BigDecimal aliquotaIcms) {
        this.aliquotaIcms = aliquotaIcms;
    }

    public BigDecimal getValorIcms() {
        return valorIcms;
    }

    public void setValorIcms(BigDecimal valorIcms) {
        this.valorIcms = valorIcms;
    }

    public BigDecimal getPercentualReducaoBcIcms() {
        return percentualReducaoBcIcms;
    }

    public void setPercentualReducaoBcIcms(BigDecimal percentualReducaoBcIcms) {
        this.percentualReducaoBcIcms = percentualReducaoBcIcms;
    }

    public BigDecimal getValorBcIcmsStRetido() {
        return valorBcIcmsStRetido;
    }

    public void setValorBcIcmsStRetido(BigDecimal valorBcIcmsStRetido) {
        this.valorBcIcmsStRetido = valorBcIcmsStRetido;
    }

    public BigDecimal getValorIcmsStRetido() {
        return valorIcmsStRetido;
    }

    public void setValorIcmsStRetido(BigDecimal valorIcmsStRetido) {
        this.valorIcmsStRetido = valorIcmsStRetido;
    }

    public BigDecimal getAliquotaIcmsStRetido() {
        return aliquotaIcmsStRetido;
    }

    public void setAliquotaIcmsStRetido(BigDecimal aliquotaIcmsStRetido) {
        this.aliquotaIcmsStRetido = aliquotaIcmsStRetido;
    }

    public BigDecimal getValorCreditoPresumidoIcms() {
        return valorCreditoPresumidoIcms;
    }

    public void setValorCreditoPresumidoIcms(BigDecimal valorCreditoPresumidoIcms) {
        this.valorCreditoPresumidoIcms = valorCreditoPresumidoIcms;
    }

    public BigDecimal getPercentualBcIcmsOutraUf() {
        return percentualBcIcmsOutraUf;
    }

    public void setPercentualBcIcmsOutraUf(BigDecimal percentualBcIcmsOutraUf) {
        this.percentualBcIcmsOutraUf = percentualBcIcmsOutraUf;
    }

    public BigDecimal getValorBcIcmsOutraUf() {
        return valorBcIcmsOutraUf;
    }

    public void setValorBcIcmsOutraUf(BigDecimal valorBcIcmsOutraUf) {
        this.valorBcIcmsOutraUf = valorBcIcmsOutraUf;
    }

    public BigDecimal getAliquotaIcmsOutraUf() {
        return aliquotaIcmsOutraUf;
    }

    public void setAliquotaIcmsOutraUf(BigDecimal aliquotaIcmsOutraUf) {
        this.aliquotaIcmsOutraUf = aliquotaIcmsOutraUf;
    }

    public BigDecimal getValorIcmsOutraUf() {
        return valorIcmsOutraUf;
    }

    public void setValorIcmsOutraUf(BigDecimal valorIcmsOutraUf) {
        this.valorIcmsOutraUf = valorIcmsOutraUf;
    }

    public Integer getSimplesNacionalIndicador() {
        return simplesNacionalIndicador;
    }

    public void setSimplesNacionalIndicador(Integer simplesNacionalIndicador) {
        this.simplesNacionalIndicador = simplesNacionalIndicador;
    }

    public BigDecimal getSimplesNacionalTotal() {
        return simplesNacionalTotal;
    }

    public void setSimplesNacionalTotal(BigDecimal simplesNacionalTotal) {
        this.simplesNacionalTotal = simplesNacionalTotal;
    }

    public String getInformacoesAddFisco() {
        return informacoesAddFisco;
    }

    public void setInformacoesAddFisco(String informacoesAddFisco) {
        this.informacoesAddFisco = informacoesAddFisco;
    }

    public BigDecimal getValorTotalCarga() {
        return valorTotalCarga;
    }

    public void setValorTotalCarga(BigDecimal valorTotalCarga) {
        this.valorTotalCarga = valorTotalCarga;
    }

    public String getProdutoPredominante() {
        return produtoPredominante;
    }

    public void setProdutoPredominante(String produtoPredominante) {
        this.produtoPredominante = produtoPredominante;
    }

    public String getCargaOutrasCaracteristicas() {
        return cargaOutrasCaracteristicas;
    }

    public void setCargaOutrasCaracteristicas(String cargaOutrasCaracteristicas) {
        this.cargaOutrasCaracteristicas = cargaOutrasCaracteristicas;
    }

    public Integer getModalVersaoLayout() {
        return modalVersaoLayout;
    }

    public void setModalVersaoLayout(Integer modalVersaoLayout) {
        this.modalVersaoLayout = modalVersaoLayout;
    }

    public String getChaveCteSubstituido() {
        return chaveCteSubstituido;
    }

    public void setChaveCteSubstituido(String chaveCteSubstituido) {
        this.chaveCteSubstituido = chaveCteSubstituido;
    }

    public EmpresaVO getEmpresa() {
        return empresa;
    }

    public void setEmpresa(EmpresaVO empresa) {
        this.empresa = empresa;
    }

    @Override
    public String toString() {
        return "com.t2tierp.cte.java.CteCabecalhoVO[id=" + id + "]";
    }

    public CteRodoviarioVO getCteRodoviario() {
        return cteRodoviario;
    }

    public void setCteRodoviario(CteRodoviarioVO cteRodoviario) {
        this.cteRodoviario = cteRodoviario;
    }

    public CteEmitenteVO getCteEmitente() {
        return cteEmitente;
    }

    public void setCteEmitente(CteEmitenteVO cteEmitente) {
        this.cteEmitente = cteEmitente;
    }

    public CteRemetenteVO getCteRemetente() {
        return cteRemetente;
    }

    public void setCteRemetente(CteRemetenteVO cteRemetente) {
        this.cteRemetente = cteRemetente;
    }

    public CteDestinatarioVO getCteDestinatario() {
        return cteDestinatario;
    }

    public void setCteDestinatario(CteDestinatarioVO cteDestinatario) {
        this.cteDestinatario = cteDestinatario;
    }

    public List<CteCargaVO> getListaCteCarga() {
        return listaCteCarga;
    }

    public void setListaCteCarga(List<CteCargaVO> listaCteCarga) {
        this.listaCteCarga = listaCteCarga;
    }

    public List<CteInformacaoNfOutrosVO> getListaCteInformacaoNfOutros() {
        return listaCteInformacaoNfOutros;
    }

    public void setListaCteInformacaoNfOutros(List<CteInformacaoNfOutrosVO> listaCteInformacaoNfOutros) {
        this.listaCteInformacaoNfOutros = listaCteInformacaoNfOutros;
    }

}
