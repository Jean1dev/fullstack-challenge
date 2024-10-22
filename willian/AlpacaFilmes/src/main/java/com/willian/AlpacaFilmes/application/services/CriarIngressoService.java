package com.willian.AlpacaFilmes.application.services;

import com.willian.AlpacaFilmes.application.exceptions.ResourceNotFoundException;
import com.willian.AlpacaFilmes.domain.dto.*;
import com.willian.AlpacaFilmes.domain.entities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;

@Service
public class CriarIngressoService {

    Logger logger = Logger.getLogger(CriarIngressoService.class.getName());

    @Autowired
    private ComboServices comboServices;

    @Autowired
    private HorariosServices horariosServices;

    @Autowired
    private ProgramacaoServices programacaoServices;

    @Autowired
    private TipoIngressoServices tipoIngressoServices;

    @Autowired
    private FilmeServices filmeServices;

    @Autowired
    private SalasServices salasServices;

    public List<Combo> pegarCombos(List<Long> combosIds) {
        List<ComboDTO> comboList = combosIds.stream().map(comboServices::buscarPorId).toList();
        return comboList.stream().map(ComboDTO::converter).toList();
    }

    public Horarios pegarHorarios(Long id) {
        return this.horariosServices.pegarPorId(id);
    }

    public Filme pegarFilme(Long programacaoId) {
        Programacao programacao = pegarProgramacao(programacaoId);
        FilmeDTO filmeDTO = filmeServices.buscarPorId(programacao.getFilme().getId());
        return FilmeDTO.converter(filmeDTO);
    }

    public Programacao pegarProgramacao(Long programacaoId) {
        ProgramacaoDTO programacaoDTO = programacaoServices.pegarPorId(programacaoId);
        return ProgramacaoDTO.converter(programacaoDTO);
    }

    public Cadeiras pegarCadeira(Long programacaoId, Long cadeiraId) {
        Programacao programacao = pegarProgramacao(programacaoId);
        Salas salas = pegarSala(programacao.getId());
        return salas.getCadeiras().stream()
                .filter(c -> Objects.equals(c.getId(), cadeiraId))
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("Cadeira não encontrada"));
    }

    public Salas pegarSala(Long programacaoId) {
        Programacao programacao = pegarProgramacao(programacaoId);
        SalasDTO salasDTO = salasServices.pegarPorId(programacao.getSala().getId());
        return SalasDTO.converter(salasDTO);
    }

    public TipoIngresso pegarTipoIngresso(Long tipoIngressoId) {
        TipoIngressoDTO tipoIngresso = tipoIngressoServices.buscarPorId(tipoIngressoId);
        return TipoIngressoDTO.converter(tipoIngresso);
    }
}
