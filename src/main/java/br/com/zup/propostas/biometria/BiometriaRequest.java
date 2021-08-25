package br.com.zup.propostas.biometria;

import br.com.zup.propostas.cartao.Cartao;
import br.com.zup.propostas.validations.Base64;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;

public class BiometriaRequest {

    @JsonProperty
    @Base64 @NotBlank
    private String biometria;

    public Biometria toBiometria(Cartao cartao){
        return new Biometria(this.biometria, cartao);
    }
}
