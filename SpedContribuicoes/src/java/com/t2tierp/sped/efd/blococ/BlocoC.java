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
package com.t2tierp.sped.efd.blococ;

import com.t2tierp.efd.SpedUtil;
import java.io.Serializable;
import java.util.List;

public class BlocoC implements Serializable {

    private static final long serialVersionUID = 1L;
    private RegistroC001 registroC001;
    private RegistroC010 registroC010;

    private Integer quantidadeRegistrosC100;
    private Integer quantidadeRegistrosC110;
    private Integer quantidadeRegistrosC170;
    private Integer quantidadeRegistrosC380;
    private Integer quantidadeRegistrosC400;
    private Integer quantidadeRegistrosC405;
    private Integer quantidadeRegistrosC481;
    private Integer quantidadeRegistrosC485;

    private RegistroC990 registroC990;

    private SpedUtil u;

    public BlocoC(SpedUtil spedUtil) {
        registroC001 = new RegistroC001();
        registroC010 = new RegistroC010();
        registroC001.setIndMov(1);

        registroC990 = new RegistroC990();
        registroC990.setQtdLinC(0);

        quantidadeRegistrosC100 = 0;
        quantidadeRegistrosC110 = 0;
        quantidadeRegistrosC170 = 0;
        quantidadeRegistrosC380 = 0;
        quantidadeRegistrosC400 = 0;
        quantidadeRegistrosC405 = 0;
        quantidadeRegistrosC481 = 0;
        quantidadeRegistrosC485 = 0;

        this.u = spedUtil;
    }

    public String gravaRegistroC001() {
        getRegistroC990().setQtdLinC(getRegistroC990().getQtdLinC() + 1);

        String registro = "";
        registro += u.preenche("C001")
                + u.preenche(getRegistroC001().getIndMov())
                + u.getDelimitador()
                + u.getCrlf();

        registro += gravaRegistroC100(getRegistroC001().getRegistroC100List());
        registro += gravaRegistroC380(getRegistroC001().getRegistroC380List());
        registro += gravaRegistroC400(getRegistroC001().getRegistroC400List());

        return registro;
    }

    private String gravaRegistroC100(List<RegistroC100> listaRegistroC100) {
        String registro = "";
        for (int i = 0; i < listaRegistroC100.size(); i++) {
            registro += u.preenche("C100")
                    + u.preenche(listaRegistroC100.get(i).getIndOper())
                    + u.preenche(listaRegistroC100.get(i).getIndEmit())
                    + u.preenche(listaRegistroC100.get(i).getCodPart())
                    + u.preenche(listaRegistroC100.get(i).getCodMod())
                    + u.preenche(listaRegistroC100.get(i).getCodSit())
                    + u.preenche(listaRegistroC100.get(i).getSer())
                    + u.preenche(listaRegistroC100.get(i).getNumDoc())
                    + u.preenche(listaRegistroC100.get(i).getChvNfe())
                    + u.preenche(listaRegistroC100.get(i).getDtDoc())
                    + u.preenche(listaRegistroC100.get(i).getDtES())
                    + u.preenche(listaRegistroC100.get(i).getVlDoc())
                    + u.preenche(listaRegistroC100.get(i).getIndPgto())
                    + u.preenche(listaRegistroC100.get(i).getVlDesc())
                    + u.preenche(listaRegistroC100.get(i).getVlAbatNt())
                    + u.preenche(listaRegistroC100.get(i).getVlMerc())
                    + u.preenche(listaRegistroC100.get(i).getIndFrt())
                    + u.preenche(listaRegistroC100.get(i).getVlFrt())
                    + u.preenche(listaRegistroC100.get(i).getVlSeg())
                    + u.preenche(listaRegistroC100.get(i).getVlOutDa())
                    + u.preenche(listaRegistroC100.get(i).getVlBcIcms())
                    + u.preenche(listaRegistroC100.get(i).getVlIcms())
                    + u.preenche(listaRegistroC100.get(i).getVlBcIcmsSt())
                    + u.preenche(listaRegistroC100.get(i).getVlIcmsSt())
                    + u.preenche(listaRegistroC100.get(i).getVlIpi())
                    + u.preenche(listaRegistroC100.get(i).getVlPis())
                    + u.preenche(listaRegistroC100.get(i).getVlCofins())
                    + u.preenche(listaRegistroC100.get(i).getVlPisSt())
                    + u.preenche(listaRegistroC100.get(i).getVlCofinsSt())
                    + u.getDelimitador()
                    + u.getCrlf();

            registro += gravaRegistroC110(listaRegistroC100.get(i).getRegistroC110List());
            registro += gravaRegistroC170(listaRegistroC100.get(i).getRegistroC170List());

            getRegistroC990().setQtdLinC(getRegistroC990().getQtdLinC() + 1);
            setQuantidadeRegistrosC100((int) (getQuantidadeRegistrosC100() + 1));
        }
        return registro;
    }

    public String gravaRegistroC110(List<RegistroC110> listaRegistroC110) {
        String registro = "";
        for (int i = 0; i < listaRegistroC110.size(); i++) {
            registro += u.preenche("C110")
                    + u.preenche(listaRegistroC110.get(i).getCodInf())
                    + u.preenche(listaRegistroC110.get(i).getTxtCompl())
                    + u.getDelimitador()
                    + u.getCrlf();

            getRegistroC990().setQtdLinC(getRegistroC990().getQtdLinC() + 1);
            setQuantidadeRegistrosC110((int) (getQuantidadeRegistrosC110() + 1));
        }
        return registro;
    }

    private String gravaRegistroC170(List<RegistroC170> listaRegistroC170) {
        String registro = "";
        for (int i = 0; i < listaRegistroC170.size(); i++) {
            registro += u.preenche("C170")
                    + u.preenche(listaRegistroC170.get(i).getNumItem())
                    + u.preenche(listaRegistroC170.get(i).getCodItem())
                    + u.preenche(listaRegistroC170.get(i).getDescrCompl())
                    + u.preenche(listaRegistroC170.get(i).getQtd())
                    + u.preenche(listaRegistroC170.get(i).getUnid())
                    + u.preenche(listaRegistroC170.get(i).getVlItem())
                    + u.preenche(listaRegistroC170.get(i).getVlDesc())
                    + u.preenche(listaRegistroC170.get(i).getIndMov())
                    + u.preenche(listaRegistroC170.get(i).getCstIcms())
                    + u.preenche(listaRegistroC170.get(i).getCfop())
                    + u.preenche(listaRegistroC170.get(i).getCodNat())
                    + u.preenche(listaRegistroC170.get(i).getVlBcIcms())
                    + u.preenche(listaRegistroC170.get(i).getAliqIcms())
                    + u.preenche(listaRegistroC170.get(i).getVlIcms())
                    + u.preenche(listaRegistroC170.get(i).getVlBcIcmsSt())
                    + u.preenche(listaRegistroC170.get(i).getAliqSt())
                    + u.preenche(listaRegistroC170.get(i).getVlIcmsSt())
                    + u.preenche(listaRegistroC170.get(i).getIndApur())
                    + u.preenche(listaRegistroC170.get(i).getCstIpi())
                    + u.preenche(listaRegistroC170.get(i).getCodEnq())
                    + u.preenche(listaRegistroC170.get(i).getVlBcIpi())
                    + u.preenche(listaRegistroC170.get(i).getAliqIpi())
                    + u.preenche(listaRegistroC170.get(i).getVlIpi())
                    + u.preenche(listaRegistroC170.get(i).getCstPis())
                    + u.preenche(listaRegistroC170.get(i).getVlBcPis())
                    + u.preenche(listaRegistroC170.get(i).getAliqPisPerc())
                    + u.preenche(listaRegistroC170.get(i).getQuantBcPis())
                    + u.preenche(listaRegistroC170.get(i).getAliqPisR())
                    + u.preenche(listaRegistroC170.get(i).getVlPis())
                    + u.preenche(listaRegistroC170.get(i).getCstCofins())
                    + u.preenche(listaRegistroC170.get(i).getVlBcCofins())
                    + u.preenche(listaRegistroC170.get(i).getAliqCofinsPerc())
                    + u.preenche(listaRegistroC170.get(i).getQuantBcCofins())
                    + u.preenche(listaRegistroC170.get(i).getAliqCofinsR())
                    + u.preenche(listaRegistroC170.get(i).getVlCofins())
                    + u.preenche(listaRegistroC170.get(i).getCodCta())
                    + u.getDelimitador()
                    + u.getCrlf();

            registroC990.setQtdLinC(registroC990.getQtdLinC() + 1);
            setQuantidadeRegistrosC170((int) (getQuantidadeRegistrosC170() + 1));
        }
        return registro;
    }

    private String gravaRegistroC380(List<RegistroC380> listaRegistroC380) {
        String registro = "";
        for (int i = 0; i < listaRegistroC380.size(); i++) {
            registro += u.preenche("C380")
                    + u.preenche(listaRegistroC380.get(i).getCodMod())
                    + u.preenche(listaRegistroC380.get(i).getDtDocIni())
                    + u.preenche(listaRegistroC380.get(i).getDtDocFin())
                    + u.preenche(listaRegistroC380.get(i).getNumDocIni())
                    + u.preenche(listaRegistroC380.get(i).getNumDocFin())
                    + u.preenche(listaRegistroC380.get(i).getVlDoc())
                    + u.preenche(listaRegistroC380.get(i).getVlDocCanc())
                    + u.getDelimitador()
                    + u.getCrlf();

            //registro += gravaRegistroC381(listaRegistroC380.get(i).getRegistroC381List());
            //registro += gravaRegistroC385(listaRegistroC380.get(i).getRegistroC385List());
            getRegistroC990().setQtdLinC(getRegistroC990().getQtdLinC() + 1);
            setQuantidadeRegistrosC380((int) (getQuantidadeRegistrosC380() + 1));
        }
        return registro;
    }

    private String gravaRegistroC400(List<RegistroC400> listaRegistroC400) {
        String registro = "";
        for (int i = 0; i < listaRegistroC400.size(); i++) {
            registro += u.preenche("C400")
                    + u.preenche(listaRegistroC400.get(i).getCodMod())
                    + u.preenche(listaRegistroC400.get(i).getEcfMod())
                    + u.preenche(listaRegistroC400.get(i).getEcfFab())
                    + u.preenche(listaRegistroC400.get(i).getEcfCx())
                    + u.getDelimitador()
                    + u.getCrlf();

            registro += gravaRegistroC405(listaRegistroC400.get(i).getRegistroC405List());

            getRegistroC990().setQtdLinC(getRegistroC990().getQtdLinC() + 1);
            setQuantidadeRegistrosC400((int) (getQuantidadeRegistrosC400() + 1));
        }
        return registro;
    }

    public String gravaRegistroC405(List<RegistroC405> listaRegistroC405) {
        String registro = "";
        for (int i = 0; i < listaRegistroC405.size(); i++) {
            registro += u.preenche("C405")
                    + u.preenche(listaRegistroC405.get(i).getDtDoc())
                    + u.preenche(listaRegistroC405.get(i).getCro())
                    + u.preenche(listaRegistroC405.get(i).getCrz())
                    + u.preenche(listaRegistroC405.get(i).getNumCooFin())
                    + u.preenche(listaRegistroC405.get(i).getGtFin())
                    + u.preenche(listaRegistroC405.get(i).getVlBrt())
                    + u.getDelimitador()
                    + u.getCrlf();

            registro += gravaRegistroC481(listaRegistroC405.get(i).getRegistroC481List());
            registro += gravaRegistroC485(listaRegistroC405.get(i).getRegistroC485List());

            getRegistroC990().setQtdLinC(getRegistroC990().getQtdLinC() + 1);
            setQuantidadeRegistrosC405((int) (getQuantidadeRegistrosC405() + 1));
        }
        return registro;
    }

    public String gravaRegistroC481(List<RegistroC481> listaRegistroC481) {
        String registro = "";
        for (int i = 0; i < listaRegistroC481.size(); i++) {
            registro += u.preenche("C481")
                    + u.preenche(listaRegistroC481.get(i).getCodItem())
                    + u.preenche(listaRegistroC481.get(i).getQtd())
                    + u.preenche(listaRegistroC481.get(i).getUnid())
                    + u.preenche(listaRegistroC481.get(i).getVlItem())
                    + u.preenche(listaRegistroC481.get(i).getVlPis())
                    + u.preenche(listaRegistroC481.get(i).getVlCofins())
                    + u.getDelimitador()
                    + u.getCrlf();

            getRegistroC990().setQtdLinC(getRegistroC990().getQtdLinC() + 1);
            setQuantidadeRegistrosC481((int) (getQuantidadeRegistrosC481() + 1));
        }
        return registro;
    }

    public String gravaRegistroC485(List<RegistroC485> listaRegistroC485) {
        String registro = "";
        for (int i = 0; i < listaRegistroC485.size(); i++) {
            registro += u.preenche("C485")
                    + u.preenche(listaRegistroC485.get(i).getCodItem())
                    + u.preenche(listaRegistroC485.get(i).getQtd())
                    + u.preenche(listaRegistroC485.get(i).getUnid())
                    + u.preenche(listaRegistroC485.get(i).getVlItem())
                    + u.preenche(listaRegistroC485.get(i).getVlPis())
                    + u.preenche(listaRegistroC485.get(i).getVlCofins())
                    + u.getDelimitador()
                    + u.getCrlf();

            getRegistroC990().setQtdLinC(getRegistroC990().getQtdLinC() + 1);
            setQuantidadeRegistrosC485((int) (getQuantidadeRegistrosC485() + 1));
        }
        return registro;
    }

    public String gravaRegistroC990() {
        registroC990.setQtdLinC(registroC990.getQtdLinC() + 1);

        return u.preenche("C990")
                + u.preenche(registroC990.getQtdLinC())
                + u.getDelimitador()
                + u.getCrlf();
    }

    /**
     * @return the registroC001
     */
    public RegistroC001 getRegistroC001() {
        return registroC001;
    }

    /**
     * @return the registroC010
     */
    public RegistroC010 getRegistroC010() {
        return registroC010;
    }

    /**
     * @param registroC001 the registroC001 to set
     */
    public void setRegistroC001(RegistroC001 registroC001) {
        this.registroC001 = registroC001;
    }

    /**
     * @return the quantidadeRegistrosC100
     */
    public int getQuantidadeRegistrosC100() {
        return quantidadeRegistrosC100;
    }

    /**
     * @param quantidadeRegistrosC100 the quantidadeRegistrosC100 to set
     */
    public void setQuantidadeRegistrosC100(int quantidadeRegistrosC100) {
        this.quantidadeRegistrosC100 = quantidadeRegistrosC100;
    }

    /**
     * @return the quantidadeRegistrosC380
     */
    public int getQuantidadeRegistrosC380() {
        return quantidadeRegistrosC380;
    }

    /**
     * @param quantidadeRegistrosC380 the quantidadeRegistrosC380 to set
     */
    public void setQuantidadeRegistrosC380(int quantidadeRegistrosC380) {
        this.quantidadeRegistrosC380 = quantidadeRegistrosC380;
    }

    /**
     * @return the quantidadeRegistrosC400
     */
    public int getQuantidadeRegistrosC400() {
        return quantidadeRegistrosC400;
    }

    /**
     * @param quantidadeRegistrosC400 the quantidadeRegistrosC400 to set
     */
    public void setQuantidadeRegistrosC400(int quantidadeRegistrosC400) {
        this.quantidadeRegistrosC400 = quantidadeRegistrosC400;
    }

    /**
     * @return the quantidadeRegistrosC405
     */
    public int getQuantidadeRegistrosC405() {
        return quantidadeRegistrosC405;
    }

    /**
     * @param quantidadeRegistrosC405 the quantidadeRegistrosC405 to set
     */
    public void setQuantidadeRegistrosC405(int quantidadeRegistrosC405) {
        this.quantidadeRegistrosC405 = quantidadeRegistrosC405;
    }

    /**
     * @return the quantidadeRegistrosC481
     */
    public int getQuantidadeRegistrosC481() {
        return quantidadeRegistrosC481;
    }

    /**
     * @param quantidadeRegistrosC481 the quantidadeRegistrosC481 to set
     */
    public void setQuantidadeRegistrosC481(int quantidadeRegistrosC481) {
        this.quantidadeRegistrosC481 = quantidadeRegistrosC481;
    }

    /**
     * @return the quantidadeRegistrosC485
     */
    public int getQuantidadeRegistrosC485() {
        return quantidadeRegistrosC485;
    }

    /**
     * @param quantidadeRegistrosC485 the quantidadeRegistrosC485 to set
     */
    public void setQuantidadeRegistrosC485(int quantidadeRegistrosC485) {
        this.quantidadeRegistrosC485 = quantidadeRegistrosC485;
    }

    /**
     * @return the registroC990
     */
    public RegistroC990 getRegistroC990() {
        return registroC990;
    }

    /**
     * @param registroC990 the registroC990 to set
     */
    public void setRegistroC990(RegistroC990 registroC990) {
        this.registroC990 = registroC990;
    }

    /**
     * @param util the util to set
     */
    public void setUtil(SpedUtil util) {
        this.u = util;
    }

    /**
     * @return the quantidadeRegistrosC110
     */
    public int getQuantidadeRegistrosC110() {
        return quantidadeRegistrosC110;
    }

    /**
     * @param quantidadeRegistrosC110 the quantidadeRegistrosC110 to set
     */
    public void setQuantidadeRegistrosC110(int quantidadeRegistrosC110) {
        this.quantidadeRegistrosC110 = quantidadeRegistrosC110;
    }

    /**
     * @return the quantidadeRegistrosC170
     */
    public int getQuantidadeRegistrosC170() {
        return quantidadeRegistrosC170;
    }

    /**
     * @param quantidadeRegistrosC170 the quantidadeRegistrosC170 to set
     */
    public void setQuantidadeRegistrosC170(int quantidadeRegistrosC170) {
        this.quantidadeRegistrosC170 = quantidadeRegistrosC170;
    }
}
