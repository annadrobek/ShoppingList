package edu.shoppinglist.demo.controllers;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import edu.shoppinglist.demo.merchandise.ShoppingList;
import edu.shoppinglist.demo.repositories.ListsRepository;
import edu.shoppinglist.demo.repositories.ListRepositoryName;
import java.util.Date;
import java.util.Iterator;

@Service
public class ListOperationService {

    @Autowired
    ListsRepository repo;
    ListRepositoryName reponame;

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

    public boolean create(String name) {
        boolean status = false;
        ShoppingList list = new ShoppingList();
        list.setName(name);
        list.setCreationDate(new Date());
        list.setModifyDate(new Date());
        long items_before = repo.count();
        repo.save(list);
        long items_after = repo.count();
        if (items_after > items_before) {
            status = true;
        }
        return status;
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
