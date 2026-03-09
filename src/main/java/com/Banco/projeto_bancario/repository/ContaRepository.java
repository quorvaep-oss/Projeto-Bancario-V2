package com.Banco.projeto_bancario.repository;

import com.Banco.projeto_bancario.model.Conta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContaRepository extends JpaRepository<Conta, Long> {

}
