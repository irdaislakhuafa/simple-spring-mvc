package com.simple.mvc.controllers;

import com.simple.mvc.models.entity.Product;
import com.simple.mvc.services.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/simple-spring-mvc/views/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public String findAll(Model model) {
        model.addAttribute("listProducts", productService.findAll());
        return "products/list";
    }

    // form add product
    @GetMapping("/add")
    public String addProduct(Model model) {
        // send new Product() object to html file
        model.addAttribute("product", new Product());
        return "products/add";
    }

    @PostMapping("/add")
    public String saveProduct(Product product, Model model) {
        // save a new product
        try {
            productService.save(product);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/simple-spring-mvc/views/products";
    }
}
