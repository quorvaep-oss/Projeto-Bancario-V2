package com.Banco.projeto_bancario.dto;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErroResposta> tratarRunTimeException(RuntimeException ex, WebRequest request){

        ErroResposta erro = new ErroResposta(LocalDateTime.now(),
        HttpStatus.BAD_REQUEST.value(), ex.getMessage(), request.getDescription(false)
        );

        return new ResponseEntity<>(erro, HttpStatus.BAD_REQUEST);
    }
}
