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

import com.t2tierp.padrao.java.Constantes;
import com.t2tierp.ponto.acjef.GeraArquivoACJEF;
import com.t2tierp.ponto.afdt.GeraArquivoAFDT;
import com.t2tierp.ponto.java.PontoFechamentoJornadaVO;
import com.t2tierp.ponto.java.PontoHorarioVO;
import com.t2tierp.ponto.java.PontoMarcacaoVO;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import org.openswing.swing.mdi.client.MDIFrame;
import org.openswing.swing.message.receive.java.ErrorResponse;
import org.openswing.swing.message.receive.java.Response;
import org.openswing.swing.message.receive.java.VOListResponse;
import org.openswing.swing.message.send.java.GridParams;
import org.openswing.swing.table.client.GridController;
import org.openswing.swing.table.java.GridDataLocator;
import org.openswing.swing.util.client.ClientUtils;

public class PontoGeracaoArquivoGridController extends GridController implements GridDataLocator {

    private PontoGeracaoArquivoGrid grid;
    private String acaoServidor;
    private Date dataInicial;
    private Date dataFinal;

    public PontoGeracaoArquivoGridController() {
        grid = new PontoGeracaoArquivoGrid(this);
        acaoServidor = "pontoGeracaoArquivoGridAction";
        MDIFrame.add(grid, true);
    }

    public Response loadData(int action, int startIndex, Map filteredColumns, ArrayList currentSortedColumns, ArrayList currentSortedVersusColumns, Class valueObjectType, Map otherGridParams) {
        try {
            grid.getPeriodo();
        } catch (Exception ex) {
            return new ErrorResponse(ex.getMessage());
        }
        //define os parametros da grid
        otherGridParams.put("acao", Constantes.LOAD);
        otherGridParams.put("dataInicial", dataInicial);
        otherGridParams.put("dataFinal", dataFinal);

        return ClientUtils.getData(acaoServidor, new GridParams(action, startIndex, filteredColumns, currentSortedColumns, currentSortedVersusColumns, otherGridParams));
    }

    @Override
    public void loadDataCompleted(boolean error) {
        grid.getGridFechamento().reloadData();
    }

    public void geraArquivoAFDT() {
        try {
            JFileChooser chooser = new JFileChooser();
            int resposta = chooser.showOpenDialog(grid);
            if (resposta == JFileChooser.APPROVE_OPTION) {
                List<PontoMarcacaoVO> marcacoes = grid.getGridMarcacao().getVOListTableModel().getDataVector();
                if (marcacoes.isEmpty()) {
                    JOptionPane.showMessageDialog(grid, "Nenhum registro para ser gerado!", "Informação do Sistema", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    GeraArquivoAFDT geraArquivo = new GeraArquivoAFDT();
                    geraArquivo.geraArquivoAFDT(chooser.getSelectedFile(), dataInicial, dataFinal, marcacoes);

                    JOptionPane.showMessageDialog(grid, "Arquivo gerado com sucesso!", "Informação do Sistema", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(grid, "Ocorreu um erro ao gerar o arquivo!\n" + e.getMessage(), "Erro do Sistema", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void geraArquivoACJEF() {
        try {
            JFileChooser chooser = new JFileChooser();
            int resposta = chooser.showOpenDialog(grid);
            if (resposta == JFileChooser.APPROVE_OPTION) {
                //define os parametros da grid
                Map otherGridParams = new HashMap();
                otherGridParams.put("acao", Constantes.LOAD);
                GridParams pars = new GridParams(0, 0, null, null, null, otherGridParams);
                Response res = ClientUtils.getData("pontoHorarioGridAction", pars);
                if (res.isError()) {
                    throw new Exception(res.getErrorMessage());
                }
                List<PontoHorarioVO> horarios = ((VOListResponse) res).getRows();

                List<PontoFechamentoJornadaVO> fechamento = grid.getGridFechamento().getVOListTableModel().getDataVector();

                if (horarios.isEmpty()) {
                    JOptionPane.showMessageDialog(grid, "Nenhum horário cadastrado!", "Informação do Sistema", JOptionPane.INFORMATION_MESSAGE);
                } else if (fechamento.isEmpty()) {
                    JOptionPane.showMessageDialog(grid, "Nenhum registro de fechamento da jornada foi encontrado!", "Informação do Sistema", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    GeraArquivoACJEF geraArquivo = new GeraArquivoACJEF();
                    geraArquivo.geraArquivoACJEF(chooser.getSelectedFile(), dataInicial, dataFinal, horarios, fechamento);

                    JOptionPane.showMessageDialog(grid, "Arquivo gerado com sucesso!", "Informação do Sistema", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(grid, "Ocorreu um erro ao gerar o arquivo!\n" + e.getMessage(), "Erro do Sistema", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void setDataInicial(Date dataInicial) {
        this.dataInicial = dataInicial;
    }

    public void setDataFinal(Date dataFinal) {
        this.dataFinal = dataFinal;
    }
}
