package com.t2tierp.sintegra.java;

import java.util.Date;
import org.jrimum.texgit.Record;

public class Tipo60D {

    private Record record;

    public Tipo60D() {
    }

    public Tipo60D(Record record) {
        this.record = record;
    }

    public Date getDataEmissao() {
        return record.getValue("DataEmissao");
    }

    public void setDataEmissao(Date dataEmissao) {
        record.setValue("DataEmissao", dataEmissao);
    }

    public String getNumeroSerie() {
        return record.getValue("NumeroSerie");
    }

    public void setNumeroSerie(String numeroSerie) {
        record.setValue("NumeroSerie", numeroSerie);
    }

    public String getCodigoProduto() {
        return record.getValue("CodigoProduto");
    }

    public void setCodigoProduto(String codigoProduto) {
        record.setValue("CodigoProduto", codigoProduto);
    }

    public Long getQuantidade() {
        return record.getValue("Quantidade");
    }

    public void setQuantidade(Long quantidade) {
        record.setValue("Quantidade", quantidade);
    }

    public Long getValor() {
        return record.getValue("Valor");
    }

    public void setValor(Long valor) {
        record.setValue("Valor", valor);
    }

    public Long getBaseCalculoIcms() {
        return record.getValue("BaseCalculoIcms");
    }

    public void setBaseCalculoIcms(Long baseCalculoIcms) {
        record.setValue("BaseCalculoIcms", baseCalculoIcms);
    }

    public String getSituacaoTributaria() {
        return record.getValue("SituacaoTributaria");
    }

    public void setSituacaoTributaria(String situacaoTributaria) {
        record.setValue("SituacaoTributaria", situacaoTributaria);
    }

    public Long getValorIcms() {
        return record.getValue("ValorIcms");
    }

    public void setValorIcms(Long valorIcms) {
        record.setValue("ValorIcms", valorIcms);
    }

    public String getBrancos() {
        return record.getValue("Brancos");
    }

    public void setBrancos(String brancos) {
        record.setValue("Brancos", brancos);
    }

    public Record getRecord() {
        return record;
    }

    public void setRecord(Record record) {
        this.record = record;
    }
}
