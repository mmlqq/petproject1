package com.example.demo.controller;

import com.example.demo.dto.ProductDto;
import com.example.demo.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.mapstruct.MappingTarget;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/products")
public class ProductController {
    private final ProductService productService;

    @GetMapping(value = "/{id}")
    public ProductDto findById(@PathVariable Integer id){
        return productService.findById(id);
    }

    @GetMapping(value = "/all")
    public List<ProductDto> findAll(){
        return productService.findAll();
    }

    @PostMapping
    public void save(@RequestBody ProductDto productDto){
        productService.save(productDto);
    }
}
