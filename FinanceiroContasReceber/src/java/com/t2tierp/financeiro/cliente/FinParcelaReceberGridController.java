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
package com.t2tierp.financeiro.cliente;

import com.t2tierp.cadastros.java.ClienteVO;
import com.t2tierp.cadastros.java.ContaCaixaVO;
import com.t2tierp.cadastros.java.EmpresaVO;
import com.t2tierp.edi.cnab240.bb.GerarArquivoRemessaBB;
import com.t2tierp.financeiro.java.FinConfiguracaoBoletoVO;
import com.t2tierp.financeiro.java.FinLancamentoReceberVO;
import com.t2tierp.financeiro.java.FinParcelaReceberVO;
import com.t2tierp.padrao.cliente.Container;
import com.t2tierp.padrao.java.Constantes;
import java.io.File;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import org.jrimum.bopepo.BancosSuportados;
import org.jrimum.bopepo.Boleto;
import org.jrimum.bopepo.view.BoletoViewer;
import org.jrimum.domkee.comum.pessoa.endereco.CEP;
import org.jrimum.domkee.comum.pessoa.endereco.Endereco;
import org.jrimum.domkee.comum.pessoa.endereco.UnidadeFederativa;
import org.jrimum.domkee.financeiro.banco.febraban.Agencia;
import org.jrimum.domkee.financeiro.banco.febraban.Carteira;
import org.jrimum.domkee.financeiro.banco.febraban.Cedente;
import org.jrimum.domkee.financeiro.banco.febraban.ContaBancaria;
import org.jrimum.domkee.financeiro.banco.febraban.NumeroDaConta;
import org.jrimum.domkee.financeiro.banco.febraban.Sacado;
import org.jrimum.domkee.financeiro.banco.febraban.TipoDeTitulo;
import org.jrimum.domkee.financeiro.banco.febraban.Titulo;
import org.jrimum.domkee.financeiro.banco.febraban.Titulo.Aceite;
import org.openswing.swing.client.GridControl;
import org.openswing.swing.message.receive.java.Response;
import org.openswing.swing.message.receive.java.VOListResponse;
import org.openswing.swing.message.send.java.GridParams;
import org.openswing.swing.table.client.GridController;
import org.openswing.swing.table.java.GridDataLocator;
import org.openswing.swing.util.client.ClientUtils;
import org.openswing.swing.util.java.Consts;

public class FinParcelaReceberGridController extends GridController implements GridDataLocator {

    private String acaoServidor;
    private FinLancamentoReceberDetalhe finLancamentoReceberDetalhe;
    private String pk;

    public FinParcelaReceberGridController(FinLancamentoReceberDetalhe finLancamentoReceberDetalhe) {
        acaoServidor = "finParcelaReceberGridAction";
        this.finLancamentoReceberDetalhe = finLancamentoReceberDetalhe;
    }

    public Response loadData(int action, int startIndex, Map filteredColumns, ArrayList currentSortedColumns, ArrayList currentSortedVersusColumns, Class valueObjectType, Map otherGridParams) {
        //define os parametros da grid
        otherGridParams.put("acao", Constantes.LOAD);
        otherGridParams.put("idLancamentoReceber", pk);
        otherGridParams.put("somenteParcelasVencidas", false);

        return ClientUtils.getData(acaoServidor, new GridParams(action, startIndex, filteredColumns, currentSortedColumns, currentSortedVersusColumns, otherGridParams));
    }

    @Override
    public boolean beforeEditGrid(GridControl grid) {
        if (finLancamentoReceberDetalhe.getFormLancamentoReceber().getMode() == Consts.READONLY) {
            finLancamentoReceberDetalhe.getFormLancamentoReceber().setMode(Consts.EDIT);
        }
        return true;
    }

    @Override
    public Response updateRecords(int[] rowNumbers, ArrayList oldPersistentObjects, ArrayList persistentObjects) throws Exception {
        return new VOListResponse(persistentObjects, false, persistentObjects.size());
    }

    public void gerarParcelas() throws Exception {
        ContaCaixaVO contaCaixa = ((FinParcelaReceberVO) finLancamentoReceberDetalhe.getFormContaCaixa().getVOModel().getValueObject()).getContaCaixa();
        if (contaCaixa == null || contaCaixa.getId() == null) {
            throw new Exception("É necessário informar a conta caixa para previsão das parcelas.");
        }
        List<FinParcelaReceberVO> listaParcelasReceber = finLancamentoReceberDetalhe.getGridControlParcelaReceber().getVOListTableModel().getDataVector();
        if (!listaParcelasReceber.isEmpty()) {
            if (JOptionPane.showConfirmDialog(finLancamentoReceberDetalhe, "As parcelas que foram geradas anteriormente serão excluídas!\nDeseja continuar?", "Pergunta do Sistema", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                excluiParcelas(listaParcelasReceber);
            }
        }

        //gera as parcelas
        finLancamentoReceberDetalhe.getGridControlParcelaReceber().getVOListTableModel().clear();
        if (finLancamentoReceberDetalhe.getFormLancamentoReceber().push()) {
            FinLancamentoReceberVO lancamentoReceber = (FinLancamentoReceberVO) finLancamentoReceberDetalhe.getFormLancamentoReceber().getVOModel().getValueObject();
            FinParcelaReceberVO parcelaReceber;
            Date dataEmissão = new Date();
            Calendar primeiroVencimento = Calendar.getInstance();
            primeiroVencimento.setTime(lancamentoReceber.getPrimeiroVencimento());
            BigDecimal valorParcela = lancamentoReceber.getValorAReceber().divide(BigDecimal.valueOf(lancamentoReceber.getQuantidadeParcela()), RoundingMode.HALF_DOWN);
            BigDecimal somaParcelas = BigDecimal.ZERO;
            BigDecimal residuo = BigDecimal.ZERO;
            String nossoNumero;
            DecimalFormat formatoNossoNumero4 = new DecimalFormat("0000");
            DecimalFormat formatoNossoNumero5 = new DecimalFormat("00000");
            SimpleDateFormat formatoNossoNumero6 = new SimpleDateFormat("DDD");
            Date dataAtual = new Date();
            for (int i = 0; i < lancamentoReceber.getQuantidadeParcela(); i++) {
                parcelaReceber = new FinParcelaReceberVO();
                parcelaReceber.setContaCaixa(contaCaixa);
                parcelaReceber.setNumeroParcela(i + 1);
                parcelaReceber.setDataEmissao(dataEmissão);
                if (i > 0) {
                    primeiroVencimento.add(Calendar.DAY_OF_MONTH, lancamentoReceber.getIntervaloEntreParcelas());
                }
                parcelaReceber.setDataVencimento(primeiroVencimento.getTime());
                parcelaReceber.setEmitiuBoleto("S");

                nossoNumero = formatoNossoNumero5.format(lancamentoReceber.getCliente().getId());
                nossoNumero += formatoNossoNumero5.format(parcelaReceber.getContaCaixa().getId());
                nossoNumero += formatoNossoNumero4.format(parcelaReceber.getNumeroParcela());
                nossoNumero += formatoNossoNumero6.format(dataAtual);
                parcelaReceber.setBoletoNossoNumero(nossoNumero);

                parcelaReceber.setValor(valorParcela);

                somaParcelas = somaParcelas.add(valorParcela);
                if (i == (lancamentoReceber.getQuantidadeParcela() - 1)) {
                    residuo = lancamentoReceber.getValorAReceber().subtract(somaParcelas);
                    valorParcela = valorParcela.add(residuo);
                    parcelaReceber.setValor(valorParcela);
                }
                finLancamentoReceberDetalhe.getGridControlParcelaReceber().getVOListTableModel().addObject(parcelaReceber);
            }
            if (finLancamentoReceberDetalhe.getFormLancamentoReceber().getMode() == Consts.READONLY) {
                finLancamentoReceberDetalhe.getFormLancamentoReceber().setMode(Consts.EDIT);
            }
        }
    }

    private void excluiParcelas(List<FinParcelaReceberVO> parcelasReceber) throws Exception {
        if (parcelasReceber.get(0).getId() != null) {
            //exclui as parcelas existentes
            Map otherGridParams = new HashMap();
            otherGridParams.put("acao", Constantes.DELETE);
            otherGridParams.put("persistentObjects", parcelasReceber);
            GridParams pars = new GridParams(0, 0, null, null, null, otherGridParams);
            Response res = ClientUtils.getData(acaoServidor, pars);
            if (res.isError()) {
                throw new Exception(res.getErrorMessage());
            }
        }
    }

    public void gerarBoleto() throws Exception {
        List<FinParcelaReceberVO> listaParcelasReceber = finLancamentoReceberDetalhe.getGridControlParcelaReceber().getVOListTableModel().getDataVector();
        if (listaParcelasReceber.isEmpty()) {
            throw new Exception("Nenhuma parcela para gerar boleto.");
        }
        ContaCaixaVO contaCaixa = listaParcelasReceber.get(0).getContaCaixa();
        if (contaCaixa.getAgenciaBanco() == null) {
            throw new Exception("A conta/caixa não está vinculada a um banco. Geração de boletos não permitida.");
        }
        List<FinParcelaReceberVO> listaParcelasBoleto = new ArrayList<FinParcelaReceberVO>();
        for (int i = 0; i < listaParcelasReceber.size(); i++) {
            if (listaParcelasReceber.get(i).getEmitiuBoleto().equals("S")) {
                listaParcelasBoleto.add(listaParcelasReceber.get(i));
            }
        }
        if (listaParcelasBoleto.isEmpty()) {
            throw new Exception("Nenhuma parcela selecionada para gerar boleto.");
        }

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        if (fileChooser.showOpenDialog(finLancamentoReceberDetalhe) == JFileChooser.APPROVE_OPTION) {

            FinConfiguracaoBoletoVO configuracaoBoleto = configuracaoBoleto(listaParcelasReceber.get(0).getContaCaixa());
            FinLancamentoReceberVO lancamentoReceber = (FinLancamentoReceberVO) finLancamentoReceberDetalhe.getFormLancamentoReceber().getVOModel().getValueObject();
            ClienteVO cliente = lancamentoReceber.getCliente();
            EmpresaVO empresa = (EmpresaVO) Container.getContainer().get("empresa");
            SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");

            Cedente cedente = new Cedente(empresa.getRazaoSocial(), empresa.getCnpj());

            String cpfCnpjSacado;
            if (cliente.getPessoa().getTipo().equals("F")) {
                cpfCnpjSacado = cliente.getPessoa().getPessoaFisica().getCpf();
            } else {
                cpfCnpjSacado = cliente.getPessoa().getPessoaJuridica().getCnpj();
            }
            Sacado sacado = new Sacado(cliente.getPessoa().getNome(), cpfCnpjSacado);

            Endereco enderecoSacado = new Endereco();
            enderecoSacado.setUF(UnidadeFederativa.valueOfSigla(cliente.getPessoa().getListaEndereco().get(0).getUf()));
            enderecoSacado.setLocalidade(cliente.getPessoa().getListaEndereco().get(0).getCidade());
            enderecoSacado.setCep(new CEP(cliente.getPessoa().getListaEndereco().get(0).getCep()));
            enderecoSacado.setBairro(cliente.getPessoa().getListaEndereco().get(0).getBairro());
            enderecoSacado.setLogradouro(cliente.getPessoa().getListaEndereco().get(0).getLogradouro());
            enderecoSacado.setNumero(cliente.getPessoa().getListaEndereco().get(0).getNumero());
            sacado.addEndereco(enderecoSacado);

            ContaBancaria contaBancaria = new ContaBancaria(BancosSuportados.suportados.get(contaCaixa.getAgenciaBanco().getBanco().getCodigo()).create());
            contaBancaria.setNumeroDaConta(new NumeroDaConta(Integer.valueOf(contaCaixa.getCodigo()), contaCaixa.getDigito()));
            contaBancaria.setCarteira(new Carteira(Integer.valueOf(configuracaoBoleto.getCarteira())));
            contaBancaria.setAgencia(new Agencia(Integer.valueOf(contaCaixa.getAgenciaBanco().getCodigo()), contaCaixa.getAgenciaBanco().getDigito()));

            Titulo titulo;
            FinParcelaReceberVO parcela;
            Boleto boleto;
            List<Boleto> listaBoleto = new ArrayList<Boleto>();
            for (int i = 0; i < listaParcelasBoleto.size(); i++) {
                parcela = listaParcelasBoleto.get(i);

                titulo = new Titulo(contaBancaria, sacado, cedente);
                titulo.setNumeroDoDocumento(parcela.getBoletoNossoNumero().substring(0, 15));
                titulo.setNossoNumero(parcela.getBoletoNossoNumero());
                titulo.setDigitoDoNossoNumero("");
                titulo.setValor(parcela.getValor());
                titulo.setDataDoDocumento(parcela.getDataEmissao());
                titulo.setDataDoVencimento(parcela.getDataVencimento());
                if (configuracaoBoleto.getEspecie().equals("DM")) {
                    titulo.setTipoDeDocumento(TipoDeTitulo.DM_DUPLICATA_MERCANTIL);
                } else if (configuracaoBoleto.getEspecie().equals("DS")) {
                    titulo.setTipoDeDocumento(TipoDeTitulo.DS_DUPLICATA_DE_SERVICO);
                } else if (configuracaoBoleto.getEspecie().equals("RC")) {
                    titulo.setTipoDeDocumento(TipoDeTitulo.RC_RECIBO);
                } else if (configuracaoBoleto.getEspecie().equals("NP")) {
                    titulo.setTipoDeDocumento(TipoDeTitulo.NP_NOTA_PROMISSORIA);
                }
                if (configuracaoBoleto.getAceite().equals("S")) {
                    titulo.setAceite(Aceite.A);
                } else {
                    titulo.setAceite(Aceite.N);
                }
                titulo.setDesconto(parcela.getValorDesconto());
                //titulo.setDeducao(BigDecimal.ZERO);
                //titulo.setMora(BigDecimal.ZERO);
                //titulo.setAcrecimo(BigDecimal.ZERO);
                //titulo.setValorCobrado(BigDecimal.ZERO);

                boleto = new Boleto(titulo);
                boleto.setLocalPagamento(configuracaoBoleto.getLocalPagamento());
                boleto.setInstrucaoAoSacado(configuracaoBoleto.getMensagem());
                boleto.setInstrucao1(configuracaoBoleto.getInstrucao01());
                boleto.setInstrucao2(configuracaoBoleto.getInstrucao02());
                if (parcela.getDescontoAte() != null && parcela.getTaxaDesconto() != null) {
                    boleto.setInstrucao3("Para pagamento até o dia "
                            + formatoData.format(parcela.getDescontoAte())
                            + " conceder desconto de "
                            + parcela.getTaxaDesconto() + "%.");
                } else {
                    boleto.setInstrucao3("");
                }

                listaBoleto.add(boleto);
            }

            String nomeArquivo = fileChooser.getSelectedFile().getAbsolutePath() + System.getProperty("file.separator") + "boleto_" + cliente.getId() + ".pdf";
            BoletoViewer.groupInOnePDF(listaBoleto, nomeArquivo);
            JOptionPane.showMessageDialog(finLancamentoReceberDetalhe, "Boletos gerados com sucesso!\n" + nomeArquivo, "Informação do sistema", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private FinConfiguracaoBoletoVO configuracaoBoleto(ContaCaixaVO contaCaixa) throws Exception {
        //busca a configuracao do boleto
        Map otherGridParams = new HashMap();
        otherGridParams.put("acao", Constantes.LOAD);
        otherGridParams.put("contaCaixa", contaCaixa);
        GridParams pars = new GridParams(0, 0, null, null, null, otherGridParams);
        Response res = ClientUtils.getData("finConfiguracaoBoletoGridAction", pars);
        if (res.isError()) {
            throw new Exception(res.getErrorMessage());
        }
        List<FinConfiguracaoBoletoVO> listaConfiguracaoBoleto = ((VOListResponse) res).getRows();
        if (listaConfiguracaoBoleto.isEmpty()) {
            throw new Exception("Não existem configurações de boleto para a conta/caixa.");
        }
        if (listaConfiguracaoBoleto.size() == 1) {
            return listaConfiguracaoBoleto.get(0);
        } else {
            FinConfiguracaoBoletoVO configuracaoes[] = new FinConfiguracaoBoletoVO[listaConfiguracaoBoleto.size()];
            listaConfiguracaoBoleto.toArray(configuracaoes);
            FinConfiguracaoBoletoVO configuracao = null;
            while (configuracao == null) {
                configuracao = (FinConfiguracaoBoletoVO) JOptionPane.showInputDialog(null,
                        "Selecione uma Configuracao:",
                        "Solicitação do Sistema",
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        configuracaoes,
                        configuracaoes[0]);
            }
            return configuracao;
        }
    }

    public void setPk(String pk) {
        this.pk = pk;
    }

    public void gerarRemessa() {
        try {
            JFileChooser fileChooser = new JFileChooser();
            File file = new File("cnab240.txt");
            fileChooser.setSelectedFile(file);
            if (fileChooser.showSaveDialog(finLancamentoReceberDetalhe) == JFileChooser.APPROVE_OPTION) {
                file = fileChooser.getSelectedFile();
                List<FinParcelaReceberVO> listaParcelasReceber = finLancamentoReceberDetalhe.getGridControlParcelaReceber().getVOListTableModel().getDataVector();
                if (listaParcelasReceber.isEmpty()) {
                    JOptionPane.showMessageDialog(finLancamentoReceberDetalhe, "É necessário gerar as parcelas antes de gerar a remessa.", "Informação do Sistema", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                FinConfiguracaoBoletoVO configuracaoBoleto = configuracaoBoleto(listaParcelasReceber.get(0).getContaCaixa());
                EmpresaVO empresa = (EmpresaVO) Container.getContainer().get("empresa");
                GerarArquivoRemessaBB gerarArquivoRemessaBB = new GerarArquivoRemessaBB();
                gerarArquivoRemessaBB.gerarArquivoRemessa(listaParcelasReceber, empresa, configuracaoBoleto, file);

                JOptionPane.showMessageDialog(finLancamentoReceberDetalhe, "Remessa gerada com sucesso.\n" + file.getAbsolutePath(), "Informação do Sistema", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(finLancamentoReceberDetalhe, "Ocorreu um erro ao gerar o arquivo remessa.\n" + e.getMessage(), "Erro do Sistema", JOptionPane.ERROR_MESSAGE);
        }
    }
}
