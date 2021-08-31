package br.com.zup.propostas.cartao.carteira;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CarteiraDigitalSistemaLegadoRequest {

    @JsonProperty
    private String carteira;

    @JsonProperty
    private String email;

    public CarteiraDigitalSistemaLegadoRequest(String carteira, String email) {
        this.carteira = carteira;
        this.email = email;
    }
}
