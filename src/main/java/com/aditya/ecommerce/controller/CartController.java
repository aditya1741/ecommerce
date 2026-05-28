package com.aditya.ecommerce.controller;

import com.aditya.ecommerce.dtos.cartdto.AddToCartRequestDto;
import com.aditya.ecommerce.dtos.cartdto.CartResponseDto;
import com.aditya.ecommerce.service.CartService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
public class CartController {
    private final CartService cartService;


    public CartController(CartService cartService) {
        this.cartService = cartService;
    }
    @PostMapping("/add")
    public CartResponseDto AddToCart(@RequestBody AddToCartRequestDto cartReqDto){
        System.out.println(cartReqDto.toString());
        return cartService.addToCart(cartReqDto);
    }

    @GetMapping("/{id}")
    public CartResponseDto getCart(@PathVariable Long id){
        return cartService.getCart(id);
    }
}
