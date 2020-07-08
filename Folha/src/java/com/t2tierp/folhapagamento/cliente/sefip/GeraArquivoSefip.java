package com.t2tierp.folhapagamento.cliente.sefip;

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

/**
 * <p>Title: T2Ti ERP</p>
 * <p>Description: Classe que gera o arquivo SEFIP. /p>
 *
 * <p>The MIT License</p>
 *
 * <p>Copyright: Copyright (C) 2010 T2Ti.COM
 *
 * Permission is hereby granted, free of charge, to any person
 * obtaining a copy of this software and associated documentation
 * files (the "Software"), to deal in the Software without
 * restriction, including without limitation the rights to use,
 * copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the
 * Software is furnished to do so, subject to the following
 * conditions:
 *
 * The above copyright notice and this permission notice shall be
 * included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES
 * OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT
 * HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY,
 * WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
 * FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR
 * OTHER DEALINGS IN THE SOFTWARE.
 *
 *        The author may be contacted at:
 *            t2ti.com@gmail.com</p>
 *
 * @author Claudio de Barros (t2ti.com@gmail.com)
 * @version 1.0
 */
public class GeraArquivoSefip {

    public void geraArquivoSefip(File arquivo, EmpresaVO empresa, List<ColaboradorVO> colaboradores, int mesCompetencia, int anoCompetencia, int codigoRecolhimento) throws Exception {
        //buscar o xml com o layout
        File layout = File.createTempFile("layout", ".xml");
        layout.deleteOnExit();
        InputStream in = this.getClass().getResourceAsStream("/com/t2tierp/folhapagamento/cliente/sefip/sefip.xml");
        FileUtils.copyInputStreamToFile(in, layout);

        //cria um objeto FlatFile
        FlatFile<Record> ff = Texgit.createFlatFile(layout);

        //registro tipo 00
        Tipo00 tipo00 = new Tipo00(ff.createRecord("Tipo00"));
        tipo00.setTipoRemessa(1);
        tipo00.setTipoInscricao(1);
        tipo00.setInscricaoResponsavel(Long.valueOf(empresa.getCnpj()));
        tipo00.setNomeResponsavel(empresa.getRazaoSocial());
        tipo00.setNomePessoaContato(empresa.getContato());
        tipo00.setLogradouro(empresa.getListaEndereco().get(0).getLogradouro() + " " + empresa.getListaEndereco().get(0).getNumero());
        tipo00.setCep(empresa.getListaEndereco().get(0).getCep());
        tipo00.setCidade(empresa.getListaEndereco().get(0).getCidade());
        tipo00.setUf(empresa.getListaEndereco().get(0).getUf());
        tipo00.setTelefoneContato(empresa.getListaEndereco().get(0).getFone());
        tipo00.setEnderecoInternet(empresa.getEmail());
        tipo00.setCompetencia(mesCompetencia < 10 ? "0" + mesCompetencia + anoCompetencia : "" + mesCompetencia + anoCompetencia);
        tipo00.setCodigoRecolhimento(codigoRecolhimento);
        tipo00.setIndicadorRecolhimentoFgts(1);
        tipo00.setModalidadeArquivo(1);
        tipo00.setTipoInscricaoFornecedor(1);
        tipo00.setDataRecolhimentoFgts(new Date());
        tipo00.setIndicadorRecolhimentoPrevidencia(1);
        tipo00.setDataRecolhimentoPrevidencia(new Date());
        tipo00.setTipoInscricaoFornecedor(1);
        tipo00.setInscricaoFornecedor(Long.valueOf(empresa.getCnpj()));

        ff.addRecord(tipo00.getRecord());

        //registro tipo 10
        Tipo10 tipo10 = new Tipo10(ff.createRecord("Tipo10"));
        tipo10.setTipoInscricao(1);
        tipo10.setInscricaoEmpresa(Long.valueOf(empresa.getCnpj()));
        tipo10.setNomeEmpresa(empresa.getRazaoSocial());
        tipo10.setLogradouro(empresa.getListaEndereco().get(0).getLogradouro() + " " + empresa.getListaEndereco().get(0).getNumero());
        tipo10.setCep(empresa.getListaEndereco().get(0).getCep());
        tipo10.setCidade(empresa.getListaEndereco().get(0).getCidade());
        tipo10.setUf(empresa.getListaEndereco().get(0).getUf());
        tipo10.setTelefone(empresa.getListaEndereco().get(0).getFone());
        tipo10.setIndicadorAlteracaoEndereco("N");
        tipo10.setCnae(Integer.valueOf(empresa.getCodigoCnaePrincipal()));
        tipo10.setIndicadorAlteracaoCnae("N");
        tipo10.setAliquotaRat(0);
        tipo10.setCodigoCentralizacao(1);
        tipo10.setSimples(2);
        tipo10.setFpas(empresa.getFpas().getCodigo());
        tipo10.setCodigoOutrasEntidades(empresa.getCodigoTerceiros());
        tipo10.setCodigoPagamentoGps(empresa.getCodigoGps());

        ff.addRecord(tipo10.getRecord());

        //REGISTRO TIPO 12 – Informações Adicionais do Recolhimento da Empresa (Opcional)
        // Implementado a critério do Participante do T2Ti ERP }

        //REGISTRO TIPO 13 – Alteração Cadastral Trabalhador (Opcional)
        // Implementado a critério do Participante do T2Ti ERP }

        //REGISTRO TIPO 14 – Inclusão/Alteração Endereço do Trabalhador (Opcional)
        // Implementado a critério do Participante do T2Ti ERP }

        //REGISTRO TIPO 20 – Registro do Tomador de Serviço/Obra de Construção Civil (Opcional)
        // Implementado a critério do Participante do T2Ti ERP }

        //REGISTRO TIPO 21 - Registro de informações adicionais do Tomador de Serviço/Obra de Const. Civil (Opcional)
        // Implementado a critério do Participante do T2Ti ERP }

        //REGISTRO TIPO 30 - Registro do Trabalhador
        Tipo30 tipo30;
        ColaboradorVO colaborador;
        for (int i = 0; i < colaboradores.size(); i++) {
            tipo30 = new Tipo30(ff.createRecord("Tipo30"));
            colaborador = colaboradores.get(i);

            tipo30.setTipoInscricaoEmpresa(1);
            tipo30.setInscricaoEmpresa(Long.valueOf(empresa.getCnpj()));
            tipo30.setPisPasep(Long.valueOf(colaborador.getPisNumero()));
            tipo30.setDataAmissao(colaborador.getDataAdmissao());
            tipo30.setCategoriaTrabalhador(Integer.valueOf(colaborador.getCategoriaSefip()));
            tipo30.setNomeTrabalhador(colaborador.getPessoa().getNome());
            tipo30.setMatriculaEmpregado(Long.valueOf(colaborador.getMatricula()));
            tipo30.setNumeroCtps(Long.valueOf(colaborador.getCtpsNumero()));
            tipo30.setSerieCtps(Long.valueOf(colaborador.getCtpsSerie()));
            tipo30.setDataOpcao(colaborador.getFgtsDataOpcao());

            ff.addRecord(tipo30.getRecord());
        }

        //REGISTRO TIPO 32 – Movimentação do Trabalhador (Opcional)
        // Implementado a critério do Participante do T2Ti ERP }

        //REGISTRO TIPO 50– Empresa Com Recolhimento pelos códigos 027, 046, 604 e 736 (Header da empresa ) (PARA IMPLEMENTAÇÃO FUTURA)
        // Implementado a critério do Participante do T2Ti ERP }

        //REGISTRO TIPO 51 - Registro de Individualização de valores recolhidos pelos códigos 027, 046, 604 e 736 (PARA IMPLEMENTAÇÃO FUTURA)
        // Implementado a critério do Participante do T2Ti ERP }

        // registros tipo90
        Tipo90 tipo90;
        tipo90 = new Tipo90(ff.createRecord("Tipo90"));
        ff.addRecord(tipo90.getRecord());

        FileUtils.writeLines(arquivo, ff.write(), System.getProperty("line.separator"));
    }
}
