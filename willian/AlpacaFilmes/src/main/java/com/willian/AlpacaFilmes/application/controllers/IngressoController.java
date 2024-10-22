package com.willian.AlpacaFilmes.application.controllers;

import com.willian.AlpacaFilmes.application.controllers.interfaces.IIngressoController;
import com.willian.AlpacaFilmes.application.services.IngressoServices;
import com.willian.AlpacaFilmes.domain.dto.CriarIngressoDTO;
import com.willian.AlpacaFilmes.domain.dto.IngressoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/ingresso")
public class IngressoController implements IIngressoController {
    @Autowired
    private IngressoServices ingressoServices;

    @Override
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<IngressoDTO> criar(@RequestBody CriarIngressoDTO ingresso) {
        IngressoDTO ingressoDTO = ingressoServices.criar(ingresso);
        return ResponseEntity.ok().body(ingressoDTO);
    }
}
