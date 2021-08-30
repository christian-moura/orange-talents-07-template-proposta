package br.com.zup.propostas.cartao.aviso;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;

public class DestinoViagemRequest {


    @JsonProperty
    @NotBlank
    private String cidade;
    @JsonProperty
    @NotBlank
    private String estado;
    @JsonProperty
    @NotBlank
    private String pais;


    public DestinoViagem toDestinoViagem(){
        return new DestinoViagem(this.cidade,this.estado, this.pais);
    }

    public String getCidade() {
        return cidade;
    }

    public String getEstado() {
        return estado;
    }

    public String getPais() {
        return pais;
    }
}
