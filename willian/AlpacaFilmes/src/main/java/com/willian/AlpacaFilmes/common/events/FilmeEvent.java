package com.willian.AlpacaFilmes.common.events;

import com.willian.AlpacaFilmes.domain.entities.Filme;
import org.springframework.context.ApplicationEvent;

public class FilmeEvent extends ApplicationEvent {
    private final Filme filme;

    public FilmeEvent(Object source, Filme filme) {
        super(source);
        this.filme = filme;
    }

    public Filme getFilme() {
        return filme;
    }
}
