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
package com.t2tierp.cadastros.servidor;

import com.t2tierp.cadastros.java.EmpresaVO;
import com.t2tierp.cadastros.java.PessoaContatoVO;
import com.t2tierp.cadastros.java.PessoaEnderecoVO;
import com.t2tierp.cadastros.java.PessoaFisicaVO;
import com.t2tierp.cadastros.java.PessoaJuridicaVO;
import com.t2tierp.cadastros.java.PessoaTelefoneVO;
import com.t2tierp.padrao.java.Constantes;
import com.t2tierp.padrao.servidor.HibernateUtil;
import com.t2tierp.cadastros.java.PessoaVO;
import java.util.ArrayList;
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

public class PessoaDetalheAction implements Action {

    public PessoaDetalheAction() {
    }

    public String getRequestName() {
        return "pessoaDetalheAction";
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
            Criteria criteria = session.createCriteria(PessoaVO.class);
            criteria.add(Restrictions.eq("id", Integer.valueOf(pk)));

            PessoaVO pessoa = (PessoaVO) criteria.uniqueResult();

            return new VOResponse(pessoa);
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
        Session session = null;
        try {
            Object[] pars = (Object[]) inputPar;
            PessoaVO pessoa = (PessoaVO) pars[1];
            EmpresaVO empresa = (EmpresaVO) pars[2];
            PessoaFisicaVO pessoaFisica = (PessoaFisicaVO) pars[3];
            PessoaJuridicaVO pessoaJuridica = (PessoaJuridicaVO) pars[4];
            List<PessoaContatoVO> listaContato = (Vector) pars[5];
            List<PessoaEnderecoVO> listaEndereco = (Vector) pars[6];
            List<PessoaTelefoneVO> listaTelefone = (Vector) pars[7];

            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();

            if (pessoa.getTipo().equals("F")) {
                Criteria criteria = session.createCriteria(PessoaFisicaVO.class);
                criteria.add(Restrictions.eq("cpf", pessoaFisica.getCpf()));
                if (criteria.uniqueResult() != null) {
                    throw new Exception("CPF já cadastrado na base.");
                }
            } else {
                Criteria criteria = session.createCriteria(PessoaJuridicaVO.class);
                criteria.add(Restrictions.eq("cnpj", pessoaJuridica.getCnpj()));
                if (criteria.uniqueResult() != null) {
                    throw new Exception("CNPJ já cadastrado na base.");
                }
            }

            for (int i = 0; i < listaContato.size(); i++) {
                listaContato.get(i).setPessoa(pessoa);
            }

            for (int i = 0; i < listaEndereco.size(); i++) {
                listaEndereco.get(i).setPessoa(pessoa);
            }

            for (int i = 0; i < listaTelefone.size(); i++) {
                listaTelefone.get(i).setPessoa(pessoa);
            }

            if (pessoa.getTipo().equals("F")) {
                pessoaFisica.setPessoa(pessoa);
                pessoa.setPessoaFisica(pessoaFisica);
            } else {
                pessoaJuridica.setPessoa(pessoa);
                pessoa.setPessoaJuridica(pessoaJuridica);
            }
            
            pessoa.setListaContato(listaContato);
            pessoa.setListaEndereco(listaEndereco);
            pessoa.setListaTelefone(listaTelefone);
            pessoa.setListaEmpresa(new ArrayList<EmpresaVO>());
            pessoa.getListaEmpresa().add(empresa);

            session.save(pessoa);

            session.getTransaction().commit();

            return new VOResponse(pessoa);
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

    public Response update(Object inputPar, UserSessionParameters userSessionPars, HttpServletRequest request, HttpServletResponse response, HttpSession userSession, ServletContext context) {
        Session session = null;
        try {
            Object[] pars = (Object[]) inputPar;
            PessoaVO pessoa = (PessoaVO) pars[2];
            PessoaFisicaVO pessoaFisica = (PessoaFisicaVO) pars[3];
            PessoaJuridicaVO pessoaJuridica = (PessoaJuridicaVO) pars[4];
            List<PessoaContatoVO> listaContato = (Vector) pars[5];
            List<PessoaEnderecoVO> listaEndereco = (Vector) pars[6];
            List<PessoaTelefoneVO> listaTelefone = (Vector) pars[7];

            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();

            if (pessoa.getTipo().equals("F")) {
                Criteria criteria = session.createCriteria(PessoaFisicaVO.class);
                criteria.add(Restrictions.eq("cpf", pessoaFisica.getCpf()));
                criteria.add(Restrictions.ne("pessoa", pessoa));
                if (criteria.uniqueResult() != null) {
                    throw new Exception("CPF já cadastrado na base.");
                }
            } else {
                Criteria criteria = session.createCriteria(PessoaJuridicaVO.class);
                criteria.add(Restrictions.eq("cnpj", pessoaJuridica.getCnpj()));
                criteria.add(Restrictions.ne("pessoa", pessoa));
                if (criteria.uniqueResult() != null) {
                    throw new Exception("CNPJ já cadastrado na base.");
                }
            }

            if (pessoa.getTipo().equals("F")) {
                pessoa.setPessoaFisica(pessoaFisica);
            } else {
                pessoa.setPessoaJuridica(pessoaJuridica);
            }

            pessoa.setListaContato(listaContato);
            pessoa.setListaEndereco(listaEndereco);
            pessoa.setListaTelefone(listaTelefone);

            session.merge(pessoa);

            session.getTransaction().commit();

            return new VOResponse(pessoa);
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
