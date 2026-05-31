package com.aditya.ecommerce.mapper;

import com.aditya.ecommerce.dtos.checkoutdto.OrderItemResponseDto;
import com.aditya.ecommerce.dtos.checkoutdto.OrderResponseDto;
import com.aditya.ecommerce.entity.Order;
import com.aditya.ecommerce.entity.OrderItem;

public class OrderMapper {
    public static OrderResponseDto toResponse(Order order){
        OrderResponseDto orderResDto = OrderResponseDto.builder()
                .orderId(order.getId())
                .status(order.getStatus())
                .items(
                        order.getItems()
                        .stream()
                        .map(orderItem -> OrderItemResponseDto
                        .builder()
                        .productId(orderItem.getProduct().getId())
                                        .productName(orderItem.getProduct().getName())
                                        .quantity(orderItem.getQuantity())
                                        .price(orderItem.getTotalPrice())
                                        .build()
                        ).toList())
                .totalAmount(order.getTotalAmount())
                .build();
        return orderResDto;
    }

}
