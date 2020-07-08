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
package com.t2tierp.wms.cliente;

import com.t2tierp.padrao.java.Constantes;
import com.t2tierp.wms.java.WmsExpedicaoVO;
import com.t2tierp.wms.java.WmsOrdemSeparacaoCabVO;
import com.t2tierp.wms.java.WmsOrdemSeparacaoDetVO;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import org.openswing.swing.form.client.FormController;
import org.openswing.swing.mdi.client.MDIFrame;
import org.openswing.swing.message.receive.java.Response;
import org.openswing.swing.message.receive.java.VOResponse;
import org.openswing.swing.util.client.ClientUtils;
import org.openswing.swing.util.java.Consts;

public class WmsExpedicaoDetalheController extends FormController {

    private WmsExpedicaoDetalhe telaDetalhe = null;
    private WmsOrdemSeparacaoCabVO ordemSeparacao;
    private WmsExpedicaoGrid wmsExpedicaoGrid = null;
    private String acaoServidor;

    public WmsExpedicaoDetalheController(WmsExpedicaoGrid wmsExpedicaoGrid, WmsOrdemSeparacaoCabVO ordemSeparacao) {
        this.wmsExpedicaoGrid = wmsExpedicaoGrid;
        this.ordemSeparacao = ordemSeparacao;
        this.acaoServidor = "wmsExpedicaoDetalheAction";
        telaDetalhe = new WmsExpedicaoDetalhe(this);
        telaDetalhe.setParentFrame(this.wmsExpedicaoGrid);
        this.wmsExpedicaoGrid.pushFrame(telaDetalhe);
        MDIFrame.add(telaDetalhe, true);

        telaDetalhe.getForm1().setMode(Consts.INSERT);
        carregaDadosOrdemSeparacao();
    }

    @Override
    public Response loadData(Class valueObjectClass) {
        return new VOResponse();
    }

    public void carregaDadosOrdemSeparacao() {
        telaDetalhe.getOrdemSeparacaoController().setOrdemSeparacao(ordemSeparacao);
        telaDetalhe.getFormOrdemSeparacao().reload();
        
        telaDetalhe.getItensController().setListaWmsRecebimentoDetalhe(ordemSeparacao.getListaWmsOrdemSeparacaoDet());
        telaDetalhe.getGridControl1().reloadData();
    }

    public void expedicaoItens() {
        try {
            int itensSelecionados[] = telaDetalhe.getGridControl1().getSelectedRows();

            if (itensSelecionados.length == 0) {
                JOptionPane.showMessageDialog(telaDetalhe, "Nenhum item selecionado", "Erro", JOptionPane.ERROR_MESSAGE);
            } else {
                WmsExpedicaoVO expedicao = (WmsExpedicaoVO) telaDetalhe.getForm1().getVOModel().getValueObject();
                if (expedicao.getWmsArmazenamento() == null || expedicao.getWmsArmazenamento().getId() == null) {
                    throw new Exception("Necessário informar o armazenamento");
                }

                List<WmsOrdemSeparacaoDetVO> itensExpedicao = new ArrayList<>();
                for (int i : itensSelecionados) {
                    itensExpedicao.add((WmsOrdemSeparacaoDetVO) telaDetalhe.getGridControl1().getVOListTableModel().getObjectForRow(i));
                }

                Response res = ClientUtils.getData(acaoServidor, new Object[]{Constantes.INSERT, expedicao.getWmsArmazenamento(), expedicao.getQuantidade(), expedicao.getDataSaida(), itensExpedicao});
                if (res.isError()) {
                    throw new Exception(res.getErrorMessage());
                }

                JOptionPane.showMessageDialog(telaDetalhe, "Expedição realizada com sucesso!", "Informacao do Sistema", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(telaDetalhe, "Ocorreu um erro.\n" + e.getMessage(), "Erro do Sistema", JOptionPane.ERROR_MESSAGE);
        }
    }
    
}
