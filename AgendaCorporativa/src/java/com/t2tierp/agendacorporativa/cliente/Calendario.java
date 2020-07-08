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
package com.t2tierp.agendacorporativa.cliente;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;

public class Calendario implements ActionListener, MouseListener {

    public static final int TIPO_MENSAL = 31;
    public static final int TIPO_SEMANAL = 7;
    public static final int TIPO_DIARIO = 1;

    private final DecimalFormat formatoDia = new DecimalFormat("00");

    private Calendar dataAgenda;
    private int tipoNavegacao = TIPO_MENSAL;
    private Map<JPanel, Calendar> listaAgenda;
    private final Map<Calendar, List<CalendarioEvento>> listaCompromisso;
    private final JPanel panelPrincipal;
    private JPanel panelCompromisso;
    private final CalendarioListener listener;

    public Calendario(JPanel panelPrincipal, CalendarioListener listener) {
        this.panelPrincipal = panelPrincipal;
        this.listener = listener;
        this.dataAgenda = Calendar.getInstance();
        this.listaCompromisso = new HashMap<>();
        montaCalendario();
    }

    public Calendario(JPanel panelPrincipal, CalendarioListener listener, Calendar dataAgenda, Map<Calendar, List<CalendarioEvento>> listaCompromisso) {
        this.panelPrincipal = panelPrincipal;
        this.listener = listener;
        this.dataAgenda = dataAgenda;
        this.listaCompromisso = listaCompromisso;
        montaCalendario();
    }

    private void montaCalendario() {
        listaAgenda = new HashMap<>();
        Calendar dataInicial = (Calendar) dataAgenda.clone();

        panelPrincipal.removeAll();
        panelPrincipal.setLayout(new GridBagLayout());

        GridBagConstraints gridBagConstraints;
        JPanel panelInterno;

        //painel com o mês e botões de navegação
        panelInterno = new JPanel();
        panelInterno.setLayout(new java.awt.GridBagLayout());
        panelInterno.setBorder(BorderFactory.createEtchedBorder());

        JButton botaoAnterior = new JButton();
        botaoAnterior.setText("<");
        botaoAnterior.setActionCommand("Anterior");
        botaoAnterior.addActionListener(this);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        panelInterno.add(botaoAnterior, gridBagConstraints);

        JButton botaoPosterior = new JButton();
        botaoPosterior.setText(">");
        botaoPosterior.setActionCommand("Posterior");
        botaoPosterior.addActionListener(this);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        panelInterno.add(botaoPosterior, gridBagConstraints);

        JLabel labelMes = new JLabel();
        labelMes.setHorizontalAlignment(SwingConstants.CENTER);
        labelMes.setText(new SimpleDateFormat("MMMM/yyyy").format(dataInicial.getTime()));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        panelInterno.add(labelMes, gridBagConstraints);

        JButton botaoDia = new JButton();
        botaoDia.setText("Dia");
        botaoDia.setActionCommand("Dia");
        botaoDia.addActionListener(this);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        panelInterno.add(botaoDia, gridBagConstraints);

        JButton botaoSemana = new JButton();
        botaoSemana.setText("Semana");
        botaoSemana.setActionCommand("Semana");
        botaoSemana.addActionListener(this);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        panelInterno.add(botaoSemana, gridBagConstraints);

        JButton botaoMes = new JButton();
        botaoMes.setText("Mês");
        botaoMes.setActionCommand("Mes");
        botaoMes.addActionListener(this);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        panelInterno.add(botaoMes, gridBagConstraints);

        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        if (tipoNavegacao != TIPO_DIARIO) {
            gridBagConstraints.gridwidth = 7;
        }
        panelPrincipal.add(panelInterno, gridBagConstraints);
        //****************

        //paineis com os dias da semana
        for (int i = 0; i < 7; i++) {
            panelInterno = new JPanel();
            panelInterno.setLayout(new java.awt.GridBagLayout());
            panelInterno.setBorder(BorderFactory.createEtchedBorder());

            JLabel labelDiaSemana = new JLabel();
            labelDiaSemana.setHorizontalAlignment(SwingConstants.CENTER);
            if (tipoNavegacao == TIPO_DIARIO) {
                labelDiaSemana.setText(getDiaSemana(dataInicial.get(Calendar.DAY_OF_WEEK)));
                i = 6;
            } else {
                labelDiaSemana.setText(getDiaSemana(i + 1));
            }
            gridBagConstraints = new java.awt.GridBagConstraints();
            gridBagConstraints.gridx = 0;
            gridBagConstraints.gridy = 0;
            gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
            gridBagConstraints.weightx = 1.0;
            gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 0);
            panelInterno.add(labelDiaSemana, gridBagConstraints);

            gridBagConstraints = new GridBagConstraints();
            if (tipoNavegacao == TIPO_DIARIO) {
                gridBagConstraints.gridx = 0;
            } else {
                gridBagConstraints.gridx = i;
            }
            gridBagConstraints.gridy = 1;
            gridBagConstraints.fill = GridBagConstraints.BOTH;
            gridBagConstraints.weightx = 1.0;
            panelPrincipal.add(panelInterno, gridBagConstraints);
        }

        //paineis com os dias do mês
        int mesInicial = dataInicial.get(Calendar.MONTH);
        if (tipoNavegacao == TIPO_MENSAL) {
            dataInicial.set(Calendar.DAY_OF_MONTH, 1);
        }
        if (tipoNavegacao != TIPO_DIARIO) {
            while (dataInicial.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY) {
                dataInicial.add(Calendar.DAY_OF_MONTH, -1);
            }
        }
        int qtdeLinhas = 6;
        int qtdeDias = 7;
        if (tipoNavegacao != TIPO_MENSAL) {
            qtdeLinhas = 1;
        }
        dataInicial.add(Calendar.DAY_OF_MONTH, -1);
        for (int i = 0; i < qtdeLinhas; i++) {
            if (tipoNavegacao == TIPO_DIARIO) {
                qtdeDias = 1;
            }
            for (int j = 0; j < qtdeDias; j++) {
                dataInicial.add(Calendar.DAY_OF_MONTH, 1);

                panelInterno = new JPanel();
                panelInterno.setLayout(new java.awt.GridBagLayout());
                panelInterno.addMouseListener(this);
                String dia = formatoDia.format(dataInicial.get(Calendar.DAY_OF_MONTH));
                if (dataInicial.get(Calendar.MONTH) == mesInicial) {
                    panelInterno.setBorder(BorderFactory.createTitledBorder(dia));
                } else {
                    panelInterno.setBorder(BorderFactory.createTitledBorder(null, dia, TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, Font.getFont("SansSerif"), Color.GRAY));
                }

                gridBagConstraints = new GridBagConstraints();
                gridBagConstraints.gridx = j;
                gridBagConstraints.gridy = i + 2;
                gridBagConstraints.fill = GridBagConstraints.BOTH;
                gridBagConstraints.weightx = 1.0;
                gridBagConstraints.weighty = 1.0;

                JScrollPane scrollPane = new JScrollPane(panelInterno);
                scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
                scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

                panelPrincipal.add(scrollPane, gridBagConstraints);

                listaAgenda.put(panelInterno, (Calendar) dataInicial.clone());
            }
        }
        panelPrincipal.revalidate();

        atualiza();
    }

    private String getDiaSemana(int diaSemana) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_WEEK, diaSemana);
        return calendar.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.SHORT_FORMAT, Locale.getDefault());
    }

    private void adicionaCompromisso(CalendarioEvento evento) {
        atualizaCompromisso(evento, true);
    }

    private void atualizaCompromisso(CalendarioEvento evento, boolean adicionaLista) {
        Component compromissos[] = panelCompromisso.getComponents();

        if (compromissos.length > 0) {
            GridBagLayout layout = (GridBagLayout) panelCompromisso.getLayout();
            GridBagConstraints constraints = layout.getConstraints(compromissos[compromissos.length - 1]);
            constraints.weighty = 0.0;

            panelCompromisso.remove(compromissos[compromissos.length - 1]);
            panelCompromisso.add(compromissos[compromissos.length - 1], constraints);
        }

        JLabel novoCompromisso;
        if (evento.getIcon() != null) {
            novoCompromisso = new JLabel(evento.getTexto(), evento.getIcon(), JLabel.LEFT);
        } else {
            novoCompromisso = new JLabel(evento.getTexto());
        }
        novoCompromisso.setOpaque(true);
        novoCompromisso.setBackground(evento.getBackground());
        novoCompromisso.setForeground(evento.getForeground());

        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = compromissos.length;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTH;

        panelCompromisso.add(novoCompromisso, gridBagConstraints);
        panelCompromisso.revalidate();

        if (adicionaLista) {
            if (listaCompromisso.get(listaAgenda.get(panelCompromisso)) == null) {
                listaCompromisso.put(listaAgenda.get(panelCompromisso), new ArrayList<>());
            }
            listaCompromisso.get(listaAgenda.get(panelCompromisso)).add(evento);
        }
    }

    private void atualiza() {
        for (JPanel p : listaAgenda.keySet()) {
            for (Calendar c : listaCompromisso.keySet()) {
                if (c.get(Calendar.DAY_OF_YEAR) == listaAgenda.get(p).get(Calendar.DAY_OF_YEAR)) {
                    try {
                        panelCompromisso = p;
                        if (listaCompromisso.get(c) != null) {
                            for (CalendarioEvento e : listaCompromisso.get(c)) {
                                atualizaCompromisso(e, false);
                            }
                        }
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            }
        }
        panelCompromisso = null;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "Anterior": {
                navegacao(true);
                break;
            }
            case "Posterior": {
                navegacao(false);
                break;
            }
            case "Dia": {
                tipoNavegacao = TIPO_DIARIO;
                montaCalendario();
                break;
            }
            case "Semana": {
                tipoNavegacao = TIPO_SEMANAL;
                montaCalendario();
                break;
            }
            case "Mes": {
                tipoNavegacao = TIPO_MENSAL;
                montaCalendario();
                break;
            }
        }
    }

    private void navegacao(boolean anterior) {
        if (tipoNavegacao == TIPO_DIARIO) {
            dataAgenda.add(Calendar.DAY_OF_MONTH, anterior ? -1 : 1);
        }
        if (tipoNavegacao == TIPO_SEMANAL) {
            dataAgenda.add(Calendar.DAY_OF_MONTH, anterior ? -7 : 7);
        }
        if (tipoNavegacao == TIPO_MENSAL) {
            dataAgenda.add(Calendar.MONTH, anterior ? -1 : 1);
        }
        montaCalendario();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getClickCount() == 2) {
            panelCompromisso = (JPanel) e.getSource();
            CalendarioEvento evento = listener.adicionaCompromisso(listaAgenda.get(panelCompromisso));
            if (evento != null) {
                adicionaCompromisso(evento);
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    public Calendar getDataAgenda() {
        return dataAgenda;
    }

    public void setDataAgenda(Calendar dataAgenda) {
        this.dataAgenda = dataAgenda;
    }

}
