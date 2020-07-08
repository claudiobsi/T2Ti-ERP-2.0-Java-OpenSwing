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
package com.t2tierp.geradoretiqueta.java;

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
@Table(name = "ETIQUETA_LAYOUT")
public class EtiquetaLayoutVO extends ValueObjectImpl implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "CODIGO_FABRICANTE")
    private String codigoFabricante;
    @Column(name = "QUANTIDADE")
    private Integer quantidade;
    @Column(name = "QUANTIDADE_HORIZONTAL")
    private Integer quantidadeHorizontal;
    @Column(name = "QUANTIDADE_VERTICAL")
    private Integer quantidadeVertical;
    @Column(name = "MARGEM_SUPERIOR")
    private Integer margemSuperior;
    @Column(name = "MARGEM_INFERIOR")
    private Integer margemInferior;
    @Column(name = "MARGEM_ESQUERDA")
    private Integer margemEsquerda;
    @Column(name = "MARGEM_DIREITA")
    private Integer margemDireita;
    @Column(name = "ESPACAMENTO_HORIZONTAL")
    private Integer espacamentoHorizontal;
    @Column(name = "ESPACAMENTO_VERTICAL")
    private Integer espacamentoVertical;
    @JoinColumn(name = "ID_FORMATO_PAPEL", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private EtiquetaFormatoPapelVO etiquetaFormatoPapel;

    public EtiquetaLayoutVO() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCodigoFabricante() {
        return codigoFabricante;
    }

    public void setCodigoFabricante(String codigoFabricante) {
        this.codigoFabricante = codigoFabricante;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public Integer getQuantidadeHorizontal() {
        return quantidadeHorizontal;
    }

    public void setQuantidadeHorizontal(Integer quantidadeHorizontal) {
        this.quantidadeHorizontal = quantidadeHorizontal;
    }

    public Integer getQuantidadeVertical() {
        return quantidadeVertical;
    }

    public void setQuantidadeVertical(Integer quantidadeVertical) {
        this.quantidadeVertical = quantidadeVertical;
    }

    public Integer getMargemSuperior() {
        return margemSuperior;
    }

    public void setMargemSuperior(Integer margemSuperior) {
        this.margemSuperior = margemSuperior;
    }

    public Integer getMargemInferior() {
        return margemInferior;
    }

    public void setMargemInferior(Integer margemInferior) {
        this.margemInferior = margemInferior;
    }

    public Integer getMargemEsquerda() {
        return margemEsquerda;
    }

    public void setMargemEsquerda(Integer margemEsquerda) {
        this.margemEsquerda = margemEsquerda;
    }

    public Integer getMargemDireita() {
        return margemDireita;
    }

    public void setMargemDireita(Integer margemDireita) {
        this.margemDireita = margemDireita;
    }

    public Integer getEspacamentoHorizontal() {
        return espacamentoHorizontal;
    }

    public void setEspacamentoHorizontal(Integer espacamentoHorizontal) {
        this.espacamentoHorizontal = espacamentoHorizontal;
    }

    public Integer getEspacamentoVertical() {
        return espacamentoVertical;
    }

    public void setEspacamentoVertical(Integer espacamentoVertical) {
        this.espacamentoVertical = espacamentoVertical;
    }

    public EtiquetaFormatoPapelVO getEtiquetaFormatoPapel() {
        return etiquetaFormatoPapel;
    }

    public void setEtiquetaFormatoPapel(EtiquetaFormatoPapelVO etiquetaFormatoPapel) {
        this.etiquetaFormatoPapel = etiquetaFormatoPapel;
    }


    @Override
    public String toString() {
        return "com.t2tierp.geradoretiqueta.java.EtiquetaLayoutVO[id=" + id + "]";
    }

}
