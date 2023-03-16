package edu.shoppinglist.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.Model;

@Controller
@RequestMapping(path="/")
public class HomeController {
    
  @Value("${spring.application.name}")
    String appName;

    @GetMapping("/")
    public String showHomePage(Model model) {
        model.addAttribute("appName", appName);
        return "home";
    }
    @GetMapping("/add")
    public String showAddPage() {
        return "add";
    }
}