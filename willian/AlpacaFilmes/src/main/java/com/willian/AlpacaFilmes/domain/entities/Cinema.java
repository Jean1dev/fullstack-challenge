package com.willian.AlpacaFilmes.domain.entities;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "cinema")
public class Cinema {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String nome;

    public Cinema() {
    }

    public Cinema(Long id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cinema cinema = (Cinema) o;
        return Objects.equals(id, cinema.id) && Objects.equals(nome, cinema.nome);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome);
    }
}
