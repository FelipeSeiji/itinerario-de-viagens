package lucastadaieski.itinerario_de_viagens.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Itinerario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String destino;
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

    protected Itinerario() {
        // Construtor JPA
    }

    // Getters (sem setters públicos para garantir imutabilidade fora do builder)

    public Long getId() {
        return id;
    }

    public String getDestino() {
        return destino;
    }

    public LocalDate getData() {
        return data;
    }

    public String getHotel() {
        return hotel;
    }

    public String getTransporte() {
        return transporte;
    }

    public String getPontoInteresse() {
        return pontoInteresse;
    }

    public Double getValorEstimado() {
        return valorEstimado;
    }

    public String getNotas() {
        return notas;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    /**
     * Atualiza os dados deste itinerário com os valores de outro objeto
     * (exceto ID e usuário, que não devem mudar).
     */
    public void atualizarCom(Itinerario outro) {
        this.destino = outro.destino;
        this.data = outro.data;
        this.hotel = outro.hotel;
        this.transporte = outro.transporte;
        this.pontoInteresse = outro.pontoInteresse;
        this.valorEstimado = outro.valorEstimado;
        this.notas = outro.notas;
        // Não altera ID nem usuário
    }

    // ==============================
    // Builder interno e seguro
    // ==============================

    public static class Builder {
        private final Usuario usuario; // obrigatório
        private String destino;
        private LocalDate data;
        private String hotel;
        private String transporte;
        private String pontoInteresse;
        private Double valorEstimado;
        private String notas;

        public Builder(Usuario usuario) {
            if (usuario == null) {
                throw new IllegalArgumentException("Usuário não pode ser nulo");
            }
            this.usuario = usuario;
        }

        public Builder destino(String destino) {
            this.destino = destino;
            return this;
        }

        public Builder data(LocalDate data) {
            this.data = data;
            return this;
        }

        public Builder hotel(String hotel) {
            this.hotel = hotel;
            return this;
        }

        public Builder transporte(String transporte) {
            this.transporte = transporte;
            return this;
        }

        public Builder pontoInteresse(String pontoInteresse) {
            this.pontoInteresse = pontoInteresse;
            return this;
        }

        public Builder valorEstimado(Double valorEstimado) {
            this.valorEstimado = valorEstimado;
            return this;
        }

        public Builder notas(String notas) {
            this.notas = notas;
            return this;
        }

        public Itinerario build() {
            Itinerario i = new Itinerario();
            i.destino = this.destino;
            i.data = this.data;
            i.hotel = this.hotel;
            i.transporte = this.transporte;
            i.pontoInteresse = this.pontoInteresse;
            i.valorEstimado = this.valorEstimado;
            i.notas = this.notas;
            i.usuario = this.usuario;
            return i;
        }
    }
}
