package edu.shoppinglist.demo.repositories;

import edu.shoppinglist.demo.merchandise.ShoppingList;
import org.springframework.data.repository.CrudRepository;

public interface ListsRepository extends CrudRepository<ShoppingList, Integer> {

}