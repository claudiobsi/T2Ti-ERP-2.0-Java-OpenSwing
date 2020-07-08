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
package com.t2tierp.financeiro.cliente;

import com.t2tierp.cadastros.java.ContaCaixaVO;
import com.t2tierp.financeiro.java.FinExtratoContaBancoVO;
import com.t2tierp.padrao.java.Constantes;
import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;
import org.openswing.swing.mdi.client.MDIFrame;
import org.openswing.swing.message.receive.java.ErrorResponse;
import org.openswing.swing.message.receive.java.Response;
import org.openswing.swing.message.receive.java.VOListResponse;
import org.openswing.swing.message.send.java.GridParams;
import org.openswing.swing.table.client.GridController;
import org.openswing.swing.table.java.GridDataLocator;
import org.openswing.swing.util.client.ClientUtils;
import org.openswing.swing.util.java.Consts;

public class FinExtratoContaBancoDetalheController extends GridController implements GridDataLocator {

    private FinExtratoContaBancoDetalhe finExtratoContaBancoDetalhe = null;
    private FinExtratoContaBancoGrid finExtratoContaBancoGrid = null;
    private String acaoServidor;
    private ContaCaixaVO contaCaixa;

    public FinExtratoContaBancoDetalheController(FinExtratoContaBancoGrid finExtratoContaBancoGrid, ContaCaixaVO contaCaixa) {
        this.finExtratoContaBancoGrid = finExtratoContaBancoGrid;
        this.acaoServidor = "finExtratoContaBancoDetalheAction";
        this.contaCaixa = contaCaixa;

        finExtratoContaBancoDetalhe = new FinExtratoContaBancoDetalhe(this);
        finExtratoContaBancoDetalhe.setParentFrame(this.finExtratoContaBancoGrid);
        this.finExtratoContaBancoGrid.pushFrame(finExtratoContaBancoDetalhe);
        MDIFrame.add(finExtratoContaBancoDetalhe, true);

        finExtratoContaBancoDetalhe.getForm1().setMode(Consts.INSERT);
        FinExtratoContaBancoVO extrato = (FinExtratoContaBancoVO) finExtratoContaBancoDetalhe.getForm1().getVOModel().getValueObject();
        extrato.setContaCaixa(contaCaixa);
        finExtratoContaBancoDetalhe.getForm1().pull();
        //finExtratoContaBancoDetalhe.getForm1().reload();
    }

    public Response loadData(int action, int startIndex, Map filteredColumns, ArrayList currentSortedColumns, ArrayList currentSortedVersusColumns, Class valueObjectType, Map otherGridParams) {
        if (!periodoValido(getMesAno())) {
            return new ErrorResponse("Mês/Ano Inválido");
        }
        //define os parametros da grid
        otherGridParams.put("acao", Constantes.LOAD);
        otherGridParams.put("contaCaixa", contaCaixa);
        otherGridParams.put("mesAno", getMesAno());

        return ClientUtils.getData(acaoServidor, new GridParams(action, startIndex, filteredColumns, currentSortedColumns, currentSortedVersusColumns, otherGridParams));
    }

    @Override
    public void loadDataCompleted(boolean error) {
        atualizaSaldo();
    }

    public String getMesAno() {
        return finExtratoContaBancoDetalhe.getPeriodo();
    }

    public void importaOFX() {
        FileFilter filter = new FileFilter() {

            @Override
            public boolean accept(File f) {
                String arquivo = f.getName().toLowerCase();
                return f.isDirectory() || arquivo.endsWith(".ofx");
            }

            @Override
            public String getDescription() {
                return "*.ofx";
            }
        };

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(filter);
        fileChooser.showOpenDialog(finExtratoContaBancoDetalhe);
        File file = fileChooser.getSelectedFile();

        if (file != null) {
            ImportaOFX importa = new ImportaOFX();
            List<FinExtratoContaBancoVO> listaExtrato = importa.importaArquivoOFX(file);
            //seta os dados do extrato
            for (int i = 0; i < listaExtrato.size(); i++) {
                listaExtrato.get(i).setContaCaixa(contaCaixa);
                finExtratoContaBancoDetalhe.getGrid1().getVOListTableModel().addObject(listaExtrato.get(i));
            }
            atualizaSaldo();
        }
    }

    public void salvaExtrato() throws Exception {
        if (!periodoValido(getMesAno())) {
            throw new Exception("Período inválido!");
        }
        List<FinExtratoContaBancoVO> listaExtrato = finExtratoContaBancoDetalhe.getGrid1().getVOListTableModel().getDataVector();
        if (listaExtrato.isEmpty()) {
            throw new Exception("Nenhum registro para salvar!");
        }
        //define os parametros da grid
        Map otherGridParams = new HashMap();
        otherGridParams.put("acao", Constantes.INSERT);
        otherGridParams.put("mesAno", getMesAno());
        otherGridParams.put("newValueObjects", listaExtrato);

        //seta os parametros da grid
        GridParams pars = new GridParams(0, 0, null, null, null, otherGridParams);

        Response res = ClientUtils.getData(acaoServidor, pars);
        if (res.isError()) {
            throw new Exception(res.getErrorMessage());
        }
    }

    private void atualizaSaldo() {
        List<FinExtratoContaBancoVO> listaExtrato = finExtratoContaBancoDetalhe.getGrid1().getVOListTableModel().getDataVector();
        BigDecimal creditos = BigDecimal.ZERO;
        BigDecimal debitos = BigDecimal.ZERO;
        BigDecimal saldo = BigDecimal.ZERO;
        for (int i = 0; i < listaExtrato.size(); i++) {
            if (listaExtrato.get(i).getValor().compareTo(BigDecimal.ZERO) == -1) {
                debitos = debitos.add(listaExtrato.get(i).getValor());
            } else {
                creditos = creditos.add(listaExtrato.get(i).getValor());
            }
        }
        saldo = creditos.add(debitos);

        finExtratoContaBancoDetalhe.setTextoCreditos(creditos);
        finExtratoContaBancoDetalhe.setTextoDebitos(debitos);
        finExtratoContaBancoDetalhe.setTextoSaldo(saldo);
    }

    public void efetuaConciliacao(String tipoConciliacao) throws Exception {
        //define os parametros da grid
        Map otherGridParams = new HashMap();
        otherGridParams.put("acao", Constantes.UPDATE);
        otherGridParams.put("tipoConciliacao", tipoConciliacao);
        otherGridParams.put("contaCaixa", contaCaixa);
        otherGridParams.put("extrato", finExtratoContaBancoDetalhe.getGrid1().getVOListTableModel().getDataVector());

        //seta os parametros da grid
        GridParams pars = new GridParams(0, 0, null, null, null, otherGridParams);

        Response res = ClientUtils.getData(acaoServidor, pars);
        if (res.isError()) {
            throw new Exception(res.getErrorMessage());
        } else {
            List<FinExtratoContaBancoVO> listaExtrato = ((VOListResponse) res).getRows();
            finExtratoContaBancoDetalhe.getGrid1().clearData();
            for (int i = 0; i < listaExtrato.size(); i++){
                finExtratoContaBancoDetalhe.getGrid1().getVOListTableModel().addObject(listaExtrato.get(i));
            }
        }
    }

    private boolean periodoValido(String periodo) {
        try {
            if (periodo == null) {
                return false;
            }
            if ((periodo.length() != 7)) {
                return false;
            }
            Calendar dataValida = Calendar.getInstance();
            dataValida.setLenient(false);
            int mes = Integer.valueOf(periodo.substring(0, 2));
            int ano = Integer.valueOf(periodo.substring(3, 7));

            dataValida.set(Calendar.DAY_OF_MONTH, 1);
            dataValida.set(Calendar.MONTH, mes - 1);
            dataValida.set(Calendar.YEAR, ano);

            dataValida.getTime();

            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
