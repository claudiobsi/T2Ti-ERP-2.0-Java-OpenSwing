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
package com.t2tierp.ponto.afdt;

import java.util.Date;
import org.jrimum.texgit.Record;

public class Tipo1 {

    private Record record;

    public Tipo1() {
    }

    public Tipo1(Record record) {
        this.record = record;
    }

    public Long getNumeroSequencialRegistro() {
        return record.getValue("NumeroSequencialRegistro");
    }

    public void setNumeroSequencialRegistro(Long numeroSequencialRegistro) {
        record.setValue("NumeroSequencialRegistro", numeroSequencialRegistro);
    }

    public Integer getTipoIdentificadorEmpregador() {
        return record.getValue("TipoIdentificadorEmpregador");
    }

    public void setTipoIdentificadorEmpregador(Integer tipoIdentificadorEmpregador) {
        record.setValue("TipoIdentificadorEmpregador", tipoIdentificadorEmpregador);
    }

    public Long getCnpjCpfEmpreador() {
        return record.getValue("CnpjCpfEmpreador");
    }

    public void setCnpjCpfEmpreador(Long cnpjCpfEmpreador) {
        record.setValue("CnpjCpfEmpreador", cnpjCpfEmpreador);
    }

    public String getCeiEmpregador() {
        return record.getValue("CeiEmpregador");
    }

    public void setCeiEmpregador(String ceiEmpregador) {
        record.setValue("CeiEmpregador", ceiEmpregador);
    }

    public String getRazaoSocialEmpregador() {
        return record.getValue("RazaoSocialEmpregador");
    }

    public void setRazaoSocialEmpregador(String razaoSocialEmpregador) {
        record.setValue("RazaoSocialEmpregador", razaoSocialEmpregador);
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

    public Date getDataGeracaoArquivo() {
        return record.getValue("DataGeracaoArquivo");
    }

    public void setDataGeracaoArquivo(Date dataGeracaoArquivo) {
        record.setValue("DataGeracaoArquivo", dataGeracaoArquivo);
    }

    public String getHoraGeracaoArquivo() {
        return record.getValue("HoraGeracaoArquivo");
    }

    public void setHoraGeracaoArquivo(String horaGeracaoArquivo) {
        record.setValue("HoraGeracaoArquivo", horaGeracaoArquivo);
    }

    public Record getRecord() {
        return record;
    }

    public void setRecord(Record record) {
        this.record = record;
    }
}
