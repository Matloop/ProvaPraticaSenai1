package com.example.provapratica1.entity;

import jakarta.persistence.*;
import lombok.Data;

// Entidade que representa uma pessoa.
@Data
@Entity
@Table(name = "pessoa")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "TIPO_PESSOA")
public class Pessoa {
    // ID da pessoa.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Nome da pessoa.
    @Column(nullable = false)
    private String nome;

    // Email da pessoa.
    @Column(nullable = false, unique = true)
    private String email;
}