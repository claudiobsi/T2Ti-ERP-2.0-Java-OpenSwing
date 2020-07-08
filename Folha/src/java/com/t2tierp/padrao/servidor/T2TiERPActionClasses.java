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
package com.t2tierp.padrao.servidor;

import com.t2tierp.cadastros.servidor.ColaboradorGridAction;
import com.t2tierp.folhapagamento.servidor.FeriasPeriodoAquisitivoDetalheAction;
import com.t2tierp.folhapagamento.servidor.FeriasPeriodoAquisitivoGridAction;
import com.t2tierp.folhapagamento.servidor.FolhaAfastamentoDetalheAction;
import com.t2tierp.folhapagamento.servidor.FolhaAfastamentoGridAction;
import com.t2tierp.folhapagamento.servidor.FolhaEventoDetalheAction;
import com.t2tierp.folhapagamento.servidor.FolhaEventoGridAction;
import com.t2tierp.folhapagamento.servidor.FolhaFechamentoDetalheAction;
import com.t2tierp.folhapagamento.servidor.FolhaFechamentoGridAction;
import com.t2tierp.folhapagamento.servidor.FolhaFeriasColetivasDetalheAction;
import com.t2tierp.folhapagamento.servidor.FolhaFeriasColetivasGridAction;
import com.t2tierp.folhapagamento.servidor.FolhaHistoricoSalarialDetalheAction;
import com.t2tierp.folhapagamento.servidor.FolhaHistoricoSalarialGridAction;
import com.t2tierp.folhapagamento.servidor.FolhaInssDetalheAction;
import com.t2tierp.folhapagamento.servidor.FolhaInssGridAction;
import com.t2tierp.folhapagamento.servidor.FolhaInssRetencaoGridAction;
import com.t2tierp.folhapagamento.servidor.FolhaInssServicoDetalheAction;
import com.t2tierp.folhapagamento.servidor.FolhaInssServicoGridAction;
import com.t2tierp.folhapagamento.servidor.FolhaLancamentoDetalheAction;
import com.t2tierp.folhapagamento.servidor.FolhaLancamentoDetalheGridAction;
import com.t2tierp.folhapagamento.servidor.FolhaLancamentoGridAction;
import com.t2tierp.folhapagamento.servidor.FolhaParametrosDetalheAction;
import com.t2tierp.folhapagamento.servidor.FolhaParametrosGridAction;
import com.t2tierp.folhapagamento.servidor.FolhaPlanoSaudeDetalheAction;
import com.t2tierp.folhapagamento.servidor.FolhaPlanoSaudeGridAction;
import com.t2tierp.folhapagamento.servidor.FolhaPppAtividadeGridAction;
import com.t2tierp.folhapagamento.servidor.FolhaPppCatGridAction;
import com.t2tierp.folhapagamento.servidor.FolhaPppDetalheAction;
import com.t2tierp.folhapagamento.servidor.FolhaPppExameMedicoGridAction;
import com.t2tierp.folhapagamento.servidor.FolhaPppFatorRiscoGridAction;
import com.t2tierp.folhapagamento.servidor.FolhaPppGridAction;
import com.t2tierp.folhapagamento.servidor.FolhaRescisaoDetalheAction;
import com.t2tierp.folhapagamento.servidor.FolhaRescisaoGridAction;
import com.t2tierp.folhapagamento.servidor.FolhaTipoAfastamentoDetalheAction;
import com.t2tierp.folhapagamento.servidor.FolhaTipoAfastamentoGridAction;
import com.t2tierp.folhapagamento.servidor.FolhaValeTransporteDetalheAction;
import com.t2tierp.folhapagamento.servidor.FolhaValeTransporteGridAction;
import com.t2tierp.folhapagamento.servidor.GuiasAcumuladasDetalheAction;
import com.t2tierp.folhapagamento.servidor.GuiasAcumuladasGridAction;
import com.t2tierp.folhapagamento.servidor.InformativosGuiasAction;
import org.openswing.swing.server.ActionsCollection;
import org.openswing.swing.server.Action;

public class T2TiERPActionClasses extends ActionsCollection {

    public T2TiERPActionClasses() {
        Action a = null;

        //infra
        a = new T2TiERPButtonAuthorizationsAction(); put(a.getRequestName(), a);
        a = new T2TiERPFunctionAuthorizationsAction(); put(a.getRequestName(), a);
        a = new UserLoginAction(); put(a.getRequestName(), a);
        a = new T2TiERPContainerAction(); put(a.getRequestName(), a);

        /*actions do módulo Cadastros*/
        a = new ColaboradorGridAction(); put(a.getRequestName(), a);
        
        /*actions do módulo */
        a = new FolhaParametrosGridAction(); put(a.getRequestName(), a);
        a = new FolhaParametrosDetalheAction(); put(a.getRequestName(), a);
        a = new GuiasAcumuladasGridAction(); put(a.getRequestName(), a);
        a = new GuiasAcumuladasDetalheAction(); put(a.getRequestName(), a);
        a = new FolhaPlanoSaudeGridAction(); put(a.getRequestName(), a);
        a = new FolhaPlanoSaudeDetalheAction(); put(a.getRequestName(), a);
        a = new FolhaEventoGridAction(); put(a.getRequestName(), a);
        a = new FolhaEventoDetalheAction(); put(a.getRequestName(), a);
        a = new FolhaTipoAfastamentoGridAction(); put(a.getRequestName(), a);
        a = new FolhaTipoAfastamentoDetalheAction(); put(a.getRequestName(), a);
        a = new FolhaAfastamentoGridAction(); put(a.getRequestName(), a);
        a = new FolhaAfastamentoDetalheAction(); put(a.getRequestName(), a);
        a = new FolhaFeriasColetivasGridAction(); put(a.getRequestName(), a);
        a = new FolhaFeriasColetivasDetalheAction(); put(a.getRequestName(), a);
        a = new FeriasPeriodoAquisitivoGridAction(); put(a.getRequestName(), a);
        a = new FeriasPeriodoAquisitivoDetalheAction(); put(a.getRequestName(), a);
        a = new FolhaFechamentoGridAction(); put(a.getRequestName(), a);
        a = new FolhaFechamentoDetalheAction(); put(a.getRequestName(), a);
        a = new FolhaLancamentoGridAction(); put(a.getRequestName(), a);
        a = new FolhaLancamentoDetalheAction(); put(a.getRequestName(), a);
        a = new FolhaLancamentoDetalheGridAction(); put(a.getRequestName(), a);
        a = new FolhaHistoricoSalarialGridAction(); put(a.getRequestName(), a);
        a = new FolhaHistoricoSalarialDetalheAction(); put(a.getRequestName(), a);
        a = new FolhaValeTransporteGridAction(); put(a.getRequestName(), a);
        a = new FolhaValeTransporteDetalheAction(); put(a.getRequestName(), a);
        a = new FolhaPppGridAction(); put(a.getRequestName(), a);
        a = new FolhaPppDetalheAction(); put(a.getRequestName(), a);
        a = new FolhaPppCatGridAction(); put(a.getRequestName(), a);
        a = new FolhaPppAtividadeGridAction(); put(a.getRequestName(), a);
        a = new FolhaPppFatorRiscoGridAction(); put(a.getRequestName(), a);
        a = new FolhaPppExameMedicoGridAction(); put(a.getRequestName(), a);
        a = new FolhaRescisaoGridAction(); put(a.getRequestName(), a);
        a = new FolhaRescisaoDetalheAction(); put(a.getRequestName(), a);
        a = new FolhaInssServicoGridAction(); put(a.getRequestName(), a);
        a = new FolhaInssServicoDetalheAction(); put(a.getRequestName(), a);
        a = new FolhaInssGridAction(); put(a.getRequestName(), a);
        a = new FolhaInssDetalheAction(); put(a.getRequestName(), a);
        a = new FolhaInssRetencaoGridAction(); put(a.getRequestName(), a);
        a = new InformativosGuiasAction(); put(a.getRequestName(), a);
        
        /*validacoes e importacoes*/
        a = new ImportaDadosAction(); put(a.getRequestName(), a);
        a = new ValidaDadosAction(); put(a.getRequestName(), a);
        
    }
}
