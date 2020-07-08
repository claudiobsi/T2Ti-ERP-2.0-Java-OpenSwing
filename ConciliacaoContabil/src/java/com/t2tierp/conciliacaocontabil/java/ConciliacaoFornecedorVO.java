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
package com.t2tierp.conciliacaocontabil.java;

import com.t2tierp.contabilidade.java.ContabilContaVO;
import com.t2tierp.contabilidade.java.ContabilHistoricoVO;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import org.openswing.swing.message.receive.java.ValueObjectImpl;

public class ConciliacaoFornecedorVO extends ValueObjectImpl implements Serializable{

    private static long serialVersionUID = 1L;

    private Date parcelaDataPagamento;
    private BigDecimal parcelaValorJuro;
    private BigDecimal parcelaValorMulta;
    private BigDecimal parcelaValorDesconto;
    private BigDecimal parcelaValorPago;
    
    private String lancamentoHistorico;
    private BigDecimal lancamentoValor;
    private String lancamentoTipo;
    private ContabilHistoricoVO lancamentoContabilHistorico;
    private ContabilContaVO lancamentoContabilConta;

    /**
     * @return the parcelaDataPagamento
     */
    public Date getParcelaDataPagamento() {
        return parcelaDataPagamento;
    }

    /**
     * @param parcelaDataPagamento the parcelaDataPagamento to set
     */
    public void setParcelaDataPagamento(Date parcelaDataPagamento) {
        this.parcelaDataPagamento = parcelaDataPagamento;
    }

    /**
     * @return the parcelaValorJuro
     */
    public BigDecimal getParcelaValorJuro() {
        return parcelaValorJuro;
    }

    /**
     * @param parcelaValorJuro the parcelaValorJuro to set
     */
    public void setParcelaValorJuro(BigDecimal parcelaValorJuro) {
        this.parcelaValorJuro = parcelaValorJuro;
    }

    /**
     * @return the parcelaValorMulta
     */
    public BigDecimal getParcelaValorMulta() {
        return parcelaValorMulta;
    }

    /**
     * @param parcelaValorMulta the parcelaValorMulta to set
     */
    public void setParcelaValorMulta(BigDecimal parcelaValorMulta) {
        this.parcelaValorMulta = parcelaValorMulta;
    }

    /**
     * @return the parcelaValorDesconto
     */
    public BigDecimal getParcelaValorDesconto() {
        return parcelaValorDesconto;
    }

    /**
     * @param parcelaValorDesconto the parcelaValorDesconto to set
     */
    public void setParcelaValorDesconto(BigDecimal parcelaValorDesconto) {
        this.parcelaValorDesconto = parcelaValorDesconto;
    }

    /**
     * @return the parcelaValorPago
     */
    public BigDecimal getParcelaValorPago() {
        return parcelaValorPago;
    }

    /**
     * @param parcelaValorPago the parcelaValorPago to set
     */
    public void setParcelaValorPago(BigDecimal parcelaValorPago) {
        this.parcelaValorPago = parcelaValorPago;
    }

    /**
     * @return the lancamentoHistorico
     */
    public String getLancamentoHistorico() {
        return lancamentoHistorico;
    }

    /**
     * @param lancamentoHistorico the lancamentoHistorico to set
     */
    public void setLancamentoHistorico(String lancamentoHistorico) {
        this.lancamentoHistorico = lancamentoHistorico;
    }

    /**
     * @return the lancamentoValor
     */
    public BigDecimal getLancamentoValor() {
        return lancamentoValor;
    }

    /**
     * @param lancamentoValor the lancamentoValor to set
     */
    public void setLancamentoValor(BigDecimal lancamentoValor) {
        this.lancamentoValor = lancamentoValor;
    }

    /**
     * @return the lancamentoTipo
     */
    public String getLancamentoTipo() {
        return lancamentoTipo;
    }

    /**
     * @param lancamentoTipo the lancamentoTipo to set
     */
    public void setLancamentoTipo(String lancamentoTipo) {
        this.lancamentoTipo = lancamentoTipo;
    }

    /**
     * @return the lancamentoContabilHistorico
     */
    public ContabilHistoricoVO getLancamentoContabilHistorico() {
        return lancamentoContabilHistorico;
    }

    /**
     * @param lancamentoContabilHistorico the lancamentoContabilHistorico to set
     */
    public void setLancamentoContabilHistorico(ContabilHistoricoVO lancamentoContabilHistorico) {
        this.lancamentoContabilHistorico = lancamentoContabilHistorico;
    }

    /**
     * @return the lancamentoContabilConta
     */
    public ContabilContaVO getLancamentoContabilConta() {
        return lancamentoContabilConta;
    }

    /**
     * @param lancamentoContabilConta the lancamentoContabilConta to set
     */
    public void setLancamentoContabilConta(ContabilContaVO lancamentoContabilConta) {
        this.lancamentoContabilConta = lancamentoContabilConta;
    }


}
