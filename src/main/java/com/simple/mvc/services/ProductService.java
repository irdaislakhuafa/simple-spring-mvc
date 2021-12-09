package com.simple.mvc.services;

import java.util.Arrays;
import java.util.List;

import com.simple.mvc.models.entity.Product;

import org.springframework.stereotype.Service;

@Service
public class ProductService {

    private static List<Product> listProducts = Arrays.asList(
            new Product(1L, "Product 1", 1000.00, "This is description of the product 1"),
            new Product(2L, "Product 2", 2000.00, "This is description of the product 2"),
            new Product(3L, "Product 3", 3000.00, "This is description of the product 3"),
            new Product(4L, "Product 4", 4000.00, "This is description of the product 4"),
            new Product(5L, "Product 5", 5000.00, "This is description of the product 5"),
            new Product(6L, "Product 6", 6000.00, "This is description of the product 6"));

    public List<Product> findAll() {
        return ProductService.listProducts;
    }

    public Product findById(Long id) {
        String idString = Long.toString(id);
        return ProductService.listProducts.get(Integer.valueOf(idString));
    }
}
