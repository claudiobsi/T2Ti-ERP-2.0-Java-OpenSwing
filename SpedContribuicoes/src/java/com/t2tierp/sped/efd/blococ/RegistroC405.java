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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RegistroC405 implements Serializable {

    private static final long serialVersionUID = 1L;
    private Date dtDoc; // Data do movimento a que se refere a Redução Z
    private Integer cro; // Posição do Contador de Reinício de Operação
    private Integer crz; // Posição do Contador de Redução Z
    private Integer numCooFin; // Número do Contador de Ordem de Operação do último documento emitido no dia. (Número do COO na Redução Z)
    private BigDecimal gtFin; // Valor do Grande Total final
    private BigDecimal vlBrt; // Valor da venda bruta
    private List<RegistroC481> registroC481List; // BLOCO C - Lista de RegistroC420 (FILHO)
    private List<RegistroC485> registroC485List; // BLOCO C - Lista de RegistroC490 (FILHO)

    public RegistroC405() {
        registroC481List = new ArrayList<>();
        registroC485List = new ArrayList<>();
    }

    /**
     * @return the dtDoc
     */
    public Date getDtDoc() {
        return dtDoc;
    }

    /**
     * @param dtDoc the dtDoc to set
     */
    public void setDtDoc(Date dtDoc) {
        this.dtDoc = dtDoc;
    }

    /**
     * @return the cro
     */
    public Integer getCro() {
        return cro;
    }

    /**
     * @param cro the cro to set
     */
    public void setCro(Integer cro) {
        this.cro = cro;
    }

    /**
     * @return the crz
     */
    public Integer getCrz() {
        return crz;
    }

    /**
     * @param crz the crz to set
     */
    public void setCrz(Integer crz) {
        this.crz = crz;
    }

    /**
     * @return the numCooFin
     */
    public Integer getNumCooFin() {
        return numCooFin;
    }

    /**
     * @param numCooFin the numCooFin to set
     */
    public void setNumCooFin(Integer numCooFin) {
        this.numCooFin = numCooFin;
    }

    /**
     * @return the gtFin
     */
    public BigDecimal getGtFin() {
        return gtFin;
    }

    /**
     * @param gtFin the gtFin to set
     */
    public void setGtFin(BigDecimal gtFin) {
        this.gtFin = gtFin;
    }

    /**
     * @return the vlBrt
     */
    public BigDecimal getVlBrt() {
        return vlBrt;
    }

    /**
     * @param vlBrt the vlBrt to set
     */
    public void setVlBrt(BigDecimal vlBrt) {
        this.vlBrt = vlBrt;
    }

    /**
     * @return the registroC481List
     */
    public List<RegistroC481> getRegistroC481List() {
        return registroC481List;
    }

    /**
     * @param registroC481List the registroC481List to set
     */
    public void setRegistroC481List(List<RegistroC481> registroC481List) {
        this.registroC481List = registroC481List;
    }

    /**
     * @return the registroC485List
     */
    public List<RegistroC485> getRegistroC485List() {
        return registroC485List;
    }

    /**
     * @param registroC485List the registroC490List to set
     */
    public void setRegistroC485List(List<RegistroC485> registroC485List) {
        this.registroC485List = registroC485List;
    }

}