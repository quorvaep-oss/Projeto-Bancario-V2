package com.Banco.projeto_bancario.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class ErroResposta {
    private LocalDateTime timestamp;
    private int status;
    private String mensagem;
    private String caminho;
}
