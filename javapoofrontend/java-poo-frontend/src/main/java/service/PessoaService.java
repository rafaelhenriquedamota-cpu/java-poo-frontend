package service;


import model.Pessoa;
import java.util.ArrayList;
import java.util.List;

public class PessoaService {
    private List<Pessoa> pessoas = new ArrayList<>();
    private int contadorId = 1;

    public List<Pessoa> listar() {
        return pessoas;
    }

    public void salvar(Pessoa pessoa) {
        pessoa.setId(contadorId++);
        pessoas.add(pessoa);
    }

    public void atualizar(Pessoa pessoa) {
        for (int i = 0; i < pessoas.size(); i++) {
            if (pessoas.get(i).getId() == pessoa.getId()) {
                pessoas.set(i, pessoa);
                break;
            }
        }
    }

    public void deletar(int id) {
        pessoas.removeIf(p -> p.getId() == id);
    }
}
