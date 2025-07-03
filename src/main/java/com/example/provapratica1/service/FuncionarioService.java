package com.example.provapratica1.service;

import com.example.provapratica1.dto.FuncionarioDTO;
import com.example.provapratica1.entity.Funcionario;
import com.example.provapratica1.entity.Pessoa;
import com.example.provapratica1.exception.RegraNegocioException;
import com.example.provapratica1.repository.FuncionarioRepository;
import com.example.provapratica1.repository.PessoaRepository;
import com.example.provapratica1.repository.ProjetoRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

// Serviço para operações de funcionários.
@Service
public class FuncionarioService {

    // Injeta o repositório de funcionário.
    @Autowired
    private FuncionarioRepository funcionarioRepository;
    // Injeta o repositório de projeto.
    @Autowired
    private ProjetoRepository projetoRepository;
    // Injeta o repositório de pessoa.
    @Autowired
    private PessoaRepository pessoaRepository;

    // Injeta o Entity Manager.
    @PersistenceContext
    private EntityManager entityManager;

    // Lista todos os funcionários.
    public List<Funcionario> listarTodos() {
        return funcionarioRepository.findAll();
    }

    // Busca um funcionário pelo ID.
    public Funcionario buscarPorId(Long id) {
        return funcionarioRepository.findById(id)
                .orElseThrow(() -> new RegraNegocioException("Funcionário não encontrado com o ID: " + id));
    }

    // Promove uma pessoa a funcionário.
    @Transactional
    public Funcionario promoverPessoa(FuncionarioDTO dto) {
        Pessoa pessoaExistente = pessoaRepository.findById(dto.getIdPessoa())
                .orElseThrow(() -> new RegraNegocioException("Pessoa com ID " + dto.getIdPessoa() + " não encontrada."));

        if (pessoaExistente instanceof Funcionario) {
            throw new RegraNegocioException("A pessoa selecionada já é um funcionário.");
        }

        if (funcionarioRepository.existsByMatricula(dto.getMatricula())) {
            throw new RegraNegocioException("A matrícula '" + dto.getMatricula() + "' já está em uso por outro funcionário.");
        }

        if (dto.getMatricula() == null || !dto.getMatricula().matches("F\\d{3}")) {
            throw new RegraNegocioException("Matrícula inválida. Deve seguir o formato 'F' seguido de três números.");
        }

        pessoaRepository.delete(pessoaExistente);

        entityManager.flush();

        Funcionario novoFuncionario = new Funcionario();
        novoFuncionario.setNome(pessoaExistente.getNome());
        novoFuncionario.setEmail(pessoaExistente.getEmail());
        novoFuncionario.setMatricula(dto.getMatricula());
        novoFuncionario.setDepartamento(dto.getDepartamento());

        Funcionario funcionarioSalvo = funcionarioRepository.save(novoFuncionario);

        System.out.println("SUCESSO: Pessoa '" + funcionarioSalvo.getNome() + "' promovida a Funcionário com novo ID " + funcionarioSalvo.getId());
        return funcionarioSalvo;
    }

    // Atualiza um funcionário.
    @Transactional
    public Funcionario atualizar(Funcionario funcionario) {
        Funcionario funcExistente = buscarPorId(funcionario.getId());
        funcExistente.setMatricula(funcionario.getMatricula());
        funcExistente.setDepartamento(funcionario.getDepartamento());
        return funcionarioRepository.save(funcExistente);
    }

    // Exclui um funcionário.
    public void excluir(Long id) {
        Funcionario funcionario = buscarPorId(id);

        if (projetoRepository.existsByFuncionarioResponsavel_Id(id)) {
            throw new RegraNegocioException("Não é possível excluir o funcionário, pois ele está vinculado a um ou mais projetos.");
        }

        funcionarioRepository.deleteById(id);

        System.out.println("SUCESSO: Funcionário '" + funcionario.getNome() + "' (ID: " + id + ") excluído com sucesso.");
    }
}