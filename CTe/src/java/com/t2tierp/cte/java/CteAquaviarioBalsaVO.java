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
@Table(name = "CTE_AQUAVIARIO_BALSA")
public class CteAquaviarioBalsaVO extends ValueObjectImpl implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "ID_BALSA")
    private String idBalsa;
    @Column(name = "NUMERO_VIAGEM")
    private Integer numeroViagem;
    @Column(name = "DIRECAO")
    private String direcao;
    @Column(name = "PORTO_EMBARQUE")
    private String portoEmbarque;
    @Column(name = "PORTO_TRANSBORDO")
    private String portoTransbordo;
    @Column(name = "PORTO_DESTINO")
    private String portoDestino;
    @Column(name = "TIPO_NAVEGACAO")
    private Integer tipoNavegacao;
    @Column(name = "IRIN")
    private String irin;
    @JoinColumn(name = "ID_CTE_AQUAVIARIO", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private CteAquaviarioVO cteAquaviario;

    public CteAquaviarioBalsaVO() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIdBalsa() {
        return idBalsa;
    }

    public void setIdBalsa(String idBalsa) {
        this.idBalsa = idBalsa;
    }

    public Integer getNumeroViagem() {
        return numeroViagem;
    }

    public void setNumeroViagem(Integer numeroViagem) {
        this.numeroViagem = numeroViagem;
    }

    public String getDirecao() {
        return direcao;
    }

    public void setDirecao(String direcao) {
        this.direcao = direcao;
    }

    public String getPortoEmbarque() {
        return portoEmbarque;
    }

    public void setPortoEmbarque(String portoEmbarque) {
        this.portoEmbarque = portoEmbarque;
    }

    public String getPortoTransbordo() {
        return portoTransbordo;
    }

    public void setPortoTransbordo(String portoTransbordo) {
        this.portoTransbordo = portoTransbordo;
    }

    public String getPortoDestino() {
        return portoDestino;
    }

    public void setPortoDestino(String portoDestino) {
        this.portoDestino = portoDestino;
    }

    public Integer getTipoNavegacao() {
        return tipoNavegacao;
    }

    public void setTipoNavegacao(Integer tipoNavegacao) {
        this.tipoNavegacao = tipoNavegacao;
    }

    public String getIrin() {
        return irin;
    }

    public void setIrin(String irin) {
        this.irin = irin;
    }

    public CteAquaviarioVO getCteAquaviario() {
        return cteAquaviario;
    }

    public void setCteAquaviario(CteAquaviarioVO cteAquaviario) {
        this.cteAquaviario = cteAquaviario;
    }


    @Override
    public String toString() {
        return "com.t2tierp.cte.java.CteAquaviarioBalsaVO[id=" + id + "]";
    }

}
