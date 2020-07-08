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
package com.t2tierp.cadastros.java;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.openswing.swing.message.receive.java.ValueObjectImpl;


@Entity
@Table(name = "SOCIO_DEPENDENTE")
public class SocioDependenteVO extends ValueObjectImpl implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "NOME")
    private String nome;
    @Temporal(TemporalType.DATE)
    @Column(name = "DATA_NASCIMENTO")
    private Date dataNascimento;
    @Temporal(TemporalType.DATE)
    @Column(name = "DATA_INICIO_DEPEDENCIA")
    private Date dataInicioDepedencia;
    @Temporal(TemporalType.DATE)
    @Column(name = "DATA_FIM_DEPENDENCIA")
    private Date dataFimDependencia;
    @Column(name = "CPF")
    private String cpf;
    @JoinColumn(name = "ID_TIPO_RELACIONAMENTO", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private TipoRelacionamentoVO tipoRelacionamento;
    @JoinColumn(name = "ID_SOCIO", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private SocioVO socio;

    public SocioDependenteVO() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public Date getDataInicioDepedencia() {
        return dataInicioDepedencia;
    }

    public void setDataInicioDepedencia(Date dataInicioDepedencia) {
        this.dataInicioDepedencia = dataInicioDepedencia;
    }

    public Date getDataFimDependencia() {
        return dataFimDependencia;
    }

    public void setDataFimDependencia(Date dataFimDependencia) {
        this.dataFimDependencia = dataFimDependencia;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public TipoRelacionamentoVO getTipoRelacionamento() {
        return tipoRelacionamento;
    }

    public void setTipoRelacionamento(TipoRelacionamentoVO tipoRelacionamento) {
        this.tipoRelacionamento = tipoRelacionamento;
    }

    public SocioVO getSocio() {
        return socio;
    }

    public void setSocio(SocioVO socio) {
        this.socio = socio;
    }


    @Override
    public String toString() {
        return "com.t2tierp.cadastros.java.SocioDependenteVO[id=" + id + "]";
    }

}
