package com.br.pdvfrontend.view;



import model.Pessoa;
import service.PessoaService;

import javax.swing.*;
import java.awt.*;

class PessoaForm extends JFrame {
    private JTextField txtNome, txtEmail;
    private JButton btnSalvar;
    private PessoaService service;

    public PessoaForm(PessoaService service) {
        this.service = service;

        setTitle("Cadastro de Pessoa");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(3, 2));

        add(new JLabel("Nome:"));
        txtNome = new JTextField();
        add(txtNome);

        add(new JLabel("Email:"));
        txtEmail = new JTextField();
        add(txtEmail);

        btnSalvar = new JButton("Salvar");
        add(btnSalvar);

        btnSalvar.addActionListener(e -> {
            Pessoa p = new Pessoa();
            p.setNome(txtNome.getText());
            p.setEmail(txtEmail.getText());
            service.salvar(p);
            JOptionPane.showMessageDialog(this, "Pessoa salva com sucesso!");
            dispose();
        });
    }
}
