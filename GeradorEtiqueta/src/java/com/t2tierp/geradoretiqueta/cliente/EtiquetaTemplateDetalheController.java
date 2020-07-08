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
package com.t2tierp.geradoretiqueta.cliente;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.oned.EAN13Writer;
import com.google.zxing.qrcode.QRCodeWriter;
import com.t2tierp.geradoretiqueta.java.EtiquetaRelatorio;
import com.t2tierp.geradoretiqueta.java.EtiquetaTemplateVO;
import com.t2tierp.padrao.java.Constantes;
import java.awt.Image;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
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

public class EtiquetaTemplateDetalheController extends FormController {

    private EtiquetaTemplateDetalhe etiquetaTemplateDetalhe = null;
    private String pk = null;
    private EtiquetaTemplateGrid etiquetaTemplateGrid = null;
    private String acaoServidor;

    public EtiquetaTemplateDetalheController(EtiquetaTemplateGrid etiquetaTemplateGrid, String pk) {
        this.etiquetaTemplateGrid = etiquetaTemplateGrid;
        this.pk = pk;
        this.acaoServidor = "etiquetaTemplateDetalheAction";
        etiquetaTemplateDetalhe = new EtiquetaTemplateDetalhe(this);
        etiquetaTemplateDetalhe.setParentFrame(this.etiquetaTemplateGrid);
        this.etiquetaTemplateGrid.pushFrame(etiquetaTemplateDetalhe);
        MDIFrame.add(etiquetaTemplateDetalhe);

        if (pk != null) {
            etiquetaTemplateDetalhe.getForm1().setMode(Consts.READONLY);
            etiquetaTemplateDetalhe.getForm1().reload();
        } else {
            etiquetaTemplateDetalhe.getForm1().setMode(Consts.INSERT);
        }
    }

    @Override
    public Response loadData(Class valueObjectClass) {
        return ClientUtils.getData(acaoServidor, new Object[]{Constantes.LOAD, pk});
    }

    @Override
    public Response insertRecord(ValueObject newPersistentObject) throws Exception {
        return ClientUtils.getData(acaoServidor, new Object[]{Constantes.INSERT, newPersistentObject});
    }

    @Override
    public void afterInsertData() {
        etiquetaTemplateGrid.getGrid1().reloadData();
        JOptionPane.showMessageDialog(etiquetaTemplateDetalhe, "Dados salvos com sucesso!", "Informacao do Sistema", JOptionPane.INFORMATION_MESSAGE);
    }

    @Override
    public Response updateRecord(ValueObject oldPersistentObject, ValueObject persistentObject) throws Exception {
        return ClientUtils.getData(acaoServidor, new Object[]{Constantes.UPDATE, oldPersistentObject, persistentObject});
    }

    @Override
    public void afterEditData() {
        etiquetaTemplateGrid.getGrid1().reloadData();
        JOptionPane.showMessageDialog(etiquetaTemplateDetalhe, "Dados alterados com sucesso!", "Informacao do Sistema", JOptionPane.INFORMATION_MESSAGE);
    }

    public void selecionarTabela() {
        try {
            if (etiquetaTemplateDetalhe.getForm1().getMode() != Consts.READONLY) {
                Response res = ClientUtils.getData("etiquetaTabelasAction", null);
                if (res.isError()) {
                    JOptionPane.showMessageDialog(etiquetaTemplateDetalhe, res.getErrorMessage(), "Erro do Sistema", JOptionPane.ERROR_MESSAGE);
                } else {
                    Map<String, List<String>> tabelas = (HashMap) ((VOResponse) res).getVo();

                    EtiquetaTabelasGrid etiquetaTabelasGrid = new EtiquetaTabelasGrid(null, true, tabelas);
                    etiquetaTabelasGrid.setLocationRelativeTo(null);
                    etiquetaTabelasGrid.setVisible(true);

                    if (!etiquetaTabelasGrid.isCancelado()) {
                        EtiquetaTemplateVO template = (EtiquetaTemplateVO) etiquetaTemplateDetalhe.getForm1().getVOModel().getValueObject();
                        template.setTabela(etiquetaTabelasGrid.getTabela());
                        template.setCampo(etiquetaTabelasGrid.getCampo());
                        etiquetaTemplateDetalhe.getForm1().pull();
                    }
                }
            } else {
                JOptionPane.showMessageDialog(etiquetaTemplateDetalhe, "Edição não permitida", "Informacao do Sistema", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(etiquetaTemplateDetalhe, e.getMessage(), "Erro do Sistema", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void geraEtiqueta() {
        try {
            if (etiquetaTemplateDetalhe.getForm1().getMode() != Consts.READONLY) {
                JOptionPane.showMessageDialog(etiquetaTemplateDetalhe, "Necessário salvar o template", "Informacao do Sistema", JOptionPane.INFORMATION_MESSAGE);
            } else {
                EtiquetaTemplateVO template = (EtiquetaTemplateVO) etiquetaTemplateDetalhe.getForm1().getVOModel().getValueObject();

                Response res = ClientUtils.getData(acaoServidor, new Object[]{99, template});
                if (res.isError()) {
                    JOptionPane.showMessageDialog(etiquetaTemplateDetalhe, res.getErrorMessage(), "Erro do Sistema", JOptionPane.ERROR_MESSAGE);
                } else {
                    List listaRegistros = ((VOListResponse) res).getRows();

                    List<EtiquetaRelatorio> listaRelatorio = new ArrayList<>();
                    for (Object o : listaRegistros) {
                        if (o != null) {
                            for (int i = 0; i < template.getQuantidadeRepeticoes(); i++) {
                                EtiquetaRelatorio er = new EtiquetaRelatorio();
                                er.setTexto(o.toString());
                                er.setCodigoBarra(geracodigoBarra(template.getFormato(), er.getTexto()));

                                listaRelatorio.add(er);
                            }
                        }
                    }

                    JRBeanCollectionDataSource jrbean = new JRBeanCollectionDataSource(listaRelatorio);
                    JasperReport jr = GeradorEtiqueta.geraEtiqueta(template.getEtiquetaLayout());
                    JasperPrint jp = JasperFillManager.fillReport(jr, new HashMap<>(), jrbean);
                    JasperViewer.viewReport(jp, false);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(etiquetaTemplateDetalhe, e.getMessage(), "Erro do Sistema", JOptionPane.ERROR_MESSAGE);
        }
    }

    private Image geracodigoBarra(int formato, String texto) throws Exception {
        if (formato == 0) {
            return MatrixToImageWriter.toBufferedImage(new EAN13Writer().encode(texto, BarcodeFormat.EAN_13, 180, 70));
        } else {
            return MatrixToImageWriter.toBufferedImage(new QRCodeWriter().encode(texto, BarcodeFormat.QR_CODE, 200, 200));
        }
    }

}
