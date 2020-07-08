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
package com.t2tierp.edi.cnab240.bb;

import java.io.Serializable;
import java.util.Date;
import org.jrimum.texgit.Record;

public class DetalheLoteSegmentoU implements Serializable {

    private Record record;

    public DetalheLoteSegmentoU() {
    }

    public DetalheLoteSegmentoU(Record record) {
        this.record = record;
    }

    public Integer getCodigoBanco() {
        return record.getValue("CodigoBanco");
    }

    public void setCodigoBanco(Integer codigoBanco) {
        record.setValue("CodigoBanco", codigoBanco);
    }

    public Integer getLoteServico() {
        return record.getValue("LoteServico");
    }

    public void setLoteServico(Integer loteServico) {
        record.setValue("LoteServico", loteServico);
    }

    public Integer getTipoRegistro() {
        return record.getValue("TipoRegistro");
    }

    public void setTipoRegistro(Integer tipoRegistro) {
        record.setValue("TipoRegistro", tipoRegistro);
    }

    public Integer getNumeroSequencialRegistro() {
        return record.getValue("NumeroSequencialRegistro");
    }

    public void setNumeroSequencialRegistro(Integer numeroSequencialRegistro) {
        record.setValue("NumeroSequencialRegistro", numeroSequencialRegistro);
    }

    public String getCNAB1() {
        return record.getValue("CNAB1");
    }

    public void setCNAB1(String cNAB1) {
        record.setValue("CNAB1", cNAB1);
    }

    public Integer getCodigoMovimentoRetorno() {
        return record.getValue("CodigoMovimentoRetorno");
    }

    public void setCodigoMovimentoRetorno(Integer codigoMovimentoRetorno) {
        record.setValue("CodigoMovimentoRetorno", codigoMovimentoRetorno);
    }

    public Long getJurosMultaEncargos() {
        return record.getValue("JurosMultaEncargos");
    }

    public void setJurosMultaEncargos(Long jurosMultaEncargos) {
        record.setValue("JurosMultaEncargos", jurosMultaEncargos);
    }

    public Long getValorDesconto() {
        return record.getValue("ValorDesconto");
    }

    public void setValorDesconto(Long valorDesconto) {
        record.setValue("ValorDesconto", valorDesconto);
    }

    public Long getValorAbatimento() {
        return record.getValue("ValorAbatimento");
    }

    public void setValorAbatimento(Long valorAbatimento) {
        record.setValue("ValorAbatimento", valorAbatimento);
    }

    public Long getValorIOF() {
        return record.getValue("ValorIOF");
    }

    public void setValorIOF(Long valorIOF) {
        record.setValue("ValorIOF", valorIOF);
    }

    public Long getValorPagoSacado() {
        return record.getValue("ValorPagoSacado");
    }

    public void setValorPagoSacado(Long valorPagoSacado) {
        record.setValue("ValorPagoSacado", valorPagoSacado);
    }

    public Long getValorLiquidoCreditado() {
        return record.getValue("ValorLiquidoCreditado");
    }

    public void setValorLiquidoCreditado(Long valorLiquidoCreditado) {
        record.setValue("ValorLiquidoCreditado", valorLiquidoCreditado);
    }

    public Long getValorOutrasDespesas() {
        return record.getValue("ValorOutrasDespesas");
    }

    public void setValorOutrasDespesas(Long valorOutrasDespesas) {
        record.setValue("ValorOutrasDespesas", valorOutrasDespesas);
    }

    public Long getValorOutrosCreditos() {
        return record.getValue("ValorOutrosCreditos");
    }

    public void setValorOutrosCreditos(Long valorOutrosCreditos) {
        record.setValue("ValorOutrosCreditos", valorOutrosCreditos);
    }

    public Date getDataOcorrenciaPagamento() {
        return record.getValue("DataOcorrenciaPagamento");
    }

    public void setDataOcorrenciaPagamento(Date dataOcorrenciaPagamento) {
        record.setValue("DataOcorrenciaPagamento", dataOcorrenciaPagamento);
    }

    public Date getDataEfetivacaoCredito() {
        return record.getValue("DataEfetivacaoCredito");
    }

    public void setDataEfetivacaoCredito(Date dataEfetivacaoCredito) {
        record.setValue("DataEfetivacaoCredito", dataEfetivacaoCredito);
    }

    public String getCodigoOcorrencia() {
        return record.getValue("CodigoOcorrencia");
    }

    public void setCodigoOcorrencia(String codigoOcorrencia) {
        record.setValue("CodigoOcorrencia", codigoOcorrencia);
    }

    public Date getDataOcorrencia() {
        return record.getValue("DataOcorrencia");
    }

    public void setDataOcorrencia(Date dataOcorrencia) {
        record.setValue("DataOcorrencia", dataOcorrencia);
    }

    public Long getValorOcorrencia() {
        return record.getValue("ValorOcorrencia");
    }

    public void setValorOcorrencia(Long valorOcorrencia) {
        record.setValue("ValorOcorrencia", valorOcorrencia);
    }

    public String getComplementoOcorrencia() {
        return record.getValue("ComplementoOcorrencia");
    }

    public void setComplementoOcorrencia(String complementoOcorrencia) {
        record.setValue("ComplementoOcorrencia", complementoOcorrencia);
    }

    public Integer getCodigoBancoCorrespondenteCompe() {
        return record.getValue("CodigoBancoCorrespondenteCompe");
    }

    public void setCodigoBancoCorrespondenteCompe(Integer codigoBancoCorrespondenteCompe) {
        record.setValue("CodigoBancoCorrespondenteCompe", codigoBancoCorrespondenteCompe);
    }

    public String getNossoNumeroBancoCorrespondente() {
        return record.getValue("NossoNumeroBancoCorrespondente");
    }

    public void setNossoNumeroBancoCorrespondente(String nossoNumeroBancoCorrespondente) {
        record.setValue("NossoNumeroBancoCorrespondente", nossoNumeroBancoCorrespondente);
    }

    public String getCNAB2() {
        return record.getValue("CNAB2");
    }

    public void setCNAB2(String cNAB2) {
        record.setValue("CNAB2", cNAB2);
    }

    public Record getRecord() {
        return record;
    }

    public void setRecord(Record record) {
        this.record = record;
    }
}
