package com.blacksteel.deneb.item.controller;

import com.blacksteel.deneb.item.model.Item;
import com.blacksteel.deneb.item.service.ItemService;
import org.hamcrest.collection.IsIterableContainingInOrder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class ItemControllerTest {

    ItemService itemServiceMock;
    HttpServletResponse responseMock;

    @Before
    public void setUp() throws Exception {
        this.itemServiceMock = Mockito.mock(ItemService.class);
        this.responseMock = Mockito.mock(HttpServletResponse.class);
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

        when(itemServiceMock.getAllItems()).thenReturn(expected);
        ItemController controller = new ItemController(this.itemServiceMock);

        // exercise
        List<Item> actual = controller.getAllItems(responseMock);

        // assert
        verify(itemServiceMock, times(1)).getAllItems();
        verify(responseMock, times(1)).setStatus(HttpServletResponse.SC_OK);
        assertThat(actual, IsIterableContainingInOrder.contains(expected.toArray()));

    }

    @Test
    public void getItemsByName() {

        // setup
        List<Item> expected = new ArrayList<>();
        Item item = Item.builder().name("test name").build();
        expected.add(item);

        when(itemServiceMock.getAllItemsByName(item)).thenReturn(expected);
        ItemController controller = new ItemController(this.itemServiceMock);

        // exercise
        List<Item> actual = controller.getItemsByName(responseMock, "test name");

        // assert
        verify(itemServiceMock, times(1)).getAllItemsByName(item);
        verify(responseMock, times(1)).setStatus(HttpServletResponse.SC_OK);
        assertThat(actual, IsIterableContainingInOrder.contains(expected.toArray()));

    }

    @Test
    public void getItemPresent() {

        // setup
        Item expected = Item.builder().id(1L).name("test name").build();
        when(itemServiceMock.existsById(expected)).thenReturn(true);
        when(itemServiceMock.getItemById(expected)).thenReturn(expected);
        ItemController controller = new ItemController(this.itemServiceMock);

        // exercise
        Item actual = controller.getItem(responseMock, 1L, "test name");

        // assert
        verify(itemServiceMock, times(1)).getItemById(expected);
        verify(responseMock, times(1)).setStatus(HttpServletResponse.SC_OK);
        assertEquals(expected, actual);

    }

    @Test
    public void getItemNotPresent() {

        // setup
        Item expected = Item.builder().id(1L).name("test name").build();
        when(itemServiceMock.existsById(expected)).thenReturn(false);
        ItemController controller = new ItemController(this.itemServiceMock);

        // exercise
        Item actual = controller.getItem(responseMock, 1L, "test name");

        // assert
        verify(responseMock, times(1)).setStatus(HttpServletResponse.SC_NOT_FOUND);
        assertNull(actual);

    }

    @Test
    public void createItem() {

        // setup
        Item expected = Item.builder().name("test name").build();
        when(itemServiceMock.createItem(expected)).thenReturn(expected);
        ItemController controller = new ItemController(this.itemServiceMock);

        // exercise
        controller.createItem(responseMock, "test name");

        // assert
        verify(itemServiceMock, times(1)).createItem(expected);
        verify(responseMock, times(1)).setStatus(HttpServletResponse.SC_OK);
    }

    @Test
    public void updateItem() {

        // setup
        Item expected = Item.builder().id(1L).name("test name").build();
        when(itemServiceMock.createItem(expected)).thenReturn(expected);
        ItemController controller = new ItemController(this.itemServiceMock);

        // exercise
        controller.updateItem(responseMock, 1L, "test name");

        // assert
        verify(itemServiceMock, times(1)).createItem(expected);
        verify(responseMock, times(1)).setStatus(HttpServletResponse.SC_OK);
    }

    @Test
    public void deleteItemPresent() {

        // setup
        Item expected = Item.builder().id(1L).name("test name").build();
        when(itemServiceMock.existsById(expected)).thenReturn(true);
        ItemController controller = new ItemController(this.itemServiceMock);

        // exercise
        controller.deleteItem(responseMock, 1L, "test name");

        // assert
        verify(itemServiceMock, times(1)).deleteItem(expected);
        verify(responseMock, times(1)).setStatus(HttpServletResponse.SC_OK);

    }

    @Test
    public void deleteItemNotPresent() {

        // setup
        Item expected = Item.builder().id(1L).name("test name").build();
        when(itemServiceMock.existsById(expected)).thenReturn(false);
        ItemController controller = new ItemController(this.itemServiceMock);

        // exercise
        controller.deleteItem(responseMock, 1L, "test name");

        // assert
        verify(responseMock, times(1)).setStatus(HttpServletResponse.SC_NOT_FOUND);
    }
}