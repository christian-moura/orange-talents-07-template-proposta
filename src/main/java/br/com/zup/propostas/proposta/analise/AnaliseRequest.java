package br.com.zup.propostas.proposta.analise;

import br.com.zup.propostas.proposta.Proposta;
import com.fasterxml.jackson.annotation.JsonProperty;

public class AnaliseRequest {

    @JsonProperty
    private String documento;
    @JsonProperty
    private String nome;
    @JsonProperty
    private String idProposta;

    public AnaliseRequest(Proposta proposta) {
        this.documento = proposta.getDocumento();
        this.nome = proposta.getNome();
        this.idProposta = proposta.getId().toString();
    }
}
