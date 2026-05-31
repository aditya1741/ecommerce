package com.aditya.ecommerce.dtos.checkoutdto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderItemResponseDto {
    private Long productId;
    private String productName;
    private Integer quantity;
    private Double price;
}
