package com.t2tierp.sintegra.java;

import java.util.Date;
import org.jrimum.texgit.Record;

public class Tipo60M {

    private Record record;

    public Tipo60M() {
    }

    public Tipo60M(Record record) {
        this.record = record;
    }

    public Date getDataEmissao() {
        return record.getValue("DataEmissao");
    }

    public void setDataEmissao(Date dataEmissao) {
        record.setValue("DataEmissao", dataEmissao);
    }

    public String getSerie() {
        return record.getValue("Serie");
    }

    public void setSerie(String serie) {
        record.setValue("Serie", serie);
    }

    public Integer getNumeroOrdemEquipamento() {
        return record.getValue("NumeroOrdemEquipamento");
    }

    public void setNumeroOrdemEquipamento(Integer numeroOrdemEquipamento) {
        record.setValue("NumeroOrdemEquipamento", numeroOrdemEquipamento);
    }

    public String getModeloDocumentoFiscal() {
        return record.getValue("ModeloDocumentoFiscal");
    }

    public void setModeloDocumentoFiscal(String modeloDocumentoFiscal) {
        record.setValue("ModeloDocumentoFiscal", modeloDocumentoFiscal);
    }

    public Integer getPrimeiroCOO() {
        return record.getValue("PrimeiroCOO");
    }

    public void setPrimeiroCOO(Integer primeiroCOO) {
        record.setValue("PrimeiroCOO", primeiroCOO);
    }

    public Integer getUltimoCOO() {
        return record.getValue("UltimoCOO");
    }

    public void setUltimoCOO(Integer ultimoCOO) {
        record.setValue("UltimoCOO", ultimoCOO);
    }

    public Integer getNumeroCRZ() {
        return record.getValue("NumeroCRZ");
    }

    public void setNumeroCRZ(Integer numeroCRZ) {
        record.setValue("NumeroCRZ", numeroCRZ);
    }

    public Integer getCRO() {
        return record.getValue("CRO");
    }

    public void setCRO(Integer cRO) {
        record.setValue("CRO", cRO);
    }

    public Long getValorVendaBruta() {
        return record.getValue("ValorVendaBruta");
    }

    public void setValorVendaBruta(Long valorVendaBruta) {
        record.setValue("ValorVendaBruta", valorVendaBruta);
    }

    public Long getValorTotalizadorGeral() {
        return record.getValue("ValorTotalizadorGeral");
    }

    public void setValorTotalizadorGeral(Long valorTotalizadorGeral) {
        record.setValue("ValorTotalizadorGeral", valorTotalizadorGeral);
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
