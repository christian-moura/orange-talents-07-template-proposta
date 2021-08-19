package br.com.zup.propostas.proposta;

import br.com.zup.propostas.validations.CPForCNPJ;
import br.com.zup.propostas.validations.UniqueValue;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class PropostaRequest {

    @JsonProperty
    @NotBlank @CPForCNPJ
    @UniqueValue(entity = Proposta.class, attribute = "documento")
    private String documento;
    @JsonProperty @NotBlank @Email
    @UniqueValue(entity = Proposta.class, attribute = "email")
    private String email;
    @JsonProperty @NotBlank
    private String nome;
    @JsonProperty @NotBlank
    private String endereco;
    @JsonProperty @NotNull @Min(0)
    private BigDecimal salario;

    public Proposta toProposta(){
        return new Proposta(this.documento, this.email, this.nome, this.endereco,this.salario);
    }
}
