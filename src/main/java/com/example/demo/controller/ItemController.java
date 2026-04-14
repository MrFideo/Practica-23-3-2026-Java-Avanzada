package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable("id") Long id) {
        repository.deleteById(id);
        return "redirect:/";
    }
    
    @GetMapping("/editar/{id}")
    public String editar(@PathVariable("id") Long id, Model model) {
        Item item = repository.findById(id).orElseThrow(() -> new IllegalArgumentException("ID Invalido"));
        model.addAttribute("items", repository.findAll());
        model.addAttribute("item", item);
        return "index";
    }
}
