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
package com.t2tierp.escritafiscal.java;

import com.t2tierp.cadastros.java.EmpresaVO;
import java.io.Serializable;
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
@Table(name = "FISCAL_PARAMETRO")
public class FiscalParametroVO extends ValueObjectImpl implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "VIGENCIA")
    private String vigencia;
    @Column(name = "DESCRICAO_VIGENCIA")
    private String descricaoVigencia;
    @Column(name = "CRITERIO_LANCAMENTO")
    private String criterioLancamento;
    @Column(name = "APURACAO")
    private String apuracao;
    @Column(name = "MICROEMPREE_INDIVIDUAL")
    private String microempreeIndividual;
    @Column(name = "CALC_PIS_COFINS_EFD")
    private String calcPisCofinsEfd;
    @Column(name = "SIMPLES_CODIGO_ACESSO")
    private String simplesCodigoAcesso;
    @Column(name = "SIMPLES_TABELA")
    private String simplesTabela;
    @Column(name = "SIMPLES_ATIVIDADE")
    private String simplesAtividade;
    @Column(name = "PERFIL_SPED")
    private String perfilSped;
    @Column(name = "APURACAO_CONSOLIDADA")
    private String apuracaoConsolidada;
    @Column(name = "SUBSTITUICAO_TRIBUTARIA")
    private String substituicaoTributaria;
    @Column(name = "FORMA_CALCULO_ISS")
    private String formaCalculoIss;
    @JoinColumn(name = "ID_EMPRESA", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private EmpresaVO empresa;
    @JoinColumn(name = "ID_FISCAL_MUNICIPAL_REGIME", referencedColumnName = "ID")
    @ManyToOne
    private FiscalMunicipalRegimeVO fiscalMunicipalRegime;
    @JoinColumn(name = "ID_FISCAL_ESTADUAL_REGIME", referencedColumnName = "ID")
    @ManyToOne
    private FiscalEstadualRegimeVO fiscalEstadualRegime;
    @JoinColumn(name = "ID_FISCAL_ESTADUAL_PORTE", referencedColumnName = "ID")
    @ManyToOne
    private FiscalEstadualPorteVO fiscalEstadualPorte;

    public FiscalParametroVO() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getVigencia() {
        return vigencia;
    }

    public void setVigencia(String vigencia) {
        this.vigencia = vigencia;
    }

    public String getDescricaoVigencia() {
        return descricaoVigencia;
    }

    public void setDescricaoVigencia(String descricaoVigencia) {
        this.descricaoVigencia = descricaoVigencia;
    }

    public String getCriterioLancamento() {
        return criterioLancamento;
    }

    public void setCriterioLancamento(String criterioLancamento) {
        this.criterioLancamento = criterioLancamento;
    }

    public String getApuracao() {
        return apuracao;
    }

    public void setApuracao(String apuracao) {
        this.apuracao = apuracao;
    }

    public String getMicroempreeIndividual() {
        return microempreeIndividual;
    }

    public void setMicroempreeIndividual(String microempreeIndividual) {
        this.microempreeIndividual = microempreeIndividual;
    }

    public String getCalcPisCofinsEfd() {
        return calcPisCofinsEfd;
    }

    public void setCalcPisCofinsEfd(String calcPisCofinsEfd) {
        this.calcPisCofinsEfd = calcPisCofinsEfd;
    }

    public String getSimplesCodigoAcesso() {
        return simplesCodigoAcesso;
    }

    public void setSimplesCodigoAcesso(String simplesCodigoAcesso) {
        this.simplesCodigoAcesso = simplesCodigoAcesso;
    }

    public String getSimplesTabela() {
        return simplesTabela;
    }

    public void setSimplesTabela(String simplesTabela) {
        this.simplesTabela = simplesTabela;
    }

    public String getSimplesAtividade() {
        return simplesAtividade;
    }

    public void setSimplesAtividade(String simplesAtividade) {
        this.simplesAtividade = simplesAtividade;
    }

    public String getPerfilSped() {
        return perfilSped;
    }

    public void setPerfilSped(String perfilSped) {
        this.perfilSped = perfilSped;
    }

    public String getApuracaoConsolidada() {
        return apuracaoConsolidada;
    }

    public void setApuracaoConsolidada(String apuracaoConsolidada) {
        this.apuracaoConsolidada = apuracaoConsolidada;
    }

    public String getSubstituicaoTributaria() {
        return substituicaoTributaria;
    }

    public void setSubstituicaoTributaria(String substituicaoTributaria) {
        this.substituicaoTributaria = substituicaoTributaria;
    }

    public String getFormaCalculoIss() {
        return formaCalculoIss;
    }

    public void setFormaCalculoIss(String formaCalculoIss) {
        this.formaCalculoIss = formaCalculoIss;
    }

    public EmpresaVO getEmpresa() {
        return empresa;
    }

    public void setEmpresa(EmpresaVO empresa) {
        this.empresa = empresa;
    }

    public FiscalMunicipalRegimeVO getFiscalMunicipalRegime() {
        return fiscalMunicipalRegime;
    }

    public void setFiscalMunicipalRegime(FiscalMunicipalRegimeVO fiscalMunicipalRegime) {
        this.fiscalMunicipalRegime = fiscalMunicipalRegime;
    }

    public FiscalEstadualRegimeVO getFiscalEstadualRegime() {
        return fiscalEstadualRegime;
    }

    public void setFiscalEstadualRegime(FiscalEstadualRegimeVO fiscalEstadualRegime) {
        this.fiscalEstadualRegime = fiscalEstadualRegime;
    }

    public FiscalEstadualPorteVO getFiscalEstadualPorte() {
        return fiscalEstadualPorte;
    }

    public void setFiscalEstadualPorte(FiscalEstadualPorteVO fiscalEstadualPorte) {
        this.fiscalEstadualPorte = fiscalEstadualPorte;
    }


    @Override
    public String toString() {
        return "com.t2tierp.escritafiscal.java.FiscalParametroVO[id=" + id + "]";
    }

}
