package com.example.basicthymeleaf.service;

import com.example.basicthymeleaf.model.Product;
import com.example.basicthymeleaf.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Product> findAllProducts() {
        log.debug("Fetching all products");
        return productRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Product> findActiveProducts() {
        log.debug("Fetching all active products");
        return productRepository.findByIsActiveTrue();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Product> findProductById(Long id) {
        log.debug("Fetching product with id: {}", id);
        return productRepository.findById(id);
    }

    @Override
    public Product saveProduct(Product product) {
        log.debug("Saving new product: {}", product.getName());
        return productRepository.save(product);
    }

    @Override
    public Product updateProduct(Long id, Product product) {
        log.debug("Updating product with id: {}", id);
        Product existing = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + id));

        existing.setName(product.getName());
        existing.setDescription(product.getDescription());
        existing.setPrice(product.getPrice());
        existing.setStock(product.getStock());
        existing.setCategory(product.getCategory());
        existing.setImageUrl(product.getImageUrl());
        existing.setIsActive(product.getIsActive());

        return productRepository.save(existing);
    }

    @Override
    public void deleteProduct(Long id) {
        log.debug("Deleting product with id: {}", id);
        productRepository.deleteById(id);
    }

    @Override
    public void toggleProductStatus(Long id) {
        log.debug("Toggling status for product with id: {}", id);
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + id));
        product.setIsActive(!product.getIsActive());
        productRepository.save(product);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Product> searchProducts(String keyword) {
        log.debug("Searching products with keyword: {}", keyword);
        return productRepository.searchProducts(keyword);
    }

    @Override
    @Transactional(readOnly = true)
    public List<String> getAllCategories() {
        return productRepository.findAllCategories();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Product> findByCategory(String category) {
        return productRepository.findByCategoryIgnoreCase(category);
    }

    @Override
    @Transactional(readOnly = true)
    public long countActiveProducts() {
        return productRepository.countByIsActiveTrue();
    }
}