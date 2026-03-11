package com.Banco.projeto_bancario.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Data
public class Conta {

    private boolean ativo = true;
    private String agencia = "0001";
    private String numeroConta;

    @PrePersist
    public void gerarNumero(){
        int numero = (int) (Math.random() * 90000) + 10000;
        int digito = (int) (Math.random() * 10);
        this.numeroConta = numero + " - " + digito;
    }

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
