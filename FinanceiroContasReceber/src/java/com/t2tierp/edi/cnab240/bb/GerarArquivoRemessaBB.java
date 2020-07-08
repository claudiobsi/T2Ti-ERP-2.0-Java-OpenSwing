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
package com.t2tierp.edi.cnab240.bb;

import com.t2tierp.cadastros.java.EmpresaVO;
import com.t2tierp.financeiro.java.FinConfiguracaoBoletoVO;
import com.t2tierp.financeiro.java.FinParcelaReceberVO;
import java.io.File;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import org.apache.commons.io.FileUtils;
import org.jrimum.texgit.FlatFile;
import org.jrimum.texgit.Record;
import org.jrimum.texgit.Texgit;

public class GerarArquivoRemessaBB {

    public void gerarArquivoRemessa(List<FinParcelaReceberVO> listaParcelaReceber, EmpresaVO empresa, FinConfiguracaoBoletoVO configuracaoBoleto, File file) throws Exception {
        if (configuracaoBoleto.getContaCaixa().getAgenciaBanco() == null) {
            throw new Exception("Conta caixa não está vinculada a uma agência bancária.\nGeração do arquivo não permitida.");
        }

        //buscar o xml com o layout
        File layout = File.createTempFile("layout", ".xml");
        layout.deleteOnExit();
        InputStream in = this.getClass().getResourceAsStream("/com/t2tierp/edi/cnab240/bb/layout_cnab240_bb_remessa.xml");

        FileUtils.copyInputStreamToFile(in, layout);

        //cria um objeto FlatFile
        FlatFile<Record> ff = Texgit.createFlatFile(layout);

        //header do arquivo
        HeaderArquivo headerArquivo = new HeaderArquivo(ff.createRecord("HeaderArquivo"));
        headerArquivo.setTipoInscricao(ConstantesBB.TIPO_INSCRICAO_CNPJ);
        headerArquivo.setNumeroInscricao(Long.valueOf(empresa.getCnpj()));
        headerArquivo.setCodigoConvenio(configuracaoBoleto.getCodigoConvenio());
        headerArquivo.setAgenciaMantenedora(Integer.valueOf(configuracaoBoleto.getContaCaixa().getAgenciaBanco().getCodigo()));
        headerArquivo.setDigitoVerificadorAgencia(configuracaoBoleto.getContaCaixa().getAgenciaBanco().getDigito());
        headerArquivo.setNumeroContaCorrente(Integer.valueOf(configuracaoBoleto.getContaCaixa().getCodigo()));
        headerArquivo.setDigitoVerificadorContaCorrente(configuracaoBoleto.getContaCaixa().getDigito());
        headerArquivo.setDigitoVerificadorAgenciaConta(" ");
        headerArquivo.setNomeEmpresa(empresa.getRazaoSocial());
        headerArquivo.setNomeBanco("BANCO DO BRASIL S.A.");
        headerArquivo.setCodigoRemessaRetorno(ConstantesBB.REMESSA);
        headerArquivo.setDataGeracaoArquivo(new Date());
        headerArquivo.setHoraGeracaoArquivo(0);
        headerArquivo.setNumeroSequencialArquivo(0);
        headerArquivo.setNumeroVersaoArquivo(84);
        headerArquivo.setDensidadeGravacaoArquivo(0);
        headerArquivo.setUsoReservadoBanco(" ");
        headerArquivo.setUsoReservadoEmpresa(" ");

        ff.addRecord(headerArquivo.getRecord());

        //header do lote
        HeaderLote headerLote = new HeaderLote(ff.createRecord("HeaderLote"));
        headerLote.setLoteServico(1);
        headerLote.setTipoOperacao("R");
        headerLote.setTipoInscricao(ConstantesBB.TIPO_INSCRICAO_CNPJ);
        headerLote.setNumeroInscricao(Long.valueOf(empresa.getCnpj()));
        /* 999999999 - nr convenio
         * CC - nr da carteira
         * VVV - nr da variacao da carteira
         */
        headerLote.setCodigoConvenio(configuracaoBoleto.getCodigoConvenio() + "0014" + configuracaoBoleto.getCarteira());
        headerLote.setAgenciaMantenedora(Integer.valueOf(configuracaoBoleto.getContaCaixa().getAgenciaBanco().getCodigo()));
        headerLote.setDigitoVerificadorAgencia(configuracaoBoleto.getContaCaixa().getAgenciaBanco().getDigito());
        headerLote.setNumeroContaCorrente(Integer.valueOf(configuracaoBoleto.getContaCaixa().getCodigo()));
        headerLote.setDigitoVerificadorContaCorrente(configuracaoBoleto.getContaCaixa().getDigito());
        headerLote.setDigitoVerificadorAgenciaConta(" ");
        headerLote.setNomeEmpresa(empresa.getRazaoSocial());
        headerLote.setMensagem1(" ");
        headerLote.setMensagem2(" ");
        headerLote.setNumeroRemessaRetorno(0);
        headerLote.setDataGravacao(new Date());
        headerLote.setDataCredito(" ");

        ff.addRecord(headerLote.getRecord());

        //segmentos P e Q
        DetalheLoteSegmentoP segmentoP;
        DetalheLoteSegmentoQ segmentoQ;
        int sequenciaLote = 0;
        FinParcelaReceberVO parcelaReceber;
        for (int i = 0; i < listaParcelaReceber.size(); i++) {
            parcelaReceber = listaParcelaReceber.get(i);
            segmentoP = new DetalheLoteSegmentoP(ff.createRecord("DetalheLoteSegmentoP"));
            segmentoQ = new DetalheLoteSegmentoQ(ff.createRecord("DetalheLoteSegmentoQ"));

            segmentoP.setLoteServico(1);//deve ser igual ao número do lote
            segmentoP.setNumeroSequencialRegistro(++sequenciaLote);
            segmentoP.setCodigoMovimentoRemessa(ConstantesBB.MOVIMENTO_REMESSA_ENTRADA);
            segmentoP.setAgenciaMantenedora(Integer.valueOf(configuracaoBoleto.getContaCaixa().getAgenciaBanco().getCodigo()));
            segmentoP.setDigitoVerificadorAgencia(configuracaoBoleto.getContaCaixa().getAgenciaBanco().getDigito());
            segmentoP.setNumeroContaCorrente(Integer.valueOf(configuracaoBoleto.getContaCaixa().getCodigo()));
            segmentoP.setDigitoVerificadorContaCorrente(configuracaoBoleto.getContaCaixa().getDigito());
            segmentoP.setDigitoVerificadorAgenciaConta(" ");
            segmentoP.setIdentificadorTitulo(parcelaReceber.getBoletoNossoNumero());//nosso número
            segmentoP.setCodigoCarteira(1);
            segmentoP.setFormaCadastroTitulo(0);
            segmentoP.setTipoDocumento(" ");
            segmentoP.setIdentificadorEmissaoBloqueto(0);
            segmentoP.setIdentificacaoDistribuicao(" ");
            segmentoP.setNumeroDocumentoCobranca(parcelaReceber.getBoletoNossoNumero().substring(0, 15));
            segmentoP.setDataVencimentoTitulo(parcelaReceber.getDataVencimento());
            segmentoP.setValorNominalTitulo(parcelaReceber.getValor().multiply(BigDecimal.valueOf(100)).longValue());
            segmentoP.setAgenciaEncarregadaCobranca(0);
            segmentoP.setDigitoVerificadorAgenciaCobranca(" ");
            segmentoP.setEspecieTitulo(2);
            if (configuracaoBoleto.getAceite().equals("S")) {
                segmentoP.setIdentificadorTituloAceito("A");
            } else {
                segmentoP.setIdentificadorTituloAceito("N");
            }
            segmentoP.setDataEmissaoTitulo(parcelaReceber.getDataEmissao());
            segmentoP.setCodigoJurosMora(3);
            segmentoP.setDataJurosMora(0);
            if (parcelaReceber.getTaxaJuro() != null) {
                segmentoP.setJurosMoraDia(parcelaReceber.getTaxaJuro().multiply(BigDecimal.valueOf(100)).longValue());
            } else {
                segmentoP.setJurosMoraDia(0l);
            }
            if (parcelaReceber.getTaxaDesconto() != null) {
                segmentoP.setCodigoDesconto1(1);
                segmentoP.setDataDesconto1(parcelaReceber.getDescontoAte());
                segmentoP.setValorPercentualConcedido(parcelaReceber.getTaxaDesconto().multiply(BigDecimal.valueOf(100)).longValue());
            } else {
                segmentoP.setCodigoDesconto1(0);
                segmentoP.setDataDesconto1(new Date());
                segmentoP.setValorPercentualConcedido(0l);
            }
            segmentoP.setValorIOFRecolhido(0l);
            segmentoP.setValorAbatimento(0l);
            segmentoP.setIdentificadorTituloEmpresa(" ");
            segmentoP.setCodigoProtesto(3);
            segmentoP.setNumeroDiasProtesto(0);
            segmentoP.setCodigoBaixaDevolucao(0);
            segmentoP.setNumeroDiasBaixaDevolucao(0);
            segmentoP.setCodigoMoeda(9);
            segmentoP.setNumeroContrato(0);

            segmentoQ.setLoteServico(1);//deve ser igual ao número do lote
            segmentoQ.setTipoRegistro(3);
            segmentoQ.setNumeroSequencialRegistro(++sequenciaLote);
            segmentoQ.setCodigoMovimentoRemessa(ConstantesBB.MOVIMENTO_REMESSA_ENTRADA);
            if (parcelaReceber.getFinLancamentoReceber().getCliente().getPessoa().getTipo().equals("F")) {
                segmentoQ.setTipoInscricaoSacado(1);//1 - PF | 2 - PJ
                segmentoQ.setNumeroInscricaoSacado(Long.valueOf(parcelaReceber.getFinLancamentoReceber().getCliente().getPessoa().getPessoaFisica().getCpf()));
            } else {
                segmentoQ.setTipoInscricaoSacado(2);//1 - PF | 2 - PJ
                segmentoQ.setNumeroInscricaoSacado(Long.valueOf(parcelaReceber.getFinLancamentoReceber().getCliente().getPessoa().getPessoaJuridica().getCnpj()));
            }
            segmentoQ.setNome(parcelaReceber.getFinLancamentoReceber().getCliente().getPessoa().getNome());
            segmentoQ.setEndereco(parcelaReceber.getFinLancamentoReceber().getCliente().getPessoa().getListaEndereco().get(0).getLogradouro() + ", " + parcelaReceber.getFinLancamentoReceber().getCliente().getPessoa().getListaEndereco().get(0).getNumero());
            segmentoQ.setBairro(parcelaReceber.getFinLancamentoReceber().getCliente().getPessoa().getListaEndereco().get(0).getBairro());
            segmentoQ.setCEP(Integer.valueOf(parcelaReceber.getFinLancamentoReceber().getCliente().getPessoa().getListaEndereco().get(0).getCep().substring(0, 5)));
            segmentoQ.setSufixoCEP(Integer.valueOf(parcelaReceber.getFinLancamentoReceber().getCliente().getPessoa().getListaEndereco().get(0).getCep().substring(5, 8)));
            segmentoQ.setCidade(parcelaReceber.getFinLancamentoReceber().getCliente().getPessoa().getListaEndereco().get(0).getCidade());//71060080
            segmentoQ.setUF(parcelaReceber.getFinLancamentoReceber().getCliente().getPessoa().getListaEndereco().get(0).getUf());
            segmentoQ.setTipoInscricaoSacadoAvalista(0);
            segmentoQ.setNumeroInscricaoSacadoAvalista(0l);
            segmentoQ.setNomeSacadorAvalista(" ");
            segmentoQ.setCodigoBancoCorrespondenteCompe(0);
            segmentoQ.setNossoNumeroBancoCorrespondente(" ");

            ff.addRecord(segmentoP.getRecord());
            segmentoP.getRecord().addInnerRecord(segmentoQ.getRecord());
        }

        //trailer de lote
        TrailerLote trailerLote = new TrailerLote(ff.createRecord("TrailerLote"));
        trailerLote.setLoteServico(1);
        trailerLote.setQuantidadeRegistros(sequenciaLote + 2);

        ff.addRecord(trailerLote.getRecord());

        //trailer de arquivo
        TrailerArquivo trailerArquivo = new TrailerArquivo(ff.createRecord("TrailerArquivo"));
        trailerArquivo.setLoteServico(9999);
        trailerArquivo.setQuantidadeLotesArquivo(1);
        trailerArquivo.setQuantidadeRegistrosArquivo(sequenciaLote + 4);
        trailerArquivo.setQuantidadeContasConciliacao(0);

        ff.addRecord(trailerArquivo.getRecord());

        FileUtils.writeLines(file, ff.write(), System.getProperty("line.separator"));
    }
}
