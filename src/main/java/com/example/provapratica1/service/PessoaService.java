package com.example.provapratica1.service;


import com.example.provapratica1.entity.Pessoa;
import com.example.provapratica1.exception.RegraNegocioException;
import com.example.provapratica1.repository.FuncionarioRepository;
import com.example.provapratica1.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Camada de serviço para a entidade Pessoa.
 * Contém a lógica de negócio principal para operações de CRUD de Pessoas,
 * garantindo a integridade dos dados em relação a Funcionários.
 */
@Service
public class PessoaService {

    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Autowired
    private FuncionarioService funcionarioService;

    /**
     * Retorna uma lista de todas as pessoas cadastradas no banco de dados.
     * @return Uma lista de objetos Pessoa.
     */
    public List<Pessoa> listarTodos() {
        return pessoaRepository.findAll();
    }

    /**
     * Busca uma pessoa específica pelo seu ID.
     * @param id O identificador único da pessoa.
     * @return O objeto Pessoa encontrado, ou null se não existir.
     */
    public Pessoa buscarPorId(Long id) {
        return pessoaRepository.findById(id).orElse(null);
    }

    /**
     * Salva uma nova pessoa ou atualiza uma existente.
     * Este método é utilizado para criar pessoas que ainda não são funcionários.
     * @param pessoa O objeto Pessoa a ser salvo.
     * @return A entidade Pessoa salva, com o ID preenchido/atualizado.
     */
    public Pessoa salvar(Pessoa pessoa) {
        // Validações adicionais poderiam ser implementadas aqui (ex: formato de email).
        Pessoa pessoaSalva = pessoaRepository.save(pessoa);

        // REGRA 5: Mensagem de confirmação no console.
        System.out.println("SUCESSO: Pessoa '" + pessoaSalva.getNome() + "' (ID: " + pessoaSalva.getId() + ") salva com sucesso.");
        return pessoaSalva;
    }

    /**
     * Exclui uma pessoa pelo seu ID, aplicando as regras de negócio corretas.
     * Antes de excluir, o método verifica se a pessoa é também um funcionário.
     * Se for um funcionário, a exclusão é delegada para o FuncionarioService,
     * que contém a lógica para verificar se o funcionário está vinculado a projetos.
     * Isso impede a exclusão indevida de funcionários ativos através da interface de pessoas.
     *
     * @param id O ID da pessoa a ser excluída.
     * @throws RegraNegocioException se a pessoa não for encontrada ou se for um funcionário
     * que não pode ser excluído devido a regras de negócio (ex: vinculado a um projeto).
     */
    @Transactional // Garante que a verificação e a exclusão ocorram em uma única transação.
    public void excluir(Long id) {
        // Primeiro, garante que a pessoa existe.
        Pessoa pessoa = pessoaRepository.findById(id)
                .orElseThrow(() -> new RegraNegocioException("Pessoa com ID " + id + " não encontrada."));

        // VERIFICAÇÃO DE INTEGRIDADE: A pessoa a ser excluída é um funcionário?
        if (funcionarioRepository.existsById(id)) {
            // Se for um funcionário, a responsabilidade de exclusão é do FuncionarioService
            // para garantir a aplicação de todas as regras de negócio de funcionário.
            try {
                funcionarioService.excluir(id); // Este método já imprime sua própria msg de sucesso.

            } catch (RegraNegocioException e) {
                // Se o FuncionarioService lançar uma exceção (ex: "funcionário com projetos"),
                // nós a repassamos para o controller para que o usuário seja notificado.
                throw new RegraNegocioException(e.getMessage());
            }
        } else {
            // Se for apenas uma pessoa (não funcionária), a exclusão é direta e segura.
            pessoaRepository.deleteById(id);

            // REGRA 5: Mensagem de confirmação no console.
            System.out.println("SUCESSO: Pessoa '" + pessoa.getNome() + "' (ID: " + id + ") excluída com sucesso.");
        }
    }
}