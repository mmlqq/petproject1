package com.example.demo.controller;

import com.example.demo.dto.BucketDto;
import com.example.demo.service.BucketService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/buckets")
public class BucketController {

    private final BucketService bucketService;

    @GetMapping(value = "/{id}")
    public BucketDto findBucketByUserId(@PathVariable Integer id) {
        return bucketService.findByUserId(id);
    }

    @PostMapping
    public void save(@RequestBody BucketDto bucketDto) {
        bucketService.save(bucketDto);
    }

    @PostMapping(value = "/addProduct/{userId}/{productId}")
    public void addProduct(@PathVariable Integer userId, @PathVariable Integer productId) {
        bucketService.addProduct(userId, productId);
    }

    @DeleteMapping(value = "/deleteProduct/{userId}/{productId}")
    public void deleteProduct(@PathVariable Integer userId, @PathVariable Integer productId) {
        bucketService.deleteProduct(userId, productId);
    }

}
