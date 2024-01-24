package com.example.demo.service;

import com.example.demo.dto.ProductDto;
import com.example.demo.mapper.ProductMapper;
import com.example.demo.model.Product;
import com.example.demo.model.ProductsCategory;
import com.example.demo.repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
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
    public List<ProductDto> findByCategory(ProductsCategory category) {
        return productMapper.to(productRepository.findByCategory(category));
    }

    @Transactional
    public List<ProductDto> sortAllByCategory(ProductsCategory category, String column, String direction) {
        return productMapper.to(findAndSort(category, column, direction));
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

    private List<Product> findAndSort(ProductsCategory category, String column, String direction) {
        List<Product> products = productRepository.findAllByCategory(category,
                direction.equals("asc")
                        ? Sort.by(Sort.Order.asc(column))
                        : Sort.by(Sort.Order.desc(column)));
        return products;
    }
}
