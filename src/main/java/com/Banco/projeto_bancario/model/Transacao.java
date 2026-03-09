package com.Banco.projeto_bancario.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class Transacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String tipo;
    private Double valor;
    private LocalDateTime data;

    @ManyToOne
    @JoinColumn(name = "conta_id")
    private Conta conta;

    public Transacao(){
        this.data = LocalDateTime.now();
    }
}
