package com.willian.AlpacaFilmes.domain.entities;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "tipo_ingresso")
public class TipoIngresso implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "tipo_ingresso", length = 10)
    private String tipoIngresso;

    @Column
    private double preco;

    public TipoIngresso() {
    }

    public TipoIngresso(Long id, String tipoIngresso, double preco) {
        this.id = id;
        this.tipoIngresso = tipoIngresso;
        this.preco = preco;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TipoIngresso that = (TipoIngresso) o;
        return Double.compare(preco, that.preco) == 0 && Objects.equals(id, that.id) && Objects.equals(tipoIngresso, that.tipoIngresso);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, tipoIngresso, preco);
    }
}
