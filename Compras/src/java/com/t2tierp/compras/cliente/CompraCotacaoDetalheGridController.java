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

import com.t2tierp.compras.java.CompraCotacaoDetalheVO;
import com.t2tierp.compras.java.CompraRequisicaoDetalheVO;
import com.t2tierp.padrao.java.Constantes;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.openswing.swing.message.receive.java.Response;
import org.openswing.swing.message.send.java.GridParams;
import org.openswing.swing.table.client.GridController;
import org.openswing.swing.table.java.GridDataLocator;
import org.openswing.swing.util.client.ClientUtils;
import org.openswing.swing.util.java.Consts;

public class CompraCotacaoDetalheGridController extends GridController implements GridDataLocator {

    private String acaoServidor;
    private String pk;
    private CompraCotacaoDetalhe compraCotacaoDetalhe;

    public CompraCotacaoDetalheGridController(CompraCotacaoDetalhe compraCotacaoDetalhe) {
        acaoServidor = "compraCotacaoDetalheGridAction";
        this.compraCotacaoDetalhe = compraCotacaoDetalhe;
    }

    public Response loadData(int action, int startIndex, Map filteredColumns, ArrayList currentSortedColumns, ArrayList currentSortedVersusColumns, Class valueObjectType, Map otherGridParams) {
        //define os parametros da grid
        otherGridParams.put("acao", Constantes.LOAD);
        otherGridParams.put("idFornecedorCompraCotacao", pk);

        return ClientUtils.getData(acaoServidor, new GridParams(action, startIndex, filteredColumns, currentSortedColumns, currentSortedVersusColumns, otherGridParams));
    }

    public void insereItemCotacao(CompraRequisicaoDetalheVO requisicaoDetalhe, BigDecimal quantidade) throws Exception {
        if (compraCotacaoDetalhe.getForm1().getMode() == Consts.READONLY) {
            throw new Exception("Não é possível alterar uma cotação!");
        }
        if (requisicaoDetalhe.getItemCotado().equals("S")) {
            throw new Exception("A quantidade total requisitada deste item já foi cotada!");
        }
        if ((requisicaoDetalhe.getQuantidade().subtract(requisicaoDetalhe.getQuantidadeCotada())).compareTo(quantidade) == -1) {
            throw new Exception("A quantidade informada excede a quantidade disponível para cotação!");
        }

        List<CompraCotacaoDetalheVO> listaCotacaoDetalhe = compraCotacaoDetalhe.getGridItensCotacao().getVOListTableModel().getDataVector();
        for (int i = 0; i < listaCotacaoDetalhe.size(); i++) {
            if (listaCotacaoDetalhe.get(i).getCompraRequisicaoDetalhe().getId().intValue() == requisicaoDetalhe.getId().intValue()) {
                throw new Exception("Item já incluso nesta cotação!");
            }
        }

        CompraCotacaoDetalheVO cotacaoDetalhe = new CompraCotacaoDetalheVO();
        cotacaoDetalhe.setProduto(requisicaoDetalhe.getProduto());
        cotacaoDetalhe.setQuantidade(quantidade);
        cotacaoDetalhe.setCompraRequisicaoDetalhe(requisicaoDetalhe);
        compraCotacaoDetalhe.getGridItensCotacao().getVOListTableModel().addObject(cotacaoDetalhe);

        requisicaoDetalhe.setQuantidadeCotada(requisicaoDetalhe.getQuantidadeCotada().add(quantidade));
        if (requisicaoDetalhe.getQuantidade().compareTo(requisicaoDetalhe.getQuantidadeCotada()) == 0) {
            requisicaoDetalhe.setItemCotado("S");
        }
    }

    public void removeItemCotacao(CompraCotacaoDetalheVO cotacaoDetalhe) throws Exception {
        if (compraCotacaoDetalhe.getForm1().getMode() == Consts.READONLY) {
            throw new Exception("Não é possível alterar uma cotação!");
        }
        List<CompraRequisicaoDetalheVO> listaRequisicaoDetalhe = compraCotacaoDetalhe.getGridRequisicaoDetalhe().getVOListTableModel().getDataVector();

        CompraRequisicaoDetalheVO requisicaoDetalhe = listaRequisicaoDetalhe.get(listaRequisicaoDetalhe.indexOf(cotacaoDetalhe.getCompraRequisicaoDetalhe()));
        requisicaoDetalhe.setItemCotado("N");
        requisicaoDetalhe.setQuantidadeCotada(requisicaoDetalhe.getQuantidadeCotada().subtract(cotacaoDetalhe.getQuantidade()));
        compraCotacaoDetalhe.getGridRequisicaoDetalhe().getVOListTableModel().updateObjectAt(listaRequisicaoDetalhe.indexOf(cotacaoDetalhe.getCompraRequisicaoDetalhe()));

        compraCotacaoDetalhe.getGridItensCotacao().getVOListTableModel().removeObjectAt(compraCotacaoDetalhe.getGridItensCotacao().getSelectedRow());
    }

    /**
     * @param pk the pk to set
     */
    public void setPk(String pk) {
        this.pk = pk;
    }
}
