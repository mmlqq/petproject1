package com.example.demo.controller;

import com.example.demo.dto.ProductDto;
import com.example.demo.model.ProductsCategory;
import com.example.demo.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping(value = "/products")
public class ProductController {
    private final ProductService productService;

    @GetMapping("/{id}")
    public String findById(@PathVariable Integer id, Model model) {
        model.addAttribute("categories", ProductsCategory.values());
        model.addAttribute("product", productService.findById(id));
        return "product";
    }

    @GetMapping
    public String findAll(@RequestParam(required = false) String column,
                          @RequestParam(required = false) String direction,
                          Model model) {
        model.addAttribute("categories", ProductsCategory.values());
        model.addAttribute("products", productService.findAll(column, direction));
        return "products";
    }

    @GetMapping("/categories")
    public String findByCategory(@RequestParam ProductsCategory category,
                                 @RequestParam(required = false) String column,
                                 @RequestParam(required = false) String direction,
                                 Model model) {

        model.addAttribute("currentCategory", category);
        model.addAttribute("categories", ProductsCategory.values());
        model.addAttribute("products", productService.findByCategory(category, column, direction));
        return "productsByCategory";
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
    @ResponseBody
    public void update(@RequestBody ProductDto productDto, @PathVariable Integer id) {
        productService.update(productDto, id);
    }
}
