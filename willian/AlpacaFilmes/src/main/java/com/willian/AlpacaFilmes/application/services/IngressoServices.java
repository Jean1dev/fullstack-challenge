package com.willian.AlpacaFilmes.application.services;

import com.willian.AlpacaFilmes.domain.dto.CriarIngressoDTO;
import com.willian.AlpacaFilmes.domain.dto.IngressoDTO;
import com.willian.AlpacaFilmes.domain.entities.Ingresso;
import com.willian.AlpacaFilmes.infra.repositories.IngressoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
public class IngressoServices {

    Logger logger = Logger.getLogger(IngressoServices.class.getName());
    @Autowired
    private IngressoRepository ingressoRepository;

    @Autowired
    private CriarIngressoService criarIngressoService;

    public IngressoDTO criar(CriarIngressoDTO criarIngressoDTO) {
        Ingresso ingresso = ingressoRepository.save(criarIngresso(criarIngressoDTO));
        return new IngressoDTO(ingresso);
    }

    private Ingresso criarIngresso(CriarIngressoDTO ingressoDTO) {
        Ingresso ingresso = new Ingresso();

        ingresso.setDocumento(ingressoDTO.getDocumento());
        ingresso.setNome(ingressoDTO.getNome());

        ingresso.setSalas(criarIngressoService.pegarSala(ingressoDTO.getProgramacao()));
        ingresso.setCadeiras(criarIngressoService.pegarCadeira(ingressoDTO.getProgramacao(), ingressoDTO.getCadeira()));
        ingresso.setFilme(criarIngressoService.pegarFilme(ingressoDTO.getProgramacao()));

        if (!ingressoDTO.getComboList().isEmpty()) {
            ingresso.setComboList(criarIngressoService.pegarCombos(ingressoDTO.getComboList()));
        }

        ingresso.setTipoIngresso(criarIngressoService.pegarTipoIngresso(ingressoDTO.getTipoIngresso()));
        ingresso.setHorarios(criarIngressoService.pegarHorarios(ingressoDTO.getHorario()));

        return ingresso;
    }

}
