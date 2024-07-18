package com.willian.AlpacaFilmes.domain.entities;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "item")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(nullable = false, length = 50)
    private String nome;

    @Column(nullable = false)
    private Double preco;

    public Item() {
    }

    public Item(Long id, String nome, Double preco) {
        Id = id;
        this.nome = nome;
        this.preco = preco;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return Objects.equals(Id, item.Id) && Objects.equals(nome, item.nome) && Objects.equals(preco, item.preco);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Id, nome, preco);
    }
}
