package com.willian.AlpacaFilmes.application.services;

import com.willian.AlpacaFilmes.application.exceptions.ResourceNotFoundException;
import com.willian.AlpacaFilmes.domain.entities.Horarios;
import com.willian.AlpacaFilmes.infra.repositories.HorariosRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doReturn;

@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class HorariosServicesTest {

    @Mock
    private HorariosRepository horariosRepository;

    @InjectMocks
    private HorariosServices horariosServices;

    private Horarios horarios;

    @BeforeAll
    public void setup() {
        horarios = new Horarios(1L, "11:00", "13:00");
    }

    @DisplayName("teste buscar todos os horarios deve retornar uma lista de objetos do tipo Horario")
    @Test
    void testFindAll() {
        //Given / Arrange
        Horarios horarios1 = new Horarios(2L, "14:00", "16:00");

        doReturn(List.of(horarios, horarios1)).when(horariosRepository).findAll();

        //When / Act
        List<Horarios> result = horariosServices.pegarTodos();

        Horarios result1 = result.getFirst();
        Horarios result2 = result.getLast();

        //Then /Assert
        assertNotNull(result, () -> "Não deveria retornar null");
        assertNotNull(result1.getId(), () -> "ID não deveria ser nulo");
        assertEquals(result1.getHoraInicio(), horarios.getHoraInicio(), () -> "A hora de inicio retornada não é o mesmo do mock");
        assertEquals(result1.getHoraFim(), horarios.getHoraFim(), () -> "A hora de temino retornada não é o mesmo do mock");

        assertNotNull(result2.getId(), () -> "ID não deveria ser nulo");
        assertEquals(result2.getHoraInicio(), horarios1.getHoraInicio(), () -> "A hora de inicio retornada não é o mesmo do mock");
        assertEquals(result2.getHoraFim(), horarios1.getHoraFim(), () -> "A hora de temino retornada não é o mesmo do mock");
    }

    @DisplayName("Test buscar todos dando um lista de horarios vazias deve retornar uma lista vazia")
    @Test
    void testtestFindAll_GivenEmptyPersonList() throws Exception {
        //Given / Arrange
        doReturn(Collections.EMPTY_LIST).when(horariosRepository).findAll();

        //When / Act
        List<Horarios> result = horariosServices.pegarTodos();

        //Then /Assert
        assertTrue(result.isEmpty(), () -> "A lista de combos deveria ser vazia");
    }

    @DisplayName("teste buscar por id buscando por um id existente deve retornar um objeto do tipo Horario")
    @Test
    void testFindById() {
        //Given / Arrange
        doReturn(Optional.of(horarios)).when(horariosRepository).findById(anyLong());

        //When / Act
        Horarios result = horariosServices.pegarPorId(1L);

        //Then /Assert
        assertNotNull(result, () -> "Não deveria retornar null");
        assertNotNull(result.getId(), () -> "ID não deveria ser nulo");
        assertEquals(result.getHoraInicio(), horarios.getHoraInicio(), () -> "A hora de inicio retornada não é o mesmo do mock");
        assertEquals(result.getHoraFim(), horarios.getHoraFim(), () -> "A hora de temino retornada não é o mesmo do mock");
    }

    @DisplayName("teste find by id buscando por um id inexistente deve apresentar exceção")
    @Test
    void testFindByIdGivenNoExistentId() {
        //Given / Arrange
        String expectedMessage = "Nenhum dado encontrado para o Id " + 185L + "!";

        //When / Act
        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> {
            horariosServices.pegarPorId(185L);
        }, () -> "Should be a exception");

        String actualMessage = exception.getMessage();

        //Then /Assert
        assertTrue(actualMessage.contains(expectedMessage), () -> "Deveria conter a mensagem " + expectedMessage);
    }
}
