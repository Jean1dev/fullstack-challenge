package com.willian.AlpacaFilmes.common.events;

import com.willian.AlpacaFilmes.domain.entities.Filme;

public class FilmeEvent {
    private final Filme filme;

    public FilmeEvent(Filme filme) {
        this.filme = filme;
    }

    public Filme getFilme() {
        return filme;
    }
}
