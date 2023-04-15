package edu.shoppinglist.demo.merchandise;

import org.junit.jupiter.api.Test;
import java.util.Date;
import static org.junit.jupiter.api.Assertions.*;

class ShoppingListTest {

    @Test//Test sprawdzający, czy po ustawieniu wartości nazwy, metoda getName() zwraca oczekiwany wynik
    public void testGetName() {
        ShoppingList list = new ShoppingList();
        list.setName("Lista zakupów");
        assertEquals("Lista zakupów", list.getName());
    }

    @Test//Test sprawdzający, czy po ustawieniu wartości daty utworzenia, metoda getCreationDate() zwraca oczekiwany wynik
    public void testGetCreationDate() {
        Date date = new Date();
        ShoppingList list = new ShoppingList();
        list.setCreationDate(date);
        assertEquals(date, list.getCreationDate());
    }

    @Test//Test sprawdzający, czy po ustawieniu wartości daty modyfikacji, metoda getModifyDate() zwraca oczekiwany wynik
    public void testGetModifyDate() {
        Date date = new Date();
        ShoppingList list = new ShoppingList();
        list.setModifyDate(date);
        assertEquals(date, list.getModifyDate());
    }

}