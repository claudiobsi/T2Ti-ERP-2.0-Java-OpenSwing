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
package com.t2tierp.escritafiscal.servidor;

import com.t2tierp.cadastros.java.EmpresaVO;
import com.t2tierp.escritafiscal.java.FiscalApuracaoIcmsVO;
import com.t2tierp.padrao.java.Constantes;
import com.t2tierp.padrao.servidor.HibernateUtil;
import com.t2tierp.padrao.java.Biblioteca;
import com.t2tierp.sped.java.ViewSpedC190VO;
import com.t2tierp.sped.java.ViewSpedC390VO;
import com.t2tierp.sped.java.ViewSpedC490VO;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.openswing.swing.message.receive.java.ErrorResponse;
import org.openswing.swing.message.receive.java.Response;
import org.openswing.swing.message.receive.java.VOResponse;
import org.openswing.swing.server.Action;
import org.openswing.swing.server.UserSessionParameters;

public class FiscalApuracaoIcmsAction implements Action {

    public FiscalApuracaoIcmsAction() {
    }

    public String getRequestName() {
        return "fiscalApuracaoIcmsAction";
    }

    public Response executeCommand(Object inputPar, UserSessionParameters userSessionPars, HttpServletRequest request, HttpServletResponse response, HttpSession userSession, ServletContext context) {
        Object[] pars = (Object[]) inputPar;
        Integer acao = (Integer) pars[0];

        switch (acao) {
            case Constantes.LOAD: {
                return load(inputPar, userSessionPars, request, response, userSession, context);
            }
            case Constantes.INSERT: {
                return insert(inputPar, userSessionPars, request, response, userSession, context);
            }
            case Constantes.UPDATE: {
                return update(inputPar, userSessionPars, request, response, userSession, context);
            }
            case Constantes.DELETE: {
                return delete(inputPar, userSessionPars, request, response, userSession, context);
            }
        }
        return null;
    }

    private Response load(Object inputPar, UserSessionParameters userSessionPars, HttpServletRequest request, HttpServletResponse response, HttpSession userSession, ServletContext context) {
        Session session = null;
        Object[] pars = (Object[]) inputPar;
        String periodo = (String) pars[1];
        EmpresaVO empresa = (EmpresaVO) pars[2];

        try {
            String periodoAnterior = "";
            Calendar dataInicio = Calendar.getInstance();
            Calendar dataFim = Calendar.getInstance();
            BigDecimal valorTotalDebitos = BigDecimal.ZERO;
            BigDecimal valorTotalCreditos = BigDecimal.ZERO;
            BigDecimal valorSaldoApurado = BigDecimal.ZERO;
            try {
                periodoAnterior = Biblioteca.periodoAnterior(periodo);
                dataInicio.setLenient(false);
                dataInicio.set(Calendar.DAY_OF_MONTH, 1);
                dataInicio.set(Calendar.MONTH, Integer.valueOf(periodo.substring(0, 2)) - 1);
                dataInicio.set(Calendar.YEAR, Integer.valueOf(periodo.substring(3, 7)));
                dataFim = Biblioteca.ultimoDiaMes(dataInicio);
            } catch (Exception e) {
                throw new Exception("Período inválido.");
            }
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();

            Criteria criteria = session.createCriteria(FiscalApuracaoIcmsVO.class);
            criteria.add(Restrictions.eq("competencia", periodoAnterior));
            FiscalApuracaoIcmsVO apuracaoIcmsAnterior = (FiscalApuracaoIcmsVO) criteria.uniqueResult();

            criteria = session.createCriteria(FiscalApuracaoIcmsVO.class);
            criteria.add(Restrictions.eq("competencia", periodo));
            FiscalApuracaoIcmsVO apuracaoIcms = (FiscalApuracaoIcmsVO) criteria.uniqueResult();

            // Se não existe registro para o período atual, cria objeto
            if (apuracaoIcms == null) {
                apuracaoIcms = new FiscalApuracaoIcmsVO();
                apuracaoIcms.setEmpresa(empresa);
                apuracaoIcms.setCompetencia(periodo);
            }

            // Se existir o período anterior, pega o saldo credor
            if (apuracaoIcmsAnterior != null) {
                apuracaoIcms.setValorSaldoCredorAnterior(apuracaoIcmsAnterior.getValorSaldoCredorAnterior());
            }

            /*
            REGISTRO E110 - Campo 02 - VL_TOT_DEBITOS'
            
            Campo 02 - Validação: o valor informado deve corresponder ao somatório de todos os documentos fiscais de saída que
            geram débito de ICMS. Deste somatório, estão excluídos os documentos extemporâneos (COD_SIT com valor igual ‘01’),
            os documentos complementares extemporâneos (COD_SIT com valor igual ‘07’) e os documentos fiscais com CFOP 5605.
            Devem ser incluídos os documentos fiscais com CFOP igual a 1605.
            O valor neste campo deve ser igual à soma dos VL_ICMS de todos os registros C190, C320, C390, C490, C590, C690,
            C790, D190, D300, D390, D410, D590, D690, D696, com as datas dos campos DT_DOC (C300, C405, C600, D300,
            D355, D400, D600) ou DT_E_S (C100, C500) ou DT_DOC_FIN (C700, D695) ou DT_A_P (D100, D500) dentro do
            período informado no registro E100.
             */

            // REGISTRO C190: REGISTRO ANALÍTICO DO DOCUMENTO (CÓDIGO 01, 1B, 04 ,55 e 65).
            criteria = session.createCriteria(ViewSpedC190VO.class);
            criteria.add(Restrictions.between("dataEmissao", dataInicio.getTime(), dataFim.getTime()));
            List<ViewSpedC190VO> listaNfeAnalitico = criteria.list();
            for (int i = 0; i < listaNfeAnalitico.size(); i++) {
                valorTotalDebitos = valorTotalDebitos.add(listaNfeAnalitico.get(i).getSomaValorIcms());
            }

            // REGISTRO C390: REGISTRO ANALÍTICO DAS NOTAS FISCAIS DE VENDA A CONSUMIDOR (CÓDIGO 02)
            criteria = session.createCriteria(ViewSpedC390VO.class);
            criteria.add(Restrictions.between("dataEmissao", dataInicio.getTime(), dataFim.getTime()));
            List<ViewSpedC390VO> listaC390 = criteria.list();
            for (int i = 0; i < listaC390.size(); i++) {
                valorTotalDebitos = valorTotalDebitos.add(listaC390.get(i).getSomaIcms());
            }

            // REGISTRO C490: REGISTRO ANALÍTICO DO MOVIMENTO DIÁRIO (CÓDIGO 02, 2D e 60).
            criteria = session.createCriteria(ViewSpedC490VO.class);
            criteria.add(Restrictions.between("dataVenda", dataInicio.getTime(), dataFim.getTime()));
            List<ViewSpedC490VO> listaC490 = criteria.list();
            for (int i = 0; i < listaC490.size(); i++) {
                valorTotalDebitos = valorTotalDebitos.add(listaC490.get(i).getSomaIcms());
            }

            apuracaoIcms.setValorTotalDebito(valorTotalDebitos);

            /*
            Campo 06 - Validação: o valor informado deve corresponder ao somatório de todos os documentos fiscais de entrada que
            geram crédito de ICMS. O valor neste campo deve ser igual à soma dos VL_ICMS de todos os registros C190, C590, D190
            e D590. Deste somatório, estão excluídos os documentos fiscais com CFOP 1605 e incluídos os documentos fiscais com
            CFOP 5605. Os documentos fiscais devem ser somados conforme o período informado no registro E100 e a data informada
            no campo DT_E_S (C100, C500) ou campo DT_A_P (D100, D500), exceto se COD_SIT do documento for igual a “01”
            (extemporâneo) ou igual a 07 (NF Complementar extemporânea), cujo valor será somado no primeiro período de apuração
            informado no registro E100.
             */

            // REGISTRO C190: REGISTRO ANALÍTICO DO DOCUMENTO (CÓDIGO 01, 1B, 04 ,55 e 65).
            criteria = session.createCriteria(ViewSpedC190VO.class);
            criteria.add(Restrictions.between("dataEmissao", dataInicio.getTime(), dataFim.getTime()));
            listaNfeAnalitico = criteria.list();
            for (int i = 0; i < listaNfeAnalitico.size(); i++) {
                valorTotalCreditos = valorTotalCreditos.add(listaNfeAnalitico.get(i).getSomaValorIcms());
            }

            // REGISTRO C390: REGISTRO ANALÍTICO DAS NOTAS FISCAIS DE VENDA A CONSUMIDOR (CÓDIGO 02)
            criteria = session.createCriteria(ViewSpedC390VO.class);
            criteria.add(Restrictions.between("dataEmissao", dataInicio.getTime(), dataFim.getTime()));
            listaC390 = criteria.list();
            for (int i = 0; i < listaC390.size(); i++) {
                valorTotalCreditos = valorTotalCreditos.add(listaC390.get(i).getSomaIcms());
            }

            // REGISTRO C490: REGISTRO ANALÍTICO DO MOVIMENTO DIÁRIO (CÓDIGO 02, 2D e 60).
            criteria = session.createCriteria(ViewSpedC490VO.class);
            criteria.add(Restrictions.between("dataVenda", dataInicio.getTime(), dataFim.getTime()));
            listaC490 = criteria.list();
            for (int i = 0; i < listaC490.size(); i++) {
                valorTotalCreditos = valorTotalCreditos.add(listaC490.get(i).getSomaIcms());
            }

            apuracaoIcms.setValorTotalCredito(valorTotalCreditos);

            /*
            Campo 11 - Validação: o valor informado deve ser preenchido com base na expressão: soma do total de débitos
            (VL_TOT_DEBITOS) com total de ajustes (VL_AJ_DEBITOS +VL_TOT_AJ_DEBITOS) com total de estorno de crédito
            (VL_ESTORNOS_CRED) menos a soma do total de créditos (VL_TOT_CREDITOS) com total de ajuste de créditos
            (VL_,AJ_CREDITOS + VL_TOT_AJ_CREDITOS) com total de estorno de débito (VL_ESTORNOS_DEB) com saldo
            credor do período anterior (VL_SLD_CREDOR_ANT). Se o valor da expressão for maior ou igual a “0” (zero), então este
            valor deve ser informado neste campo e o campo 14 (VL_SLD_CREDOR_TRANSPORTAR) deve ser igual a “0” (zero).
            Se o valor da expressão for menor que “0” (zero), então este campo deve ser preenchido com “0” (zero) e o valor absoluto
            da expressão deve ser informado no campo VL_SLD_CREDOR_TRANSPORTAR.
             */

            valorSaldoApurado = valorTotalDebitos.subtract(valorTotalCreditos);
            if (apuracaoIcms.getValorSaldoCredorAnterior() != null) {
                valorSaldoApurado = valorSaldoApurado.add(apuracaoIcms.getValorSaldoCredorAnterior());
            }

            if (valorSaldoApurado.compareTo(BigDecimal.ZERO) >= 0) {
                apuracaoIcms.setValorSaldoApurado(valorSaldoApurado);
                apuracaoIcms.setValorSaldoCredorTransp(BigDecimal.ZERO);
            } else {
                apuracaoIcms.setValorSaldoApurado(BigDecimal.ZERO);
                apuracaoIcms.setValorSaldoCredorTransp(valorSaldoApurado.negate());
            }

            /*
            Campo 13 – Validação: o valor informado deve corresponder à diferença entre o campo VL_SLD_APURADO e o campo
            VL_TOT_DED. Se o resultado dessa operação for negativo, informe o valor zero neste campo, e o valor absoluto
            correspondente no campo VL_SLD_CREDOR_TRANSPORTAR. Verificar se a legislação da UF permite que dedução
            seja maior que o saldo devedor.
             */

            if (valorSaldoApurado.compareTo(BigDecimal.ZERO) >= 0) {
                apuracaoIcms.setValorIcmsRecolher(valorSaldoApurado);
            } else {
                apuracaoIcms.setValorIcmsRecolher(BigDecimal.ZERO);
            }

            session.saveOrUpdate(apuracaoIcms);

            session.getTransaction().commit();

            return new VOResponse(apuracaoIcms);
        } catch (Exception ex) {
            ex.printStackTrace();
            session.getTransaction().rollback();
            return new ErrorResponse(ex.getMessage());
        } finally {
            try {
                session.close();
            } catch (Exception ex1) {
            }
        }
    }

    public Response insert(Object inputPar, UserSessionParameters userSessionPars, HttpServletRequest request, HttpServletResponse response, HttpSession userSession, ServletContext context) {
        return null;
    }

    public Response update(Object inputPar, UserSessionParameters userSessionPars, HttpServletRequest request, HttpServletResponse response, HttpSession userSession, ServletContext context) {
        return null;
    }

    public Response delete(Object inputPar, UserSessionParameters userSessionPars, HttpServletRequest request, HttpServletResponse response, HttpSession userSession, ServletContext context) {
        return null;
    }
}
