package com.example.basicthymeleaf.config;

import com.example.basicthymeleaf.model.Product;
import com.example.basicthymeleaf.model.User;
import com.example.basicthymeleaf.repository.ProductRepository;
import com.example.basicthymeleaf.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@RequiredArgsConstructor
@Slf4j
public class DataInitializer implements CommandLineRunner {

    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    @Override
    public void run(String... args) {
        if (productRepository.count() == 0) {
            log.info("Initializing sample product data...");
            initProducts();
        }

        if (userRepository.count() == 0) {
            log.info("Initializing sample user data...");
            initUsers();
        }
    }

    private void initProducts() {
        productRepository.save(Product.builder()
                .name("MacBook Pro 16\"")
                .description("Apple MacBook Pro with M3 chip, 16GB RAM, 512GB SSD. " +
                        "Stunning Liquid Retina XDR display with incredible performance.")
                .price(new BigDecimal("2499.99"))
                .stock(15)
                .category("Electronics")
                .imageUrl("https://images.unsplash.com/photo-1517336714731-489689fd1ca8?w=400")
                .isActive(true)
                .build());

        productRepository.save(Product.builder()
                .name("Sony WH-1000XM5")
                .description("Industry-leading noise canceling headphones with Auto NC Optimizer. " +
                        "Up to 30-hour battery life with quick charging.")
                .price(new BigDecimal("349.99"))
                .stock(42)
                .category("Electronics")
                .imageUrl("https://images.unsplash.com/photo-1505740420928-5e560c06d30e?w=400")
                .isActive(true)
                .build());

        productRepository.save(Product.builder()
                .name("Nike Air Max 2024")
                .description("Revolutionary Air cushioning with breathable mesh upper. " +
                        "Perfect for running and everyday wear.")
                .price(new BigDecimal("129.99"))
                .stock(78)
                .category("Footwear")
                .imageUrl("https://images.unsplash.com/photo-1542291026-7eec264c27ff?w=400")
                .isActive(true)
                .build());

        productRepository.save(Product.builder()
                .name("Ergonomic Office Chair")
                .description("Premium ergonomic chair with lumbar support and adjustable armrests. " +
                        "Designed for long hours of comfortable sitting.")
                .price(new BigDecimal("449.00"))
                .stock(20)
                .category("Furniture")
                .imageUrl("https://images.unsplash.com/photo-1592078615290-033ee584e267?w=400")
                .isActive(true)
                .build());

        productRepository.save(Product.builder()
                .name("The Clean Coder")
                .description("A Code of Conduct for Professional Programmers by Robert C. Martin. " +
                        "Essential reading for every software developer.")
                .price(new BigDecimal("39.99"))
                .stock(100)
                .category("Books")
                .imageUrl("https://images.unsplash.com/photo-1532012197267-da84d127e765?w=400")
                .isActive(true)
                .build());

        productRepository.save(Product.builder()
                .name("Samsung 4K OLED TV 55\"")
                .description("Stunning 4K OLED display with HDR10+ support. Smart TV with " +
                        "built-in streaming apps and voice control.")
                .price(new BigDecimal("1299.00"))
                .stock(8)
                .category("Electronics")
                .imageUrl("https://images.unsplash.com/photo-1593784991095-a205069470b6?w=400")
                .isActive(true)
                .build());

        productRepository.save(Product.builder()
                .name("Yoga Mat Premium")
                .description("Non-slip premium yoga mat made from eco-friendly TPE material. " +
                        "6mm thick for optimal comfort and stability.")
                .price(new BigDecimal("49.99"))
                .stock(55)
                .category("Sports")
                .imageUrl("https://images.unsplash.com/photo-1601925228536-1ca3f2e81de4?w=400")
                .isActive(true)
                .build());

        productRepository.save(Product.builder()
                .name("Coffee Maker Deluxe")
                .description("12-cup programmable coffee maker with built-in grinder. " +
                        "Features thermal carafe to keep coffee hot for hours.")
                .price(new BigDecimal("89.99"))
                .stock(33)
                .category("Kitchen")
                .imageUrl("https://images.unsplash.com/photo-1495474472287-4d71bcdd2085?w=400")
                .isActive(false)
                .build());

        log.info("Sample products initialized successfully.");
    }

    private void initUsers() {
        userRepository.save(User.builder()
                .firstName("Alice")
                .lastName("Johnson")
                .email("alice.johnson@example.com")
                .phone("+12125551234")
                .role("ADMIN")
                .address("123 Main St, New York, NY 10001")
                .isActive(true)
                .build());

        userRepository.save(User.builder()
                .firstName("Bob")
                .lastName("Smith")
                .email("bob.smith@example.com")
                .phone("+13105559876")
                .role("MANAGER")
                .address("456 Oak Ave, Los Angeles, CA 90001")
                .isActive(true)
                .build());

        userRepository.save(User.builder()
                .firstName("Carol")
                .lastName("Williams")
                .email("carol.williams@example.com")
                .phone("+17735554321")
                .role("USER")
                .address("789 Pine Rd, Chicago, IL 60601")
                .isActive(true)
                .build());

        userRepository.save(User.builder()
                .firstName("David")
                .lastName("Brown")
                .email("david.brown@example.com")
                .phone("+14045557890")
                .role("USER")
                .address("321 Elm St, Atlanta, GA 30301")
                .isActive(false)
                .build());

        userRepository.save(User.builder()
                .firstName("Emma")
                .lastName("Davis")
                .email("emma.davis@example.com")
                .phone("+16175553456")
                .role("MODERATOR")
                .address("654 Maple Dr, Boston, MA 02101")
                .isActive(true)
                .build());

        log.info("Sample users initialized successfully.");
    }
}