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

import com.t2tierp.padrao.java.Constantes;
import com.t2tierp.tributacao.java.TributCofinsCodApuracaoVO;
import com.t2tierp.tributacao.java.TributConfiguraOfGtVO;
import com.t2tierp.tributacao.java.TributIcmsUfVO;
import com.t2tierp.tributacao.java.TributIpiDipiVO;
import com.t2tierp.tributacao.java.TributPisCodApuracaoVO;
import java.util.List;
import javax.swing.JOptionPane;
import org.openswing.swing.form.client.FormController;
import org.openswing.swing.mdi.client.MDIFrame;
import org.openswing.swing.message.receive.java.Response;
import org.openswing.swing.message.receive.java.ValueObject;
import org.openswing.swing.util.client.ClientUtils;
import org.openswing.swing.util.java.Consts;

public class TributConfiguraOfGtDetalheController extends FormController {

    private TributConfiguraOfGtDetalhe tributConfiguraOfGtDetalhe = null;
    private String pk = null;
    private TributConfiguraOfGtGrid tributConfiguraOfGtGrid = null;
    private String acaoServidor;

    public TributConfiguraOfGtDetalheController(TributConfiguraOfGtGrid tributConfiguraOfGtGrid, String pk) {
        this.tributConfiguraOfGtGrid = tributConfiguraOfGtGrid;
        this.pk = pk;
        this.acaoServidor = "tributConfiguraOfGtDetalheAction";
        tributConfiguraOfGtDetalhe = new TributConfiguraOfGtDetalhe(this);
        tributConfiguraOfGtDetalhe.setParentFrame(this.tributConfiguraOfGtGrid);
        this.tributConfiguraOfGtGrid.pushFrame(tributConfiguraOfGtDetalhe);
        MDIFrame.add(tributConfiguraOfGtDetalhe, true);

        if (pk != null) {
            tributConfiguraOfGtDetalhe.getForm1().setMode(Consts.READONLY);
            tributConfiguraOfGtDetalhe.getForm1().reload();
        } else {
            tributConfiguraOfGtDetalhe.getForm1().setMode(Consts.INSERT);
            tributConfiguraOfGtDetalhe.getGridControlIcms().reloadData();
            tributConfiguraOfGtDetalhe.getFormPis().setMode(Consts.INSERT);
            tributConfiguraOfGtDetalhe.getFormCofins().setMode(Consts.INSERT);
            tributConfiguraOfGtDetalhe.getFormIpi().setMode(Consts.INSERT);
        }
    }

    @Override
    public Response loadData(Class valueObjectClass) {
        return ClientUtils.getData(acaoServidor, new Object[]{Constantes.LOAD, pk});
    }

    @Override
    public void loadDataCompleted(boolean error) {
        TributConfiguraOfGtVO tributConfiguraOfGtVO = (TributConfiguraOfGtVO) tributConfiguraOfGtDetalhe.getForm1().getVOModel().getValueObject();

        tributConfiguraOfGtDetalhe.getIcmsUfGridController().setPk(pk);
        tributConfiguraOfGtDetalhe.getGridControlIcms().reloadData();

        tributConfiguraOfGtDetalhe.getPisCodApuracaoController().setTributConfiguraOfGt(tributConfiguraOfGtVO);
        tributConfiguraOfGtDetalhe.getFormPis().reload();

        tributConfiguraOfGtDetalhe.getCofinsCodApuracaoController().setTributConfiguraOfGt(tributConfiguraOfGtVO);
        tributConfiguraOfGtDetalhe.getFormCofins().reload();

        tributConfiguraOfGtDetalhe.getIpiDipiController().setTributConfiguraOfGt(tributConfiguraOfGtVO);
        tributConfiguraOfGtDetalhe.getFormIpi().reload();
    }

    @Override
    public Response insertRecord(ValueObject newPersistentObject) throws Exception {
        List<TributIcmsUfVO> listaIcmsUf = tributConfiguraOfGtDetalhe.getGridControlIcms().getVOListTableModel().getDataVector();
        TributPisCodApuracaoVO pisCodApuracao = (TributPisCodApuracaoVO) tributConfiguraOfGtDetalhe.getFormPis().getVOModel().getValueObject();
        TributCofinsCodApuracaoVO cofinsCodApuracao = (TributCofinsCodApuracaoVO) tributConfiguraOfGtDetalhe.getFormCofins().getVOModel().getValueObject();
        TributIpiDipiVO ipiDipi = (TributIpiDipiVO) tributConfiguraOfGtDetalhe.getFormIpi().getVOModel().getValueObject();

        return ClientUtils.getData(acaoServidor, new Object[]{Constantes.INSERT, newPersistentObject, listaIcmsUf, pisCodApuracao, cofinsCodApuracao, ipiDipi});
    }

    @Override
    public void afterInsertData() {
        tributConfiguraOfGtGrid.getGrid1().reloadData();
        JOptionPane.showMessageDialog(tributConfiguraOfGtDetalhe, "Dados salvos com sucesso!", "Informacao do Sistema", JOptionPane.INFORMATION_MESSAGE);
    }

    @Override
    public Response updateRecord(ValueObject oldPersistentObject, ValueObject persistentObject) throws Exception {
        List<TributIcmsUfVO> listaIcmsUf = tributConfiguraOfGtDetalhe.getGridControlIcms().getVOListTableModel().getDataVector();
        TributPisCodApuracaoVO pisCodApuracao = (TributPisCodApuracaoVO) tributConfiguraOfGtDetalhe.getFormPis().getVOModel().getValueObject();
        TributCofinsCodApuracaoVO cofinsCodApuracao = (TributCofinsCodApuracaoVO) tributConfiguraOfGtDetalhe.getFormCofins().getVOModel().getValueObject();
        TributIpiDipiVO ipiDipi = (TributIpiDipiVO) tributConfiguraOfGtDetalhe.getFormIpi().getVOModel().getValueObject();

        return ClientUtils.getData(acaoServidor, new Object[]{Constantes.UPDATE, oldPersistentObject, persistentObject, listaIcmsUf, pisCodApuracao, cofinsCodApuracao, ipiDipi});
    }

    @Override
    public void afterEditData() {
        tributConfiguraOfGtGrid.getGrid1().reloadData();
        JOptionPane.showMessageDialog(tributConfiguraOfGtDetalhe, "Dados alterados com sucesso!", "Informacao do Sistema", JOptionPane.INFORMATION_MESSAGE);
    }
}
