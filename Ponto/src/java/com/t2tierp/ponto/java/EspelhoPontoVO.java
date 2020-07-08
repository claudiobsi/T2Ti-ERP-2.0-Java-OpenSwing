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
package com.t2tierp.ponto.java;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import org.openswing.swing.message.receive.java.ValueObjectImpl;

public class EspelhoPontoVO extends ValueObjectImpl implements Serializable {

    private static final long serialVersionUID = 1L;
    private String cnpjEmpregador;
    private String nomeEmpregador;
    private String ederecoEmpregador;
    private String pisEmpregado;
    private String nomeEmpregado;
    private Date dataAdmissao;
    private Date dataEmissaoRelatorio;
    private List<PontoHorarioVO> listaHorarios;
    private List<EspelhoPontoPeriodoVO> listaPeriodo;

    /**
     * @return the cnpjEmpregador
     */
    public String getCnpjEmpregador() {
        return cnpjEmpregador;
    }

    /**
     * @param cnpjEmpregador the cnpjEmpregador to set
     */
    public void setCnpjEmpregador(String cnpjEmpregador) {
        this.cnpjEmpregador = cnpjEmpregador;
    }

    /**
     * @return the nomeEmpregador
     */
    public String getNomeEmpregador() {
        return nomeEmpregador;
    }

    /**
     * @param nomeEmpregador the nomeEmpregador to set
     */
    public void setNomeEmpregador(String nomeEmpregador) {
        this.nomeEmpregador = nomeEmpregador;
    }

    /**
     * @return the ederecoEmpregador
     */
    public String getEderecoEmpregador() {
        return ederecoEmpregador;
    }

    /**
     * @param ederecoEmpregador the ederecoEmpregador to set
     */
    public void setEderecoEmpregador(String ederecoEmpregador) {
        this.ederecoEmpregador = ederecoEmpregador;
    }

    /**
     * @return the pisEmpregado
     */
    public String getPisEmpregado() {
        return pisEmpregado;
    }

    /**
     * @param pisEmpregado the pisEmpregado to set
     */
    public void setPisEmpregado(String pisEmpregado) {
        this.pisEmpregado = pisEmpregado;
    }

    /**
     * @return the nomeEmpregado
     */
    public String getNomeEmpregado() {
        return nomeEmpregado;
    }

    /**
     * @param nomeEmpregado the nomeEmpregado to set
     */
    public void setNomeEmpregado(String nomeEmpregado) {
        this.nomeEmpregado = nomeEmpregado;
    }

    /**
     * @return the dataAdmissao
     */
    public Date getDataAdmissao() {
        return dataAdmissao;
    }

    /**
     * @param dataAdmissao the dataAdmissao to set
     */
    public void setDataAdmissao(Date dataAdmissao) {
        this.dataAdmissao = dataAdmissao;
    }

    /**
     * @return the dataEmissaoRelatorio
     */
    public Date getDataEmissaoRelatorio() {
        return dataEmissaoRelatorio;
    }

    /**
     * @param dataEmissaoRelatorio the dataEmissaoRelatorio to set
     */
    public void setDataEmissaoRelatorio(Date dataEmissaoRelatorio) {
        this.dataEmissaoRelatorio = dataEmissaoRelatorio;
    }

    /**
     * @return the listaHorarios
     */
    public List<PontoHorarioVO> getListaHorarios() {
        return listaHorarios;
    }

    /**
     * @param listaHorarios the listaHorarios to set
     */
    public void setListaHorarios(List<PontoHorarioVO> listaHorarios) {
        this.listaHorarios = listaHorarios;
    }

    /**
     * @return the listaPeriodo
     */
    public List<EspelhoPontoPeriodoVO> getListaPeriodo() {
        return listaPeriodo;
    }

    /**
     * @param listaPeriodo the listaPeriodo to set
     */
    public void setListaPeriodo(List<EspelhoPontoPeriodoVO> listaPeriodo) {
        this.listaPeriodo = listaPeriodo;
    }

}