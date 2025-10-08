package view;

import service.PessoaService;

import javax.swing.*;

public class MainApp {
    public static void main(String[] args) {


        SwingUtilities.invokeLater(() -> {
            JFrame menu = new JFrame("PDV Frontend");
            menu.setSize(400, 200);
            menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            JButton btnForm = new JButton("Cadastrar Pessoa");
            JButton btnList = new JButton("Listar Pessoas");

            PessoaService service = null;
            btnForm.addActionListener(e -> new PessoaForm(service).setVisible(true));
            btnList.addActionListener(e -> new PessoaList(service).setVisible(true));

            JPanel panel = new JPanel();
            panel.add(btnForm);
            panel.add(btnList);

            menu.add(panel);
            menu.setVisible(true);
        });
    }
}
