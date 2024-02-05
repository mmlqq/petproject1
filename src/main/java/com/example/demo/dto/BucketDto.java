package com.example.demo.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
public class BucketDto {

    private List<ProductDto> products = new ArrayList<>();

}
