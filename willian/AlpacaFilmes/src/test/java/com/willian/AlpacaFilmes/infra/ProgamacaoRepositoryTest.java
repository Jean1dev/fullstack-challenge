package com.willian.AlpacaFilmes.infra;

import com.willian.AlpacaFilmes.domain.entities.Programacao;
import com.willian.AlpacaFilmes.infra.repositories.ProgamacaoRepository;
import com.willian.AlpacaFilmes.integrationsTest.testContainers.AbstractIntegrationTest;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Transactional
@ActiveProfiles("test")
public class ProgamacaoRepositoryTest extends AbstractIntegrationTest {

    @Autowired
    ProgamacaoRepository progamacaoRepository;

    @DisplayName("Teste do método findTop4ByOrderByIdDesc")
    @Test
    public void testFindTop4ByOrderByIdDesc() {
        // Given / Arrange

        // When / Act
        List<Programacao> result = progamacaoRepository.findTop4ByOrderByIdDesc();

        // Then / Assert
        assertEquals(4, result.size(), () -> "Veirifica se o repositorio está retornando");
    }
}
