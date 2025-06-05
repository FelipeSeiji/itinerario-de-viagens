package lucastadaieski.itinerario_de_viagens.factory;

import lucastadaieski.itinerario_de_viagens.model.Itinerario;
import lucastadaieski.itinerario_de_viagens.model.Usuario;


public interface ItinerarioFactory {
    Itinerario criarItinerario(Usuario usuario);
}