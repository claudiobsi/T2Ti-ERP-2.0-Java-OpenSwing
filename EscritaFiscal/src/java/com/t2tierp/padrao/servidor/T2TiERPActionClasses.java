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

import com.t2tierp.contabilidade.servidor.RegistroCartorioDetalheAction;
import com.t2tierp.contabilidade.servidor.RegistroCartorioGridAction;
import com.t2tierp.controleestoque.servidor.EntradaNotaDetalheAction;
import com.t2tierp.controleestoque.servidor.EntradaNotaGridAction;
import com.t2tierp.controleestoque.servidor.NfeCteReferenciadoGridAction;
import com.t2tierp.controleestoque.servidor.NfeCupomFiscalReferenciadoGridAction;
import com.t2tierp.controleestoque.servidor.NfeDetalheGridAction;
import com.t2tierp.controleestoque.servidor.NfeDuplicataGridAction;
import com.t2tierp.controleestoque.servidor.NfeEmitenteDetalheAction;
import com.t2tierp.controleestoque.servidor.NfeFaturaDetalheAction;
import com.t2tierp.controleestoque.servidor.NfeLocalEntregaDetalheAction;
import com.t2tierp.controleestoque.servidor.NfeLocalRetiradaDetalheAction;
import com.t2tierp.controleestoque.servidor.NfeNfReferenciadaGridAction;
import com.t2tierp.controleestoque.servidor.NfeProdRuralReferenciadaGridAction;
import com.t2tierp.controleestoque.servidor.NfeReferenciadaGridAction;
import com.t2tierp.controleestoque.servidor.NfeTransporteDetalheAction;
import com.t2tierp.controleestoque.servidor.NfeTransporteReboqueGridAction;
import com.t2tierp.controleestoque.servidor.NfeTransporteVolumeGridAction;
import com.t2tierp.controleestoque.servidor.NfeTransporteVolumeLacreGridAction;
import com.t2tierp.escritafiscal.servidor.FiscalApuracaoIcmsAction;
import com.t2tierp.escritafiscal.servidor.FiscalLivroDetalheAction;
import com.t2tierp.escritafiscal.servidor.FiscalLivroGridAction;
import com.t2tierp.escritafiscal.servidor.FiscalParametrosDetalheAction;
import com.t2tierp.escritafiscal.servidor.FiscalParametrosGridAction;
import com.t2tierp.escritafiscal.servidor.FiscalTermoGridAction;
import com.t2tierp.escritafiscal.servidor.SimplesNacionalCabecalhoDetalheAction;
import com.t2tierp.escritafiscal.servidor.SimplesNacionalCabecalhoGridAction;
import com.t2tierp.escritafiscal.servidor.SimplesNacionalDetalheGridAction;
import com.t2tierp.vendas.servidor.NotaFiscalTipoDetalheAction;
import com.t2tierp.vendas.servidor.NotaFiscalTipoGridAction;
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
        a = new FiscalParametrosGridAction(); put(a.getRequestName(), a);
        a = new FiscalParametrosDetalheAction(); put(a.getRequestName(), a);
        a = new SimplesNacionalCabecalhoGridAction(); put(a.getRequestName(), a);
        a = new SimplesNacionalCabecalhoDetalheAction(); put(a.getRequestName(), a);
        a = new SimplesNacionalDetalheGridAction(); put(a.getRequestName(), a);
        a = new FiscalLivroGridAction(); put(a.getRequestName(), a);
        a = new FiscalLivroDetalheAction(); put(a.getRequestName(), a);
        a = new FiscalTermoGridAction(); put(a.getRequestName(), a);
        a = new FiscalApuracaoIcmsAction(); put(a.getRequestName(), a);

        /*actions do módulo contabilidade*/
        a = new RegistroCartorioGridAction(); put(a.getRequestName(), a);
        a = new RegistroCartorioDetalheAction(); put(a.getRequestName(), a);

        /*actions do módulo controle de estoque*/
        a = new EntradaNotaGridAction(); put(a.getRequestName(), a);
        a = new EntradaNotaDetalheAction(); put(a.getRequestName(), a);
        a = new NfeEmitenteDetalheAction(); put(a.getRequestName(), a);
        a = new NfeDetalheGridAction(); put(a.getRequestName(), a);
        a = new NfeReferenciadaGridAction(); put(a.getRequestName(), a);
        a = new NfeNfReferenciadaGridAction(); put(a.getRequestName(), a);
        a = new NfeCteReferenciadoGridAction(); put(a.getRequestName(), a);
        a = new NfeProdRuralReferenciadaGridAction(); put(a.getRequestName(), a);
        a = new NfeCupomFiscalReferenciadoGridAction(); put(a.getRequestName(), a);
        a = new NfeLocalEntregaDetalheAction(); put(a.getRequestName(), a);
        a = new NfeLocalRetiradaDetalheAction(); put(a.getRequestName(), a);
        a = new NfeTransporteDetalheAction(); put(a.getRequestName(), a);
        a = new NfeTransporteReboqueGridAction(); put(a.getRequestName(), a);
        a = new NfeTransporteVolumeGridAction(); put(a.getRequestName(), a);
        a = new NfeTransporteVolumeLacreGridAction(); put(a.getRequestName(), a);
        a = new NfeFaturaDetalheAction(); put(a.getRequestName(), a);
        a = new NfeDuplicataGridAction(); put(a.getRequestName(), a);
        
        /*actions do módulo vendas*/
        a = new NotaFiscalTipoGridAction(); put(a.getRequestName(), a);
        a = new NotaFiscalTipoDetalheAction(); put(a.getRequestName(), a);
        
        /*validacoes e importacoes*/
        a = new ImportaDadosAction(); put(a.getRequestName(), a);
        a = new ValidaDadosAction(); put(a.getRequestName(), a);
        
    }
}
