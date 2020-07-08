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
import com.t2tierp.wms.java.WmsArmazenamentoVO;
import com.t2tierp.wms.java.WmsRecebimentoCabecalhoVO;
import com.t2tierp.wms.java.WmsRecebimentoDetalheVO;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import org.openswing.swing.form.client.FormController;
import org.openswing.swing.mdi.client.MDIFrame;
import org.openswing.swing.message.receive.java.Response;
import org.openswing.swing.message.receive.java.VOResponse;
import org.openswing.swing.util.client.ClientUtils;
import org.openswing.swing.util.java.Consts;

public class WmsArmazenamentoDetalheController extends FormController {

    private WmsArmazenamentoDetalhe telaDetalhe = null;
    private WmsRecebimentoCabecalhoVO recebimento = null;
    private WmsArmazenamentoGrid wmsArmazenamentoGrid = null;
    private String acaoServidor;

    public WmsArmazenamentoDetalheController(WmsArmazenamentoGrid wmsArmazenamentoGrid, WmsRecebimentoCabecalhoVO recebimento) {
        this.wmsArmazenamentoGrid = wmsArmazenamentoGrid;
        this.recebimento = recebimento;
        this.acaoServidor = "wmsArmazenamentoDetalheAction";
        telaDetalhe = new WmsArmazenamentoDetalhe(this);
        telaDetalhe.setParentFrame(this.wmsArmazenamentoGrid);
        this.wmsArmazenamentoGrid.pushFrame(telaDetalhe);
        MDIFrame.add(telaDetalhe, true);

        telaDetalhe.getForm1().setMode(Consts.INSERT);
        carregaDadosRecebimento();
    }

    @Override
    public Response loadData(Class valueObjectClass) {
        return new VOResponse();
    }

    public void carregaDadosRecebimento() {
        telaDetalhe.getRecebimentoController().setRecebimento(recebimento);
        telaDetalhe.getFormRecebimento().reload();
        
        telaDetalhe.getItensController().setListaWmsRecebimentoDetalhe(recebimento.getListaWmsRecebimentoDetalhe());
        telaDetalhe.getGridControl1().reloadData();
    }

    public void armazenaItens() {
        try {
            int itensSelecionados[] = telaDetalhe.getGridControl1().getSelectedRows();

            if (itensSelecionados.length == 0) {
                JOptionPane.showMessageDialog(telaDetalhe, "Nenhum item selecionado", "Erro", JOptionPane.ERROR_MESSAGE);
            } else {
                WmsArmazenamentoVO armazenamento = (WmsArmazenamentoVO) telaDetalhe.getForm1().getVOModel().getValueObject();
                if (armazenamento.getWmsCaixa() == null || armazenamento.getWmsCaixa().getId() == null) {
                    throw new Exception("Necessário informar a caixa");
                }

                List<WmsRecebimentoDetalheVO> itensArmazenar = new ArrayList<>();
                for (int i : itensSelecionados) {
                    itensArmazenar.add((WmsRecebimentoDetalheVO) telaDetalhe.getGridControl1().getVOListTableModel().getObjectForRow(i));
                }

                Response res = ClientUtils.getData(acaoServidor, new Object[]{Constantes.INSERT, armazenamento.getWmsCaixa(), itensArmazenar});
                if (res.isError()) {
                    throw new Exception(res.getErrorMessage());
                }

                JOptionPane.showMessageDialog(telaDetalhe, "Armazenamento realizado com sucesso!", "Informacao do Sistema", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(telaDetalhe, "Ocorreu um erro.\n" + e.getMessage(), "Erro do Sistema", JOptionPane.ERROR_MESSAGE);
        }
    }

}
