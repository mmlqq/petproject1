package com.example.demo.service;

import com.example.demo.dto.BucketDto;
import com.example.demo.mapper.BucketMapper;
import com.example.demo.model.Bucket;
import com.example.demo.repository.BucketRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BucketService {

    private final BucketRepository bucketRepository;

    private final BucketMapper bucketMapper;

    private final ProductService productService;

    @Transactional
    public BucketDto findByUserId(Integer id) {
        return bucketMapper.to(bucketRepository.findByUserId(id));
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
        bucket.getProducts().add(productService.fetch(productId));
    }

    @Transactional
    public void deleteProduct(Integer userId, Integer productId) {
        Bucket bucket = bucketRepository.findByUserId(userId);
        bucket.getProducts().remove(productService.fetch(productId));
    }

    @Transactional
    public void deleteAllProducts(Integer userId) {
        Bucket bucket = bucketRepository.findByUserId(userId);
        bucket.getProducts().clear();
    }
}
