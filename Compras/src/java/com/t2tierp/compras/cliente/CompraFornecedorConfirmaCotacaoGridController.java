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
package com.t2tierp.compras.cliente;

import com.t2tierp.compras.java.CompraCotacaoDetalheVO;
import com.t2tierp.compras.java.CompraFornecedorCotacaoVO;
import com.t2tierp.padrao.java.Constantes;
import java.io.File;
import java.io.PrintStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;
import org.apache.commons.io.FileUtils;
import org.openswing.swing.message.receive.java.Response;
import org.openswing.swing.message.send.java.GridParams;
import org.openswing.swing.table.client.GridController;
import org.openswing.swing.table.java.GridDataLocator;
import org.openswing.swing.util.client.ClientUtils;
import org.openswing.swing.util.java.Consts;

public class CompraFornecedorConfirmaCotacaoGridController extends GridController implements GridDataLocator {

    private String acaoServidor;
    private String pk;
    private CompraConfirmaCotacaoDetalhe compraConfirmaCotacaoDetalhe;

    public CompraFornecedorConfirmaCotacaoGridController(CompraConfirmaCotacaoDetalhe compraConfirmaCotacaoDetalhe) {
        acaoServidor = "compraFornecedorCotacaoGridAction";
        this.compraConfirmaCotacaoDetalhe = compraConfirmaCotacaoDetalhe;
    }

    public Response loadData(int action, int startIndex, Map filteredColumns, ArrayList currentSortedColumns, ArrayList currentSortedVersusColumns, Class valueObjectType, Map otherGridParams) {
        //define os parametros da grid
        otherGridParams.put("acao", Constantes.LOAD);
        otherGridParams.put("idCompraCotacao", pk);

        return ClientUtils.getData(acaoServidor, new GridParams(action, startIndex, filteredColumns, currentSortedColumns, currentSortedVersusColumns, otherGridParams));
    }

    @Override
    public Response updateRecords(int[] rowNumbers, ArrayList oldPersistentObjects, ArrayList persistentObjects) throws Exception {
        //define os parametros da grid
        Map otherGridParams = new HashMap();
        otherGridParams.put("acao", Constantes.UPDATE);
        otherGridParams.put("oldPersistentObjects", oldPersistentObjects);
        otherGridParams.put("persistentObjects", persistentObjects);

        //seta os parametros da grid
        GridParams pars = new GridParams(0, 0, null, null, null, otherGridParams);

        if (compraConfirmaCotacaoDetalhe.getForm1().getMode() == Consts.READONLY) {
            compraConfirmaCotacaoDetalhe.getForm1().setMode(Consts.EDIT);
        }

        return ClientUtils.getData(acaoServidor, pars);
    }

    @Override
    public void rowChanged(int rowNumber) {
        CompraFornecedorCotacaoVO fornecedor = (CompraFornecedorCotacaoVO) compraConfirmaCotacaoDetalhe.getGridFornecedor().getVOListTableModel().getObjectForRow(rowNumber);
        compraConfirmaCotacaoDetalhe.getCotacaoDetalheController().setPk(fornecedor.getId().toString());
        compraConfirmaCotacaoDetalhe.getGridItensCotacao().reloadData();
    }

    public void exportaCsvFornecedor() throws Exception {
        List<CompraFornecedorCotacaoVO> fornecedores = compraConfirmaCotacaoDetalhe.getGridFornecedor().getVOListTableModel().getDataVector();
        List<CompraCotacaoDetalheVO> itens = compraConfirmaCotacaoDetalhe.getGridItensCotacao().getVOListTableModel().getDataVector();
        String nomeArquivo;
        String linhasArquivo;
        for (int i = 0; i < fornecedores.size(); i++) {
            nomeArquivo = "Fornecedor_" + fornecedores.get(i).getCompraCotacao().getId() + "_" + fornecedores.get(i).getId() + ".csv";
            linhasArquivo = "Id Produto;Nome Produto;Quantidade;Valor Unitário;Valor Subtotal;Taxa Desconto;Valor Desconto;Valor Total" + System.getProperty("line.separator");
            for (int j = 0; j < itens.size(); j++) {
                linhasArquivo += itens.get(j).getProduto().getId()
                        + ";" + itens.get(j).getProduto().getNome()
                        + ";" + itens.get(j).getQuantidade()
                        + ";" + "0;0;0;0;0"
                        + System.getProperty("line.separator");
            }
            PrintStream printStream = new PrintStream(System.getProperty("user.home") + System.getProperty("file.separator") + nomeArquivo);
            printStream.print(linhasArquivo);
            printStream.close();
        }
        JOptionPane.showMessageDialog(compraConfirmaCotacaoDetalhe, "Arquivos salvos em " + System.getProperty("user.home"), "Informação do Sistema", JOptionPane.INFORMATION_MESSAGE);
    }

    public void importaCsvFornecedor() throws Exception {
        int row = compraConfirmaCotacaoDetalhe.getGridFornecedor().getSelectedRow();
        if (row == -1) {
            throw new Exception("Nenhum fornecedor selecionado!");
        }
        FileFilter filter = new FileFilter() {

            @Override
            public boolean accept(File f) {
                String arquivo = f.getName().toLowerCase();
                return f.isDirectory() || arquivo.endsWith(".csv");
            }

            @Override
            public String getDescription() {
                return "*.csv";
            }
        };

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(filter);
        fileChooser.showOpenDialog(compraConfirmaCotacaoDetalhe);
        File file = fileChooser.getSelectedFile();

        if (file != null) {
            CompraFornecedorCotacaoVO fornecedor = (CompraFornecedorCotacaoVO) compraConfirmaCotacaoDetalhe.getGridFornecedor().getVOListTableModel().getObjectForRow(row);
            List<CompraCotacaoDetalheVO> itens = compraConfirmaCotacaoDetalhe.getGridItensCotacao().getVOListTableModel().getDataVector();
            List<String> lines = FileUtils.readLines(file);

            int idProduto;
            BigDecimal valorSubtotal = BigDecimal.ZERO;
            BigDecimal taxaDesconto = BigDecimal.ZERO;
            BigDecimal valorDesconto = BigDecimal.ZERO;
            BigDecimal valorTotal = BigDecimal.ZERO;
            String linhaArquivo[];
            List<String> linhaErro = new ArrayList<String>();
            compraConfirmaCotacaoDetalhe.getGridItensCotacao().setMode(Consts.EDIT);
            for (int i = 0; i < lines.size(); i++) {
                if (i != 0) {
                    linhaArquivo = lines.get(i).split(";");
                    for (int j = 0; j < itens.size(); j++) {
                        try {
                            idProduto = Integer.valueOf(linhaArquivo[0]);
                            if (itens.get(j).getProduto().getId().intValue() == idProduto) {
                                itens.get(j).setValorUnitario(BigDecimal.valueOf(Double.valueOf(linhaArquivo[3])));
                                itens.get(j).setValorSubtotal(BigDecimal.valueOf(Double.valueOf(linhaArquivo[4])));
                                valorSubtotal = valorSubtotal.add(itens.get(j).getValorSubtotal());
                                itens.get(j).setTaxaDesconto(BigDecimal.valueOf(Double.valueOf(linhaArquivo[5])));
                                taxaDesconto = taxaDesconto.add(itens.get(j).getTaxaDesconto());
                                itens.get(j).setValorDesconto(BigDecimal.valueOf(Double.valueOf(linhaArquivo[6])));
                                valorDesconto = valorDesconto.add(itens.get(j).getValorDesconto());
                                itens.get(j).setValorTotal(BigDecimal.valueOf(Double.valueOf(linhaArquivo[7])));
                                valorTotal = valorTotal.add(itens.get(j).getValorTotal());

                                compraConfirmaCotacaoDetalhe.getGridItensCotacao().getVOListTableModel().updateObjectAt(j);
                                break;
                            }
                        } catch (Exception e) {
                            linhaErro.add("Linha " + (i + 1) + ": " + lines.get(i) + "\n" + "Erro: " + e.getMessage() + "\n");
                        }
                    }
                }
            }
            compraConfirmaCotacaoDetalhe.getGridItensCotacao().save();
            compraConfirmaCotacaoDetalhe.getGridFornecedor().setMode(Consts.EDIT);
            fornecedor.setValorSubtotal(valorSubtotal);
            fornecedor.setTaxaDesconto(taxaDesconto);
            fornecedor.setValorDesconto(valorDesconto);
            fornecedor.setTotal(valorTotal);
            compraConfirmaCotacaoDetalhe.getGridFornecedor().getVOListTableModel().updateObjectAt(row);
            compraConfirmaCotacaoDetalhe.getGridFornecedor().save();

            String mensagem = "";
            if (!linhaErro.isEmpty()) {
                for (int i = 0; i < linhaErro.size(); i++) {
                    mensagem += linhaErro.get(i);
                }
                throw new Exception(mensagem);
            }
        } else {
            throw new Exception("Importação Cancelada!");
        }
        if (compraConfirmaCotacaoDetalhe.getForm1().getMode() == Consts.READONLY) {
            compraConfirmaCotacaoDetalhe.getForm1().setMode(Consts.EDIT);
        }
    }

    /**
     * @param pk the pk to set
     */
    public void setPk(String pk) {
        this.pk = pk;
    }
}
