package com.Banco.projeto_bancario.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Data
public class Conta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O nome do titular é obrigatorio")
    private String titular;

    @NotNull(message = "O saldo inicial não pode ser nulo.")
    @Min(value = 0, message = "Não pode ser um valor negativo")
    private Double saldo;

    public Conta(){}

    public Conta(String titular, Double saldo){
        this.titular = titular;
        this.saldo = saldo;
    }
}
