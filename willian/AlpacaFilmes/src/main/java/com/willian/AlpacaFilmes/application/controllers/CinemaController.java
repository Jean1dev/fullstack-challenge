package com.willian.AlpacaFilmes.application.controllers;

import com.willian.AlpacaFilmes.application.controllers.interfaces.ICinemaController;
import com.willian.AlpacaFilmes.application.services.CinemaServices;
import com.willian.AlpacaFilmes.domain.dto.CinemaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/cinema")
public class CinemaController implements ICinemaController {
    @Autowired
    private CinemaServices cinemaServices;

    @Override
    public ResponseEntity<List<CinemaDTO>> resgatarCinema() {
        List<CinemaDTO> cinemas = cinemaServices.findALl();
        return ResponseEntity.ok().body(cinemas);
    }
}
