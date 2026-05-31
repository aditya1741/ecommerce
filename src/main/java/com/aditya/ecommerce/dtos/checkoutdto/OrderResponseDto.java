package com.aditya.ecommerce.dtos.checkoutdto;

import lombok.*;

import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderResponseDto {
    private Long orderId;
    private Double totalAmount;
    private String status;
    private List<OrderItemResponseDto> items;
}
