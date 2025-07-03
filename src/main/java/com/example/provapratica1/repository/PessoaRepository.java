package com.example.provapratica1.repository;


import com.example.provapratica1.entity.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

// Repositório para a entidade Pessoa.
@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
    // Busca pessoas que não são funcionários.
    @Query("SELECT p FROM Pessoa p WHERE TYPE(p) = Pessoa")
    List<Pessoa> findPessoasNaoFuncionarios();
    // Busca uma pessoa pelo email.
    Optional<Pessoa> findByEmail(String email);
    // Verifica se um email já existe.
    boolean existsByEmail(String email);
}