package com.willian.AlpacaFilmes.application.services;

import com.willian.AlpacaFilmes.application.exceptions.ResourceNotFoundException;
import com.willian.AlpacaFilmes.domain.dto.ItemDTO;
import com.willian.AlpacaFilmes.domain.entities.Item;
import com.willian.AlpacaFilmes.infra.repositories.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService {
    @Autowired
    private ItemRepository itemRepository;

    public List<ItemDTO> pegarTodos() {
        List<Item> itemList = itemRepository.findAll();
        return itemList.stream().map(ItemDTO::new).toList();
    }

    public ItemDTO buscarPorId(Long id) {
        Item item = itemRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Nenhum dado encontrado para o Id " + id + "!"));
        return new ItemDTO(item);
    }
}
