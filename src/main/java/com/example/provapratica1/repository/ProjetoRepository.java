package com.example.provapratica1.repository;


import com.example.provapratica1.entity.Projeto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// Repositório para a entidade Projeto.
@Repository
public interface ProjetoRepository extends JpaRepository<Projeto, Long> {
    // Verifica se existe algum projeto associado a um determinado ID de funcionário.
    boolean existsByFuncionarioResponsavelId(Long funcionarioId);
    // Verifica se existe algum projeto associado a um determinado ID de funcionário.
    boolean existsByFuncionarioResponsavel_Id(Long funcionarioId);
}