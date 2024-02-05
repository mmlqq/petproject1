package com.example.demo.mapper;

import com.example.demo.dto.BucketDto;
import com.example.demo.model.Bucket;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = ProductMapper.class)
public interface BucketMapper {

    @Mapping(target = "id", ignore = true)
    Bucket to(BucketDto source);

    BucketDto to(Bucket source);

}
