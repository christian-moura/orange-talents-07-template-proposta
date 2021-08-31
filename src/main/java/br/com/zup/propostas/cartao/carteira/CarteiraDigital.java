package br.com.zup.propostas.cartao.carteira;

import br.com.zup.propostas.cartao.Cartao;

import javax.persistence.*;

@Entity
public class CarteiraDigital {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Carteira carteira;

    @Column(nullable = false)
    private String email;

    @ManyToOne
    private Cartao cartao;

    @Deprecated
    public CarteiraDigital() {
    }

    public CarteiraDigital(Carteira carteira, String email, Cartao cartao) {
        this.carteira = carteira;
        this.email = email;
        this.cartao = cartao;
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public Carteira getCarteira() {
        return carteira;
    }
}
