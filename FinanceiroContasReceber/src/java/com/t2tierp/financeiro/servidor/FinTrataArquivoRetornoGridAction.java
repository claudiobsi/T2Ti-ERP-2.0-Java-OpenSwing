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
package com.t2tierp.financeiro.servidor;

import com.t2tierp.administrativo.java.AdmParametroVO;
import com.t2tierp.cadastros.java.EmpresaVO;
import com.t2tierp.edi.cnab240.bb.DetalheLoteSegmentoT;
import com.t2tierp.edi.cnab240.bb.DetalheLoteSegmentoU;
import com.t2tierp.padrao.servidor.HibernateUtil;
import com.t2tierp.padrao.java.Constantes;
import com.t2tierp.financeiro.java.FinParcelaReceberVO;
import com.t2tierp.financeiro.java.FinParcelaRecebimentoVO;
import com.t2tierp.financeiro.java.FinProcessamentoRetornoVO;
import com.t2tierp.financeiro.java.FinStatusParcelaVO;
import com.t2tierp.financeiro.java.FinTipoRecebimentoVO;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
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
import org.openswing.swing.message.receive.java.VOListResponse;
import org.openswing.swing.message.send.java.GridParams;
import org.openswing.swing.server.Action;
import org.openswing.swing.server.UserSessionParameters;

public class FinTrataArquivoRetornoGridAction implements Action {

    public FinTrataArquivoRetornoGridAction() {
    }

    public String getRequestName() {
        return "finTrataArquivoRetornoGridAction";
    }

    public Response executeCommand(Object inputPar, UserSessionParameters userSessionPars, HttpServletRequest request, HttpServletResponse response, HttpSession userSession, ServletContext context) {
        GridParams pars = (GridParams) inputPar;
        Integer acao = (Integer) pars.getOtherGridParams().get("acao");

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
        GridParams pars = (GridParams) inputPar;
        Collection<DetalheLoteSegmentoT> listaSegmentoT = (Collection) pars.getOtherGridParams().get("listaSegmentoT");
        EmpresaVO empresa = (EmpresaVO) pars.getOtherGridParams().get("empresa");

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();

            Criteria criteria = session.createCriteria(FinTipoRecebimentoVO.class);
            criteria.add(Restrictions.eq("tipo", "01"));
            FinTipoRecebimentoVO tipoRecebimento = (FinTipoRecebimentoVO) criteria.uniqueResult();
            if (tipoRecebimento == null) {
                throw new Exception("Tipo de recebimento não cadastrado. Entre em contato com a Software House");
            }

            criteria = session.createCriteria(AdmParametroVO.class);
            criteria.add(Restrictions.eq("empresa", empresa));
            AdmParametroVO admParametro = (AdmParametroVO) criteria.uniqueResult();

            FinStatusParcelaVO statusParcela = null;
            if (admParametro != null) {
                criteria = session.createCriteria(FinStatusParcelaVO.class);
                criteria.add(Restrictions.eq("id", admParametro.getFinParcelaQuitado()));
                
                statusParcela = (FinStatusParcelaVO) criteria.uniqueResult();
            }
            if (statusParcela == null) {
                throw new Exception("Status de parcela não cadastrado. Entre em contato com a Software House");
            }

            DetalheLoteSegmentoT segmentoT;
            DetalheLoteSegmentoU segmentoU;

            List<FinProcessamentoRetornoVO> listaRetorno = new ArrayList<FinProcessamentoRetornoVO>();
            FinProcessamentoRetornoVO retorno;
            FinParcelaReceberVO parcelaReceber;
            FinParcelaRecebimentoVO parcelaRecebimento;
            for (Iterator<DetalheLoteSegmentoT> i = listaSegmentoT.iterator(); i.hasNext();) {
                segmentoT = i.next();

                segmentoU = segmentoT.getSegmentoU();

                retorno = new FinProcessamentoRetornoVO();
                retorno.setNossoNumero(segmentoT.getIdentificadorTitulo());

                criteria = session.createCriteria(FinParcelaReceberVO.class);
                criteria.add(Restrictions.eq("boletoNossoNumero", segmentoT.getIdentificadorTitulo().trim()));

                parcelaReceber = (FinParcelaReceberVO) criteria.uniqueResult();

                if (parcelaReceber == null) {
                    retorno.setResultado("Nosso Número não localizado no banco de dados.");
                } else {
                    parcelaReceber.setFinStatusParcela(statusParcela);
                    session.update(parcelaReceber);

                    parcelaRecebimento = new FinParcelaRecebimentoVO();
                    parcelaRecebimento.setFinTipoRecebimento(tipoRecebimento);
                    parcelaRecebimento.setFinParcelaReceber(parcelaReceber);
                    parcelaRecebimento.setContaCaixa(parcelaReceber.getContaCaixa());
                    parcelaRecebimento.setDataRecebimento(segmentoU.getDataEfetivacaoCredito());
                    parcelaRecebimento.setValorMulta(BigDecimal.valueOf(segmentoU.getJurosMultaEncargos() / 100));
                    parcelaRecebimento.setValorDesconto(BigDecimal.valueOf(segmentoU.getValorDesconto() / 100));
                    parcelaRecebimento.setHistorico("RECEBIDO VIA EDI BANCARIO");
                    parcelaRecebimento.setValorRecebido(BigDecimal.valueOf(segmentoU.getValorLiquidoCreditado() / 100));
                    session.save(parcelaRecebimento);

                    retorno.setResultado("Título processado com sucesso.");
                }
                listaRetorno.add(retorno);
            }

            session.getTransaction().commit();

            return new VOListResponse(listaRetorno, false, listaRetorno.size());
        } catch (Exception ex) {
            if (session != null) {
                session.getTransaction().rollback();
            }
            ex.printStackTrace();
            return new ErrorResponse(ex.getMessage());
        } finally {
            try {
                if (session != null) {
                    session.close();
                }
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
