package com.example.demo.dto;

import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Setter
@Getter
public class ProductDto {

    private Integer id;

    private String name;

    private Integer price;

    private String description;
}
