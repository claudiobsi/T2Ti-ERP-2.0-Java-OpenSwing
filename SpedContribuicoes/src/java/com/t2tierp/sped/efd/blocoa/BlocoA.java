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
package com.t2tierp.sped.efd.blocoa;

import com.t2tierp.efd.SpedUtil;
import java.io.Serializable;

public class BlocoA implements Serializable {

    private static final long serialVersionUID = 1L;

    private RegistroA001 registroA001;
    private RegistroA990 registroA990;
    private SpedUtil u;

    public BlocoA(SpedUtil spedUtil) {
        registroA001 = new RegistroA001();
        registroA001.setIndMov(0);

        registroA990 = new RegistroA990();
        registroA990.setQtdLinA(0);

        this.u = spedUtil;
    }

    public String gravaRegistroA001() {
        getRegistroA990().setQtdLinA(getRegistroA990().getQtdLinA() + 1);

        String registro = "";
        registro += u.preenche("A001")
                + u.preenche(getRegistroA001().getIndMov())
                + u.getDelimitador()
                + u.getCrlf();

        return registro;
    }

    public String gravaRegistroA990() {
        return u.preenche("A990")
                + u.preenche(getRegistroA990().getQtdLinA())
                + u.getDelimitador()
                + u.getCrlf();
    }

    public RegistroA990 getRegistroA990() {
        return registroA990;
    }

    public RegistroA001 getRegistroA001() {
        return registroA001;
    }

}
