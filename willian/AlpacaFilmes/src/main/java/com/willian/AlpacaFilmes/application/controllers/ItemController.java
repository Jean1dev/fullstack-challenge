package com.willian.AlpacaFilmes.application.controllers;

import com.willian.AlpacaFilmes.application.controllers.interfaces.IItemController;
import com.willian.AlpacaFilmes.application.services.ItemService;
import com.willian.AlpacaFilmes.domain.dto.ItemDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/items")
public class ItemController implements IItemController {

    @Autowired
    private ItemService itemService;

    @Override
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ItemDTO>> buscarTodos() {
        List<ItemDTO> itemDTOList = itemService.pegarTodos();
        return ResponseEntity.ok().body(itemDTOList);
    }

    @Override
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ItemDTO> buscarPorId(@PathVariable(value = "id") Long id) {
        ItemDTO itemDTO = itemService.buscarPorId(id);
        return ResponseEntity.ok().body(itemDTO);
    }
}
