package com.willian.AlpacaFilmes.application.services;

import com.willian.AlpacaFilmes.application.exceptions.ResourceNotFoundException;
import com.willian.AlpacaFilmes.domain.dto.ComboDTO;
import com.willian.AlpacaFilmes.domain.entities.Combo;
import com.willian.AlpacaFilmes.domain.entities.Item;
import com.willian.AlpacaFilmes.infra.repositories.ComboRepository;
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
public class ComboServicesTest {

    @Mock
    private ComboRepository comboRepository;

    @InjectMocks
    private ComboServices comboServices;

    private Combo combo;

    @BeforeAll
    public void setup() {
        combo = new Combo(1L, "Combo 1", criarListaDeItens(), 30.00);
    }

    @DisplayName("teste buscar todos os combos deve retornar uma lista de objetos do tipo ComboDTO")
    @Test
    void testFindAll() {
        //Given / Arrange
        Combo combo1 = new Combo(2L, "Combo 2", criarListaDeItens(), 35.00);

        doReturn(List.of(combo, combo1)).when(comboRepository).findAll();

        //When / Act
        List<ComboDTO> result = comboServices.pegarTodos();

        ComboDTO comboDTO = result.getFirst();
        ComboDTO comboDTO1 = result.getLast();

        //Then /Assert
        assertNotNull(result, () -> "Não deveria retornar null");
        assertNotNull(comboDTO.getId(), () -> "ID não deveria ser nulo");
        assertEquals(comboDTO.getNome(), combo.getNome(), () -> "Os nomes do combo retornado não é o mesmo do mock");
        assertEquals(comboDTO.getPreco(), combo.getPreco(), () -> "Ids do combo retornado não é o mesmo do mock");
        assertEquals(comboDTO.getItens().getFirst().getId(), combo.getItens().getFirst().getId(), () -> "O id do primeiro item do combo retornado não é o mesmo do mock");

        assertNotNull(comboDTO1.getId(), () -> "ID não deveria ser nulo");
        assertEquals(comboDTO1.getNome(), combo1.getNome(), () -> "Os nomes do combo retornado não é o mesmo do mock");
        assertEquals(comboDTO1.getPreco(), combo1.getPreco(), () -> "Ids do combo retornado não é o mesmo do mock");
        assertEquals(comboDTO1.getItens().getFirst().getId(), combo1.getItens().getFirst().getId(), () -> "O id do primeiro item do combo retornado não é o mesmo do mock");
    }

    @DisplayName("Test buscar todos dando um lista de combos vazias deve retornar uma lista vazia")
    @Test
    void testtestFindAll_GivenEmptyPersonList() throws Exception {
        //Given / Arrange
        doReturn(Collections.EMPTY_LIST).when(comboRepository).findAll();

        //When / Act
        List<ComboDTO> result = comboServices.pegarTodos();

        //Then /Assert
        assertTrue(result.isEmpty(), () -> "A lista de combos deveria ser vazia");
    }

    @DisplayName("teste buscar por id id buscando por um id existente deve retornar um objeto do tipo ComboDTO")
    @Test
    void testFindById() {
        //Given / Arrange
        doReturn(Optional.of(combo)).when(comboRepository).findById(anyLong());

        //When / Act
        ComboDTO result = comboServices.buscarPorId(1L);

        //Then /Assert
        assertNotNull(result, () -> "Não deveria retornar null");
        assertNotNull(result.getId(), () -> "ID não deveria ser nulo");
        assertEquals(result.getNome(), combo.getNome(), () -> "O nome do combo retornado não é o mesmo do mock");
        assertEquals(result.getId(), combo.getId(), () -> "Id do combo retornado não é o mesmo do mock");
        assertEquals(result.getItens().getFirst().getId(), combo.getItens().getFirst().getId(), () -> "O id do primeirio item do combo retornado não é o mesmo do mock");
    }

    @DisplayName("teste find by id buscando por um id inexistente deve apresentar exceção")
    @Test
    void testFindByIdGivenNoExistentId() {
        //Given / Arrange
        String expectedMessage = "Nenhum dado encontrado para o Id " + 185L + "!";

        //When / Act
        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> {
            comboServices.buscarPorId(185L);
        }, () -> "Should be a exception");

        String actualMessage = exception.getMessage();

        //Then /Assert
        assertTrue(actualMessage.contains(expectedMessage), () -> "Deveria conter a mensagem " + expectedMessage);
    }


    private List<Item> criarListaDeItens() {
        List<Item> itens = new ArrayList<>();

        itens.add(new Item(1L, "Item1", 10.0));
        itens.add(new Item(2L, "Item2", 20.0));
        itens.add(new Item(3L, "Item3", 30.0));
        return itens;
    }
}
