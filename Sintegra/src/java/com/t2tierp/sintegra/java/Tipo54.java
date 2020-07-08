package com.t2tierp.sintegra.java;

import org.jrimum.texgit.Record;

public class Tipo54 {

    private Record record;

    public Tipo54() {
    }

    public Tipo54(Record record) {
        this.record = record;
    }

    public Long getCNPJ() {
        return record.getValue("CNPJ");
    }

    public void setCNPJ(Long cNPJ) {
        record.setValue("CNPJ", cNPJ);
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

    public Integer getNumeroNf() {
        return record.getValue("NumeroNf");
    }

    public void setNumeroNf(Integer numeroNf) {
        record.setValue("NumeroNf", numeroNf);
    }

    public Integer getCfop() {
        return record.getValue("Cfop");
    }

    public void setCfop(Integer cfop) {
        record.setValue("Cfop", cfop);
    }

    public String getCst() {
        return record.getValue("Cst");
    }

    public void setCst(String cst) {
        record.setValue("Cst", cst);
    }

    public Integer getNumeroItem() {
        return record.getValue("NumeroItem");
    }

    public void setNumeroItem(Integer numeroItem) {
        record.setValue("NumeroItem", numeroItem);
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

    public Long getValorProduto() {
        return record.getValue("ValorProduto");
    }

    public void setValorProduto(Long valorProduto) {
        record.setValue("ValorProduto", valorProduto);
    }

    public Long getValorDesconto() {
        return record.getValue("ValorDesconto");
    }

    public void setValorDesconto(Long valorDesconto) {
        record.setValue("ValorDesconto", valorDesconto);
    }

    public Long getBaseCalculoIcms() {
        return record.getValue("BaseCalculoIcms");
    }

    public void setBaseCalculoIcms(Long baseCalculoIcms) {
        record.setValue("BaseCalculoIcms", baseCalculoIcms);
    }

    public Long getBaseCalculoIcmsSt() {
        return record.getValue("BaseCalculoIcmsSt");
    }

    public void setBaseCalculoIcmsSt(Long baseCalculoIcmsSt) {
        record.setValue("BaseCalculoIcmsSt", baseCalculoIcmsSt);
    }

    public Long getValorIpi() {
        return record.getValue("ValorIpi");
    }

    public void setValorIpi(Long valorIpi) {
        record.setValue("ValorIpi", valorIpi);
    }

    public Long getAliquotaIcms() {
        return record.getValue("AliquotaIcms");
    }

    public void setAliquotaIcms(Long aliquotaIcms) {
        record.setValue("AliquotaIcms", aliquotaIcms);
    }

    public Record getRecord() {
        return record;
    }

    public void setRecord(Record record) {
        this.record = record;
    }
}
