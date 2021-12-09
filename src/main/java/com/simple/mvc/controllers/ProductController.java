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
        String title = "List All Products";

        model.addAttribute("listProducts", productService.findAll());
        model.addAttribute("title", title);
        return "products/listProducts";
    }

    // form add product
    @GetMapping("/add")
    public String addProduct(Model model) {
        // send new Product() object to html file
        model.addAttribute("product", new Product());
        return "products/addProduct";
    }

    @PostMapping("/add")
    public String saveProduct(Product product) {
        System.out.println(product.getName());
        return "redirect:/simple-spring-mvc/views/products";
    }
}
