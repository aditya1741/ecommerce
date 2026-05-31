package com.aditya.ecommerce.service.IService;

import com.aditya.ecommerce.dtos.checkoutdto.CheckoutRequestDto;
import com.aditya.ecommerce.dtos.checkoutdto.OrderResponseDto;

import java.util.List;

public interface IOrderService {
    OrderResponseDto checkout(CheckoutRequestDto checkoutReqDto);
    List<OrderResponseDto> getAllOrders(Long userId);
    OrderResponseDto getOrder(Long orderId);


}
