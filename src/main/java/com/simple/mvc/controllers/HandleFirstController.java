package com.simple.mvc.controllers;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Component
@RequestMapping("/")
public class HandleFirstController {
    @GetMapping
    public String firstHandler() {
        return "redirect:/simple-spring-mvc/views/home";
    }
}
