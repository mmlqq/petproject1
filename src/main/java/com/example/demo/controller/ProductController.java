package com.example.demo.controller;

import com.example.demo.dto.FiltersDto;
import com.example.demo.dto.ProductDto;
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

    @GetMapping
    public List<ProductDto> findAll() {
        return productService.findAll();
    }

    @GetMapping(value = "/{id}")
    public ProductDto findById(@PathVariable Integer id) {
        return productService.findById(id);
    }

    @GetMapping(value = "/find{category}")
    public List<ProductDto> findByCategory(@RequestParam ProductsCategory category) {
        return productService.findByCategory(category);
    }

    @PostMapping(value = "/filter")
    public List<ProductDto> findByCategoryAndFilter(@RequestBody FiltersDto filters) {
        return productService.findAllByCategoryAndSortByColumn(filters);
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
