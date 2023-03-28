package edu.shoppinglist.demo.controllers;

import com.google.zxing.WriterException;
import edu.shoppinglist.demo.merchandise.ShoppingList;
import java.io.IOException;
import java.util.Base64;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(path = "/")
public class HomeController {

    @Autowired
    private ListOperationService service;

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
    public String saveProduct(@ModelAttribute("list") ShoppingList list) {
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
        byte[] image = new byte[0];
        try {
            image = QRCodeGenerator.getQRCodeImage(String.valueOf(id), 250, 250);
        } catch (WriterException | IOException e) {
        }
        String qrcode = Base64.getEncoder().encodeToString(image);
        model.addAttribute("qrcode", qrcode);
        return "qrcode";
    }
}
