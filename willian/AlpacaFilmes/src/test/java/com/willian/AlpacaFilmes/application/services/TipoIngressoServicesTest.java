package com.willian.AlpacaFilmes.application.services;

import com.willian.AlpacaFilmes.application.exceptions.ResourceNotFoundException;
import com.willian.AlpacaFilmes.domain.dto.TipoIngressoDTO;
import com.willian.AlpacaFilmes.domain.entities.TipoIngresso;
import com.willian.AlpacaFilmes.infra.repositories.TipoIngressoRepository;
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
public class TipoIngressoServicesTest {

    @Mock
    private TipoIngressoRepository tipoIngressoRepository;

    @InjectMocks
    private TipoIngressoServices tipoIngressoServices;

    private TipoIngresso tipoIngresso;

    @BeforeAll
    public void setup() {
        tipoIngresso = new TipoIngresso(1L, "Meia", 15.00);
    }

    @DisplayName("teste buscar todos os tipos de ingressos deve retornar uma lista de objetos do tipo ITipo Ingresso DTO")
    @Test
    void testFindAll() {
        //Given / Arrange
        TipoIngresso tipoIngresso1 = new TipoIngresso(1L, "Meia", 30.00);

        doReturn(List.of(tipoIngresso, tipoIngresso1)).when(tipoIngressoRepository).findAll();

        //When / Act
        List<TipoIngressoDTO> result = tipoIngressoServices.pegarTodos();

        TipoIngressoDTO result1 = result.getFirst();
        TipoIngressoDTO result2 = result.getLast();

        //Then /Assert
        assertNotNull(result, () -> "Não deveria retornar null");
        assertNotNull(result1.getId(), () -> "ID não deveria ser nulo");
        assertEquals(result1.getTipoIngresso(), tipoIngresso.getTipoIngresso(), () -> "O nome retornado não é o mesmo do mock");
        assertEquals(result1.getPreco(), tipoIngresso.getPreco(), () -> "O preço retornado não é o mesmo do mock");

        assertNotNull(result2.getId(), () -> "ID não deveria ser nulo");
        assertEquals(result2.getTipoIngresso(), tipoIngresso1.getTipoIngresso(), () -> "O nome retornado não é o mesmo do mock");
        assertEquals(result2.getPreco(), tipoIngresso1.getPreco(), () -> "O preço retornado não é o mesmo do mock");
    }

    @DisplayName("Test buscar todos dando um lista vazias deve retornar uma lista vazia")
    @Test
    void testtestFindAll_GivenEmptyPersonList() throws Exception {
        //Given / Arrange
        doReturn(Collections.EMPTY_LIST).when(tipoIngressoRepository).findAll();

        //When / Act
        List<TipoIngressoDTO> result = tipoIngressoServices.pegarTodos();

        //Then /Assert
        assertTrue(result.isEmpty(), () -> "A lista de itens deveria ser vazia");
    }

    @DisplayName("teste buscar por id buscando por um id existente deve retornar um objeto do tipo Tipo Ingresso DTO")
    @Test
    void testFindById() {
        //Given / Arrange
        doReturn(Optional.of(tipoIngresso)).when(tipoIngressoRepository).findById(anyLong());

        //When / Act
        TipoIngressoDTO result = tipoIngressoServices.buscarPorId(1L);

        //Then /Assert
        assertNotNull(result, () -> "Não deveria retornar null");
        assertNotNull(result.getId(), () -> "ID não deveria ser nulo");
        assertEquals(result.getTipoIngresso(), tipoIngresso.getTipoIngresso(), () -> "O nome do item retornado não é o mesmo do mock");
        assertEquals(result.getPreco(), tipoIngresso.getPreco(), () -> "O preço do item retornado não é o mesmo do mock");
    }

    @DisplayName("teste buscando por um id inexistente deve apresentar exceção")
    @Test
    void testFindByIdGivenNoExistentId() {
        //Given / Arrange
        String expectedMessage = "Nenhum dado encontrado para o Id " + 185L + "!";

        //When / Act
        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> {
            tipoIngressoServices.buscarPorId(185L);
        }, () -> "Should be a exception");

        String actualMessage = exception.getMessage();

        //Then /Assert
        assertTrue(actualMessage.contains(expectedMessage), () -> "Deveria conter a mensagem " + expectedMessage);
    }

}
