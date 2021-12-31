package com.simple.mvc.services;

import java.text.SimpleDateFormat;
import java.util.Date;
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

    @Autowired
    private EmailService mailService;

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public Product findById(String id) {
        return productRepository.findById(id).get();
    }

    public Boolean save(Product product) throws Exception {

        // new Thread(() -> {
        String now = new SimpleDateFormat("DD MMMM YYYY").format(new Date());
        String from = "irdhaislakhuafa@gmail.com",
                to = "rizkiekaputri0911r@gmail.com",
                subject = "New Products has been added",
                text = "Hello " + to + ", new product with code \"" + product.getCode()
                        + "\" and name \"" + product.getName() + "\" has been created at " + now;

        mailService.sendEmail(from, to, subject, text);
        // }).start();
        System.out.println("Email already send");
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

    public List<Product> findByName(String name) {
        return productRepository.findByNameContainsIgnoreCase(name);
    }
}
