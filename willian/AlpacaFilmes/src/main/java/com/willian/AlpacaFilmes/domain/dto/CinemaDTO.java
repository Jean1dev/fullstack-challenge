package com.willian.AlpacaFilmes.domain.dto;

import com.fasterxml.jackson.annotation.JsonRootName;
import com.willian.AlpacaFilmes.domain.entities.Cinema;

import java.io.Serializable;

@JsonRootName("cinema")
public class CinemaDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;

    private String nome;

    public CinemaDTO() {
    }

    public CinemaDTO(Long id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public CinemaDTO(Cinema cinema) {
        this.id = cinema.getId();
        this.nome = cinema.getNome();
    }

    public static Cinema converter(CinemaDTO cinemaDTO) {
        Cinema cinema = new Cinema();
        cinema.setId(cinemaDTO.getId());
        cinema.setNome(cinemaDTO.getNome());
        return cinema;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
