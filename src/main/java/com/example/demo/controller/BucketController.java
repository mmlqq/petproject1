package com.example.demo.controller;

import com.example.demo.dto.BucketDto;
import com.example.demo.model.Product;
import com.example.demo.model.ProductsCategory;
import com.example.demo.service.BucketService;
import com.example.demo.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping(value = "/buckets")
public class BucketController {

    private final BucketService bucketService;
    private final ProductService productService;

    @GetMapping(value = "/{id}")
    public String findBucketByUserId(@PathVariable Integer id, Model model) {
        List<Product> products = bucketService.findByUserId(id).getProducts();
        model.addAttribute("categories", ProductsCategory.values());
        model.addAttribute("count", bucketService.findByUserId(id).getProducts().size());
        model.addAttribute("totalPrice", bucketService.getTotalPrice(id));
        if (products.size() == 0) {
            return "emptyBucket";
        } else {
            model.addAttribute("bucketProducts", products);
            return "bucket";
        }
    }

    @PostMapping
    public void save(@RequestBody BucketDto bucketDto) {
        bucketService.save(bucketDto);
    }

    @PostMapping(value = "/addProduct/{userId}/{productId}")
    public String addProduct(@PathVariable Integer userId, @PathVariable Integer productId, Model model) {
        bucketService.addProduct(userId, productId);
        model.addAttribute("categories", ProductsCategory.values());
        model.addAttribute("product", productService.findById(productId));
        return "product";
    }

    @DeleteMapping(value = "/deleteProduct/{userId}/{productId}")
    public String deleteProduct(@PathVariable Integer userId, @PathVariable Integer productId, Model model) {
        bucketService.deleteProduct(userId, productId);
        model.addAttribute("categories", ProductsCategory.values());
        model.addAttribute("count", bucketService.findByUserId(userId).getProducts().size());
        model.addAttribute("bucketProducts", bucketService.findByUserId(userId).getProducts());
        model.addAttribute("totalPrice", bucketService.getTotalPrice(userId));
        return "bucket";
    }
}
