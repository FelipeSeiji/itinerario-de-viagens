package lucastadaieski.itinerario_de_viagens.service;

import lucastadaieski.itinerario_de_viagens.model.Itinerario;
import lucastadaieski.itinerario_de_viagens.model.Usuario;
import lucastadaieski.itinerario_de_viagens.repository.ItinerarioRepository;
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

    public Itinerario salvar(Itinerario dados, Usuario usuarioLogado) {
        Itinerario novo = new Itinerario.Builder(usuarioLogado)
                .destino(dados.getDestino())
                .data(dados.getData())
                .hotel(dados.getHotel())
                .transporte(dados.getTransporte())
                .pontoInteresse(dados.getPontoInteresse())
                .valorEstimado(dados.getValorEstimado())
                .notas(dados.getNotas())
                .build();

        return itinerarioRepository.save(novo);
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

    public void atualizar(Itinerario dados, Usuario usuarioLogado) {
        Itinerario existente = buscarPorId(dados.getId())
                .orElseThrow(() -> new IllegalArgumentException("Itinerário não encontrado"));

        validarPermissaoDeEdicao(existente, usuarioLogado);

        Itinerario atualizadoComBuilder = new Itinerario.Builder(usuarioLogado)
                .destino(dados.getDestino())
                .data(dados.getData())
                .hotel(dados.getHotel())
                .transporte(dados.getTransporte())
                .pontoInteresse(dados.getPontoInteresse())
                .valorEstimado(dados.getValorEstimado())
                .notas(dados.getNotas())
                .build();

        existente.atualizarCom(atualizadoComBuilder);
        itinerarioRepository.save(existente);
    }




    private void validarPermissaoDeEdicao(Itinerario itinerario, Usuario usuarioLogado) {
        if (!itinerario.getUsuario().getId().equals(usuarioLogado.getId())) {
            throw new AccessDeniedException("Você não tem permissão para editar este itinerário.");
        }
    }




}
