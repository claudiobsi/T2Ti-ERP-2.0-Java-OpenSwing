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
package com.t2tierp.pcp.java;

import com.t2tierp.cadastros.java.ProdutoVO;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.openswing.swing.message.receive.java.ValueObjectImpl;


@Entity
@Table(name = "PCP_OP_DETALHE")
public class PcpOpDetalheVO extends ValueObjectImpl implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "QUANTIDADE_PRODUZIR")
    private BigDecimal quantidadeProduzir;
    @Column(name = "QUANTIDADE_PRODUZIDA")
    private BigDecimal quantidadeProduzida;
    @Column(name = "QUANTIDADE_ENTREGUE")
    private BigDecimal quantidadeEntregue;
    @Column(name = "CUSTO_PREVISTO")
    private BigDecimal custoPrevisto;
    @Column(name = "CUSTO_REALIZADO")
    private BigDecimal custoRealizado;
    @JoinColumn(name = "ID_PCP_OP_CABECALHO", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private PcpOpCabecalhoVO pcpOpCabecalho;
    @JoinColumn(name = "ID_PRODUTO", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private ProdutoVO produto;
    @OneToMany(mappedBy="pcpOpDetalhe", cascade = CascadeType.ALL, orphanRemoval = true)
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<PcpServicoVO> listaPcpServico;

    public PcpOpDetalheVO() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigDecimal getQuantidadeProduzir() {
        return quantidadeProduzir;
    }

    public void setQuantidadeProduzir(BigDecimal quantidadeProduzir) {
        this.quantidadeProduzir = quantidadeProduzir;
    }

    public BigDecimal getQuantidadeProduzida() {
        return quantidadeProduzida;
    }

    public void setQuantidadeProduzida(BigDecimal quantidadeProduzida) {
        this.quantidadeProduzida = quantidadeProduzida;
    }

    public BigDecimal getQuantidadeEntregue() {
        return quantidadeEntregue;
    }

    public void setQuantidadeEntregue(BigDecimal quantidadeEntregue) {
        this.quantidadeEntregue = quantidadeEntregue;
    }

    public BigDecimal getCustoPrevisto() {
        return custoPrevisto;
    }

    public void setCustoPrevisto(BigDecimal custoPrevisto) {
        this.custoPrevisto = custoPrevisto;
    }

    public BigDecimal getCustoRealizado() {
        return custoRealizado;
    }

    public void setCustoRealizado(BigDecimal custoRealizado) {
        this.custoRealizado = custoRealizado;
    }

    public PcpOpCabecalhoVO getPcpOpCabecalho() {
        return pcpOpCabecalho;
    }

    public void setPcpOpCabecalho(PcpOpCabecalhoVO pcpOpCabecalho) {
        this.pcpOpCabecalho = pcpOpCabecalho;
    }

    public ProdutoVO getProduto() {
        return produto;
    }

    public void setProduto(ProdutoVO produto) {
        this.produto = produto;
    }


    @Override
    public String toString() {
        return "com.t2tierp.pcp.java.PcpOpDetalheVO[id=" + id + "]";
    }

    public List<PcpServicoVO> getListaPcpServico() {
        return listaPcpServico;
    }

    public void setListaPcpServico(List<PcpServicoVO> listaPcpServico) {
        this.listaPcpServico = listaPcpServico;
    }

}
