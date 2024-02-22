package com.example.demo.service;

import com.example.demo.dto.OrderDto;
import com.example.demo.mapper.OrderMapper;
import com.example.demo.model.Order;
import com.example.demo.model.OrderStatus;
import com.example.demo.model.Product;
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
    public List<Order> findByUserId(Integer id) {
        return orderRepository.findByUserId(id);
    }

    @Transactional
    public void create(Integer productId, OrderDto orderDto) {
        Order order = new Order();
        order.setUser(userService.fetch(orderDto.getUserId()));
        Product product = productService.findById(productId);
        order.getProducts().add(product);
        order.setAddress(orderDto.getAddress());
        order.setOrderStatus(OrderStatus.CREATED);
        order.setTotalPrice(product.getPrice());
        orderRepository.save(order);
    }

    @Transactional
    public void createByUsingBucket(Integer userId, String address) {
        orderRepository.save(createAnOrder(userId, address));
    }

    @Transactional
    public void deleteById(Integer id) {
        orderRepository.deleteById(id);
    }

    @Transactional
    public void cancelOrder(Integer id, boolean flag) {
        if (flag) {
            Order order = orderRepository.findById(id).orElseThrow(EntityNotFoundException::new);
            order.setOrderStatus(OrderStatus.CANCELED);
        }
    }

    private Order createAnOrder(Integer userId, String address) {
        int totalPrice = 0;
        Order order = new Order();
        order.setUser(userService.fetch(userId));
        order.setAddress(address);

        List<Product> products = bucketService.fetchByUserId(userId).getProducts();
        order.getProducts().addAll(products);

        for (Product product : products) {
            totalPrice = totalPrice + product.getPrice();
        }

        order.setTotalPrice(totalPrice);
        order.setOrderStatus(OrderStatus.CREATED);
        bucketService.deleteAllProductsByUserId(userId);

        return order;
    }

    public boolean bucketIsEmpty(Integer userId) {
        if (bucketService.findByUserId(userId).getProducts().size() == 0) {
            return true;
        } else {
            return false;
        }
    }
}
