package com.willian.AlpacaFilmes.infra.listeners;

import com.willian.AlpacaFilmes.application.services.ProgamacaoServices;
import com.willian.AlpacaFilmes.common.events.FilmeEvent;
import com.willian.AlpacaFilmes.domain.entities.Filme;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class FilmeEventListener {
    @Autowired
    private ProgamacaoServices progamacaoServices;

    @EventListener
    public void handleFilmeEvent(FilmeEvent event) {
        Filme filme = event.getFilme();
        progamacaoServices.criarProgramacao(filme);
    }
}
