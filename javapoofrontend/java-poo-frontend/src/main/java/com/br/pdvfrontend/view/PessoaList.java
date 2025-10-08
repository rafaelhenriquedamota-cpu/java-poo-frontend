package com.br.pdvfrontend.view;



import model.Pessoa;
import service.PessoaService;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class PessoaList extends JFrame {
    private PessoaService service;
    private DefaultListModel<String> model;

    public PessoaList(PessoaService service) {
        this.service = service;

        setTitle("Lista de Pessoas");
        setSize(300, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        model = new DefaultListModel<>();
        JList<String> list = new JList<>(model);
        add(new JScrollPane(list), BorderLayout.CENTER);

        JButton btnAtualizar = new JButton("Atualizar");
        add(btnAtualizar, BorderLayout.SOUTH);

        btnAtualizar.addActionListener(e -> carregarLista());

        carregarLista();
    }

    private void carregarLista() {
        model.clear();
        List<Pessoa> pessoas = service.listar();
        for (Pessoa p : pessoas) {
            model.addElement(p.toString());
        }
    }
}
