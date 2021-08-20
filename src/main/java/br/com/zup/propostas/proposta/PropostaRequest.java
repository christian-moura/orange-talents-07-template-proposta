package br.com.zup.propostas.proposta;

import br.com.zup.propostas.handler.exception.PersonalizadaException;
import br.com.zup.propostas.validations.CPForCNPJ;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.http.HttpStatus;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class PropostaRequest {

    @JsonProperty
    @NotBlank @CPForCNPJ
    private String documento;
    @JsonProperty @NotBlank @Email
    private String email;
    @JsonProperty @NotBlank
    private String nome;
    @JsonProperty @NotBlank
    private String endereco;
    @JsonProperty @NotNull @Min(0)
    private BigDecimal salario;

    public Proposta toProposta(EntityManager entityManager){
        Query query = entityManager.createQuery("select p from Proposta p where p.documento = :value");
        query.setParameter("value", this.documento);
        if(!query.getResultList().isEmpty())
            throw new PersonalizadaException(HttpStatus.UNPROCESSABLE_ENTITY,
                    "Não foi possível finalizar a requisição. Já existe uma proposta para o documento CPF/CNPJ informado");
        return new Proposta(this.documento, this.email, this.nome, this.endereco,this.salario);
    }
}
