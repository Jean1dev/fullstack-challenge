package com.willian.AlpacaFilmes.application.controllers;

import com.willian.AlpacaFilmes.application.controllers.interfaces.IComboController;
import com.willian.AlpacaFilmes.application.services.ComboServices;
import com.willian.AlpacaFilmes.domain.dto.ComboDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/combos")
public class ComboController implements IComboController {
    @Autowired
    private ComboServices comboService;

    @Override
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ComboDTO>> buscarTodos() {
        List<ComboDTO> comboDTOList = comboService.pegarTodos();
        return ResponseEntity.ok().body(comboDTOList);
    }

    @Override
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ComboDTO> buscarPorId(@PathVariable(value = "id") Long id) {
        ComboDTO comboDTO = comboService.buscarPorId(id);
        return ResponseEntity.ok().body(comboDTO);
    }
}
