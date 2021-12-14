package com.simple.mvc.services;

import java.util.List;

import javax.transaction.Transactional;

import com.simple.mvc.models.entity.Product;
import com.simple.mvc.models.repositories.ProductRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public Product findById(String id) {
        return productRepository.findById(id).get();
    }

    public Boolean save(Product product) throws Exception {
        Product tempProduct = productRepository.save(product);
        return (tempProduct != null);
    }

    public void deleteById(String id) {
        productRepository.deleteById(id);
    }

    public Boolean update(Product product) {
        Product tempProduct = productRepository.save(product);
        return (tempProduct != null);
    }

    public List<Product> findByNameOrPriceOrCode(String name, Double price, String code) {
        return productRepository.findByNameContainsIgnoreCaseOrPriceLessThanEqualOrCodeContains(name, price, code);
    }
}
