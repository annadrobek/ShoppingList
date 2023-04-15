package edu.shoppinglist.demo.merchandise;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ItemTest {

    @Test//Test sprawdzający, czy po ustawieniu wartości licznika, metoda getCount() zwraca oczekiwany wynik
    public void testGetCount() {
        Item item = new Item();
        item.setCount(5);
        assertEquals(5, item.getCount());
    }

    @Test //Test sprawdzający, czy po ustawieniu wartości nazwy, metoda getName() zwraca oczekiwany wynik
    public void testGetName() {
        Item item = new Item();
        item.setName("Chleb");
        assertEquals("Chleb", item.getName());
    }

    @Test //Test sprawdzający, czy po ustawieniu wartości ID, metoda getId() zwraca oczekiwany wynik
    public void testGetId() {
        Item item = new Item();
        item.setId(12);
        assertEquals(12, item.getId());
    }

    @Test//Test sprawdzający, czy po ustawieniu wartości licznika, metoda setCount() ustawia wartość poprawni
    public void testSetCount() {
        Item item = new Item();
        item.setCount(7);
        assertEquals(7, item.getCount());
    }

    @Test//Test sprawdzający, czy po ustawieniu wartości nazwy, metoda setName() ustawia wartość poprawnie
    public void testSetName() {
        Item item = new Item();
        item.setName("Mleko");
        assertEquals("Mleko", item.getName());
    }
    @Test//Test sprawdzający, czy po ustawieniu wartości ID, metoda setId() ustawia wartość poprawnie
    public void testSetId() {
        Item item = new Item();
        item.setId(82);
        assertEquals(82, item.getId());
    }
}