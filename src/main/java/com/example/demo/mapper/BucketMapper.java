package com.example.demo.mapper;

import com.example.demo.dto.BucketDto;
import com.example.demo.dto.ProductDto;
import com.example.demo.model.Bucket;
import com.example.demo.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = ProductMapper.class)
public interface BucketMapper {

    @Mapping(target = "id", ignore = true)
    Bucket to(BucketDto source);

    BucketDto to(Bucket source);

    List<Product> to(List<ProductDto> source);

}
