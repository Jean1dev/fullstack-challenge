package com.willian.AlpacaFilmes.infra.listeners;

import com.willian.AlpacaFilmes.common.events.FilmeEvent;
import com.willian.AlpacaFilmes.domain.entities.Filme;
import jakarta.persistence.PostPersist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class FilmeTrailListener {

    @Autowired
    private ApplicationEventPublisher eventPublisher;

    @PostPersist
    private void aposPersistirFilme(Filme filme) {
        eventPublisher.publishEvent(new FilmeEvent(this, filme));
    }
}
