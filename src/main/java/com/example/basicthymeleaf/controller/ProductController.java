package com.example.basicthymeleaf.controller;

import com.example.basicthymeleaf.model.Product;
import com.example.basicthymeleaf.service.ProductService;
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
@RequestMapping("/products")
@RequiredArgsConstructor
@Slf4j
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public String listProducts(
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) String category,
            Model model) {

        List<Product> products;

        if (keyword != null && !keyword.isBlank()) {
            products = productService.searchProducts(keyword);
            model.addAttribute("keyword", keyword);
        } else if (category != null && !category.isBlank()) {
            products = productService.findByCategory(category);
            model.addAttribute("selectedCategory", category);
        } else {
            products = productService.findAllProducts();
        }

        model.addAttribute("products", products);
        model.addAttribute("categories", productService.getAllCategories());
        model.addAttribute("pageTitle", "Products");
        model.addAttribute("totalProducts", products.size());
        return "products/list";
    }

    @GetMapping("/{id}")
    public String productDetail(@PathVariable Long id, Model model,
                                RedirectAttributes redirectAttributes) {
        return productService.findProductById(id)
                .map(product -> {
                    model.addAttribute("product", product);
                    model.addAttribute("pageTitle", product.getName());
                    return "products/detail";
                })
                .orElseGet(() -> {
                    redirectAttributes.addFlashAttribute("errorMessage",
                            "Product not found with ID: " + id);
                    return "redirect:/products";
                });
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("product", new Product());
        model.addAttribute("categories", productService.getAllCategories());
        model.addAttribute("pageTitle", "Add New Product");
        model.addAttribute("formAction", "/products");
        model.addAttribute("isEdit", false);
        return "products/form";
    }

    @PostMapping
    public String createProduct(@Valid @ModelAttribute("product") Product product,
                                BindingResult result,
                                Model model,
                                RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            model.addAttribute("categories", productService.getAllCategories());
            model.addAttribute("pageTitle", "Add New Product");
            model.addAttribute("formAction", "/products");
            model.addAttribute("isEdit", false);
            return "products/form";
        }

        try {
            Product saved = productService.saveProduct(product);
            redirectAttributes.addFlashAttribute("successMessage",
                    "Product '" + saved.getName() + "' created successfully!");
            return "redirect:/products/" + saved.getId();
        } catch (Exception e) {
            log.error("Error creating product", e);
            model.addAttribute("errorMessage", "Error creating product: " + e.getMessage());
            model.addAttribute("categories", productService.getAllCategories());
            model.addAttribute("pageTitle", "Add New Product");
            model.addAttribute("formAction", "/products");
            model.addAttribute("isEdit", false);
            return "products/form";
        }
    }

    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable Long id, Model model,
                               RedirectAttributes redirectAttributes) {
        return productService.findProductById(id)
                .map(product -> {
                    model.addAttribute("product", product);
                    model.addAttribute("categories", productService.getAllCategories());
                    model.addAttribute("pageTitle", "Edit Product");
                    model.addAttribute("formAction", "/products/" + id + "/update");
                    model.addAttribute("isEdit", true);
                    return "products/form";
                })
                .orElseGet(() -> {
                    redirectAttributes.addFlashAttribute("errorMessage",
                            "Product not found with ID: " + id);
                    return "redirect:/products";
                });
    }

    @PostMapping("/{id}/update")
    public String updateProduct(@PathVariable Long id,
                                @Valid @ModelAttribute("product") Product product,
                                BindingResult result,
                                Model model,
                                RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            model.addAttribute("categories", productService.getAllCategories());
            model.addAttribute("pageTitle", "Edit Product");
            model.addAttribute("formAction", "/products/" + id + "/update");
            model.addAttribute("isEdit", true);
            return "products/form";
        }

        try {
            Product updated = productService.updateProduct(id, product);
            redirectAttributes.addFlashAttribute("successMessage",
                    "Product '" + updated.getName() + "' updated successfully!");
            return "redirect:/products/" + id;
        } catch (Exception e) {
            log.error("Error updating product", e);
            model.addAttribute("errorMessage", "Error updating product: " + e.getMessage());
            model.addAttribute("categories", productService.getAllCategories());
            model.addAttribute("pageTitle", "Edit Product");
            model.addAttribute("formAction", "/products/" + id + "/update");
            model.addAttribute("isEdit", true);
            return "products/form";
        }
    }

    @PostMapping("/{id}/delete")
    public String deleteProduct(@PathVariable Long id,
                                RedirectAttributes redirectAttributes) {
        try {
            productService.deleteProduct(id);
            redirectAttributes.addFlashAttribute("successMessage",
                    "Product deleted successfully!");
        } catch (Exception e) {
            log.error("Error deleting product", e);
            redirectAttributes.addFlashAttribute("errorMessage",
                    "Error deleting product: " + e.getMessage());
        }
        return "redirect:/products";
    }

    @PostMapping("/{id}/toggle")
    public String toggleProduct(@PathVariable Long id,
                                RedirectAttributes redirectAttributes) {
        try {
            productService.toggleProductStatus(id);
            redirectAttributes.addFlashAttribute("successMessage",
                    "Product status updated successfully!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage",
                    "Error updating product status: " + e.getMessage());
        }
        return "redirect:/products";
    }
}