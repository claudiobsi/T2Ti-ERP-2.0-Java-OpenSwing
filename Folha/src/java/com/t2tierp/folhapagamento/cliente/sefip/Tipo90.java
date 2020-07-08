package com.t2tierp.folhapagamento.cliente.sefip;

import org.jrimum.texgit.Record;

public class Tipo90 {

    private Record record;

    public Tipo90() {
    }

    public Tipo90(Record record) {
        this.record = record;
    }

    public String getFinalRegistro() {
        return record.getValue("FinalRegistro");
    }

    public void setFinalRegistro(String finalRegistro) {
        record.setValue("FinalRegistro", finalRegistro);
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
