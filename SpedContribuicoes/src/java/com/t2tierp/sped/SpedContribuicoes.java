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
package com.t2tierp.sped;

import com.t2tierp.efd.SpedUtil;
import com.t2tierp.sped.efd.bloco0.Bloco0;
import com.t2tierp.sped.efd.bloco1.Bloco1;
import com.t2tierp.sped.efd.bloco9.Bloco9;
import com.t2tierp.sped.efd.bloco9.Registro9900;
import com.t2tierp.sped.efd.blocoa.BlocoA;
import com.t2tierp.sped.efd.blococ.BlocoC;
import com.t2tierp.sped.efd.blocod.BlocoD;
import com.t2tierp.sped.efd.blocof.BlocoF;
import com.t2tierp.sped.efd.blocoi.BlocoI;
import com.t2tierp.sped.efd.blocom.BlocoM;
import java.io.File;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.io.FileUtils;

public class SpedContribuicoes {

    private Bloco0 bloco0;
    private BlocoA blocoA;
    private BlocoC blocoC;
    private BlocoD blocoD;
    private BlocoF blocoF;
    private BlocoI blocoI;
    private BlocoM blocoM;
    private Bloco1 bloco1;
    private Bloco9 bloco9;
    private SpedUtil spedUtil;
    private List<String> linhasArquivo;

    public SpedContribuicoes() {
        spedUtil = new SpedUtil();
        bloco0 = new Bloco0(spedUtil);
        blocoA = new BlocoA(spedUtil);
        blocoC = new BlocoC(spedUtil);
        blocoD = new BlocoD(spedUtil);
        blocoF = new BlocoF(spedUtil);
        blocoI = new BlocoI(spedUtil);
        blocoM = new BlocoM(spedUtil);
        bloco1 = new Bloco1(spedUtil);
        bloco9 = new Bloco9(spedUtil);
        linhasArquivo = new ArrayList<>();
    }

    public void geraArquivoTxt(File arquivo) throws Exception {
        //bloco 0
        linhasArquivo.add(bloco0.gravaRegistro0000());
        incluiRegistro9900("0000", 1);
        linhasArquivo.add(bloco0.gravaRegistro0001());
        incluiRegistro9900("0001", 1);
        incluiRegistro9900("0001", 1);
        incluiRegistro9900("0100", 1);
        incluiRegistro9900("0140", 1);
        if (getBloco0().getQuantidadeRegistros0150() > 0) {
            incluiRegistro9900("0150", getBloco0().getQuantidadeRegistros0150());
        }
        if (getBloco0().getQuantidadeRegistros0190() > 0) {
            incluiRegistro9900("0190", getBloco0().getQuantidadeRegistros0190());
        }
        if (getBloco0().getQuantidadeRegistros0200() > 0) {
            incluiRegistro9900("0200", getBloco0().getQuantidadeRegistros0200());
            if (getBloco0().getQuantidadeRegistros0205() > 0) {
                incluiRegistro9900("0205", getBloco0().getQuantidadeRegistros0205());
            }
        }
        if (getBloco0().getQuantidadeRegistros0400() > 0) {
            incluiRegistro9900("0400", getBloco0().getQuantidadeRegistros0400());
        }
        if (getBloco0().getQuantidadeRegistros0450() > 0) {
            incluiRegistro9900("0450", getBloco0().getQuantidadeRegistros0450());
        }
        if (getBloco0().getQuantidadeRegistros0500() > 0) {
            incluiRegistro9900("0500", getBloco0().getQuantidadeRegistros0500());
        }
        if (getBloco0().getQuantidadeRegistros0600() > 0) {
            incluiRegistro9900("0600", getBloco0().getQuantidadeRegistros0600());
        }
        linhasArquivo.add(getBloco0().gravaRegistro0990());
        incluiRegistro9900("0990", 1);

        //bloco A
        linhasArquivo.add(getBlocoA().gravaRegistroA001());
        incluiRegistro9900("A001", 1);
        linhasArquivo.add(getBlocoA().gravaRegistroA990());
        incluiRegistro9900("A990", 1);

        //bloco C
        linhasArquivo.add(getBlocoC().gravaRegistroC001());
        incluiRegistro9900("C001", 1);
        if (getBlocoC().getQuantidadeRegistrosC100() > 0) {
            incluiRegistro9900("C100", getBlocoC().getQuantidadeRegistrosC100());
            if (getBlocoC().getQuantidadeRegistrosC110() > 0) {
                incluiRegistro9900("C110", getBlocoC().getQuantidadeRegistrosC110());
            }
        }
        if (getBlocoC().getQuantidadeRegistrosC380() > 0) {
            incluiRegistro9900("C380", getBlocoC().getQuantidadeRegistrosC380());
            /*
             * Exercício: Implementar
             * 
             if (getBlocoC().getQuantidadeRegistrosC381() > 0) {
             incluiRegistro9900("C381", getBlocoC().getQuantidadeRegistrosC381());
             }
             if (getBlocoC().getQuantidadeRegistrosC385() > 0) {
             incluiRegistro9900("C385", getBlocoC().getQuantidadeRegistrosC385());
             }
             */
        }
        if (getBlocoC().getQuantidadeRegistrosC400() > 0) {
            incluiRegistro9900("C400", getBlocoC().getQuantidadeRegistrosC400());
            if (getBlocoC().getQuantidadeRegistrosC405() > 0) {
                incluiRegistro9900("C405", getBlocoC().getQuantidadeRegistrosC405());
                if (getBlocoC().getQuantidadeRegistrosC481() > 0) {
                    incluiRegistro9900("C481", getBlocoC().getQuantidadeRegistrosC481());
                }
                if (getBlocoC().getQuantidadeRegistrosC485() > 0) {
                    incluiRegistro9900("C485", getBlocoC().getQuantidadeRegistrosC485());
                }
            }
        }
        linhasArquivo.add(getBlocoC().gravaRegistroC990());
        incluiRegistro9900("C990", 1);

        //bloco D
        linhasArquivo.add(getBlocoD().gravaRegistroD001());
        incluiRegistro9900("D001", 1);
        linhasArquivo.add(getBlocoD().gravaRegistroD990());
        incluiRegistro9900("D990", 1);

        //bloco F
        linhasArquivo.add(getBlocoF().gravaRegistroF001());
        incluiRegistro9900("F001", 1);
        linhasArquivo.add(getBlocoF().gravaRegistroF990());
        incluiRegistro9900("F990", 1);

        //bloco I
        linhasArquivo.add(getBlocoI().gravaRegistroI001());
        incluiRegistro9900("I001", 1);
        linhasArquivo.add(getBlocoI().gravaRegistroI990());
        incluiRegistro9900("I990", 1);

        //bloco M
        linhasArquivo.add(getBlocoM().gravaRegistroM001());
        incluiRegistro9900("M001", 1);
        linhasArquivo.add(getBlocoM().gravaRegistroM990());
        incluiRegistro9900("M990", 1);

        //bloco 1
        linhasArquivo.add(getBloco1().gravaRegistro1001());
        incluiRegistro9900("1001", 1);
        linhasArquivo.add(getBloco1().gravaRegistro1990());
        incluiRegistro9900("1990", 1);

        //bloco 9
        linhasArquivo.add(getBloco9().gravaRegistro9001());
        incluiRegistro9900("9001", 1);

        incluiRegistro9900("9900", getBloco9().getListaRegistro9900().size() + 2);
        incluiRegistro9900("9990", 1);
        incluiRegistro9900("9999", 1);
        linhasArquivo.add(getBloco9().gravaRegistro9900());
        linhasArquivo.add(getBloco9().gravaRegistro9990());

        getBloco9().getRegistro9999().setQtdLin(
                getBloco0().getRegistro0990().getQtdLin0()
                + getBlocoA().getRegistroA990().getQtdLinA()
                + getBlocoC().getRegistroC990().getQtdLinC()
                + getBlocoD().getRegistroD990().getQtdLinD()
                + getBlocoF().getRegistroF990().getQtdLinF()
                + getBlocoI().getRegistroI990().getQtdLinI()
                + getBlocoM().getRegistroM990().getQtdLinM()
                + getBloco1().getRegistro1990().getQtdLin1()
                + getBloco9().getRegistro9990().getQtdLin9());
        linhasArquivo.add(getBloco9().gravaRegistro9999());

        FileUtils.writeLines(arquivo, linhasArquivo, "");
    }

    private void incluiRegistro9900(String registro, Integer quantidade) {
        Registro9900 registro9900 = new Registro9900();
        registro9900.setRegBlc(registro);
        registro9900.setQtdRegBlc(quantidade);

        bloco9.getListaRegistro9900().add(registro9900);
    }

    public String getDelimitador() {
        return spedUtil.getDelimitador();
    }

    public void setDelimitador(String delimitador) {
        spedUtil.setDelimitador(delimitador);
    }

    public DecimalFormat getFormatoDecimal() {
        return spedUtil.getFormatoDecimal();
    }

    public void setFormatoDecimal(DecimalFormat formatoDecimal) {
        spedUtil.setFormatoDecimal(formatoDecimal);
    }

    public SimpleDateFormat getFormatoData() {
        return spedUtil.getFormatoData();
    }

    public void setFormatoData(SimpleDateFormat formatoData) {
        spedUtil.setFormatoData(formatoData);
    }

    /**
     * @return the bloco9
     */
    public Bloco9 getBloco9() {
        return bloco9;
    }

    /**
     * @param bloco9 the bloco9 to set
     */
    public void setBloco9(Bloco9 bloco9) {
        this.bloco9 = bloco9;
    }

    /**
     * @return the bloco0
     */
    public Bloco0 getBloco0() {
        return bloco0;
    }

    /**
     * @param bloco0 the bloco0 to set
     */
    public void setBloco0(Bloco0 bloco0) {
        this.bloco0 = bloco0;
    }

    /**
     * @return the bloco1
     */
    public Bloco1 getBloco1() {
        return bloco1;
    }

    /**
     * @param bloco1 the bloco1 to set
     */
    public void setBloco1(Bloco1 bloco1) {
        this.bloco1 = bloco1;
    }

    /**
     * @return the blocoA
     */
    public BlocoA getBlocoA() {
        return blocoA;
    }

    /**
     * @param blocoA the blocoA to set
     */
    public void setBlocoA(BlocoA blocoA) {
        this.blocoA = blocoA;
    }

    /**
     * @return the blocoC
     */
    public BlocoC getBlocoC() {
        return blocoC;
    }

    /**
     * @param blocoC the blocoC to set
     */
    public void setBlocoC(BlocoC blocoC) {
        this.blocoC = blocoC;
    }

    /**
     * @return the blocoD
     */
    public BlocoD getBlocoD() {
        return blocoD;
    }

    /**
     * @param blocoD the blocoD to set
     */
    public void setBlocoD(BlocoD blocoD) {
        this.blocoD = blocoD;
    }

    /**
     * @return the blocoF
     */
    public BlocoF getBlocoF() {
        return blocoF;
    }

    /**
     * @param blocoF the blocoF to set
     */
    public void setBlocoF(BlocoF blocoF) {
        this.blocoF = blocoF;
    }

    /**
     * @return the blocoI
     */
    public BlocoI getBlocoI() {
        return blocoI;
    }

    /**
     * @param blocoI the blocoI to set
     */
    public void setBlocoI(BlocoI blocoI) {
        this.blocoI = blocoI;
    }

    /**
     * @return the blocoM
     */
    public BlocoM getBlocoM() {
        return blocoM;
    }

    /**
     * @param blocoM the blocoM to set
     */
    public void setBlocoM(BlocoM blocoM) {
        this.blocoM = blocoM;
    }

    /**
     * @return the spedUtil
     */
    public SpedUtil getSpedUtil() {
        return spedUtil;
    }
}
