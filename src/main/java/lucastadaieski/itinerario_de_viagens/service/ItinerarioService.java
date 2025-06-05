package lucastadaieski.itinerario_de_viagens.service;

import lucastadaieski.itinerario_de_viagens.factory.ItinerarioFactory;
import lucastadaieski.itinerario_de_viagens.factory.ItinerarioTuristicoFactory;
import lucastadaieski.itinerario_de_viagens.model.Itinerario;
import lucastadaieski.itinerario_de_viagens.model.Usuario;
import lucastadaieski.itinerario_de_viagens.repository.ItinerarioRepository;
import lucastadaieski.itinerario_de_viagens.util.LoggerSingleton;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItinerarioService {

    private final ItinerarioRepository itinerarioRepository;

    @Autowired
    public ItinerarioService(ItinerarioRepository itinerarioRepository) {
        this.itinerarioRepository = itinerarioRepository;
    }

    // Salvar novo ou existente (o controller injeta o usuário antes de salvar)
    public void salvar(Itinerario itinerario) {
        itinerarioRepository.save(itinerario);
        LoggerSingleton.getInstancia().registrar("Itinerário salvo: " + itinerario.getDestino());
    }

    public List<Itinerario> listarTodosPorUsuario(Usuario usuario) {
        return itinerarioRepository.findByUsuario(usuario);
    }

    public Optional<Itinerario> buscarPorId(Long id) {
        return itinerarioRepository.findById(id);
    }

    public void deletar(Long id) {
        itinerarioRepository.deleteById(id);
    }

    public void atualizar(Itinerario atualizacao, Usuario usuarioLogado) {
        Itinerario existente = buscarPorId(atualizacao.getId())
                .orElseThrow(() -> new IllegalArgumentException("Itinerário não encontrado"));

        validarPermissaoDeEdicao(existente, usuarioLogado);

        existente.atualizarCom(atualizacao); // método do próprio modelo
        itinerarioRepository.save(existente);
    }

    private void validarPermissaoDeEdicao(Itinerario itinerario, Usuario usuarioLogado) {
        if (!itinerario.getUsuario().getId().equals(usuarioLogado.getId())) {
            throw new AccessDeniedException("Você não tem permissão para editar este itinerário.");
        }
    }

    public Itinerario criarItinerarioTuristico(Usuario usuario) {
        ItinerarioFactory factory = new ItinerarioTuristicoFactory();
        return factory.criarItinerario(usuario);
    }

}
