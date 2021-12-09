package com.simple.mvc.controllers;

import com.simple.mvc.services.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/simple-spring-mvc/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public String findAll(Model model) {
        model.addAttribute("listProducts", productService.findAll());
        return "products/listProducts";
    }
}
