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
package com.t2tierp.sped.servidor;

import com.t2tierp.cadastros.java.ContadorVO;
import com.t2tierp.cadastros.java.EmpresaVO;
import com.t2tierp.contabilidade.java.ContabilContaVO;
import com.t2tierp.contabilidade.java.ContabilHistoricoVO;
import com.t2tierp.contabilidade.java.ContabilLancamentoCabecalhoVO;
import com.t2tierp.contabilidade.java.ContabilLancamentoDetalheVO;
import com.t2tierp.contabilidade.java.ContabilLivroVO;
import com.t2tierp.contabilidade.java.ContabilTermoVO;
import com.t2tierp.contabilidade.java.PlanoContaVO;
import com.t2tierp.contabilidade.java.RegistroCartorioVO;
import com.t2tierp.ged.java.ArquivoVO;
import com.t2tierp.padrao.java.Biblioteca;
import com.t2tierp.padrao.servidor.HibernateUtil;
import com.t2tierp.sped.SpedContabil;
import com.t2tierp.sped.contabil.blocoi.RegistroI050;
import com.t2tierp.sped.contabil.blocoi.RegistroI075;
import com.t2tierp.sped.contabil.blocoi.RegistroI150;
import com.t2tierp.sped.contabil.blocoi.RegistroI155;
import com.t2tierp.sped.contabil.blocoi.RegistroI200;
import com.t2tierp.sped.contabil.blocoi.RegistroI250;
import com.t2tierp.sped.contabil.blocoj.RegistroJ930;
import com.t2tierp.sped.java.ViewSpedI155VoId;
import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.openswing.swing.message.receive.java.ErrorResponse;
import org.openswing.swing.message.receive.java.Response;
import org.openswing.swing.message.receive.java.VOResponse;
import org.openswing.swing.message.send.java.GridParams;
import org.openswing.swing.server.Action;
import org.openswing.swing.server.UserSessionParameters;

public class GeraSpedContabilAction implements Action {

    private SpedContabil sped;
    private Calendar dataInicial;
    private Calendar dataFinal;
    private String formaEscrituracao;
    private String versaoLayout;
    private EmpresaVO empresa;
    private Session session;
    private Criteria criteria;

    public GeraSpedContabilAction() {
    }

    public String getRequestName() {
        return "geraSpedContabilAction";
    }

    public Response executeCommand(Object inputPar, UserSessionParameters userSessionPars, HttpServletRequest request, HttpServletResponse response, HttpSession userSession, ServletContext context) {
        GridParams pars = (GridParams) inputPar;
        try {
            this.session = HibernateUtil.getSessionFactory().openSession();
            this.sped = new SpedContabil();
            this.dataInicial = Calendar.getInstance();
            this.dataInicial.setTime((Date) pars.getOtherGridParams().get("dataInicial"));
            this.dataFinal = Calendar.getInstance();
            this.dataFinal.setTime((Date) pars.getOtherGridParams().get("dataFinal"));
            this.formaEscrituracao = (String) pars.getOtherGridParams().get("formaEscrituracao");
            this.versaoLayout = (String) pars.getOtherGridParams().get("versaoLayout");
            this.empresa = (EmpresaVO) pars.getOtherGridParams().get("empresa");

            geraBloco0();
            geraBlocoI();
            geraBlocoJ();

            File file = File.createTempFile("sped_contabil", ".txt");
            file.deleteOnExit();

            sped.geraArquivoTxt(file);

            ArquivoVO arquivo = new ArquivoVO();
            arquivo.setFile(Biblioteca.getBytesFromFile(file));

            return new VOResponse(arquivo);
        } catch (Exception ex) {
            ex.printStackTrace();
            return new ErrorResponse(ex.getMessage());
        } finally {
            try {
                this.session.close();
            } catch (Exception ex1) {
            }
        }
    }

    //Bloco 0 - Abertura, Identificação e Referências
    private void geraBloco0() {
        sped.getBloco0().limpaRegistros();

        // REGISTRO 0000: ABERTURA DO ARQUIVO DIGITAL E IDENTIFICAÇÃO DO EMPRESÁRIO OU DA SOCIEDADE EMPRESÁRIA
        sped.getBloco0().getRegistro0000().setDtIni(dataInicial.getTime());
        sped.getBloco0().getRegistro0000().setDtFin(dataFinal.getTime());
        sped.getBloco0().getRegistro0000().setNome(empresa.getRazaoSocial());
        sped.getBloco0().getRegistro0000().setCnpj(empresa.getCnpj());
        sped.getBloco0().getRegistro0000().setIe(empresa.getInscricaoEstadual());
        sped.getBloco0().getRegistro0000().setCodMun(empresa.getCodigoIbgeCidade());
        sped.getBloco0().getRegistro0000().setIm(empresa.getInscricaoMunicipal());
        sped.getBloco0().getRegistro0000().setIndSitEsp("");
        sped.getBloco0().getRegistro0000().setUf(empresa.getListaEndereco().get(0).getUf());

        // REGISTRO 0001: ABERTURA DO BLOCO 0
        // bloco com dados informados = 0 | sem dados inf = 1
        sped.getBloco0().getRegistro0001().setIndDad(0);

        // REGISTRO 0007: OUTRAS INSCRIÇÕES CADASTRAIS DA PESSOA JURÍDICA
        // Implementado a critério do Participante do T2Ti ERP
        // REGISTRO 0020: ESCRITURAÇÃO CONTÁBIL DESCENTRALIZADA
        // Implementado a critério do Participante do T2Ti ERP - Para o treinamento a escrituração será centralizada
        // REGISTRO 0150: TABELA DE CADASTRO DO PARTICIPANTE
        // Implementado a critério do Participante do T2Ti ERP
        // REGISTRO 0180: IDENTIFICAÇÃO DO RELACIONAMENTO COM O PARTICIPANTE
        // Implementado a critério do Participante do T2Ti ERP
    }

    //Bloco I - Lançamentos Contábeis
    private void geraBlocoI() throws Exception {
        // REGISTRO I001: ABERTURA DO BLOCO I
        sped.getBlocoI().getRegistroI001().setIndDad(0);

        // REGISTRO I010: IDENTIFICAÇÃO DA ESCRITURAÇÃO CONTÁBIL
        sped.getBlocoI().getRegistroI010().setIndEsc(formaEscrituracao);
        sped.getBlocoI().getRegistroI010().setCodVerLc(versaoLayout);

        // REGISTRO I012: LIVROS AUXILIARES AO DIÁRIO
        // Implementado a critério do Participante do T2Ti ERP
        // REGISTRO I015: IDENTIFICAÇÃO DAS CONTAS DA ESCRITURAÇÃO RESUMIDA A QUE SE REFERE A ESCRITURAÇÃO AUXILIAR
        // Implementado a critério do Participante do T2Ti ERP
        // REGISTRO I020: CAMPOS ADICIONAIS
        // Implementado a critério do Participante do T2Ti ERP
        // REGISTRO I030: TERMO DE ABERTURA
        criteria = session.createCriteria(ContabilLivroVO.class);
        criteria.add(Restrictions.eq("formaEscrituracao", formaEscrituracao));
        criteria.add(Restrictions.eq("competencia", Biblioteca.mesAno(dataInicial)));
        ContabilLivroVO contabilLivro = (ContabilLivroVO) criteria.uniqueResult();

        if (contabilLivro != null) {
            criteria = session.createCriteria(ContabilTermoVO.class);
            criteria.add(Restrictions.eq("contabilLivro", contabilLivro));
            criteria.add(Restrictions.eq("aberturaEncerramento", "A"));
            ContabilTermoVO contabilTermo = (ContabilTermoVO) criteria.uniqueResult();
            if (contabilTermo == null) {
                throw new Exception("Termo de Abertura não encontrado");
            }
            criteria = session.createCriteria(RegistroCartorioVO.class);
            criteria.add(Restrictions.eq("empresa", empresa));
            RegistroCartorioVO registroCartorio = (RegistroCartorioVO) criteria.uniqueResult();
            if (contabilTermo == null) {
                throw new Exception("Registro em Cartório não encontrado");
            }

            sped.getBlocoI().getRegistroI030().setNumOrd(contabilTermo.getNumeroRegistro());
            sped.getBlocoI().getRegistroI030().setNatLivr(contabilLivro.getDescricao());
            sped.getBlocoI().getRegistroI030().setNome(empresa.getRazaoSocial());
            sped.getBlocoI().getRegistroI030().setNire(registroCartorio.getNire());
            sped.getBlocoI().getRegistroI030().setCnpj(empresa.getCnpj());
            sped.getBlocoI().getRegistroI030().setDtArq(registroCartorio.getDataRegistro());
            sped.getBlocoI().getRegistroI030().setDescMun(empresa.getListaEndereco().get(0).getCidade());
        }

        // REGISTRO I050: PLANO DE CONTAS
        criteria = session.createCriteria(PlanoContaVO.class);
        criteria.add(Restrictions.eq("empresa", empresa));
        PlanoContaVO planoConta = (PlanoContaVO) criteria.uniqueResult();
        List<ContabilContaVO> listaContabilConta = new ArrayList();

        if (planoConta != null) {
            criteria = session.createCriteria(ContabilContaVO.class);
            criteria.add(Restrictions.eq("planoConta", planoConta));
            listaContabilConta = criteria.list();

            RegistroI050 registroI050;
            for (int i = 0; i < listaContabilConta.size(); i++) {
                registroI050 = new RegistroI050();
                registroI050.setDtAlt(listaContabilConta.get(i).getDataInclusao());
                registroI050.setCodNat(listaContabilConta.get(i).getCodigoEfd());
                registroI050.setIndCta(listaContabilConta.get(i).getTipo());
                String classificacao[] = listaContabilConta.get(i).getClassificacao().split(".");
                registroI050.setNivel(String.valueOf(classificacao.length));
                registroI050.setCodCta(listaContabilConta.get(i).getClassificacao());
                registroI050.setCodCtaSup("");
                registroI050.setCta(listaContabilConta.get(i).getDescricao());

                // REGISTRO I051: PLANO DE CONTAS REFERENCIAL
                /*
                 Observação: A partir da versão 3.X e alterações posteriores do PVA do Sped Contábil, não haverá o plano de
                 contas referencial da RFB . Portanto, para as empresas que utilizavam esse plano, não será necessário o preenchimento
                 do registro I051.

                 Fonte: Manual de Orientação da ECD
                 */
                //registroI050.getRegistroi051List().add(registroI051);
                sped.getBlocoI().getListaRegistroI050().add(registroI050);
            }
        }

        // REGISTRO I052: INDICAÇÃO DOS CÓDIGOS DE AGLUTINAÇÃO
        // Implementado a critério do Participante do T2Ti ERP
        // REGISTRO I075: TABELA DE HISTÓRICO PADRONIZADO
        criteria = session.createCriteria(ContabilHistoricoVO.class);
        criteria.add(Restrictions.eq("empresa", empresa));
        List<ContabilHistoricoVO> contabilHistorico = criteria.list();
        RegistroI075 registroI075;
        for (int i = 0; i < contabilHistorico.size(); i++) {
            registroI075 = new RegistroI075();

            registroI075.setCodHist(contabilHistorico.get(i).getId().toString());
            registroI075.setDescrHist(contabilHistorico.get(i).getDescricao());

            sped.getBlocoI().getListaRegistroI075().add(registroI075);
        }

        // REGISTRO I100: CENTRO DE CUSTOS
        // Implementado a critério do Participante do T2Ti ERP
        // REGISTRO I150: SALDOS PERIÓDICOS – IDENTIFICAÇÃO DO PERÍODO
        RegistroI150 registroI150 = new RegistroI150();
        registroI150.setDtIni(dataInicial.getTime());
        registroI150.setDtFin(dataFinal.getTime());

        // REGISTRO I151: Hash dos Arquivos que Contêm as Fichas de Lançamento Utilizadas no Período
        // Implementado a critério do Participante do T2Ti ERP
        BigDecimal creditos;
        BigDecimal debitos;
        BigDecimal saldo;
        ViewSpedI155VoId i155Credito;
        ViewSpedI155VoId i155Debito;
        for (int i = 0; i < listaContabilConta.size(); i++) {
            // REGISTRO I155: DETALHE DOS SALDOS PERIÓDICOS
            //busca o saldo anterior
            criteria = session.createCriteria(ViewSpedI155VoId.class);
            criteria.add(Restrictions.eq("viewSpedI155VO.mesAno", Biblioteca.mesAno(Biblioteca.mesAnterior(dataInicial))));
            criteria.add(Restrictions.eq("viewSpedI155VO.idContabilConta", listaContabilConta.get(i).getId()));
            criteria.add(Restrictions.eq("viewSpedI155VO.tipo", "C"));

            i155Credito = (ViewSpedI155VoId) criteria.uniqueResult();
            if (i155Credito != null) {
                creditos = i155Credito.getViewSpedI155VO().getSomaValor();
            } else {
                creditos = BigDecimal.ZERO;
            }

            criteria = session.createCriteria(ViewSpedI155VoId.class);
            criteria.add(Restrictions.eq("viewSpedI155VO.mesAno", Biblioteca.mesAno(Biblioteca.mesAnterior(dataInicial))));
            criteria.add(Restrictions.eq("viewSpedI155VO.idContabilConta", listaContabilConta.get(i).getId()));
            criteria.add(Restrictions.eq("viewSpedI155VO.tipo", "D"));

            i155Debito = (ViewSpedI155VoId) criteria.uniqueResult();
            if (i155Debito != null) {
                debitos = i155Credito.getViewSpedI155VO().getSomaValor();
            } else {
                debitos = BigDecimal.ZERO;
            }

            saldo = creditos.subtract(debitos);

            RegistroI155 registroI155 = new RegistroI155();
            registroI155.setCodCta(listaContabilConta.get(i).getClassificacao());
            registroI155.setCodCcus("");
            registroI155.setVlSldIni(saldo);
            if (saldo.compareTo(BigDecimal.ZERO) == -1) {
                registroI155.setIndDcIni("D");
            } else {
                registroI155.setIndDcIni("C");
            }

            //busca o saldo atual
            criteria = session.createCriteria(ViewSpedI155VoId.class);
            criteria.add(Restrictions.eq("viewSpedI155VO.mesAno", Biblioteca.mesAno(dataInicial)));
            criteria.add(Restrictions.eq("viewSpedI155VO.idContabilConta", listaContabilConta.get(i).getId()));
            criteria.add(Restrictions.eq("viewSpedI155VO.tipo", "C"));

            i155Credito = (ViewSpedI155VoId) criteria.uniqueResult();
            if (i155Credito != null) {
                creditos = i155Credito.getViewSpedI155VO().getSomaValor();
            } else {
                creditos = BigDecimal.ZERO;
            }

            criteria = session.createCriteria(ViewSpedI155VoId.class);
            criteria.add(Restrictions.eq("viewSpedI155VO.mesAno", Biblioteca.mesAno(dataInicial)));
            criteria.add(Restrictions.eq("viewSpedI155VO.idContabilConta", listaContabilConta.get(i).getId()));
            criteria.add(Restrictions.eq("viewSpedI155VO.tipo", "D"));

            i155Debito = (ViewSpedI155VoId) criteria.uniqueResult();
            if (i155Debito != null) {
                debitos = i155Credito.getViewSpedI155VO().getSomaValor();
            } else {
                debitos = BigDecimal.ZERO;
            }

            saldo = creditos.subtract(debitos);

            registroI155.setVlDeb(debitos);
            registroI155.setVlCred(creditos);
            registroI155.setVlSldFin(saldo);
            if (saldo.compareTo(BigDecimal.ZERO) == -1) {
                registroI155.setIndDcFin("D");
            } else {
                registroI155.setIndDcFin("C");
            }

            registroI150.getRegistroi155List().add(registroI155);

            // REGISTRO I157: TRANSFERÊNCIA DE SALDOS DE PLANO DE CONTAS ANTERIOR
            // Implementado a critério do Participante do T2Ti ERP
        }
        sped.getBlocoI().getListaRegistroI150().add(registroI150);

        // REGISTRO I200: LANÇAMENTO CONTÁBIL
        criteria = session.createCriteria(ContabilLancamentoCabecalhoVO.class);
        criteria.add(Restrictions.between("dataLancamento", dataInicial.getTime(), dataFinal.getTime()));
        List<ContabilLancamentoCabecalhoVO> listaLancamentoCabecalho = criteria.list();
        RegistroI200 registroI200;
        RegistroI250 registroI250;
        for (int i = 0; i < listaLancamentoCabecalho.size(); i++) {
            registroI200 = new RegistroI200();
            registroI200.setNumLcto(String.valueOf(listaLancamentoCabecalho.get(i).getId()));
            registroI200.setDtLcto(listaLancamentoCabecalho.get(i).getDataLancamento());
            registroI200.setVlLcto(listaLancamentoCabecalho.get(i).getValor());
            registroI200.setIndLcto("N");

            criteria = session.createCriteria(ContabilLancamentoDetalheVO.class);
            criteria.add(Restrictions.eq("contabilLancamentoCabecalho", listaLancamentoCabecalho.get(i)));
            List<ContabilLancamentoDetalheVO> lancamentoDetalhe = criteria.list();
            for (int j = 0; j < lancamentoDetalhe.size(); j++) {
                registroI250 = new RegistroI250();
                registroI250.setCodCta(lancamentoDetalhe.get(j).getContabilConta().getClassificacao());
                //registroI250.setCodCcus("");
                registroI250.setVlDc(lancamentoDetalhe.get(j).getValor());
                registroI250.setIndDc(lancamentoDetalhe.get(j).getTipo());
                //registroI250.setNumArq("");
                registroI250.setCodHistPad(String.valueOf(lancamentoDetalhe.get(j).getContabilHistorico().getId()));
                registroI250.setHist(lancamentoDetalhe.get(j).getHistorico());
                //registroI250.setCodPart("");

                registroI200.getRegistroi250List().add(registroI250);
            }

            sped.getBlocoI().getListaRegistroI200().add(registroI200);
        }

        // REGISTRO I300: BALANCETES DIÁRIOS – IDENTIFICAÇÃO DA DATA
        // REGISTRO I310: DETALHES DO BALANCETE DIÁRIO
        // Implementados a critério do Participante do T2Ti ERP
        // REGISTRO I350: SALDOS DAS CONTAS DE RESULTADO ANTES DO ENCERRAMENTO – IDENTIFICAÇÃO DA DATA
        // REGISTRO I355: DETALHES DOS SALDOS DAS CONTAS DE RESULTADO ANTES DO ENCERRAMENTO
        // Implementados a critério do Participante do T2Ti ERP
        // REGISTRO I500: PARÂMETROS DE IMPRESSÃO E VISUALIZAÇÃO DO LIVRO RAZÃO AUXILIAR COM LEIAUTE PARAMETRIZÁVEL
        // REGISTRO I510: DEFINIÇÃO DE CAMPOS DO LIVRO RAZÃO AUXILIAR COM LEIAUTE PARAMETRIZÁVEL
        // REGISTRO I550: DETALHES DO LIVRO AUXILIAR COM LEIAUTE PARAMETRIZÁVEL
        // REGISTRO I555: TOTAIS NO LIVRO AUXILIAR COM LEIAUTE PARAMETRIZÁVEL
        // Implementados a critério do Participante do T2Ti ERP
    }

    //Bloco J - Demonstrações Contábeis
    private void geraBlocoJ() throws Exception {
        // REGISTRO J001: ABERTURA DO BLOCO J
        sped.getBlocoJ().getRegistroJ001().setIndDad(0);

        // REGISTRO J005: DEMONSTRAÇÕES CONTÁBEIS
        // Implementado a critério do Participante do T2Ti ERP
        // REGISTRO J100: BALANÇO PATRIMONIAL
        // Implementado a critério do Participante do T2Ti ERP
        //REGISTRO J150: DEMONSTRAÇÃO DO RESULTADO DO EXERCÍCIO
        //Implementado a critério do Participante do T2Ti ERP
        // REGISTRO J200: TABELA DE HISTÓRICO DE FATOS CONTÁBEIS QUE MODIFICAM A CONTA LUCROS ACUMULADOS OU A CONTA PREJUÍZOS ACUMULADOS OU TODO O PATRIMÔNIO LÍQUIDO
        // REGISTRO J210: DLPA – DEMONSTRAÇÃO DE LUCROS OU PREJUÍZOS ACUMULADOS/DMPL – DEMONSTRAÇÃO DE MUTAÇÕES DO PATRIMÔNIO LÍQUIDO
        // REGISTRO J215: FATO CONTÁBIL QUE ALTERA A CONTA LUCROS ACUMULADOS OU A CONTA PREJUÍZOS ACUMULADOS OU TODO O PATRIMÔNIO LÍQUIDO
        // Implementados a critério do Participante do T2Ti ERP
        // REGISTRO J310: DEMONSTRAÇÃO DO FLUXO DE CAIXA
        // Implementado a critério do Participante do T2Ti ERP
        // REGISTRO J410: DEMONSTRAÇÃO DO VALOR ADICIONADO
        // Implementado a critério do Participante do T2Ti ERP
        // REGISTRO J800: OUTRAS INFORMAÇÕES
        // Implementado a critério do Participante do T2Ti ERP
        // REGISTRO J900: TERMO DE ENCERRAMENTO
        criteria = session.createCriteria(ContabilLivroVO.class);
        criteria.add(Restrictions.eq("formaEscrituracao", formaEscrituracao));
        criteria.add(Restrictions.eq("competencia", Biblioteca.mesAno(dataInicial)));
        ContabilLivroVO contabilLivro = (ContabilLivroVO) criteria.uniqueResult();

        if (contabilLivro != null) {
            criteria = session.createCriteria(ContabilTermoVO.class);
            criteria.add(Restrictions.eq("contabilLivro", contabilLivro));
            criteria.add(Restrictions.eq("aberturaEncerramento", "E"));
            ContabilTermoVO contabilTermo = (ContabilTermoVO) criteria.uniqueResult();
            if (contabilTermo == null) {
                throw new Exception("Termo de Encerramento não encontrado");
            }

            sped.getBlocoJ().getRegistroJ900().setNumOrd(contabilTermo.getNumeroRegistro());
            sped.getBlocoJ().getRegistroJ900().setNatLivro(contabilLivro.getDescricao());
            sped.getBlocoJ().getRegistroJ900().setNome(empresa.getRazaoSocial());
            sped.getBlocoJ().getRegistroJ900().setDtIniEscr(contabilTermo.getEscrituracaoInicio());
            sped.getBlocoJ().getRegistroJ900().setDtFinEscr(contabilTermo.getEscrituracaoFim());

            // REGISTRO J930: IDENTIFICAÇÃO DOS SIGNATÁRIOS DA ESCRITURAÇÃO
            criteria = session.createCriteria(ContadorVO.class);
            List<ContadorVO> contadores = criteria.list();
            RegistroJ930 registroJ930;
            for (int i = 0; i < contadores.size(); i++) {
                registroJ930 = new RegistroJ930();
                registroJ930.setIdentNom(contadores.get(i).getNome());
                registroJ930.setIdentCpf(contadores.get(i).getCpf());
                registroJ930.setIdentQualif("CONTADOR");
                registroJ930.setCodAssin("900");
                registroJ930.setIndCrc(contadores.get(i).getInscricaoCrc());

                sped.getBlocoJ().getListaRegistroJ930().add(registroJ930);
            }
        }
    }
}
