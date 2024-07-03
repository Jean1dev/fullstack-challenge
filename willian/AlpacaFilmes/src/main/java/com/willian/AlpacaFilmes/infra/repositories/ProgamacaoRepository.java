package com.willian.AlpacaFilmes.infra.repositories;

import com.willian.AlpacaFilmes.domain.entities.Programacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProgamacaoRepository extends JpaRepository<Programacao, Long> {

    List<Programacao> findTop4ByOrderByIdDesc();
}
