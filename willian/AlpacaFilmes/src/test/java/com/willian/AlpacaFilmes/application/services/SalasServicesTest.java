package com.willian.AlpacaFilmes.application.services;

import com.willian.AlpacaFilmes.application.exceptions.ResourceNotFoundException;
import com.willian.AlpacaFilmes.domain.dto.CadeirasDTO;
import com.willian.AlpacaFilmes.domain.dto.SalasDTO;
import com.willian.AlpacaFilmes.domain.entities.Cadeiras;
import com.willian.AlpacaFilmes.domain.entities.Salas;
import com.willian.AlpacaFilmes.domain.enums.CadeiraStatus;
import com.willian.AlpacaFilmes.infra.repositories.SalasRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doReturn;

@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class SalasServicesTest {

    @Mock
    private SalasRepository salasRepository;

    @InjectMocks
    private SalasServices salasServices;

    private Salas salas;

    @BeforeAll
    public void setUp() {
        salas = new Salas(1L, 1, mockCadeiras());
    }

    @DisplayName("test Find all dando um lista de salas deve retornar uma lista de salas")
    @Test
    void testFindAll() {
    	//Given / Arrange
        Salas salas1 = new Salas(2L, 1, mockCadeiras());

        doReturn(List.of(salas, salas1)).when(salasRepository).findAll();

    	//When / Act
        List<SalasDTO> result = salasServices.pegarTodos();

        SalasDTO salasDTO = result.getFirst();
        SalasDTO salasDTO1 = result.get(1);

        //Then /Assert
        assertNotNull(result, () -> "Não deveria retornar null");
        assertNotNull(salasDTO.getId(), () -> "ID não deveria ser nulo");
        assertEquals(salasDTO.getNumero(), salas.getNumero(), () -> "O numero da sala retornada não é o mesmo do mock");
        assertEquals(salasDTO.getCadeiras(), salas.getCadeiras(), () -> "Cadeiras da sala retornada não é o mesmo do mock");

        assertNotNull(salasDTO1.getId(), () -> "ID não deveria ser nulo");
        assertEquals(salasDTO1.getNumero(), salas1.getNumero(), () -> "O numero da sala retornada não é o mesmo da sala 1");
        assertEquals(salasDTO1.getCadeiras(), salas1.getCadeiras(), () -> "Cadeiras da sala retornada não é o mesmo da sala 1");
    }

    @DisplayName("test Find all dando um lista vazia de salas deve retornar uma lista vazia")
    @Test
    void testFindAllGivenEmptyList() {
        //Given / Arrange

        doReturn(Collections.EMPTY_LIST).when(salasRepository).findAll();

        //When / Act
        List<SalasDTO> result = salasServices.pegarTodos();

        //Then /Assert
        assertTrue(result.isEmpty(), () -> "A lista de filmes deveria ser vazia");
    }

    @DisplayName("teste find by id buscando por um id existente deve retornar um objeto do tipo filmeDTO")
    @Test
    void testFindById() {
        //Given / Arrange
        doReturn(Optional.of(salas)).when(salasRepository).findById(anyLong());

        //When / Act
        SalasDTO result = salasServices.pegarPorId(1L);

        //Then /Assert
        assertNotNull(result, () -> "Não deveria retornar null");
        assertNotNull(result.getId(), () -> "ID não deveria ser nulo");
        assertEquals(result.getNumero(), salas.getNumero(), () -> "O numero da sala retornada não é o mesmo da sala 1");
        assertEquals(result.getCadeiras(), salas.getCadeiras(), () -> "Cadeiras da sala retornada não é o mesmo da sala 1");
    }

    @DisplayName("teste find by id buscando por um id inexistente deve apresentar exceção")
    @Test
    void testFindByIdGivenNoExistentId() {
        //Given / Arrange
        String expectedMessage = "Nenhum dado encontrado para o Id: " + 12L + "!";

        //When / Act
        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> {
            salasServices.pegarPorId(12L);
        }, () -> "Should be a exception");

        String actualMessage = exception.getMessage();

        //Then /Assert
        assertTrue(actualMessage.contains(expectedMessage), () ->"Deveria conter a mensagem " + expectedMessage + actualMessage);
    }

    @DisplayName("teste find Cadeiras Salas buscando por um id existente deve retornar um objeto do tipo filmeDTO")
    @Test
    void testFindCadeirasSalas() {
        //Given / Arrange
        doReturn(Optional.of(salas)).when(salasRepository).findById(anyLong());

        List<Cadeiras> cadeirasList = mockCadeiras();
        Cadeiras cadeira = cadeirasList.getFirst();
        Cadeiras cadeira1 = cadeirasList.get(2);

        //When / Act
        List<CadeirasDTO> result = salasServices.buscarCadeirasSalas(1L);

        CadeirasDTO cadeirasDTO = result.getFirst();
        CadeirasDTO cadeirasDTO1 = result.get(2);

        //Then /Assert
        assertNotNull(result, () -> "Não deveria retornar null");
        assertNotNull(cadeirasDTO.getId(), () -> "ID Should not return null");
        assertEquals(cadeirasDTO.getNumero(), cadeira.getNumero(), () -> "O numero da cadeira retornada não é o mesmo da caderia");
        assertEquals(cadeirasDTO.isStatus(), cadeira.isStatus(), () -> "O status da caderia retornada não é o mesmo da caderia");

        assertNotNull(cadeirasDTO1.getId(), () -> "ID Should not return null");
        assertEquals(cadeirasDTO1.getNumero(), cadeira1.getNumero(), () -> "O numero da cadeira 1 retornada não é o mesmo da caderia 1");
        assertEquals(cadeirasDTO1.isStatus(), cadeira1.isStatus(), () -> "O status da caderia 1 retornada não é o mesmo da caderia 1");
    }

    @DisplayName("teste find Cadeiras Salas buscando por um id inexistente deve apresentar exceção")
    @Test
    void testFindCadeirasSalasGivenNoExistentId() {
        //Given / Arrange
        String expectedMessage = "Nenhum dado encontrado para o Id: " + 12L + "!";

        //When / Act
        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> {
            salasServices.buscarCadeirasSalas(12L);
        }, () -> "Should be a exception");

        String actualMessage = exception.getMessage();

        //Then /Assert
        assertTrue(actualMessage.contains(expectedMessage), () ->"Deveria conter a mensagem " + expectedMessage + actualMessage);
    }


    private List<Cadeiras> mockCadeiras() {
        List<Cadeiras> cadeirasList = new ArrayList<>();

        for(int i = 1; i < 4; i++) {
            cadeirasList.add(new Cadeiras(Long.parseLong(String.valueOf(i)), i, CadeiraStatus.LIVRE));
        }

        return cadeirasList;
    }
}
