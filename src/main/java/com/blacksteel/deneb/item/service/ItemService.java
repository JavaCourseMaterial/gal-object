package com.blacksteel.deneb.item.service;

import com.blacksteel.deneb.item.model.Item;
import com.blacksteel.deneb.item.repository.ItemRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ItemService {

    private final ItemRepository itemRepository;

    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public List<Item> getAllItems() {
        return itemRepository.findAll();
    }

    public Item getItemById(Item sourceItem) {
        return itemRepository.findById(sourceItem.getId()).get();
    }

    public Item createItem(Item sourceItem) {
        return itemRepository.save(sourceItem);
    }

    public void deleteItem(Item sourceItem) {
        itemRepository.delete(sourceItem);
    }

    public boolean existsById(Item item) {
        return itemRepository.existsById(item.getId());
    }

    public List<Item> getAllItemsByName(Item sourceItem) {
        List<String> itemIds = new ArrayList<>();
        itemIds.add(sourceItem.getName());
        return itemRepository.findAllByName(itemIds);
    }
}
