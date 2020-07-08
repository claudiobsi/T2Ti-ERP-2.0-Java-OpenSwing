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
@Table(name = "ETIQUETA_TEMPLATE")
public class EtiquetaTemplateVO extends ValueObjectImpl implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "TABELA")
    private String tabela;
    @Column(name = "CAMPO")
    private String campo;
    @Column(name = "FORMATO")
    private Integer formato;
    @Column(name = "QUANTIDADE_REPETICOES")
    private Integer quantidadeRepeticoes;
    @Column(name = "FILTRO")
    private String filtro;
    @JoinColumn(name = "ID_ETIQUETA_LAYOUT", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private EtiquetaLayoutVO etiquetaLayout;

    public EtiquetaTemplateVO() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTabela() {
        return tabela;
    }

    public void setTabela(String tabela) {
        this.tabela = tabela;
    }

    public String getCampo() {
        return campo;
    }

    public void setCampo(String campo) {
        this.campo = campo;
    }

    public Integer getFormato() {
        return formato;
    }

    public void setFormato(Integer formato) {
        this.formato = formato;
    }

    public Integer getQuantidadeRepeticoes() {
        return quantidadeRepeticoes;
    }

    public void setQuantidadeRepeticoes(Integer quantidadeRepeticoes) {
        this.quantidadeRepeticoes = quantidadeRepeticoes;
    }

    public String getFiltro() {
        return filtro;
    }

    public void setFiltro(String filtro) {
        this.filtro = filtro;
    }

    public EtiquetaLayoutVO getEtiquetaLayout() {
        return etiquetaLayout;
    }

    public void setEtiquetaLayout(EtiquetaLayoutVO etiquetaLayout) {
        this.etiquetaLayout = etiquetaLayout;
    }


    @Override
    public String toString() {
        return "com.t2tierp.geradoretiqueta.java.EtiquetaTemplateVO[id=" + id + "]";
    }

}
