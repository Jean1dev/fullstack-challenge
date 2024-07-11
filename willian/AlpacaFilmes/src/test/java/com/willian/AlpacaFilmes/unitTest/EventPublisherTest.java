package com.willian.AlpacaFilmes.unitTest;

import com.willian.AlpacaFilmes.infra.publisher.EventPublisher;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.mockito.Mockito.*;

@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class EventPublisherTest {
    @Mock
    private ApplicationEventPublisher mockEventPublisher;

    @InjectMocks
    private EventPublisher eventPublisher;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @DisplayName("Teste de publicação de evento com transação ativa")
    @Test
    public void testPublishEvent_TransactionActive() {
        // Given / Arrange
        Object event = new Object();
        doAnswer(invocation -> {
            EventPublisher.publishEvent(event);
            return null;
        }).when(mockEventPublisher).publishEvent(any());

        // When / Act
        EventPublisher.publishEvent(event);

        // Then / Assert
        verify(mockEventPublisher, times(1)).publishEvent(event);
    }

    @DisplayName("Teste de publicação de evento sem transação ativa")
    @Test
    public void testPublishEvent_NoTransactionActive() {
        // Given / Arrange
        Object event = new Object();

        // When / Act
        EventPublisher.publishEvent(event);

        // Then / Assert
        verify(mockEventPublisher, times(1)).publishEvent(event);
    }
}
