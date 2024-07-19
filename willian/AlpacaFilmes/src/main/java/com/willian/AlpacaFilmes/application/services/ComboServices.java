package com.willian.AlpacaFilmes.application.services;

import com.willian.AlpacaFilmes.application.exceptions.ResourceNotFoundException;
import com.willian.AlpacaFilmes.domain.dto.ComboDTO;
import com.willian.AlpacaFilmes.domain.entities.Combo;
import com.willian.AlpacaFilmes.infra.repositories.ComboRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComboServices {
    @Autowired
    private ComboRepository comboRepository;

    public List<ComboDTO> pegarTodos() {
        List<Combo> combosList = comboRepository.findAll();
        return combosList.stream().map(ComboDTO::new).toList();
    }

    public ComboDTO buscarPorId(Long id) {
        Combo combo = comboRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Nenhum dado encontrado para o Id " + id + "!"));
        return new ComboDTO(combo);
    }
}
