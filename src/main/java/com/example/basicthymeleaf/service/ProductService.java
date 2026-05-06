package com.example.basicthymeleaf.service;

import com.example.basicthymeleaf.model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    List<Product> findAllProducts();

    List<Product> findActiveProducts();

    Optional<Product> findProductById(Long id);

    Product saveProduct(Product product);

    Product updateProduct(Long id, Product product);

    void deleteProduct(Long id);

    void toggleProductStatus(Long id);

    List<Product> searchProducts(String keyword);

    List<String> getAllCategories();

    List<Product> findByCategory(String category);

    long countActiveProducts();
}