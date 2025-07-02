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

@Controller
@RequestMapping("/projetos")
public class ProjetoController {

    @Autowired
    private ProjetoService projetoService;

    @Autowired
    private FuncionarioService funcionarioService; // Dependência para listar funcionários

    /**
     * Exibe a lista de todos os projetos.
     * @param model O modelo para a view.
     * @return O template da lista de projetos.
     */
    @GetMapping
    public String listarProjetos(Model model) {
        model.addAttribute("projetos", projetoService.listarTodos());
        return "projetos/lista";
    }

    /**
     * Prepara e exibe o formulário para um novo projeto.
     * Carrega a lista de funcionários para o dropdown de seleção.
     * @param model O modelo para a view.
     * @return O template do formulário de projeto.
     */
    @GetMapping("/novo")
    public String novoProjetoForm(Model model) {
        model.addAttribute("projeto", new Projeto());
        model.addAttribute("funcionarios", funcionarioService.listarTodos()); // Essencial para o <select>
        return "projetos/formulario";
    }

    /**
     * Salva ou atualiza um projeto, tratando as regras de negócio.
     * @param projeto O projeto vindo do formulário.
     * @param redirectAttributes Atributos para mensagens.
     * @return Redireciona para a lista de projetos.
     */
    @PostMapping("/salvar")
    public String salvarProjeto(@ModelAttribute Projeto projeto, RedirectAttributes redirectAttributes) {
        try {
            projetoService.salvar(projeto);
            redirectAttributes.addFlashAttribute("sucesso", "Projeto salvo com sucesso!"); // REGRA 5
        } catch (RegraNegocioException e) {
            redirectAttributes.addFlashAttribute("erro", e.getMessage()); // REGRA 4
            redirectAttributes.addFlashAttribute("projeto", projeto);
            if (projeto.getId() == null) {
                return "redirect:/projetos/novo";
            } else {
                return "redirect:/projetos/editar/" + projeto.getId();
            }
        }
        return "redirect:/projetos";
    }

    /**
     * Prepara e exibe o formulário para editar um projeto existente.
     * Carrega a lista de funcionários para o dropdown de seleção.
     * @param id O ID do projeto.
     * @param model O modelo para a view.
     * @return O template do formulário de projeto.
     */
    @GetMapping("/editar/{id}")
    public String editarProjetoForm(@PathVariable Long id, Model model) {
        model.addAttribute("projeto", projetoService.buscarPorId(id));
        model.addAttribute("funcionarios", funcionarioService.listarTodos()); // Essencial para o <select>
        return "projetos/formulario";
    }

    /**
     * Exclui um projeto.
     * @param id O ID do projeto.
     * @param redirectAttributes Atributos para mensagens.
     * @return Redireciona para a lista de projetos.
     */
    @GetMapping("/excluir/{id}")
    public String excluirProjeto(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            projetoService.excluir(id);
            redirectAttributes.addFlashAttribute("sucesso", "Projeto excluído com sucesso!"); // REGRA 5
        } catch (RegraNegocioException e) {
            redirectAttributes.addFlashAttribute("erro", e.getMessage()); // REGRA 4
        }
        return "redirect:/projetos";
    }
}