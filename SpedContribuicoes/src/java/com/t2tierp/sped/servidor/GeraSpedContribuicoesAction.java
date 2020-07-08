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
import com.t2tierp.cadastros.java.EmpresaEnderecoVO;
import com.t2tierp.cadastros.java.EmpresaVO;
import com.t2tierp.cadastros.java.ProdutoAlteracaoItemVO;
import com.t2tierp.cadastros.java.ProdutoVO;
import com.t2tierp.cadastros.java.UnidadeProdutoVO;
import com.t2tierp.ged.java.ArquivoVO;
import com.t2tierp.nfe.java.NfeCabecalhoVO;
import com.t2tierp.nfe.java.NfeDestinatarioVO;
import com.t2tierp.nfe.java.NfeDetalheVO;
import com.t2tierp.nfe.java.NfeEmitenteVO;
import com.t2tierp.nfe.java.NfeTransporteVO;
import com.t2tierp.padrao.java.Biblioteca;
import com.t2tierp.padrao.servidor.HibernateUtil;
import com.t2tierp.pafecf.java.EcfImpressoraVO;
import com.t2tierp.pafecf.java.EcfNotaFiscalCabecalhoVO;
import com.t2tierp.pafecf.java.EcfR02VO;
import com.t2tierp.sped.SpedContribuicoes;
import com.t2tierp.sped.efd.bloco0.Registro0150;
import com.t2tierp.sped.efd.bloco0.Registro0190;
import com.t2tierp.sped.efd.bloco0.Registro0200;
import com.t2tierp.sped.efd.bloco0.Registro0205;
import com.t2tierp.sped.efd.bloco0.Registro0400;
import com.t2tierp.sped.efd.blococ.RegistroC100;
import com.t2tierp.sped.efd.blococ.RegistroC170;
import com.t2tierp.sped.efd.blococ.RegistroC380;
import com.t2tierp.sped.efd.blococ.RegistroC400;
import com.t2tierp.sped.efd.blococ.RegistroC405;
import com.t2tierp.sped.java.ViewSpedC300VO;
import com.t2tierp.sped.java.ViewSpedC300VoId;
import com.t2tierp.tributacao.java.TributOperacaoFiscalVO;
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

public class GeraSpedContribuicoesAction implements Action {

    private SpedContribuicoes sped;
    private Calendar dataInicial;
    private Calendar dataFinal;
    private String versao;
    private String tipoEscrituracao;
    private EmpresaVO empresa;
    private EmpresaEnderecoVO enderecoPrincipal;
    private ContadorVO contador;
    private Session session;
    private Criteria criteria;

    public GeraSpedContribuicoesAction() {
    }

    public String getRequestName() {
        return "geraSpedContribuicoesAction";
    }

    public Response executeCommand(Object inputPar, UserSessionParameters userSessionPars, HttpServletRequest request, HttpServletResponse response, HttpSession userSession, ServletContext context) {
        GridParams pars = (GridParams) inputPar;
        try {
            this.session = HibernateUtil.getSessionFactory().openSession();
            this.sped = new SpedContribuicoes();
            this.dataInicial = Calendar.getInstance();
            this.dataInicial.setTime((Date) pars.getOtherGridParams().get("dataInicial"));
            this.dataFinal = Calendar.getInstance();
            this.dataFinal.setTime((Date) pars.getOtherGridParams().get("dataFinal"));
            this.versao = (String) pars.getOtherGridParams().get("versao");
            this.tipoEscrituracao = (String) pars.getOtherGridParams().get("tipoEscrituracao");
            this.contador = (ContadorVO) pars.getOtherGridParams().get("contador");
            this.contador = (ContadorVO) Biblioteca.nullToEmpty(contador);
            this.empresa = (EmpresaVO) pars.getOtherGridParams().get("empresa");
            this.empresa = (EmpresaVO) Biblioteca.nullToEmpty(empresa);
            this.enderecoPrincipal = (EmpresaEnderecoVO) pars.getOtherGridParams().get("enderecoPrincipalEmpresa");
            this.enderecoPrincipal = (EmpresaEnderecoVO) Biblioteca.nullToEmpty(enderecoPrincipal);

            // BLOCO 0: ABERTURA, IDENTIFICAÇÃO E REFERÊNCIAS
            geraBloco0();

            // BLOCO A: DOCUMENTOS FISCAIS - SERVIÇOS (NÃO SUJEITOS AO ICMS)
                /*
             Será analisado após a implementação da NFS-e
             Exercício:
             Analisar como implementar com dados de um NF-e de serviço.
             */
            gerarBlocoA();

            // BLOCO C: DOCUMENTOS FISCAIS I - MERCADORIAS (ICMS/IPI
            geraBlocoC();

            // BLOCO D: DOCUMENTOS FISCAIS II - SERVIÇOS (ICMS).
            // Estabelecimentos  que  efetivamente  tenham realizado as operações especificadas no Bloco D (prestação ou contratação), relativas a serviços de transporte de cargas e/ou de passageiros, serviços de comunicação e de telecomunicação, mediante emissão de documento fiscal definido pela legislação do ICMS e do IPI, que devam ser escrituradas no Bloco D.
            // Não Implementado 
            gerarBlocoD();

            // BLOCO F: DEMAIS DOCUMENTOS E OPERAÇÕES
            // Neste  bloco  serão  informadas  pela  pessoa  jurídica,  as  demais  operações  geradoras  de  contribuição  ou  de crédito,  não informadas nos Blocos A, C e D
            // Não Implementado 
            gerarBlocoF();

            // BLOCO I: OPERAÇÕES DAS INSTITUIÇÕES FINANCEIRAS, SEGURADORAS, ENTIDADES DE  PREVIDENCIA  PRIVADA,  OPERADORAS  DE  PLANOS  DE  ASSISTÊNCIA  À SAÚDE E DEMAIS PESSOAS JURÍDICAS REFERIDAS NOS §§ 6º, 8ºE 9ºDO ART. 3ºDA LEI nº9.718/98.
            // Não Implementado 
            gerarBlocoI();

            // BLOCO  M: APURAÇÃO  DA  CONTRIBUIÇÃO  E  CRÉDITO  DO  PIS/PASEP  E DA COFINS
            // Gerado pelo PVA 
            gerarBlocoM();

            // BLOCO P: APURAÇÃO DA CONTRIBUIÇÃO PREVIDENCIÁRIA SOBRE A RECEITA BRUTA (CPRB)
            // Não Implementado 
            // BLOCO 1: COMPLEMENTO DA ESCRITURAÇÃO – CONTROLE DE  SALDOS DE  CRÉDITOS  E  DE  RETENÇÕES,  OPERAÇÕES  EXTEMPORÂNEAS E OUTRAS INFORMAÇÕES
            // Não Implementado 
            gerarBloco1();

            File file = File.createTempFile("sped_contribuicoes", ".txt");
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

    //BLOCO 0: ABERTURA, IDENTIFICAÇÃO E REFERÊNCIAS
    private void geraBloco0() throws Exception {
        criteria = session.createCriteria(NfeCabecalhoVO.class);
        criteria.add(Restrictions.between("dataHoraEmissao", dataInicial.getTime(), dataFinal.getTime()));
        List<NfeCabecalhoVO> listaNfeCabecalho = criteria.list();

        List<NfeEmitenteVO> listaNfeEmitente = new ArrayList<>();
        List<NfeDestinatarioVO> listaNfeDestinatario = new ArrayList<>();
        List<NfeDetalheVO> listaNfeDetalhe = new ArrayList<>();

        if (!listaNfeCabecalho.isEmpty()) {
            criteria = session.createCriteria(NfeEmitenteVO.class);
            criteria.add(Restrictions.in("nfeCabecalho", listaNfeCabecalho));
            listaNfeEmitente = criteria.list();

            criteria = session.createCriteria(NfeDestinatarioVO.class);
            criteria.add(Restrictions.in("nfeCabecalho", listaNfeCabecalho));
            listaNfeDestinatario = criteria.list();

            criteria = session.createCriteria(NfeDetalheVO.class);
            criteria.add(Restrictions.in("nfeCabecalho", listaNfeCabecalho));
            listaNfeDetalhe = criteria.list();
        }

        List<UnidadeProdutoVO> listaUnidadeProduto = new ArrayList<>();

        criteria = session.createCriteria(TributOperacaoFiscalVO.class);
        List<TributOperacaoFiscalVO> listaOperacaoFiscal = criteria.list();

        // REGISTRO 0000: ABERTURA DO ARQUIVO DIGITAL E IDENTIFICAÇÃO DA ENTIDADE
        sped.getBloco0().getRegistro0000().setDtIni(dataInicial.getTime());
        sped.getBloco0().getRegistro0000().setDtFin(dataFinal.getTime());
        sped.getBloco0().getRegistro0000().setCodVer(versao);
        //sped.getBloco0().getRegistro0000().setCodFin(finalidade);
        //sped.getBloco0().getRegistro0000().setIndPerfil(perfil);
        sped.getBloco0().getRegistro0000().setNome(empresa.getRazaoSocial());
        sped.getBloco0().getRegistro0000().setCnpj(empresa.getCnpj());
        sped.getBloco0().getRegistro0000().setCpf("");
        sped.getBloco0().getRegistro0000().setIe(empresa.getInscricaoEstadual());
        sped.getBloco0().getRegistro0000().setCodMun(empresa.getCodigoIbgeCidade().toString());
        sped.getBloco0().getRegistro0000().setIm(empresa.getInscricaoMunicipal());
        sped.getBloco0().getRegistro0000().setSuframa(empresa.getSuframa());
        sped.getBloco0().getRegistro0000().setIndAtiv("1");
        sped.getBloco0().getRegistro0000().setUf(enderecoPrincipal.getUf());

        // REGISTRO 0001: ABERTURA DO BLOCO 0
        sped.getBloco0().getRegistro0001().setIndMov(0);

        // REGISTRO 0035: IDENTIFICAÇÃO DE SOCIEDADE EM CONTA DE PARTICIPAÇÃO – SCP
        //{ Não Implementado }
        // REGISTRO 0100: DADOS DO CONTABILISTA
        sped.getBloco0().getRegistro0100().setNome(contador.getNome());
        if (!contador.getCpf().isEmpty()) {
            sped.getBloco0().getRegistro0100().setCpf(contador.getCpf());
        } else if (!contador.getCnpj().isEmpty()) {
            sped.getBloco0().getRegistro0100().setCpf(contador.getCnpj());
        }
        sped.getBloco0().getRegistro0100().setCrc(contador.getInscricaoCrc());
        sped.getBloco0().getRegistro0100().setCep(contador.getCep());
        sped.getBloco0().getRegistro0100().setEndereco(contador.getLogradouro());
        sped.getBloco0().getRegistro0100().setNum(contador.getNumero());
        sped.getBloco0().getRegistro0100().setCompl(contador.getComplemento());
        sped.getBloco0().getRegistro0100().setBairro(contador.getBairro());
        sped.getBloco0().getRegistro0100().setFone(contador.getFone());
        sped.getBloco0().getRegistro0100().setFax(contador.getFax());
        sped.getBloco0().getRegistro0100().setEmail(contador.getEmail());
        sped.getBloco0().getRegistro0100().setCodMun(contador.getMunicipioIbge());

        // REGISTRO  0110:  REGIMES  DE  APURAÇÃO  DA  CONTRIBUIÇÃO  SOCIAL  E  DE APROPRIAÇÃO DE CRÉDITO
        sped.getBloco0().getRegistro0110().setCodIncTrib("1");
        sped.getBloco0().getRegistro0110().setIndAproCred("1");
        sped.getBloco0().getRegistro0110().setCodTipoCont("1");

        // REGISTRO  0111:  TABELA  DE  RECEITA  BRUTA  MENSAL  PARA  FINS  DE  RATEIO  DE CRÉDITOS COMUNS
        // REGISTRO  0120:  IDENTIFICAÇÃO  DE  PERÍODOS  DISPENSADOS  DA  ESCRITURAÇÃO FISCAL DIGITAL DAS CONTRIBUIÇÕES – EFD-CONTRIBUIÇÕES
        // { Não Implementados }
        // REGISTRO 0140: TABELA DE CADASTRO DE ESTABELECIMENTO
        sped.getBloco0().getRegistro0140().setCodEst("MATRIZ");
        sped.getBloco0().getRegistro0140().setNome(empresa.getRazaoSocial());
        sped.getBloco0().getRegistro0140().setCnpj(empresa.getCnpj());
        sped.getBloco0().getRegistro0140().setUf(enderecoPrincipal.getUf());
        sped.getBloco0().getRegistro0140().setIe(empresa.getInscricaoEstadual());
        sped.getBloco0().getRegistro0140().setCodMun(empresa.getCodigoIbgeCidade());
        sped.getBloco0().getRegistro0140().setIm(empresa.getInscricaoMunicipal());
        sped.getBloco0().getRegistro0140().setSuframa(empresa.getSuframa());

        // REGISTRO 0145: REGIME DE APURAÇÃO DA CONTRIBUIÇÃO PREVIDENCIÁRIA SOBRE A RECEITA BRUTA
        // { Não Implementado }
        // REGISTRO 0150: TABELA DE CADASTRO DO PARTICIPANTE
        Registro0150 registro0150;
        NfeEmitenteVO emitente;
        for (int i = 0; i < listaNfeEmitente.size(); i++) {
            registro0150 = new Registro0150();
            emitente = listaNfeEmitente.get(i);

            registro0150.setCodPart("F" + emitente.getNfeCabecalho().getFornecedor().getId());
            registro0150.setNome(emitente.getNome());
            registro0150.setCodPais("01058");
            if (emitente.getCpfCnpj().length() == 11) {
                registro0150.setCpf(emitente.getCpfCnpj());
            } else if (emitente.getCpfCnpj().length() == 14) {
                registro0150.setCnpj(emitente.getCpfCnpj());
            }
            registro0150.setCodMun(emitente.getCodigoMunicipio());
            registro0150.setSuframa(String.valueOf(emitente.getSuframa()));
            registro0150.setEndereco(emitente.getLogradouro());
            registro0150.setNum(emitente.getNumero());
            registro0150.setCompl(emitente.getComplemento());
            registro0150.setBairro(emitente.getBairro());

            sped.getBloco0().getRegistro0140().getRegistro0150List().add(registro0150);
        }
        NfeDestinatarioVO destinatario;
        for (int i = 0; i < listaNfeDestinatario.size(); i++) {
            registro0150 = new Registro0150();
            destinatario = listaNfeDestinatario.get(i);

            registro0150.setCodPart("C" + destinatario.getNfeCabecalho().getCliente().getId());
            registro0150.setNome(destinatario.getNome());
            registro0150.setCodPais("01058");
            if (destinatario.getCpfCnpj().length() == 11) {
                registro0150.setCpf(destinatario.getCpfCnpj());
            } else if (destinatario.getCpfCnpj().length() == 14) {
                registro0150.setCnpj(destinatario.getCpfCnpj());
            }
            registro0150.setCodMun(destinatario.getCodigoMunicipio());
            registro0150.setSuframa(String.valueOf(destinatario.getSuframa()));
            registro0150.setEndereco(destinatario.getLogradouro());
            registro0150.setNum(destinatario.getNumero());
            registro0150.setCompl(destinatario.getComplemento());
            registro0150.setBairro(destinatario.getBairro());

            sped.getBloco0().getRegistro0140().getRegistro0150List().add(registro0150);
        }

        //REGISTRO 0200: TABELA DE IDENTIFICAÇÃO DO ITEM (PRODUTO E SERVIÇOS)
        Registro0200 registro0200;
        ProdutoVO produto;
        for (int i = 0; i < listaNfeDetalhe.size(); i++) {
            registro0200 = new Registro0200();
            produto = listaNfeDetalhe.get(i).getProduto();

            registro0200.setCodItem(produto.getId().toString());
            registro0200.setDescrItem(produto.getDescricao());
            registro0200.setCodBarra(produto.getGtin());
            //TEM QUE PREENCHER PARA INFORMAR NO 0205
            registro0200.setCodAntItem("");
            registro0200.setUnidInv(produto.getUnidadeProduto().getId().toString());
            registro0200.setTipoItem(produto.getTipoItemSped());
            registro0200.setCodNcm(produto.getNcm());
            registro0200.setExIpi(produto.getExTipi());
            registro0200.setCodGen(produto.getNcm().substring(0, 2));
            registro0200.setCodLst(produto.getCodigoLst());
            registro0200.setAliqIcms(produto.getAliquotaIcmsPaf());

            if (!listaUnidadeProduto.contains(produto.getUnidadeProduto())) {
                listaUnidadeProduto.add(produto.getUnidadeProduto());
            }

            // REGISTRO 0205: ALTERAÇÃO DO ITEM
            criteria = session.createCriteria(ProdutoAlteracaoItemVO.class);
            criteria.add(Restrictions.eq("produto", produto));
            criteria.add(Restrictions.between("dataInicial", dataInicial.getTime(), dataFinal.getTime()));
            List<ProdutoAlteracaoItemVO> listaProdutoAlteracaoItem = criteria.list();
            Registro0205 registro0205;
            ProdutoAlteracaoItemVO produtoAlteracaoItem;
            for (int j = 0; j < listaProdutoAlteracaoItem.size(); j++) {
                registro0205 = new Registro0205();
                produtoAlteracaoItem = listaProdutoAlteracaoItem.get(j);

                registro0205.setDescrAntItem(produtoAlteracaoItem.getNome());
                registro0205.setDtIni(produtoAlteracaoItem.getDataInicial());
                registro0205.setDtFin(produtoAlteracaoItem.getDataFinal());
                registro0205.setCodAntItem(produtoAlteracaoItem.getCodigo());

                registro0200.getRegistro0205List().add(registro0205);
            }
            sped.getBloco0().getRegistro0140().getRegistro0200List().add(registro0200);
        }

        // REGISTRO 0190: IDENTIFICAÇÃO DAS UNIDADES DE MEDIDA
        Registro0190 registro0190;
        UnidadeProdutoVO unidade;
        for (int i = 0; i < listaUnidadeProduto.size(); i++) {
            registro0190 = new Registro0190();
            unidade = listaUnidadeProduto.get(i);

            registro0190.setUnid(unidade.getId().toString());
            registro0190.setDescr(unidade.getSigla());

            sped.getBloco0().getRegistro0140().getRegistro0190List().add(registro0190);
        }

        // REGISTRO 0300: CADASTRO DE BENS OU COMPONENTES DO ATIVO IMOBILIZADO
        // REGISTRO 0305: INFORMAÇÃO SOBRE A UTILIZAÇÃO DO BEM
        // Implementado a critério do Participante do T2Ti ERP - versão 1.0 não
        // possui controle CIAP
        // REGISTRO 0400: TABELA DE NATUREZA DA OPERAÇÃO/PRESTAÇÃO
        Registro0400 registro0400;
        TributOperacaoFiscalVO operacaoFiscal;
        for (int i = 0; i < listaOperacaoFiscal.size(); i++) {
            registro0400 = new Registro0400();
            operacaoFiscal = listaOperacaoFiscal.get(i);

            registro0400.setCodNat(operacaoFiscal.getId().toString());
            registro0400.setDescrNat(operacaoFiscal.getDescricaoNaNf());
        }

        // REGISTRO 0450: TABELA DE INFORMAÇÃO COMPLEMENTAR DO DOCUMENTO FISCAL
        //{ Não Implementado }
        // REGISTRO 0500: PLANO DE CONTAS CONTÁBEIS
        //{ Não Implementado }
        // REGISTRO 0600: CENTRO DE CUSTOS
        //{ Não Implementado }
    }

    //BLOCO A: DOCUMENTOS FISCAIS - SERVIÇOS (NÃO SUJEITOS AO ICMS)
    public void gerarBlocoA() {
        sped.getBlocoA().getRegistroA001().setIndMov(1); //sem dados
    }

    //BLOCO C: DOCUMENTOS FISCAIS I - MERCADORIAS (ICMS/IPI)
    private void geraBlocoC() throws Exception {
        criteria = session.createCriteria(NfeCabecalhoVO.class);
        criteria.add(Restrictions.between("dataHoraEmissao", dataInicial.getTime(), dataFinal.getTime()));
        List<NfeCabecalhoVO> listaNfeCabecalho = criteria.list();

        // REGISTRO C001: ABERTURA DO BLOCO C
        sped.getBlocoC().getRegistroC001().setIndMov(0);

        // REGISTRO C010: IDENTIFICAÇÃO DO ESTABELECIMENTO
        sped.getBlocoC().getRegistroC010().setCnpj(empresa.getCnpj());
        // 1 – Apuração com base nos registros de consolidaçãodas operações por NF-e (C180 e C190) e por ECF (C490);
        // 2 – Apuração com base no registro individualizado de NF-e (C100 e C170) e de ECF (C400)
        sped.getBlocoC().getRegistroC010().setIndEscri("2");

        // REGISTRO C100
        RegistroC100 registroC100;
        NfeCabecalhoVO nfe;
        for (int i = 0; i < listaNfeCabecalho.size(); i++) {
            registroC100 = new RegistroC100();
            nfe = listaNfeCabecalho.get(i);

            registroC100.setIndOper(String.valueOf(nfe.getTipoOperacao()));
            registroC100.setIndEmit("0"); // 0 - Emissao Propria
            if (nfe.getCliente() != null) {
                registroC100.setCodPart("C" + nfe.getCliente().getId().toString());
            } else if (nfe.getFornecedor() != null) {
                registroC100.setCodPart("F" + nfe.getFornecedor().getId().toString());
            }
            registroC100.setCodMod(nfe.getCodigoModelo());

            /*
             4.1.2- Tabela Situação do Documento
             Código Descrição
             00 Documento regular
             01 Documento regular extemporâneo
             02 Documento cancelado
             03 Documento cancelado extemporâneo
             04 NFe denegada
             05 Nfe – Numeração inutilizada
             06 Documento Fiscal Complementar
             07 Documento Fiscal Complementar extemporâneo.
             08 Documento Fiscal emitido com base em Regime Especial ou Norma Específica
             */
            if (nfe.getStatusNota().equals("5")) {
                registroC100.setCodSit("00");
            } else if (nfe.getStatusNota().equals("6")) {
                registroC100.setCodSit("02");
            }
            registroC100.setSer(nfe.getSerie());
            registroC100.setNumDoc(nfe.getNumero());
            registroC100.setChvNfe(nfe.getChaveAcesso());
            registroC100.setDtDoc(nfe.getDataHoraEmissao());
            registroC100.setDtES(nfe.getDataHoraEntradaSaida());
            registroC100.setVlDoc(nfe.getValorTotal());
            registroC100.setIndPgto(String.valueOf(nfe.getIndicadorFormaPagamento()));
            registroC100.setVlDesc(nfe.getValorDesconto());
            registroC100.setVlAbatNt(BigDecimal.ZERO);
            registroC100.setVlMerc(nfe.getValorTotalProdutos());

            criteria = session.createCriteria(NfeTransporteVO.class);
            criteria.add(Restrictions.eq("nfeCabecalho", listaNfeCabecalho.get(i)));
            NfeTransporteVO transporte = (NfeTransporteVO) criteria.uniqueResult();
            if (transporte != null) {
                registroC100.setIndFrt(String.valueOf(transporte.getModalidadeFrete()));
            }

            registroC100.setVlFrt(nfe.getValorFrete());
            registroC100.setVlSeg(nfe.getValorSeguro());
            registroC100.setVlOutDa(nfe.getValorDespesasAcessorias());
            registroC100.setVlBcIcms(nfe.getBaseCalculoIcms());
            registroC100.setVlIcms(nfe.getValorIcms());
            registroC100.setVlBcIcmsSt(nfe.getBaseCalculoIcmsSt());
            registroC100.setVlIcmsSt(nfe.getValorIcmsSt());
            registroC100.setVlIpi(nfe.getValorIpi());
            registroC100.setVlPis(nfe.getValorPis());
            registroC100.setVlPisSt(BigDecimal.ZERO);
            registroC100.setVlCofins(nfe.getValorCofins());
            registroC100.setVlCofinsSt(BigDecimal.ZERO);

            // REGISTRO C110: COMPLEMENTO  DO  DOCUMENTO  -  INFORMAÇÃO  COMPLEMENTAR  DA NOTA FISCAL (CÓDIGOS 01, 1B, 04 e 55)
            //{ Não Implementado }
            // REGISTRO C111: PROCESSO REFERENCIADO
            //{ Não Implementado }
            // REGISTRO  C120:  COMPLEMENTO  DO  DOCUMENTO  -  OPERAÇÕES DE  IMPORTAÇÃO (CÓDIGO 01)
            //{ Não Implementado }
            // REGISTRO  C170:  COMPLEMENTO  DO  DOCUMENTO  -  ITENS  DO  DOCUMENTO (CÓDIGOS 01, 1B, 04 e 55)
            criteria = session.createCriteria(NfeDetalheVO.class);
            criteria.add(Restrictions.eq("nfeCabecalho", listaNfeCabecalho.get(i)));
            List<NfeDetalheVO> listaNfeDetalhe = criteria.list();
            RegistroC170 registroC170;
            NfeDetalheVO nfeDetalhe;
            for (int j = 0; j < listaNfeDetalhe.size(); j++) {
                registroC170 = new RegistroC170();
                nfeDetalhe = listaNfeDetalhe.get(j);

                registroC170.setNumItem(nfeDetalhe.getNumeroItem().toString());
                registroC170.setCodItem(nfeDetalhe.getGtin());
                registroC170.setDescrCompl(nfeDetalhe.getNomeProduto());
                registroC170.setQtd(nfeDetalhe.getQuantidadeComercial());
                registroC170.setUnid(nfeDetalhe.getProduto().getUnidadeProduto().getId().toString());
                registroC170.setVlItem(nfeDetalhe.getValorTotal());
                registroC170.setVlDesc(nfeDetalhe.getValorDesconto());
                registroC170.setIndMov(0);
                registroC170.setCstIcms(nfeDetalhe.getNfeDetalheImpostoIcms().getCstIcms());
                registroC170.setCfop(nfeDetalhe.getCfop().toString());
                registroC170.setCodNat(nfeDetalhe.getNfeCabecalho().getTributOperacaoFiscal().getId().toString());
                registroC170.setVlBcIcms(nfeDetalhe.getNfeDetalheImpostoIcms().getBaseCalculoIcms());
                registroC170.setAliqIcms(nfeDetalhe.getNfeDetalheImpostoIcms().getAliquotaIcms());
                registroC170.setVlIcms(nfeDetalhe.getNfeDetalheImpostoIcms().getValorIcms());
                registroC170.setVlBcIcmsSt(nfeDetalhe.getNfeDetalheImpostoIcms().getValorBaseCalculoIcmsSt());
                registroC170.setAliqSt(nfeDetalhe.getNfeDetalheImpostoIcms().getAliquotaIcmsSt());
                registroC170.setVlIcmsSt(nfeDetalhe.getNfeDetalheImpostoIcms().getValorIcmsSt());
                registroC170.setIndApur(0);
                registroC170.setCstIpi(nfeDetalhe.getNfeDetalheImpostoIpi().getCstIpi());
                registroC170.setCodEnq(nfeDetalhe.getNfeDetalheImpostoIpi().getEnquadramentoIpi());
                registroC170.setVlBcIpi(nfeDetalhe.getNfeDetalheImpostoIpi().getValorBaseCalculoIpi());
                registroC170.setAliqIpi(nfeDetalhe.getNfeDetalheImpostoIpi().getAliquotaIpi());
                registroC170.setVlIpi(nfeDetalhe.getNfeDetalheImpostoIpi().getValorIpi());
                registroC170.setCstPis(nfeDetalhe.getNfeDetalheImpostoIpi().getCstIpi());
                registroC170.setVlBcPis(nfeDetalhe.getNfeDetalheImpostoPis().getValorBaseCalculoPis());
                registroC170.setAliqPisPerc(nfeDetalhe.getNfeDetalheImpostoPis().getAliquotaPisPercentual());
                registroC170.setQuantBcPis(nfeDetalhe.getNfeDetalheImpostoPis().getQuantidadeVendida());
                registroC170.setAliqPisR(nfeDetalhe.getNfeDetalheImpostoPis().getAliquotaPisReais());
                registroC170.setVlPis(nfeDetalhe.getNfeDetalheImpostoPis().getValorPis());
                registroC170.setCstCofins(nfeDetalhe.getNfeDetalheImpostoCofins().getCstCofins());
                registroC170.setVlBcCofins(nfeDetalhe.getNfeDetalheImpostoCofins().getBaseCalculoCofins());
                registroC170.setAliqCofinsPerc(nfeDetalhe.getNfeDetalheImpostoCofins().getAliquotaCofinsPercentual());
                registroC170.setQuantBcCofins(nfeDetalhe.getNfeDetalheImpostoCofins().getQuantidadeVendida());
                registroC170.setAliqCofinsR(nfeDetalhe.getNfeDetalheImpostoCofins().getAliquotaCofinsReais());
                registroC170.setVlCofins(nfeDetalhe.getNfeDetalheImpostoCofins().getValorCofins());
                registroC170.setCodCta("");

                registroC100.getRegistroC170List().add(registroC170);
            }

            // REGISTRO C175: REGISTRO ANALÍTICO DO DOCUMENTO (CÓDIGO 65)
            // { Será analisado após a implementação da NFC-e }
            // REGISTRO C180: CONSOLIDAÇÃO  DE  NOTAS  FISCAIS  ELETRÔNICAS  EMITIDAS PELA PESSOA JURÍDICA (CÓDIGOS 55 e 65) – OPERAÇÕES DE VENDAS
            // REGISTRO C181: DETALHAMENTO DA CONSOLIDAÇÃO – OPERAÇÕES DE VENDAS – PIS/PASEP
            // REGISTRO C185: DETALHAMENTO DA CONSOLIDAÇÃO – OPERAÇÕES DE VENDAS – COFINS
            // REGISTRO C188: PROCESSO REFERENCIADO
            // { Não Implementados }
            // REGISTRO C190: CONSOLIDAÇÃO DE NOTAS FISCAIS ELETRÔNICAS (CÓDIGO 55) – OPERAÇÕES  DE  AQUISIÇÃO  COM  DIREITO  A  CRÉDITO,  E  OPERAÇÕES  DE DEVOLUÇÃO DE COMPRAS E VENDAS.
            // REGISTRO C191:  DETALHAMENTO  DA  CONSOLIDAÇÃO  –  OPERAÇÕES  DE AQUISIÇÃO  COM  DIREITO  A  CRÉDITO,  E  OPERAÇÕES  DE  DEVOLUÇÃO  DE COMPRAS E VENDAS – PIS/PASEP
            // REGISTRO C195:  DETALHAMENTO  DA  CONSOLIDAÇÃO  -  OPERAÇÕES  DE AQUISIÇÃO  COM  DIREITO  A  CRÉDITO,  E  OPERAÇÕES  DE  DEVOLUÇÃO  DE COMPRAS E VENDAS – COFINS
            // REGISTRO C198: PROCESSO REFERENCIADO
            // REGISTRO C199: COMPLEMENTO DO DOCUMENTO - OPERAÇÕESDE IMPORTAÇÃO (CÓDIGO 55)
            // { Não Implementados }
        }

        // REGISTRO  C380:  NOTA  FISCAL  DE  VENDA  A  CONSUMIDOR  (CÓDIGO  02)  - CONSOLIDAÇÃO DE DOCUMENTOS EMITIDOS.
        criteria = session.createCriteria(ViewSpedC300VoId.class);
        criteria.add(Restrictions.between("viewSpedC300VO.dataEmissao", dataInicial.getTime(), dataFinal.getTime()));
        List<ViewSpedC300VoId> listaC300 = criteria.list();
        ViewSpedC300VO viewC300;
        RegistroC380 registroC380;
        for (int i = 0; i < listaC300.size(); i++) {
            viewC300 = listaC300.get(i).getViewSpedC300VO();
            registroC380 = new RegistroC380();
            registroC380.setCodMod("2");
            registroC380.setNumDocIni(viewC300.getSerie()); // Como pegar o número inicial?
            registroC380.setNumDocFin(viewC300.getSerie()); // Como pegar o número Final?
            registroC380.setDtDocIni(viewC300.getDataEmissao()); // Como pegar a data inicial?
            registroC380.setDtDocFin(viewC300.getDataEmissao()); // Como pegar a data Final?
            registroC380.setVlDoc(viewC300.getSomaTotalNf());
            registroC380.setVlDocCanc(viewC300.getSomaTotalNf()); // Como pegar os valores cancelados?

            // REGISTRO C381: DETALHAMENTO DA CONSOLIDAÇÃO – PIS/P ASEP
            // REGISTRO C385: DETALHAMENTO DA CONSOLIDAÇÃO – COFINS
            // { Exercício: implementar }
            // REGISTRO C395: NOTAS FISCAIS DE VENDA A CONSUMIDOR(CÓDIGOS 02, 2D, 2E e 59) – AQUISIÇÕES/ENTRADAS COM CRÉDITO.
            // REGISTRO C396:  ITENS  DO  DOCUMENTO  (CÓDIGOS  02,  2D,  2E  e  59)  – AQUISIÇÕES/ENTRADAS COM CRÉDITO
            // { Não Implementados }
        }

        // REGISTRO C400: EQUIPAMENTO ECF (CÓDIGO 02, 2D e 60).
        criteria = session.createCriteria(EcfImpressoraVO.class);
        List<EcfImpressoraVO> listaEcf = criteria.list();
        RegistroC400 registroC400;
        EcfImpressoraVO ecf;
        for (int i = 0; i < listaEcf.size(); i++) {
            registroC400 = new RegistroC400();
            ecf = listaEcf.get(i);

            registroC400.setCodMod(ecf.getModeloDocumentoFiscal());
            registroC400.setEcfMod(ecf.getModelo());
            registroC400.setEcfFab(ecf.getSerie());
            registroC400.setEcfCx(ecf.getNumero().toString());

            // REGISTRO C405: REDUÇÃO Z (CÓDIGO 02, 2D e 60).
            // verifica se existe movimento no periodo para aquele ECF
            criteria = session.createCriteria(EcfR02VO.class);
            criteria.add(Restrictions.eq("idImpressora", ecf.getId()));
            criteria.add(Restrictions.between("dataEmissao", dataInicial.getTime(), dataFinal.getTime()));
            List<EcfR02VO> listaR02 = criteria.list();
            RegistroC405 registroC405;
            EcfR02VO r02;
            for (int j = 0; j < listaR02.size(); j++) {
                registroC405 = new RegistroC405();
                r02 = listaR02.get(j);

                registroC405.setDtDoc(r02.getDataMovimento());
                registroC405.setCro(r02.getCro());
                registroC405.setCrz(r02.getCrz());
                registroC405.setNumCooFin(r02.getCoo());
                registroC405.setGtFin(r02.getGrandeTotal());
                registroC405.setVlBrt(r02.getVendaBruta());

                // REGISTRO C481: RESUMO DIÁRIO DE DOCUMENTOS EMITIDOSPOR ECF – PIS/PASEP (CÓDIGOS 02 e 2D).
                // REGISTRO C485: RESUMO DIÁRIO DE DOCUMENTOS EMITIDOSPOR ECF – COFINS (CÓDIGOS 02 e 2D)
                // {Exercício: Implementar}                           
                registroC400.getRegistroC405List().add(registroC405);
            }

            // REGISTRO C489: PROCESSO REFERENCIADO
            // { Não Implementado }
            // REGISTRO C490: CONSOLIDAÇÃO DE DOCUMENTOS EMITIDOS  POR ECF (CÓDIGOS 02, 2D, 59 e 60)
            // REGISTRO C491:  DETALHAMENTO  DA CONSOLIDAÇÃO DE DOCUMENTOS EMITIDOS POR ECF (CÓDIGOS 02, 2D e 59) – PIS/PASEP
            // REGISTRO C495:  DETALHAMENTO  DA CONSOLIDAÇÃO DE DOCUMENTOS EMITIDOS POR ECF (CÓDIGOS 02, 2D e 59) – COFINS
            // REGISTRO C499: PROCESSO REFERENCIADO
            // { Não Implementados }
        }

        // REGISTRO C500:  NOTA  FISCAL/CONTA  DE  ENERGIA  ELÉTRICA  (CÓDIGO  06),  NOTA FISCAL/CONTA  DE  FORNECIMENTO  D'ÁGUA  CANALIZADA  (CÓDIGO  29)  E  NOTA  FISCAL CONSUMO  FORNECIMENTO  DE  GÁS  (CÓDIGO  28)  E  NF-e  (CÓDIGO  55)–  DOCUMENTOS  DE ENTRADA/AQUISIÇÃO COM CRÉDITO
        // REGISTRO C501: COMPLEMENTO DA OPERAÇÃO (CÓDIGOS 06,28 e 29) – PIS/PASEP
        // REGISTRO C505: COMPLEMENTO DA OPERAÇÃO (CÓDIGOS 06,28 e 29) – COFINS
        // REGISTRO C509: PROCESSO REFERENCIADO
        // REGISTRO C600:  CONSOLIDAÇÃO  DIÁRIA  DE  NOTAS  FISCAIS/CONTAS  EMITIDAS  DE ENERGIA  ELÉTRICA  (CÓDIGO  06),  NOTA  FISCAL/CONTA  DE  FORNECIMENTO  D'ÁGUA CANALIZADA (CÓDIGO 29) E NOTA FISCAL/CONTA DE FORNECIMENTO DE GÁS (CÓDIGO 28) (EMPRESAS OBRIGADAS OU NÃO OBRIGADAS AO CONVENIO ICMS 115/03) – DOCUMENTOS DE SAÍDA
        // REGISTRO C601: COMPLEMENTO DA CONSOLIDAÇÃO DIÁRIA (CÓDIGOS 06, 28 e 29) – DOCUMENTOS DE SAÍDAS - PIS/PASEP
        // REGISTRO C605: COMPLEMENTO DA CONSOLIDAÇÃO DIÁRIA (CÓDIGOS 06, 28 e 29) – DOCUMENTOS DE SAÍDAS – COFINS
        // REGISTRO C609: PROCESSO REFERENCIADO
        // { Não Implementados }
        // REGISTRO C800: CUPOM FISCAL ELETRÔNICO (CÓDIGO 59)
        // REGISTRO C810:  DETALHAMENTO  DO  CUPOM  FISCAL  ELETRÔNICO  (CÓDIGO  59)  – PIS/PASEP E COFINS
        // REGISTRO C820:  DETALHAMENTO  DO  CUPOM  FISCAL  ELETRÔNICO  (CÓDIGO  59)  – PIS/PASEP E COFINS APURADO POR UNIDADE DE MEDIDA DEPRODUTO
        // REGISTRO C830: PROCESSO RERENCIADO
        // REGISTRO C860: IDENTIFICAÇÃO DO EQUIPAMENTO SAT-CF-E
        // REGISTRO C870:  RESUMO  DIÁRIO  DE  DOCUMENTOS  EMITIDOS POR  EQUIPAMENTO SAT-CF-E (CÓDIGO 59) – PIS/PASEP E COFINS
        // REGISTRO C880:  RESUMO  DIÁRIO  DE  DOCUMENTOS  EMITIDOS POR  EQUIPAMENTO SAT-CF-E (CÓDIGO 59) – PIS/PASEP E COFINS APURADO POR UNIDADE DE MEDIDA DE PRODUTO
        // REGISTRO C890: PROCESSO REFERENCIADO
        // (* Serão analisados após implementação do SAT *)
    }

    //BLOCO D: DOCUMENTOS FISCAIS II - SERVIÇOS (ICMS)
    public void gerarBlocoD() {
        sped.getBlocoD().getRegistroD001().setIndMov(1); //sem dados
    }

    //BLOCO F: DEMAIS DOCUMENTOS E OPERAÇÕES
    public void gerarBlocoF() {
        sped.getBlocoF().getRegistroF001().setIndMov(1); //sem dados
    }

    //BLOCO I: OPERAÇÕES DAS INSTITUIÇÕES FINANCEIRAS, SEGURADORAS, ENTIDADES DE  PREVIDENCIA  PRIVADA,  OPERADORAS  DE  PLANOS  DE  ASSISTÊNCIA  À SAÚDE E DEMAIS PESSOAS JURÍDICAS REFERIDAS NOS §§ 6º, 8ºE 9ºDO ART. 3ºDA LEI nº9.718/98'}
    public void gerarBlocoI() {
        sped.getBlocoI().getRegistroI001().setIndMov(1); //sem dados
    }

    //BLOCO M: APURAÇÃO  DA  CONTRIBUIÇÃO  E  CRÉDITO  DO  PIS/PASEP  E DA COFINS'}
    public void gerarBlocoM() {
        sped.getBlocoM().getRegistroM001().setIndMov(1); //sem dados
    }

    //BLOCO 1: OUTRAS INFORMAÇÕES
    public void gerarBloco1() {
        sped.getBloco1().getRegistro1001().setIndMov(1); //sem dados
    }

}
