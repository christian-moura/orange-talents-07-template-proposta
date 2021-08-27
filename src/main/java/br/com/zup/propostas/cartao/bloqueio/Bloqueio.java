package br.com.zup.propostas.cartao.bloqueio;

import br.com.zup.propostas.cartao.Cartao;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
@Entity
public class Bloqueio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @CreationTimestamp
    @Column(nullable = false)
    private LocalDateTime bloqueadoEm;
    @Column(nullable = false)
    private String ip;
    @Column(nullable = false)
    private String userAgent;
    @OneToOne(fetch = FetchType.LAZY)
    private Cartao cartao;

    @Deprecated
    public Bloqueio() {
    }

    public Bloqueio(String ip, String userAgent, Cartao cartao) {
        this.bloqueadoEm = LocalDateTime.now();
        this.ip = ip;
        this.userAgent = userAgent;
        this.cartao = cartao;
    }
}
