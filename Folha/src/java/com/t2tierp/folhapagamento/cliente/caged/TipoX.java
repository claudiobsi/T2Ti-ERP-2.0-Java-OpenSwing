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
package com.t2tierp.folhapagamento.cliente.caged;

import java.util.Date;
import org.jrimum.texgit.Record;

public class TipoX {

    private Record record;

    public TipoX() {
    }

    public TipoX(Record record) {
        this.record = record;
    }

    public Integer getTipoIdentificacao() {
        return record.getValue("TipoIdentificacao");
    }

    public void setTipoIdentificacao(Integer tipoIdentificacao) {
        record.setValue("TipoIdentificacao", tipoIdentificacao);
    }

    public Long getNumeroIdentificadorEstabelecimento() {
        return record.getValue("NumeroIdentificadorEstabelecimento");
    }

    public void setNumeroIdentificadorEstabelecimento(Long numeroIdentificadorEstabelecimento) {
        record.setValue("NumeroIdentificadorEstabelecimento", numeroIdentificadorEstabelecimento);
    }

    public Integer getSequencia() {
        return record.getValue("Sequencia");
    }

    public void setSequencia(Integer sequencia) {
        record.setValue("Sequencia", sequencia);
    }

    public Long getPisPasep() {
        return record.getValue("PisPasep");
    }

    public void setPisPasep(Long pisPasep) {
        record.setValue("PisPasep", pisPasep);
    }

    public Integer getSexo() {
        return record.getValue("Sexo");
    }

    public void setSexo(Integer sexo) {
        record.setValue("Sexo", sexo);
    }

    public Date getDataNascimento() {
        return record.getValue("DataNascimento");
    }

    public void setDataNascimento(Date dataNascimento) {
        record.setValue("DataNascimento", dataNascimento);
    }

    public Integer getInstrucao() {
        return record.getValue("Instrucao");
    }

    public void setInstrucao(Integer instrucao) {
        record.setValue("Instrucao", instrucao);
    }

    public String getFiller() {
        return record.getValue("Filler");
    }

    public void setFiller(String filler) {
        record.setValue("Filler", filler);
    }

    public Integer getSalarioMensal() {
        return record.getValue("SalarioMensal");
    }

    public void setSalarioMensal(Integer salarioMensal) {
        record.setValue("SalarioMensal", salarioMensal);
    }

    public Integer getHorasTrabalhadas() {
        return record.getValue("HorasTrabalhadas");
    }

    public void setHorasTrabalhadas(Integer horasTrabalhadas) {
        record.setValue("HorasTrabalhadas", horasTrabalhadas);
    }

    public Date getDataAdmissao() {
        return record.getValue("DataAdmissao");
    }

    public void setDataAdmissao(Date dataAdmissao) {
        record.setValue("DataAdmissao", dataAdmissao);
    }

    public Integer getTipoMovimento() {
        return record.getValue("TipoMovimento");
    }

    public void setTipoMovimento(Integer tipoMovimento) {
        record.setValue("TipoMovimento", tipoMovimento);
    }

    public Integer getDiaDesligamento() {
        return record.getValue("DiaDesligamento");
    }

    public void setDiaDesligamento(Integer diaDesligamento) {
        record.setValue("DiaDesligamento", diaDesligamento);
    }

    public String getNomeEmpregado() {
        return record.getValue("NomeEmpregado");
    }

    public void setNomeEmpregado(String nomeEmpregado) {
        record.setValue("NomeEmpregado", nomeEmpregado);
    }

    public Integer getNumeroCtps() {
        return record.getValue("NumeroCtps");
    }

    public void setNumeroCtps(Integer numeroCtps) {
        record.setValue("NumeroCtps", numeroCtps);
    }

    public Integer getSerieCtps() {
        return record.getValue("SerieCtps");
    }

    public void setSerieCtps(Integer serieCtps) {
        record.setValue("SerieCtps", serieCtps);
    }

    public Integer getAtualizacao() {
        return record.getValue("Atualizacao");
    }

    public void setAtualizacao(Integer atualizacao) {
        record.setValue("Atualizacao", atualizacao);
    }

    public Integer getMesCompetenciaAcerto() {
        return record.getValue("MesCompetenciaAcerto");
    }

    public void setMesCompetenciaAcerto(Integer mesCompetenciaAcerto) {
        record.setValue("MesCompetenciaAcerto", mesCompetenciaAcerto);
    }

    public Integer getAnoCompetenciaAcerto() {
        return record.getValue("AnoCompetenciaAcerto");
    }

    public void setAnoCompetenciaAcerto(Integer anoCompetenciaAcerto) {
        record.setValue("AnoCompetenciaAcerto", anoCompetenciaAcerto);
    }

    public Integer getRacaCor() {
        return record.getValue("RacaCor");
    }

    public void setRacaCor(Integer racaCor) {
        record.setValue("RacaCor", racaCor);
    }

    public Integer getPessoaDeficiente() {
        return record.getValue("PessoaDeficiente");
    }

    public void setPessoaDeficiente(Integer pessoaDeficiente) {
        record.setValue("PessoaDeficiente", pessoaDeficiente);
    }

    public Integer getCBO2000() {
        return record.getValue("CBO2000");
    }

    public void setCBO2000(Integer cBO2000) {
        record.setValue("CBO2000", cBO2000);
    }

    public Integer getAprendiz() {
        return record.getValue("Aprendiz");
    }

    public void setAprendiz(Integer aprendiz) {
        record.setValue("Aprendiz", aprendiz);
    }

    public String getUFCtps() {
        return record.getValue("UFCtps");
    }

    public void setUFCtps(String uFCtps) {
        record.setValue("UFCtps", uFCtps);
    }

    public Integer getTipoDeficiencia() {
        return record.getValue("TipoDeficiencia");
    }

    public void setTipoDeficiencia(Integer tipoDeficiencia) {
        record.setValue("TipoDeficiencia", tipoDeficiencia);
    }

    public Long getCPF() {
        return record.getValue("CPF");
    }

    public void setCPF(Long cPF) {
        record.setValue("CPF", cPF);
    }

    public Integer getCEPResidenciaTrabalhador() {
        return record.getValue("CEPResidenciaTrabalhador");
    }

    public void setCEPResidenciaTrabalhador(Integer cEPResidenciaTrabalhador) {
        record.setValue("CEPResidenciaTrabalhador", cEPResidenciaTrabalhador);
    }

    public String getFiller3() {
        return record.getValue("Filler3");
    }

    public void setFiller3(String filler3) {
        record.setValue("Filler3", filler3);
    }

    public Record getRecord() {
        return record;
    }

    public void setRecord(Record record) {
        this.record = record;
    }
}
