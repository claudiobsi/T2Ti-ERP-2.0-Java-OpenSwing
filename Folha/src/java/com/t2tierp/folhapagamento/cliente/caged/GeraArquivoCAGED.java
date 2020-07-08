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
package com.t2tierp.folhapagamento.cliente.caged;

import com.t2tierp.cadastros.java.ColaboradorVO;
import com.t2tierp.cadastros.java.EmpresaVO;
import java.io.File;
import java.io.InputStream;
import java.util.List;
import org.apache.commons.io.FileUtils;
import org.jrimum.texgit.FlatFile;
import org.jrimum.texgit.Record;
import org.jrimum.texgit.Texgit;

public class GeraArquivoCAGED {

    public void geraArquivoCAGED(File arquivo, EmpresaVO empresa, List<ColaboradorVO> colaboradores, int mesCompetencia, int anoCompetencia, int alteracaoAutorizado) throws Exception {
        //buscar o xml com o layout
        File layout = File.createTempFile("layout", ".xml");
        layout.deleteOnExit();
        InputStream in = this.getClass().getResourceAsStream("/com/t2tierp/folhapagamento/cliente/caged/caged.xml");
        FileUtils.copyInputStreamToFile(in, layout);

        int sequencia = 1;

        //cria um objeto FlatFile
        FlatFile<Record> ff = Texgit.createFlatFile(layout);

        //registro tipo A
        TipoA tipoA = new TipoA(ff.createRecord("TipoA"));
        tipoA.setTipoLayout("L2009");
        tipoA.setMesCompetencia(mesCompetencia);
        tipoA.setAnoCompetencia(anoCompetencia);
        tipoA.setAlteracao(alteracaoAutorizado);
        tipoA.setSequencia(sequencia);
        tipoA.setTipoIdentificacao(1);
        tipoA.setNumeroIdentificadorAutorizado(Long.valueOf(empresa.getCnpj()));
        tipoA.setNomeAutorizado(empresa.getRazaoSocial());
        tipoA.setEnderecoAutorizado(empresa.getListaEndereco().get(0).getLogradouro() + " " + empresa.getListaEndereco().get(0).getNumero());
        tipoA.setCEP(Integer.valueOf(empresa.getListaEndereco().get(0).getCep()));
        tipoA.setUF(empresa.getListaEndereco().get(0).getUf());
        tipoA.setDDD(Integer.valueOf(empresa.getListaEndereco().get(0).getFone().substring(0, 2)));
        tipoA.setTelefone(Integer.valueOf(empresa.getListaEndereco().get(0).getFone().substring(2)));
        tipoA.setRamal(0);
        tipoA.setTotalEstabelecimentosInformados(1);
        tipoA.setTotalMovimentacoesInformadas(colaboradores.size());

        ff.addRecord(tipoA.getRecord());

        sequencia++;

        //registro tipo B
        TipoB tipoB = new TipoB(ff.createRecord("TipoB"));
        tipoB.setTipoIdentificacao(1);
        tipoB.setNumeroIdentificadorEstabelecimento(Long.valueOf(empresa.getCnpj()));
        tipoB.setPrimeiraDeclaracao(1);
        tipoB.setAlteracao(alteracaoAutorizado);
        tipoB.setCEP(Integer.valueOf(empresa.getListaEndereco().get(0).getCep()));
        tipoB.setNomeEstabelecimento(empresa.getRazaoSocial());
        tipoB.setEndereco(empresa.getListaEndereco().get(0).getLogradouro() + " " + empresa.getListaEndereco().get(0).getNumero());
        tipoB.setBairro(empresa.getListaEndereco().get(0).getBairro());
        tipoB.setUF(empresa.getListaEndereco().get(0).getUf());
        tipoB.setTotalEmpregadosPrimeiroDia(0);
        tipoB.setPorteEstabelecimento(1);
        tipoB.setCNAE(Integer.valueOf(empresa.getCodigoCnaePrincipal()));
        tipoB.setDDD(Integer.valueOf(empresa.getListaEndereco().get(0).getFone().substring(0, 2)));
        tipoB.setTelefone(Integer.valueOf(empresa.getListaEndereco().get(0).getFone().substring(2)));
        tipoB.setEmail(empresa.getEmail());

        ff.addRecord(tipoB.getRecord());

        // registros tipoC
        TipoC tipoC;
        ColaboradorVO colaborador;
        for (int i = 0; i < colaboradores.size(); i++) {
            tipoC = new TipoC(ff.createRecord("TipoC"));
            colaborador = colaboradores.get(i);

            sequencia++;
            tipoC.setTipoIdentificacao(1);
            tipoC.setNumeroIdentificadorEstabelecimento(Long.valueOf(empresa.getCnpj()));
            tipoC.setSequencia(sequencia);
            tipoC.setPisPasep(Long.valueOf(colaborador.getPisNumero()));
            if (colaborador.getPessoa().getPessoaFisica() != null && colaborador.getPessoa().getPessoaFisica().getSexo() != null) {
                if (colaborador.getPessoa().getPessoaFisica().getSexo().equals("M")) {
                    tipoC.setSexo(1);
                } else {
                    tipoC.setSexo(1);
                }
            }
            if (colaborador.getPessoa().getPessoaFisica() != null) {
                tipoC.setDataNascimento(colaborador.getPessoa().getPessoaFisica().getDataNascimento());
            }
            tipoC.setInstrucao(colaborador.getNivelFormacao().getGrauInstrucaoCaged());

            ff.addRecord(tipoC.getRecord());
        }

        // registros tipoX
        //Implementado a critério do Participante do T2Ti ERP
        //No proximo ciclo sera implementado o eSocial
        TipoX tipoX;
        for (int i = 0; i < colaboradores.size(); i++) {
            tipoX = new TipoX(ff.createRecord("TipoX"));
            colaborador = colaboradores.get(i);

            sequencia++;
            tipoX.setTipoIdentificacao(1);
            tipoX.setNumeroIdentificadorEstabelecimento(Long.valueOf(empresa.getCnpj()));
            tipoX.setSequencia(sequencia);
            tipoX.setPisPasep(Long.valueOf(colaborador.getPisNumero()));
            // TODO : como buscar a informacao dos atributos Sexo e DataNascimento?
            tipoX.setSexo(1);
            tipoX.setDataNascimento(colaborador.getDataAdmissao());
            tipoX.setInstrucao(colaborador.getNivelFormacao().getGrauInstrucaoCaged());
            // TODO : como buscar a informacao dos atributos SalarioMensal e HorasTrabalhadas?
            tipoX.setSalarioMensal(62200);
            tipoX.setHorasTrabalhadas(40);
            tipoX.setDataAdmissao(colaborador.getDataAdmissao());
            if (colaborador.getDataDemissao() == null) {
                tipoX.setTipoMovimento(colaborador.getCodigoAdmissaoCaged());
            } else {
                tipoX.setTipoMovimento(colaborador.getCodigoDemissaoCaged());
            }
            tipoX.setNomeEmpregado(colaborador.getPessoa().getNome());
            tipoX.setNumeroCtps(Integer.valueOf(colaborador.getCtpsNumero()));
            tipoX.setSerieCtps(Integer.valueOf(colaborador.getCtpsSerie()));
            // TODO : onde buscar a informacao da Atualizacao?
            tipoX.setAtualizacao(1);
            tipoX.setMesCompetenciaAcerto(mesCompetencia);
            tipoX.setAnoCompetenciaAcerto(anoCompetencia);
            // TODO : como buscar a informacao dos atributos RacaCor, PessoaDeficiente, CBO2000, Aprendiz, TipoDeficiencia?
            tipoX.setRacaCor(1);
            tipoX.setPessoaDeficiente(2);
            tipoX.setCBO2000(i);
            tipoX.setAprendiz(1);
            tipoX.setUFCtps(colaborador.getCtpsUf());
            tipoX.setTipoDeficiencia(0);
            // TODO : onde buscar o cpf e o cep do colaborador?
            tipoX.setCPF(11111111111l);
            tipoX.setCEPResidenciaTrabalhador(71000000);

            ff.addRecord(tipoX.getRecord());
        }

        FileUtils.writeLines(arquivo, ff.write(), System.getProperty("line.separator"));
    }
}
