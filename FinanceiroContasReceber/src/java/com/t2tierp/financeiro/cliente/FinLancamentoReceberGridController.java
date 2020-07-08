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

import com.t2tierp.padrao.java.Constantes;
import com.t2tierp.financeiro.java.FinLancamentoReceberVO;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.openswing.swing.client.GridControl;
import org.openswing.swing.mdi.client.MDIFrame;
import org.openswing.swing.message.receive.java.Response;
import org.openswing.swing.message.receive.java.VOResponse;
import org.openswing.swing.message.receive.java.ValueObject;
import org.openswing.swing.message.send.java.GridParams;
import org.openswing.swing.table.client.GridController;
import org.openswing.swing.table.java.GridDataLocator;
import org.openswing.swing.util.client.ClientUtils;

public class FinLancamentoReceberGridController extends GridController implements GridDataLocator {

    private FinLancamentoReceberGrid grid;
    private String acaoServidor;

    public FinLancamentoReceberGridController() {
        grid = new FinLancamentoReceberGrid(this);
        acaoServidor = "finLancamentoReceberGridAction";
        MDIFrame.add(grid);
    }
    
    public Response loadData(int action, int startIndex, Map filteredColumns, ArrayList currentSortedColumns, ArrayList currentSortedVersusColumns, Class valueObjectType, Map otherGridParams) {
        //define os parametros da grid
        otherGridParams.put("acao", Constantes.LOAD);
        return ClientUtils.getData(acaoServidor, new GridParams(action, startIndex, filteredColumns, currentSortedColumns, currentSortedVersusColumns, otherGridParams));
    }

    @Override
    public boolean beforeInsertGrid(GridControl grid) {
        new FinLancamentoReceberDetalheController(this.grid, null);
        return false;
    }

    @Override
    public void doubleClick(int rowNumber, ValueObject persistentObject) {
        FinLancamentoReceberVO finLancamentoReceber = (FinLancamentoReceberVO) persistentObject;
        new FinLancamentoReceberDetalheController(grid, finLancamentoReceber.getId().toString());
    }

    @Override
    public Response deleteRecords(ArrayList persistentObjects) throws Exception {
        //define os parametros da grid
        Map otherGridParams = new HashMap();
        otherGridParams.put("acao", Constantes.DELETE);
        otherGridParams.put("persistentObjects", persistentObjects);

        //seta os parametros da grid
        GridParams pars = new GridParams(0, 0, null, null, null, otherGridParams);

        return ClientUtils.getData(acaoServidor, pars);
    }
    
    public void mesclarLancamentos() throws Exception {
        List<FinLancamentoReceberVO> lancamentos = grid.getGrid1().getVOListTableModel().getDataVector();
        List<FinLancamentoReceberVO> lancamentosSelecionados = new ArrayList<>();
        for (FinLancamentoReceberVO l : lancamentos) {
            if (l.isSelecionado()) {
                lancamentosSelecionados.add(l);
            }
        }
        
        if (lancamentosSelecionados.size() <=1) {
            throw new Exception("Necessário selecionar 2 ou mais lançamentos");
        }
        
        //define os parametros da grid
        Map otherGridParams = new HashMap();
        otherGridParams.put("acao", 99);
        otherGridParams.put("lancamentos", lancamentosSelecionados);
        //seta os parametros da grid
        GridParams pars = new GridParams(0, 0, null, null, null, otherGridParams);

        Response res = ClientUtils.getData(acaoServidor, pars);
        if (res.isError()) {
            throw new Exception("Erro ao mesclar lançamentos\n" + res.getErrorMessage());
        }
        
        FinLancamentoReceberVO finLancamentoReceber = (FinLancamentoReceberVO) ((VOResponse) res).getVo();
        new FinLancamentoReceberDetalheController(grid, finLancamentoReceber.getId().toString());
        
        grid.getGrid1().reloadData();
    }
    
}
