package com.willian.AlpacaFilmes.infra.listeners;

import com.willian.AlpacaFilmes.application.services.CriarProgamacaoServices;
import com.willian.AlpacaFilmes.application.services.TheMovieDbServices;
import com.willian.AlpacaFilmes.common.events.FilmeEvent;
import com.willian.AlpacaFilmes.domain.entities.Filme;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.logging.Logger;

@Component
public class FilmeEventListener {
    @Autowired
    private CriarProgamacaoServices progamacaoServices;

    Logger logger = Logger.getLogger(TheMovieDbServices.class.getName());

    @EventListener
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void handleFilmeEvent(FilmeEvent event) {
        Filme filme = event.getFilme();

        try {
            progamacaoServices.criarProgramacao(filme);
        } catch (Exception e) {
            logger.warning("Conflito ao criar programação para o filme: " + filme.getId() + "Error: " + e.getMessage());
        }
    }

}
