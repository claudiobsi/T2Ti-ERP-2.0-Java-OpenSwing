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
package com.t2tierp.sped.efd.bloco1;

import com.t2tierp.efd.SpedUtil;
import java.io.Serializable;

public class Bloco1 implements Serializable {

    private static final long serialVersionUID = 1L;
    private Registro1001 registro1001;
    private Registro1990 registro1990;
    private SpedUtil u;

    public Bloco1(SpedUtil spedUtil) {
        registro1001 = new Registro1001();
        registro1001.setIndMov(1);

        registro1990 = new Registro1990();
        registro1990.setQtdLin1(0);

        this.u = spedUtil;
    }

    public String gravaRegistro1001() {
        registro1990.setQtdLin1(registro1990.getQtdLin1() + 1);

        return u.preenche("1001")
                + u.preenche(registro1001.getIndMov())
                + u.getDelimitador()
                + u.getCrlf();
    }

    public String gravaRegistro1990() {
        registro1990.setQtdLin1(registro1990.getQtdLin1() + 1);

        return u.preenche("1990")
                + u.preenche(registro1990.getQtdLin1())
                + u.getDelimitador()
                + u.getCrlf();
    }

    public Registro1001 getRegistro1001() {
        return registro1001;
    }

    public Registro1990 getRegistro1990() {
        return registro1990;
    }

}
