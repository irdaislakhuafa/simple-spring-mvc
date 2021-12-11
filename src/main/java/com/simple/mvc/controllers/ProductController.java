package com.simple.mvc.controllers;

import com.simple.mvc.models.entity.Product;
import com.simple.mvc.services.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    // start add form
    @GetMapping("/add")
    public String addProduct(Model model) {
        // send new Product() object to html file
        model.addAttribute("product", new Product());
        return "products/add";
    }

    @PostMapping("/add")
    public String saveProduct(Product product) {
        // save a new product
        try {
            productService.save(product);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/simple-spring-mvc/views/products";
    }
    // end add form

    // start delete
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") String id) {
        // Product product = productService.findById(id);
        // System.out.println(product);
        productService.deleteById(id);
        return "redirect:/simple-spring-mvc/views/products";
    }
    // end delete

    // start edit
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") String id, Model model) {
        // System.out.println(productService.findById(id));
        Product product = productService.findById(id);
        model.addAttribute("product", product);
        return "products/edit";
    }
    // end edit

    // start update
    @PostMapping("/update")
    public String update(Product product) {
        Product productOld = productService.findById(product.getId());
        Product productNew = product;
        productService.update(productOld, productNew); // return boolean type
        return "redirect:/simple-spring-mvc/views/products";
    }
    // end update
}
