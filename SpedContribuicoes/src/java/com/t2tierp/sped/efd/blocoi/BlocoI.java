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
package com.t2tierp.sped.efd.blocoi;

import com.t2tierp.efd.SpedUtil;
import java.io.Serializable;

public class BlocoI implements Serializable {

    private static final long serialVersionUID = 1L;
    private RegistroI001 registroI001;
    private RegistroI990 registroI990;
    private SpedUtil u;

    public BlocoI(SpedUtil spedUtil) {
        registroI001 = new RegistroI001();
        registroI001.setIndMov(1);

        registroI990 = new RegistroI990();
        registroI990.setQtdLinI(0);

        this.u = spedUtil;
    }

    public String gravaRegistroI001() {
        registroI990.setQtdLinI(registroI990.getQtdLinI() + 1);

        return u.preenche("I001")
                + u.preenche(registroI001.getIndMov())
                + u.getDelimitador()
                + u.getCrlf();
    }

    public String gravaRegistroI990() {
        registroI990.setQtdLinI(registroI990.getQtdLinI() + 1);

        return u.preenche("I990")
                + u.preenche(registroI990.getQtdLinI())
                + u.getDelimitador()
                + u.getCrlf();
    }

    public RegistroI001 getRegistroI001() {
        return registroI001;
    }

    public RegistroI990 getRegistroI990() {
        return registroI990;
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
