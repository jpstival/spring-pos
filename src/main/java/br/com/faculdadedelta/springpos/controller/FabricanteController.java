package br.com.faculdadedelta.springpos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.faculdadedelta.springpos.dao.FabricanteRepository;
import br.com.faculdadedelta.springpos.model.Fabricante;

@Controller
@RequestMapping("/fabricante")
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
    public ModelAndView salvar(@Validated Fabricante produto, 
            Errors errors,
            RedirectAttributes redirectAttributes) {
        
        if (errors.hasErrors()) {
            return new ModelAndView("cadastro_fabricante");
        }
        
        this.repository.save(produto);
        
        redirectAttributes.addFlashAttribute("mensagem",
                "Fabricante salvo com sucesso!");
        
        return new ModelAndView("redirect:/fabricante/novo");
    }

}
