package br.com.zup.propostas.cartao.bloqueio;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BloqueioResponse {

    @JsonProperty
    private BloqueioStatus resultado;

    public BloqueioResponse(@JsonProperty("resultado") BloqueioStatus resultado) {
        this.resultado = resultado;
    }

    public BloqueioStatus getBloqueioStatus() {
        return resultado;
    }
}
