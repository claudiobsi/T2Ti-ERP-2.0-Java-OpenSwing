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

import com.t2tierp.ponto.servidor.PontoAbonoDetalheAction;
import com.t2tierp.ponto.servidor.PontoAbonoGridAction;
import com.t2tierp.ponto.servidor.PontoAbonoUtilizacaoGridAction;
import com.t2tierp.ponto.servidor.PontoBancoHorasDetalheAction;
import com.t2tierp.ponto.servidor.PontoBancoHorasGridAction;
import com.t2tierp.ponto.servidor.PontoBancoHorasUtilizacaoGridAction;
import com.t2tierp.ponto.servidor.PontoClassificacaoJornadaDetalheAction;
import com.t2tierp.ponto.servidor.PontoClassificacaoJornadaGridAction;
import com.t2tierp.ponto.servidor.PontoEscalaDetalheAction;
import com.t2tierp.ponto.servidor.PontoEscalaGridAction;
import com.t2tierp.ponto.servidor.PontoEspelhoDetalheAction;
import com.t2tierp.ponto.servidor.PontoEspelhoGridAction;
import com.t2tierp.ponto.servidor.PontoFechamentoJornadaGridAction;
import com.t2tierp.ponto.servidor.PontoGeracaoArquivoGridAction;
import com.t2tierp.ponto.servidor.PontoHoraAction;
import com.t2tierp.ponto.servidor.PontoHorarioDetalheAction;
import com.t2tierp.ponto.servidor.PontoHorarioGridAction;
import com.t2tierp.ponto.servidor.PontoImportaMarcacaoAction;
import com.t2tierp.ponto.servidor.PontoParametroDetalheAction;
import com.t2tierp.ponto.servidor.PontoParametroGridAction;
import com.t2tierp.ponto.servidor.PontoProcessaFechamentoJornadaAction;
import com.t2tierp.ponto.servidor.PontoRegistraMarcacaoAction;
import com.t2tierp.ponto.servidor.PontoRelogioDetalheAction;
import com.t2tierp.ponto.servidor.PontoRelogioGridAction;
import com.t2tierp.ponto.servidor.PontoTurmaGridAction;
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

        /*actions do módulo */
        a = new PontoParametroGridAction(); put(a.getRequestName(), a);
        a = new PontoParametroDetalheAction(); put(a.getRequestName(), a);
        a = new PontoHorarioGridAction(); put(a.getRequestName(), a);
        a = new PontoHorarioDetalheAction(); put(a.getRequestName(), a);
        a = new PontoEscalaGridAction(); put(a.getRequestName(), a);
        a = new PontoEscalaDetalheAction(); put(a.getRequestName(), a);
        a = new PontoTurmaGridAction(); put(a.getRequestName(), a);
        a = new PontoClassificacaoJornadaGridAction(); put(a.getRequestName(), a);
        a = new PontoClassificacaoJornadaDetalheAction(); put(a.getRequestName(), a);
        a = new PontoAbonoGridAction(); put(a.getRequestName(), a);
        a = new PontoAbonoDetalheAction(); put(a.getRequestName(), a);
        a = new PontoAbonoUtilizacaoGridAction(); put(a.getRequestName(), a);
        a = new PontoRelogioGridAction(); put(a.getRequestName(), a);
        a = new PontoRelogioDetalheAction(); put(a.getRequestName(), a);
        a = new PontoBancoHorasGridAction(); put(a.getRequestName(), a);
        a = new PontoBancoHorasDetalheAction(); put(a.getRequestName(), a);
        a = new PontoBancoHorasUtilizacaoGridAction(); put(a.getRequestName(), a);
        a = new PontoImportaMarcacaoAction(); put(a.getRequestName(), a);
        a = new PontoProcessaFechamentoJornadaAction(); put(a.getRequestName(), a);
        a = new PontoGeracaoArquivoGridAction(); put(a.getRequestName(), a);
        a = new PontoFechamentoJornadaGridAction(); put(a.getRequestName(), a);
        a = new PontoEspelhoGridAction(); put(a.getRequestName(), a);
        a = new PontoEspelhoDetalheAction(); put(a.getRequestName(), a);
        a = new PontoHoraAction(); put(a.getRequestName(), a);
        a = new PontoRegistraMarcacaoAction(); put(a.getRequestName(), a);
        
        /*validacoes e importacoes*/
        a = new ImportaDadosAction(); put(a.getRequestName(), a);
        a = new ValidaDadosAction(); put(a.getRequestName(), a);
        
    }
}
