package com.blacksteel.deneb.item.model;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ItemTest {

    private Item testItem;

    @Before
    public void setUp() throws Exception {

        this.testItem = Item.builder().id(1L).name("test").build();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getId() {
        Long expected = 1L;
        assertEquals(expected, this.testItem.getId());
    }

    @Test
    public void getName() {
        String expected = "test";
        assertEquals(expected, this.testItem.getName());
    }

    @Test
    public void testEquals() {

        Item item1 = Item.builder().id(1L).name("test1").build();
        Item item2 = Item.builder().id(1L).name("test1").build();

        boolean value = item1.equals(item2);
        assertTrue(value);
    }

    @Test
    public void testCanEqual() {

        Item item1 = Item.builder().id(1L).name("test1").build();
        Item item2 = Item.builder().id(1L).name("test1").build();

        boolean value = item1.canEqual(item2);
        assertTrue(value);
    }

    @Test
    public void testHashCode() {

        Item item1 = Item.builder().id(1L).name("test1").build();
        int hash = item1.hashCode();

        assertEquals(item1.hashCode(), hash);
    }

    @Test
    public void testToString() {

        Item item1 = Item.builder().id(1L).name("test1").build();
        String testString = item1.toString();

        assertEquals(item1.toString(), testString);
    }

}