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
import com.t2tierp.cte.java.CteCargaVO;
import com.t2tierp.cte.java.CteDestinatarioVO;
import com.t2tierp.cte.java.CteInformacaoNfOutrosVO;
import com.t2tierp.cte.java.CteRemetenteVO;
import com.t2tierp.nfe.java.NfeCabecalhoVO;
import com.t2tierp.nfe.java.NfeDetalheVO;
import com.t2tierp.padrao.cliente.Container;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.openswing.swing.client.GridControl;
import org.openswing.swing.message.receive.java.Response;
import org.openswing.swing.message.receive.java.VOListResponse;
import org.openswing.swing.table.client.GridController;
import org.openswing.swing.table.java.GridDataLocator;
import org.openswing.swing.util.client.ClientUtils;
import org.openswing.swing.util.java.Consts;

public class CteInformacaoNfOutrosGridController extends GridController implements GridDataLocator {

    private List<CteInformacaoNfOutrosVO> listaCteInformacaoNfOutros;
    private CteCabecalhoDetalhe telaDetalhe;

    public CteInformacaoNfOutrosGridController(CteCabecalhoDetalhe telaDetalhe) {
        this.telaDetalhe = telaDetalhe;
        listaCteInformacaoNfOutros = new ArrayList<>();
    }

    public Response loadData(int action, int startIndex, Map filteredColumns, ArrayList currentSortedColumns, ArrayList currentSortedVersusColumns, Class valueObjectType, Map otherGridParams) {
        return new VOListResponse(listaCteInformacaoNfOutros, false, listaCteInformacaoNfOutros.size());
    }

    @Override
    public boolean beforeInsertGrid(GridControl grid) {
        edicaoForm();
        return true;
    }

    @Override
    public Response insertRecords(int[] rowNumbers, ArrayList newValueObjects) throws Exception {
        for (Object o : newValueObjects) {
            CteInformacaoNfOutrosVO cteInformacaoNfOutros = (CteInformacaoNfOutrosVO) o;
            //cteInformacaoNfOutros.setChaveAcessoNfe(cteInformacaoNfOutros.getNfeCabecalho().getChaveAcesso());
            cteInformacaoNfOutros.setChaveAcessoNfe(cteInformacaoNfOutros.getChaveAcessoNfe() + cteInformacaoNfOutros.getNfeCabecalho().getDigitoChaveAcesso());
            cteInformacaoNfOutros.setCodigoModelo(cteInformacaoNfOutros.getNfeCabecalho().getCodigoModelo());
            cteInformacaoNfOutros.setSerie(cteInformacaoNfOutros.getNfeCabecalho().getSerie());
            cteInformacaoNfOutros.setBaseCalculoIcms(cteInformacaoNfOutros.getNfeCabecalho().getBaseCalculoIcms());
            //cteInformacaoNfOutros.setCfopPredominante(cteInformacaoNfOutros.getNfeCabecalho().getListaNfeDetalhe().get(0).getCfop());
            cteInformacaoNfOutros.setDataEmissao(cteInformacaoNfOutros.getNfeCabecalho().getDataHoraEmissao());
            cteInformacaoNfOutros.setValorIcms(cteInformacaoNfOutros.getNfeCabecalho().getValorIcms());
            cteInformacaoNfOutros.setValorTotal(cteInformacaoNfOutros.getNfeCabecalho().getValorTotal());
            cteInformacaoNfOutros.setValorTotalProdutos(cteInformacaoNfOutros.getNfeCabecalho().getValorTotalProdutos());

            defineDadosCte(cteInformacaoNfOutros.getNfeCabecalho());
        }
        listaCteInformacaoNfOutros.addAll(newValueObjects);
        return new VOListResponse(newValueObjects, false, newValueObjects.size());
    }

    @Override
    public boolean beforeEditGrid(GridControl grid) {
        edicaoForm();
        return true;
    }

    @Override
    public Response updateRecords(int[] rowNumbers, ArrayList oldPersistentObjects, ArrayList persistentObjects) throws Exception {
        listaCteInformacaoNfOutros = telaDetalhe.getGridControlDocumentos().getVOListTableModel().getDataVector();
        return new VOListResponse(persistentObjects, false, persistentObjects.size());
    }

    @Override
    public boolean beforeDeleteGrid(GridControl grid) {
        edicaoForm();
        return true;
    }

    @Override
    public Response deleteRecords(ArrayList persistentObjects) throws Exception {
        listaCteInformacaoNfOutros.removeAll(persistentObjects);
        return new VOListResponse(persistentObjects, false, persistentObjects.size());
    }

    private void edicaoForm() {
        if (telaDetalhe.getForm1().getMode() == Consts.READONLY) {
            telaDetalhe.getForm1().setMode(Consts.EDIT);
        }
    }

    public List<CteInformacaoNfOutrosVO> getListaCteDocumentos() {
        return listaCteInformacaoNfOutros;
    }

    public void setListaCteDocumentos(List<CteInformacaoNfOutrosVO> listaCteDocumentos) {
        if (listaCteDocumentos != null) {
            this.listaCteInformacaoNfOutros = listaCteDocumentos;
        }
    }

    private void defineDadosCte(NfeCabecalhoVO nfeCabecalho) throws Exception {
        CteRemetenteVO cteRemetente = new CteRemetenteVO();

        EmpresaEnderecoVO enderecoPrincipalEmpresa = (EmpresaEnderecoVO) Container.getContainer().get("enderecoPrincipalEmpresa");
        cteRemetente.setBairro(enderecoPrincipalEmpresa.getBairro());
        cteRemetente.setCep(enderecoPrincipalEmpresa.getCep());
        cteRemetente.setCodigoMunicipio(enderecoPrincipalEmpresa.getMunicipioIbge());
        cteRemetente.setCodigoPais(55);
        cteRemetente.setComplemento(enderecoPrincipalEmpresa.getComplemento());
        cteRemetente.setLogradouro(enderecoPrincipalEmpresa.getLogradouro());
        cteRemetente.setNomeMunicipio(enderecoPrincipalEmpresa.getCidade());
        cteRemetente.setNomePais("BRASIL");
        cteRemetente.setNumero(enderecoPrincipalEmpresa.getNumero());
        cteRemetente.setTelefone(enderecoPrincipalEmpresa.getFone());
        cteRemetente.setUf(enderecoPrincipalEmpresa.getUf());
        cteRemetente.setCnpj(nfeCabecalho.getEmpresa().getCnpj());
        cteRemetente.setFantasia(nfeCabecalho.getEmpresa().getNomeFantasia());
        cteRemetente.setEmail(nfeCabecalho.getEmpresa().getEmail());
        cteRemetente.setNome(nfeCabecalho.getEmpresa().getRazaoSocial());

        telaDetalhe.getRemetenteController().setCteRemetente(cteRemetente);
        telaDetalhe.getFormRemetente().reload();

        CteDestinatarioVO cteDestinatario = new CteDestinatarioVO();
        cteDestinatario.setBairro(nfeCabecalho.getDestinatario().getBairro());
        cteDestinatario.setCep(nfeCabecalho.getDestinatario().getCep());
        if (nfeCabecalho.getDestinatario().getCpfCnpj().length() == 11) {
            cteDestinatario.setCpf(nfeCabecalho.getDestinatario().getCpfCnpj());
        } else {
            cteDestinatario.setCnpj(nfeCabecalho.getDestinatario().getCpfCnpj());
        }
        cteDestinatario.setCodigoMunicipio(nfeCabecalho.getDestinatario().getCodigoMunicipio());
        cteDestinatario.setCodigoPais(nfeCabecalho.getDestinatario().getCodigoPais());
        cteDestinatario.setComplemento(nfeCabecalho.getDestinatario().getComplemento());
        cteDestinatario.setEmail(nfeCabecalho.getDestinatario().getEmail());
        cteDestinatario.setFantasia(nfeCabecalho.getDestinatario().getNome());
        cteDestinatario.setIe(nfeCabecalho.getDestinatario().getInscricaoEstadual());
        cteDestinatario.setLogradouro(nfeCabecalho.getDestinatario().getLogradouro());
        cteDestinatario.setNome(nfeCabecalho.getDestinatario().getNome());
        cteDestinatario.setNomeMunicipio(nfeCabecalho.getDestinatario().getNomeMunicipio());
        cteDestinatario.setNomePais(nfeCabecalho.getDestinatario().getNomePais());
        cteDestinatario.setNumero(nfeCabecalho.getDestinatario().getNumero());
        cteDestinatario.setTelefone(nfeCabecalho.getDestinatario().getTelefone());
        cteDestinatario.setUf(nfeCabecalho.getDestinatario().getUf());

        telaDetalhe.getDestinatarioController().setCteDestinatario(cteDestinatario);
        telaDetalhe.getFormDestinatario().reload();

        Response res = ClientUtils.getData("cteCabecalhoDetalheAction", new Object[]{100, nfeCabecalho.getId()});
        if (res.isError()) {
            throw new Exception(res.getErrorMessage());
        }
        List<NfeDetalheVO> listaNfeDetalhe = ((VOListResponse) res).getRows();
        BigDecimal valorTotalCarga = BigDecimal.ZERO;
        List<CteCargaVO> listaCteCarga = new ArrayList<>();
        for (NfeDetalheVO d : listaNfeDetalhe) {
            CteCargaVO cteCarga = new CteCargaVO();
            cteCarga.setCodigoUnidadeMedida("00");
            cteCarga.setQuantidade(d.getQuantidadeComercial());
            cteCarga.setTipoMedida("PESO DECLARADO");
            
            listaCteCarga.add(cteCarga);
            
            telaDetalhe.getCabecalhoController().defineProdutoPredominante(d.getNomeProduto());
            
            valorTotalCarga = valorTotalCarga.add(d.getValorTotal());
        }
        telaDetalhe.getCargaGridController().setListaCteCarga(listaCteCarga);
        telaDetalhe.getGridControlCarga().reloadData();
        
        telaDetalhe.getCabecalhoController().defineValorTotalCarga(valorTotalCarga);
        
    }
}
