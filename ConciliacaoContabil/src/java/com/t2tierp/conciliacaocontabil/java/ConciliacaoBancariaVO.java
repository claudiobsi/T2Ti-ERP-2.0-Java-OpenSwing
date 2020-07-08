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

import com.t2tierp.cadastros.java.ContaCaixaVO;
import com.t2tierp.contabilidade.java.ContabilContaVO;
import com.t2tierp.contabilidade.java.ContabilHistoricoVO;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import org.openswing.swing.message.receive.java.ValueObjectImpl;

public class ConciliacaoBancariaVO extends ValueObjectImpl implements Serializable{

    private static final long serialVersionUID = 1L;
    private String extratoMes;
    private String extratoAno;
    private Date extratoDataMovimento;
    private Date extratoDataBalancete;
    private String extratoHistorico;
    private BigDecimal extratoValor;
    private ContaCaixaVO extratoContaCaixa;

    private String lancamentoHistorico;
    private BigDecimal lancamentoValor;
    private String lancamentoTipo;
    private ContabilHistoricoVO lancamentoContabilHistorico;
    private ContabilContaVO lancamentoContabilConta;

    /**
     * @return the extratoMes
     */
    public String getExtratoMes() {
        return extratoMes;
    }

    /**
     * @param extratoMes the extratoMes to set
     */
    public void setExtratoMes(String extratoMes) {
        this.extratoMes = extratoMes;
    }

    /**
     * @return the extratoAno
     */
    public String getExtratoAno() {
        return extratoAno;
    }

    /**
     * @param extratoAno the extratoAno to set
     */
    public void setExtratoAno(String extratoAno) {
        this.extratoAno = extratoAno;
    }

    /**
     * @return the extratoDataMovimento
     */
    public Date getExtratoDataMovimento() {
        return extratoDataMovimento;
    }

    /**
     * @param extratoDataMovimento the extratoDataMovimento to set
     */
    public void setExtratoDataMovimento(Date extratoDataMovimento) {
        this.extratoDataMovimento = extratoDataMovimento;
    }

    /**
     * @return the extratoDataBalancete
     */
    public Date getExtratoDataBalancete() {
        return extratoDataBalancete;
    }

    /**
     * @param extratoDataBalancete the extratoDataBalancete to set
     */
    public void setExtratoDataBalancete(Date extratoDataBalancete) {
        this.extratoDataBalancete = extratoDataBalancete;
    }

    /**
     * @return the extratoHistorico
     */
    public String getExtratoHistorico() {
        return extratoHistorico;
    }

    /**
     * @param extratoHistorico the extratoHistorico to set
     */
    public void setExtratoHistorico(String extratoHistorico) {
        this.extratoHistorico = extratoHistorico;
    }

    /**
     * @return the extratoValor
     */
    public BigDecimal getExtratoValor() {
        return extratoValor;
    }

    /**
     * @param extratoValor the extratoValor to set
     */
    public void setExtratoValor(BigDecimal extratoValor) {
        this.extratoValor = extratoValor;
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

    /**
     * @return the extratoContaCaixa
     */
    public ContaCaixaVO getExtratoContaCaixa() {
        return extratoContaCaixa;
    }

    /**
     * @param extratoContaCaixa the extratoContaCaixa to set
     */
    public void setExtratoContaCaixa(ContaCaixaVO extratoContaCaixa) {
        this.extratoContaCaixa = extratoContaCaixa;
    }

}
