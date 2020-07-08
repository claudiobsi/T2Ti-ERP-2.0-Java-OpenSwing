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
package com.t2tierp.escritafiscal.cliente;

import com.t2tierp.escritafiscal.java.SimplesNacionalCabecalhoVO;
import com.t2tierp.escritafiscal.java.SimplesNacionalDetalheVO;
import com.t2tierp.padrao.java.Constantes;
import java.util.List;
import javax.swing.JOptionPane;
import org.openswing.swing.form.client.FormController;
import org.openswing.swing.mdi.client.MDIFrame;
import org.openswing.swing.message.receive.java.Response;
import org.openswing.swing.message.receive.java.ValueObject;
import org.openswing.swing.util.client.ClientUtils;
import org.openswing.swing.util.java.Consts;

public class SimplesNacionalCabecalhoDetalheController extends FormController {

    private SimplesNacionalCabecalhoDetalhe simplesNacionalCabecalhoDetalhe = null;
    private String pk = null;
    private SimplesNacionalCabecalhoGrid simplesNacionalCabecalhoGrid = null;
    private String acaoServidor;

    public SimplesNacionalCabecalhoDetalheController(SimplesNacionalCabecalhoGrid simplesNacionalCabecalhoGrid, String pk) {
        this.simplesNacionalCabecalhoGrid = simplesNacionalCabecalhoGrid;
        this.pk = pk;
        this.acaoServidor = "simplesNacionalCabecalhoDetalheAction";
        simplesNacionalCabecalhoDetalhe = new SimplesNacionalCabecalhoDetalhe(this);
        simplesNacionalCabecalhoDetalhe.setParentFrame(this.simplesNacionalCabecalhoGrid);
        this.simplesNacionalCabecalhoGrid.pushFrame(simplesNacionalCabecalhoDetalhe);
        MDIFrame.add(simplesNacionalCabecalhoDetalhe);

        if (pk != null) {
            simplesNacionalCabecalhoDetalhe.getForm1().setMode(Consts.READONLY);
            simplesNacionalCabecalhoDetalhe.getForm1().reload();
        } else {
            simplesNacionalCabecalhoDetalhe.getForm1().setMode(Consts.INSERT);
            simplesNacionalCabecalhoDetalhe.getGridControl1().reloadData();
        }
    }

    @Override
    public Response loadData(Class valueObjectClass) {
        return ClientUtils.getData(acaoServidor, new Object[]{Constantes.LOAD, pk});
    }

    @Override
    public void loadDataCompleted(boolean error) {
        SimplesNacionalCabecalhoVO cabecalho = (SimplesNacionalCabecalhoVO) simplesNacionalCabecalhoDetalhe.getForm1().getVOModel().getValueObject();
        this.pk = cabecalho.getId().toString();

        simplesNacionalCabecalhoDetalhe.getGridController().setPk(pk);
        simplesNacionalCabecalhoDetalhe.getGridControl1().reloadData();
    }

    @Override
    public Response insertRecord(ValueObject newPersistentObject) throws Exception {
        List<SimplesNacionalDetalheVO> detalhe = simplesNacionalCabecalhoDetalhe.getGridControl1().getVOListTableModel().getDataVector();
        
        return ClientUtils.getData(acaoServidor, new Object[]{Constantes.INSERT, newPersistentObject, detalhe});
    }

    @Override
    public void afterInsertData() {
        simplesNacionalCabecalhoGrid.getGrid1().reloadData();
        JOptionPane.showMessageDialog(simplesNacionalCabecalhoDetalhe, "Dados salvos com sucesso!", "Informacao do Sistema", JOptionPane.INFORMATION_MESSAGE);
    }

    @Override
    public Response updateRecord(ValueObject oldPersistentObject, ValueObject persistentObject) throws Exception {
        List<SimplesNacionalDetalheVO> detalhe = simplesNacionalCabecalhoDetalhe.getGridControl1().getVOListTableModel().getDataVector();

        return ClientUtils.getData(acaoServidor, new Object[]{Constantes.UPDATE, oldPersistentObject, persistentObject, detalhe});
    }

    @Override
    public void afterEditData() {
        simplesNacionalCabecalhoGrid.getGrid1().reloadData();
        JOptionPane.showMessageDialog(simplesNacionalCabecalhoDetalhe, "Dados alterados com sucesso!", "Informacao do Sistema", JOptionPane.INFORMATION_MESSAGE);
    }
}
