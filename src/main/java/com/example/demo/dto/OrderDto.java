package com.example.demo.dto;

import com.example.demo.model.OrderStatus;
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

    private OrderStatus orderStatus;

    private Integer totalPrice;

    private List<ProductDto> products = new ArrayList<>();

}
