package com.willian.AlpacaFilmes.infra.listeners;

import com.willian.AlpacaFilmes.application.services.FilmeServices;
import com.willian.AlpacaFilmes.common.events.FilmeEvent;
import com.willian.AlpacaFilmes.domain.entities.Filme;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class FilmeEventListener {
    @Autowired
    private FilmeServices filmeServices;

    @EventListener
    public void handleFilmeEvent(FilmeEvent event) {
        Filme filme = event.getFilme();
        filmeServices.salvarFilmes();
    }
}
