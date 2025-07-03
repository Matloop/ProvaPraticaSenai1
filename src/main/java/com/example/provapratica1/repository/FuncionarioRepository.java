package com.example.provapratica1.repository;


import com.example.provapratica1.entity.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

// Repositório para a entidade Funcionario.
@Repository
public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {
    // Busca um funcionário pelo email.
    Optional<Funcionario> findByEmail(String email);
    // Verifica se uma matrícula já existe.
    boolean existsByMatricula(String matricula);
}