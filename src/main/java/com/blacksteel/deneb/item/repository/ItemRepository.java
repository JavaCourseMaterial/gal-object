package com.blacksteel.deneb.item.repository;

import com.blacksteel.deneb.item.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long> {

    List<Item> findAllByName(List<String> itemIds);
}
