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
package com.t2tierp.agendacorporativa.cliente;

import com.t2tierp.agendacorporativa.java.AgendaCompromissoVO;
import com.t2tierp.agendacorporativa.java.ReuniaoSalaEventoVO;
import com.t2tierp.padrao.java.Constantes;
import java.util.Calendar;
import java.util.List;
import javax.swing.JOptionPane;
import org.openswing.swing.form.client.FormController;
import org.openswing.swing.mdi.client.MDIFrame;
import org.openswing.swing.message.receive.java.Response;
import org.openswing.swing.message.receive.java.VOListResponse;
import org.openswing.swing.message.receive.java.ValueObject;
import org.openswing.swing.util.client.ClientUtils;
import org.openswing.swing.util.java.Consts;

public class AgendaCompromissoDetalheController extends FormController {

    private AgendaCompromissoDetalhe agendaCompromissoDetalhe = null;
    private String pk = null;
    private AgendaCompromissoGrid agendaCompromissoGrid = null;
    private String acaoServidor;

    public AgendaCompromissoDetalheController(AgendaCompromissoGrid agendaCompromissoGrid, String pk) {
        this.agendaCompromissoGrid = agendaCompromissoGrid;
        this.pk = pk;
        this.acaoServidor = "agendaCompromissoDetalheAction";
        agendaCompromissoDetalhe = new AgendaCompromissoDetalhe(this);
        agendaCompromissoDetalhe.setParentFrame(this.agendaCompromissoGrid);
        this.agendaCompromissoGrid.pushFrame(agendaCompromissoDetalhe);
        MDIFrame.add(agendaCompromissoDetalhe, true);

        if (pk != null) {
            agendaCompromissoDetalhe.getForm1().setMode(Consts.READONLY);
            agendaCompromissoDetalhe.getFormReuniao().setMode(Consts.READONLY);
            agendaCompromissoDetalhe.getForm1().reload();
        } else {
            agendaCompromissoDetalhe.getForm1().setMode(Consts.INSERT);
            agendaCompromissoDetalhe.getGridControl1().reloadData();
            agendaCompromissoDetalhe.getFormReuniao().setMode(Consts.READONLY);
        }
    }

    @Override
    public Response loadData(Class valueObjectClass) {
        return ClientUtils.getData(acaoServidor, new Object[]{Constantes.LOAD, pk});
    }

    @Override
    public void loadDataCompleted(boolean error) {
        AgendaCompromissoVO compromisso = (AgendaCompromissoVO) agendaCompromissoDetalhe.getForm1().getVOModel().getValueObject();
        agendaCompromissoDetalhe.getGridConvidadoController().setConvidados(compromisso.getListaAgendaCompromissoConvidado());
        agendaCompromissoDetalhe.getGridControl1().reloadData();
        
        agendaCompromissoDetalhe.getReuniaoController().setCompromisso(compromisso);
        agendaCompromissoDetalhe.getFormReuniao().reload();
    }

    @Override
    public Response insertRecord(ValueObject newPersistentObject) throws Exception {
        ((AgendaCompromissoVO) newPersistentObject).setListaAgendaCompromissoConvidado(agendaCompromissoDetalhe.getGridConvidadoController().getConvidados());
        
        ReuniaoSalaEventoVO reuniaoSalaEvento = (ReuniaoSalaEventoVO) agendaCompromissoDetalhe.getFormReuniao().getVOModel().getValueObject();

        return ClientUtils.getData(acaoServidor, new Object[]{Constantes.INSERT, newPersistentObject, reuniaoSalaEvento});
    }

    @Override
    public void afterInsertData() {
        agendaCompromissoGrid.getGrid1().reloadData();
        JOptionPane.showMessageDialog(agendaCompromissoDetalhe, "Dados salvos com sucesso!", "Informacao do Sistema", JOptionPane.INFORMATION_MESSAGE);
    }

    @Override
    public Response updateRecord(ValueObject oldPersistentObject, ValueObject persistentObject) throws Exception {
        ((AgendaCompromissoVO) persistentObject).setListaAgendaCompromissoConvidado(agendaCompromissoDetalhe.getGridConvidadoController().getConvidados());

        ReuniaoSalaEventoVO reuniaoSalaEvento = (ReuniaoSalaEventoVO) agendaCompromissoDetalhe.getFormReuniao().getVOModel().getValueObject();
        
        return ClientUtils.getData(acaoServidor, new Object[]{Constantes.UPDATE, oldPersistentObject, persistentObject, reuniaoSalaEvento});
    }

    @Override
    public void afterEditData() {
        agendaCompromissoGrid.getGrid1().reloadData();
        JOptionPane.showMessageDialog(agendaCompromissoDetalhe, "Dados alterados com sucesso!", "Informacao do Sistema", JOptionPane.INFORMATION_MESSAGE);
    }

    public void visualizaAgenda() {
        try {
            AgendaCompromissoVO compromisso = (AgendaCompromissoVO) agendaCompromissoDetalhe.getForm1().getVOModel().getValueObject();

            Response res = ClientUtils.getData(acaoServidor, new Object[]{99, compromisso});
            if (res.isError()) {
                throw new Exception(res.getErrorMessage());
            }

            List<AgendaCompromissoVO> listaCompromisso = ((VOListResponse) res).getRows();

            Calendar dataCompromisso = Calendar.getInstance();
            dataCompromisso.setTime(compromisso.getDataCompromisso());

            new AgendaCalendarioGridController(dataCompromisso, listaCompromisso);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(agendaCompromissoDetalhe, e.getMessage(), "Erro do Sistema", JOptionPane.ERROR_MESSAGE);
        }
    }

}
