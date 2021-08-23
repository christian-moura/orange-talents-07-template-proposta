package br.com.zup.propostas.cartao;

import br.com.zup.propostas.cartao.vencimento.VencimentoRequest;
import br.com.zup.propostas.proposta.Proposta;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

public class CartaoResponse {

    @JsonProperty
    private String id;
    @JsonProperty
    //@JsonFormat(pattern = "dd/MM/yyyy",shape = JsonFormat.Shape.STRING)
    private LocalDateTime emitidoEm;
    @JsonProperty
    private String titular;
    @JsonProperty
    private Integer  limite;
    @JsonProperty
    private VencimentoRequest vencimento;
    @JsonProperty
    private String idProposta;

    public Cartao toCartao(Proposta proposta){
        return new Cartao(this.id,this.emitidoEm,this.titular,this.limite,
                this.vencimento.toVencimento(), proposta);
    }

}
