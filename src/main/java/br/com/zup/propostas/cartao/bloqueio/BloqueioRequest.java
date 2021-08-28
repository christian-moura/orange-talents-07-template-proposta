package br.com.zup.propostas.cartao.bloqueio;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class BloqueioRequest {

    @JsonProperty
    private String sistemaResponsavel;

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    public BloqueioRequest(String sistemaResponsavel) {
        this.sistemaResponsavel = sistemaResponsavel;
    }

    @Override
    public String toString() {
        return "BloqueioRequest{" +
                "sistemaResponsavel='" + sistemaResponsavel + '\'' +
                '}';
    }
}
