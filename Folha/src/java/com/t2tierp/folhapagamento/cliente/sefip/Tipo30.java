package com.t2tierp.folhapagamento.cliente.sefip;

import java.util.Date;
import org.jrimum.texgit.Record;

public class Tipo30 {

    private Record record;

    public Tipo30() {
    }

    public Tipo30(Record record) {
        this.record = record;
    }

    public Integer getTipoInscricaoEmpresa() {
        return record.getValue("TipoInscricaoEmpresa");
    }

    public void setTipoInscricaoEmpresa(Integer tipoInscricaoEmpresa) {
        record.setValue("TipoInscricaoEmpresa", tipoInscricaoEmpresa);
    }

    public Long getInscricaoEmpresa() {
        return record.getValue("InscricaoEmpresa");
    }

    public void setInscricaoEmpresa(Long inscricaoEmpresa) {
        record.setValue("InscricaoEmpresa", inscricaoEmpresa);
    }

    public Integer getTipoInscricaoTomador() {
        return record.getValue("TipoInscricaoTomador");
    }

    public void setTipoInscricaoTomador(Integer tipoInscricaoTomador) {
        record.setValue("TipoInscricaoTomador", tipoInscricaoTomador);
    }

    public Long getInscricaoTomador() {
        return record.getValue("InscricaoTomador");
    }

    public void setInscricaoTomador(Long inscricaoTomador) {
        record.setValue("InscricaoTomador", inscricaoTomador);
    }

    public Long getPisPasep() {
        return record.getValue("PisPasep");
    }

    public void setPisPasep(Long pisPasep) {
        record.setValue("PisPasep", pisPasep);
    }

    public Date getDataAmissao() {
        return record.getValue("DataAmissao");
    }

    public void setDataAmissao(Date dataAmissao) {
        record.setValue("DataAmissao", dataAmissao);
    }

    public Integer getCategoriaTrabalhador() {
        return record.getValue("CategoriaTrabalhador");
    }

    public void setCategoriaTrabalhador(Integer categoriaTrabalhador) {
        record.setValue("CategoriaTrabalhador", categoriaTrabalhador);
    }

    public String getNomeTrabalhador() {
        return record.getValue("NomeTrabalhador");
    }

    public void setNomeTrabalhador(String nomeTrabalhador) {
        record.setValue("NomeTrabalhador", nomeTrabalhador);
    }

    public Long getMatriculaEmpregado() {
        return record.getValue("MatriculaEmpregado");
    }

    public void setMatriculaEmpregado(Long matriculaEmpregado) {
        record.setValue("MatriculaEmpregado", matriculaEmpregado);
    }

    public Long getNumeroCtps() {
        return record.getValue("NumeroCtps");
    }

    public void setNumeroCtps(Long numeroCtps) {
        record.setValue("NumeroCtps", numeroCtps);
    }

    public Long getSerieCtps() {
        return record.getValue("SerieCtps");
    }

    public void setSerieCtps(Long serieCtps) {
        record.setValue("SerieCtps", serieCtps);
    }

    public Date getDataOpcao() {
        return record.getValue("DataOpcao");
    }

    public void setDataOpcao(Date dataOpcao) {
        record.setValue("DataOpcao", dataOpcao);
    }

    public Date getDataNascimento() {
        return record.getValue("DataNascimento");
    }

    public void setDataNascimento(Date dataNascimento) {
        record.setValue("DataNascimento", dataNascimento);
    }

    public String getCbo() {
        return record.getValue("Cbo");
    }

    public void setCbo(String cbo) {
        record.setValue("Cbo", cbo);
    }

    public Long getRemuneracao() {
        return record.getValue("Remuneracao");
    }

    public void setRemuneracao(Long remuneracao) {
        record.setValue("Remuneracao", remuneracao);
    }

    public Long getRemuneracao13() {
        return record.getValue("Remuneracao13");
    }

    public void setRemuneracao13(Long remuneracao13) {
        record.setValue("Remuneracao13", remuneracao13);
    }

    public Integer getClasseContribuicao() {
        return record.getValue("ClasseContribuicao");
    }

    public void setClasseContribuicao(Integer classeContribuicao) {
        record.setValue("ClasseContribuicao", classeContribuicao);
    }

    public Integer getOcorrencia() {
        return record.getValue("Ocorrencia");
    }

    public void setOcorrencia(Integer ocorrencia) {
        record.setValue("Ocorrencia", ocorrencia);
    }

    public Long getValorDescontadoSegurado() {
        return record.getValue("ValorDescontadoSegurado");
    }

    public void setValorDescontadoSegurado(Long valorDescontadoSegurado) {
        record.setValue("ValorDescontadoSegurado", valorDescontadoSegurado);
    }

    public Long getRemuneracaoBaseCalculoPrevidencia() {
        return record.getValue("RemuneracaoBaseCalculoPrevidencia");
    }

    public void setRemuneracaoBaseCalculoPrevidencia(Long remuneracaoBaseCalculoPrevidencia) {
        record.setValue("RemuneracaoBaseCalculoPrevidencia", remuneracaoBaseCalculoPrevidencia);
    }

    public Long getBaseCalculo13() {
        return record.getValue("BaseCalculo13");
    }

    public void setBaseCalculo13(Long baseCalculo13) {
        record.setValue("BaseCalculo13", baseCalculo13);
    }

    public Long getBaseCalculo13Previdencia() {
        return record.getValue("BaseCalculo13Previdencia");
    }

    public void setBaseCalculo13Previdencia(Long baseCalculo13Previdencia) {
        record.setValue("BaseCalculo13Previdencia", baseCalculo13Previdencia);
    }

    public String getBrancos() {
        return record.getValue("Brancos");
    }

    public void setBrancos(String brancos) {
        record.setValue("Brancos", brancos);
    }

    public String getFinalLinha() {
        return record.getValue("FinalLinha");
    }

    public void setFinalLinha(String finalLinha) {
        record.setValue("FinalLinha", finalLinha);
    }

    public Record getRecord() {
        return record;
    }

    public void setRecord(Record record) {
        this.record = record;
    }
}
