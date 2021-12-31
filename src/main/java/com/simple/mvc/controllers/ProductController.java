package com.simple.mvc.controllers;

import javax.servlet.http.HttpSession;

import com.simple.mvc.helpers.BaseHelpers;
import com.simple.mvc.models.dto.SearchKeyword;
import com.simple.mvc.models.entity.Product;
import com.simple.mvc.services.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
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

    @Autowired // get session in Spring Boot
    private HttpSession session;

    private static final String productsUrl = BaseHelpers.getBaseUrl() + "/products";

    @GetMapping
    @Async
    public String findAll(Model model) {
        model.addAttribute("search", new SearchKeyword());
        model.addAttribute("listProducts", productService.findAll());
        model.addAttribute("productsUrl", productsUrl);
        return "products/list";
    }

    // start add form
    @GetMapping("/add")
    @Async
    public String addProduct(Model model) {
        // send new Product() object to html file
        model.addAttribute("product", new Product());
        model.addAttribute("baseUrl", BaseHelpers.getBaseUrl());
        return "products/add";
    }

    @PostMapping("/add")
    @Async
    public String saveProduct(Product product) {
        // save a new product
        new Thread(() -> {
            try {
                productService.save(product);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
        return "redirect:" + productsUrl;
    }
    // end add form

    // start delete
    @GetMapping("/delete/{id}")
    @Async
    public String delete(@PathVariable("id") String id) {
        // Product product = productService.findById(id);
        // System.out.println(product);
        productService.deleteById(id);
        return "redirect:" + productsUrl;
    }
    // end delete

    // start edit
    @GetMapping("/edit/{id}")
    @Async
    public String edit(@PathVariable("id") String id, Model model) {
        // System.out.println(productService.findById(id));
        Product product = productService.findById(id);
        model.addAttribute("product", product);
        model.addAttribute("productsUrl", productsUrl);
        return "products/edit";
    }
    // end edit

    // start update
    @PostMapping("/update")
    @Async
    public String update(Product product) {
        try {
            productService.update(product); // return boolean type
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println(product);
            e.printStackTrace();
        }
        return "redirect:" + productsUrl;
    }
    // end update

    // start search
    @PostMapping("/search")
    @Async
    public String search(SearchKeyword keyword, Model model) {
        // Double price = Double.valueOf(0);
        // try {
        // price = Double.valueOf(keyword.getKeyword());
        // } catch (Exception e) {
        // // TODO: handle exception
        // e.printStackTrace();
        // }
        model.addAttribute("search", keyword);
        // model.addAttribute("listProducts",
        // productService.findByNameOrPriceOrCode(keyword.getKeyword(), price,
        // keyword.getKeyword()));
        model.addAttribute("listProducts", productService.findByName(keyword.getKeyword()));
        model.addAttribute("productsUrl", productsUrl);

        // add attribut to session
        session.setAttribute("searchKeyword", keyword.getKeyword());

        return "products/list";
    }

    @GetMapping("/search")
    @Async
    public String redirectSearchToProductList() {
        return "redirect:" + productsUrl;
    }
    // end search
}
