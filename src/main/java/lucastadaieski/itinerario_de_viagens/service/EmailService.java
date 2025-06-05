package lucastadaieski.itinerario_de_viagens.service;

import lucastadaieski.itinerario_de_viagens.model.Itinerario;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    public void enviarResumo(String email, Itinerario itinerario) {
        System.out.println("Resumo do itinerário enviado para: " + email);
    }
}

