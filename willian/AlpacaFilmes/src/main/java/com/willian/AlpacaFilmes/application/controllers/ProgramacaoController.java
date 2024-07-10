package com.willian.AlpacaFilmes.application.controllers;

import com.willian.AlpacaFilmes.application.controllers.interfaces.IProgramacaoController;
import com.willian.AlpacaFilmes.application.services.ProgramacaoServices;
import com.willian.AlpacaFilmes.domain.dto.ProgramacaoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("v1/programacao")
public class ProgramacaoController implements IProgramacaoController {

    @Autowired
    private ProgramacaoServices programacaoServices;

    @Override
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ProgramacaoDTO>> pegarTodos() {
        List<ProgramacaoDTO> programacaoDTOList = programacaoServices.pegarTodos();
        return ResponseEntity.ok().body(programacaoDTOList);
    }

    @Override
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProgramacaoDTO> pegarPorId(@PathVariable(value = "id") Long id) {
        ProgramacaoDTO programacaoDTO = programacaoServices.pegarPorId(id);
        return ResponseEntity.ok().body(programacaoDTO);
    }
}
