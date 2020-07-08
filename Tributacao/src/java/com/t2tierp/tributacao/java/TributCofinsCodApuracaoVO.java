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
package com.t2tierp.tributacao.java;

import com.t2tierp.cadastros.java.CstCofinsVO;
import com.t2tierp.cadastros.java.EfdTabela435VO;
import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import org.openswing.swing.message.receive.java.ValueObjectImpl;


@Entity
@Table(name = "TRIBUT_COFINS_COD_APURACAO")
public class TributCofinsCodApuracaoVO extends ValueObjectImpl implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "CST_COFINS")
    private String cstCofins;
    @Column(name = "EFD_TABELA_435")
    private String efdTabela435;
    @Column(name = "MODALIDADE_BASE_CALCULO")
    private String modalidadeBaseCalculo;
    @Column(name = "PORCENTO_BASE_CALCULO")
    private BigDecimal porcentoBaseCalculo;
    @Column(name = "ALIQUOTA_PORCENTO")
    private BigDecimal aliquotaPorcento;
    @Column(name = "ALIQUOTA_UNIDADE")
    private BigDecimal aliquotaUnidade;
    @Column(name = "VALOR_PRECO_MAXIMO")
    private BigDecimal valorPrecoMaximo;
    @Column(name = "VALOR_PAUTA_FISCAL")
    private BigDecimal valorPautaFiscal;
    @JoinColumn(name = "ID_TRIBUT_CONFIGURA_OF_GT", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private TributConfiguraOfGtVO tributConfiguraOfGt;
    @Transient
    private CstCofinsVO cstCofinsVO;
    @Transient
    private EfdTabela435VO codigoApuracaoEfdVO;

    public TributCofinsCodApuracaoVO() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCstCofins() {
        return cstCofins;
    }

    public void setCstCofins(String cstCofins) {
        this.cstCofins = cstCofins;
    }

    public String getEfdTabela435() {
        return efdTabela435;
    }

    public void setEfdTabela435(String efdTabela435) {
        this.efdTabela435 = efdTabela435;
    }

    public String getModalidadeBaseCalculo() {
        return modalidadeBaseCalculo;
    }

    public void setModalidadeBaseCalculo(String modalidadeBaseCalculo) {
        this.modalidadeBaseCalculo = modalidadeBaseCalculo;
    }

    public BigDecimal getPorcentoBaseCalculo() {
        return porcentoBaseCalculo;
    }

    public void setPorcentoBaseCalculo(BigDecimal porcentoBaseCalculo) {
        this.porcentoBaseCalculo = porcentoBaseCalculo;
    }

    public BigDecimal getAliquotaPorcento() {
        return aliquotaPorcento;
    }

    public void setAliquotaPorcento(BigDecimal aliquotaPorcento) {
        this.aliquotaPorcento = aliquotaPorcento;
    }

    public BigDecimal getAliquotaUnidade() {
        return aliquotaUnidade;
    }

    public void setAliquotaUnidade(BigDecimal aliquotaUnidade) {
        this.aliquotaUnidade = aliquotaUnidade;
    }

    public BigDecimal getValorPrecoMaximo() {
        return valorPrecoMaximo;
    }

    public void setValorPrecoMaximo(BigDecimal valorPrecoMaximo) {
        this.valorPrecoMaximo = valorPrecoMaximo;
    }

    public BigDecimal getValorPautaFiscal() {
        return valorPautaFiscal;
    }

    public void setValorPautaFiscal(BigDecimal valorPautaFiscal) {
        this.valorPautaFiscal = valorPautaFiscal;
    }

    public TributConfiguraOfGtVO getTributConfiguraOfGt() {
        return tributConfiguraOfGt;
    }

    public void setTributConfiguraOfGt(TributConfiguraOfGtVO tributConfiguraOfGt) {
        this.tributConfiguraOfGt = tributConfiguraOfGt;
    }


    @Override
    public String toString() {
        return "com.t2tierp.tributacao.java.TributCofinsCodApuracaoVO[id=" + id + "]";
    }

    public CstCofinsVO getCstCofinsVO() {
        return cstCofinsVO;
    }

    public void setCstCofinsVO(CstCofinsVO cstCofinsVO) {
        this.cstCofinsVO = cstCofinsVO;
    }

    public EfdTabela435VO getCodigoApuracaoEfdVO() {
        return codigoApuracaoEfdVO;
    }

    public void setCodigoApuracaoEfdVO(EfdTabela435VO codigoApuracaoEfdVO) {
        this.codigoApuracaoEfdVO = codigoApuracaoEfdVO;
    }
    
}
