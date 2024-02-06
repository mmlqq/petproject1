package com.example.demo.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
public class OrderDto {

    private Integer id;

    private Integer userId;

    private String address;

    private List<ProductDto> products = new ArrayList<>();

}
