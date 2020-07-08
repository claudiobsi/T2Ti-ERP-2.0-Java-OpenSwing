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
package com.t2tierp.sped.efd.blococ;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class RegistroC380 implements Serializable {

    private static final long serialVersionUID = 1L;
    private String codMod; // Código do modelo do documento fiscal, conforme a Tabela 4.1.1 (Código 02 – Nota Fiscal de Venda a Consumidor) 
    private Date dtDocIni; // Data de Emissão Inicial dos Documentos 
    private Date dtDocFin; //Data de Emissão Final dos Documentos
    private String numDocIni; // Número do documento fiscal inicial
    private String numDocFin; // Número do documento fiscal final
    private BigDecimal vlDoc; // Valor total dos documentos
    private BigDecimal vlDocCanc; // Valor total dos documentos cancelados

    public String getCodMod() {
        return codMod;
    }

    public void setCodMod(String codMod) {
        this.codMod = codMod;
    }

    public Date getDtDocIni() {
        return dtDocIni;
    }

    public void setDtDocIni(Date dtDocIni) {
        this.dtDocIni = dtDocIni;
    }

    public Date getDtDocFin() {
        return dtDocFin;
    }

    public void setDtDocFin(Date dtDocFin) {
        this.dtDocFin = dtDocFin;
    }

    public String getNumDocIni() {
        return numDocIni;
    }

    public void setNumDocIni(String numDocIni) {
        this.numDocIni = numDocIni;
    }

    public String getNumDocFin() {
        return numDocFin;
    }

    public void setNumDocFin(String numDocFin) {
        this.numDocFin = numDocFin;
    }

    public BigDecimal getVlDoc() {
        return vlDoc;
    }

    public void setVlDoc(BigDecimal vlDoc) {
        this.vlDoc = vlDoc;
    }

    public BigDecimal getVlDocCanc() {
        return vlDocCanc;
    }

    public void setVlDocCanc(BigDecimal vlDocCanc) {
        this.vlDocCanc = vlDocCanc;
    }

}
