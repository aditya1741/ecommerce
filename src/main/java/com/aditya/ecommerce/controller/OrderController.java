package com.aditya.ecommerce.controller;

import com.aditya.ecommerce.dtos.checkoutdto.CheckoutRequestDto;
import com.aditya.ecommerce.dtos.checkoutdto.OrderResponseDto;
import com.aditya.ecommerce.service.IService.IOrderService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private final IOrderService service;

    public OrderController(IOrderService service) {
        this.service = service;
    }
    @PostMapping("/checkout")
    public OrderResponseDto checkout(@RequestBody CheckoutRequestDto checkReqDto){
        return service.checkout(checkReqDto);
    }
    @GetMapping("/{userId}")
    public List<OrderResponseDto> getAllOrder(@PathVariable Long userId){
        return service.getAllOrders(userId);
    }
    @GetMapping
    public OrderResponseDto getOrder(@RequestParam("orderId") Long orderId){
        return service.getOrder(orderId);
    }



}
