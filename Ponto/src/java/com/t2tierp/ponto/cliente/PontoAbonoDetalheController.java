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
package com.t2tierp.ponto.cliente;

import com.t2tierp.padrao.java.Constantes;
import com.t2tierp.ponto.java.PontoAbonoUtilizacaoVO;
import com.t2tierp.ponto.java.PontoAbonoVO;
import java.util.List;
import javax.swing.JOptionPane;
import org.openswing.swing.form.client.FormController;
import org.openswing.swing.mdi.client.MDIFrame;
import org.openswing.swing.message.receive.java.Response;
import org.openswing.swing.message.receive.java.ValueObject;
import org.openswing.swing.util.client.ClientUtils;
import org.openswing.swing.util.java.Consts;

public class PontoAbonoDetalheController extends FormController {

    private PontoAbonoDetalhe pontoAbonoDetalhe = null;
    private String pk = null;
    private PontoAbonoGrid pontoAbonoGrid = null;
    private String acaoServidor;

    public PontoAbonoDetalheController(PontoAbonoGrid pontoAbonoGrid, String pk) {
        this.pontoAbonoGrid = pontoAbonoGrid;
        this.pk = pk;
        this.acaoServidor = "pontoAbonoDetalheAction";
        pontoAbonoDetalhe = new PontoAbonoDetalhe(this);
        pontoAbonoDetalhe.setParentFrame(this.pontoAbonoGrid);
        this.pontoAbonoGrid.pushFrame(pontoAbonoDetalhe);
        MDIFrame.add(pontoAbonoDetalhe, true);

        if (pk != null) {
            pontoAbonoDetalhe.getForm1().setMode(Consts.READONLY);
            pontoAbonoDetalhe.getForm1().reload();
        } else {
            pontoAbonoDetalhe.getForm1().setMode(Consts.INSERT);
            pontoAbonoDetalhe.getGridControl1().reloadData();
        }
    }

    @Override
    public Response loadData(Class valueObjectClass) {
        return ClientUtils.getData(acaoServidor, new Object[]{Constantes.LOAD, pk});
    }

    @Override
    public void loadDataCompleted(boolean error) {
        PontoAbonoVO abono = (PontoAbonoVO) pontoAbonoDetalhe.getForm1().getVOModel().getValueObject();
        this.pk = abono.getId().toString();

        pontoAbonoDetalhe.getUtilizacaoController().setPk(pk);
        pontoAbonoDetalhe.getGridControl1().reloadData();
    }

    @Override
    public Response insertRecord(ValueObject newPersistentObject) throws Exception {
        List<PontoAbonoUtilizacaoVO> utilizacao = pontoAbonoDetalhe.getGridControl1().getVOListTableModel().getDataVector();
        
        return ClientUtils.getData(acaoServidor, new Object[]{Constantes.INSERT, newPersistentObject, utilizacao});
    }

    @Override
    public void afterInsertData() {
        pontoAbonoGrid.getGrid1().reloadData();
        JOptionPane.showMessageDialog(pontoAbonoDetalhe, "Dados salvos com sucesso!", "Informa��o do Sistema", JOptionPane.INFORMATION_MESSAGE);
    }

    @Override
    public Response updateRecord(ValueObject oldPersistentObject, ValueObject persistentObject) throws Exception {
        return ClientUtils.getData(acaoServidor, new Object[]{Constantes.UPDATE, oldPersistentObject, persistentObject});
    }

    @Override
    public void afterEditData() {
        pontoAbonoGrid.getGrid1().reloadData();
        JOptionPane.showMessageDialog(pontoAbonoDetalhe, "Dados alterados com sucesso!", "Informa��o do Sistema", JOptionPane.INFORMATION_MESSAGE);
    }
}
