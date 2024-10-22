package com.willian.AlpacaFilmes.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.willian.AlpacaFilmes.domain.entities.Cadeiras;
import com.willian.AlpacaFilmes.domain.enums.CadeiraStatus;

import java.io.Serializable;

@JsonRootName("cadeiras")
public class CadeirasDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private int numero;
    @JsonProperty("status")
    private CadeiraStatus status;

    public CadeirasDTO() {
    }

    public CadeirasDTO(Long id, int numero, CadeiraStatus status) {
        this.id = id;
        this.numero = numero;
        this.status = status;
    }

    public CadeirasDTO(Cadeiras cadeiras) {
        this.id = cadeiras.getId();
        this.numero = cadeiras.getNumero();
        this.status = cadeiras.isStatus();
    }

    public static Cadeiras converter (CadeirasDTO cadeirasDTO) {
        Cadeiras cadeiras = new Cadeiras();
        cadeiras.setNumero(cadeirasDTO.getNumero());
        cadeiras.setStatus(cadeirasDTO.isStatus());
        return cadeiras;
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


    public CadeiraStatus isStatus() {
        return status;
    }

    public void setStatus(CadeiraStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "CadeirasDTO{" +
                "id=" + id +
                ", numero=" + numero +
                ", status=" + status +
                '}';
    }
}
