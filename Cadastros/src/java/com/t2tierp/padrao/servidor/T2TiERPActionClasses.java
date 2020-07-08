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

import com.t2tierp.cadastros.servidor.AgenciaBancoDetalheAction;
import com.t2tierp.cadastros.servidor.AgenciaBancoGridAction;
import com.t2tierp.cadastros.servidor.AlmoxarifadoDetalheAction;
import com.t2tierp.cadastros.servidor.AlmoxarifadoGridAction;
import com.t2tierp.cadastros.servidor.AtividadeForCliDetalheAction;
import com.t2tierp.cadastros.servidor.AtividadeForCliGridAction;
import com.t2tierp.cadastros.servidor.BancoDetalheAction;
import com.t2tierp.cadastros.servidor.BancoGridAction;
import com.t2tierp.cadastros.servidor.CargoDetalheAction;
import com.t2tierp.cadastros.servidor.CargoGridAction;
import com.t2tierp.cadastros.servidor.CepDetalheAction;
import com.t2tierp.cadastros.servidor.CepGridAction;
import com.t2tierp.cadastros.servidor.CfopDetalheAction;
import com.t2tierp.cadastros.servidor.CfopGridAction;
import com.t2tierp.cadastros.servidor.ChequeDetalheAction;
import com.t2tierp.cadastros.servidor.ChequeGridAction;
import com.t2tierp.cadastros.servidor.ClienteDetalheAction;
import com.t2tierp.cadastros.servidor.ClienteGridAction;
import com.t2tierp.cadastros.servidor.ColaboradorDetalheAction;
import com.t2tierp.cadastros.servidor.ColaboradorGridAction;
import com.t2tierp.cadastros.servidor.ContaCaixaDetalheAction;
import com.t2tierp.cadastros.servidor.ContaCaixaGridAction;
import com.t2tierp.cadastros.servidor.ContadorDetalheAction;
import com.t2tierp.cadastros.servidor.ContadorGridAction;
import com.t2tierp.cadastros.servidor.ConvenioDetalheAction;
import com.t2tierp.cadastros.servidor.ConvenioGridAction;
import com.t2tierp.cadastros.servidor.EstadoCivilDetalheAction;
import com.t2tierp.cadastros.servidor.EstadoCivilGridAction;
import com.t2tierp.cadastros.servidor.FeriadosDetalheAction;
import com.t2tierp.cadastros.servidor.FeriadosGridAction;
import com.t2tierp.cadastros.servidor.FornecedorDetalheAction;
import com.t2tierp.cadastros.servidor.FornecedorGridAction;
import com.t2tierp.cadastros.servidor.MunicipioDetalheAction;
import com.t2tierp.cadastros.servidor.MunicipioGridAction;
import com.t2tierp.cadastros.servidor.NcmGridAction;
import com.t2tierp.cadastros.servidor.NivelFormacaoDetalheAction;
import com.t2tierp.cadastros.servidor.NivelFormacaoGridAction;
import com.t2tierp.cadastros.servidor.OperadoraCartaoDetalheAction;
import com.t2tierp.cadastros.servidor.OperadoraCartaoGridAction;
import com.t2tierp.cadastros.servidor.OperadoraPlanoSaudeDetalheAction;
import com.t2tierp.cadastros.servidor.OperadoraPlanoSaudeGridAction;
import com.t2tierp.cadastros.servidor.PaisDetalheAction;
import com.t2tierp.cadastros.servidor.PaisGridAction;
import com.t2tierp.cadastros.servidor.PessoaContatoGridAction;
import com.t2tierp.cadastros.servidor.PessoaDetalheAction;
import com.t2tierp.cadastros.servidor.PessoaEnderecoGridAction;
import com.t2tierp.cadastros.servidor.PessoaFisicaDetalheAction;
import com.t2tierp.cadastros.servidor.PessoaGridAction;
import com.t2tierp.cadastros.servidor.PessoaJuridicaDetalheAction;
import com.t2tierp.cadastros.servidor.ProdutoDetalheAction;
import com.t2tierp.cadastros.servidor.ProdutoGridAction;
import com.t2tierp.cadastros.servidor.ProdutoGrupoDetalheAction;
import com.t2tierp.cadastros.servidor.ProdutoGrupoGridAction;
import com.t2tierp.cadastros.servidor.ProdutoMarcaDetalheAction;
import com.t2tierp.cadastros.servidor.ProdutoMarcaGridAction;
import com.t2tierp.cadastros.servidor.ProdutoSubGrupoDetalheAction;
import com.t2tierp.cadastros.servidor.ProdutoSubGrupoGridAction;
import com.t2tierp.cadastros.servidor.SetorDetalheAction;
import com.t2tierp.cadastros.servidor.SetorGridAction;
import com.t2tierp.cadastros.servidor.SindicatoDetalheAction;
import com.t2tierp.cadastros.servidor.SindicatoGridAction;
import com.t2tierp.cadastros.servidor.SituacaoColaboradorDetalheAction;
import com.t2tierp.cadastros.servidor.SituacaoColaboradorGridAction;
import com.t2tierp.cadastros.servidor.SituacaoForCliDetalheAction;
import com.t2tierp.cadastros.servidor.SituacaoForCliGridAction;
import com.t2tierp.cadastros.servidor.TalonarioChequeDetalheAction;
import com.t2tierp.cadastros.servidor.TalonarioChequeGridAction;
import com.t2tierp.cadastros.servidor.TipoAdmissaoDetalheAction;
import com.t2tierp.cadastros.servidor.TipoAdmissaoGridAction;
import com.t2tierp.cadastros.servidor.TipoColaboradorDetalheAction;
import com.t2tierp.cadastros.servidor.TipoColaboradorGridAction;
import com.t2tierp.cadastros.servidor.TipoDesligamentoDetalheAction;
import com.t2tierp.cadastros.servidor.TipoDesligamentoGridAction;
import com.t2tierp.cadastros.servidor.TipoRelacionamentoDetalheAction;
import com.t2tierp.cadastros.servidor.TipoRelacionamentoGridAction;
import com.t2tierp.cadastros.servidor.TransportadoraDetalheAction;
import com.t2tierp.cadastros.servidor.TransportadoraGridAction;
import com.t2tierp.cadastros.servidor.UfDetalheAction;
import com.t2tierp.cadastros.servidor.UfGridAction;
import com.t2tierp.cadastros.servidor.UnidadeProdutoDetalheAction;
import com.t2tierp.cadastros.servidor.UnidadeProdutoGridAction;
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
        //PESSOA
        a = new EstadoCivilGridAction(); put(a.getRequestName(), a);
        a = new EstadoCivilDetalheAction(); put(a.getRequestName(), a);
        a = new PessoaGridAction(); put(a.getRequestName(), a);
        a = new PessoaDetalheAction(); put(a.getRequestName(), a);
        a = new PessoaContatoGridAction(); put(a.getRequestName(), a);
        a = new PessoaEnderecoGridAction(); put(a.getRequestName(), a);
        a = new PessoaFisicaDetalheAction(); put(a.getRequestName(), a);
        a = new PessoaJuridicaDetalheAction(); put(a.getRequestName(), a);

        //CLIENTE - FORNECEDOR - TRANSPORTADORA
        a = new AtividadeForCliGridAction(); put(a.getRequestName(), a);
        a = new AtividadeForCliDetalheAction(); put(a.getRequestName(), a);
        a = new SituacaoForCliGridAction(); put(a.getRequestName(), a);
        a = new SituacaoForCliDetalheAction(); put(a.getRequestName(), a);
        a = new ClienteGridAction(); put(a.getRequestName(), a);
        a = new ClienteDetalheAction(); put(a.getRequestName(), a);
        a = new FornecedorGridAction(); put(a.getRequestName(), a);
        a = new FornecedorDetalheAction(); put(a.getRequestName(), a);
        a = new TransportadoraGridAction(); put(a.getRequestName(), a);
        a = new TransportadoraDetalheAction(); put(a.getRequestName(), a);
        
        //Colaborador
        a = new TipoAdmissaoGridAction(); put(a.getRequestName(), a);
        a = new TipoAdmissaoDetalheAction(); put(a.getRequestName(), a);
        a = new TipoRelacionamentoGridAction(); put(a.getRequestName(), a);
        a = new TipoRelacionamentoDetalheAction(); put(a.getRequestName(), a);
        a = new SituacaoColaboradorGridAction(); put(a.getRequestName(), a);
        a = new SituacaoColaboradorDetalheAction(); put(a.getRequestName(), a);
        a = new TipoDesligamentoGridAction(); put(a.getRequestName(), a);
        a = new TipoDesligamentoDetalheAction(); put(a.getRequestName(), a);
        a = new TipoColaboradorGridAction(); put(a.getRequestName(), a);
        a = new TipoColaboradorDetalheAction(); put(a.getRequestName(), a);
        a = new CargoGridAction(); put(a.getRequestName(), a);
        a = new CargoDetalheAction(); put(a.getRequestName(), a);
        a = new NivelFormacaoGridAction(); put(a.getRequestName(), a);
        a = new NivelFormacaoDetalheAction(); put(a.getRequestName(), a);
        a = new ColaboradorGridAction(); put(a.getRequestName(), a);
        a = new ColaboradorDetalheAction(); put(a.getRequestName(), a);
        
        //CONTADOR - SINDICATO - CONVENIO
        a = new ContadorGridAction(); put(a.getRequestName(), a);
        a = new ContadorDetalheAction(); put(a.getRequestName(), a);
        a = new SindicatoGridAction(); put(a.getRequestName(), a);
        a = new SindicatoDetalheAction(); put(a.getRequestName(), a);
        a = new ConvenioGridAction(); put(a.getRequestName(), a);
        a = new ConvenioDetalheAction(); put(a.getRequestName(), a);

        //DIVERSOS
        a = new SetorGridAction(); put(a.getRequestName(), a);
        a = new SetorDetalheAction(); put(a.getRequestName(), a);
        a = new AlmoxarifadoGridAction(); put(a.getRequestName(), a);
        a = new AlmoxarifadoDetalheAction(); put(a.getRequestName(), a);
        a = new OperadoraPlanoSaudeGridAction(); put(a.getRequestName(), a);
        a = new OperadoraPlanoSaudeDetalheAction(); put(a.getRequestName(), a);
        a = new OperadoraCartaoGridAction(); put(a.getRequestName(), a);
        a = new OperadoraCartaoDetalheAction(); put(a.getRequestName(), a);
        
        //ENDERECO
        a = new PaisGridAction(); put(a.getRequestName(), a);
        a = new PaisDetalheAction(); put(a.getRequestName(), a);
        a = new UfGridAction(); put(a.getRequestName(), a);
        a = new UfDetalheAction(); put(a.getRequestName(), a);
        a = new MunicipioGridAction(); put(a.getRequestName(), a);
        a = new MunicipioDetalheAction(); put(a.getRequestName(), a);
        a = new CepGridAction(); put(a.getRequestName(), a);
        a = new CepDetalheAction(); put(a.getRequestName(), a);
        
        //Produto
        a = new ProdutoMarcaGridAction(); put(a.getRequestName(), a);
        a = new ProdutoMarcaDetalheAction(); put(a.getRequestName(), a);
        a = new NcmGridAction(); put(a.getRequestName(), a);
        a = new UnidadeProdutoGridAction(); put(a.getRequestName(), a);
        a = new UnidadeProdutoDetalheAction(); put(a.getRequestName(), a);
        a = new ProdutoGrupoGridAction(); put(a.getRequestName(), a);
        a = new ProdutoGrupoDetalheAction(); put(a.getRequestName(), a);
        a = new ProdutoSubGrupoGridAction(); put(a.getRequestName(), a);
        a = new ProdutoSubGrupoDetalheAction(); put(a.getRequestName(), a);
        a = new ProdutoGridAction(); put(a.getRequestName(), a);
        a = new ProdutoDetalheAction(); put(a.getRequestName(), a);
        
        //FINANCEIRO
        a = new BancoGridAction(); put(a.getRequestName(), a);
        a = new BancoDetalheAction(); put(a.getRequestName(), a);
        a = new AgenciaBancoGridAction(); put(a.getRequestName(), a);
        a = new AgenciaBancoDetalheAction(); put(a.getRequestName(), a);
        a = new ContaCaixaGridAction(); put(a.getRequestName(), a);
        a = new ContaCaixaDetalheAction(); put(a.getRequestName(), a);
        a = new TalonarioChequeGridAction(); put(a.getRequestName(), a);
        a = new TalonarioChequeDetalheAction(); put(a.getRequestName(), a);
        a = new ChequeGridAction(); put(a.getRequestName(), a);
        a = new ChequeDetalheAction(); put(a.getRequestName(), a);

        //TABELAS
        a = new CfopGridAction(); put(a.getRequestName(), a);
        a = new CfopDetalheAction(); put(a.getRequestName(), a);
        a = new FeriadosGridAction(); put(a.getRequestName(), a);
        a = new FeriadosDetalheAction(); put(a.getRequestName(), a);
        
        /*validacoes e importacoes*/
        a = new ImportaDadosAction(); put(a.getRequestName(), a);
        a = new ValidaDadosAction(); put(a.getRequestName(), a);
    }
}
