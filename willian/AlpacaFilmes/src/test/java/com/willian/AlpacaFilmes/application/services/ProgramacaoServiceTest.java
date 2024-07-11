package com.willian.AlpacaFilmes.application.services;

import com.willian.AlpacaFilmes.application.exceptions.ResourceNotFoundException;
import com.willian.AlpacaFilmes.domain.dto.ProgramacaoDTO;
import com.willian.AlpacaFilmes.domain.entities.*;
import com.willian.AlpacaFilmes.domain.enums.CadeiraStatus;
import com.willian.AlpacaFilmes.infra.repositories.ProgamacaoRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doReturn;

@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
public class ProgramacaoServiceTest {
    @Mock
    private ProgamacaoRepository progamacaoRepository;

    @InjectMocks
    private ProgramacaoServices programacaoServices;

    @DisplayName("test pegar Todos dando uma lista de programação deve retornar uma lista de programação filme DTO")
    @Test
    public void testFindAll_GivenProgramacaoList() {
        //Given / Arrange
        List<Programacao> programacaoList = criarProgramacao();
        doReturn(programacaoList).when(progamacaoRepository).findTop4ByOrderByIdDesc();

        //When / Act
        List<ProgramacaoDTO> programacaoDTOList = programacaoServices.pegarTodos();

        ProgramacaoDTO programacaoDTO = programacaoDTOList.getFirst();
        ProgramacaoDTO programacaoDTO1 = programacaoDTOList.getLast();

        //Then /Assert
        assertNotNull(programacaoDTOList, () -> "Lista retornada não deveria ser null");
        assertNotNull(programacaoDTO.getId(), () -> "ID não deveria ser nulo");
        assertNotNull(programacaoDTO.getFilme(), () -> "Filme não deveria ser nulo");
        assertNotNull(programacaoDTO.getSala(), () -> "Salas não deveria ser nulo");
        assertNotNull(programacaoDTO.getHorarios(), () -> "Hoaririos não deveria ser nulo");

        assertNotNull(programacaoDTO1.getId(), () -> "ID da programação 1 não deveria ser nulo");
        assertNotNull(programacaoDTO1.getFilme(), () -> "Filme da programação 1 não deveria ser nulo");
        assertNotNull(programacaoDTO1.getSala(), () -> "Salas da programação 1 não deveria ser nulo");
        assertNotNull(programacaoDTO1.getHorarios(), () -> "Hoaririos da programação 1 não deveria ser nulo");
    }

    @DisplayName("Test Find all dando um lista de programação vazias deve retornar uma lista vazia")
    @Test
    void testFindAll_GivenEmptyProgramacaoList() throws Exception {
        //Given / Arrange
        doReturn(Collections.EMPTY_LIST).when(progamacaoRepository).findTop4ByOrderByIdDesc();

        //When / Act
        List<ProgramacaoDTO> result = programacaoServices.pegarTodos();

        //Then /Assert
        assertTrue(result.isEmpty(), () -> "A lista de programação deveria ser vazia");
    }

    @DisplayName("teste find by id buscando por um id existente deve retornar um objeto do tipo programação DTO")
    @org.junit.jupiter.api.Test
    void testFindById() {
        //Given / Arrange
        Programacao programacao = new Programacao(1L, criarFilme("1"), criarSalas("1"), criarHorarios(), new Date());
        doReturn(Optional.of(programacao)).when(progamacaoRepository).findById(anyLong());

        //When / Act
        ProgramacaoDTO result = programacaoServices.pegarPorId(1L);

        //Then /Assert
        assertNotNull(result, () -> "Não deveria retornar null");
        assertNotNull(result.getId(), () -> "ID não deveria ser nulo");
        assertNotNull(result.getFilme(), () -> "Filme não deveria ser nulo");
        assertNotNull(result.getSala(), () -> "Salas não deveria ser nulo");
        assertNotNull(result.getHorarios(), () -> "Hoaririos não deveria ser nulo");
    }

    @DisplayName("teste find by id buscando por um id inexistente deve apresentar exceção")
    @org.junit.jupiter.api.Test
    void testFindByIdGivenNoExistentId() {
        //Given / Arrange
        String expectedMessage = "Nenhum dado encontrado para o Id";

        //When / Act
        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> {
            programacaoServices.pegarPorId(123L);
        }, () -> "Should be a exception");

        String actualMessage = exception.getMessage();

        //Then /Assert
        assertTrue(actualMessage.contains(expectedMessage), () -> "Deveria conter a mensagem " + expectedMessage);
    }

    private List<Programacao> criarProgramacao() {
        List<Programacao> programacoes = new ArrayList<>();

        programacoes.add(new Programacao(1L, criarFilme("1"), criarSalas("1"), criarHorarios(), new Date()));
        programacoes.add(new Programacao(2L, criarFilme("2"), criarSalas("2"), criarHorarios(), new Date()));
        programacoes.add(new Programacao(3L, criarFilme("3"), criarSalas("3"), criarHorarios(), new Date()));
        programacoes.add(new Programacao(4L, criarFilme("4"), criarSalas("4"), criarHorarios(), new Date()));

        return programacoes;
    }

    private Filme criarFilme(String value) {
        return new Filme(Long.parseLong(value), "Teste de filme " + value, "Film test " + value, "2025-10-11",
                "asdasdasda123123.jpg", "Esse é um modelo de overview do teste de filme " + value, new Date());
    }

    private Salas criarSalas(String value) {
        return new Salas(Long.parseLong(value), Integer.parseInt(value), mockCadeiras());
    }

    private List<Horarios> criarHorarios() {
        List<Horarios> horarios = new ArrayList<>();
        for (int i = 1; i < 4; i++) {
            horarios.add(new Horarios(Long.parseLong(String.valueOf(i)), "10:00", "13:00"));
        }
        return horarios;
    }

    private List<Cadeiras> mockCadeiras() {
        List<Cadeiras> cadeirasList = new ArrayList<>();

        for (int i = 1; i < 4; i++) {
            cadeirasList.add(new Cadeiras(Long.parseLong(String.valueOf(i)), i, CadeiraStatus.LIVRE));
        }

        return cadeirasList;
    }
}
