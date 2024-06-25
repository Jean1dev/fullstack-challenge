package com.willian.AlpacaFilmes.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.willian.AlpacaFilmes.domain.entities.Cadeiras;
import com.willian.AlpacaFilmes.domain.entities.Salas;

import java.io.Serializable;
import java.util.List;

@JsonRootName("salas")
public class SalasDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private int numero;
    @JsonIgnore
    private List<Cadeiras> cadeiras;

    public SalasDTO() {
    }

    public SalasDTO(Long id, int numero, List<Cadeiras> cadeiras) {
        this.id = id;
        this.numero = numero;
        this.cadeiras = cadeiras;
    }

    public SalasDTO(Salas sala) {
        this.id = sala.getId();
        this.numero = sala.getNumero();
        this.cadeiras = sala.getCadeiras();
    }

    public static Salas converter(SalasDTO salasDTO) {
        Salas salas = new Salas();
        salas.setNumero(salasDTO.getNumero());
        salas.setCadeiras(salasDTO.getCadeiras());
        return salas;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public List<Cadeiras> getCadeiras() {
        return cadeiras;
    }

    public void setCadeiras(List<Cadeiras> cadeiras) {
        this.cadeiras = cadeiras;
    }
}
