package com.example.provapratica1.dto;

import lombok.Data;

// Objeto de transferência de dados para Funcionário.
@Data
public class FuncionarioDTO {
    // ID da pessoa a ser promovida.
    private Long idPessoa;
    // Matrícula do funcionário.
    private String matricula;
    // Departamento do funcionário.
    private String departamento;
}