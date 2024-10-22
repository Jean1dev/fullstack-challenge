package com.willian.AlpacaFilmes.application.services;

import com.willian.AlpacaFilmes.application.exceptions.ResourceNotFoundException;
import com.willian.AlpacaFilmes.domain.entities.Cadeiras;
import com.willian.AlpacaFilmes.domain.enums.CadeiraStatus;
import com.willian.AlpacaFilmes.infra.repositories.CadeirasRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CadeirasServicesTest {

    @Mock
    private CadeirasRepository cadeirasRepository;

    @InjectMocks
    private CadeirasServices cadeirasServices;

    private Cadeiras cadeira;

    @BeforeAll
    public void setUp() {
        cadeira= new Cadeiras(1l,1, CadeiraStatus.LIVRE);
    }

    @DisplayName("test Update Status quando passamos um status válido deve retornar um obejto do tipo Cadeira")
    @Test
    void testUpdateStatus() {
    	//Given / Arrange
       doReturn(Optional.of(cadeira)).when(cadeirasRepository).findById(anyLong());
       doReturn(cadeira).when(cadeirasRepository).save(any(Cadeiras.class));

    	//When / Act
        cadeirasServices.updateStatus(CadeiraStatus.OCUPADA, 1l);

    	//Then /Assert
        verify(cadeirasRepository, times(1)).save(any(Cadeiras.class));
    }

    @DisplayName("test Update Status quando passamos um status inválido deve apresentar exceção")
    @Test
    void testUpstadeStatus_GivenWrongStatus() {
        //Given / Arrange

        //When / Act
        IllegalArgumentException exception = assertThrows( IllegalArgumentException.class,
                () -> {
                    cadeirasServices.updateStatus(CadeiraStatus.valueOf(""), 1l);
                },
                () -> "Status vazio deve causar um Illegal Argument Exception"
        );

        //Then /Assert
        verify(cadeirasRepository, times(0)).save(any(Cadeiras.class));
    }

    @DisplayName("test Update Status quando passamos um ID inválido deve apresentar exceção")
    @Test
    void testUpdateStatus_GivenWrongID() {
        //Given / Arrange
        String expectedMessage = "Nenhum dado encontrado para o Id";

        //When / Act
        ResourceNotFoundException exception = assertThrows( ResourceNotFoundException.class,
                () -> {
                    cadeirasServices.updateStatus(CadeiraStatus.OCUPADA, 100l);
                },
                () -> "ID incorreto deve causar um Resource Not Found Exception"
        );
        String actualMessage = exception.getMessage();

        //Then /Assert
        assertTrue(actualMessage.contains(expectedMessage), () -> "Deveria conter a mensagem: " + expectedMessage);
        verify(cadeirasRepository, times(0)).save(any(Cadeiras.class));
    }
}
