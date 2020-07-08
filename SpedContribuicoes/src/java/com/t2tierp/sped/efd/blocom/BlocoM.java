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
package com.t2tierp.sped.efd.blocom;

import com.t2tierp.efd.SpedUtil;
import java.io.Serializable;

public class BlocoM implements Serializable {

    private static final long serialVersionUID = 1L;
    private RegistroM001 registroM001;
    private RegistroM990 registroM990;
    private SpedUtil u;

    public BlocoM(SpedUtil spedUtil) {
        registroM001 = new RegistroM001();
        registroM001.setIndMov(1);

        registroM990 = new RegistroM990();
        registroM990.setQtdLinM(0);

        this.u = spedUtil;
    }

    public String gravaRegistroM001() {
        registroM990.setQtdLinM(registroM990.getQtdLinM() + 1);

        return u.preenche("M001")
                + u.preenche(registroM001.getIndMov())
                + u.getDelimitador()
                + u.getCrlf();
    }

    public String gravaRegistroM990() {
        registroM990.setQtdLinM(registroM990.getQtdLinM() + 1);

        return u.preenche("M990")
                + u.preenche(registroM990.getQtdLinM())
                + u.getDelimitador()
                + u.getCrlf();
    }

    /**
     * @return the registroM001
     */
    public RegistroM001 getRegistroM001() {
        return registroM001;
    }

    /**
     * @param registroM001 the registroM001 to set
     */
    public void setRegistroM001(RegistroM001 registroM001) {
        this.registroM001 = registroM001;
    }

    /**
     * @return the registroM990
     */
    public RegistroM990 getRegistroM990() {
        return registroM990;
    }

    /**
     * @param registroM990 the registroM990 to set
     */
    public void setRegistroM990(RegistroM990 registroM990) {
        this.registroM990 = registroM990;
    }

    /**
     * @return the util
     */
    public SpedUtil getUtil() {
        return u;
    }

    /**
     * @param util the util to set
     */
    public void setUtil(SpedUtil util) {
        this.u = util;
    }

}
