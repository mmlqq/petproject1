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
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Transactional
    public Product findById(Integer id) {
        return productRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    @Transactional
    public List<Product> findByCategory(ProductsCategory category) {
        return productRepository.findByCategory((category));
    }

    @Transactional
    public List<ProductDto> sortByColumn(String columnName, String direction) {
        return productMapper.to(productRepository.findAll(sortByColumnAndGet(columnName, direction)));
    }

//    @Transactional
//    public List<Product> filterByColumn(ProductsCategory category,
//                                        String column,
//                                        String direction,
//                                        Integer lowestPrice,
//                                        Integer highestPrice) {
//        return productRepository.findAll(getByCategoryAndSortByColumn(
//                category,
//                column,
//                direction,
//                lowestPrice,
//                highestPrice));
//    }

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
                                                                String direction,
                                                                Integer lowestPrice,
                                                                Integer highestPrice) {
        return ((root, query, criteriaBuilder) -> {
            Path<Object> columnName = root.get(column);
            query.orderBy(direction.equals("asc")
                    ? criteriaBuilder.asc(columnName)
                    : criteriaBuilder.desc(columnName));
            return criteriaBuilder.and(
                    criteriaBuilder.between(root.get("price"), lowestPrice, highestPrice),
                    criteriaBuilder.equal(root.get("category"), category)
            );
        });
    }
}
