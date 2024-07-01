package com.willian.AlpacaFilmes.application.services;

import com.willian.AlpacaFilmes.domain.dto.themoviedb.Movie;
import com.willian.AlpacaFilmes.domain.dto.themoviedb.TheMovieDbResponse;
import com.willian.AlpacaFilmes.domain.entities.Filme;
import com.willian.AlpacaFilmes.infra.clients.TheMovieDbRepository;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
//@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TheMovieDbServicesTest {

    @MockBean
    private TheMovieDbRepository theMovieDbRepository;

    @InjectMocks
    private TheMovieDbServices theMovieDbServices;

    @DisplayName("Test Get Movies dando lista de filmes deve retornar uma lista de filmes")
    @Test
    public void testGetMovies() {
        //Given / Arrange
        TheMovieDbResponse mockResponse = new TheMovieDbResponse();
        mockResponse.setResults(criarMovies());

        ResponseEntity<TheMovieDbResponse> mockResponseEntity = new ResponseEntity<>(mockResponse, HttpStatus.OK);

        when(theMovieDbRepository.getNowPlayingMovies(eq("application/json"), anyString())).thenReturn(mockResponseEntity);

        //When / Act
        List<Filme> filmes = theMovieDbServices.getMovies();

        //Then /Assert
        assertNotNull(filmes, () -> "Não deveria retornar null");
        assertEquals(4, filmes.size(), () -> "Deveria ter 4 itens na lista");
        assertEquals("Title of Movie 1", filmes.get(0).getTitle(), () -> "O Titulo do filme deveria ser: Title of Movie 1");
        assertEquals("Overview for Movie 2", filmes.get(1).getOverview(), () -> "A Overview do filme deveria ser: Overview for Movie 2");
        assertEquals("Title of Movie 3", filmes.get(2).getTitle(), () -> "O Titulo do filme deveria ser: Title of Movie 3");
        assertEquals("Overview for Movie 3", filmes.get(2).getOverview(), "A Overview do filme deveria ser: Overview for Movie 3");
        assertEquals("Title of Movie 4", filmes.get(3).getTitle(), () -> "O Titulo do filme deveria ser: Title of Movie 4");
        assertEquals("Overview for Movie 4", filmes.get(3).getOverview(), "A Overview do filme deveria ser: Overview for Movie 4");
    }

    private List<Movie> criarMovies() {
        List<Integer> genreIds1 = Arrays.asList(28, 12, 878);
        Movie movie1 = new Movie(
                false,
                "/path/to/backdrop1.jpg",
                genreIds1,
                1234,
                "en",
                "Original Title 1",
                "Overview for Movie 1",
                1000L,
                "/path/to/poster1.jpg",
                "2023-01-01",
                "Title of Movie 1",
                true,
                7.5,
                500
        );

        List<Integer> genreIds2 = Arrays.asList(18, 36);
        Movie movie2 = new Movie(
                true,
                "/path/to/backdrop2.jpg",
                genreIds2,
                5678,
                "fr",
                "Original Title 2",
                "Overview for Movie 2",
                800L,
                "/path/to/poster2.jpg",
                "2024-05-15",
                "Title of Movie 2",
                false,
                8.2,
                300
        );

        List<Integer> genreIds3 = Arrays.asList(35, 10749);
        Movie movie3 = new Movie(
                false,
                "/path/to/backdrop3.jpg",
                genreIds3,
                9101,
                "es",
                "Original Title 3",
                "Overview for Movie 3",
                600L,
                "/path/to/poster3.jpg",
                "2023-08-21",
                "Title of Movie 3",
                true,
                6.9,
                200
        );

        List<Integer> genreIds4 = Arrays.asList(16, 10751);
        Movie movie4 = new Movie(
                false,
                "/path/to/backdrop4.jpg",
                genreIds4,
                1121,
                "jp",
                "Original Title 4",
                "Overview for Movie 4",
                900L,
                "/path/to/poster4.jpg",
                "2024-11-11",
                "Title of Movie 4",
                false,
                9.0,
                150
        );

        List<Movie> movies = new ArrayList<>();
        movies.add(movie1);
        movies.add(movie2);
        movies.add(movie3);
        movies.add(movie4);

        return movies;
    }
}
