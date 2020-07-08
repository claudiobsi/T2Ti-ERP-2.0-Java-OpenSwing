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
@Table(name = "CTE_RODOVIARIO_PEDAGIO")
public class CteRodoviarioPedagioVO extends ValueObjectImpl implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "CNPJ_FORNECEDOR")
    private String cnpjFornecedor;
    @Column(name = "COMPROVANTE_COMPRA")
    private String comprovanteCompra;
    @Column(name = "CNPJ_RESPONSAVEL")
    private String cnpjResponsavel;
    @Column(name = "VALOR")
    private BigDecimal valor;
    @JoinColumn(name = "ID_CTE_RODOVIARIO", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private CteRodoviarioVO cteRodoviario;

    public CteRodoviarioPedagioVO() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCnpjFornecedor() {
        return cnpjFornecedor;
    }

    public void setCnpjFornecedor(String cnpjFornecedor) {
        this.cnpjFornecedor = cnpjFornecedor;
    }

    public String getComprovanteCompra() {
        return comprovanteCompra;
    }

    public void setComprovanteCompra(String comprovanteCompra) {
        this.comprovanteCompra = comprovanteCompra;
    }

    public String getCnpjResponsavel() {
        return cnpjResponsavel;
    }

    public void setCnpjResponsavel(String cnpjResponsavel) {
        this.cnpjResponsavel = cnpjResponsavel;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public CteRodoviarioVO getCteRodoviario() {
        return cteRodoviario;
    }

    public void setCteRodoviario(CteRodoviarioVO cteRodoviario) {
        this.cteRodoviario = cteRodoviario;
    }


    @Override
    public String toString() {
        return "com.t2tierp.cte.java.CteRodoviarioPedagioVO[id=" + id + "]";
    }

}
