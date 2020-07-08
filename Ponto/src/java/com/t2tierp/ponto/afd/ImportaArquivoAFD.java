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
package com.t2tierp.ponto.afd;

import com.t2tierp.ponto.java.AFDTipo3VO;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.io.FileUtils;
import org.jrimum.texgit.Record;

public class ImportaArquivoAFD extends AbstractFlatFile {

    public ImportaArquivoAFD() {
        super("/com/t2tierp/ponto/afd/afd.xml");
    }

    public List<AFDTipo3VO> leArquivoAFD(File arquivoRetorno) throws Exception {
        //busca as linhas do arquivo
        List<String> lines = FileUtils.readLines(arquivoRetorno);
        //ordena os registros
        for (int i = 0; i < lines.size(); i++) {
            if (i != lines.size() - 1) {
                lines.set(i, lines.get(i).substring(9, 10) + lines.get(i));
            }
        }
        Collections.sort(lines);
        for (int i = 0; i < lines.size(); i++) {
            if (i != lines.size() - 1) {
                lines.set(i, lines.get(i).substring(1, lines.get(i).length()));
            }
        }
        //le os registros
        read(lines);

        //instancia os objetos
        Record t1 = getFlatFile().getRecord("Tipo1");
        Tipo1 tipo1 = new Tipo1(t1);

        //instancia os objetos
        Collection<Record> listaTipo3 = getFlatFile().getRecords("Tipo3");

        Tipo3 tipo3;
        AFDTipo3VO afdTipo3;
        List<AFDTipo3VO> listaAfdTipo3 = new ArrayList<AFDTipo3VO>();
        for (Iterator<Record> i = listaTipo3.iterator(); i.hasNext();) {
            tipo3 = new Tipo3(i.next());

            afdTipo3 = new AFDTipo3VO();
            afdTipo3.setSequencial(tipo3.getNumeroSequencialRegistro());
            afdTipo3.setDataMarcacao(tipo3.getDataMarcacao());
            afdTipo3.setHoraMarcacao(tipo3.getHoraMarcacao().substring(0, 2)
                    + ":"
                    + tipo3.getHoraMarcacao().substring(2, 4)
                    + ":00");
            afdTipo3.setPisEmpregado(tipo3.getPisEmpregado());
            afdTipo3.setNumeroSerieRelogioPonto(tipo1.getNumeroFabricacaoRep().toString());
            afdTipo3.setDesconsiderar(false);
            afdTipo3.setTipoRegistro("O");

            listaAfdTipo3.add(afdTipo3);
        }
        return listaAfdTipo3;
    }
}
