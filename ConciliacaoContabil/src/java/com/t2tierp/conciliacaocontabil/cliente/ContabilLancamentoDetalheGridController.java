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
package com.t2tierp.conciliacaocontabil.cliente;

import com.t2tierp.contabilidade.java.ContabilLancamentoDetalheVO;
import com.t2tierp.padrao.java.Constantes;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.openswing.swing.client.GridControl;
import org.openswing.swing.message.receive.java.Response;
import org.openswing.swing.message.receive.java.VOListResponse;
import org.openswing.swing.message.send.java.GridParams;
import org.openswing.swing.table.client.GridController;
import org.openswing.swing.table.java.GridDataLocator;
import org.openswing.swing.util.client.ClientUtils;

public class ContabilLancamentoDetalheGridController extends GridController implements GridDataLocator {

    private String acaoServidor;
    private String pk;

    public ContabilLancamentoDetalheGridController() {
        acaoServidor = "contabilLancamentoDetalheGridAction";
    }

    public Response loadData(int action, int startIndex, Map filteredColumns, ArrayList currentSortedColumns, ArrayList currentSortedVersusColumns, Class valueObjectType, Map otherGridParams) {
        //define os parametros da grid
        otherGridParams.put("acao", Constantes.LOAD);
        otherGridParams.put("idLancamentoCabecalho", pk);

        return ClientUtils.getData(acaoServidor, new GridParams(action, startIndex, filteredColumns, currentSortedColumns, currentSortedVersusColumns, otherGridParams));
    }

    @Override
    public Response insertRecords(int[] rowNumbers, ArrayList newValueObjects) throws Exception {
        return new VOListResponse(newValueObjects, false, newValueObjects.size());
    }

    @Override
    public Response updateRecords(int[] rowNumbers, ArrayList oldPersistentObjects, ArrayList persistentObjects) throws Exception {
        return new VOListResponse(persistentObjects, false, persistentObjects.size());
    }

    @Override
    public Response deleteRecords(ArrayList persistentObjects) throws Exception {
        return new VOListResponse(persistentObjects, false, persistentObjects.size());
    }

    public void conciliaLancamentos(GridControl gridLancamentos){
        List<ContabilLancamentoDetalheVO> lancamentos = gridLancamentos.getVOListTableModel().getDataVector();
        BigDecimal totalCreditos = BigDecimal.ZERO;
        BigDecimal totalDebitos = BigDecimal.ZERO;
        String conciliado = "N";

        for (int i = 0; i < lancamentos.size(); i++){
            if (lancamentos.get(i).getTipo().equals("C")){
                totalCreditos = totalCreditos.add(lancamentos.get(i).getValor());
            } else if (lancamentos.get(i).getTipo().equals("D")) {
                totalDebitos = totalDebitos.add(lancamentos.get(i).getValor());
            }
        }

        if (totalCreditos.compareTo(totalDebitos) == 0){
            conciliado = "S";
        }

        for (int i = 0; i < lancamentos.size(); i++){
            lancamentos.get(i).setConciliado(conciliado);
        }
    }

    public void estorna(ContabilLancamentoDetalheVO lancamento){
        //Implementado a critério do Participante do T2Ti ERP
        lancamento.setConciliado("E");
    }

    public void complementa(ContabilLancamentoDetalheVO lancamento){
        //Implementado a critério do Participante do T2Ti ERP
        lancamento.setConciliado("C");
    }

    public void transfere(ContabilLancamentoDetalheVO lancamento){
        //Implementado a critério do Participante do T2Ti ERP
        lancamento.setConciliado("T");
    }

    /**
     * @param pk the pk to set
     */
    public void setPk(String pk) {
        this.pk = pk;
    }

}
