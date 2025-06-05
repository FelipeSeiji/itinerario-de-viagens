package lucastadaieski.itinerario_de_viagens.repository;

import lucastadaieski.itinerario_de_viagens.model.Itinerario;
import lucastadaieski.itinerario_de_viagens.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItinerarioRepository extends JpaRepository<Itinerario, Long> {

    // Retorna os itinerários de um usuário específico
    List<Itinerario> findByUsuario(Usuario usuario);
}

