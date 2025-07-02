package com.example.provapratica1.service;


import com.example.provapratica1.entity.Projeto;
import com.example.provapratica1.exception.RegraNegocioException;
import com.example.provapratica1.repository.FuncionarioRepository;
import com.example.provapratica1.repository.ProjetoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ProjetoService {
    @Autowired
    private ProjetoRepository projetoRepository;

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    public List<Projeto> listarTodos() {
        return projetoRepository.findAll();
    }

    public Projeto buscarPorId(Long id) {
        return projetoRepository.findById(id)
                .orElseThrow(() -> new RegraNegocioException("Projeto não encontrado com o ID: " + id));
    }

    /**
     * Salva um novo projeto ou atualiza um existente.
     * Regra de Negócio 2: Um projeto não pode ser criado sem vínculo com um funcionário existente.
     */
    public Projeto salvar(Projeto projeto) {
        // REGRA 2: Validar se o funcionário responsável foi informado.
        if (projeto.getFuncionarioResponsavel() == null || projeto.getFuncionarioResponsavel().getId() == null) {
            // REGRA 4: Mensagem de erro clara.
            throw new RegraNegocioException("O projeto deve estar vinculado a um funcionário responsável.");
        }

        // Verificação extra para garantir que o ID do funcionário realmente existe no banco.
        Long funcionarioId = projeto.getFuncionarioResponsavel().getId();
        funcionarioRepository.findById(funcionarioId)
                .orElseThrow(() -> new RegraNegocioException("Funcionário responsável com ID " + funcionarioId + " não encontrado."));

        return projetoRepository.save(projeto);
    }

    public void excluir(Long id) {
        if (!projetoRepository.existsById(id)) {
            throw new RegraNegocioException("Tentativa de excluir um projeto que não existe. ID: " + id);
        }
        projetoRepository.deleteById(id);
    }
}