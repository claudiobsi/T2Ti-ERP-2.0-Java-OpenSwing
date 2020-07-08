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

import com.t2tierp.ponto.afd.ImportaArquivoAFD;
import com.t2tierp.ponto.java.AFDTipo3VO;
import com.t2tierp.ponto.java.PontoMarcacaoVO;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import org.openswing.swing.mdi.client.MDIFrame;
import org.openswing.swing.message.receive.java.Response;
import org.openswing.swing.message.receive.java.VOListResponse;
import org.openswing.swing.message.receive.java.ValueObject;
import org.openswing.swing.table.client.GridController;
import org.openswing.swing.table.java.GridDataLocator;
import org.openswing.swing.util.client.ClientUtils;

public class PontoImportaMarcacaoController extends GridController implements GridDataLocator {

    private PontoImportaMarcacao grid;
    private String acaoServidor;

    public PontoImportaMarcacaoController() {
        grid = new PontoImportaMarcacao(this);
        acaoServidor = "pontoImportaMarcacaoAction";
        MDIFrame.add(grid, true);
    }

    public Response loadData(int action, int startIndex, Map filteredColumns, ArrayList currentSortedColumns, ArrayList currentSortedVersusColumns, Class valueObjectType, Map otherGridParams) {
        try {
            JFileChooser chooser = new JFileChooser();
            int resposta = chooser.showOpenDialog(grid);
            if (resposta == JFileChooser.APPROVE_OPTION) {
                ImportaArquivoAFD importa = new ImportaArquivoAFD();
                List<AFDTipo3VO> listaTipo3 = importa.leArquivoAFD(chooser.getSelectedFile());
                return new VOListResponse(listaTipo3, false, listaTipo3.size());
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(grid, "Ocorreu um erro ao importar o arquivo!\n" + e.getMessage(), "Erro do sistema", JOptionPane.ERROR_MESSAGE);
        }

        return new VOListResponse();
    }

    @Override
    public void loadDataCompleted(boolean error) {
        salvaRegistros();
    }

    @Override
    public void doubleClick(int rowNumber, ValueObject persistentObject) {
        if (JOptionPane.showConfirmDialog(grid, "Desconsiderar este registro?", "Pergunta do Sistema", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            ((AFDTipo3VO) persistentObject).setDesconsiderar(true);
        } else {
            ((AFDTipo3VO) persistentObject).setDesconsiderar(false);
        }
    }

    public void importaMarcacao() {
        grid.getGridImportaArquivo().reloadData();
    }

    public void salvaRegistros() {
        List<AFDTipo3VO> importacoes = grid.getGridImportaArquivo().getVOListTableModel().getDataVector();
        if (!importacoes.isEmpty()) {
            for (int i = (importacoes.size() - 1); i > 0 ; i--){
                if (importacoes.get(i).getSequencial().intValue() == 0){
                    importacoes.remove(i);
                }
            }

            List<PontoMarcacaoVO> marcacoes = grid.getGridMarcacao().getVOListTableModel().getDataVector();
            AFDTipo3VO tipo3;
            for (int i = 0; i < marcacoes.size(); i++) {
                tipo3 = new AFDTipo3VO();
                //tipo3.setColaborador(marcacoes.get(i).getColaborador());
                tipo3.setPisEmpregado(marcacoes.get(i).getColaborador().getPisNumero());
                tipo3.setDataMarcacao(marcacoes.get(i).getDataMarcacao());
                tipo3.setHoraMarcacao(marcacoes.get(i).getHoraMarcacao());
                //tipo3.setSequencial(0l);
                tipo3.setTipoRegistro("I");
                tipo3.setJustificativa(marcacoes.get(i).getJustificativa());
                tipo3.setDesconsiderar(false);

                importacoes.add(tipo3);
            }

            Response res = ClientUtils.getData(acaoServidor, importacoes);
            if (res.isError()) {
                JOptionPane.showMessageDialog(grid, "Ocorreu um erro ao salvar os registros!\n" + res.getErrorMessage(), "Erro do Sistema", JOptionPane.ERROR_MESSAGE);
            } else {
                grid.getGridImportaArquivo().clearData();
                importacoes = ((VOListResponse) res).getRows();
                for (int i = 0; i < importacoes.size(); i++) {
                    grid.getGridImportaArquivo().getVOListTableModel().addObject(importacoes.get(i));
                }
            }
        }
    }
}
