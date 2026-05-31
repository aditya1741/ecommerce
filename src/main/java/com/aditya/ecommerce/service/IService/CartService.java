package com.aditya.ecommerce.service.IService;

import com.aditya.ecommerce.dtos.cartdto.AddToCartRequestDto;
import com.aditya.ecommerce.dtos.cartdto.CartResponseDto;

public interface CartService {
    CartResponseDto addToCart(AddToCartRequestDto addToCartReqDto);
    CartResponseDto getCart(Long userId);
    CartResponseDto removeItem(Long userId, Long cartItemId);

}
