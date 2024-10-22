package com.willian.AlpacaFilmes.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.willian.AlpacaFilmes.domain.entities.Horarios;
import com.willian.AlpacaFilmes.domain.entities.Programacao;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@JsonRootName("programacao")
public class ProgramacaoDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;

    private FilmeDTO filme;

    private SalasDTO sala;

    private List<Horarios> horarios;

    @JsonIgnore
    private Date dataCadastro;

    public ProgramacaoDTO() {
    }

    public ProgramacaoDTO(Long id, FilmeDTO filme, SalasDTO sala, List<Horarios> horarios, Date dataCadastro) {
        this.id = id;
        this.filme = filme;
        this.sala = sala;
        this.horarios = horarios;
        this.dataCadastro = dataCadastro;
    }

    public ProgramacaoDTO(Programacao programacao) {
        this.id = programacao.getId();
        this.filme = new FilmeDTO(programacao.getFilme());
        this.sala = new SalasDTO(programacao.getSala());
        this.horarios = programacao.getHorarios();
        this.dataCadastro = programacao.getDataCadastro();
    }

    public static Programacao converter(ProgramacaoDTO programacaoDTO) {
        Programacao programacao = new Programacao();
        programacao.setId(programacaoDTO.getId());
        programacao.setFilme(FilmeDTO.converter(programacaoDTO.getFilme()));
        programacao.setSala(SalasDTO.converter(programacaoDTO.getSala()));
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

    public FilmeDTO getFilme() {
        return filme;
    }

    public void setFilme(FilmeDTO filme) {
        this.filme = filme;
    }

    public SalasDTO getSala() {
        return sala;
    }

    public void setSala(SalasDTO sala) {
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
