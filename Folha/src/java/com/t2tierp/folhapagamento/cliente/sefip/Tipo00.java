package com.t2tierp.folhapagamento.cliente.sefip;

import java.util.Date;
import org.jrimum.texgit.Record;

public class Tipo00 {

    private Record record;

    public Tipo00() {
    }

    public Tipo00(Record record) {
        this.record = record;
    }

    public String getBrancos() {
        return record.getValue("Brancos");
    }

    public void setBrancos(String brancos) {
        record.setValue("Brancos", brancos);
    }

    public Integer getTipoRemessa() {
        return record.getValue("TipoRemessa");
    }

    public void setTipoRemessa(Integer tipoRemessa) {
        record.setValue("TipoRemessa", tipoRemessa);
    }

    public Integer getTipoInscricao() {
        return record.getValue("TipoInscricao");
    }

    public void setTipoInscricao(Integer tipoInscricao) {
        record.setValue("TipoInscricao", tipoInscricao);
    }

    public Long getInscricaoResponsavel() {
        return record.getValue("InscricaoResponsavel");
    }

    public void setInscricaoResponsavel(Long inscricaoResponsavel) {
        record.setValue("InscricaoResponsavel", inscricaoResponsavel);
    }

    public String getNomeResponsavel() {
        return record.getValue("NomeResponsavel");
    }

    public void setNomeResponsavel(String nomeResponsavel) {
        record.setValue("NomeResponsavel", nomeResponsavel);
    }

    public String getNomePessoaContato() {
        return record.getValue("NomePessoaContato");
    }

    public void setNomePessoaContato(String nomePessoaContato) {
        record.setValue("NomePessoaContato", nomePessoaContato);
    }

    public String getLogradouro() {
        return record.getValue("Logradouro");
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

    public String getTelefoneContato() {
        return record.getValue("TelefoneContato");
    }

    public void setTelefoneContato(String telefoneContato) {
        record.setValue("TelefoneContato", telefoneContato);
    }

    public String getEnderecoInternet() {
        return record.getValue("EnderecoInternet");
    }

    public void setEnderecoInternet(String enderecoInternet) {
        record.setValue("EnderecoInternet", enderecoInternet);
    }

    public String getCompetencia() {
        return record.getValue("Competencia");
    }

    public void setCompetencia(String competencia) {
        record.setValue("Competencia", competencia);
    }

    public Integer getCodigoRecolhimento() {
        return record.getValue("CodigoRecolhimento");
    }

    public void setCodigoRecolhimento(Integer codigoRecolhimento) {
        record.setValue("CodigoRecolhimento", codigoRecolhimento);
    }

    public Integer getIndicadorRecolhimentoFgts() {
        return record.getValue("IndicadorRecolhimentoFgts");
    }

    public void setIndicadorRecolhimentoFgts(Integer indicadorRecolhimentoFgts) {
        record.setValue("IndicadorRecolhimentoFgts", indicadorRecolhimentoFgts);
    }

    public Integer getModalidadeArquivo() {
        return record.getValue("ModalidadeArquivo");
    }

    public void setModalidadeArquivo(Integer modalidadeArquivo) {
        record.setValue("ModalidadeArquivo", modalidadeArquivo);
    }

    public Date getDataRecolhimentoFgts() {
        return record.getValue("DataRecolhimentoFgts");
    }

    public void setDataRecolhimentoFgts(Date dataRecolhimentoFgts) {
        record.setValue("DataRecolhimentoFgts", dataRecolhimentoFgts);
    }

    public Integer getIndicadorRecolhimentoPrevidencia() {
        return record.getValue("IndicadorRecolhimentoPrevidencia");
    }

    public void setIndicadorRecolhimentoPrevidencia(Integer indicadorRecolhimentoPrevidencia) {
        record.setValue("IndicadorRecolhimentoPrevidencia", indicadorRecolhimentoPrevidencia);
    }

    public Date getDataRecolhimentoPrevidencia() {
        return record.getValue("DataRecolhimentoPrevidencia");
    }

    public void setDataRecolhimentoPrevidencia(Date dataRecolhimentoPrevidencia) {
        record.setValue("DataRecolhimentoPrevidencia", dataRecolhimentoPrevidencia);
    }

    public String getIndiceRecolhimentoAtrasoPrevidencia() {
        return record.getValue("IndiceRecolhimentoAtrasoPrevidencia");
    }

    public void setIndiceRecolhimentoAtrasoPrevidencia(String indiceRecolhimentoAtrasoPrevidencia) {
        record.setValue("IndiceRecolhimentoAtrasoPrevidencia", indiceRecolhimentoAtrasoPrevidencia);
    }

    public Integer getTipoInscricaoFornecedor() {
        return record.getValue("TipoInscricaoFornecedor");
    }

    public void setTipoInscricaoFornecedor(Integer tipoInscricaoFornecedor) {
        record.setValue("TipoInscricaoFornecedor", tipoInscricaoFornecedor);
    }

    public Long getInscricaoFornecedor() {
        return record.getValue("InscricaoFornecedor");
    }

    public void setInscricaoFornecedor(Long inscricaoFornecedor) {
        record.setValue("InscricaoFornecedor", inscricaoFornecedor);
    }

    public String getBrancos2() {
        return record.getValue("Brancos2");
    }

    public void setBrancos2(String brancos2) {
        record.setValue("Brancos2", brancos2);
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
