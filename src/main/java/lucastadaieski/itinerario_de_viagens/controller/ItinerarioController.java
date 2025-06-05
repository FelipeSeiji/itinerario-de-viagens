package lucastadaieski.itinerario_de_viagens.controller;

import lucastadaieski.itinerario_de_viagens.model.Itinerario;
import lucastadaieski.itinerario_de_viagens.model.Usuario;
import lucastadaieski.itinerario_de_viagens.service.ItinerarioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
        Itinerario itinerario = new Itinerario.Builder(usuarioLogado).build();
        model.addAttribute("itinerario", itinerario);
        return "form-itinerario";
    }


    @PostMapping
    public String salvar(@ModelAttribute("itinerario") Itinerario itinerario,
                         @AuthenticationPrincipal Usuario usuarioLogado) {
        if (itinerario.getId() != null) {
            itinerarioService.atualizar(itinerario, usuarioLogado);
        } else {
            itinerarioService.salvar(itinerario, usuarioLogado); // corrigido aqui
        }
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

}
