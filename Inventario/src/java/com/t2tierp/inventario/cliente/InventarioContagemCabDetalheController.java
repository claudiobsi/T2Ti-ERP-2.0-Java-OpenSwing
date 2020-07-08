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
package com.t2tierp.inventario.cliente;

import com.t2tierp.cadastros.java.EmpresaVO;
import com.t2tierp.cadastros.java.ProdutoVO;
import com.t2tierp.inventario.java.InventarioContagemCabVO;
import com.t2tierp.inventario.java.InventarioContagemDetVO;
import com.t2tierp.padrao.cliente.Container;
import com.t2tierp.padrao.java.Biblioteca;
import com.t2tierp.padrao.java.Constantes;
import java.math.BigDecimal;
import java.util.List;
import javax.swing.JOptionPane;
import org.openswing.swing.form.client.FormController;
import org.openswing.swing.mdi.client.MDIFrame;
import org.openswing.swing.message.receive.java.ErrorResponse;
import org.openswing.swing.message.receive.java.Response;
import org.openswing.swing.message.receive.java.VOListResponse;
import org.openswing.swing.message.receive.java.ValueObject;
import org.openswing.swing.util.client.ClientUtils;
import org.openswing.swing.util.java.Consts;

public class InventarioContagemCabDetalheController extends FormController {

    private InventarioContagemCabDetalhe inventarioContagemCabDetalhe = null;
    private String pk = null;
    private InventarioContagemCabGrid inventarioContagemCabGrid = null;
    private String acaoServidor;

    public InventarioContagemCabDetalheController(InventarioContagemCabGrid inventarioContagemCabGrid, String pk) {
        this.inventarioContagemCabGrid = inventarioContagemCabGrid;
        this.pk = pk;
        this.acaoServidor = "inventarioContagemCabDetalheAction";
        inventarioContagemCabDetalhe = new InventarioContagemCabDetalhe(this);
        inventarioContagemCabDetalhe.setParentFrame(this.inventarioContagemCabGrid);
        this.inventarioContagemCabGrid.pushFrame(inventarioContagemCabDetalhe);
        MDIFrame.add(inventarioContagemCabDetalhe, true);

        if (pk != null) {
            inventarioContagemCabDetalhe.getForm1().setMode(Consts.READONLY);
            inventarioContagemCabDetalhe.getForm1().reload();
        } else {
            inventarioContagemCabDetalhe.getForm1().setMode(Consts.INSERT);
            inventarioContagemCabDetalhe.getGridControl1().reloadData();
        }
    }

    @Override
    public Response loadData(Class valueObjectClass) {
        return ClientUtils.getData(acaoServidor, new Object[]{Constantes.LOAD, pk});
    }

    @Override
    public void loadDataCompleted(boolean error) {
        inventarioContagemCabDetalhe.getItensController().setPk(pk);
        inventarioContagemCabDetalhe.getGridControl1().reloadData();
    }
    
    @Override
    public Response insertRecord(ValueObject newPersistentObject) throws Exception {
        List<InventarioContagemDetVO> itensContagem = inventarioContagemCabDetalhe.getGridControl1().getVOListTableModel().getDataVector();

        if (itensContagem.isEmpty()) {
            return new ErrorResponse("Nenhum produto foi selecionado para contagem");
        }

        EmpresaVO empresa = (EmpresaVO) Container.getContainer().get("empresa");
        ((InventarioContagemCabVO) newPersistentObject).setEmpresa(empresa);

        if (inventarioContagemCabDetalhe.atualizarEstoque()) {
            ((InventarioContagemCabVO) newPersistentObject).setEstoqueAtualizado("S");
        } else {
            ((InventarioContagemCabVO) newPersistentObject).setEstoqueAtualizado("N");
        }

        return ClientUtils.getData(acaoServidor, new Object[]{Constantes.INSERT, newPersistentObject, itensContagem});
    }

    @Override
    public void afterInsertData() {
        inventarioContagemCabGrid.getGrid1().reloadData();
        JOptionPane.showMessageDialog(inventarioContagemCabDetalhe, "Dados salvos com sucesso!", "Informacao do Sistema", JOptionPane.INFORMATION_MESSAGE);
    }

    @Override
    public Response updateRecord(ValueObject oldPersistentObject, ValueObject persistentObject) throws Exception {
        if (((InventarioContagemCabVO) persistentObject).getEstoqueAtualizado().equals("S")) {
            return new ErrorResponse("Estoque já atualizado nesta contagem. Alteração não permitida");
        }

        List<InventarioContagemDetVO> itensContagem = inventarioContagemCabDetalhe.getGridControl1().getVOListTableModel().getDataVector();

        if (itensContagem.isEmpty()) {
            return new ErrorResponse("Nenhum produto foi selecionado para contagem");
        }

        if (inventarioContagemCabDetalhe.atualizarEstoque()) {
            ((InventarioContagemCabVO) persistentObject).setEstoqueAtualizado("S");
        }

        return ClientUtils.getData(acaoServidor, new Object[]{Constantes.UPDATE, oldPersistentObject, persistentObject, itensContagem});
    }

    @Override
    public void afterEditData() {
        inventarioContagemCabGrid.getGrid1().reloadData();
        JOptionPane.showMessageDialog(inventarioContagemCabDetalhe, "Dados alterados com sucesso!", "Informacao do Sistema", JOptionPane.INFORMATION_MESSAGE);
    }
    
    public void buscaGrupoProdutos() {
        InventarioContagemCabVO contagem = (InventarioContagemCabVO) inventarioContagemCabDetalhe.getForm1().getVOModel().getValueObject();
        if (contagem.getProdutoSubGrupo().getId() != null) {
            Response res = ClientUtils.getData(acaoServidor, new Object[]{99, contagem.getProdutoSubGrupo()});
            if (res.isError()) {
                JOptionPane.showMessageDialog(inventarioContagemCabDetalhe, "Ocorreu um erro ao buscar os produtos do sub grupo.\n" + res.getErrorMessage(), "Errro do Sistema", JOptionPane.ERROR_MESSAGE);
                return;
            }
            List<ProdutoVO> produtos = ((VOListResponse) res).getRows();
            InventarioContagemDetVO contagemDetalhe;
            for (int i = 0; i < produtos.size(); i++) {
                contagemDetalhe = new InventarioContagemDetVO();
                contagemDetalhe.setProduto(produtos.get(i));
                contagemDetalhe.setQuantidadeSistema(produtos.get(i).getQuantidadeEstoque());

                inventarioContagemCabDetalhe.getGridControl1().getVOListTableModel().addObject(contagemDetalhe);
            }
            if (produtos.isEmpty()) {
                JOptionPane.showMessageDialog(inventarioContagemCabDetalhe, "Nenhum produto encontrado para o grupo selecionado.", "Informação do Sistema", JOptionPane.INFORMATION_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(inventarioContagemCabDetalhe, "Selecione um grupo.", "Informação do Sistema", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public void efetuarCalculos() {
        List<InventarioContagemDetVO> itensContagem = inventarioContagemCabDetalhe.getGridControl1().getVOListTableModel().getDataVector();
        if (itensContagem.isEmpty()) {
            JOptionPane.showMessageDialog(inventarioContagemCabDetalhe, "Nenhum item para calcular.", "Informação do Sistema", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        InventarioContagemDetVO item;
        for (int i = 0; i < itensContagem.size(); i++) {
            item = itensContagem.get(i);
            
            // EXERCICIO
            // Verifique qual dos três campos foi selecionado para o fechamento da contagem (FECHADO_CONTAGEM)
            // e realize o calculo com base nesse campo            
            if (item.getContagem01() != null && item.getQuantidadeSistema() != null) {
                //acuracidade dos registros = (registros sistema / registros contados) X 100 }
                if (item.getContagem01().compareTo(BigDecimal.ZERO) != 0) {
                    item.setAcuracidade(Biblioteca.multiplica(Biblioteca.divide(item.getQuantidadeSistema(), item.getContagem01()), BigDecimal.valueOf(100)));
                } else {
                    item.setAcuracidade(BigDecimal.ZERO);
                }
                //divergencia dos registros = ((registros contados - registros sistema) / registros sistema) X 100 }
                item.setDivergencia(Biblioteca.multiplica(Biblioteca.divide(Biblioteca.subtrai(item.getContagem01(), item.getQuantidadeSistema()), item.getQuantidadeSistema()), BigDecimal.valueOf(100)));
            }
            inventarioContagemCabDetalhe.getGridControl1().getVOListTableModel().updateObjectAt(i);
        }
    }
    
}
