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
package com.t2tierp.ponto.cliente;

import com.t2tierp.cadastros.java.UsuarioVO;
import com.t2tierp.padrao.java.Biblioteca;
import com.t2tierp.padrao.java.Constantes;
import javax.swing.JOptionPane;
import org.openswing.swing.form.client.FormController;
import org.openswing.swing.mdi.client.MDIFrame;
import org.openswing.swing.message.receive.java.Response;
import org.openswing.swing.message.receive.java.ValueObject;
import org.openswing.swing.util.client.ClientUtils;
import org.openswing.swing.util.java.Consts;

public class PontoRegistraMarcacaoController extends FormController {

    private PontoRegistraMarcacao pontoMarcacao = null;
    private String acaoServidor;

    public PontoRegistraMarcacaoController() {
        this.acaoServidor = "pontoRegistraMarcacaoAction";
        pontoMarcacao = new PontoRegistraMarcacao(this);
        MDIFrame.add(pontoMarcacao);

        pontoMarcacao.getForm1().setMode(Consts.INSERT);
    }

    @Override
    public Response insertRecord(ValueObject newPersistentObject) throws Exception {
        UsuarioVO usuario = (UsuarioVO) newPersistentObject;
        usuario.setSenha(Biblioteca.MD5String(usuario.getLogin() + usuario.getSenha()));
        
        return ClientUtils.getData(acaoServidor, new Object[]{Constantes.INSERT, usuario});
    }

    @Override
    public void afterInsertData() {
        JOptionPane.showMessageDialog(pontoMarcacao, "Ponto registrado com sucesso!", "Informação do Sistema", JOptionPane.INFORMATION_MESSAGE);
        pontoMarcacao.dispose();
    }

}
