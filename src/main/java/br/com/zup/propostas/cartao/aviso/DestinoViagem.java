package br.com.zup.propostas.cartao.aviso;

import javax.persistence.*;

@Entity
public class DestinoViagem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String cidade;
    @Column(nullable = false)
    private String estado;
    @Column(nullable = false)
    private String pais;

    @Deprecated
    public DestinoViagem() {
    }

    public DestinoViagem(String cidade, String estado, String pais) {
        this.cidade = cidade;
        this.estado = estado;
        this.pais = pais;
    }

    @Override
    public String toString() {
        return cidade +" "+estado+" "+pais;
    }
}
