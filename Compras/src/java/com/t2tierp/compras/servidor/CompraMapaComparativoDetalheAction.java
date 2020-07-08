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
package com.t2tierp.compras.servidor;

import com.t2tierp.compras.java.CompraCotacaoDetalheVO;
import com.t2tierp.compras.java.CompraCotacaoPedidoDetalheVO;
import com.t2tierp.padrao.java.Constantes;
import com.t2tierp.padrao.servidor.HibernateUtil;
import com.t2tierp.compras.java.CompraCotacaoVO;
import com.t2tierp.compras.java.CompraFornecedorCotacaoVO;
import com.t2tierp.compras.java.CompraPedidoDetalheVO;
import com.t2tierp.compras.java.CompraPedidoVO;
import com.t2tierp.compras.java.CompraTipoPedidoVO;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;
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

public class CompraMapaComparativoDetalheAction implements Action {

    public CompraMapaComparativoDetalheAction() {
    }

    public String getRequestName() {
        return "compraMapaComparativoDetalheAction";
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
        String pk = (String) pars[1];

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Criteria criteria = session.createCriteria(CompraCotacaoVO.class);
            criteria.add(Restrictions.eq("id", Integer.valueOf(pk)));

            CompraCotacaoVO compraCotacao = (CompraCotacaoVO) criteria.uniqueResult();

            return new VOResponse(compraCotacao);
        } catch (Exception ex) {
            ex.printStackTrace();
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
        Session session = null;
        try {
            Object[] pars = (Object[]) inputPar;
            CompraCotacaoVO compraCotacao = (CompraCotacaoVO) pars[1];
            List<CompraCotacaoDetalheVO> compraCotacaoDetalhe = (Vector) pars[2];
            List<CompraFornecedorCotacaoVO> fornecedores = (Vector) pars[3];

            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();

            //Pedido vindo de cotação sempre será marcado como Normal
            Criteria criteria = session.createCriteria(CompraTipoPedidoVO.class);
            criteria.add(Restrictions.eq("id", 1));

            CompraTipoPedidoVO tipoPedido = (CompraTipoPedidoVO) criteria.uniqueResult();
            if (tipoPedido == null) {
                return new ErrorResponse("Tipo de Pedido não cadastrado!");
            }

            List<CompraPedidoVO> listaPedido = new ArrayList<CompraPedidoVO>();
            List<CompraCotacaoPedidoDetalheVO> listaCotacaoPedidoDetalhe = new ArrayList<CompraCotacaoPedidoDetalheVO>();
            CompraPedidoVO pedido;
            Date dataPedido = new Date();
            boolean incluiPedido;
            BigDecimal subTotal;
            BigDecimal totalDesconto;
            BigDecimal totalGeral;
            BigDecimal totalBaseCalculoIcms;
            BigDecimal totalIcms;
            BigDecimal totalIpi;
            for (int i = 0; i < fornecedores.size(); i++) {
                pedido = new CompraPedidoVO();
                pedido.setCompraTipoPedido(tipoPedido);
                pedido.setDataPedido(dataPedido);
                pedido.setFornecedor(fornecedores.get(i).getFornecedor());
                pedido.setListaCompraPedidoDetalhe(new ArrayList<CompraPedidoDetalheVO>());
                incluiPedido = false;
                subTotal = BigDecimal.ZERO;
                totalDesconto = BigDecimal.ZERO;
                totalGeral = BigDecimal.ZERO;
                totalBaseCalculoIcms = BigDecimal.ZERO;
                totalIcms = BigDecimal.ZERO;
                totalIpi = BigDecimal.ZERO;
                //inclui os itens no pedido
                for (int j = 0; j < compraCotacaoDetalhe.size(); j++) {
                    if (compraCotacaoDetalhe.get(j).getCompraFornecedorCotacao().getId().intValue() == fornecedores.get(i).getId().intValue()) {
                        if (compraCotacaoDetalhe.get(j).getQuantidadePedida() != null) {
                            if (compraCotacaoDetalhe.get(j).getQuantidadePedida().compareTo(BigDecimal.ZERO) == 1) {
                                if (compraCotacaoDetalhe.get(j).getValorUnitario() == null) {
                                    return new ErrorResponse("Valor unitário do produto '" + compraCotacaoDetalhe.get(i).getProduto().getNome() + " não informado!");
                                }
                                incluiPedido = true;

                                CompraPedidoDetalheVO pedidoDetalhe = new CompraPedidoDetalheVO();
                                pedidoDetalhe.setCompraPedido(pedido);
                                pedidoDetalhe.setProduto(compraCotacaoDetalhe.get(j).getProduto());
                                pedidoDetalhe.setQuantidade(compraCotacaoDetalhe.get(j).getQuantidadePedida());
                                pedidoDetalhe.setValorUnitario(compraCotacaoDetalhe.get(j).getValorUnitario());
                                pedidoDetalhe.setValorSubtotal(compraCotacaoDetalhe.get(j).getValorSubtotal());
                                pedidoDetalhe.setTaxaDesconto(compraCotacaoDetalhe.get(j).getTaxaDesconto());
                                pedidoDetalhe.setValorDesconto(compraCotacaoDetalhe.get(j).getValorDesconto());
                                pedidoDetalhe.setValorTotal(compraCotacaoDetalhe.get(j).getValorTotal());
                                pedidoDetalhe.setBaseCalculoIcms(pedidoDetalhe.getValorTotal());
                                pedidoDetalhe.setAliquotaIcms(compraCotacaoDetalhe.get(j).getProduto().getAliquotaIcmsPaf());
                                if (pedidoDetalhe.getAliquotaIcms() != null && pedidoDetalhe.getBaseCalculoIcms() != null) {
                                    pedidoDetalhe.setValorIcms(pedidoDetalhe.getBaseCalculoIcms().multiply(pedidoDetalhe.getAliquotaIcms().divide(BigDecimal.valueOf(100), RoundingMode.HALF_DOWN)));
                                } else {
                                    pedidoDetalhe.setAliquotaIcms(BigDecimal.ZERO);
                                    pedidoDetalhe.setValorIcms(BigDecimal.ZERO);
                                }
                                pedidoDetalhe.setAliquotaIpi(BigDecimal.ZERO);
                                if (pedidoDetalhe.getAliquotaIpi() != null) {
                                    pedidoDetalhe.setValorIpi(pedidoDetalhe.getValorTotal().multiply(pedidoDetalhe.getAliquotaIpi().divide(BigDecimal.valueOf(100), RoundingMode.HALF_DOWN)));
                                } else {
                                    pedidoDetalhe.setAliquotaIpi(BigDecimal.ZERO);
                                    pedidoDetalhe.setValorIpi(BigDecimal.ZERO);
                                }
                                pedido.getListaCompraPedidoDetalhe().add(pedidoDetalhe);

                                subTotal = subTotal.add(pedidoDetalhe.getValorSubtotal());
                                totalDesconto = totalDesconto.add(pedidoDetalhe.getValorDesconto());
                                totalGeral = totalGeral.add(pedidoDetalhe.getValorTotal());
                                totalBaseCalculoIcms = totalBaseCalculoIcms.add(pedidoDetalhe.getBaseCalculoIcms());
                                totalIcms = totalIcms.add(pedidoDetalhe.getValorIcms());
                                totalIpi = totalIpi.add(pedidoDetalhe.getValorIpi());

                                CompraCotacaoPedidoDetalheVO cotacaoPedidoDetalhe = new CompraCotacaoPedidoDetalheVO();
                                cotacaoPedidoDetalhe.setCompraPedido(pedido);
                                cotacaoPedidoDetalhe.setCompraCotacaoDetalhe(compraCotacaoDetalhe.get(j));
                                cotacaoPedidoDetalhe.setQuantidadePedida(compraCotacaoDetalhe.get(j).getQuantidadePedida());
                                listaCotacaoPedidoDetalhe.add(cotacaoPedidoDetalhe);
                            }
                        }
                    }
                }
                pedido.setValorSubtotal(subTotal);
                pedido.setValorDesconto(totalDesconto);
                pedido.setValorTotalPedido(totalGeral);
                pedido.setBaseCalculoIcms(totalBaseCalculoIcms);
                pedido.setValorIcms(totalIcms);
                pedido.setValorTotalProdutos(totalGeral);
                pedido.setValorIpi(totalIpi);
                pedido.setValorTotalNf(totalGeral);

                if (incluiPedido) {
                    listaPedido.add(pedido);
                }
            }

            for (int i = 0; i < listaPedido.size(); i++) {
                session.save(listaPedido.get(i));
            }

            for (int i = 0; i < listaCotacaoPedidoDetalhe.size(); i++) {
                session.save(listaCotacaoPedidoDetalhe.get(i));
            }

            compraCotacao.setSituacao("F");
            session.merge(compraCotacao);

            session.getTransaction().commit();

            return new VOResponse(compraCotacao);
        } catch (Exception ex) {
            ex.printStackTrace();
            if (session != null) {
                session.getTransaction().rollback();
            }
            return new ErrorResponse(ex.getMessage());
        } finally {
            try {
                if (session != null) {
                    session.close();
                }
            } catch (Exception ex1) {
                ex1.printStackTrace();
            }
        }
    }

    public Response delete(Object inputPar, UserSessionParameters userSessionPars, HttpServletRequest request, HttpServletResponse response, HttpSession userSession, ServletContext context) {
        return null;
    }
}
