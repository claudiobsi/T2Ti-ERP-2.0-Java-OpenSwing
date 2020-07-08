/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.t2tierp.ged.java;

import java.io.Serializable;
import org.openswing.swing.message.receive.java.ValueObjectImpl;

/**
* <p>Title: T2Ti ERP</p>
* <p>Description:  VO que representa um arquivo.</p>
*
* <p>The MIT License</p>
*
* <p>Copyright: Copyright (C) 2010 T2Ti.COM
*
* Permission is hereby granted, free of charge, to any person
* obtaining a copy of this software and associated documentation
* files (the "Software"), to deal in the Software without
* restriction, including without limitation the rights to use,
* copy, modify, merge, publish, distribute, sublicense, and/or sell
* copies of the Software, and to permit persons to whom the
* Software is furnished to do so, subject to the following
* conditions:
*
* The above copyright notice and this permission notice shall be
* included in all copies or substantial portions of the Software.
*
* THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
* EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES
* OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
* NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT
* HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY,
* WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
* FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR
* OTHER DEALINGS IN THE SOFTWARE.
*
*        The author may be contacted at:
*            t2ti.com@gmail.com</p>
*
* @author Claudio de Barros (t2ti.com@gmail.com)
* @version 1.0
*/
public class ArquivoVO extends ValueObjectImpl implements Serializable {

    private static final long serialVersionUID = 1L;
    private byte[] file;
    private String extensao;
    private String hash;
    private Integer tamanho;
    private byte[] assinatura;

    /**
     * @return the file
     */
    public byte[] getFile() {
        return file;
    }

    /**
     * @param file the file to set
     */
    public void setFile(byte[] file) {
        this.file = file;
    }

    /**
     * @return the extensao
     */
    public String getExtensao() {
        return extensao;
    }

    /**
     * @param extensao the extensao to set
     */
    public void setExtensao(String extensao) {
        this.extensao = extensao;
    }

    /**
     * @return the hash
     */
    public String getHash() {
        return hash;
    }

    /**
     * @param hash the hash to set
     */
    public void setHash(String hash) {
        this.hash = hash;
    }

    /**
     * @return the tamanho
     */
    public Integer getTamanho() {
        return tamanho;
    }

    /**
     * @param tamanho the tamanho to set
     */
    public void setTamanho(Integer tamanho) {
        this.tamanho = tamanho;
    }

    /**
     * @return the assinatura
     */
    public byte[] getAssinatura() {
        return assinatura;
    }

    /**
     * @param assinatura the assinatura to set
     */
    public void setAssinatura(byte[] assinatura) {
        this.assinatura = assinatura;
    }
}
