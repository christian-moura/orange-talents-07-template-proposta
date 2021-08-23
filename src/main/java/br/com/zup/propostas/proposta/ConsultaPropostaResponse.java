package br.com.zup.propostas.proposta;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ConsultaPropostaResponse {
    @JsonProperty
    private Long idProposta;
    @JsonProperty
    private PropostaEstado estadoProposta;

    public ConsultaPropostaResponse(Proposta proposta) {
        this.idProposta = proposta.getId();
        this.estadoProposta = proposta.getEstado();
    }
}
