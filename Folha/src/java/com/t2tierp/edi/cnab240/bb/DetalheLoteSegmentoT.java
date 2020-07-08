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

public class DetalheLoteSegmentoT implements Serializable {

    private Record record;
    private DetalheLoteSegmentoU segmentoU;

    public DetalheLoteSegmentoT() {
    }

    public DetalheLoteSegmentoT(Record record) {
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

    public Integer getAgenciaMantenedora() {
        return record.getValue("AgenciaMantenedora");
    }

    public void setAgenciaMantenedora(Integer agenciaMantenedora) {
        record.setValue("AgenciaMantenedora", agenciaMantenedora);
    }

    public String getDigitoVerificadorAgencia() {
        return record.getValue("DigitoVerificadorAgencia");
    }

    public void setDigitoVerificadorAgencia(String digitoVerificadorAgencia) {
        record.setValue("DigitoVerificadorAgencia", digitoVerificadorAgencia);
    }

    public Integer getNumeroContaCorrente() {
        return record.getValue("NumeroContaCorrente");
    }

    public void setNumeroContaCorrente(Integer numeroContaCorrente) {
        record.setValue("NumeroContaCorrente", numeroContaCorrente);
    }

    public String getDigitoVerificadorContaCorrente() {
        return record.getValue("DigitoVerificadorContaCorrente");
    }

    public void setDigitoVerificadorContaCorrente(String digitoVerificadorContaCorrente) {
        record.setValue("DigitoVerificadorContaCorrente", digitoVerificadorContaCorrente);
    }

    public String getDigitoVerificadorAgenciaConta() {
        return record.getValue("DigitoVerificadorAgenciaConta");
    }

    public void setDigitoVerificadorAgenciaConta(String digitoVerificadorAgenciaConta) {
        record.setValue("DigitoVerificadorAgenciaConta", digitoVerificadorAgenciaConta);
    }

    public String getIdentificadorTitulo() {
        return record.getValue("IdentificadorTitulo");
    }

    public void setIdentificadorTitulo(String identificadorTitulo) {
        record.setValue("IdentificadorTitulo", identificadorTitulo);
    }

    public String getCodigoCarteira() {
        return record.getValue("CodigoCarteira");
    }

    public void setCodigoCarteira(String codigoCarteira) {
        record.setValue("CodigoCarteira", codigoCarteira);
    }

    public String getNumeroDocumentoCobranca() {
        return record.getValue("NumeroDocumentoCobranca");
    }

    public void setNumeroDocumentoCobranca(String numeroDocumentoCobranca) {
        record.setValue("NumeroDocumentoCobranca", numeroDocumentoCobranca);
    }

    public Date getDataVencimentoTitulo() {
        return record.getValue("DataVencimentoTitulo");
    }

    public void setDataVencimentoTitulo(Date dataVencimentoTitulo) {
        record.setValue("DataVencimentoTitulo", dataVencimentoTitulo);
    }

    public Integer getValorNominalTitulo() {
        return record.getValue("ValorNominalTitulo");
    }

    public void setValorNominalTitulo(Integer valorNominalTitulo) {
        record.setValue("ValorNominalTitulo", valorNominalTitulo);
    }

    public Integer getNumeroBanco() {
        return record.getValue("NumeroBanco");
    }

    public void setNumeroBanco(Integer numeroBanco) {
        record.setValue("NumeroBanco", numeroBanco);
    }

    public Integer getAgenciaCobradoraRecebedora() {
        return record.getValue("AgenciaCobradoraRecebedora");
    }

    public void setAgenciaCobradoraRecebedora(Integer agenciaCobradoraRecebedora) {
        record.setValue("AgenciaCobradoraRecebedora", agenciaCobradoraRecebedora);
    }

    public String getDigitoVerificadorAgenciaCobradora() {
        return record.getValue("DigitoVerificadorAgenciaCobradora");
    }

    public void setDigitoVerificadorAgenciaCobradora(String digitoVerificadorAgenciaCobradora) {
        record.setValue("DigitoVerificadorAgenciaCobradora", digitoVerificadorAgenciaCobradora);
    }

    public String getIdentificadorTituloEmpresa() {
        return record.getValue("IdentificadorTituloEmpresa");
    }

    public void setIdentificadorTituloEmpresa(String identificadorTituloEmpresa) {
        record.setValue("IdentificadorTituloEmpresa", identificadorTituloEmpresa);
    }

    public String getCodigoMoeda() {
        return record.getValue("CodigoMoeda");
    }

    public void setCodigoMoeda(String codigoMoeda) {
        record.setValue("CodigoMoeda", codigoMoeda);
    }

    public Integer getTipoInscricaoSacadoAvalista() {
        return record.getValue("TipoInscricaoSacadoAvalista");
    }

    public void setTipoInscricaoSacadoAvalista(Integer tipoInscricaoSacadoAvalista) {
        record.setValue("TipoInscricaoSacadoAvalista", tipoInscricaoSacadoAvalista);
    }

    public Long getNumeroInscricaoSacadoAvalista() {
        return record.getValue("NumeroInscricaoSacadoAvalista");
    }

    public void setNumeroInscricaoSacadoAvalista(Long numeroInscricaoSacadoAvalista) {
        record.setValue("NumeroInscricaoSacadoAvalista", numeroInscricaoSacadoAvalista);
    }

    public String getNomeSacadorAvalista() {
        return record.getValue("NomeSacadorAvalista");
    }

    public void setNomeSacadorAvalista(String nomeSacadorAvalista) {
        record.setValue("NomeSacadorAvalista", nomeSacadorAvalista);
    }

    public String getNumeroContratoOperacaoCredito() {
        return record.getValue("NumeroContratoOperacaoCredito");
    }

    public void setNumeroContratoOperacaoCredito(String numeroContratoOperacaoCredito) {
        record.setValue("NumeroContratoOperacaoCredito", numeroContratoOperacaoCredito);
    }

    public Long getValorTarifaCustas() {
        return record.getValue("ValorTarifaCustas");
    }

    public void setValorTarifaCustas(Long valorTarifaCustas) {
        record.setValue("ValorTarifaCustas", valorTarifaCustas);
    }

    public String getIdentificadorRejeicao() {
        return record.getValue("IdentificadorRejeicao");
    }

    public void setIdentificadorRejeicao(String identificadorRejeicao) {
        record.setValue("IdentificadorRejeicao", identificadorRejeicao);
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

    public DetalheLoteSegmentoU getSegmentoU() {
        return segmentoU;
    }

    public void setSegmentoU(DetalheLoteSegmentoU segmentoU) {
        this.segmentoU = segmentoU;
    }
}
