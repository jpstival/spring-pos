package br.com.faculdadedelta.springpos.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.faculdadedelta.springpos.dao.CarroRepository;
import br.com.faculdadedelta.springpos.dao.LocacaoRepository;
import br.com.faculdadedelta.springpos.dao.MotoristaRepository;
import br.com.faculdadedelta.springpos.model.Carro;
import br.com.faculdadedelta.springpos.model.Fabricante;
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

	@RequestMapping(value = "/locacoes", method = RequestMethod.POST)
	public ModelAndView salvar(@Validated Locacao locacao, Errors errors, RedirectAttributes redirectAttributes) {

		if (errors.hasErrors()) {
			return new ModelAndView("cadastro_locacao");
		}

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

}
