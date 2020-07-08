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

import com.t2tierp.cadastros.java.ColaboradorVO;
import com.t2tierp.cadastros.java.EmpresaVO;
import java.io.File;
import java.io.InputStream;
import java.util.Date;
import java.util.List;
import org.apache.commons.io.FileUtils;
import org.jrimum.texgit.FlatFile;
import org.jrimum.texgit.Record;
import org.jrimum.texgit.Texgit;

public class GerarArquivoRemessaPagamentoBB {

    public void gerarArquivoRemessa(List<ColaboradorVO> listaColaborador, EmpresaVO empresa, File file) throws Exception {
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
        headerArquivo.setCodigoConvenio("001");
        headerArquivo.setAgenciaMantenedora(1);
        headerArquivo.setDigitoVerificadorAgencia("1");
        headerArquivo.setNumeroContaCorrente(1);
        headerArquivo.setDigitoVerificadorContaCorrente("1");
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
        headerLote.setCodigoConvenio("0014");
        headerLote.setAgenciaMantenedora(1);
        headerLote.setDigitoVerificadorAgencia("1");
        headerLote.setNumeroContaCorrente(1);
        headerLote.setDigitoVerificadorContaCorrente("1");
        headerLote.setDigitoVerificadorAgenciaConta(" ");
        headerLote.setNomeEmpresa(empresa.getRazaoSocial());
        headerLote.setMensagem1(" ");
        headerLote.setMensagem2(" ");
        headerLote.setNumeroRemessaRetorno(0);
        headerLote.setDataGravacao(new Date());
        headerLote.setDataCredito(" ");

        ff.addRecord(headerLote.getRecord());

        int sequenciaLote = 0;
        ColaboradorVO colaborador;
        for (int i = 0; i < listaColaborador.size(); i++) {
            colaborador = listaColaborador.get(i);
            //Exercicio: definir os atributos referente aos dados de pagamento
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
