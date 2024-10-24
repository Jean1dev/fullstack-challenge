package com.willian.AlpacaFilmes.application.services;

import com.willian.AlpacaFilmes.application.exceptions.ApiResponseException;
import com.willian.AlpacaFilmes.domain.dto.themoviedb.Movie;
import com.willian.AlpacaFilmes.domain.dto.themoviedb.TheMovieDbResponse;
import com.willian.AlpacaFilmes.domain.entities.Filme;
import com.willian.AlpacaFilmes.infra.clients.TheMovieDbRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class TheMovieDbServices {
    private final TheMovieDbRepository theMovieDbRepository;

    @Autowired
    public TheMovieDbServices(TheMovieDbRepository theMovieDbRepository) {
        this.theMovieDbRepository = theMovieDbRepository;
    }

    @Value("${the-movie-db.token}")
    private String accessToken;

    public List<Filme> getMovies() throws ApiResponseException {

        List<Filme> filmes;

        try {
            ResponseEntity<TheMovieDbResponse> result = theMovieDbRepository.getNowPlayingMovies("application/json", "Bearer " + this.accessToken);
            TheMovieDbResponse response = result.getBody();

            filmes = resgatarPrimeiros(response);

            return filmes;
        } catch (Exception e) {
            throw new ApiResponseException("Falha ao resgatar filmes: " + e.getMessage(), e);
        }
    }

    private List<Filme> resgatarPrimeiros(TheMovieDbResponse response) {
        List<Filme> filmes = new ArrayList<>();

        for(int i = 0; i < 4; i++) {
            assert response != null;
            Movie movie = response.getResults().get(i);
            filmes.add(converteParaFilme(movie));
        }

        return filmes;
    }

    private Filme converteParaFilme(Movie movie) {
        Filme filme = new Filme();
        filme.setId((long) movie.getId());
        filme.setReleaseDate(movie.getReleaseDate());
        filme.setOverview(movie.getOverview());
        filme.setTitle(movie.getTitle());
        filme.setOriginalTitle(movie.getOriginalTitle());
        filme.setPosterPath(movie.getPosterPath());
        filme.setDataCadastro(new Date());
        return filme;
    }
}
