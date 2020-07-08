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
package com.t2tierp.tributacao.cliente;

import com.t2tierp.cadastros.java.EmpresaVO;
import com.t2tierp.padrao.cliente.Container;
import com.t2tierp.padrao.java.Constantes;
import com.t2tierp.tributacao.java.TributIcmsCustomCabVO;
import com.t2tierp.tributacao.java.TributIcmsCustomDetVO;
import java.util.List;
import javax.swing.JOptionPane;
import org.openswing.swing.form.client.FormController;
import org.openswing.swing.mdi.client.MDIFrame;
import org.openswing.swing.message.receive.java.Response;
import org.openswing.swing.message.receive.java.ValueObject;
import org.openswing.swing.util.client.ClientUtils;
import org.openswing.swing.util.java.Consts;

public class TributIcmsCustomCabDetalheController extends FormController {

    private TributIcmsCustomCabDetalhe tributIcmsCustomCabDetalhe = null;
    private String pk = null;
    private TributIcmsCustomCabGrid tributIcmsCustomCabGrid = null;
    private String acaoServidor;

    public TributIcmsCustomCabDetalheController(TributIcmsCustomCabGrid tributIcmsCustomCabGrid, String pk) {
        this.tributIcmsCustomCabGrid = tributIcmsCustomCabGrid;
        this.pk = pk;
        this.acaoServidor = "tributIcmsCustomCabDetalheAction";
        tributIcmsCustomCabDetalhe = new TributIcmsCustomCabDetalhe(this);
        tributIcmsCustomCabDetalhe.setParentFrame(this.tributIcmsCustomCabGrid);
        this.tributIcmsCustomCabGrid.pushFrame(tributIcmsCustomCabDetalhe);
        MDIFrame.add(tributIcmsCustomCabDetalhe, true);

        if (pk != null) {
            tributIcmsCustomCabDetalhe.getForm1().setMode(Consts.READONLY);
            tributIcmsCustomCabDetalhe.getForm1().reload();
        } else {
            tributIcmsCustomCabDetalhe.getForm1().setMode(Consts.INSERT);
            tributIcmsCustomCabDetalhe.getGridControlIcms().reloadData();
        }
    }

    @Override
    public Response loadData(Class valueObjectClass) {
        return ClientUtils.getData(acaoServidor, new Object[]{Constantes.LOAD, pk});
    }

    @Override
    public void loadDataCompleted(boolean error) {
        tributIcmsCustomCabDetalhe.getIcmsUfGridController().setPk(pk);
        tributIcmsCustomCabDetalhe.getGridControlIcms().reloadData();
    }

    @Override
    public Response insertRecord(ValueObject newPersistentObject) throws Exception {
        List<TributIcmsCustomDetVO> listaIcmsUf = tributIcmsCustomCabDetalhe.getGridControlIcms().getVOListTableModel().getDataVector();
        EmpresaVO empresa = (EmpresaVO) Container.getContainer().get("empresa");
        ((TributIcmsCustomCabVO) newPersistentObject).setEmpresa(empresa);
        
        return ClientUtils.getData(acaoServidor, new Object[]{Constantes.INSERT, newPersistentObject, listaIcmsUf});
    }

    @Override
    public void afterInsertData() {
        tributIcmsCustomCabGrid.getGrid1().reloadData();
        JOptionPane.showMessageDialog(tributIcmsCustomCabDetalhe, "Dados salvos com sucesso!", "Informacao do Sistema", JOptionPane.INFORMATION_MESSAGE);
    }

    @Override
    public Response updateRecord(ValueObject oldPersistentObject, ValueObject persistentObject) throws Exception {
        List<TributIcmsCustomDetVO> listaIcmsUf = tributIcmsCustomCabDetalhe.getGridControlIcms().getVOListTableModel().getDataVector();
        
        return ClientUtils.getData(acaoServidor, new Object[]{Constantes.UPDATE, oldPersistentObject, persistentObject, listaIcmsUf});
    }

    @Override
    public void afterEditData() {
        tributIcmsCustomCabGrid.getGrid1().reloadData();
        JOptionPane.showMessageDialog(tributIcmsCustomCabDetalhe, "Dados alterados com sucesso!", "Informacao do Sistema", JOptionPane.INFORMATION_MESSAGE);
    }
}
