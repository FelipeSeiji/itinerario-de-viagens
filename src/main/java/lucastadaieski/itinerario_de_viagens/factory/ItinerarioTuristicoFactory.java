package lucastadaieski.itinerario_de_viagens.factory;

import lucastadaieski.itinerario_de_viagens.model.Itinerario;
import lucastadaieski.itinerario_de_viagens.model.Usuario;

import java.time.LocalDate;

public class ItinerarioTuristicoFactory implements ItinerarioFactory {

    @Override
    public Itinerario criarItinerario(Usuario usuario) {
        return Itinerario.builder()
                .usuario(usuario)
                .destino("Rio de Janeiro")
                .data(LocalDate.now().plusDays(15))
                .hotel("Copacabana Palace")
                .transporte("Aéreo")
                .pontoInteresse("Cristo Redentor, Pão de Açúcar")
                .valorEstimado(4200.00)
                .notas("Inclui passeio de barco e city tour.")
                .build();
    }
}
