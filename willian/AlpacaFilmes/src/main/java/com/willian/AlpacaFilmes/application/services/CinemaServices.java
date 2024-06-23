package com.willian.AlpacaFilmes.application.services;

import com.willian.AlpacaFilmes.domain.dto.CinemaDTO;
import com.willian.AlpacaFilmes.domain.entities.Cinema;
import com.willian.AlpacaFilmes.infra.repositories.CinemaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CinemaServices {
    @Autowired
    private CinemaRepository cinemaRepository;

    public List<CinemaDTO> findALl() {
        List<Cinema> cinemas = cinemaRepository.findAll();
        return cinemas.stream().map(CinemaDTO::new).toList();
    }
}
