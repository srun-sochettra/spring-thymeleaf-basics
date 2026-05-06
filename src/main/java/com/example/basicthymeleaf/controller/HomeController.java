package com.example.basicthymeleaf.controller;

import com.example.basicthymeleaf.service.ProductService;
import com.example.basicthymeleaf.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final ProductService productService;
    private final UserService userService;

    @GetMapping({"/", "/home"})
    public String home(Model model) {
        model.addAttribute("pageTitle", "Home");
        model.addAttribute("activeProducts", productService.countActiveProducts());
        model.addAttribute("activeUsers", userService.countActiveUsers());
        model.addAttribute("recentProducts",
                productService.findActiveProducts()
                        .stream()
                        .limit(6)
                        .toList());
        model.addAttribute("categories", productService.getAllCategories());
        return "home";
    }

    @GetMapping("/about")
    public String about(Model model) {
        model.addAttribute("pageTitle", "About Us");
        return "about";
    }
}