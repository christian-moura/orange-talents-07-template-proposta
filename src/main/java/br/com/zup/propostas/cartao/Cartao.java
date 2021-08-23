package br.com.zup.propostas.cartao;

import br.com.zup.propostas.cartao.vencimento.Vencimento;
import br.com.zup.propostas.proposta.Proposta;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Cartao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String idCartao;
    @Column(nullable = false)
    private LocalDateTime emitidoEm;
    @Column(nullable = false)
    private String titular;
    @Column(nullable = false)
    private Integer  limite;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name="vencimento_id")
    private Vencimento vencimento;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="proposta_id")
    private Proposta proposta;

    @Deprecated
    public Cartao() {
    }

    public Cartao(String idCartao, LocalDateTime emitidoEm, String titular, Integer limite, Vencimento vencimento, Proposta proposta) {
        this.idCartao = idCartao;
        this.emitidoEm = emitidoEm;
        this.titular = titular;
        this.limite = limite;
        this.vencimento = vencimento;
        this.proposta = proposta;
    }
}
