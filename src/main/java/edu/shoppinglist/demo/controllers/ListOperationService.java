package edu.shoppinglist.demo.controllers;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import edu.shoppinglist.demo.merchandise.ShoppingList;
import edu.shoppinglist.demo.repositories.ListsRepository;
import java.util.Date;

@Service
public class ListOperationService {

    @Autowired
    ListsRepository repo;

    public List<ShoppingList> getAllLists() {
        List<ShoppingList> lists = new ArrayList<>();
        repo.findAll().forEach(lists1 -> lists.add(lists1));
        return lists;
    }

    public ShoppingList getListsById(int id) {
        return repo.findById(id).get();
    }

    public void saveOrUpdate(ShoppingList list) {
        list.setCreationDate(new Date());
        list.setModifyDate(new Date());
        repo.save(list);
    }

    public void delete(int id) {
        repo.deleteById(id);
    }

    public void update(ShoppingList list, int id) {
        list.setCreationDate(getListsById(id).getCreationDate());
        list.setModifyDate(new Date());
        repo.save(list);
    }
}