package com.willian.AlpacaFilmes.infra.repositories;

import com.willian.AlpacaFilmes.domain.entities.Salas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SalasRepository extends JpaRepository<Salas, Long> {
}
