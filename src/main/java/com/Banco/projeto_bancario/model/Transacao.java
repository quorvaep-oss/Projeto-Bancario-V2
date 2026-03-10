package com.Banco.projeto_bancario.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class Transacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String tipo; //Debito ou crédito
    private Double valor;
    private LocalDateTime data;

    @ManyToOne
    @JoinColumn(name = "conta_id")
    @JsonIgnore
    private Conta conta;

    public Transacao(){
        this.data = LocalDateTime.now();
    }

}
