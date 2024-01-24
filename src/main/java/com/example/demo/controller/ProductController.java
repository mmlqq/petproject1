package com.example.demo.controller;

import com.example.demo.dto.ProductDto;
import com.example.demo.dto.ProductsSortingDto;
import com.example.demo.model.ProductsCategory;
import com.example.demo.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/products")
public class ProductController {
    private final ProductService productService;

    @GetMapping(value = "/{id}")
    public ProductDto findById(@PathVariable Integer id) {
        return productService.findById(id);
    }

    @GetMapping
    public List<ProductDto> findAll() {
        return productService.findAll();
    }

//    @GetMapping("/search")
//    public List<ProductDto> findByCategory(@RequestParam ProductsCategory category,
//                                           @RequestParam String column,
//                                           @RequestParam String direction) {
//        return productService.sortAllByCategory(category, column, direction);

    //    }

    @PostMapping(value = "/search")
    public List<ProductDto> sortProducts(@RequestBody ProductsSortingDto productsSortingDto) {
        return productService.sortAllByCategory(
                productsSortingDto.getCategory(),
                productsSortingDto.getColumn(),
                productsSortingDto.getDirection());
    }

    @PostMapping
    public void save(@RequestBody ProductDto productDto) {
        productService.save(productDto);
    }

    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable Integer id) {
        productService.delete(id);
    }

    @PutMapping(value = "/{id}")
    public void update(@RequestBody ProductDto productDto, @PathVariable Integer id) {
        productService.update(productDto, id);
    }
}
