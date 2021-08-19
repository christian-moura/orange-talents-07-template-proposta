package br.com.zup.propostas.proposta.analise;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AnaliseResponse {
    @JsonProperty
    private String documento;
    @JsonProperty
    private String nome;
    @JsonProperty
    private AnaliseStatus resultadoSolicitacao;
    @JsonProperty
    private String idProposta;

    public AnaliseStatus getResultadoSolicitacao() {
        return resultadoSolicitacao;
    }
}
