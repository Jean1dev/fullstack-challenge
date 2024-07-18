package com.willian.AlpacaFilmes.infra.repositories;

import com.willian.AlpacaFilmes.domain.entities.Combo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComboRepository extends JpaRepository<Combo, Long> {
}
