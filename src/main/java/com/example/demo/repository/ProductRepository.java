package com.example.demo.repository;

import com.example.demo.model.Product;
import com.example.demo.model.ProductsCategory;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    List<Product> findByCategory(ProductsCategory category);

    List<Product> findAllByCategory(ProductsCategory category, Sort sort);
}
