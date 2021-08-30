package br.com.zup.propostas.cartao.aviso;

import br.com.zup.propostas.cartao.Cartao;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.Valid;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class AvisoViagemRequest {

    @JsonProperty @NotNull @Future
    @JsonFormat(pattern = "dd/MM/yyyy",shape = JsonFormat.Shape.STRING)
    private LocalDate terminaEm;

    @JsonProperty @NotNull @Valid
    private DestinoViagemRequest destinoViagem;

    public AvisoViagem toAvisoViagem(String ip, String userAgent, Cartao cartao ){
        return new AvisoViagem(ip, userAgent, this.terminaEm, cartao, destinoViagem.toDestinoViagem());
    }

    public DestinoViagemRequest getDestinoViagem() {
        return destinoViagem;
    }
}
