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
package com.t2tierp.sped.efd.bloco0;

import com.t2tierp.efd.SpedUtil;
import java.io.Serializable;
import java.util.List;

public class Bloco0 implements Serializable {

    private static final long serialVersionUID = 1L;
    private Registro0000 registro0000;
    private Registro0001 registro0001;
    private Registro0100 registro0100;
    private Registro0110 registro0110;
    private Registro0140 registro0140;
    private Registro0990 registro0990;

    private int quantidadeRegistros0150;
    private int quantidadeRegistros0190;
    private int quantidadeRegistros0200;
    private int quantidadeRegistros0205;
    private int quantidadeRegistros0400;
    private int quantidadeRegistros0450;
    private int quantidadeRegistros0500;
    private int quantidadeRegistros0600;

    private SpedUtil u;

    public Bloco0(SpedUtil spedUtil) {
        registro0000 = new Registro0000();
        registro0001 = new Registro0001();
        registro0100 = new Registro0100();
        registro0110 = new Registro0110();
        registro0140 = new Registro0140();

        registro0001.setIndMov(1);

        registro0990 = new Registro0990();
        registro0990.setQtdLin0(0);

        quantidadeRegistros0150 = 0;
        quantidadeRegistros0190 = 0;
        quantidadeRegistros0200 = 0;
        quantidadeRegistros0205 = 0;
        quantidadeRegistros0400 = 0;
        quantidadeRegistros0450 = 0;
        quantidadeRegistros0500 = 0;
        quantidadeRegistros0600 = 0;

        this.u = spedUtil;
    }

    public String gravaRegistro0000() {
        getRegistro0990().setQtdLin0(getRegistro0990().getQtdLin0() + 1);

        return u.preenche("0000")
                + u.preenche(getRegistro0000().getCodVer())
                + u.preenche(getRegistro0000().getCodFin())
                + u.preenche(getRegistro0000().getDtIni())
                + u.preenche(getRegistro0000().getDtFin())
                + u.preenche(getRegistro0000().getNome())
                + u.preenche(getRegistro0000().getCnpj())
                + u.preenche(getRegistro0000().getCpf())
                + u.preenche(getRegistro0000().getUf())
                + u.preenche(getRegistro0000().getIe())
                + u.preenche(getRegistro0000().getCodMun())
                + u.preenche(getRegistro0000().getIm())
                + u.preenche(getRegistro0000().getSuframa())
                + u.preenche(getRegistro0000().getIndPerfil())
                + u.preenche(getRegistro0000().getIndAtiv())
                + u.getDelimitador()
                + u.getCrlf();
    }

    public String gravaRegistro0001() {
        getRegistro0990().setQtdLin0(getRegistro0990().getQtdLin0() + 1);

        String registro = "";
        registro += u.preenche("0001")
                + u.preenche(getRegistro0001().getIndMov())
                + u.getDelimitador()
                + u.getCrlf();

        registro += gravaRegistro0100(getRegistro0001().getRegistro0100());
        registro += gravaRegistro0110(getRegistro0001().getRegistro0110());
        registro += gravaRegistro0140(getRegistro0001().getRegistro0140());
        registro += gravaRegistro0400(getRegistro0001().getRegistro0400List());
        registro += gravaRegistro0450(getRegistro0001().getRegistro0450List());
        registro += gravaRegistro0500(getRegistro0001().getRegistro0500List());
        registro += gravaRegistro0600(getRegistro0001().getRegistro0600List());

        return registro;
    }

    public String gravaRegistro0100(Registro0100 registro0100) {
        getRegistro0990().setQtdLin0(getRegistro0990().getQtdLin0() + 1);

        return u.preenche("0100")
                + u.preenche(registro0100.getNome())
                + u.preenche(registro0100.getCpf())
                + u.preenche(registro0100.getCrc())
                + u.preenche(registro0100.getCnpj())
                + u.preenche(registro0100.getCep())
                + u.preenche(registro0100.getEndereco())
                + u.preenche(registro0100.getNum())
                + u.preenche(registro0100.getCompl())
                + u.preenche(registro0100.getBairro())
                + u.preenche(registro0100.getFone())
                + u.preenche(registro0100.getFax())
                + u.preenche(registro0100.getEmail())
                + u.preenche(registro0100.getCodMun())
                + u.getDelimitador()
                + u.getCrlf();
    }

    public String gravaRegistro0110(Registro0110 registro0110) {
        getRegistro0990().setQtdLin0(getRegistro0990().getQtdLin0() + 1);

        return u.preenche("0110")
                + u.preenche(registro0110.getCodIncTrib())
                + u.preenche(registro0110.getIndAproCred())
                + u.preenche(registro0110.getCodTipoCont())
                + u.preenche(registro0110.getIndRegCum())
                + u.getDelimitador()
                + u.getCrlf();
    }

    public String gravaRegistro0140(Registro0140 registro0140) {
        getRegistro0990().setQtdLin0(getRegistro0990().getQtdLin0() + 1);

        String registro = "";
        registro += u.preenche("0140")
                + u.preenche(registro0140.getCodEst())
                + u.preenche(registro0140.getNome())
                + u.preenche(registro0140.getCnpj())
                + u.preenche(registro0140.getUf())
                + u.preenche(registro0140.getIe())
                + u.preenche(registro0140.getCodMun())
                + u.preenche(registro0140.getIm())
                + u.preenche(registro0140.getSuframa())
                + u.getDelimitador()
                + u.getCrlf();

        registro += gravaRegistro0150(registro0140.getRegistro0150List());
        registro += gravaRegistro0190(registro0140.getRegistro0190List());
        registro += gravaRegistro0200(registro0140.getRegistro0200List());

        return registro;
    }

    public String gravaRegistro0150(List<Registro0150> listaRegistro0150) {
        String registro = "";
        for (Registro0150 r : listaRegistro0150) {
            registro += u.preenche("0150")
                    + u.preenche(r.getCodPart())
                    + u.preenche(r.getNome())
                    + u.preenche(r.getCodPais())
                    + u.preenche(r.getCnpj())
                    + u.preenche(r.getCpf())
                    + u.preenche(r.getIe())
                    + u.preenche(r.getCodMun())
                    + u.preenche(r.getSuframa())
                    + u.preenche(r.getEndereco())
                    + u.preenche(r.getNum())
                    + u.preenche(r.getCompl())
                    + u.preenche(r.getBairro())
                    + u.getDelimitador()
                    + u.getCrlf();

            getRegistro0990().setQtdLin0(getRegistro0990().getQtdLin0() + 1);
            quantidadeRegistros0150 += 1;
        }
        return registro;
    }

    public String gravaRegistro0190(List<Registro0190> listaRegistro0190) {
        String registro = "";
        for (Registro0190 r : listaRegistro0190) {
            registro += u.preenche("0190")
                    + u.preenche(r.getUnid())
                    + u.preenche(r.getDescr())
                    + u.getDelimitador()
                    + u.getCrlf();

            getRegistro0990().setQtdLin0(getRegistro0990().getQtdLin0() + 1);
            quantidadeRegistros0190 += 1;
        }
        return registro;
    }

    public String gravaRegistro0200(List<Registro0200> listaRegistro0200) {
        String registro = "";
        for (Registro0200 r : listaRegistro0200) {
            registro += u.preenche("0200")
                    + u.preenche(r.getCodItem())
                    + u.preenche(r.getDescrItem())
                    + u.preenche(r.getCodBarra())
                    + u.preenche(r.getCodAntItem())
                    + u.preenche(r.getUnidInv())
                    + u.preenche(r.getTipoItem())
                    + u.preenche(r.getCodNcm())
                    + u.preenche(r.getExIpi())
                    + u.preenche(r.getCodGen())
                    + u.preenche(r.getCodLst())
                    + u.preenche(r.getAliqIcms())
                    + u.getDelimitador()
                    + u.getCrlf();

            registro += gravaRegistro0205(r.getRegistro0205List());

            getRegistro0990().setQtdLin0(getRegistro0990().getQtdLin0() + 1);
            quantidadeRegistros0200 += 1;
        }
        return registro;
    }

    public String gravaRegistro0205(List<Registro0205> listaRegistro0205) {
        String registro = "";
        for (Registro0205 r : listaRegistro0205) {
            registro += u.preenche("0205")
                    + u.preenche(r.getDescrAntItem())
                    + u.preenche(r.getDtIni())
                    + u.preenche(r.getDtFin())
                    + u.preenche(r.getCodAntItem())
                    + u.getDelimitador()
                    + u.getCrlf();

            getRegistro0990().setQtdLin0(getRegistro0990().getQtdLin0() + 1);
            quantidadeRegistros0205 += 1;
        }
        return registro;
    }

    public String gravaRegistro0400(List<Registro0400> listaRegistro0400) {
        String registro = "";
        for (Registro0400 r : listaRegistro0400) {
            registro += u.preenche("0400")
                    + u.preenche(r.getCodNat())
                    + u.preenche(r.getDescrNat())
                    + u.getDelimitador()
                    + u.getCrlf();

            getRegistro0990().setQtdLin0(getRegistro0990().getQtdLin0() + 1);
            quantidadeRegistros0400 += 1;
        }
        return registro;
    }

    public String gravaRegistro0450(List<Registro0450> listaRegistro0450) {
        String registro = "";
        for (Registro0450 r : listaRegistro0450) {
            registro += u.preenche("0450")
                    + u.preenche(r.getCodInf())
                    + u.preenche(r.getTxt())
                    + u.getDelimitador()
                    + u.getCrlf();

            getRegistro0990().setQtdLin0(getRegistro0990().getQtdLin0() + 1);
            quantidadeRegistros0450 += 1;
        }
        return registro;
    }

    public String gravaRegistro0500(List<Registro0500> listaRegistro0500) {
        String registro = "";
        for (Registro0500 r : listaRegistro0500) {
            registro += u.preenche("0500")
                    + u.preenche(r.getDtAlt())
                    + u.preenche(r.getCodNatCc())
                    + u.preenche(r.getIndCta())
                    + u.preenche(r.getNivel())
                    + u.preenche(r.getCodCta())
                    + u.preenche(r.getNomeCta())
                    + u.getDelimitador()
                    + u.getCrlf();

            getRegistro0990().setQtdLin0(getRegistro0990().getQtdLin0() + 1);
            quantidadeRegistros0500 += 1;
        }
        return registro;
    }

    public String gravaRegistro0600(List<Registro0600> listaRegistro0600) {
        String registro = "";
        for (Registro0600 r : listaRegistro0600) {
            registro += u.preenche("0600")
                    + u.preenche(r.getDtAlt())
                    + u.preenche(r.getCodCcus())
                    + u.preenche(r.getCcus())
                    + u.getDelimitador()
                    + u.getCrlf();

            getRegistro0990().setQtdLin0(getRegistro0990().getQtdLin0() + 1);
            quantidadeRegistros0600 += 1;
        }
        return registro;
    }

    public String gravaRegistro0990() {
        getRegistro0990().setQtdLin0(getRegistro0990().getQtdLin0() + 1);

        return u.preenche("0990")
                + u.preenche(getRegistro0990().getQtdLin0())
                + u.getDelimitador()
                + u.getCrlf();
    }

    public Registro0000 getRegistro0000() {
        return registro0000;
    }

    public Registro0001 getRegistro0001() {
        return registro0001;
    }

    public Registro0100 getRegistro0100() {
        return registro0100;
    }

    public Registro0110 getRegistro0110() {
        return registro0110;
    }

    public Registro0140 getRegistro0140() {
        return registro0140;
    }

    public Registro0990 getRegistro0990() {
        return registro0990;
    }

    public int getQuantidadeRegistros0150() {
        return quantidadeRegistros0150;
    }

    public int getQuantidadeRegistros0190() {
        return quantidadeRegistros0190;
    }

    public int getQuantidadeRegistros0200() {
        return quantidadeRegistros0200;
    }

    public int getQuantidadeRegistros0205() {
        return quantidadeRegistros0205;
    }

    public int getQuantidadeRegistros0400() {
        return quantidadeRegistros0400;
    }

    public int getQuantidadeRegistros0450() {
        return quantidadeRegistros0450;
    }

    public int getQuantidadeRegistros0500() {
        return quantidadeRegistros0500;
    }

    public int getQuantidadeRegistros0600() {
        return quantidadeRegistros0600;
    }

}
