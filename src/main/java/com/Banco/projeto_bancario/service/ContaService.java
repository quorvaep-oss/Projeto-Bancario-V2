package com.Banco.projeto_bancario.service;

import com.Banco.projeto_bancario.model.Conta;
import com.Banco.projeto_bancario.model.Transacao;
import com.Banco.projeto_bancario.repository.ContaRepository;
import com.Banco.projeto_bancario.repository.TransacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ContaService {


    @Autowired
    private ContaRepository repository;

    @Autowired
    private TransacaoRepository transacaoRepository;

    @Transactional
    public void transferir(Long idOrigem, Long idDestino, Double valor){

        Conta origem = repository.findByIdAndAtivoTrue(idOrigem).orElseThrow(() -> new RuntimeException("Conta de origem não encontrada!"));

        Conta destino = repository.findByIdAndAtivoTrue(idDestino).orElseThrow(() -> new RuntimeException("Conta de destino não encontrada!"));


        if(origem.getSaldo() < valor){
            throw new RuntimeException("Saldo insuficiente para a transferencia!");
        }

        // 1. Atualiza dados
        origem.setSaldo(origem.getSaldo() - valor);
        destino.setSaldo(destino.getSaldo() + valor);

        // 2. Cria o registro de Débito para quem enviou
        Transacao debito = new Transacao();
        debito.setTipo("TRANSFERENCIA_ENVIADA");
        debito.setValor(valor);
        debito.setConta(origem);
        transacaoRepository.save(debito);

        // 3. Cria o registro de Crédito para quem recebeu
        Transacao credito = new Transacao();
        credito.setTipo("TRANSFERENCIA_RECEBIDA");
        credito.setValor(valor);
        credito.setConta(destino);
        transacaoRepository.save(credito);

        repository.save(origem);
        repository.save(destino);
    }

    public void desativarConta(Long id){
        Conta conta = repository.findById(id).orElseThrow(() -> new RuntimeException("Conta não encontrada!"));

        conta.setAtivo(false);
        repository.save(conta);
    }
}
