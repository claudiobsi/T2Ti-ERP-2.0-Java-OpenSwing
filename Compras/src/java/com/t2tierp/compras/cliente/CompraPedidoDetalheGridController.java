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
package com.t2tierp.compras.cliente;

import com.t2tierp.compras.java.CompraPedidoDetalheVO;
import com.t2tierp.compras.java.CompraPedidoVO;
import com.t2tierp.padrao.java.Constantes;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.openswing.swing.client.GridControl;
import org.openswing.swing.message.receive.java.Response;
import org.openswing.swing.message.receive.java.VOListResponse;
import org.openswing.swing.message.send.java.GridParams;
import org.openswing.swing.table.client.GridController;
import org.openswing.swing.table.java.GridDataLocator;
import org.openswing.swing.util.client.ClientUtils;
import org.openswing.swing.util.java.Consts;

public class CompraPedidoDetalheGridController extends GridController implements GridDataLocator {

    private String acaoServidor;
    private String pk;
    private CompraPedidoDetalhe compraPedidoDetalhe;
    private boolean dadosAlterados;
    private boolean compraSugerida;

    public CompraPedidoDetalheGridController(CompraPedidoDetalhe compraPedidoDetalhe) {
        acaoServidor = "compraPedidoDetalheGridAction";
        this.compraPedidoDetalhe = compraPedidoDetalhe;
        dadosAlterados = false;
        compraSugerida = false;
    }

    public Response loadData(int action, int startIndex, Map filteredColumns, ArrayList currentSortedColumns, ArrayList currentSortedVersusColumns, Class valueObjectType, Map otherGridParams) {
        if (dadosAlterados) {
            //define os parametros da grid
            otherGridParams.put("acao", Constantes.LOAD);
            otherGridParams.put("idCompraPedido", pk);

            return ClientUtils.getData(acaoServidor, new GridParams(action, startIndex, filteredColumns, currentSortedColumns, currentSortedVersusColumns, otherGridParams));
        }
        CompraPedidoVO pedido = (CompraPedidoVO) compraPedidoDetalhe.getForm1().getVOModel().getValueObject();
        if (pedido.getListaCompraPedidoDetalhe() == null) {
            return new VOListResponse(new ArrayList<CompraPedidoDetalheVO>(), false, 0);
        }
        return new VOListResponse(pedido.getListaCompraPedidoDetalhe(), false, pedido.getListaCompraPedidoDetalhe().size());
    }

    @Override
    public void loadDataCompleted(boolean error) {
        if (compraSugerida){
            compraPedidoDetalhe.getController().incluiCompraSugerida();
            compraSugerida = false;
        }
    }

    @Override
    public boolean beforeInsertGrid(GridControl grid) {
        if (compraPedidoDetalhe.getForm1().getMode() == Consts.READONLY) {
            compraPedidoDetalhe.getForm1().setMode(Consts.EDIT);
        }
        return true;
    }

    @Override
    public Response insertRecords(int[] rowNumbers, ArrayList newValueObjects) throws Exception {
        // atualiza os totais
        CompraPedidoVO pedido = (CompraPedidoVO) compraPedidoDetalhe.getForm1().getVOModel().getValueObject();
        atualizaTotaisItens(newValueObjects);
        if (pedido.getId() != null) {
            dadosAlterados = true;
            //define os parametros da grid
            Map otherGridParams = new HashMap();
            otherGridParams.put("acao", Constantes.INSERT);
            otherGridParams.put("newValueObjects", newValueObjects);
            otherGridParams.put("compraPedido", pedido);
            //seta os parametros da grid
            GridParams pars = new GridParams(0, 0, null, null, null, otherGridParams);
            return ClientUtils.getData(acaoServidor, pars);
        }
        return new VOListResponse(newValueObjects, false, newValueObjects.size());
    }

    @Override
    public void afterInsertGrid(GridControl grid) {
        atualizaTotaisPedido();
    }

    @Override
    public boolean beforeEditGrid(GridControl grid) {
        if (compraPedidoDetalhe.getForm1().getMode() == Consts.READONLY) {
            compraPedidoDetalhe.getForm1().setMode(Consts.EDIT);
        }
        return true;
    }

    @Override
    public Response updateRecords(int[] rowNumbers, ArrayList oldPersistentObjects, ArrayList persistentObjects) throws Exception {
        CompraPedidoVO pedido = (CompraPedidoVO) compraPedidoDetalhe.getForm1().getVOModel().getValueObject();
        atualizaTotaisItens(persistentObjects);
        if (pedido.getId() != null) {
            dadosAlterados = true;
            //define os parametros da grid
            Map otherGridParams = new HashMap();
            otherGridParams.put("acao", Constantes.UPDATE);
            otherGridParams.put("persistentObjects", persistentObjects);
            //seta os parametros da grid
            GridParams pars = new GridParams(0, 0, null, null, null, otherGridParams);
            
            return ClientUtils.getData(acaoServidor, pars);
        }
        return new VOListResponse(persistentObjects, false, persistentObjects.size());
    }

    @Override
    public void afterEditGrid(GridControl grid) {
        atualizaTotaisPedido();
    }

    @Override
    public boolean beforeDeleteGrid(GridControl grid) {
        if (compraPedidoDetalhe.getForm1().getMode() == Consts.READONLY) {
            compraPedidoDetalhe.getForm1().setMode(Consts.EDIT);
        }
        return true;
    }

    @Override
    public Response deleteRecords(ArrayList persistentObjects) throws Exception {
        CompraPedidoVO pedido = (CompraPedidoVO) compraPedidoDetalhe.getForm1().getVOModel().getValueObject();
        if (pedido.getId() != null) {
            dadosAlterados = true;
            //remove os itens da lista de detalhes do pedido
            CompraPedidoDetalheVO pedidoDetalhe;
            for (int i = 0; i < persistentObjects.size(); i++) {
                pedidoDetalhe = (CompraPedidoDetalheVO) persistentObjects.get(i);
                pedido.getListaCompraPedidoDetalhe().remove(pedido.getListaCompraPedidoDetalhe().indexOf(pedidoDetalhe));
            }
            //define os parametros da grid
            Map otherGridParams = new HashMap();
            otherGridParams.put("acao", Constantes.DELETE);
            otherGridParams.put("persistentObjects", persistentObjects);
            //seta os parametros da grid
            GridParams pars = new GridParams(0, 0, null, null, null, otherGridParams);
            return ClientUtils.getData(acaoServidor, pars);
        }
        return new VOListResponse(persistentObjects, false, persistentObjects.size());
    }

    @Override
    public void afterDeleteGrid() {
        atualizaTotaisPedido();
    }

    public void atualizaTotaisItens(List<CompraPedidoDetalheVO> listaDetalhe) {
        CompraPedidoDetalheVO pedidoDetalhe;
        for (int i = 0; i < listaDetalhe.size(); i++) {
            pedidoDetalhe = listaDetalhe.get(i);
            pedidoDetalhe.setValorSubtotal(pedidoDetalhe.getQuantidade().multiply(pedidoDetalhe.getValorUnitario()));
            if (pedidoDetalhe.getTaxaDesconto() != null) {
                pedidoDetalhe.setValorDesconto(pedidoDetalhe.getValorSubtotal().multiply(pedidoDetalhe.getTaxaDesconto().divide(BigDecimal.valueOf(100), RoundingMode.HALF_DOWN)));
            } else {
                pedidoDetalhe.setValorDesconto(BigDecimal.ZERO);
                pedidoDetalhe.setTaxaDesconto(BigDecimal.ZERO);
            }
            pedidoDetalhe.setValorTotal(pedidoDetalhe.getValorSubtotal().subtract(pedidoDetalhe.getValorDesconto()));
            pedidoDetalhe.setBaseCalculoIcms(pedidoDetalhe.getValorTotal());
            //icms
            if (pedidoDetalhe.getAliquotaIcms() != null) {
                pedidoDetalhe.setValorIcms(pedidoDetalhe.getBaseCalculoIcms().multiply(pedidoDetalhe.getAliquotaIcms().divide(BigDecimal.valueOf(100), RoundingMode.HALF_DOWN)));
            } else {
                pedidoDetalhe.setAliquotaIcms(BigDecimal.ZERO);
                pedidoDetalhe.setValorIcms(BigDecimal.ZERO);
            }
            //ipi
            if (pedidoDetalhe.getAliquotaIpi() != null) {
                pedidoDetalhe.setValorIpi(pedidoDetalhe.getValorTotal().multiply(pedidoDetalhe.getAliquotaIpi().divide(BigDecimal.valueOf(100), RoundingMode.HALF_DOWN)));
            } else {
                pedidoDetalhe.setAliquotaIpi(BigDecimal.ZERO);
                pedidoDetalhe.setValorIpi(BigDecimal.ZERO);
            }
        }
    }

    public void atualizaTotaisPedido() {
        List<CompraPedidoDetalheVO> listaDetalhe = compraPedidoDetalhe.getGridControl1().getVOListTableModel().getDataVector();
        CompraPedidoDetalheVO pedidoDetalhe;
        BigDecimal subTotal = BigDecimal.ZERO;
        BigDecimal totalDesconto = BigDecimal.ZERO;
        BigDecimal totalGeral = BigDecimal.ZERO;
        BigDecimal totalBaseCalculoIcms = BigDecimal.ZERO;
        BigDecimal totalIcms = BigDecimal.ZERO;
        BigDecimal totalIpi = BigDecimal.ZERO;
        for (int i = 0; i < listaDetalhe.size(); i++) {
            pedidoDetalhe = listaDetalhe.get(i);
            subTotal = subTotal.add(pedidoDetalhe.getValorSubtotal());
            totalDesconto = totalDesconto.add(pedidoDetalhe.getValorDesconto());
            totalGeral = totalGeral.add(pedidoDetalhe.getValorTotal());
            totalBaseCalculoIcms = totalBaseCalculoIcms.add(pedidoDetalhe.getBaseCalculoIcms());
            totalIcms = totalIcms.add(pedidoDetalhe.getValorIcms());
            totalIpi = totalIpi.add(pedidoDetalhe.getValorIpi());
        }
        CompraPedidoVO pedido = (CompraPedidoVO) compraPedidoDetalhe.getForm1().getVOModel().getValueObject();
        pedido.setValorSubtotal(subTotal);
        pedido.setValorDesconto(totalDesconto);
        pedido.setValorTotalPedido(totalGeral);
        pedido.setBaseCalculoIcms(totalBaseCalculoIcms);
        pedido.setValorIcms(totalIcms);
        pedido.setValorTotalProdutos(totalGeral);
        pedido.setValorIpi(totalIpi);
        pedido.setValorTotalNf(totalGeral.add(totalIcms));
        compraPedidoDetalhe.getForm1().pull();
    }

    /**
     * @param pk the pk to set
     */
    public void setPk(String pk) {
        this.pk = pk;
    }

    /**
     * @param compraSugerida the compraSugerida to set
     */
    public void setCompraSugerida(boolean compraSugerida) {
        this.compraSugerida = compraSugerida;
    }
}
