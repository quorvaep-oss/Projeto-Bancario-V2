package com.Banco.projeto_bancario.controller;

import com.Banco.projeto_bancario.dto.ContaExibicaoDto;
import com.Banco.projeto_bancario.model.Conta;
import com.Banco.projeto_bancario.model.Transacao;
import com.Banco.projeto_bancario.repository.ContaRepository;
import com.Banco.projeto_bancario.repository.TransacaoRepository;
import com.Banco.projeto_bancario.service.ContaService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static java.lang.Double.sum;

@RestController
@RequestMapping("/contas")
@Validated
public class ContaController {

    @Autowired
    private ContaRepository repository;

    @Autowired
    private ContaService service;

    @Autowired
    private TransacaoRepository transacaoRepository;

    @PostMapping
    public Conta criarConta(@Valid @RequestBody Conta conta){
        return repository.save(conta);
    }

    @PostMapping("/transferir")
    public String transferir(@RequestParam Long de, @RequestParam Long para, @RequestParam @Positive(message = "O valor deve ser maior que zero.") Double valor){
       service.transferir(de, para, valor);
       return "Transferencia realizada!";
    }

    @GetMapping("/{id}/extrato")
    public List<Transacao> verExtrato(@PathVariable Long id){
        return transacaoRepository.findByContaId(id);
    }

    @GetMapping("/saldo-total")
    public Double getSaldoTotal() {
        List<Conta> contas = repository.findByAtivoTrue();

        return contas.stream().mapToDouble(Conta::getSaldo).sum();
    }

    @GetMapping
    public List<ContaExibicaoDto> listarTodas(){
        List<Conta> contas = repository.findByAtivoTrue();

        return contas.stream().map(ContaExibicaoDto::new).toList();
    }

    @DeleteMapping("/{id}")
    public String deletarConta(@PathVariable Long id){
        service.desativarConta(id);
        return "Conta desativa com sucesso!";
    }

}