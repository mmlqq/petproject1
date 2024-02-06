package com.example.demo.mapper;

import com.example.demo.dto.OrderDto;
import com.example.demo.model.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    @Mapping(target = "id", ignore = true)
    Order to(OrderDto source);

    @Mapping(source = "source.user.id", target = "userId")
    OrderDto to(Order source);

    List<OrderDto> to(List<Order> source);

}
