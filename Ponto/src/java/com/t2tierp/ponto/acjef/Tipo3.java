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

import java.util.Date;
import org.jrimum.texgit.Record;

public class Tipo3 {

    private Record record;

    public Tipo3() {
    }

    public Tipo3(Record record) {
        this.record = record;
    }

    public Long getNumeroSequencialRegistro() {
        return record.getValue("NumeroSequencialRegistro");
    }

    public void setNumeroSequencialRegistro(Long numeroSequencialRegistro) {
        record.setValue("NumeroSequencialRegistro", numeroSequencialRegistro);
    }

    public String getPisEmpregado() {
        return record.getValue("PisEmpregado");
    }

    public void setPisEmpregado(String pisEmpregado) {
        record.setValue("PisEmpregado", pisEmpregado);
    }

    public Date getDataInicioJornada() {
        return record.getValue("DataInicioJornada");
    }

    public void setDataInicioJornada(Date dataInicioJornada) {
        record.setValue("DataInicioJornada", dataInicioJornada);
    }

    public String getPrimeiroHorarioEntrada() {
        return record.getValue("PrimeiroHorarioEntrada");
    }

    public void setPrimeiroHorarioEntrada(String primeiroHorarioEntrada) {
        record.setValue("PrimeiroHorarioEntrada", primeiroHorarioEntrada);
    }

    public Integer getCodigoHorario() {
        return record.getValue("CodigoHorario");
    }

    public void setCodigoHorario(Integer codigoHorario) {
        record.setValue("CodigoHorario", codigoHorario);
    }

    public Integer getHorasDiurnasNaoExtraordinarias() {
        return record.getValue("HorasDiurnasNaoExtraordinarias");
    }

    public void setHorasDiurnasNaoExtraordinarias(Integer horasDiurnasNaoExtraordinarias) {
        record.setValue("HorasDiurnasNaoExtraordinarias", horasDiurnasNaoExtraordinarias);
    }

    public Integer getHorasNoturnasNaoExtraordinarias() {
        return record.getValue("HorasNoturnasNaoExtraordinarias");
    }

    public void setHorasNoturnasNaoExtraordinarias(Integer horasNoturnasNaoExtraordinarias) {
        record.setValue("HorasNoturnasNaoExtraordinarias", horasNoturnasNaoExtraordinarias);
    }

    public Integer getHorasExtras1() {
        return record.getValue("HorasExtras1");
    }

    public void setHorasExtras1(Integer horasExtras1) {
        record.setValue("HorasExtras1", horasExtras1);
    }

    public Integer getPercentualAdicionalHorasExtras1() {
        return record.getValue("PercentualAdicionalHorasExtras1");
    }

    public void setPercentualAdicionalHorasExtras1(Integer percentualAdicionalHorasExtras1) {
        record.setValue("PercentualAdicionalHorasExtras1", percentualAdicionalHorasExtras1);
    }

    public String getModalidadeHoraExtra1() {
        return record.getValue("ModalidadeHoraExtra1");
    }

    public void setModalidadeHoraExtra1(String modalidadeHoraExtra1) {
        record.setValue("ModalidadeHoraExtra1", modalidadeHoraExtra1);
    }

    public Integer getHorasExtras2() {
        return record.getValue("HorasExtras2");
    }

    public void setHorasExtras2(Integer horasExtras2) {
        record.setValue("HorasExtras2", horasExtras2);
    }

    public Integer getPercentualAdicionalHorasExtras2() {
        return record.getValue("PercentualAdicionalHorasExtras2");
    }

    public void setPercentualAdicionalHorasExtras2(Integer percentualAdicionalHorasExtras2) {
        record.setValue("PercentualAdicionalHorasExtras2", percentualAdicionalHorasExtras2);
    }

    public String getModalidadeHoraExtra2() {
        return record.getValue("ModalidadeHoraExtra2");
    }

    public void setModalidadeHoraExtra2(String modalidadeHoraExtra2) {
        record.setValue("ModalidadeHoraExtra2", modalidadeHoraExtra2);
    }

    public Integer getHorasExtras3() {
        return record.getValue("HorasExtras3");
    }

    public void setHorasExtras3(Integer horasExtras3) {
        record.setValue("HorasExtras3", horasExtras3);
    }

    public Integer getPercentualAdicionalHorasExtras3() {
        return record.getValue("PercentualAdicionalHorasExtras3");
    }

    public void setPercentualAdicionalHorasExtras3(Integer percentualAdicionalHorasExtras3) {
        record.setValue("PercentualAdicionalHorasExtras3", percentualAdicionalHorasExtras3);
    }

    public String getModalidadeHoraExtra3() {
        return record.getValue("ModalidadeHoraExtra3");
    }

    public void setModalidadeHoraExtra3(String modalidadeHoraExtra3) {
        record.setValue("ModalidadeHoraExtra3", modalidadeHoraExtra3);
    }

    public Integer getHorasExtras4() {
        return record.getValue("HorasExtras4");
    }

    public void setHorasExtras4(Integer horasExtras4) {
        record.setValue("HorasExtras4", horasExtras4);
    }

    public Integer getPercentualAdicionalHorasExtras4() {
        return record.getValue("PercentualAdicionalHorasExtras4");
    }

    public void setPercentualAdicionalHorasExtras4(Integer percentualAdicionalHorasExtras4) {
        record.setValue("PercentualAdicionalHorasExtras4", percentualAdicionalHorasExtras4);
    }

    public String getModalidadeHoraExtra4() {
        return record.getValue("ModalidadeHoraExtra4");
    }

    public void setModalidadeHoraExtra4(String modalidadeHoraExtra4) {
        record.setValue("ModalidadeHoraExtra4", modalidadeHoraExtra4);
    }

    public Integer getHorasFaltasAtrasos() {
        return record.getValue("HorasFaltasAtrasos");
    }

    public void setHorasFaltasAtrasos(Integer horasFaltasAtrasos) {
        record.setValue("HorasFaltasAtrasos", horasFaltasAtrasos);
    }

    public Integer getSinalHorasCompensar() {
        return record.getValue("SinalHorasCompensar");
    }

    public void setSinalHorasCompensar(Integer sinalHorasCompensar) {
        record.setValue("SinalHorasCompensar", sinalHorasCompensar);
    }

    public Integer getSaldoHorasCompensar() {
        return record.getValue("SaldoHorasCompensar");
    }

    public void setSaldoHorasCompensar(Integer saldoHorasCompensar) {
        record.setValue("SaldoHorasCompensar", saldoHorasCompensar);
    }

    public Record getRecord() {
        return record;
    }

    public void setRecord(Record record) {
        this.record = record;
    }
}
