package com.example.basicthymeleaf.controller;

import com.example.basicthymeleaf.model.User;
import com.example.basicthymeleaf.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/users")
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserService userService;

    private static final List<String> ROLES = List.of("USER", "ADMIN", "MANAGER", "MODERATOR");

    @GetMapping
    public String listUsers(
            @RequestParam(required = false) String keyword,
            Model model) {

        List<User> users;

        if (keyword != null && !keyword.isBlank()) {
            users = userService.searchUsers(keyword);
            model.addAttribute("keyword", keyword);
        } else {
            users = userService.findAllUsers();
        }

        model.addAttribute("users", users);
        model.addAttribute("pageTitle", "Users");
        model.addAttribute("totalUsers", users.size());
        model.addAttribute("activeUsers", userService.countActiveUsers());
        return "users/list";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("roles", ROLES);
        model.addAttribute("pageTitle", "Add New User");
        model.addAttribute("formAction", "/users");
        model.addAttribute("isEdit", false);
        return "users/form";
    }

    @PostMapping
    public String createUser(@Valid @ModelAttribute("user") User user,
                             BindingResult result,
                             Model model,
                             RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            model.addAttribute("roles", ROLES);
            model.addAttribute("pageTitle", "Add New User");
            model.addAttribute("formAction", "/users");
            model.addAttribute("isEdit", false);
            return "users/form";
        }

        try {
            User saved = userService.saveUser(user);
            redirectAttributes.addFlashAttribute("successMessage",
                    "User '" + saved.getFullName() + "' created successfully!");
            return "redirect:/users";
        } catch (Exception e) {
            log.error("Error creating user", e);
            model.addAttribute("errorMessage", "Error: " + e.getMessage());
            model.addAttribute("roles", ROLES);
            model.addAttribute("pageTitle", "Add New User");
            model.addAttribute("formAction", "/users");
            model.addAttribute("isEdit", false);
            return "users/form";
        }
    }

    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable Long id, Model model,
                               RedirectAttributes redirectAttributes) {
        return userService.findUserById(id)
                .map(user -> {
                    model.addAttribute("user", user);
                    model.addAttribute("roles", ROLES);
                    model.addAttribute("pageTitle", "Edit User");
                    model.addAttribute("formAction", "/users/" + id + "/update");
                    model.addAttribute("isEdit", true);
                    return "users/form";
                })
                .orElseGet(() -> {
                    redirectAttributes.addFlashAttribute("errorMessage",
                            "User not found with ID: " + id);
                    return "redirect:/users";
                });
    }

    @PostMapping("/{id}/update")
    public String updateUser(@PathVariable Long id,
                             @Valid @ModelAttribute("user") User user,
                             BindingResult result,
                             Model model,
                             RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            model.addAttribute("roles", ROLES);
            model.addAttribute("pageTitle", "Edit User");
            model.addAttribute("formAction", "/users/" + id + "/update");
            model.addAttribute("isEdit", true);
            return "users/form";
        }

        try {
            User updated = userService.updateUser(id, user);
            redirectAttributes.addFlashAttribute("successMessage",
                    "User '" + updated.getFullName() + "' updated successfully!");
            return "redirect:/users";
        } catch (Exception e) {
            log.error("Error updating user", e);
            model.addAttribute("errorMessage", "Error: " + e.getMessage());
            model.addAttribute("roles", ROLES);
            model.addAttribute("pageTitle", "Edit User");
            model.addAttribute("formAction", "/users/" + id + "/update");
            model.addAttribute("isEdit", true);
            return "users/form";
        }
    }

    @PostMapping("/{id}/delete")
    public String deleteUser(@PathVariable Long id,
                             RedirectAttributes redirectAttributes) {
        try {
            userService.deleteUser(id);
            redirectAttributes.addFlashAttribute("successMessage",
                    "User deleted successfully!");
        } catch (Exception e) {
            log.error("Error deleting user", e);
            redirectAttributes.addFlashAttribute("errorMessage",
                    "Error deleting user: " + e.getMessage());
        }
        return "redirect:/users";
    }

    @PostMapping("/{id}/toggle")
    public String toggleUser(@PathVariable Long id,
                             RedirectAttributes redirectAttributes) {
        try {
            userService.toggleUserStatus(id);
            redirectAttributes.addFlashAttribute("successMessage",
                    "User status updated successfully!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage",
                    "Error updating user status: " + e.getMessage());
        }
        return "redirect:/users";
    }
}