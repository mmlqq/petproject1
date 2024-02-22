package com.example.demo.controller;

import com.example.demo.model.ProductsCategory;
import com.example.demo.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping(value = "/orders")
public class OrderController {

    private final OrderService orderService;

    @PostMapping(value = "/{userId}")
    public String createByUsingBucket(@RequestParam String address, @PathVariable Integer userId, Model model) {
        model.addAttribute("orders", orderService.findByUserId(1));
        model.addAttribute("categories", ProductsCategory.values());
        if (orderService.bucketIsEmpty(userId)) {
            return "emptyBucket";
        } else {
            orderService.createByUsingBucket(userId, address);
            return "orderCreated";
        }
    }

    @PutMapping()

//    @GetMapping(value = "/{id}")
//    public OrderDto findById(@PathVariable Integer id) {
//        return orderService.findById(id);
//    }

    @GetMapping(value = "/find")
    public String findByUserId(@RequestParam Integer userId, Model model) {
        model.addAttribute("orders", orderService.findByUserId(1));
        model.addAttribute("categories", ProductsCategory.values());
        return "orders";
    }

    @DeleteMapping(value = "/{id}")
    @ResponseBody
    public void deleteById(@PathVariable Integer id) {
        orderService.deleteById(id);
    }


}
