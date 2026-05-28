package com.aditya.ecommerce.mapper;

import com.aditya.ecommerce.dtos.cartdto.CartItemDto;
import com.aditya.ecommerce.dtos.cartdto.CartResponseDto;
import com.aditya.ecommerce.entity.Cart;
import com.aditya.ecommerce.entity.CartItem;
public class CartMapper {
    public static CartResponseDto toCartResponse(Cart cart) {
        CartResponseDto cartRes = CartResponseDto.builder()
                .cartId(cart.getId())
                .items(cart.getCartItems()
                        .stream()
                        .map(cartItem -> CartItemDto.builder()
                                .id(cartItem.getId())
                                .price(cartItem.getProduct().getPrice())
                                .productName(cartItem.getProduct().getName())
                                .productId(cartItem.getProduct().getId())
                                .quantity(cartItem.getQuantity())
                                .totalPrice(cartItem.getTotalPrice())
                                .build()
                        ).toList()
                ).totalPrice(
                        cart
                                .getCartItems()
                                .stream()
                                .mapToDouble(CartItem::getTotalPrice)
                                .sum()
                ).build();
        return cartRes;
    }
}
