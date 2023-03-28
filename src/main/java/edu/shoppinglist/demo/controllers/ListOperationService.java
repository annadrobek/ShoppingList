package edu.shoppinglist.demo.controllers;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import edu.shoppinglist.demo.merchandise.ShoppingList;
import edu.shoppinglist.demo.repositories.ListsRepository;
import java.util.Date;
import java.util.Optional;
//defining the business logic  

@Service
public class ListOperationService {

    @Autowired
    ListsRepository repo;
//getting all books record by using the method findaAll() of CrudRepository  

    public List<ShoppingList> getAllLists() {
        List<ShoppingList> lists = new ArrayList<>();
        repo.findAll().forEach(lists1 -> lists.add(lists1));
        return lists;
    }
//getting a specific record by using the method findById() of CrudRepository  

    public ShoppingList getListsById(int id) {
        return repo.findById(id).get();
    }
//saving a specific record by using the method save() of CrudRepository  

    public void saveOrUpdate(ShoppingList list) {
        list.setCreationDate(new Date());
        list.setModifyDate(new Date());
        repo.save(list);
    }
//deleting a specific record by using the method deleteById() of CrudRepository  

    public void delete(int id) {
        repo.deleteById(id);
    }
//updating a record  

    public void update(ShoppingList list, int id) {
        list.setCreationDate(getListsById(id).getCreationDate());
        list.setModifyDate(new Date());
        repo.save(list);
    }
}
