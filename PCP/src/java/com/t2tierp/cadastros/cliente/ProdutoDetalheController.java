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
package com.t2tierp.cadastros.cliente;

import com.t2tierp.cadastros.java.FichaTecnicaVO;
import com.t2tierp.cadastros.java.ProdutoVO;
import com.t2tierp.padrao.java.Constantes;
import java.util.List;
import javax.swing.JOptionPane;
import org.openswing.swing.form.client.FormController;
import org.openswing.swing.mdi.client.MDIFrame;
import org.openswing.swing.message.receive.java.ErrorResponse;
import org.openswing.swing.message.receive.java.Response;
import org.openswing.swing.message.receive.java.ValueObject;
import org.openswing.swing.util.client.ClientUtils;
import org.openswing.swing.util.java.Consts;

public class ProdutoDetalheController extends FormController {

    private ProdutoDetalhe produtoDetalhe = null;
    private String pk = null;
    private ProdutoGrid produtoGrid = null;
    private String acaoServidor;
    private boolean chamadoOutroModulo = false;

    public ProdutoDetalheController(ProdutoGrid produtoGrid, String pk) {
        this.produtoGrid = produtoGrid;
        this.pk = pk;
        this.acaoServidor = "produtoDetalheAction";
        produtoDetalhe = new ProdutoDetalhe(this);
        produtoDetalhe.setParentFrame(this.produtoGrid);
        this.produtoGrid.pushFrame(produtoDetalhe);
        MDIFrame.add(produtoDetalhe, true);

        if (pk != null) {
            produtoDetalhe.getForm1().setMode(Consts.READONLY);
            produtoDetalhe.getForm1().reload();
        } else {
            produtoDetalhe.getForm1().setMode(Consts.INSERT);
            produtoDetalhe.getGridControl1().reloadData();
        }
    }

    public ProdutoDetalheController(ProdutoVO produto) {
        chamadoOutroModulo = true;
        this.acaoServidor = "produtoDetalheAction";
        produtoDetalhe = new ProdutoDetalhe(this);
        MDIFrame.add(produtoDetalhe, true);
        produtoDetalhe.setModal(true);
        produtoDetalhe.getForm1().setMode(Consts.INSERT);

        produtoDetalhe.getForm1().getVOModel().setValueObject(produto);
        produtoDetalhe.getForm1().pull();
    }

    @Override
    public Response loadData(Class valueObjectClass) {
        return ClientUtils.getData(acaoServidor, new Object[]{Constantes.LOAD, pk});
    }

    @Override
    public void loadDataCompleted(boolean error) {
        ProdutoVO produto = (ProdutoVO) produtoDetalhe.getForm1().getVOModel().getValueObject();
        produtoDetalhe.getFichaTecnicaController().setIdProduto(String.valueOf(produto.getId()));
        produtoDetalhe.getGridControl1().reloadData();
    }
    
    @Override
    public Response insertRecord(ValueObject newPersistentObject) throws Exception {
        ProdutoVO produto = (ProdutoVO) newPersistentObject;
        if (produto.getTributGrupoTributario().getId() == null && produto.getTributIcmsCustomCab().getId() == null) {
            return new ErrorResponse("É necesário informar o Grupo Tributário ou o ICMS Customizado.");
        }
        
        List<FichaTecnicaVO> fichaTecnica = produtoDetalhe.getGridControl1().getVOListTableModel().getDataVector();

        return ClientUtils.getData(acaoServidor, new Object[]{Constantes.INSERT, newPersistentObject, fichaTecnica});
    }

    @Override
    public void afterInsertData() {
        JOptionPane.showMessageDialog(produtoDetalhe, "Dados salvos com sucesso!", "Informacao do Sistema", JOptionPane.INFORMATION_MESSAGE);
        if (!chamadoOutroModulo) {
            produtoGrid.getGrid1().reloadData();
        } else {
            produtoDetalhe.dispose();
        }
    }

    @Override
    public Response updateRecord(ValueObject oldPersistentObject, ValueObject persistentObject) throws Exception {
        ProdutoVO produto = (ProdutoVO) persistentObject;
        if (produto.getTributGrupoTributario().getId() == null && produto.getTributIcmsCustomCab().getId() == null) {
            return new ErrorResponse("É necesário informar o Grupo Tributário ou o ICMS Customizado.");
        }

        List<FichaTecnicaVO> fichaTecnica = produtoDetalhe.getGridControl1().getVOListTableModel().getDataVector();
        
        return ClientUtils.getData(acaoServidor, new Object[]{Constantes.UPDATE, oldPersistentObject, persistentObject, fichaTecnica});
    }

    @Override
    public void afterEditData() {
        produtoGrid.getGrid1().reloadData();
        JOptionPane.showMessageDialog(produtoDetalhe, "Dados alterados com sucesso!", "Informacao do Sistema", JOptionPane.INFORMATION_MESSAGE);
    }

    @Override
    public boolean validateControl(String attributeName, Object oldValue, Object newValue) {
        if (attributeName.equals("tributGrupoTributario.id")) {
            ProdutoVO produto = (ProdutoVO) produtoDetalhe.getForm1().getVOModel().getValueObject();
            produto.getTributIcmsCustomCab().setId(null);
            produto.getTributIcmsCustomCab().setDescricao(null);
            produtoDetalhe.getForm1().pull();
        } else if (attributeName.equals("tributIcmsCustomCab.id")) {
            ProdutoVO produto = (ProdutoVO) produtoDetalhe.getForm1().getVOModel().getValueObject();
            produto.getTributGrupoTributario().setId(null);
            produto.getTributGrupoTributario().setDescricao(null);
            produtoDetalhe.getForm1().pull();
        }
        return super.validateControl(attributeName, oldValue, newValue);
    }

    public ProdutoDetalhe getProdutoDetalhe() {
        return produtoDetalhe;
    }
}
