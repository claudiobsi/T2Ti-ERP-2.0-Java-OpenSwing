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

public class TipoB {

    private Record record;

    public TipoB() {
    }

    public TipoB(Record record) {
        this.record = record;
    }

    public Integer getTipoIdentificacao() {
        return record.getValue("TipoIdentificacao");
    }

    public void setTipoIdentificacao(Integer tipoIdentificacao) {
        record.setValue("TipoIdentificacao", tipoIdentificacao);
    }

    public Long getNumeroIdentificadorEstabelecimento() {
        return record.getValue("NumeroIdentificadorEstabelecimento");
    }

    public void setNumeroIdentificadorEstabelecimento(Long numeroIdentificadorEstabelecimento) {
        record.setValue("NumeroIdentificadorEstabelecimento", numeroIdentificadorEstabelecimento);
    }

    public Integer getSequencia() {
        return record.getValue("Sequencia");
    }

    public void setSequencia(Integer sequencia) {
        record.setValue("Sequencia", sequencia);
    }

    public Integer getPrimeiraDeclaracao() {
        return record.getValue("PrimeiraDeclaracao");
    }

    public void setPrimeiraDeclaracao(Integer primeiraDeclaracao) {
        record.setValue("PrimeiraDeclaracao", primeiraDeclaracao);
    }

    public Integer getAlteracao() {
        return record.getValue("Alteracao");
    }

    public void setAlteracao(Integer alteracao) {
        record.setValue("Alteracao", alteracao);
    }

    public Integer getCEP() {
        return record.getValue("CEP");
    }

    public void setCEP(Integer cEP) {
        record.setValue("CEP", cEP);
    }

    public String getFiller() {
        return record.getValue("Filler");
    }

    public void setFiller(String filler) {
        record.setValue("Filler", filler);
    }

    public String getNomeEstabelecimento() {
        return record.getValue("NomeEstabelecimento");
    }

    public void setNomeEstabelecimento(String nomeEstabelecimento) {
        record.setValue("NomeEstabelecimento", nomeEstabelecimento);
    }

    public String getEndereco() {
        return record.getValue("Endereco");
    }

    public void setEndereco(String endereco) {
        record.setValue("Endereco", endereco);
    }

    public String getBairro() {
        return record.getValue("Bairro");
    }

    public void setBairro(String bairro) {
        record.setValue("Bairro", bairro);
    }

    public String getUF() {
        return record.getValue("UF");
    }

    public void setUF(String uF) {
        record.setValue("UF", uF);
    }

    public Integer getTotalEmpregadosPrimeiroDia() {
        return record.getValue("TotalEmpregadosPrimeiroDia");
    }

    public void setTotalEmpregadosPrimeiroDia(Integer totalEmpregadosPrimeiroDia) {
        record.setValue("TotalEmpregadosPrimeiroDia", totalEmpregadosPrimeiroDia);
    }

    public Integer getPorteEstabelecimento() {
        return record.getValue("PorteEstabelecimento");
    }

    public void setPorteEstabelecimento(Integer porteEstabelecimento) {
        record.setValue("PorteEstabelecimento", porteEstabelecimento);
    }

    public Integer getCNAE() {
        return record.getValue("CNAE");
    }

    public void setCNAE(Integer cNAE) {
        record.setValue("CNAE", cNAE);
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

    public String getEmail() {
        return record.getValue("Email");
    }

    public void setEmail(String email) {
        record.setValue("Email", email);
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
