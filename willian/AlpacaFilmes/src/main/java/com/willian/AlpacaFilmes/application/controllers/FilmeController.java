package com.willian.AlpacaFilmes.application.controllers;

import com.willian.AlpacaFilmes.application.controllers.interfaces.IFilmeController;
import com.willian.AlpacaFilmes.application.services.FilmeServices;
import com.willian.AlpacaFilmes.application.services.TheMovieDbServices;
import com.willian.AlpacaFilmes.domain.dto.FilmeDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("/v1/filmes")
public class FilmeController implements IFilmeController {
    Logger logger = Logger.getLogger(TheMovieDbServices.class.getName());
    @Autowired
    private FilmeServices filmeServices;

    @Override
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<FilmeDTO>> buscarTodos() {
        List<FilmeDTO> filmeDTOList = filmeServices.findAll();
        return ResponseEntity.ok().body(filmeDTOList);
    }

    @Override
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<FilmeDTO> buscarPorId(@PathVariable(value = "id") Long id) {
        logger.info("id " + id);
        FilmeDTO filmeDTO = filmeServices.findById(id);
        return ResponseEntity.ok().body(filmeDTO);
    }
}
