package com.willian.AlpacaFilmes.domain.entities;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "combo")
public class Combo implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50, nullable = false)
    private String nome;

    @OneToMany(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
    @JoinTable(name = "combo_item", joinColumns = {@JoinColumn(name = "combo_id")},
            inverseJoinColumns = {@JoinColumn(name = "item_id")})
    private List<Item> itens;

    @Column(nullable = false)
    private Double preco;

    public Combo() {
    }

    public Combo(Long id, String nome, List<Item> itens, Double preco) {
        this.id = id;
        this.nome = nome;
        this.itens = itens;
        this.preco = preco;
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
        return Objects.equals(id, combo.id) && Objects.equals(nome, combo.nome) && Objects.equals(itens, combo.itens) && Objects.equals(preco, combo.preco);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, itens, preco);
    }
}
