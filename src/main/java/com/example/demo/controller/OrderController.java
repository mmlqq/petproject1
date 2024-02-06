package com.example.demo.controller;

import com.example.demo.dto.OrderDto;
import com.example.demo.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/orders")
public class OrderController {

    private final OrderService orderService;

    @PostMapping(value = "/quick/{productId}")
    public void createOrder(@PathVariable Integer productId, @RequestBody OrderDto orderDto) {
        orderService.create(productId, orderDto);
    }

    @PostMapping(value = "/create")
    public void createByUsingBucket(@RequestBody OrderDto orderDto) {
        orderService.createByUsingBucket(orderDto);
    }

    @GetMapping(value = "/{id}")
    public OrderDto findById(@PathVariable Integer id) {
        return orderService.findById(id);
    }

    @GetMapping(value = "/find")
    public List<OrderDto> findByUserId(@RequestParam Integer userId) {
        return orderService.findByUserId(userId);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteById(@PathVariable Integer id) {
        orderService.deleteById(id);
    }


}
