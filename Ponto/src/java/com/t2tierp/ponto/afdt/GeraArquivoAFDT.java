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
package com.t2tierp.ponto.afdt;

import com.t2tierp.cadastros.java.EmpresaVO;
import com.t2tierp.padrao.cliente.Container;
import com.t2tierp.ponto.java.PontoMarcacaoVO;
import java.io.File;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.apache.commons.io.FileUtils;
import org.jrimum.texgit.FlatFile;
import org.jrimum.texgit.Record;
import org.jrimum.texgit.Texgit;

public class GeraArquivoAFDT {

    public void geraArquivoAFDT(File arquivo, Date dataInicial, Date dataFinal, List<PontoMarcacaoVO> marcacoes) throws Exception {

        SimpleDateFormat formatoHora = new SimpleDateFormat("HHmm");
        Date dataAtual = new Date();

        //buscar o xml com o layout
        File layout = File.createTempFile("layout", ".xml");
        layout.deleteOnExit();
        InputStream in = this.getClass().getResourceAsStream("/com/t2tierp/ponto/afdt/afdt.xml");
        FileUtils.copyInputStreamToFile(in, layout);

        //cria um objeto FlatFile
        FlatFile<Record> ff = Texgit.createFlatFile(layout);

        EmpresaVO empresa = (EmpresaVO) Container.getContainer().get("empresa");

        //registro tipo 1
        Tipo1 tipo1 = new Tipo1(ff.createRecord("Tipo1"));
        tipo1.setNumeroSequencialRegistro(1l);
        tipo1.setTipoIdentificadorEmpregador(1);
        tipo1.setCnpjCpfEmpreador(Long.valueOf(empresa.getCnpj()));
        tipo1.setCeiEmpregador(empresa.getCei());
        tipo1.setRazaoSocialEmpregador(empresa.getRazaoSocial());
        tipo1.setDataInicial(dataInicial);
        tipo1.setDataFinal(dataFinal);
        tipo1.setDataGeracaoArquivo(dataAtual);
        tipo1.setHoraGeracaoArquivo(formatoHora.format(dataAtual));

        ff.addRecord(tipo1.getRecord());

        //registros tipo 2
        Tipo2 tipo2;
        long sequencia = 1;
        PontoMarcacaoVO marcacao;
        for (int i = 0; i < marcacoes.size(); i++) {
            tipo2 = new Tipo2(ff.createRecord("Tipo2"));
            marcacao = marcacoes.get(i);

            sequencia++;
            tipo2.setNumeroSequencialRegistro(sequencia);
            tipo2.setDataMarcacao(marcacao.getDataMarcacao());
            tipo2.setHoraMarcacao(marcacao.getHoraMarcacao().substring(0, 2) + marcacao.getHoraMarcacao().substring(3, 5));
            tipo2.setPisEmpregado(marcacao.getColaborador().getPisNumero());
            tipo2.setNumFabricacaoREP(Long.valueOf(marcacao.getPontoRelogio().getNumeroSerie()));
            tipo2.setTipoMarcacao(marcacao.getTipoMarcacao());
            tipo2.setNumeroSequencialEmpregado(Integer.valueOf(marcacao.getParEntradaSaida().substring(1)));
            tipo2.setTipoRegistroMarcado(marcacao.getTipoRegistro());
            if (marcacao.getJustificativa() != null) {
                tipo2.setMotivo(marcacao.getJustificativa());
            } else {
                tipo2.setMotivo(" ");
            }

            ff.addRecord(tipo2.getRecord());
        }

        //registro tipo 9
        sequencia++;
        Tipo9 tipo9 = new Tipo9(ff.createRecord("Tipo9"));
        tipo9.setNumeroSequencialRegistro(sequencia);

        ff.addRecord(tipo9.getRecord());

        FileUtils.writeLines(arquivo, ff.write(), System.getProperty("line.separator"));
    }
}
