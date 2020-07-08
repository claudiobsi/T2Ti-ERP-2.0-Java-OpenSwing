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

import com.t2tierp.cadastros.java.EmpresaVO;
import com.t2tierp.padrao.cliente.Container;
import com.t2tierp.padrao.java.Constantes;
import com.t2tierp.ponto.java.PontoClassificacaoJornadaVO;
import javax.swing.JOptionPane;
import org.openswing.swing.form.client.FormController;
import org.openswing.swing.mdi.client.MDIFrame;
import org.openswing.swing.message.receive.java.Response;
import org.openswing.swing.message.receive.java.ValueObject;
import org.openswing.swing.util.client.ClientUtils;
import org.openswing.swing.util.java.Consts;

public class PontoClassificacaoJornadaDetalheController extends FormController {

    private PontoClassificacaoJornadaDetalhe pontoClassificacaoJornadaDetalhe = null;
    private String pk = null;
    private PontoClassificacaoJornadaGrid pontoClassificacaoJornadaGrid = null;
    private String acaoServidor;

    public PontoClassificacaoJornadaDetalheController(PontoClassificacaoJornadaGrid pontoClassificacaoJornadaGrid, String pk) {
        this.pontoClassificacaoJornadaGrid = pontoClassificacaoJornadaGrid;
        this.pk = pk;
        this.acaoServidor = "pontoClassificacaoJornadaDetalheAction";
        pontoClassificacaoJornadaDetalhe = new PontoClassificacaoJornadaDetalhe(this);
        pontoClassificacaoJornadaDetalhe.setParentFrame(this.pontoClassificacaoJornadaGrid);
        this.pontoClassificacaoJornadaGrid.pushFrame(pontoClassificacaoJornadaDetalhe);
        MDIFrame.add(pontoClassificacaoJornadaDetalhe);

        if (pk != null) {
            pontoClassificacaoJornadaDetalhe.getForm1().setMode(Consts.READONLY);
            pontoClassificacaoJornadaDetalhe.getForm1().reload();
        } else {
            pontoClassificacaoJornadaDetalhe.getForm1().setMode(Consts.INSERT);
        }
    }

    @Override
    public Response loadData(Class valueObjectClass) {
        return ClientUtils.getData(acaoServidor, new Object[]{Constantes.LOAD, pk});
    }

    @Override
    public Response insertRecord(ValueObject newPersistentObject) throws Exception {
        EmpresaVO empresa = (EmpresaVO) Container.getContainer().get("empresa");

        PontoClassificacaoJornadaVO classificacaoJornada = (PontoClassificacaoJornadaVO) newPersistentObject;
        classificacaoJornada.setEmpresa(empresa);

        return ClientUtils.getData(acaoServidor, new Object[]{Constantes.INSERT, newPersistentObject});
    }

    @Override
    public void afterInsertData() {
        pontoClassificacaoJornadaGrid.getGrid1().reloadData();
        JOptionPane.showMessageDialog(pontoClassificacaoJornadaDetalhe, "Dados salvos com sucesso!", "Informacao do Sistema", JOptionPane.INFORMATION_MESSAGE);
    }

    @Override
    public Response updateRecord(ValueObject oldPersistentObject, ValueObject persistentObject) throws Exception {
        return ClientUtils.getData(acaoServidor, new Object[]{Constantes.UPDATE, oldPersistentObject, persistentObject});
    }

    @Override
    public void afterEditData() {
        pontoClassificacaoJornadaGrid.getGrid1().reloadData();
        JOptionPane.showMessageDialog(pontoClassificacaoJornadaDetalhe, "Dados alterados com sucesso!", "Informacao do Sistema", JOptionPane.INFORMATION_MESSAGE);
    }
}
