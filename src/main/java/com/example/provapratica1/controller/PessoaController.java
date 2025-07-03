package com.example.provapratica1.controller;

import com.example.provapratica1.entity.Pessoa;
import com.example.provapratica1.exception.RegraNegocioException;
import com.example.provapratica1.service.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

// Gerencia as operações das pessoas.
@Controller
@RequestMapping("/pessoas")
public class PessoaController {

    // Injeta o serviço de pessoa.
    @Autowired
    private PessoaService pessoaService;

    // Lista todas as pessoas.
    @GetMapping
    public String listarPessoas(Model model) {
        model.addAttribute("pessoas", pessoaService.listarTodos());
        return "pessoas/lista";
    }

    // Prepara o formulário para criar uma nova pessoa.
    @GetMapping("/novo")
    public String novaPessoaForm(Model model) {
        if (!model.containsAttribute("pessoa")) {
            model.addAttribute("pessoa", new Pessoa());
        }
        return "pessoas/formulario";
    }

    // Prepara o formulário para editar uma pessoa.
    @GetMapping("/editar/{id}")
    public String editarPessoaForm(@PathVariable Long id, Model model) {
        if (!model.containsAttribute("pessoa")) {
            model.addAttribute("pessoa", pessoaService.buscarPorId(id));
        }
        return "pessoas/formulario";
    }

    // Salva uma pessoa (criação ou atualização).
    @PostMapping("/salvar")
    public String salvarPessoa(@ModelAttribute Pessoa pessoa, RedirectAttributes redirectAttributes) {
        try {
            pessoaService.salvar(pessoa);
            redirectAttributes.addFlashAttribute("sucesso", "Pessoa salva com sucesso!");
        } catch (RegraNegocioException e) {
            redirectAttributes.addFlashAttribute("erro", e.getMessage());
            redirectAttributes.addFlashAttribute("pessoa", pessoa);

            if (pessoa.getId() == null) {
                return "redirect:/pessoas/novo";
            } else {
                return "redirect:/pessoas/editar/" + pessoa.getId();
            }
        }
        return "redirect:/pessoas";
    }

    // Exclui uma pessoa.
    @GetMapping("/excluir/{id}")
    public String excluirPessoa(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            pessoaService.excluir(id);
            redirectAttributes.addFlashAttribute("sucesso", "Pessoa excluída com sucesso!");
        } catch (RegraNegocioException e) {
            redirectAttributes.addFlashAttribute("erro", e.getMessage());
        }
        return "redirect:/pessoas";
    }
}