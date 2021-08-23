package br.com.zup.propostas.cartao.vencimento;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;

public class VencimentoRequest {

    @JsonProperty
    private String id;
    @JsonProperty
    private Integer dia;
    @JsonProperty
    private LocalDate dataDeCriacao;


    public Vencimento toVencimento(){
        return new Vencimento(this.id,this.dia,this.dataDeCriacao );
    }

}
