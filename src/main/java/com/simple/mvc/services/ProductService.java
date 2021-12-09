package com.simple.mvc.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.simple.mvc.models.entity.Product;

import org.springframework.stereotype.Service;

@Service
public class ProductService {
    // Arrays.asList() is fixed size and cannot add new element
    private static List<Product> listProducts = new ArrayList<>(
            Arrays.asList(
                    new Product("1", "001", "Product 1", 1000.00, "This is description of the product 1"),
                    new Product("2", "002", "Product 2", 2000.00, "This is description of the product 2"),
                    new Product("3", "003", "Product 3", 3000.00, "This is description of the product 3"),
                    new Product("4", "004", "Product 4", 4000.00, "This is description of the product 4"),
                    new Product("5", "005", "Product 5", 5000.00, "This is description of the product 5"),
                    new Product("6", "006", "Product 6", 6000.00, "This is description of the product 6"),
                    new Product("7", "007", "Product 7", 6000.00, "This is description of the product 7")));

    public List<Product> findAll() {
        System.out.println(listProducts.get(listProducts.size() - 1));
        return ProductService.listProducts;
    }

    public Product findById(Long id) {
        String idString = Long.toString(id);
        return ProductService.listProducts.get(Integer.valueOf(idString));
    }

    public Boolean save(Product product) {
        Integer newId = listProducts.size() + 1;
        product.setId(newId.toString());
        try {
            return listProducts.add(product);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
