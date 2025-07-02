package com.example.provapratica1.repository;


import com.example.provapratica1.entity.Projeto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjetoRepository extends JpaRepository<Projeto, Long> {
    /**
     * Verifica se existe algum projeto associado a um determinado ID de funcionário.
     * Útil para a regra de negócio que impede a exclusão de funcionários com projetos no
     * service
     */
    boolean existsByFuncionarioResponsavelId(Long funcionarioId);
}