package edu.shoppinglist.demo.repositories;

import edu.shoppinglist.demo.merchandise.ShoppingList;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface ListRepositoryName extends CrudRepository<ShoppingList, String> {
    ShoppingList findByName(@Param("name") String name);
}
