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
package com.t2tierp.wms.java;

import com.t2tierp.cadastros.java.ProdutoVO;
import java.io.Serializable;
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
@Table(name = "WMS_RECEBIMENTO_DETALHE")
public class WmsRecebimentoDetalheVO extends ValueObjectImpl implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "QUANTIDADE_VOLUME")
    private Integer quantidadeVolume;
    @Column(name = "QUANTIDADE_ITEM_POR_VOLUME")
    private Integer quantidadeItemPorVolume;
    @Column(name = "QUANTIDADE_RECEBIDA")
    private Integer quantidadeRecebida;
    @Column(name = "DESTINO")
    private String destino;
    @JoinColumn(name = "ID_WMS_RECEBIMENTO_CABECALHO", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private WmsRecebimentoCabecalhoVO wmsRecebimentoCabecalho;
    @JoinColumn(name = "ID_PRODUTO", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private ProdutoVO produto;

    public WmsRecebimentoDetalheVO() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getQuantidadeVolume() {
        return quantidadeVolume;
    }

    public void setQuantidadeVolume(Integer quantidadeVolume) {
        this.quantidadeVolume = quantidadeVolume;
    }

    public Integer getQuantidadeItemPorVolume() {
        return quantidadeItemPorVolume;
    }

    public void setQuantidadeItemPorVolume(Integer quantidadeItemPorVolume) {
        this.quantidadeItemPorVolume = quantidadeItemPorVolume;
    }

    public Integer getQuantidadeRecebida() {
        return quantidadeRecebida;
    }

    public void setQuantidadeRecebida(Integer quantidadeRecebida) {
        this.quantidadeRecebida = quantidadeRecebida;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public WmsRecebimentoCabecalhoVO getWmsRecebimentoCabecalho() {
        return wmsRecebimentoCabecalho;
    }

    public void setWmsRecebimentoCabecalho(WmsRecebimentoCabecalhoVO wmsRecebimentoCabecalho) {
        this.wmsRecebimentoCabecalho = wmsRecebimentoCabecalho;
    }

    public ProdutoVO getProduto() {
        return produto;
    }

    public void setProduto(ProdutoVO produto) {
        this.produto = produto;
    }


    @Override
    public String toString() {
        return "com.t2tierp.wms.java.WmsRecebimentoDetalheVO[id=" + id + "]";
    }

}
