package com.blacksteel.deneb.item.service;

import com.blacksteel.deneb.item.model.Item;
import com.blacksteel.deneb.item.repository.ItemRepository;
import org.hamcrest.collection.IsIterableContainingInOrder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class ItemServiceTest {

    ItemRepository itemRepositoryMock;

    @Before
    public void setUp() throws Exception {

        this.itemRepositoryMock = Mockito.mock(ItemRepository.class);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getAllItems() {

        // setup
        List<Item> expected = new ArrayList<>();
        Item item = Item.builder().id(1L).name("test name").build();
        expected.add(item);

        ItemService itemService = new ItemService(this.itemRepositoryMock);
        when(itemRepositoryMock.findAll()).thenReturn(expected);

        // exercise
        List<Item> actual = itemService.getAllItems();

        // assert
        assertThat(expected, IsIterableContainingInOrder.contains(actual.toArray()));

    }

    @Test
    public void getItemById() {

        // setup
        Item expected = Item.builder().id(1L).name("test name").build();
        Optional<Item> optional = Optional.of(expected);

        ItemService itemService = new ItemService(this.itemRepositoryMock);
        when(itemRepositoryMock.findById(expected.getId())).thenReturn(optional);

        // exercise
        Item actual = itemService.getItemById(expected);

        // assert
        assertEquals(expected, actual);
    }

    @Test
    public void createItem() {

        // setup
        Item expected = Item.builder().id(1L).name("test name").build();
        ItemService itemService = new ItemService(this.itemRepositoryMock);
        when(itemRepositoryMock.save(expected)).thenReturn(expected);

        // exercise
        Item actual = itemService.createItem(expected);

        // assert
        assertEquals(expected, actual);
    }

    @Test
    public void deleteItem() {

        // setup
        Item expected = Item.builder().id(1L).name("test name").build();
        ItemService itemService = new ItemService(this.itemRepositoryMock);

        // exercise
        itemService.deleteItem(expected);

        // assert
        verify(itemRepositoryMock, times(1)).delete(expected);

    }

    @Test
    public void existsByIdTrue() {

        // setup
        Item dummyData = Item.builder().id(1L).name("test name").build();
        ItemService itemService = new ItemService(this.itemRepositoryMock);
        when(itemRepositoryMock.existsById(dummyData.getId())).thenReturn(true);
        // exercise
        boolean actual = itemService.existsById(dummyData);

        // assert
        verify(itemRepositoryMock, times(1)).existsById(dummyData.getId());
        assertTrue(actual);
    }

    @Test
    public void existsByIdFalse() {

        // setup
        Item dummyData = Item.builder().id(1L).name("test name").build();
        ItemService itemService = new ItemService(this.itemRepositoryMock);
        when(itemRepositoryMock.existsById(dummyData.getId())).thenReturn(false);
        // exercise
        boolean actual = itemService.existsById(dummyData);

        // assert
        verify(itemRepositoryMock, times(1)).existsById(dummyData.getId());
        assertFalse(actual);
    }

    @Test
    public void getAllItemsByName() {

        // setup
        List<Item> expected = new ArrayList<>();
        Item item1 = Item.builder().id(1L).name("test name").build();
        Item item2 = Item.builder().id(2L).name("test name").build();
        expected.add(item1);
        expected.add(item2);

        List<String> itemIds = new ArrayList<>();
        itemIds.add("test name");

        ItemService itemService = new ItemService(this.itemRepositoryMock);
        when(itemRepositoryMock.findAllByName(itemIds)).thenReturn(expected);

        // exercise
        List<Item> actual = itemService.getAllItemsByName(item1);

        // assert
        assertThat(expected, IsIterableContainingInOrder.contains(actual.toArray()));

    }
}