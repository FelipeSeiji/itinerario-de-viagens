package lucastadaieski.itinerario_de_viagens.facade;

import lucastadaieski.itinerario_de_viagens.model.Itinerario;
import lucastadaieski.itinerario_de_viagens.service.EmailService;
import lucastadaieski.itinerario_de_viagens.service.ItinerarioService;
import lucastadaieski.itinerario_de_viagens.util.LoggerSingleton;

public class ItinerarioFacade {
    private final ItinerarioService itinerarioService;
    private final EmailService emailService;

    public ItinerarioFacade(ItinerarioService itinerarioService, EmailService emailService) {
        this.itinerarioService = itinerarioService;
        this.emailService = emailService;
    }

    public void criarSalvarEEnviar(Itinerario itinerario) {
        itinerarioService.salvar(itinerario);
        emailService.enviarResumo(itinerario.getUsuario().getEmail(), itinerario);
        LoggerSingleton.getInstancia().registrar("Resumo enviado para " + itinerario.getUsuario().getEmail());
    }
}
