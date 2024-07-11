package com.willian.AlpacaFilmes.infra;

import com.willian.AlpacaFilmes.application.services.CriarProgamacaoServices;
import com.willian.AlpacaFilmes.common.events.FilmeEvent;
import com.willian.AlpacaFilmes.domain.entities.Filme;
import com.willian.AlpacaFilmes.infra.listeners.FilmeEventListener;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.logging.Logger;

import static org.mockito.Mockito.*;

@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class FilmeEventListenerTest {

    @Mock
    private CriarProgamacaoServices progamacaoServices;

    @Mock
    private Logger logger;

    @InjectMocks
    private FilmeEventListener listener;

    private Filme filme;

    private FilmeEvent event;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        filme = new Filme();
    }

    @DisplayName("Teste sucesso do Filme Event listenner")
    @Test
    public void testHandleFilmeEvent_Success() {
        //Given / Arrange
        filme = new Filme();
        filme.setId(1L);
        event = new FilmeEvent(filme);

        doNothing().when(progamacaoServices).criarProgramacao(filme);

        //When / Act
        listener.handleFilmeEvent(event);

        //Then /Assert
        verify(progamacaoServices, times(1)).criarProgramacao(filme);
        verify(logger, never()).warning(anyString());
    }

    @DisplayName("Teste exception do Filme Event listenner")
    @Test
    public void testHandleFilmeEvent_Exception() {
        //Given / Arrange
        filme = new Filme();
        filme.setId(2L);
        event = new FilmeEvent(filme);

        doThrow(new RuntimeException("Simulated exception")).when(progamacaoServices).criarProgramacao(filme);

        //When / Act
        listener.handleFilmeEvent(event);

        //Then /Assert
        verify(progamacaoServices, times(1)).criarProgramacao(filme);
        verify(logger, times(1)).warning("Conflito ao criar programação para o filme: " + filme.getId());
    }
}
