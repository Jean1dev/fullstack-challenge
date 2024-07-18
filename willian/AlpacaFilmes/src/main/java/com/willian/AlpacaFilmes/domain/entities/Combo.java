package com.willian.AlpacaFilmes.domain.entities;

import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "combo")
public class Combo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50, nullable = false)
    private String nome;

    @OneToMany(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
    @JoinColumn(name = "combo_id")
    private List<Item> itens;

    @Column(nullable = false)
    private Double preco;

    public Combo() {
    }

    public Combo(Long id, List<Item> itens, Double preco) {
        this.id = id;
        this.itens = itens;
        this.preco = preco;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Item> getItens() {
        return itens;
    }

    public void setItens(List<Item> itens) {
        this.itens = itens;
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
        Combo combo = (Combo) o;
        return Objects.equals(id, combo.id) && Objects.equals(itens, combo.itens) && Objects.equals(preco, combo.preco);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, itens, preco);
    }
}
