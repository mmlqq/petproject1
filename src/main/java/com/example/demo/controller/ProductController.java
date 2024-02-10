package com.example.demo.controller;

import com.example.demo.dto.ProductDto;
import com.example.demo.model.Product;
import com.example.demo.model.ProductsCategory;
import com.example.demo.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping(value = "/products")
public class ProductController {
    private final ProductService productService;

    @GetMapping("/{id}")
    public String findById(@PathVariable Integer id, Model model) {
        Product product = productService.findById(id);
        model.addAttribute("categories", ProductsCategory.values());
        model.addAttribute("product", product);
        return "product";
    }

    @GetMapping
    public String findAll(Model model) {
        List<Product> productList = productService.findAll();
        model.addAttribute("categories", ProductsCategory.values());
        model.addAttribute("products", productList);
        return "products";
    }

    @GetMapping("/")
    public String findByCategory(@RequestParam ProductsCategory category, Model model) {
        List<Product> productsByCategory = productService.findByCategory(category);
        model.addAttribute("categories", ProductsCategory.values());
        model.addAttribute("products", productsByCategory);
        return "products";
    }

//    @GetMapping(value = "/{column}/{direction}/{lowestPrice}/{highestPrice}")
//    public String filterByColumn(@PathVariable String column,
//                                 Model model) {
//        List<Product> productsList = productService.sortByColumn(column);
//        model.addAttribute("categories", ProductsCategory.values());
//        model.addAttribute("products", productsList);
//        return "products";
//    }

//    @GetMapping(value = "/{columnName}/{direction}")
//    public List<Product> findAndSortByColumn(@PathVariable String columnName, @PathVariable String direction){
//        return productService.sortByColumn(columnName, direction);
//    }

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
