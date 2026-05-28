package com.aditya.ecommerce.service;

import com.aditya.ecommerce.dtos.cartdto.AddToCartRequestDto;
import com.aditya.ecommerce.dtos.cartdto.CartResponseDto;
import com.aditya.ecommerce.entity.Cart;
import com.aditya.ecommerce.entity.User;

public interface CartService {
    CartResponseDto addToCart(AddToCartRequestDto addToCartReqDto);
    CartResponseDto getCart(Long userId);
    CartResponseDto removeItem(Long userId, Long cartItemId);

}
