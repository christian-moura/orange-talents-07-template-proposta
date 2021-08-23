package br.com.zup.propostas.cartao.vencimento;

import br.com.zup.propostas.cartao.Cartao;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Vencimento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    @Column(nullable = false)
    private String idVencimento;
    @Column(nullable = false)
    private Integer dia;
    @Column(nullable = false)
    private LocalDate dataDeCriacao;
    @OneToOne(mappedBy = "vencimento")
    private Cartao cartao;

    @Deprecated
    public Vencimento() {
    }

    public Vencimento(String idVencimento, Integer dia, LocalDate dataDeCriacao) {
        this.idVencimento = idVencimento;
        this.dia = dia;
        this.dataDeCriacao = dataDeCriacao;
    }
}
