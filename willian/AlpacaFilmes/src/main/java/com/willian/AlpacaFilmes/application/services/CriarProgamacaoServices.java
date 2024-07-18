package com.willian.AlpacaFilmes.application.services;

import com.willian.AlpacaFilmes.domain.dto.SalasDTO;
import com.willian.AlpacaFilmes.domain.entities.Filme;
import com.willian.AlpacaFilmes.domain.entities.Horarios;
import com.willian.AlpacaFilmes.domain.entities.Programacao;
import com.willian.AlpacaFilmes.domain.entities.Salas;
import com.willian.AlpacaFilmes.infra.repositories.ProgamacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

@Service
public class CriarProgamacaoServices {
    private static final Logger log = Logger.getLogger(CriarProgamacaoServices.class.getName());

    @Autowired
    private ProgamacaoRepository progamacaoRepository;

    @Autowired
    private SalasServices salasServices;

    @Autowired
    private HorariosServices horariosServices;

    public void criarProgramacao(Filme filme) {
        Programacao programacao = new Programacao();

        programacao.setFilme(filme);
        programacao.setDataCadastro(new Date());
        programacao.setSala(pegarSala());
        programacao.setHorarios(pegarHorarios());

        salvar(programacao);
    }

    @Transactional
    private void salvar(Programacao programacao) {
        progamacaoRepository.save(programacao);
    }

    protected Salas pegarSala() {
        List<Salas> salasList = salasServices
                .pegarTodos()
                .stream()
                .map(SalasDTO::converter)
                .toList();

        Salas salaLivre = new Salas();

        for (Salas sala : salasList) {
            boolean estaLivre = validaSalaLivre(sala);
            if (estaLivre) {
                salaLivre = sala;
                break;
            }
        }

        if (salaLivre.getId() == null) {
            salaLivre = liberarSala();
        }

        return salaLivre;
    }

    protected boolean validaSalaLivre(Salas sala) {
        List<Programacao> programacaoList = progamacaoRepository.findTop4ByOrderByIdDesc();

        for (Programacao programacao : programacaoList) {
            if (sala == programacao.getSala()) {
                return false;
            }
        }

        return true;
    }

    protected Salas liberarSala() {
        List<Programacao> programacaoList = progamacaoRepository.findTop4ByOrderByIdDesc();

        return programacaoList.getLast().getSala();
    }

    protected List<Horarios> pegarHorarios() {
        return horariosServices.pegarTodos();
    }
}
