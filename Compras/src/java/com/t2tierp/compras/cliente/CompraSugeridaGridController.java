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

import com.t2tierp.cadastros.java.ProdutoVO;
import com.t2tierp.padrao.java.Constantes;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.openswing.swing.mdi.client.MDIFrame;
import org.openswing.swing.message.receive.java.Response;
import org.openswing.swing.message.receive.java.VOListResponse;
import org.openswing.swing.message.send.java.GridParams;
import org.openswing.swing.util.client.ClientUtils;

public class CompraSugeridaGridController {

    private CompraSugeridaGrid grid;
    private String acaoServidor;

    public CompraSugeridaGridController() {
        grid = new CompraSugeridaGrid(this);
        acaoServidor = "compraSugeridaGridAction";
        MDIFrame.add(grid);
    }

    public void loadData(String tipo) throws Exception {
        //define os parametros da grid
        Map otherGridParams = new HashMap();
        otherGridParams.put("acao", Constantes.LOAD);
        GridParams pars = new GridParams(0, 0, null, null, null, otherGridParams);

        Response res = ClientUtils.getData(acaoServidor, pars);
        if (res.isError()) {
            throw new Exception(res.getErrorMessage());
        }

        List<ProdutoVO> listaProduto = ((VOListResponse) res).getRows();
        if (listaProduto.isEmpty()){
            throw new Exception("Nenhum produto com estoque menor que o mínimo!");
        }
        if (tipo.equals("Requisicao")){
            new CompraRequisicaoGridController(listaProduto);
        } else {
            new CompraPedidoGridController(listaProduto);
        }
    }
}
