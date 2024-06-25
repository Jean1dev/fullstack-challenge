package com.willian.AlpacaFilmes.application.services;

import com.willian.AlpacaFilmes.application.exceptions.ApiResponseException;
import com.willian.AlpacaFilmes.application.exceptions.ResourceNotFoundException;
import com.willian.AlpacaFilmes.domain.dto.FilmeDTO;
import com.willian.AlpacaFilmes.domain.dto.themoviedb.Movie;
import com.willian.AlpacaFilmes.domain.entities.Filme;
import com.willian.AlpacaFilmes.infra.repositories.FilmesRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class FilmesServicesTest {

    @Mock
    private FilmesRepository filmesRepository;
    @Mock
    private TheMovieDbServices theMovieDbServices;
    @InjectMocks
    private FilmeServices filmeServices;

    private Filme filme;

    @BeforeAll
    public void setUp() {
        filme = new Filme(12367L, "Teste de filme", "Film test", "2024-05-01",
                "asdasdasda.jpg", "Esse é um modelo de overview", new Date());
    }

    @DisplayName("teste Salvar Filmes quando buscando na API")
    @Test
    void testSalvarFilmes() {
        //Given / Arrange
        Filme filme2 = new Filme(123696L, "Teste de filme 2", "Film test 2", "2025-10-11",
                "asdasdasda123123.jpg", "Esse é um modelo de overview do teste de filme 2", new Date());

        doReturn(List.of(filme, filme2)).when(theMovieDbServices).getMovies();

        doReturn(Optional.empty()).when(filmesRepository).findById(anyLong());

        //When / ActFilmeDTO result = filmeServices.findById(12367L);
        filmeServices.salvarFilmes();

        //Then /Assert
        verify(filmesRepository, times(2)).save(any(Filme.class));
    }

    @DisplayName("teste Salvar Filmes quando passamos uma lista invalida do retorno da api")
    @Test
    void testSalvarFilmesGivenWrongEntity() {
        //Given / Arrange
        List<Movie> invalidMovies = criarMovies();
        doReturn(invalidMovies).when(theMovieDbServices).getMovies();

        //When / ActFilmeDTO result = filmeServices.findById(12367L);
        filmeServices.salvarFilmes();

        //Then /Assert
        verify(filmesRepository, times(0)).save(any(Filme.class));
    }

    @DisplayName("teste Salvar Filmes quando a api retorna error")
    @Test
    void testSalvarFilmesGivenAPIError() {
        //Given / Arrange
        doThrow(new ApiResponseException("Erro na API")).when(theMovieDbServices).getMovies();

        //When / ActFilmeDTO result = filmeServices.findById(12367L);
        filmeServices.salvarFilmes();

        //Then /Assert
        verify(filmesRepository, times(0)).save(any(Filme.class));
        verify(theMovieDbServices, times(3)).getMovies();
    }

    @DisplayName("teste find by id buscando por um id existente deve retornar um objeto do tipo filmeDTO")
    @Test
    void testFindById() {
    	//Given / Arrange
    	doReturn(Optional.of(filme)).when(filmesRepository).findById(anyLong());

    	//When / Act
        FilmeDTO result = filmeServices.findById(12367L);

        //Then /Assert
        assertNotNull(result, () -> "Não deveria retornar null");
        assertNotNull(result.getId(), () -> "ID Should not return null");
        assertEquals(result.getTitle(), filme.getTitle(), () -> "Os titulos do filme retornado não é o mesmo do mock");
        assertEquals(result.getId(), filme.getId(), () -> "Ids do filme retornado não é o mesmo do mock");
        assertEquals(result.getOverview(), filme.getOverview(), () -> "Overview do filme retornado não é o mesmo do mock");
    }

    @DisplayName("teste find by id buscando por um id inexistente deve apresentar exceção")
    @Test
    void testFindByIdGivenNoExistentId() {
        //Given / Arrange
        String expectedMessage = "Nenhum dado encontrado para o Id " + 123L + "!";

        //When / Act
        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> {
            filmeServices.findById(123L);
        }, () -> "Should be a exception");

        String actualMessage = exception.getMessage();

        //Then /Assert
        assertTrue(actualMessage.contains(expectedMessage), () ->"Deveria conter a mensagem " + expectedMessage);
    }

    @DisplayName("teste find All quando buscar todos os filmes existentes deve retornar uma lista de objetos do tipo filmeDTO")
    @Test
    void testFindAll() {
        //Given / Arrange
        Filme filme2 = new Filme(123696L, "Teste de filme 2", "Film test 2", "2025-10-11",
                "asdasdasda123123.jpg", "Esse é um modelo de overview do teste de filme 2", new Date());

        doReturn(List.of(filme, filme2)).when(filmesRepository).findAll();

        //When / Act
        List<FilmeDTO> result = filmeServices.findAll();

        FilmeDTO filmeDTO1 = result.getFirst();
        FilmeDTO filmeDTO2 = result.get(1);

        //Then /Assert
        assertNotNull(result, () -> "Não deveria retornar null");
        assertNotNull(filmeDTO1.getId(), () -> "ID Should not return null");
        assertEquals(filmeDTO1.getTitle(), filme.getTitle(), () -> "Os titulos do filme retornado não é o mesmo do mock");
        assertEquals(filmeDTO1.getId(), filme.getId(), () -> "Ids do filme retornado não é o mesmo do mock");
        assertEquals(filmeDTO1.getOverview(), filme.getOverview(), () -> "Overview do filme retornado não é o mesmo do mock");

        assertNotNull(filmeDTO2.getId(), () -> "ID Should not return null");
        assertEquals(filmeDTO2.getTitle(), filme2.getTitle(), () -> "Os titulos do filme retornado não é o mesmo do filme 2");
        assertEquals(filmeDTO2.getId(), filme2.getId(), () -> "Ids do filme retornado não é o mesmo do ilme 2");
        assertEquals(filmeDTO2.getOverview(), filme2.getOverview(), () -> "Overview do filme retornado não é o mesmo do ilme 2");
    }

    @DisplayName("Test Find all dando um lista de filmes vazias deve retornar uma lista vazia")
    @Test
    void testtestFindAll_GivenEmptyPersonList() throws Exception {
        //Given / Arrange
        doReturn(Collections.EMPTY_LIST).when(filmesRepository).findAll();

        //When / Act
        List<FilmeDTO> result = filmeServices.findAll();

        //Then /Assert
        assertTrue(result.isEmpty(), () -> "A lista de filmes deveria ser vazia");
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

        List<Movie> movies = new ArrayList<>();
        movies.add(movie1);
        movies.add(movie2);

        return movies;
    }
}
