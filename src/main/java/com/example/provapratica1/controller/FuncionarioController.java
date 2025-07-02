package com.example.provapratica1.controller;


import com.example.provapratica1.entity.Funcionario;
import com.example.provapratica1.exception.RegraNegocioException;
import com.example.provapratica1.service.FuncionarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/funcionarios")
public class FuncionarioController {

    @Autowired
    private FuncionarioService funcionarioService;

    /**
     * Exibe a lista de todos os funcionários.
     * @param model O modelo para a view.
     * @return O template da lista de funcionários.
     */
    @GetMapping
    public String listarFuncionarios(Model model) {
        model.addAttribute("funcionarios", funcionarioService.listarTodos());
        return "funcionarios/lista";
    }

    /**
     * Exibe o formulário para criar um novo funcionário.
     * @param model O modelo para o formulário.
     * @return O template do formulário de funcionário.
     */
    @GetMapping("/novo")
    public String novoFuncionarioForm(Model model) {
        model.addAttribute("funcionario", new Funcionario());
        return "funcionarios/formulario";
    }

    /**
     * Salva ou atualiza um funcionário, tratando as regras de negócio.
     * @param funcionario O funcionário vindo do formulário.
     * @param redirectAttributes Atributos para mensagens de sucesso ou erro.
     * @return Redireciona para a lista de funcionários.
     */
    @PostMapping("/salvar")
    public String salvarFuncionario(@ModelAttribute Funcionario funcionario, RedirectAttributes redirectAttributes) {
        try {
            funcionarioService.salvar(funcionario);
            redirectAttributes.addFlashAttribute("sucesso", "Funcionário salvo com sucesso!"); // REGRA 5
        } catch (RegraNegocioException e) {
            redirectAttributes.addFlashAttribute("erro", e.getMessage()); // REGRA 4
            redirectAttributes.addFlashAttribute("funcionario", funcionario);
            if (funcionario.getId() == null) {
                return "redirect:/funcionarios/novo";
            } else {
                return "redirect:/funcionarios/editar/" + funcionario.getId();
            }
        }
        return "redirect:/funcionarios";
    }

    /**
     * Exibe o formulário para editar um funcionário.
     * @param id O ID do funcionário.
     * @param model O modelo para a view.
     * @return O template do formulário de funcionário.
     */
    @GetMapping("/editar/{id}")
    public String editarFuncionarioForm(@PathVariable Long id, Model model) {
        model.addAttribute("funcionario", funcionarioService.buscarPorId(id));
        return "funcionarios/formulario";
    }

    /**
     * Tenta excluir um funcionário, respeitando a regra de negócio.
     * @param id O ID do funcionário a ser excluído.
     * @param redirectAttributes Atributos para mensagens.
     * @return Redireciona para a lista de funcionários.
     */
    @GetMapping("/excluir/{id}")
    public String excluirFuncionario(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            funcionarioService.excluir(id);
            redirectAttributes.addFlashAttribute("sucesso", "Funcionário excluído com sucesso!"); // REGRA 5
        } catch (RegraNegocioException e) {
            redirectAttributes.addFlashAttribute("erro", e.getMessage()); // REGRA 4
        }
        return "redirect:/funcionarios";
    }
}