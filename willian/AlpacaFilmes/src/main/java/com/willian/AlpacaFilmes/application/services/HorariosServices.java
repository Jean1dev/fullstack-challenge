package com.willian.AlpacaFilmes.application.services;

import com.willian.AlpacaFilmes.domain.entities.Horarios;
import com.willian.AlpacaFilmes.infra.repositories.HorariosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HorariosServices {

    @Autowired
    private HorariosRepository horariosRepository;

    public List<Horarios> pegarTodos() {
        return horariosRepository.findAll();
    }
}
