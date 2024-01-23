package com.example.demo.service;

import com.example.demo.dto.ProductDto;
import com.example.demo.mapper.ProductMapper;
import com.example.demo.model.Product;
import com.example.demo.repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductMapper productMapper;
    private final ProductRepository productRepository;

    @Transactional
    public ProductDto findById(Integer id) {
        Product product = productRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
        return productMapper.to(product);
    }

    @Transactional
    public List<ProductDto> findAll() {
        List<Product> productList = productRepository.findAll();
        return productMapper.to(productList);
    }

    @Transactional
    public void save(ProductDto productDto) {
        Product product = productMapper.to(productDto);
        productRepository.save(product);
    }

    @Transactional
    public void delete(Integer id) {
        productRepository.deleteById(id);
    }

    @Transactional
    public void update(ProductDto productDto, Integer id) {
        Product product = productRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
        productMapper.update(productDto, product);

    }


}
