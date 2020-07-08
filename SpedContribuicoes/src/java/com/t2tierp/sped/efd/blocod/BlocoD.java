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
package com.t2tierp.sped.efd.blocod;

import com.t2tierp.efd.SpedUtil;
import java.io.Serializable;

public class BlocoD implements Serializable {

    private static final long serialVersionUID = 1L;
    private RegistroD001 registroD001;
    private RegistroD990 registroD990;
    private SpedUtil u;

    public BlocoD(SpedUtil spedUtil) {
        registroD001 = new RegistroD001();
        registroD001.setIndMov(1);

        registroD990 = new RegistroD990();
        registroD990.setQtdLinD(0);

        this.u = spedUtil;
    }

    public String gravaRegistroD001() {
        registroD990.setQtdLinD(registroD990.getQtdLinD() + 1);

        return u.preenche("D001")
                + u.preenche(registroD001.getIndMov())
                + u.getDelimitador()
                + u.getCrlf();
    }

    public String gravaRegistroD990() {
        registroD990.setQtdLinD(registroD990.getQtdLinD() + 1);

        return u.preenche("D990")
                + u.preenche(registroD990.getQtdLinD())
                + u.getDelimitador()
                + u.getCrlf();
    }

    /**
     * @return the registroD001
     */
    public RegistroD001 getRegistroD001() {
        return registroD001;
    }

    /**
     * @param registroD001 the registroD001 to set
     */
    public void setRegistroD001(RegistroD001 registroD001) {
        this.registroD001 = registroD001;
    }

    /**
     * @return the registroD990
     */
    public RegistroD990 getRegistroD990() {
        return registroD990;
    }

    /**
     * @param registroD990 the registroD990 to set
     */
    public void setRegistroD990(RegistroD990 registroD990) {
        this.registroD990 = registroD990;
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
