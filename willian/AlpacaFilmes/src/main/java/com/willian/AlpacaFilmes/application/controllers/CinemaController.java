package com.willian.AlpacaFilmes.application.controllers;

import com.willian.AlpacaFilmes.application.controllers.interfaces.ICinemaController;
import com.willian.AlpacaFilmes.application.services.CinemaServices;
import com.willian.AlpacaFilmes.domain.dto.CinemaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/cinema")
public class CinemaController implements ICinemaController {
    @Autowired
    private CinemaServices cinemaServices;

    @Override
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<CinemaDTO>> resgatarCinema() {
        List<CinemaDTO> cinemas = cinemaServices.pegarTosdos();
        return ResponseEntity.ok().body(cinemas);
    }
}
