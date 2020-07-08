package com.t2tierp.sintegra.java;

import java.util.Date;
import org.jrimum.texgit.Record;

public class Tipo61 {

    private Record record;

    public Tipo61() {
    }

    public Tipo61(Record record) {
        this.record = record;
    }

    public String getBrancos() {
        return record.getValue("Brancos1");
    }

    public void setBrancos(String brancos) {
        record.setValue("Brancos1", brancos);
    }

    public String getBrancos2() {
        return record.getValue("Brancos2");
    }

    public void setBrancos2(String brancos) {
        record.setValue("Brancos2", brancos);
    }

    public Date getDataEmissao() {
        return record.getValue("DataEmissao");
    }

    public void setDataEmissao(Date dataEmissao) {
        record.setValue("DataEmissao", dataEmissao);
    }

    public Integer getModelo() {
        return record.getValue("Modelo");
    }

    public void setModelo(Integer modelo) {
        record.setValue("Modelo", modelo);
    }

    public String getSerie() {
        return record.getValue("Serie");
    }

    public void setSerie(String serie) {
        record.setValue("Serie", serie);
    }

    public String getSubSerie() {
        return record.getValue("SubSerie");
    }

    public void setSubSerie(String subSerie) {
        record.setValue("SubSerie", subSerie);
    }

    public Integer getNumeroInicialOrdem() {
        return record.getValue("NumeroInicialOrdem");
    }

    public void setNumeroInicialOrdem(Integer numeroInicialOrdem) {
        record.setValue("NumeroInicialOrdem", numeroInicialOrdem);
    }

    public Integer getNumeroFinalOrdem() {
        return record.getValue("NumeroFinalOrdem");
    }

    public void setNumeroFinalOrdem(Integer numeroFinalOrdem) {
        record.setValue("NumeroFinalOrdem", numeroFinalOrdem);
    }

    public Long getValorTotal() {
        return record.getValue("ValorTotal");
    }

    public void setValorTotal(Long valorTotal) {
        record.setValue("ValorTotal", valorTotal);
    }

    public Long getBaseCalculoIcms() {
        return record.getValue("BaseCalculoIcms");
    }

    public void setBaseCalculoIcms(Long baseCalculoIcms) {
        record.setValue("BaseCalculoIcms", baseCalculoIcms);
    }

    public Long getValorIcms() {
        return record.getValue("ValorIcms");
    }

    public void setValorIcms(Long valorIcms) {
        record.setValue("ValorIcms", valorIcms);
    }

    public Long getIsentaNaoTributada() {
        return record.getValue("IsentaNaoTributada");
    }

    public void setIsentaNaoTributada(Long isentaNaoTributada) {
        record.setValue("IsentaNaoTributada", isentaNaoTributada);
    }

    public Long getOutras() {
        return record.getValue("Outras");
    }

    public void setOutras(Long outras) {
        record.setValue("Outras", outras);
    }

    public Long getAliquota() {
        return record.getValue("Aliquota");
    }

    public void setAliquota(Long aliquota) {
        record.setValue("Aliquota", aliquota);
    }

    public String getBranco() {
        return record.getValue("Branco");
    }

    public void setBranco(String branco) {
        record.setValue("Branco", branco);
    }

    public Record getRecord() {
        return record;
    }

    public void setRecord(Record record) {
        this.record = record;
    }
}
