package br.com.zup.propostas.cartao.aviso;

import br.com.zup.propostas.cartao.Cartao;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class AvisoViagem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String ip;
    @Column(nullable = false)
    private String userAgent;
    @Column(nullable = false)
    private LocalDateTime avisadoEm;
    @Column(nullable = false)
    private LocalDate terminaEm;

    @ManyToOne
    private Cartao cartao;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "destino_viagem_id")
    private DestinoViagem destino;


    @Deprecated
    public AvisoViagem() {
    }

    public AvisoViagem(String ip, String userAgent, LocalDate terminaEm, Cartao cartao, DestinoViagem destino) {
        this.ip = ip;
        this.userAgent = userAgent;
        this.avisadoEm = LocalDateTime.now();
        this.terminaEm = terminaEm;
        this.cartao = cartao;
        this.destino = destino;
    }

    public Long getId() {
        return id;
    }

    public LocalDate getTerminaEm() {
        return terminaEm;
    }

    public DestinoViagem getDestino() {
        return destino;
    }
}
