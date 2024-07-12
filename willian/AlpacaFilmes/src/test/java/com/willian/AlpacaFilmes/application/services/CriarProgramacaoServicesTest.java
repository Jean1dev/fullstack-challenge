package com.willian.AlpacaFilmes.application.services;

import com.willian.AlpacaFilmes.domain.dto.SalasDTO;
import com.willian.AlpacaFilmes.domain.entities.*;
import com.willian.AlpacaFilmes.domain.enums.CadeiraStatus;
import com.willian.AlpacaFilmes.infra.repositories.ProgamacaoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
public class CriarProgramacaoServicesTest {

    @Mock
    private ProgamacaoRepository progamacaoRepository;

    @Mock
    private SalasServices salasServices;

    @Mock
    private HorariosServices horariosServices;

    @InjectMocks
    private CriarProgamacaoServices progamacaoServices;

    private Filme filme;

    private List<Horarios> horarios;

    private List<SalasDTO> salas;

    @BeforeEach
    public void setup() {
        filme = criarFilme();
        horarios = criarHorarios();
        salas = criarSalas();
    }

    @Test
    @DisplayName("Teste criar programação dando um objeto do tipo filme")
    public void testCriarProgrmaca_DandoObjetoFilme() {
        //Given / Arrange
        doReturn(salas).when(salasServices).findAll();
        doReturn(horarios).when(horariosServices).pegarTodos();

        //When / Act
        progamacaoServices.criarProgramacao(filme);

        //Then /Assert
        verify(progamacaoRepository, times(1)).save(any(Programacao.class));
    }

    @Test
    @DisplayName("Teste pegar sala quando criar uma programação")
    public void testPegarSala_QuandoCriar() {
        //Given / Arrange
        when(salasServices.findAll()).thenReturn(salas);

        //When / Act
        Salas result = progamacaoServices.pegarSala();

        //Then /Assert
        assertNotNull(result, () -> "A sala retornada deveria ser nula");
    }

    @Test
    @DisplayName("Teste validar sala livre quando a sala está livre")
    public void testValidarSalaLivre_QuandoSalaLivre() {
        //Given / Arrange
        doReturn(Collections.EMPTY_LIST).when(progamacaoRepository).findTop4ByOrderByIdDesc();

        //When / Act
        boolean result = progamacaoServices.validaSalaLivre(SalasDTO.converter(salas.getFirst()));

        //Then /Assert
        assertTrue(result);
    }

    @Test
    @DisplayName("Teste validar sala livre quando a sala está ocupada")
    public void testValidarSalaLivre_QuandoSalaOcupada() {
        //Given / Arrange
        Salas sala = new Salas();
        sala.setId(1L);

        Programacao programacao = new Programacao();
        programacao.setSala(sala);

        List<Programacao> programacaoList = List.of(programacao);
        when(progamacaoRepository.findTop4ByOrderByIdDesc()).thenReturn(programacaoList);

        //When / Act
        boolean result = progamacaoServices.validaSalaLivre(sala);

        //Then /Assert
        assertFalse(result);
    }

    @Test
    @DisplayName("Teste Liberar sala quando não há salas disponiveis")
    public void testLiberarSala() {
        // Arrange
        Salas sala = new Salas();
        Programacao programacao = new Programacao();
        programacao.setSala(sala);
        List<Programacao> programacaoList = List.of(programacao);

        when(progamacaoRepository.findTop4ByOrderByIdDesc()).thenReturn(programacaoList);

        //When / Act
        Salas result = progamacaoServices.liberarSala();

        //Then /Assert
        assertEquals(sala, result);
    }

    @DisplayName("test pegar horarios ao criar uma programação")
    @Test
    void testGiven_When_Should() {
        //Given / Arrange
        when(horariosServices.pegarTodos()).thenReturn(horarios);
        //When / Act
        List<Horarios> result = progamacaoServices.pegarHorarios();
        //Then /Assert
        assertNotNull(result, () -> "Os horarios retornados deveria ser nula");
        assertFalse(result.isEmpty(), () -> "A lista de horarios não deveria ser vazia");
        assertEquals(4, result.size(), () -> "O tamanha da lista de horarios deveria ser igual a 4");
    }

    private Filme criarFilme() {
        return new Filme(15324L, "Teste de filme", "Film test", "2025-10-11",
                "asdasdasda123123.jpg", "Esse é um modelo de overview do teste de filme", new Date());
    }

    private List<SalasDTO> criarSalas() {
        List<SalasDTO> salasList = new ArrayList<>();
        for (int i = 1; i <= 4; i++) {
            salasList.add(new SalasDTO(Long.parseLong(String.valueOf(i)), i, mockCadeiras()));
        }
        return salasList;
    }

    private List<Horarios> criarHorarios() {
        List<Horarios> horarios = new ArrayList<>();
        for (int i = 1; i <= 4; i++) {
            horarios.add(new Horarios(Long.parseLong(String.valueOf(i)), "10:00", "13:00"));
        }
        return horarios;
    }

    private List<Cadeiras> mockCadeiras() {
        List<Cadeiras> cadeirasList = new ArrayList<>();

        for (int i = 1; i <= 4; i++) {
            cadeirasList.add(new Cadeiras(Long.parseLong(String.valueOf(i)), i, CadeiraStatus.LIVRE));
        }

        return cadeirasList;
    }

}
