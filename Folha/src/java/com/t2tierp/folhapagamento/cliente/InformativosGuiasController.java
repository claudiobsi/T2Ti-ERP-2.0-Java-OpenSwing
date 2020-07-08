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
package com.t2tierp.folhapagamento.cliente;

import com.t2tierp.cadastros.java.ColaboradorVO;
import com.t2tierp.cadastros.java.EmpresaVO;
import com.t2tierp.cadastros.java.SefipCodigoRecolhimentoVO;
import com.t2tierp.edi.cnab240.bb.DetalheLoteSegmentoT;
import com.t2tierp.edi.cnab240.bb.GerarArquivoRemessaPagamentoBB;
import com.t2tierp.edi.cnab240.bb.LeArquivoRetornoPagamentoBB;
import com.t2tierp.folhapagamento.cliente.caged.GeraArquivoCAGED;
import com.t2tierp.folhapagamento.cliente.sefip.GeraArquivoSefip;
import com.t2tierp.folhapagamento.java.DarfVO;
import com.t2tierp.folhapagamento.java.GpsVO;
import com.t2tierp.padrao.cliente.Container;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;
import org.openswing.swing.mdi.client.MDIFrame;
import org.openswing.swing.message.receive.java.Response;
import org.openswing.swing.message.receive.java.VOListResponse;
import org.openswing.swing.util.client.ClientUtils;

public class InformativosGuiasController {

    private InformativosGuias grid;
    private String acaoServidor;

    public InformativosGuiasController() {
        acaoServidor = "informativosGuiasAction";
        grid = new InformativosGuias(this);
        MDIFrame.add(grid);
    }

    public List<SefipCodigoRecolhimentoVO> carregaCodigoRecolhimentoGfip() {
        try {
            Response res = ClientUtils.getData(acaoServidor, new Object[]{"codigoRecolhimentoGfip"});
            if (res.isError()) {
                return null;
            }
            return ((VOListResponse) res).getRows();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void geraArquivoCAGED(int mesCompetencia, int anoCompetencia, int alteracaoAutorizado) {
        try {
            Response res = ClientUtils.getData(acaoServidor, new Object[]{"colaboradoresCaged", mesCompetencia, anoCompetencia});
            if (res.isError()) {
                throw new Exception("Erro ao buscar os dados no servidor!");
            }

            EmpresaVO empresa = (EmpresaVO) Container.getContainer().get("empresa");
            List<ColaboradorVO> colaboradores = ((VOListResponse) res).getRows();

            JFileChooser chooser = new JFileChooser();
            int resposta = chooser.showSaveDialog(grid);
            if (resposta == JFileChooser.APPROVE_OPTION) {
                GeraArquivoCAGED arquivoCaged = new GeraArquivoCAGED();
                arquivoCaged.geraArquivoCAGED(chooser.getSelectedFile(), empresa, colaboradores, mesCompetencia, anoCompetencia, alteracaoAutorizado);
                JOptionPane.showMessageDialog(grid, "Arquivo gerado com sucesso!", "Informação do Sistema", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(grid, "Erro ao gerar o arquivo!\n" + e.getMessage(), "Erro do Sistema", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void geraGuiaGps(GpsVO gps) throws Exception {
        EmpresaVO empresa = (EmpresaVO) Container.getContainer().get("empresa");

        List<GpsVO> listaGps = new ArrayList<GpsVO>();
        gps.setRazaoSocial(empresa.getRazaoSocial());
        gps.setBairro(empresa.getListaEndereco().get(0).getBairro());
        gps.setCidade(empresa.getListaEndereco().get(0).getCidade());
        gps.setCodigoPagamento(gps.getCodigoPagamento());
        gps.setEndereco(empresa.getListaEndereco().get(0).getLogradouro() + " " + empresa.getListaEndereco().get(0).getNumero());
        gps.setIdentificador(empresa.getCnpj());
        gps.setMesAnoCompetencia(gps.getMesAnoCompetencia());
        gps.setTelefone(empresa.getListaEndereco().get(0).getFone());
        gps.setUf(empresa.getListaEndereco().get(0).getUf());
        gps.setCep(empresa.getListaEndereco().get(0).getCep());
        gps.setValorInss(gps.getValorInss());
        gps.setValorMultaJuros(gps.getValorMultaJuros());
        gps.setValorOutrasEntidades(gps.getValorOutrasEntidades());
        gps.setValorTotal(gps.getValorTotal());

        listaGps.add(gps);

        //gera a guia
        ImageIcon logoInss = new ImageIcon(this.getClass().getResource("/images/inss_logo.jpg"));
        Map parametros = new HashMap();
        parametros.put("LOGO_INSS", logoInss.getImage());

        JRBeanCollectionDataSource jrbean = new JRBeanCollectionDataSource(listaGps);
        JasperPrint jp = JasperFillManager.fillReport(this.getClass().getResourceAsStream("/relatorios/gps.jasper"), parametros, jrbean);
        JasperViewer.viewReport(jp, false);
    }

    public void geraArquivoSefip(int mesCompetencia, int anoCompetencia, int codigoRecolhimento) {
        try {
            Response res = ClientUtils.getData(acaoServidor, new Object[]{"colaboradoresCaged", mesCompetencia, anoCompetencia});
            if (res.isError()) {
                throw new Exception("Erro ao buscar os dados no servidor!");
            }

            EmpresaVO empresa = (EmpresaVO) Container.getContainer().get("empresa");
            List<ColaboradorVO> colaboradores = ((VOListResponse) res).getRows();

            JFileChooser chooser = new JFileChooser();
            int resposta = chooser.showSaveDialog(grid);
            if (resposta == JFileChooser.APPROVE_OPTION) {
                GeraArquivoSefip arquivoSefip = new GeraArquivoSefip();
                arquivoSefip.geraArquivoSefip(chooser.getSelectedFile(), empresa, colaboradores, mesCompetencia, anoCompetencia, codigoRecolhimento);
                JOptionPane.showMessageDialog(grid, "Arquivo gerado com sucesso!", "Informação do Sistema", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(grid, "Erro ao gerar o arquivo!\n" + e.getMessage(), "Erro do Sistema", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void geraDarf(DarfVO darf) throws Exception {
        List<DarfVO> listaDarf = new ArrayList<DarfVO>();
        EmpresaVO empresa = (EmpresaVO) Container.getContainer().get("empresa");

        darf.setRazaoSocial(empresa.getRazaoSocial());
        darf.setTelefone(empresa.getListaEndereco().get(0).getFone());
        darf.setCpfCnpj(empresa.getCnpj());

        listaDarf.add(darf);

        //gera a guia
        ImageIcon logoReceita = new ImageIcon(this.getClass().getResource("/images/receita.jpg"));
        Map parametros = new HashMap();
        parametros.put("LOGO_RECEITA", logoReceita.getImage());

        JRBeanCollectionDataSource jrbean = new JRBeanCollectionDataSource(listaDarf);
        JasperPrint jp = JasperFillManager.fillReport(this.getClass().getResourceAsStream("/relatorios/darf.jasper"), parametros, jrbean);
        JasperViewer.viewReport(jp, false);
    }

    public void gerarRemessaEDI() {
        try {
            EmpresaVO empresa = (EmpresaVO) Container.getContainer().get("empresa");
            List<ColaboradorVO> colaboradores = new ArrayList<>();

            JFileChooser chooser = new JFileChooser();
            int resposta = chooser.showSaveDialog(grid);
            if (resposta == JFileChooser.APPROVE_OPTION) {
                GerarArquivoRemessaPagamentoBB arquivoRemessa = new GerarArquivoRemessaPagamentoBB();
                arquivoRemessa.gerarArquivoRemessa(colaboradores, empresa, chooser.getSelectedFile());
                ClientUtils.displayURL(chooser.getSelectedFile().getAbsolutePath());
                //JOptionPane.showMessageDialog(grid, "Arquivo gerado com sucesso!", "Informação do Sistema", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(grid, "Erro ao gerar o arquivo!\n" + e.getMessage(), "Erro do Sistema", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void lerRetornoEDI() {
        try {
            JFileChooser chooser = new JFileChooser();
            int resposta = chooser.showOpenDialog(grid);
            if (resposta == JFileChooser.APPROVE_OPTION) {
                File file = chooser.getSelectedFile();
                LeArquivoRetornoPagamentoBB arquivoRetorno = new LeArquivoRetornoPagamentoBB();
                Collection<DetalheLoteSegmentoT> listaSegmentoT = arquivoRetorno.leArquivoRetorno(file);
                for (DetalheLoteSegmentoT d : listaSegmentoT) {
                    //exercício: realizar o processamento do retorno
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Ocorreu um erro ao processar o arquivo!\n" + e.getMessage(), "Erro do Sistema", JOptionPane.ERROR_MESSAGE);
        }
    }
}
