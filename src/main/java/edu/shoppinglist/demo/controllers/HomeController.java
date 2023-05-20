package edu.shoppinglist.demo.controllers;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.zxing.WriterException;
import edu.shoppinglist.demo.merchandise.Item;
import edu.shoppinglist.demo.merchandise.ShoppingList;
import java.io.IOException;
import java.util.Base64;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(path = "/")
public class HomeController {

    @Autowired
    private ListOperationService service;
    @Autowired
    @Qualifier("ItemService")
    private ItemOperationService itemservice;

    @Value("${spring.application.name}")
    String appName;

    @GetMapping("/")
    public String showHomePage(Model model) {
        model.addAttribute("appName", appName);
        return "home";
    }

    @Value("${spring.application.managelist.subpagename}")
    String managelistName;

    @GetMapping("/listsManager")
    public String showListManager(Model model) {
        List<ShoppingList> listOfLists = service.getAllLists();
        model.addAttribute("listOfLists", listOfLists);
        model.addAttribute("managelistName", managelistName);
        return "listsManager";
    }

    @Value("${spring.application.addlist.subpagename}")
    String addlistName;

    @RequestMapping("/add")
    public String showNewListPage(Model model) {
        ShoppingList list = new ShoppingList();
        model.addAttribute("list", list);
        model.addAttribute("addlistName", addlistName);
        return "add";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveList(@ModelAttribute("list") ShoppingList list) {
        service.saveOrUpdate(list);
        return "redirect:/";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String updateList(@ModelAttribute("list") ShoppingList list) {
        service.update(list, list.getId());
        return "redirect:/";
    }

    @RequestMapping("/edit/{id}")
    public ModelAndView showEditListPage(@PathVariable(name = "id") int id) {
        ModelAndView mav = new ModelAndView("edit_list");
        ShoppingList list = service.getListsById(id);
        mav.addObject("list", list);
        return mav;
    }

    @RequestMapping("/delete/{id}")
    public String deleteList(@PathVariable(name = "id") int id) {
        service.delete(id);
        return "redirect:/";
    }

    @RequestMapping("/showQR/{id}")
    public String showQR4List(@PathVariable(name = "id") int id, Model model) {
        Gson gson = new Gson();
        List<Item> items = itemservice.getAllItemsOnList(id);
        JsonObject jsonRequest = new JsonObject();
        JsonArray jsonArray = new JsonArray();
        for (int i = 0; i < items.size(); i++) {
            jsonRequest.addProperty("item_name", items.get(i).getName());
            jsonRequest.addProperty("item_count", items.get(i).getCount());
            jsonArray.add(jsonRequest);
        }
        String jsonInString = gson.toJson(jsonArray);
        byte[] image = new byte[0];
        try {
            image = QRCodeGenerator.getQRCodeImage(String.valueOf(jsonInString), 250, 250);
        } catch (WriterException | IOException e) {
        }
        String qrcode = Base64.getEncoder().encodeToString(image);
        model.addAttribute("qrcode", qrcode);
        return "qrcode";
    }

    @RequestMapping("/getAllLists")
    public List<ShoppingList> showAllLists() {
        return service.getAllLists();
    }

    @RequestMapping("/createList/{name}")
    public ResponseEntity<String> createListAPI(@PathVariable(name = "name") String name) {
        if (service.create(name)) {
            return ResponseEntity.status(HttpStatus.ACCEPTED).body("OK");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("ERROR");
        }
    }

    @RequestMapping("/deleteList/{id}")
    public ResponseEntity<String> deleteListAPI(@PathVariable(name = "id") int id) {
        service.delete(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("OK");
    }

    @RequestMapping("/edit/additemtolist/add")
    public String additem(@RequestParam("list_id") int id, Model model) {
        model.addAttribute("list_id", id);
        Item item = new Item();
        model.addAttribute("item", item);
        return "additem";
    }

    @RequestMapping(value = "/saveItem", method = RequestMethod.POST)
    public String saveItem(@ModelAttribute("item") Item item) {
        itemservice.save(item);
        return "redirect:/";
    }

    @RequestMapping("/getAllItemsOnListById/{id}")
    public String showAllItemsOnListById(@PathVariable(name = "id") int id, Model model) {
        model.addAttribute("itemsOnList", itemservice.getAllItemsOnList(id));
        return "showitems";
    }

    @GetMapping(path = "/getAllItemsOnListByIdJSON/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> get(@PathVariable(name = "id") int id) {
        Gson gson = new Gson();
        List<Item> items = itemservice.getAllItemsOnList(id);
        JsonObject jsonRequest = new JsonObject();
        JsonArray jsonArray = new JsonArray();
        for (int i = 0; i < items.size(); i++) {
            jsonRequest.addProperty("item_name", items.get(i).getName());
            jsonRequest.addProperty("item_count", items.get(i).getCount());
            jsonArray.add(jsonRequest);
        }
        String jsonInString = gson.toJson(jsonArray);
        return ResponseEntity.ok(jsonInString);
    }
}
