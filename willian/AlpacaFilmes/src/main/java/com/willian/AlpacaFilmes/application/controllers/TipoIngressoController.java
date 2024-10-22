package com.willian.AlpacaFilmes.application.controllers;

import com.willian.AlpacaFilmes.application.controllers.interfaces.ITipoIngressoController;
import com.willian.AlpacaFilmes.application.services.TipoIngressoServices;
import com.willian.AlpacaFilmes.domain.dto.TipoIngressoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/tipo-ingresso")
public class TipoIngressoController implements ITipoIngressoController {

    @Autowired
    private TipoIngressoServices tipoIngressoServices;

    @Override
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<TipoIngressoDTO>> buscarTodos() {
        List<TipoIngressoDTO> tipoIngressoDTOS = tipoIngressoServices.pegarTodos();
        return ResponseEntity.ok().body(tipoIngressoDTOS);
    }

    @Override
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TipoIngressoDTO> buscarPorId(@PathVariable(value = "id") Long id) {
        TipoIngressoDTO tipoIngressoDTO = tipoIngressoServices.buscarPorId(id);
        return ResponseEntity.ok().body(tipoIngressoDTO);
    }
}
