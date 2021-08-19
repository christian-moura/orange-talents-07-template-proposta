package br.com.zup.propostas.handler;

import br.com.zup.propostas.handler.exception.ErrorFieldsException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class Handler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<ErrorFieldsBody>> MethodArgumentNotValid(MethodArgumentNotValidException e){
        List<ErrorFieldsBody> erros = e.getFieldErrors().stream().map(ErrorFieldsBody::new).collect(Collectors.toList());
        return ResponseEntity.badRequest().body(erros);
    }

    @ExceptionHandler(ErrorFieldsException.class)
    public ResponseEntity<ErrorFieldsBody> PersonalizadaFields(ErrorFieldsException e){
        ErrorFieldsBody error = new ErrorFieldsBody(e.getCampo(), e.getMessage());
        return ResponseEntity.status(e.getStatus()).body(error);
    }
}
