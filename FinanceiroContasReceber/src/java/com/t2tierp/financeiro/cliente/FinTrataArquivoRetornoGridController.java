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

import com.t2tierp.cadastros.java.EmpresaVO;
import com.t2tierp.edi.cnab240.bb.DetalheLoteSegmentoT;
import com.t2tierp.edi.cnab240.bb.LeArquivoRetornoBB;
import com.t2tierp.padrao.cliente.Container;
import com.t2tierp.padrao.java.Constantes;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import org.openswing.swing.mdi.client.MDIFrame;
import org.openswing.swing.message.receive.java.Response;
import org.openswing.swing.message.send.java.GridParams;
import org.openswing.swing.table.client.GridController;
import org.openswing.swing.table.java.GridDataLocator;
import org.openswing.swing.util.client.ClientUtils;

public class FinTrataArquivoRetornoGridController extends GridController implements GridDataLocator {

    private FinTrataArquivoRetornoGrid grid;
    private String acaoServidor;
    private Collection<DetalheLoteSegmentoT> listaSegmentoT;

    public FinTrataArquivoRetornoGridController() {
        grid = new FinTrataArquivoRetornoGrid(this);
        acaoServidor = "finTrataArquivoRetornoGridAction";
        MDIFrame.add(grid);
    }

    public Response loadData(int action, int startIndex, Map filteredColumns, ArrayList currentSortedColumns, ArrayList currentSortedVersusColumns, Class valueObjectType, Map otherGridParams) {
        EmpresaVO empresa = (EmpresaVO) Container.getContainer().get("empresa");
        //define os parametros da grid
        otherGridParams.put("acao", Constantes.LOAD);
        otherGridParams.put("listaSegmentoT", listaSegmentoT);
        otherGridParams.put("empresa", empresa);

        return ClientUtils.getData(acaoServidor, new GridParams(action, startIndex, filteredColumns, currentSortedColumns, currentSortedVersusColumns, otherGridParams));
    }

    @Override
    public void loadDataCompleted(boolean error) {
        if (!error) {
            JOptionPane.showMessageDialog(grid, "Arquivo processado.", "Informação do Sistema", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public void processaArquivoRetorno() {
        try {
            JFileChooser chooser = new JFileChooser();
            int resposta = chooser.showOpenDialog(grid);
            if (resposta == JFileChooser.APPROVE_OPTION) {
                File file = chooser.getSelectedFile();
                LeArquivoRetornoBB arquivoRetorno = new LeArquivoRetornoBB();
                listaSegmentoT = arquivoRetorno.leArquivoRetorno(file);
                grid.getGrid1().reloadData();
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Ocorreu um erro ao processar o arquivo!\n" + e.getMessage(), "Erro do Sistema", JOptionPane.ERROR_MESSAGE);
        }
    }
}
