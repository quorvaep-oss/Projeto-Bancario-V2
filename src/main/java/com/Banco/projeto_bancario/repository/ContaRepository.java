package com.Banco.projeto_bancario.repository;

import com.Banco.projeto_bancario.model.Conta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ContaRepository extends JpaRepository<Conta, Long> {
    List<Conta> findByAtivoTrue();

    Optional<Conta> findByIdAndAtivoTrue(Long id);
}
