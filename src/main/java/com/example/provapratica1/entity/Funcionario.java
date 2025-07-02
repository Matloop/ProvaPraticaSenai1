package com.example.provapratica1.entity;

import com.example.provapratica1.entity.Pessoa;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;
//getters, setters e construtores
@Data
//facilita a comparação de objetos
@EqualsAndHashCode(callSuper = true)
//indica pro banco que é uma entidade
@Entity
@Table(name = "funcionario")
//liga o id dessa tabela para a tabela pessoa
@PrimaryKeyJoinColumn(name = "pessoa_id")
public class Funcionario extends Pessoa {

    @Column(nullable = false, unique = true, length = 10)
    private String matricula;

    @Column(nullable = false, length = 50)
    private String departamento;

    // Um funcionário pode ser responsável por muitos projetos
    @OneToMany(mappedBy = "funcionarioResponsavel", cascade = CascadeType.ALL)
    private List<Projeto> projetos;
}