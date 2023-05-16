package edu.shoppinglist.demo.repositories;

import edu.shoppinglist.demo.merchandise.Item;
import org.springframework.data.repository.CrudRepository;

public interface ItemRepository extends CrudRepository<Item, Integer> {
}