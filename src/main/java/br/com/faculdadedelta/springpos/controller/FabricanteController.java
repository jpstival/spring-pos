package br.com.faculdadedelta.springpos.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.faculdadedelta.springpos.dao.FabricanteRepository;
import br.com.faculdadedelta.springpos.model.Fabricante;

@Controller
@RequestMapping("/fabricantes")
public class FabricanteController {
	
    @Autowired
    private FabricanteRepository repository;

    @RequestMapping(value="/novo", method = RequestMethod.GET)
    public ModelAndView novo() {
        ModelAndView mv = new ModelAndView("cadastro_fabricante");
        mv.addObject(new Fabricante());
        return mv;
        // return new ModelAndView("produto");
    }
    
    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView salvar(@Validated Fabricante fabricante, 
            Errors errors,
            RedirectAttributes redirectAttributes) {
        
        if (errors.hasErrors()) {
            return new ModelAndView("cadastro_fabricante");
        }
        
        this.repository.save(fabricante);
        
        redirectAttributes.addFlashAttribute("mensagem",
                "Fabricante salvo com sucesso!");
        
        return new ModelAndView("redirect:/fabricantes/novo");
    }
    
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView listar() {
		ModelAndView mv = new ModelAndView("lista_fabricantes");
		List<Fabricante> fabricantes = repository.findAll();

		mv.addObject("fabricantes", fabricantes);
		return mv;
	}

	@RequestMapping(value = "/editar/{id}", 
			method = RequestMethod.GET)
	public ModelAndView editar(@PathVariable("id") Long id) {
		Fabricante fabricante = this.repository.findOne(id);
		ModelAndView mv = new ModelAndView("cadastro_fabricante");
		mv.addObject(fabricante);
		return mv;
	}
	
	@RequestMapping(value = "/excluir/{id}", 
			method= RequestMethod.GET)
	public ModelAndView excluir(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
		try{
			this.repository.delete(id);
	        redirectAttributes.addFlashAttribute("sucesso",
	                "Fabricante excluido com sucesso!");
		}catch (Exception e) {
	        redirectAttributes.addFlashAttribute("falha",
	                "Não é possivel excluir esse fabricante!");
		}
		return new ModelAndView("redirect:/fabricantes");
	}

}
