package com.willian.AlpacaFilmes.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.willian.AlpacaFilmes.domain.entities.TipoIngresso;

import java.io.Serializable;

@JsonRootName("tipo_ingresso")
public class TipoIngressoDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;

    @JsonProperty("tipo_ingresso")
    private String tipoIngresso;

    private double preco;

    public TipoIngressoDTO() {
    }

    public TipoIngressoDTO(Long id, String tipoIngresso, double preco) {
        this.id = id;
        this.tipoIngresso = tipoIngresso;
        this.preco = preco;
    }

    public TipoIngressoDTO(TipoIngresso tipoIngresso) {
        this.id = tipoIngresso.getId();
        this.tipoIngresso = tipoIngresso.getTipoIngresso();
        this.preco = tipoIngresso.getPreco();
    }

    public static TipoIngresso converter(TipoIngressoDTO tipoIngressoDTO) {
        TipoIngresso tipoIngresso = new TipoIngresso();
        tipoIngresso.setId(tipoIngressoDTO.getId());
        tipoIngresso.setTipoIngresso(tipoIngressoDTO.getTipoIngresso());
        tipoIngresso.setPreco(tipoIngressoDTO.getPreco());
        return tipoIngresso;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTipoIngresso() {
        return tipoIngresso;
    }

    public void setTipoIngresso(String tipoIngresso) {
        this.tipoIngresso = tipoIngresso;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }
}
