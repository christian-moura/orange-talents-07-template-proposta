package br.com.zup.propostas.handler.exception;

import org.springframework.http.HttpStatus;

public class PersonalizadaException extends RuntimeException{

    private HttpStatus status;

    public PersonalizadaException(HttpStatus status,  String message ) {
        super(message);
        this.status = status;
    }
    public HttpStatus getStatus() {
        return status;
    }
}
