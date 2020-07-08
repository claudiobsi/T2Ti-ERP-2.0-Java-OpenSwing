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
package com.t2tierp.sintegra.servidor;

import com.t2tierp.cadastros.java.EmpresaEnderecoVO;
import com.t2tierp.cadastros.java.EmpresaVO;
import com.t2tierp.cadastros.java.ProdutoVO;
import com.t2tierp.ged.java.ArquivoVO;
import com.t2tierp.nfe.java.NfeCabecalhoVO;
import com.t2tierp.nfe.java.NfeDetalheVO;
import com.t2tierp.padrao.java.Biblioteca;
import com.t2tierp.padrao.servidor.HibernateUtil;
import com.t2tierp.pafecf.java.EcfNotaFiscalCabecalhoVO;
import com.t2tierp.pafecf.java.EcfSintegra60aVO;
import com.t2tierp.pafecf.java.EcfSintegra60mVO;
import com.t2tierp.sintegra.java.Tipo10;
import com.t2tierp.sintegra.java.Tipo11;
import com.t2tierp.sintegra.java.Tipo50;
import com.t2tierp.sintegra.java.Tipo54;
import com.t2tierp.sintegra.java.Tipo60A;
import com.t2tierp.sintegra.java.Tipo60D;
import com.t2tierp.sintegra.java.Tipo60M;
import com.t2tierp.sintegra.java.Tipo60R;
import com.t2tierp.sintegra.java.Tipo61;
import com.t2tierp.sintegra.java.Tipo61R;
import com.t2tierp.sintegra.java.Tipo75;
import com.t2tierp.sintegra.java.Tipo90;
import com.t2tierp.sintegra.java.ViewSintegra60dVO;
import com.t2tierp.sintegra.java.ViewSintegra60dVoId;
import com.t2tierp.sintegra.java.ViewSintegra60rVO;
import com.t2tierp.sintegra.java.ViewSintegra60rVoId;
import com.t2tierp.sintegra.java.ViewSintegra61rVO;
import com.t2tierp.sintegra.java.ViewSintegra61rVoId;
import java.io.File;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.io.FileUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.jrimum.texgit.FlatFile;
import org.jrimum.texgit.Record;
import org.jrimum.texgit.Texgit;
import org.openswing.swing.message.receive.java.ErrorResponse;
import org.openswing.swing.message.receive.java.Response;
import org.openswing.swing.message.receive.java.VOResponse;
import org.openswing.swing.message.send.java.GridParams;
import org.openswing.swing.server.Action;
import org.openswing.swing.server.UserSessionParameters;

public class GeraSintegraAction implements Action {

    private Calendar dataInicial;
    private Calendar dataFinal;
    private EmpresaVO empresa;
    private String convenio;
    private String naturezaOperacao;
    private String finalidadeArquivo;
    private Session session;
    private Criteria criteria;

    public GeraSintegraAction() {
    }

    public String getRequestName() {
        return "geraSintegraAction";
    }

    public Response executeCommand(Object inputPar, UserSessionParameters userSessionPars, HttpServletRequest request, HttpServletResponse response, HttpSession userSession, ServletContext context) {
        GridParams pars = (GridParams) inputPar;
        try {
            this.session = HibernateUtil.getSessionFactory().openSession();
            this.dataInicial = Calendar.getInstance();
            this.dataInicial.setTime((Date) pars.getOtherGridParams().get("dataInicial"));
            this.dataFinal = Calendar.getInstance();
            this.dataFinal.setTime((Date) pars.getOtherGridParams().get("dataFinal"));
            this.convenio = (String) pars.getOtherGridParams().get("convenio");
            this.naturezaOperacao = (String) pars.getOtherGridParams().get("naturezaOperacao");
            this.finalidadeArquivo = (String) pars.getOtherGridParams().get("finalidadeArquivo");
            this.empresa = (EmpresaVO) pars.getOtherGridParams().get("empresa");
            this.empresa = (EmpresaVO) Biblioteca.nullToEmpty(this.empresa);
            this.empresa.getListaEndereco().set(0, (EmpresaEnderecoVO) Biblioteca.nullToEmpty(this.empresa.getListaEndereco().get(0)));

            File file = geraArquivoSintegra();

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

    private File geraArquivoSintegra() throws Exception {
        int totalRegistrosArquivo = 0;
        int totalRegistros50 = 0;
        int totalRegistros54 = 0;
        int totalRegistros60 = 0;
        int totalRegistros61 = 0;
        List<Tipo75> listaTipo75 = new ArrayList<Tipo75>();
        Tipo75 tipo75;

        //buscar o xml com o layout
        File layout = File.createTempFile("layout", ".xml");
        layout.deleteOnExit();
        InputStream in = this.getClass().getResourceAsStream("/com/t2tierp/sintegra/cliente/sintegra.xml");
        FileUtils.copyInputStreamToFile(in, layout);

        //cria um objeto FlatFile
        FlatFile<Record> ff = Texgit.createFlatFile(layout);

        //REGISTRO TIPO 10 - MESTRE DO ESTABELECIMENTO
        Tipo10 tipo10 = new Tipo10(ff.createRecord("Tipo10"));
        tipo10.setCNPJ(Long.parseLong(empresa.getCnpj()));
        tipo10.setInscricaoEstadual(empresa.getInscricaoEstadual());
        tipo10.setNomeContribuinte(empresa.getRazaoSocial());
        tipo10.setMunicipio(empresa.getListaEndereco().get(0).getCidade());
        tipo10.setUF(empresa.getListaEndereco().get(0).getUf());
        tipo10.setDataInicial(dataInicial.getTime());
        tipo10.setDataFinal(dataFinal.getTime());
        tipo10.setCodigoIdentificaoConvenio(convenio);
        tipo10.setCodigoIdentificaoNaturezaOperacao(naturezaOperacao);
        tipo10.setCodigoFinalidadeArquivo(finalidadeArquivo);

        ff.addRecord(tipo10.getRecord());
        totalRegistrosArquivo++;

        //Registro Tipo 11 - Dados Complementares do Informante
        Tipo11 tipo11 = new Tipo11(ff.createRecord("Tipo11"));
        tipo11.setLogradouro(empresa.getListaEndereco().get(0).getLogradouro());
        tipo11.setNumero(Integer.valueOf(empresa.getListaEndereco().get(0).getNumero()));
        tipo11.setComplemento(empresa.getListaEndereco().get(0).getComplemento());
        tipo11.setBairro(empresa.getListaEndereco().get(0).getBairro());
        tipo11.setCEP(Integer.valueOf(empresa.getListaEndereco().get(0).getCep()));
        tipo11.setNomeContato(empresa.getEmpresaContato());
        if (empresa.getListaEndereco().get(0).getFone().isEmpty()) {
            tipo11.setTelefone(0l);
        } else {
            tipo11.setTelefone(Long.valueOf(empresa.getListaEndereco().get(0).getFone()));
        }

        ff.addRecord(tipo11.getRecord());
        totalRegistrosArquivo++;

        /* REGISTRO TIPO 50 - Nota Fiscal, modelo 1 ou 1-A (código 01), quanto ao ICMS, a critério de cada UF, Nota Fiscal do Produtor, modelo 4
         * (código 04), Nota Fiscal/Conta de Energia Elétrica, modelo 6 (código 06), Nota Fiscal de Serviço de Comunicação, modelo 21 (código 21),
         * Nota Fiscal de Serviços de Telecomunicações, modelo 22 (código 22), Nota Fiscal Eletrônica, modelo 55 (código 55).
         */
        criteria = session.createCriteria(NfeCabecalhoVO.class);
        criteria.add(Restrictions.between("dataHoraEmissao", dataInicial.getTime(), dataFinal.getTime()));
        List<NfeCabecalhoVO> listaNfe = criteria.list();
        Tipo50 tipo50;
        Tipo54 tipo54;
        NfeCabecalhoVO nfeCabecalho;
        NfeDetalheVO nfeDetalhe;
        for (int i = 0; i < listaNfe.size(); i++) {
            nfeCabecalho = listaNfe.get(i);

            tipo50 = new Tipo50(ff.createRecord("Tipo50"));
            tipo50.setCNPJ(Long.valueOf(empresa.getCnpj()));
            tipo50.setInscricaoEstadual(empresa.getInscricaoEstadual());
            tipo50.setDataEmissao(nfeCabecalho.getDataHoraEmissao());
            tipo50.setUF(empresa.getListaEndereco().get(0).getUf());
            tipo50.setCodigoModelo(Integer.valueOf(nfeCabecalho.getCodigoModelo()));
            tipo50.setNumeroNota(Integer.valueOf(nfeCabecalho.getNumero()));
            tipo50.setSerie(nfeCabecalho.getSerie());
            tipo50.setEmitente("P");
            tipo50.setValorTotal(nfeCabecalho.getValorTotal().multiply(BigDecimal.valueOf(100)).longValue());
            tipo50.setBaseCalculoIcms(nfeCabecalho.getBaseCalculoIcms().multiply(BigDecimal.valueOf(100)).longValue());
            tipo50.setValorIcms(nfeCabecalho.getValorIcms().multiply(BigDecimal.valueOf(100)).longValue());
            tipo50.setValorIsento(0l);
            tipo50.setValorOutras(nfeCabecalho.getValorDespesasAcessorias().multiply(BigDecimal.valueOf(100)).longValue());
            tipo50.setAliquotaIcms(nfeCabecalho.getValorIcms().intValue());
            if (nfeCabecalho.getStatusNota().equals("5")) {
                tipo50.setSituacao("N");
            } else if (nfeCabecalho.getStatusNota().equals("6")) {
                tipo50.setSituacao("S");
            }

            // REGISTRO TIPO 51 - TOTAL DE NOTA FISCAL QUANTO AO IPI
            // REGISTRO TIPO 53 - SUBSTITUIÇÃO TRIBUTÁRIA
            // Implementados a critério do Participante do T2Ti ERP }
            // REGISTRO TIPO 54 - PRODUTO
            criteria = session.createCriteria(NfeDetalheVO.class);
            criteria.add(Restrictions.eq("nfeCabecalho", listaNfe.get(i)));
            List<NfeDetalheVO> listaNfeDetalhe = criteria.list();
            for (int j = 0; j < listaNfeDetalhe.size(); j++) {
                nfeDetalhe = listaNfeDetalhe.get(j);

                tipo54 = new Tipo54(ff.createRecord("Tipo54"));
                tipo54.setCNPJ(Long.valueOf(empresa.getCnpj()));
                tipo54.setModelo(Integer.valueOf(nfeCabecalho.getCodigoModelo()));
                tipo54.setSerie(nfeCabecalho.getSerie());
                tipo54.setNumeroNf(Integer.valueOf(nfeCabecalho.getNumero()));
                tipo54.setCfop(nfeDetalhe.getCfop());
                tipo54.setCst(nfeDetalhe.getNfeDetalheImpostoIcms().getCstIcms());
                tipo54.setNumeroItem(nfeDetalhe.getNumeroItem());
                tipo54.setCodigoProduto(nfeDetalhe.getGtin());
                tipo54.setQuantidade(nfeDetalhe.getQuantidadeComercial().multiply(BigDecimal.valueOf(100)).longValue());
                tipo54.setValorProduto(nfeDetalhe.getValorTotal().multiply(BigDecimal.valueOf(100)).longValue());
                tipo54.setBaseCalculoIcms(nfeDetalhe.getNfeDetalheImpostoIcms().getBaseCalculoIcms().multiply(BigDecimal.valueOf(100)).longValue());
                tipo54.setBaseCalculoIcms(nfeDetalhe.getNfeDetalheImpostoIcms().getValorBaseCalculoIcmsSt().multiply(BigDecimal.valueOf(100)).longValue());
                tipo54.setValorIpi(nfeDetalhe.getNfeDetalheImpostoIpi().getValorIpi().multiply(BigDecimal.valueOf(100)).longValue());
                tipo54.setAliquotaIcms(nfeDetalhe.getNfeDetalheImpostoIcms().getAliquotaIcms().multiply(BigDecimal.valueOf(100)).longValue());

                tipo50.getRecord().addInnerRecord(tipo54.getRecord());
                totalRegistros54++;
                totalRegistrosArquivo++;

                // REGISTRO TIPO 75 - CÓDIGO DE PRODUTO OU SERVIÇO
                tipo75 = new Tipo75(ff.createRecord("Tipo75"));
                tipo75.setDataInicial(dataInicial.getTime());
                tipo75.setDataFinal(dataFinal.getTime());
                tipo75.setCodigoProduto(nfeDetalhe.getGtin());
                tipo75.setCodigoNcm(nfeDetalhe.getNcm());
                tipo75.setDescricao(nfeDetalhe.getNomeProduto());
                tipo75.setUnidade(nfeDetalhe.getUnidadeComercial());
                tipo75.setAliquotaIpi(nfeDetalhe.getNfeDetalheImpostoIpi().getAliquotaIpi().multiply(BigDecimal.valueOf(100)).longValue());
                tipo75.setAliquotaIcms(nfeDetalhe.getNfeDetalheImpostoIpi().getAliquotaIpi().multiply(BigDecimal.valueOf(100)).longValue());

                listaTipo75.add(tipo75);
            }

            // REGISTRO TIPO 55 - GUIA NACIONAL DE RECOLHIMENTO DE TRIBUTOS ESTADUAIS
            // REGISTRO TIPO 56 - OPERAÇÕES COM VEÍCULOS AUTOMOTORES NOVOS
            // REGISTRO TIPO 57 - NÚMERO DE LOTE DE FABRICAÇÃO DE PRODUTO
            // Implementados a critério do Participante do T2Ti ERP
            ff.addRecord(tipo50.getRecord());
            totalRegistros50++;
            totalRegistrosArquivo++;
        }

        /* Registro Tipo 60 - Cupom Fiscal, Cupom Fiscal - PDV ,e os seguintes Documentos Fiscais quando emitidos por
         * Equipamento Emissor de Cupom Fiscal: Bilhete de Passagem Rodoviário (modelo 13), Bilhete de Passagem Aquaviário
         * (modelo 14), Bilhete de Passagem e Nota de Bagagem (modelo 15), Bilhete de Passagem Ferroviário (modelo 16), e
         * Nota Fiscal de Venda a Consumidor (modelo 2)
         *
         */
        criteria = session.createCriteria(EcfSintegra60mVO.class);
        criteria.add(Restrictions.between("dataEmissao", dataInicial.getTime(), dataFinal.getTime()));
        List<EcfSintegra60mVO> lista60M = criteria.list();
        Tipo60M tipo60M;
        Tipo60A tipo60A;
        EcfSintegra60mVO sintegra60m;
        EcfSintegra60aVO sintegra60a;
        for (int i = 0; i < lista60M.size(); i++) {
            sintegra60m = lista60M.get(i);

            // Registro Tipo 60 - Mestre (60M): Identificador do equipamento
            tipo60M = new Tipo60M(ff.createRecord("Tipo60M"));
            tipo60M.setDataEmissao(sintegra60m.getDataEmissao());
            tipo60M.setSerie(sintegra60m.getNumeroSerieEcf());
            tipo60M.setNumeroOrdemEquipamento(sintegra60m.getNumeroEquipamento());
            if (sintegra60m.getModeloDocumentoFiscal() == null) {
                tipo60M.setModeloDocumentoFiscal("2D");
            } else {
                tipo60M.setModeloDocumentoFiscal(sintegra60m.getModeloDocumentoFiscal());
            }
            tipo60M.setPrimeiroCOO(sintegra60m.getCooInicial());
            tipo60M.setUltimoCOO(sintegra60m.getCooFinal());
            tipo60M.setNumeroCRZ(sintegra60m.getCrz());
            tipo60M.setCRO(sintegra60m.getCro());
            tipo60M.setValorVendaBruta(sintegra60m.getValorVendaBruta().multiply(BigDecimal.valueOf(100)).longValue());
            tipo60M.setValorTotalizadorGeral(sintegra60m.getValorGrandeTotal().multiply(BigDecimal.valueOf(100)).longValue());

            totalRegistros60++;
            totalRegistrosArquivo++;

            criteria = session.createCriteria(EcfSintegra60aVO.class);
            criteria.add(Restrictions.eq("idSintegra60m", sintegra60m.getId()));
            List<EcfSintegra60aVO> lista60A = criteria.list();
            for (int j = 0; j < lista60M.size(); j++) {
                sintegra60a = lista60A.get(j);

                // Registro Tipo 60 - Analítico (60A): Identificador de cada Situação Tributária no final do dia de cada equipamento emissor de cupom fiscal
                tipo60A = new Tipo60A(ff.createRecord("Tipo60A"));
                tipo60A.setDataEmissao(sintegra60m.getDataEmissao());
                tipo60A.setNumeroSerie(sintegra60m.getNumeroSerieEcf());
                tipo60A.setSituacaoTributaria(sintegra60a.getSituacaoTributaria());
                tipo60A.setValorAcumuladoTotalizadorParcial(sintegra60a.getValor().multiply(BigDecimal.valueOf(100)).longValue());

                tipo60M.getRecord().addInnerRecord(tipo60A.getRecord());

                totalRegistros60++;
                totalRegistrosArquivo++;
            }

            ff.addRecord(tipo60M.getRecord());
        }

        // Registro Tipo 60 - Resumo Diário (60D): Registro de mercadoria/produto ou serviço constante em documento fiscal emitido por Terminal Ponto de Venda (PDV) ou equipamento Emissor de Cupom Fiscal (ECF)
        criteria = session.createCriteria(ViewSintegra60dVoId.class);
        criteria.add(Restrictions.between("viewSintegra60d.dataEmissao", dataInicial.getTime(), dataFinal.getTime()));
        List<ViewSintegra60dVoId> lista60D = criteria.list();
        Tipo60D tipo60D;
        ViewSintegra60dVO sintegra60d;
        for (int i = 0; i < lista60D.size(); i++) {
            sintegra60d = lista60D.get(i).getViewSintegra60d();
            tipo60D = new Tipo60D(ff.createRecord("Tipo60D"));

            tipo60D.setDataEmissao(sintegra60d.getDataEmissao());
            tipo60D.setNumeroSerie(sintegra60d.getSerie());
            tipo60D.setCodigoProduto(sintegra60d.getGtin());
            tipo60D.setQuantidade(sintegra60d.getSomaQuantidade().multiply(BigDecimal.valueOf(100)).longValue());
            tipo60D.setValor(sintegra60d.getSomaItem().multiply(BigDecimal.valueOf(100)).longValue());
            tipo60D.setBaseCalculoIcms(sintegra60d.getSomaBaseIcms().multiply(BigDecimal.valueOf(100)).longValue());
            tipo60D.setSituacaoTributaria(sintegra60d.getEcfIcmsSt());
            tipo60D.setValorIcms(sintegra60d.getSomaIcms().multiply(BigDecimal.valueOf(100)).longValue());

            ff.addRecord(tipo60D.getRecord());

            totalRegistros60++;
            totalRegistrosArquivo++;

            // REGISTRO TIPO 75 - CÓDIGO DE PRODUTO OU SERVIÇO
            criteria = session.createCriteria(ProdutoVO.class);
            criteria.add(Restrictions.eq("gtin", sintegra60d.getGtin()));
            ProdutoVO produto = (ProdutoVO) criteria.uniqueResult();
            tipo75 = new Tipo75(ff.createRecord("Tipo75"));
            tipo75.setDataInicial(dataInicial.getTime());
            tipo75.setDataFinal(dataFinal.getTime());
            tipo75.setCodigoProduto(sintegra60d.getGtin());
            tipo75.setCodigoNcm(produto.getNcm());
            tipo75.setDescricao(produto.getDescricaoPdv());
            tipo75.setUnidade(produto.getUnidadeProduto().getSigla());
            tipo75.setAliquotaIpi(BigDecimal.ZERO.longValue());
            tipo75.setAliquotaIcms(BigDecimal.ZERO.longValue());

            listaTipo75.add(tipo75);
        }

        // Registro Tipo 60 - Resumo Mensal (60R): Registro de mercadoria/produto ou serviço processado em equipamento Emissor de Cupom Fiscal
        criteria = session.createCriteria(ViewSintegra60rVoId.class);
        criteria.add(Restrictions.between("viewSintegra60r.dataEmissao", dataInicial.getTime(), dataFinal.getTime()));
        List<ViewSintegra60rVoId> lista60R = criteria.list();
        Tipo60R tipo60R;
        ViewSintegra60rVO sintegra60r;
        for (int i = 0; i < lista60R.size(); i++) {
            sintegra60r = lista60R.get(i).getViewSintegra60r();
            tipo60R = new Tipo60R(ff.createRecord("Tipo60R"));

            tipo60R.setDataEmissao(sintegra60r.getDataEmissao());
            tipo60R.setCodigoProduto(sintegra60r.getGtin());
            tipo60R.setQuantidade(sintegra60r.getSomaQuantidade().multiply(BigDecimal.valueOf(100)).longValue());
            tipo60R.setValor(sintegra60r.getSomaItem().multiply(BigDecimal.valueOf(100)).longValue());
            tipo60R.setBaseCalculoIcms(sintegra60r.getSomaBaseIcms().multiply(BigDecimal.valueOf(100)).longValue());
            tipo60R.setSituacaoTributaria(sintegra60r.getEcfIcmsSt());

            ff.addRecord(tipo60R.getRecord());

            totalRegistros60++;
            totalRegistrosArquivo++;

            // REGISTRO TIPO 75 - CÓDIGO DE PRODUTO OU SERVIÇO
            criteria = session.createCriteria(ProdutoVO.class);
            criteria.add(Restrictions.eq("gtin", sintegra60r.getGtin()));
            ProdutoVO produto = (ProdutoVO) criteria.uniqueResult();
            tipo75 = new Tipo75(ff.createRecord("Tipo75"));
            tipo75.setDataInicial(dataInicial.getTime());
            tipo75.setDataFinal(dataFinal.getTime());
            tipo75.setCodigoProduto(sintegra60r.getGtin());
            tipo75.setCodigoNcm(produto.getNcm());
            tipo75.setDescricao(produto.getDescricaoPdv());
            tipo75.setUnidade(produto.getUnidadeProduto().getSigla());
            tipo75.setAliquotaIpi(BigDecimal.ZERO.longValue());
            tipo75.setAliquotaIcms(BigDecimal.ZERO.longValue());

            listaTipo75.add(tipo75);
        }

        /* Registro Tipo 61 - REGISTRO TIPO 61: Para os documentos fiscais descritos a seguir, quando não emitidos por equipamento emissor
         * de cupom fiscal : Bilhete de Passagem Aquaviário (modelo 14), Bilhete de Passagem e Nota de Bagagem (modelo 15),
         * Bilhete de Passagem Ferroviário (modelo 16), Bilhete de Passagem Rodoviário (modelo 13) e Nota Fiscal de Venda a
         * Consumidor (modelo 2), Nota Fiscal de Produtor (modelo 4), para as unidades da Federação que não o exigirem na
         * forma prevista no item 11
         */
        criteria = session.createCriteria(EcfNotaFiscalCabecalhoVO.class);
        criteria.add(Restrictions.between("dataEmissao", dataInicial.getTime(), dataFinal.getTime()));
        List<EcfNotaFiscalCabecalhoVO> listaNF2Cabecalho = criteria.list();
        Tipo61 tipo61;
        EcfNotaFiscalCabecalhoVO sintegra61;
        for (int i = 0; i < listaNF2Cabecalho.size(); i++) {
            sintegra61 = listaNF2Cabecalho.get(i);
            tipo61 = new Tipo61(ff.createRecord("Tipo61"));

            tipo61.setDataEmissao(sintegra61.getDataEmissao());
            tipo61.setModelo(2);
            tipo61.setNumeroInicialOrdem(Integer.valueOf(sintegra61.getNumero()));
            tipo61.setNumeroFinalOrdem(Integer.valueOf(sintegra61.getNumero()));
            tipo61.setSerie(sintegra61.getSerie());
            tipo61.setSubSerie(sintegra61.getSubserie());
            tipo61.setValorTotal(sintegra61.getTotalNf().multiply(BigDecimal.valueOf(100)).longValue());
            tipo61.setBaseCalculoIcms(sintegra61.getBaseIcms().multiply(BigDecimal.valueOf(100)).longValue());
            tipo61.setValorIcms(sintegra61.getIcms().multiply(BigDecimal.valueOf(100)).longValue());
            tipo61.setOutras(sintegra61.getIcmsOutras().multiply(BigDecimal.valueOf(100)).longValue());

            ff.addRecord(tipo61.getRecord());

            totalRegistros61++;
            totalRegistrosArquivo++;
        }

        // Registro Tipo 61 - Resumo Mensal por Item (61R): Registro de mercadoria/produto ou serviço comercializados através de Nota Fiscal de Produtor ou Nota Fiscal de Venda a Consumidor não emitida por ECF
        criteria = session.createCriteria(ViewSintegra61rVoId.class);
        criteria.add(Restrictions.between("viewSintegra61r.dataEmissao", dataInicial.getTime(), dataFinal.getTime()));
        List<ViewSintegra61rVoId> lista61R = criteria.list();
        Tipo61R tipo61R;
        ViewSintegra61rVO sintegra61r;
        for (int i = 0; i < lista61R.size(); i++) {
            sintegra61r = lista61R.get(i).getViewSintegra61r();
            tipo61R = new Tipo61R(ff.createRecord("Tipo61R"));

            tipo61R.setDataEmissao(sintegra61r.getDataEmissao());
            tipo61R.setCodigoProduto(sintegra61r.getGtin());
            tipo61R.setQuantidade(sintegra61r.getSomaQuantidade().multiply(BigDecimal.valueOf(100)).longValue());
            tipo61R.setValor(sintegra61r.getSomaItem().multiply(BigDecimal.valueOf(100)).longValue());
            tipo61R.setBaseCalculoIcms(sintegra61r.getSomaBaseIcms().multiply(BigDecimal.valueOf(100)).longValue());
            if (sintegra61r.getEcfIcmsSt() != null) {
                tipo61R.setAliquota(sintegra61r.getEcfIcmsSt());
            } else {
                tipo61R.setAliquota("0");
            }

            ff.addRecord(tipo61R.getRecord());

            totalRegistros61++;
            totalRegistrosArquivo++;

            // REGISTRO TIPO 75 - CÓDIGO DE PRODUTO OU SERVIÇO
            criteria = session.createCriteria(ProdutoVO.class);
            criteria.add(Restrictions.eq("gtin", sintegra61r.getGtin()));
            ProdutoVO produto = (ProdutoVO) criteria.uniqueResult();
            tipo75 = new Tipo75(ff.createRecord("Tipo75"));
            tipo75.setDataInicial(dataInicial.getTime());
            tipo75.setDataFinal(dataFinal.getTime());
            tipo75.setCodigoProduto(sintegra61r.getGtin());
            tipo75.setCodigoNcm(produto.getNcm());
            tipo75.setDescricao(produto.getDescricaoPdv());
            tipo75.setUnidade(produto.getUnidadeProduto().getSigla());
            tipo75.setAliquotaIpi(BigDecimal.ZERO.longValue());
            tipo75.setAliquotaIcms(BigDecimal.ZERO.longValue());

            listaTipo75.add(tipo75);
        }

        // REGISTRO TIPO 70 - Nota Fiscal de Serviço de Transporte E OUTRAS
        // REGISTRO TIPO 71 - Informações da Carga Transportada
        // REGISTRO TIPO 74 - REGISTRO DE INVENTÁRIO
        // REGISTRO TIPO 76 - NOTA FISCAL DE SERVIÇOS DE COMUNICAÇÃO E OUTRAS
        // REGISTRO TIPO 77 - SERVIÇOS DE COMUNICAÇÃO E TELECOMUNICAÇÃO
        // REGISTRO TIPO 85 - Informações de Exportações
        // REGISTRO TIPO 86 - Informações Complementares de Exportações
        // Implementados a critério do Participante do T2Ti ERP
        for (int i = 0; i < listaTipo75.size(); i++) {
            ff.addRecord(listaTipo75.get(i).getRecord());
        }

        //registro tipo 90
        totalRegistrosArquivo++;
        totalRegistrosArquivo += listaTipo75.size();
        DecimalFormat formatoTotal = new DecimalFormat("00000000");

        Tipo90 tipo90 = new Tipo90(ff.createRecord("Tipo90"));
        tipo90.setCNPJ(Long.parseLong(empresa.getCnpj()));
        tipo90.setInscricaoEstadual(empresa.getInscricaoEstadual());
        tipo90.setTotalizadores("50" + formatoTotal.format(totalRegistros50)
                + "54" + formatoTotal.format(totalRegistros54)
                + "60" + formatoTotal.format(totalRegistros60)
                + "61" + formatoTotal.format(totalRegistros61)
                + "75" + formatoTotal.format(listaTipo75.size())
                + "99" + formatoTotal.format(totalRegistrosArquivo));
        tipo90.setTotalRegistrosTipo90(1);

        ff.addRecord(tipo90.getRecord());
        List<String> linhasArquivo = new ArrayList<>();

        try {
            linhasArquivo = ff.write();
        } catch(Exception e){
            linhasArquivo.add(tipo10.getRecord().write());
            linhasArquivo.add(tipo11.getRecord().write());
            if (totalRegistros50 == 0){
                linhasArquivo.add("Não foram encontrados registros TIPO50 para o período informado");
            }
            if (totalRegistros54 == 0){
                linhasArquivo.add("Não foram encontrados registros TIPO54 para o período informado");
            }
            if (totalRegistros60 == 0){
                linhasArquivo.add("Não foram encontrados registros TIPO60 para o período informado");
            }
            if (totalRegistros61 == 0){
                linhasArquivo.add("Não foram encontrados registros TIPO61 para o período informado");
            }
            if (listaTipo75.isEmpty()){
                linhasArquivo.add("Não foram encontrados registros TIPO75 para o período informado");
            }
            linhasArquivo.add(tipo90.getRecord().write());
        }
        
        File arquivo = File.createTempFile("sintegra", ".txt");
        arquivo.deleteOnExit();

        FileUtils.writeLines(arquivo, linhasArquivo, System.getProperty("line.separator"));

        return arquivo;
    }
}