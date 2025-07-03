package com.example.provapratica1.entity;

import com.example.provapratica1.entity.Pessoa;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

// Entidade que representa um funcionário.
@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "funcionario")
@PrimaryKeyJoinColumn(name = "pessoa_id")
@DiscriminatorValue("FUNCIONARIO")
@NoArgsConstructor
public class Funcionario extends Pessoa {

    // Matrícula do funcionário.
    @Column(nullable = false, unique = true, length = 4)
    private String matricula;

    // Departamento do funcionário.
    @Column(nullable = false, length = 50)
    private String departamento;

    // Lista de projetos pelos quais o funcionário é responsável.
    @OneToMany(mappedBy = "funcionarioResponsavel", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Projeto> projetos;

    // Construtor para promover uma pessoa a funcionário.
    public Funcionario(Pessoa pessoa) {
        super();
        this.setId(pessoa.getId());
        this.setNome(pessoa.getNome());
        this.setEmail(pessoa.getEmail());
    }
}