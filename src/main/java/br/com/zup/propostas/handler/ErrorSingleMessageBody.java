package br.com.zup.propostas.handler;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ErrorSingleMessageBody {

    @JsonProperty
    private String mensagem;

    public ErrorSingleMessageBody(String mensagem) {
        this.mensagem = mensagem;
    }
}
