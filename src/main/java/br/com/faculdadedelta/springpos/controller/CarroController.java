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

import br.com.faculdadedelta.springpos.dao.CarroRepository;
import br.com.faculdadedelta.springpos.dao.ModeloRepository;
import br.com.faculdadedelta.springpos.model.Carro;
import br.com.faculdadedelta.springpos.model.Fabricante;
import br.com.faculdadedelta.springpos.model.Modelo;

@Controller
@RequestMapping("/carros")
public class CarroController {
	@Autowired
	private CarroRepository repository;

	@Autowired
	private ModeloRepository mr;
	
	@RequestMapping(value = "/novo", method = RequestMethod.GET)
	public ModelAndView novo() {
		ModelAndView mv = new ModelAndView("cadastro_carro");
		mv.addObject(new Carro());
		return mv;
		// return new ModelAndView("produto");
	}

	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView salvar(@Validated Carro carro, Errors errors, RedirectAttributes redirectAttributes) {

		if (errors.hasErrors()) {
			return new ModelAndView("cadastro_carro");
		}

		this.repository.save(carro);

		redirectAttributes.addFlashAttribute("mensagem", "Carro salvo com sucesso!");

		return new ModelAndView("redirect:/carros/novo");
	}

	@ModelAttribute("todosModelos")
	public List<Modelo> todosModelos() {
		List<Modelo> modelos = this.mr.findAll();
		return modelos;
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView listar() {
		ModelAndView mv = new ModelAndView("lista_carros");
		List<Carro> carros = repository.findAll();

		mv.addObject("carros", carros);
		return mv;
	}

	@RequestMapping(value = "/editar/{id}", 
			method = RequestMethod.GET)
	public ModelAndView editar(@PathVariable("id") Long id) {
		Carro carro = this.repository.findOne(id);
		ModelAndView mv = new ModelAndView("cadastro_carro");
		mv.addObject(carro);
		return mv;
	}
	
	@RequestMapping(value = "/excluir/{id}", 
			method= RequestMethod.GET)
	public ModelAndView excluir(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
		try{
			this.repository.delete(id);
	        redirectAttributes.addFlashAttribute("sucesso",
	                "Carro excluido com sucesso!");
		}catch (Exception e) {
	        redirectAttributes.addFlashAttribute("falha",
	                "Não é possivel excluir esse Carro!");
		}
		return new ModelAndView("redirect:/carros");
	}
}
