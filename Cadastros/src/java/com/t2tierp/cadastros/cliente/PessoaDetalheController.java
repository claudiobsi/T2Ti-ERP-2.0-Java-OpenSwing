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
package com.t2tierp.cadastros.cliente;

import com.t2tierp.cadastros.java.PessoaContatoVO;
import com.t2tierp.cadastros.java.EmpresaVO;
import com.t2tierp.cadastros.java.PessoaEnderecoVO;
import com.t2tierp.cadastros.java.PessoaFisicaVO;
import com.t2tierp.cadastros.java.PessoaJuridicaVO;
import com.t2tierp.cadastros.java.PessoaTelefoneVO;
import com.t2tierp.cadastros.java.PessoaVO;
import com.t2tierp.padrao.cliente.Container;
import com.t2tierp.padrao.java.Biblioteca;
import com.t2tierp.padrao.java.Constantes;
import java.beans.PropertyVetoException;
import java.util.List;
import javax.swing.JOptionPane;
import org.openswing.swing.form.client.Form;
import org.openswing.swing.form.client.FormController;
import org.openswing.swing.mdi.client.MDIFrame;
import org.openswing.swing.message.receive.java.ErrorResponse;
import org.openswing.swing.message.receive.java.Response;
import org.openswing.swing.message.receive.java.ValueObject;
import org.openswing.swing.util.client.ClientUtils;
import org.openswing.swing.util.java.Consts;

public class PessoaDetalheController extends FormController {

    private PessoaDetalhe pessoaDetalhe = null;
    private String pk = null;
    private PessoaGrid pessoaGrid = null;
    private String acaoServidor;

    public PessoaDetalheController(PessoaGrid pessoaGrid, String pk) {
        this.pessoaGrid = pessoaGrid;
        this.pk = pk;
        this.acaoServidor = "pessoaDetalheAction";
        pessoaDetalhe = new PessoaDetalhe(this);
        pessoaDetalhe.setParentFrame(this.pessoaGrid);
        this.pessoaGrid.pushFrame(pessoaDetalhe);
        MDIFrame.add(pessoaDetalhe);
        try {
            pessoaDetalhe.setMaximum(true);
        } catch (PropertyVetoException ex) {
        }

        if (pk != null) {
            pessoaDetalhe.getForm1().setMode(Consts.READONLY);
            pessoaDetalhe.getForm1().reload();
        } else {
            pessoaDetalhe.getForm1().setMode(Consts.INSERT);
            pessoaDetalhe.getFormPessoaFisica().setMode(Consts.INSERT);
            pessoaDetalhe.getFormPessoaJuridica().setMode(Consts.INSERT);
            pessoaDetalhe.getGridControlContatos().reloadData();
            pessoaDetalhe.getGridControlEndereco().reloadData();
            pessoaDetalhe.getGridControlTelefone().reloadData();
        }
    }

    @Override
    public Response loadData(Class valueObjectClass) {
        return ClientUtils.getData(acaoServidor, new Object[]{Constantes.LOAD, pk});
    }

    @Override
    public void loadDataCompleted(boolean error) {
        PessoaVO pessoa = (PessoaVO) pessoaDetalhe.getForm1().getVOModel().getValueObject();
        if (pessoa.getTipo().equals("F")) {
            pessoaDetalhe.getFormPessoaFisica().setVisible(true);
            pessoaDetalhe.getFormPessoaJuridica().setVisible(false);
        } else {
            pessoaDetalhe.getFormPessoaFisica().setVisible(false);
            pessoaDetalhe.getFormPessoaJuridica().setVisible(true);
        }

        pessoaDetalhe.getPessoaFisicaController().setPessoa(pessoa);
        pessoaDetalhe.getFormPessoaFisica().reload();

        pessoaDetalhe.getPessoaJuridicaController().setPessoa(pessoa);
        pessoaDetalhe.getFormPessoaJuridica().reload();

        pessoaDetalhe.getContatoController().setPk(pessoa.getId().toString());
        pessoaDetalhe.getGridControlContatos().reloadData();

        pessoaDetalhe.getEnderecoController().setPessoa(pessoa);
        pessoaDetalhe.getGridControlEndereco().reloadData();
        
        pessoaDetalhe.getTelefoneController().setPessoa(pessoa);
        pessoaDetalhe.getGridControlTelefone().reloadData();
    }

    @Override
    public Response insertRecord(ValueObject newPersistentObject) throws Exception {
        PessoaVO pessoa = (PessoaVO) newPersistentObject;
        EmpresaVO empresa = (EmpresaVO) Container.getContainer().get("empresa");

        if (pessoa.getTipo().equals("F")) {
            if (!pessoaDetalhe.getFormPessoaFisica().save()) {
                return new ErrorResponse("Erro ao salvar os dados da Pessoa Fisica!");
            }
            PessoaFisicaVO pessoaFisica = (PessoaFisicaVO) pessoaDetalhe.getFormPessoaFisica().getVOModel().getValueObject();
            if (!Biblioteca.cpfValido(pessoaFisica.getCpf())) {
                pessoaDetalhe.getFormPessoaFisica().setMode(Consts.EDIT);
                return new ErrorResponse("CPF Inválido");
            }
        } else {
            if (!pessoaDetalhe.getFormPessoaJuridica().save()) {
                return new ErrorResponse("Erro ao salvar os dados da Pessoa Juridica!");
            }
            PessoaJuridicaVO pessoaJuridica = (PessoaJuridicaVO) pessoaDetalhe.getFormPessoaJuridica().getVOModel().getValueObject();
            if (!Biblioteca.cnpjValido(pessoaJuridica.getCnpj())) {
                pessoaDetalhe.getFormPessoaJuridica().setMode(Consts.EDIT);
                return new ErrorResponse("CNPJ Inválido!");
            }
        }

        PessoaFisicaVO pessoaFisica = (PessoaFisicaVO) pessoaDetalhe.getFormPessoaFisica().getVOModel().getValueObject();

        PessoaJuridicaVO pessoaJuridica = (PessoaJuridicaVO) pessoaDetalhe.getFormPessoaJuridica().getVOModel().getValueObject();

        List<PessoaContatoVO> listaContato = pessoaDetalhe.getGridControlContatos().getVOListTableModel().getDataVector();

        List<PessoaEnderecoVO> listaEndereco = pessoaDetalhe.getGridControlEndereco().getVOListTableModel().getDataVector();
        
        List<PessoaTelefoneVO> listaTelefone = pessoaDetalhe.getGridControlTelefone().getVOListTableModel().getDataVector();

        Response res = ClientUtils.getData(acaoServidor, new Object[]{Constantes.INSERT, newPersistentObject, empresa, pessoaFisica, pessoaJuridica, listaContato, listaEndereco, listaTelefone});

        if (res.isError()) {
            if (pessoa.getTipo().equals("F")) {
                pessoaDetalhe.getFormPessoaFisica().setMode(Consts.EDIT);
            } else {
                pessoaDetalhe.getFormPessoaJuridica().setMode(Consts.EDIT);
            }
        }

        return res;
    }

    @Override
    public void afterInsertData() {
        pessoaGrid.getGrid1().reloadData();
        JOptionPane.showMessageDialog(pessoaDetalhe, "Dados salvos com sucesso!", "Informacao do Sistema", JOptionPane.INFORMATION_MESSAGE);
    }

    @Override
    public boolean beforeEditData(Form form) {
        PessoaVO pessoa = (PessoaVO) pessoaDetalhe.getForm1().getVOModel().getValueObject();
        if (pessoa.getTipo().equals("F")) {
            pessoaDetalhe.getFormPessoaFisica().setMode(Consts.EDIT);
        } else {
            pessoaDetalhe.getFormPessoaJuridica().setMode(Consts.EDIT);
        }
        return super.beforeEditData(form);
    }

    @Override
    public Response updateRecord(ValueObject oldPersistentObject, ValueObject persistentObject) throws Exception {
        PessoaVO pessoa = (PessoaVO) persistentObject;

        if (pessoa.getTipo().equals("F")) {
            if (!pessoaDetalhe.getFormPessoaFisica().save()) {
                return new ErrorResponse("Erro ao salvar os dados da Pessoa Fisica!");
            }
            PessoaFisicaVO pessoaFisica = (PessoaFisicaVO) pessoaDetalhe.getFormPessoaFisica().getVOModel().getValueObject();
            if (!Biblioteca.cpfValido(pessoaFisica.getCpf())) {
                pessoaDetalhe.getFormPessoaFisica().setMode(Consts.EDIT);
                return new ErrorResponse("CPF Inválido");
            }
        } else {
            if (!pessoaDetalhe.getFormPessoaJuridica().save()) {
                return new ErrorResponse("Erro ao salvar os dados da Pessoa Juridica!");
            }
            PessoaJuridicaVO pessoaJuridica = (PessoaJuridicaVO) pessoaDetalhe.getFormPessoaJuridica().getVOModel().getValueObject();
            if (!Biblioteca.cnpjValido(pessoaJuridica.getCnpj())) {
                pessoaDetalhe.getFormPessoaJuridica().setMode(Consts.EDIT);
                return new ErrorResponse("CNPJ Inválido!");
            }
        }

        PessoaFisicaVO pessoaFisica = (PessoaFisicaVO) pessoaDetalhe.getFormPessoaFisica().getVOModel().getValueObject();

        PessoaJuridicaVO pessoaJuridica = (PessoaJuridicaVO) pessoaDetalhe.getFormPessoaJuridica().getVOModel().getValueObject();

        List<PessoaContatoVO> listaContato = pessoaDetalhe.getGridControlContatos().getVOListTableModel().getDataVector();

        List<PessoaEnderecoVO> listaEndereco = pessoaDetalhe.getGridControlEndereco().getVOListTableModel().getDataVector();
        
        List<PessoaTelefoneVO> listaTelefone = pessoaDetalhe.getGridControlTelefone().getVOListTableModel().getDataVector();

        Response res = ClientUtils.getData(acaoServidor, new Object[]{Constantes.UPDATE, oldPersistentObject, persistentObject, pessoaFisica, pessoaJuridica, listaContato, listaEndereco, listaTelefone});

        if (res.isError()) {
            if (pessoa.getTipo().equals("F")) {
                pessoaDetalhe.getFormPessoaFisica().setMode(Consts.EDIT);
            } else {
                pessoaDetalhe.getFormPessoaJuridica().setMode(Consts.EDIT);
            }
        }

        return res;
    }

    @Override
    public void afterEditData() {
        pessoaGrid.getGrid1().reloadData();
        JOptionPane.showMessageDialog(pessoaDetalhe, "Dados alterados com sucesso!", "Informacao do Sistema", JOptionPane.INFORMATION_MESSAGE);
    }

    @Override
    public boolean validateControl(String attributeName, Object oldValue, Object newValue) {
        if (attributeName.equals("tipo")) {
            if (newValue.toString().equals("F")) {
                pessoaDetalhe.getFormPessoaFisica().setVisible(true);
                pessoaDetalhe.getFormPessoaJuridica().setVisible(false);
            } else {
                pessoaDetalhe.getFormPessoaFisica().setVisible(false);
                pessoaDetalhe.getFormPessoaJuridica().setVisible(true);
            }
        }

        return super.validateControl(attributeName, oldValue, newValue);
    }
}
