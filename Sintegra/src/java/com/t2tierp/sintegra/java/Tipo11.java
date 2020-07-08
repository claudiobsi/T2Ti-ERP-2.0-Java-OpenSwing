package com.t2tierp.sintegra.java;

import org.jrimum.texgit.Record;

public class Tipo11 {

    private Record record;

    public Tipo11() {
    }

    public Tipo11(Record record) {
        this.record = record;
    }

    public String getLogradouro() {
        return record.getValue("Logradouro");
    }

    public void setLogradouro(String logradouro) {
        record.setValue("Logradouro", logradouro);
    }

    public Integer getNumero() {
        return record.getValue("Numero");
    }

    public void setNumero(Integer numero) {
        record.setValue("Numero", numero);
    }

    public String getComplemento() {
        return record.getValue("Complemento");
    }

    public void setComplemento(String complemento) {
        record.setValue("Complemento", complemento);
    }

    public String getBairro() {
        return record.getValue("Bairro");
    }

    public void setBairro(String bairro) {
        record.setValue("Bairro", bairro);
    }

    public Integer getCEP() {
        return record.getValue("CEP");
    }

    public void setCEP(Integer cEP) {
        record.setValue("CEP", cEP);
    }

    public String getNomeContato() {
        return record.getValue("NomeContato");
    }

    public void setNomeContato(String nomeContato) {
        record.setValue("NomeContato", nomeContato);
    }

    public Long getTelefone() {
        return record.getValue("Telefone");
    }

    public void setTelefone(Long telefone) {
        record.setValue("Telefone", telefone);
    }

    public Record getRecord() {
        return record;
    }

    public void setRecord(Record record) {
        this.record = record;
    }
}
