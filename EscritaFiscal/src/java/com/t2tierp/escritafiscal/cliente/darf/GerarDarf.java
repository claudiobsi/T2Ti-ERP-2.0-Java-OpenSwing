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
package com.t2tierp.escritafiscal.cliente.darf;

import com.t2tierp.cadastros.java.EmpresaVO;
import com.t2tierp.escritafiscal.java.DarfVO;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.ImageIcon;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;

public class GerarDarf {

    public void geraDarf(EmpresaVO empresa, DarfVO darf) throws Exception {
        List<DarfVO> listaDarf = new ArrayList<>();
        
        darf.setRazaoSocial(empresa.getRazaoSocial());
        darf.setTelefone(empresa.getListaEndereco().get(0).getFone());
        darf.setCpfCnpj(empresa.getCnpj());

        listaDarf.add(darf);

        //gera a guia
        ImageIcon logoReceita = new ImageIcon(this.getClass().getResource("/images/receita.jpg"));
        Map parametros = new HashMap();
        parametros.put("LOGO_RECEITA", logoReceita.getImage());

        JRBeanCollectionDataSource jrbean = new JRBeanCollectionDataSource(listaDarf);
        JasperPrint jp = JasperFillManager.fillReport(this.getClass().getResourceAsStream("/com/t2tierp/escritafiscal/cliente/darf/darf.jasper"), parametros, jrbean);
        JasperViewer.viewReport(jp, false);
    }
}
