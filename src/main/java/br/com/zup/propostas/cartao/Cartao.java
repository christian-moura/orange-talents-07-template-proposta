package br.com.zup.propostas.cartao;

import br.com.zup.propostas.cartao.biometria.Biometria;
import br.com.zup.propostas.cartao.bloqueio.Bloqueio;
import br.com.zup.propostas.cartao.vencimento.Vencimento;
import br.com.zup.propostas.proposta.Proposta;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

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

    @Column(nullable = false)
    private CartaoEstado estado;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name="vencimento_id")
    private Vencimento vencimento;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="proposta_id")
    private Proposta proposta;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "cartao")
    private List<Biometria> biometrias;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "cartao")
    private Bloqueio bloqueio;

    @Deprecated
    public Cartao() {
    }

    public Cartao(String idCartao, LocalDateTime emitidoEm, String titular, Integer limite, Vencimento vencimento, Proposta proposta) {
        this.idCartao = idCartao;
        this.emitidoEm = emitidoEm;
        this.titular = titular;
        this.estado = CartaoEstado.DESBLOQUEADO;
        this.limite = limite;
        this.vencimento = vencimento;
        this.proposta = proposta;
    }

    public String getIdCartao() {
        return idCartao;
    }

    public Bloqueio getBloqueio() {
        return bloqueio;
    }

    public void bloquear(Bloqueio bloqueio){
        this.estado = CartaoEstado.AGUARDANDO_BLOQUEIO;
        this.bloqueio = bloqueio;
    }

    public void bloqueadoNoLegado(){
        this.estado = CartaoEstado.BLOQUEADO;
    }
}
