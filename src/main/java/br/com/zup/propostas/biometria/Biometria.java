package br.com.zup.propostas.biometria;

import br.com.zup.propostas.cartao.Cartao;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Biometria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String biometria;

    @CreationTimestamp
    @Column(nullable = false)
    private LocalDateTime criadoEm ;

    @ManyToOne
    private Cartao cartao;

    @Deprecated
    public Biometria() {
    }

    public Biometria(String biometria, Cartao cartao) {
        this.biometria = biometria;
        this.criadoEm = LocalDateTime.now();
        this.cartao = cartao;
    }

    public Long getId() {
        return id;
    }
}
