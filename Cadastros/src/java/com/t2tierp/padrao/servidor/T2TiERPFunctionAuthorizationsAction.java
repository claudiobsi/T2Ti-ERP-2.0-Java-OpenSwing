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
package com.t2tierp.padrao.servidor;

import com.t2tierp.cadastros.java.PapelFuncaoVO;
import com.t2tierp.cadastros.java.UsuarioVO;
import java.util.ArrayList;
import java.util.List;
import org.openswing.swing.server.*;
import javax.servlet.*;
import javax.servlet.http.*;
import org.openswing.swing.message.receive.java.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.openswing.swing.mdi.java.ApplicationFunction;
import org.openswing.swing.internationalization.java.ResourcesFactory;
import org.openswing.swing.tree.java.OpenSwingTreeNode;

public class T2TiERPFunctionAuthorizationsAction implements Action {

    public T2TiERPFunctionAuthorizationsAction() {
    }

    /**
     * @return request name
     */
    public final String getRequestName() {
        return "getFunctionAuthorizations";
    }

    /**
     * Business logic to execute.
     */
    public final Response executeCommand(Object inputPar, UserSessionParameters userSessionPars, HttpServletRequest request, HttpServletResponse response, HttpSession userSession, ServletContext context) {
        //recupera as definições de internacionalização (objeto Resources)...
        ResourcesFactory factory = (ResourcesFactory) context.getAttribute(Controller.RESOURCES_FACTORY);
        String langId = userSessionPars.getLanguageId();

        /**
         * deve ser gerado de forma automática de acordo com as definições do
         * banco de dados.
         */
        DefaultMutableTreeNode root = new OpenSwingTreeNode();
        root.setUserObject("T2Ti ERP");
        DefaultTreeModel model = new DefaultTreeModel(root);

        ApplicationFunction n1 = new ApplicationFunction(factory.getResources(langId).getResource("Cadastros"), null);

        ApplicationFunction nPessoal = new ApplicationFunction(factory.getResources(langId).getResource("Pessoal"), null);

        ApplicationFunction n13 = new ApplicationFunction(factory.getResources(langId).getResource("Pessoa"), null);
        ApplicationFunction n131 = new ApplicationFunction(factory.getResources(langId).getResource("Estado Civil"), "estadoCivil", null, "getEstadoCivil");
        ApplicationFunction n132 = new ApplicationFunction(factory.getResources(langId).getResource("Pessoa"), "pessoa", null, "getPessoa");

        ApplicationFunction n14 = new ApplicationFunction(factory.getResources(langId).getResource("Cliente | Fornecedor | Transportadora"), null);
        ApplicationFunction n141 = new ApplicationFunction(factory.getResources(langId).getResource("Atividade"), "atividadeForCli", null, "getAtividadeForCli");
        ApplicationFunction n142 = new ApplicationFunction(factory.getResources(langId).getResource("Situação"), "situacaoForCli", null, "getSituacaoForCli");
        ApplicationFunction n143 = new ApplicationFunction(factory.getResources(langId).getResource("Cliente"), "cliente", null, "getCliente");
        ApplicationFunction n144 = new ApplicationFunction(factory.getResources(langId).getResource("Fornecedor"), "fornecedor", null, "getFornecedor");
        ApplicationFunction n145 = new ApplicationFunction(factory.getResources(langId).getResource("Transportadora"), "transportadora", null, "getTransportadora");

        ApplicationFunction n15 = new ApplicationFunction(factory.getResources(langId).getResource("Colaborador"), null);
        ApplicationFunction n151 = new ApplicationFunction(factory.getResources(langId).getResource("Tipo Admissão"), "tipoAdmissao", null, "getTipoAdmissao");
        ApplicationFunction n152 = new ApplicationFunction(factory.getResources(langId).getResource("Tipo Relacionamento"), "tipoRelacionamento", null, "getTipoRelacionamento");
        ApplicationFunction n153 = new ApplicationFunction(factory.getResources(langId).getResource("Situação"), "situacaoColaborador", null, "getSituacaoColaborador");
        ApplicationFunction n154 = new ApplicationFunction(factory.getResources(langId).getResource("Tipo Desligamento"), "tipoDesligamento", null, "getTipoDesligamento");
        ApplicationFunction n155 = new ApplicationFunction(factory.getResources(langId).getResource("Tipo"), "tipoColaborador", null, "getTipoColaborador");
        ApplicationFunction n156 = new ApplicationFunction(factory.getResources(langId).getResource("Cargo"), "tipoCargo", null, "getCargo");
        ApplicationFunction n157 = new ApplicationFunction(factory.getResources(langId).getResource("Nível Formação"), "nivelFormacao", null, "getNivelFormacao");
        ApplicationFunction n158 = new ApplicationFunction(factory.getResources(langId).getResource("Colaborador"), "colaborador", null, "getColaborador");

        ApplicationFunction n16 = new ApplicationFunction(factory.getResources(langId).getResource("Outros"), null);
        ApplicationFunction n161 = new ApplicationFunction(factory.getResources(langId).getResource("Contador"), "contador", null, "getContador");
        ApplicationFunction n162 = new ApplicationFunction(factory.getResources(langId).getResource("Sindicato"), "sindicato", null, "getSindicato");
        ApplicationFunction n163 = new ApplicationFunction(factory.getResources(langId).getResource("Convênio"), "convenio", null, "getConvenio");

        ApplicationFunction n17 = new ApplicationFunction(factory.getResources(langId).getResource("Diversos"), null);
        ApplicationFunction n171 = new ApplicationFunction(factory.getResources(langId).getResource("Setor"), "setor", null, "getSetor");
        ApplicationFunction n172 = new ApplicationFunction(factory.getResources(langId).getResource("Almoxarifado"), "almoxarifado", null, "getAlmoxarifado");
        ApplicationFunction n173 = new ApplicationFunction(factory.getResources(langId).getResource("Operadora Plano Saúde"), "operadoraPlanoSaude", null, "getOperadoraPlanoSaude");
        ApplicationFunction n174 = new ApplicationFunction(factory.getResources(langId).getResource("Operadora Cartão"), "operadoraCartao", null, "getOperadoraCartao");

        ApplicationFunction n18 = new ApplicationFunction(factory.getResources(langId).getResource("Endereço"), null);
        ApplicationFunction n181 = new ApplicationFunction(factory.getResources(langId).getResource("País"), "pais", null, "getPais");
        ApplicationFunction n182 = new ApplicationFunction(factory.getResources(langId).getResource("Estado"), "uf", null, "getUf");
        ApplicationFunction n183 = new ApplicationFunction(factory.getResources(langId).getResource("Município"), "municipio", null, "getMunicipio");
        ApplicationFunction n184 = new ApplicationFunction(factory.getResources(langId).getResource("CEP"), "cep", null, "getCep");

        ApplicationFunction n11 = new ApplicationFunction(factory.getResources(langId).getResource("Produto"), null);
        ApplicationFunction n111 = new ApplicationFunction(factory.getResources(langId).getResource("Marca"), "produtoMarca", null, "getProdutoMarca");
        ApplicationFunction n112 = new ApplicationFunction(factory.getResources(langId).getResource("NCM"), "ncm", null, "getNcm");
        ApplicationFunction n113 = new ApplicationFunction(factory.getResources(langId).getResource("Unidade"), "unidadeProduto", null, "getUnidadeProduto");
        ApplicationFunction n114 = new ApplicationFunction(factory.getResources(langId).getResource("Grupo"), "produtoGrupo", null, "getProdutoGrupo");
        ApplicationFunction n115 = new ApplicationFunction(factory.getResources(langId).getResource("Sub Grupo"), "produtoSubGrupo", null, "getProdutoSubGrupo");
        ApplicationFunction n116 = new ApplicationFunction(factory.getResources(langId).getResource("Produto"), "produto", null, "getProduto");
        
        ApplicationFunction n19 = new ApplicationFunction(factory.getResources(langId).getResource("Financeiro"), null);
        ApplicationFunction n191 = new ApplicationFunction(factory.getResources(langId).getResource("Banco"), "banco", null, "getBanco");
        ApplicationFunction n192 = new ApplicationFunction(factory.getResources(langId).getResource("Agência"), "agenciaBanco", null, "getAgenciaBanco");
        ApplicationFunction n193 = new ApplicationFunction(factory.getResources(langId).getResource("Conta Caixa"), "contaCaixa", null, "getContaCaixa");
        ApplicationFunction n194 = new ApplicationFunction(factory.getResources(langId).getResource("Talonário Cheque"), "talonarioCheque", null, "getTalonarioCheque");
        ApplicationFunction n195 = new ApplicationFunction(factory.getResources(langId).getResource("Cheque"), "cheque", null, "getCheque");

        ApplicationFunction n110 = new ApplicationFunction(factory.getResources(langId).getResource("Tabelas"), null);
        ApplicationFunction n1101 = new ApplicationFunction(factory.getResources(langId).getResource("CFOP"), "cfop", null, "getCfop");
        ApplicationFunction n1102 = new ApplicationFunction(factory.getResources(langId).getResource("Feriados"), "feriados", null, "getFeriados");
        
        n1.add(nPessoal);
        n1.add(n17);
        n1.add(n18);
        n1.add(n11);
        n1.add(n19);
        n1.add(n110);

        n11.add(n111);
        n11.add(n112);
        n11.add(n113);
        n11.add(n114);
        n11.add(n115);
        n11.add(n116);

        nPessoal.add(n13);
        nPessoal.add(n14);
        nPessoal.add(n15);
        nPessoal.add(n16);

        n13.add(n131);
        n13.add(n132);

        n14.add(n141);
        n14.add(n142);
        n14.add(n143);
        n14.add(n144);
        n14.add(n145);

        n15.add(n151);
        n15.add(n152);
        n15.add(n153);
        n15.add(n154);
        n15.add(n155);
        n15.add(n156);
        n15.add(n157);
        n15.add(n158);

        n16.add(n161);
        n16.add(n162);
        n16.add(n163);

        n17.add(n171);
        n17.add(n172);
        n17.add(n173);
        n17.add(n174);

        n18.add(n181);
        n18.add(n182);
        n18.add(n183);
        n18.add(n184);
        
        n19.add(n191);
        n19.add(n192);
        n19.add(n193);
        n19.add(n194);
        n19.add(n195);

        n110.add(n1101);
        n110.add(n1102);
        
        root.add(n1);

        UsuarioVO usuario = (UsuarioVO) inputPar;

        if (!usuario.getPapel().getAcessoCompleto().equals("S")) {
            for (int i = 0; i < root.getChildCount(); i++) {
                autorizaFuncoes((DefaultMutableTreeNode) root.getChildAt(i), buscaPapelFuncao(usuario));
            }
        }

        return new VOResponse(model);
    }

    private List<String> buscaPapelFuncao(UsuarioVO usuario) {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Criteria criteria = session.createCriteria(PapelFuncaoVO.class);
            criteria.add(Restrictions.eq("papel", usuario.getPapel()));

            List<PapelFuncaoVO> listaPapelFuncao = criteria.list();
            List<String> funcoes = new ArrayList<String>();
            for (int i = 0; i < listaPapelFuncao.size(); i++) {
                if (listaPapelFuncao.get(i).getPodeAlterar().equals("S")
                        || listaPapelFuncao.get(i).getPodeInserir().equals("S")
                        || listaPapelFuncao.get(i).getPodeExcluir().equals("S")) {
                    funcoes.add(listaPapelFuncao.get(i).getFuncao().getNome());
                } else if (listaPapelFuncao.get(i).getPodeConsultar().equals("S")) {
                    funcoes.add(listaPapelFuncao.get(i).getFuncao().getNome());
                }
            }
            return funcoes;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                session.close();
            } catch (Exception ex1) {
            }
        }
    }

    private void autorizaFuncoes(DefaultMutableTreeNode pasta, List<String> funcoes) {
        ApplicationFunction funcao;
        for (int i = 0; i < pasta.getChildCount(); i++) {
            funcao = (ApplicationFunction) pasta.getChildAt(i);
            if (funcao.isFolder()) {
                for (int j = 0; j < funcao.getChildCount(); j++) {
                    autorizaFuncoes(funcao, funcoes);
                }
            } else {
                if (!funcoes.contains(funcao.getFunctionId())) {
                    pasta.remove(i);
                    ApplicationFunction appFunction = new ApplicationFunction(funcao.getDescription() + "*", "funcaoPadrao", null, "getFuncaoPadrao");
                    pasta.insert(appFunction, i);
                }
            }
        }
    }
}
