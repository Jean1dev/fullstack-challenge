package com.willian.AlpacaFilmes.domain.entities;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "salas")
public class Salas implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private int numero;

    @OneToMany(mappedBy = "sala", cascade = CascadeType.DETACH)
    private List<Cadeiras> cadeiras;


    public Salas() {
    }

    public Salas(Long id, int numero, List<Cadeiras> cadeiras) {
        this.id = id;
        this.numero = numero;
        this.cadeiras = cadeiras;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Salas salas = (Salas) o;
        return numero == salas.numero && Objects.equals(id, salas.id) && Objects.equals(cadeiras, salas.cadeiras);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, numero, cadeiras);
    }
}
