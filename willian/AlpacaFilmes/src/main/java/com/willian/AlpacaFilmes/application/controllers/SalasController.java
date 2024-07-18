package com.willian.AlpacaFilmes.application.controllers;

import com.willian.AlpacaFilmes.application.controllers.interfaces.ISalaController;
import com.willian.AlpacaFilmes.application.services.SalasServices;
import com.willian.AlpacaFilmes.application.services.TheMovieDbServices;
import com.willian.AlpacaFilmes.domain.dto.CadeirasDTO;
import com.willian.AlpacaFilmes.domain.dto.SalasDTO;
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
@RequestMapping("v1/salas")
public class SalasController implements ISalaController {
    Logger logger = Logger.getLogger(TheMovieDbServices.class.getName());
    @Autowired
    private SalasServices salasServices;

    @Override
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<SalasDTO>> buscarTodos() {
        List<SalasDTO> salasDTO = salasServices.pegarTodos();
        return ResponseEntity.ok().body(salasDTO);
    }

    @Override
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SalasDTO> buscarPorId(@PathVariable(value = "id") Long id) {
        SalasDTO salasDTO = salasServices.pegarPorId(id);
        return ResponseEntity.ok().body(salasDTO);
    }

    @Override
    @GetMapping(value = "/{id}/cadeiras", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<CadeirasDTO>> buscarCadeiras(@PathVariable(value = "id") Long id) {
        List<CadeirasDTO> cadeirasDTOS = salasServices.buscarCadeirasSalas(id);
        return ResponseEntity.ok().body(cadeirasDTOS);
    }
}
