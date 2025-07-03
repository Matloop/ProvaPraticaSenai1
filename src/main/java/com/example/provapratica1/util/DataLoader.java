package com.example.provapratica1.util; // Altere para o seu pacote base, se diferente

import com.example.provapratica1.entity.Funcionario;
import com.example.provapratica1.entity.Pessoa;
import com.example.provapratica1.entity.Projeto;
import com.example.provapratica1.repository.FuncionarioRepository;
import com.example.provapratica1.repository.PessoaRepository;
import com.example.provapratica1.repository.ProjetoRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class DataLoader implements CommandLineRunner {

    private final PessoaRepository pessoaRepository;
    private final FuncionarioRepository funcionarioRepository;
    private final ProjetoRepository projetoRepository;

    @PersistenceContext
    private EntityManager entityManager;

    public DataLoader(PessoaRepository pessoaRepository, FuncionarioRepository funcionarioRepository, ProjetoRepository projetoRepository) {
        this.pessoaRepository = pessoaRepository;
        this.funcionarioRepository = funcionarioRepository;
        this.projetoRepository = projetoRepository;
    }

    @Override
    @Transactional // Garante que todas as operações dentro deste método são atômicas
    public void run(String... args) throws Exception {
        System.out.println("Forçando a limpeza e re-inicialização do banco de dados...");

        // 1. Desabilita a checagem de chaves estrangeiras para permitir TRUNCATE
        entityManager.createNativeQuery("SET FOREIGN_KEY_CHECKS = 0").executeUpdate();

        // 2. Limpa todas as tabelas na ordem inversa de dependência
        entityManager.createNativeQuery("TRUNCATE TABLE projeto").executeUpdate();
        entityManager.createNativeQuery("TRUNCATE TABLE funcionario").executeUpdate();
        entityManager.createNativeQuery("TRUNCATE TABLE pessoa").executeUpdate();

        // 3. Habilita a checagem de chaves estrangeiras de volta
        entityManager.createNativeQuery("SET FOREIGN_KEY_CHECKS = 1").executeUpdate();

        System.out.println("Banco de dados limpo. Iniciando inserção de dados...");

        // **AQUI ESTÁ A MUDANÇA PRINCIPAL:**
        // Crie e salve os funcionários diretamente. O Hibernate cuidará da parte da Pessoa.
        Funcionario funcAna = new Funcionario();
        funcAna.setNome("Ana Silva");
        funcAna.setEmail("ana.silva@empresa.com");
        funcAna.setMatricula("F001");
        funcAna.setDepartamento("TI");
        funcionarioRepository.save(funcAna); // Isso irá inserir na tabela 'pessoa' e 'funcionario'

        Funcionario funcBruno = new Funcionario();
        funcBruno.setNome("Bruno Costa");
        funcBruno.setEmail("bruno.costa@empresa.com");
        funcBruno.setMatricula("F002");
        funcBruno.setDepartamento("RH");
        funcionarioRepository.save(funcBruno);

        Funcionario funcCarlos = new Funcionario();
        funcCarlos.setNome("Carlos Dias");
        funcCarlos.setEmail("carlos.dias@empresa.com");
        funcCarlos.setMatricula("F003");
        funcCarlos.setDepartamento("Marketing");
        funcionarioRepository.save(funcCarlos);

        // Inserindo Pessoas que NÃO SÃO FUNCIONÁRIOS (ainda como objetos Pessoa simples)
        Pessoa daniela = new Pessoa();
        daniela.setNome("Daniela Faria");
        daniela.setEmail("daniela.faria@empresa.com");
        pessoaRepository.save(daniela);

        Pessoa eduardo = new Pessoa();
        eduardo.setNome("Eduardo Lima");
        eduardo.setEmail("eduardo.lima@empresa.com");
        pessoaRepository.save(eduardo);

        // Inserindo Projetos (ligados aos Funcionários)
        Projeto projeto1 = new Projeto();
        projeto1.setNome("Sistema de Vendas Online");
        projeto1.setDescricao("Desenvolvimento de uma plataforma de e-commerce completa.");
        projeto1.setFuncionarioResponsavel(funcAna); // Vincula ao objeto Funcionario salvo
        projetoRepository.save(projeto1);

        Projeto projeto2 = new Projeto();
        projeto2.setNome("Campanha de Marketing Digital Q3");
        projeto2.setDescricao("Planejamento e execução da campanha de marketing para o terceiro trimestre.");
        projeto2.setFuncionarioResponsavel(funcCarlos);
        projetoRepository.save(projeto2);

        Projeto projeto3 = new Projeto();
        projeto3.setNome("Migração para Nuvem");
        projeto3.setDescricao("Mover toda a infraestrutura on-premise para a AWS.");
        projeto3.setFuncionarioResponsavel(funcAna);
        projetoRepository.save(projeto3);

        Projeto projeto4 = new Projeto();
        projeto4.setNome("Avaliação de Desempenho");
        projeto4.setDescricao("Implementação do novo sistema de avaliação de desempenho.");
        projeto4.setFuncionarioResponsavel(funcBruno);
        projetoRepository.save(projeto4);

        System.out.println("Dados iniciais populados com sucesso!");
    }
}