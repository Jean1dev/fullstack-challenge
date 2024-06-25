package com.willian.AlpacaFilmes.domain.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.willian.AlpacaFilmes.domain.entities.Cadeiras;
import com.willian.AlpacaFilmes.domain.entities.Salas;

import java.io.Serializable;

@JsonRootName("cadeiras")
@JsonPropertyOrder({
        "id", "numero", "status", "id_sala"
})
public class CadeirasDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private int numero;
    private boolean status;
    private Salas sala;

    public CadeirasDTO() {
    }

    public CadeirasDTO(Long id, int numero, boolean status, Salas sala) {
        this.id = id;
        this.numero = numero;
        this.status = status;
        this.sala = sala;
    }

    public CadeirasDTO(Cadeiras cadeiras) {
        this.id = cadeiras.getId();
        this.numero = cadeiras.getNumero();
        this.status = cadeiras.isStatus();
        this.sala = cadeiras.getSala();
    }

    public static Cadeiras converter (CadeirasDTO cadeirasDTO) {
        Cadeiras cadeiras = new Cadeiras();
        cadeiras.setSala(cadeirasDTO.getSala());
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

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Salas getSala() {
        return sala;
    }

    public void setSala(Salas sala) {
        this.sala = sala;
    }
}
