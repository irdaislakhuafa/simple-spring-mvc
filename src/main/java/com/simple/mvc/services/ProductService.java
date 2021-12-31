package com.simple.mvc.services;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import com.simple.mvc.models.entity.Product;
import com.simple.mvc.models.repositories.ProductRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private EmailService emailService;

    @Async
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Async
    public Product findById(String id) {
        return productRepository.findById(id).get();
    }

    @Async
    public Boolean save(Product product) throws Exception {
        new Thread(() -> {
            Date date = new Date();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("DD MMMM YYYY");
            String now = simpleDateFormat.format(date);

            String from = "irdhaislakhuafa@gmail.com",
                    to = "rizkiekaputri0911r@gmail.com",
                    subject = "New product has been created",
                    textMessage = "New product with name \"" + product.getName() + "\" and code \"" + product.getCode()
                            + "\" has been created at " + now;

            emailService.sendEmail(from, to, subject, textMessage);
        }).start();
        Product tempProduct = productRepository.save(product);
        return (tempProduct != null);
    }

    @Async
    public void deleteById(String id) {
        productRepository.deleteById(id);
    }

    @Async
    public Boolean update(Product product) {
        Product tempProduct = productRepository.save(product);
        return (tempProduct != null);
    }

    @Async
    public List<Product> findByNameOrPriceOrCode(String name, Double price, String code) {
        return productRepository.findByNameContainsIgnoreCaseOrPriceLessThanEqualOrCodeContains(name, price, code);
    }

    @Async
    public List<Product> findByName(String name) {
        return productRepository.findByNameContainsIgnoreCase(name);
    }
}
