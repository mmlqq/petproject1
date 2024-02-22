package com.example.demo.service;

import com.example.demo.dto.ProductDto;
import com.example.demo.mapper.ProductMapper;
import com.example.demo.model.Product;
import com.example.demo.model.ProductsCategory;
import com.example.demo.repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.criteria.Path;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductMapper productMapper;

    private final ProductRepository productRepository;


    @Transactional
    public List<Product> findAll(String column, String direction) {
        List<Product> products = (column == null && direction == null)
                ? productRepository.findAll()
                : productRepository.findAll(sortByColumnAndGet(column, direction));
        return products;
    }

    @Transactional
    public Product findById(Integer id) {
        return productRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    @Transactional
    public List<Product> findByCategory(ProductsCategory category, String column, String direction) {
        List<Product> products = (column == null && direction == null)
                ? productRepository.findByCategory(category)
                : productRepository.findAll(getByCategoryAndSortByColumn(category, column, direction));
        return products;
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

    private Specification<Product> sortByColumnAndGet(String columnName, String direction) {
        return (((root, query, criteriaBuilder) -> {
            Path<Object> column = root.get(columnName);
            query.orderBy(direction.equals("asc")
                    ? criteriaBuilder.asc(column)
                    : criteriaBuilder.desc(column));
            return criteriaBuilder.conjunction();
        }));
    }

    private Specification<Product> getByCategoryAndSortByColumn(ProductsCategory category,
                                                                String column,
                                                                String direction) {
        return ((root, query, criteriaBuilder) -> {
            Path<Object> columnName = root.get(column);
            query.orderBy(direction.equals("asc")
                    ? criteriaBuilder.asc(columnName)
                    : criteriaBuilder.desc(columnName));
            return criteriaBuilder.equal(root.get("category"), category);
        });
    }
}
