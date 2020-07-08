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

import com.t2tierp.contabilidade.cliente.AidfAimdfGridController;
import com.t2tierp.contabilidade.cliente.BalancoPatrimonialGridController;
import com.t2tierp.contabilidade.cliente.ContabilContaGridController;
import com.t2tierp.contabilidade.cliente.ContabilDreCabecalhoGridController;
import com.t2tierp.contabilidade.cliente.ContabilEncerramentoExeCabGridController;
import com.t2tierp.contabilidade.cliente.ContabilFechamentoGridController;
import com.t2tierp.contabilidade.cliente.ContabilHistoricoGridController;
import com.t2tierp.contabilidade.cliente.ContabilIndiceGridController;
import com.t2tierp.contabilidade.cliente.ContabilLancamentoCabecalhoGridController;
import com.t2tierp.contabilidade.cliente.ContabilLancamentoOrcadoGridController;
import com.t2tierp.contabilidade.cliente.ContabilLancamentoPadraoGridController;
import com.t2tierp.contabilidade.cliente.ContabilLivroGridController;
import com.t2tierp.contabilidade.cliente.ContabilLoteGridController;
import com.t2tierp.contabilidade.cliente.ContabilParametrosGridController;
import com.t2tierp.contabilidade.cliente.DfcGridController;
import com.t2tierp.contabilidade.cliente.FapGridController;
import com.t2tierp.contabilidade.cliente.LivroContabilGridController;
import com.t2tierp.contabilidade.cliente.PlanoContaGridController;
import com.t2tierp.contabilidade.cliente.PlanoContaRefSpedGridController;
import com.t2tierp.contabilidade.cliente.RegistroCartorioGridController;
import javax.swing.JOptionPane;
import org.openswing.swing.mdi.client.*;

public class Fachada implements ClientFacade {

    public Fachada() {
    }
    
    public void getRegistroCartorio() {
        new RegistroCartorioGridController();
    }

    public void getParametros(){
        new ContabilParametrosGridController();
    }

    public void getIndices(){
        new ContabilIndiceGridController();
    }

    public void getHistorico(){
        new ContabilHistoricoGridController();
    }

    public void getAidfAimdf(){
        new AidfAimdfGridController();
    }

    public void getFap(){
        new FapGridController();
    }

    public void getPlanoContas(){
        new PlanoContaGridController();
    }

    public void getPlanoContasRefSped(){
        new PlanoContaRefSpedGridController();
    }

    public void getContaContabil(){
        new ContabilContaGridController();
    }

    public void getFechamento(){
        new ContabilFechamentoGridController();
    }

    public void getLancamentoPadrao(){
        new ContabilLancamentoPadraoGridController();
    }

    public void getLancamentoLote(){
        new ContabilLoteGridController();
    }

    public void getLancamentoOrcado(){
        new ContabilLancamentoOrcadoGridController();
    }

    public void getLancamentoContabil(){
        new ContabilLancamentoCabecalhoGridController();
    }

    public void getDre(){
        new ContabilDreCabecalhoGridController();
    }

    public void getEncerramentoExercicio(){
        new ContabilEncerramentoExeCabGridController();
    }

    public void getLivrosTermos(){
        new ContabilLivroGridController();
    }

    public void getDfc(){
        new DfcGridController();
    }

    public void getBalancoPatrimonial(){
        new BalancoPatrimonialGridController();
    }

    public void getLivroContabil(){
        new LivroContabilGridController();
    }
    
    public void getFuncaoPadrao() {
        JOptionPane.showMessageDialog(null, "Acesso não autorizado!", "Informação do Sistema", JOptionPane.INFORMATION_MESSAGE);
    }
}