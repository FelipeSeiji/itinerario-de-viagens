package lucastadaieski.itinerario_de_viagens.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Itinerario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String destino;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate data;

    private String hotel;
    private String transporte;
    private String pontoInteresse;
    private Double valorEstimado;

    @Column(length = 1000)
    private String notas;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)

    private Usuario usuario;

    public void atualizarCom(Itinerario outro) {
        this.destino = outro.destino;
        this.data = outro.data;
        this.hotel = outro.hotel;
        this.transporte = outro.transporte;
        this.pontoInteresse = outro.pontoInteresse;
        this.valorEstimado = outro.valorEstimado;
        this.notas = outro.notas;
    }
}
