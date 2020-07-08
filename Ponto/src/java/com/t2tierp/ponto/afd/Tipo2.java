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
package com.t2tierp.ponto.afd;

import java.util.Date;
import org.jrimum.texgit.Record;

public class Tipo2 {

    private Record record;

    public Tipo2() {
    }

    public Tipo2(Record record) {
        this.record = record;
    }

    public Long getNumeroSequencialRegistro() {
        return record.getValue("NumeroSequencialRegistro");
    }

    public void setNumeroSequencialRegistro(Long numeroSequencialRegistro) {
        record.setValue("NumeroSequencialRegistro", numeroSequencialRegistro);
    }

    public Date getDataGravacao() {
        return record.getValue("DataGravacao");
    }

    public void setDataGravacao(Date dataGravacao) {
        record.setValue("DataGravacao", dataGravacao);
    }

    public String getHoraGravacao() {
        return record.getValue("HoraGravacao");
    }

    public void setHoraGravacao(String horaGravacao) {
        record.setValue("HoraGravacao", horaGravacao);
    }

    public Integer getTipoIdentificadorEmpregador() {
        return record.getValue("TipoIdentificadorEmpregador");
    }

    public void setTipoIdentificadorEmpregador(Integer tipoIdentificadorEmpregador) {
        record.setValue("TipoIdentificadorEmpregador", tipoIdentificadorEmpregador);
    }

    public Long getCnpjCpfEmpregador() {
        return record.getValue("CnpjCpfEmpregador");
    }

    public void setCnpjCpfEmpregador(Long cnpjCpfEmpregador) {
        record.setValue("CnpjCpfEmpregador", cnpjCpfEmpregador);
    }

    public Long getCeiEmpregador() {
        return record.getValue("CeiEmpregador");
    }

    public void setCeiEmpregador(Long ceiEmpregador) {
        record.setValue("CeiEmpregador", ceiEmpregador);
    }

    public String getRazaoSocialEmpregador() {
        return record.getValue("RazaoSocialEmpregador");
    }

    public void setRazaoSocialEmpregador(String razaoSocialEmpregador) {
        record.setValue("RazaoSocialEmpregador", razaoSocialEmpregador);
    }

    public String getLocalPrestacaoServico() {
        return record.getValue("LocalPrestacaoServico");
    }

    public void setLocalPrestacaoServico(String localPrestacaoServico) {
        record.setValue("LocalPrestacaoServico", localPrestacaoServico);
    }

    public Record getRecord() {
        return record;
    }

    public void setRecord(Record record) {
        this.record = record;
    }
}
