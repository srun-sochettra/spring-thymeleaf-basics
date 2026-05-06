package com.example.basicthymeleaf.repository;

import com.example.basicthymeleaf.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("SELECT p FROM Product p WHERE p.isActive = true ORDER BY p.createdAt DESC")
    List<Product> findByIsActiveTrue();

    @Query("SELECT p FROM Product p WHERE LOWER(p.category) = LOWER(:category)")
    List<Product> findByCategoryIgnoreCase(@Param("category") String category);

    @Query("SELECT p FROM Product p WHERE LOWER(p.name) LIKE LOWER(CONCAT('%', :name, '%'))")
    List<Product> findByNameContainingIgnoreCase(@Param("name") String name);

    @Query("SELECT DISTINCT p.category FROM Product p ORDER BY p.category")
    List<String> findAllCategories();

    @Query("SELECT p FROM Product p WHERE p.isActive = true AND " +
            "(:keyword IS NULL OR " +
            " LOWER(p.name) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            " LOWER(p.description) LIKE LOWER(CONCAT('%', :keyword, '%')))" +
            " ORDER BY p.createdAt DESC")
    List<Product> searchProducts(@Param("keyword") String keyword);

    @Query("SELECT COUNT(p) FROM Product p WHERE p.isActive = true")
    long countByIsActiveTrue();

    @Query("SELECT COUNT(p) FROM Product p WHERE p.category = :category")
    long countByCategory(@Param("category") String category);
}