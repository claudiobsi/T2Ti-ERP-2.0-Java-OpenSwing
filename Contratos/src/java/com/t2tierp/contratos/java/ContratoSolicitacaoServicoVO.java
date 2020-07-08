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
package com.t2tierp.contratos.java;

import com.t2tierp.cadastros.java.ClienteVO;
import com.t2tierp.cadastros.java.ColaboradorVO;
import com.t2tierp.cadastros.java.FornecedorVO;
import com.t2tierp.cadastros.java.SetorVO;
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
@Table(name = "CONTRATO_SOLICITACAO_SERVICO")
public class ContratoSolicitacaoServicoVO extends ValueObjectImpl implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Temporal(TemporalType.DATE)
    @Column(name = "DATA_SOLICITACAO")
    private Date dataSolicitacao;
    @Temporal(TemporalType.DATE)
    @Column(name = "DATA_DESEJADA_INICIO")
    private Date dataDesejadaInicio;
    @Column(name = "URGENTE")
    private String urgente;
    @Column(name = "STATUS_SOLICITACAO")
    private String statusSolicitacao;
    @Column(name = "DESCRICAO")
    private String descricao;
    @JoinColumn(name = "ID_CONTRATO_TIPO_SERVICO", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private ContratoTipoServicoVO contratoTipoServico;
    @JoinColumn(name = "ID_COLABORADOR", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private ColaboradorVO colaborador;
    @JoinColumn(name = "ID_SETOR", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private SetorVO setor;
    @JoinColumn(name = "ID_CLIENTE", referencedColumnName = "ID")
    @ManyToOne
    private ClienteVO cliente;
    @JoinColumn(name = "ID_FORNECEDOR", referencedColumnName = "ID")
    @ManyToOne
    private FornecedorVO fornecedor;

    public ContratoSolicitacaoServicoVO() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDataSolicitacao() {
        return dataSolicitacao;
    }

    public void setDataSolicitacao(Date dataSolicitacao) {
        this.dataSolicitacao = dataSolicitacao;
    }

    public Date getDataDesejadaInicio() {
        return dataDesejadaInicio;
    }

    public void setDataDesejadaInicio(Date dataDesejadaInicio) {
        this.dataDesejadaInicio = dataDesejadaInicio;
    }

    public String getUrgente() {
        return urgente;
    }

    public void setUrgente(String urgente) {
        this.urgente = urgente;
    }

    public String getStatusSolicitacao() {
        return statusSolicitacao;
    }

    public void setStatusSolicitacao(String statusSolicitacao) {
        this.statusSolicitacao = statusSolicitacao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public ContratoTipoServicoVO getContratoTipoServico() {
        return contratoTipoServico;
    }

    public void setContratoTipoServico(ContratoTipoServicoVO contratoTipoServico) {
        this.contratoTipoServico = contratoTipoServico;
    }

    public ColaboradorVO getColaborador() {
        return colaborador;
    }

    public void setColaborador(ColaboradorVO colaborador) {
        this.colaborador = colaborador;
    }

    public SetorVO getSetor() {
        return setor;
    }

    public void setSetor(SetorVO setor) {
        this.setor = setor;
    }

    public ClienteVO getCliente() {
        return cliente;
    }

    public void setCliente(ClienteVO cliente) {
        this.cliente = cliente;
    }

    public FornecedorVO getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(FornecedorVO fornecedor) {
        this.fornecedor = fornecedor;
    }


    @Override
    public String toString() {
        return "com.t2tierp.contratos.java.ContratoSolicitacaoServicoVO[id=" + id + "]";
    }

}
