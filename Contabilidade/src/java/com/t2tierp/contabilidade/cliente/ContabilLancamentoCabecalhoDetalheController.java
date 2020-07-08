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
package com.t2tierp.contabilidade.cliente;

import com.t2tierp.cadastros.java.EmpresaVO;
import com.t2tierp.contabilidade.java.ContabilLancamentoCabecalhoVO;
import com.t2tierp.contabilidade.java.ContabilLancamentoDetalheVO;
import com.t2tierp.padrao.cliente.Container;
import com.t2tierp.padrao.java.Constantes;
import java.math.BigDecimal;
import java.util.List;
import javax.swing.JOptionPane;
import org.openswing.swing.form.client.FormController;
import org.openswing.swing.mdi.client.MDIFrame;
import org.openswing.swing.message.receive.java.Response;
import org.openswing.swing.message.receive.java.ValueObject;
import org.openswing.swing.util.client.ClientUtils;
import org.openswing.swing.util.java.Consts;

public class ContabilLancamentoCabecalhoDetalheController extends FormController {

    private ContabilLancamentoCabecalhoDetalhe contabilLancamentoCabecalhoDetalhe = null;
    private String pk = null;
    private ContabilLancamentoCabecalhoGrid contabilLancamentoCabecalhoGrid = null;
    private String acaoServidor;

    public ContabilLancamentoCabecalhoDetalheController(ContabilLancamentoCabecalhoGrid contabilLancamentoCabecalhoGrid, String pk) {
        this.contabilLancamentoCabecalhoGrid = contabilLancamentoCabecalhoGrid;
        this.pk = pk;
        this.acaoServidor = "contabilLancamentoCabecalhoDetalheAction";
        contabilLancamentoCabecalhoDetalhe = new ContabilLancamentoCabecalhoDetalhe(this);
        contabilLancamentoCabecalhoDetalhe.setParentFrame(this.contabilLancamentoCabecalhoGrid);
        this.contabilLancamentoCabecalhoGrid.pushFrame(contabilLancamentoCabecalhoDetalhe);
        MDIFrame.add(contabilLancamentoCabecalhoDetalhe, true);

        if (pk != null) {
            contabilLancamentoCabecalhoDetalhe.getForm1().setMode(Consts.READONLY);
            contabilLancamentoCabecalhoDetalhe.getForm1().reload();
        } else {
            contabilLancamentoCabecalhoDetalhe.getForm1().setMode(Consts.INSERT);
            contabilLancamentoCabecalhoDetalhe.getGridControl1().reloadData();
        }
    }

    @Override
    public Response loadData(Class valueObjectClass) {
        return ClientUtils.getData(acaoServidor, new Object[]{Constantes.LOAD, pk});
    }

    @Override
    public void loadDataCompleted(boolean error) {
        ContabilLancamentoCabecalhoVO lancamento = (ContabilLancamentoCabecalhoVO) contabilLancamentoCabecalhoDetalhe.getForm1().getVOModel().getValueObject();
        this.pk = lancamento.getId().toString();

        contabilLancamentoCabecalhoDetalhe.getGridController().setPk(pk);
        contabilLancamentoCabecalhoDetalhe.getGridControl1().reloadData();
    }

    @Override
    public Response insertRecord(ValueObject newPersistentObject) throws Exception {
        List<ContabilLancamentoDetalheVO> detalhe = contabilLancamentoCabecalhoDetalhe.getGridControl1().getVOListTableModel().getDataVector();

        EmpresaVO empresa = (EmpresaVO) Container.getContainer().get("empresa");
        ContabilLancamentoCabecalhoVO lancamento = (ContabilLancamentoCabecalhoVO) newPersistentObject;
        lancamento.setEmpresa(empresa);
        
        verificaLancamentos(lancamento.getTipo(), detalhe);

        return ClientUtils.getData(acaoServidor, new Object[]{Constantes.INSERT, lancamento, detalhe});
    }

    @Override
    public void afterInsertData() {
        contabilLancamentoCabecalhoGrid.getGrid1().reloadData();
        JOptionPane.showMessageDialog(contabilLancamentoCabecalhoDetalhe, "Dados salvos com sucesso!", "Informacao do Sistema", JOptionPane.INFORMATION_MESSAGE);
    }

    @Override
    public Response updateRecord(ValueObject oldPersistentObject, ValueObject persistentObject) throws Exception {
        List<ContabilLancamentoDetalheVO> detalhe = contabilLancamentoCabecalhoDetalhe.getGridControl1().getVOListTableModel().getDataVector();

        return ClientUtils.getData(acaoServidor, new Object[]{Constantes.UPDATE, oldPersistentObject, persistentObject, detalhe});
    }

    @Override
    public void afterEditData() {
        contabilLancamentoCabecalhoGrid.getGrid1().reloadData();
        JOptionPane.showMessageDialog(contabilLancamentoCabecalhoDetalhe, "Dados alterados com sucesso!", "Informacao do Sistema", JOptionPane.INFORMATION_MESSAGE);
    }

    private void verificaLancamentos(String tipoLancamento, List<ContabilLancamentoDetalheVO> listaDetalhe) throws Exception {
        BigDecimal creditos = BigDecimal.ZERO;
        BigDecimal debitos = BigDecimal.ZERO;
        int quantidadeCreditos = 0;
        int quantidadeDebitos = 0;
        String mensagem = "";

        for (ContabilLancamentoDetalheVO d : listaDetalhe) {
            if (d.getTipo().equals("C")) {
                quantidadeCreditos += 1;
                creditos = creditos.add(d.getValor());
            }
            if (d.getTipo().equals("D")) {
                quantidadeDebitos += 1;
                debitos = debitos.add(d.getValor());
            }
        }

        //Verifica os totais
        if (creditos.compareTo(debitos) != 0) {
            mensagem = "Total de créditos difere do total de débitos.";
        }

        //Verifica os tipos de lançamento
        //UDVC - Um Débito para Vários Créditos
        if (tipoLancamento.equals("UDVC")) {
            if (quantidadeDebitos > 1) {
                mensagem += "\nUDVC - Mais do que um débito informado.";
            }
            if (quantidadeDebitos < 1) {
                mensagem += "\nUDVC -  - Nenhum débito informado.";
            }
            if (quantidadeCreditos < 1) {
                mensagem += "\nUDVC - Nenhum crédito informado.";
            }
            if (quantidadeCreditos == 1) {
                mensagem += "\nUDVC - Apenas um crédito informado.";
            }
        }

        // UCVD - Um Crédito para Vários Débitos
        if (tipoLancamento.equals("UCVD")) {
            if (quantidadeCreditos > 1) {
                mensagem += "\nUCVD - Mais do que um crédito informado.";
            }
            if (quantidadeCreditos < 1) {
                mensagem += "\nUCVD - Nenhum crédito informado.";
            }
            if (quantidadeDebitos < 1) {
                mensagem += "\nUCVD - Nenhum débito informado.";
            }
            if (quantidadeDebitos == 1) {
                mensagem += "\nUCVD - Apenas um débito informado.";
            }
        }

        // UDUC - Um Débito para Um Crédito
        if (tipoLancamento.equals("UDUC")) {
            if (quantidadeCreditos > 1) {
                mensagem += "\nUDUC - Mais do que um crédito informado.";
            }
            if (quantidadeDebitos > 1) {
                mensagem += "\nUDUC - Mais do que um crédito informado.";
            }
            if (quantidadeCreditos < 1) {
                mensagem += "\nUDUC - Nenhum crédito informado.";
            }
            if (quantidadeDebitos < 1) {
                mensagem += "\nUDUC - Nenhum débito informado.";
            }
        }

        // VDVC - Vários Débitos para Vários Créditos
        if (tipoLancamento.equals("VDVC")) {
            if (quantidadeCreditos < 1) {
                mensagem += "\nVDVC - Nenhum crédito informado.";
            }
            if (quantidadeDebitos < 1) {
                mensagem += "\nVDVC - Nenhum débito informado.";
            }
            if (quantidadeCreditos == 1) {
                mensagem += "\nVDVC - Apenas um crédito informado.";
            }
            if (quantidadeDebitos == 1) {
                mensagem += "\nVDVC - Apenas um débito informado.";
            }
        }

        if (!mensagem.isEmpty()) {
            throw new Exception("Ocorreram erros na validação dos dados informados.\n" + mensagem);
        }
    }

}
