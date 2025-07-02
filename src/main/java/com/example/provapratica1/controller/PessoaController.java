package com.example.provapratica1.controller;


import com.example.provapratica1.entity.Pessoa;
import com.example.provapratica1.exception.RegraNegocioException;
import com.example.provapratica1.service.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/pessoas")
public class PessoaController {

    @Autowired
    private PessoaService pessoaService;

    /**
     * Exibe a lista de todas as pessoas cadastradas.
     * @param model O modelo para adicionar atributos para a view.
     * @return O nome do template da lista de pessoas.
     */
    @GetMapping
    public String listarPessoas(Model model) {
        model.addAttribute("pessoas", pessoaService.listarTodos());
        return "pessoas/lista";
    }

    /**
     * Exibe o formulário para criar uma nova pessoa.
     * @param model O modelo para adicionar o objeto Pessoa ao formulário.
     * @return O nome do template do formulário de pessoa.
     */
    @GetMapping("/novo")
    public String novaPessoaForm(Model model) {
        model.addAttribute("pessoa", new Pessoa());
        return "pessoas/formulario";
    }

    /**
     * Salva uma pessoa nova ou atualiza uma existente.
     * @param pessoa O objeto Pessoa preenchido a partir do formulário.
     * @param redirectAttributes Atributos para redirecionamento, usados para mensagens.
     * @return Redireciona para a lista de pessoas.
     */
    @PostMapping("/salvar")
    public String salvarPessoa(@ModelAttribute Pessoa pessoa, RedirectAttributes redirectAttributes) {
        try {
            pessoaService.salvar(pessoa);
            redirectAttributes.addFlashAttribute("sucesso", "Pessoa salva com sucesso!"); // REGRA 5
        } catch (RegraNegocioException e) {
            redirectAttributes.addFlashAttribute("erro", e.getMessage()); // REGRA 4
            redirectAttributes.addFlashAttribute("pessoa", pessoa);
            if (pessoa.getId() == null) {
                return "redirect:/pessoas/novo";
            } else {
                return "redirect:/pessoas/editar/" + pessoa.getId();
            }
        }
        return "redirect:/pessoas";
    }

    /**
     * Exibe o formulário para editar uma pessoa existente.
     * @param id O ID da pessoa a ser editada.
     * @param model O modelo para adicionar os dados da pessoa ao formulário.
     * @return O nome do template do formulário de pessoa.
     */
    @GetMapping("/editar/{id}")
    public String editarPessoaForm(@PathVariable Long id, Model model) {
        model.addAttribute("pessoa", pessoaService.buscarPorId(id));
        return "pessoas/formulario";
    }

    /**
     * Exclui uma pessoa pelo seu ID.
     * @param id O ID da pessoa a ser excluída.
     * @param redirectAttributes Atributos para redirecionamento, usados para mensagens.
     * @return Redireciona para a lista de pessoas.
     */
    @GetMapping("/excluir/{id}")
    public String excluirPessoa(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            pessoaService.excluir(id);
            redirectAttributes.addFlashAttribute("sucesso", "Pessoa excluída com sucesso!"); // REGRA 5
        } catch (RegraNegocioException e) {
            redirectAttributes.addFlashAttribute("erro", e.getMessage()); // REGRA 4
        }
        return "redirect:/pessoas";
    }
}