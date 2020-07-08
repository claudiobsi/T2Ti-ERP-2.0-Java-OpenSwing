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

    public Date getDataMarcacao() {
        return record.getValue("DataMarcacao");
    }

    public void setDataMarcacao(Date dataMarcacao) {
        record.setValue("DataMarcacao", dataMarcacao);
    }

    public String getHoraMarcacao() {
        return record.getValue("HoraMarcacao");
    }

    public void setHoraMarcacao(String horaMarcacao) {
        record.setValue("HoraMarcacao", horaMarcacao);
    }

    public String getPisEmpregado() {
        return record.getValue("PisEmpregado");
    }

    public void setPisEmpregado(String pisEmpregado) {
        record.setValue("PisEmpregado", pisEmpregado);
    }

    public Long getNumFabricacaoREP() {
        return record.getValue("NumFabricacaoREP");
    }

    public void setNumFabricacaoREP(Long numFabricacaoREP) {
        record.setValue("NumFabricacaoREP", numFabricacaoREP);
    }

    public String getTipoMarcacao() {
        return record.getValue("TipoMarcacao");
    }

    public void setTipoMarcacao(String tipoMarcacao) {
        record.setValue("TipoMarcacao", tipoMarcacao);
    }

    public Integer getNumeroSequencialEmpregado() {
        return record.getValue("NumeroSequencialEmpregado");
    }

    public void setNumeroSequencialEmpregado(Integer numeroSequencialEmpregado) {
        record.setValue("NumeroSequencialEmpregado", numeroSequencialEmpregado);
    }

    public String getTipoRegistroMarcado() {
        return record.getValue("TipoRegistroMarcado");
    }

    public void setTipoRegistroMarcado(String tipoRegistro) {
        record.setValue("TipoRegistroMarcado", tipoRegistro);
    }

    public String getMotivo() {
        return record.getValue("Motivo");
    }

    public void setMotivo(String motivo) {
        record.setValue("Motivo", motivo);
    }

    public Record getRecord() {
        return record;
    }

    public void setRecord(Record record) {
        this.record = record;
    }
}
