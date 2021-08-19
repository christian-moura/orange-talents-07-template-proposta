package br.com.zup.propostas.handler.exception;

import org.springframework.http.HttpStatus;

public class ErrorFieldsException extends RuntimeException {

    private String campo;
    private HttpStatus status;

    public ErrorFieldsException(HttpStatus status, String campo, String message ) {
        super(message);
        this.status = status;
        this.campo = campo;
    }

    public ErrorFieldsException(HttpStatus status, String message) {
        super(message);
        this.status = status;
    }

    public String getCampo() {
        return campo;
    }

    public HttpStatus getStatus() {
        return status;
    }
}
