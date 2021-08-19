package br.com.zup.propostas.handler;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.validation.FieldError;

public class ErrorFieldsBody {

    @JsonProperty
    private String campo;

    @JsonProperty
    private String mensagem;

    public ErrorFieldsBody(String campo, String mensagem) {
        this.campo = campo;
        this.mensagem = mensagem;
    }

    public ErrorFieldsBody(String mensagem) {
        this.mensagem = mensagem;
    }

    public ErrorFieldsBody(FieldError error) {
        this.campo = error.getField();
        this.mensagem = error.getDefaultMessage();
    }
}
