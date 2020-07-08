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
package com.t2tierp.ponto.acjef;

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

    public Integer getCodigoHorario() {
        return record.getValue("CodigoHorario");
    }

    public void setCodigoHorario(Integer codigoHorario) {
        record.setValue("CodigoHorario", codigoHorario);
    }

    public String getHoraEntrada() {
        return record.getValue("HoraEntrada");
    }

    public void setHoraEntrada(String horaEntrada) {
        record.setValue("HoraEntrada", horaEntrada);
    }

    public String getHoraSaida() {
        return record.getValue("HoraSaida");
    }

    public void setHoraSaida(String horaSaida) {
        record.setValue("HoraSaida", horaSaida);
    }

    public String getInicioIntervalo() {
        return record.getValue("InicioIntervalo");
    }

    public void setInicioIntervalo(String inicioIntervalo) {
        record.setValue("InicioIntervalo", inicioIntervalo);
    }

    public String getFimIntervalo() {
        return record.getValue("FimIntervalo");
    }

    public void setFimIntervalo(String fimIntervalo) {
        record.setValue("FimIntervalo", fimIntervalo);
    }

    public Record getRecord() {
        return record;
    }

    public void setRecord(Record record) {
        this.record = record;
    }
}
