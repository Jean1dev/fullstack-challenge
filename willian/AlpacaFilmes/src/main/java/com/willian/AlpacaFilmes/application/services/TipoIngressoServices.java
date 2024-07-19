package com.willian.AlpacaFilmes.application.services;

import com.willian.AlpacaFilmes.application.exceptions.ResourceNotFoundException;
import com.willian.AlpacaFilmes.domain.dto.TipoIngressoDTO;
import com.willian.AlpacaFilmes.domain.entities.TipoIngresso;
import com.willian.AlpacaFilmes.infra.repositories.TipoIngressoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TipoIngressoServices {

    @Autowired
    private TipoIngressoRepository tipoIngressoRepository;

    public List<TipoIngressoDTO> pegarTodos() {
        List<TipoIngresso> tipoIngressoList = tipoIngressoRepository.findAll();
        return tipoIngressoList.stream().map(TipoIngressoDTO::new).toList();
    }

    public TipoIngressoDTO bucarPorId(Long id) {
        TipoIngresso tipoIngresso = tipoIngressoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Nenhum dado encontrado para o Id " + id + "!"));
        return new TipoIngressoDTO(tipoIngresso);
    }
}
