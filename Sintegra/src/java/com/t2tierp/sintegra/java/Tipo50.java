package com.t2tierp.sintegra.java;

import java.util.Date;
import org.jrimum.texgit.Record;

public class Tipo50 {

    private Record record;

    public Tipo50() {
    }

    public Tipo50(Record record) {
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

    public Date getDataEmissao() {
        return record.getValue("DataEmissao");
    }

    public void setDataEmissao(Date dataEmissao) {
        record.setValue("DataEmissao", dataEmissao);
    }

    public String getUF() {
        return record.getValue("UF");
    }

    public void setUF(String uF) {
        record.setValue("UF", uF);
    }

    public Integer getCodigoModelo() {
        return record.getValue("CodigoModelo");
    }

    public void setCodigoModelo(Integer codigoModelo) {
        record.setValue("CodigoModelo", codigoModelo);
    }

    public String getSerie() {
        return record.getValue("Serie");
    }

    public void setSerie(String serie) {
        record.setValue("Serie", serie);
    }

    public Integer getNumeroNota() {
        return record.getValue("NumeroNota");
    }

    public void setNumeroNota(Integer numeroNota) {
        record.setValue("NumeroNota", numeroNota);
    }

    public Integer getCFOP() {
        return record.getValue("CFOP");
    }

    public void setCFOP(Integer cFOP) {
        record.setValue("CFOP", cFOP);
    }

    public String getEmitente() {
        return record.getValue("Emitente");
    }

    public void setEmitente(String emitente) {
        record.setValue("Emitente", emitente);
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

    public Long getValorIsento() {
        return record.getValue("ValorIsento");
    }

    public void setValorIsento(Long valorIsento) {
        record.setValue("ValorIsento", valorIsento);
    }

    public Long getValorOutras() {
        return record.getValue("ValorOutras");
    }

    public void setValorOutras(Long valorOutras) {
        record.setValue("ValorOutras", valorOutras);
    }

    public Integer getAliquotaIcms() {
        return record.getValue("AliquotaIcms");
    }

    public void setAliquotaIcms(Integer aliquotaIcms) {
        record.setValue("AliquotaIcms", aliquotaIcms);
    }

    public String getSituacao() {
        return record.getValue("Situacao");
    }

    public void setSituacao(String situacao) {
        record.setValue("Situacao", situacao);
    }

    public Record getRecord() {
        return record;
    }

    public void setRecord(Record record) {
        this.record = record;
    }
}
