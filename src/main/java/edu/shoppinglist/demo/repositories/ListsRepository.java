package edu.shoppinglist.demo.repositories;

import edu.shoppinglist.demo.merchandise.List;
import org.springframework.data.repository.CrudRepository;

public interface ListsRepository extends CrudRepository<List, Integer> {

}