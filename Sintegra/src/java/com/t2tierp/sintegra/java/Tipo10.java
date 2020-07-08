package com.t2tierp.sintegra.java;

import java.util.Date;
import org.jrimum.texgit.Record;

public class Tipo10 {

    private Record record;

    public Tipo10() {
    }

    public Tipo10(Record record) {
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

    public String getNomeContribuinte() {
        return record.getValue("NomeContribuinte");
    }

    public void setNomeContribuinte(String nomeContribuinte) {
        record.setValue("NomeContribuinte", nomeContribuinte);
    }

    public String getMunicipio() {
        return record.getValue("Municipio");
    }

    public void setMunicipio(String municipio) {
        record.setValue("Municipio", municipio);
    }

    public String getUF() {
        return record.getValue("UF");
    }

    public void setUF(String uF) {
        record.setValue("UF", uF);
    }

    public Long getFax() {
        return record.getValue("Fax");
    }

    public void setFax(Long fax) {
        record.setValue("Fax", fax);
    }

    public Date getDataInicial() {
        return record.getValue("DataInicial");
    }

    public void setDataInicial(Date dataInicial) {
        record.setValue("DataInicial", dataInicial);
    }

    public Date getDataFinal() {
        return record.getValue("DataFinal");
    }

    public void setDataFinal(Date dataFinal) {
        record.setValue("DataFinal", dataFinal);
    }

    public String getCodigoIdentificaoConvenio() {
        return record.getValue("CodigoIdentificaoConvenio");
    }

    public void setCodigoIdentificaoConvenio(String codigoIdentificaoConvenio) {
        record.setValue("CodigoIdentificaoConvenio", codigoIdentificaoConvenio);
    }

    public String getCodigoIdentificaoNaturezaOperacao() {
        return record.getValue("CodigoIdentificaoNaturezaOperacao");
    }

    public void setCodigoIdentificaoNaturezaOperacao(String codigoIdentificaoNaturezaOperacao) {
        record.setValue("CodigoIdentificaoNaturezaOperacao", codigoIdentificaoNaturezaOperacao);
    }

    public String getCodigoFinalidadeArquivo() {
        return record.getValue("CodigoFinalidadeArquivo");
    }

    public void setCodigoFinalidadeArquivo(String codigoFinalidadeArquivo) {
        record.setValue("CodigoFinalidadeArquivo", codigoFinalidadeArquivo);
    }

    public Record getRecord() {
        return record;
    }

    public void setRecord(Record record) {
        this.record = record;
    }
}
