package com.willian.AlpacaFilmes.domain.dto;

import com.fasterxml.jackson.annotation.JsonRootName;
import com.willian.AlpacaFilmes.domain.entities.Combo;
import com.willian.AlpacaFilmes.domain.entities.Item;

import java.io.Serializable;
import java.util.List;

@JsonRootName("combo")
public class ComboDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long id;

    private String nome;

    private List<ItemComboDTO> itens;

    private Double preco;

    public ComboDTO() {
    }

    public ComboDTO(Long id, String nome, List<Item> itens, Double preco) {
        this.id = id;
        this.nome = nome;
        setItens(itens);
        this.preco = preco;
    }

    public static Combo converter(ComboDTO comboDTO) {
        Combo combo = new Combo();
        combo.setId(comboDTO.getId());
        combo.setNome(comboDTO.getNome());
        combo.setPreco(comboDTO.getPreco());
        List<Item> itensList = comboDTO.getItens().stream().map(ItemComboDTO::converter).toList();
        combo.setItens(itensList);
        return combo;
    }

    public ComboDTO(Combo combo) {
        this.id = combo.getId();
        this.nome = combo.getNome();
        setItens(combo.getItens());
        this.preco = combo.getPreco();
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

    public List<ItemComboDTO> getItens() {
        return itens;
    }

    public void setItens(List<Item> itens) {
        this.itens = itens.stream().map(ItemComboDTO::new).toList();
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }
}
