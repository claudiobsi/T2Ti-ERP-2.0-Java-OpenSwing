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

import org.jrimum.texgit.Record;

public class Tipo9 {

    private Record record;

    public Tipo9() {
    }

    public Tipo9(Record record) {
        this.record = record;
    }

    public Long getNumeroSequencialRegistro() {
        return record.getValue("NumeroSequencialRegistro");
    }

    public void setNumeroSequencialRegistro(Long numeroSequencialRegistro) {
        record.setValue("NumeroSequencialRegistro", numeroSequencialRegistro);
    }

    public Long getQuantidadeTipo2() {
        return record.getValue("QuantidadeTipo2");
    }

    public void setQuantidadeTipo2(Long quantidadeTipo2) {
        record.setValue("QuantidadeTipo2", quantidadeTipo2);
    }

    public Long getQuantidadeTipo3() {
        return record.getValue("QuantidadeTipo3");
    }

    public void setQuantidadeTipo3(Long quantidadeTipo3) {
        record.setValue("QuantidadeTipo3", quantidadeTipo3);
    }

    public Long getQuantidadeTipo4() {
        return record.getValue("QuantidadeTipo4");
    }

    public void setQuantidadeTipo4(Long quantidadeTipo4) {
        record.setValue("QuantidadeTipo4", quantidadeTipo4);
    }

    public Long getQuantidadeTipo5() {
        return record.getValue("QuantidadeTipo5");
    }

    public void setQuantidadeTipo5(Long quantidadeTipo5) {
        record.setValue("QuantidadeTipo5", quantidadeTipo5);
    }

    public Record getRecord() {
        return record;
    }

    public void setRecord(Record record) {
        this.record = record;
    }
}
