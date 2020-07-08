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
package com.t2tierp.cte.cliente;

import com.t2tierp.cadastros.java.EmpresaEnderecoVO;
import com.t2tierp.cadastros.java.EmpresaVO;
import com.t2tierp.cadastros.java.UsuarioVO;
import com.t2tierp.cte.java.CteCabecalhoVO;
import com.t2tierp.cte.java.CteEmitenteVO;
import com.t2tierp.cte.java.CteRodoviarioVO;
import com.t2tierp.nfe.java.RespostaSefaz;
import com.t2tierp.padrao.cliente.Container;
import com.t2tierp.padrao.java.Constantes;
import java.math.BigDecimal;
import javax.swing.JOptionPane;
import org.openswing.swing.form.client.FormController;
import org.openswing.swing.mdi.client.MDIFrame;
import org.openswing.swing.message.receive.java.Response;
import org.openswing.swing.message.receive.java.VOResponse;
import org.openswing.swing.message.receive.java.ValueObject;
import org.openswing.swing.util.client.ClientUtils;
import org.openswing.swing.util.java.Consts;

public class CteCabecalhoDetalheController extends FormController {

    private CteCabecalhoDetalhe cteCabecalhoDetalhe = null;
    private String pk = null;
    private CteCabecalhoGrid cteCabecalhoGrid = null;
    private String acaoServidor;

    public CteCabecalhoDetalheController(CteCabecalhoGrid cteCabecalhoGrid, String pk) {
        this.cteCabecalhoGrid = cteCabecalhoGrid;
        this.pk = pk;
        this.acaoServidor = "cteCabecalhoDetalheAction";
        cteCabecalhoDetalhe = new CteCabecalhoDetalhe(this);
        cteCabecalhoDetalhe.setParentFrame(this.cteCabecalhoGrid);
        this.cteCabecalhoGrid.pushFrame(cteCabecalhoDetalhe);
        MDIFrame.add(cteCabecalhoDetalhe, true);

        if (pk != null) {
            cteCabecalhoDetalhe.getForm1().setMode(Consts.READONLY);
            cteCabecalhoDetalhe.getForm1().reload();
        } else {
            cteCabecalhoDetalhe.getForm1().setMode(Consts.INSERT);
            cteCabecalhoDetalhe.getGridControlDocumentos().reloadData();
            cteCabecalhoDetalhe.getGridControlCarga().reloadData();
            cteCabecalhoDetalhe.getGridControlOcc().reloadData();
        }
    }

    @Override
    public void createPersistentObject(ValueObject persistentObject) throws Exception {
        CteCabecalhoVO cteCabecalho = (CteCabecalhoVO) persistentObject;
        cteCabecalho.setNaturezaOperacao("PRESTAÇÃO DE SERVIÇO");
        cteCabecalho.setModelo("57");
        cteCabecalho.setCfop(5353);
        
        UsuarioVO usuario = (UsuarioVO) Container.getContainer().get("usuario");
        cteCabecalho.setFuncionarioEmissor(usuario.getColaborador().getPessoa().getNome());
        
        EmpresaVO empresa = (EmpresaVO) Container.getContainer().get("empresa");
        cteCabecalho.setEmpresa(empresa);

        EmpresaEnderecoVO enderecoPrincipalEmpresa = (EmpresaEnderecoVO) Container.getContainer().get("enderecoPrincipalEmpresa");
        CteEmitenteVO cteEmitente = new CteEmitenteVO();
        cteEmitente.setBairro(enderecoPrincipalEmpresa.getBairro());
        cteEmitente.setCep(enderecoPrincipalEmpresa.getCep());
        cteEmitente.setCodigoMunicipio(enderecoPrincipalEmpresa.getMunicipioIbge());
        cteEmitente.setComplemento(enderecoPrincipalEmpresa.getComplemento());
        cteEmitente.setLogradouro(enderecoPrincipalEmpresa.getLogradouro());
        cteEmitente.setNomeMunicipio(enderecoPrincipalEmpresa.getCidade());
        cteEmitente.setNumero(enderecoPrincipalEmpresa.getNumero());
        cteEmitente.setTelefone(enderecoPrincipalEmpresa.getFone());
        cteEmitente.setUf(enderecoPrincipalEmpresa.getUf());
        cteEmitente.setCnpj(empresa.getCnpj());
        cteEmitente.setIe(empresa.getInscricaoEstadual());
        cteEmitente.setFantasia(empresa.getNomeFantasia());
        cteEmitente.setNome(empresa.getRazaoSocial());
        
        cteCabecalho.setCteEmitente(cteEmitente);
        cteCabecalho.setCst("00");
        cteCabecalho.setSimplesNacionalIndicador(0);
        cteCabecalho.setValorBcIcmsOutraUf(BigDecimal.ZERO);
        cteCabecalho.setValorBcIcmsStRetido(BigDecimal.ZERO);
        cteCabecalho.setValorCreditoPresumidoIcms(BigDecimal.ZERO);
        cteCabecalho.setValorIcms(BigDecimal.ZERO);
        cteCabecalho.setValorIcmsOutraUf(BigDecimal.ZERO);
        cteCabecalho.setValorIcmsStRetido(BigDecimal.ZERO);
        cteCabecalho.setValorReceber(BigDecimal.ZERO);
        cteCabecalho.setValorTotalCarga(BigDecimal.ZERO);
        cteCabecalho.setValorTotalServico(BigDecimal.ZERO);
        cteCabecalho.setAliquotaIcms(BigDecimal.ZERO);
        cteCabecalho.setAliquotaIcmsOutraUf(BigDecimal.ZERO);
        cteCabecalho.setAliquotaIcmsStRetido(BigDecimal.ZERO);
        cteCabecalho.setBaseCalculoIcms(BigDecimal.ZERO);
        cteCabecalho.setPercentualBcIcmsOutraUf(BigDecimal.ZERO);
        cteCabecalho.setPercentualReducaoBcIcms(BigDecimal.ZERO);
    }
    
    @Override
    public Response loadData(Class valueObjectClass) {
        return ClientUtils.getData(acaoServidor, new Object[]{Constantes.LOAD, pk});
    }

    @Override
    public void loadDataCompleted(boolean error) {
        CteCabecalhoVO cteCabecalho = (CteCabecalhoVO) cteCabecalhoDetalhe.getForm1().getVOModel().getValueObject();
        
        cteCabecalhoDetalhe.getCargaGridController().setListaCteCarga(cteCabecalho.getListaCteCarga());
        cteCabecalhoDetalhe.getGridControlCarga().reloadData();
                
        cteCabecalhoDetalhe.getInformacaoNfOutrosGridController().setListaCteDocumentos(cteCabecalho.getListaCteInformacaoNfOutros());
        cteCabecalhoDetalhe.getGridControlDocumentos().reloadData();
        
        cteCabecalhoDetalhe.getRodoviarioController().setCteRodoviario(cteCabecalho.getCteRodoviario());
        cteCabecalhoDetalhe.getFormRodoviario().reload();

        cteCabecalhoDetalhe.getRodoviarioOccGridController().setListaCteRodoviarioOcc(cteCabecalho.getCteRodoviario().getListaCteRodoviarioOcc());
        cteCabecalhoDetalhe.getGridControlOcc().reloadData();
        
        cteCabecalhoDetalhe.getRemetenteController().setCteRemetente(cteCabecalho.getCteRemetente());
        cteCabecalhoDetalhe.getFormRemetente().reload();
        
        cteCabecalhoDetalhe.getDestinatarioController().setCteDestinatario(cteCabecalho.getCteDestinatario());
        cteCabecalhoDetalhe.getFormDestinatario().reload();
    }

    @Override
    public Response insertRecord(ValueObject newPersistentObject) throws Exception {
        CteCabecalhoVO cteCabecalho = (CteCabecalhoVO) newPersistentObject;
        cteCabecalho.setListaCteCarga(cteCabecalhoDetalhe.getCargaGridController().getListaCteCarga());
        cteCabecalho.setListaCteInformacaoNfOutros(cteCabecalhoDetalhe.getInformacaoNfOutrosGridController().getListaCteDocumentos());
        cteCabecalho.setCteDestinatario(cteCabecalhoDetalhe.getDestinatarioController().getCteDestinatario());
        cteCabecalho.setCteRemetente(cteCabecalhoDetalhe.getRemetenteController().getCteRemetente());
        cteCabecalho.setCteRodoviario((CteRodoviarioVO) cteCabecalhoDetalhe.getFormRodoviario().getVOModel().getValueObject());
        
        return ClientUtils.getData(acaoServidor, new Object[]{Constantes.INSERT, cteCabecalho});
    }

    @Override
    public void afterInsertData() {
        cteCabecalhoGrid.getGrid1().reloadData();
        JOptionPane.showMessageDialog(cteCabecalhoDetalhe, "Dados salvos com sucesso!", "Informacao do Sistema", JOptionPane.INFORMATION_MESSAGE);
    }

    @Override
    public Response updateRecord(ValueObject oldPersistentObject, ValueObject persistentObject) throws Exception {
        CteCabecalhoVO cteCabecalho = (CteCabecalhoVO) persistentObject;
        cteCabecalho.setListaCteCarga(cteCabecalhoDetalhe.getCargaGridController().getListaCteCarga());
        cteCabecalho.setListaCteInformacaoNfOutros(cteCabecalhoDetalhe.getInformacaoNfOutrosGridController().getListaCteDocumentos());
        cteCabecalho.setCteDestinatario(cteCabecalhoDetalhe.getDestinatarioController().getCteDestinatario());
        cteCabecalho.setCteRemetente(cteCabecalhoDetalhe.getRemetenteController().getCteRemetente());
        cteCabecalho.setCteRodoviario((CteRodoviarioVO) cteCabecalhoDetalhe.getFormRodoviario().getVOModel().getValueObject());

        return ClientUtils.getData(acaoServidor, new Object[]{Constantes.UPDATE, oldPersistentObject, cteCabecalho});
    }

    @Override
    public void afterEditData() {
        cteCabecalhoGrid.getGrid1().reloadData();
        JOptionPane.showMessageDialog(cteCabecalhoDetalhe, "Dados alterados com sucesso!", "Informacao do Sistema", JOptionPane.INFORMATION_MESSAGE);
    }

    public void defineProdutoPredominante(String nomeProduto) {
        CteCabecalhoVO cteCabecalho = (CteCabecalhoVO) cteCabecalhoDetalhe.getForm1().getVOModel().getValueObject();
        cteCabecalho.setProdutoPredominante(nomeProduto);
        cteCabecalho.setCargaOutrasCaracteristicas("NENHUMA");
    }

    public void defineValorTotalCarga(BigDecimal valorTotalCarga) {
        CteCabecalhoVO cteCabecalho = (CteCabecalhoVO) cteCabecalhoDetalhe.getForm1().getVOModel().getValueObject();
        cteCabecalho.setValorTotalCarga(valorTotalCarga);
        cteCabecalho.setValorTotalServico(valorTotalCarga);
    }
    
    public void enviarCte() {
        try {
            if (cteCabecalhoDetalhe.getForm1().getMode() != Consts.READONLY) {
                JOptionPane.showMessageDialog(cteCabecalhoDetalhe, "Necessário salvar CT-e", "Informação do Sistema", JOptionPane.INFORMATION_MESSAGE);
            } else {
                CteCabecalhoVO cteCabecalho = (CteCabecalhoVO) cteCabecalhoDetalhe.getForm1().getVOModel().getValueObject();
                Response res = ClientUtils.getData(acaoServidor, new Object[]{99, cteCabecalho.getId()});

                if (res.isError()) {
                    throw new Exception(res.getErrorMessage());
                }

                RespostaSefaz respostaSefaz = (RespostaSefaz) ((VOResponse) res).getVo();

                JOptionPane.showMessageDialog(cteCabecalhoDetalhe, respostaSefaz.getResposta(), "Informação do Sistema", JOptionPane.INFORMATION_MESSAGE);
                cteCabecalhoDetalhe.getForm1().reload();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(cteCabecalhoDetalhe, ex.getMessage(), "Erro do Sistema", JOptionPane.ERROR_MESSAGE);
        }
    }
    
}
