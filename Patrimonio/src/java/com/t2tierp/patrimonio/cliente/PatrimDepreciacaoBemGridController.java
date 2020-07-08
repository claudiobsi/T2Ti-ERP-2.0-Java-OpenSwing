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
package com.t2tierp.patrimonio.cliente;

import com.t2tierp.padrao.java.Constantes;
import com.t2tierp.patrimonio.java.PatrimBemVO;
import com.t2tierp.patrimonio.java.PatrimDepreciacaoBemVO;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Map;
import org.openswing.swing.client.GridControl;
import org.openswing.swing.message.receive.java.Response;
import org.openswing.swing.message.receive.java.VOListResponse;
import org.openswing.swing.message.send.java.GridParams;
import org.openswing.swing.table.client.GridController;
import org.openswing.swing.table.java.GridDataLocator;
import org.openswing.swing.util.client.ClientUtils;
import org.openswing.swing.util.java.Consts;

public class PatrimDepreciacaoBemGridController extends GridController implements GridDataLocator {

    private String acaoServidor;
    private String pk;
    private PatrimBemDetalhe patrimBemDetalhe;

    public PatrimDepreciacaoBemGridController(PatrimBemDetalhe patrimBemDetalhe) {
        acaoServidor = "patrimDepreciacaoBemGridAction";
        this.patrimBemDetalhe = patrimBemDetalhe;
    }

    public Response loadData(int action, int startIndex, Map filteredColumns, ArrayList currentSortedColumns, ArrayList currentSortedVersusColumns, Class valueObjectType, Map otherGridParams) {
        //define os parametros da grid
        otherGridParams.put("acao", Constantes.LOAD);
        otherGridParams.put("idBem", pk);

        return ClientUtils.getData(acaoServidor, new GridParams(action, startIndex, filteredColumns, currentSortedColumns, currentSortedVersusColumns, otherGridParams));
    }

    @Override
    public boolean beforeInsertGrid(GridControl grid) {
        if (patrimBemDetalhe.getForm1().getMode() == Consts.READONLY) {
            patrimBemDetalhe.getForm1().setMode(Consts.EDIT);
        }
        return true;
    }

    @Override
    public Response insertRecords(int[] rowNumbers, ArrayList newValueObjects) throws Exception {
        return new VOListResponse(newValueObjects, false, newValueObjects.size());
    }

    @Override
    public boolean beforeEditGrid(GridControl grid) {
        if (patrimBemDetalhe.getForm1().getMode() == Consts.READONLY) {
            patrimBemDetalhe.getForm1().setMode(Consts.EDIT);
        }
        return true;
    }

    @Override
    public Response updateRecords(int[] rowNumbers, ArrayList oldPersistentObjects, ArrayList persistentObjects) throws Exception {
        return new VOListResponse(persistentObjects, false, persistentObjects.size());
    }

    @Override
    public boolean beforeDeleteGrid(GridControl grid) {
        if (patrimBemDetalhe.getForm1().getMode() == Consts.READONLY) {
            patrimBemDetalhe.getForm1().setMode(Consts.EDIT);
        }
        return true;
    }

    @Override
    public Response deleteRecords(ArrayList persistentObjects) throws Exception {
        return new VOListResponse(persistentObjects, false, persistentObjects.size());
    }

    /**
     * @param pk the pk to set
     */
    public void setPk(String pk) {
        this.pk = pk;
    }

    public void calculaDepreciacao() throws Exception {
        PatrimBemVO bem = (PatrimBemVO) patrimBemDetalhe.getForm1().getVOModel().getValueObject();

        Calendar dataAtual = Calendar.getInstance();
        PatrimDepreciacaoBemVO depreciacao = new PatrimDepreciacaoBemVO();

        depreciacao.setDataDepreciacao(dataAtual.getTime());
        depreciacao.setDias(dataAtual.get(Calendar.DAY_OF_MONTH));

        String tipoDepreciacao = bem.getTipoDepreciacao();
        //Normal
        if (tipoDepreciacao.equals("N")){
            if (bem.getTaxaMensalDepreciacao() == null){
                throw new Exception("Taxa mensal de depreciação não definida!");
            }
            depreciacao.setIndice(BigDecimal.valueOf(depreciacao.getDias().doubleValue() / 30).multiply(bem.getTaxaMensalDepreciacao()));
            depreciacao.setTaxa(bem.getTaxaMensalDepreciacao());
        }

        //Acelerada
        if (tipoDepreciacao.equals("A")){
            if (bem.getTaxaDepreciacaoAcelerada() == null){
                throw new Exception("Taxa de depreciação acelerada não definida!");
            }
            depreciacao.setIndice(BigDecimal.valueOf(depreciacao.getDias().doubleValue() / 30).multiply(bem.getTaxaDepreciacaoAcelerada()));
            depreciacao.setTaxa(bem.getTaxaDepreciacaoAcelerada());
        }
        
        //Incentivada
        if (tipoDepreciacao.equals("I")){
            if (bem.getTaxaDepreciacaoIncentivada() == null){
                throw new Exception("Taxa de depreciação incentivada não definida!");
            }
            depreciacao.setIndice(BigDecimal.valueOf(depreciacao.getDias().doubleValue() / 30).multiply(bem.getTaxaDepreciacaoIncentivada()));
            depreciacao.setTaxa(bem.getTaxaDepreciacaoIncentivada());
        }

        if (bem.getValorOriginal() == null){
            throw new Exception("Valor Original do bem não definido!");
        }
        depreciacao.setValor(bem.getValorOriginal().multiply(depreciacao.getIndice()));
        depreciacao.setDepreciacaoAcumulada(bem.getValorOriginal().subtract(depreciacao.getValor()));

        patrimBemDetalhe.getGridDepreciacao().getVOListTableModel().addObject(depreciacao);
    }
}
