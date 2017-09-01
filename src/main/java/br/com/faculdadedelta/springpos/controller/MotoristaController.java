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

import br.com.faculdadedelta.springpos.dao.MotoristaRepository;
import br.com.faculdadedelta.springpos.model.Modelo;
import br.com.faculdadedelta.springpos.model.Motorista;
import br.com.faculdadedelta.springpos.model.type.Sexo;

@Controller
@RequestMapping("/motoristas")
public class MotoristaController {
	@Autowired
	private MotoristaRepository repository;

	@RequestMapping(value = "/novo", method = RequestMethod.GET)
	public ModelAndView novo() {
		ModelAndView mv = new ModelAndView("cadastro_motorista");
		mv.addObject(new Motorista());
		return mv;
		// return new ModelAndView("produto");
	}

	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView salvar(@Validated Motorista motorista, Errors errors, RedirectAttributes redirectAttributes) {

		if (errors.hasErrors()) {
			return new ModelAndView("cadastro_motorista");
		}

		this.repository.save(motorista);

		redirectAttributes.addFlashAttribute("mensagem", "Motorista salvo com sucesso!");

		return new ModelAndView("redirect:/motoristas/novo");
	}

	@ModelAttribute("todosSexos")
	public Sexo[] todosSexos() {
		return Sexo.values();
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView listar() {
		ModelAndView mv = new ModelAndView("lista_motoristas");
		List<Motorista> motoristas = repository.findAll();

		mv.addObject("motoristas", motoristas);
		return mv;
	}

	@RequestMapping(value = "/editar/{id}", 
			method = RequestMethod.GET)
	public ModelAndView editar(@PathVariable("id") Long id) {
		Motorista motorista = this.repository.findOne(id);
		ModelAndView mv = new ModelAndView("cadastro_motorista");
		mv.addObject(motorista);
		return mv;
	}
	
	@RequestMapping(value = "/excluir/{id}", 
			method= RequestMethod.GET)
	public ModelAndView excluir(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
		try{
			this.repository.delete(id);
	        redirectAttributes.addFlashAttribute("sucesso",
	                "Motorista excluido com sucesso!");
		}catch (Exception e) {
	        redirectAttributes.addFlashAttribute("falha",
	                "Não é possivel excluir esse Motorista!");
		}
		return new ModelAndView("redirect:/motoristas");
	}
	
}
