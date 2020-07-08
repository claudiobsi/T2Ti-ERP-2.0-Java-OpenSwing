package com.t2tierp.sintegra.java;

import java.util.Date;
import org.jrimum.texgit.Record;

public class Tipo75 {

    private Record record;

    public Tipo75() {
    }

    public Tipo75(Record record) {
        this.record = record;
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

    public String getCodigoProduto() {
        return record.getValue("CodigoProduto");
    }

    public void setCodigoProduto(String codigoProduto) {
        record.setValue("CodigoProduto", codigoProduto);
    }

    public String getCodigoNcm() {
        return record.getValue("CodigoNcm");
    }

    public void setCodigoNcm(String codigoNcm) {
        record.setValue("CodigoNcm", codigoNcm);
    }

    public String getDescricao() {
        return record.getValue("Descricao");
    }

    public void setDescricao(String descricao) {
        record.setValue("Descricao", descricao);
    }

    public String getUnidade() {
        return record.getValue("Unidade");
    }

    public void setUnidade(String unidade) {
        record.setValue("Unidade", unidade);
    }

    public Long getAliquotaIpi() {
        return record.getValue("AliquotaIpi");
    }

    public void setAliquotaIpi(Long aliquotaIpi) {
        record.setValue("AliquotaIpi", aliquotaIpi);
    }

    public Long getAliquotaIcms() {
        return record.getValue("AliquotaIcms");
    }

    public void setAliquotaIcms(Long aliquotaIcms) {
        record.setValue("AliquotaIcms", aliquotaIcms);
    }

    public Long getReducaoBaseCalculoIcms() {
        return record.getValue("ReducaoBaseCalculoIcms");
    }

    public void setReducaoBaseCalculoIcms(Long reducaoBaseCalculoIcms) {
        record.setValue("ReducaoBaseCalculoIcms", reducaoBaseCalculoIcms);
    }

    public Long getBaseCalculoIcmsSt() {
        return record.getValue("BaseCalculoIcmsSt");
    }

    public void setBaseCalculoIcmsSt(Long baseCalculoIcmsSt) {
        record.setValue("BaseCalculoIcmsSt", baseCalculoIcmsSt);
    }

    public Record getRecord() {
        return record;
    }

    public void setRecord(Record record) {
        this.record = record;
    }
}
