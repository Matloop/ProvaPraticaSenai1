package com.example.provapratica1.controller;

import com.example.provapratica1.dto.FuncionarioDTO;
import com.example.provapratica1.entity.Funcionario;
import com.example.provapratica1.exception.RegraNegocioException;
import com.example.provapratica1.repository.PessoaRepository;
import com.example.provapratica1.service.FuncionarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

// Gerencia as operações dos funcionários.
@Controller
@RequestMapping("/funcionarios")
public class FuncionarioController {
    // Injeta o serviço de funcionário.
    @Autowired private FuncionarioService funcionarioService;
    // Injeta o repositório de pessoas.
    @Autowired private PessoaRepository pessoaRepository;

    // Lista todos os funcionários.
    @GetMapping
    public String listarFuncionarios(Model model) {
        model.addAttribute("funcionarios", funcionarioService.listarTodos());
        return "funcionarios/lista";
    }

    // Prepara o formulário para criar um novo funcionário.
    @GetMapping("/novo")
    public String novoFuncionarioForm(Model model) {
        model.addAttribute("funcionarioDto", new FuncionarioDTO());
        model.addAttribute("pessoasDisponiveis", pessoaRepository.findPessoasNaoFuncionarios());
        return "funcionarios/formulario";
    }

    // Prepara o formulário para editar um funcionário.
    @GetMapping("/editar/{id}")
    public String editarFuncionarioForm(@PathVariable Long id, Model model) {
        model.addAttribute("funcionario", funcionarioService.buscarPorId(id));
        return "funcionarios/formulario";
    }

    // Cria um novo funcionário.
    @PostMapping("/criar")
    public String criarFuncionario(@ModelAttribute("funcionarioDto") FuncionarioDTO dto, RedirectAttributes redirectAttributes) {
        try {
            funcionarioService.promoverPessoa(dto);
            redirectAttributes.addFlashAttribute("sucesso", "Funcionário cadastrado com sucesso!");
        } catch (RegraNegocioException e) {
            redirectAttributes.addFlashAttribute("erro", e.getMessage());
            return "redirect:/funcionarios/novo";
        }
        return "redirect:/funcionarios";
    }

    // Atualiza um funcionário existente.
    @PostMapping("/atualizar")
    public String atualizarFuncionario(@ModelAttribute Funcionario funcionario, RedirectAttributes redirectAttributes) {
        try {
            funcionarioService.atualizar(funcionario);
            redirectAttributes.addFlashAttribute("sucesso", "Funcionário atualizado com sucesso!");
        } catch (RegraNegocioException e) {
            redirectAttributes.addFlashAttribute("erro", e.getMessage());
            return "redirect:/funcionarios/editar/" + funcionario.getId();
        }
        return "redirect:/funcionarios";
    }

    // Exclui um funcionário.
    @GetMapping("/excluir/{id}")
    public String excluirFuncionario(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            funcionarioService.excluir(id);
            redirectAttributes.addFlashAttribute("sucesso", "Funcionário excluído com sucesso!");
        } catch (RegraNegocioException e) {
            redirectAttributes.addFlashAttribute("erro", e.getMessage());
        }
        return "redirect:/funcionarios";
    }
}