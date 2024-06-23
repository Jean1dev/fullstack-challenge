package com.willian.AlpacaFilmes.infra.clients;

import com.willian.AlpacaFilmes.domain.dto.themoviedb.TheMovieDbResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "themoviedb", url = "https://api.themoviedb.org/3")
public interface TheMovieDbRepository {
    @GetMapping("/movie/now_playing?language=pt-BR&page=1&region=BR")
    ResponseEntity<TheMovieDbResponse> getNowPlayingMovies(
            @RequestHeader("Accept") String accept,
            @RequestHeader("Authorization") String authorization
    );
}
