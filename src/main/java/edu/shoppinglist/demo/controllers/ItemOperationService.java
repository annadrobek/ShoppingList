package edu.shoppinglist.demo.controllers;

import edu.shoppinglist.demo.merchandise.Item;
import edu.shoppinglist.demo.merchandise.ShoppingList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import edu.shoppinglist.demo.repositories.ItemRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Qualifier;

@Service
@Qualifier("ItemService")
public class ItemOperationService {

    @Autowired
    ItemRepository itemrepo;

    public void save(Item item) {
        itemrepo.save(item);
    }

    public void delete(int id) {
        itemrepo.deleteById(id);
    }

    public List<Item> getAllItemsOnList(int list_id) {
        List<Item> lists = new ArrayList<>();
        List<Item> filteredlist = new ArrayList<>();
        itemrepo.findAll().forEach(lists::add);
        for (int i = 0; i < lists.size(); i++) {
            if (lists.get(i).getList_id() == list_id) {
                filteredlist.add(lists.get(i));
            }
        }
        return filteredlist;
    }
    /*public void update(ShoppingList list, int id) {
        list.setCreationDate(getListsById(id).getCreationDate());
        list.setModifyDate(new Date());
        repo.save(list);
    }*/
}
