package com.t2tierp.folhapagamento.cliente.sefip;

import org.jrimum.texgit.Record;

public class Tipo10 {

    private Record record;

    public Tipo10() {
    }

    public Tipo10(Record record) {
        this.record = record;
    }

    public Integer getTipoInscricao() {
        return record.getValue("TipoInscricao");
    }

    public void setTipoInscricao(Integer tipoInscricao) {
        record.setValue("TipoInscricao", tipoInscricao);
    }

    public Long getInscricaoEmpresa() {
        return record.getValue("InscricaoEmpresa");
    }

    public void setInscricaoEmpresa(Long inscricaoEmpresa) {
        record.setValue("InscricaoEmpresa", inscricaoEmpresa);
    }

    public Integer getZeros() {
        return record.getValue("Zeros");
    }

    public void setZeros(Integer zeros) {
        record.setValue("Zeros", zeros);
    }

    public String getNomeEmpresa() {
        return record.getValue("NomeEmpresa");
    }

    public void setNomeEmpresa(String nomeEmpresa) {
        record.setValue("NomeEmpresa", nomeEmpresa);
    }

    public String getLobragouro() {
        return record.getValue("Lobragouro");
    }

    public void setLogradouro(String logradouro) {
        record.setValue("Logradouro", logradouro);
    }

    public String getBairro() {
        return record.getValue("Bairro");
    }

    public void setBairro(String bairro) {
        record.setValue("Bairro", bairro);
    }

    public String getCep() {
        return record.getValue("Cep");
    }

    public void setCep(String cep) {
        record.setValue("Cep", cep);
    }

    public String getCidade() {
        return record.getValue("Cidade");
    }

    public void setCidade(String cidade) {
        record.setValue("Cidade", cidade);
    }

    public String getUf() {
        return record.getValue("Uf");
    }

    public void setUf(String uf) {
        record.setValue("Uf", uf);
    }

    public String getTelefone() {
        return record.getValue("Telefone");
    }

    public void setTelefone(String telefone) {
        record.setValue("Telefone", telefone);
    }

    public String getIndicadorAlteracaoEndereco() {
        return record.getValue("IndicadorAlteracaoEndereco");
    }

    public void setIndicadorAlteracaoEndereco(String indicadorAlteracaoEndereco) {
        record.setValue("IndicadorAlteracaoEndereco", indicadorAlteracaoEndereco);
    }

    public Integer getCnae() {
        return record.getValue("Cnae");
    }

    public void setCnae(Integer cnae) {
        record.setValue("Cnae", cnae);
    }

    public String getIndicadorAlteracaoCnae() {
        return record.getValue("IndicadorAlteracaoCnae");
    }

    public void setIndicadorAlteracaoCnae(String indicadorAlteracaoCnae) {
        record.setValue("IndicadorAlteracaoCnae", indicadorAlteracaoCnae);
    }

    public Integer getAliquotaRat() {
        return record.getValue("AliquotaRat");
    }

    public void setAliquotaRat(Integer aliquotaRat) {
        record.setValue("AliquotaRat", aliquotaRat);
    }

    public Integer getCodigoCentralizacao() {
        return record.getValue("CodigoCentralizacao");
    }

    public void setCodigoCentralizacao(Integer codigoCentralizacao) {
        record.setValue("CodigoCentralizacao", codigoCentralizacao);
    }

    public Integer getSimples() {
        return record.getValue("Simples");
    }

    public void setSimples(Integer simples) {
        record.setValue("Simples", simples);
    }

    public Integer getFpas() {
        return record.getValue("Fpas");
    }

    public void setFpas(Integer fpas) {
        record.setValue("Fpas", fpas);
    }

    public Integer getCodigoOutrasEntidades() {
        return record.getValue("CodigoOutrasEntidades");
    }

    public void setCodigoOutrasEntidades(Integer codigoOutrasEntidades) {
        record.setValue("CodigoOutrasEntidades", codigoOutrasEntidades);
    }

    public Integer getCodigoPagamentoGps() {
        return record.getValue("CodigoPagamentoGps");
    }

    public void setCodigoPagamentoGps(Integer codigoPagamentoGps) {
        record.setValue("CodigoPagamentoGps", codigoPagamentoGps);
    }

    public Integer getPercentualIsencaoFilantropia() {
        return record.getValue("PercentualIsencaoFilantropia");
    }

    public void setPercentualIsencaoFilantropia(Integer percentualIsencaoFilantropia) {
        record.setValue("PercentualIsencaoFilantropia", percentualIsencaoFilantropia);
    }

    public Long getSalarioFamilia() {
        return record.getValue("SalarioFamilia");
    }

    public void setSalarioFamilia(Long salarioFamilia) {
        record.setValue("SalarioFamilia", salarioFamilia);
    }

    public Long getSalarioMaternidade() {
        return record.getValue("SalarioMaternidade");
    }

    public void setSalarioMaternidade(Long salarioMaternidade) {
        record.setValue("SalarioMaternidade", salarioMaternidade);
    }

    public Long getContribDescEmpregado() {
        return record.getValue("ContribDescEmpregado");
    }

    public void setContribDescEmpregado(Long contribDescEmpregado) {
        record.setValue("ContribDescEmpregado", contribDescEmpregado);
    }

    public Integer getIndicadorPositivoNegativo() {
        return record.getValue("IndicadorPositivoNegativo");
    }

    public void setIndicadorPositivoNegativo(Integer indicadorPositivoNegativo) {
        record.setValue("IndicadorPositivoNegativo", indicadorPositivoNegativo);
    }

    public Long getValorDevidoPrevidencia() {
        return record.getValue("ValorDevidoPrevidencia");
    }

    public void setValorDevidoPrevidencia(Long valorDevidoPrevidencia) {
        record.setValue("ValorDevidoPrevidencia", valorDevidoPrevidencia);
    }

    public String getBanco() {
        return record.getValue("Banco");
    }

    public void setBanco(String banco) {
        record.setValue("Banco", banco);
    }

    public String getAgencia() {
        return record.getValue("Agencia");
    }

    public void setAgencia(String agencia) {
        record.setValue("Agencia", agencia);
    }

    public String getContaCorrente() {
        return record.getValue("ContaCorrente");
    }

    public void setContaCorrente(String contaCorrente) {
        record.setValue("ContaCorrente", contaCorrente);
    }

    public Integer getZeros2() {
        return record.getValue("Zeros2");
    }

    public void setZeros2(Integer zeros2) {
        record.setValue("Zeros2", zeros2);
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
