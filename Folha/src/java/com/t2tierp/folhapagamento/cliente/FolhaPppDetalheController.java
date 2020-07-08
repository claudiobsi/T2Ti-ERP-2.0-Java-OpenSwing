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
package com.t2tierp.folhapagamento.cliente;

import com.t2tierp.folhapagamento.java.FolhaPppAtividadeVO;
import com.t2tierp.folhapagamento.java.FolhaPppCatVO;
import com.t2tierp.folhapagamento.java.FolhaPppExameMedicoVO;
import com.t2tierp.folhapagamento.java.FolhaPppFatorRiscoVO;
import com.t2tierp.folhapagamento.java.FolhaPppVO;
import com.t2tierp.padrao.java.Constantes;
import java.util.List;
import javax.swing.JOptionPane;
import org.openswing.swing.form.client.FormController;
import org.openswing.swing.mdi.client.MDIFrame;
import org.openswing.swing.message.receive.java.Response;
import org.openswing.swing.message.receive.java.ValueObject;
import org.openswing.swing.util.client.ClientUtils;
import org.openswing.swing.util.java.Consts;

public class FolhaPppDetalheController extends FormController {

    private FolhaPppDetalhe folhaPppDetalhe = null;
    private String pk = null;
    private FolhaPppGrid folhaPppGrid = null;
    private String acaoServidor;

    public FolhaPppDetalheController(FolhaPppGrid folhaPppGrid, String pk) {
        this.folhaPppGrid = folhaPppGrid;
        this.pk = pk;
        this.acaoServidor = "folhaPppDetalheAction";
        folhaPppDetalhe = new FolhaPppDetalhe(this);
        folhaPppDetalhe.setParentFrame(this.folhaPppGrid);
        this.folhaPppGrid.pushFrame(folhaPppDetalhe);
        MDIFrame.add(folhaPppDetalhe, true);

        if (pk != null) {
            folhaPppDetalhe.getForm1().setMode(Consts.READONLY);
            folhaPppDetalhe.getForm1().reload();
        } else {
            folhaPppDetalhe.getForm1().setMode(Consts.INSERT);
            folhaPppDetalhe.getGridCat().reloadData();
            folhaPppDetalhe.getGridAtividade().reloadData();
            folhaPppDetalhe.getGridFatorRisco().reloadData();
            folhaPppDetalhe.getGridExameMedico().reloadData();
        }
    }

    @Override
    public Response loadData(Class valueObjectClass) {
        return ClientUtils.getData(acaoServidor, new Object[]{Constantes.LOAD, pk});
    }

    @Override
    public void loadDataCompleted(boolean error) {
        FolhaPppVO ppp = (FolhaPppVO) folhaPppDetalhe.getForm1().getVOModel().getValueObject();
        this.pk = ppp.getId().toString();

        folhaPppDetalhe.getCatController().setPk(pk);
        folhaPppDetalhe.getGridCat().reloadData();
        
        folhaPppDetalhe.getAtividadeController().setPk(pk);
        folhaPppDetalhe.getGridAtividade().reloadData();

        folhaPppDetalhe.getFatorRiscoController().setPk(pk);
        folhaPppDetalhe.getGridFatorRisco().reloadData();

        folhaPppDetalhe.getExameMedicoController().setPk(pk);
        folhaPppDetalhe.getGridExameMedico().reloadData();
    }

    @Override
    public Response insertRecord(ValueObject newPersistentObject) throws Exception {
        List<FolhaPppCatVO> cat = folhaPppDetalhe.getGridCat().getVOListTableModel().getDataVector();
        List<FolhaPppAtividadeVO> atividade = folhaPppDetalhe.getGridAtividade().getVOListTableModel().getDataVector();
        List<FolhaPppFatorRiscoVO> fatorRisco = folhaPppDetalhe.getGridFatorRisco().getVOListTableModel().getDataVector();
        List<FolhaPppExameMedicoVO> exameMedico = folhaPppDetalhe.getGridExameMedico().getVOListTableModel().getDataVector();
        
        return ClientUtils.getData(acaoServidor, new Object[]{Constantes.INSERT, newPersistentObject, cat, atividade, fatorRisco, exameMedico});
    }

    @Override
    public void afterInsertData() {
        folhaPppGrid.getGrid1().reloadData();
        JOptionPane.showMessageDialog(folhaPppDetalhe, "Dados salvos com sucesso!", "Informacao do Sistema", JOptionPane.INFORMATION_MESSAGE);
    }

    @Override
    public Response updateRecord(ValueObject oldPersistentObject, ValueObject persistentObject) throws Exception {
        List<FolhaPppCatVO> cat = folhaPppDetalhe.getGridCat().getVOListTableModel().getDataVector();
        List<FolhaPppAtividadeVO> atividade = folhaPppDetalhe.getGridAtividade().getVOListTableModel().getDataVector();
        List<FolhaPppFatorRiscoVO> fatorRisco = folhaPppDetalhe.getGridFatorRisco().getVOListTableModel().getDataVector();
        List<FolhaPppExameMedicoVO> exameMedico = folhaPppDetalhe.getGridExameMedico().getVOListTableModel().getDataVector();

        return ClientUtils.getData(acaoServidor, new Object[]{Constantes.UPDATE, oldPersistentObject, persistentObject, cat, atividade, fatorRisco, exameMedico});
    }

    @Override
    public void afterEditData() {
        folhaPppGrid.getGrid1().reloadData();
        JOptionPane.showMessageDialog(folhaPppDetalhe, "Dados alterados com sucesso!", "Informacao do Sistema", JOptionPane.INFORMATION_MESSAGE);
    }
}
