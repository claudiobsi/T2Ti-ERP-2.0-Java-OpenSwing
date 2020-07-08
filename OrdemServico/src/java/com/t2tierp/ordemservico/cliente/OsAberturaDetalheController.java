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
package com.t2tierp.ordemservico.cliente;

import com.t2tierp.ordemservico.java.OsAberturaVO;
import com.t2tierp.padrao.java.Constantes;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;
import org.openswing.swing.form.client.FormController;
import org.openswing.swing.mdi.client.MDIFrame;
import org.openswing.swing.message.receive.java.Response;
import org.openswing.swing.message.receive.java.ValueObject;
import org.openswing.swing.util.client.ClientUtils;
import org.openswing.swing.util.java.Consts;

public class OsAberturaDetalheController extends FormController {

    private OsAberturaDetalhe osAberturaDetalhe = null;
    private String pk = null;
    private OsAberturaGrid osAberturaGrid = null;
    private String acaoServidor;

    public OsAberturaDetalheController(OsAberturaGrid osAberturaGrid, String pk) {
        this.osAberturaGrid = osAberturaGrid;
        this.pk = pk;
        this.acaoServidor = "osAberturaDetalheAction";
        osAberturaDetalhe = new OsAberturaDetalhe(this);
        osAberturaDetalhe.setParentFrame(this.osAberturaGrid);
        this.osAberturaGrid.pushFrame(osAberturaDetalhe);
        MDIFrame.add(osAberturaDetalhe, true);

        if (pk != null) {
            osAberturaDetalhe.getForm1().setMode(Consts.READONLY);
            osAberturaDetalhe.getForm1().reload();
        } else {
            osAberturaDetalhe.getForm1().setMode(Consts.INSERT);
            osAberturaDetalhe.getGridControlEvolucao().reloadData();
            osAberturaDetalhe.getGridControlProdutoServico().reloadData();
            osAberturaDetalhe.getGridControlEquipamento().reloadData();
        }
    }

    @Override
    public void createPersistentObject(ValueObject persistentObject) throws Exception {
        OsAberturaVO osAbertura = (OsAberturaVO) persistentObject;
        osAbertura.setDataInicio(new Date());
        osAbertura.setHoraInicio(new SimpleDateFormat("HH:mm:ss").format(new Date()));
    }

    @Override
    public Response loadData(Class valueObjectClass) {
        return ClientUtils.getData(acaoServidor, new Object[]{Constantes.LOAD, pk});
    }

    @Override
    public void loadDataCompleted(boolean error) {
        OsAberturaVO osAbertura = (OsAberturaVO) osAberturaDetalhe.getForm1().getVOModel().getValueObject();

        osAberturaDetalhe.getEvolucaoGridController().setListaEvolucao(osAbertura.getListaOsEvolucao());
        osAberturaDetalhe.getGridControlEvolucao().reloadData();

        osAberturaDetalhe.getProdutoServicoGridController().setListaProdutoServico(osAbertura.getListaOsProdutoServico());
        osAberturaDetalhe.getGridControlProdutoServico().reloadData();

        osAberturaDetalhe.getAberturaEquipamentoGridController().setListaAberturaEquipamento(osAbertura.getListaOsAberturaEquipamento());
        osAberturaDetalhe.getGridControlEquipamento().reloadData();
    }

    @Override
    public Response insertRecord(ValueObject newPersistentObject) throws Exception {
        OsAberturaVO abertura = (OsAberturaVO) newPersistentObject;
        abertura.setListaOsEvolucao(osAberturaDetalhe.getEvolucaoGridController().getListaEvolucao());
        abertura.setListaOsAberturaEquipamento(osAberturaDetalhe.getAberturaEquipamentoGridController().getListaAberturaEquipamento());
        abertura.setListaOsProdutoServico(osAberturaDetalhe.getProdutoServicoGridController().getListaProdutoServico());

        return ClientUtils.getData(acaoServidor, new Object[]{Constantes.INSERT, abertura});
    }

    @Override
    public void afterInsertData() {
        osAberturaGrid.getGrid1().reloadData();
        JOptionPane.showMessageDialog(osAberturaDetalhe, "Dados salvos com sucesso!", "Informacao do Sistema", JOptionPane.INFORMATION_MESSAGE);
    }

    @Override
    public Response updateRecord(ValueObject oldPersistentObject, ValueObject persistentObject) throws Exception {
        OsAberturaVO abertura = (OsAberturaVO) persistentObject;
        abertura.setListaOsEvolucao(osAberturaDetalhe.getEvolucaoGridController().getListaEvolucao());
        abertura.setListaOsAberturaEquipamento(osAberturaDetalhe.getAberturaEquipamentoGridController().getListaAberturaEquipamento());
        abertura.setListaOsProdutoServico(osAberturaDetalhe.getProdutoServicoGridController().getListaProdutoServico());

        return ClientUtils.getData(acaoServidor, new Object[]{Constantes.UPDATE, oldPersistentObject, abertura});
    }

    @Override
    public void afterEditData() {
        osAberturaGrid.getGrid1().reloadData();
        JOptionPane.showMessageDialog(osAberturaDetalhe, "Dados alterados com sucesso!", "Informacao do Sistema", JOptionPane.INFORMATION_MESSAGE);
    }

    public void geraOrcamento() throws Exception {
        OsAberturaVO osAbertura = (OsAberturaVO) osAberturaDetalhe.getForm1().getVOModel().getValueObject();
        
        Map map = new HashMap();
        map.put("NOME_CLIENTE", osAbertura.getCliente().getPessoa().getNome());
        
        JRBeanCollectionDataSource jrbean = new JRBeanCollectionDataSource(osAberturaDetalhe.getProdutoServicoGridController().getListaProdutoServico());
        JasperPrint jp = JasperFillManager.fillReport(this.getClass().getResourceAsStream("/relatorios/os_orcamento.jasper"), map, jrbean);
        JasperViewer.viewReport(jp, false);
    }
}
