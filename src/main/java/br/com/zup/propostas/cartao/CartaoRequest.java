package br.com.zup.propostas.cartao;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CartaoRequest {

    @JsonProperty
    private String idProposta;
    @JsonProperty
    private String documento;
    @JsonProperty
    private String nome;


    public CartaoRequest(String idProposta, String documento, String nome) {
        this.idProposta = idProposta;
        this.documento = documento;
        this.nome = nome;
    }
}
