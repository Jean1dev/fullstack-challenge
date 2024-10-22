package com.willian.AlpacaFilmes.application.controllers;

import com.willian.AlpacaFilmes.application.controllers.interfaces.ICadeirasController;
import com.willian.AlpacaFilmes.application.services.CadeirasServices;
import com.willian.AlpacaFilmes.domain.dto.CadeiraStatusDTO;
import com.willian.AlpacaFilmes.domain.dto.CadeirasDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/caderias")
public class CadeiraController implements ICadeirasController {

    @Autowired
    private CadeirasServices cadeirasServices;

    @Override
    @PutMapping(value = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateStatus(@PathVariable(value = "id") Long id, @RequestBody CadeiraStatusDTO status) {
        cadeirasServices.updateStatus(status.getStatus(), id);
        return ResponseEntity.noContent().build();
    }
}
