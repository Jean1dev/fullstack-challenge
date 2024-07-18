package com.willian.AlpacaFilmes.domain.dto;

import com.fasterxml.jackson.annotation.JsonRootName;
import com.willian.AlpacaFilmes.domain.entities.Item;

import java.io.Serializable;

@JsonRootName("item")
public class ItemDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long id;

    private String nome;

    private Double preco;

    public ItemDTO() {
    }

    public ItemDTO(Long id, String nome, Double preco) {
        this.id = id;
        this.nome = nome;
        this.preco = preco;
    }

    public ItemDTO(Item item) {
        id = item.getId();
        this.nome = item.getNome();
        this.preco = item.getPreco();
    }

    public static Item converter(ItemDTO itemDTO) {
        Item item = new Item();
        item.setId(itemDTO.getId());
        item.setNome(itemDTO.getNome());
        item.setPreco(itemDTO.getPreco());
        return item;
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

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }
}
