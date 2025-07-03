package com.example.provapratica1.entity;

import jakarta.persistence.*;
import lombok.Data;

// Entidade que representa um projeto.
@Data
@Entity
@Table(name = "projeto")
public class Projeto {
    // ID do projeto.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Nome do projeto.
    @Column(nullable = false, length = 100)
    private String nome;

    // Descrição do projeto.
    @Column(nullable = false, columnDefinition = "TEXT")
    private String descricao;

    // Funcionário responsável pelo projeto.
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_funcionario", nullable = false)
    private Funcionario funcionarioResponsavel;
}