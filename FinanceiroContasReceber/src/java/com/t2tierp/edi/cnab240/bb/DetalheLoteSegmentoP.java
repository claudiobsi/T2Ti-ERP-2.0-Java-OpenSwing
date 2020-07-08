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

import java.util.Date;
import org.jrimum.texgit.Record;

public class DetalheLoteSegmentoP {

    private Record record;

    public DetalheLoteSegmentoP() {
    }

    public DetalheLoteSegmentoP(Record record) {
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

    public Integer getCodigoMovimentoRemessa() {
        return record.getValue("CodigoMovimentoRemessa");
    }

    public void setCodigoMovimentoRemessa(Integer codigoMovimentoRemessa) {
        record.setValue("CodigoMovimentoRemessa", codigoMovimentoRemessa);
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

    public Integer getCodigoCarteira() {
        return record.getValue("CodigoCarteira");
    }

    public void setCodigoCarteira(Integer codigoCarteira) {
        record.setValue("CodigoCarteira", codigoCarteira);
    }

    public Integer getFormaCadastroTitulo() {
        return record.getValue("FormaCadastroTitulo");
    }

    public void setFormaCadastroTitulo(Integer formaCadastroTitulo) {
        record.setValue("FormaCadastroTitulo", formaCadastroTitulo);
    }

    public String getTipoDocumento() {
        return record.getValue("TipoDocumento");
    }

    public void setTipoDocumento(String tipoDocumento) {
        record.setValue("TipoDocumento", tipoDocumento);
    }

    public Integer getIdentificadorEmissaoBloqueto() {
        return record.getValue("IdentificadorEmissaoBloqueto");
    }

    public void setIdentificadorEmissaoBloqueto(Integer identificadorEmissaoBloqueto) {
        record.setValue("IdentificadorEmissaoBloqueto", identificadorEmissaoBloqueto);
    }

    public String getIdentificacaoDistribuicao() {
        return record.getValue("IdentificacaoDistribuicao");
    }

    public void setIdentificacaoDistribuicao(String identificacaoDistribuicao) {
        record.setValue("IdentificacaoDistribuicao", identificacaoDistribuicao);
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

    public Long getValorNominalTitulo() {
        return record.getValue("ValorNominalTitulo");
    }

    public void setValorNominalTitulo(Long valorNominalTitulo) {
        record.setValue("ValorNominalTitulo", valorNominalTitulo);
    }

    public Integer getAgenciaEncarregadaCobranca() {
        return record.getValue("AgenciaEncarregadaCobranca");
    }

    public void setAgenciaEncarregadaCobranca(Integer agenciaEncarregadaCobranca) {
        record.setValue("AgenciaEncarregadaCobranca", agenciaEncarregadaCobranca);
    }

    public String getDigitoVerificadorAgenciaCobranca() {
        return record.getValue("DigitoVerificadorAgenciaCobranca");
    }

    public void setDigitoVerificadorAgenciaCobranca(String digitoVerificadorAgenciaCobranca) {
        record.setValue("DigitoVerificadorAgenciaCobranca", digitoVerificadorAgenciaCobranca);
    }

    public Integer getEspecieTitulo() {
        return record.getValue("EspecieTitulo");
    }

    public void setEspecieTitulo(Integer especieTitulo) {
        record.setValue("EspecieTitulo", especieTitulo);
    }

    public String getIdentificadorTituloAceito() {
        return record.getValue("IdentificadorTituloAceito");
    }

    public void setIdentificadorTituloAceito(String identificadorTituloAceito) {
        record.setValue("IdentificadorTituloAceito", identificadorTituloAceito);
    }

    public Date getDataEmissaoTitulo() {
        return record.getValue("DataEmissaoTitulo");
    }

    public void setDataEmissaoTitulo(Date dataEmissaoTitulo) {
        record.setValue("DataEmissaoTitulo", dataEmissaoTitulo);
    }

    public Integer getCodigoJurosMora() {
        return record.getValue("CodigoJurosMora");
    }

    public void setCodigoJurosMora(Integer codigoJurosMora) {
        record.setValue("CodigoJurosMora", codigoJurosMora);
    }

    public Integer getDataJurosMora() {
        return record.getValue("DataJurosMora");
    }

    public void setDataJurosMora(Integer dataJurosMora) {
        record.setValue("DataJurosMora", dataJurosMora);
    }

    public Long getJurosMoraDia() {
        return record.getValue("JurosMoraDia");
    }

    public void setJurosMoraDia(Long jurosMoraDia) {
        record.setValue("JurosMoraDia", jurosMoraDia);
    }

    public Integer getCodigoDesconto1() {
        return record.getValue("CodigoDesconto1");
    }

    public void setCodigoDesconto1(Integer codigoDesconto1) {
        record.setValue("CodigoDesconto1", codigoDesconto1);
    }

    public Date getDataDesconto1() {
        return record.getValue("DataDesconto1");
    }

    public void setDataDesconto1(Date dataDesconto1) {
        record.setValue("DataDesconto1", dataDesconto1);
    }

    public Long getValorPercentualConcedido() {
        return record.getValue("ValorPercentualConcedido");
    }

    public void setValorPercentualConcedido(Long valorPercentualConcedido) {
        record.setValue("ValorPercentualConcedido", valorPercentualConcedido);
    }

    public Long getValorIOFRecolhido() {
        return record.getValue("ValorIOFRecolhido");
    }

    public void setValorIOFRecolhido(Long valorIOFRecolhido) {
        record.setValue("ValorIOFRecolhido", valorIOFRecolhido);
    }

    public Long getValorAbatimento() {
        return record.getValue("ValorAbatimento");
    }

    public void setValorAbatimento(Long valorAbatimento) {
        record.setValue("ValorAbatimento", valorAbatimento);
    }

    public String getIdentificadorTituloEmpresa() {
        return record.getValue("IdentificadorTituloEmpresa");
    }

    public void setIdentificadorTituloEmpresa(String identificadorTituloEmpresa) {
        record.setValue("IdentificadorTituloEmpresa", identificadorTituloEmpresa);
    }

    public Integer getCodigoProtesto() {
        return record.getValue("CodigoProtesto");
    }

    public void setCodigoProtesto(Integer codigoProtesto) {
        record.setValue("CodigoProtesto", codigoProtesto);
    }

    public Integer getNumeroDiasProtesto() {
        return record.getValue("NumeroDiasProtesto");
    }

    public void setNumeroDiasProtesto(Integer numeroDiasProtesto) {
        record.setValue("NumeroDiasProtesto", numeroDiasProtesto);
    }

    public Integer getCodigoBaixaDevolucao() {
        return record.getValue("CodigoBaixaDevolucao");
    }

    public void setCodigoBaixaDevolucao(Integer codigoBaixaDevolucao) {
        record.setValue("CodigoBaixaDevolucao", codigoBaixaDevolucao);
    }

    public Integer getNumeroDiasBaixaDevolucao() {
        return record.getValue("NumeroDiasBaixaDevolucao");
    }

    public void setNumeroDiasBaixaDevolucao(Integer numeroDiasBaixaDevolucao) {
        record.setValue("NumeroDiasBaixaDevolucao", numeroDiasBaixaDevolucao);
    }

    public Integer getCodigoMoeda() {
        return record.getValue("CodigoMoeda");
    }

    public void setCodigoMoeda(Integer codigoMoeda) {
        record.setValue("CodigoMoeda", codigoMoeda);
    }

    public Integer getNumeroContrato() {
        return record.getValue("NumeroContrato");
    }

    public void setNumeroContrato(Integer numeroContrato) {
        record.setValue("NumeroContrato", numeroContrato);
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
