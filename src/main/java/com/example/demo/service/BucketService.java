package com.example.demo.service;

import com.example.demo.dto.BucketDto;
import com.example.demo.mapper.BucketMapper;
import com.example.demo.model.Bucket;
import com.example.demo.model.Product;
import com.example.demo.repository.BucketRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BucketService {

    private final BucketRepository bucketRepository;

    private final BucketMapper bucketMapper;

    private final ProductService productService;

    @Transactional
    public Bucket findByUserId(Integer id) {
        return bucketRepository.findByUserId(id);
    }

    @Transactional
    public Bucket fetchByUserId(Integer id) {
        return bucketRepository.findByUserId(id);
    }

    @Transactional
    public void save(BucketDto bucketDto) {
        bucketRepository.save(bucketMapper.to(bucketDto));
    }

    @Transactional
    public void addProduct(Integer userId, Integer productId) {
        Bucket bucket = bucketRepository.findByUserId(userId);
        bucket.getProducts().add(productService.findById(productId));
    }

    @Transactional
    public void deleteProduct(Integer userId, Integer productId) {
        Bucket bucket = bucketRepository.findByUserId(userId);
        bucket.getProducts().remove(productService.findById(productId));
    }

    @Transactional
    public void deleteAllProductsByUserId(Integer userId) {
        Bucket bucket = bucketRepository.findByUserId(userId);
        bucket.getProducts().clear();
    }

    @Transactional
    public Integer getTotalPrice(Integer id) {
        Integer totalPrice = 0;
        List<Product> products = bucketRepository.findByUserId(id).getProducts();
        for (Product product : products) {
            totalPrice = totalPrice + product.getPrice();
        }
        return totalPrice;
    }

    @Transactional
    public Product getProductById(Integer id) {
        return productService.findById(id);
    }
}
