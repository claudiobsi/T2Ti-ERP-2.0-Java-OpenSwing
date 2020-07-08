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
@Table(name = "CTE_RODOVIARIO_VEICULO")
public class CteRodoviarioVeiculoVO extends ValueObjectImpl implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "CODIGO_INTERNO")
    private String codigoInterno;
    @Column(name = "RENAVAM")
    private String renavam;
    @Column(name = "PLACA")
    private String placa;
    @Column(name = "TARA")
    private Integer tara;
    @Column(name = "CAPACIDADE_KG")
    private Integer capacidadeKg;
    @Column(name = "CAPACIDADE_M3")
    private Integer capacidadeM3;
    @Column(name = "TIPO_PROPRIEDADE")
    private String tipoPropriedade;
    @Column(name = "TIPO_VEICULO")
    private Integer tipoVeiculo;
    @Column(name = "TIPO_RODADO")
    private String tipoRodado;
    @Column(name = "TIPO_CARROCERIA")
    private String tipoCarroceria;
    @Column(name = "UF")
    private String uf;
    @Column(name = "PROPRIETARIO_CPF")
    private String proprietarioCpf;
    @Column(name = "PROPRIETARIO_CNPJ")
    private String proprietarioCnpj;
    @Column(name = "PROPRIETARIO_RNTRC")
    private String proprietarioRntrc;
    @Column(name = "PROPRIETARIO_NOME")
    private String proprietarioNome;
    @Column(name = "PROPRIETARIO_IE")
    private String proprietarioIe;
    @Column(name = "PROPRIETARIO_UF")
    private String proprietarioUf;
    @Column(name = "PROPRIETARIO_TIPO")
    private Integer proprietarioTipo;
    @JoinColumn(name = "ID_CTE_RODOVIARIO", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private CteRodoviarioVO cteRodoviario;

    public CteRodoviarioVeiculoVO() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCodigoInterno() {
        return codigoInterno;
    }

    public void setCodigoInterno(String codigoInterno) {
        this.codigoInterno = codigoInterno;
    }

    public String getRenavam() {
        return renavam;
    }

    public void setRenavam(String renavam) {
        this.renavam = renavam;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public Integer getTara() {
        return tara;
    }

    public void setTara(Integer tara) {
        this.tara = tara;
    }

    public Integer getCapacidadeKg() {
        return capacidadeKg;
    }

    public void setCapacidadeKg(Integer capacidadeKg) {
        this.capacidadeKg = capacidadeKg;
    }

    public Integer getCapacidadeM3() {
        return capacidadeM3;
    }

    public void setCapacidadeM3(Integer capacidadeM3) {
        this.capacidadeM3 = capacidadeM3;
    }

    public String getTipoPropriedade() {
        return tipoPropriedade;
    }

    public void setTipoPropriedade(String tipoPropriedade) {
        this.tipoPropriedade = tipoPropriedade;
    }

    public Integer getTipoVeiculo() {
        return tipoVeiculo;
    }

    public void setTipoVeiculo(Integer tipoVeiculo) {
        this.tipoVeiculo = tipoVeiculo;
    }

    public String getTipoRodado() {
        return tipoRodado;
    }

    public void setTipoRodado(String tipoRodado) {
        this.tipoRodado = tipoRodado;
    }

    public String getTipoCarroceria() {
        return tipoCarroceria;
    }

    public void setTipoCarroceria(String tipoCarroceria) {
        this.tipoCarroceria = tipoCarroceria;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public String getProprietarioCpf() {
        return proprietarioCpf;
    }

    public void setProprietarioCpf(String proprietarioCpf) {
        this.proprietarioCpf = proprietarioCpf;
    }

    public String getProprietarioCnpj() {
        return proprietarioCnpj;
    }

    public void setProprietarioCnpj(String proprietarioCnpj) {
        this.proprietarioCnpj = proprietarioCnpj;
    }

    public String getProprietarioRntrc() {
        return proprietarioRntrc;
    }

    public void setProprietarioRntrc(String proprietarioRntrc) {
        this.proprietarioRntrc = proprietarioRntrc;
    }

    public String getProprietarioNome() {
        return proprietarioNome;
    }

    public void setProprietarioNome(String proprietarioNome) {
        this.proprietarioNome = proprietarioNome;
    }

    public String getProprietarioIe() {
        return proprietarioIe;
    }

    public void setProprietarioIe(String proprietarioIe) {
        this.proprietarioIe = proprietarioIe;
    }

    public String getProprietarioUf() {
        return proprietarioUf;
    }

    public void setProprietarioUf(String proprietarioUf) {
        this.proprietarioUf = proprietarioUf;
    }

    public Integer getProprietarioTipo() {
        return proprietarioTipo;
    }

    public void setProprietarioTipo(Integer proprietarioTipo) {
        this.proprietarioTipo = proprietarioTipo;
    }

    public CteRodoviarioVO getCteRodoviario() {
        return cteRodoviario;
    }

    public void setCteRodoviario(CteRodoviarioVO cteRodoviario) {
        this.cteRodoviario = cteRodoviario;
    }


    @Override
    public String toString() {
        return "com.t2tierp.cte.java.CteRodoviarioVeiculoVO[id=" + id + "]";
    }

}
