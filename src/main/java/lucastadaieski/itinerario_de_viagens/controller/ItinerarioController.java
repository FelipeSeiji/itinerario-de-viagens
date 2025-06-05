package lucastadaieski.itinerario_de_viagens.controller;

import lucastadaieski.itinerario_de_viagens.facade.ItinerarioFacade;
import lucastadaieski.itinerario_de_viagens.model.Itinerario;
import lucastadaieski.itinerario_de_viagens.model.Usuario;
import lucastadaieski.itinerario_de_viagens.service.EmailService;
import lucastadaieski.itinerario_de_viagens.service.ItinerarioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/itinerarios")
public class ItinerarioController {

    @Autowired
    private ItinerarioService itinerarioService;

    @GetMapping
    public String listar(Model model, @AuthenticationPrincipal Usuario usuarioLogado) {
        model.addAttribute("itinerarios", itinerarioService.listarTodosPorUsuario(usuarioLogado));
        model.addAttribute("usuarioLogado", usuarioLogado);
        return "lista-itinerarios";
    }

    @GetMapping("/novo")
    public String novo(Model model, @AuthenticationPrincipal Usuario usuarioLogado) {
        Itinerario itinerario = Itinerario.builder()
                .usuario(usuarioLogado)
                .build();
        model.addAttribute("itinerario", itinerario);
        return "form-itinerario";
    }


    @PostMapping
    public String salvar(@ModelAttribute Itinerario itinerario,
                         @AuthenticationPrincipal Usuario usuarioLogado) {
        itinerario.setUsuario(usuarioLogado);
        itinerarioService.salvar(itinerario);
        return "redirect:/itinerarios";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model,
                         @AuthenticationPrincipal Usuario usuarioLogado) {
        Itinerario itinerario = itinerarioService.buscarPorId(id)
                .orElseThrow(() -> new IllegalArgumentException("Itinerário não encontrado"));

        if (!itinerario.getUsuario().getId().equals(usuarioLogado.getId())) {
            return "redirect:/itinerarios";
        }

        model.addAttribute("itinerario", itinerario);
        return "form-itinerario";
    }

    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable Long id, @AuthenticationPrincipal Usuario usuarioLogado) {
        Itinerario itinerario = itinerarioService.buscarPorId(id)
                .orElseThrow(() -> new IllegalArgumentException("Itinerário não encontrado"));

        if (!itinerario.getUsuario().getId().equals(usuarioLogado.getId())) {
            throw new AccessDeniedException("Você não tem permissão para excluir este itinerário.");
        }

        itinerarioService.deletar(id);
        return "redirect:/itinerarios";
    }

    @GetMapping("/modelo-turistico")
    public String modeloTuristico(Model model, @AuthenticationPrincipal Usuario usuarioLogado) {
        Itinerario itinerario = itinerarioService.criarItinerarioTuristico(usuarioLogado);
        model.addAttribute("itinerario", itinerario);
        return "form-itinerario";
    }

    @Autowired
    private EmailService emailService;

    @PostMapping("/com-facade")
    public String salvarComFacade(@ModelAttribute Itinerario itinerario,
                                  @AuthenticationPrincipal Usuario usuarioLogado) {

        itinerario.setUsuario(usuarioLogado);
        ItinerarioFacade facade = new ItinerarioFacade(itinerarioService, emailService);
        facade.criarSalvarEEnviar(itinerario);
        return "redirect:/itinerarios";
    }

}
