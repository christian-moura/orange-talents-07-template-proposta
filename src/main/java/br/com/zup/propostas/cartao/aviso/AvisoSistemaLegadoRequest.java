package br.com.zup.propostas.cartao.aviso;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AvisoSistemaLegadoRequest {

    @JsonProperty
    private String destino;
    @JsonProperty
    private String validoAte;

    public AvisoSistemaLegadoRequest(String destino, String validoAte) {
        this.destino = destino;
        this.validoAte = validoAte;
    }
}
