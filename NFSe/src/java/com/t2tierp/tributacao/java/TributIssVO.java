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
package com.t2tierp.tributacao.java;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.openswing.swing.message.receive.java.ValueObjectImpl;


@Entity
@Table(name = "TRIBUT_ISS")
public class TributIssVO extends ValueObjectImpl implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "MODALIDADE_BASE_CALCULO")
    private String modalidadeBaseCalculo;
    @Column(name = "PORCENTO_BASE_CALCULO")
    private BigDecimal porcentoBaseCalculo;
    @Column(name = "ALIQUOTA_PORCENTO")
    private BigDecimal aliquotaPorcento;
    @Column(name = "ALIQUOTA_UNIDADE")
    private BigDecimal aliquotaUnidade;
    @Column(name = "VALOR_PRECO_MAXIMO")
    private BigDecimal valorPrecoMaximo;
    @Column(name = "VALOR_PAUTA_FISCAL")
    private BigDecimal valorPautaFiscal;
    @Column(name = "ITEM_LISTA_SERVICO")
    private Integer itemListaServico;
    @Column(name = "CODIGO_TRIBUTACAO")
    private String codigoTributacao;
    @JoinColumn(name = "ID_TRIBUT_OPERACAO_FISCAL", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private TributOperacaoFiscalVO tributOperacaoFiscal;

    public TributIssVO() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getModalidadeBaseCalculo() {
        return modalidadeBaseCalculo;
    }

    public void setModalidadeBaseCalculo(String modalidadeBaseCalculo) {
        this.modalidadeBaseCalculo = modalidadeBaseCalculo;
    }

    public BigDecimal getPorcentoBaseCalculo() {
        return porcentoBaseCalculo;
    }

    public void setPorcentoBaseCalculo(BigDecimal porcentoBaseCalculo) {
        this.porcentoBaseCalculo = porcentoBaseCalculo;
    }

    public BigDecimal getAliquotaPorcento() {
        return aliquotaPorcento;
    }

    public void setAliquotaPorcento(BigDecimal aliquotaPorcento) {
        this.aliquotaPorcento = aliquotaPorcento;
    }

    public BigDecimal getAliquotaUnidade() {
        return aliquotaUnidade;
    }

    public void setAliquotaUnidade(BigDecimal aliquotaUnidade) {
        this.aliquotaUnidade = aliquotaUnidade;
    }

    public BigDecimal getValorPrecoMaximo() {
        return valorPrecoMaximo;
    }

    public void setValorPrecoMaximo(BigDecimal valorPrecoMaximo) {
        this.valorPrecoMaximo = valorPrecoMaximo;
    }

    public BigDecimal getValorPautaFiscal() {
        return valorPautaFiscal;
    }

    public void setValorPautaFiscal(BigDecimal valorPautaFiscal) {
        this.valorPautaFiscal = valorPautaFiscal;
    }

    public Integer getItemListaServico() {
        return itemListaServico;
    }

    public void setItemListaServico(Integer itemListaServico) {
        this.itemListaServico = itemListaServico;
    }

    public String getCodigoTributacao() {
        return codigoTributacao;
    }

    public void setCodigoTributacao(String codigoTributacao) {
        this.codigoTributacao = codigoTributacao;
    }

    public TributOperacaoFiscalVO getTributOperacaoFiscal() {
        return tributOperacaoFiscal;
    }

    public void setTributOperacaoFiscal(TributOperacaoFiscalVO tributOperacaoFiscal) {
        this.tributOperacaoFiscal = tributOperacaoFiscal;
    }


    @Override
    public String toString() {
        return "com.t2tierp.tributacao.java.TributIssVO[id=" + id + "]";
    }

}
