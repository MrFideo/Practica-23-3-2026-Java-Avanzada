package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.model.Item;
import com.example.demo.respository.ItemRepository;

@Controller
public class ItemController {

    @Autowired
    private ItemRepository repository;

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("items", repository.findAll());
        model.addAttribute("item", new Item());
        return "index";
    }

    @PostMapping("/agregar")
    public String agregar(@ModelAttribute Item item) {
        repository.save(item);
        return "redirect:/";
    }

}
