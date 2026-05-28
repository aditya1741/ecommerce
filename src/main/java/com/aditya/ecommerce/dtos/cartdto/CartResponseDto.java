package com.aditya.ecommerce.dtos.cartdto;

import lombok.*;

import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CartResponseDto {
    private Long cartId;
    private Double totalPrice;
    private List<CartItemDto> items;
}
