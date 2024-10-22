package com.willian.AlpacaFilmes.application.services;

import com.willian.AlpacaFilmes.application.exceptions.ResourceNotFoundException;
import com.willian.AlpacaFilmes.domain.dto.CadeirasDTO;
import com.willian.AlpacaFilmes.domain.dto.SalasDTO;
import com.willian.AlpacaFilmes.domain.entities.Cadeiras;
import com.willian.AlpacaFilmes.domain.entities.Salas;
import com.willian.AlpacaFilmes.infra.repositories.SalasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Service
public class SalasServices {
    Logger logger = Logger.getLogger(TheMovieDbServices.class.getName());

    @Autowired
    private SalasRepository salasRepository;

    public List<SalasDTO> pegarTodos() {
        List<Salas> salasList = salasRepository.findAll();
        return salasList.stream().map(SalasDTO::new).toList();
    }

    public List<CadeirasDTO> buscarCadeirasSalas(Long id) {
        Salas salas = salasRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Nenhum dado encontrado para o Id: " + id + "!"));

        List<Cadeiras> cadeiras = salas.getCadeiras();
        return cadeiras.stream()
                .map(CadeirasDTO::new)
                .collect(Collectors.toList());
    }

    public SalasDTO pegarPorId(Long id) {
        Salas salas = salasRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Nenhum dado encontrado para o Id: " + id + "!"));
        return new SalasDTO(salas);
    }
}
