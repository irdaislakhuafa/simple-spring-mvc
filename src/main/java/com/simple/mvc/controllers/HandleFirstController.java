package com.simple.mvc.controllers;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Component
@RequestMapping("/")
public class HandleFirstController {

    @GetMapping
    @Async
    public String firstHandler() {
        return "redirect:/simple-spring-mvc/views/home";
    }
}
