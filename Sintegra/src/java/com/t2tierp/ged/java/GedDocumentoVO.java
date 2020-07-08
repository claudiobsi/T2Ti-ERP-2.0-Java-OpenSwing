package com.t2tierp.ged.java;

import com.t2tierp.cadastros.java.EmpresaVO;
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
import javax.persistence.Transient;
import org.openswing.swing.message.receive.java.ValueObjectImpl;


/**
* <p>Title: T2Ti ERP
* <p>Description:  VO relacionado a tabela [GED_DOCUMENTO]
*
* <p>The MIT License
*
* <p>Copyright: Copyright (C) 2010 T2Ti.COM
*
* Permission is hereby granted, free of charge, to any person
* obtaining a copy of this software and associated documentation
* files (the "Software"), to deal in the Software without
* restriction, including without limitation the rights to use,
* copy, modify, merge, publish, distribute, sublicense, and/or sell
* copies of the Software, and to permit persons to whom the
* Software is furnished to do so, subject to the following
* conditions:
*
* The above copyright notice and this permission notice shall be
* included in all copies or substantial portions of the Software.
*
* THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
* EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES
* OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
* NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT
* HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY,
* WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
* FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR
* OTHER DEALINGS IN THE SOFTWARE.
*
*        The author may be contacted at:
*            t2ti.com@gmail.com</p>
*
* @author Claudio de Barros (t2ti.com@gmail.com)
* @version 1.0
*/
@Entity
@Table(name = "GED_DOCUMENTO")
public class GedDocumentoVO extends ValueObjectImpl implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "NOME")
    private String nome;
    @Column(name = "DESCRICAO")
    private String descricao;
    @Column(name = "PALAVRA_CHAVE")
    private String palavraChave;
    @Column(name = "PODE_EXCLUIR")
    private String podeExcluir;
    @Column(name = "PODE_ALTERAR")
    private String podeAlterar;
    @Column(name = "ASSINADO")
    private String assinado;
    @Temporal(TemporalType.DATE)
    @Column(name = "DATA_FIM_VIGENCIA")
    private Date dataFimVigencia;
    @Temporal(TemporalType.DATE)
    @Column(name = "DATA_EXCLUSAO")
    private Date dataExclusao;
    @JoinColumn(name = "ID_GED_TIPO_DOCUMENTO", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private GedTipoDocumentoVO gedTipoDocumento;
    @JoinColumn(name = "ID_EMPRESA", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private EmpresaVO empresa;
    @Transient
    private ArquivoVO arquivo;

    public GedDocumentoVO() {
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

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getPalavraChave() {
        return palavraChave;
    }

    public void setPalavraChave(String palavraChave) {
        this.palavraChave = palavraChave;
    }

    public String getPodeExcluir() {
        return podeExcluir;
    }

    public void setPodeExcluir(String podeExcluir) {
        this.podeExcluir = podeExcluir;
    }

    public String getPodeAlterar() {
        return podeAlterar;
    }

    public void setPodeAlterar(String podeAlterar) {
        this.podeAlterar = podeAlterar;
    }

    public String getAssinado() {
        return assinado;
    }

    public void setAssinado(String assinado) {
        this.assinado = assinado;
    }

    public Date getDataFimVigencia() {
        return dataFimVigencia;
    }

    public void setDataFimVigencia(Date dataFimVigencia) {
        this.dataFimVigencia = dataFimVigencia;
    }

    public Date getDataExclusao() {
        return dataExclusao;
    }

    public void setDataExclusao(Date dataExclusao) {
        this.dataExclusao = dataExclusao;
    }

    public GedTipoDocumentoVO getGedTipoDocumento() {
        return gedTipoDocumento;
    }

    public void setGedTipoDocumento(GedTipoDocumentoVO gedTipoDocumento) {
        this.gedTipoDocumento = gedTipoDocumento;
    }

    public EmpresaVO getEmpresa() {
        return empresa;
    }

    public void setEmpresa(EmpresaVO empresa) {
        this.empresa = empresa;
    }

    public ArquivoVO getArquivo() {
        return arquivo;
    }

    public void setArquivo(ArquivoVO arquivo) {
        this.arquivo = arquivo;
    }
}
