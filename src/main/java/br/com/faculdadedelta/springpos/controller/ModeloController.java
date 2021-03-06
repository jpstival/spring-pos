package br.com.faculdadedelta.springpos.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.faculdadedelta.springpos.dao.FabricanteRepository;
import br.com.faculdadedelta.springpos.dao.ModeloRepository;
import br.com.faculdadedelta.springpos.model.Fabricante;
import br.com.faculdadedelta.springpos.model.Modelo;
import br.com.faculdadedelta.springpos.model.type.Categoria;

@Controller
@RequestMapping("modelos")
public class ModeloController {

	@Autowired
	private ModeloRepository repository;

	@Autowired
	private FabricanteRepository fr;
	
	@RequestMapping(value = "/novo", method = RequestMethod.GET)
	public ModelAndView novo() {
		ModelAndView mv = new ModelAndView("cadastro_modelo");
		mv.addObject(new Modelo());
		return mv;
		// return new ModelAndView("produto");
	}

	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView salvar(@Validated Modelo modelo, Errors errors, RedirectAttributes redirectAttributes) {

		if (errors.hasErrors()) {
			return new ModelAndView("cadastro_modelo");
		}

		this.repository.save(modelo);

		redirectAttributes.addFlashAttribute("mensagem", "Modelo salvo com sucesso!");

		return new ModelAndView("redirect:/modelos/novo");
	}

	@ModelAttribute("todasCategorias")
	public Categoria[] todasCategorias() {
		return Categoria.values();
	}
	
	@ModelAttribute("todosFabricantes")
	public List<Fabricante> todosFabricantes() {
		List<Fabricante> fabricantes = this.fr.findAll();
		return fabricantes;
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView listar() {
		ModelAndView mv = new ModelAndView("lista_modelos");
		List<Modelo> modelos = repository.findAll();

		mv.addObject("modelos", modelos);
		return mv;
	}

	@RequestMapping(value = "/editar/{id}", 
			method = RequestMethod.GET)
	public ModelAndView editar(@PathVariable("id") Long id) {
		Modelo modelo = this.repository.findOne(id);
		ModelAndView mv = new ModelAndView("cadastro_modelo");
		mv.addObject(modelo);
		return mv;
	}
	
	@RequestMapping(value = "/excluir/{id}", 
			method= RequestMethod.GET)
	public ModelAndView excluir(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
		try{
			this.repository.delete(id);
	        redirectAttributes.addFlashAttribute("sucesso",
	                "Modelo excluido com sucesso!");
		}catch (Exception e) {
	        redirectAttributes.addFlashAttribute("falha",
	                "Não é possivel excluir esse Modelo!");
		}
		return new ModelAndView("redirect:/modelos");
	}
	

}
