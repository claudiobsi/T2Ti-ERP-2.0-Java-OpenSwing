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
package com.t2tierp.geradoretiqueta.cliente;

import com.t2tierp.geradoretiqueta.java.EtiquetaLayoutVO;
import java.awt.Image;
import net.sf.jasperreports.engine.DefaultJasperReportsContext;
import net.sf.jasperreports.engine.JRPropertiesUtil;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JRDesignBand;
import net.sf.jasperreports.engine.design.JRDesignExpression;
import net.sf.jasperreports.engine.design.JRDesignField;
import net.sf.jasperreports.engine.design.JRDesignImage;
import net.sf.jasperreports.engine.design.JRDesignSection;
import net.sf.jasperreports.engine.design.JRDesignTextField;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.type.HorizontalAlignEnum;
import net.sf.jasperreports.engine.type.PrintOrderEnum;
import net.sf.jasperreports.engine.type.ScaleImageEnum;
import net.sf.jasperreports.engine.type.VerticalAlignEnum;

public class GeradorEtiqueta {

    public static JasperReport geraEtiqueta(EtiquetaLayoutVO layout) throws Exception {
        float pxValue = 2.8346f;

        int espacamentoHorizontal = Math.round(layout.getEspacamentoHorizontal() * pxValue);
        int espacamentoVertical = Math.round(layout.getEspacamentoVertical() * pxValue);
        int margemDireita = Math.round(layout.getMargemDireita() * pxValue);
        int margemEsquerda = Math.round(layout.getMargemEsquerda() * pxValue);
        int margemInferior = Math.round(layout.getMargemInferior() * pxValue);
        int margemSuperior = Math.round(layout.getMargemSuperior() * pxValue);
        int altura = Math.round(layout.getEtiquetaFormatoPapel().getAltura() * pxValue);
        int largura = Math.round(layout.getEtiquetaFormatoPapel().getLargura() * pxValue);

        JasperDesign design = new JasperDesign();
        design.setName(layout.getCodigoFabricante());
        design.setPageWidth(largura);
        design.setPageHeight(altura);
        design.setColumnCount(layout.getQuantidadeHorizontal());
        design.setColumnWidth(espacamentoHorizontal);
        design.setColumnSpacing(0);
        design.setLeftMargin(margemEsquerda);
        design.setRightMargin(margemDireita);
        design.setTopMargin(margemSuperior);
        design.setBottomMargin(margemInferior);
        design.setPrintOrder(PrintOrderEnum.HORIZONTAL);

        //Fields
        JRDesignField field = new JRDesignField();
        field.setName("texto");
        field.setValueClass(String.class);
        design.addField(field);

        field = new JRDesignField();
        field.setName("codigoBarra");
        field.setValueClass(Image.class);
        design.addField(field);

        //Detail
        JRDesignBand band = new JRDesignBand();
        band.setHeight(espacamentoVertical);

        JRDesignTextField textField = new JRDesignTextField();
        textField.setX(0);
        textField.setY(0);
        textField.setWidth(espacamentoHorizontal);
        textField.setHeight(20);
        textField.setHorizontalAlignment(HorizontalAlignEnum.CENTER);
        textField.setVerticalAlignment(VerticalAlignEnum.MIDDLE);
        textField.setFontSize(5f);
        JRDesignExpression expression = new JRDesignExpression();
        expression.setText("$F{texto}");
        textField.setExpression(expression);
        band.addElement(textField);

        JRDesignImage image = new JRDesignImage(design);
        image.setX(0);
        image.setY(20);
        image.setWidth(espacamentoHorizontal);
        image.setHeight(espacamentoVertical - 20);
        image.setScaleImage(ScaleImageEnum.RETAIN_SHAPE);
        image.setHorizontalAlignment(HorizontalAlignEnum.CENTER);
        image.setVerticalAlignment(VerticalAlignEnum.MIDDLE);
        expression = new JRDesignExpression();
        expression.setText("$F{codigoBarra}");
        image.setExpression(expression);
        band.addElement(image);

        ((JRDesignSection) design.getDetailSection()).addBand(band);

        JRPropertiesUtil.getInstance(DefaultJasperReportsContext.getInstance()).setProperty("net.sf.jasperreports.compiler.temp.dir", System.getProperty("user.home"));
        return JasperCompileManager.compileReport(design);
    }

}
