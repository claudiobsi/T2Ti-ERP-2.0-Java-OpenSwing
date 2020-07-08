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
package com.t2tierp.contabilidade.cliente;

import com.t2tierp.cadastros.java.EmpresaVO;
import com.t2tierp.contabilidade.java.ContabilIndiceVO;
import com.t2tierp.contabilidade.java.ContabilIndiceValorVO;
import com.t2tierp.padrao.cliente.Container;
import com.t2tierp.padrao.java.Constantes;
import java.util.List;
import javax.swing.JOptionPane;
import org.openswing.swing.form.client.FormController;
import org.openswing.swing.mdi.client.MDIFrame;
import org.openswing.swing.message.receive.java.Response;
import org.openswing.swing.message.receive.java.ValueObject;
import org.openswing.swing.util.client.ClientUtils;
import org.openswing.swing.util.java.Consts;

public class ContabilIndiceDetalheController extends FormController {

    private ContabilIndiceDetalhe contabilIndiceDetalhe = null;
    private String pk = null;
    private ContabilIndiceGrid contabilIndiceGrid = null;
    private String acaoServidor;

    public ContabilIndiceDetalheController(ContabilIndiceGrid contabilIndiceGrid, String pk) {
        this.contabilIndiceGrid = contabilIndiceGrid;
        this.pk = pk;
        this.acaoServidor = "contabilIndiceDetalheAction";
        contabilIndiceDetalhe = new ContabilIndiceDetalhe(this);
        contabilIndiceDetalhe.setParentFrame(this.contabilIndiceGrid);
        this.contabilIndiceGrid.pushFrame(contabilIndiceDetalhe);
        MDIFrame.add(contabilIndiceDetalhe, true);

        if (pk != null) {
            contabilIndiceDetalhe.getForm1().setMode(Consts.READONLY);
            contabilIndiceDetalhe.getForm1().reload();
        } else {
            contabilIndiceDetalhe.getForm1().setMode(Consts.INSERT);
            contabilIndiceDetalhe.getGridControl1().reloadData();
        }
    }

    @Override
    public Response loadData(Class valueObjectClass) {
        return ClientUtils.getData(acaoServidor, new Object[]{Constantes.LOAD, pk});
    }

    @Override
    public void loadDataCompleted(boolean error) {
        ContabilIndiceVO indice = (ContabilIndiceVO) contabilIndiceDetalhe.getForm1().getVOModel().getValueObject();
        this.pk = indice.getId().toString();

        contabilIndiceDetalhe.getIndiceValorGridController().setPk(pk);
        contabilIndiceDetalhe.getGridControl1().reloadData();
    }

    @Override
    public Response insertRecord(ValueObject newPersistentObject) throws Exception {
        List<ContabilIndiceValorVO> valores = contabilIndiceDetalhe.getGridControl1().getVOListTableModel().getDataVector();

        EmpresaVO empresa = (EmpresaVO) Container.getContainer().get("empresa");
        ((ContabilIndiceVO) newPersistentObject).setEmpresa(empresa);

        return ClientUtils.getData(acaoServidor, new Object[]{Constantes.INSERT, newPersistentObject, valores});
    }

    @Override
    public void afterInsertData() {
        contabilIndiceGrid.getGrid1().reloadData();
        JOptionPane.showMessageDialog(contabilIndiceDetalhe, "Dados salvos com sucesso!", "Informacao do Sistema", JOptionPane.INFORMATION_MESSAGE);
    }

    @Override
    public Response updateRecord(ValueObject oldPersistentObject, ValueObject persistentObject) throws Exception {
        List<ContabilIndiceValorVO> valores = contabilIndiceDetalhe.getGridControl1().getVOListTableModel().getDataVector();
        
        return ClientUtils.getData(acaoServidor, new Object[]{Constantes.UPDATE, oldPersistentObject, persistentObject, valores});
    }

    @Override
    public void afterEditData() {
        contabilIndiceGrid.getGrid1().reloadData();
        JOptionPane.showMessageDialog(contabilIndiceDetalhe, "Dados alterados com sucesso!", "Informacao do Sistema", JOptionPane.INFORMATION_MESSAGE);
    }
}
