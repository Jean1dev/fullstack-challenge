package com.willian.AlpacaFilmes.application.services;

import com.willian.AlpacaFilmes.domain.entities.Filme;
import com.willian.AlpacaFilmes.infra.repositories.FilmesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Service
public class FilmeServices {
    Logger logger = Logger.getLogger(TheMovieDbServices.class.getName());

    @Autowired
    private FilmesRepository filmesRepository;

    @Autowired
    private TheMovieDbServices movieDbServices;

    public void salvarFilmes() {
        List<Filme> filmes = movieDbServices.getMovies();

        for (Filme filme : filmes) {
            Optional<Filme> estaRegistrado = filmesRepository.findById(filme.getId());

            if (estaRegistrado.isEmpty()) {
                filmesRepository.save(filme);
            }
        }

        logger.info("Filmes Atualizados");
    }
}
