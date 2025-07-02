package com.example.provapratica1.service;


import com.example.provapratica1.entity.Funcionario;
import com.example.provapratica1.exception.RegraNegocioException;
import com.example.provapratica1.repository.FuncionarioRepository;
import com.example.provapratica1.repository.ProjetoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class FuncionarioService {
    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Autowired
    private ProjetoRepository projetoRepository;

    public List<Funcionario> listarTodos() {
        return funcionarioRepository.findAll();
    }

    public Funcionario buscarPorId(Long id) {
        return funcionarioRepository.findById(id)
                .orElseThrow(() -> new RegraNegocioException("Funcionário não encontrado com o ID: " + id));
    }

    /**
     * Salva um novo funcionário ou atualiza um existente.
     * Regra de Negócio 1: É inerentemente satisfeita pela herança. Ao salvar um Funcionario,
     * uma Pessoa correspondente é criada ou atualizada.
     */
    public Funcionario salvar(Funcionario funcionario) {
        // Validação da matrícula
        if (funcionario.getMatricula() == null || !funcionario.getMatricula().matches("F\\d{3}")) {
            throw new RegraNegocioException("Matrícula inválida. Deve seguir o formato 'F' seguido de três números (ex: F007).");
        }
        Funcionario funcionarioSalvo = funcionarioRepository.save(funcionario);
        // REGRA 5: Mensagem de confirmação no console
        System.out.println("SUCESSO: Funcionário '" + funcionarioSalvo.getNome() + "' (ID: " + funcionarioSalvo.getId() + ") salvo com sucesso.");
        return funcionarioSalvo;

    }

    /**
     * Exclui um funcionário pelo ID.
     * Regra de Negócio 3: Proibir a exclusão de um funcionário que esteja vinculado a algum projeto.
     */
    public void excluir(Long id) {
        Funcionario funcionario = buscarPorId(id);
        // Verifica se o funcionário a ser excluído existe.
        if (!funcionarioRepository.existsById(id)) {
            throw new RegraNegocioException("Tentativa de excluir um funcionário que não existe. ID: " + id);
        }

        // REGRA 3: Verifica se o funcionário está vinculado a algum projeto.
        if (projetoRepository.existsByFuncionarioResponsavelId(id)) {
            // REGRA 4: Mensagem de erro clara.
            throw new RegraNegocioException("Não é possível excluir o funcionário, pois ele está vinculado a um ou mais projetos.");
        }

        funcionarioRepository.deleteById(id);
        // REGRA 5: Mensagem de confirmação no console
        System.out.println("SUCESSO: Funcionário '" + funcionario.getNome() + "' (ID: " + id + ") excluído com sucesso.");
    }
}