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
@Table(name = "COLABORADOR_RELACIONAMENTO")
public class ColaboradorRelacionamentoVO extends ValueObjectImpl implements Serializable {

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
    @Column(name = "CPF")
    private String cpf;
    @Column(name = "REGISTRO_MATRICULA")
    private String registroMatricula;
    @Column(name = "REGISTRO_CARTORIO")
    private String registroCartorio;
    @Column(name = "REGISTRO_CARTORIO_NUMERO")
    private String registroCartorioNumero;
    @Column(name = "REGISTRO_NUMERO_LIVRO")
    private String registroNumeroLivro;
    @Column(name = "REGISTRO_NUMERO_FOLHA")
    private String registroNumeroFolha;
    @Temporal(TemporalType.DATE)
    @Column(name = "DATA_ENTREGA_DOCUMENTO")
    private Date dataEntregaDocumento;
    @Column(name = "SALARIO_FAMILIA")
    private String salarioFamilia;
    @Column(name = "SALARIO_FAMILIA_IDADE_LIMITE")
    private Integer salarioFamiliaIdadeLimite;
    @Temporal(TemporalType.DATE)
    @Column(name = "SALARIO_FAMILIA_DATA_FIM")
    private Date salarioFamiliaDataFim;
    @Column(name = "IMPOSTO_RENDA_IDADE_LIMITE")
    private Integer impostoRendaIdadeLimite;
    @Column(name = "IMPOSTO_RENDA_DATA_FIM")
    private Integer impostoRendaDataFim;
    @JoinColumn(name = "ID_COLABORADOR", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private ColaboradorVO colaborador;
    @JoinColumn(name = "ID_TIPO_RELACIONAMENTO", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private TipoRelacionamentoVO tipoRelacionamento;

    public ColaboradorRelacionamentoVO() {
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

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getRegistroMatricula() {
        return registroMatricula;
    }

    public void setRegistroMatricula(String registroMatricula) {
        this.registroMatricula = registroMatricula;
    }

    public String getRegistroCartorio() {
        return registroCartorio;
    }

    public void setRegistroCartorio(String registroCartorio) {
        this.registroCartorio = registroCartorio;
    }

    public String getRegistroCartorioNumero() {
        return registroCartorioNumero;
    }

    public void setRegistroCartorioNumero(String registroCartorioNumero) {
        this.registroCartorioNumero = registroCartorioNumero;
    }

    public String getRegistroNumeroLivro() {
        return registroNumeroLivro;
    }

    public void setRegistroNumeroLivro(String registroNumeroLivro) {
        this.registroNumeroLivro = registroNumeroLivro;
    }

    public String getRegistroNumeroFolha() {
        return registroNumeroFolha;
    }

    public void setRegistroNumeroFolha(String registroNumeroFolha) {
        this.registroNumeroFolha = registroNumeroFolha;
    }

    public Date getDataEntregaDocumento() {
        return dataEntregaDocumento;
    }

    public void setDataEntregaDocumento(Date dataEntregaDocumento) {
        this.dataEntregaDocumento = dataEntregaDocumento;
    }

    public String getSalarioFamilia() {
        return salarioFamilia;
    }

    public void setSalarioFamilia(String salarioFamilia) {
        this.salarioFamilia = salarioFamilia;
    }

    public Integer getSalarioFamiliaIdadeLimite() {
        return salarioFamiliaIdadeLimite;
    }

    public void setSalarioFamiliaIdadeLimite(Integer salarioFamiliaIdadeLimite) {
        this.salarioFamiliaIdadeLimite = salarioFamiliaIdadeLimite;
    }

    public Date getSalarioFamiliaDataFim() {
        return salarioFamiliaDataFim;
    }

    public void setSalarioFamiliaDataFim(Date salarioFamiliaDataFim) {
        this.salarioFamiliaDataFim = salarioFamiliaDataFim;
    }

    public Integer getImpostoRendaIdadeLimite() {
        return impostoRendaIdadeLimite;
    }

    public void setImpostoRendaIdadeLimite(Integer impostoRendaIdadeLimite) {
        this.impostoRendaIdadeLimite = impostoRendaIdadeLimite;
    }

    public Integer getImpostoRendaDataFim() {
        return impostoRendaDataFim;
    }

    public void setImpostoRendaDataFim(Integer impostoRendaDataFim) {
        this.impostoRendaDataFim = impostoRendaDataFim;
    }

    public ColaboradorVO getColaborador() {
        return colaborador;
    }

    public void setColaborador(ColaboradorVO colaborador) {
        this.colaborador = colaborador;
    }

    public TipoRelacionamentoVO getTipoRelacionamento() {
        return tipoRelacionamento;
    }

    public void setTipoRelacionamento(TipoRelacionamentoVO tipoRelacionamento) {
        this.tipoRelacionamento = tipoRelacionamento;
    }


    @Override
    public String toString() {
        return "com.t2tierp.cadastros.java.ColaboradorRelacionamentoVO[id=" + id + "]";
    }

}
