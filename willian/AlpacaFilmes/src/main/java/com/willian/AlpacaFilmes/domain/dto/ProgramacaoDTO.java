package com.willian.AlpacaFilmes.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.willian.AlpacaFilmes.domain.entities.Filme;
import com.willian.AlpacaFilmes.domain.entities.Horarios;
import com.willian.AlpacaFilmes.domain.entities.Programacao;
import com.willian.AlpacaFilmes.domain.entities.Salas;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@JsonRootName("programacao")
public class ProgramacaoDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;

    private Filme filme;

    private Salas sala;

    private List<Horarios> horarios;

    @JsonIgnore
    private Date dataCadastro;

    public ProgramacaoDTO() {
    }

    public ProgramacaoDTO(Long id, Filme filme, Salas sala, List<Horarios> horarios, Date dataCadastro) {
        this.id = id;
        this.filme = filme;
        this.sala = sala;
        this.horarios = horarios;
        this.dataCadastro = dataCadastro;
    }

    public ProgramacaoDTO(Programacao programacao) {
        this.id = programacao.getId();
        this.filme = programacao.getFilme();
        this.sala = programacao.getSala();
        this.horarios = programacao.getHorarios();
        this.dataCadastro = programacao.getDataCadastro();
    }

    public static Programacao converter(ProgramacaoDTO programacaoDTO) {
        Programacao programacao = new Programacao();
        programacao.setId(programacaoDTO.getId());
        programacao.setFilme(programacaoDTO.getFilme());
        programacao.setSala(programacaoDTO.getSala());
        programacao.setHorarios(programacaoDTO.getHorarios());
        programacao.setDataCadastro(programacaoDTO.getDataCadastro());
        return programacao;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Filme getFilme() {
        return filme;
    }

    public void setFilme(Filme filme) {
        this.filme = filme;
    }

    public Salas getSala() {
        return sala;
    }

    public void setSala(Salas sala) {
        this.sala = sala;
    }

    public List<Horarios> getHorarios() {
        return horarios;
    }

    public void setHorarios(List<Horarios> horarios) {
        this.horarios = horarios;
    }

    public Date getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(Date dataCadastro) {
        this.dataCadastro = dataCadastro;
    }
}
