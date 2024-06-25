package com.willian.AlpacaFilmes.application.services;

import com.willian.AlpacaFilmes.application.exceptions.ResourceNotFoundException;
import com.willian.AlpacaFilmes.domain.entities.Cadeiras;
import com.willian.AlpacaFilmes.domain.enums.CadeiraStatus;
import com.willian.AlpacaFilmes.infra.repositories.CadeirasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CadeirasServices {
    @Autowired
    private CadeirasRepository cadeirasRepository;

    public void updateStatus(CadeiraStatus status, Long id) {
        if(status == null) throw new IllegalArgumentException("Status não pode ser nulo!");

        Cadeiras cadeiras = cadeirasRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Nenhum dado encontrado para o Id " + id + "!"));

        cadeiras.setStatus(status);

        cadeirasRepository.save(cadeiras);
    }
}
