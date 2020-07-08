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
import java.util.ArrayList;
import java.util.List;

public class RegistroC001 implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer indMov; //Indicador de movimento: 0 - Bloco com dados informados, 1 - Bloco sem dados informados
    private List<RegistroC100> registroC100List; // BLOCO C - Lista de RegistroC100 (FILHO)
    private List<RegistroC380> registroC380List; // BLOCO C - Lista de RegistroC380 (FILHO)
    private List<RegistroC400> registroC400List; // BLOCO C - Lista de RegistroC400 (FILHO)

    public RegistroC001() {
        registroC100List = new ArrayList<>();
        registroC380List = new ArrayList<>();
        registroC400List = new ArrayList<>();
    }

    /**
     * @return the indMov
     */
    public Integer getIndMov() {
        return indMov;
    }

    /**
     * @param indMov the indMov to set
     */
    public void setIndMov(Integer indMov) {
        this.indMov = indMov;
    }

    /**
     * @return the registroC100List
     */
    public List<RegistroC100> getRegistroC100List() {
        return registroC100List;
    }

    /**
     * @param registroC100List the registroC100List to set
     */
    public void setRegistroC100List(List<RegistroC100> registroC100List) {
        this.registroC100List = registroC100List;
    }

    /**
     * @return the registroC380List
     */
    public List<RegistroC380> getRegistroC380List() {
        return registroC380List;
    }

    /**
     * @param registroC380List the registroC380List to set
     */
    public void setRegistroC380List(List<RegistroC380> registroC380List) {
        this.registroC380List = registroC380List;
    }

    /**
     * @return the registroC400List
     */
    public List<RegistroC400> getRegistroC400List() {
        return registroC400List;
    }

    /**
     * @param registroC400List the registroC400List to set
     */
    public void setRegistroC400List(List<RegistroC400> registroC400List) {
        this.registroC400List = registroC400List;
    }

}
