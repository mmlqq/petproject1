package com.example.demo.service;

import com.example.demo.dto.OrderDto;
import com.example.demo.mapper.OrderMapper;
import com.example.demo.model.Order;
import com.example.demo.repository.OrderRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    private final ProductService productService;

    private final UserService userService;

    private final BucketService bucketService;

    private final OrderMapper orderMapper;

    @Transactional
    public OrderDto findById(Integer id) {
        return orderMapper.to(orderRepository.findById(id).orElseThrow(EntityNotFoundException::new));
    }

    @Transactional
    public List<OrderDto> findByUserId(Integer id) {
        return orderMapper.to(orderRepository.findByUserId(id));
    }

    @Transactional
    public void create(Integer productId, OrderDto orderDto) {
        Order order = new Order();
        order.setUser(userService.fetch(orderDto.getUserId()));
        order.getProducts().add(productService.fetch(productId));
        order.setAddress(orderDto.getAddress());
        orderRepository.save(order);
    }

    @Transactional
    public void createByUsingBucket(OrderDto orderDto) {
        Order order = new Order();
        order.setUser(userService.fetch(orderDto.getUserId()));
        order.setAddress(orderDto.getAddress());
        order.getProducts().addAll(bucketService.fetchByUserId(orderDto.getUserId()).getProducts());
        orderRepository.save(order);
        bucketService.deleteAllProducts(orderDto.getUserId());
    }

    @Transactional
    public void deleteById(Integer id) {
        orderRepository.deleteById(id);
    }
}
