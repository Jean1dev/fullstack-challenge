package com.willian.AlpacaFilmes.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.willian.AlpacaFilmes.domain.entities.Horarios;
import com.willian.AlpacaFilmes.domain.entities.Ingresso;
import com.willian.AlpacaFilmes.domain.enums.StatusIngresso;

import java.io.Serializable;
import java.util.List;

@JsonRootName("ingresso")
public class IngressoDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    @JsonProperty("combos")
    private List<ComboDTO> comboList;
    private FilmeDTO filme;
    private SalasDTO salas;
    private Horarios horarios;
    @JsonProperty("tipo_ingresso")
    private TipoIngressoDTO tipoIngresso;
    private CadeirasDTO cadeiras;
    private String nome;
    private String documento;
    private StatusIngresso status;

    public IngressoDTO() {
    }

    public IngressoDTO(Ingresso ingresso) {
        this.id = ingresso.getId();
        this.comboList = ingresso.getComboList().stream().map(ComboDTO::new).toList();
        this.filme = new FilmeDTO(ingresso.getFilme());
        this.salas = new SalasDTO(ingresso.getSalas());
        this.horarios = ingresso.getHorarios();
        this.tipoIngresso = new TipoIngressoDTO(ingresso.getTipoIngresso());
        this.cadeiras = new CadeirasDTO(ingresso.getCadeiras());
        this.nome = ingresso.getNome();
        this.documento = ingresso.getDocumento();
        this.status = ingresso.getStatus();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<ComboDTO> getComboList() {
        return comboList;
    }

    public void setComboList(List<ComboDTO> comboList) {
        this.comboList = comboList;
    }

    public FilmeDTO getFilme() {
        return filme;
    }

    public void setFilme(FilmeDTO filme) {
        this.filme = filme;
    }

    public SalasDTO getSalas() {
        return salas;
    }

    public void setSalas(SalasDTO salas) {
        this.salas = salas;
    }

    public Horarios getHorarios() {
        return horarios;
    }

    public void setHorarios(Horarios horarios) {
        this.horarios = horarios;
    }

    public TipoIngressoDTO getTipoIngresso() {
        return tipoIngresso;
    }

    public void setTipoIngresso(TipoIngressoDTO tipoIngresso) {
        this.tipoIngresso = tipoIngresso;
    }

    public CadeirasDTO getCadeiras() {
        return cadeiras;
    }

    public void setCadeiras(CadeirasDTO cadeiras) {
        this.cadeiras = cadeiras;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public StatusIngresso getStatus() {
        return status;
    }

    public void setStatus(StatusIngresso status) {
        this.status = status;
    }
}
