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
import br.com.faculdadedelta.springpos.dao.LocacaoRepository;
import br.com.faculdadedelta.springpos.dao.MotoristaRepository;
import br.com.faculdadedelta.springpos.model.Carro;
import br.com.faculdadedelta.springpos.model.Locacao;
import br.com.faculdadedelta.springpos.model.Motorista;

@Controller
@RequestMapping("/locacoes")
public class LocacaoController {
	@Autowired
	private LocacaoRepository repository;

	@Autowired
	private MotoristaRepository mr;

	@Autowired
	private CarroRepository cr;

	@RequestMapping(value = "/novo", method = RequestMethod.GET)
	public ModelAndView novo() {
		ModelAndView mv = new ModelAndView("cadastro_locacao");
		mv.addObject(new Locacao());
		return mv;
		// return new ModelAndView("produto");
	}

	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView salvar(@Validated Locacao locacao, Errors errors, RedirectAttributes redirectAttributes) {

		if (errors.hasErrors()) {
			return new ModelAndView("cadastro_locacao");
		}

		locacao.setValorTotal(locacao);
		
		this.repository.save(locacao);

		redirectAttributes.addFlashAttribute("mensagem", "Locação salvo com sucesso!");

		return new ModelAndView("redirect:/locacoes/novo");
	}

	@ModelAttribute("todosMotoristas")
	public List<Motorista> todosMotoristas() {
		List<Motorista> motoristas = this.mr.findAll();
		return motoristas;
	}

	@ModelAttribute("todosCarros")
	public List<Carro> todosCarros() {
		List<Carro> carros = this.cr.findAll();
		return carros;
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView listar() {
		ModelAndView mv = new ModelAndView("lista_locacoes");
		List<Locacao> locacoes = repository.findAll();

		mv.addObject("locacoes", locacoes);
		return mv;
	}

	@RequestMapping(value = "/editar/{id}", 
			method = RequestMethod.GET)
	public ModelAndView editar(@PathVariable("id") Long id) {
		Locacao locacao = this.repository.findOne(id);
		ModelAndView mv = new ModelAndView("cadastro_locacao");
		mv.addObject(locacao);
		return mv;
	}
	
	@RequestMapping(value = "/excluir/{id}", 
			method= RequestMethod.GET)
	public ModelAndView excluir(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
		try{
			this.repository.delete(id);
	        redirectAttributes.addFlashAttribute("sucesso",
	                "Locação excluida com sucesso!");
		}catch (Exception e) {
	        redirectAttributes.addFlashAttribute("falha",
	                "Não é possivel excluir essa Locação!");
		}
		return new ModelAndView("redirect:/locacoes");
	}

}
