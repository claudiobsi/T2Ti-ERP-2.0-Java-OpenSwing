/*
 * The MIT License
 * 
 * Copyright: Copyright (C) 2014 T2Ti.COM
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 * 
 * The author may be contacted at: t2ti.com@gmail.com
 *
 * @author Claudio de Barros (T2Ti.com)
 * @version 2.0
 */
package com.t2tierp.folhapagamento.cliente.caged;

import org.jrimum.texgit.Record;

public class TipoA {

    private Record record;

    public TipoA() {
    }

    public TipoA(Record record) {
        this.record = record;
    }

    public String getTipoLayout() {
        return record.getValue("TipoLayout");
    }

    public void setTipoLayout(String tipoLayout) {
        record.setValue("TipoLayout", tipoLayout);
    }

    public String getFiller() {
        return record.getValue("Filler");
    }

    public void setFiller(String filler) {
        record.setValue("Filler", filler);
    }

    public Integer getMesCompetencia() {
        return record.getValue("MesCompetencia");
    }

    public void setMesCompetencia(Integer mesCompetencia) {
        record.setValue("MesCompetencia", mesCompetencia);
    }

    public Integer getAnoCompetencia() {
        return record.getValue("AnoCompetencia");
    }

    public void setAnoCompetencia(Integer anoCompetencia) {
        record.setValue("AnoCompetencia", anoCompetencia);
    }

    public Integer getAlteracao() {
        return record.getValue("Alteracao");
    }

    public void setAlteracao(Integer alteracao) {
        record.setValue("Alteracao", alteracao);
    }

    public Integer getSequencia() {
        return record.getValue("Sequencia");
    }

    public void setSequencia(Integer sequencia) {
        record.setValue("Sequencia", sequencia);
    }

    public Integer getTipoIdentificacao() {
        return record.getValue("TipoIdentificacao");
    }

    public void setTipoIdentificacao(Integer tipoIdentificacao) {
        record.setValue("TipoIdentificacao", tipoIdentificacao);
    }

    public Long getNumeroIdentificadorAutorizado() {
        return record.getValue("NumeroIdentificadorAutorizado");
    }

    public void setNumeroIdentificadorAutorizado(Long numeroIdentificadorAutorizado) {
        record.setValue("NumeroIdentificadorAutorizado", numeroIdentificadorAutorizado);
    }

    public String getNomeAutorizado() {
        return record.getValue("NomeAutorizado");
    }

    public void setNomeAutorizado(String nomeAutorizado) {
        record.setValue("NomeAutorizado", nomeAutorizado);
    }

    public String getEnderecoAutorizado() {
        return record.getValue("EnderecoAutorizado");
    }

    public void setEnderecoAutorizado(String enderecoAutorizado) {
        record.setValue("EnderecoAutorizado", enderecoAutorizado);
    }

    public Integer getCEP() {
        return record.getValue("CEP");
    }

    public void setCEP(Integer cEP) {
        record.setValue("CEP", cEP);
    }

    public String getUF() {
        return record.getValue("UF");
    }

    public void setUF(String uF) {
        record.setValue("UF", uF);
    }

    public Integer getDDD() {
        return record.getValue("DDD");
    }

    public void setDDD(Integer dDD) {
        record.setValue("DDD", dDD);
    }

    public Integer getTelefone() {
        return record.getValue("Telefone");
    }

    public void setTelefone(Integer telefone) {
        record.setValue("Telefone", telefone);
    }

    public Integer getRamal() {
        return record.getValue("Ramal");
    }

    public void setRamal(Integer ramal) {
        record.setValue("Ramal", ramal);
    }

    public Integer getTotalEstabelecimentosInformados() {
        return record.getValue("TotalEstabelecimentosInformados");
    }

    public void setTotalEstabelecimentosInformados(Integer totalEstabelecimentosInformados) {
        record.setValue("TotalEstabelecimentosInformados", totalEstabelecimentosInformados);
    }

    public Integer getTotalMovimentacoesInformadas() {
        return record.getValue("TotalMovimentacoesInformadas");
    }

    public void setTotalMovimentacoesInformadas(Integer totalMovimentacoesInformadas) {
        record.setValue("TotalMovimentacoesInformadas", totalMovimentacoesInformadas);
    }

    public String getFiller2() {
        return record.getValue("Filler2");
    }

    public void setFiller2(String filler2) {
        record.setValue("Filler2", filler2);
    }

    public Record getRecord() {
        return record;
    }

    public void setRecord(Record record) {
        this.record = record;
    }
}
