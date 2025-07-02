package com.example.provapratica1.entity;

import jakarta.persistence.*;
import lombok.Data;
//Getters,setters e construtores
@Data
//entidade no banco
@Entity
@Table(name = "pessoa")
@Inheritance(strategy = InheritanceType.JOINED) // Estratégia de herança
public class Pessoa {
    //criação do ID
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false, unique = true)
    private String email;
}