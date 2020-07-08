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
package com.t2tierp.folhapagamento.java;

import com.t2tierp.cadastros.java.EmpresaVO;
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
@Table(name = "FOLHA_EVENTO")
public class FolhaEventoVO extends ValueObjectImpl implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "CODIGO")
    private String codigo;
    @Column(name = "NOME")
    private String nome;
    @Column(name = "DESCRICAO")
    private String descricao;
    @Column(name = "TIPO")
    private String tipo;
    @Column(name = "UNIDADE")
    private String unidade;
    @Column(name = "BASE_CALCULO")
    private String baseCalculo;
    @Column(name = "TAXA")
    private BigDecimal taxa;
    @Column(name = "RUBRICA_ESOCIAL")
    private String rubricaEsocial;
    @Column(name = "COD_INCIDENCIA_PREVIDENCIA")
    private String codIncidenciaPrevidencia;
    @Column(name = "COD_INCIDENCIA_IRRF")
    private String codIncidenciaIrrf;
    @Column(name = "COD_INCIDENCIA_FGTS")
    private String codIncidenciaFgts;
    @Column(name = "COD_INCIDENCIA_SINDICATO")
    private String codIncidenciaSindicato;
    @Column(name = "REPERCUTE_DSR")
    private String repercuteDsr;
    @Column(name = "REPERCUTE_13")
    private String repercute13;
    @Column(name = "REPERCUTE_FERIAS")
    private String repercuteFerias;
    @Column(name = "REPERCUTE_AVISO")
    private String repercuteAviso;
    @JoinColumn(name = "ID_EMPRESA", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private EmpresaVO empresa;

    public FolhaEventoVO() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getUnidade() {
        return unidade;
    }

    public void setUnidade(String unidade) {
        this.unidade = unidade;
    }

    public String getBaseCalculo() {
        return baseCalculo;
    }

    public void setBaseCalculo(String baseCalculo) {
        this.baseCalculo = baseCalculo;
    }

    public BigDecimal getTaxa() {
        return taxa;
    }

    public void setTaxa(BigDecimal taxa) {
        this.taxa = taxa;
    }

    public String getRubricaEsocial() {
        return rubricaEsocial;
    }

    public void setRubricaEsocial(String rubricaEsocial) {
        this.rubricaEsocial = rubricaEsocial;
    }

    public String getCodIncidenciaPrevidencia() {
        return codIncidenciaPrevidencia;
    }

    public void setCodIncidenciaPrevidencia(String codIncidenciaPrevidencia) {
        this.codIncidenciaPrevidencia = codIncidenciaPrevidencia;
    }

    public String getCodIncidenciaIrrf() {
        return codIncidenciaIrrf;
    }

    public void setCodIncidenciaIrrf(String codIncidenciaIrrf) {
        this.codIncidenciaIrrf = codIncidenciaIrrf;
    }

    public String getCodIncidenciaFgts() {
        return codIncidenciaFgts;
    }

    public void setCodIncidenciaFgts(String codIncidenciaFgts) {
        this.codIncidenciaFgts = codIncidenciaFgts;
    }

    public String getCodIncidenciaSindicato() {
        return codIncidenciaSindicato;
    }

    public void setCodIncidenciaSindicato(String codIncidenciaSindicato) {
        this.codIncidenciaSindicato = codIncidenciaSindicato;
    }

    public String getRepercuteDsr() {
        return repercuteDsr;
    }

    public void setRepercuteDsr(String repercuteDsr) {
        this.repercuteDsr = repercuteDsr;
    }

    public String getRepercute13() {
        return repercute13;
    }

    public void setRepercute13(String repercute13) {
        this.repercute13 = repercute13;
    }

    public String getRepercuteFerias() {
        return repercuteFerias;
    }

    public void setRepercuteFerias(String repercuteFerias) {
        this.repercuteFerias = repercuteFerias;
    }

    public String getRepercuteAviso() {
        return repercuteAviso;
    }

    public void setRepercuteAviso(String repercuteAviso) {
        this.repercuteAviso = repercuteAviso;
    }

    public EmpresaVO getEmpresa() {
        return empresa;
    }

    public void setEmpresa(EmpresaVO empresa) {
        this.empresa = empresa;
    }


    @Override
    public String toString() {
        return "com.t2tierp.folhapagamento.java.FolhaEventoVO[id=" + id + "]";
    }

}
