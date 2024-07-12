package com.willian.AlpacaFilmes.application.scheduling;

import com.willian.AlpacaFilmes.application.services.FilmeServices;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
public class FilmeSchedulerTest {

    @Mock
    private FilmeServices filmeServices;

    @InjectMocks
    private FilmeScheduler filmeScheduler;

    @DisplayName("test agendamento cadastra filmes")
    @Test
    void testCadastrarFilmes() {
        //Given / Arrange
        //When / Act
        filmeScheduler.cadastraFilmes();

        //Then /Assert
        verify(filmeServices, times(1)).salvarFilmes();
    }
}
