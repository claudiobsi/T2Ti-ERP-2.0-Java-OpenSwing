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
package com.t2tierp.ponto.cliente;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.swing.JLabel;
import org.openswing.swing.message.receive.java.Response;
import org.openswing.swing.message.receive.java.VOResponse;
import org.openswing.swing.util.client.ClientUtils;

public class Relogio implements Runnable {

    private Calendar dataAtual;
    private SimpleDateFormat formato = new SimpleDateFormat("E, dd 'de' MMMM 'de' yyyy - HH:mm:ss");
    private Thread t;
    private JLabel labelRelogio;

    public Relogio(JLabel labelRelogio) throws Exception{
        this.labelRelogio = labelRelogio;
        Response res = ClientUtils.getData("pontoHoraAction", null);
        if (res.isError()){
            throw new Exception(res.getErrorMessage());
        }
        dataAtual = (Calendar)((VOResponse) res).getVo();
        t = new Thread(this);
        t.start();
    }

    public void run() {
        try {
            while (true) {
                dataAtual.add(Calendar.SECOND, 1);
                labelRelogio.setText(formato.format(dataAtual.getTime()));
                t.sleep(1000);
            }
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }
}
