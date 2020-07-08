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
package com.t2tierp.pcp.cliente;

import com.t2tierp.cadastros.java.EmpresaVO;
import com.t2tierp.padrao.cliente.Container;
import com.t2tierp.padrao.java.Constantes;
import com.t2tierp.pcp.java.PcpInstrucaoOpVO;
import com.t2tierp.pcp.java.PcpOpCabecalhoVO;
import com.t2tierp.pcp.java.PcpOpDetalheVO;
import java.util.List;
import javax.swing.JOptionPane;
import org.openswing.swing.form.client.FormController;
import org.openswing.swing.mdi.client.MDIFrame;
import org.openswing.swing.message.receive.java.Response;
import org.openswing.swing.message.receive.java.ValueObject;
import org.openswing.swing.util.client.ClientUtils;
import org.openswing.swing.util.java.Consts;

public class PcpOpCabecalhoDetalheController extends FormController {

    private PcpOpCabecalhoDetalhe pcpOpCabecalhoDetalhe = null;
    private String pk = null;
    private PcpOpCabecalhoGrid pcpOpCabecalhoGrid = null;
    private String acaoServidor;

    public PcpOpCabecalhoDetalheController(PcpOpCabecalhoGrid pcpOpCabecalhoGrid, String pk) {
        this.pcpOpCabecalhoGrid = pcpOpCabecalhoGrid;
        this.pk = pk;
        this.acaoServidor = "pcpOpCabecalhoDetalheAction";
        pcpOpCabecalhoDetalhe = new PcpOpCabecalhoDetalhe(this);
        pcpOpCabecalhoDetalhe.setParentFrame(this.pcpOpCabecalhoGrid);
        this.pcpOpCabecalhoGrid.pushFrame(pcpOpCabecalhoDetalhe);
        MDIFrame.add(pcpOpCabecalhoDetalhe, true);

        if (pk != null) {
            pcpOpCabecalhoDetalhe.getForm1().setMode(Consts.READONLY);
            pcpOpCabecalhoDetalhe.getForm1().reload();
        } else {
            pcpOpCabecalhoDetalhe.getForm1().setMode(Consts.INSERT);
            pcpOpCabecalhoDetalhe.getGridControl1().reloadData();
            pcpOpCabecalhoDetalhe.getGridControl2().reloadData();
        }
    }

    @Override
    public Response loadData(Class valueObjectClass) {
        return ClientUtils.getData(acaoServidor, new Object[]{Constantes.LOAD, pk});
    }

    @Override
    public void loadDataCompleted(boolean error) {
        PcpOpCabecalhoVO pcp = (PcpOpCabecalhoVO) pcpOpCabecalhoDetalhe.getForm1().getVOModel().getValueObject();

        pcpOpCabecalhoDetalhe.getDetalheController().setIdPcpCabecalho(String.valueOf(pcp.getId()));
        pcpOpCabecalhoDetalhe.getGridControl1().reloadData();
        
        pcpOpCabecalhoDetalhe.getInstrucaoOpController().setIdPcpCabecalho(String.valueOf(pcp.getId()));
        pcpOpCabecalhoDetalhe.getGridControl2().reloadData();
        
    }

    @Override
    public Response insertRecord(ValueObject newPersistentObject) throws Exception {
        PcpOpCabecalhoVO pcp = (PcpOpCabecalhoVO) newPersistentObject;
        pcp.setEmpresa((EmpresaVO) Container.getContainer().get("empresa"));
        
        List<PcpOpDetalheVO> detalhe = pcpOpCabecalhoDetalhe.getGridControl1().getVOListTableModel().getDataVector();
        
        List<PcpInstrucaoOpVO> pcpInstrucao = pcpOpCabecalhoDetalhe.getGridControl2().getVOListTableModel().getDataVector();
        
        return ClientUtils.getData(acaoServidor, new Object[]{Constantes.INSERT, newPersistentObject, detalhe, pcpInstrucao});
    }

    @Override
    public void afterInsertData() {
        pcpOpCabecalhoGrid.getGrid1().reloadData();
        JOptionPane.showMessageDialog(pcpOpCabecalhoDetalhe, "Dados salvos com sucesso!", "Informacao do Sistema", JOptionPane.INFORMATION_MESSAGE);
    }

    @Override
    public Response updateRecord(ValueObject oldPersistentObject, ValueObject persistentObject) throws Exception {
        List<PcpOpDetalheVO> detalhe = pcpOpCabecalhoDetalhe.getGridControl1().getVOListTableModel().getDataVector();

        List<PcpInstrucaoOpVO> pcpInstrucao = pcpOpCabecalhoDetalhe.getGridControl2().getVOListTableModel().getDataVector();
        
        return ClientUtils.getData(acaoServidor, new Object[]{Constantes.UPDATE, oldPersistentObject, persistentObject, detalhe, pcpInstrucao});
    }

    @Override
    public void afterEditData() {
        pcpOpCabecalhoGrid.getGrid1().reloadData();
        JOptionPane.showMessageDialog(pcpOpCabecalhoDetalhe, "Dados alterados com sucesso!", "Informacao do Sistema", JOptionPane.INFORMATION_MESSAGE);
    }
}
