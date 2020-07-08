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
package com.t2tierp.orcamento.cliente;

import com.t2tierp.orcamento.java.OrcamentoFluxoCaixaVO;
import com.t2tierp.padrao.java.Constantes;
import javax.swing.JOptionPane;
import org.openswing.swing.form.client.FormController;
import org.openswing.swing.mdi.client.MDIFrame;
import org.openswing.swing.message.receive.java.Response;
import org.openswing.swing.message.receive.java.ValueObject;
import org.openswing.swing.util.client.ClientUtils;
import org.openswing.swing.util.java.Consts;

public class OrcamentoFluxoCaixaDetalheController extends FormController {

    private OrcamentoFluxoCaixaDetalhe orcamentoFluxoCaixaDetalhe = null;
    private String pk = null;
    private OrcamentoFluxoCaixaGrid orcamentoFluxoCaixaGrid = null;
    private String acaoServidor;

    public OrcamentoFluxoCaixaDetalheController(OrcamentoFluxoCaixaGrid orcamentoFluxoCaixaGrid, String pk) {
        this.orcamentoFluxoCaixaGrid = orcamentoFluxoCaixaGrid;
        this.pk = pk;
        this.acaoServidor = "orcamentoFluxoCaixaDetalheAction";
        orcamentoFluxoCaixaDetalhe = new OrcamentoFluxoCaixaDetalhe(this);
        orcamentoFluxoCaixaDetalhe.setParentFrame(this.orcamentoFluxoCaixaGrid);
        this.orcamentoFluxoCaixaGrid.pushFrame(orcamentoFluxoCaixaDetalhe);
        MDIFrame.add(orcamentoFluxoCaixaDetalhe, true);

        if (pk != null) {
            orcamentoFluxoCaixaDetalhe.getForm1().setMode(Consts.READONLY);
            orcamentoFluxoCaixaDetalhe.getForm1().reload();
        } else {
            orcamentoFluxoCaixaDetalhe.getForm1().setMode(Consts.INSERT);
        }
    }

    @Override
    public Response loadData(Class valueObjectClass) {
        return ClientUtils.getData(acaoServidor, new Object[]{Constantes.LOAD, pk});
    }

    @Override
    public void loadDataCompleted(boolean error) {
        orcamentoFluxoCaixaDetalhe.getGridController().setPk(pk);
        orcamentoFluxoCaixaDetalhe.getGridControl1().reloadData();
    }

    @Override
    public Response insertRecord(ValueObject newPersistentObject) throws Exception {
        return ClientUtils.getData(acaoServidor, new Object[]{Constantes.INSERT, newPersistentObject});
    }

    @Override
    public void afterInsertData() {
        OrcamentoFluxoCaixaVO orcamentoFluxoCaixa = (OrcamentoFluxoCaixaVO) orcamentoFluxoCaixaDetalhe.getForm1().getVOModel().getValueObject();
        pk = orcamentoFluxoCaixa.getId().toString();

        orcamentoFluxoCaixaGrid.getGrid1().reloadData();
        orcamentoFluxoCaixaDetalhe.getForm1().reload();
        JOptionPane.showMessageDialog(orcamentoFluxoCaixaDetalhe, "Dados salvos com sucesso!", "Informacao do Sistema", JOptionPane.INFORMATION_MESSAGE);
    }

    @Override
    public Response updateRecord(ValueObject oldPersistentObject, ValueObject persistentObject) throws Exception {
        return ClientUtils.getData(acaoServidor, new Object[]{Constantes.UPDATE, oldPersistentObject, persistentObject});
    }

    @Override
    public void afterEditData() {
        orcamentoFluxoCaixaGrid.getGrid1().reloadData();
        orcamentoFluxoCaixaDetalhe.getForm1().reload();
        JOptionPane.showMessageDialog(orcamentoFluxoCaixaDetalhe, "Dados alterados com sucesso!", "Informacao do Sistema", JOptionPane.INFORMATION_MESSAGE);
    }

    public void buscaValorRealizado() throws Exception {
        if (orcamentoFluxoCaixaDetalhe.getForm1().getMode() == Consts.READONLY) {
            OrcamentoFluxoCaixaVO orcamentoFluxoCaixa = (OrcamentoFluxoCaixaVO) orcamentoFluxoCaixaDetalhe.getForm1().getVOModel().getValueObject();
            Response res = ClientUtils.getData(acaoServidor, new Object[]{98, orcamentoFluxoCaixa});
            if (res.isError()) {
                throw new Exception(res.getErrorMessage());
            }
            orcamentoFluxoCaixaDetalhe.getForm1().reload();
        } else {
            throw new Exception("É necessário salvar o orçamento antes de buscar os valores realizados");
        }
    }

    public void calcularVariacao() throws Exception {
        if (orcamentoFluxoCaixaDetalhe.getForm1().getMode() == Consts.READONLY) {
            OrcamentoFluxoCaixaVO orcamentoFluxoCaixa = (OrcamentoFluxoCaixaVO) orcamentoFluxoCaixaDetalhe.getForm1().getVOModel().getValueObject();
            Response res = ClientUtils.getData(acaoServidor, new Object[]{99, orcamentoFluxoCaixa});
            if (res.isError()) {
                throw new Exception(res.getErrorMessage());
            }
            orcamentoFluxoCaixaDetalhe.getForm1().reload();
        } else {
            throw new Exception("É necessário salvar o orçamento antes de calcular a variação");
        }
    }
}
