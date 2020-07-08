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
package com.t2tierp.cte.java;

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
@Table(name = "CTE_PERIGOSO")
public class CtePerigosoVO extends ValueObjectImpl implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "NUMERO_ONU")
    private String numeroOnu;
    @Column(name = "NOME_APROPRIADO")
    private String nomeApropriado;
    @Column(name = "CLASSE_RISCO")
    private String classeRisco;
    @Column(name = "GRUPO_EMBALAGEM")
    private String grupoEmbalagem;
    @Column(name = "QUANTIDADE_TOTAL_PRODUTO")
    private String quantidadeTotalProduto;
    @Column(name = "QUANTIDADE_TIPO_VOLUME")
    private String quantidadeTipoVolume;
    @Column(name = "PONTO_FULGOR")
    private String pontoFulgor;
    @JoinColumn(name = "ID_CTE_CABECALHO", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private CteCabecalhoVO cteCabecalho;

    public CtePerigosoVO() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNumeroOnu() {
        return numeroOnu;
    }

    public void setNumeroOnu(String numeroOnu) {
        this.numeroOnu = numeroOnu;
    }

    public String getNomeApropriado() {
        return nomeApropriado;
    }

    public void setNomeApropriado(String nomeApropriado) {
        this.nomeApropriado = nomeApropriado;
    }

    public String getClasseRisco() {
        return classeRisco;
    }

    public void setClasseRisco(String classeRisco) {
        this.classeRisco = classeRisco;
    }

    public String getGrupoEmbalagem() {
        return grupoEmbalagem;
    }

    public void setGrupoEmbalagem(String grupoEmbalagem) {
        this.grupoEmbalagem = grupoEmbalagem;
    }

    public String getQuantidadeTotalProduto() {
        return quantidadeTotalProduto;
    }

    public void setQuantidadeTotalProduto(String quantidadeTotalProduto) {
        this.quantidadeTotalProduto = quantidadeTotalProduto;
    }

    public String getQuantidadeTipoVolume() {
        return quantidadeTipoVolume;
    }

    public void setQuantidadeTipoVolume(String quantidadeTipoVolume) {
        this.quantidadeTipoVolume = quantidadeTipoVolume;
    }

    public String getPontoFulgor() {
        return pontoFulgor;
    }

    public void setPontoFulgor(String pontoFulgor) {
        this.pontoFulgor = pontoFulgor;
    }

    public CteCabecalhoVO getCteCabecalho() {
        return cteCabecalho;
    }

    public void setCteCabecalho(CteCabecalhoVO cteCabecalho) {
        this.cteCabecalho = cteCabecalho;
    }


    @Override
    public String toString() {
        return "com.t2tierp.cte.java.CtePerigosoVO[id=" + id + "]";
    }

}
