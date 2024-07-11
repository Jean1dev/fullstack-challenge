package com.willian.AlpacaFilmes.application.services;

import com.willian.AlpacaFilmes.domain.dto.CinemaDTO;
import com.willian.AlpacaFilmes.domain.entities.Cinema;
import com.willian.AlpacaFilmes.infra.repositories.CinemaRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.doReturn;

@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CinemaServicesTest {

    @Mock
    private CinemaRepository cinemaRepository;

    @InjectMocks
    private CinemaServices cinemaServices;

    private Cinema cinema;

    @BeforeAll
    public void setUp() {
        cinema =new Cinema(1L, "Alpaca Cinema");
    }

    @DisplayName("teste find All quando buscar todos os cinemas cadastrados deve retornar uma lista de objetos do tipo CinemaDTO")
    @Test
    void testFindAll() {
        //Given / Arrange
        doReturn(List.of(cinema)).when(cinemaRepository).findAll();

        //When / Act
        List<CinemaDTO> result = cinemaServices.findAll();

        CinemaDTO cinemaDTO = result.getFirst();

        //Then /Assert
        assertNotNull(result, () -> "Não deveria retornar null");
        assertNotNull(cinemaDTO.getId(), () -> "ID não deveria ser nulo");
        assertNotNull(cinemaDTO.getId(), () -> "ID não deveria ser nulo");
        assertEquals(cinemaDTO.getNome(), cinema.getNome(), () -> "O Nome do cinema retornado não é o mesmo do mock");
    }
}
