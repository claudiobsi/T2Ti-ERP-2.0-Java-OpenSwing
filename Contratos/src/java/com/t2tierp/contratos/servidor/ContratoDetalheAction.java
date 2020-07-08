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
package com.t2tierp.contratos.servidor;

import com.t2tierp.contratos.java.ContratoHistFaturamentoVO;
import com.t2tierp.contratos.java.ContratoHistoricoReajusteVO;
import com.t2tierp.contratos.java.ContratoPrevFaturamentoVO;
import com.t2tierp.padrao.java.Constantes;
import com.t2tierp.padrao.servidor.HibernateUtil;
import com.t2tierp.contratos.java.ContratoVO;
import com.t2tierp.contratos.java.ViewContratoDadosContratanteVO;
import com.t2tierp.ged.java.ArquivoVO;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Vector;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.openswing.swing.message.receive.java.ErrorResponse;
import org.openswing.swing.message.receive.java.Response;
import org.openswing.swing.message.receive.java.VOResponse;
import org.openswing.swing.server.Action;
import org.openswing.swing.server.UserSessionParameters;

public class ContratoDetalheAction implements Action {

    public ContratoDetalheAction() {
    }

    public String getRequestName() {
        return "contratoDetalheAction";
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
            case Constantes.DOWNLOAD: {
                return download(inputPar, userSessionPars, request, response, userSession, context);
            }
            case 99: {
                return dadosContratante(inputPar, userSessionPars, request, response, userSession, context);
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
            Criteria criteria = session.createCriteria(ContratoVO.class);
            criteria.add(Restrictions.eq("id", Integer.valueOf(pk)));

            ContratoVO contrato = (ContratoVO) criteria.uniqueResult();

            return new VOResponse(contrato);
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
            ContratoVO contrato = (ContratoVO) pars[1];
            List<ContratoHistFaturamentoVO> historicoFaturamento = (Vector) pars[2];
            List<ContratoHistoricoReajusteVO> historicoReajuste = (Vector) pars[3];
            List<ContratoPrevFaturamentoVO> previsaoFaturamento = (Vector) pars[4];
            ArquivoVO arquivo = (ArquivoVO) pars[5];

            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();

            session.save(contrato);

            for (int i = 0; i < historicoFaturamento.size(); i++) {
                historicoFaturamento.get(i).setContrato(contrato);
                session.save(historicoFaturamento.get(i));
            }

            for (int i = 0; i < historicoReajuste.size(); i++) {
                historicoReajuste.get(i).setContrato(contrato);
                session.save(historicoReajuste.get(i));
            }

            for (int i = 0; i < previsaoFaturamento.size(); i++) {
                previsaoFaturamento.get(i).setContrato(contrato);
                session.save(previsaoFaturamento.get(i));
            }

            //salva o arquivo
            if (arquivo.getFile() != null) {
                String caminhoArquivo = context.getRealPath("/contratos") + System.getProperty("file.separator") + contrato.getId() + ".odt";
                File file = new File(caminhoArquivo);
                if (file.createNewFile()) {
                    salvaArquivo(caminhoArquivo, arquivo.getFile());
                }
            }

            session.getTransaction().commit();

            return new VOResponse(contrato);
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
            ContratoVO contrato = (ContratoVO) pars[2];
            List<ContratoHistFaturamentoVO> historicoFaturamento = (Vector) pars[3];
            List<ContratoHistoricoReajusteVO> historicoReajuste = (Vector) pars[4];
            List<ContratoPrevFaturamentoVO> previsaoFaturamento = (Vector) pars[5];
            ArquivoVO arquivo = (ArquivoVO) pars[6];

            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();

            session.update(contrato);

            //deleta o historico de faturmento
            String sql = "delete from CONTRATO_HIST_FATURAMENTO where ID_CONTRATO = :id";
            Query query = session.createSQLQuery(sql);
            query.setInteger("id", contrato.getId());
            query.executeUpdate();

            //deleta o historico de reajuste
            sql = "delete from CONTRATO_HISTORICO_REAJUSTE where ID_CONTRATO = :id";
            query = session.createSQLQuery(sql);
            query.setInteger("id", contrato.getId());
            query.executeUpdate();

            //deleta a previsao de faturamento
            sql = "delete from CONTRATO_PREV_FATURAMENTO where ID_CONTRATO = :id";
            query = session.createSQLQuery(sql);
            query.setInteger("id", contrato.getId());
            query.executeUpdate();

            for (int i = 0; i < historicoFaturamento.size(); i++) {
                historicoFaturamento.get(i).setContrato(contrato);
                session.save(historicoFaturamento.get(i));
            }

            for (int i = 0; i < historicoReajuste.size(); i++) {
                historicoReajuste.get(i).setContrato(contrato);
                session.save(historicoReajuste.get(i));
            }

            for (int i = 0; i < previsaoFaturamento.size(); i++) {
                previsaoFaturamento.get(i).setContrato(contrato);
                session.save(previsaoFaturamento.get(i));
            }

            //salva o arquivo
            if (arquivo.getFile() != null) {
                String caminhoArquivo = context.getRealPath("/contratos") + System.getProperty("file.separator") + contrato.getId() + ".odt";
                File file = new File(caminhoArquivo);
                if (file.exists()) {
                    file.delete();
                }
                if (file.createNewFile()) {
                    salvaArquivo(caminhoArquivo, arquivo.getFile());
                }
            }

            session.getTransaction().commit();

            return new VOResponse(contrato);
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

    public Response dadosContratante(Object inputPar, UserSessionParameters userSessionPars, HttpServletRequest request, HttpServletResponse response, HttpSession userSession, ServletContext context) {
        Session session = null;
        Object[] pars = (Object[]) inputPar;
        String pk = (String) pars[1];

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Criteria criteria = session.createCriteria(ViewContratoDadosContratanteVO.class);
            criteria.add(Restrictions.eq("idSolicitacao", Integer.valueOf(pk)));

            ViewContratoDadosContratanteVO dadosContratante = (ViewContratoDadosContratanteVO) criteria.uniqueResult();

            return new VOResponse(dadosContratante);
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

    public Response download(Object inputPar, UserSessionParameters userSessionPars, HttpServletRequest request, HttpServletResponse response, HttpSession userSession, ServletContext context) {
        try {
            Object[] pars = (Object[]) inputPar;
            Integer idContrato = (Integer) pars[1];

            String caminhoArquivo = context.getRealPath("/contratos") + System.getProperty("file.separator") + idContrato + ".odt";

            File file = new File(caminhoArquivo);
            ArquivoVO arquivo = new ArquivoVO();
            if (file.exists()) {
                arquivo.setFile(getBytesFromFile(file));
            }

            return new VOResponse(arquivo);
        } catch (Exception e) {
            e.printStackTrace();
            return new ErrorResponse(e.getMessage());
        }
    }

    private byte[] getBytesFromFile(File file) throws Exception {
        //converte o arquio em array de bytes
        InputStream is = new FileInputStream(file);
        // Get the size of the file
        long length = file.length();
        // Create the byte array to hold the data
        byte[] documento = new byte[(int) length];
        // Read in the bytes
        int offset = 0;
        int numRead = 0;
        while (offset < documento.length
                && (numRead = is.read(documento, offset, documento.length - offset)) >= 0) {
            offset += numRead;
        }
        // Ensure all the bytes have been read in
        if (offset < documento.length) {
            throw new IOException("Could not completely read file " + file.getName());
        }
        // Close the input stream and return bytes
        is.close();
        return documento;
    }

    private void salvaArquivo(String caminhoArquivo, byte[] file) throws Exception {
        FileOutputStream out = new FileOutputStream(caminhoArquivo);
        out.write(file);
        out.close();
    }
}
