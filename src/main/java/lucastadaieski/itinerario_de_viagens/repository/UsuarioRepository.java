package lucastadaieski.itinerario_de_viagens.repository;

import lucastadaieski.itinerario_de_viagens.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByEmail(String email);
}

