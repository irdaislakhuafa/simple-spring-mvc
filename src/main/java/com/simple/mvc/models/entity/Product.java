package com.simple.mvc.models.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    private String id;
    private String code;
    private String name;
    private Double price;
    private String description;
}
