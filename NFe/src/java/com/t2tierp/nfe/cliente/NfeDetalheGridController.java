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
package com.t2tierp.nfe.cliente;

import com.t2tierp.cadastros.java.EmpresaVO;
import com.t2tierp.nfe.java.NfeCabecalhoVO;
import com.t2tierp.nfe.java.NfeCalculoVO;
import com.t2tierp.nfe.java.NfeDeclaracaoImportacaoVO;
import com.t2tierp.nfe.java.NfeDestinatarioVO;
import com.t2tierp.nfe.java.NfeDetEspecificoArmamentoVO;
import com.t2tierp.nfe.java.NfeDetEspecificoMedicamentoVO;
import com.t2tierp.nfe.java.NfeDetalheImpostoCofinsVO;
import com.t2tierp.nfe.java.NfeDetalheImpostoIcmsVO;
import com.t2tierp.nfe.java.NfeDetalheImpostoIiVO;
import com.t2tierp.nfe.java.NfeDetalheImpostoIpiVO;
import com.t2tierp.nfe.java.NfeDetalheImpostoIssqnVO;
import com.t2tierp.nfe.java.NfeDetalheImpostoPisVO;
import com.t2tierp.nfe.java.NfeDetalheVO;
import com.t2tierp.padrao.cliente.Container;
import com.t2tierp.tributacao.java.TributIssVO;
import com.t2tierp.tributacao.java.TributOperacaoFiscalVO;
import com.t2tierp.tributacao.java.ViewTributacaoCofinsVO;
import com.t2tierp.tributacao.java.ViewTributacaoIcmsCustomVO;
import com.t2tierp.tributacao.java.ViewTributacaoIcmsVO;
import com.t2tierp.tributacao.java.ViewTributacaoIpiVO;
import com.t2tierp.tributacao.java.ViewTributacaoPisVO;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.swing.JOptionPane;
import org.openswing.swing.client.GridControl;
import org.openswing.swing.mdi.client.MDIFrame;
import org.openswing.swing.message.receive.java.Response;
import org.openswing.swing.message.receive.java.VOListResponse;
import org.openswing.swing.message.receive.java.VOResponse;
import org.openswing.swing.message.receive.java.ValueObject;
import org.openswing.swing.table.client.GridController;
import org.openswing.swing.table.java.GridDataLocator;
import org.openswing.swing.util.client.ClientUtils;
import org.openswing.swing.util.java.Consts;

public class NfeDetalheGridController extends GridController implements GridDataLocator {

    private NfeDetalhe nfeDetalhe;
    private NfeCabecalhoVO nfeCabecalho;

    public NfeDetalheGridController(NfeDetalhe nfeDetalhe) {
        this.nfeDetalhe = nfeDetalhe;
    }

    public Response loadData(int action, int startIndex, Map filteredColumns, ArrayList currentSortedColumns, ArrayList currentSortedVersusColumns, Class valueObjectType, Map otherGridParams) {
        if (nfeCabecalho.getListaNfeDetalhe() == null) {
            nfeCabecalho.setListaNfeDetalhe(new ArrayList<NfeDetalheVO>());
        }
        return new VOListResponse(nfeCabecalho.getListaNfeDetalhe(), false, nfeCabecalho.getListaNfeDetalhe().size());
    }

    @Override
    public boolean beforeInsertGrid(GridControl grid) {
        return verificaDadosInformados();
    }

    @Override
    public Response insertRecords(int[] rowNumbers, ArrayList newValueObjects) throws Exception {
        realizaCalculosItem(newValueObjects);

        return new VOListResponse(newValueObjects, false, newValueObjects.size());
    }

    @Override
    public void afterInsertGrid(GridControl grid) {
        nfeDetalhe.getDadosNfeController().atualizaTotais(grid.getVOListTableModel().getDataVector());
    }

    @Override
    public boolean beforeEditGrid(GridControl grid) {
        return verificaDadosInformados();
    }

    @Override
    public Response updateRecords(int[] rowNumbers, ArrayList oldPersistentObjects, ArrayList persistentObjects) throws Exception {
        realizaCalculosItem(persistentObjects);

        return new VOListResponse(persistentObjects, false, persistentObjects.size());
    }

    @Override
    public void afterEditGrid(GridControl grid) {
        nfeDetalhe.getDadosNfeController().atualizaTotais(grid.getVOListTableModel().getDataVector());
    }

    @Override
    public boolean beforeDeleteGrid(GridControl grid) {
        if (nfeDetalhe.getFormDadosNfe().getMode() == Consts.READONLY) {
            nfeDetalhe.getFormDadosNfe().setMode(Consts.EDIT);
        }
        return true;
    }

    @Override
    public Response deleteRecords(ArrayList persistentObjects) throws Exception {
        return new VOListResponse(persistentObjects, false, persistentObjects.size());
    }

    @Override
    public void afterDeleteGrid() {
        nfeDetalhe.getDadosNfeController().atualizaTotais(nfeDetalhe.getGridControlProduto().getVOListTableModel().getDataVector());
    }

    public void setNfeCabecalho(NfeCabecalhoVO nfeCabecalho) {
        this.nfeCabecalho = nfeCabecalho;
    }

    @Override
    public void doubleClick(int rowNumber, ValueObject persistentObject) {
        NfeDetalheVO detalhe = (NfeDetalheVO) persistentObject;

        NfeDetalheImpostoGrid impostoGrid = new NfeDetalheImpostoGrid(MDIFrame.getInstance(), true);

        impostoGrid.getIcmsController().setIcms(detalhe.getNfeDetalheImpostoIcms());
        impostoGrid.getFormIcms().reload();

        impostoGrid.getPisController().setPis(detalhe.getNfeDetalheImpostoPis());
        impostoGrid.getFormPis().reload();

        impostoGrid.getCofinsController().setCofins(detalhe.getNfeDetalheImpostoCofins());
        impostoGrid.getFormCofins().reload();

        impostoGrid.getIpiController().setIpi(detalhe.getNfeDetalheImpostoIpi());
        impostoGrid.getFormIpi().reload();

        impostoGrid.getIiController().setIi(detalhe.getNfeDetalheImpostoIi());
        impostoGrid.getFormIi().reload();

        impostoGrid.getIssqnController().setIssqn(detalhe.getNfeDetalheImpostoIssqn());
        impostoGrid.getFormIssqn().reload();

        impostoGrid.getCombustivelController().setCombustivel(detalhe.getCombustivel());
        impostoGrid.getFormCombustivel().reload();

        impostoGrid.getVeiculoController().setVeiculo(detalhe.getVeiculo());
        impostoGrid.getFormVeiculo().reload();

        impostoGrid.getMedicamentoController().setMedicamento(detalhe.getListaMedicamento());
        impostoGrid.getGridControlMedicamento().reloadData();

        impostoGrid.getArmamentoController().setArmamento(detalhe.getListaArmamento());
        impostoGrid.getGridControlArmamento().reloadData();

        impostoGrid.getDeclaracaoController().setDeclaracao(detalhe.getListaDeclaracaoImportacao());
        impostoGrid.getGridControlDeclaracaoImportacao().reloadData();

        impostoGrid.setVisible(true);
    }

    public boolean verificaDadosInformados() {
        NfeCabecalhoVO cabecalho = (NfeCabecalhoVO) nfeDetalhe.getFormDadosNfe().getVOModel().getValueObject();
        NfeDestinatarioVO destinatario = (NfeDestinatarioVO) nfeDetalhe.getFormDestinatario().getVOModel().getValueObject();
        if (cabecalho.getTributOperacaoFiscal().getId() == null) {
            JOptionPane.showMessageDialog(nfeDetalhe, "Antes de incluir produtos selecione a Operação Fiscal.", "Informação do Sistema", JOptionPane.INFORMATION_MESSAGE);
            return false;
        } else if (destinatario.getUf() == null) {
            JOptionPane.showMessageDialog(nfeDetalhe, "Antes de incluir produtos selecione o destinatário.", "Informação do Sistema", JOptionPane.INFORMATION_MESSAGE);
            return false;
        } else {
            if (nfeDetalhe.getFormDadosNfe().getMode() == Consts.READONLY) {
                nfeDetalhe.getFormDadosNfe().setMode(Consts.EDIT);
            }
            return true;
        }
    }

    private List<NfeDetalheVO> realizaCalculosItem(List<NfeDetalheVO> itensNfe) throws Exception {
        for (int i = 0; i < itensNfe.size(); i++) {
            itensNfe.get(i).setCodigoProduto(itensNfe.get(i).getGtin());
            itensNfe.get(i).setGtinUnidadeTributavel(itensNfe.get(i).getGtin());
            itensNfe.get(i).setUnidadeTributavel(itensNfe.get(i).getUnidadeComercial());
            itensNfe.get(i).setValorUnitarioTributavel(itensNfe.get(i).getValorUnitarioComercial());
            itensNfe.get(i).setValorBrutoProduto(itensNfe.get(i).getValorUnitarioComercial().multiply(itensNfe.get(i).getQuantidadeComercial()));
            itensNfe.get(i).setQuantidadeTributavel(itensNfe.get(i).getQuantidadeComercial());
            itensNfe.get(i).setValorSubtotal(itensNfe.get(i).getValorBrutoProduto());
            itensNfe.get(i).setEntraTotal(1);
            if (itensNfe.get(i).getValorFrete() == null) {
                itensNfe.get(i).setValorFrete(BigDecimal.ZERO);
            }
            if (itensNfe.get(i).getValorOutrasDespesas() == null) {
                itensNfe.get(i).setValorOutrasDespesas(BigDecimal.ZERO);
            }
            if (itensNfe.get(i).getValorSeguro() == null) {
                itensNfe.get(i).setValorSeguro(BigDecimal.ZERO);
            }
            if (itensNfe.get(i).getValorDesconto() == null) {
                itensNfe.get(i).setValorDesconto(BigDecimal.ZERO);
            }
            itensNfe.get(i).setValorTotal(itensNfe.get(i).getValorBrutoProduto().subtract(itensNfe.get(i).getValorDesconto()));

            defineTributacao(itensNfe.get(i));
        }
        return itensNfe;
    }

    private void defineTributacao(NfeDetalheVO item) throws Exception {
        NfeCabecalhoVO cabecalho = (NfeCabecalhoVO) nfeDetalhe.getFormDadosNfe().getVOModel().getValueObject();
        TributOperacaoFiscalVO operacaoFiscal = cabecalho.getTributOperacaoFiscal();
        EmpresaVO empresa = (EmpresaVO) Container.getContainer().get("empresa");
        NfeDestinatarioVO destinatario = (NfeDestinatarioVO) nfeDetalhe.getFormDestinatario().getVOModel().getValueObject();
        item.setNfeDetalheImpostoIssqn(new NfeDetalheImpostoIssqnVO());
        item.getNfeDetalheImpostoIssqn().setNfeDetalhe(item);
        item.setNfeDetalheImpostoPis(new NfeDetalheImpostoPisVO());
        item.getNfeDetalheImpostoPis().setNfeDetalhe(item);
        item.setNfeDetalheImpostoCofins(new NfeDetalheImpostoCofinsVO());
        item.getNfeDetalheImpostoCofins().setNfeDetalhe(item);
        item.setNfeDetalheImpostoIcms(new NfeDetalheImpostoIcmsVO());
        item.getNfeDetalheImpostoIcms().setNfeDetalhe(item);
        item.setNfeDetalheImpostoIpi(new NfeDetalheImpostoIpiVO());
        item.getNfeDetalheImpostoIpi().setNfeDetalhe(item);
        item.setNfeDetalheImpostoIi(new NfeDetalheImpostoIiVO());
        item.getNfeDetalheImpostoIi().setNfeDetalhe(item);
        item.setListaArmamento(new ArrayList<NfeDetEspecificoArmamentoVO>());
        item.setListaMedicamento(new ArrayList<NfeDetEspecificoMedicamentoVO>());
        item.setListaDeclaracaoImportacao(new ArrayList<NfeDeclaracaoImportacaoVO>());
        Response res;

        // Se houver CFOP cadastrado na Operação Fiscal, a nota é de serviços
        if (operacaoFiscal.getCfop() != null) {
            item.setCfop(operacaoFiscal.getCfop());

            //ISSQN
            TributIssVO iss = operacaoFiscal.getListaIss().get(0);
            item.getNfeDetalheImpostoIssqn().setBaseCalculoIssqn(item.getValorBrutoProduto());
            item.getNfeDetalheImpostoIssqn().setAliquotaIssqn(iss.getAliquotaPorcento());
            item.getNfeDetalheImpostoIssqn().setValorIssqn(item.getNfeDetalheImpostoIssqn().getBaseCalculoIssqn().multiply(iss.getAliquotaPorcento().divide(BigDecimal.valueOf(100), RoundingMode.HALF_DOWN)));
            item.getNfeDetalheImpostoIssqn().setMunicipioIssqn(empresa.getCodigoIbgeCidade());
            item.getNfeDetalheImpostoIssqn().setItemListaServicos(iss.getItemListaServico());
            //item.getNfeDetalheImpostoIssqn().setTributacaoIssqn(iss.getCodigoTributacao());

            //PIS ISSQN
            item.getNfeDetalheImpostoPis().setAliquotaPisPercentual(BigDecimal.ZERO);
            item.getNfeDetalheImpostoPis().setAliquotaPisReais(BigDecimal.ZERO);
            item.getNfeDetalheImpostoPis().setValorBaseCalculoPis(BigDecimal.ZERO);
            item.getNfeDetalheImpostoPis().setValorPis(BigDecimal.ZERO);

            //COFINS ISSQN
            item.getNfeDetalheImpostoCofins().setAliquotaCofinsPercentual(BigDecimal.ZERO);
            item.getNfeDetalheImpostoCofins().setAliquotaCofinsReais(BigDecimal.ZERO);
            item.getNfeDetalheImpostoCofins().setBaseCalculoCofins(BigDecimal.ZERO);
            item.getNfeDetalheImpostoCofins().setValorCofins(BigDecimal.ZERO);
        } else {
            //ICMS
            //Se o Produto estiver vinculado a uma configuração de Operação Fiscal + Grupo Tributário, carrega esses dados
            if (item.getProduto().getTributGrupoTributario() != null) {
                res = ClientUtils.getData("nfeTributAction", new Object[]{1, operacaoFiscal.getId(), item.getProduto().getTributGrupoTributario().getId(), destinatario.getUf()});
                if (res.isError()) {
                    throw new Exception(res.getErrorMessage());
                }
                ViewTributacaoIcmsVO icms = (ViewTributacaoIcmsVO) ((VOResponse) res).getVo();
                if (icms != null) {
                    item.setCfop(icms.getCfop());
                    item.getNfeDetalheImpostoIcms().setOrigemMercadoria(Integer.valueOf(icms.getOrigemMercadoria()));
                    item.getNfeDetalheImpostoIcms().setCstIcms(icms.getCstB());
                    item.getNfeDetalheImpostoIcms().setCsosn(icms.getCsosnB());
                    item.getNfeDetalheImpostoIcms().setModalidadeBcIcms(Integer.valueOf(icms.getModalidadeBc()));
                    item.getNfeDetalheImpostoIcms().setTaxaReducaoBcIcms(icms.getPorcentoBc());
                    item.getNfeDetalheImpostoIcms().setAliquotaIcms(icms.getAliquota());
                    item.getNfeDetalheImpostoIcms().setModalidadeBcIcmsSt(Integer.valueOf(icms.getModalidadeBcSt()));
                    item.getNfeDetalheImpostoIcms().setPercentualMvaIcmsSt(icms.getMva());
                    item.getNfeDetalheImpostoIcms().setPercentualReducaoBcIcmsSt(icms.getPorcentoBcSt());
                    item.getNfeDetalheImpostoIcms().setAliquotaIcmsSt(icms.getAliquotaIcmsSt());
                    item.getNfeDetalheImpostoIcms().setAliquotaCreditoIcmsSn(BigDecimal.ZERO);
                } else {
                    throw new Exception("Não existe tributação de ICMS definida para os parâmetros informados. Operação não realizada.");
                }

                //IPI
                res = ClientUtils.getData("nfeTributAction", new Object[]{2, operacaoFiscal.getId(), item.getProduto().getTributGrupoTributario().getId()});
                if (res.isError()) {
                    throw new Exception(res.getErrorMessage());
                }
                ViewTributacaoIpiVO ipi = (ViewTributacaoIpiVO) ((VOResponse) res).getVo();
                if (ipi != null) {
                    item.getNfeDetalheImpostoIpi().setCstIpi(ipi.getCstIpi());
                    item.getNfeDetalheImpostoIpi().setAliquotaIpi(ipi.getAliquotaPorcento());
                }

                //PIS ICMS
                res = ClientUtils.getData("nfeTributAction", new Object[]{3, operacaoFiscal.getId(), item.getProduto().getTributGrupoTributario().getId()});
                if (res.isError()) {
                    throw new Exception(res.getErrorMessage());
                }
                ViewTributacaoPisVO pis = (ViewTributacaoPisVO) ((VOResponse) res).getVo();
                if (pis != null) {
                    item.getNfeDetalheImpostoPis().setCstPis(pis.getCstPis());
                    item.getNfeDetalheImpostoPis().setAliquotaPisPercentual(pis.getAliquotaPorcento());
                    item.getNfeDetalheImpostoPis().setAliquotaPisReais(pis.getAliquotaUnidade());
                }

                //COFINS ICMS
                res = ClientUtils.getData("nfeTributAction", new Object[]{4, operacaoFiscal.getId(), item.getProduto().getTributGrupoTributario().getId()});
                if (res.isError()) {
                    throw new Exception(res.getErrorMessage());
                }
                ViewTributacaoCofinsVO cofins = (ViewTributacaoCofinsVO) ((VOResponse) res).getVo();
                if (cofins != null) {
                    item.getNfeDetalheImpostoCofins().setNfeDetalhe(item);
                    item.getNfeDetalheImpostoCofins().setCstCofins(cofins.getCstCofins());
                    item.getNfeDetalheImpostoCofins().setAliquotaCofinsPercentual(cofins.getAliquotaPorcento());
                    item.getNfeDetalheImpostoCofins().setAliquotaCofinsReais(cofins.getAliquotaUnidade());
                }
            } else if (item.getProduto().getTributIcmsCustomCab() != null) {
                //Senão pega do ICMS Customizado
                res = ClientUtils.getData("nfeTributAction", new Object[]{5, item.getProduto().getTributIcmsCustomCab().getId(), destinatario.getUf()});
                if (res.isError()) {
                    throw new Exception(res.getErrorMessage());
                }
                ViewTributacaoIcmsCustomVO icms = (ViewTributacaoIcmsCustomVO) ((VOResponse) res).getVo();
                if (icms != null) {
                    item.getNfeDetalheImpostoIcms().setNfeDetalhe(item);
                    item.setCfop(icms.getCfop());
                    item.getNfeDetalheImpostoIcms().setOrigemMercadoria(Integer.valueOf(icms.getOrigemMercadoria()));
                    item.getNfeDetalheImpostoIcms().setCstIcms(icms.getCstB());
                    item.getNfeDetalheImpostoIcms().setCsosn(icms.getCsosnB());
                    item.getNfeDetalheImpostoIcms().setModalidadeBcIcms(Integer.valueOf(icms.getModalidadeBc()));
                    item.getNfeDetalheImpostoIcms().setTaxaReducaoBcIcms(icms.getPorcentoBc());
                    item.getNfeDetalheImpostoIcms().setAliquotaIcms(icms.getAliquota());
                    item.getNfeDetalheImpostoIcms().setModalidadeBcIcmsSt(Integer.valueOf(icms.getModalidadeBcSt()));
                    item.getNfeDetalheImpostoIcms().setPercentualMvaIcmsSt(icms.getMva());
                    item.getNfeDetalheImpostoIcms().setPercentualReducaoBcIcmsSt(icms.getPorcentoBcSt());
                    item.getNfeDetalheImpostoIcms().setAliquotaIcmsSt(icms.getAliquotaIcmsSt());
                    item.getNfeDetalheImpostoIcms().setAliquotaCreditoIcmsSn(BigDecimal.ZERO);
                } else {
                    throw new Exception("Não existe tributação de ICMS definida para os parâmetros informados. Operação não realizada.");
                }
            }

            NfeCalculoVO calculo = NfeCalculoController.calculo(item, empresa, destinatario);

            //Valores ICMS
            item.getNfeDetalheImpostoIcms().setBaseCalculoIcms(calculo.getBaseCalculoIcms());
            item.getNfeDetalheImpostoIcms().setPercentualReducaoBcIcmsSt(calculo.getReducaoBcIcmsSt());
            item.getNfeDetalheImpostoIcms().setValorIcms(calculo.getValorIcms());
            // valores de icms st
            item.getNfeDetalheImpostoIcms().setValorBaseCalculoIcmsSt(calculo.getBaseCalculoIcmsSt());
            item.getNfeDetalheImpostoIcms().setValorIcmsSt(calculo.getValorIcmsSt());
            // credito de icmssn
            item.getNfeDetalheImpostoIcms().setValorCreditoIcmsSn(calculo.getValorCreditoIcmsSn());

            //Valores IPI
            item.getNfeDetalheImpostoIpi().setValorBaseCalculoIpi(calculo.getBaseCalculoIpi());
            item.getNfeDetalheImpostoIpi().setValorIpi(calculo.getValorIpi());

            //Valores PIS
            item.getNfeDetalheImpostoPis().setValorBaseCalculoPis(calculo.getBaseCalculoPis());
            item.getNfeDetalheImpostoPis().setValorPis(calculo.getValorPis());

            //Valores COFINS
            item.getNfeDetalheImpostoCofins().setBaseCalculoCofins(calculo.getBaseCalculoCofins());
            item.getNfeDetalheImpostoCofins().setValorCofins(calculo.getValorCofins());
        }
    }
}
