package com.willian.AlpacaFilmes.domain.dto;

import com.fasterxml.jackson.annotation.JsonRootName;
import com.willian.AlpacaFilmes.domain.entities.Item;

import java.io.Serializable;

@JsonRootName("lista_items")
public class ItemComboDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long id;

    private String nome;

    public ItemComboDTO() {
    }

    public ItemComboDTO(Long id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public ItemComboDTO(Item item) {
        this.id = item.getId();
        this.nome = item.getNome();
    }

    public static Item converter(ItemComboDTO itemComboDTO) {
        Item item = new Item();
        item.setNome(itemComboDTO.getNome());
        item.setId(itemComboDTO.getId());
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
}
