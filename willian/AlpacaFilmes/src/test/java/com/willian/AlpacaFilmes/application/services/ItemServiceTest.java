package com.willian.AlpacaFilmes.application.services;

import com.willian.AlpacaFilmes.application.exceptions.ResourceNotFoundException;
import com.willian.AlpacaFilmes.domain.dto.ItemDTO;
import com.willian.AlpacaFilmes.domain.entities.Item;
import com.willian.AlpacaFilmes.infra.repositories.ItemRepository;
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
public class ItemServiceTest {
    @Mock
    private ItemRepository itemRepository;

    @InjectMocks
    private ItemService itemService;

    private Item item;

    @BeforeAll
    public void setup() {
        item = new Item(1L, "item 1", 15.00);
    }

    @DisplayName("teste buscar todos os itens deve retornar uma lista de objetos do tipo ItemDTO")
    @Test
    void testFindAll() {
        //Given / Arrange
        Item item1 = new Item(2L, "item 2", 10.00);

        doReturn(List.of(item, item1)).when(itemRepository).findAll();

        //When / Act
        List<ItemDTO> result = itemService.pegarTodos();

        ItemDTO result1 = result.getFirst();
        ItemDTO result2 = result.getLast();

        //Then /Assert
        assertNotNull(result, () -> "Não deveria retornar null");
        assertNotNull(result1.getId(), () -> "ID não deveria ser nulo");
        assertEquals(result1.getNome(), item.getNome(), () -> "O nome do item retornado não é o mesmo do mock");
        assertEquals(result1.getPreco(), item.getPreco(), () -> "O preço do item retornado não é o mesmo do mock");

        assertNotNull(result2.getId(), () -> "ID não deveria ser nulo");
        assertEquals(result2.getNome(), item1.getNome(), () -> "O nome do item retornado não é o mesmo do mock");
        assertEquals(result2.getPreco(), item1.getPreco(), () -> "O preço do item retornado não é o mesmo do mock");

    }

    @DisplayName("Test buscar todos dando um lista de itens vazias deve retornar uma lista vazia")
    @Test
    void testtestFindAll_GivenEmptyPersonList() throws Exception {
        //Given / Arrange
        doReturn(Collections.EMPTY_LIST).when(itemRepository).findAll();

        //When / Act
        List<ItemDTO> result = itemService.pegarTodos();

        //Then /Assert
        assertTrue(result.isEmpty(), () -> "A lista de itens deveria ser vazia");
    }

    @DisplayName("teste buscar por id buscando por um id existente deve retornar um objeto do tipo Item DTO")
    @Test
    void testFindById() {
        //Given / Arrange
        doReturn(Optional.of(item)).when(itemRepository).findById(anyLong());

        //When / Act
        ItemDTO result = itemService.buscarPorId(1L);

        //Then /Assert
        assertNotNull(result, () -> "Não deveria retornar null");
        assertNotNull(result.getId(), () -> "ID não deveria ser nulo");
        assertEquals(result.getNome(), item.getNome(), () -> "O nome do item retornado não é o mesmo do mock");
        assertEquals(result.getPreco(), item.getPreco(), () -> "O preço do item retornado não é o mesmo do mock");
    }

    @DisplayName("teste buscando por um id inexistente deve apresentar exceção")
    @Test
    void testFindByIdGivenNoExistentId() {
        //Given / Arrange
        String expectedMessage = "Nenhum dado encontrado para o Id " + 185L + "!";

        //When / Act
        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> {
            itemService.buscarPorId(185L);
        }, () -> "Should be a exception");

        String actualMessage = exception.getMessage();

        //Then /Assert
        assertTrue(actualMessage.contains(expectedMessage), () -> "Deveria conter a mensagem " + expectedMessage);
    }
}
