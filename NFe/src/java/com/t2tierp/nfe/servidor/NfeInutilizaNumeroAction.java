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
package com.t2tierp.nfe.servidor;

import com.t2tierp.nfe.java.RespostaSefaz;
import com.t2tierp.padrao.java.Certificado;
import java.io.ByteArrayInputStream;
import java.security.KeyStore;
import java.util.Map;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.openswing.swing.message.receive.java.ErrorResponse;
import org.openswing.swing.message.receive.java.Response;
import org.openswing.swing.message.receive.java.VOResponse;
import org.openswing.swing.server.Action;
import org.openswing.swing.server.UserSessionParameters;

public class NfeInutilizaNumeroAction implements Action {

    @Override
    public String getRequestName() {
        return "nfeInutilizaNumero";
    }

    @Override
    public Response executeCommand(Object inputPar, UserSessionParameters userSessionPars, HttpServletRequest request, HttpServletResponse response, HttpSession userSession, ServletContext context) {
        try {
            Object[] pars = (Object[]) inputPar;
            
            Certificado certificado = (Certificado) pars[0];
            String justificativa = (String) pars[1];
            String cnpj = (String) pars[2];
            String numeroInicial = (String) pars[3];
            String numeroFinal = (String) pars[4];
                    
            KeyStore ks = KeyStore.getInstance("PKCS12");
            ks.load(new ByteArrayInputStream(certificado.getArquivo()), certificado.getSenha());
            String alias = ks.aliases().nextElement();

            InutilizaNumero inutilizaNumero = new InutilizaNumero();
            RespostaSefaz resposta = new RespostaSefaz();
            Map consulta = inutilizaNumero.inutiliza(alias, ks, certificado.getSenha(), justificativa, cnpj, numeroInicial, numeroFinal);
            resposta.setAutorizado((Boolean)consulta.get("nfeInutilizada"));
            resposta.setResposta(consulta.get("motivo").toString());
            return new VOResponse(resposta);
        } catch (Exception e) {
            e.printStackTrace();
            return new ErrorResponse(e.getMessage());
        }
    }

}
