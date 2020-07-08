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
import com.t2tierp.ponto.java.PontoFechamentoJornadaVO;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JOptionPane;
import org.openswing.swing.message.receive.java.Response;
import org.openswing.swing.message.receive.java.ValueObject;
import org.openswing.swing.message.send.java.GridParams;
import org.openswing.swing.table.client.GridController;
import org.openswing.swing.table.java.GridDataLocator;
import org.openswing.swing.util.client.ClientUtils;

public class PontoProcessaFechamentoJornadaController extends GridController implements GridDataLocator {

    private String acaoServidor;
    private PontoImportaMarcacao grid;

    public PontoProcessaFechamentoJornadaController(PontoImportaMarcacao grid) {
        acaoServidor = "pontoProcessaFechamentoJornadaAction";
        this.grid = grid;
    }

    public Response loadData(int action, int startIndex, Map filteredColumns, ArrayList currentSortedColumns, ArrayList currentSortedVersusColumns, Class valueObjectType, Map otherGridParams) {
        //define os parametros da grid
        otherGridParams.put("acao", Constantes.LOAD);
        otherGridParams.put("marcacoes", grid.getGridImportaArquivo().getVOListTableModel().getDataVector());

        return ClientUtils.getData(acaoServidor, new GridParams(action, startIndex, filteredColumns, currentSortedColumns, currentSortedVersusColumns, otherGridParams));
    }

    public void gravaFechamento() {
        List<PontoFechamentoJornadaVO> listaFechamento = grid.getGridFechamento().getVOListTableModel().getDataVector();
        boolean registroPendente = false;

        if (!listaFechamento.isEmpty()) {
            for (int i = 0; i < listaFechamento.size(); i++) {
                if (listaFechamento.get(i).getObservacao() != null) {
                    registroPendente = true;
                    break;
                }
            }
            if (registroPendente) {
                JOptionPane.showMessageDialog(grid, "Existem registros de entrada e/ou saída pendentes!\nGravação não efetuada.", "Informação do Sistema", JOptionPane.INFORMATION_MESSAGE);
            } else {
                //define os parametros da grid
                Map otherGridParams = new HashMap();
                otherGridParams.put("acao", Constantes.INSERT);
                otherGridParams.put("listaFechamento", listaFechamento);
                GridParams pars = new GridParams(0, 0, null, null, null, otherGridParams);

                Response res = ClientUtils.getData(acaoServidor, pars);
                if (res.isError()) {
                    JOptionPane.showMessageDialog(grid, "Ocorreu um erro ao gravar o fechamento\n" + res.getErrorMessage(), "Erro do Sistema", JOptionPane.ERROR_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(grid, "Registros gravados com sucesso!", "Informação do Sistema", JOptionPane.INFORMATION_MESSAGE);
                    grid.dispose();
                }
            }
        } else {
            JOptionPane.showMessageDialog(grid, "Nenhum registro para gravar!", "Informação do Sistema", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    @Override
    public void doubleClick(int rowNumber, ValueObject persistentObject) {
        PontoFechamentoJornadaVO fechamentoJornada = (PontoFechamentoJornadaVO) persistentObject;
        String  mensagem = fechamentoJornada.getObservacao() == null ? "Registro OK" : fechamentoJornada.getObservacao();
        JOptionPane.showMessageDialog(grid, mensagem, "Observação", JOptionPane.INFORMATION_MESSAGE);
    }
}
