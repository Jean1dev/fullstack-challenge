package com.willian.AlpacaFilmes.application.services;

import com.willian.AlpacaFilmes.application.exceptions.ResourceNotFoundException;
import com.willian.AlpacaFilmes.domain.dto.ProgramacaoDTO;
import com.willian.AlpacaFilmes.domain.entities.Programacao;
import com.willian.AlpacaFilmes.infra.repositories.ProgamacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

@Service
public class ProgramacaoServices {

    Logger log = Logger.getLogger(ProgramacaoServices.class.getName());

    @Autowired
    private ProgamacaoRepository progamacaoRepository;

    public List<ProgramacaoDTO> pegarTodos() {
        List<ProgramacaoDTO> programacaoDTOList = progamacaoRepository.
                findTop4ByOrderByIdDesc()
                .stream()
                .map(ProgramacaoDTO::new)
                .toList();

        return programacaoDTOList;
    }

    public ProgramacaoDTO pegarPorId(Long id) {
        Programacao programacao = progamacaoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Nenhum dado encontrado para o Id " + id + "!"));
        return new ProgramacaoDTO(programacao);
    }
}
