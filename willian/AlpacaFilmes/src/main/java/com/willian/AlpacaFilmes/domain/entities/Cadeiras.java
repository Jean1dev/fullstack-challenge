package com.willian.AlpacaFilmes.domain.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.willian.AlpacaFilmes.domain.enums.CadeiraStatus;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "cadeiras")
public class Cadeiras implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private int numero;

    @Column
    @Enumerated(value = EnumType.STRING)
    private CadeiraStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_sala")
    @JsonBackReference
    private Salas sala;

    public Cadeiras() {
    }

    public Cadeiras(Long id, int numero, CadeiraStatus status) {
        this.id = id;
        this.numero = numero;
        this.status = status;
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

    public Salas getSala() {
        return sala;
    }

    public void setSala(Salas sala) {
        this.sala = sala;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cadeiras cadeiras = (Cadeiras) o;
        return numero == cadeiras.numero && status == cadeiras.status && Objects.equals(id, cadeiras.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, numero, status);
    }
}
