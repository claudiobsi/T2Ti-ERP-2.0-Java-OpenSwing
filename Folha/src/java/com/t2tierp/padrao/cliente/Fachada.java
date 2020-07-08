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

import com.t2tierp.folhapagamento.cliente.FeriasPeriodoAquisitivoGridController;
import com.t2tierp.folhapagamento.cliente.FolhaAfastamentoGridController;
import com.t2tierp.folhapagamento.cliente.FolhaEventoGridController;
import com.t2tierp.folhapagamento.cliente.FolhaFechamentoGridController;
import com.t2tierp.folhapagamento.cliente.FolhaFeriasColetivasGridController;
import com.t2tierp.folhapagamento.cliente.FolhaHistoricoSalarialGridController;
import com.t2tierp.folhapagamento.cliente.FolhaInssGridController;
import com.t2tierp.folhapagamento.cliente.FolhaInssServicoGridController;
import com.t2tierp.folhapagamento.cliente.FolhaLancamentoGridController;
import com.t2tierp.folhapagamento.cliente.FolhaParametrosGridController;
import com.t2tierp.folhapagamento.cliente.FolhaPlanoSaudeGridController;
import com.t2tierp.folhapagamento.cliente.FolhaPppGridController;
import com.t2tierp.folhapagamento.cliente.FolhaRescisaoGridController;
import com.t2tierp.folhapagamento.cliente.FolhaTipoAfastamentoGridController;
import com.t2tierp.folhapagamento.cliente.FolhaValeTransporteGridController;
import com.t2tierp.folhapagamento.cliente.GuiasAcumuladasGridController;
import com.t2tierp.folhapagamento.cliente.InformativosGuiasController;
import javax.swing.JOptionPane;
import org.openswing.swing.mdi.client.*;

public class Fachada implements ClientFacade {

    public Fachada() {
    }
    
    public void getFolhaParametro(){
        new FolhaParametrosGridController();
    }

    public void getGuiasAcumuladas(){
        new GuiasAcumuladasGridController();
    }

    public void getPlanoSaude(){
        new FolhaPlanoSaudeGridController();
    }

    public void getEvento(){
        new FolhaEventoGridController();
    }

    public void getTipoAfastamento(){
        new FolhaTipoAfastamentoGridController();
    }

    public void getAfastamento(){
        new FolhaAfastamentoGridController();
    }

    public void getFeriasColetivas(){
        new FolhaFeriasColetivasGridController();
    }

    public void getFeriasPeriodoAquisitivo(){
        new FeriasPeriodoAquisitivoGridController();
    }

    public void getFechamento(){
        new FolhaFechamentoGridController();
    }

    public void getLancamento(){
        new FolhaLancamentoGridController();
    }

    public void getAlteracaoSalarial(){
        new FolhaHistoricoSalarialGridController();
    }

    public void getValeTransporte(){
        new FolhaValeTransporteGridController();
    }

    public void getPpp(){
        new FolhaPppGridController();
    }

    public void getRescisao(){
        new FolhaRescisaoGridController();
    }

    public void getInssServico(){
        new FolhaInssServicoGridController();
    }

    public void getInssRetencao(){
        new FolhaInssGridController();
    }

    public void getInformativosGuias(){
        new InformativosGuiasController();
    }
    
    public void getFuncaoPadrao() {
        JOptionPane.showMessageDialog(null, "Acesso não autorizado!", "Informação do Sistema", JOptionPane.INFORMATION_MESSAGE);
    }
}