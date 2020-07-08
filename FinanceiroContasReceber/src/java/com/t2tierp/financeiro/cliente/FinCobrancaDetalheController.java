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

import com.t2tierp.financeiro.java.FinCobrancaParcelaReceberVO;
import com.t2tierp.financeiro.java.FinCobrancaVO;
import com.t2tierp.financeiro.java.FinParcelaReceberVO;
import com.t2tierp.padrao.java.Biblioteca;
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

public class FinCobrancaDetalheController extends FormController {

    private FinCobrancaDetalhe finCobrancaDetalhe = null;
    private String pk = null;
    private FinCobrancaGrid finCobrancaGrid = null;
    private String acaoServidor;

    public FinCobrancaDetalheController(FinCobrancaGrid finCobrancaGrid, String pk) {
        this.finCobrancaGrid = finCobrancaGrid;
        this.pk = pk;
        this.acaoServidor = "finCobrancaDetalheAction";
        finCobrancaDetalhe = new FinCobrancaDetalhe(this);
        finCobrancaDetalhe.setParentFrame(this.finCobrancaGrid);
        this.finCobrancaGrid.pushFrame(finCobrancaDetalhe);
        MDIFrame.add(finCobrancaDetalhe, true);

        if (pk != null) {
            finCobrancaDetalhe.getForm1().setMode(Consts.READONLY);
            finCobrancaDetalhe.getForm1().reload();
        } else {
            finCobrancaDetalhe.getForm1().setMode(Consts.INSERT);
            finCobrancaDetalhe.getGridControlParcelaCobranca().reloadData();
        }
    }

    @Override
    public Response loadData(Class valueObjectClass) {
        return ClientUtils.getData(acaoServidor, new Object[]{Constantes.LOAD, pk});
    }

    @Override
    public void loadDataCompleted(boolean error) {
        if (!error) {
            FinCobrancaVO cobranca = (FinCobrancaVO) finCobrancaDetalhe.getForm1().getVOModel().getValueObject();
            pk = cobranca.getId().toString();

            finCobrancaDetalhe.getParcelaCobrancaController().setPk(pk);
            finCobrancaDetalhe.getGridControlParcelaCobranca().reloadData();
        }
    }

    @Override
    public Response insertRecord(ValueObject newPersistentObject) throws Exception {
        //Exercício: Salvar os detalhes da cobrança/acordo
        return ClientUtils.getData(acaoServidor, new Object[]{Constantes.INSERT, newPersistentObject});
    }

    @Override
    public void afterInsertData() {
        finCobrancaGrid.getGrid1().reloadData();
        JOptionPane.showMessageDialog(finCobrancaDetalhe, "Dados salvos com sucesso!", "Informacao do Sistema", JOptionPane.INFORMATION_MESSAGE);
    }

    @Override
    public Response updateRecord(ValueObject oldPersistentObject, ValueObject persistentObject) throws Exception {
        return ClientUtils.getData(acaoServidor, new Object[]{Constantes.UPDATE, oldPersistentObject, persistentObject});
    }

    @Override
    public void afterEditData() {
        finCobrancaGrid.getGrid1().reloadData();
        JOptionPane.showMessageDialog(finCobrancaDetalhe, "Dados alterados com sucesso!", "Informacao do Sistema", JOptionPane.INFORMATION_MESSAGE);
    }

    public void buscaParcelasVencidas() {
        FinCobrancaVO cobranca = (FinCobrancaVO) finCobrancaDetalhe.getForm1().getVOModel().getValueObject();
        if (cobranca.getCliente() == null || cobranca.getCliente().getId() == null) {
            JOptionPane.showMessageDialog(finCobrancaDetalhe, "Necessário selecionar um cliente!", "Informacao do Sistema", JOptionPane.INFORMATION_MESSAGE);
        } else {
            finCobrancaDetalhe.getParcelaVencidaController().setIdCliente(cobranca.getCliente().getId());
            finCobrancaDetalhe.getGridControlParcelaReceber().reloadData();
        }
    }

    public void calcularJurosMulta() {
        List<FinParcelaReceberVO> parcelasVencidas = finCobrancaDetalhe.getGridControlParcelaReceber().getVOListTableModel().getDataVector();

        if (parcelasVencidas.isEmpty()) {
            JOptionPane.showMessageDialog(finCobrancaDetalhe, "Nenhuma parcela vencida!", "Informacao do Sistema", JOptionPane.INFORMATION_MESSAGE);
        } else {
            BigDecimal juros = BigDecimal.ZERO;
            BigDecimal multa = BigDecimal.ZERO;
            BigDecimal total = BigDecimal.ZERO;
            BigDecimal totalAtraso = BigDecimal.ZERO;

            BigDecimal valorJurosParcela;
            BigDecimal valorMultaParcela;

            finCobrancaDetalhe.getGridControlParcelaCobranca().getVOListTableModel().clear();

            finCobrancaDetalhe.getGridControlParcelaCobranca().setMode(Consts.EDIT);
            for (FinParcelaReceberVO p : parcelasVencidas) {
                p = (FinParcelaReceberVO) Biblioteca.nullToEmpty(p, false);

                FinCobrancaParcelaReceberVO parcelaCobranca = new FinCobrancaParcelaReceberVO();
                parcelaCobranca.setIdFinLancamentoReceber(p.getFinLancamentoReceber().getId());
                parcelaCobranca.setIdFinParcelaReceber(p.getId());
                parcelaCobranca.setDataVencimento(p.getDataVencimento());
                parcelaCobranca.setValorParcela(p.getValor());

                valorJurosParcela = p.getValor().multiply(p.getTaxaJuro().divide(BigDecimal.valueOf(100)));
                valorMultaParcela = p.getValor().multiply(p.getTaxaMulta().divide(BigDecimal.valueOf(100)));

                parcelaCobranca.setValorJuroSimulado(valorJurosParcela);
                parcelaCobranca.setValorJuroAcordo(valorJurosParcela);
                parcelaCobranca.setValorMultaSimulado(valorMultaParcela);
                parcelaCobranca.setValorMultaAcordo(valorMultaParcela);
                parcelaCobranca.setValorReceberSimulado(p.getValor().add(valorJurosParcela).add(valorMultaParcela));
                parcelaCobranca.setValorReceberAcordo(parcelaCobranca.getValorReceberSimulado());

                totalAtraso = totalAtraso.add(p.getValor());
                total = total.add(parcelaCobranca.getValorReceberSimulado());
                juros = juros.add(parcelaCobranca.getValorJuroSimulado());
                multa = multa.add(parcelaCobranca.getValorMultaSimulado());

                finCobrancaDetalhe.getGridControlParcelaCobranca().getVOListTableModel().addObject(parcelaCobranca);
            }
            finCobrancaDetalhe.getGridControlParcelaCobranca().save();

            FinCobrancaVO cobranca = (FinCobrancaVO) finCobrancaDetalhe.getForm1().getVOModel().getValueObject();
            cobranca.setTotalReceberNaData(total);
            cobranca.setTotalJuros(juros);
            cobranca.setTotalMulta(multa);
            cobranca.setTotalAtrasado(totalAtraso);

            finCobrancaDetalhe.getForm1().pull();
        }
    }

    public void simulaValores() {
        BigDecimal juros = BigDecimal.ZERO;
        BigDecimal multa = BigDecimal.ZERO;
        BigDecimal total = BigDecimal.ZERO;

        List<FinCobrancaParcelaReceberVO> parcelas = finCobrancaDetalhe.getGridControlParcelaCobranca().getVOListTableModel().getDataVector();

        for (FinCobrancaParcelaReceberVO p : parcelas) {
            p = (FinCobrancaParcelaReceberVO) Biblioteca.nullToEmpty(p, false);

            total = total.add(p.getValorReceberSimulado());
            juros = juros.add(p.getValorJuroSimulado());
            multa = multa.add(p.getValorMultaSimulado());
        }

        FinCobrancaVO cobranca = (FinCobrancaVO) finCobrancaDetalhe.getForm1().getVOModel().getValueObject();
        cobranca.setTotalReceberNaData(total);
        cobranca.setTotalJuros(juros);
        cobranca.setTotalMulta(multa);

        finCobrancaDetalhe.getForm1().pull();
    }
}
