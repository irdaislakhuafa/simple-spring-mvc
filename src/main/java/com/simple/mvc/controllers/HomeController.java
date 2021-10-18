package com.simple.mvc.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/views")
public class HomeController {
    
    @GetMapping(value = "/hello")
    public String welcome(Model model) {
        String something = "Hello World From Controller";
        model.addAttribute("something", something);
        return "home";
    }

}
