package com.t2tierp.sintegra.java;

import java.util.Date;
import org.jrimum.texgit.Record;

public class Tipo60A {

    private Record record;

    public Tipo60A() {
    }

    public Tipo60A(Record record) {
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

    public String getSituacaoTributaria() {
        return record.getValue("SituacaoTributaria");
    }

    public void setSituacaoTributaria(String situacaoTributaria) {
        record.setValue("SituacaoTributaria", situacaoTributaria);
    }

    public Long getValorAcumuladoTotalizadorParcial() {
        return record.getValue("ValorAcumuladoTotalizadorParcial");
    }

    public void setValorAcumuladoTotalizadorParcial(Long valorAcumuladoTotalizadorParcial) {
        record.setValue("ValorAcumuladoTotalizadorParcial", valorAcumuladoTotalizadorParcial);
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
