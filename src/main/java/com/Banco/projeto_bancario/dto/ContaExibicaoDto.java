package com.Banco.projeto_bancario.dto;

import com.Banco.projeto_bancario.model.Conta;
import lombok.Getter;

@Getter
public class ContaExibicaoDto {
    private Long id;
    private String titular;

    public ContaExibicaoDto(Conta conta){
        this.id = conta.getId();
        this.titular = conta.getTitular();
    }
}
