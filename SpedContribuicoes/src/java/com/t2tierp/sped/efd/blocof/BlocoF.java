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
package com.t2tierp.sped.efd.blocof;

import com.t2tierp.efd.SpedUtil;
import java.io.Serializable;

public class BlocoF implements Serializable {

    private static final long serialVersionUID = 1L;
    private RegistroF001 registroF001;
    private RegistroF990 registroF990;
    private SpedUtil u;

    public BlocoF(SpedUtil spedUtil) {
        registroF001 = new RegistroF001();
        registroF001.setIndMov(1);

        registroF990 = new RegistroF990();
        registroF990.setQtdLinF(0);

        this.u = spedUtil;
    }

    public String gravaRegistroF001() {
        getRegistroF990().setQtdLinF(getRegistroF990().getQtdLinF() + 1);

        return u.preenche("F001")
                + u.preenche(registroF001.getIndMov())
                + u.getDelimitador()
                + u.getCrlf();
    }

    public String gravaRegistroF990() {
        getRegistroF990().setQtdLinF(getRegistroF990().getQtdLinF() + 1);

        return u.preenche("F990")
                + u.preenche(getRegistroF990().getQtdLinF())
                + u.getDelimitador()
                + u.getCrlf();
    }

    public RegistroF001 getRegistroF001() {
        return registroF001;
    }

    public void setRegistroF001(RegistroF001 registroF001) {
        this.registroF001 = registroF001;
    }

    public RegistroF990 getRegistroF990() {
        return registroF990;
    }

    public void setRegistroF990(RegistroF990 registroF990) {
        this.registroF990 = registroF990;
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
