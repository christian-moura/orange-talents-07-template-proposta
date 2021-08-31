package br.com.zup.propostas.cartao.carteira;

import br.com.zup.propostas.cartao.Cartao;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class CarteiraDigitalRequest {

    @JsonProperty @NotNull
    //@ExistEnum(targetClassType = Carteira.class)
    private Carteira carteira;

    @JsonProperty
    @Email @NotBlank
    private String email;

    public CarteiraDigital toCarteiraDigital(Cartao cartao){
        return new CarteiraDigital(this.carteira, this.email, cartao);
    }

    public Carteira getCarteira() {
        return carteira;
    }
}
