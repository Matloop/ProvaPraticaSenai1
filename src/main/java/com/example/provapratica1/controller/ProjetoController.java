package com.example.provapratica1.controller;

import com.example.provapratica1.entity.Projeto;
import com.example.provapratica1.exception.RegraNegocioException;
import com.example.provapratica1.service.FuncionarioService;
import com.example.provapratica1.service.ProjetoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

// Gerencia as operações dos projetos.
@Controller
@RequestMapping("/projetos")
public class ProjetoController {

    // Injeta o serviço de projeto.
    @Autowired
    private ProjetoService projetoService;

    // Injeta o serviço de funcionário.
    @Autowired
    private FuncionarioService funcionarioService;

    // Lista todos os projetos.
    @GetMapping
    public String listarProjetos(Model model) {
        model.addAttribute("projetos", projetoService.listarTodos());
        return "projetos/lista";
    }

    // Prepara o formulário para criar um novo projeto.
    @GetMapping("/novo")
    public String novoProjetoForm(Model model) {
        if (!model.containsAttribute("projeto")) {
            model.addAttribute("projeto", new Projeto());
        }
        model.addAttribute("funcionarios", funcionarioService.listarTodos());
        return "projetos/formulario";
    }

    // Salva um projeto (criação ou atualização).
    @PostMapping("/salvar")
    public String salvarProjeto(@ModelAttribute Projeto projeto, RedirectAttributes redirectAttributes) {
        try {
            projetoService.salvar(projeto);
            redirectAttributes.addFlashAttribute("sucesso", "Projeto salvo com sucesso!");
        } catch (RegraNegocioException e) {
            redirectAttributes.addFlashAttribute("erro", e.getMessage());
            redirectAttributes.addFlashAttribute("projeto", projeto);
            if (projeto.getId() == null) {
                return "redirect:/projetos/novo";
            } else {
                return "redirect:/projetos/editar/" + projeto.getId();
            }
        }
        return "redirect:/projetos";
    }

    // Prepara o formulário para editar um projeto.
    @GetMapping("/editar/{id}")
    public String editarProjetoForm(@PathVariable Long id, Model model) {
        if (!model.containsAttribute("projeto")) {
            model.addAttribute("projeto", projetoService.buscarPorId(id));
        }
        model.addAttribute("funcionarios", funcionarioService.listarTodos());
        return "projetos/formulario";
    }

    // Exclui um projeto.
    @GetMapping("/excluir/{id}")
    public String excluirProjeto(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            projetoService.excluir(id);
            redirectAttributes.addFlashAttribute("sucesso", "Projeto excluído com sucesso!");
        } catch (RegraNegocioException e) {
            redirectAttributes.addFlashAttribute("erro", e.getMessage());
        }
        return "redirect:/projetos";
    }
}