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
package com.t2tierp.padrao.cliente;

import com.t2tierp.ponto.cliente.PontoAbonoGridController;
import com.t2tierp.ponto.cliente.PontoBancoHorasGridController;
import com.t2tierp.ponto.cliente.PontoClassificacaoJornadaGridController;
import com.t2tierp.ponto.cliente.PontoEscalaGridController;
import com.t2tierp.ponto.cliente.PontoEspelhoGridController;
import com.t2tierp.ponto.cliente.PontoGeracaoArquivoGridController;
import com.t2tierp.ponto.cliente.PontoHorarioGridController;
import com.t2tierp.ponto.cliente.PontoImportaMarcacaoController;
import com.t2tierp.ponto.cliente.PontoParametroGridController;
import com.t2tierp.ponto.cliente.PontoRegistraMarcacaoController;
import com.t2tierp.ponto.cliente.PontoRelogioGridController;
import javax.swing.JOptionPane;
import org.openswing.swing.mdi.client.*;

public class Fachada implements ClientFacade {

    public Fachada() {
    }
    
    public void getPontoParametro() {
        new PontoParametroGridController();
    }

    public void getPontoHorario() {
        new PontoHorarioGridController();
    }

    public void getPontoEscala() {
        new PontoEscalaGridController();
    }

    public void getPontoClassificacaoJornada() {
        new PontoClassificacaoJornadaGridController();
    }

    public void getPontoAbono() {
        new PontoAbonoGridController();
    }

    public void getPontoRelogio() {
        new PontoRelogioGridController();
    }

    public void getPontoBancoHoras() {
        new PontoBancoHorasGridController();
    }

    public void getPontoImportaMarcacao() {
        new PontoImportaMarcacaoController();
    }

    public void getPontoGeracaoArquivo() {
        new PontoGeracaoArquivoGridController();
    }

    public void getPontoEspelho() {
        new PontoEspelhoGridController();
    }
    
    public void getPontoRegistraMarcacao() {
        new PontoRegistraMarcacaoController();
    }
    
    public void getFuncaoPadrao() {
        JOptionPane.showMessageDialog(null, "Acesso não autorizado!", "Informação do Sistema", JOptionPane.INFORMATION_MESSAGE);
    }
}