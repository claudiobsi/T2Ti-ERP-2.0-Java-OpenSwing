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
package com.t2tierp.nfse.cliente;

import com.t2tierp.cadastros.java.EmpresaVO;
import com.t2tierp.nfe.java.RespostaSefaz;
import com.t2tierp.nfse.java.NfseCabecalhoVO;
import com.t2tierp.nfse.java.NfseDetalheVO;
import com.t2tierp.padrao.cliente.Container;
import com.t2tierp.padrao.java.Constantes;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;
import org.openswing.swing.form.client.FormController;
import org.openswing.swing.mdi.client.MDIFrame;
import org.openswing.swing.message.receive.java.Response;
import org.openswing.swing.message.receive.java.VOListResponse;
import org.openswing.swing.message.receive.java.VOResponse;
import org.openswing.swing.message.receive.java.ValueObject;
import org.openswing.swing.util.client.ClientUtils;
import org.openswing.swing.util.java.Consts;

public class NfseCabecalhoDetalheController extends FormController {

    private NfseCabecalhoDetalhe telaNfseDetalhe = null;
    private String pk = null;
    private NfseCabecalhoGrid nfseCabecalhoGrid = null;
    private String acaoServidor;

    public NfseCabecalhoDetalheController(NfseCabecalhoGrid nfseCabecalhoGrid, String pk) {
        this.nfseCabecalhoGrid = nfseCabecalhoGrid;
        this.pk = pk;
        this.acaoServidor = "nfseCabecalhoDetalheAction";
        telaNfseDetalhe = new NfseCabecalhoDetalhe(this);
        telaNfseDetalhe.setParentFrame(this.nfseCabecalhoGrid);
        this.nfseCabecalhoGrid.pushFrame(telaNfseDetalhe);
        MDIFrame.add(telaNfseDetalhe, true);

        if (pk != null) {
            telaNfseDetalhe.getForm1().setMode(Consts.READONLY);
            telaNfseDetalhe.getForm1().reload();
        } else {
            telaNfseDetalhe.getForm1().setMode(Consts.INSERT);
            telaNfseDetalhe.getGridControlServicos().reloadData();
            telaNfseDetalhe.getGridControlIntermediario().reloadData();
        }
    }

    @Override
    public Response loadData(Class valueObjectClass) {
        return ClientUtils.getData(acaoServidor, new Object[]{Constantes.LOAD, pk});
    }

    @Override
    public void loadDataCompleted(boolean error) {
        NfseCabecalhoVO nfseCabecalho = (NfseCabecalhoVO) telaNfseDetalhe.getForm1().getVOModel().getValueObject();

        telaNfseDetalhe.getServicosController().setListaNfseDetalhe(nfseCabecalho.getListaNfseDetalhe());
        telaNfseDetalhe.getGridControlServicos().reloadData();

        telaNfseDetalhe.getIntermediarioController().setListaNfseIntermediario(nfseCabecalho.getListaNfseIntermediario());
        telaNfseDetalhe.getGridControlIntermediario().reloadData();
    }

    @Override
    public Response insertRecord(ValueObject newPersistentObject) throws Exception {
        EmpresaVO empresa = (EmpresaVO) Container.getContainer().get("empresa");
        NfseCabecalhoVO nfseCabecalho = (NfseCabecalhoVO) newPersistentObject;
        nfseCabecalho.setEmpresa(empresa);
        nfseCabecalho.setListaNfseDetalhe(telaNfseDetalhe.getServicosController().getListaNfseDetalhe());
        nfseCabecalho.setListaNfseIntermediario(telaNfseDetalhe.getIntermediarioController().getListaNfseIntermediario());

        return ClientUtils.getData(acaoServidor, new Object[]{Constantes.INSERT, nfseCabecalho});
    }

    @Override
    public void afterInsertData() {
        NfseCabecalhoVO nfseCabecalho = (NfseCabecalhoVO) telaNfseDetalhe.getForm1().getVOModel().getValueObject();
        pk = nfseCabecalho.getId().toString();

        nfseCabecalhoGrid.getGrid1().reloadData();
        JOptionPane.showMessageDialog(telaNfseDetalhe, "Dados salvos com sucesso!", "Informacao do Sistema", JOptionPane.INFORMATION_MESSAGE);
    }

    @Override
    public Response updateRecord(ValueObject oldPersistentObject, ValueObject persistentObject) throws Exception {
        NfseCabecalhoVO nfseCabecalho = (NfseCabecalhoVO) persistentObject;
        nfseCabecalho.setListaNfseDetalhe(telaNfseDetalhe.getServicosController().getListaNfseDetalhe());
        nfseCabecalho.setListaNfseIntermediario(telaNfseDetalhe.getIntermediarioController().getListaNfseIntermediario());

        return ClientUtils.getData(acaoServidor, new Object[]{Constantes.UPDATE, oldPersistentObject, nfseCabecalho});
    }

    @Override
    public void afterEditData() {
        nfseCabecalhoGrid.getGrid1().reloadData();
        JOptionPane.showMessageDialog(telaNfseDetalhe, "Dados alterados com sucesso!", "Informacao do Sistema", JOptionPane.INFORMATION_MESSAGE);
    }

    @Override
    public boolean validateControl(String attributeName, Object oldValue, Object newValue) {
        if (attributeName.equals("osAbertura.numero")) {
            carregaServicoOs((String) newValue);
        }
        return super.validateControl(attributeName, oldValue, newValue);
    }

    private void carregaServicoOs(String numeroOs) {
        //(ValorServicos - ValorPIS - ValorCOFINS - ValorINSS - ValorIR - ValorCSLL - OutrasRetençoes - ValorISSRetido -  DescontoIncondicionado - DescontoCondicionado)
        try {
            EmpresaVO empresa = (EmpresaVO) Container.getContainer().get("empresa");
            Response res = ClientUtils.getData(acaoServidor, new Object[]{97, numeroOs, empresa});

            if (res.isError()) {
                throw new Exception(res.getErrorMessage());
            }

            List<NfseDetalheVO> listaNfseDetalhe = ((VOListResponse) res).getRows();

            telaNfseDetalhe.getServicosController().setListaNfseDetalhe(listaNfseDetalhe);
            telaNfseDetalhe.getGridControlServicos().reloadData();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(telaNfseDetalhe, e.getMessage(), "Erro do Sistema", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void enviarNfse() {
        try {
            if (telaNfseDetalhe.getForm1().getMode() != Consts.READONLY) {
                JOptionPane.showMessageDialog(telaNfseDetalhe, "Necessário salvar a NFS-e", "Informação do Sistema", JOptionPane.INFORMATION_MESSAGE);
            } else {
                NfseCabecalhoVO nfseCabecalho = (NfseCabecalhoVO) telaNfseDetalhe.getForm1().getVOModel().getValueObject();
                Response res = ClientUtils.getData(acaoServidor, new Object[]{98, nfseCabecalho});

                if (res.isError()) {
                    throw new Exception(res.getErrorMessage());
                }

                RespostaSefaz respostaSefaz = (RespostaSefaz) ((VOResponse) res).getVo();

                JOptionPane.showMessageDialog(telaNfseDetalhe, respostaSefaz.getResposta(), "Informação do Sistema", JOptionPane.INFORMATION_MESSAGE);
                telaNfseDetalhe.getForm1().reload();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(telaNfseDetalhe, ex.getMessage(), "Erro do Sistema", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void consultaEnvioNfse() {
        try {
            if (telaNfseDetalhe.getForm1().getMode() != Consts.READONLY) {
                JOptionPane.showMessageDialog(telaNfseDetalhe, "Necessário salvar a NFS-e", "Informação do Sistema", JOptionPane.INFORMATION_MESSAGE);
            } else {
                NfseCabecalhoVO nfseCabecalho = (NfseCabecalhoVO) telaNfseDetalhe.getForm1().getVOModel().getValueObject();
                Response res = ClientUtils.getData(acaoServidor, new Object[]{99, nfseCabecalho});

                if (res.isError()) {
                    throw new Exception(res.getErrorMessage());
                }

                RespostaSefaz respostaSefaz = (RespostaSefaz) ((VOResponse) res).getVo();

                JOptionPane.showMessageDialog(telaNfseDetalhe, respostaSefaz.getResposta(), "Informação do Sistema", JOptionPane.INFORMATION_MESSAGE);
                telaNfseDetalhe.getForm1().reload();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(telaNfseDetalhe, ex.getMessage(), "Erro do Sistema", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void imprimeNfse() {
        try {
            if (telaNfseDetalhe.getForm1().getMode() != Consts.READONLY) {
                JOptionPane.showMessageDialog(telaNfseDetalhe, "Necessário salvar a NFS-e", "Informação do Sistema", JOptionPane.INFORMATION_MESSAGE);
            } else {
                NfseCabecalhoVO nfseCabecalho = (NfseCabecalhoVO) telaNfseDetalhe.getForm1().getVOModel().getValueObject();
                if (nfseCabecalho.getNumero() == null || nfseCabecalho.getNumero().isEmpty()) {
                    JOptionPane.showMessageDialog(telaNfseDetalhe, "NFS-e ainda não autorizada", "Informação do Sistema", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    Map map = new HashMap();
                    map.put("NUMERO_NOTA", nfseCabecalho.getNumero());
                    map.put("DATA_EMISSAO", nfseCabecalho.getDataHoraEmissao());
                    map.put("COMPETENCIA", nfseCabecalho.getCompetencia());
                    map.put("CODIGO_VERIFICACAO", nfseCabecalho.getCodigoVerificacao());
                    map.put("PRESTADOR_RAZAO_SOCIAL", nfseCabecalho.getEmpresa().getRazaoSocial());
                    map.put("PRESTADOR_CNPJ", nfseCabecalho.getEmpresa().getCnpj());
                    map.put("TOMADOR_RAZAO_SOCIAL", nfseCabecalho.getCliente().getPessoa().getNome());
                    if (nfseCabecalho.getCliente().getPessoa().getPessoaFisica() != null) {
                        map.put("TOMADOR_CPF_CNPJ", nfseCabecalho.getCliente().getPessoa().getPessoaFisica().getCpf());
                    }
                    if (nfseCabecalho.getCliente().getPessoa().getPessoaJuridica() != null) {
                        map.put("TOMADOR_CPF_CNPJ", nfseCabecalho.getCliente().getPessoa().getPessoaJuridica().getCnpj());
                    }
                    map.put("LOGO_EMPRESA", new ImageIcon(ClientUtils.getImage("logo_t2ti.png")).getImage());

                    JRBeanCollectionDataSource jrbean = new JRBeanCollectionDataSource(nfseCabecalho.getListaNfseDetalhe());
                    JasperPrint jp = JasperFillManager.fillReport(this.getClass().getResourceAsStream("/relatorios/nfse.jasper"), map, jrbean);
                    JasperViewer.viewReport(jp, false);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(telaNfseDetalhe, ex.getMessage(), "Erro do Sistema", JOptionPane.ERROR_MESSAGE);
        }
    }
}
