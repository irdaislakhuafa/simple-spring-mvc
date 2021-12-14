package com.simple.mvc.models.repositories;

import java.util.List;

import com.simple.mvc.models.entity.Product;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, String> {
    List<Product> findByNameContainsIgnoreCaseOrPriceLessThanEqualOrCodeContains(String name, Double price,
            String code);
}
