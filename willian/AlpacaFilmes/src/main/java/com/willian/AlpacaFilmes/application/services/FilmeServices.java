package com.willian.AlpacaFilmes.application.services;

import com.willian.AlpacaFilmes.application.exceptions.ApiResponseException;
import com.willian.AlpacaFilmes.application.exceptions.ResourceNotFoundException;
import com.willian.AlpacaFilmes.domain.dto.FilmeDTO;
import com.willian.AlpacaFilmes.domain.entities.Filme;
import com.willian.AlpacaFilmes.infra.repositories.FilmesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Service
public class FilmeServices {
    Logger logger = Logger.getLogger(TheMovieDbServices.class.getName());
    private static final int MAX_RETRIES = 3;
    private static final int RETRY_DELAY_MS = 60000;

    @Autowired
    private FilmesRepository filmesRepository;

    @Autowired
    private TheMovieDbServices movieDbServices;

    @Transactional
    public void salvarFilmes() {
        int attempts = 0;
        while(attempts < MAX_RETRIES) {
            try {
                List<Filme> filmes = movieDbServices.getMovies();

                for (Filme filme : filmes) {
                    Optional<Filme> estaRegistrado = filmesRepository.findById(filme.getId());

                    if (estaRegistrado.isEmpty()) {
                        filmesRepository.save(filme);
                    }
                }
                logger.info("Filmes Atualizados");
                attempts = MAX_RETRIES;
            } catch (ApiResponseException ae) {
                attempts++;
                logger.warning("Falha ao resgatar filmes. Tentativa " + attempts + " de " + MAX_RETRIES);

                if (attempts >= MAX_RETRIES) {
                    logger.warning("Número máximo de tentativas atingido. Abortando." + ae.getMessage());
                }

                try {
                    Thread.sleep(RETRY_DELAY_MS);
                } catch (InterruptedException ie) {
                    Thread.currentThread().interrupt();
                    logger.warning("Thread interrompida" + ie);
                }
            } catch (Exception ex) {
                logger.warning("Erro inesperado ao tentar salvar filmes: " + ex.getMessage());
                attempts = MAX_RETRIES;
            }
        }
    }

    public FilmeDTO findById(Long id) {
        Filme filme = filmesRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Nenhum dado encontrado para o Id " + id + "!"));

        return new FilmeDTO(filme);
    }

    public List<FilmeDTO> findAll() {
        List<Filme> filmeList = filmesRepository.findAll();
        return filmeList.stream().map(FilmeDTO::new).toList();
    }
}
