package com.example.demo.dto;

import com.example.demo.model.ProductsCategory;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ProductsSortingDto {

    private ProductsCategory category;

    private String column;

    private String direction;
}
