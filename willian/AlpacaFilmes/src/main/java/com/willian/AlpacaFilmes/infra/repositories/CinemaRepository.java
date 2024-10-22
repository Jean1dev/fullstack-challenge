package com.willian.AlpacaFilmes.infra.repositories;

import com.willian.AlpacaFilmes.domain.entities.Cinema;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CinemaRepository extends JpaRepository<Cinema, Long> {
}
