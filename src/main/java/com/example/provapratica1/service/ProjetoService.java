package com.example.provapratica1.service;


import com.example.provapratica1.entity.Projeto;
import com.example.provapratica1.exception.RegraNegocioException;
import com.example.provapratica1.repository.FuncionarioRepository;
import com.example.provapratica1.repository.ProjetoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

// Serviço para operações de projetos.
@Service
public class ProjetoService {
    // Injeta o repositório de projeto.
    @Autowired
    private ProjetoRepository projetoRepository;

    // Injeta o repositório de funcionário.
    @Autowired
    private FuncionarioRepository funcionarioRepository;

    // Lista todos os projetos.
    public List<Projeto> listarTodos() {
        return projetoRepository.findAll();
    }

    // Busca um projeto pelo ID.
    public Projeto buscarPorId(Long id) {
        return projetoRepository.findById(id)
                .orElseThrow(() -> new RegraNegocioException("Projeto não encontrado com o ID: " + id));
    }

    // Salva um projeto.
    public Projeto salvar(Projeto projeto) {
        if (projeto.getFuncionarioResponsavel() == null || projeto.getFuncionarioResponsavel().getId() == null) {
            throw new RegraNegocioException("O projeto deve estar vinculado a um funcionário responsável.");
        }

        Long funcionarioId = projeto.getFuncionarioResponsavel().getId();
        funcionarioRepository.findById(funcionarioId)
                .orElseThrow(() -> new RegraNegocioException("Funcionário responsável com ID " + funcionarioId + " não encontrado."));

        return projetoRepository.save(projeto);
    }

    // Exclui um projeto.
    public void excluir(Long id) {
        if (!projetoRepository.existsById(id)) {
            throw new RegraNegocioException("Tentativa de excluir um projeto que não existe. ID: " + id);
        }
        projetoRepository.deleteById(id);
    }
}
