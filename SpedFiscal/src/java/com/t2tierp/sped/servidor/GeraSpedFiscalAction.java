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
import com.t2tierp.cadastros.java.UnidadeConversaoVO;
import com.t2tierp.cadastros.java.UnidadeProdutoVO;
import com.t2tierp.escritafiscal.java.FiscalApuracaoIcmsVO;
import com.t2tierp.ged.java.ArquivoVO;
import com.t2tierp.nfe.java.NfeCabecalhoVO;
import com.t2tierp.nfe.java.NfeCupomFiscalReferenciadoVO;
import com.t2tierp.nfe.java.NfeDestinatarioVO;
import com.t2tierp.nfe.java.NfeDetalheVO;
import com.t2tierp.nfe.java.NfeEmitenteVO;
import com.t2tierp.nfe.java.NfeTransporteVO;
import com.t2tierp.padrao.java.Biblioteca;
import com.t2tierp.padrao.servidor.HibernateUtil;
import com.t2tierp.pafecf.java.EcfImpressoraVO;
import com.t2tierp.pafecf.java.EcfNotaFiscalCabecalhoVO;
import com.t2tierp.pafecf.java.EcfR02VO;
import com.t2tierp.pafecf.java.EcfR03VO;
import com.t2tierp.pafecf.java.EcfVendaCabecalhoVO;
import com.t2tierp.pafecf.java.EcfVendaDetalheVO;
import com.t2tierp.sped.SpedFiscal;
import com.t2tierp.sped.fiscal.bloco0.Registro0150;
import com.t2tierp.sped.fiscal.bloco0.Registro0190;
import com.t2tierp.sped.fiscal.bloco0.Registro0200;
import com.t2tierp.sped.fiscal.bloco0.Registro0205;
import com.t2tierp.sped.fiscal.bloco0.Registro0220;
import com.t2tierp.sped.fiscal.bloco0.Registro0400;
import com.t2tierp.sped.fiscal.bloco1.Registro1010;
import com.t2tierp.sped.fiscal.blococ.RegistroC100;
import com.t2tierp.sped.fiscal.blococ.RegistroC114;
import com.t2tierp.sped.fiscal.blococ.RegistroC170;
import com.t2tierp.sped.fiscal.blococ.RegistroC190;
import com.t2tierp.sped.fiscal.blococ.RegistroC300;
import com.t2tierp.sped.fiscal.blococ.RegistroC310;
import com.t2tierp.sped.fiscal.blococ.RegistroC320;
import com.t2tierp.sped.fiscal.blococ.RegistroC321;
import com.t2tierp.sped.fiscal.blococ.RegistroC350;
import com.t2tierp.sped.fiscal.blococ.RegistroC370;
import com.t2tierp.sped.fiscal.blococ.RegistroC390;
import com.t2tierp.sped.fiscal.blococ.RegistroC400;
import com.t2tierp.sped.fiscal.blococ.RegistroC405;
import com.t2tierp.sped.fiscal.blococ.RegistroC420;
import com.t2tierp.sped.fiscal.blococ.RegistroC425;
import com.t2tierp.sped.fiscal.blococ.RegistroC460;
import com.t2tierp.sped.fiscal.blococ.RegistroC470;
import com.t2tierp.sped.fiscal.blococ.RegistroC490;
import com.t2tierp.sped.fiscal.blocoe.RegistroE100;
import com.t2tierp.sped.fiscal.blocoe.RegistroE110;
import com.t2tierp.sped.fiscal.blocoe.RegistroE116;
import com.t2tierp.sped.fiscal.blocoh.RegistroH005;
import com.t2tierp.sped.fiscal.blocoh.RegistroH010;
import com.t2tierp.sped.java.ViewSpedC321VO;
import com.t2tierp.sped.java.ViewSpedC370VO;
import com.t2tierp.sped.java.ViewSpedC390VO;
import com.t2tierp.sped.java.ViewSpedC490VO;
import com.t2tierp.sped.java.ViewSpedC190VO;
import com.t2tierp.sped.java.ViewSpedC190VoId;
import com.t2tierp.sped.java.ViewSpedC300VO;
import com.t2tierp.sped.java.ViewSpedC300VoId;
import com.t2tierp.sped.java.ViewSpedC321VoId;
import com.t2tierp.sped.java.ViewSpedC370VoId;
import com.t2tierp.sped.java.ViewSpedC390VoId;
import com.t2tierp.sped.java.ViewSpedC425VO;
import com.t2tierp.sped.java.ViewSpedC425VoId;
import com.t2tierp.sped.java.ViewSpedC490VoId;
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
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.openswing.swing.message.receive.java.ErrorResponse;
import org.openswing.swing.message.receive.java.Response;
import org.openswing.swing.message.receive.java.VOResponse;
import org.openswing.swing.message.send.java.GridParams;
import org.openswing.swing.server.Action;
import org.openswing.swing.server.UserSessionParameters;

public class GeraSpedFiscalAction implements Action {

    private SpedFiscal sped;
    private Calendar dataInicial;
    private Calendar dataFinal;
    private String versao;
    private String finalidade;
    private String perfil;
    private EmpresaVO empresa;
    private EmpresaEnderecoVO enderecoPrincipal;
    private Integer inventario;
    private ContadorVO contador;
    private Session session;
    private Criteria criteria;

    public GeraSpedFiscalAction() {
    }

    public String getRequestName() {
        return "geraSpedFiscalAction";
    }

    public Response executeCommand(Object inputPar, UserSessionParameters userSessionPars, HttpServletRequest request, HttpServletResponse response, HttpSession userSession, ServletContext context) {
        GridParams pars = (GridParams) inputPar;
        try {
            this.session = HibernateUtil.getSessionFactory().openSession();
            this.sped = new SpedFiscal();
            this.dataInicial = Calendar.getInstance();
            this.dataInicial.setTime((Date) pars.getOtherGridParams().get("dataInicial"));
            this.dataFinal = Calendar.getInstance();
            this.dataFinal.setTime((Date) pars.getOtherGridParams().get("dataFinal"));
            this.versao = (String) pars.getOtherGridParams().get("versao");
            this.finalidade = (String) pars.getOtherGridParams().get("finalidade");
            this.perfil = (String) pars.getOtherGridParams().get("perfil");
            this.inventario = (Integer) pars.getOtherGridParams().get("inventario");
            this.contador = (ContadorVO) pars.getOtherGridParams().get("contador");
            this.contador = (ContadorVO) Biblioteca.nullToEmpty(contador);
            this.empresa = (EmpresaVO) pars.getOtherGridParams().get("empresa");
            this.empresa = (EmpresaVO) Biblioteca.nullToEmpty(empresa);
            this.enderecoPrincipal = (EmpresaEnderecoVO) pars.getOtherGridParams().get("enderecoPrincipalEmpresa");
            this.enderecoPrincipal = (EmpresaEnderecoVO) Biblioteca.nullToEmpty(enderecoPrincipal);

            geraBloco0();
            geraBlocoC();
            // BLOCO D: DOCUMENTOS FISCAIS II - SERVIÇOS (ICMS).
            // Bloco de registros dos dados relativos à emissão ou ao recebimento de documentos fiscais que acobertam as prestações de serviços de comunicação, transporte intermunicipal e interestadual.
            // Implementado a critério do Participante do T2Ti ERP
            geraBlocoE();
            // BLOCO G – CONTROLE DO CRÉDITO DE ICMS DO ATIVO PERMANENTE CIAP
            // Implementado a critério do Participante do T2Ti ERP
            if (inventario > 0) {
                geraBlocoH();
            }
            geraBloco1();

            File file = File.createTempFile("sped_fiscal", ".txt");
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
        sped.getBloco0().getRegistro0000().setCodFin(finalidade);
        sped.getBloco0().getRegistro0000().setIndPerfil(perfil);
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

        // REGISTRO 0005: DADOS COMPLEMENTARES DA ENTIDADE
        sped.getBloco0().getRegistro0005().setFantasia(empresa.getNomeFantasia());
        sped.getBloco0().getRegistro0005().setCep(enderecoPrincipal.getCep());
        sped.getBloco0().getRegistro0005().setEndereco(enderecoPrincipal.getLogradouro());
        sped.getBloco0().getRegistro0005().setNum(enderecoPrincipal.getNumero());
        sped.getBloco0().getRegistro0005().setCompl(enderecoPrincipal.getComplemento());
        sped.getBloco0().getRegistro0005().setBairro(enderecoPrincipal.getBairro());
        sped.getBloco0().getRegistro0005().setFone(enderecoPrincipal.getFone());
        sped.getBloco0().getRegistro0005().setFax(enderecoPrincipal.getFone());
        sped.getBloco0().getRegistro0005().setEmail(empresa.getEmail());

        // REGISTRO 0015: DADOS DO CONTRIBUINTE SUBSTITUTO
        // Implementado a critério do Participante do T2Ti ERP
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

        // REGISTRO 0150: TABELA DE CADASTRO DO PARTICIPANTE
        /*
         Deverão ser informados somente os  participantes que tiveram movimentação no período de referência da EFD,
         não sendo necessário informar os CNPJs e CPFs citados nos registros C350 e C460
         [adquirentes nas operações acobertadas com nota fiscal de venda a consumidor ou cupom fiscal]
         */
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

            sped.getBloco0().getListaRegistro0150().add(registro0150);
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

            sped.getBloco0().getListaRegistro0150().add(registro0150);
        }

        // REGISTRO 0175: ALTERAÇÃO DA TABELA DE CADASTRO DE PARTICIPANTE
        //  Pegar os dados de PESSOA_ALTERACAO para gerar o registro 0175
        // registro 0200
        Registro0200 registro0200;
        Registro0220 registro0220;
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

            // REGISTRO 0206: CÓDIGO DE PRODUTO CONFORME TABELA PUBLICADA PELA ANP (COMBUSTÍVEIS)
            // Implementado a critério do Participante do T2Ti ERP
            // REGISTRO 0210: CONSUMO ESPECÍFICO PADRONIZADO
            // Implementado a critério do Participante do T2Ti ERP
            // REGISTRO 0220: FATORES DE CONVERSÃO DE UNIDADES
            criteria = session.createCriteria(UnidadeConversaoVO.class);
            criteria.add(Restrictions.eq("produto", produto));
            criteria.add(Restrictions.eq("unidadeProduto", produto.getUnidadeProduto()));
            UnidadeConversaoVO unidadeConversao = (UnidadeConversaoVO) criteria.uniqueResult();
            if (unidadeConversao != null) {
                registro0220 = new Registro0220();
                registro0220.setUnidConv(unidadeConversao.getProduto().getUnidadeProduto().getId().toString());
                registro0220.setFatConv(unidadeConversao.getFatorConversao());

                registro0200.getRegistro0220List().add(registro0220);
            }
        }

        // REGISTRO 0190: IDENTIFICAÇÃO DAS UNIDADES DE MEDIDA
        Registro0190 registro0190;
        UnidadeProdutoVO unidade;
        for (int i = 0; i < listaUnidadeProduto.size(); i++) {
            registro0190 = new Registro0190();
            unidade = listaUnidadeProduto.get(i);

            registro0190.setUnid(unidade.getId().toString());
            registro0190.setDescr(unidade.getSigla());

            sped.getBloco0().getListaRegistro0190().add(registro0190);
        }

        // REGISTRO 0300: CADASTRO DE BENS OU COMPONENTES DO ATIVO IMOBILIZADO
        // REGISTRO 0305: INFORMAÇÃO SOBRE A UTILIZAÇÃO DO BEM
        // Implementado a critério do Participante do T2Ti ERP - versão 1.0 não possui controle CIAP
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
        // Implementado a critério do Participante do T2Ti ERP
        // REGISTRO 0460: TABELA DE OBSERVAÇÕES DO LANÇAMENTO FISCAL
        // Implementado a critério do Participante do T2Ti ERP
        // REGISTRO 0500: PLANO DE CONTAS CONTÁBEIS
        // Implementado a critério do Participante do T2Ti ERP
        // REGISTRO 0600: CENTRO DE CUSTOS
        // Implementado a critério do Participante do T2Ti ERP
    }

    //BLOCO C: DOCUMENTOS FISCAIS I - MERCADORIAS (ICMS/IPI)
    private void geraBlocoC() throws Exception {
        criteria = session.createCriteria(NfeCabecalhoVO.class);
        criteria.add(Restrictions.between("dataHoraEmissao", dataInicial.getTime(), dataFinal.getTime()));
        List<NfeCabecalhoVO> listaNfeCabecalho = criteria.list();

        criteria = session.createCriteria(EcfNotaFiscalCabecalhoVO.class);
        criteria.add(Restrictions.between("dataEmissao", dataInicial.getTime(), dataFinal.getTime()));
        List<EcfNotaFiscalCabecalhoVO> listaNf2Cabecalho = criteria.list();

        criteria = session.createCriteria(EcfNotaFiscalCabecalhoVO.class);
        criteria.add(Restrictions.eq("cancelada", "S"));
        criteria.add(Restrictions.between("dataEmissao", dataInicial.getTime(), dataFinal.getTime()));
        List<EcfNotaFiscalCabecalhoVO> listaNf2Cancelada = criteria.list();

        // PERFIL A
        if (perfil.equals("A")) {
            // REGISTRO C100: NOTA FISCAL (CÓDIGO 01), NOTA FISCAL AVULSA (CÓDIGO 1B), NOTA FISCAL DE PRODUTOR (CÓDIGO 04), NF-e (CÓDIGO 55) e NFC-e (CÓDIGO 65)
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

                // REGISTRO C105: OPERAÇÕES COM ICMS ST RECOLHIDO PARA UF DIVERSA DO DESTINATÁRIO DO DOCUMENTO FISCAL (CÓDIGO 55).
                // Implementado a critério do Participante do T2Ti ERP
                // REGISTRO C110: INFORMAÇÃO COMPLEMENTAR DA NOTA FISCAL (CÓDIGO 01, 1B, 04 e 55).
                // Implementado a critério do Participante do T2Ti ERP
                // REGISTRO C111: PROCESSO REFERENCIADO
                // Implementado a critério do Participante do T2Ti ERP
                // REGISTRO C112: DOCUMENTO DE ARRECADAÇÃO REFERENCIADO.
                // Implementado a critério do Participante do T2Ti ERP
                // REGISTRO C113: DOCUMENTO FISCAL REFERENCIADO.
                // Implementado a critério do Participante do T2Ti ERP
                // REGISTRO C114: CUPOM FISCAL REFERENCIADO
                criteria = session.createCriteria(NfeTransporteVO.class);
                criteria.add(Restrictions.eq("nfeCabecalho", listaNfeCabecalho.get(i)));
                List<NfeCupomFiscalReferenciadoVO> listaCupomFiscal = criteria.list();
                RegistroC114 registroC114;
                NfeCupomFiscalReferenciadoVO cupomFiscal;
                for (int j = 0; j < listaCupomFiscal.size(); j++) {
                    registroC114 = new RegistroC114();
                    cupomFiscal = listaCupomFiscal.get(j);

                    registroC114.setCodMod(cupomFiscal.getModeloDocumentoFiscal());
                    registroC114.setEcfFab(cupomFiscal.getNumeroSerieEcf());
                    registroC114.setEcfCx(cupomFiscal.getNumeroCaixa().toString());
                    registroC114.setNumDoc(cupomFiscal.getCoo().toString());
                    registroC114.setDtDoc(cupomFiscal.getDataEmissaoCupom());

                    sped.getBlocoC().getListaRegistroC114().add(registroC114);
                }

                // REGISTRO C115: LOCAL DA COLETA E/OU ENTREGA (CÓDIGO 01, 1B E 04).
                // Implementado a critério do Participante do T2Ti ERP
                // REGISTRO C116: CUPOM FISCAL ELETRÔNICO REFERENCIADO
                // Implementado a critério do Participante do T2Ti ERP
                // REGISTRO C120: COMPLEMENTO DE DOCUMENTO - OPERAÇÕES DE IMPORTAÇÃO (CÓDIGOS 01 e 55).
                // Implementado a critério do Participante do T2Ti ERP
                // REGISTRO C130: ISSQN, IRRF E PREVIDÊNCIA SOCIAL.
                // Implementado a critério do Participante do T2Ti ERP
                // REGISTRO C140: FATURA (CÓDIGO 01)
                // Implementado a critério do Participante do T2Ti ERP
                // REGISTRO C141: VENCIMENTO DA FATURA (CÓDIGO 01).
                // Implementado a critério do Participante do T2Ti ERP
                // REGISTRO C160: VOLUMES TRANSPORTADOS (CÓDIGO 01 E 04) - EXCETO COMBUSTÍVEIS.
                // Implementado a critério do Participante do T2Ti ERP
                // REGISTRO C165: OPERAÇÕES COM COMBUSTÍVEIS (CÓDIGO 01).
                // Implementado a critério do Participante do T2Ti ERP
                // REGISTRO C170: ITENS DO DOCUMENTO (CÓDIGO 01, 1B, 04 e 55).
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

                // REGISTRO C171: ARMAZENAMENTO DE COMBUSTIVEIS (código 01, 55).
                // Implementado a critério do Participante do T2Ti ERP
                // REGISTRO C172: OPERAÇÕES COM ISSQN (CÓDIGO 01)
                // Implementado a critério do Participante do T2Ti ERP
                // REGISTRO C173: OPERAÇÕES COM MEDICAMENTOS (CÓDIGO 01 e 55).
                // Implementado a critério do Participante do T2Ti ERP
                // REGISTRO C174: OPERAÇÕES COM ARMAS DE FOGO (CÓDIGO 01).
                // Implementado a critério do Participante do T2Ti ERP
                // REGISTRO C175: OPERAÇÕES COM VEÍCULOS NOVOS (CÓDIGO 01 e 55).
                // Implementado a critério do Participante do T2Ti ERP
                // REGISTRO C176: RESSARCIMENTO DE ICMS EM OPERAÇÕES COM SUBSTITUIÇÃO TRIBUTÁRIA (CÓDIGO 01, 55).
                // Implementado a critério do Participante do T2Ti ERP
                // REGISTRO C177: OPERAÇÕES COM PRODUTOS SUJEITOS A SELO DE CONTROLE IPI.
                // Implementado a critério do Participante do T2Ti ERP
                // REGISTRO C178: OPERAÇÕES COM PRODUTOS SUJEITOS À TRIBUTAÇÀO DE IPI POR UNIDADE OU QUANTIDADE DE PRODUTO.
                // Implementado a critério do Participante do T2Ti ERP
                // REGISTRO C179: INFORMAÇÕES COMPLEMENTARES ST (CÓDIGO 01).
                // Implementado a critério do Participante do T2Ti ERP
                // REGISTRO C190: REGISTRO ANALÍTICO DO DOCUMENTO (CÓDIGO 01, 1B, 04 ,55 e 65).
                criteria = session.createCriteria(ViewSpedC190VoId.class);
                criteria.add(Restrictions.eq("viewSpedC190VO.id", listaNfeCabecalho.get(i).getId()));
                List<ViewSpedC190VoId> listaNfeAnalitico = criteria.list();
                RegistroC190 registroC190;
                ViewSpedC190VO spedC190;
                for (int j = 0; j < listaNfeAnalitico.size(); j++) {
                    registroC190 = new RegistroC190();
                    spedC190 = listaNfeAnalitico.get(j).getViewSpedC190VO();

                    registroC190.setCstIcms(spedC190.getCstIcms());
                    registroC190.setCfop(spedC190.getCfop().toString());
                    registroC190.setAliqIcms(spedC190.getAliquotaIcms());
                    registroC190.setVlOpr(spedC190.getSomaValorOperacao());
                    registroC190.setVlBcIcms(spedC190.getSomaBaseCalculoIcms());
                    registroC190.setVlIcms(spedC190.getSomaValorIcms());
                    registroC190.setVlBcIcmsSt(spedC190.getSomaBaseCalculoIcmsSt());
                    registroC190.setVlIcmsSt(spedC190.getSomaValorIcmsSt());
                    registroC190.setVlRedBc(spedC190.getSomaVlRedBc());
                    registroC190.setVlIpi(spedC190.getSomaValorIpi());
                    registroC190.setCodObs("");

                    registroC100.getRegistroC190List().add(registroC190);
                }

                // REGISTRO C195: OBSERVAÇOES DO LANÇAMENTO FISCAL (CÓDIGO 01, 1B E 55)
                // Implementado a critério do Participante do T2Ti ERP
                // REGISTRO C197: OUTRAS OBRIGAÇÕES TRIBUTÁRIAS, AJUSTES E INFORMAÇÕES DE VALORES PROVENIENTES DE DOCUMENTO FISCAL.
                // Implementado a critério do Participante do T2Ti ERP
            }

            // REGISTRO C350: NOTA FISCAL DE VENDA A CONSUMIDOR (CÓDIGO 02)
            RegistroC350 registroC350;
            EcfNotaFiscalCabecalhoVO notaFiscal;
            for (int i = 0; i < listaNf2Cabecalho.size(); i++) {
                registroC350 = new RegistroC350();
                notaFiscal = listaNf2Cabecalho.get(i);

                registroC350.setSer(notaFiscal.getSerie());
                registroC350.setSubSer(notaFiscal.getSubserie());
                registroC350.setNumDoc(notaFiscal.getNumero());
                registroC350.setDtDoc(notaFiscal.getDataEmissao());
                registroC350.setCnpjCpf(notaFiscal.getCpfCnpjCliente());
                registroC350.setVlMerc(notaFiscal.getTotalProdutos());
                registroC350.setVlDoc(notaFiscal.getTotalNf());
                registroC350.setVlDesc(notaFiscal.getDesconto());
                registroC350.setVlPis(notaFiscal.getPis());
                registroC350.setVlCofins(notaFiscal.getCofins());
                registroC350.setCodCta("");

                sped.getBlocoC().getListaRegistroC350().add(registroC350);

                // REGISTRO C370: ITENS DO DOCUMENTO (CÓDIGO 02)
                criteria = session.createCriteria(ViewSpedC370VoId.class);
                criteria.add(Restrictions.eq("viewC370Vo.idNfCabecalho", listaNf2Cabecalho.get(i).getId()));
                List<ViewSpedC370VoId> listaC370 = criteria.list();
                RegistroC370 registroC370;
                ViewSpedC370VO viewC370;
                for (int j = 0; j < listaC370.size(); j++) {
                    registroC370 = new RegistroC370();
                    viewC370 = listaC370.get(j).getViewC370Vo();

                    registroC370.setNumItem(viewC370.getItem().toString());
                    registroC370.setCodItem(viewC370.getIdProduto().toString());
                    registroC370.setQtd(viewC370.getQuantidade());
                    registroC370.setUnid(viewC370.getIdUnidadeProduto().toString());
                    registroC370.setVlItem(viewC370.getValorTotal());
                    registroC370.setVlDesc(viewC370.getDesconto());

                    registroC350.getRegistroC370List().add(registroC370);
                }

                // REGISTRO C390: REGISTRO ANALÍTICO DAS NOTAS FISCAIS DE VENDA A CONSUMIDOR (CÓDIGO 02)
                criteria = session.createCriteria(ViewSpedC390VoId.class);
                criteria.add(Restrictions.between("viewC390Vo.dataEmissao", dataInicial.getTime(), dataFinal.getTime()));
                List<ViewSpedC390VoId> listaC390 = criteria.list();
                RegistroC390 registroC390;
                ViewSpedC390VO viewC390;
                for (int j = 0; j < listaC390.size(); j++) {
                    registroC390 = new RegistroC390();
                    viewC390 = listaC390.get(j).getViewC390Vo();

                    registroC390.setCstIcms(viewC390.getCst());
                    registroC390.setCfop(viewC390.getCfop().toString());
                    registroC390.setAliqIcms(viewC390.getTaxaIcms());
                    registroC390.setVlOpr(viewC390.getSomaItem());
                    registroC390.setVlBcIcms(viewC390.getSomaBaseIcms());
                    registroC390.setVlIcms(viewC390.getSomaIcms());
                    registroC390.setVlRedBc(viewC390.getSomaIcmsOutras());

                    registroC350.getRegistroC390List().add(registroC390);
                }
            }
        }

        // PERFIL B
        if (perfil.equals("B")) {
            // REGISTRO C300: RESUMO DIÁRIO DAS NOTAS FISCAIS DE VENDA A CONSUMIDOR (CÓDIGO 02)
            criteria = session.createCriteria(ViewSpedC300VoId.class);
            criteria.add(Restrictions.between("viewSpedC300VO.dataEmissao", dataInicial.getTime(), dataFinal.getTime()));
            List<ViewSpedC300VoId> listaC300 = criteria.list();
            RegistroC300 registroC300;
            RegistroC310 registroC310;
            ViewSpedC300VO viewC300;
            for (int i = 0; i < listaC300.size(); i++) {
                viewC300 = listaC300.get(i).getViewSpedC300VO();

                registroC300 = new RegistroC300();

                registroC300.setCodMod("02");
                registroC300.setSer(viewC300.getSerie());
                registroC300.setSub(viewC300.getSubserie());

                criteria = session.createCriteria(EcfNotaFiscalCabecalhoVO.class);
                criteria.add(Restrictions.between("dataEmissao", dataInicial.getTime(), dataFinal.getTime()));
                criteria.add(Restrictions.eq("serie", viewC300.getSerie()));
                criteria.add(Restrictions.eq("subserie", viewC300.getSerie()));
                criteria.addOrder(Order.asc(""));
                List<EcfNotaFiscalCabecalhoVO> listaEcfNotaFiscal = criteria.list();
                if (!listaEcfNotaFiscal.isEmpty()) {
                    registroC300.setNumDocIni(listaEcfNotaFiscal.get(0).getNumero());
                    registroC300.setNumDocFin(listaEcfNotaFiscal.get(listaEcfNotaFiscal.size() - 1).getNumero());
                }

                registroC300.setDtDoc(viewC300.getDataEmissao());
                registroC300.setVlDoc(viewC300.getSomaTotalNf());
                registroC300.setVlPis(viewC300.getSomaPis());
                registroC300.setVlCofins(viewC300.getSomaCofins());

                // REGISTRO C310: DOCUMENTOS CANCELADOS DE NOTAS FISCAIS DE VENDA A CONSUMIDOR (CÓDIGO 02).
                for (int j = 0; j < listaNf2Cancelada.size(); j++) {
                    registroC310 = new RegistroC310();

                    registroC310.setNumDocCanc(listaNf2Cancelada.get(j).getNumero());
                    
                    registroC300.getRegistroC310List().add(registroC310);
                }

                // REGISTRO C320: REGISTRO ANALÍTICO DO RESUMO DIÁRIO DAS NOTAS FISCAIS DE VENDA A CONSUMIDOR (CÓDIGO 02). ---> igual ao C390
                criteria = session.createCriteria(ViewSpedC390VoId.class);
                criteria.add(Restrictions.between("viewC390Vo.dataEmissao", dataInicial.getTime(), dataFinal.getTime()));
                List<ViewSpedC390VoId> listaC390 = criteria.list();
                RegistroC320 registroC320;
                ViewSpedC390VO viewC390;
                for (int j = 0; j < listaC390.size(); j++) {
                    registroC320 = new RegistroC320();
                    viewC390 = listaC390.get(j).getViewC390Vo();

                    registroC320.setCstIcms(viewC390.getCst());
                    registroC320.setCfop(viewC390.getCfop().toString());
                    registroC320.setAliqIcms(viewC390.getTaxaIcms());
                    registroC320.setVlOpr(viewC390.getSomaItem());
                    registroC320.setVlBcIcms(viewC390.getSomaBaseIcms());
                    registroC320.setVlIcms(viewC390.getSomaIcms());
                    registroC320.setVlRedBc(viewC390.getSomaIcmsOutras());

                    // REGISTRO C321: ITENS DO RESUMO DIÁRIO DOS DOCUMENTOS (CÓDIGO 02).
                    criteria = session.createCriteria(ViewSpedC321VoId.class);
                    criteria.add(Restrictions.between("viewC321Vo.dataEmissao", dataInicial.getTime(), dataFinal.getTime()));
                    List<ViewSpedC321VoId> listaC321 = criteria.list();
                    RegistroC321 registroC321;
                    ViewSpedC321VO spedC321;
                    for (int k = 0; k < listaC321.size(); k++) {
                        registroC321 = new RegistroC321();
                        spedC321 = listaC321.get(k).getViewC321Vo();

                        registroC321.setCodItem(spedC321.getIdProduto().toString());
                        registroC321.setQtd(spedC321.getSomaQuantidade());
                        registroC321.setUnid(spedC321.getDescricaoUnidade());
                        registroC321.setVlItem(spedC321.getSomaItem());
                        registroC321.setVlDesc(spedC321.getSomaDesconto());
                        registroC321.setVlBcIcms(spedC321.getSomaBaseIcms());
                        registroC321.setVlIcms(spedC321.getSomaIcms());
                        registroC321.setVlPis(spedC321.getSomaPis());
                        registroC321.setVlCofins(spedC321.getSomaCofins());

                        registroC320.getRegistroC321List().add(registroC321);
                    }

                    registroC300.getRegistroC320List().add(registroC320);
                }

                sped.getBlocoC().getListaRegistroC300().add(registroC300);
            }
        }//if (perfil.equals("B")) {

        // Ambos os Perfis
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

                // REGISTRO C410: PIS E COFINS TOTALIZADOS NO DIA (CÓDIGO 02 e 2D).
                // Implementado a critério do Participante do T2Ti ERP
                // REGISTRO C420: REGISTRO DOS TOTALIZADORES PARCIAIS DA REDUÇÃO Z (COD 02, 2D e 60).
                criteria = session.createCriteria(EcfR03VO.class);
                criteria.add(Restrictions.eq("idR02", r02.getId()));
                List<EcfR03VO> listaR03 = criteria.list();
                RegistroC420 registroC420;
                EcfR03VO r03;
                for (int k = 0; k < listaR03.size(); k++) {
                    registroC420 = new RegistroC420();
                    r03 = listaR03.get(j);

                    if (r03.getTotalizadorParcial().length() == 8) {
                        registroC420.setCodTotPar(r03.getTotalizadorParcial().substring(1));
                    } else {
                        registroC420.setCodTotPar(r03.getTotalizadorParcial());
                    }
                    registroC420.setVlrAcumTot(r03.getValorAcumulado());
                    if (r03.getTotalizadorParcial().length() == 7) {
                        registroC420.setNrTot(Integer.valueOf(r03.getTotalizadorParcial().substring(0, 2)));
                    } else {
                        registroC420.setNrTot(0);
                    }

                    if (perfil.equals("B")) {
                        // REGISTRO C425: RESUMO DE ITENS DO MOVIMENTO DIÁRIO (CÓDIGO 02 e 2D).
                        criteria = session.createCriteria(ViewSpedC425VoId.class);
                        criteria.add(Restrictions.between("viewC425Vo.dataEmissao", dataInicial.getTime(), dataFinal.getTime()));
                        criteria.add(Restrictions.not(Restrictions.like("viewC425Vo.totalizadorParcial", "%CAN%")));
                        criteria.add(Restrictions.not(Restrictions.like("viewC425Vo.totalizadorParcial", "%S%")));
                        List<ViewSpedC425VoId> listaC425 = criteria.list();
                        RegistroC425 registroC425;
                        ViewSpedC425VO viewC425;
                        for (int l = 0; l > listaC425.size(); l++) {
                            registroC425 = new RegistroC425();
                            viewC425 = listaC425.get(l).getViewC425Vo();

                            registroC425.setCodItem(viewC425.getIdEcfProduto().toString());
                            registroC425.setUnid(viewC425.getDescricaoUnidade());
                            registroC425.setQtd(viewC425.getSomaQuantidade());
                            registroC425.setVlItem(viewC425.getSomaItem());
                            registroC425.setVlPis(viewC425.getSomaPis());
                            registroC425.setVlCofins(viewC425.getSomaCofins());

                            registroC420.getRegistroc425List().add(registroC425);
                        }
                    }

                    registroC405.getRegistroC420List().add(registroC420);
                }

                // se tiver o perfil A, gera o C460 com C470
                if (perfil.equals("A")) {
                    // REGISTRO C460: DOCUMENTO FISCAL EMITIDO POR ECF (CÓDIGO 02, 2D e 60).
                    criteria = session.createCriteria(EcfVendaCabecalhoVO.class);
                    criteria.add(Restrictions.between("dataVenda", dataInicial.getTime(), dataFinal.getTime()));
                    List<EcfVendaCabecalhoVO> listaR04 = criteria.list();
                    RegistroC460 registroC460;
                    EcfVendaCabecalhoVO r04;
                    for (int k = 0; k < listaR04.size(); k++) {
                        registroC460 = new RegistroC460();
                        r04 = listaR04.get(k);

                        registroC460.setCodMod("2D");
                        if (r04.getStatusVenda().equals("C")) {
                            registroC460.setCodSit("02");
                        } else {
                            registroC460.setCodSit("00");
                            registroC460.setDtDoc(r04.getDataVenda());
                            registroC460.setVlDoc(r04.getValorFinal());
                            registroC460.setVlPis(r04.getPis());
                            registroC460.setVlCofins(r04.getCofins());
                            registroC460.setCpfCnpj(r04.getCpfCnpjCliente());
                            registroC460.setNomAdq(r04.getNomeCliente());

                            // REGISTRO C465: COMPLEMENTO DO CUPOM FISCAL ELETRÔNICO EMITIDO POR ECF – CF-e-ECF (CÓDIGO 60).
                            // Implementado a critério do Participante do T2Ti ERP }
                            // REGISTRO C470: ITENS DO DOCUMENTO FISCAL EMITIDO POR ECF (CÓDIGO 02 e 2D).
                            criteria = session.createCriteria(EcfVendaDetalheVO.class);
                            criteria.add(Restrictions.eq("idEcfVendaCabecalho", r04.getId()));
                            List<EcfVendaDetalheVO> listaR05 = criteria.list();
                            RegistroC470 registroC470;
                            EcfVendaDetalheVO r05;
                            for (int l = 0; l < listaR05.size(); l++) {
                                registroC470 = new RegistroC470();
                                r05 = listaR05.get(l);

                                registroC470.setCodItem(r05.getIdEcfProduto().toString());
                                registroC470.setQtd(r05.getQuantidade());
                                registroC470.setQtdCanc(BigDecimal.ZERO);

                                criteria = session.createCriteria(ProdutoVO.class);
                                criteria.add(Restrictions.eq("id", r05.getIdEcfProduto()));
                                ProdutoVO produto = (ProdutoVO) criteria.uniqueResult();

                                registroC470.setUnid(produto.getUnidadeProduto().getId().toString());
                                registroC470.setVlItem(r05.getTotalItem());
                                registroC470.setCstIcms(r05.getCst());
                                registroC470.setCfop(r05.getCfop().toString());
                                registroC470.setAliqIcms(r05.getTaxaIcms());
                                registroC470.setVlPis(r05.getPis());
                                registroC470.setVlCofins(r05.getCofins());

                                registroC460.getRegistroC470List().add(registroC470);
                            }
                        }
                        registroC460.setNumDoc(r04.getCoo().toString());

                        registroC405.getRegistroC460List().add(registroC460);
                    }
                }

                // REGISTRO C490: REGISTRO ANALÍTICO DO MOVIMENTO DIÁRIO (CÓDIGO 02, 2D e 60).
                criteria = session.createCriteria(ViewSpedC490VoId.class);
                criteria.add(Restrictions.between("viewC490Vo.dataVenda", dataInicial.getTime(), dataFinal.getTime()));
                List<ViewSpedC490VoId> listaC490 = criteria.list();
                RegistroC490 registroC490;
                ViewSpedC490VO viewC490;
                for (int k = 0; k < listaC490.size(); k++) {
                    registroC490 = new RegistroC490();
                    viewC490 = listaC490.get(k).getViewC490Vo();

                    registroC490.setCstIcms(viewC490.getCst());
                    registroC490.setCfop(viewC490.getCfop().toString());
                    registroC490.setAliqIcms(viewC490.getTaxaIcms());
                    registroC490.setVlOpr(viewC490.getSomaItem());
                    registroC490.setVlBcIcms(viewC490.getSomaBaseIcms());
                    registroC490.setVlIcms(viewC490.getSomaIcms());

                    registroC405.getRegistroC490List().add(registroC490);
                }

                // REGISTRO C495: RESUMO MENSAL DE ITENS DO ECF POR ESTABELECIMENTO (CÓDIGO 02 e 2D).
                // Implementado a critério do Participante do T2Ti ERP
                registroC400.getRegistroC405List().add(registroC405);
            }

            // REGISTRO C500: NOTA FISCAL/CONTA DE ENERGIA ELÉTRICA (CÓDIGO 06), NOTA FISCAL/CONTA DE FORNECIMENTO D'ÁGUA CANALIZADA (CÓDIGO 29) E NOTA FISCAL CONSUMO FORNECIMENTO DE GÁS (CÓDIGO 28).
            // REGISTRO C510: ITENS DO DOCUMENTO NOTA FISCAL/CONTA ENERGIA ELÉTRICA (CÓDIGO 06), NOTA FISCAL/CONTA DE FORNECIMENTO D'ÁGUA CANALIZADA (CÓDIGO 29) E NOTA FISCAL/CONTA DE FORNECIMENTO DE GÁS (CÓDIGO 28).
            // REGISTRO C590: REGISTRO ANALÍTICO DO DOCUMENTO - NOTA FISCAL/CONTA DE ENERGIA ELÉTRICA (CÓDIGO 06), NOTA FISCAL/CONTA DE FORNECIMENTO D'ÁGUA CANALIZADA (CÓDIGO 29) E NOTA FISCAL CONSUMO FORNECIMENTO DE GÁS (CÓDIGO 28).
            // REGISTRO C600: CONSOLIDAÇÃO DIÁRIA DE NOTAS FISCAIS/CONTAS DE ENERGIA ELÉTRICA (CÓDIGO 06), NOTA FISCAL/CONTA DE FORNECIMENTO D'ÁGUA CANALIZADA (CÓDIGO 29) E NOTA FISCAL/CONTA DE FORNECIMENTO DE GÁS (CÓDIGO 28) (EMPRESAS NÃO OBRIGADAS AO CONVÊNIO ICMS 115/03).
            // REGISTRO C601: DOCUMENTOS CANCELADOS - CONSOLIDAÇÃO DIÁRIA DE NOTAS FISCAIS/CONTAS DE ENERGIA ELÉTRICA (CÓDIGO 06), NOTA FISCAL/CONTA DE FORNECIMENTO D'ÁGUA CANALIZADA (CÓDIGO 29) E NOTA FISCAL/CONTA DE FORNECIMENTO DE GÁS (CÓDIGO 28)
            // REGISTRO C610: ITENS DO DOCUMENTO CONSOLIDADO (CÓDIGO 06), NOTA FISCAL/CONTA DE FORNECIMENTO D'ÁGUA CANALIZADA (CÓDIGO 29) E NOTA FISCAL/CONTA DE FORNECIMENTO DE GÁS (CÓDIGO 28) (EMPRESAS NÃO OBRIGADAS AO CONVÊNIO ICMS 115/03).
            // REGISTRO C690: REGISTRO ANALÍTICO DOS DOCUMENTOS (NOTAS FISCAIS/CONTAS DE ENERGIA ELÉTRICA (CÓDIGO 06), NOTA FISCAL/CONTA DE FORNECIMENTO D’ÁGUA CANALIZADA (CÓDIGO 29) E NOTA FISCAL/CONTA DE FORNECIMENTO DE GÁS (CÓDIGO 28)
            // REGISTRO C700: CONSOLIDAÇÃO DOS DOCUMENTOS NF/CONTA ENERGIA ELÉTRICA (CÓD 06), EMITIDAS EM VIA ÚNICA (EMPRESAS OBRIGADAS À ENTREGA DO ARQUIVO PREVISTO NO CONVÊNIO ICMS 115/03) E NOTA FISCAL/CONTA DE FORNECIMENTO DE GÁS CANALIZADO (CÓDIGO 28)
            // REGISTRO C790: REGISTRO ANALÍTICO DOS DOCUMENTOS (CÓDIGOS 06 e 28).
            // REGISTRO C791: REGISTRO DE INFORMAÇÕES DE ST POR UF (COD 06)
            /*Implementados a critério do Participante do T2Ti ERP*/

            // REGISTRO C800: CUPOM FISCAL ELETRÔNICO (CÓDIGO 59)
            // REGISTRO C850: REGISTRO ANALÍTICO DO CF-E (CODIGO 59)
            // REGISTRO C860: IDENTIFICAÇÃO DO EQUIPAMENTO SAT-CF-E
            // REGISTRO C890: RESUMO DIÁRIO DO CF-E (CÓDIGO 59) POR EQUIPAMENTO SATCF-E
            /*SERÃO IMPLEMENTADOS QUANDO O SAT FOR CONSTRUIDO*/
            
            sped.getBlocoC().getListaRegistroC400().add(registroC400);
        }
    }

    //BLOCO E: APURAÇÃO DO ICMS E DO IPI
    private void geraBlocoE() throws Exception {
        // REGISTRO E001: ABERTURA DO BLOCO E
        sped.getBlocoE().getRegistroE001().setIndMov(0);

        // REGISTRO E100: PERÍODO DA APURAÇÃO DO ICMS.
        RegistroE100 registroE100 = new RegistroE100();
        registroE100.setDtIni(dataInicial.getTime());
        registroE100.setDtFin(dataFinal.getTime());
        sped.getBlocoE().getListaRegistroE100().add(registroE100);

        // REGISTRO E110: APURAÇÃO DO ICMS – OPERAÇÕES PRÓPRIAS.
        criteria = session.createCriteria(FiscalApuracaoIcmsVO.class);
        criteria.add(Restrictions.eq("competencia", Biblioteca.mesAno(dataInicial)));
        List<FiscalApuracaoIcmsVO> listaE110 = criteria.list();
        FiscalApuracaoIcmsVO v;
        RegistroE110 registroE110;
        if (!listaE110.isEmpty()) {
            registroE110 = new RegistroE110();
            v = listaE110.get(0);

            registroE110.setVlTotDebitos(v.getValorTotalDebito());
            registroE110.setVlAjDebitos(v.getValorAjusteDebito());
            registroE110.setVlTotAjDebitos(v.getValorTotalAjusteDebito());
            registroE110.setVlEstornosCred(v.getValorEstornoCredito());
            registroE110.setVlTotCreditos(v.getValorTotalCredito());
            registroE110.setVlAjCreditos(v.getValorAjusteCredito());
            registroE110.setVlTotAjCreditos(v.getValorTotalAjusteCredito());
            registroE110.setVlEstornosDeb(v.getValorEstornoDebito());
            registroE110.setVlSldCredorAnt(v.getValorSaldoCredorAnterior());
            registroE110.setVlSldApurado(v.getValorSaldoApurado());
            registroE110.setVlTotDed(v.getValorTotalDeducao());
            registroE110.setVlIcmsRecolher(v.getValorIcmsRecolher());
            registroE110.setVlSldCredorTransportar(v.getValorSaldoCredorTransp());
            registroE110.setDebEsp(v.getValorDebitoEspecial());

            //registro E116
            RegistroE116 registroE116 = new RegistroE116();
            registroE116.setCodOr("000");
            registroE116.setVlOr(v.getValorIcmsRecolher());
            registroE116.setDtVcto(dataFinal.getTime());
            registroE116.setCodRec("1");
            registroE116.setNumProc("");
            registroE116.setIndProc("");
            registroE116.setProc("");
            registroE116.setTxtCompl("");
            registroE116.setMesRef("");

            registroE110.getRegistroE116List().add(registroE116);

            registroE100.setRegistroE110(registroE110);
        }
    }

    //BLOCO H: INVENTÁRIO FÍSICO
    private void geraBlocoH() throws Exception {
        sped.getBlocoH().getRegistroH001().setIndMov(0);//com dados

        criteria = session.createCriteria(ProdutoVO.class);
        List<ProdutoVO> listaProduto = criteria.list();
        BigDecimal totalGeral = BigDecimal.ZERO;
        for (int i = 0; i < listaProduto.size(); i++) {
            totalGeral = Biblioteca.soma(totalGeral, Biblioteca.multiplica(listaProduto.get(i).getValorCompra(), listaProduto.get(i).getQuantidadeEstoque()));
        }

        // REGISTRO H005: TOTAIS DO INVENTÁRIO
        RegistroH005 registroH005 = new RegistroH005();
        registroH005.setDtInv(dataFinal.getTime());
        registroH005.setVlInv(totalGeral);
        registroH005.setMotInv("0" + inventario);
        sped.getBlocoH().getListaRegistroH005().add(registroH005);

        RegistroH010 registroH010;
        for (int i = 0; i < listaProduto.size(); i++) {
            registroH010 = new RegistroH010();

            registroH010.setCodItem(listaProduto.get(i).getId().toString());
            registroH010.setUnid(listaProduto.get(i).getUnidadeProduto().getId().toString());
            registroH010.setQtd(listaProduto.get(i).getQuantidadeEstoque());
            registroH010.setVlUnit(listaProduto.get(i).getValorCompra());
            registroH010.setVlItem(Biblioteca.multiplica(listaProduto.get(i).getQuantidadeEstoque(), listaProduto.get(i).getValorCompra()));
            registroH010.setIndProp("0");

            registroH005.getRegistroH010List().add(registroH010);
        }

        // REGISTRO H020: Informação complementar do Inventário.
        // Implementado a critério do Participante do T2Ti ERP }
    }

    //BLOCO 1: OUTRAS INFORMAÇÕES
    private void geraBloco1() throws Exception {
        sped.getBloco1().getRegistro1001().setIndMov(0);//com dados

        Registro1010 registro1010 = new Registro1010();
        registro1010.setIndExp("N");
        registro1010.setIndCcrf("N");
        registro1010.setIndComb("N");
        registro1010.setIndUsina("N");
        registro1010.setIndVa("N");
        registro1010.setIndEe("N");
        registro1010.setIndCart("N");
        registro1010.setIndForm("N");
        registro1010.setIndAer("N");

        sped.getBloco1().getListaRegistro1010().add(registro1010);
    }
}
