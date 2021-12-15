package com.simple.mvc.controllers;

import com.simple.mvc.models.dto.SearchKeyword;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/simple-spring-mvc/views")
public class HomeController {

    // home.html
    @GetMapping(value = "/home")
    public String welcome(Model model) {
        String something = "Hello World From Controller";
        String btnTxt = "Pergi ke Products";
        String title = "Welcome to my homepage :D";

        model.addAttribute("something", something);
        model.addAttribute("btnText", btnTxt);
        model.addAttribute("title", title);
        model.addAttribute("search", new SearchKeyword());
        return "home";
    }

}
