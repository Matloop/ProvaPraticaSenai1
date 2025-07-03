package com.example.provapratica1.service;


import com.example.provapratica1.entity.Funcionario;
import com.example.provapratica1.entity.Pessoa;
import com.example.provapratica1.exception.RegraNegocioException;
import com.example.provapratica1.repository.FuncionarioRepository;
import com.example.provapratica1.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

// Serviço para operações de pessoas.
@Service
public class PessoaService {

    // Injeta o repositório de pessoa.
    @Autowired
    private PessoaRepository pessoaRepository;

    // Injeta o repositório de funcionário.
    @Autowired
    private FuncionarioRepository funcionarioRepository;

    // Injeta o serviço de funcionário.
    @Autowired
    private FuncionarioService funcionarioService;

    // Lista todas as pessoas.
    public List<Pessoa> listarTodos() {
        return pessoaRepository.findAll();
    }

    // Busca uma pessoa pelo ID.
    public Pessoa buscarPorId(Long id) {
        return pessoaRepository.findById(id).orElse(null);
    }

    // Salva uma pessoa.
    @Transactional
    public Pessoa salvar(Pessoa pessoaFormulario) {
        if (pessoaFormulario.getNome() == null || pessoaFormulario.getNome().trim().isEmpty()) {
            throw new RegraNegocioException("O nome da pessoa não pode ser vazio.");
        }
        if (pessoaFormulario.getEmail() == null || pessoaFormulario.getEmail().trim().isEmpty()) {
            throw new RegraNegocioException("O e-mail da pessoa não pode ser vazio.");
        }

        validarEmailUnico(pessoaFormulario);

        if (pessoaFormulario.getId() != null && funcionarioRepository.existsById(pessoaFormulario.getId())) {
            Funcionario funcParaAtualizar = funcionarioService.buscarPorId(pessoaFormulario.getId());
            return funcionarioRepository.save(funcParaAtualizar);
        } else {
            Pessoa pessoaSalva = pessoaRepository.save(pessoaFormulario);
            System.out.println("SUCESSO: Pessoa '" + pessoaSalva.getNome() + "' (ID: " + pessoaSalva.getId() + ") salva com sucesso.");
            return pessoaSalva;
        }
    }

    // Valida se o email é único.
    private void validarEmailUnico(Pessoa pessoa) {
        Optional<Pessoa> pessoaExistente = pessoaRepository.findByEmail(pessoa.getEmail());

        if (pessoaExistente.isPresent() && !pessoaExistente.get().getId().equals(pessoa.getId())) {
            throw new RegraNegocioException("O e-mail '" + pessoa.getEmail() + "' já está cadastrado no sistema.");
        }
    }

    // Exclui uma pessoa.
    @Transactional
    public void excluir(Long id) {
        Pessoa pessoa = buscarPorId(id);

        if (funcionarioRepository.existsById(id)) {
            try {
                funcionarioService.excluir(id);
            } catch (RegraNegocioException e) {
                throw new RegraNegocioException(e.getMessage());
            }
        } else {
            pessoaRepository.deleteById(id);
            System.out.println("SUCESSO: Pessoa '" + pessoa.getNome() + "' (ID: " + id + ") excluída com sucesso.");
        }
    }
}