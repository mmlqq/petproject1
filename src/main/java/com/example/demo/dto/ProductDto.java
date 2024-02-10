package com.example.demo.dto;

import com.example.demo.model.ProductsCategory;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ProductDto {

    private Integer id;

    private String name;

    private Integer price;

    private String description;

    private String imageLink;

    private ProductsCategory category;
}
