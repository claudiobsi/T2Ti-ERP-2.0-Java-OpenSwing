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

public class EspelhoPontoPeriodoVO extends ValueObjectImpl implements Serializable {

    private static final long serialVersionUID = 1L;
    private Date dataInicio;
    private Date dataFim;
    private Integer dia;
    private String marcacaoEntrada01;
    private String marcacaoSaida01;
    private String marcacaoEntrada02;
    private String marcacaoSaida02;
    private String jornadaEntrada01;
    private String jornadaSaida01;
    private String jornadaEntrada02;
    private String jornadaSaida02;
    private String jornadaEntrada03;
    private String jornadaSaida03;
    private String codigoHorario;
    private List<PontoMarcacaoVO> marcacoesTratadas;

    public Integer getDia() {
        return dia;
    }

    public void setDia(Integer dia) {
        this.dia = dia;
    }

    public String getMarcacaoEntrada01() {
        return marcacaoEntrada01;
    }

    public void setMarcacaoEntrada01(String marcacaoEntrada01) {
        this.marcacaoEntrada01 = marcacaoEntrada01;
    }

    public String getMarcacaoSaida01() {
        return marcacaoSaida01;
    }

    public void setMarcacaoSaida01(String marcacaoSaida01) {
        this.marcacaoSaida01 = marcacaoSaida01;
    }

    public String getMarcacaoEntrada02() {
        return marcacaoEntrada02;
    }

    public void setMarcacaoEntrada02(String marcacaoEntrada02) {
        this.marcacaoEntrada02 = marcacaoEntrada02;
    }

    public String getMarcacaoSaida02() {
        return marcacaoSaida02;
    }

    public void setMarcacaoSaida02(String marcacaoSaida02) {
        this.marcacaoSaida02 = marcacaoSaida02;
    }

    public String getJornadaEntrada01() {
        return jornadaEntrada01;
    }

    public void setJornadaEntrada01(String jornadaEntrada01) {
        this.jornadaEntrada01 = jornadaEntrada01;
    }

    public String getJornadaSaida01() {
        return jornadaSaida01;
    }

    public void setJornadaSaida01(String jornadaSaida01) {
        this.jornadaSaida01 = jornadaSaida01;
    }

    public String getJornadaEntrada02() {
        return jornadaEntrada02;
    }

    public void setJornadaEntrada02(String jornadaEntrada02) {
        this.jornadaEntrada02 = jornadaEntrada02;
    }

    public String getJornadaSaida02() {
        return jornadaSaida02;
    }

    public void setJornadaSaida02(String jornadaSaida02) {
        this.jornadaSaida02 = jornadaSaida02;
    }

    public String getJornadaEntrada03() {
        return jornadaEntrada03;
    }

    public void setJornadaEntrada03(String jornadaEntrada03) {
        this.jornadaEntrada03 = jornadaEntrada03;
    }

    public String getJornadaSaida03() {
        return jornadaSaida03;
    }

    public void setJornadaSaida03(String jornadaSaida03) {
        this.jornadaSaida03 = jornadaSaida03;
    }

    public String getCodigoHorario() {
        return codigoHorario;
    }

    public void setCodigoHorario(String codigoHorario) {
        this.codigoHorario = codigoHorario;
    }

    public List<PontoMarcacaoVO> getMarcacoesTratadas() {
        return marcacoesTratadas;
    }

    public void setMarcacoesTratadas(List<PontoMarcacaoVO> marcacoesTratadas) {
        this.marcacoesTratadas = marcacoesTratadas;
    }

    /**
     * @return the dataInicio
     */
    public Date getDataInicio() {
        return dataInicio;
    }

    /**
     * @param dataInicio the dataInicio to set
     */
    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    /**
     * @return the dataFim
     */
    public Date getDataFim() {
        return dataFim;
    }

    /**
     * @param dataFim the dataFim to set
     */
    public void setDataFim(Date dataFim) {
        this.dataFim = dataFim;
    }


}
