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
package com.t2tierp.ponto.cliente;

import com.t2tierp.cadastros.java.ColaboradorVO;
import com.t2tierp.cadastros.java.EmpresaVO;
import com.t2tierp.padrao.cliente.Container;
import com.t2tierp.padrao.java.Biblioteca;
import com.t2tierp.padrao.java.Constantes;
import com.t2tierp.ponto.java.EspelhoPontoPeriodoVO;
import com.t2tierp.ponto.java.EspelhoPontoVO;
import com.t2tierp.ponto.java.PontoClassificacaoJornadaVO;
import com.t2tierp.ponto.java.PontoFechamentoJornadaVO;
import com.t2tierp.ponto.java.PontoHorarioVO;
import com.t2tierp.ponto.java.PontoMarcacaoVO;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;
import org.openswing.swing.mdi.client.MDIFrame;
import org.openswing.swing.message.receive.java.ErrorResponse;
import org.openswing.swing.message.receive.java.Response;
import org.openswing.swing.message.receive.java.VOListResponse;
import org.openswing.swing.message.send.java.GridParams;
import org.openswing.swing.table.client.GridController;
import org.openswing.swing.table.java.GridDataLocator;
import org.openswing.swing.util.client.ClientUtils;
import org.openswing.swing.util.java.Consts;

public class PontoEspelhoDetalheController extends GridController implements GridDataLocator {

    private PontoEspelhoDetalhe pontoEspelhoDetalhe = null;
    private PontoEspelhoGrid pontoEspelhoGrid = null;
    private String acaoServidor;
    private ColaboradorVO colaborador;
    private Date dataInicio;
    private Date dataFim;

    public PontoEspelhoDetalheController(PontoEspelhoGrid pontoEspelhoGrid, ColaboradorVO colaborador) {
        this.pontoEspelhoGrid = pontoEspelhoGrid;
        this.acaoServidor = "pontoEspelhoDetalheAction";
        this.colaborador = colaborador;
        pontoEspelhoDetalhe = new PontoEspelhoDetalhe(this);
        pontoEspelhoDetalhe.setParentFrame(this.pontoEspelhoGrid);
        this.pontoEspelhoGrid.pushFrame(pontoEspelhoDetalhe);
        MDIFrame.add(pontoEspelhoDetalhe, true);

        pontoEspelhoDetalhe.getFormColaborador().setMode(Consts.READONLY);
        pontoEspelhoDetalhe.getFormColaborador().getVOModel().setValueObject(colaborador);
        pontoEspelhoDetalhe.getFormColaborador().pull();
    }

    public Response loadData(int action, int startIndex, Map filteredColumns, ArrayList currentSortedColumns, ArrayList currentSortedVersusColumns, Class valueObjectType, Map otherGridParams) {
        try {
            getPeriodo();
        } catch (Exception ex) {
            return new ErrorResponse(ex.getMessage());
        }
        //define os parametros da grid
        otherGridParams.put("acao", Constantes.LOAD);
        otherGridParams.put("colaborador", colaborador);
        otherGridParams.put("dataInicio", dataInicio);
        otherGridParams.put("dataFim", dataFim);

        //seta os parametros da grid
        GridParams pars = new GridParams(0, 0, null, null, null, otherGridParams);

        return ClientUtils.getData(acaoServidor, pars);
    }

    private void getPeriodo() throws Exception {
        String periodo = pontoEspelhoDetalhe.getPeriodo();
        try {
            int mes = Integer.valueOf(periodo.substring(0, 2));
            int ano = Integer.valueOf(periodo.substring(3, 7));

            if (!Biblioteca.validaData(1, mes, ano)) {
                throw new Exception("Período inválido!");
            }
            Calendar c = Calendar.getInstance();
            c.set(Calendar.DAY_OF_MONTH, 1);
            c.set(Calendar.MONTH, mes - 1);
            c.set(Calendar.YEAR, ano);
            dataInicio = c.getTime();
            c.add(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH) - 1);
            dataFim = c.getTime();
        } catch (Exception e) {
            throw new Exception("Período inválido!");
        }
    }

    public void classificarDia() {
        Date dataclassificar = pontoEspelhoDetalhe.getDataClassificar();
        PontoClassificacaoJornadaVO classificacao = pontoEspelhoDetalhe.getClassificacao();
        if (dataclassificar == null) {
            JOptionPane.showMessageDialog(pontoEspelhoDetalhe, "Selecione a data de classificação da jornada", "Informação do Sistema", JOptionPane.INFORMATION_MESSAGE);
        } else if (classificacao == null) {
            JOptionPane.showMessageDialog(pontoEspelhoDetalhe, "Selecione a classificação da jornada", "Informação do Sistema", JOptionPane.INFORMATION_MESSAGE);
        } else {
            if (JOptionPane.showConfirmDialog(pontoEspelhoDetalhe, "Deseja classificar este dia?\n" + dataclassificar.toString(), "Pergunta do Sistema", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                //define os parametros da grid
                Map otherGridParams = new HashMap();
                otherGridParams.put("acao", Constantes.INSERT);
                otherGridParams.put("usuario", Container.getContainer().get("usuario"));
                otherGridParams.put("colaborador", colaborador);
                otherGridParams.put("dataClassificar", dataclassificar);
                otherGridParams.put("classificacao", classificacao);

                //seta os parametros da grid
                GridParams pars = new GridParams(0, 0, null, null, null, otherGridParams);

                Response res = ClientUtils.getData(acaoServidor, pars);
                if (res.isError()) {
                    JOptionPane.showMessageDialog(pontoEspelhoDetalhe, "Ocorreu um erro ao classificar o dia!\n" + res.getErrorMessage(), "Erro do Sistema", JOptionPane.ERROR_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(pontoEspelhoDetalhe, "Classificação realizada com sucesso!", "Informação do Sistema", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        }
    }

    public void geraRelatório() throws Exception {
        List<EspelhoPontoVO> listaEspelho = new ArrayList<EspelhoPontoVO>();

        EmpresaVO empresa = (EmpresaVO) Container.getContainer().get("empresa");
        EspelhoPontoVO espelho = new EspelhoPontoVO();

        espelho.setCnpjEmpregador(empresa.getCnpj());
        espelho.setNomeEmpregador(empresa.getRazaoSocial());
        espelho.setEderecoEmpregador(empresa.getListaEndereco().get(0).getLogradouro());
        espelho.setDataEmissaoRelatorio(new Date());
        espelho.setPisEmpregado(colaborador.getPisNumero());
        espelho.setNomeEmpregado(colaborador.getPessoa().getNome());
        espelho.setDataAdmissao(colaborador.getDataAdmissao());

        //busca os horarios do colaborador
        Map otherGridParams = new HashMap();
        otherGridParams.put("acao", 98);
        otherGridParams.put("colaborador", colaborador);
        GridParams pars = new GridParams(0, 0, null, null, null, otherGridParams);
        Response res = ClientUtils.getData(acaoServidor, pars);
        if (res.isError()) {
            throw new Exception(res.getErrorMessage());
        }
        List<PontoHorarioVO> listaHorario = ((VOListResponse) res).getRows();

        //busca os registro de marcacoes do colaborador
        otherGridParams.put("acao", 99);
        otherGridParams.put("colaborador", colaborador);
        otherGridParams.put("dataInicio", dataInicio);
        otherGridParams.put("dataFim", dataFim);
        pars = new GridParams(0, 0, null, null, null, otherGridParams);
        res = ClientUtils.getData(acaoServidor, pars);
        if (res.isError()) {
            throw new Exception(res.getErrorMessage());
        }
        List<PontoMarcacaoVO> listaMarcacoes = ((VOListResponse) res).getRows();

        List<EspelhoPontoPeriodoVO> listaPeriodo = new ArrayList<EspelhoPontoPeriodoVO>();
        List<PontoMarcacaoVO> marcacoesTratadas;
        PontoMarcacaoVO marcacao;
        EspelhoPontoPeriodoVO periodo;
        Calendar dataMarcacao = Calendar.getInstance();

        List<PontoFechamentoJornadaVO> listaFechamento = pontoEspelhoDetalhe.getGridControlFechamento().getVOListTableModel().getDataVector();
        for (int i = 0; i < listaFechamento.size(); i++) {
            dataMarcacao.setTime(listaFechamento.get(i).getDataFechamento());
            periodo = new EspelhoPontoPeriodoVO();
            periodo.setDia(dataMarcacao.get(Calendar.DAY_OF_MONTH));
            periodo.setCodigoHorario(listaFechamento.get(i).getCodigoHorario());
            marcacoesTratadas = new ArrayList<PontoMarcacaoVO>();

            for (int j = 0; j < listaMarcacoes.size(); j++) {
                marcacao = listaMarcacoes.get(j);
                if (marcacao.getDataMarcacao().compareTo(listaFechamento.get(i).getDataFechamento()) == 0) {
                    if (marcacao.getTipoRegistro().equals("O")) {
                        if (!marcacao.getTipoMarcacao().equals("D")) {
                            if (marcacao.getParEntradaSaida().equals("E1")) {
                                periodo.setMarcacaoEntrada01(marcacao.getHoraMarcacao());
                            }
                            if (marcacao.getParEntradaSaida().equals("S1")) {
                                periodo.setMarcacaoSaida01(marcacao.getHoraMarcacao());
                            }
                            if (marcacao.getParEntradaSaida().equals("E2")) {
                                periodo.setMarcacaoEntrada02(marcacao.getHoraMarcacao());
                            }
                            if (marcacao.getParEntradaSaida().equals("S2")) {
                                periodo.setMarcacaoSaida02(marcacao.getHoraMarcacao());
                            }
                        }
                    } else {
                        marcacoesTratadas.add(marcacao);
                    }
                }
            }
            periodo.setJornadaEntrada01(listaFechamento.get(i).getEntrada01());
            periodo.setJornadaSaida01(listaFechamento.get(i).getSaida01());
            periodo.setJornadaEntrada02(listaFechamento.get(i).getEntrada02());
            periodo.setJornadaSaida02(listaFechamento.get(i).getSaida02());
            periodo.setJornadaEntrada03(listaFechamento.get(i).getEntrada03());
            periodo.setJornadaSaida03(listaFechamento.get(i).getSaida03());
            periodo.setMarcacoesTratadas(marcacoesTratadas);
            
            listaPeriodo.add(periodo);
        }

        espelho.setListaHorarios(listaHorario);
        espelho.setListaPeriodo(listaPeriodo);
        listaEspelho.add(espelho);

        //gera o relatorio
        Map parametros = new HashMap();
        parametros.put("SUBREPORT_DIR", this.getClass().getResource("/com/t2tierp/ponto/espelho/").toString());
        parametros.put("DATA_INICIO", dataInicio);
        parametros.put("DATA_FIM", dataFim);

        JRBeanCollectionDataSource jrbean = new JRBeanCollectionDataSource(listaEspelho);
        JasperPrint jp = JasperFillManager.fillReport(this.getClass().getResourceAsStream("/com/t2tierp/ponto/espelho/espelho_ponto.jasper") , parametros, jrbean);
        JasperViewer.viewReport(jp, false);
    }
}
