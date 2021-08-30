package br.com.zup.propostas.cartao.aviso;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AvisoSistemaLegadoResponse {
    @JsonProperty
    private String resultado;

    public AvisoSistemaLegadoResponse(@JsonProperty("resultado") String resultado) {
        this.resultado = resultado;
    }

    public String getBloqueioStatus() {
        return resultado;
    }
}
