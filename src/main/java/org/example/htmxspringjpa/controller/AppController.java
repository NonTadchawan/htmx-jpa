package org.example.htmxspringjpa.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AppController {
    @GetMapping
    String renderApp() {
        return "app";
    }
    @GetMapping("/add-student")
    String addStudent() {
        return "add-student";
    }
}
