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
package com.t2tierp.sped.cliente;

import com.t2tierp.cadastros.java.ContadorVO;
import com.t2tierp.cadastros.java.EmpresaEnderecoVO;
import com.t2tierp.cadastros.java.EmpresaVO;
import com.t2tierp.ged.java.ArquivoVO;
import com.t2tierp.padrao.cliente.Container;
import com.t2tierp.padrao.java.Biblioteca;
import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.openswing.swing.mdi.client.MDIFrame;
import org.openswing.swing.message.receive.java.Response;
import org.openswing.swing.message.receive.java.VOResponse;
import org.openswing.swing.message.send.java.GridParams;
import org.openswing.swing.util.client.ClientUtils;

public class SpedFiscalGridController {

    public SpedFiscalGridController() {
        SpedFiscalGrid grid = new SpedFiscalGrid(this);
        MDIFrame.add(grid);
    }

    public void geraSpedFiscal(Date dataInicial, Date dataFinal, String versao, String finalidade, String perfil, int inventario, ContadorVO contador, File arquivo) throws Exception {
        EmpresaVO empresa = (EmpresaVO) Container.getContainer().get("empresa");
        EmpresaEnderecoVO enderecoPrincipalEmpresa = (EmpresaEnderecoVO) Container.getContainer().get("enderecoPrincipalEmpresa");
        
        Map otherGridParams = new HashMap();
        otherGridParams.put("dataInicial", dataInicial);
        otherGridParams.put("dataFinal", dataFinal);
        otherGridParams.put("versao", versao);
        otherGridParams.put("finalidade", finalidade);
        otherGridParams.put("perfil", perfil);
        otherGridParams.put("inventario", inventario);
        otherGridParams.put("contador", contador);
        otherGridParams.put("empresa", empresa);
        otherGridParams.put("enderecoPrincipalEmpresa", enderecoPrincipalEmpresa);

        GridParams pars = new GridParams(0, 0, null, null, null, otherGridParams);

        Response res = ClientUtils.getData("geraSpedFiscalAction", pars);
        if (res.isError()) {
            throw new Exception(res.getErrorMessage());
        }
        ArquivoVO arquivoVO = (ArquivoVO) ((VOResponse) res).getVo();
        Biblioteca.salvaArquivo(arquivo.getAbsolutePath(), arquivoVO.getFile());
    }
}
