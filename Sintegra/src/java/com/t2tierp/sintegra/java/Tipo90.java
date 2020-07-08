package com.t2tierp.sintegra.java;

import org.jrimum.texgit.Record;

public class Tipo90 {

    private Record record;

    public Tipo90() {
    }

    public Tipo90(Record record) {
        this.record = record;
    }

    public Long getCNPJ() {
        return record.getValue("CNPJ");
    }

    public void setCNPJ(Long cNPJ) {
        record.setValue("CNPJ", cNPJ);
    }

    public String getInscricaoEstadual() {
        return record.getValue("InscricaoEstadual");
    }

    public void setInscricaoEstadual(String inscricaoEstadual) {
        record.setValue("InscricaoEstadual", inscricaoEstadual);
    }

    public String getTotalizadores() {
        return record.getValue("Totalizadores");
    }

    public void setTotalizadores(String totalizadores) {
        record.setValue("Totalizadores", totalizadores);
    }

    public Integer getTotalRegistrosTipo90() {
        return record.getValue("TotalRegistrosTipo90");
    }

    public void setTotalRegistrosTipo90(Integer totalRegistrosTipo90) {
        record.setValue("TotalRegistrosTipo90", totalRegistrosTipo90);
    }

    public Record getRecord() {
        return record;
    }

    public void setRecord(Record record) {
        this.record = record;
    }
}
