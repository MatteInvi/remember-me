package my.project.remember.me.remember_me.Controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.validation.Valid;
import my.project.remember.me.remember_me.Model.Evento;
import my.project.remember.me.remember_me.Repository.EventoRepository;


@Controller
@RequestMapping("/")
public class HomeController {

    @Autowired
    EventoRepository eventoRepository;
    
    @GetMapping("")
    public String getMethodName(Model Model) {
        Evento evento = new Evento();
        Model.addAttribute("newEvent", evento);
        return "events/create";
    }
    


    @PostMapping("/event/create")
    public String createEvent(@Valid @ModelAttribute("newEvent") Evento evento, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            return "events/create";
        }
        eventoRepository.save(evento);
        redirectAttributes.addFlashAttribute("message", "Evento creato con successo!");
        return "redirect:/";
    }
    
}
